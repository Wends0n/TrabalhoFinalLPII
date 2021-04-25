package servicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import dados.Time;

public class TimeService {
    public ArrayList<Time> verificarTimesBD(String diretorio){
        ArrayList<Time> times = new ArrayList<>();
        BufferedReader leitor;
        try{
            leitor = new BufferedReader(new FileReader(diretorio));
            String linha = null;
            while((linha = leitor.readLine()) != null){
                String linhaSeparada[] = linha.split(";");
                Time time = new Time(linhaSeparada[0], linhaSeparada[1]);
                time.setTotalJogadores(Integer.parseInt(linhaSeparada[2]));
                time.setQuantidadeJogos(Integer.parseInt(linhaSeparada[3]));
                time.setGolsMarcados(Integer.parseInt(linhaSeparada[4]));
                time.setGolsSofridos(Integer.parseInt(linhaSeparada[5]));
                String pt[] = linhaSeparada[6].split("-");
                for(int i = 0; i < pt.length; i ++){
                    time.setPartidas(pt[i]);
                }
                times.add(time);
            }
            leitor.close();
        }catch(FileNotFoundException file){
            //
        }
        catch(IOException e){
            System.out.println("Erro ao tentar ler");
        }
        return times;
    }
    public void adicionarEstatisticaTimeBD(ArrayList<Time> times, String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio));
            for(int i = 0; i < times.size(); i ++){
                escritor.write(times.get(i).getNome()+";");
                escritor.write(times.get(i).getEstado()+";");
                escritor.write(times.get(i).getTotalJogadores()+";");
                escritor.write(times.get(i).getQuantidadeJogos()+";");
                escritor.write(times.get(i).getGolsMarcados()+";");
                escritor.write(times.get(i).getGolsSofridos()+";");
                for(int j = 0; j < times.get(i).getPartidas().size(); j ++){
                    escritor.write(times.get(i).getPartidas().get(j)+"-");
                }
                escritor.write("\n");
            }
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void adicionarTimeBD(Time time, String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio,true));
            escritor.append(time.getNome()+";");
            escritor.append(time.getEstado()+";");
            escritor.append(time.getTotalJogadores()+";");
            escritor.append(time.getQuantidadeJogos()+";");
            escritor.append(time.getGolsMarcados()+";");
            escritor.append(time.getGolsSofridos()+";");
            if(time.getPartidas().isEmpty()){
                escritor.append(" ");
            }else{
                for(int i = 0; i < time.getPartidas().size(); i++){
                    escritor.append(time.getPartidas().get(i)+"-");
                }    
            }
            escritor.append("\n");
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public int verificaTimeExiste(ArrayList<Time> times,String nome, String estado){
        for(int i = 0; i < times.size(); i ++){
            if(times.get(i).getNome().equals(nome) && times.get(i).getEstado().equals(estado)){
                return 1;//Existe
            }
        }
        return 0;//Nao existe
    }
    public int verificaExistenciaPartida(Time time,String partida){
        for(int i = 0; i < time.getPartidas().size(); i ++){
            if(time.getPartidas().get(i).equals(partida)){
                return 1;
            }
        }
        return 0;
    }
}