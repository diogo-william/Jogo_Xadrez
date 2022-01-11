package Main;

import jdk.swing.interop.SwingInterOpUtils;
import tabuleiro.ConsoleCor;
import tabuleiro.Cor;
import tabuleiro.Tabuleiro;
import tabuleiro.Peca;
import xadrez.PartidaDeXadrez;
import xadrez.PosicaoXadrez;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Scanner;

public class Tela {

    public static void imprimirTabuleiro(Tabuleiro tab)
    {

    for (int i =0; i < tab.linhas; i++)
    {
        System.out.print(8 - i  +" ");

        for (int j =0; j < tab.colunas; j++)
        {


           /* if ( tab.peca(i,j) == null) {
                System.out.print("-  ");
            }
            else{

            */
                imprimirPeca(tab.peca(i,j));
               /* System.out.print("  ");
            } */

        }
        System.out.println();


    }
        System.out.print(ConsoleCor.LIGHT_BLUE);
        System.out.print("  A  B  C  D  E  F  G  H");
        System.out.print(ConsoleCor.RESET);

    }


    public static void imprimirTabuleiro(Tabuleiro tab, boolean[][] posicoesPossiveis)
    {

        for (int i =0; i < tab.linhas; i++)
        {
            System.out.print(8 - i  +" ");

            for (int j =0; j < tab.colunas; j++)
            {
                if(posicoesPossiveis[i][j] == true){
                    System.out.print(ConsoleCor.RED);
                    System.out.flush();

                }
                else
                {
                    System.out.print(ConsoleCor.RESET);
                }


            imprimirPeca(tab.peca(i,j));


            }
            System.out.print(ConsoleCor.RESET);
            System.out.println();


        }
        System.out.print(ConsoleCor.RESET);
        System.out.print("  A  B  C  D  E  F  G  H");

    }


    public static void imprimirPeca(Peca peca)
    {
        if (peca == null)
        {
            System.out.print("-  ");
        }
        else {

            if (peca.getCor() == Cor.Branca) {
                System.out.print(peca);
            } else {
                System.out.print(ConsoleCor.YELLOW + peca);
                System.out.print(ConsoleCor.RESET);

            }
            System.out.print("  ");

        }


    }

/*

    public static void limpaTela()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

 */


    public static void imprimirPartida(PartidaDeXadrez partida) {
        imprimirTabuleiro(partida.tab);
        System.out.println();
        imprimirPecasCapturadas(partida);
        System.out.println();
        System.out.println("Turno " + partida.turno);
        if (!partida.terminada) {
            System.out.println("Aguardando jogada " + partida.jogadorAtual);
            if (partida.xeque) {
                System.out.println("XEQUE!!");
            }

        }
        else
        {
            System.out.println();
            System.out.println("XEQUEMATE!!");
            System.out.println("Vecendor "+partida.jogadorAtual);
        }
    }

    public static void imprimirPecasCapturadas(PartidaDeXadrez partida)
    {
        System.out.println("Pecas Capturadas");
        System.out.print("Brancas");
        imprimirConjunto(partida.pecasCapturadas(Cor.Branca));
        System.out.println();
        System.out.print("Pretas");
        System.out.print(ConsoleCor.YELLOW);
        imprimirConjunto(partida.pecasCapturadas(Cor.Preta));
        ConsoleCor.reseta();

    }


    public static void imprimirConjunto(HashSet<Peca> conjunto)
    {
        System.out.print("[");
        for(Peca p : conjunto)
        {
            System.out.print(p+" ");
        }
        System.out.print("]");

    }



    public static PosicaoXadrez lerPosicaoXadrez()
    {
        Scanner leia = new Scanner(System.in);
        String s = leia.next();
        char coluna = s.charAt(0);
        int linha = s.charAt(1) - '0';
        return new PosicaoXadrez(coluna,linha);

    }


}
