package br.ucsal;

import java.util.Scanner;

public class JogoDaVelha {

	public static void main(String[] args, String nomeJogador) {

		int lTab = 4, cTab = 4, quer;

		do { 
			
		String[][] tabuleiro = tabuleiro(nomeJogador, lTab, cTab);
		String[] separador = separador(); 

			Print.p("\nJOGO DA VELHA"
				+ "\n");

		Print.tabVelha(tabuleiro, separador);

		String escolhaPeca = pecaInicial(nomeJogador);

		jogoON(escolhaPeca, tabuleiro, separador, nomeJogador); 
		
		Print.p("Quer jogar de novo?"
				+ "\n(1) Sim;"
				+ "\n(2) Não.");
		
		Scanner sc = new Scanner(System.in);
		quer = sc.nextInt();
	
		} while(quer == 1);

		Print.p("Até a próxima!");

		Play.aindaQuerJogar(nomeJogador);

	}


	public static String[][] tabuleiro (String nomeJogador, int l, int c) {

		String [] [] tabuleiro = new String [l] [c];

		for (int il = 1, numeroCasa = 0; il < tabuleiro.length; il++) {
			for (int ic = 1; ic < tabuleiro[il].length; ic++) {

				tabuleiro [il] [ic] = " ";
			}

			while(numeroCasa <= 3) {

				tabuleiro[0][numeroCasa] = numeroCasa + "";
				tabuleiro[numeroCasa][0] = numeroCasa + "";
				numeroCasa++;
			}
		}

		tabuleiro[0][0] = "#";
		return tabuleiro;

	}

	private static String[] separador() {

		String[] sep = new String[12];

		for (int i = 0; i < sep.length; i++) {

			sep[i] = "-";

		}

		return sep;
	}

	public static String pecaInicial(String nomeJogador) {

		String escolhaPeca;

		do {

			Print.p("\n" + nomeJogador + ", quem começa, 'X' ou 'O'?");
			Scanner sc = new Scanner(System.in);
			escolhaPeca = sc.nextLine();


		} while (escolhaPeca.equals("x") == false && 
				escolhaPeca.equals("X") == false && 
				escolhaPeca.equals("o") == false && 
				escolhaPeca.equals("O") == false);

		if (escolhaPeca.equals("x")) {
			escolhaPeca = "X";
		} else if (escolhaPeca.equals("o")) {
			escolhaPeca = "O";		
		}

		return escolhaPeca;
	}

	public static String [] [] jogoON(String pecaEscolhida, String [][] tab, String[] sep, String nomeJogador) {

		int rodada = 1;
		String pecaOposta = null;
		boolean teste = false;

		if (pecaEscolhida.equals("X")) {
			pecaOposta = "O";

		} else if (pecaEscolhida.equals("O")) {
			pecaOposta = "X";

		}

		while (teste == false) {

			Print.p("\nRODADA " + rodada + ":");
			tab = jogadaPeca(tab, pecaEscolhida);
			Print.tabVelha(tab, sep);
			teste = testeVitoriaOuEmpate(tab, nomeJogador, pecaEscolhida);

			if(teste == false) {

				Print.p("\nRODADA " + rodada + ":");
				tab = jogadaPeca(tab, pecaOposta);
				Print.tabVelha(tab, sep);
				teste = testeVitoriaOuEmpate(tab, nomeJogador, pecaOposta);

				rodada++;
			}

		} 

		return tab;

	}

	public static boolean testeVitoriaOuEmpate(String[][] tab, String nomeJogador, String peca) {

		boolean teste = false;

		if(vitoria(tab, nomeJogador, peca) == true || ninguemGanha(tab, nomeJogador) == true) {
			teste = true;
		};

		return teste;

	}


	public static int jogadaLinha(String[][] tabuleiro, String peca) {

		int l;
		Scanner sc = new Scanner(System.in);

		do {
			Print.p("Jogada de " + peca
					+ "\nEm qual linha você deseja inserir " + peca + "?");
			l = sc.nextInt();

		} while (l != 1 && l != 2 && l != 3);

		return l;
	}

	public static int jogadaColuna(String[][] tabuleiro, String peca) {

		int c;
		Scanner sc = new Scanner(System.in);

		do {
			Print.p("Jogada de " + peca
					+ "\nEm qual coluna você deseja inserir " + peca + "?");
			c = sc.nextInt();

		} while (c != 1 && c != 2 && c != 3);

		return c;

	}


	public static String[][] jogadaPeca(String[][] tabuleiro, String peca) {

		int c, l;

		do {

			l = jogadaLinha(tabuleiro, peca);
			c = jogadaColuna(tabuleiro, peca);

		} while (validarJogada(tabuleiro, l, c) == false);

		tabuleiro[l][c] = peca;

		return tabuleiro;

	}


	public static boolean validarJogada(String[][] tab, int l, int c) {

		boolean validarJogada = false;

		if(tab[l][c] == " ") {
			validarJogada = true;
		}

		return validarJogada;

	}

	public static boolean ninguemGanha(String[][] tab, String nomeJogador) {

		boolean ninguemGanha = false;

		if (tab[1][1] != " " && tab[1][2] != " " && tab[1][3] != " " &&
				tab[2][1] != " " && tab[2][2] != " " && tab [2][3] != " " &&
				tab[3][1] != " " && tab[3][2] != " " && tab[3][3] != " ") {

			ninguemGanha = true;
			Print.p("\nEmpate!");

		}

		return ninguemGanha;
	}

	public static boolean vitoria(String[][] tab, String nomeJogador, String peca) {

		boolean vitoria = false;


		if(tab[1][1] == tab[1][2] && tab[1][1] == tab[1][3] && tab[1][1] == peca || 
				tab[2][1] == tab[2][2] && tab[2][1] == tab[2][3] && tab[2][1] == peca ||
				tab[3][1] == tab[3][2] && tab[3][1] == tab[3][3] && tab[3][1] == peca) {
			//Linhas

			vitoria = true;

		} 		else if (tab[1][1] == tab[2][1] && tab[1][1] == tab[3][1] && tab[1][1] == peca || 
				tab[1][2] == tab[2][2] && tab[1][2] == tab[3][2] && tab[1][2] == peca ||
				tab[1][3] == tab[2][3] && tab[1][3] == tab[3][3] && tab[1][3] == peca) {
			//Colunas

			vitoria = true;

		} 		else if (tab[1][1] == tab[2][2] && tab[1][1] == tab[3][3] && tab[1][1] == peca || 
				tab[1][3] == tab[2][2] && tab[1][3] == tab[3][1] && tab[1][3] == peca) {
			//Diagonais

			vitoria = true;

		} 	

		if(vitoria == true) { 
			Print.p("\nParabéns! Vitória de " + peca + ".");

		}

		return vitoria;
	}

}