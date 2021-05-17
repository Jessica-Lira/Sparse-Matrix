package trabalhoEDD1;

public class GeradorMatriz {

    public static MatrizEsparsa gerarMatrizLinha(int linhas, int colunas){
        int[][] matriz = new int[linhas][colunas];

        for(int i = 0; i< linhas; i++) {
            matriz[0][i] =(int) (1 + Math.floor((int) (Math.random() * 100)));
        }

        return new MatrizEsparsa(matriz, linhas, colunas);
    }

    public static MatrizEsparsa gerarMatrizColuna(int linhas, int colunas){
        int[][] matriz = new int[linhas][colunas];

        for(int i=0;i< linhas;i++) {
            matriz[i][colunas-1] =(int) (1 + Math.floor((int) (Math.random() * 100)));
        }

        return new MatrizEsparsa(matriz, linhas, colunas);
    }

    public static MatrizEsparsa gerarMatrizDiagonal(int linhas, int colunas) {
        int[][] matriz = new int[linhas][colunas];

        for(int i=0;i< linhas;i++) {
            for (int j = 0; j < colunas; j++) {
                if (i == j)
                    matriz[i][j] =(int) (1 + Math.floor((int) (Math.random() * 100)));
            }
        }
        return new MatrizEsparsa(matriz, linhas, colunas);
    }

    public static MatrizEsparsa gerarMatrizTriangularInferior(int linhas, int colunas) {
        int[][] matriz = new int[linhas][colunas];

        for(int i=0;i< linhas;i++) {
            for (int j = 0; j < colunas; j++) {
                if (i == j || i> j)
                    matriz[i][j] =(int) (1 + Math.floor((int) (Math.random() * 100)));
            }
        }
        return new MatrizEsparsa(matriz, linhas, colunas);
    }

    public static MatrizEsparsa gerarMatrizTriangularSuperior(int linhas, int colunas) {
        int[][] matriz = new int[linhas][colunas];

        for(int i=0;i< linhas;i++) {
            for (int j = 0; j < colunas; j++) {
                if (i == j || i < j)
                    matriz[i][j] =(int) (1 + Math.floor((int) (Math.random() * 100)));
            }
        }
        return new MatrizEsparsa(matriz, linhas, colunas);
    }

    public static MatrizEsparsa gerarMatrizEsparsa(int linhas, int colunas) {
        int[] vetorNumeros = gerar60PorcentoNumeros0(linhas * colunas);

        int[][] matrizEsparsa = new int[linhas][colunas];

        for (int linhaCorrente = 0; linhaCorrente < linhas; linhaCorrente++) {
            for (int colunaCorrente = 0; colunaCorrente < colunas; colunaCorrente++) {
                // ordem nesse caso e como se fosse uma pagina de uma paginacao, i e a pagina corrente, e j e a entrada na pagina
                matrizEsparsa[linhaCorrente][colunaCorrente] = vetorNumeros[(linhaCorrente*colunas)+colunaCorrente];
            }
        }
        return new MatrizEsparsa(matrizEsparsa, linhas, colunas);
    }

    private static int[] gerar60PorcentoNumeros0(int total) {
        int contadorZero = (int)Math.floor(total * 0.6);
        int contadorNaoZero = total - contadorZero;

        // Gera vetor unidemensional com 0 e nao zeros
        int[] vetorNumeros = new int[total];
        for (int i = 0; i < total; i++) {
            double random = Math.random();
            // Joga uma moeda, se coroa (>0.5) entao tenta inserir nao zero, se cara (<0.5) entao tenta inserir zero
            if (random > 0.5) {
                // Se ainda falta inserir numeros nao zeros, insere numero nao zero, caso ja tenha inserido todos nao zero, entao insere um 0
                if (contadorNaoZero > 0) {
                    vetorNumeros[i] = (int) (1 + Math.floor((int) (Math.random() * 100)));
                    contadorNaoZero--;
                    // Se inseridos todos os nao zero, entao com certeza ainda existem 0 a serem inseridos
                } else {
                    vetorNumeros[i] = 0;
                    contadorZero--;
                }
            } else {
                // Se ainda falta inserir numeros zeros, insere numero zero, caso ja tenha inserido todos zero, entao insere um nao zero
                if (contadorZero > 0 ) {
                    vetorNumeros[i] = 0;
                    contadorZero--;
                    // Se inseridos todos os zero, entao com certeza ainda existem nao zero a serem inseridos
                } else {
                    vetorNumeros[i] = (int) (1 + Math.floor((int) (Math.random() * 100)));
                    contadorNaoZero--;
                }
            }
        }
        return vetorNumeros;
    }
}