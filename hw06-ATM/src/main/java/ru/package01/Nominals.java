package ru.package01;

public enum Nominals {
    nom50(50),

    nom100(100),

    nom200(200),

    nom500(500),

    nom1000(1000),

    nom2000(2000),

    nom5000(5000);

    private int nominal;

    Nominals(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }
}
