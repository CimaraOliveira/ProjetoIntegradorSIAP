package controllers;

import models.Conta;
import models.FrenteDeCaixa;
import play.mvc.Controller;

public class CompraRefeicao extends Controller{
	
	public static void CompraVerificarNumeroConta(Integer numeroConta){
		Conta conta = Conta.find("numeroConta = ?", numeroConta).first();
		
		if(conta == null){
			flash.error("Erro!!! Informe um número de conta válida!");
			Operadores.operador();
		}else{
			Long idConta = conta.id;
			ComprarRefeicao(idConta); 
		}
		
	}
	
	public static void ComprarRefeicao(Long idConta) {
		Conta conta = Conta.findById(idConta);
		if(conta.saldo > 0) {
			conta.saldo -= 1;
			conta.save();
			long id = new Long(session.get("idFrenteCaixa"));
			FrenteDeCaixa frenteDeCaixa = FrenteDeCaixa.findById(id);
			frenteDeCaixa.totalVendaRefeicao++;
			frenteDeCaixa.save();
			flash.success("Compra realizada com sucesso!");
		}
		else {
			flash.error("Saldo insuficiente faça uma recarga!");
		}
        Operadores.operador();
	}

}
