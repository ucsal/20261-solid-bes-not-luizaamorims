package br.com.ucsal.olimpiadas.memory;

import br.com.ucsal.olimpiadas.models.Participante;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteMemory implements ParticipanteRepository {

    //essas classes que fiz de memoria, na verdade são para armazenar os dados de cada classe, nesse
    //caso aqui, é de participante, o proximoId é para ir incrementando o id de cada participante
    // que for criado, e a lista de participantes é para armazenar os participantes criados

    private long proximoId = 1;
    private final List<Participante> participantes = new ArrayList<>();

    public void salvar(Participante participante) {
        participante.setId(proximoId++);
        participantes.add(participante);
    }

    public List<Participante> listarTodos() {
        return new ArrayList<>(participantes);
    }

    public boolean existeParticipante(long id) {
        for (Participante p : participantes) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }
}

