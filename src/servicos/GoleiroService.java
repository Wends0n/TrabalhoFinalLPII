package servicos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dados.Goleiro;

public class GoleiroService {
    public ArrayList<Goleiro> verificarGoleirosBD(String diretorio){
        ArrayList<Goleiro> goleiros = new ArrayList<>();
        BufferedReader leitor;
        
        try{
            leitor = new BufferedReader(new FileReader(diretorio));
            String linha = null;
            while((linha = leitor.readLine()) != null){
                String linhaSeparada[] = linha.split(";");
                Goleiro goleiro = new Goleiro(linhaSeparada[0], linhaSeparada[1], linhaSeparada[2], linhaSeparada[3], linhaSeparada[4], linhaSeparada[5]);
                goleiro.setGolsSofridos(Integer.parseInt(linhaSeparada[6]));
                goleiro.setDefesasDificeis(Integer.parseInt(linhaSeparada[7]));
                goleiro.setPenaltis(Integer.parseInt(linhaSeparada[8]));
                goleiro.setPenaltisDefendidos(Integer.parseInt(linhaSeparada[9]));
                goleiro.setGol(Integer.parseInt(linhaSeparada[10]));
                goleiro.setAssistencia(Integer.parseInt(linhaSeparada[11]));
                goleiro.setNJogos(Integer.parseInt(linhaSeparada[12]));

                String pt[] = linhaSeparada[13].split("-");
                for(int i = 0; i < pt.length; i ++){
                    goleiro.setPartidas(pt[i]);
                }
                goleiros.add(goleiro);
            }
            leitor.close();
        }catch(FileNotFoundException file){
           //
        }
        catch(IOException e){
            System.out.println("Erro ao tentar ler");
        }
        return goleiros;
    }
    public void adicionarEstatisticaGoleirosBD(ArrayList<Goleiro> goleiros,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio));
            
            for(int i = 0; i < goleiros.size(); i ++){
                escritor.write(goleiros.get(i).getId()+";");
                escritor.write(goleiros.get(i).getNome()+";");
                escritor.write(goleiros.get(i).getNumero()+";");
                escritor.write(goleiros.get(i).getPosicao()+";");
                escritor.write(goleiros.get(i).getTime()+";");
                escritor.write(goleiros.get(i).getEstado()+";");
                escritor.write(goleiros.get(i).getGolsSofridos()+";");
                escritor.write(goleiros.get(i).getDefesasDificeis()+";");
                escritor.write(goleiros.get(i).getPenaltis()+";");
                escritor.write(goleiros.get(i).getPenaltisDefendidos()+";");
                escritor.write(goleiros.get(i).getGol()+";");
                escritor.write(goleiros.get(i).getAssistencia()+";");
                escritor.write(goleiros.get(i).getNJogos()+";");
                for(int j = 0; j < goleiros.get(i).getPartidas().size(); j ++){
                    escritor.write(goleiros.get(i).getPartidas().get(j)+"-");
                }
                escritor.write("\n");
            }
            
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void adicionarGoleiroBD(Goleiro goleiro,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio,true));
            
            escritor.append(goleiro.getId()+";");
            escritor.append(goleiro.getNome()+";");
            escritor.append(goleiro.getNumero()+";");
            escritor.append(goleiro.getPosicao()+";");
            escritor.append(goleiro.getTime()+";");
            escritor.append(goleiro.getEstado()+";");
            escritor.append(goleiro.getGolsSofridos()+";");
            escritor.append(goleiro.getDefesasDificeis()+";");
            escritor.append(goleiro.getPenaltis()+";");
            escritor.append(goleiro.getPenaltisDefendidos()+";");
            escritor.append(goleiro.getGol()+";");
            escritor.append(goleiro.getAssistencia()+";");
            escritor.append(goleiro.getNJogos()+";");
            if(goleiro.getPartidas().isEmpty()){
                escritor.append(" ");
            }else{
                for(int i = 0; i < goleiro.getPartidas().size(); i++){
                    escritor.append(goleiro.getPartidas().get(i)+"-");
                }    
            }
            escritor.append("\n");
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public int verificaGoleiroExiste(ArrayList<Goleiro> goleiros,String id){
        for(int i = 0; i < goleiros.size(); i ++){
            if(goleiros.get(i).getId().equals(id)){
                return 1;//Existe
            }
        }
        return 0;//Nao existe
    }
    public int verificaExistenciaPartida(Goleiro goleiro,String partida){
        for(int i = 0; i < goleiro.getPartidas().size(); i ++){
            if(goleiro.getPartidas().get(i).equals(partida)){
                return 1;
            }
        }
        return 0;
    }
}
