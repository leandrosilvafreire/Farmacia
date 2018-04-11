package br.com.leandro.farmacia.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

import br.com.leandro.farmacia.entidade.Estado;

public class EstadoDaoTeste {
	
	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Amazonas");
		estado.setSigla("AM");
		
		
		Estado estado1 = new Estado();
		estado1.setNome("Maranhão");
		estado1.setSigla("MA");
		
		EstadoDao estadoDao = new EstadoDao();
		estadoDao.salvar(estado1);
		estadoDao.salvar(estado);
		
	}
	
	@Test
	@Ignore
	public void listar() {
		EstadoDao estadoDao = new EstadoDao();
		List<Estado> resultado = estadoDao.listar();
		System.out.println("Total de registros = " + resultado.size() + "\n");
		
		for(Estado estado : resultado) {
			System.out.println(estado.getCodigo() + ". " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Test
	@Ignore
	public void consultar() {
		Long codigo = 7L;
		
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.consultar(codigo);
		
		if(estado==null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			System.out.println();
			System.out.println(estado.getCodigo() + ". " + estado.getNome());
		}
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 8L;
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.consultar(codigo);
		
		if(estado==null) {
			System.out.println("Nenhum registro encontrado!");
		}
		else {
			estadoDao.excluir(estado);
		}		
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigo = 20L;
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.consultar(codigo);
		
		if(estado==null) {
			System.out.println("Nenhum registro encontrado!");
		}
		else {
			System.out.println("Registro anterior: " + estado.getCodigo() + ". " + estado.getSigla() + " - " + estado.getNome());
			estado.setSigla("MT");
			estado.setNome("Mato Grosso");
			estadoDao.editar(estado);
			System.out.println("Registro Atual: " + estado.getCodigo() + ". " + estado.getSigla() + " - " + estado.getNome());

		}
		
				
	}

}
