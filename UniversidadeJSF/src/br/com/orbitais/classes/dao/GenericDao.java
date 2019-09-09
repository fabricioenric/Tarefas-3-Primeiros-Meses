/*
* Universidade Federal do Rio Grande do Norte
* Superintendência de Informática
* Diretoria de Sistemas
* Equipe Orbitais
*
* É proibido usar, copiar, modificar, mesclar, publicar, distribuir,
sublicenciar e / ou vender cópias
* desse Software sem estar de acordo com os termos da cooperação da UFRN.
*
*
* O aviso de copyright acima deve ser incluído em todas as cópias ou partes
substanciais do Software.
*
* UniversidadeJSF
* br.com.orbitais.classes.dao
* GenericDao.java
* 31/07/2019 06:30
*/
package br.com.orbitais.classes.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.orbitais.classes.model.Aluno;
import br.com.orbitais.classes.model.Pessoa;

/**
* DAO
* TODO Classe generica e abstrata de uma classe DAO
*
* @author Fabrício - fabricioenric@info.ufrn.br 
*
* @version 1.0
* @since 31/07/2019
*
*/
public abstract class GenericDao {
	protected static SessionFactory sessionFactory = null;
	
	protected static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration()
							 .configure("hibernate.cfg.xml")
							 .addAnnotatedClass(Pessoa.class)
							 .addAnnotatedClass(Aluno.class)
							 .buildSessionFactory();
		}
		return sessionFactory;
	}
}
