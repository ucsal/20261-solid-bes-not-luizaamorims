package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.models.Questao;

import java.util.List;

public interface QuestaoRepository {
    void salvar(Questao questao);
    List<Questao> listarPorIdProva(long idProva);
}
