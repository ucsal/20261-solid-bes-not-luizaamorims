package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.models.Prova;

import java.util.List;

public interface ProvaRepository {
    void salvar (Prova prova);
    List<Prova> listarTodos();
    boolean existeProva(long id);

}
