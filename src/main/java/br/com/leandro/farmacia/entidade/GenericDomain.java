package br.com.leandro.farmacia.entidade;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//Esta anotação é do java serve para ignoar os warnings
@SuppressWarnings("serial")
//Esta anotação significa classe não pertence a uma tabela, mas sera gerada por outras
@MappedSuperclass
public class GenericDomain implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigo;
	
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
}
