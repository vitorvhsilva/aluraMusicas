package alura.musica.desafio.model;

public enum Tipo {
    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("banda");

    private String tipo;

    Tipo(String tipo) {
        this.tipo = tipo;
    }

    public static Tipo fromString(String text) {
        for (Tipo tipoPego : Tipo.values()) {
            if (tipoPego.tipo.equalsIgnoreCase(text)) {
                return tipoPego;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
