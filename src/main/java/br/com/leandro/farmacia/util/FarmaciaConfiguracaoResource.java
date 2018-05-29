package br.com.leandro.farmacia.util;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/*serviços = back end em uma linguagem e front end em outra linguagem, webservices e RESTful
RESTful em java chama JAX-RS padronização e documentação que explica o que um servidor RESTful, 
a implementação da oracle é a Jersey

Classe para configuração para dizer onde estão o repositório que esta as coisas 
que o Jersey precisa e para colocar um nome no RESTFull que vai ser chamado através de
uma URL com esse nome

Classe herda da Classe que são as cofigurações dos recursos do RESTFull*/
@ApplicationPath("restful") // nome que vai na url entre parenteses
public class FarmaciaConfiguracaoResource extends ResourceConfig {

	// Construtor que fala onde vai estar o serviço
	public FarmaciaConfiguracaoResource() {
		packages("br.com.leandro.farmacia.servico");
	}
}
