package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.controller.ProdutoFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoPO;
import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.view.ProdutoForm;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.utilidades.Messages;

public class ProdutoAction extends DispatchAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		//Aplicando Especialização de ActionForm para Produto Form
		ProdutoForm meuForm = (ProdutoForm) form;

		meuForm.limparTela();

		return filtrar( mapping, meuForm, request, response );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			//Aplicando Especialização de ActionForm para Produto Form
			ProdutoForm meuForm = (ProdutoForm) form;

			List< ProdutoPO > encontrados;

			ProdutoFACADE facade = new ProdutoFACADE();
			encontrados = facade.filtrar( meuForm.getProduto() );

			meuForm.getProdutos().clear();
			meuForm.getProdutos().addAll( encontrados );

		} catch ( BackendException e ) {
			e.printStackTrace();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return mapping.findForward( "produtoView" );
	}

	public ActionForward limpar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		return abrirTela( mapping, form, request, response );
	}

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			//Aplicando Especialização de ActionForm para Produto Form
			ProdutoForm meuForm = (ProdutoForm) form;

			ProdutoFACADE facade = new ProdutoFACADE();
			facade.inserir( meuForm.getProduto() );

			meuForm.limparTela();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", "Erro desconhecido, " + e.getMessage() ) );
		}
		return filtrar( mapping, form, request, response );
	}

	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			//Aplicando Especialização de ActionForm para Produto Form
			ProdutoForm meuForm = (ProdutoForm) form;

			ProdutoFACADE facade = new ProdutoFACADE();
			facade.alterar( meuForm.getProduto() );

			meuForm.limparTela();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", "Erro desconhecido, " + e.getMessage() ) );
		}
		return filtrar( mapping, form, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			//Aplicando Especialização de ActionForm para Produto Form
			ProdutoForm meuForm = (ProdutoForm) form;

			ProdutoFACADE facade = new ProdutoFACADE();
			facade.excluir( meuForm.getProduto() );

			meuForm.limparTela();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( BackendException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", "Erro desconhecido, " + e.getMessage() ) );
		}
		return filtrar( mapping, form, request, response );
	}

	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			//Aplicando Especialização de ActionForm para Produto Form
			ProdutoForm meuForm = (ProdutoForm) form;

			ProdutoPO encontrado;

			ProdutoFACADE facade = new ProdutoFACADE();
			encontrado = (ProdutoPO) facade.filtrarPorId( meuForm.getIdSelecionar() );

			meuForm.setProduto( encontrado );

		} catch ( BackendException e ) {
			e.printStackTrace();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return mapping.findForward( "produtoView" );
	}

}
