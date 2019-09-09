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
* br.com.orbitais.classes.model
* Pessoa.java
* 31/07/2019 06:29
*/
package br.com.orbitais.classes.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
* MODEL
* Classe modelo de uma pessoa
*
* @author Fabrício - fabricioenric@info.ufrn.br 
*
* @version 1.0
* @since 31/07/2019
*
*/

@Entity
@Table(name = "pessoa", schema = "comum")
public class Pessoa implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_seq")
	@SequenceGenerator(name = "pessoa_seq", sequenceName = "comum.pessoa_seq", allocationSize = 1)
	@Column(name = "id_pessoa")
	private long idPessoa;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy = "pessoa", targetEntity = Aluno.class, cascade = CascadeType.ALL)
	private List<Aluno> alunos;
	
	public Pessoa() {
		
	}
	
	public Pessoa(String nome) {
		this.nome = nome;
	}

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}
