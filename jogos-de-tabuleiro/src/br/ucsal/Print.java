package br.ucsal;

public class Print {

	
	public static void tabVelha(String[][] tabuleiro, String[] sep) {

		System.out.println();
		
		for (int i = 0; i < tabuleiro.length; i++) {

			for (int j = 0; j < tabuleiro[i].length; j++) {
				System.out.print(" " + tabuleiro[i][j] + " ");
			}
			System.out.println();

			for (int iS = 0; iS < sep.length; iS++) {
				System.out.print(sep[iS]);
			}

			System.out.println();

		}

	}

	public static void tabDamas(String[][] tabuleiro) {

		System.out.println("");

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				System.out.print(tabuleiro[i][j] + "");}
			System.out.println(" ");
		}

	}

	public static void charada(String[][] array, int numAleatorio) {

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < array[1].length; j++) {

				System.out.print((array[numAleatorio][j]) + " ");

			}
		}

		System.out.println();

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {

				if (i == numAleatorio) {
					System.out.print(array[i][j] + " ");
				}
			}
		}

	}
	
	//---------------atv até aqui-------------------
	
	public static void p (double dbl) {
		System.out.printf("%.2f", dbl);
	}

	public static void p (int vlr) {
		System.out.println(vlr);
	}

	public static void p (String str) {
		System.out.println(str);
	}

	public static void p (String [] vetor) {
		System.out.print(vetor);
	}
	
	public static void tab(String[][] tabuleiro) {

		for (int i = 0; i < tabuleiro.length; i++) {

			for (int j = 0; j < tabuleiro[i].length; j++) {
				System.out.print(tabuleiro[i][j] +	" ");
			}
			
			System.out.println();
		}
	}


	public static String preencherEspacoStr(String str, int qtd){

		String res = "";
		int tam = qtd - (str + "").length();

		for (int i = 0; i < tam; i++) {
			res+=" ";
		}

		return res+str;

	}

	/*public static void arrayInt (int[] ordemColuna) {
		for (int i = 0; i < ordemColuna.length; i++) {
			System.out.print(ordemColuna[i] + " | ");
		}

		System.out.println();
	}*/

	//usar %5d para jogo da velha e damas

	public static String preencherZero (int valor, int qtd){

		String res = "";
		int tam = qtd - (valor + "").length();

		for (int i = 0; i < tam; i++) {
			res+="0";
		}

		return res+valor;

	}


}