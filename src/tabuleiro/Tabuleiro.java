package tabuleiro;

public class Tabuleiro {

    public int linhas;
    public int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linha, int coluna) {
        this.linhas = linha;
        this.colunas = coluna;
        pecas = new Peca[linha][coluna];
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public Peca[][] getPecas() {
        return pecas;
    }

    public void setPecas(Peca[][] pecas) {
        this.pecas = pecas;
    }

    public Peca peca(int linha, int coluna)
    {
        return pecas [linha][coluna];

    }

    public Peca peca(Posicao pos)
    {
        return pecas[pos.linha][pos.coluna];
    }

    public boolean existePeca(Posicao pos)throws TabuleiroException   {
        validarPosicao(pos);
        return peca(pos) !=null;

    }

    public void colocarPeca(Peca p, Posicao pos) throws TabuleiroException  {

            if (existePeca(pos))
            {

                    throw new TabuleiroException("Ja existe uma peça nessa posição");

            }

    pecas[pos.linha][pos.coluna] = p;
    p.posicao = pos;

    }

    public Peca retirarPeca (Posicao pos)
    {
        if (peca(pos) == null)
        {
            return null;
        }
        else
        {
            Peca aux = peca(pos);
            aux.posicao = null;
            pecas[pos.linha][pos.coluna] = null;
            return aux;
        }

    }

    public boolean posicaoValida(Posicao pos)
    {
            if (pos.linha < 0 || pos.linha >= linhas || pos.coluna < 0 || pos.coluna >= colunas) {
                return false;

            }


    return true;


    }


    public void validarPosicao(Posicao pos)throws TabuleiroException
    {
        if (!posicaoValida(pos))
        {


                throw new TabuleiroException("Posição invalida");


        }

    }







}
