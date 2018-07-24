package controllers;

import java.util.Date;

import models.Cliente;
import models.Conta;
import models.FrenteDeCaixa;
import models.Recarga;
import play.data.validation.Valid;
import play.mvc.Controller;

  public class Recargas extends Controller{
	  
	  public static void salvarRecarga(Conta conta) {
			
			conta.saldo += conta.recarga;
			conta.save();
			
			Recarga recarga = new Recarga();
			recarga.recarga = conta.recarga;
			recarga.pagamento = conta.pagamento;
			recarga.conta = conta;
			recarga.data = new Date();
			recarga.save();
			
			long id = new Long(session.get("idFrenteCaixa"));
			FrenteDeCaixa frenteDeCaixa = FrenteDeCaixa.findById(id);
			frenteDeCaixa.totalVendaRecarga += recarga.recarga;
			frenteDeCaixa.save();

			flash.success("recarga salva com sucesso!");

			renderTemplate("Recargas/detalhesRecarga.html", recarga);
		}
	  
	  public static void formConta(Long idConta) {
			Conta conta = Conta.findById(idConta);
			Cliente cliente = Cliente.findById(idConta);
			
			render(conta, cliente);
		} 
	  
	  public static void verificarNumeroConta(Integer numeroConta){
			Conta conta = Conta.find("numeroConta = ?", numeroConta).first();
			
			if(conta == null){
				flash.error("Erro!!! Informe um número de conta válida!");
				Operadores.operador();
			}else{
				Long idConta = conta.id;
				formConta(idConta); 
			}
			
		}
	
	public static void salvar(@Valid Recarga recarga) {		
		recarga.save();
		render(recarga);
				
		flash.success("Recarga salva com sucesso!");
		
	}
	
	public static void detalhesRecarga(Recarga recarga) {
		render(recarga); 
	}

}
