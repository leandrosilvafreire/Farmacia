package br.com.leandro.farmacia.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.leandro.farmacia.entidade.Funcionario;
import br.com.leandro.farmacia.entidade.Pessoa;

public class FuncionarioDaoTeste {

	@Test
	@Ignore
	public void salvar() throws ParseException {

		Funcionario funcionario = new Funcionario();

		funcionario.setCarteiraAdmissao("12.256-956");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("14/08/2017"));

		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = pessoaDao.consultar(2L);
		funcionario.setPessoa(pessoa);

		FuncionarioDao funcionarioDao = new FuncionarioDao();
		funcionarioDao.salvar(funcionario);

	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		List<Funcionario> funcionarios = funcionarioDao.listar();

		System.out.println("Total de registros = " + funcionarios.size());

		for (Funcionario funcionario : funcionarios) {
			System.out.println();
			System.out.println(funcionario.getCodigo() + ". " + funcionario.getPessoa().getNome() + " - "
					+ funcionario.getDataAdmissao() + " - " + funcionario.getCarteiraAdmissao());
		}

	}

	@Test
	@Ignore
	public void consultar() {
		Long codigo = 5L;
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.consultar(codigo);

		if (funcionario == null) {
			System.out.println("Nenhum registro encontrado!");
		} else {
			System.out.println();
			System.out.println(funcionario.getCodigo() + ". " + funcionario.getPessoa().getNome() + " - "
					+ funcionario.getDataAdmissao() + " - " + funcionario.getCarteiraAdmissao());
		}
	}

	@Test
	@Ignore
	public void excuir() {
		Long codigo = 3L;
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.consultar(codigo);

		if (funcionario == null) {
			System.out.println("Nenhum registro encontrado!");
		} else {
			funcionarioDao.excluir(funcionario);
		}
	}

	@Test
	public void editar() throws ParseException {
		Long codigoFuncionario = 1L;
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.consultar(codigoFuncionario);

		if (funcionario == null) {
			System.out.println("Nenhum registro encontrado!");
		} else {

			System.out.println();
			System.out.println("Registro Anterior: ");
			System.out.println(funcionario.getCodigo() + ". " + funcionario.getPessoa().getNome() + " - "
					+ funcionario.getDataAdmissao() + " - " + funcionario.getCarteiraAdmissao());

			funcionario.setCarteiraAdmissao("12.895-756");
			funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2015"));

			Long codigoPessoa = 3L;
			PessoaDao pessoaDao = new PessoaDao();
			Pessoa pessoa = pessoaDao.consultar(codigoPessoa);
			funcionario.setPessoa(pessoa);

			funcionarioDao.editar(funcionario);

			System.out.println();
			System.out.println("Registro Atual: ");
			System.out.println(funcionario.getCodigo() + ". " + funcionario.getPessoa().getNome() + " - "
					+ funcionario.getDataAdmissao() + " - " + funcionario.getCarteiraAdmissao());
		}
	}

}
