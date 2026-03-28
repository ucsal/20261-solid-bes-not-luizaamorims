package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.models.Questao;
import br.com.ucsal.olimpiadas.models.Tentativa;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;

import java.util.List;

public class AplicarProvaService {
    private final TentativaRepository tentativaRepo;
    private final CalculadoraNota calculadora;

    public AplicarProvaService(TentativaRepository tentativaRepo, CalculadoraNota calculadora) {
        this.tentativaRepo = tentativaRepo;
        this.calculadora = calculadora;
    }

    public void registrarTentativa(long participanteId, long provaId,
                                        List<Questao> questoes, List<Character> marcadas) {
        Tentativa tentativa = new Tentativa();
        tentativa.setParticipanteId(participanteId);
        tentativa.setProvaId(provaId);

      tentativaRepo.salvar(tentativa, provaId, questoes, marcadas);
    }

    public int calcularNota(Tentativa tentativa) {
        return calculadora.calcular(tentativa);
    }

    public List<Tentativa> listarTentativas() {
        return tentativaRepo.listarTentativas();
    }
}
