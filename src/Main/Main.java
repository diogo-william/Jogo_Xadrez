package Main;

import tabuleiro.*;
import xadrez.PartidaDeXadrez;
import xadrez.PosicaoXadrez;
import xadrez.Rei;
import xadrez.Torre;
import java.util.Scanner;
import javax.swing.*;

import java.io.Console;

public class Main {

    public static void main(String[] args) throws TabuleiroException {

        Scanner leia = new Scanner(System.in);
        PartidaDeXadrez partida = new PartidaDeXadrez();


        while (!partida.isTerminada()) {


            try {

                ConsoleCor.limpaTela();
                Tela.imprimirPartida(partida);
                System.out.println();
                System.out.println("Origem");

                Posicao origem = Tela.lerPosicaoXadrez().toPosicao();


                partida.validarPosicaoOrigem(origem);


                boolean[][] posicoesPossiveis = partida.tab.peca(origem).movimentosPossiveis();

                ConsoleCor.limpaTela();
                Tela.imprimirTabuleiro(partida.tab, posicoesPossiveis);
                System.out.println();
                System.out.println("Destino");
                Posicao destino = Tela.lerPosicaoXadrez().toPosicao();
                partida.validarPosicaoDestino(origem, destino);
                partida.realizaJogada(origem, destino);


            } catch (TabuleiroException e) {
                System.out.println(e);


            }
            ConsoleCor.limpaTela();
            Tela.imprimirPartida(partida);


        }


        System.out.println();

    }


}













