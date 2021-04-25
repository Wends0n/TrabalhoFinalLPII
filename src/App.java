import interfaces.Interface;
import servicos.Sistema;

//Nome - Wendson Menezes Dias
public class App {
    public static void main(String[] args) throws Exception {
        Interface ig = new Interface();
        Sistema bancoDeDados = new Sistema();
        
        int opcao;
        String id,nome,numero ,posicao,time,estado,partida;
        boolean continuar = true;
        
        
        while(continuar){
            opcao = ig.inicio();
            switch(opcao){
                case 1:
                    opcao = ig.telaTime();
                    if(opcao == 1){
                        time = ig.telaNomeTime();
                        estado = ig.telaEstado();
                        if(bancoDeDados.criarTime(time, estado) == 0){
                            System.out.println("O time ja esta cadastrado");
                        }else{
                            System.out.println("Time cadastrado com sucesso");
                        }
                    }else if(opcao == 2){
                        time = ig.telaNomeTime();
                        estado = ig.telaEstado();
                        if(bancoDeDados.removerTime(time, estado) == 0){
                            System.out.println("O time informado nao esta cadastrado");
                        }else{
                            System.out.println("Time removido com sucesso");
                        }
                    }else if(opcao == 3){
                        time = ig.telaNomeTime();
                        estado = ig.telaEstado();
                        if(bancoDeDados.buscarTime(time, estado) == 0){
                            System.out.println("O time informado nao foi encontrado, verifique o time e estado, e tente novamente");
                        }
                    }else if(opcao == 4){
                        bancoDeDados.listarTimes();
                    }else if(opcao == 5){
                        String timeA, timeB;
                        String estadoA, estadoB;
                        timeA = ig.telaNomeTime();
                        estadoA = ig.telaEstado();
                        timeB = ig.telaNomeTime();
                        estadoB = ig.telaEstado();
                        bancoDeDados.compararTimes(timeA, estadoA, timeB, estadoB);
                    }else if(opcao == 6){
                        break;
                    }else if(opcao == 7){
                        continuar = false;
                        break;
                    }
                    break;
                case 2:
                    opcao = ig.telaJogador();
                    if(opcao == 1){
                        id = ig.telaId();
                        nome = ig.telaNome();
                        numero = ig.telaNumero();
                        posicao = ig.telaPosicao();
                        time = ig.telaNomeTime();
                        estado = ig.telaEstado();
                        if(posicao.equals("atacante")){
                            if(bancoDeDados.criarAtacante(id, nome, numero, posicao, time, estado) == 0){
                                System.out.println("Erro, o atacante ja esta cadastrado OU o time informado nao existe");
                            }
                        }else if(posicao.equals("meia")){
                            if(bancoDeDados.criarMeia(id, nome, numero, posicao, time, estado) == 0){
                                System.out.println("Erro, o meia ja esta cadastrado OU o time informado nao existe");
                            }
                        }else if(posicao.equals("lateral")){
                            if(bancoDeDados.criarLateral(id, nome, numero, posicao, time, estado) == 0){
                                System.out.println("Erro, o lateral ja esta cadastrado OU o time informado nao existe");
                            }
                        }else if(posicao.equals("zagueiro")){
                            if(bancoDeDados.criarZagueiro(id, nome, numero, posicao, time, estado) == 0){
                                System.out.println("Erro, o zagueiro ja esta cadastrado OU o time informado nao existe");
                            }
                        }else if(posicao.equals("goleiro")){
                            if(bancoDeDados.criarGoleiro(id, nome, numero, posicao, time, estado) == 0){
                                System.out.println("Erro, o goleiro ja esta cadastrado OU o time informado nao existe");
                            }
                        }else{
                            System.out.println("Erro, alguma informação esta errada, verifique se a posição do jogador foi informada somente com letras minusculas");
                        }
                    }else if(opcao == 2){
                        id = ig.telaId();
                        posicao = ig.telaPosicao();
                        if(posicao.equals("atacante")){
                            if(bancoDeDados.removerAtacante(id) == 0){
                                System.out.println("O atacante informado nao existe!");
                            }else{
                                System.out.println("Atacante removido com sucesso");
                            }
                        }else if(posicao.equals("meia")){
                            if(bancoDeDados.removerMeia(id) == 0){
                                System.out.println("O meia informado nao existe!");
                            }else{
                                System.out.println("Meia removido com sucesso");
                            }
                        }else if(posicao.equals("lateral")){
                            if(bancoDeDados.removerLateral(id) == 0){
                                System.out.println("O lateral informado nao existe!");
                            }else{
                                System.out.println("Lateral removido com sucesso");
                            }
                        }else if(posicao.equals("zagueiro")){
                            if(bancoDeDados.removerZagueiro(id) == 0){
                                System.out.println("O zagueiro informado nao existe!");
                            }else{
                                System.out.println("Zagueiro removido com sucesso");
                            }
                        }else if(posicao.equals("goleiro")){
                            if(bancoDeDados.removerGoleiro(id) == 0){
                                System.out.println("O goleiro informado nao existe!");
                            }else{
                                System.out.println("Goleiro removido com sucesso");
                            }
                        }
                    }else if(opcao == 3){
                        int gol,assistencia;
                        id = ig.telaId();
                        posicao = ig.telaPosicao();
                        partida = ig.telaPartida();
                        gol = ig.telaGol();
                        assistencia = ig.telaAssistencia();
                        if(posicao.equals("atacante")){
                            int finalizacoes,finalizacoesCertas;
                            finalizacoes = ig.telaFinalizacoes();
                            finalizacoesCertas = ig.telaFinalizacoesCertas();
                            if(bancoDeDados.adicionarEstatisticaAtacante(id, posicao, partida, finalizacoes, finalizacoesCertas, gol, assistencia) == 0){
                                ig.erroPartidaId();
                            }
                        }else if(posicao.equals("meia")){
                            int passes, passesCertos;
                            int lancamentos, lancamentosCertos;
                            passes = ig.telaPasses();
                            passesCertos = ig.telaPassesCertos();
                            lancamentos = ig.telaLancamentos();
                            lancamentosCertos = ig.telaLancamentosCertos();
                            if(bancoDeDados.adicionarEstatisticaMeia(id, posicao, partida, passes, passesCertos, lancamentos, lancamentosCertos, gol, assistencia) == 0){
                                ig.erroPartidaId();
                            }
                        }else if(posicao.equals("lateral")){
                            int cruzamentos,cruzamentosCertos;
                            cruzamentos = ig.telaCruzamentos();
                            cruzamentosCertos = ig.telaCruzamentosCertos();
                            if(bancoDeDados.adicionarEstatisticaLateral(id, posicao, partida, cruzamentos, cruzamentosCertos, gol, assistencia) == 0){
                                ig.erroPartidaId();
                            }
                        }else if(posicao.equals("zagueiro")){
                            int desarmes;
                            int faltas;
                            int falhasDefensivas;
                            desarmes = ig.telaDesarmes();
                            faltas = ig.telaFaltas();
                            falhasDefensivas = ig.telaFalhasDefensivas();
                            if(bancoDeDados.adicionarEstatisticaZagueiro(id, posicao, partida, desarmes, faltas, falhasDefensivas, gol, assistencia) == 0){
                                ig.erroPartidaId();
                            }
                        }else if(posicao.equals("goleiro")){
                            int golsSofridos;
                            int defesasDificeis;
                            int penaltis,penaltisDefendidos;
                            golsSofridos = ig.telaGolsSofridos();
                            defesasDificeis = ig.telaDefesasDificeis();
                            penaltis = ig.telaPenaltis();
                            penaltisDefendidos = ig.telaPenaltisDefendidos();
                            if(bancoDeDados.adicionarEstatisticaGoleiro(id, posicao, partida, golsSofridos, defesasDificeis, penaltis, penaltisDefendidos, gol, assistencia) == 0){
                                ig.erroPartidaId();
                            }
                        }
                    }else if(opcao == 4){
                        id = ig.telaId();
                        posicao = ig.telaPosicao();
                        if(bancoDeDados.buscarJogador(id, posicao) == 0){
                            System.out.println("O jogador nao foi encontrado!");
                        }
                    }else if(opcao == 5){
                        bancoDeDados.listarJogadores();
                    }else if(opcao == 6){
                        String idA,idB;
                        String posicaoA,posicaoB;
                        idA = ig.telaId();
                        posicaoA = ig.telaPosicao();
                        idB = ig.telaId();
                        posicaoB = ig.telaPosicao();
                        bancoDeDados.compararJogadores(idA, posicaoA, idB, posicaoB);
                    }else if (opcao == 7){
                        break;
                    }else if(opcao == 8){
                        continuar = false;
                        break;
                    }
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }
    }
}
