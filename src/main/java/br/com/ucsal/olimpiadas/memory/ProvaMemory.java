package br.com.ucsal.olimpiadas.memory;

import br.com.ucsal.olimpiadas.models.Prova;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProvaMemory implements ProvaRepository {

    private long proximoId = 1;
    private final List<Prova> provas = new ArrayList<>();

    public void salvar(Prova prova) {
        Prova p = new Prova();
        prova.setId(proximoId++);
        prova.setTitulo(p.getTitulo());
        provas.add(p);

    }

    public List<Prova> listarTodos() {
        return Collections.unmodifiableList(provas);
    }

    public boolean existeProva(long id) {
        return provas.stream().anyMatch(p -> p.getId() == id);
    }
}
