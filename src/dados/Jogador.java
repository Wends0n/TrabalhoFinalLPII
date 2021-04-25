package dados;
import java.util.ArrayList;

public abstract class Jogador{
    private String id;
    private String nome;
    private String numero;
    private String posicao;
    private String time;
    private String estado;
    private int gol;
    private int assistencia;
    private ArrayList<String> partidas = new ArrayList<>();
    private double nota;
    private int nJogos;

    protected Jogador(String id,String nome,String numero,String posicao,String time,String estado){
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.posicao = posicao;
        this.time = time;
        this.estado = estado;
        this.gol = 0;
        this.assistencia = 0;
        this.nota = 0;
        this.nJogos = 0;
    }
    public String getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getNumero(){
        return this.numero;
    }
    public String getPosicao(){
        return this.posicao;
    }
    public String getTime(){
        return this.time;
    }
    public String getEstado(){
        return this.estado;
    }
    public int getGol(){
        return this.gol;
    }
    public int getAssistencia(){
        return this.assistencia;
    }
    public ArrayList<String> getPartidas(){
        return this.partidas;
    }
    public double getNota(){
        return this.nota;
    }
    public int getNJogos(){
        return this.nJogos;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setNumero(String numero){
        this.numero = numero;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    public void setGol(int gol){
        this.gol += gol;
    }
    public void setAssistencia(int assistencia){
        this.assistencia += assistencia;
    }
    public void setPartidas(String partida){
        this.partidas.add(partida);
    }
    public void setNota(double nota){
        this.nota = nota;
    }
    public void setNJogos(int nJogos){
        this.nJogos += nJogos;
    }
    public abstract double calcularNota();
}
