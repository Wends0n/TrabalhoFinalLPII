package interfaces;

import java.util.Scanner;

public class Interface {
    private Scanner entrada = new Scanner(System.in);
    public int inicio(){
        int opcao;
        String op;

        System.out.println("Escolha uma opcao");
        System.out.println("1 - Time");
        System.out.println("2 - Jogador");
        System.out.println("3 - Sair");
        op = entrada.nextLine();
        opcao = Integer.parseInt(op);
        return opcao;
    }
    public int telaTime(){
        int opcao;
        String op;
        System.out.println("Escolha uma opcao");
        System.out.println("1 - Criar time");
        System.out.println("2 - Remover time");
        System.out.println("3 - Buscar time");
        System.out.println("4 - Listar times");
        System.out.println("5 - Comparar times");
        System.out.println("6 - Voltar");
        System.out.println("7 - Sair");
        op = entrada.nextLine();
        opcao = Integer.parseInt(op);
        return opcao;
    }
    public int telaJogador(){
        int opcao;
        String op;
        System.out.println("Escolha uma opcao");
        System.out.println("1 - Criar jogador");
        System.out.println("2 - Remover jogador");
        System.out.println("3 - Adicionar estatistica jogador");
        System.out.println("4 - Buscar jogador");
        System.out.println("5 - Listar jogadores");
        System.out.println("6 - Comparar jogadores");
        System.out.println("7 - Voltar");
        System.out.println("8 - Sair");
        op = entrada.nextLine();
        opcao = Integer.parseInt(op);
        return opcao;
    }
    public String telaId(){
        String id;
        System.out.println("ID: ");
        id = entrada.nextLine();
        return id;
    }
    public String telaNome(){
        String nome;
        System.out.println("Nome: ");
        nome = entrada.nextLine();
        return nome;
    }
    public String telaNumero(){
        String numero;
        System.out.println("Numero: ");
        numero = entrada.nextLine();
        return numero;
    }
    public String telaPosicao(){
        String posicao;
        System.out.println("Somente letras minusculas");
        System.out.println("Posicao (atacante,meia,lateral,zagueiro,goleiro) -->");
        posicao = entrada.nextLine();
        return posicao;
    }
    public String telaNomeTime(){
        String time;
        System.out.println("Time: ");
        time = entrada.nextLine();
        return time;
    }
    public String telaEstado(){
        String estado;
        System.out.println("Estado: ");
        estado = entrada.nextLine();
        return estado;
    }
    public String telaPartida(){
        String partida;
        System.out.print("Informe a partida: (time A x Time B)");
        partida = entrada.nextLine();
        return partida;
    }
    public int telaFinalizacoes(){
        String fin;
        int finalizacoes;
        System.out.print("Finalizacoes: ");
        fin = entrada.nextLine();
        finalizacoes = Integer.parseInt(fin);
        return finalizacoes;
    }
    public int telaFinalizacoesCertas(){
        String fin;
        int finalizacoesCertas;
        System.out.print("Finalizacoes Certas: ");
        fin = entrada.nextLine();
        finalizacoesCertas = Integer.parseInt(fin);
        return finalizacoesCertas;
    }
    public int telaPasses(){
        String passes;
        System.out.print("Passes: ");
        passes = entrada.nextLine();
        return Integer.parseInt(passes);
    }
    public int telaPassesCertos(){
        String passesCertos;
        System.out.print("Passes Certos: ");
        passesCertos = entrada.nextLine();
        return Integer.parseInt(passesCertos);
    }
    public int telaLancamentos(){
        String lancamentos;
        System.out.print("Lancamentos: ");
        lancamentos = entrada.nextLine();
        return Integer.parseInt(lancamentos);
    }
    public int telaLancamentosCertos(){
        String lancamentosCertos;
        System.out.println("Lancamentos Certos: ");
        lancamentosCertos = entrada.nextLine();
        return Integer.parseInt(lancamentosCertos);
    }
    public int telaCruzamentos(){
        String cruzamentos;
        System.out.print("Cruzamentos: ");
        cruzamentos = entrada.nextLine();
        return Integer.parseInt(cruzamentos);
    }
    public int telaCruzamentosCertos(){
        String cruzamentosCertos;
        System.out.println("Cruzamentos certos: ");
        cruzamentosCertos = entrada.nextLine();
        return Integer.parseInt(cruzamentosCertos);
    }
    public int telaDesarmes(){
        String desarmes;
        System.out.print("Desarmes: ");
        desarmes = entrada.nextLine();
        return Integer.parseInt(desarmes);
    }
    public int telaFaltas(){
        String faltas;
        System.out.print("Faltas: ");
        faltas = entrada.nextLine();
        return Integer.parseInt(faltas);
    }
    public int telaFalhasDefensivas(){
        String falhasDefensivas;
        System.out.print("Falhas defensivas: ");
        falhasDefensivas = entrada.nextLine();
        return Integer.parseInt(falhasDefensivas);
    }
    public int telaGolsSofridos(){
        String golsSofridos;
        System.out.print("Gols Sofridos: ");
        golsSofridos = entrada.nextLine();
        return Integer.parseInt(golsSofridos);
    }
    public int telaDefesasDificeis(){
        String defesasDificeis;
        System.out.println("Defesas dificeis: ");
        defesasDificeis = entrada.nextLine();
        return Integer.parseInt(defesasDificeis);
    }
    public int telaPenaltis(){
        String penaltis;
        System.out.println("Penaltis: ");
        penaltis = entrada.nextLine();
        return Integer.parseInt(penaltis);
    }
    public int telaPenaltisDefendidos(){
        String penaltisDefendidos;
        System.out.println("Penaltis defendidos: ");
        penaltisDefendidos = entrada.nextLine();
        return Integer.parseInt(penaltisDefendidos);
    }
    public int telaGol(){
        String g;
        int gol;
        System.out.print("Gols: ");
        g = entrada.nextLine();
        gol = Integer.parseInt(g);
        return gol;
    }
    public int telaAssistencia(){
        String a;
        int assistencia;
        System.out.print("Assistencias: ");
        a = entrada.nextLine();
        assistencia = Integer.parseInt(a);
        return assistencia;
    }
    public void erroPartidaId(){
        System.out.println("Erro, a partida ja foi cadastrada OU o id informado nao existe");
    }
}
