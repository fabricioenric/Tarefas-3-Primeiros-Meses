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
* Aluno.java
* 31/07/2019 06:29
*/
package br.com.orbitais.classes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
* MODEL
* Classe modelo de um aluno
*
* @author Fabrício - fabricioenric@info.ufrn.br
*
* @version 1.0
* @since 31/07/2019
*
*/

@Entity
@Table(name = "aluno", schema = "graduacao")
public class Aluno implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluno_seq")
	@SequenceGenerator(name = "aluno_seq", sequenceName = "graduacao.aluno_seq", allocationSize = 1)
	@Column(name = "id_aluno")
	private long idAluno;
	
	@Column(name = "matricula")
	private long matricula;
	
	@Column(name = "ano")
	private int ano;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
	private Pessoa pessoa;

	public Aluno() {
		pessoa = new Pessoa();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + (int) (matricula ^ (matricula >>> 32));
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (ano != other.ano)
			return false;
		if (matricula != other.matricula)
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}

	public Aluno(long matricula, int ano, Pessoa pessoa) {
		this.matricula = matricula;
		this.ano = ano;
		this.pessoa = pessoa;
	}

	public long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(long idAluno) {
		this.idAluno = idAluno;
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
