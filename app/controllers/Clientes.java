package controllers;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import models.Cliente;
import models.Conta;
import play.data.validation.Valid;
import play.libs.Crypto;
import play.libs.Mail;
import play.mvc.Controller;

public class Clientes extends Controller{
	
	public static void cadastro(Cliente cliente) {
		render(cliente);
	}
	 
	public static void recuperarSenha(String Email) {
		render(Email);
	}
	
	public static void dadosCliente(Cliente cliente) {
		long id = new Long(session.get("idClienteLogado"));
		cliente = Cliente.findById(id);
		render(cliente);
	}
	
	public static void salvar(@Valid Cliente cliente, String confirmarSenha) {
	if (cliente.isUnico()) {
		if(cliente.senha.equals(confirmarSenha)) {		
		
			if(validation.hasErrors()) {
				validation.keep();
				params.flash();
				cadastro(cliente);
		    }
		
		
		cliente.save();
		
		Conta conta = new Conta();
		conta.numeroConta = (cliente.id.intValue() + 1000);
		conta.save();
		
		cliente.conta = conta;
		cliente.save();
		
		flash.success("Cliente adicionado com sucesso!");
		renderTemplate("Logins/login.html", cliente);
		
		} else {
			flash.error("Senhas não são iguais");
			cadastro(cliente);
		} 
	} else {
		flash.error("Este E-mail já está em uso. Tente outro.");
	}
		cadastro(cliente);
	}
	
	
	public static void atualizar(@Valid Cliente cliente, String confirmarSenha) {
		
			System.out.println(validation.hasErrors());
			
			if(validation.hasErrors()) {
				validation.keep();
				params.flash();
				dadosCliente(cliente);
			}
			
		cliente.save();
		flash.success("Dados atualizados com sucesso!");
		renderTemplate("Clientes/dadosCliente.html", cliente);
	} 
	
	public static void detalhes(Cliente cliente) {
		render(cliente); 
	}
	
	public static void buscar(String busca) {
		List<Cliente> clientes = Cliente.find("nome like ? or email like ?", "%" + busca + "%", "%" + busca + "%").fetch();
		renderTemplate("Clientes/listar.html", clientes);
	}
	
	public static void fotoCliente(Long id) {
		Cliente cliente = Cliente.findById(id);
		notFoundIfNull(cliente);
		response.setContentTypeIfNotSet(cliente.foto.type());
		renderBinary(cliente.foto.get());
	} 
	
	 
	public static void novaSenha(Cliente cliente) throws EmailException{
		Cliente c = Cliente.find("email = ?", cliente.email).first();
		
		if(c != null) {
			String nSenha = Crypto.passwordHash(new Date().toString());
			nSenha = nSenha.substring(0, 4);
			
			c.senha = nSenha;
			c.save();
			
			HtmlEmail email = new HtmlEmail();
			email.addTo(c.email, c.nome);
			email.setFrom("suporte.projeto.siap@gmail.com", "SIAP");
			email.setSubject("Nova senha gerada para o sistema SIAP");
			email.setHtmlMsg("<h1>SIAP - Nova Senha </h1><p>Sua senha agora é "+ nSenha +".</p>");
			Mail.send(email);
			
			flash.success("Uma nova senha foi emviada para seu E-mail!");
			Logins.login();
			
		}
	}
}
