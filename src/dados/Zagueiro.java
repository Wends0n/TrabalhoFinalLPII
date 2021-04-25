package dados;
public class Zagueiro extends Jogador{
    private int desarmes;
    private int faltas;
    private int falhasDefensivas;

    public Zagueiro(String id,String nome,String numero,String posicao,String time,String estado){
        super(id,nome,numero,posicao,time,estado);
        this.desarmes = 0;
        this.faltas = 0;
        this.falhasDefensivas = 0;
    }
    public int getDesarmes(){
        return this.desarmes;
    }
    public int getFaltas(){
        return this.faltas;
    }
    public int getFalhasDefensivas(){
        return this.falhasDefensivas;
    }
    public void setDesarmes(int desarmes){
        this.desarmes = desarmes;
    }
    public void setFaltas(int faltas){
        this.faltas = faltas;
    }
    public void setFalhasDefensivas(int falhasDefensivas){
        this.falhasDefensivas = falhasDefensivas;
    }
    @Override
    public double calcularNota(){
        double nota = 10 - ((getFaltas() * 0.5) + getFalhasDefensivas()) + getDesarmes() + super.getGol() + (super.getAssistencia() * 0.5);
        if(nota > 10)
            nota = 10;
        super.setNota(nota);
        return super.getNota();
    }
    
}
