package dados;
public class Lateral extends Jogador{
    private int cruzamentos;
    private int cruzamentosCertos;
    public Lateral(String id,String nome,String numero,String posicao,String time,String estado){
        super(id,nome,numero,posicao,time,estado);
        this.cruzamentos = 0;
        this.cruzamentosCertos = 0;
    }
    public int getCruzamentos(){
        return this.cruzamentos;
    }
    public int getCruzamentosCertos(){
        return this.cruzamentosCertos;
    }
    public double getPorcentagemCruzamentosCertos(){
        double a = this.cruzamentosCertos;
        double b = this.cruzamentos;
        if(a == 0 || b == 0)
            return 0;
        return (100*(a / b));
    }
    public void setCruzamentos(int cruzamentos){
        this.cruzamentos += cruzamentos;
    }
    public void setCruzamentosCertos(int cruzamentosCertos){
        this.cruzamentosCertos += cruzamentosCertos;
    }
    @Override
    public double calcularNota(){
        double nota = getPorcentagemCruzamentosCertos() + super.getGol() + (super.getAssistencia() * 0.5);
        if(nota > 10)
            nota = 10;
        super.setNota(nota);
        return super.getNota();
    }
    
}
