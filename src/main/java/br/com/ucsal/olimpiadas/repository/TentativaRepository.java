package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.models.Questao;
import br.com.ucsal.olimpiadas.models.Tentativa;

import java.util.List;

public interface TentativaRepository {
    //interface para o repositório de tentativas, com métodos para salvar uma tentativa e listar todas as tentativas

    void salvar(Tentativa tentativa, long prova, List<Questao> questoes, List<Character> marcadas);
    List<Tentativa> listarTentativas();
}
