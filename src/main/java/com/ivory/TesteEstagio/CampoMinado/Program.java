package com.ivory.TesteEstagio.CampoMinado;

public class Program {
	
	public void executar() {
		
		CampoMinado campoMinado = new CampoMinado();
		
		System.out.println("Início do jogo\n=======");
		System.out.println(campoMinado.Tabuleiro());
		
		// Realize sua codificação a partir deste ponto, boa sorte!
                
                //Variavel para armanezar a linha e a coluna da posição que será aberta
                int linha, coluna;
               
                while(campoMinado.JogoStatus().equals(StatusTipo.Aberto)){
                    //Analisa qual posição será aberta
                    int[] posicao= this.analisarPosicao(); 
                    linha = posicao[0];
                    coluna = posicao[1];
                   //Abre a melhor posição
                   campoMinado.Abrir(linha,coluna);
                   System.out.println("\nPosição escolhida: "+linha+","+coluna);
                   //Exibe o tabuleiro após a escolha da posição
                   System.out.println("\n=======Atualização do tabuleiro=======");
                   System.out.println(campoMinado.Tabuleiro());
                }
                //Exibe o resultado final do jogo
                if(campoMinado.JogoStatus().equals(StatusTipo.Vitoria)){
                   System.out.println("\n****PARABÉNS, VOCÊ VENCEU!****");
                }
                else{
                   System.out.println("\n****GAME OVER! TENTE NOVAMENTE.****");
                }
    }
        
        //--------------------FALTA FAZER ESSA FUNÇÃO--------------
        private int[] analisarPosicao(){
                int[] posicao = new int[2];
                posicao[0]=4;
                posicao[1]=1;
                return posicao;
        }

  
}
