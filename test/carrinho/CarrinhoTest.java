package carrinho;

// CORRIGIDO: Imports estáticos centralizados e organizados
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produto.Produto;
import produto.ProdutoNaoEncontradoException;

@DisplayName("Classe para teste do Carrinho") // CORRIGIDO: Nome da classe de teste
public class CarrinhoTest {
	
	private Carrinho carr;
    private Produto produtoA;
    private Produto produtoB;

    
	@BeforeEach
	public void inicializa() {
		carr = new Carrinho();
		// Nota: Assumi que o preço é double, pois getValorTotal() retorna double.
		produtoA = new Produto("Caneta", 10.0); 
	    produtoB = new Produto("Caderno", 15.0);
	}
	
	// --- Testes de Adicionar Item e Quantidade ---
	
	@Test // CORRIGIDO: Removido DisplayName incorreto
	@DisplayName("Testa adicionar dois itens e verificar a quantidade")
	public void addItemTest() {
		carr.addItem(produtoA);
		carr.addItem(produtoB);
		
		assertEquals(2, carr.getQtdeItems());
	}
	
	@Test
	@DisplayName("Testa adicionar o mesmo item duas vezes (duplicata)")
	public void testAdicionarItemDuplicado() {
		carr.addItem(produtoA);
		carr.addItem(produtoA);
		
		// O ArrayList deve aceitar duplicatas
		assertEquals(2, carr.getQtdeItems());
	}

	// --- Testes de Remover Item ---

	@Test // CORRIGIDO: Adicionada anotação @Test
	@DisplayName("Testa remover um item existente")
	public void removeItemTest() throws ProdutoNaoEncontradoException {
		carr.addItem(produtoA);
		carr.addItem(produtoB); // Carrinho: [A, B]
		
		carr.removeItem(produtoA); // Carrinho: [B]
		
		assertEquals(1, carr.getQtdeItems());
	}
	
	@Test // NOVO: Teste de exceção (assertThrows)
	@DisplayName("Testa remover item que não está no carrinho")
	public void testRemoverItemNaoExistente() {
		carr.addItem(produtoA); // Adiciona A
		
		// Tenta remover B (que não foi adicionado)
		assertThrows(ProdutoNaoEncontradoException.class, () -> {
			carr.removeItem(produtoB);
		});
		
		// Garante que o item A ainda está lá
		assertEquals(1, carr.getQtdeItems());
	}

	@Test // NOVO: Teste de exceção em carrinho vazio
	@DisplayName("Testa remover item de um carrinho vazio")
	public void testRemoverItemDeCarrinhoVazio() {
		// Tenta remover qualquer item de um carrinho vazio
		assertThrows(ProdutoNaoEncontradoException.class, () -> {
			carr.removeItem(produtoA);
		});
	}
	
	@Test // NOVO: Teste de remoção de duplicata
	@DisplayName("Testa remover item duplicado (remove apenas a primeira instância)")
	public void testRemoverItemDuplicado() throws ProdutoNaoEncontradoException {
		carr.addItem(produtoA); // 10.0
		carr.addItem(produtoB); // 15.0
		carr.addItem(produtoA); // 10.0
		// Carrinho: [A, B, A], Total = 35.0
		
		assertEquals(3, carr.getQtdeItems());
		
		carr.removeItem(produtoA); // Remove a primeira ocorrência de A
		// Carrinho: [B, A], Total = 25.0
		
		assertEquals(2, carr.getQtdeItems());
		assertEquals(25.0, carr.getValorTotal()); // Verifica se o total bate
	}

	// --- Testes de Esvaziar ---

	@Test // CORRIGIDO: Adicionada anotação @Test
	@DisplayName("Testa esvaziar um carrinho com itens")
	public void esvaziaTest() {
		carr.addItem(produtoA);
		carr.addItem(produtoB);
		
		carr.esvazia();
		
		assertEquals(0, carr.getQtdeItems());
	}
	
	@Test // NOVO: Teste de caso de borda
	@DisplayName("Testa esvaziar um carrinho já vazio")
	public void testEsvaziarCarrinhoJaVazio() {
		assertEquals(0, carr.getQtdeItems()); // Confirma que está vazio
		carr.esvazia(); // Não deve lançar exceção
		assertEquals(0, carr.getQtdeItems()); // Continua vazio
	}
	
	
	// --- Testes de Valor Total ---

	@Test // CORRIGIDO: Adicionada anotação @Test
	@DisplayName("Testa o valor total com múltiplos itens")
	public void getValorTotalTest() {
        carr.addItem(produtoA); // 10.0
        carr.addItem(produtoB); // 15.0
        
        // Usar 25.0 (double) para comparar com um retorno double é mais seguro
        assertEquals(25.0, carr.getValorTotal());
	}
	
	@Test // NOVO: Teste de caso de borda
	@DisplayName("Testa o valor total de um carrinho vazio")
	public void testValorTotalCarrinhoVazio() {
		assertEquals(0.0, carr.getValorTotal());
	}
	
	@Test // NOVO: Teste de interação
	@DisplayName("Testa valor total após adicionar e remover item")
	public void testValorTotalAposRemocao() throws ProdutoNaoEncontradoException {
		carr.addItem(produtoA); // 10.0
		carr.addItem(produtoB); // 15.0
		carr.removeItem(produtoA); // Remove 10.0
		
		assertEquals(15.0, carr.getValorTotal()); // Deve sobrar só o valor de B
	}
}