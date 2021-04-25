package servicos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dados.Atacante;

public class AtacanteService {
    public ArrayList<Atacante> verificarAtacantesBD(String diretorio){
        ArrayList<Atacante> atacantes = new ArrayList<>();
        BufferedReader leitor;
        
        try{
            leitor = new BufferedReader(new FileReader(diretorio));
            String linha = null;
            while((linha = leitor.readLine()) != null){
                String linhaSeparada[] = linha.split(";");
                Atacante atacante = new Atacante(linhaSeparada[0], linhaSeparada[1], linhaSeparada[2], linhaSeparada[3], linhaSeparada[4], linhaSeparada[5]);
                atacante.setFinalizacoes(Integer.parseInt(linhaSeparada[6]));
                atacante.setFinalizacoesCertas(Integer.parseInt(linhaSeparada[7]));
                atacante.setGol(Integer.parseInt(linhaSeparada[8]));
                atacante.setAssistencia(Integer.parseInt(linhaSeparada[9]));
                atacante.setNJogos(Integer.parseInt(linhaSeparada[10]));

                String pt[] = linhaSeparada[11].split("-");
                for(int i = 0; i < pt.length; i ++){
                    atacante.setPartidas(pt[i]);
                }
                atacantes.add(atacante);
            }
            leitor.close();
        }catch(FileNotFoundException file){
           //
        }
        catch(IOException e){
            System.out.println("Erro ao tentar ler");
        }
        return atacantes;
    }
    public void adicionarEstatisticaAtacantesBD(ArrayList<Atacante> atacantes,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio));
            
            for(int i = 0; i < atacantes.size(); i ++){
                escritor.write(atacantes.get(i).getId()+";");
                escritor.write(atacantes.get(i).getNome()+";");
                escritor.write(atacantes.get(i).getNumero()+";");
                escritor.write(atacantes.get(i).getPosicao()+";");
                escritor.write(atacantes.get(i).getTime()+";");
                escritor.write(atacantes.get(i).getEstado()+";");
                escritor.write(atacantes.get(i).getFinalizacoes()+";");
                escritor.write(atacantes.get(i).getFinalizacoesCertas()+";");
                escritor.write(atacantes.get(i).getGol()+";");
                escritor.write(atacantes.get(i).getAssistencia()+";");
                escritor.write(atacantes.get(i).getNJogos()+";");
                for(int j = 0; j < atacantes.get(i).getPartidas().size(); j ++){
                    escritor.write(atacantes.get(i).getPartidas().get(j)+"-");
                }
                escritor.write("\n");
            }
            
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void adicionarAtacanteBD(Atacante atacante,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio,true));
            
            escritor.append(atacante.getId()+";");
            escritor.append(atacante.getNome()+";");
            escritor.append(atacante.getNumero()+";");
            escritor.append(atacante.getPosicao()+";");
            escritor.append(atacante.getTime()+";");
            escritor.append(atacante.getEstado()+";");
            escritor.append(atacante.getFinalizacoes()+";");
            escritor.append(atacante.getFinalizacoesCertas()+";");
            escritor.append(atacante.getGol()+";");
            escritor.append(atacante.getAssistencia()+";");
            escritor.append(atacante.getNJogos()+";");
            if(atacante.getPartidas().isEmpty()){
                escritor.append(" ");
            }else{
                for(int i = 0; i < atacante.getPartidas().size(); i++){
                    escritor.append(atacante.getPartidas().get(i)+"-");
                }    
            }
            escritor.append("\n");
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public int verificaAtacanteExiste(ArrayList<Atacante> atacantes,String id){
        for(int i = 0; i < atacantes.size(); i ++){
            if(atacantes.get(i).getId().equals(id)){
                return 1;//Existe
            }
        }
        return 0;//Nao existe
    }
    public int verificaExistenciaPartida(Atacante atacante,String partida){
        for(int i = 0; i < atacante.getPartidas().size(); i ++){
            if(atacante.getPartidas().get(i).equals(partida)){
                return 1;
            }
        }
        return 0;
    }
}
