package xadrez;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.Cor;

public class Rei extends Peca{


    private PartidaDeXadrez partida;

    public Rei(Tabuleiro tab, Cor cor, PartidaDeXadrez partida)
    {
         super(tab, cor);
        this.partida = partida;
    }



    @Override
    public String toString(){

        return "R";
    }

    private boolean podeMover(Posicao pos)
    {
        Peca p = tabuleiro.peca(pos);
        return p == null || p.getCor() != cor;

    }

    private boolean testeTorreRoque(Posicao pos)
    {
      Peca p = tabuleiro.peca(pos);
      return p != null && p instanceof Torre && p.getCor() == cor && p.getQteMovimentos() == 0;

    }

    @Override
    public boolean[][] movimentosPossiveis()
    {

        boolean[][] mat = new boolean[tabuleiro.linhas][tabuleiro.colunas];
        Posicao pos = new Posicao(0,0);

        // acima

        pos.definirValores(posicao.linha - 1, posicao.coluna );
        if (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // nordeste

        pos.definirValores(posicao.linha - 1, posicao.coluna +1 );
        if (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // direita

        pos.definirValores(posicao.linha , posicao.coluna +1 );
        if (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // sudeste

        pos.definirValores(posicao.linha +1, posicao.coluna +1 );
        if (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // abaixo

        pos.definirValores(posicao.linha +1, posicao.coluna  );
        if (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // sudoeste

        pos.definirValores(posicao.linha +1, posicao.coluna -1  );
        if (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // esquerda

        pos.definirValores(posicao.linha , posicao.coluna -1  );
        if (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // noroeste

        pos.definirValores(posicao.linha -1 , posicao.coluna -1  );
        if (tabuleiro.posicaoValida(pos) && podeMover(pos))
        {
            mat[pos.linha][pos.coluna] = true;
        }

        // Jogada Especial Roque



        if (qteMovimentos == 0 && !partida.xeque) {

            // Roque pequeno
            Posicao posT1 = new Posicao(posicao.linha, posicao.coluna + 3);
            if (testeTorreRoque(posT1)) {
                Posicao ps1 = new Posicao(posicao.linha, posicao.coluna + 1);
                Posicao ps2 = new Posicao(posicao.linha, posicao.coluna + 2);
                if (tabuleiro.peca(ps1) == null && tabuleiro.peca(ps2) == null) {
                    mat[posicao.linha][posicao.coluna + 2] = true;
                }

            }


            // Roque Grande

            Posicao posT2 = new Posicao(posicao.linha, posicao.coluna - 4);
            if (testeTorreRoque(posT2)) {
                Posicao ps1 = new Posicao(posicao.linha, posicao.coluna - 1);
                Posicao ps2 = new Posicao(posicao.linha, posicao.coluna - 2);
                Posicao ps3 = new Posicao(posicao.linha, posicao.coluna - 3);
                if (tabuleiro.peca(ps1) == null && tabuleiro.peca(ps2) == null && tabuleiro.peca(ps3) == null) {
                    mat[posicao.linha][posicao.coluna - 2] = true;
                }

            }
        }




        return mat;






    }




}