package servicos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dados.Lateral;

public class LateralService {
    public ArrayList<Lateral> verificarLateraisBD(String diretorio){
        ArrayList<Lateral> laterais = new ArrayList<>();
        BufferedReader leitor;
        
        try{
            leitor = new BufferedReader(new FileReader(diretorio));
            String linha = null;
            while((linha = leitor.readLine()) != null){
                String linhaSeparada[] = linha.split(";");
                Lateral lateral = new Lateral(linhaSeparada[0], linhaSeparada[1], linhaSeparada[2], linhaSeparada[3], linhaSeparada[4], linhaSeparada[5]);
                lateral.setCruzamentos(Integer.parseInt(linhaSeparada[6]));
                lateral.setCruzamentosCertos(Integer.parseInt(linhaSeparada[7]));
                lateral.setGol(Integer.parseInt(linhaSeparada[8]));
                lateral.setAssistencia(Integer.parseInt(linhaSeparada[9]));
                lateral.setNJogos(Integer.parseInt(linhaSeparada[10]));

                String pt[] = linhaSeparada[11].split("-");
                for(int i = 0; i < pt.length; i ++){
                    lateral.setPartidas(pt[i]);
                }
                laterais.add(lateral);
            }
            leitor.close();
        }catch(FileNotFoundException file){
           //
        }
        catch(IOException e){
            System.out.println("Erro ao tentar ler");
        }
        return laterais;
    }
    public void adicionarEstatisticaLateraisBD(ArrayList<Lateral> laterais,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio));
            
            for(int i = 0; i < laterais.size(); i ++){
                escritor.write(laterais.get(i).getId()+";");
                escritor.write(laterais.get(i).getNome()+";");
                escritor.write(laterais.get(i).getNumero()+";");
                escritor.write(laterais.get(i).getPosicao()+";");
                escritor.write(laterais.get(i).getTime()+";");
                escritor.write(laterais.get(i).getEstado()+";");
                escritor.write(laterais.get(i).getCruzamentos()+";");
                escritor.write(laterais.get(i).getCruzamentosCertos()+";");
                escritor.write(laterais.get(i).getGol()+";");
                escritor.write(laterais.get(i).getAssistencia()+";");
                escritor.write(laterais.get(i).getNJogos()+";");
                for(int j = 0; j < laterais.get(i).getPartidas().size(); j ++){
                    escritor.write(laterais.get(i).getPartidas().get(j)+"-");
                }
                escritor.write("\n");
            }
            
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void adicionarLateralBD(Lateral lateral,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio,true));
            
            escritor.append(lateral.getId()+";");
            escritor.append(lateral.getNome()+";");
            escritor.append(lateral.getNumero()+";");
            escritor.append(lateral.getPosicao()+";");
            escritor.append(lateral.getTime()+";");
            escritor.append(lateral.getEstado()+";");
            escritor.append(lateral.getCruzamentos()+";");
            escritor.append(lateral.getCruzamentosCertos()+";");
            escritor.append(lateral.getGol()+";");
            escritor.append(lateral.getAssistencia()+";");
            escritor.append(lateral.getNJogos()+";");
            if(lateral.getPartidas().isEmpty()){
                escritor.append(" ");
            }else{
                for(int i = 0; i < lateral.getPartidas().size(); i++){
                    escritor.append(lateral.getPartidas().get(i)+"-");
                }    
            }
            escritor.append("\n");
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public int verificaLateralExiste(ArrayList<Lateral> laterais,String id){
        for(int i = 0; i < laterais.size(); i ++){
            if(laterais.get(i).getId().equals(id)){
                return 1;//Existe
            }
        }
        return 0;//Nao existe
    }
    public int verificaExistenciaPartida(Lateral lateral,String partida){
        for(int i = 0; i < lateral.getPartidas().size(); i ++){
            if(lateral.getPartidas().get(i).equals(partida)){
                return 1;
            }
        }
        return 0;
    }
}
