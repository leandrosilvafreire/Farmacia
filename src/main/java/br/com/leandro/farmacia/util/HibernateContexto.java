package br.com.leandro.farmacia.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {

	// Fecha o tomcat
	@Override
	public void contextDestroyed(ServletContextEvent evento) {
		HibernateUtil.getFabricaSessoes().close();
	}

	// Abre o tomcat
	@Override
	public void contextInitialized(ServletContextEvent evento) {
		HibernateUtil.getFabricaSessoes();
	}

}
