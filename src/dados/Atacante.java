package dados;
public class Atacante extends Jogador{
    private int finalizacoes;
    private int finalizacoesCertas;
    public Atacante(String id,String nome,String numero,String posicao,String time,String estado){
        super(id,nome,numero,posicao,time,estado);
        this.finalizacoes = 0;
        this.finalizacoesCertas = 0;
    }
    public int getFinalizacoes(){
        return this.finalizacoes;
    }
    public int getFinalizacoesCertas(){
        return this.finalizacoesCertas;
    }
    public double getPorcentagemFinalizacoesCertas(){
        double a = getFinalizacoesCertas();
        double b = getFinalizacoes();
        if(a == 0 || b == 0){
            return 0;
        }
        return (100*(a / b));
    }
    public void setFinalizacoes(int finalizacoes){
        this.finalizacoes += finalizacoes;
    }
    public void setFinalizacoesCertas(int finalizacoesCertas){
        this.finalizacoesCertas += finalizacoesCertas;
    }
    @Override
    public double calcularNota(){
        double nota = getPorcentagemFinalizacoesCertas() + super.getGol() + (super.getAssistencia()*0.5);
        if(nota > 10)
            nota = 10;
        super.setNota(nota);
        return super.getNota();
    }
}
