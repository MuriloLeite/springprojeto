package com.site.springprojeto.verificar;

import com.site.springprojeto.models.entity.Jogo;

public class GeneroAndClassificacoesVerificar {

    private static final String[] GENEROS = {
            "Ação",
            "Aventura",
            "RPG",
            "Estratégia",
            "Simulação",
            "Corrida",
            "Esporte",
            "Puzzle",
            "Plataforma",
            "Indie",
            "Casual",
            "MMORPG",
            "FPS",
            "MOBA",
            "Battle Royale",
            "Sobrevivência",
            "Horror",
            "Open World"
    };

    private static final String[] CLASSIFICACOES = {
            "10",
            "12",
            "14",
            "16",
            "18",
            "L"
    };

    public static boolean isValidGenre(String genero) {
        for (String generos : GENEROS) {
            if (generos.equalsIgnoreCase(genero)) {
                return true;
            }
        }
        return false;
    }

    public static String getGenero(Jogo jogo) {
        return jogo.getGenero();
    }

    public static String getClassificacao(Jogo jogo) {
        return jogo.getClassificacao();
    }

    public static boolean isValidClassificacao(String classificacao) {
        for (String classif : CLASSIFICACOES) {
            if (classif.equalsIgnoreCase(classificacao)) {
                return true;
            }
        }
        return false;
    }
}
