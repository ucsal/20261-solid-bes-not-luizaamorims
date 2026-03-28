package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.models.Prova;

import java.util.List;

public interface ProvaRepository {
    // Interface para o repositório de provas, definindo os métodos necessários para salvar, listar e verificar a existência de provas.
    //basicamente é um contrato que qualquer implementação de repositório de provas deve seguir,
    // garantindo que as operações essenciais estejam disponíveis.

    void salvar (Prova prova);
    List<Prova> listarTodos();
    boolean existeProva(long id);

}
