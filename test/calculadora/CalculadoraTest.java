package calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse; // Importado para ehPositivo
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

	// --- Testes de Soma ---
	
	@DisplayName("Testa a soma de dois números positivos")
	@Test
	public void testSomaDoisNumeros() {
		int soma = calc.soma(4, 5);		
		Assertions.assertEquals(9, soma);		
	}

	@Test
	@DisplayName("Testa a soma com número negativo")
	public void testSomaComNegativo() {
		assertEquals(5, calc.soma(10, -5));
	}

	@Test
	@DisplayName("Testa a soma de dois números negativos")
	public void testSomaDoisNegativos() {
		assertEquals(-15, calc.soma(-10, -5));
	}

	@Test
	@DisplayName("Testa a soma com zero (identidade)")
	public void testSomaComZero() {
		assertEquals(10, calc.soma(10, 0));
		assertEquals(5, calc.soma(0, 5));
		assertEquals(0, calc.soma(0, 0));
	}

	// --- Testes de Subtração ---

	@Test
	@DisplayName("Testa a subtração de dois números")
	public void subtracaoTest() {
		int subtracao = calc.subtracao(10, 5);		
		Assertions.assertEquals(5, subtracao);
	}

	@Test
	@DisplayName("Testa a subtração resultando em negativo")
	public void testSubtracaoResultadoNegativo() {
		assertEquals(-5, calc.subtracao(5, 10));
	}

	@Test
	@DisplayName("Testa a subtração de número negativo (vira soma)")
	public void testSubtracaoDeNegativo() {
		assertEquals(15, calc.subtracao(10, -5));
	}

	@Test
	@DisplayName("Testa a subtração com zero")
	public void testSubtracaoComZero() {
		assertEquals(10, calc.subtracao(10, 0));
		assertEquals(-5, calc.subtracao(0, 5));
	}

	// --- Testes de Multiplicação ---

	@Test
	@DisplayName("Testa a multiplicação de dois números")
	public void multiplicacaoTest() {
		int multiplicacao = calc.multiplicacao(2,3);
		Assertions.assertEquals(6, multiplicacao);
	}

	@Test
	@DisplayName("Testa a multiplicação por zero")
	public void testMultiplicacaoPorZero() {
		assertEquals(0, calc.multiplicacao(10, 0));
		assertEquals(0, calc.multiplicacao(0, 5));
		assertEquals(0, calc.multiplicacao(0, 0));
	}

	@Test
	@DisplayName("Testa a multiplicação com número negativo")
	public void testMultiplicacaoComNegativo() {
		assertEquals(-10, calc.multiplicacao(5, -2));
		assertEquals(-10, calc.multiplicacao(-5, 2));
	}

	@Test
	@DisplayName("Testa a multiplicação de dois números negativos")
	public void testMultiplicacaoDoisNegativos() {
		assertEquals(10, calc.multiplicacao(-5, -2));
	}

	@Test
	@DisplayName("Testa a multiplicação por um (identidade)")
	public void testMultiplicacaoPorUm() {
		assertEquals(5, calc.multiplicacao(5, 1));
		assertEquals(5, calc.multiplicacao(1, 5));
	}
	
	// --- Testes de Divisão ---
	
	@Test
	@DisplayName("Testa a divisão de dois números")
	public void testDivisaoDoisNumeros() {
		int divisao = calc.divisao(8, 4);
		assertTrue(divisao == 2);
	}
	
	@Test
	@DisplayName("Testa a divisão por zero (try-catch)")
	public void testDivisaoPorZero() {
		try {
			int divisao = calc.divisao(8, 0);
			fail("Exceção não lançada"); // Falha se a exceção não for lançada
		}catch (ArithmeticException e) {
			assertEquals("/ by zero", e.getMessage());
		}		
	}
	
	@Test
	@DisplayName("Testa a divisão por zero (assertThrows)")
	public void testDivisaoPorZeroComAssertThrows() {
		assertThrows(ArithmeticException.class,
				() -> calc.divisao(8, 0));
	}

	@Test
	@DisplayName("Testa a divisão de zero por um número")
	public void testDivisaoDeZero() {
		assertEquals(0, calc.divisao(0, 5));
	}

	@Test
	@DisplayName("Testa a divisão com truncamento (divisão inteira)")
	public void testDivisaoComTruncamento() {
		// 10 / 3 = 3.333... deve resultar em 3
		assertEquals(3, calc.divisao(10, 3));
	}

	@Test
	@DisplayName("Testa a divisão com números negativos")
	public void testDivisaoComNegativos() {
		assertEquals(-2, calc.divisao(10, -5));
		assertEquals(-2, calc.divisao(-10, 5));
		
		// LINHA CORRIGIDA:
		assertEquals(5, calc.divisao(-10, -2)); // O esperado é 5
	}

	// --- Testes de Somatória ---
	
	@Test
	@DisplayName("Testa a somatória de N=5")
	public void somatoriaTest() {
		// 5 + 4 + 3 + 2 + 1 + 0 = 15
		int somatoria = calc.somatoria(5);
		Assertions.assertEquals(15, somatoria);
	}

	@Test
	@DisplayName("Testa a somatória de N=1 (caso base)")
	public void testSomatoriaDeUm() {
		// 1 + 0 = 1
		assertEquals(1, calc.somatoria(1));
	}

	@Test
	@DisplayName("Testa a somatória de N=0 (caso limite)")
	public void testSomatoriaDeZero() {
		// 0 = 0
		assertEquals(0, calc.somatoria(0));
	}

	@Test
	@DisplayName("Testa a somatória de N negativo (edge case)")
	public void testSomatoriaDeNegativo() {
		// Loop while (n >= 0) não deve executar
		assertEquals(0, calc.somatoria(-5));
	}
	
	// --- Testes de ehPositivo ---
	
	@Test
	@DisplayName("Testa ehPositivo com número negativo")
	public void ehPositivoTest() {
		boolean positivo = calc.ehPositivo(-5);
		Assertions.assertEquals(false, positivo); // Original
		assertFalse(positivo); // Forma mais idiomática
	}

	@Test
	@DisplayName("Testa ehPositivo com número positivo")
	public void testEhPositivoComPositivo() {
		assertTrue(calc.ehPositivo(5));
	}

	@Test
	@DisplayName("Testa ehPositivo com zero (caso limite)")
	public void testEhPositivoComZero() {
		// A regra é n >= 0, então 0 deve ser considerado positivo
		assertTrue(calc.ehPositivo(0));
	}
	
	// --- Testes de Compara ---
	
	@Test
	@DisplayName("Testa compara A > B")
	public void comparaMaiorTest() {
		int retorno = calc.compara(4, 3);
		Assertions.assertEquals(1, retorno);	
	}
	
	@Test
	@DisplayName("Testa compara A < B")
	public void comparaMenorTest() {
		int retorno = calc.compara(2, 3);
		Assertions.assertEquals(-1, retorno);	
	}
	
	@Test
	@DisplayName("Testa compara A == B")
	public void comparaIgualTest() {
		int retorno = calc.compara(3, 3);
		Assertions.assertEquals(0, retorno);	
	}

	@Test
	@DisplayName("Testa compara com números negativos")
	public void testComparaComNegativos() {
		assertEquals(1, calc.compara(-2, -5), "Comparando -2 > -5");
		assertEquals(-1, calc.compara(-10, -5), "Comparando -10 < -5");
		assertEquals(0, calc.compara(-4, -4), "Comparando -4 == -4");
		assertEquals(1, calc.compara(5, -5), "Comparando 5 > -5");
	}
	
}