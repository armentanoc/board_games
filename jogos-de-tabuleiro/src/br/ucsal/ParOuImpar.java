package br.ucsal;

import java.util.Random;
import java.util.Scanner;

public class ParOuImpar {


	public static void main(String[] args, String nomeJogador) {
		Scanner sc = new Scanner(System.in);
		int op = 0, vit = 0, der = 0, jogadaPc = 0, jogadaEu = 0, quer = 0;


		do {
			op = escolhaPlayer(op);	
			jogadaEu = jogadaPlayer(jogadaEu);
			jogadaPc = jogadaPc(jogadaPc);
			resultado(op, jogadaEu, jogadaPc, vit, der);
			System.out.println("Quer jogar de novo? \n[1 - sim | 2 - não]");
			quer = sc.nextInt();
		} while(quer == 1);
		System.out.println("Até a próxima!");
		
		Play.aindaQuerJogar(nomeJogador);

	}

	public static void resultado(int op, int jogadaEu, int jogadaPc, int vit, int der) {
		int total;
		double resto;
		total = jogadaEu + jogadaPc;
		resto = total % 2;

		if(op == 1 && resto == 0) {
			System.out.println("Perdeu! Você escolheu ímpar e " 
					+ total + " é par.");
			der++;
		}
		else if(op == 1 && resto != 0) {
			System.out.println("Ganhou! Você escolheu ímpar e "
					+ total + " é ímpar.");
			vit++;
		}
		else if(op == 2 && resto == 0) {
			System.out.println("Ganhou! Você escolheu par e " 
					+ total + " é par.");
			vit++;
		}
		else if(op == 2 && resto != 0) {
			System.out.println("Perdeu! Você escolheu par e "
					+ total + " é ímpar.");
			der++;
		}

	}

	public static int jogadaPc(int jogadaPc) {
		Random random = new Random();
		int nR = random.nextInt(10);
		System.out.println("Eu joguei "+ nR + ".") ;
		jogadaPc = nR;
		return jogadaPc;
	}

	public static int escolhaPlayer(int op) {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Quer par ou ímpar? "
					+ "\n[1 - ímpar] \n[2 - par]");
			op = sc.nextInt();
		}while(op != 1 && op != 2);
		if(op == 1) {
			System.out.println("Você escolheu ímpar.");
		}
		if(op == 2) {
			System.out.println("Você escolheu par.");
		}
		return op;	
	}


	public static int jogadaPlayer(int jogadaEu) {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Qual sua jogada? [0 - 10]");
			jogadaEu = sc.nextInt();
		}while(jogadaEu >10 || jogadaEu <0);
		System.out.println("Você jogou " + jogadaEu + ".");

		return jogadaEu;

	}
}