package br.com.ucsal.olimpiadas.repository;
import br.com.ucsal.olimpiadas.models.Participante;

import java.util.List;

public interface ParticipanteRepository {
    // Interface para o repositório de participantes, definindo os métodos necessários para salvar,
    // listar e verificar a existência de participantes.
    //basicamente é um contrato que qualquer implementação de repositório de participantes deve seguir,
    // garantindo que as operações essenciais estejam disponíveis.
    void salvar (Participante participante);
    List<Participante> listarTodos();
    boolean existeParticipante(long id);
}
