package xadrez;

import tabuleiro.Cor;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Peao extends Peca {

    private PartidaDeXadrez partida;

    public Peao (Tabuleiro tab, Cor cor, PartidaDeXadrez partida)
    {
        super ( tab,cor);
        this.partida = partida;
    }


    @Override
    public String toString()
    {
        return "p";
    }

    private  boolean existeInimigo(Posicao pos)
    {
        Peca p = tabuleiro.peca(pos);
        return p != null && p.getCor() != cor;


    }


    private boolean livre (Posicao pos)
    {
        return  tabuleiro.peca(pos) == null;

    }


    @Override
    public boolean[][] movimentosPossiveis()
    {

        boolean[][] mat = new boolean[tabuleiro.linhas][tabuleiro.colunas];
        Posicao pos = new Posicao(0,0);

        if(cor == Cor.Branca)
        {

            pos.definirValores(posicao.linha -1, posicao.coluna);
            if(tabuleiro.posicaoValida(pos) && livre(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }

            pos.definirValores(posicao.linha -2, posicao.coluna);
            if(tabuleiro.posicaoValida(pos) && livre(pos) && qteMovimentos == 0)
            {
                mat[pos.linha][pos.coluna] = true;
            }

            pos.definirValores(posicao.linha -1, posicao.coluna -1);
            if(tabuleiro.posicaoValida(pos) && existeInimigo(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }

            pos.definirValores(posicao.linha -1, posicao.coluna +1);
            if(tabuleiro.posicaoValida(pos) && existeInimigo(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }

            // jogada especial en passant

            if(posicao.linha == 3)
            {
                Posicao esq = new Posicao(posicao.linha, posicao.coluna -1);
                if (tabuleiro.posicaoValida(esq) && existeInimigo(esq) && tabuleiro.peca(esq) == partida.getVulneravelPassant() )
                {
                    mat[esq.linha-1][esq.coluna] =true;

                }

                Posicao dir = new Posicao(posicao.linha, posicao.coluna +1);
                if (tabuleiro.posicaoValida(dir) && existeInimigo(dir) && tabuleiro.peca(dir) == partida.getVulneravelPassant() )
                {
                    mat[dir.linha-1][dir.coluna] =true;

                }




            }







        }
        else
        {
            pos.definirValores(posicao.linha +1, posicao.coluna);
            if(tabuleiro.posicaoValida(pos) && livre(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }

            pos.definirValores(posicao.linha +2, posicao.coluna);
            if(tabuleiro.posicaoValida(pos) && livre(pos) && qteMovimentos == 0)
            {
                mat[pos.linha][pos.coluna] = true;
            }

            pos.definirValores(posicao.linha +1, posicao.coluna -1);
            if(tabuleiro.posicaoValida(pos) && existeInimigo(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }

            pos.definirValores(posicao.linha +1, posicao.coluna +1);
            if(tabuleiro.posicaoValida(pos) && existeInimigo(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }

            if(posicao.linha == 4)
            {
                Posicao esq = new Posicao(posicao.linha, posicao.coluna -1);
                if (tabuleiro.posicaoValida(esq) && existeInimigo(esq) && tabuleiro.peca(esq) == partida.getVulneravelPassant() )
                {
                    mat[esq.linha+1][esq.coluna] =true;

                }

                Posicao dir = new Posicao(posicao.linha, posicao.coluna +1);
                if (tabuleiro.posicaoValida(dir) && existeInimigo(esq) && tabuleiro.peca(dir) == partida.getVulneravelPassant() )
                {
                    mat[dir.linha+1][dir.coluna] =true;

                }




            }


        }

return mat;

    }





}
