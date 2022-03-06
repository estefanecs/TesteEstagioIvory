package com.ivory.TesteEstagio.CampoMinado;

public class Program {

    public void executar() {

        CampoMinado campoMinado = new CampoMinado();

        System.out.println("Início do jogo\n=======");
        System.out.println(campoMinado.Tabuleiro());

        // Realize sua codificação a partir deste ponto, boa sorte!
        while (campoMinado.JogoStatus().equals(StatusTipo.Aberto)) {
            int[] posicao = this.analisarPosicao(campoMinado); //Analisa qual posição será aberta
            campoMinado.Abrir(posicao[0] + 1, posicao[1] + 1); //Abre a melhor posicao(linha,coluna)
            System.out.println("\nPosição escolhida: " + posicao[0] + "," + posicao[1]);
            //Exibe o tabuleiro após a escolha da posição
            System.out.println("\n*****Atualização do tabuleiro*****");
            System.out.println(campoMinado.Tabuleiro());

        }
        //Exibe o resultado final do jogo
        if (campoMinado.JogoStatus().equals(StatusTipo.Vitoria)) {
            System.out.println("\n****PARABÉNS, VOCÊ VENCEU!****");
        } else {
            System.out.println("\n****GAME OVER! TENTE NOVAMENTE.****");
        }
    }

    // Função que analisa qual posição deverá ser aberta
    private int[] analisarPosicao(CampoMinado campoMinado) {
        String[][] tabuleiro = new String[9][9];
        int[] posicao = new int[2];

        //Transforma a string Tabuleiro em matriz
        String[] colunas;
        String[] linhas = campoMinado.Tabuleiro().split("\r\n");
        for (int l = 0; l < 9; l++) {
            colunas = linhas[l].split("(?!^)");
            for (int c = 0; c < 9; c++) {
                tabuleiro[l][c] = colunas[c];
            }
        }

        int l = 0, c;
        boolean isBomba;
        boolean encontrou;
        while (l < 9) {
            c = 0;
            while (c < 9) {
                isBomba = false;
                encontrou = false;
                int risco = 0;
                if ("-".equals(tabuleiro[l][c])) {
                    //Posição da casa atual
                    posicao[0] = l;
                    posicao[1] = c;
                    String valorCasa;

                    //LINHA ABAIXO, MESMA COLUNA
                    if (l != 8) {
                        valorCasa = tabuleiro[l + 1][c];
                        if (valorCasa.equals("0")) {
                            return posicao;
                        } else {
                            isBomba = true;
                            encontrou = false;
                        }
                    }

                    //LINHA ABAIXO, COLUNA Á DIREITA
                    if (l != 8 && c != 8) {
                        valorCasa = tabuleiro[l + 1][c + 1];
                        if (valorCasa.equals("0")) {
                            return posicao;
                        } else if (tabuleiro[l][c + 1].equals("-") && c + 2 <= 8) {
                            if (tabuleiro[l + 1][c + 2].equals("0")) {
                                posicao[0] = l;
                                posicao[1] = c + 1;
                                return posicao;

                            } else if (!"-".equals(tabuleiro[l + 1][c + 2])) {
                                isBomba = false;
                                encontrou = true;

                            }
                        }
                    }

                    //LINHA ABAIXO, COLUNA Á ESQUERDA
                    if (l != 8 && c != 0) {
                        valorCasa = tabuleiro[l + 1][c - 1];
                        if (valorCasa.equals("0")) {
                            isBomba = false;
                            encontrou = true;
                        } else {
                            isBomba = true;
                        }
                    }

                    //LINHA ACIMA, MESMA COLUNA
                    if (l != 0) {
                        valorCasa = tabuleiro[l - 1][c];
                        if (valorCasa.equals("0")) {
                            encontrou = true;
                            isBomba = false;

                        } else {
                            isBomba = true;
                        }
                    }

                    //LINHA ACIMA, COLUNA Á DIREITA
                    if (l != 0 && c != 8) {
                        valorCasa = tabuleiro[l - 1][c + 1];
                        if (valorCasa.equals("0")) {
                            encontrou = true;
                            return posicao;
                        } else if (isBomba && tabuleiro[l][c + 1].equals("-") && c + 2 <= 8) {
                            if (tabuleiro[l - 1][c + 2].equals("0")) {
                                posicao[0] = l;
                                posicao[1] = c + 1;
                                return posicao;

                            } else if (!"-".equals(tabuleiro[l - 1][c + 2])) {
                                isBomba = false;
                                encontrou = true;
                            }
                        }
                        if (encontrou && c == 0) {
                            return posicao;
                        }
                    }

                    //LINHA ACIMA, COLUNA Á ESQUERDA
                    if (l != 0 && c != 0) {
                        valorCasa = tabuleiro[l - 1][c - 1];
                        if (valorCasa.equals("0")) {
                            encontrou = true;
                            return posicao;
                        } else if (tabuleiro[l][c - 1].equals("-") && c - 2 >= 0) {
                            if (tabuleiro[l - 1][c - 2].equals("0")) {
                                posicao[0] = l;
                                posicao[1] = c - 1;
                                return posicao;

                            } else if (!"-".equals(tabuleiro[l - 1][c - 2])) {
                                isBomba = false;
                                encontrou = true;
                            }

                        }
                        if (encontrou && c == 9) {
                            return posicao;
                        } else {
                            encontrou = false;
                        }

                    }

                    //MESMA LINHA, E UMA COLUNA Á ESQUERDA
                    if (c != 0) {
                        valorCasa = tabuleiro[l][c - 1];
                        if (valorCasa.equals("0")) {
                            encontrou = true;
                            isBomba = false;

                        } else if (valorCasa.equals("-") && c - 2 >= 0) {
                            if (tabuleiro[l][c - 2].equals("0")) {
                                posicao[0] = l;
                                posicao[1] = c - 1;
                                return posicao;

                            } else if (!"-".equals(tabuleiro[l][c - 2])) {

                                if (c == 9 && !isBomba) {
                                    encontrou = true;
                                }
                                isBomba = false;
                            }

                        } else {
                            encontrou = false;
                            isBomba = true;
                        }
                    }

                    //MESMA LINHA, E UMA COLUNA Á DIREITA
                    if (c != 8) {
                        valorCasa = tabuleiro[l][c + 1];
                        if (valorCasa.equals("0")) {
                            encontrou = true;
                            isBomba = false;

                        } else if (valorCasa.equals("-") && c + 2 <= 8) {
                            if (tabuleiro[l][c + 2].equals("0")) {
                                posicao[0] = l;
                                posicao[1] = c + 1;
                                return posicao;

                            } else if (!"-".equals(tabuleiro[l][c + 2])) {
                                if (c == 0 && !isBomba) {
                                    encontrou = true;
                                }
                                isBomba = false;

                            }
                        } else {
                            encontrou = false;
                            isBomba = true;
                        }
                    }
                    if (encontrou) {
                        return posicao;
                    }
                }
                c++;
            }
            l++;
        }
        return posicao;
    }
}
