package dados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Html {
    private String arq;
    public void gerarHtml(String tipo){
        BufferedReader leitor;
        try{
            leitor = new BufferedReader(new FileReader("src/bancoDeDados/htmlBase/"+tipo+".html"));
            String linha = null;
            
            while((linha = leitor.readLine()) != null){
                this.arq += linha;
            }
            
            leitor.close();
        }catch(FileNotFoundException file){
           //
        }
        catch(IOException e){
            System.out.println("Erro ao tentar ler");
        }
    }
    public void reescreverHtml(String conteudo,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio));
            
            escritor.write(conteudo);
            
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public String getConteudoArquivo(){
        return this.arq;
    }
}
