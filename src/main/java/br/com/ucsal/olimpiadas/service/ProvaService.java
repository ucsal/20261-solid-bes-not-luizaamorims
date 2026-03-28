package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.models.Prova;
import br.com.ucsal.olimpiadas.models.Questao;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;

import java.util.List;

public class ProvaService {
    //classe de serviço para gerenciar as provas e questões, com métodos para cadastrar provas e questões,
    // listar provas e questões por prova, e verificar a existência de uma prova por ID

    private final ProvaRepository provaRepo;
    private final QuestaoRepository questaoRepo;

    public ProvaService(ProvaRepository provaRepo, QuestaoRepository questaoRepo) {
        this.provaRepo = provaRepo;
        this.questaoRepo = questaoRepo;
    }

    public Prova cadastrarProva(String titulo) {
        Prova prova = new Prova();
        prova.setTitulo(titulo);
        provaRepo.salvar(prova);
        return prova;
    }

    public Questao cadastrarQuestao(long provaId, String enunciado,
                                 String[] alternativas, char correta,
                                 String fenInicial) {
        if (!provaRepo.existeProva(provaId)) {
            throw new IllegalArgumentException("prova não encontrada: " + provaId);
        }
        Questao Questao = new Questao();
        Questao.setProvaId(provaId);
        Questao.setEnunciado(enunciado);
        Questao.setAlternativas(alternativas);
        Questao.setAlternativaCorreta(correta);
        Questao.setFenInicial(fenInicial);
        questaoRepo.salvar(Questao);
        return Questao;
    }

    public List<Prova> listarProvas() {
        return provaRepo.listarTodos();
    }

    public List<Questao> listarQuestoesDaProva(long provaId) {
        return questaoRepo.listarPorIdProva(provaId);
    }

    public boolean existeProvaPorId(long id) {
        return provaRepo.existeProva(id);
    }
}

