package dados;
import java.util.ArrayList;

public class Time {
    private String nome;
    private String estado;
    private int totalJogadores;
    private int quantidadeJogos;
    private int golsMarcados;
    private int golsSofridos;
    //Time a x Time b - Data(DD/MM/AAAA)
    public ArrayList<String> partidas = new ArrayList<>();

    public Time(String nome,String estado){
        this.nome = nome;
        this.estado = estado;
        this.totalJogadores = 0;
        this.quantidadeJogos = 0;
        this.golsMarcados = 0;
        this.golsSofridos = 0;
    }
    public String getNome(){
        return this.nome;
    }
    public String getEstado(){
        return this.estado;
    }
    public int getTotalJogadores(){
        return this.totalJogadores;
    }
    public int getQuantidadeJogos(){
        return this.quantidadeJogos;
    }
    public int getGolsMarcados(){
        return this.golsMarcados;
    }
    public int getGolsSofridos(){
        return this.golsSofridos;
    }
    public ArrayList<String> getPartidas(){
        return this.partidas;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    public void setTotalJogadores(int totalJogadores){
        this.totalJogadores += totalJogadores;
    }
    public void setQuantidadeJogos(int quantidadeJogos){
        this.quantidadeJogos += quantidadeJogos;
    }
    public void setGolsMarcados(int golsMarcados){
        this.golsMarcados += golsMarcados;
    }
    public void setGolsSofridos(int golsSofridos){
        this.golsSofridos += golsSofridos;
    }
    public void setPartidas(String partida){
        this.partidas.add(partida);
    }
}
