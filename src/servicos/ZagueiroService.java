package servicos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dados.Zagueiro;

public class ZagueiroService {
    public ArrayList<Zagueiro> verificarZagueirosBD(String diretorio){
        ArrayList<Zagueiro> zagueiros = new ArrayList<>();
        BufferedReader leitor;
        
        try{
            leitor = new BufferedReader(new FileReader(diretorio));
            String linha = null;
            while((linha = leitor.readLine()) != null){
                String linhaSeparada[] = linha.split(";");
                Zagueiro zagueiro = new Zagueiro(linhaSeparada[0], linhaSeparada[1], linhaSeparada[2], linhaSeparada[3], linhaSeparada[4], linhaSeparada[5]);
                zagueiro.setDesarmes(Integer.parseInt(linhaSeparada[6]));
                zagueiro.setFaltas(Integer.parseInt(linhaSeparada[7]));
                zagueiro.setFalhasDefensivas(Integer.parseInt(linhaSeparada[8]));
                zagueiro.setGol(Integer.parseInt(linhaSeparada[9]));
                zagueiro.setAssistencia(Integer.parseInt(linhaSeparada[10]));
                zagueiro.setNJogos(Integer.parseInt(linhaSeparada[11]));

                String pt[] = linhaSeparada[12].split("-");
                for(int i = 0; i < pt.length; i ++){
                    zagueiro.setPartidas(pt[i]);
                }
                zagueiros.add(zagueiro);
            }
            leitor.close();
        }catch(FileNotFoundException file){
           //
        }
        catch(IOException e){
            System.out.println("Erro ao tentar ler");
        }
        return zagueiros;
    }
    public void adicionarEstatisticaZagueirosBD(ArrayList<Zagueiro> zagueiros,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio));
            
            for(int i = 0; i < zagueiros.size(); i ++){
                escritor.write(zagueiros.get(i).getId()+";");
                escritor.write(zagueiros.get(i).getNome()+";");
                escritor.write(zagueiros.get(i).getNumero()+";");
                escritor.write(zagueiros.get(i).getPosicao()+";");
                escritor.write(zagueiros.get(i).getTime()+";");
                escritor.write(zagueiros.get(i).getEstado()+";");
                escritor.write(zagueiros.get(i).getDesarmes()+";");
                escritor.write(zagueiros.get(i).getFaltas()+";");
                escritor.write(zagueiros.get(i).getFalhasDefensivas()+";");
                escritor.write(zagueiros.get(i).getGol()+";");
                escritor.write(zagueiros.get(i).getAssistencia()+";");
                escritor.write(zagueiros.get(i).getNJogos()+";");
                for(int j = 0; j < zagueiros.get(i).getPartidas().size(); j ++){
                    escritor.write(zagueiros.get(i).getPartidas().get(j)+"-");
                }
                escritor.write("\n");
            }
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void adicionarZagueiroBD(Zagueiro zagueiro,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio,true));
            
            escritor.append(zagueiro.getId()+";");
            escritor.append(zagueiro.getNome()+";");
            escritor.append(zagueiro.getNumero()+";");
            escritor.append(zagueiro.getPosicao()+";");
            escritor.append(zagueiro.getTime()+";");
            escritor.append(zagueiro.getEstado()+";");
            escritor.append(zagueiro.getDesarmes()+";");
            escritor.append(zagueiro.getFaltas()+";");
            escritor.append(zagueiro.getFalhasDefensivas()+";");
            escritor.append(zagueiro.getGol()+";");
            escritor.append(zagueiro.getAssistencia()+";");
            escritor.append(zagueiro.getNJogos()+";");
            if(zagueiro.getPartidas().isEmpty()){
                escritor.append(" ");
            }else{
                for(int i = 0; i < zagueiro.getPartidas().size(); i++){
                    escritor.append(zagueiro.getPartidas().get(i)+"-");
                }    
            }
            escritor.append("\n");
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public int verificaZagueiroExiste(ArrayList<Zagueiro> zagueiros,String id){
        for(int i = 0; i < zagueiros.size(); i ++){
            if(zagueiros.get(i).getId().equals(id)){
                return 1;//Existe
            }
        }
        return 0;//Nao existe
    }
    public int verificaExistenciaPartida(Zagueiro zagueiro,String partida){
        for(int i = 0; i < zagueiro.getPartidas().size(); i ++){
            if(zagueiro.getPartidas().get(i).equals(partida)){
                return 1;
            }
        }
        return 0;
    }
}
