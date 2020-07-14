package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model;

import java.text.Collator;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.srcsoftware.manager.abstracts.AbstractPO;

/**
 * 
 * Classe que representa a ess�ncia de uma Categoria
 * Classe da camada Model que representa a parte de persistencia
 * da Categoria.
 *
 *
 * @author Matheus de Brito Vieira Martins <mathbvm@gmail.com.br>
 * @since 24 de jul de 2018 21:57:02
 * @version 1.0
 */

@Entity
@Table( name = "categorias" )
public final class CategoriaPO extends AbstractPO implements Comparable< CategoriaPO >{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY ) //Nesta linha especifiquei que meu generated � uma PrimaryKey
	private Long id;

	@Column( length = 20, nullable = false, unique = true )
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	@Transient
	public String getIdToString() {
		if ( getId() != null ) {
			return getId().toString();
		}
		return null;
	}

	public void setIdToString( String id ) {
		if ( id != null && !id.isEmpty() ) {
			setId( Long.valueOf( id ) );
			return;
		}
		setId( null );
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof CategoriaPO ) ) {
			return false;
		}
		CategoriaPO other = (CategoriaPO) obj;
		if ( nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !nome.equals( other.nome ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "CategoriaPO\n\t [id=" );
		builder.append( id );
		builder.append( ",\n\t  nome=" );
		builder.append( nome );
		builder.append( ",\n\t  getDataHoraCadastro()=" );
		builder.append( getDataHoraCadastro() );
		builder.append( "]\n " );
		return builder.toString();
	}

	@Override
	public int compareTo( CategoriaPO comparar ) {
		Collator ignoraAcentos = Collator.getInstance( new Locale( "pt", "BR" ) );

		return ignoraAcentos.compare( this.getNome(), comparar.getNome() );
	}

}
