package carrinho;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produto.Produto;
import produto.ProdutoNaoEncontradoException;

@DisplayName("Classe para teste da carrinho")
public class carrinhoTest {
	
	private Carrinho carr;
    private Produto produtoA;
    private Produto produtoB;

    
	@BeforeEach
	public void inicializa() {
		carr = new Carrinho();
		produtoA = new Produto("Caneta",10);
	    produtoB = new Produto("Caderno",15);
	}
	
	@DisplayName("Testa a soma de dois n�meros")
	@Test

	
	public void addItemTest() {
		carr.addItem(produtoA);
		carr.addItem(produtoB);
		
		assertEquals(2, carr.getQtdeItems());
		
	}
	
	public void removeItemTest() throws ProdutoNaoEncontradoException {
		carr.addItem(produtoA);
		carr.addItem(produtoB);
		
		carr.removeItem(produtoA);
		
		assertEquals(1, carr.getQtdeItems());
		
	}
	
	public void esvaziaTest() {
		carr.addItem(produtoA);
		carr.addItem(produtoB);
		
		carr.esvazia();
		assertEquals(0, carr.getQtdeItems());
		
	}
	
	
	
	public void getValorTotalTest() {

        
        carr.addItem(produtoA);
        carr.addItem(produtoB);
        
        
        assertEquals(25, carr.getValorTotal());
        
	}
	
}
