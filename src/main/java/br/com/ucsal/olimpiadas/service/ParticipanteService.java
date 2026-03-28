package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.models.Participante;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;

import java.util.List;

public class ParticipanteService {

    private final ParticipanteRepository repositorio;

    public ParticipanteService(ParticipanteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(String nome, String email) {
        Participante participante = new Participante();
        participante.setNome(nome);
        participante.setEmail(email);
    }

    public List<Participante> listarTodos() {
        return repositorio.listarTodos();
    }

    public boolean existePorId(long id) {
        return repositorio.existeParticipante(id);
    }
}
