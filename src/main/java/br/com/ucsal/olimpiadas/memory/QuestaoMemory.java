package br.com.ucsal.olimpiadas.memory;

import br.com.ucsal.olimpiadas.models.Questao;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;

import java.util.ArrayList;
import java.util.List;

public class QuestaoMemory implements QuestaoRepository {

    private long proximoId = 1;
    private final List<Questao> questoes = new ArrayList<>();

    public void salvar(Questao questao) {
        Questao q = new Questao();
        q.setId(proximoId++);
        q.setProvaId(q.getId());
        q.setEnunciado(q.getEnunciado());
        q.setAlternativas(q.getAlternativas());
        q.setAlternativaCorreta(q.getAlternativaCorreta());
        q.setFenInicial(q.getFenInicial());
        questoes.add(q);
    }

    public List<Questao> listarPorIdProva(long provaId) {
        List<Questao> resultado = new ArrayList<>();
        for (Questao q : questoes) {
            if (q.getProvaId() == provaId) {
                resultado.add(q);
            }
        }
        return resultado;
    }

}
