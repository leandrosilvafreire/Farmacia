package br.com.leandro.farmacia.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.leandro.farmacia.entidade.Cliente;
import br.com.leandro.farmacia.entidade.Funcionario;
import br.com.leandro.farmacia.entidade.Venda;

public class VendaDaoTeste {

	@Test
	@Ignore
	public void salvar() throws ParseException {

		Venda venda = new Venda();
		venda.setHorario(new SimpleDateFormat("dd/MM/yyyy").parse("28/12/2015"));
		venda.setPrecoTotal(new BigDecimal("500"));

		Long codigoCliente = 1L;
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.consultar(codigoCliente);
		venda.setCliente(cliente);

		Long codigoFuncionario = 1L;
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.consultar(codigoFuncionario);
		venda.setFuncionario(funcionario);

		VendaDao vendaDao = new VendaDao();
		vendaDao.salvar(venda);

	}

	@Test
	@Ignore
	public void listar() {
		VendaDao vendaDao = new VendaDao();
		List<Venda> vendas = vendaDao.listar();

		System.out.println("Total de registros: " + vendas.size() + "\n");

		for (Venda venda : vendas) {

			System.out.println(venda.getCodigo() + ". " + venda.getCliente().getPessoa().getNome() + " - R$ "
					+ venda.getPrecoTotal() + " - " + venda.getHorario() + " - "
					+ venda.getFuncionario().getPessoa().getNome());
		}

	}

	@Test
	@Ignore
	public void consultar() {
		Long codigo = 5L;
		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.consultar(codigo);

		if (venda == null) {
			System.out.println("Nenhum registro encontrado!");
		} else {
			System.out.println();
			System.out.println(venda.getCodigo() + ". " + venda.getCliente().getPessoa().getNome() + " - R$"
					+ venda.getPrecoTotal() + " - " + venda.getHorario() + " - "
					+ venda.getFuncionario().getPessoa().getNome());
		}
	}

	@Test
	@Ignore
	public void editar() throws ParseException {
		Long codigoVenda = 3L;
		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.consultar(codigoVenda);

		if (venda == null) {
			System.out.println("\nNenhum registro encontrado!");
		} else {
			System.out.println();
			System.out.println("Registro Anterior: ");
			System.out.println(venda.getCodigo() + ". " + venda.getCliente().getPessoa().getNome() + " - R$"
					+ venda.getPrecoTotal() + " - " + venda.getHorario() + " - "
					+ venda.getFuncionario().getPessoa().getNome());

			Long codigoCliente = 4L;
			ClienteDao clienteDao = new ClienteDao();
			Cliente cliente = clienteDao.consultar(codigoCliente);
			venda.setCliente(cliente);

			Long codigoFuncionario = 1L;
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			Funcionario funcionario = funcionarioDao.consultar(codigoFuncionario);
			venda.setFuncionario(funcionario);

			venda.setHorario(new SimpleDateFormat("dd/MM/yyyy").parse("20/06/2017"));
			venda.setPrecoTotal(new BigDecimal("529"));

			vendaDao.editar(venda);

			System.out.println();
			System.out.println("Registro Atual: ");
			System.out.println(venda.getCodigo() + ". " + venda.getCliente().getPessoa().getNome() + " - R$"
					+ venda.getPrecoTotal() + " - " + venda.getHorario() + " - "
					+ venda.getFuncionario().getPessoa().getNome());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L;
		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.consultar(codigo);

		if (venda == null) {
			System.out.println("\nNenhum registro encontrado!");
		}
		else {
			vendaDao.excluir(venda);
		}

	}

}
