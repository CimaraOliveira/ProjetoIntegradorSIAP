package controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import models.Cliente;
import models.FrenteDeCaixa;
import play.mvc.Controller;

public class Logins extends Controller{
	
	public static void login() {
		render();
	}
	
	public static void autenticar(String login, String senha) {
		boolean tipoLogin = false;
		Cliente cliente = null;
		for(int i = 0; i < login.length() ; i++) {
			if(login.charAt(i) == '@') {
				tipoLogin = true;
			}
		}

		if(tipoLogin) {
			cliente = Cliente.find("email = ? and senha = ?", login, senha).first();
		}
		else {
			cliente = Cliente.find("telefone = ? and senha = ?", login, senha).first();
		}
		  
		if(cliente == null){
			flash.error("Por favor, entre com usuário e senha corretos.");
			login();
		}
		else if(login.equals("admin@123") && senha.equals("admin")) {
			session.put("idClienteLogado", cliente.id);
			
			FrenteDeCaixa frenteDeCaixa = new FrenteDeCaixa();
			frenteDeCaixa.abrirCaixa = new Date();
			frenteDeCaixa.save();
			session.put("idFrenteCaixa", frenteDeCaixa.id);
			 
			Operadores.operador(); 
			  
		}
		else{
			session.put("idClienteLogado", cliente.id);
			Application.index(); 
		}
	}
	
	public static void logout() {
		
		session.clear();
		System.out.println("logout");
		login();
	}
	
	public static void logoutOperador() {
		long id = new Long(session.get("idFrenteCaixa"));
		FrenteDeCaixa frenteDeCaixa = FrenteDeCaixa.findById(id);
		frenteDeCaixa.fecharCaixa = new Date();
		
		frenteDeCaixa.save();
		
		session.clear();
		System.out.println("logout");
		login();
	}
}
