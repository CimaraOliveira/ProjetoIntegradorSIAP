package controllers;

import java.util.Date;
import java.util.List;


import models.Cliente;
import models.Conta;
import models.FrenteDeCaixa;
import models.Recarga;
import play.mvc.Controller;

public class Contas extends Controller {

	
		
	public static void detalhesConta(Conta conta) {
		long id = new Long(session.get("idClienteLogado"));
		Cliente cliente = Cliente.findById(id);
		
		conta = cliente.conta;
		List<Recarga> recargas = Recarga.find("conta_id = ?", conta.id).fetch();
		render(conta,cliente,recargas);
	}
	
	public static void contaListar() {
		List<Conta> contas = Conta.findAll();
		render(contas);
	}
	
		
	public static void editar(Long id) {
		Conta conta = Conta.findById(id);
		renderTemplate("Contas/formConta.html", conta);
	}
	
	public static void remover(Long id) {
		Conta conta = Conta.findById(id);
		conta.delete();
		flash.success("Conta removida com sucesso!");
		contaListar();
	}
}
