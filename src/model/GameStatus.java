package model;

public enum GameStatus {

    NON_STARTED("Não iniciado"),
    INCOMPLETE("Incompleto"),
    COMPLETE("Completo");

    private String label;

    GameStatus(final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
