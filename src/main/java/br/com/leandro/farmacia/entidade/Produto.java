package br.com.leandro.farmacia.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "farmacia_Produto")
public class Produto extends GenericDomain {

	@Column(name = "nome", length = 80, nullable = false)
	private String nome;

	@Column(name = "quantidade", nullable = false)
	private Short quantidade;

	@Column(name = "preco", precision = 7, scale = 2, nullable = false)
	private BigDecimal preco;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fabricante", nullable = false)
	private Fabricante fabricante;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
