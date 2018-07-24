package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import play.db.jpa.Model;
@Entity
public class Conta extends Model {
	
	public Integer numeroConta;
	
	public double saldo;

	@Transient
	public double recarga = 0;
		
	@Transient
	public String pagamento;
	
	@OneToOne(mappedBy = "conta")
	public Cliente cliente;
	
	@OneToMany(mappedBy = "conta")
	public List<Recarga> recargas;
	
	@Override
	public String toString() {
		return "\nconta.id: " + id + "\n conta.numeroConta: " + numeroConta + "\nconta.saldo: " + saldo;
	}
}
