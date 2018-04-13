package br.com.leandro.farmacia.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.leandro.farmacia.entidade.Cliente;
import br.com.leandro.farmacia.entidade.Pessoa;

public class ClienteDaoTeste {
	@Test
	@Ignore
	public void salvar() throws ParseException {

		Cliente cliente = new Cliente();
		// cliente.setDataCadastro(new
		// SimpleDateFormat("dd/MM/yyyy").parse("25/05/2017"));
		cliente.setDataCadastro(new Date());
		cliente.setLiberado(false);

		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = pessoaDao.consultar(2L);
		cliente.setPessoa(pessoa);

		ClienteDao clienteDao = new ClienteDao();
		clienteDao.salvar(cliente);
	}

	@Test
	@Ignore
	public void listar() {

		ClienteDao clienteDao = new ClienteDao();
		List<Cliente> clientes = clienteDao.listar();

		System.out.println("Total de registros = " + clientes.size());

		for (Cliente cliente : clientes) {
			System.out.println();
			System.out.println(cliente.getCodigo() + ". " + cliente.getPessoa().getNome() + " - "
					+ cliente.getLiberado() + " - " + cliente.getDataCadastro());

		}

	}

	@Test
	@Ignore
	public void consultar() {
		Long codigo = 5L;
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.consultar(codigo);

		if (cliente == null) {
			System.out.println("Nenhum registro encontrado!");
		} else {
			System.out.println();
			System.out.println(cliente.getCodigo() + ". " + cliente.getPessoa().getNome() + " - "
					+ cliente.getLiberado() + " - " + cliente.getDataCadastro());
		}

	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.consultar(codigo);
		
		if (cliente == null) 
		{
			System.out.println("Nenhum registro encontrado!");
		}
		else
		{
			clienteDao.excluir(cliente);
		}
		
	}
	
	
	@Test
	@Ignore
	public void editar(){
		Long codigoCliente = 3L;
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.consultar(codigoCliente);
		
		if (cliente == null) 
		{
			System.out.println("Nenhum registro encontrado!");
		}
		else
		{
			System.out.println();
			System.out.println("Registro  Anterior: ");
			System.out.println(cliente.getCodigo() + ". " + cliente.getPessoa().getNome() + " - "
					+ cliente.getLiberado() + " - " + cliente.getDataCadastro());
		
			cliente.setDataCadastro(new Date());
			cliente.setLiberado(true);
		
			Long codigoPessoa = 3L;
			PessoaDao pessoaDao = new PessoaDao();
			Pessoa pessoa = pessoaDao.consultar(codigoPessoa);
			cliente.setPessoa(pessoa);
			clienteDao.editar(cliente);
			
			System.out.println();
			System.out.println("Registro Atual: ");
			System.out.println(cliente.getCodigo() + ". " + cliente.getPessoa().getNome() + " - "
					+ cliente.getLiberado() + " - " + cliente.getDataCadastro());
		}
		
		
		
	}

}
