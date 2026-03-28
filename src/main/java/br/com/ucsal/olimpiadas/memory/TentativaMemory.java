package br.com.ucsal.olimpiadas.memory;

import br.com.ucsal.olimpiadas.models.Questao;
import br.com.ucsal.olimpiadas.models.Resposta;
import br.com.ucsal.olimpiadas.models.Tentativa;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;

import java.util.ArrayList;
import java.util.List;


public class TentativaMemory implements TentativaRepository {

    //essas classes que fiz de memoria, na verdade são para armazenar os dados de cada classe, nesse
    // caso aqui, é das tentativas , o proximoId é para ir incrementando o id de cada tentativa
    // que for feita, e a lista de tentativas é para armazenar as tentativas feitas

    private long proximoId = 1;
    private final List<Tentativa> tentativas = new ArrayList<>();

    public void salvar(Tentativa tentativa, long prova, List<Questao> questoes, List<Character> marcadas) {
        Tentativa t = new Tentativa();
        tentativa.setId(proximoId++);
        tentativa.setParticipanteId(t.getParticipanteId());
        tentativa.setProvaId(t.getProvaId());

        for (int i = 0; i < questoes.size(); i++) {
            Questao q = questoes.get(i);
            char marcada = marcadas.get(i);
            Resposta r = new Resposta();
            r.setQuestaoId(q.getId());
            r.setAlternativaMarcada(marcada);
            r.setCorreta(q.isRespostaCorreta(marcada));
            tentativa.getRespostas().add(r);
        }
        tentativas.add(tentativa);
    }

    public List<Tentativa> listarTentativas() {
        return List.of(tentativas.toArray(new Tentativa[0]));
    }
}
