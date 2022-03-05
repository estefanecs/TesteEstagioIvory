package com.ivory.TesteEstagio.CampoMinado;

public class Program {

    public void executar() {

        CampoMinado campoMinado = new CampoMinado();

        System.out.println("Início do jogo\n=======");
        System.out.println(campoMinado.Tabuleiro());

        // Realize sua codificação a partir deste ponto, boa sorte!

        while (campoMinado.JogoStatus().equals(StatusTipo.Aberto)) {
            //Analisa qual posição será aberta
            int[] posicao = this.analisarPosicao(campoMinado);
            //Abre a melhor posição(linha,coluna)
            campoMinado.Abrir(posicao[0] + 1, posicao[1] + 1);
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

        //APARTIR DAQUI ESTÁ ERRADO
        int l = 0, c = 0;
        boolean isBomba;
        boolean encontrou;
        while (l < 9) {
            System.out.println("\n l=" + l);
            c = 0;
            while (c < 9) {
                System.out.println("\n************ ESTOU EM c=" + c);
                isBomba = false;
                encontrou = false;
                System.out.println("\n valor do tabuleiro: " + tabuleiro[l][c]);
                if ("-".equals(tabuleiro[l][c])) {
                    System.out.println("\nentrei na analise de -: posicao " + l + c);
                    //Posição da casa atual
                    posicao[0] = l;
                    posicao[1] = c;

                    //LINHA ABAIXO, MESMA COLUNA
                    if (l != 8) {
                        System.out.println("\n entrei em l!8");
                        String a = tabuleiro[l + 1][c];
                        if (a.equals("0")) {
                            System.out.println("\n retornei em l!0 e c!8");
                            return posicao;
                        } else {
                            isBomba = true;
                            encontrou = false;
                        }
                        System.out.println("\n valor de c" + c+ " valor de encontrou= "+encontrou);
                    }

                    //LINHA ABAIXO, COLUNA Á DIREITA
                    if (l != 8 && c != 8) {
                        System.out.println("\n entrei em= l!8 e c!8");
                        String b = tabuleiro[l + 1][c + 1];
                        if (b.equals("0")) {
                            System.out.println("\n retornei em l!0 e c!8");
                            return posicao;
                        } else if (tabuleiro[l][c + 1].equals("-") && c+2<=8) {
                            if (tabuleiro[l + 1][c + 2].equals("0")) {
                                posicao[0] = l;
                                posicao[1] = c + 1;
                                encontrou = true;
                                isBomba = false;
                                System.out.println("\r\n posicao nova: " + l + c + 1);
                                return posicao;

                            } //funciona em casos que a lateral não existe, poderia retornar direto
                            else if (!"-".equals(tabuleiro[l + 1][c + 2])) {
                                posicao[0] = l;
                                posicao[1] = c;
                                isBomba = false;
                                encontrou = true;
                                System.out.println("\r\n posicao atual: " + l + c);

                            }
                        }
                        System.out.println("\n valor de c" + c+ " valor de encontrou= "+encontrou);
                    }

                    //LINHA ABAIXO, COLUNA Á ESQUERDA
                    if (l != 8 && c != 0) {
                        System.out.println("\n entrei em l!8 e c!0=");
                        String d = tabuleiro[l + 1][c - 1];
                        if (d.equals("0")) {
                            System.out.println("\n retornei em l!0 e c!8");
                            isBomba = false;
                            encontrou = true;
                            //return posicao;
                        } else {
                            isBomba = true;
                        }
                        System.out.println("\n valor de c" + c+ " valor de encontrou= "+encontrou);
                    }

                    //LINHA ACIMA, MESMA COLUNA
                    if (l != 0) {
                        System.out.println("\n entrei em l!0=");
                        String e = tabuleiro[l - 1][c];
                        if (e.equals("0")) {
                            System.out.println("\n retornei em l!0 e c!8");
                            encontrou = true;
                            //return posicao;
                            isBomba = false;

                        } else {
                            isBomba = true;
                        }
                        System.out.println("\n valor de c" + c+ " valor de encontrou= "+encontrou);
                    }

                    //LINHA ACIMA, COLUNA Á DIREITA
                    if (l != 0 && c != 8) {
                        System.out.println("\n entrei em l!0 e c!8");
                        String f = tabuleiro[l - 1][c + 1];
                        if (f.equals("0")) {
                            System.out.println("\n retornei em l!0 e c!8");
                            encontrou = true;
                            //return posicao;
                            isBomba = false;
                        } else if (isBomba == true && tabuleiro[l][c + 1].equals("-") && c+2<=8) {
                            if (tabuleiro[l - 1][c + 2].equals("0")) {
                                posicao[0] = l;
                                posicao[1] = c + 1;
                                encontrou = true;
                                isBomba = false;
                                System.out.println("\r\n posicao nova: " + l + c + 1);
                                return posicao;

                            } //funciona em casos que a lateral não existe, poderia retornar direto
                            else if (!"-".equals(tabuleiro[l - 1][c + 2])) {
                                posicao[0] = l;
                                posicao[1] = c;
                                isBomba = false;
                                encontrou = true;
                                System.out.println("\r\n posicao atual: " + l + c);
                            }
                            
                        }
                        if(encontrou && c==0){
                            System.out.println("retornei por aqui ");
                            return posicao;
                        }
                        System.out.println("\n valor de c" + c+ " valor de encontrou= "+encontrou +"analisando LINHA ACIMA, COLUNA Á DIREITA");
                    }

                    //LINHA ACIMA, COLUNA Á ESQUERDA
                    if (l != 0 && c != 0) {
                        System.out.println("\n entrei em l!0 e c!0");
                        String g = tabuleiro[l - 1][c - 1];
                        if (g.equals("0")) {
                            System.out.println("\n retornei em l!0 e c!0");
                            encontrou = true;
                            return posicao;
                        } else if (tabuleiro[l][c - 1].equals("-") && c-2>=0) {
                            if (tabuleiro[l - 1][c - 2].equals("0")) {
                                posicao[0] = l;
                                posicao[1] = c - 1;
                                encontrou = true;
                                isBomba = false;
                                System.out.println("\r\n posicao nova: " + l + c + 1);
                                return posicao;

                            } //funciona em casos que a lateral não existe, poderia retornar direto
                            else if (!"-".equals(tabuleiro[l - 1][c - 2])) {
                                posicao[0] = l;
                                posicao[1] = c;
                                isBomba = false;
                                encontrou = true;
                                System.out.println("\r\n achei posicao atual: " + l + c);
                            }
                            
                        }
                        if(encontrou && c==9){
                            return posicao;
                        }
                        else{
                            encontrou = false;
                        }
                        System.out.println("\n valor de c" + c+ " valor de encontrou= "+encontrou);
                    }

                    //MESMA LINHA, E UMA COLUNA Á ESQUERDA
                    if (c != 0) {
                        System.out.println("\n entrei em c!0=");
                        String e = tabuleiro[l][c - 1];
                        if (e.equals("0")) {
                            System.out.println("\n retornei em  c!0");
                            encontrou = true;
                            isBomba = false;

                        } else{
                            isBomba = true;
                        }
                        System.out.println("\n valor de c" + c+ " valor de encontrou= "+encontrou);
                    }

                    //MESMA LINHA, E UMA COLUNA Á DIREITA
                    if (c != 8) {
                        System.out.println("\n entrei em c!8=");
                        String e = tabuleiro[l][c + 1];
                        if (e.equals("0")) {
                            System.out.println("\n retornei em  c!8");
                            encontrou = true;
                            isBomba = false;

                        } else {
                            isBomba = true;
                        }
                        System.out.println("\n valor de c" + c + " valor de encontrou= "+encontrou);
                    }

                    if (encontrou == true) {
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
