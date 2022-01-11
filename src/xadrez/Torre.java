package xadrez;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.Cor;

public class Torre extends Peca{



    public Torre(Tabuleiro tab, Cor cor) {
        super(tab, cor);
    }

    @Override
    public String toString(){

        return "T";
    }

    private boolean podeMover(Posicao pos)
    {
        Peca p = tabuleiro.peca(pos);
        return p == null || p.getCor() != cor;
    }


    @Override
    public boolean[][] movimentosPossiveis()
    {
        boolean[][] mat = new boolean[tabuleiro.linhas][tabuleiro.colunas];
        Posicao pos = new Posicao(0,0);


        // acima
        pos.definirValores(posicao.linha -1 , posicao.coluna);
        while (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
            if (tabuleiro.peca(pos) != null && tabuleiro.peca(pos).getCor() != this.cor)
            {
                break;
            }
            pos.linha = pos.linha -1;

        }

        // abaixo
        pos.definirValores(posicao.linha +1 , posicao.coluna);
        while (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
            if (tabuleiro.peca(pos) != null && tabuleiro.peca(pos).getCor() != this.cor)
            {
                break;
            }
            pos.linha = pos.linha +1;

        }

        // direita
        pos.definirValores(posicao.linha  , posicao.coluna + 1);
        while (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
            if (tabuleiro.peca(pos) != null && tabuleiro.peca(pos).getCor() != this.cor)
            {
                break;
            }
            pos.coluna = pos.coluna +1;

        }

        // esquerda
        pos.definirValores(posicao.linha  , posicao.coluna - 1);
        while (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
            if (tabuleiro.peca(pos) != null && tabuleiro.peca(pos).getCor() != this.cor)
            {
                break;
            }
            pos.coluna = pos.coluna -1;

        }


        return mat;






    }




}