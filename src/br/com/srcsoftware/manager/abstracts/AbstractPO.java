package br.com.srcsoftware.manager.abstracts;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import br.com.srcsoftware.manager.utilidades.Utilidades;

/**
 * 
 * @MappedSuperclass: Informa ao Hibernate que esta classe n�o
 *                    possuira Tabela, ela � apenas uma SuperClasse
 * 
 *                    OU SEJA
 * 
 *                    Mapeia esta classe como apenas uma classe de Heran�a, onde todos
 *                    seu atributos ser�o implementados nas tabelas que representam as
 *                    classes FILHAS
 *
 *
 * @author Matheus de Brito Vieira Martins <mathbvm@gmail.com.br>
 * @since 6 de ago de 2018 20:24:55
 * @version 1.0
 */

@MappedSuperclass
public abstract class AbstractPO{

	@Column( name = "dataHoraCadastro", nullable = false )
	private LocalDateTime dataHoraCadastro;

	public AbstractPO(){
		setDataHoraCadrastro( LocalDateTime.now() );
	}

	public LocalDateTime getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadrastro( LocalDateTime dataHoraCadastro ) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	@Transient
	public String getDataHoraCadastroToString() {
		if ( getDataHoraCadastro() != null ) {
			return Utilidades.parseLocalDateTime( getDataHoraCadastro() );
		}
		return null;
	}

	public void setDataHoraCadastroToString( String dataHoraCadastro ) {
		if ( dataHoraCadastro != null && !dataHoraCadastro.isEmpty() ) {
			setDataHoraCadrastro( Utilidades.parseLocalDateTime( dataHoraCadastro ) );
			return;
		}
		setDataHoraCadrastro( null );
	}

	@Override
	public abstract String toString();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals( Object obj );

}
