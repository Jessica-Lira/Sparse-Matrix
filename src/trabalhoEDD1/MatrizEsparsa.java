package trabalhoEDD1;

public class MatrizEsparsa {

    public int[][] matriz;
    public int colunas;
    public int linhas;
    // Otimiza verificacao por matriz vazia;
    public boolean vazia;

    public MatrizEsparsa (int[][] matriz, int linhas, int colunas) {
        this.colunas = colunas;
        this.linhas = linhas;
        this.matriz = matriz;
        this.vazia = verificaVazia();
    }

    /* Insere elemento. */
    public void insere(int linhaASerInserida, int colunaASerInserida, int valor) {
        matriz[linhaASerInserida][colunaASerInserida] = valor;
        if (valor != 0) this.vazia = false;
    }

    /* Remove elemento */
    public void remove(int linhaASerRemovida, int colunaASerRemovida) {
        if (!this.vazia) {
            matriz[linhaASerRemovida][colunaASerRemovida] = 0;
            this.vazia = verificaVazia();
        }
    }

    /* Verifica se um determinado elemento esta na lista. */
    public boolean busca(int dadoASerBuscado) {
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                if (matriz[i][j] == dadoASerBuscado) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Imprime matriz */
    public void imprime() {
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    /* Cria matriz vazia. */
    public MatrizEsparsa() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                matriz[linha][coluna] = 0;
            }
        }
    }

    /* Verifica se a matriz está vazia. */
    public boolean verificaVazia() {
        if (matriz == null) return true;
        for (int i = 0; i < linhas ; i++) {
            for (int j = 0; j < colunas ; j++) {
                if (matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Verifica se eh uma matriz diagonal */
    public boolean verificaDiagonal() {
        if (this.vazia) return false;

        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                if (i != j && matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        // Se matriz nao esta vazia e nao foi encontrado nenhum valor fora da diagonal, entao a matriz eh diagonal
        return true;
    }

    /* Verifica se eh uma matriz linha (só tem elementos em uma única linha) */
    public boolean verificaMatrizLinha() {
        if (this.vazia) return false;
        if (this.linhas == 1) return true;

        int linhaPreenchida = -1;
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                if (matriz[i][j] != 0) {
                    // Caso ainda nao tenha sido encontrado uma outra linha preenchida ainda
                    if (linhaPreenchida == -1) {
                        linhaPreenchida = i;
                        break;
                    } else {
                        // Ja existe uma outra linha preenchida
                        return false;
                    }
                }
            }
        }
        // Se matriz nao esta vazia e nao foi retornado false anteriormente, entao so existe uma linha preenchida
        return true;
    }

    /* Verifica se eh uma matriz coluna (só tem elementos em uma única coluna) */
    public boolean verificaMatrizColuna() {
        if (this.vazia) return false;
        if (this.colunas == 1) return true;

        int colunaPreenchida = -1;
        for (int i = 0; i < this.colunas; i++) {
            for (int j = 0; j < this.linhas; j++) {
                if (matriz[j][i] != 0) {
                    // Caso ainda nao tenha sido encontrado uma outra coluna preenchida ainda
                    if (colunaPreenchida == -1) {
                        colunaPreenchida = i;
                        break;
                    } else {
                        // Ja existe uma outra coluna preenchida
                        return false;
                    }
                }
            }
        }
        // Se matriz nao esta vazia e nao foi retornado false anteriormente, entao so existe uma coluna preenchida
        return true;
    }

    //Verifica se a matriz informada eh uma matriz triangular inferior
    public boolean verificaTringularInferior() {
        for (int linha = 0; linha < this.linhas; linha++) {
            // percorre os elementos acima da diagonal principal
            for (int coluna = (linha + 1); coluna < this.colunas; coluna++) {
                // verifica se os elementos acima da diagonal principal sao iguais a zero
                if (matriz[linha][coluna] != 0)
                    return false;
            }
        }
        return true;
    }

    /* Verifica se a matriz informada eh uma matriz triangular superior */
    public boolean verificaTringularSuperior() {
        for (int i = 0; i < this.linhas; i++) {
            // percorre os elementos abaixo da diagonal principal
            for (int j = 0; j < i; j++) {
                // verifica se os elementos abaixo da diagonal principal sao iguais a zero
                if (this.matriz[i][j] != 0)
                    return false;
            }
        }
        return true;
    }

    /* Verifica se eh matriz simetrica */
    public boolean verificaSimetria() {
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Soma duas matrizes. */
    public MatrizEsparsa somaMatriz(MatrizEsparsa matrizB) {
        if (this.linhas != matrizB.linhas || this.colunas != matrizB.colunas) {
            throw new IllegalArgumentException("Matrizes com dimensões diferentes!");
        }

        int[][] somaMatriz = new int[linhas][colunas];

        for (int l = 0; l < this.matriz.length; l++) {
            for (int c = 0; c < this.matriz.length; c++) {
                somaMatriz[l][c] = this.matriz[l][c] + matrizB.matriz[l][c];
            }
        }

        return new MatrizEsparsa(somaMatriz, linhas, colunas);
    }

    /* Multiplica duas matrizes. */
    public MatrizEsparsa multiplicaMatriz(MatrizEsparsa matrizB) {
        if (this.linhas != matrizB.linhas || this.colunas != matrizB.colunas) {
            throw new IllegalArgumentException("Matrizes com dimensões diferentes!");
        }

        int[][] multiplicaMatriz = new int[linhas][colunas];

        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                multiplicaMatriz[i][j] = this.matriz[i][j] * matrizB.matriz[i][j];
            }
        }

        return new MatrizEsparsa(multiplicaMatriz, linhas, colunas);
    }

    /* Matriz Transposta */
    public MatrizEsparsa transporMatriz() {
        if (this.linhas != this.colunas) {
            throw new IllegalArgumentException("Matrizes com dimensões diferentes!");
        }

        int[][] matrizTransposta = new int[this.linhas][this.colunas];
        for (int linha = 0; linha < this.linhas; linha++) {
            for (int coluna = 0; coluna < this.colunas; coluna++) {
                if (linha > coluna) {
                    matrizTransposta[linha][coluna] = matriz[coluna][linha];
                    matrizTransposta[coluna][linha] = matriz[linha][coluna];
                } else {
                    matrizTransposta[linha][coluna] = matriz[linha][coluna];
                }
            }
        }
        return new MatrizEsparsa(matrizTransposta, this.linhas, this.colunas);
    }
}