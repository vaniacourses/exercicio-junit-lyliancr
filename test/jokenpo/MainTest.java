package jokenpo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import jokenpo.Main;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


public class MainTest {

    private final Main jogo = new Main();

    /**
     * Testes de empate (mesmas escolhas)
     */
    @Test
    public void testEmpatePapel() {
        assertEquals(0, jogo.jogar(1, 1), "Papel vs Papel deve resultar em empate");
    }

    @Test
    public void testEmpatePedra() {
        assertEquals(0, jogo.jogar(2, 2), "Pedra vs Pedra deve resultar em empate");
    }

    @Test
    public void testEmpateTesoura() {
        assertEquals(0, jogo.jogar(3, 3), "Tesoura vs Tesoura deve resultar em empate");
    }

    /**
     * Testes de vitória do jogador 1
     */
    @Test
    public void testJogador1VenceComPapelContraPedra() {
        assertEquals(1, jogo.jogar(1, 2), "Papel vence Pedra");
    }

    @Test
    public void testJogador1VenceComPedraContraTesoura() {
        assertEquals(1, jogo.jogar(2, 3), "Pedra vence Tesoura");
    }

    @Test
    public void testJogador1VenceComTesouraContraPapel() {
        assertEquals(1, jogo.jogar(3, 1), "Tesoura vence Papel");
    }

    /**
     * Testes de vitória do jogador 2
     */
    @Test
    public void testJogador2VenceComPapelContraPedra() {
        assertEquals(2, jogo.jogar(2, 1), "Papel vence Pedra (jogador 2)");
    }

    @Test
    public void testJogador2VenceComPedraContraTesoura() {
        assertEquals(2, jogo.jogar(3, 2), "Pedra vence Tesoura (jogador 2)");
    }

    @Test
    public void testJogador2VenceComTesouraContraPapel() {
        assertEquals(2, jogo.jogar(1, 3), "Tesoura vence Papel (jogador 2)");
    }

    /**
     * Testes de entradas inválidas
     */
    @Test
    public void testJogador1InvalidoMenorQue1() {
        assertEquals(-1, jogo.jogar(0, 2), "Escolha inválida (jogador1 < 1)");
    }

    @Test
    public void testJogador1InvalidoMaiorQue3() {
        assertEquals(-1, jogo.jogar(4, 2), "Escolha inválida (jogador1 > 3)");
    }

    @Test
    public void testJogador2InvalidoMenorQue1() {
        assertEquals(-1, jogo.jogar(2, 0), "Escolha inválida (jogador2 < 1)");
    }

    @Test
    public void testJogador2InvalidoMaiorQue3() {
        assertEquals(-1, jogo.jogar(1, 5), "Escolha inválida (jogador2 > 3)");
    }

    @Test
    public void testAmbosInvalidos() {
        assertEquals(-1, jogo.jogar(-1, 7), "Ambos jogadores inválidos");
    }
}
