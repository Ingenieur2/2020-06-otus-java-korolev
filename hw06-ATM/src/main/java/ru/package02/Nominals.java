package ru.package02;

public enum Nominals {

    nom5000(5000),
    nom2000(2000),
    nom1000(1000),
    nom500(500),
    nom200(200),
    nom100(100),
    nom50(50);

    int value;

    Nominals(int value) {
        this.value = value;
    }
}
