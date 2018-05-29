package br.com.leandro.farmacia.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.leandro.farmacia.util.HibernateUtil;

public class GenericoDao<Entidade> {

	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericoDao() {
		// Classe pai pega classe genérica e seu tipo e joga na váriavel classe
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	public void salvar(Entidade entidade) {
		// captura e abra a fábrica de sessão
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		// Usado para salvar, editar e excluir
		Transaction transacao = null;

		try {
			// inicia a sessão
			transacao = sessao.beginTransaction();
			// salava a entidade
			sessao.save(entidade);
			// encerra a sessão
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;// repropaga o erro e finaliza a sessão
		} finally {
			sessao.close();// Finaliza a sessão
		}

	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		// Abre a fabrica de sessões, pega uma sessão aberta
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();

		// Não é necessário transação pois o listar só consulta

		try {
			// Cria uma criterio para listar
			Criteria consulta = sessao.createCriteria(classe);
			// Cria variavel que vai retornar a lista
			List<Entidade> resultado = consulta.list();
			// Retorna a lista
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listarCampo(String campoOrdenado) {
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.addOrder(Order.asc(campoOrdenado));
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Entidade consultar(Long codigo) {
		// Abre a fabrica de sessões, pega uma sessão aberta
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();

		// Não é necessário transação pois o o método só vai consultar

		try {
			// Cria uma criterio para consultar
			Criteria consulta = sessao.createCriteria(classe);
			// consulta através de uma restrição que é somente pelo que o
			// usuário digitar com a chave primária
			consulta.add(Restrictions.idEq(codigo));
			// Cria variáel que vai retornar uma única consulta e faz uma
			// conversão para o tipo entidade
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;// não fica invisivel o erro
		} finally {// fecha a sessão independente se cair no try ou no catch
			sessao.close();
		}
	}

	public void excluir(Entidade entidade) {
		// Abre a fabrica de sessões, pega uma sessão aberta
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		// Cria uma transação
		Transaction transacao = null;
		try {
			// Inicia a transação
			transacao = sessao.beginTransaction();
			// deleta o objeto
			sessao.delete(entidade);
			// Confirma a transação
			transacao.commit();
		} catch (RuntimeException erro) {
			// desfaz a transação
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;// repropaga a transação para camadas superiores e não
			// fica invisivel o erro
		} finally {// fecha a sessão independente se cair no try ou no catch
			sessao.close();
		}
	}

	public void editar(Entidade entidade) {
		// Abre a fabrica de sessões, pega uma sessão aberta
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		// Cria uma transação
		Transaction transacao = null;
		try {
			// Inicia a transação
			transacao = sessao.beginTransaction();
			// atualiza o objeto
			sessao.update(entidade);
			// Confirma a transação
			transacao.commit();
		} catch (RuntimeException erro) {
			// desfaz a transação
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;// repropaga a transação para camadas superiores e não
			// fica invisivel o erro
		} finally {// fecha a sessão independente se cair no try ou no catch
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Entidade merge(Entidade entidade) {
		// Abre a fabrica de sessões, pega uma sessão aberta
		Session sessao = HibernateUtil.getFabricaSessoes().openSession();
		// Cria uma transação
		Transaction transacao = null;
		try {
			// Inicia a transação
			transacao = sessao.beginTransaction();
			// Salva ou edita objeto
			Entidade retorno = (Entidade) sessao.merge(entidade);
			// Confirma a transação
			transacao.commit();
			// retorna o objeto
			return retorno;
		} catch (RuntimeException erro) {
			if (transacao != null) {
				// desfaz a transação
				transacao.rollback();
			}
			// repropaga a transação para camadas superiores e não fica
			// invisivel o erro
			throw erro;
		} finally {// fecha a sessão independente se cair no try ou no catch
			sessao.close();
		}

	}

}
