package trabalhoEDD1;

public class MatrizEsparsaEncadeada {
    /* Referência para primeira linha de elos */
    public Elo[] arrayElo;
    public int colunas;
    public int linhas;

    protected static class Elo {
        public int dado;
        public Elo prox;
        public int linha;
        public int col;

        public Elo(int elem, Elo proxElem, int linha, int col) {
            this.linha = linha;
            this.col = col;
            this.dado = elem;
            this.prox = proxElem;
        }

        public String toString() {
            return " L:" + linha + " C:" + col + "V:" + dado;
        }
    }

    public MatrizEsparsaEncadeada(Elo[] primeiraLinha, int colunas) {
        this.arrayElo = primeiraLinha;
        this.colunas = colunas;
        this.linhas = primeiraLinha.length;
    }

    /* Insere elemento */
    public void insere(int linhaASerInserida, int colunaASerInserida, int valor) {
        Elo corrente = arrayElo[linhaASerInserida];

        // entrada com tamanho que excede as medidas da matriz
        if (linhaASerInserida > this.linhas || colunaASerInserida > this.colunas) {
            System.out.println("Entrada inválida.");
            return;
        }

        if (corrente == null) {
            arrayElo[linhaASerInserida] = new Elo(valor, null, linhaASerInserida, colunaASerInserida);
            return;
        }

        if (corrente.col > colunaASerInserida) {
            Elo novoElo = new Elo(valor, corrente, linhaASerInserida, colunaASerInserida);
            arrayElo[colunaASerInserida] = novoElo;
            novoElo.prox = corrente;
            return;
        }

        while (corrente != null) {
            // Se for menor, continua busca
            if (corrente.prox != null && corrente.prox.col < colunaASerInserida) {
                corrente = corrente.prox;
                continue;
                // Se for igual a gente substitui
            } else if (corrente.prox != null && corrente.prox.col == colunaASerInserida) {
                corrente.prox.dado = valor;
                return;
                // Se for a prox for maior, entao a insercao tem que acontecer nesse ponto
            } else {
                corrente.prox = new Elo(valor, corrente.prox, linhaASerInserida, colunaASerInserida);
                return;
            }
        }
    }

    /* Remove da lista o primeiro elemento com valor igual a “elem". Ret. true se removeu. */
    public boolean remove(int linhaASerRemovida, int colunaASerRemovida) {
        Elo corrente = arrayElo[linhaASerRemovida];
        Elo anterior = null;
        while (corrente != null) {
            // Se for menor, continua busca
            if (corrente.col < colunaASerRemovida) {
                anterior = corrente;
                corrente = corrente.prox;
                continue;
                // Se for igual a gente substitui
            } else if (corrente.col == colunaASerRemovida) {
                anterior.prox = corrente.prox;
                return true;
                // Se for a prox for maior, entao a insercao tem que acontecer nesse ponto
            } else {
                return false;
            }
        }
        return false;
    }

    public Elo buscaCelula(int linha, int coluna) { //método auxiliar criado para o método de matriz simétrica
        Elo corrente = arrayElo[linha];
        while (corrente != null) {
            // Se for menor, continua busca
            if (corrente.col < coluna) {
                corrente = corrente.prox;
                continue;
                // Se for igual a gente substitui
            } else if (corrente.col == coluna) {
                return corrente;
                // Se for a prox for maior, entao a insercao tem que acontecer nesse ponto
            } else {
                return null;
            }
        }
        return null;
    }

    /* Verifica se um determinado elemento estah na lista. */
    public Elo busca(int dadoASerBuscado) {
        for (int linhaCorrente = 0; linhaCorrente < linhas; linhaCorrente++) {
            Elo eloCorrente = arrayElo[linhaCorrente];
            while (eloCorrente != null) {
                if (eloCorrente.dado == dadoASerBuscado) {
                    return eloCorrente;
                } else {
                    eloCorrente = eloCorrente.prox;
                }
            }
        }
        return null;
    }

    /* Imprime a matriz gerada */
    public void imprime() {
        for (int i = 0; i < linhas; i++) {
            Elo corrente = arrayElo[i];
            if (corrente == null) {
                System.out.print(" Vazio");
            } else {
                while (corrente != null) {
                    System.out.print(corrente + " > ");
                    corrente = corrente.prox;

                }
            }
            System.out.println();
        }
    }

    /* Representa matrizEncadeada vazia */
    public Elo[] representaMatrizVazia() {

        return new Elo[0];
    }

    /* Verifica se a matriz estah vazia. */
    public boolean vazia() {
        for (Elo elo : arrayElo) {
            if (elo != null) return false;
        }
        return true;
    }

    /* Verifica se eh uma matriz diagonal (soh tem elementos na diagonal principal)*/
    public boolean verificaMatrizDiagonal() {
        if (vazia()) return false;

        for (int linhaCorrente = 0; linhaCorrente < linhas; linhaCorrente++) {
            Elo temp = arrayElo[linhaCorrente];

            if (temp == null) continue;
            while (temp != null) {
                // So precisamos verificar se linha != col, pois valores 0 nao sao incluidos
                if (temp.linha != temp.col) {
                    return false;
                }
                temp = temp.prox;
            }
        }
        return true;
    }

    /* Verifica se eh uma matriz linha (soh tem elementos em uma unica linha) */
    public boolean verificaMatrizLinha() {
        if (this.vazia()) return false;
        if (this.linhas == 1) {
            return true;
        }

        int linhaPreenchida = -1;
        for (int i = 0; i < this.linhas; i++) {
            Elo eloCorrente = arrayElo[i];
            if (eloCorrente != null) {
                if (linhaPreenchida != -1) return false;

                linhaPreenchida = i;
            }
        }
        return true;
    }

    /* Verifica se eh uma matriz coluna (soh tem elementos em uma unica coluna) */
    public boolean verificaMatrizColuna() {
        if (this.vazia()) return false;
        if (this.colunas == 1) {
            return true;
        }

        int colunaPreenchida = -1;
        for (int i = 0; i < this.linhas; i++) {
            Elo eloCorrente = arrayElo[i];
            while (eloCorrente != null) {
                if (colunaPreenchida != -1 || colunaPreenchida != i) return false;
                eloCorrente.col = i;
                eloCorrente = eloCorrente.prox;
            }
        }
        return true;
    }

    /* Verifica se eh uma matriz triangular inferior (soh tem elementos abaixo da diagonal principal)*/
    public boolean verificaTringularInferior() {
        for (int linha = 0; linha < this.linhas; linha++) {
            Elo temp = arrayElo[linha];

            if (temp == null) continue;
            while (temp != null) {
                // So precisamos verificar se linha < col, pois valores 0 nao sao incluidos
                if (temp.linha < temp.col) {
                    return false;
                }
                temp = temp.prox;
            }
        }

        return true;
    }

    /* Verifica se eh uma matriz triangular inferior (só tem elementos acima da diagonal principal)*/
    public boolean verificaTringularSuperior() {
        for (int linha = 0; linha < this.linhas; linha++) {
            Elo temp = arrayElo[linha];

            if (temp == null) continue;
            while (temp != null) {
                // So precisamos verificar se linha > col, pois valores 0 nao sao incluidos
                if (temp.col < temp.linha) {
                    return false;
                }
                temp = temp.prox;
            }
        }
        return true;
    }

    /* Verifica se a matriz eh simetrica (A[i,j] = A[j,i]) */
    public boolean verificaMatrizSimetrica() {
        Elo corrente;
        for (int i = 0; i < this.linhas; i++) {
            for (corrente = arrayElo[i]; corrente != null; corrente = corrente.prox) {
                Elo celula = buscaCelula(corrente.col, corrente.linha);
                if (celula == null || celula.dado != corrente.dado) return false;
            }
        }
        return true;
    }

    /* Soma duas matrizes */
    public MatrizEsparsaEncadeada somaMatrizes(MatrizEsparsaEncadeada matrizB) {
        if (this.linhas != matrizB.linhas || this.colunas != matrizB.colunas)
            throw new IllegalArgumentException("Matrizes com dimensões diferentes!");

        Elo[] matrizSomada = new Elo[this.linhas];
        for (int i = 0; i < this.linhas; i++) {
            Elo primeiroEloMatrizSomada = null;
            Elo eloCorrente = null;
            Elo eloCorrenteMatrizA = this.arrayElo[i];
            Elo eloCorrenteMatrizB = matrizB.arrayElo[i];
            while (eloCorrenteMatrizA != null || eloCorrenteMatrizB != null) {
                Elo eloASerAdicionado = null;
                // Caso uma das duas nao esteja nula e a A esta nula, entao a B nao esta nula
                if (eloCorrenteMatrizA == null) {
                    eloASerAdicionado = eloCorrenteMatrizB;
                    eloCorrenteMatrizB = eloCorrenteMatrizB.prox;
                    // Vice versa
                } else if (eloCorrenteMatrizB == null) {
                    eloASerAdicionado = eloCorrenteMatrizA;
                    eloCorrenteMatrizA = eloCorrenteMatrizA.prox;
                    // A partir daqui comparamos as colunas para ver qual vem primeiro
                } else if (eloCorrenteMatrizA.col < eloCorrenteMatrizB.col) {
                    eloASerAdicionado = new Elo(eloCorrenteMatrizA.dado, null, eloCorrenteMatrizA.linha, eloCorrenteMatrizA.col);
                    eloCorrenteMatrizA = eloCorrenteMatrizA.prox;
                } else if (eloCorrenteMatrizB.col < eloCorrenteMatrizA.col) {
                    eloASerAdicionado = new Elo(eloCorrenteMatrizB.dado, null, eloCorrenteMatrizB.linha, eloCorrenteMatrizB.col);
                    eloCorrenteMatrizB = eloCorrenteMatrizB.prox;
                } else {
                    eloASerAdicionado = new Elo(eloCorrenteMatrizA.dado + eloCorrenteMatrizB.dado, null, eloCorrenteMatrizB.linha, eloCorrenteMatrizB.col);
                    eloCorrenteMatrizA = eloCorrenteMatrizA.prox;
                    eloCorrenteMatrizB = eloCorrenteMatrizB.prox;
                }

                if (primeiroEloMatrizSomada == null) {
                    primeiroEloMatrizSomada = eloASerAdicionado;
                    eloCorrente = eloASerAdicionado;
                } else {
                    eloCorrente.prox = eloASerAdicionado;
                    eloCorrente = eloASerAdicionado;
                }
            }
            matrizSomada[i] = primeiroEloMatrizSomada;
        }

        return new MatrizEsparsaEncadeada(matrizSomada, this.colunas);
    }


    /* Multiplica duas matrizes */
    public MatrizEsparsaEncadeada multiplicarMatrizes(MatrizEsparsaEncadeada matrizB) {
        if (this.linhas != matrizB.linhas || this.colunas != matrizB.colunas)
            throw new IllegalArgumentException("Matrizes com dimensões diferentes!");

        Elo[] matrizMultiplicada = new Elo[this.linhas];
        for (int i = 0; i < this.linhas; i++) {
            Elo primeiroEloMatrizMultiplicada = null;
            Elo eloCorrente = null;
            Elo eloCorrenteMatrizA = this.arrayElo[i];
            Elo eloCorrenteMatrizB = matrizB.arrayElo[i];
            while (eloCorrenteMatrizA != null || eloCorrenteMatrizB != null) {
                Elo eloASerAdicionado = null;
                // Caso uma das duas nao esteja nula e a A esta nula, entao a B nao esta nula
                if (eloCorrenteMatrizA == null) {
                    eloASerAdicionado = eloCorrenteMatrizB;
                    eloCorrenteMatrizB = eloCorrenteMatrizB.prox;
                    // Vice versa
                } else if (eloCorrenteMatrizB == null) {
                    eloASerAdicionado = eloCorrenteMatrizA;
                    eloCorrenteMatrizA = eloCorrenteMatrizA.prox;
                    // A partir daqui comparamos as colunas para ver qual vem primeiro
                } else if (eloCorrenteMatrizA.col < eloCorrenteMatrizB.col) {
                    eloASerAdicionado = new Elo(eloCorrenteMatrizA.dado, null, eloCorrenteMatrizA.linha, eloCorrenteMatrizA.col);
                    eloCorrenteMatrizA = eloCorrenteMatrizA.prox;
                } else if (eloCorrenteMatrizB.col < eloCorrenteMatrizA.col) {
                    eloASerAdicionado = new Elo(eloCorrenteMatrizB.dado, null, eloCorrenteMatrizB.linha, eloCorrenteMatrizB.col);
                    eloCorrenteMatrizB = eloCorrenteMatrizB.prox;
                } else {
                    eloASerAdicionado = new Elo(eloCorrenteMatrizA.dado * eloCorrenteMatrizB.dado, null, eloCorrenteMatrizB.linha, eloCorrenteMatrizB.col);
                    eloCorrenteMatrizA = eloCorrenteMatrizA.prox;
                    eloCorrenteMatrizB = eloCorrenteMatrizB.prox;
                }

                if (primeiroEloMatrizMultiplicada == null) {
                    primeiroEloMatrizMultiplicada = eloASerAdicionado;
                    eloCorrente = eloASerAdicionado;
                } else {
                    eloCorrente.prox = eloASerAdicionado;
                    eloCorrente = eloASerAdicionado;
                }
            }
            matrizMultiplicada[i] = primeiroEloMatrizMultiplicada;
        }

        return new MatrizEsparsaEncadeada(matrizMultiplicada, this.colunas);
    }

    public MatrizEsparsaEncadeada transporMatriz() {
        MatrizEsparsaEncadeada resultado = new MatrizEsparsaEncadeada(new Elo[linhas], colunas);
        Elo p;
        for (int i = 0; i < this.linhas; i++) {
            for (p = arrayElo[i]; p != null; p = p.prox) {
                resultado.insere(p.col, p.linha, p.dado);
            }
        }
        return resultado;
    }
}