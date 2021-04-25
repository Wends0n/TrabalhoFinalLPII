package dados;
public class Goleiro extends Jogador{
    private int golsSofridos;
    private int defesasDificeis;
    private int penaltis;
    private int penaltisDefendidos;
    public Goleiro(String id,String nome,String numero,String posicao,String time,String estado){
        super(id,nome,numero,posicao,time,estado);
        this.golsSofridos = 0;
        this.defesasDificeis = 0;
        this.penaltis = 0;
        this.penaltisDefendidos = 0;
    }
    public int getGolsSofridos(){
        return this.golsSofridos;
    }
    public int getDefesasDificeis(){
        return this.defesasDificeis;
    }
    public int getPenaltis(){
        return this.penaltis;
    }
    public int getPenaltisDefendidos(){
        return this.penaltisDefendidos;
    }
    public double getPorcentagemDefesasPenaltis(){
        double a = this.penaltisDefendidos;
        double b = this.penaltis;
        if(a == 0 || b == 0)
            return 0;
        return (100*(a / b)); 
    }
    
    public void setGolsSofridos(int golsSofridos){
        this.golsSofridos += golsSofridos;
    }
    public void setDefesasDificeis(int defesasDificeis){
        this.defesasDificeis += defesasDificeis;
    }
    public void setPenaltis(int penaltis){
        this.penaltis += penaltis;
    }
    public void setPenaltisDefendidos(int penaltisDefendidos){
        this.penaltisDefendidos += penaltisDefendidos;
    }
    @Override
    public double calcularNota(){
        double nota = (getPorcentagemDefesasPenaltis() + getDefesasDificeis() + super.getGol() + (super.getAssistencia() * 0.5)) - (getGolsSofridos() * 0.5);
        if(nota > 10)
            nota = 10;
        super.setNota(nota);
        return super.getNota();
    }
}
