package controllers;

import java.util.Date;
import java.util.List;

import models.Cliente;
import models.FrenteDeCaixa;
import play.mvc.Controller;

public class FrenteDeCaixas extends Controller{
	
	public static void fechaCaixa() {
		long idOp = new Long(session.get("idClienteLogado"));
		Cliente cliente = Cliente.findById(idOp);
		
		long id = new Long(session.get("idFrenteCaixa"));
		FrenteDeCaixa frenteDeCaixa = FrenteDeCaixa.findById(id);
		frenteDeCaixa.fecharCaixa = new Date();
		
		frenteDeCaixa.save();
		render(frenteDeCaixa, cliente);
	}
	
 	public static void listagemCaixa() {
		List<FrenteDeCaixa> frenteDeCaixas = FrenteDeCaixa.findAll();
		render(frenteDeCaixas); 
	}

}
