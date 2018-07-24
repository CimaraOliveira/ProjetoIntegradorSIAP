package job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Cliente;
import models.Conta;
import models.Recarga;
import models.Sugestao;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

	@Override
	public void doJob() throws Exception {
		 
		if(Cliente.count() == 0){
			
			Cliente cliente1 = new Cliente();
			cliente1.nome = "Administrador";
			cliente1.rua = "Beco da Lagartixa";
			cliente1.bairro = "São Judas Tadeu";
			cliente1.cidade = "Pau dos Ferros";
			cliente1.cpf = "016.777.904-50";
			cliente1.dataNasc = new Date();
			cliente1.numero = 32;
			cliente1.telefone = "8499954301";
			cliente1.email = "admin@123";
			cliente1.senha = "admin";
			cliente1.save();
			
			Conta conta = new Conta();
			conta.numeroConta = (cliente1.id.intValue() + 999);
			conta.saldo += conta.recarga;	
			conta.save();
			
			cliente1.conta = conta;
			cliente1.save();
			
			conta.cliente = cliente1;
			conta.save();
			
			Recarga recarga1 = new Recarga();
			recarga1.conta = conta;
			recarga1.recarga = 50;
			recarga1.data = new Date();
			recarga1.pagamento = "a vista";
			recarga1.save();
			
			List<Recarga> recargas = new ArrayList<>();
			recargas.add(recarga1);
			
			conta.recargas = recargas;
			conta.save();
			
			Sugestao sugestao = new Sugestao();
			sugestao.sugestao = "Se melhorar fica melhor!!!";
			sugestao.cliente = cliente1;
		}
		
	}
	
}
