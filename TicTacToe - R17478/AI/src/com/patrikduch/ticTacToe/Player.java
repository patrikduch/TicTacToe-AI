package com.patrikduch.ticTacToe;

public enum Player {

    COMPUTER("X"), USER("O"), EMPTY("-");

    private final String text;

    Player(String text){

        this.text = text;
    }

    @Override
    public String toString() {
        return this.text; // for returning O,X,-
    }
}
