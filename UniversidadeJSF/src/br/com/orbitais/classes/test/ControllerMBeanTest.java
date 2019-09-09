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
* br.com.orbitais.classes.test
* ControllerMBeanTest.java
* 12/08/2019 06:56
*/
package br.com.orbitais.classes.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.orbitais.classes.controller.ControllerMBean;
import br.com.orbitais.classes.dao.AlunoDao;
import br.com.orbitais.classes.model.Aluno;
import br.com.orbitais.classes.model.Pessoa;

/**
* TODO Testes unitarios dos metodos da camada de controle
*
* @author Fabrício - fabricioenric@info.ufrn.br  
*
* @version 1.0
* @since 12/08/2019
*
*/
public class ControllerMBeanTest {

	/**
	 * Test method for {@link br.com.orbitais.classes.controller.ControllerMBean#cadastrarAluno(AlunoDao, Aluno, List<Aluno>)}.
	 */
	@Test
	public void testCadastrarAluno() {
		ControllerMBean controllerMBeanTest = new ControllerMBean();
		
		Pessoa pessoa = new Pessoa("Aluno Teste");
		Aluno aluno = new Aluno(20123239841l, 2017, pessoa);
		AlunoDao alunoDao = new AlunoDao();
		List<Aluno> alunos = alunoDao.listarAlunos();
		
		// Testa se o objeto aluno não eh null ou se a matricula ja nao foi cadastrada antes
		assertNotNull(controllerMBeanTest.cadastrarAluno(alunoDao, aluno, alunos));
		
		// Testa individualmente se cada um dos campos aluno nao eh null
		assertNotNull(aluno.getMatricula());
		assertNotNull(aluno.getPessoa().getNome());
		assertNotNull(aluno.getAno());
		
		// Testa se o ano de ingresso do aluno eh maior ou igual que o atual
		assertTrue(aluno.getAno() >= 2019);
	}

	/**
	 * Test method for {@link br.com.orbitais.classes.controller.ControllerMBean#editarAluno(int, AlunoDao, Aluno, List<Aluno>)}.
	 */
	@Test
	public void testEditarAluno() {
		ControllerMBean controllerMBeanTest = new ControllerMBean();
		
		Pessoa pessoa = new Pessoa("Aluno Teste");
		Aluno aluno = new Aluno(2002139842l, 2019, pessoa);
		AlunoDao alunoDao = new AlunoDao();
		List<Aluno> alunos = alunoDao.listarAlunos();
		int rowIndex = alunos.indexOf(alunos.get(0));
		
		// Testa se o objeto aluno não eh null ou se a matricula ja nao foi cadastrada antes
		assertNotNull(controllerMBeanTest.editarAluno(rowIndex,alunoDao, aluno, alunos));
		
		// Testa individualmente se cada um dos campos aluno nao eh null
		assertNotNull(aluno.getMatricula());
		assertNotNull(aluno.getPessoa().getNome());
		assertNotNull(aluno.getAno());
				
		// Testa se o ano de ingresso do aluno eh maior ou igual que o atual
		assertTrue(aluno.getAno() >= 2019);
	}
}
