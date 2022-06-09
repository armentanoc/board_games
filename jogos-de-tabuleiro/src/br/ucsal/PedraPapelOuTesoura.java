package br.ucsal;

import java.util.Random;
import java.util.Scanner;

public class PedraPapelOuTesoura {

	public static void main(String[] args, String nomeJogador) {

		Scanner sc = new Scanner(System.in);
		int quer, jogadaEu = 0, jogadaPc = 0;

		do {
			jogadaEu = jogadaPlayer(jogadaEu);
			jogadaPc = jogadadoPc(jogadaPc);
			resultado(jogadaEu, jogadaPc);
			
			System.out.println("Quer jogar de novo? \n[1 - sim | 2 - não]");
			quer = sc.nextInt();
			
		} while(quer == 1);
		System.out.println("Até a próxima!");
		
		Play.aindaQuerJogar(nomeJogador);
	}

	public static void resultado(int jogadaEu, int jogadaPc) {
		if(jogadaEu == 1 && jogadaPc == 0) {
			System.out.println("Nós dois jogamos pedra. Empate!");
		}
		else if(jogadaEu == 1 && jogadaPc == 1) {
			System.out.println("Você jogou pedra e eu papel. Ganhei :p");
		}
		else if(jogadaEu == 1 && jogadaPc == 2) {
			System.out.println("Você jogou pedra e eu tesoura. Perdi :(");
		}
		else if(jogadaEu == 2 && jogadaPc == 0) {
			System.out.println("Você jogou papel e eu pedra. Perdi :(");
		}
		else if(jogadaEu == 2 && jogadaPc == 1) {
			System.out.println("Nós dois jogamos papel. Empate!");
		}
		else if(jogadaEu == 2 && jogadaPc == 2) {
			System.out.println("Você jogou papel e eu tesoura. Ganhei :p");
		}
		else if(jogadaEu == 3 && jogadaPc == 0) {
			System.out.println("Você jogou tesoura e eu pedra. Ganhei :p");
		}
		else if(jogadaEu == 3 && jogadaPc == 1) {
			System.out.println("Você jogou tesoura e eu papel. Perdi :( ");
		}
		else if(jogadaEu == 3 && jogadaPc == 2) {
			System.out.println("Nós dois jogamos tesoura. Empate!");
		}
	}

	public static int jogadaPlayer(int jogadaEu) {
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Qual sua jogada?\n"
					+ "[1 - pedra | 2 - papel | 3 - tesoura]");
			jogadaEu = sc.nextInt();
		}while(jogadaEu != 1 && jogadaEu != 2 && jogadaEu!=3);
		return jogadaEu;
	}

	public static int jogadadoPc(int jogadaPc) {
		Random random = new Random();
		int nR = random.nextInt(3);
		if(nR == 0) {
			System.out.println("Eu joguei pedra!");
		}
		else if(nR == 1) {
			System.out.println("Eu joguei papel!");
		}
		else if(nR == 2) {
			System.out.println("Eu joguei tesoura!");
		}
		jogadaPc = nR;
		return jogadaPc;
	}

}