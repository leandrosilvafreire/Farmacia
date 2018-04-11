package br.com.leandro.farmacia.dao;


import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import br.com.leandro.farmacia.entidade.Fabricante;

public class FabricanteDaoTeste {
	
	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setNome("Teste Medicamentos SA");
		
		FabricanteDao fabricanteDao = new FabricanteDao();
		fabricanteDao.salvar(fabricante);
	}
	
	@Test
	@Ignore
	public void listar() {
		FabricanteDao fabricanteDao =  new FabricanteDao();
		List<Fabricante> resultado = fabricanteDao.listar();
		System.out.println("Total de registros = " + resultado.size() + "\n");
		
		for(Fabricante fabricante : resultado) {
			System.out.println(fabricante.getCodigo() + ". " + fabricante.getNome());
		}

	}
	
	@Test
	@Ignore
	public void consultar() {
		Long codigo = 3L;
		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.consultar(codigo);
		
		if(fabricante == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			System.out.println();
			System.out.println(fabricante.getCodigo() + ". " + fabricante.getNome());
		}
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L;
		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.consultar(codigo);
		
		if(fabricante==null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			fabricanteDao.excluir(fabricante);
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigo = 1L;
		FabricanteDao fabricanteDao = new FabricanteDao();
		Fabricante fabricante = fabricanteDao.consultar(codigo);
		
		if (fabricante == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			System.out.println("Registro anterior: " + fabricante.getCodigo() + ". " + fabricante.getNome());
			fabricante.setNome("Laboratorios Ltda");
			fabricanteDao.editar(fabricante);
			System.out.println("Registro atual: " + fabricante.getCodigo() + ". " + fabricante.getNome());
		}
		
	}
	

}
