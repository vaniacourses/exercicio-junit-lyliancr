package calculadora;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe para teste da calculadora")
public class CalculadoraTest {
	
	private Calculadora calc;
	
	@BeforeEach
	public void inicializa() {
		calc = new Calculadora();
	}
	
	@DisplayName("Testa a soma de dois n�meros")
	@Test
	public void testSomaDoisNumeros() {
		int soma = calc.soma(4, 5);		
		Assertions.assertEquals(9, soma);		
	}
	
	@Test
	public void testDivisaoDoisNumeros() {
		int divisao = calc.divisao(8, 4);
		assertTrue(divisao == 2);
	}
	
	@Test
	public void testDivisaoPorZero() {
		try {
			int divisao = calc.divisao(8, 0);
			fail("Exce��o n�o lan�ada");
		}catch (ArithmeticException e) {
			assertEquals("/ by zero", e.getMessage());
		}		
	}
	
	@Test
	public void testDivisaoPorZeroComAssertThrows() {
		assertThrows(ArithmeticException.class,
				() -> calc.divisao(8, 0));
	}
	
	@Test
	public void subtracaoTest() {
		int subtracao = calc.subtracao(10, 5);		
		Assertions.assertEquals(5, subtracao);
	}

	@Test
	public void multiplicacaoTest() {
		int multiplicacao = calc.multiplicacao(2,3);
		Assertions.assertEquals(6, multiplicacao);
	}
	
	@Test
	public void somatoriaTest() {
		int somatoria = calc.somatoria(5);
		Assertions.assertEquals(15, somatoria);
	}
	
	@Test
	public void ehPositivoTest() {
		boolean positivo = calc.ehPositivo(-5);
		Assertions.assertEquals(false, positivo);
	}
	
	@Test
	public void comparaMaiorTest() {
		int retorno = calc.compara(4, 3);
		Assertions.assertEquals(1, retorno);	
	}
	
	@Test
	public void comparaMenorTest() {
		int retorno = calc.compara(2, 3);
		Assertions.assertEquals(-1, retorno);	
	}
	
	@Test
	public void comparaIgualTest() {
		int retorno = calc.compara(3, 3);
		Assertions.assertEquals(0, retorno);	
	}
	
}
