
package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class FrenteDeCaixa extends Model {

	public Date abrirCaixa;
	public Date fecharCaixa;
	public int totalVendaRefeicao=0;
	public double totalVendaRecarga=0;
	
}
