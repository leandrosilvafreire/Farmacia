package br.com.leandro.farmacia.servico;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("farmacia") // Nome que vai na url depois do nome da configuração
public class FarmaciaServico {

	// Prtotocolo que exibe a url a página
	@GET
	public String exibir() {
		return "Leandro Silva Freire";
	}
}
