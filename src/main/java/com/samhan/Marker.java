package com.samhan;


public enum Marker {
    X, O;

    public Marker opponent() {
        return this == X ? O : X;
    }
}
