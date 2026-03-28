package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.models.Resposta;
import br.com.ucsal.olimpiadas.models.Tentativa;

public class CalculadoraNota {

    //classe para calcular a nota de uma tentativa, contando o número de respostas corretas

    public int calcular(Tentativa tentativa) {
        int acertos = 0;
        for (Resposta resposta : tentativa.getRespostas()) {
            if (resposta.isCorreta()) {
                acertos++;
            }
        }
        return acertos;
    }


}
