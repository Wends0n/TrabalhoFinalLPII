package servicos;
import java.util.ArrayList;

import dados.Atacante;
import dados.Meia;
import dados.Lateral;
import dados.Zagueiro;
import dados.Goleiro;

import dados.Time;
import dados.Html;
public class Sistema {
    private TimeService ts = new TimeService();
    private AtacanteService as = new AtacanteService();
    private MeiaService ms = new MeiaService();
    private LateralService ls = new LateralService();
    private ZagueiroService zs = new ZagueiroService();
    private GoleiroService gs = new GoleiroService();

    private ArrayList<Atacante> atacantes;
    private ArrayList<Meia> meias;
    private ArrayList<Time> times;
    private ArrayList<Lateral> laterais;
    private ArrayList<Zagueiro> zagueiros;
    private ArrayList<Goleiro> goleiros;

    private String diretorioTime = "src/bancoDeDados/times.txt";
    private String diretorioAtacante = "src/bancoDeDados/jogadores/atacante.txt";
    private String diretorioMeia = "src/bancoDeDados/jogadores/meia.txt";
    private String diretorioLateral = "src/bancoDeDados/jogadores/lateral.txt";
    private String diretorioZagueiro = "src/bancoDeDados/jogadores/zagueiro.txt";
    private String diretorioGoleiro = "src/bancoDeDados/jogadores/goleiro.txt";

    private void lerBancoDeDados(){
        this.times = ts.verificarTimesBD(this.diretorioTime);
        this.atacantes = as.verificarAtacantesBD(this.diretorioAtacante);
        this.meias = ms.verificarMeiasBD(this.diretorioMeia);
        this.laterais = ls.verificarLateraisBD(this.diretorioLateral);
        this.zagueiros = zs.verificarZagueirosBD(this.diretorioZagueiro);
        this.goleiros = gs.verificarGoleirosBD(this.diretorioGoleiro);
    }
    public int criarTime(String nome,String estado){
        lerBancoDeDados();
        if(ts.verificaTimeExiste(this.times, nome, estado) == 0){
            Time time = new Time(nome,estado);
            ts.adicionarTimeBD(time, diretorioTime);
            return 1;
        }
        return 0;
    }

    public int adicionarEstatisticaTime(String nome,String estado,String partida,int totalJogadores,int golsMarcados, int golsSofridos){
        lerBancoDeDados();
        if(ts.verificaTimeExiste(this.times, nome, estado) == 1){
            for(int i = 0; i < this.times.size(); i ++){
                if(this.times.get(i).getNome().equals(nome) && this.times.get(i).getEstado().equals(estado)){
                    this.times.get(i).setTotalJogadores(totalJogadores);
                    this.times.get(i).setGolsMarcados(golsMarcados);
                    if(ts.verificaExistenciaPartida(this.times.get(i), partida) == 0){
                        this.times.get(i).setPartidas(partida);
                        this.times.get(i).setQuantidadeJogos(1);
                    }else{
                        this.times.get(i).setQuantidadeJogos(0);
                    }
                    this.times.get(i).setGolsSofridos(golsSofridos);
                }
            }
            ts.adicionarEstatisticaTimeBD(this.times, diretorioTime);
            return 1;
        }
        return 0;
    }
    public int criarAtacante(String id,String nome,String numero,String posicao,String time,String estado){
        lerBancoDeDados();
        if(as.verificaAtacanteExiste(this.atacantes, id) == 0 && ts.verificaTimeExiste(this.times, time, estado) == 1){
            Atacante atacante = new Atacante(id, nome, numero, posicao, time, estado);
            as.adicionarAtacanteBD(atacante, this.diretorioAtacante);
            adicionarEstatisticaTime(time, estado," ", 1, 0, 0);
            return 1;
        }
        return 0;
    }
    public int criarMeia(String id,String nome,String numero,String posicao,String time,String estado){
        lerBancoDeDados();
        if(ms.verificaMeiaExiste(this.meias, id) == 0 && ts.verificaTimeExiste(this.times, time, estado) == 1){
            Meia meia = new Meia(id, nome, numero, posicao, time, estado);
            ms.adicionarMeiaBD(meia, this.diretorioMeia);
            adicionarEstatisticaTime(time, estado," ", 1, 0, 0);
            return 1;
        }
        return 0;
    }
    public int criarLateral(String id,String nome,String numero,String posicao,String time,String estado){
        lerBancoDeDados();
        if(ls.verificaLateralExiste(this.laterais, id) == 0 && ts.verificaTimeExiste(this.times, time, estado) == 1){
            Lateral lateral = new Lateral(id, nome, numero, posicao, time, estado);
            ls.adicionarLateralBD(lateral, this.diretorioLateral);
            adicionarEstatisticaTime(time, estado," ", 1, 0, 0);
            return 1;
        }
        return 0;
    }
    public int criarZagueiro(String id,String nome,String numero,String posicao,String time,String estado){
        lerBancoDeDados();
        if(zs.verificaZagueiroExiste(this.zagueiros, id) == 0 && ts.verificaTimeExiste(this.times, time, estado) == 1){
            Zagueiro zagueiro = new Zagueiro(id, nome, numero, posicao, time, estado);
            zs.adicionarZagueiroBD(zagueiro, this.diretorioZagueiro);
            adicionarEstatisticaTime(time, estado," ", 1, 0, 0);
            return 1;
        }
        return 0;
    }
    public int criarGoleiro(String id,String nome,String numero,String posicao,String time,String estado){
        lerBancoDeDados();
        if(gs.verificaGoleiroExiste(this.goleiros, id) == 0 && ts.verificaTimeExiste(this.times, time, estado) == 1){
            Goleiro goleiro = new Goleiro(id, nome, numero, posicao, time, estado);
            gs.adicionarGoleiroBD(goleiro, this.diretorioGoleiro);
            adicionarEstatisticaTime(time, estado," ", 1, 0, 0);
            return 1;
        }
        return 0;
    }
    public int adicionarEstatisticaAtacante(String id,String posicao,String partida,int finalizacoes,int finalizacoesCertas, int gol, int assistencia){
        int aux = 0;
        lerBancoDeDados();
        if(as.verificaAtacanteExiste(this.atacantes, id) == 1){
            for(int i = 0; i < this.atacantes.size(); i++){
                if(this.atacantes.get(i).getId().equals(id)){
                    if(as.verificaExistenciaPartida(this.atacantes.get(i), partida) == 0){
                        this.atacantes.get(i).setFinalizacoes(finalizacoes);
                        this.atacantes.get(i).setFinalizacoesCertas(finalizacoesCertas);
                        this.atacantes.get(i).setGol(gol);
                        this.atacantes.get(i).setAssistencia(assistencia);
                        this.atacantes.get(i).setNJogos(1);
                        this.atacantes.get(i).setPartidas(partida);
                        aux = i;
                        break;
                    }else{
                        return 0;//essa partida ja foi cadastrada
                    }
                }
            }
            as.adicionarEstatisticaAtacantesBD(this.atacantes, this.diretorioAtacante);
            adicionarEstatisticaTime(this.atacantes.get(aux).getTime(),this.atacantes.get(aux).getEstado(),partida, 0,gol,0);
            return 1;//adicionado com sucesso
        }
        return 0;//o id informado nao foi encontrado
    }
    public int adicionarEstatisticaMeia(String id,String posicao,String partida,int passes,int passesCertos,int lancamentos,int lancamentosCertos, int gol, int assistencia){
        int aux = 0;
        lerBancoDeDados();
        if(ms.verificaMeiaExiste(this.meias, id) == 1){
            for(int i = 0; i < this.meias.size(); i++){
                if(this.meias.get(i).getId().equals(id)){
                    if(ms.verificaExistenciaPartida(this.meias.get(i), partida) == 0){
                        this.meias.get(i).setPasses(passes);
                        this.meias.get(i).setPassesCertos(passesCertos);
                        this.meias.get(i).setLancamentos(lancamentos);
                        this.meias.get(i).setLancamentosCertos(lancamentosCertos);
                        this.meias.get(i).setGol(gol);
                        this.meias.get(i).setAssistencia(assistencia);
                        this.meias.get(i).setNJogos(1);
                        this.meias.get(i).setPartidas(partida);
                        aux = i;
                        break;
                    }else{
                        return 0;//essa partida ja foi cadastrada
                    }
                }
            }
            ms.adicionarEstatisticaMeiasBD(this.meias, this.diretorioMeia);
            adicionarEstatisticaTime(this.meias.get(aux).getTime(),this.meias.get(aux).getEstado(),partida, 0,gol,0);
            return 1;//adicionado com sucesso
        }
        return 0;//o id informado nao foi encontrado
    }
    public int adicionarEstatisticaLateral(String id,String posicao,String partida,int cruzamentos,int cruzamentosCertos, int gol, int assistencia){
        int aux = 0;
        lerBancoDeDados();
        if(ls.verificaLateralExiste(this.laterais, id) == 1){
            for(int i = 0; i < this.laterais.size(); i++){
                if(this.laterais.get(i).getId().equals(id)){
                    if(ls.verificaExistenciaPartida(this.laterais.get(i), partida) == 0){
                        this.laterais.get(i).setCruzamentos(cruzamentos);
                        this.laterais.get(i).setCruzamentosCertos(cruzamentosCertos);
                        this.laterais.get(i).setGol(gol);
                        this.laterais.get(i).setAssistencia(assistencia);
                        this.laterais.get(i).setNJogos(1);
                        this.laterais.get(i).setPartidas(partida);
                        aux = i;
                        break;
                    }else{
                        return 0;//essa partida ja foi cadastrada
                    }
                }
            }
            ls.adicionarEstatisticaLateraisBD(this.laterais, this.diretorioLateral);
            adicionarEstatisticaTime(this.laterais.get(aux).getTime(),this.laterais.get(aux).getEstado(),partida, 0,gol,0);
            return 1;//adicionado com sucesso
        }
        return 0;//o id informado nao foi encontrado
    }
    public int adicionarEstatisticaZagueiro(String id,String posicao,String partida,int desarmes,int faltas,int falhasDefensivas, int gol, int assistencia){
        int aux = 0;
        lerBancoDeDados();
        if(zs.verificaZagueiroExiste(this.zagueiros, id) == 1){
            for(int i = 0; i < this.zagueiros.size(); i++){
                if(this.zagueiros.get(i).getId().equals(id)){
                    if(zs.verificaExistenciaPartida(this.zagueiros.get(i), partida) == 0){
                        this.zagueiros.get(i).setDesarmes(desarmes);
                        this.zagueiros.get(i).setFaltas(faltas);
                        this.zagueiros.get(i).setFalhasDefensivas(falhasDefensivas);
                        this.zagueiros.get(i).setGol(gol);
                        this.zagueiros.get(i).setAssistencia(assistencia);
                        this.zagueiros.get(i).setNJogos(1);
                        this.zagueiros.get(i).setPartidas(partida);
                        aux = i;
                        break;
                    }else{
                        return 0;//essa partida ja foi cadastrada
                    }
                }
            }
            zs.adicionarEstatisticaZagueirosBD(this.zagueiros, this.diretorioZagueiro);
            adicionarEstatisticaTime(this.zagueiros.get(aux).getTime(),this.zagueiros.get(aux).getEstado(),partida, 0,gol,0);
            return 1;//adicionado com sucesso
        }
        return 0;//o id informado nao foi encontrado
    }
    public int adicionarEstatisticaGoleiro(String id,String posicao,String partida,int golsSofridos,int defesasDificeis,int penaltis,int penaltisDefendidos, int gol, int assistencia){
        int aux = 0;
        lerBancoDeDados();
        if(gs.verificaGoleiroExiste(this.goleiros, id) == 1){
            for(int i = 0; i < this.goleiros.size(); i++){
                if(this.goleiros.get(i).getId().equals(id)){
                    if(gs.verificaExistenciaPartida(this.goleiros.get(i), partida) == 0){
                        this.goleiros.get(i).setGolsSofridos(golsSofridos);
                        this.goleiros.get(i).setDefesasDificeis(defesasDificeis);
                        this.goleiros.get(i).setPenaltis(penaltis);
                        this.goleiros.get(i).setPenaltisDefendidos(penaltisDefendidos);
                        this.goleiros.get(i).setGol(gol);
                        this.goleiros.get(i).setAssistencia(assistencia);
                        this.goleiros.get(i).setNJogos(1);
                        this.goleiros.get(i).setPartidas(partida);
                        aux = i;
                        break;
                    }else{
                        return 0;//essa partida ja foi cadastrada
                    }
                }
            }
            gs.adicionarEstatisticaGoleirosBD(this.goleiros, this.diretorioGoleiro);
            adicionarEstatisticaTime(this.goleiros.get(aux).getTime(),this.goleiros.get(aux).getEstado(),partida, 0,gol,this.goleiros.get(aux).getGolsSofridos());
            return 1;//adicionado com sucesso
        }
        return 0;//o id informado nao foi encontrado
    }
    public int removerTime(String nome,String estado){
        lerBancoDeDados();
        if(ts.verificaTimeExiste(this.times, nome, estado) == 0){
            return 0;//time informado nao existe
        }else{
            for(int i = 0; i < this.times.size(); i ++){
                if(this.times.get(i).getNome().equals(nome) && this.times.get(i).getEstado().equals(estado)){
                    this.times.remove(i);
                    break;
                }
            }
            ts.adicionarEstatisticaTimeBD(this.times, this.diretorioTime);
        }
        return 1;
    }
    public int removerAtacante(String id){
        lerBancoDeDados();
        if(as.verificaAtacanteExiste(this.atacantes, id) == 0){
            return 0;//atacante informado nao existe
        }else{
            for(int i = 0; i < this.atacantes.size(); i ++){
                if(this.atacantes.get(i).getId().equals(id)){
                    this.atacantes.remove(i);
                    break;
                }
            }
            as.adicionarEstatisticaAtacantesBD(this.atacantes, this.diretorioAtacante);
        }
        return 1;
    }
    public int removerMeia(String id){
        lerBancoDeDados();
        if(ms.verificaMeiaExiste(this.meias, id) == 0){
            return 0;//meia informado nao existe
        }else{
            for(int i = 0; i < this.meias.size(); i ++){
                if(this.meias.get(i).getId().equals(id)){
                    this.meias.remove(i);
                    break;
                }
            }
            ms.adicionarEstatisticaMeiasBD(this.meias, this.diretorioMeia);
        }
        return 1;
    }
    public int removerLateral(String id){
        lerBancoDeDados();
        if(ls.verificaLateralExiste(this.laterais, id) == 0){
            return 0;//lateral informado nao existe
        }else{
            for(int i = 0; i < this.laterais.size(); i ++){
                if(this.laterais.get(i).getId().equals(id)){
                    this.laterais.remove(i);
                    break;
                }
            }
            ls.adicionarEstatisticaLateraisBD(this.laterais, this.diretorioLateral);
        }
        return 1;
    }
    public int removerZagueiro(String id){
        lerBancoDeDados();
        if(zs.verificaZagueiroExiste(this.zagueiros, id) == 0){
            return 0;//zagueiro informado nao existe
        }else{
            for(int i = 0; i < this.zagueiros.size(); i ++){
                if(this.zagueiros.get(i).getId().equals(id)){
                    this.zagueiros.remove(i);
                    break;
                }
            }
            zs.adicionarEstatisticaZagueirosBD(this.zagueiros, this.diretorioZagueiro);
        }
        return 1;
    }
    public int removerGoleiro(String id){
        lerBancoDeDados();
        if(gs.verificaGoleiroExiste(this.goleiros, id) == 0){
            return 0;//goleiro informado nao existe
        }else{
            for(int i = 0; i < this.goleiros.size(); i ++){
                if(this.goleiros.get(i).getId().equals(id)){
                    this.goleiros.remove(i);
                    break;
                }
            }
            gs.adicionarEstatisticaGoleirosBD(this.goleiros, this.diretorioGoleiro);
        }
        return 1;
    }
    public int compararJogadores(String idA, String posicaoA, String idB, String posicaoB){
        String diretorio = "src/" + idA + " x "+ idB +".html";
        String conteudo;
        int aux1 = 0,aux2 = 0;
        Html arquivoHtml = new Html();
        
        lerBancoDeDados();
        
        arquivoHtml.gerarHtml("jogador");
        conteudo = arquivoHtml.getConteudoArquivo();

        if(posicaoA.equals("atacante") && as.verificaAtacanteExiste(this.atacantes, idA) == 1){
            for(int i = 0; i < this.atacantes.size(); i ++){
                if(this.atacantes.get(i).getId().equals(idA)){
                    conteudo = conteudo.replaceAll("JogadorA", this.atacantes.get(i).getNome());
                    conteudo = conteudo.replaceAll("idA","ID: " + this.atacantes.get(i).getId());
                    conteudo = conteudo.replaceAll("NomeA","Nome: " + this.atacantes.get(i).getNome());
                    conteudo = conteudo.replaceAll("NumeroA","Numero: " + this.atacantes.get(i).getNumero());
                    conteudo = conteudo.replaceAll("PosicaoA","Posicao: " + this.atacantes.get(i).getPosicao());
                    conteudo = conteudo.replaceAll("TimeA","Time: " + this.atacantes.get(i).getTime());
                    conteudo = conteudo.replaceAll("EstadoA","Estado: " + this.atacantes.get(i).getEstado());
                    conteudo = conteudo.replaceAll("InformacaoA1","Finalizacoes: " + this.atacantes.get(i).getFinalizacoes());
                    conteudo = conteudo.replaceAll("InformacaoA2","Finalizacoes Certas: " + this.atacantes.get(i).getFinalizacoesCertas());
                    conteudo = conteudo.replaceAll("InformacaoA3","Porcentagem finalizacoes certas: " + this.atacantes.get(i).getPorcentagemFinalizacoesCertas());
                    conteudo = conteudo.replaceAll("InformacaoA4","");
                    conteudo = conteudo.replaceAll("InformacaoA5","");
                    conteudo = conteudo.replaceAll("InformacaoA6","");
                    conteudo = conteudo.replaceAll("GolA","Gols: " + this.atacantes.get(i).getGol());
                    conteudo = conteudo.replaceAll("AssistenciaA","Assistencia: " + this.atacantes.get(i).getAssistencia());
                    conteudo = conteudo.replaceAll("NJogosA","Numero Jogos: " + this.atacantes.get(i).getNJogos());
                    conteudo = conteudo.replaceAll("NotaA","Nota: " + this.atacantes.get(i).calcularNota());
                    aux1 ++;
                }
            }
        }else if(posicaoA.equals("meia") && ms.verificaMeiaExiste(this.meias, idA) == 1){
            for(int i = 0; i < this.meias.size(); i ++){
                if(this.meias.get(i).getId().equals(idA)){
                    conteudo = conteudo.replaceAll("JogadorA", this.meias.get(i).getNome());
                    conteudo = conteudo.replaceAll("idA","ID: " + this.meias.get(i).getId());
                    conteudo = conteudo.replaceAll("NomeA","Nome: " + this.meias.get(i).getNome());
                    conteudo = conteudo.replaceAll("NumeroA","Numero: " + this.meias.get(i).getNumero());
                    conteudo = conteudo.replaceAll("PosicaoA","Posicao: " + this.meias.get(i).getPosicao());
                    conteudo = conteudo.replaceAll("TimeA","Time: " + this.meias.get(i).getTime());
                    conteudo = conteudo.replaceAll("EstadoA","Estado: " + this.meias.get(i).getEstado());
                    conteudo = conteudo.replaceAll("InformacaoA1","Passes: " + this.meias.get(i).getPasses());
                    conteudo = conteudo.replaceAll("InformacaoA2","Passes Certos: " + this.meias.get(i).getPassesCertos());
                    conteudo = conteudo.replaceAll("InformacaoA3","Porcentagem passes certos: " + this.meias.get(i).getPorcentagemPassesCertos());
                    conteudo = conteudo.replaceAll("InformacaoA4","Lancamentos: " + this.meias.get(i).getLancamentos());
                    conteudo = conteudo.replaceAll("InformacaoA5","Lancamentos certos: " + this.meias.get(i).getLancamentosCertos());
                    conteudo = conteudo.replaceAll("InformacaoA6","Porcentagem Lancamentos certos: " + this.meias.get(i).getPorcentagemLancamentosCertos());
                    conteudo = conteudo.replaceAll("GolA","Gols: " + this.meias.get(i).getGol());
                    conteudo = conteudo.replaceAll("AssistenciaA","Assistencia: " + this.meias.get(i).getAssistencia());
                    conteudo = conteudo.replaceAll("NJogosA","Numero Jogos: " + this.meias.get(i).getNJogos());
                    conteudo = conteudo.replaceAll("NotaA","Nota: " + this.meias.get(i).calcularNota());
                    aux1++;
                }
            }
        }else if(posicaoA.equals("lateral") && ls.verificaLateralExiste(this.laterais, idA) == 1){
            for(int i = 0; i < this.laterais.size(); i ++){
                if(this.laterais.get(i).getId().equals(idA)){
                    conteudo = conteudo.replaceAll("JogadorA", this.laterais.get(i).getNome());
                    conteudo = conteudo.replaceAll("idA","ID: " + this.laterais.get(i).getId());
                    conteudo = conteudo.replaceAll("NomeA","Nome: " + this.laterais.get(i).getNome());
                    conteudo = conteudo.replaceAll("NumeroA","Numero: " + this.laterais.get(i).getNumero());
                    conteudo = conteudo.replaceAll("PosicaoA","Posicao: " + this.laterais.get(i).getPosicao());
                    conteudo = conteudo.replaceAll("TimeA","Time: " + this.laterais.get(i).getTime());
                    conteudo = conteudo.replaceAll("EstadoA","Estado: " + this.laterais.get(i).getEstado());
                    conteudo = conteudo.replaceAll("InformacaoA1","Cruzamentos: " + this.laterais.get(i).getCruzamentos());
                    conteudo = conteudo.replaceAll("InformacaoA2","Cruzamentos Certos: " + this.laterais.get(i).getCruzamentosCertos());
                    conteudo = conteudo.replaceAll("InformacaoA3","Porcentagem cruzamentos certos: " + this.laterais.get(i).getPorcentagemCruzamentosCertos());
                    conteudo = conteudo.replaceAll("InformacaoA4","");
                    conteudo = conteudo.replaceAll("InformacaoA5","");
                    conteudo = conteudo.replaceAll("InformacaoA6","");
                    conteudo = conteudo.replaceAll("GolA","Gols: " + this.laterais.get(i).getGol());
                    conteudo = conteudo.replaceAll("AssistenciaA","Assistencia: " + this.laterais.get(i).getAssistencia());
                    conteudo = conteudo.replaceAll("NJogosA","Numero Jogos: " + this.laterais.get(i).getNJogos());
                    conteudo = conteudo.replaceAll("NotaA","Nota: " + this.laterais.get(i).calcularNota());
                    aux1++;
                }
            }
        }else if(posicaoA.equals("zagueiro") && zs.verificaZagueiroExiste(this.zagueiros, idA) == 1){
            for(int i = 0; i < this.zagueiros.size(); i ++){
                if(this.zagueiros.get(i).getId().equals(idA)){
                    conteudo = conteudo.replaceAll("JogadorA", this.zagueiros.get(i).getNome());
                    conteudo = conteudo.replaceAll("idA","ID: " + this.zagueiros.get(i).getId());
                    conteudo = conteudo.replaceAll("NomeA","Nome: " + this.zagueiros.get(i).getNome());
                    conteudo = conteudo.replaceAll("NumeroA","Numero: " + this.zagueiros.get(i).getNumero());
                    conteudo = conteudo.replaceAll("PosicaoA","Posicao: " + this.zagueiros.get(i).getPosicao());
                    conteudo = conteudo.replaceAll("TimeA","Time: " + this.zagueiros.get(i).getTime());
                    conteudo = conteudo.replaceAll("EstadoA","Estado: " + this.zagueiros.get(i).getEstado());
                    conteudo = conteudo.replaceAll("InformacaoA1","Desarmes: " + this.zagueiros.get(i).getDesarmes());
                    conteudo = conteudo.replaceAll("InformacaoA2","Faltas: " + this.zagueiros.get(i).getFaltas());
                    conteudo = conteudo.replaceAll("InformacaoA3","Falhas Defensivas: " + this.zagueiros.get(i).getFalhasDefensivas());
                    conteudo = conteudo.replaceAll("InformacaoA4","");
                    conteudo = conteudo.replaceAll("InformacaoA5","");
                    conteudo = conteudo.replaceAll("InformacaoA6","");
                    conteudo = conteudo.replaceAll("GolA","Gols: " + this.zagueiros.get(i).getGol());
                    conteudo = conteudo.replaceAll("AssistenciaA","Assistencia: " + this.zagueiros.get(i).getAssistencia());
                    conteudo = conteudo.replaceAll("NJogosA","Numero Jogos: " + this.zagueiros.get(i).getNJogos());
                    conteudo = conteudo.replaceAll("NotaA","Nota: " + this.zagueiros.get(i).calcularNota());
                    aux1++;
                }
            }
        }else if(posicaoA.equals("goleiro") && gs.verificaGoleiroExiste(this.goleiros, idA) == 1){
            for(int i = 0; i < this.goleiros.size(); i ++){
                if(this.goleiros.get(i).getId().equals(idA)){
                    conteudo = conteudo.replaceAll("JogadorA", this.goleiros.get(i).getNome());
                    conteudo = conteudo.replaceAll("idA","ID: " + this.goleiros.get(i).getId());
                    conteudo = conteudo.replaceAll("NomeA","Nome: " + this.goleiros.get(i).getNome());
                    conteudo = conteudo.replaceAll("NumeroA","Numero: " + this.goleiros.get(i).getNumero());
                    conteudo = conteudo.replaceAll("PosicaoA","Posicao: " + this.goleiros.get(i).getPosicao());
                    conteudo = conteudo.replaceAll("TimeA","Time: " + this.goleiros.get(i).getTime());
                    conteudo = conteudo.replaceAll("EstadoA","Estado: " + this.goleiros.get(i).getEstado());
                    conteudo = conteudo.replaceAll("InformacaoA1","Gols Sofridos: " + this.goleiros.get(i).getGolsSofridos());
                    conteudo = conteudo.replaceAll("InformacaoA2","Defesas Dificeis: " + this.goleiros.get(i).getDefesasDificeis());
                    conteudo = conteudo.replaceAll("InformacaoA3","Penaltis: " + this.goleiros.get(i).getPenaltis());
                    conteudo = conteudo.replaceAll("InformacaoA4","Penaltis Defendidos: " + this.goleiros.get(i).getPenaltisDefendidos());
                    conteudo = conteudo.replaceAll("InformacaoA5","Porcentagem penaltis defendidos: " + this.goleiros.get(i).getPorcentagemDefesasPenaltis());
                    conteudo = conteudo.replaceAll("InformacaoA6","");
                    conteudo = conteudo.replaceAll("GolA","Gols: " + this.goleiros.get(i).getGol());
                    conteudo = conteudo.replaceAll("AssistenciaA","Assistencia: " + this.goleiros.get(i).getAssistencia());
                    conteudo = conteudo.replaceAll("NJogosA","Numero Jogos: " + this.goleiros.get(i).getNJogos());
                    conteudo = conteudo.replaceAll("NotaA","Nota: " + this.goleiros.get(i).calcularNota());
                    aux1++;
                }
            }
        }
        //--------------------------------------------------------------------------------------
        if(posicaoB.equals("atacante") && as.verificaAtacanteExiste(this.atacantes, idB) == 1){
            for(int i = 0; i < this.atacantes.size(); i ++){
                if(this.atacantes.get(i).getId().equals(idB)){
                    conteudo = conteudo.replaceAll("JogadorB", this.atacantes.get(i).getNome());
                    conteudo = conteudo.replaceAll("idB","ID: " + this.atacantes.get(i).getId());
                    conteudo = conteudo.replaceAll("NomeB","Nome: " + this.atacantes.get(i).getNome());
                    conteudo = conteudo.replaceAll("NumeroB","Numero: " + this.atacantes.get(i).getNumero());
                    conteudo = conteudo.replaceAll("PosicaoB","Posicao: " + this.atacantes.get(i).getPosicao());
                    conteudo = conteudo.replaceAll("TimeB","Time: " + this.atacantes.get(i).getTime());
                    conteudo = conteudo.replaceAll("EstadoB","Estado: " + this.atacantes.get(i).getEstado());
                    conteudo = conteudo.replaceAll("InformacaoB1","Finalizacoes: " + this.atacantes.get(i).getFinalizacoes());
                    conteudo = conteudo.replaceAll("InformacaoB2","Finalizacoes Certas: " + this.atacantes.get(i).getFinalizacoesCertas());
                    conteudo = conteudo.replaceAll("InformacaoB3","Porcentagem finalizacoes certas: " + this.atacantes.get(i).getPorcentagemFinalizacoesCertas());
                    conteudo = conteudo.replaceAll("InformacaoB4","");
                    conteudo = conteudo.replaceAll("InformacaoB5","");
                    conteudo = conteudo.replaceAll("InformacaoB6","");
                    conteudo = conteudo.replaceAll("GolB","Gols: " + this.atacantes.get(i).getGol());
                    conteudo = conteudo.replaceAll("AssistenciaB","Assistencia: " + this.atacantes.get(i).getAssistencia());
                    conteudo = conteudo.replaceAll("NJogosB","Numero Jogos: " + this.atacantes.get(i).getNJogos());
                    conteudo = conteudo.replaceAll("NotaB","Nota: " + this.atacantes.get(i).calcularNota());
                    aux2++;
                }
            }
        }else if(posicaoB.equals("meia") && ms.verificaMeiaExiste(this.meias, idB) == 1){
            for(int i = 0; i < this.meias.size(); i ++){
                if(this.meias.get(i).getId().equals(idB)){
                    conteudo = conteudo.replaceAll("JogadorB", this.meias.get(i).getNome());
                    conteudo = conteudo.replaceAll("idB","ID: " + this.meias.get(i).getId());
                    conteudo = conteudo.replaceAll("NomeB","Nome: " + this.meias.get(i).getNome());
                    conteudo = conteudo.replaceAll("NumeroB","Numero: " + this.meias.get(i).getNumero());
                    conteudo = conteudo.replaceAll("PosicaoB","Posicao: " + this.meias.get(i).getPosicao());
                    conteudo = conteudo.replaceAll("TimeB","Time: " + this.meias.get(i).getTime());
                    conteudo = conteudo.replaceAll("EstadoB","Estado: " + this.meias.get(i).getEstado());
                    conteudo = conteudo.replaceAll("InformacaoB1","Passes: " + this.meias.get(i).getPasses());
                    conteudo = conteudo.replaceAll("InformacaoB2","Passes Certos: " + this.meias.get(i).getPassesCertos());
                    conteudo = conteudo.replaceAll("InformacaoB3","Porcentagem passes certos: " + this.meias.get(i).getPorcentagemPassesCertos());
                    conteudo = conteudo.replaceAll("InformacaoB4","Lancamentos: " + this.meias.get(i).getLancamentos());
                    conteudo = conteudo.replaceAll("InformacaoB5","Lancamentos certos: " + this.meias.get(i).getLancamentosCertos());
                    conteudo = conteudo.replaceAll("InformacaoB6","Porcentagem Lancamentos certos: " + this.meias.get(i).getPorcentagemLancamentosCertos());
                    conteudo = conteudo.replaceAll("GolB","Gols: " + this.meias.get(i).getGol());
                    conteudo = conteudo.replaceAll("AssistenciaB","Assistencia: " + this.meias.get(i).getAssistencia());
                    conteudo = conteudo.replaceAll("NJogosB","Numero Jogos: " + this.meias.get(i).getNJogos());
                    conteudo = conteudo.replaceAll("NotaB","Nota: " + this.meias.get(i).calcularNota());
                    aux2++;
                }
            }
        }else if(posicaoB.equals("lateral") && ls.verificaLateralExiste(this.laterais, idB) == 1){
            for(int i = 0; i < this.laterais.size(); i ++){
                if(this.laterais.get(i).getId().equals(idB)){
                    conteudo = conteudo.replaceAll("JogadorB", this.laterais.get(i).getNome());
                    conteudo = conteudo.replaceAll("idB","ID: " + this.laterais.get(i).getId());
                    conteudo = conteudo.replaceAll("NomeB","Nome: " + this.laterais.get(i).getNome());
                    conteudo = conteudo.replaceAll("NumeroB","Numero: " + this.laterais.get(i).getNumero());
                    conteudo = conteudo.replaceAll("PosicaoB","Posicao: " + this.laterais.get(i).getPosicao());
                    conteudo = conteudo.replaceAll("TimeB","Time: " + this.laterais.get(i).getTime());
                    conteudo = conteudo.replaceAll("EstadoB","Estado: " + this.laterais.get(i).getEstado());
                    conteudo = conteudo.replaceAll("InformacaoB1","Cruzamentos: " + this.laterais.get(i).getCruzamentos());
                    conteudo = conteudo.replaceAll("InformacaoB2","Cruzamentos Certos: " + this.laterais.get(i).getCruzamentosCertos());
                    conteudo = conteudo.replaceAll("InformacaoB3","Porcentagem cruzamentos certos: " + this.laterais.get(i).getPorcentagemCruzamentosCertos());
                    conteudo = conteudo.replaceAll("InformacaoB4","");
                    conteudo = conteudo.replaceAll("InformacaoB5","");
                    conteudo = conteudo.replaceAll("InformacaoB6","");
                    conteudo = conteudo.replaceAll("GolB","Gols: " + this.laterais.get(i).getGol());
                    conteudo = conteudo.replaceAll("AssistenciaB","Assistencia: " + this.laterais.get(i).getAssistencia());
                    conteudo = conteudo.replaceAll("NJogosB","Numero Jogos: " + this.laterais.get(i).getNJogos());
                    conteudo = conteudo.replaceAll("NotaB","Nota: " + this.laterais.get(i).calcularNota());
                    aux2++;
                }
            }
        }else if(posicaoB.equals("zagueiro") && zs.verificaZagueiroExiste(this.zagueiros, idB) == 1){
            for(int i = 0; i < this.zagueiros.size(); i ++){
                if(this.zagueiros.get(i).getId().equals(idB)){
                    conteudo = conteudo.replaceAll("JogadorB", this.zagueiros.get(i).getNome());
                    conteudo = conteudo.replaceAll("idB","ID: " + this.zagueiros.get(i).getId());
                    conteudo = conteudo.replaceAll("NomeB","Nome: " + this.zagueiros.get(i).getNome());
                    conteudo = conteudo.replaceAll("NumeroB","Numero: " + this.zagueiros.get(i).getNumero());
                    conteudo = conteudo.replaceAll("PosicaoB","Posicao: " + this.zagueiros.get(i).getPosicao());
                    conteudo = conteudo.replaceAll("TimeB","Time: " + this.zagueiros.get(i).getTime());
                    conteudo = conteudo.replaceAll("EstadoB","Estado: " + this.zagueiros.get(i).getEstado());
                    conteudo = conteudo.replaceAll("InformacaoB1","Desarmes: " + this.zagueiros.get(i).getDesarmes());
                    conteudo = conteudo.replaceAll("InformacaoB2","Faltas: " + this.zagueiros.get(i).getFaltas());
                    conteudo = conteudo.replaceAll("InformacaoB3","Falhas Defensivas: " + this.zagueiros.get(i).getFalhasDefensivas());
                    conteudo = conteudo.replaceAll("InformacaoB4","");
                    conteudo = conteudo.replaceAll("InformacaoB5","");
                    conteudo = conteudo.replaceAll("InformacaoB6","");
                    conteudo = conteudo.replaceAll("GolB","Gols: " + this.zagueiros.get(i).getGol());
                    conteudo = conteudo.replaceAll("AssistenciaB","Assistencia: " + this.zagueiros.get(i).getAssistencia());
                    conteudo = conteudo.replaceAll("NJogosB","Numero Jogos: " + this.zagueiros.get(i).getNJogos());
                    conteudo = conteudo.replaceAll("NotaB","Nota: " + this.zagueiros.get(i).calcularNota());
                    aux2++;
                }
            }
        }else if(posicaoB.equals("goleiro") && gs.verificaGoleiroExiste(this.goleiros, idB) == 1){
            for(int i = 0; i < this.goleiros.size(); i ++){
                if(this.goleiros.get(i).getId().equals(idB)){
                    conteudo = conteudo.replaceAll("JogadorB", this.goleiros.get(i).getNome());
                    conteudo = conteudo.replaceAll("idB","ID: " + this.goleiros.get(i).getId());
                    conteudo = conteudo.replaceAll("NomeB","Nome: " + this.goleiros.get(i).getNome());
                    conteudo = conteudo.replaceAll("NumeroB","Numero: " + this.goleiros.get(i).getNumero());
                    conteudo = conteudo.replaceAll("PosicaoB","Posicao: " + this.goleiros.get(i).getPosicao());
                    conteudo = conteudo.replaceAll("TimeB","Time: " + this.goleiros.get(i).getTime());
                    conteudo = conteudo.replaceAll("EstadoB","Estado: " + this.goleiros.get(i).getEstado());
                    conteudo = conteudo.replaceAll("InformacaoB1","Gols Sofridos: " + this.goleiros.get(i).getGolsSofridos());
                    conteudo = conteudo.replaceAll("InformacaoB2","Defesas Dificeis: " + this.goleiros.get(i).getDefesasDificeis());
                    conteudo = conteudo.replaceAll("InformacaoB3","Penaltis: " + this.goleiros.get(i).getPenaltis());
                    conteudo = conteudo.replaceAll("InformacaoB4","Penaltis Defendidos: " + this.goleiros.get(i).getPenaltisDefendidos());
                    conteudo = conteudo.replaceAll("InformacaoB5","Porcentagem penaltis defendidos: " + this.goleiros.get(i).getPorcentagemDefesasPenaltis());
                    conteudo = conteudo.replaceAll("InformacaoB6","");
                    conteudo = conteudo.replaceAll("GolB","Gols: " + this.goleiros.get(i).getGol());
                    conteudo = conteudo.replaceAll("AssistenciaB","Assistencia: " + this.goleiros.get(i).getAssistencia());
                    conteudo = conteudo.replaceAll("NJogosB","Numero Jogos: " + this.goleiros.get(i).getNJogos());
                    conteudo = conteudo.replaceAll("NotaB","Nota: " + this.goleiros.get(i).calcularNota());
                    aux2++;
                }
            }
        }
        if(aux1 != 0 && aux2 != 0){
            arquivoHtml.reescreverHtml(conteudo, diretorio);
            System.out.println("Arquivo html criado comparando os dois jogadores");
        }else{
            System.out.println("Erro, verifique se os ids e posicoes informados estao corretos!");
        }
        return 0;
    }
    public int compararTimes(String timeA,String estadoA, String timeB,String estadoB){
        String diretorio = "src/" + timeA + " x "+ timeB +".html";
        String conteudo;
        int aux1 = 0,aux2 = 0;
        Html arquivoHtml = new Html();
        
        lerBancoDeDados();
        
        arquivoHtml.gerarHtml("time");
        conteudo = arquivoHtml.getConteudoArquivo();

        if(ts.verificaTimeExiste(this.times, timeA, estadoA) == 1 ){
            for(int i = 0; i < this.times.size(); i ++){
                if(this.times.get(i).getNome().equals(timeA) && this.times.get(i).getEstado().equals(estadoA)){
                    conteudo = conteudo.replaceAll("TIME A",this.times.get(i).getNome());
                    conteudo = conteudo.replaceAll("TimeA","Nome: " + this.times.get(i).getNome());
                    conteudo = conteudo.replaceAll("EstadoA","Estado: " + this.times.get(i).getEstado());
                    conteudo = conteudo.replaceAll("TotalJogadoresA","Total jogadores: " + this.times.get(i).getTotalJogadores());
                    conteudo = conteudo.replaceAll("QuantidadeJogosA","Quantidade Jogos: " + this.times.get(i).getQuantidadeJogos());
                    conteudo = conteudo.replaceAll("GolsMarcadosA","Gols Marcados: " + this.times.get(i).getGolsMarcados());
                    conteudo = conteudo.replaceAll("GolsSofridosA","Gols Sofridos: " + this.times.get(i).getGolsSofridos());
                    aux1++;
                }
            }
        }
        if(ts.verificaTimeExiste(this.times, timeB, estadoB) == 1){
            for(int i = 0; i < this.times.size(); i ++){
                if(this.times.get(i).getNome().equals(timeB) && this.times.get(i).getEstado().equals(estadoB)){
                    conteudo = conteudo.replaceAll("TIME B",this.times.get(i).getNome());
                    conteudo = conteudo.replaceAll("TimeB","Nome: " + this.times.get(i).getNome());
                    conteudo = conteudo.replaceAll("EstadoB","Estado: " + this.times.get(i).getEstado());
                    conteudo = conteudo.replaceAll("TotaJogadoresB","Total jogadores: " + this.times.get(i).getTotalJogadores());
                    conteudo = conteudo.replaceAll("QuantidadeJogosB","Quantidade Jogos: " + this.times.get(i).getQuantidadeJogos());
                    conteudo = conteudo.replaceAll("GolsMarcadosB","Gols Marcados: " + this.times.get(i).getGolsMarcados());
                    conteudo = conteudo.replaceAll("GolsSofridosB","Gols Sofridos: " + this.times.get(i).getGolsSofridos());
                    aux2++;
                }
            }
        }
        if(aux1 != 0 && aux2 != 0){
            arquivoHtml.reescreverHtml(conteudo, diretorio);
            System.out.println("Arquivo html criado comparando os dois times");
        }else{
            System.out.println("Erro, verifique se os times e estados informados existem");
        }
        return 0;
    }
    public int buscarTime(String nome,String estado){
        lerBancoDeDados();
        for(int i = 0; i < this.times.size(); i ++){
            if(this.times.get(i).getNome().equals(nome) && this.times.get(i).getEstado().equals(estado)){
                System.out.println("Nome: " + this.times.get(i).getNome());
                System.out.println("Estado: " + this.times.get(i).getEstado());
                System.out.println("Total Jogadores: " + this.times.get(i).getTotalJogadores());
                System.out.println("Quantidade de jogos: " + this.times.get(i).getQuantidadeJogos());
                System.out.println("Gols marcados: " + this.times.get(i).getGolsMarcados());
                System.out.println("Gols sofridos: " + this.times.get(i).getGolsSofridos());
                return 1;
            }
        }
        return 0;
    }
    public void listarTimes(){
        lerBancoDeDados();
        for(int i = 0; i < times.size(); i ++){
            System.out.println("----------------------------------------------------------");
            System.out.println("Nome: " + this.times.get(i).getNome());
            System.out.println("Estado: " + this.times.get(i).getEstado());
            System.out.println("Total Jogadores: " + this.times.get(i).getTotalJogadores());
            System.out.println("Quantidade de jogos: " + this.times.get(i).getQuantidadeJogos());
            System.out.println("Gols marcados: " + this.times.get(i).getGolsMarcados());
            System.out.println("Gols sofridos: " + this.times.get(i).getGolsSofridos());
            System.out.println("----------------------------------------------------------");
        }
    }
    public int buscarJogador(String id,String posicao){
        lerBancoDeDados();
        if(posicao.equals("atacante")){
            for(int i = 0; i < this.atacantes.size(); i ++){
                if(this.atacantes.get(i).getId().equals(id)){
                    System.out.println("ID: " + this.atacantes.get(i).getId());
                    System.out.println("Nome: " + this.atacantes.get(i).getNome());
                    System.out.println("Numero: " + this.atacantes.get(i).getNumero());
                    System.out.println("Posicao: " + this.atacantes.get(i).getPosicao());
                    System.out.println("Time: " + this.atacantes.get(i).getTime());
                    System.out.println("Estado: " + this.atacantes.get(i).getEstado());
                    System.out.println("Finalizações: " + this.atacantes.get(i).getFinalizacoes());
                    System.out.println("Finalizações Certas: " + this.atacantes.get(i).getFinalizacoesCertas());
                    System.out.println("Porcentagem finalizações certas: " + this.atacantes.get(i).getPorcentagemFinalizacoesCertas());
                    System.out.println("Gols: " + this.atacantes.get(i).getGol());
                    System.out.println("Assistencias: " + this.atacantes.get(i).getAssistencia());
                    System.out.println("Numero de jogos: " + this.atacantes.get(i).getNJogos());
                    System.out.println("Nota: " + this.atacantes.get(i).calcularNota());
                    return 1;
                }
            }
        }else if(posicao.equals("meia")){
            for(int i = 0; i < this.meias.size(); i ++){
                if(this.meias.get(i).getId().equals(id)){
                    System.out.println("ID: " + this.meias.get(i).getId());
                    System.out.println("Nome: " + this.meias.get(i).getNome());
                    System.out.println("Numero: " + this.meias.get(i).getNumero());
                    System.out.println("Posicao: " + this.meias.get(i).getPosicao());
                    System.out.println("Time: " + this.meias.get(i).getTime());
                    System.out.println("Estado: " + this.meias.get(i).getEstado());
                    System.out.println("Passes: " + this.meias.get(i).getPasses());
                    System.out.println("Passes Certos: " + this.meias.get(i).getPassesCertos());
                    System.out.println("Porcentagem passes certos: " + this.meias.get(i).getPorcentagemPassesCertos());
                    System.out.println("Lancamentos: " + this.meias.get(i).getLancamentos());
                    System.out.println("Lancamentos Certos: " + this.meias.get(i).getLancamentosCertos());
                    System.out.println("Porcentagem Lancamentos Certos: " + this.meias.get(i).getPorcentagemLancamentosCertos());
                    System.out.println("Gols: " + this.meias.get(i).getGol());
                    System.out.println("Assistencias: " + this.meias.get(i).getAssistencia());
                    System.out.println("Numero de jogos: " + this.meias.get(i).getNJogos());
                    System.out.println("Nota: " + this.meias.get(i).calcularNota());
                    return 1;
                }
            }
        }else if(posicao.equals("lateral")){
            for(int i = 0; i < this.laterais.size(); i ++){
                if(this.laterais.get(i).getId().equals(id)){
                    System.out.println("ID: " + this.laterais.get(i).getId());
                    System.out.println("Nome: " + this.laterais.get(i).getNome());
                    System.out.println("Numero: " + this.laterais.get(i).getNumero());
                    System.out.println("Posicao: " + this.laterais.get(i).getPosicao());
                    System.out.println("Time: " + this.laterais.get(i).getTime());
                    System.out.println("Estado: " + this.laterais.get(i).getEstado());
                    System.out.println("Cruzamentos: " + this.laterais.get(i).getCruzamentos());
                    System.out.println("Cruzamentos Certos: " + this.laterais.get(i).getCruzamentosCertos());
                    System.out.println("Porcentagem cruzamentos certos: " + this.laterais.get(i).getPorcentagemCruzamentosCertos());
                    System.out.println("Gols: " + this.laterais.get(i).getGol());
                    System.out.println("Assistencias: " + this.laterais.get(i).getAssistencia());
                    System.out.println("Numero de jogos: " + this.laterais.get(i).getNJogos());
                    System.out.println("Nota: " + this.laterais.get(i).calcularNota());
                    return 1;
                }
            }
        }else if(posicao.equals("zagueiro")){
            for(int i = 0; i < this.zagueiros.size(); i ++){
                if(this.zagueiros.get(i).getId().equals(id)){
                    System.out.println("ID: " + this.zagueiros.get(i).getId());
                    System.out.println("Nome: " + this.zagueiros.get(i).getNome());
                    System.out.println("Numero: " + this.zagueiros.get(i).getNumero());
                    System.out.println("Posicao: " + this.zagueiros.get(i).getPosicao());
                    System.out.println("Time: " + this.zagueiros.get(i).getTime());
                    System.out.println("Estado: " + this.zagueiros.get(i).getEstado());
                    System.out.println("Desarmes: " + this.zagueiros.get(i).getDesarmes());
                    System.out.println("Faltas: " + this.zagueiros.get(i).getFaltas());
                    System.out.println("Falhas defensivas: " + this.zagueiros.get(i).getFalhasDefensivas());
                    System.out.println("Gols: " + this.zagueiros.get(i).getGol());
                    System.out.println("Assistencias: " + this.zagueiros.get(i).getAssistencia());
                    System.out.println("Numero de jogos: " + this.zagueiros.get(i).getNJogos());
                    System.out.println("Nota: " + this.zagueiros.get(i).calcularNota());
                    return 1;
                }
            }
        }else if(posicao.equals("goleiro")){
            for(int i = 0; i < this.goleiros.size(); i ++){
                if(this.goleiros.get(i).getId().equals(id)){
                    System.out.println("ID: " + this.goleiros.get(i).getId());
                    System.out.println("Nome: " + this.goleiros.get(i).getNome());
                    System.out.println("Numero: " + this.goleiros.get(i).getNumero());
                    System.out.println("Posicao: " + this.goleiros.get(i).getPosicao());
                    System.out.println("Time: " + this.goleiros.get(i).getTime());
                    System.out.println("Estado: " + this.goleiros.get(i).getEstado());
                    System.out.println("Gols sofridos: " + this.goleiros.get(i).getGolsSofridos());
                    System.out.println("Defesas dificeis: " + this.goleiros.get(i).getDefesasDificeis());
                    System.out.println("Penaltis: " + this.goleiros.get(i).getPenaltis());
                    System.out.println("Penaltis defendidos: " + this.goleiros.get(i).getPenaltisDefendidos());
                    System.out.println("Porcentagem Penaltis defendidos: " + this.goleiros.get(i).getPorcentagemDefesasPenaltis());
                    System.out.println("Gols: " + this.goleiros.get(i).getGol());
                    System.out.println("Assistencias: " + this.goleiros.get(i).getAssistencia());
                    System.out.println("Numero de jogos: " + this.goleiros.get(i).getNJogos());
                    System.out.println("Nota: " + this.goleiros.get(i).calcularNota());
                    return 1;
                }
            }
        }
        return 0;
    }
    public void listarJogadores(){
        lerBancoDeDados();
        for(int i = 0; i < this.atacantes.size(); i ++){
            System.out.println("ID: " + this.atacantes.get(i).getId());
            System.out.println("Nome: " + this.atacantes.get(i).getNome());
            System.out.println("Numero: " + this.atacantes.get(i).getNumero());
            System.out.println("Posicao: " + this.atacantes.get(i).getPosicao());
            System.out.println("Time: " + this.atacantes.get(i).getTime());
            System.out.println("Estado: " + this.atacantes.get(i).getEstado());
            System.out.println("Finalizações: " + this.atacantes.get(i).getFinalizacoes());
            System.out.println("Finalizações Certas: " + this.atacantes.get(i).getFinalizacoesCertas());
            System.out.println("Porcentagem finalizações certas: " + this.atacantes.get(i).getPorcentagemFinalizacoesCertas());
            System.out.println("Gols: " + this.atacantes.get(i).getGol());
            System.out.println("Assistencias: " + this.atacantes.get(i).getAssistencia());
            System.out.println("Numero de jogos: " + this.atacantes.get(i).getNJogos());
            System.out.println("Nota: " + this.atacantes.get(i).calcularNota());
        }
        for(int i = 0; i < this.meias.size(); i ++){
            System.out.println("ID: " + this.meias.get(i).getId());
            System.out.println("Nome: " + this.meias.get(i).getNome());
            System.out.println("Numero: " + this.meias.get(i).getNumero());
            System.out.println("Posicao: " + this.meias.get(i).getPosicao());
            System.out.println("Time: " + this.meias.get(i).getTime());
            System.out.println("Estado: " + this.meias.get(i).getEstado());
            System.out.println("Passes: " + this.meias.get(i).getPasses());
            System.out.println("Passes Certos: " + this.meias.get(i).getPassesCertos());
            System.out.println("Porcentagem passes certos: " + this.meias.get(i).getPorcentagemPassesCertos());
            System.out.println("Lancamentos: " + this.meias.get(i).getLancamentos());
            System.out.println("Lancamentos Certos: " + this.meias.get(i).getLancamentosCertos());
            System.out.println("Porcentagem Lancamentos Certos: " + this.meias.get(i).getPorcentagemLancamentosCertos());
            System.out.println("Gols: " + this.meias.get(i).getGol());
            System.out.println("Assistencias: " + this.meias.get(i).getAssistencia());
            System.out.println("Numero de jogos: " + this.meias.get(i).getNJogos());
            System.out.println("Nota: " + this.meias.get(i).calcularNota());
        }
        for(int i = 0; i < this.laterais.size(); i ++){
            System.out.println("ID: " + this.laterais.get(i).getId());
            System.out.println("Nome: " + this.laterais.get(i).getNome());
            System.out.println("Numero: " + this.laterais.get(i).getNumero());
            System.out.println("Posicao: " + this.laterais.get(i).getPosicao());
            System.out.println("Time: " + this.laterais.get(i).getTime());
            System.out.println("Estado: " + this.laterais.get(i).getEstado());
            System.out.println("Cruzamentos: " + this.laterais.get(i).getCruzamentos());
            System.out.println("Cruzamentos Certos: " + this.laterais.get(i).getCruzamentosCertos());
            System.out.println("Porcentagem cruzamentos certos: " + this.laterais.get(i).getPorcentagemCruzamentosCertos());
            System.out.println("Gols: " + this.laterais.get(i).getGol());
            System.out.println("Assistencias: " + this.laterais.get(i).getAssistencia());
            System.out.println("Numero de jogos: " + this.laterais.get(i).getNJogos());
            System.out.println("Nota: " + this.laterais.get(i).calcularNota());
        }
        for(int i = 0; i < this.zagueiros.size(); i ++){
            System.out.println("ID: " + this.zagueiros.get(i).getId());
            System.out.println("Nome: " + this.zagueiros.get(i).getNome());
            System.out.println("Numero: " + this.zagueiros.get(i).getNumero());
            System.out.println("Posicao: " + this.zagueiros.get(i).getPosicao());
            System.out.println("Time: " + this.zagueiros.get(i).getTime());
            System.out.println("Estado: " + this.zagueiros.get(i).getEstado());
            System.out.println("Desarmes: " + this.zagueiros.get(i).getDesarmes());
            System.out.println("Faltas: " + this.zagueiros.get(i).getFaltas());
            System.out.println("Falhas defensivas: " + this.zagueiros.get(i).getFalhasDefensivas());
            System.out.println("Gols: " + this.zagueiros.get(i).getGol());
            System.out.println("Assistencias: " + this.zagueiros.get(i).getAssistencia());
            System.out.println("Numero de jogos: " + this.zagueiros.get(i).getNJogos());
            System.out.println("Nota: " + this.zagueiros.get(i).calcularNota());
        }
        for(int i = 0; i < this.goleiros.size(); i ++){
            System.out.println("ID: " + this.goleiros.get(i).getId());
            System.out.println("Nome: " + this.goleiros.get(i).getNome());
            System.out.println("Numero: " + this.goleiros.get(i).getNumero());
            System.out.println("Posicao: " + this.goleiros.get(i).getPosicao());
            System.out.println("Time: " + this.goleiros.get(i).getTime());
            System.out.println("Estado: " + this.goleiros.get(i).getEstado());
            System.out.println("Gols sofridos: " + this.goleiros.get(i).getGolsSofridos());
            System.out.println("Defesas dificeis: " + this.goleiros.get(i).getDefesasDificeis());
            System.out.println("Penaltis: " + this.goleiros.get(i).getPenaltis());
            System.out.println("Penaltis defendidos: " + this.goleiros.get(i).getPenaltisDefendidos());
            System.out.println("Porcentagem Penaltis defendidos: " + this.goleiros.get(i).getPorcentagemDefesasPenaltis());
            System.out.println("Gols: " + this.goleiros.get(i).getGol());
            System.out.println("Assistencias: " + this.goleiros.get(i).getAssistencia());
            System.out.println("Numero de jogos: " + this.goleiros.get(i).getNJogos());
            System.out.println("Nota: " + this.goleiros.get(i).calcularNota());
        }
    }
}
