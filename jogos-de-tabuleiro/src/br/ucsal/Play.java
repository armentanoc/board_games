package br.ucsal;

import java.util.Scanner;

public class Play {

	public static void main(String[] args) {

		Print.p("\n"
				+ "JOGOS DE TABULEIRO NO TERMINAL"
				+ "\n"	
				+ "\nOlá! Informe seu nome:");

		Scanner sc = new Scanner(System.in);
		String nomeJogador = sc.nextLine();

		obterEscolhaJogo(nomeJogador);

	}

	public static void obterEscolhaJogo(String nomeJogador) {
		
		Scanner sc = new Scanner(System.in);
		
		int escolha;
		
		do {
			
			Print.p("\n"
					+ "Qual jogo você quer jogar, " + nomeJogador + "?"
					+ "\n(1) Jogo da Velha;"
					+ "\n(2) Jogo de Damas;"
					+ "\n(3) Charada;"
					+ "\n(4) Par ou ímpar;"
					+ "\n(5) Pedra, papel ou tesoura;"
					+ "\n(6) Sair.");
			
			escolha = sc.nextInt();
			
			if (escolha != 1 && escolha != 2 && escolha != 3 && escolha != 4 && escolha != 5 && escolha != 6) {
				
				Print.p("Não temos essa opção, tente novamente.");
				
			}
			
		} while (escolha != 1 && escolha != 2 && escolha != 3 && escolha != 4 && escolha != 5 && escolha != 6);
		
		switch (escolha) {

		case 1: 
			JogoDaVelha.main(null, nomeJogador);
			break;

		case 2: 
			JogoDeDamas.main(null, nomeJogador);
			break;
			
		case 3: 
			Charada.main(null, nomeJogador);
			break;
			
		case 4: 
			ParOuImpar.main(null, nomeJogador);
			break;
		
		case 5:
			PedraPapelOuTesoura.main(null, nomeJogador);
			break;
			
		case 6:
			Print.p("Certo! Podemos jogar de outra vez.");
			break;

		default:
			Print.p("Erro inesperado.");

		}
	}
	
	public static void aindaQuerJogar(String nomeJogador) {

		int aindaQuerJogar;
		Scanner sc = new Scanner (System.in);

		do {

			Print.p("Você ainda quer jogar outro jogo, " + nomeJogador + "?"
					+ "\n(1) Sim;"
					+ "\n(2) Não;");

			aindaQuerJogar = sc.nextInt();

		} while (aindaQuerJogar != 1 && aindaQuerJogar != 2);

		if (aindaQuerJogar == 1) {
			Play.obterEscolhaJogo(nomeJogador);
		} else {
			Print.p("Certo! Nos vemos de outra vez.");
		}
	}
}