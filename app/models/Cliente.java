package models;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import sun.security.util.Password;



@Entity
public class Cliente extends Model {
	
	@Required
	public String nome;
	
	@Required
	@Email
	public String email;
	
	@Required
	public String cpf;
	
	@Required
	public String telefone;
	
	@Required
	public String senha;
	
   // @Required
  	@Temporal(TemporalType.DATE)
	public Date dataNasc;
	
	public Blob foto;
	public String cidade;
	public String bairro;
	public String rua;
    public int numero;
    
    @OneToOne
    @JoinColumn(name = "conta_id")
    public Conta conta;
	
    public boolean isUnico() {
		Cliente c = Cliente.find("email", email).first();
		
		if (c == null || c.id == id) {
			return true;
		}
		
		return false;
	}

}
