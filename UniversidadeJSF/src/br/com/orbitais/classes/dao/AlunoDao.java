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
* AlunoDao.java
* 31/07/2019 06:30
*/
package br.com.orbitais.classes.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import br.com.orbitais.classes.model.Aluno;
import br.com.orbitais.classes.model.Pessoa;

/**
* DAO
* Classe para recuperar e manipular diretamente dados do banco de dados
*
* @author Fabrício - fabricioenric@info.ufrn.br
*
* @version 1.0
* @since 31/07/2019
*
*/

public class AlunoDao extends GenericDao {
	private SessionFactory sessionFactory;
	
	public AlunoDao() {
		sessionFactory = getSessionFactory();
	}
	
	/////////////////// CRUD ////////////////////////////
	
	/**
	 * Salvar aluno no DB
	 * @param a
	 */
	public void salvarAluno(Aluno a) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.save(a.getPessoa());
			session.save(a);
			
			session.flush();
			
			session.getTransaction().commit();
			
			session.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	/**
	 * Remover aluno do DB
	 * @param id
	 */
	public void deletarAluno(long id) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Aluno a = (Aluno)session.get(Aluno.class, id);
			
			session.delete(a.getPessoa());
			session.delete(a);
			
			session.flush();
			
			session.getTransaction().commit();
			
			session.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	/**
	 * Edita as informacoes do aluno com determinado id no DB
	 * @param id
	 * @param matricula
	 * @param nome
	 * @param ano
	 * @return
	 */
	public Aluno atualizarAluno(long id, long matricula, String nome, int ano) {
		Session session = sessionFactory.getCurrentSession();
		Pessoa p = new Pessoa();
		Aluno a = new Aluno();
		
		try {
			session.beginTransaction();
		
			p = (Pessoa)session.get(Pessoa.class, id);
			a = (Aluno)session.get(Aluno.class, id);
			
			p.setNome(nome);
			a.setMatricula(matricula);
			a.setAno(ano);
			
			session.update(p);
			session.update(a);
			
			session.flush();
			
			session.getTransaction().commit();
			
			session.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return a;
	}
	
	/**
	 * Lista todos os aluno do DB
	 * @return
	 */
	public List<Aluno> listarAlunos() {
		Session session = sessionFactory.getCurrentSession();
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		try {
			session.beginTransaction();
			
			String hql = "FROM Aluno a ORDER BY a.pessoa.nome ASC";
			
			Query<Aluno> query = session.createQuery(hql);
			
			alunos = query.list();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
		return alunos;
	}
	
	/**
	 * Recupera as informacoes um aluno com determinado id do DB 
	 * @param idAluno
	 * @return
	 */
	public List<Aluno> findAluno(long idAluno) {
		Session session = sessionFactory.getCurrentSession();
		
		Aluno aluno = new Aluno();
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		try {
			session.beginTransaction();
			
			String hql = "from Aluno aluno"+
					" where aluno.id = :idAluno";
			
			Query query = session.createQuery(hql);
			query.setParameter("idAluno", idAluno);
			
			alunos = query.list();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return alunos;
	}
}
