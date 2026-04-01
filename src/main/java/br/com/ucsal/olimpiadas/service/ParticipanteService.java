package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.models.Participante;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;

import java.util.List;

public class ParticipanteService {

    //classse de serviço, nela tem as regras de negócio para o participante, como cadastrar um novo participante,
    // listar todos os participantes e verificar se um participante existe por id.
    // Ela utiliza o repositório de participantes para realizar as operações de persistência.

    private final ParticipanteRepository repositorio;


    public ParticipanteService(ParticipanteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Participante cadastrar(String nome, String email) {
        Participante participante = new Participante();
        participante.setNome(nome);
        participante.setEmail(email);
        repositorio.salvar(participante);
        return participante;
    }

    public List<Participante> listarTodos() {
        return repositorio.listarTodos();
    }

    public boolean existePorId(long id) {
        return repositorio.existeParticipante(id);
    }
}
