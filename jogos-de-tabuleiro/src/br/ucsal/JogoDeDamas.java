package br.ucsal;

import java.util.Scanner;

public class JogoDeDamas {

	public static void main(String[] args, String nomeJogador) {

		int quer; 

		do {
			
			Print.p("\nJOGO DE DAMAS - In Progress");

			String [][] tabuleiroInicial = tabuleiro();

			Print.tabDamas(tabuleiroInicial);

			jogoON(tabuleiroInicial);

			Print.p("Quer jogar de novo?"
					+ "\n(1) Sim;"
					+ "\n(2) Não.");

			Scanner sc = new Scanner(System.in);
			quer = sc.nextInt();

		} while(quer == 1);

		Print.p("Até a próxima!");

		Play.aindaQuerJogar(nomeJogador);


	}

	public static String [] [] tabuleiro() {

		int l = 9, c = 9;

		String [][] tabuleiro = new String [l][c];

		for (int il = 0; il < tabuleiro.length - 1; il++) {
			for (int ic = 0; ic < tabuleiro[il].length - 1; ic++) {

				tabuleiro [il] [ic] = "|_|";

				if (il == 0) {
					if (ic % 2 == 0) {
						tabuleiro[il][ic] = "|○|";
					} 
				} else if (il == 1) {
					if (ic % 2 != 0) {
						tabuleiro[il][ic] = "|○|";
					} 
				} else if (il == 2) {
					if (ic % 2 == 0) {
						tabuleiro[il][ic] = "|○|";
					} 
				}

				if (il == 5) {
					if (ic % 2 != 0) {
						tabuleiro[il][ic] = "|●|";
					} 
				} else if (il == 6) {
					if (ic % 2 == 0) {
						tabuleiro[il][ic] = "|●|";
					} 
				} else if (il == 7 ) {
					if (ic % 2 != 0) {
						tabuleiro[il][ic] = "|●|";
					} 
				}

			}

			int numeroCasa= 0;

			while(numeroCasa < 8) {

				tabuleiro[8][numeroCasa] = "|"+ numeroCasa + "|";
				tabuleiro[numeroCasa][8] = "|"+ numeroCasa + "|";
				numeroCasa++;
			}
		}

		tabuleiro[8][8] = "|#|";

		return tabuleiro;

	}

	public static String [][] jogoON(String [][] tabuleiro) {

		String corPreto = "○", corBranco = "●";
		String pecaPreto = "|○|", pecaBranco = "|●|", damaPreto = "|◎|", damaBranco = "|◉|";
		int rodada = 1, ptsBranco = 0, ptsPreto = 0,
				auxBranco = ptsBranco, auxPreto = ptsPreto, roundSemComer = 0;
		boolean empate = false;

		do { 

			tabuleiro = jogada(tabuleiro, corBranco, pecaBranco, damaBranco, rodada, ptsBranco, ptsPreto);
			tabuleiro = promoverDama(tabuleiro);
			Print.tabDamas(tabuleiro);
			ptsBranco = pontosBranco(pecaPreto, tabuleiro, ptsBranco);

			if (ptsBranco <= auxBranco) {
				roundSemComer++;
			} else {
				roundSemComer = 0;
			}

			empate = verificarEmpate(tabuleiro, roundSemComer);
			// verifica se tem empate, ou vitoria

			if (ptsBranco == 12 || empate == true) {
				break;
			}

			tabuleiro = jogada(tabuleiro, corPreto, pecaPreto, damaPreto, rodada, ptsBranco, ptsPreto);
			tabuleiro = promoverDama(tabuleiro);
			Print.tabDamas(tabuleiro);
			ptsPreto = pontosPreto(pecaBranco, tabuleiro, ptsPreto);	

			if (ptsPreto <= auxPreto) {
				roundSemComer++;
			} else {
				roundSemComer = 0;
			}

			empate = verificarEmpate(tabuleiro, roundSemComer);
			// verifica se tem empate, ou vitoria
			
			if (ptsPreto == 12 || empate == true) {
				break;
			}

			rodada++;

		} while (ptsBranco <= 11 && ptsPreto <= 11);

		if (ptsBranco == 12) {
			System.out.println("BRANCO GANHOU");
		} else if (ptsPreto == 12) {
			System.out.println("PRETO GANHOU");
		} else if (empate == true) {
			System.out.println("EMPATE");
		}

		return tabuleiro;

	}

	public static String [][] jogada (String [][] tabuleiro, String corPeca, String tipoPeca, String tipoDama, int rodada, int ptsBranco, int ptsPreto) {

		int colunaSeguinte, linhaSeguinte, colunaOrigem, linhaOrigem;
		boolean validar = false;

		String pecaPreta = "○", pecaBranca = "●";

		Scanner sc = new Scanner(System.in);

		do {
			do {
				Print.p("\n"
						+ "Rodada " + rodada + " - Jogada de " + corPeca + ":"
						+ "\nPts. " + pecaBranca + " = " + ptsBranco + " | Pts. " + pecaPreta + " = " + ptsPreto
						+ "\n"
						+ "\nQual a linha da peça que deseja mover? (0 a 7)");

				linhaOrigem = sc.nextInt();

				Print.p("Qual a coluna da peça que deseja mover? (0 a 7)");
				colunaOrigem = sc.nextInt();

				if (tabuleiro[linhaOrigem][colunaOrigem] == tipoDama) {
					tipoPeca = tipoDama;
				}
				// do while pra quando alguem digitar um número maior q o tamanho da matriz
			
			} while (linhaOrigem > 7 || colunaOrigem > 7);

		} while (tabuleiro[linhaOrigem][colunaOrigem] != tipoPeca && tabuleiro[linhaOrigem][colunaOrigem] != tipoDama);
		// verificando se o local de origem tem peca

		do {
			do {
				Print.p("\nPara qual linha deseja mover essa peça? (0 a 7)");

				linhaSeguinte = sc.nextInt();

				Print.p("Para qual coluna deseja mover essa peça? (0 a 7)");

				colunaSeguinte = sc.nextInt();
				// do while pra quando alguem digitar um número maior q o tamanho da matriz

			} while (linhaSeguinte >= 8 || colunaSeguinte >= 8);

			validar = validarJogada(linhaOrigem, linhaSeguinte, colunaOrigem, colunaSeguinte, tabuleiro, tipoPeca,
					tipoDama, ptsBranco, ptsPreto);
			// verificando se é possível mover a peça pra o local desejado
		} while (validar == false);

		return tabuleiro;
	}

	public static boolean validarJogada(int linhaOrigem, int linhaSeguinte, int colunaOrigem, int colunaSeguinte, String[][] tabuleiro, String tipoPeca, String tipoDama, int ptsBranco, int ptsPreto) {

		boolean validar = false;

		int subLinha = linhaSeguinte - linhaOrigem, subCol = colunaSeguinte - colunaOrigem, 
				linhaSegComeu = 0, colSegComeu = 0, auxLinhaSegDama, auxColunaSegDama,
				auxLinhaOrigemDama, auxColunaOrigemDama;

		String pecaPreta = "|○|", pecaBranca = "|●|", damaPreto = "|◎|", damaBranco = "|◉|", 
				vazio = "|_|", pecaOposta = "|_|", linhaIntermed, colunaIntermed;

		if (tipoPeca == pecaPreta || tipoPeca == damaPreto) {

			pecaOposta = pecaBranca;
			tipoDama = damaPreto;

		} else if (tipoPeca == pecaBranca || tipoPeca == damaBranco) {

			pecaOposta = pecaPreta;
			tipoDama = damaBranco;
		}

		if (subCol != -1 || subLinha != 0) {

			if (subLinha == 2) {
				linhaSegComeu = 1;
			} else if (subLinha == -2) {
				linhaSegComeu = -1;
			}

			if (subCol == 2) {
				colSegComeu = 1;
			} else if (subCol == -2) {
				colSegComeu = -1;
			}
		}

		if (tabuleiro[linhaSeguinte][colunaSeguinte] == vazio) {

			if(tipoPeca == pecaPreta || tipoPeca == pecaBranca) {

				if ((linhaSeguinte == linhaOrigem + 1 || linhaSeguinte == linhaOrigem - 1)
						&& colunaSeguinte == colunaOrigem + 1 || colunaSeguinte == colunaOrigem - 1) {

					tabuleiro[linhaSeguinte][colunaSeguinte] = tipoPeca;
					// preenchendo com a peça a linha e coluna seguinte

					tabuleiro[linhaOrigem][colunaOrigem] = vazio;
					// apagando onde a peça estava anteriormente

					validar = true;

				} else {

					if ((tabuleiro[linhaOrigem + linhaSegComeu][colunaOrigem + colSegComeu] == pecaOposta)
							&& (linhaSeguinte == linhaOrigem + subLinha) && (colunaSeguinte == colunaOrigem + subCol)) {

						validar = true;

						tabuleiro[linhaOrigem + linhaSegComeu][colunaOrigem + colSegComeu] = vazio;
						// apagando a peça comida
						tabuleiro[linhaOrigem][colunaOrigem] = vazio;
						// apagando onde a peça estava anteriormente
						tabuleiro[linhaSeguinte][colunaSeguinte] = tipoPeca;
						// preenchendo com a peça a linha e coluna seguintes
						
						if (linhaSeguinte == 0 && tipoPeca == pecaBranca) {
							tabuleiro[linhaSeguinte][colunaSeguinte] = damaBranco;
						}

						if (linhaSeguinte == 7 && tipoPeca == pecaPreta) {
							tabuleiro[linhaSeguinte][colunaSeguinte] = damaPreto;
						}

						if (tipoPeca == pecaPreta || tipoPeca == damaPreto) {
							ptsPreto++;
						} else if (tipoPeca == pecaBranca || tipoPeca == damaBranco) {
							ptsBranco++;
						}
					}
				}
			} 
			
		} else {
			System.out.println("\nPOSIÇÃO INVALIDA");
		}
		return validar;
	}


	public static int pontosBranco (String pecaPreto, String [][] tabuleiro, int ptsBranco) {

		ptsBranco = 12;

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {

				if(tabuleiro[i][j] == pecaPreto) {
					ptsBranco--;
				}
			}
		}

		return ptsBranco;
	}

	public static int pontosPreto (String pecaBranco, String [][] tabuleiro, int ptsPreto) {

		ptsPreto = 12;

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {

				if(tabuleiro[i][j] == pecaBranco) {
					ptsPreto--;
				}
			}
		}

		return ptsPreto;
	}

	public static boolean verificarEmpate(String[][] tabuleiro, int qtdRoudSemComer) {

		boolean empate = false;
		int damaPreta = 0, damaBranca = 0, pecaBranca = 0, pecaPreta = 0;

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {

				if (tabuleiro[i][j] == "|●|") {	
					pecaBranca++;
				}
				if (tabuleiro[i][j] == "|○|") {
					pecaPreta++;
				}
				if (tabuleiro[i][j] == "|◉|") {
					damaBranca++;
				}
				if (tabuleiro[i][j] == "|◎|") {
					damaPreta++;
				}
			}
		}

		// SE FICAR 20 ROUNDS SEM COMER NENHUMA PEDRA

		if (qtdRoudSemComer == 20) {
			empate = true;
		}
		// 2 damas contra 2 damas

		if (damaBranca == 2 && damaPreta == 2 && pecaBranca == 0 && pecaPreta == 0) {
			empate = true;
		// 2 damas contra uma

		} else if ((damaBranca == 2 && damaPreta == 1 && pecaBranca == 0 && pecaPreta == 0)
				|| damaBranca == 1 && damaPreta == 2 && pecaBranca == 0 && pecaPreta == 0) {
			empate = true;
		// 2 damas contra uma dama e uma peça

		} else if ((damaBranca == 2 && damaPreta == 1 && pecaBranca == 0 && pecaPreta == 1)
				|| (damaBranca == 1 && damaPreta == 2 && pecaBranca == 1 && pecaPreta == 0)) {
			empate = true;
		// Uma dama contra uma

		} else if (damaBranca == 1 && damaPreta == 1 && pecaBranca == 0 && pecaPreta == 0) {
			empate = true;
		// Uma dama contra uma dama e uma pedra

		} else if ((damaBranca == 1 && damaPreta == 1 && pecaBranca == 1 && pecaPreta == 0)
				|| (damaBranca == 1 && damaPreta == 1 && pecaBranca == 0 && pecaPreta == 1)) {

			empate = true;
		}
		return empate;
	}

	public static String[][] promoverDama(String[][] tabuleiro) {

		String pecaPreta = "|○|", pecaBranca = "|●|";

		for (int i = 0; i < 8; i++) {

			if (tabuleiro[0][i] == pecaBranca) {
				tabuleiro[0][i] = "|◉|";
			}

			if (tabuleiro[7][i] == pecaPreta) {
				tabuleiro[7][i] = "|◎|";
			}
		}
		return tabuleiro;
	}

	/* PEDRA SIMPLES: ● ○
	 * DAMA: ◉ ◎*/

}