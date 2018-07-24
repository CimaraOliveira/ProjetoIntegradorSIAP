package interceptors;

import controllers.Logins;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller{ 
	
	@Before(unless="login")
    static void verificaAutenticacao() {
        if(!session.contains("cliente")) {
            flash.error("Para acessar essa funcionalidade você deve estar logado no sistema!");
            Logins.login();
        }
    }
}