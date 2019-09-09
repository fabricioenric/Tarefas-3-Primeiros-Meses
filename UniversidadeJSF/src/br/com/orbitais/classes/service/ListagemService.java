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
* br.com.orbitais.classes.service
* ListagemService.java
* 16/08/2019 05:01
*/
package br.com.orbitais.classes.service;

import java.util.Calendar;
import java.util.List;

import br.com.orbitais.classes.dao.AlunoDao;
import br.com.orbitais.classes.model.Aluno;

/**
* Classe para aplicar as regras de negocio de cadastro e edicao do CRUD
*
* @author Fabrício - fabricioenric@info.ufrn.br 
*
* @version 1.0
* @since 16/08/2019
*
*/
public class ListagemService {
	
	private Calendar date = Calendar.getInstance();
	private int year = date.get(Calendar.YEAR);
	
	public ListagemService() {
		
	}
	
	/**
	 * Funcao para cadastrar um novo aluno
	 * @return
	 */
	public Aluno cadastrarAluno(AlunoDao alunoDao, Aluno aluno, List<Aluno> alunos) {
		// Verifica se o ano eh o atual - regra de negocio
		if(aluno.getAno() < year) {
			return null;
		}
		
		// Verifica se a matricula ja foi cadastrada anteriormente - regra de negocio
		for(int i = 0; i < alunos.size(); i++) {
			if(aluno.getMatricula() == alunos.get(i).getMatricula()) {
				return null;
			}
		}
		alunoDao.salvarAluno(aluno);
		alunos.add(aluno);
		
		return aluno;
	}
	
	/**
	 * Funcao para editar dados do aluno com os valores passados
	 * @return
	 */
	public Aluno editarAluno(int rowIndex, AlunoDao alunoDao, Aluno aluno, List<Aluno> alunos) {
		// Verifica se o ano eh o atual - regra de negocio
		if(aluno.getAno() < year) {
			return null;
		}
		
		aluno.getPessoa().setIdPessoa(alunos.get(rowIndex).getPessoa().getIdPessoa());
		aluno.setIdAluno(alunos.get(rowIndex).getIdAluno());
		
		// Verifica se a matricula ja foi cadastrada anteriormente - regra de negocio
		for(int i = 0; i < alunos.size(); i++) {
			if(aluno.getMatricula() == alunos.get(i).getMatricula() &&
					aluno.getIdAluno() != alunos.get(i).getIdAluno()) {
				
				return null;
			}
		}
		
		alunos.set(rowIndex, alunoDao.atualizarAluno(alunos.get(rowIndex).getIdAluno(), 
				aluno.getMatricula(), aluno.getPessoa().getNome(), aluno.getAno()));
		
		return aluno;
	}
}
