package dados;
public class Meia extends Jogador{
    private int passes;
    private int passesCertos;
    private int lancamentos;
    private int lancamentosCertos;

    public Meia(String id,String nome,String numero,String posicao,String time,String estado){
        super(id,nome,numero,posicao,time,estado);
        this.passes = 0;
        this.passesCertos = 0;
        this.lancamentos = 0;
        this.lancamentosCertos = 0;
    }
    public int getPasses(){
        return this.passes;
    }
    public int getPassesCertos(){
        return this.passesCertos;
    }
    public int getLancamentos(){
        return this.lancamentos;
    }
    public int getLancamentosCertos(){
        return this.lancamentosCertos;
    }
    public double getPorcentagemPassesCertos(){
        double a = this.passesCertos;
        double b = this.passes;
        if(a == 0 || b == 0){
            return 0;
        }
        return (100*(a / b));
    }
    public double getPorcentagemLancamentosCertos(){
        double a = this.lancamentosCertos;
        double b = this.lancamentos;
        if(a == 0 || b == 0){
            return 0;
        }
        return (100*(a / b));
    }
    public void setPasses(int passes){
        this.passes += passes;
    }
    public void setPassesCertos(int passesCertos){
        this.passesCertos += passesCertos;
    }
    public void setLancamentos(int lancamentos){
        this.lancamentos += lancamentos;
    }
    public void setLancamentosCertos(int lancamentosCertos){
        this.lancamentosCertos += lancamentosCertos;
    }
    @Override
    public double calcularNota(){
        double nota = (getPorcentagemLancamentosCertos() + getPorcentagemPassesCertos()) * 0.5 + super.getGol() + (super.getAssistencia() * 0.5);
        if(nota > 10)
            nota = 10;
        super.setNota(nota);
        return super.getNota();
    }
}