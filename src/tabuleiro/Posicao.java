package tabuleiro;

public class Posicao {

    public int linha;
    public int coluna;

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void definirValores(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;

    }

    @Override
    public String toString() {
        return "Posicao{" +
                "linha=" + linha +
                ", coluna=" + coluna +
                '}';
    }
}
