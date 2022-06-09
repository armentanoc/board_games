package br.ucsal;
import java.util.Random;
import java.util.Scanner;

public class Charada {

	public static void main(String[] args, String nomeJogador) {

		int limite, numAleatorio;

		String [][] tempPainelCharada = painelCharada();

		limite = tempPainelCharada.length;

		numAleatorio = numeroAleatorio(limite);

		jogoON(numAleatorio, tempPainelCharada, nomeJogador);

		Play.aindaQuerJogar(nomeJogador);

	}

	private static void jogoON(int numAleatorio, String[][] tempPainelCharada, String nomeJogador) {

		String resposta = "";
		int qtdChances = 3;
		Scanner sc = new Scanner(System.in);
		boolean acertou = false;

		while(acertou == false) {

			Print.p("O que é o que é?");
			Print.p(tempPainelCharada[numAleatorio][0]);
			resposta = sc.next();

			if (resposta.equals(tempPainelCharada[numAleatorio][1]) || resposta.equals(tempPainelCharada[numAleatorio][2]) || resposta.equals(tempPainelCharada[numAleatorio][3])) {

				acertou = true;
				Print.p("Acertou! A resposta correta é: " + tempPainelCharada[numAleatorio][1] + ".");


			} else {

				qtdChances--;
				
				if (qtdChances > 0) {
					Print.p("Tente novamente. Você ainda tem " + qtdChances + " chance(s).");
				}
				
				else if(qtdChances == 0) { 
					Print.p("As chances acabaram! A resposta correta é: " + tempPainelCharada[numAleatorio][1] + ".");
					acertou = true;
				}
			}
		}
	}


	public static String [][] painelCharada() {

		String [][] painelCharada = new String [5][4];

		//Pergunta x Resposta

		painelCharada[0][0] = "É feito de água, mas se for colocado dentro da água morrerá.";
		painelCharada[0][1] = "Gelo";
		painelCharada[0][2] = "gelo";
		painelCharada[0][3] = "Gelo";

		painelCharada[1][0] = "Está sempre a caminho, mas nunca chega";
		painelCharada[1][1] = "Amanhã";
		painelCharada[1][2] = "amanha";
		painelCharada[1][3] = "amanhã";

		painelCharada[2][0] = "Tem cidades, mas não tem casas. Tem montanhas, mas não tem árvores. Tem água, mas não tem peixe.";
		painelCharada[2][1] = "Mapa";	
		painelCharada[2][2] = "mapa";
		painelCharada[2][3] = "mapas";

		painelCharada[3][0] = "O que tem no final do arco-íris?";
		painelCharada[3][1] = "A letra 's'";	
		painelCharada[3][2] = "s";
		painelCharada[3][3] = "S";

		painelCharada[4][0] = "Quando você me vira de lado, sou tudo. Quando me corta ao meio, não sou nada.";
		painelCharada[4][1] = "O número '8'";	
		painelCharada[4][2] = "oito";
		painelCharada[4][3] = "8";

		return painelCharada;

	}

	public static int numeroAleatorio(int limite) {

		Random rd = new Random();
		return rd.nextInt(limite);

	}

}
