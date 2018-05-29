package br.com.leandro.farmacia.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.leandro.farmacia.entidade.Cidade;
import br.com.leandro.farmacia.util.HibernateUtil;

public class CidadeDao extends GenericoDao<Cidade> {

	@SuppressWarnings("unchecked")
	public List<Cidade> listarPorEstado(Long codigo) {
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			// Where no Sql
			consulta.add(Restrictions.eq("estado.codigo", codigo));
			// Ordena pelo nome 
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
