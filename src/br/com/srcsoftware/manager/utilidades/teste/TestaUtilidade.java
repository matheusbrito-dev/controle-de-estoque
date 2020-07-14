package br.com.srcsoftware.manager.utilidades.teste;

import java.math.BigDecimal;

import br.com.srcsoftware.manager.utilidades.Utilidades;

public class TestaUtilidade{

	public static void main( String[ ] args ) {

		System.out.println( Utilidades.parseBigDecimal( new BigDecimal( "1923.12" ), "R$" ) );

	}

}
