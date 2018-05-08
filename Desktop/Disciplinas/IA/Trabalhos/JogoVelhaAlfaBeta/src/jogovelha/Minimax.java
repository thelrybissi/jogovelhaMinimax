package jogovelha;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Thelr
 */
public class Minimax {
    
    static ArrayList<Sucessor> sucessores = new ArrayList<Sucessor>();
    int tam, maxProf;
    
    
    public Minimax(int tam, int maxProf){
        this.tam = tam;
        if(maxProf > 0)
            this.maxProf = maxProf;
        else 
            this.maxProf = Integer.MAX_VALUE;
    }
    
    public int[][] decisao_minimax(int[][] tab) {
        
        sucessores.clear();
        
        //Recebe utilidade maxima
        int v = valor_max (tab, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        
        //Percorre a lista  em busca do primeiro sucessor com utilidade maxima
        for(Sucessor s : sucessores)
            if(s.utilidade == v)
                return s.tabuleiro;
        
    return tab;
    }
    
    public int valor_max(int[][] tab, int alfa, int beta, boolean prim){
        //Se a profundidade for maior que a maxima ou jogo acabou
        //retorna a utilidade
        if(teste_terminal(tab))
            return utilidade (tab);
        
        //Atribui menor valor de um inteiro
        int v = Integer.MIN_VALUE;
        
        //Percorre os nós sucessores de MAX
        for(Sucessor s : gerar_sucessores (tab, 1)){
            v = Math.max(v, valor_min( s.tabuleiro, alfa, beta));
            s.utilidade = v;
            //Se forem os primeiros sucessores, adiciona na lista de sucessores
            if(prim)
                sucessores.add(s);
            
            //Poda beta, se o valor for maior que beta, retorna o valor
            if(v>=beta)
                return v;
            
            alfa = Math.max(alfa, v);
        }
        return v;
    }
    
    public int valor_min(int[][] tab, int alfa, int beta){
        //Se a profundidade for maior que a maxima ou jogo acabou
        //retorna a utilidade
        if(teste_terminal(tab))
            return utilidade (tab);
        
        //Atribui o maior valor inteiro
        int v = Integer.MAX_VALUE;
        
        //Percorre os nós sucessores de MIN
        for(Sucessor s : gerar_sucessores (tab, -1)){
            v = Math.min(v, valor_max(s.tabuleiro, alfa, beta, false));
            s.utilidade = v;
            
            //Poda alfa se o valor for menor que alfa, retorna o valor
            if(v <= alfa)
                return v;
            
            //Se valor menor que beta, beta recebe...
            beta = Math.min(beta, v);
        }
        return v;
    }
    
        
    //Gera os sucessores de um jogador, a partir de um estado atual    
    public ArrayList<Sucessor> gerar_sucessores(int[][] tab, int v){

        ArrayList<Sucessor> suc = new ArrayList<>();
        
        for(int i = 0; i < tam; i++){
            for(int j = 0; j < tam; j++){
                if(tab[i][j] == 0){
                    tab[i][j] = v;
                    suc.add(new Sucessor (tab));
                    tab[i][j] = 0;
                }
            }
        }
    return suc;
    }
    
    //Verifica se chegou em algum estado terminal e em caso
    //afirmativo encerra o jogo
    public boolean teste_terminal(int[][] tab){
        return (ganhou( tab, 1) || ganhou(tab, -1) || semEspaco(tab));
    }
    
    //retorna a utilidade
    public int utilidade (int[][] tab){
        if(ganhou (tab, 1))
            return 1;
        else if(ganhou (tab, -1))
            return -1;
        else
            return 0;
    }
    
    //Verifica se jogador ganhou
    public boolean ganhou(int[][] tab, int v){
        
        for(int i = 0; i < tam; i++)
            if(ganhouLinha (tab, i, v) || ganhouColuna (tab, i, v))
                return  true;
        
           if(ganhouDiag1 (tab, v) || ganhouDiag2 (tab, v))
           return true;
            
      return false;
    }
    
    
    public boolean ganhouLinha(int[][] tab, int l, int v){
        for(int i = 0; i < tam; i++)
            if(tab[l][i] != v)
                return false;
        
        return true;
    }
    
        public boolean ganhouColuna(int[][] tab, int c, int v){
        for(int i = 0; i < tam; i++)
            if(tab[i][c] != v)
                return false;
        
        return true;
    }
    
        
        public boolean ganhouDiag1 (int[][] tab, int v){
        for(int i = 0; i < tam; i++)
            if(tab[i][i] != v)
                return false;
        
        return true;
    }
        
        
        public boolean ganhouDiag2 (int[][] tab, int v){
        
            for(int i = 0; i < tam; i++)
                if(tab[(tam -1)-i][i] != v)
                return false;
        
        return true;
    }
        
        //Não tem mais espaços restantes no tabuleiro...
        public boolean semEspaco (int[][] tab){
            for(int l = 0; l < tam; l++)
                for(int c = 0; c < tam; c++)
                    if(tab[l][c] == 0)
                        return false;
                
            return true;
            }
}
