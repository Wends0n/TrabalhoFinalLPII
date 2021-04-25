package servicos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dados.Meia;
public class MeiaService {
    public ArrayList<Meia> verificarMeiasBD(String diretorio){
        ArrayList<Meia> meias = new ArrayList<>();
        BufferedReader leitor;
        
        try{
            leitor = new BufferedReader(new FileReader(diretorio));
            String linha = null;
            while((linha = leitor.readLine()) != null){
                String linhaSeparada[] = linha.split(";");
                Meia meia = new Meia(linhaSeparada[0], linhaSeparada[1], linhaSeparada[2], linhaSeparada[3], linhaSeparada[4], linhaSeparada[5]);
                meia.setPasses(Integer.parseInt(linhaSeparada[6]));
                meia.setPassesCertos(Integer.parseInt(linhaSeparada[7]));
                meia.setLancamentos(Integer.parseInt(linhaSeparada[8]));
                meia.setLancamentosCertos(Integer.parseInt(linhaSeparada[9]));
                meia.setGol(Integer.parseInt(linhaSeparada[10]));
                meia.setAssistencia(Integer.parseInt(linhaSeparada[11]));
                meia.setNJogos(Integer.parseInt(linhaSeparada[12]));

                String pt[] = linhaSeparada[13].split("-");
                for(int i = 0; i < pt.length; i ++){
                    meia.setPartidas(pt[i]);
                }
                meias.add(meia);
            }
            leitor.close();
        }catch(FileNotFoundException file){
           //
        }
        catch(IOException e){
            System.out.println("Erro ao tentar ler");
        }
        return meias;
    }
    public void adicionarEstatisticaMeiasBD(ArrayList<Meia> meias,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio));
            
            for(int i = 0; i < meias.size(); i ++){
                escritor.write(meias.get(i).getId()+";");
                escritor.write(meias.get(i).getNome()+";");
                escritor.write(meias.get(i).getNumero()+";");
                escritor.write(meias.get(i).getPosicao()+";");
                escritor.write(meias.get(i).getTime()+";");
                escritor.write(meias.get(i).getEstado()+";");
                escritor.write(meias.get(i).getPasses()+";");
                escritor.write(meias.get(i).getPassesCertos()+";");
                escritor.write(meias.get(i).getLancamentos()+";");
                escritor.write(meias.get(i).getLancamentosCertos()+";");
                escritor.write(meias.get(i).getGol()+";");
                escritor.write(meias.get(i).getAssistencia()+";");
                escritor.write(meias.get(i).getNJogos()+";");
                for(int j = 0; j < meias.get(i).getPartidas().size(); j ++){
                    escritor.write(meias.get(i).getPartidas().get(j)+"-");
                }
                escritor.write("\n");
            }
            
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void adicionarMeiaBD(Meia meia,String diretorio){
        BufferedWriter escritor;
        try{
            escritor = new BufferedWriter(new FileWriter(diretorio,true));
            
            escritor.append(meia.getId()+";");
            escritor.append(meia.getNome()+";");
            escritor.append(meia.getNumero()+";");
            escritor.append(meia.getPosicao()+";");
            escritor.append(meia.getTime()+";");
            escritor.append(meia.getEstado()+";");
            escritor.append(meia.getPasses()+";");
            escritor.append(meia.getPassesCertos()+";");
            escritor.append(meia.getLancamentos()+";");
            escritor.append(meia.getLancamentosCertos()+";");
            escritor.append(meia.getGol()+";");
            escritor.append(meia.getAssistencia()+";");
            escritor.append(meia.getNJogos()+";");
            if(meia.getPartidas().isEmpty()){
                escritor.append(" ");
            }else{
                for(int i = 0; i < meia.getPartidas().size(); i++){
                    escritor.append(meia.getPartidas().get(i)+"-");
                }    
            }
            escritor.append("\n");
            escritor.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public int verificaMeiaExiste(ArrayList<Meia> meias,String id){
        for(int i = 0; i < meias.size(); i ++){
            if(meias.get(i).getId().equals(id)){
                return 1;//Existe
            }
        }
        return 0;//Nao existe
    }
    public int verificaExistenciaPartida(Meia meia,String partida){
        for(int i = 0; i < meia.getPartidas().size(); i ++){
            if(meia.getPartidas().get(i).equals(partida)){
                return 1;
            }
        }
        return 0;
    }
}
