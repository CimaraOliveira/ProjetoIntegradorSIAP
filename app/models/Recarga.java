package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Recarga extends Model {
	
	public double recarga;
	public String pagamento;
	public Date data;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conta_id")
	public Conta conta;
}
