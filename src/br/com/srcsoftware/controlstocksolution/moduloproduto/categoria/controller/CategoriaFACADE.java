package br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.controller;

import java.util.List;

import br.com.srcsoftware.controlstocksolution.moduloproduto.categoria.model.CategoriaSERVICE;
import br.com.srcsoftware.manager.abstracts.AbstractPO;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;

/**
 * 
 * 
 * Classe que representa a liga��o entre Front-end e Back-end.
 * Classe respons�vel por interligar a camda VIEW com a camada de
 * MODEL(neg�cio)
 *
 *
 * @author Matheus de Brito Vieira Martins <mathbvm@gmail.com.br>
 * @since 24 de jul de 2018 22:24:49
 * @version 1.0
 */
public final class CategoriaFACADE implements Crud{

	/** Garante a aplica��o da associa��o entre o CONTROLLER e o SERVICE */
	private final CategoriaSERVICE SERVICE;

	public CategoriaFACADE(){
		SERVICE = new CategoriaSERVICE();
	}

	@Override
	public void inserir( AbstractPO po ) throws BackendException {
		System.out.println( "FACADE: inserindo" );
		SERVICE.inserir( po );

	}

	@Override
	public void alterar( AbstractPO po ) throws BackendException {
		System.out.println( "FACADE: alterando" );
		SERVICE.alterar( po );

	}

	@Override
	public void excluir( AbstractPO po ) throws BackendException {
		System.out.println( "FACADE: excluindo" );
		SERVICE.excluir( po );

	}

	@Override
	public List filtrar( AbstractPO po ) throws BackendException {
		System.out.println( "FACADE: filtrando" );
		return SERVICE.filtrar( po );
	}

	@Override
	public AbstractPO filtrarPorId( String id ) throws BackendException {
		System.out.println( "FACADE: filtrando por id" );
		return SERVICE.filtrarPorId( id );
	}

}
