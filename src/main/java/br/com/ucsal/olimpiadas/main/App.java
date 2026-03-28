package br.com.ucsal.olimpiadas.main;

import br.com.ucsal.olimpiadas.memory.ParticipanteMemory;
import br.com.ucsal.olimpiadas.memory.ProvaMemory;
import br.com.ucsal.olimpiadas.memory.QuestaoMemory;
import br.com.ucsal.olimpiadas.memory.TentativaMemory;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;
import br.com.ucsal.olimpiadas.service.AplicarProvaService;
import br.com.ucsal.olimpiadas.service.CalculadoraNota;
import br.com.ucsal.olimpiadas.service.ParticipanteService;
import br.com.ucsal.olimpiadas.service.ProvaService;


import java.util.Scanner;

public class App {
    //ponto de entrada da aplicação.
    // responsável por instanciar e conectar todas as dependências  e popular os dados iniciais via seed.
    // não contém lógica de negócio nem de apresentação.

    public static void main(String[] args) {
        ParticipanteRepository participanteRepo = new ParticipanteMemory();
        ProvaRepository provaRepo = new ProvaMemory();
        QuestaoRepository questaoRepo = new QuestaoMemory();
        TentativaRepository tentativaRepo = new TentativaMemory();

        var calculadora = new CalculadoraNota();
        var participanteService = new ParticipanteService(participanteRepo);
        var provaService = new ProvaService(provaRepo, questaoRepo);
        var aplicarProvaService = new AplicarProvaService(tentativaRepo, calculadora);

        seed(provaService);

        var menu = new MenuConsole(
                new Scanner(System.in),
                participanteService,
                provaService,
                aplicarProvaService,
                new TabuleiroImprimir()
        );

        menu.iniciar();
    }

    private static void seed(ProvaService provaService) {
        var prova = provaService.cadastrarProva("Olimpíada 2026 • Nível 1 • Prova A");

        provaService.cadastrarQuestao(
                prova.getId(),
                """
                Questão 1 — Mate em 1.
                É a vez das brancas.
                Encontre o lance que dá mate imediatamente.
                """,
                new String[]{"A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#"},
                'C',
                "6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1"
        );
    }
}
