package xadrez;

import tabuleiro.Cor;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Cavalo extends Peca {


    public Cavalo (Tabuleiro tab, Cor cor)
    {
        super(tab,cor);

    }

    @Override
    public String toString()
    {
        return  "C";
    }


    public boolean podeMover(Posicao pos)
    {

        Peca p = tabuleiro.peca(pos);
        return p == null || p.getCor() != this.cor;

    }

    @Override
    public boolean[][] movimentosPossiveis()
    {
        boolean[][] mat = new boolean[tabuleiro.linhas][tabuleiro.colunas];
        Posicao pos = new Posicao(0,0);



        //  Acima ¨I
        pos.definirValores(posicao.linha -2, posicao.coluna -1 );
        if ( tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // Acima I¨

        pos.definirValores(posicao.linha -2, posicao.coluna +1 );
        if ( tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // Acima  __I

        pos.definirValores(posicao.linha -1, posicao.coluna -2 );
        if ( tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // Acima  I__

        pos.definirValores(posicao.linha -1, posicao.coluna +2 );
        if ( tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        //  Abaixo .I
        pos.definirValores(posicao.linha +2, posicao.coluna -1 );
        if ( tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        //  Abaixo I.
        pos.definirValores(posicao.linha +2, posicao.coluna +1 );
        if ( tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }


        //  Abaixo  I¨¨
        pos.definirValores(posicao.linha +1, posicao.coluna -2 );
        if ( tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        //  Abaixo  ¨¨I
        pos.definirValores(posicao.linha +1, posicao.coluna +2 );
        if ( tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        return mat;

    }









}
