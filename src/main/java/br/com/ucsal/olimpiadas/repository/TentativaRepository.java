package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.models.Questao;
import br.com.ucsal.olimpiadas.models.Tentativa;

import java.util.List;

public interface TentativaRepository {
    void salvar(Tentativa tentativa, long prova, List<Questao> questoes, List<Character> marcadas);
    List<Tentativa> listarTentativas();
}
