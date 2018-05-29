package br.com.leandro.farmacia.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "farmacia_item_venda")
public class ItemVenda extends GenericoEntidade {

	@Column(name = "quantidade", length = 5, nullable = false)
	private Short quantidade;

	@Column(name = "preco_parcial", precision = 6, scale = 2, nullable = false)
	private BigDecimal precoParcial;

	@ManyToOne
	@JoinColumn(name = "produto", nullable = false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "venda", nullable = false)
	private Venda venda;

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoParcial() {
		return precoParcial;
	}

	public void setPrecoParcial(BigDecimal precoParcial) {
		this.precoParcial = precoParcial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
