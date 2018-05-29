package br.com.leandro.farmacia.servico;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.leandro.farmacia.dao.FabricanteDao;
import br.com.leandro.farmacia.entidade.Fabricante;

@Path("fabricante")
public class FabricanteServico {
	@GET
	public String listar() {
		FabricanteDao fabricanteDao = new FabricanteDao();
		List<Fabricante> fabricantes = fabricanteDao.listar();

		Gson gson = new Gson();
		String json = gson.toJson(fabricantes);
		return json;
	}

	@GET
	@Path("{codigo}")
	public String consultar(@PathParam("codigo") Long codigo) {

		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.consultar(codigo);

		Gson gson = new Gson();
		String json = gson.toJson(fabricante);
		return json;
	}

	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);

		FabricanteDao fabricanteDao = new FabricanteDao();
		fabricanteDao.salvar(fabricante);

		String jsonRetorno = gson.toJson(fabricante);
		return jsonRetorno;
	}

	@PUT
	public String editar(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);

		FabricanteDao fabricanteDao = new FabricanteDao();
		fabricanteDao.editar(fabricante);

		String jsonRetorno = gson.toJson(fabricante);
		return jsonRetorno;
	}

	@DELETE
	public String excluir(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);

		FabricanteDao fabricanteDao = new FabricanteDao();
		fabricante = fabricanteDao.consultar(fabricante.getCodigo());
		
		if (fabricante != null) {
			fabricanteDao.excluir(fabricante);
			String jsonRetorno = gson.toJson(fabricante);
			return jsonRetorno;
		} else {
			String mensagem = "Nenhum registro encontrado!";
			return mensagem;
		}
	}

}
