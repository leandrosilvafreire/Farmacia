package br.com.leandro.farmacia.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "farmacia_funcionario")
public class Funcionario extends GenericoEntidade{
	
	@Column(name="carteira_admissao", length=20, nullable=false)
	private String carteiraAdmissao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_admissao", nullable=false)
	private Date dataAdmissao;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="pessoa", nullable=false)
	private Pessoa pessoa;

	public String getCarteiraAdmissao() {
		return carteiraAdmissao;
	}

	public void setCarteiraAdmissao(String carteiraAdmissao) {
		this.carteiraAdmissao = carteiraAdmissao;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	

}
