package com.samhan;


public enum Marker {
    X, O, EMPTY;

    public Marker opponent() {
        return this == X ? O : X;
    }
}
