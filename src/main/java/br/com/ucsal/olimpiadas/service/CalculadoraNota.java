package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.models.Tentativa;

public class CalculadoraNota {

    public int calcular(Tentativa tentativa) {
        int acertos = 0;
        for (Tentativa r : tentativa.getRespostas()) {
            if (r.isCorreta()) acertos++;
        }
        return acertos;
    }


}
