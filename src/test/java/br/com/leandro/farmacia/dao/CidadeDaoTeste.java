package br.com.leandro.farmacia.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.leandro.farmacia.entidade.Cidade;
import br.com.leandro.farmacia.entidade.Estado;

public class CidadeDaoTeste {

	@Test
	@Ignore
	public void salvar() {

		Cidade cidade = new Cidade();
		cidade.setNome("Salvador");
       
		Long codigoEstado = 5L;
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = estadoDao.consultar(codigoEstado);
		cidade.setEstado(estado);

		CidadeDao cidadeDao = new CidadeDao();
		cidadeDao.salvar(cidade);

	}

	@Test
	@Ignore
	public void listar() {
		CidadeDao cidadeDao = new CidadeDao();
		List<Cidade> resultado = cidadeDao.listar();

		System.out.println("Total de registros = " + resultado.size() + "\n");

		for (Cidade cidade : resultado) {
			System.out.println(cidade.getCodigo() + ". " + cidade.getNome() + " - " + cidade.getEstado().getSigla());
		}

	}
	
	@Test
	@Ignore
	public void consultar() {
		    Long codigo = 9L;
			CidadeDao cidadeDao = new CidadeDao();
			Cidade cidade = cidadeDao.consultar(codigo);
			
			if(cidade==null)
			{
				System.out.println("Nenhum registro encontrado!");
			}
			else
			{
				System.out.println();
				System.out.println(cidade.getCodigo() + ". " + cidade.getNome() + " - " + cidade.getEstado().getSigla());
			}		

	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 5L;
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = cidadeDao.consultar(codigo);
		
		if(cidade==null)
		{
			System.out.println("Nenhum registro encontrado!");
		}
		else
		{
			cidadeDao.excluir(cidade);
		}

	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;
		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = cidadeDao.consultar(codigo);
		
		if(cidade==null)
		{
			System.out.println("Nenhum registro encontrado!");
		}
		else
		{
			System.out.println("\nRegistro anterior!\n");
			System.out.println(cidade.getCodigo() + ". " + cidade.getNome() + " - " + cidade.getEstado().getSigla());
			System.out.println();
			cidade.setNome("Ribeirão Preto");
			
			Long codigoEstado = 1L;
			EstadoDao estadoDao = new EstadoDao();
			Estado estado = estadoDao.consultar(codigoEstado);
			cidade.setEstado(estado);
			
			cidadeDao.editar(cidade);
			
			
			System.out.println("\nRegistro atual!\n");
			System.out.println(cidade.getCodigo() + ". " + cidade.getNome() + " - " + cidade.getEstado().getNome());
		}
		
	}
}
