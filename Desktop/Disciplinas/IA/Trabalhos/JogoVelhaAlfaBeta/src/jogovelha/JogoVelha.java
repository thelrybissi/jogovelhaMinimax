package jogovelha;
import java.util.Scanner;
/**
 *
 * @author Thelry, Fabio e Ricardo
 */

public class JogoVelha {
        
    static int TAM = 3, PROF = -1;

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        Tabuleiro t = new Tabuleiro(TAM);
        
        Minimax mm = new Minimax(TAM,PROF);
        
        System.out.println("Jogo da Velha \n Boa Sorte");
        
        t.imprimirTab();
        
        do{
            int l, c;
            System.out.printf("Sua Jogada:\r\nLinha [0 - %d]: ", (TAM - 1));
            l = entrada.nextInt();
            System.out.printf("Coluna [0 - %d]: ", (TAM-1));
            c = entrada.nextInt();
            
            //Realiza Jogada
            t.fazerJogada(l, c);
            t.imprimirTab();
            
            //Verifica se não é estado terminal
            if(!mm.teste_terminal(t.tabuleiro)){
                
                //Aplica minimax ao tabuleiro
                Tabuleiro.tabuleiro = mm.decisao_minimax(t.tabuleiro);
                System.out.println("Jogada do Computador");
                t.imprimirTab();
            }
        }
        while(!mm.teste_terminal(t.tabuleiro));
                //Verifica ganhador ou empate
                if(mm.ganhou(t.tabuleiro, 1))
                    System.out.println("Computador Ganhou");
                else if (mm.ganhou(t.tabuleiro, -1))
                    System.out.println("Voce Ganhou!!");
                else
                    System.out.println("Empate!");
    }
}
