package controllers;


import java.util.List;

import interceptors.Seguranca;
import models.Cliente;
import play.mvc.Controller;
import play.mvc.With;


public class Operadores extends Controller{

	 	public static void operador() {
	        render();
	    }
	 
	 	public static void listar() {
			List<Cliente> clientes = Cliente.findAll();
			render(clientes); 
		}
			
		public static void remover(Long id) {
			Cliente cliente = Cliente.findById(id);
			cliente.delete();
			flash.success("Cliente removido com sucesso!");
			listar();
		}
	 
	 
}
