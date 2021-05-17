package trabalhoEDD1;

public class Main {
	public static void main(String[] args) {


		long tempoInicial = System.currentTimeMillis();

		testeMatrizAleatoria();
		testeMatrizLinha();
		testeMatrizDiagonal();
		testeMatrizColuna();

		System.out.println("o metodo executou em " + System.currentTimeMillis() + (-tempoInicial));


		/* Teste de tempo dos métodos */
//		int [] vetor = {10,20,30,40,50,100,200,500,1000,10000};
//
//		for(int i = 0 ; i< vetor.length;i++){
//			long t0 =0;
//			long t1 =0;
//				MatrizEsparsa matrizEsparsadiag = GeradorMatriz.gerarMatrizDiagonal(vetor[i],vetor[i]);
//				MatrizEsparsaEncadeada matrizEsparsaEncadeadaSuperior =gerarMatrizEsparsaEncadeada(matrizEsparsadiag);
//				//matrizEsparsaSuperior.verificaTringularSuperior();
//				//matrizEsparsaEncadeadaSuperior.verificaTringularSuperior();
//			for(int j = 0 ; j<100;j++){
//
//				t0+=System.nanoTime();
//				matrizEsparsaEncadeadaSuperior.verificaMatrizDiagonal();
//				t1+=System.nanoTime();
//			}
//			long total =(t1-t0)/100;
//			System.out.println("Vez:" + (i+1) + " " + total);
//		}


	}

	private static void testeMatrizAleatoria() {
		MatrizEsparsa matriz = GeradorMatriz.gerarMatrizEsparsa(4, 4);
		imprimirCarecteristicasMatriz("Aleatoria", matriz);
		MatrizEsparsa transposta = matriz.transporMatriz();
		imprimirCarecteristicasMatriz("Transposta da linha", transposta);
		MatrizEsparsa somaDasDuas = matriz.somaMatriz(transposta);
		imprimirCarecteristicasMatriz("Soma da matriz com sua transposta", somaDasDuas);
		MatrizEsparsa multiplicaAsDuas = matriz.multiplicaMatriz(transposta);
		imprimirCarecteristicasMatriz("Multiplicação da matriz com sua transposta", multiplicaAsDuas);
		MatrizEsparsaEncadeada matrizEsparsaEncadeada = gerarMatrizEsparsaEncadeada(matriz);
		imprimirCarecteristicasMatrizEncadeada(matrizEsparsaEncadeada);
	}

	private static void testeMatrizLinha() {
		MatrizEsparsa matriz = GeradorMatriz.gerarMatrizLinha(4, 4);
		imprimirCarecteristicasMatriz("Linha", matriz);
		MatrizEsparsa transposta = matriz.transporMatriz();
		imprimirCarecteristicasMatriz("Transposta da linha", transposta);
		MatrizEsparsa somaDasDuas = matriz.somaMatriz(transposta);
		imprimirCarecteristicasMatriz("Soma da linha com sua transposta", somaDasDuas);
		MatrizEsparsa multiplicaAsDuas = matriz.multiplicaMatriz(transposta);
		imprimirCarecteristicasMatriz("Multiplicação da linha com sua transposta", multiplicaAsDuas);
		MatrizEsparsaEncadeada matrizEsparsaEncadeada = gerarMatrizEsparsaEncadeada(matriz);
		imprimirCarecteristicasMatrizEncadeada(matrizEsparsaEncadeada);
	}

	private static void testeMatrizDiagonal() {
		MatrizEsparsa matriz = GeradorMatriz.gerarMatrizDiagonal(4, 4);
		imprimirCarecteristicasMatriz("Diagonal", matriz);
		MatrizEsparsaEncadeada matrizEsparsaEncadeada = gerarMatrizEsparsaEncadeada(matriz);
		imprimirCarecteristicasMatrizEncadeada(matrizEsparsaEncadeada);
	}

	private static void testeMatrizColuna() {
		MatrizEsparsa matriz = GeradorMatriz.gerarMatrizColuna(4, 4);
		imprimirCarecteristicasMatriz("Coluna", matriz);
		MatrizEsparsa transposta = matriz.transporMatriz();
		imprimirCarecteristicasMatriz("Transposta da coluna", transposta);
		MatrizEsparsa somaDasDuas = matriz.somaMatriz(transposta);
		imprimirCarecteristicasMatriz("Soma da coluna com sua transposta", somaDasDuas);
		MatrizEsparsa multiplicaAsDuas = matriz.multiplicaMatriz(transposta);
		imprimirCarecteristicasMatriz("Multiplicação da coluna com sua transposta", multiplicaAsDuas);
		MatrizEsparsaEncadeada matrizEsparsaEncadeada = gerarMatrizEsparsaEncadeada(matriz);
		imprimirCarecteristicasMatrizEncadeada(matrizEsparsaEncadeada);
	}

	private static void imprimirCarecteristicasMatriz(String nome, MatrizEsparsa matriz) {
		System.out.println("---------------------------");
		System.out.println("Matriz: " + nome);
		matriz.imprime();

		MatrizEsparsa matrizTransposta = matriz.transporMatriz();
		System.out.println("Transposta: ");
		matrizTransposta.imprime();

		boolean vazia = matriz.vazia;
		System.out.println("Verifica se e matriz vazia: " + vazia);
		boolean ehColuna = matriz.verificaMatrizColuna();
		System.out.println("Verifica se e matriz coluna: " + ehColuna);
		boolean ehLinha = matriz.verificaMatrizLinha();
		System.out.println("Verifica se e matriz linha: " + ehLinha);
		boolean ehSimetrica = matriz.verificaSimetria();
		System.out.println("Verifica se e matriz simetrica: " + ehSimetrica);
		boolean ehDiagonal = matriz.verificaDiagonal();
		System.out.println("Verifica se e matriz diagonal: " + ehDiagonal);
		boolean ehSuperior = matriz.verificaTringularSuperior();
		System.out.println("Verifica se e matriz diagonal superior: " + ehSuperior);
		boolean ehInferior = matriz.verificaTringularInferior();
		System.out.println("Verifica se e matriz diagonal inferior: " + ehInferior);
		System.out.println("---------------------------");
	}

	private static void imprimirCarecteristicasMatrizEncadeada(MatrizEsparsaEncadeada matriz) {
		System.out.println("---------------------------");
		System.out.println("Matriz Esparsa encadeada");
		matriz.imprime();

		MatrizEsparsaEncadeada matrizTransposta = matriz.transporMatriz();
		System.out.println("Transposta: ");
		matrizTransposta.imprime();

		MatrizEsparsaEncadeada matrizSomada = matriz.somaMatrizes(matrizTransposta);
		System.out.println("Soma com a transposta: ");
		matrizSomada.imprime();
		MatrizEsparsaEncadeada matrizMultiplicada = matriz.multiplicarMatrizes(matrizTransposta);
		System.out.println("Multiplicacao com a transposta: ");
		matrizMultiplicada.imprime();
		boolean vazia = matriz.vazia();
		System.out.println("Verifica se e matriz vazia: " + vazia);
		boolean ehColuna = matriz.verificaMatrizColuna();
		System.out.println("Verifica se e matriz coluna: " + ehColuna);
		boolean ehLinha = matriz.verificaMatrizLinha();
		System.out.println("Verifica se e matriz linha: " + ehLinha);
		boolean ehSimetrica = matriz.verificaMatrizSimetrica();
		System.out.println("Verifica se e matriz simetrica: " + ehSimetrica);
		boolean ehDiagonal = matriz.verificaMatrizDiagonal();
		System.out.println("Verifica se e matriz diagonal: " + ehDiagonal);
		boolean ehSuperior = matriz.verificaTringularSuperior();
		System.out.println("Verifica se e matriz diagonal superior: " + ehSuperior);
		boolean ehInferior = matriz.verificaTringularInferior();
		System.out.println("Verifica se e matriz diagonal inferior: " + ehInferior);
		System.out.println("---------------------------");
	}

	private static MatrizEsparsaEncadeada gerarMatrizEsparsaEncadeada(MatrizEsparsa matrizEsparsa) {
		MatrizEsparsaEncadeada.Elo[] arrayElos = new MatrizEsparsaEncadeada.Elo[matrizEsparsa.linhas];

		// Itera sobre as linhas a matriz esparsa passada
		for (int linhaCorrente = 0; linhaCorrente < matrizEsparsa.linhas; linhaCorrente++) {

			// Cria o primeiro elo, mesmo que o valor seja 0, pois precisamos de uma base

			MatrizEsparsaEncadeada.Elo eloLinha = null;

			// Comeca a partir da segunda linha, porque a primeira foi lida no codigo acima

			for (int colunaCorrente = 0; colunaCorrente < matrizEsparsa.colunas; colunaCorrente++) {

				int celula = matrizEsparsa.matriz[linhaCorrente][colunaCorrente];
				// Se valor 0 entao nao cria elo
				if (celula == 0) continue;
				// Se primeiro elo da linha entao cria o elo e adiciona a lista
				if (eloLinha == null) {
					eloLinha = new MatrizEsparsaEncadeada.Elo(celula, null, linhaCorrente, colunaCorrente);
					arrayElos[linhaCorrente] = eloLinha;
					// Adiciona mais um elo a linha
				} else {
					MatrizEsparsaEncadeada.Elo novoElo = new MatrizEsparsaEncadeada.Elo(celula, null, linhaCorrente, colunaCorrente);
					eloLinha.prox = novoElo;
					eloLinha = novoElo;
				}
			}
			// Caso nao encontre nenhum valor >0 para aquela coluna, entao a primeiraLinha[coluna] vai ficar nula.
			// O restante do codigo ja lida com essa possibilidade ao sempre verificar caso o elo esteja nulo
		}
		return new MatrizEsparsaEncadeada(arrayElos, matrizEsparsa.colunas);
	}
}