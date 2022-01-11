package xadrez;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.Cor;

public class Bispo extends Peca {

    public Bispo(Tabuleiro tab, Cor cor)
    {
        super(tab, cor);
    }


    @Override
    public String toString()
    {
        return  "B";
    }


    private boolean podeMover(Posicao pos)
    {
        Peca p = tabuleiro.peca(pos);
        return p == null || p.getCor() != this.cor;
    }


    @Override
    public boolean[][] movimentosPossiveis()
    {

        boolean[][] mat = new boolean[tabuleiro.linhas][tabuleiro.colunas];
        Posicao pos = new Posicao(0,0);


        //Noroeste

        pos.definirValores(posicao.linha -1, posicao.coluna -1);
        while (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
            if (tabuleiro.peca(pos) != null && tabuleiro.peca(pos).getCor() != this.cor)
            {
                break;
            }

            pos.definirValores(pos.linha-1, pos.coluna -1);

        }


        // Nordeste


        pos.definirValores(posicao.linha -1, posicao.coluna +1);
        while (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
            if (tabuleiro.peca(pos) != null && tabuleiro.peca(pos).getCor() != this.cor)
            {
                break;
            }

            pos.definirValores(pos.linha-1, pos.coluna +1);

        }


         //Sudoeste

        pos.definirValores(posicao.linha +1, posicao.coluna -1);
        while (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
            if (tabuleiro.peca(pos) != null && tabuleiro.peca(pos).getCor() != this.cor)
            {
                break;
            }

            pos.definirValores(pos.linha+1, pos.coluna -1);

        }


        //Sudeste

        pos.definirValores(posicao.linha +1, posicao.coluna +1);
        while (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
            if (tabuleiro.peca(pos) != null && tabuleiro.peca(pos).getCor() != this.cor)
            {
                break;
            }

            pos.definirValores(pos.linha+1, pos.coluna +1);

        }


        return mat;
    }






}
