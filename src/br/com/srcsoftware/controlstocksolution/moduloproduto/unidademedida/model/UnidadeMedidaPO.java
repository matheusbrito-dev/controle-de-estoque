package br.com.srcsoftware.controlstocksolution.moduloproduto.unidademedida.model;

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
 * Classe que representa a essência de uma UnidadeMedida
 * Classe da camada Model que representa a parte de persistencia
 * da UnidadeMedida.
 *
 *
 * @author Matheus de Brito Vieira Martins <mathbvm@gmail.com.br>
 * @since 24 de jul de 2018 21:57:02
 * @version 1.0
 */

@Entity
@Table( name = "unidadeMedidas" )
public final class UnidadeMedidaPO extends AbstractPO implements Comparable< UnidadeMedidaPO >{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY ) //Nesta linha especifiquei que meu generated é uma PrimaryKey
	private Long id;

	@Column( length = 20, nullable = false, unique = true )
	private String nome;

	@Column( length = 3, nullable = false, unique = true )
	private String sigla;

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

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla( String sigla ) {
		this.sigla = sigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( sigla == null ) ? 0 : sigla.hashCode() );
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
		if ( !( obj instanceof UnidadeMedidaPO ) ) {
			return false;
		}
		UnidadeMedidaPO other = (UnidadeMedidaPO) obj;
		if ( nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !nome.equals( other.nome ) ) {
			return false;
		}
		if ( sigla == null ) {
			if ( other.sigla != null ) {
				return false;
			}
		} else if ( !sigla.equals( other.sigla ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "UnidadeMedidaPO\n\t [id=" );
		builder.append( id );
		builder.append( ",\n\t  nome=" );
		builder.append( nome );
		builder.append( ",\n\t  sigla=" );
		builder.append( sigla );
		builder.append( "]\n " );
		return builder.toString();
	}

	@Override
	public int compareTo( UnidadeMedidaPO comparar ) {
		Collator ignoraAcentos = Collator.getInstance( new Locale( "pt", "BR" ) );

		return ignoraAcentos.compare( this.getNome(), comparar.getNome() );
	}

}
