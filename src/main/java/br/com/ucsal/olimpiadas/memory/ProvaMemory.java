package br.com.ucsal.olimpiadas.memory;

import br.com.ucsal.olimpiadas.models.Prova;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;

import java.util.ArrayList;
import java.util.List;

public class ProvaMemory implements ProvaRepository {

  //essas classes que fiz de memoria, na verdade são para armazenar os dados de cada classe, nesse
    // caso aqui, é da prova, o proximoId é para ir incrementando o id de cada prova
   // que for criada, e a lista de prova é para armazenar as provas criados


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
