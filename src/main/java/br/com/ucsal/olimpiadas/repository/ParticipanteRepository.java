package br.com.ucsal.olimpiadas.repository;
import br.com.ucsal.olimpiadas.models.Participante;

import java.util.List;

public interface ParticipanteRepository {
    void salvar (Participante participante);
    List<Participante> listarTodos();
    boolean existeParticipante(long id);
}
