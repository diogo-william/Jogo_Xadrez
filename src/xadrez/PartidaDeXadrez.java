package xadrez;

import tabuleiro.*;

import java.util.HashSet;

public class PartidaDeXadrez {


    public Tabuleiro tab;
    public int turno;
    public Cor jogadorAtual;
    public boolean terminada;
    private HashSet<Peca> pecas;
    private HashSet<Peca> capturadas;
    public boolean xeque ;
    private Peca vulneravelPassant;

    public Peca getVulneravelPassant() {
        return vulneravelPassant;
    }

    private void setVulneravelPassant(Peca vulneravelPassant) {
        this.vulneravelPassant = vulneravelPassant;
    }

    public boolean isXeque() {
        return xeque;
    }



    public void setXeque(boolean xeque) {
        this.xeque = xeque;
    }

    public boolean isTerminada() {
        return terminada;
    }

    public void setTerminada(boolean terminada) {
        this.terminada = terminada;
    }

    public Tabuleiro getTab() {
        return tab;
    }

    public void setTab(Tabuleiro tab) {
        this.tab = tab;
    }


    public int getTurno() {
        return turno;
    }

    private void setTurno(int turno) {
        this.turno = turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadorAtual(Cor jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    public PartidaDeXadrez() throws TabuleiroException
    {
        tab = new Tabuleiro(8,8);
        turno = 1;
        jogadorAtual = Cor.Branca;
        terminada = false;
        xeque = false;
        vulneravelPassant = null;
        pecas = new HashSet<Peca>();
        capturadas = new HashSet<Peca>();
        colocarPecas();

    }

    public HashSet<Peca> pecasCapturadas (Cor cor )
    {

        HashSet<Peca> aux = new HashSet<Peca>();
        for (Peca x : capturadas )
        {
            if (x.getCor() == cor )
            {
                aux.add(x);
            }
        }
    return aux;

    }


    public HashSet<Peca> pecasEmJogo(Cor cor)
    {
        HashSet<Peca> aux = new HashSet<Peca>();
        for (Peca p : pecas)
        {
            if (p.getCor() == cor )
            {
                aux.add(p);
            }

        }
        aux.removeAll(pecasCapturadas(cor));
    return aux;
    }



    public Peca executarMovimento(Posicao origem, Posicao destino) throws TabuleiroException
    {
        Peca p = tab.retirarPeca(origem);
        p.incrementarQtdMovimento();
        Peca pecaCapturada = tab.retirarPeca(destino);
        tab.colocarPeca(p, destino);
        if (pecaCapturada != null)
        {
         capturadas.add(pecaCapturada);
        }

        //  #Jogada especial

        // roque pequeno
        if (p instanceof Rei && destino.coluna == + 2)
        {
            Posicao origemT = new Posicao(origem.linha, origem.coluna +3);
            Posicao destinoT = new Posicao(origem.linha, origem.coluna +1);
            Peca t = tab.retirarPeca(origemT);
            t.incrementarQtdMovimento();
            tab.colocarPeca(t,destinoT);

        }

        // roque grande
        else if (p instanceof Rei && destino.coluna == -2)
        {

            Posicao origemT = new Posicao(origem.linha, origem.coluna -4);
            Posicao destinoT = new Posicao(origem.linha, origem.coluna -1);
            Peca t = tab.retirarPeca(origemT);
            t.incrementarQtdMovimento();
            tab.colocarPeca(t,destinoT);

        }

        // En Passant
        if (p instanceof Peao)
        {
            if (origem.coluna != destino.coluna && pecaCapturada == null  )
            {
                Posicao posP;
                if (p.getCor() == Cor.Branca)
                {
                    posP = new Posicao(destino.linha+ 1 , destino.coluna);

                }
                else
                {
                    posP = new Posicao(destino.linha- 1 , destino.coluna);
                }
               pecaCapturada = tab.peca(posP);
               capturadas.add(pecaCapturada);



            }


        }





        return pecaCapturada;
    }

    public void realizaJogada(Posicao origem, Posicao destino) throws TabuleiroException
    {

       Peca pecaCapturada = executarMovimento(origem, destino);

        if (estaEmXeque(jogadorAtual))
        {
            desfazMovimento(origem,destino,pecaCapturada);
            throw new TabuleiroException("Voce não pode se colocar em Xeque!");

        }

        Peca x = tab.peca(destino);

        if (x instanceof Peao) {
            if(( x.getCor() == Cor.Branca && destino.linha == 0) || ( x.getCor() == Cor.Preta && destino.linha == 7))
            {
                x = tab.retirarPeca(destino);
                pecas.remove(x);
                Peca dama = new Dama(tab ,x.getCor());
                tab.colocarPeca(dama, destino);
                pecas.add(dama);


            }
        }

        if (estaEmXeque(adversaria(jogadorAtual)))
            xeque = true;
        else
        {
            xeque = false;
        }

        if(testeXequeMate(adversaria(jogadorAtual)))
        {
            terminada = true;
        }
        else
        {
            turno++;
            mudaJogador();
        }



        // jogada especial En Passant

        if ( x instanceof  Peao && ( destino.linha == origem.linha -2 || destino.linha == origem.linha + 2))
        {
            vulneravelPassant = x;

        }
        else
        {
            vulneravelPassant = null;
        }

    }




    public void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada)throws TabuleiroException
    {

        Peca p = tab.retirarPeca(destino);
        p.decrementarQtdMovimento();
        if (pecaCapturada != null)
        {
            tab.colocarPeca(pecaCapturada, destino);
            capturadas.remove(pecaCapturada);


        }
        tab.colocarPeca(p, origem);


        // roque pequeno
        if (p instanceof Rei && destino.coluna == + 2)
        {
            Posicao origemT = new Posicao(origem.linha, origem.coluna +3);
            Posicao destinoT = new Posicao(origem.linha, origem.coluna +1);
            Peca t = tab.retirarPeca(destinoT);
            t.decrementarQtdMovimento();
            tab.colocarPeca(t,origemT);

        }

        // roque grande
        else if (p instanceof Rei && destino.coluna == -2)
        {

            Posicao origemT = new Posicao(origem.linha, origem.coluna -4);
            Posicao destinoT = new Posicao(origem.linha, origem.coluna -1);
            Peca t = tab.retirarPeca(destinoT);
            t.incrementarQtdMovimento();
            tab.colocarPeca(t,origemT);

        }

        // En Passant
        if ( p instanceof  Peao)
        {
            if ( origem.coluna != destino.coluna && pecaCapturada == vulneravelPassant)
            {
               Peca peao  = tab.retirarPeca(destino);
               Posicao posP;
               if (p.getCor() == Cor.Branca )
               {
                   posP = new Posicao(3, destino.coluna);

               }
               else
               {
                   posP = new Posicao(4, destino.coluna);

               }
               tab.colocarPeca(peao, posP);

            }


        }






    }

    public boolean estaEmXeque(Cor cor)throws TabuleiroException
    {
        Peca R = rei(cor);

        if (R == null)
        {
            throw new TabuleiroException("Não Possui Rei dessa cor "+cor+" no tabuleiro !!");
        }

        for(Peca x : pecasEmJogo(adversaria(cor)))
        {

            boolean[][] mat = x.movimentosPossiveis();


            if(mat[R.posicao.linha][R.posicao.coluna])
            {

             return true;

            }

        }

        return false;
    }



    public boolean testeXequeMate(Cor cor)throws TabuleiroException
    {
    if (!estaEmXeque(cor))
    {
        return false;
    }

    for ( Peca p : pecasEmJogo(cor))
    {
        boolean[][] mat = p.movimentosPossiveis();
        for (int i=0; i < tab.linhas; i++)
        {
            for (int j =0; j < tab.colunas; j++)
            {

                if (mat[i][j])
                {
                    Posicao origem = p.posicao;
                    Posicao destino = new Posicao(i,j);
                    Peca pecaCapturada = executarMovimento(origem,destino);
                    boolean testeXeque = estaEmXeque(cor);
                    desfazMovimento(origem, destino, pecaCapturada);
                    if (!testeXeque)
                    {
                        return false;
                    }

                }


            }


        }

    }


    return true;
    }


    public void colocarNovaPeca(char coluna, int linha, Peca peca) throws TabuleiroException
    {
        tab.colocarPeca(peca, new PosicaoXadrez(coluna,linha).toPosicao());
        pecas.add(peca);

    }


    private void colocarPecas() throws TabuleiroException
    {


        // Pecas Pretas

       colocarNovaPeca('a',8,new Torre(tab, Cor.Preta));
       colocarNovaPeca('b',8,new Cavalo(tab, Cor.Preta));
       colocarNovaPeca('c',8,new Bispo(tab, Cor.Preta));
       colocarNovaPeca('d',8,new Dama(tab, Cor.Preta));
       colocarNovaPeca('e',8,new Rei(tab, Cor.Preta, this));
       colocarNovaPeca('f',8,new Bispo(tab, Cor.Preta));
       colocarNovaPeca('g',8,new Cavalo(tab, Cor.Preta));
       colocarNovaPeca('h',8,new Torre(tab, Cor.Preta));

        // Peao Preto

        colocarNovaPeca('a',7,new Peao(tab, Cor.Preta, this));
        colocarNovaPeca('b',7,new Peao(tab, Cor.Preta, this));
        colocarNovaPeca('c',7,new Peao(tab, Cor.Preta, this));
        colocarNovaPeca('d',7,new Peao(tab, Cor.Preta, this));
        colocarNovaPeca('e',7,new Peao(tab, Cor.Preta, this));
        colocarNovaPeca('f',7,new Peao(tab, Cor.Preta, this));
        colocarNovaPeca('g',7,new Peao(tab, Cor.Preta, this));
        colocarNovaPeca('h',7,new Peao(tab, Cor.Preta, this));


        // Pecas Brancas

        colocarNovaPeca('a',1,new Torre(tab, Cor.Branca));
        colocarNovaPeca('b',1,new Cavalo(tab, Cor.Branca));
        colocarNovaPeca('c',1,new Bispo(tab, Cor.Branca));
        colocarNovaPeca('d',1,new Dama(tab, Cor.Branca));
        colocarNovaPeca('e',1,new Rei(tab, Cor.Branca, this));
        colocarNovaPeca('f',1,new Bispo(tab, Cor.Branca));
        colocarNovaPeca('g',1,new Cavalo(tab, Cor.Branca));
        colocarNovaPeca('h',1,new Torre(tab, Cor.Branca));

        // Peao Branco

        colocarNovaPeca('a',2,new Peao(tab, Cor.Branca, this));
        colocarNovaPeca('b',2,new Peao(tab, Cor.Branca, this));
        colocarNovaPeca('c',2,new Peao(tab, Cor.Branca, this));
        colocarNovaPeca('d',2,new Peao(tab, Cor.Branca, this));
        colocarNovaPeca('e',2,new Peao(tab, Cor.Branca, this));
        colocarNovaPeca('f',2,new Peao(tab, Cor.Branca, this));
        colocarNovaPeca('g',2,new Peao(tab, Cor.Branca, this));
        colocarNovaPeca('h',2,new Peao(tab, Cor.Branca, this));

        // forma antiga
        /*
        tab.colocarPeca(new Torre(tab, Cor.Preta), new PosicaoXadrez('c',1).toPosicao());
        tab.colocarPeca(new Torre(tab, Cor.Branca), new PosicaoXadrez('a',3).toPosicao());
        tab.colocarPeca(new Rei(tab, Cor.Preta), new PosicaoXadrez('a',8).toPosicao());

         */


    }



    private void mudaJogador()
    {
        if (jogadorAtual == Cor.Branca)
        {
            jogadorAtual = Cor.Preta;

        }
        else
        {
            jogadorAtual = Cor.Branca;
        }

    }

    public void validarPosicaoOrigem(Posicao pos) throws TabuleiroException
    {
        if(tab.peca(pos)  == null)
        {
            throw new TabuleiroException("Não existe peça na posição de origem escolhida!");

        }
        if ( jogadorAtual != tab.peca(pos).getCor())
        {
            throw new TabuleiroException("Peça de origem escolhida não é sua");
        }
        if (!tab.peca(pos).existeMovimentosPossiveis())
        {
            throw new TabuleiroException("Não ha movimentos disponiveis para a peça de origem ");
        }


    }

    public void validarPosicaoDestino(Posicao origem, Posicao destino)throws TabuleiroException
    {
        if (!tab.peca(origem).movimentoPossivel(destino))
        {
            throw new TabuleiroException("Posição invalida!");

        }

    }






    private Cor adversaria(Cor cor)
    {
        if (cor == Cor.Branca)
        {
            return Cor.Preta;
        }
        else
        {
            return Cor.Branca;
        }

    }


    public Peca rei(Cor cor)
    {
        for(Peca p : pecasEmJogo(cor))
        {
            if(p instanceof Rei)
            {
                return  p;
            }
        }
        return null;
    }





}
