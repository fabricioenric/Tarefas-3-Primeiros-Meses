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
* br.com.orbitais.classes
* MainClass.java
* 31/07/2019 06:28
*/
package br.com.orbitais.classes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;

import br.com.orbitais.classes.dao.AlunoDao;
import br.com.orbitais.classes.model.Aluno;
import br.com.orbitais.classes.service.ListagemService;

/**
* CONTROLLER 
* ManagedBean que gerecia o CRUD de alunos.
*
* @author Fabrício - fabricioenric@info.ufrn.br 
*
* @version 1.0
* @since 31/07/2019
*
*/

@ManagedBean(name = "controllerMBean")
@SessionScoped
public class ControllerMBean {
	
	
	////////////////////////// Campos ////////////////////////////////
	
	private ListagemService service;
	private AlunoDao dao = new AlunoDao();
	private Aluno aluno = new Aluno();
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private HtmlDataTable htmlDataTable = new HtmlDataTable();
	private int rowIndex;
	private String operacao;
	
	
	////////////////////////// Construtores ////////////////////////////////
	
	public ControllerMBean() {
		service = new ListagemService();
		iniciar();
	}
	
	////////////////////////// CRUD ////////////////////////////////
	
	/**
	 * Modifica o comportamento do botao submeter de acordo com a operacao em acao
	 * @return
	 */
	public String determinarActionDoCommandButton() {
		if(operacao.equals("cadastrar")) {
			cadastrarAluno(this.dao, this.aluno, this.alunos);
		}
		else if(operacao.equals("editar")) {
			editarAluno(this.rowIndex, this.dao, this.aluno, this.alunos);
		}
		return iniciar();
	}
	
	/**
	 * Seta os valores padroes nos campos input quando for cadastrar novo aluno
	 */
	public void determinarValoresPadraoDosCampos() {	
		this.aluno.setMatricula(1);
		this.aluno.getPessoa().setNome("");
		this.aluno.setAno(2019);
	}
	
	
	////////////////////////// Listar /////////////////////////////////////
	
	/**
	 * Lista os alunos cadastradados na tabela sempre que a pagina listagem for carregada
	 */
	
	public String iniciar() {		
		this.alunos = dao.listarAlunos();	
		
		return "listagem_alunos";
	}
	
	
	/////////////////////// Cadastrar //////////////////////////////////////
	
	/**
	 * Call para iniciar o cadastro de um novo aluno
	 * @return
	 */
	public String preCadastrarAluno() {
		this.operacao = "cadastrar";
		
		determinarValoresPadraoDosCampos();
		
		return "cadastro";
	}
	
	
	/**
	 * Call para cadastrar um novo aluno
	 * @return
	 */
	public Aluno cadastrarAluno(AlunoDao alunoDao, Aluno aluno, List<Aluno> alunos) {	
		return service.cadastrarAluno(alunoDao, aluno, alunos);
	}

	
	///////////////////////// Editar /////////////////////////////////////
	
	/**
	 * Call para iniciar a edicao de um novo aluno
	 * @return
	 */
	public String preEditarAluno() {
		this.operacao = "editar";
		
		return "cadastro";
	}
	
	/**
	 * Recupera a tabela da listagem e a linha em que o botao clicado esta
	 * e seta os valores dos campos input com as informacoes ja cadastrada do aluno a ser editado
	 * @param evt
	 */
	public void coletarLinhaDoAlunoParaEditar(ActionEvent evt) {		
		this.htmlDataTable = getDataTable((UIComponent) evt.getSource());
		this.rowIndex = this.htmlDataTable.getRowIndex();
		
		this.aluno.setMatricula(this.alunos.get(rowIndex).getMatricula());
		this.aluno.getPessoa().setNome(this.alunos.get(rowIndex).getPessoa().getNome());
		this.aluno.setAno(this.alunos.get(rowIndex).getAno());
	}
	
	/**
	 * Funcao para editar dados do aluno com os valores passados
	 * @return
	 */
	public Aluno editarAluno(int rowIndex, AlunoDao alunoDao, Aluno aluno, List<Aluno> alunos) {
		return service.editarAluno(rowIndex, alunoDao, aluno, alunos);
	}
	
	////////////////////////// Remover /////////////////////////////////////
	
	/**
	 * Call quando o usuario clica em remover aluno
	 * @return
	 */
	public void removerAluno(ActionEvent evt) {
		this.htmlDataTable = getDataTable((UIComponent) evt.getSource());
		this.rowIndex = this.htmlDataTable.getRowIndex();
		
		this.dao.deletarAluno(this.alunos.get(rowIndex).getIdAluno());
		this.alunos.remove(rowIndex);
	}
	
	
	////////////////////////// Getters e Setters ////////////////////////////////
	
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * Recupera a dataTabela se esta existir
	 * @param table
	 * @return
	 */
	public HtmlDataTable getDataTable(UIComponent table) {
	    if (table == null) {
	        return null;
	    }
	    if (table instanceof HtmlDataTable) {
	        return (HtmlDataTable) table;
	    }
	    return getDataTable(table.getParent());
	}
}
