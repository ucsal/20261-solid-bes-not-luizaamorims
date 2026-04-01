package br.com.ucsal.olimpiadas.memory;

import br.com.ucsal.olimpiadas.models.Questao;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;

import java.util.ArrayList;
import java.util.List;

public class QuestaoMemory implements QuestaoRepository {

    //essas classes que fiz de memoria, na verdade são para armazenar os dados de cada classe, nesse
    // caso aqui, é das questoes, o proximoId é para ir incrementando o id de cada questao
    // que for criada, e a lista de questoes é para armazenar as questoes criados
    //tambem lista por id da prova

    private long proximoId = 1;
    private final List<Questao> questoes = new ArrayList<>();

    public void salvar(Questao questao) {
        questao.setId(proximoId++);
        questoes.add(questao);
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
