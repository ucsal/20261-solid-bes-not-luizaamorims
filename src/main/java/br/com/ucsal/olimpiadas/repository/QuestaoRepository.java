package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.models.Questao;

import java.util.List;

public interface QuestaoRepository {
    //interface para o repositório de questões, com métodos para salvar e listar questões por prova


    void salvar(Questao questao);
    List<Questao> listarPorIdProva(long idProva);
}
