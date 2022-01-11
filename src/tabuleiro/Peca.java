package tabuleiro;

public abstract class Peca {

    public Posicao posicao;
    protected Cor cor;
    protected int qteMovimentos;
    protected Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro, Cor cor) {
        this.posicao = null;
        this.cor = cor;
        this.tabuleiro = tabuleiro;
        this.qteMovimentos = 0;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public int getQteMovimentos() { return qteMovimentos; }

    public void setQteMovimentos(int qteMovimentos) {
        this.qteMovimentos = qteMovimentos;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void incrementarQtdMovimento()
    {
        qteMovimentos++;
    }

    public void decrementarQtdMovimento()
    {
        qteMovimentos--;
    }

    public abstract boolean[][] movimentosPossiveis();




    @Override
    public String toString() {
        return "Peca{" +
                "posicao=" + posicao +
                ", cor=" + cor +
                ", qteMovimentos=" + qteMovimentos +
                ", tabuleiro=" + tabuleiro +
                '}';
    }



    public boolean existeMovimentosPossiveis()
    {
        boolean[][] mat = movimentosPossiveis();

        for (int i = 0; i < tabuleiro.linhas; i ++)
        {
            for (int j =0; j < tabuleiro.colunas; j++)
            {
                if (mat[i][j])
                {
                    return  true;
                }

            }
        }

        return  false;

    }

    public boolean movimentoPossivel(Posicao pos)
    {
        return movimentosPossiveis()[pos.linha][pos.coluna];

    }









}
