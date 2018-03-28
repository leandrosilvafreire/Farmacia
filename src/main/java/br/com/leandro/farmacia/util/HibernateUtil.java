package br.com.leandro.farmacia.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	//Objeto que recebe a fabrica de sessões variável global
	private static SessionFactory fabricaSessoes =  criarFabricaDeSessoes();
	
	//Quando o usuário for usar a fábrica de sessões ele chama o métoto get
	public static SessionFactory getFabricaSessoes() {
		return fabricaSessoes;		
	}	
	
	//Cria a fábrica de sessões
	private static SessionFactory criarFabricaDeSessoes() {
		try {
			//lê o hibernate.cfg.xml
			Configuration configuracao = new Configuration().configure();
			//Registra o serviço
			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
			//Cria a fábrica de sessões variável local
			SessionFactory fabrica = configuracao.buildSessionFactory(registro);
			//retorna a fábrica criada para variável global
			return fabrica;		
		} catch (Throwable ex) {
			System.err.println("Erro ao criar a fábrica de sessões." + ex);
			//A aplicação vai ser avisada quando der erro
			throw new ExceptionInInitializerError(ex);
			
		}
	}
	
}
