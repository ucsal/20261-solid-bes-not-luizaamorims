package br.com.ucsal.olimpiadas.memory;

import br.com.ucsal.olimpiadas.models.Prova;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;

import java.util.ArrayList;
import java.util.List;

public class ProvaMemory implements ProvaRepository {

    private long proximoId = 1;
    private final List<Prova> provas = new ArrayList<>();

    public void salvar(Prova prova) {
        prova.setId(proximoId++);
        provas.add(prova);
    }

    public List<Prova> listarTodos() {
        return new ArrayList<>(provas);
    }

    public boolean existeProva(long id) {
        for (Prova p : provas) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
