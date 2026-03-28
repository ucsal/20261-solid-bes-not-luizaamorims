package br.com.ucsal.olimpiadas.main;

import br.com.ucsal.olimpiadas.models.Questao;
import br.com.ucsal.olimpiadas.models.Tentativa;
import br.com.ucsal.olimpiadas.service.AplicarProvaService;
import br.com.ucsal.olimpiadas.service.ParticipanteService;
import br.com.ucsal.olimpiadas.service.ProvaService;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MenuConsole {
    //essa classe é responsável pela interação com o usuário via console.
    //exibe o menu, coleta entradas e delega as ações aos serviços competentes.
    // não contém lógica de negócio, apenas apresentação e navegação.

    private final Scanner sc;
    private final ParticipanteService participanteService;
    private final ProvaService provaService;
    private final AplicarProvaService aplicarProvaService;
    private final TabuleiroImprimir tabuleiroFenPrinter;

    public MenuConsole(Scanner sc,
                       ParticipanteService participanteService,
                       ProvaService provaService,
                       AplicarProvaService aplicarProvaService,
                       TabuleiroImprimir tabuleiroFenPrinter) {
        this.sc = sc;
        this.participanteService = participanteService;
        this.provaService = provaService;
        this.aplicarProvaService = aplicarProvaService;
        this.tabuleiroFenPrinter = tabuleiroFenPrinter;
    }

    public void iniciar() {
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
            System.out.println("1) Cadastrar participante");
            System.out.println("2) Cadastrar prova");
            System.out.println("3) Cadastrar questão (A–E) em uma prova");
            System.out.println("4) Aplicar prova (selecionar participante + prova)");
            System.out.println("5) Listar tentativas (resumo)");
            System.out.println("0) Sair");
            System.out.print("> ");

            switch (sc.nextLine()) {
                case "1" -> cadastrarParticipante();
                case "2" -> cadastrarProva();
                case "3" -> cadastrarQuestao();
                case "4" -> aplicarProva();
                case "5" -> listarTentativas();
                case "0" -> {
                    System.out.println("tchau");
                    rodando = false;
                }
                default -> System.out.println("opção inválida");
            }
        }
    }


    private void cadastrarParticipante() {
        System.out.print("Nome: ");
        var nome = sc.nextLine();
        System.out.print("Email (opcional): ");
        var email = sc.nextLine();
        try {
            var p = participanteService.cadastrar(nome, email);
            System.out.println("Participante cadastrado: " + p.getId());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cadastrarProva() {
        System.out.print("Título da prova: ");
        var titulo = sc.nextLine();
        try {
            var prova = provaService.cadastrarProva(titulo);
            System.out.println("Prova criada: " + prova.getId());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cadastrarQuestao() {
        if (provaService.listarProvas().isEmpty()) {
            System.out.println("não há provas cadastradas");
            return;
        }
        var provaId = escolherProva();
        if (provaId == null) return;

        System.out.println("Enunciado:");
        var enunciado = sc.nextLine();

        var alternativas = new String[5];
        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            System.out.print("Alternativa " + letra + ": ");
            alternativas[i] = letra + ") " + sc.nextLine();
        }

        System.out.print("Alternativa correta (A–E): ");
        char correta;
        try {
            correta = Questao.normalizar(sc.nextLine().trim().charAt(0));
        } catch (Exception e) {
            System.out.println("alternativa inválida");
            return;
        }

        System.out.print("FEN inicial (deixe em branco para nenhum): ");
        var fen = sc.nextLine();

        try {
            var q = provaService.cadastrarQuestao(provaId, enunciado, alternativas, correta, fen);
            System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void aplicarProva() {
        if (participanteService.listarTodos().isEmpty()) {
            System.out.println("cadastre participantes primeiro");
            return;
        }
        if (provaService.listarProvas().isEmpty()) {
            System.out.println("cadastre provas primeiro");
            return;
        }

        var participanteId = escolherParticipante();
        if (participanteId == null) return;

        var provaId = escolherProva();
        if (provaId == null) return;

        var questoesDaProva = provaService.listarQuestoesDaProva(provaId);
        if (questoesDaProva.isEmpty()) {
            System.out.println("esta prova não possui questões cadastradas");
            return;
        }

        System.out.println("\n--- Início da Prova ---");

        List<Character> respostas = new ArrayList<>();
        for (var q : questoesDaProva) {
            respostas.add(coletarResposta(q));
        }

        Tentativa tentativa = aplicarProvaService.registrarTentativa(
                participanteId, provaId, questoesDaProva, respostas);

        int nota = aplicarProvaService.calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }

    private char coletarResposta(Questao q) {
        System.out.println("\nQuestão #" + q.getId());
        System.out.println(q.getEnunciado());
        System.out.println("Posição inicial:");
        tabuleiroFenPrinter.imprimir(q.getFenInicial());
        for (var alt : q.getAlternativas()) {
            System.out.println(alt);
        }
        System.out.print("Sua resposta (A–E): ");
        try {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("resposta não pode estar vazia");
                return coletarResposta(q);
            }
            return Questao.normalizar(input.charAt(0));
        } catch (IllegalArgumentException e) {
            System.out.println("resposta inválida (marcando como errada)");
            return 'X';
        }
    }

    private void listarTentativas() {
        System.out.println("\n--- Tentativas ---");
        for (var t : aplicarProvaService.listarTentativas()) {
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(), t.getParticipanteId(), t.getProvaId(),
                    aplicarProvaService.calcularNota(t), t.getRespostas().size());
        }
    }

    private Long escolherParticipante() {
        System.out.println("\nParticipantes:");
        for (var p : participanteService.listarTodos()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
        }
        System.out.print("Escolha o id do participante: ");
        try {
            long id = Long.parseLong(sc.nextLine());
            if (!participanteService.existePorId(id)) {
                System.out.println("id inválido");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("entrada inválida");
            return null;
        }
    }

    private Long escolherProva() {
        System.out.println("\nProvas:");
        for (var p : provaService.listarProvas()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
        }
        System.out.print("Escolha o id da prova: ");
        try {
            long id = Long.parseLong(sc.nextLine());
            if (!provaService.existeProvaPorId(id)) {
                System.out.println("id inválido");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("entrada inválida");
            return null;
        }
    }
}
