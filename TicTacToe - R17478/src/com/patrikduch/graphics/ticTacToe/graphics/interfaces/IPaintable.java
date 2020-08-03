package com.patrikduch.graphics.ticTacToe.graphics.interfaces;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Interface for filling the gaming-board
 *
 * <pre>
 * first = column, second = row
 *
 * 0,0 1,0 2,0
 * 0,1 1,1 2,1
 * 0,2 1,2 2,2
 * </pre>
 */
public interface IPaintable {

    /**
     * Chooses cell by filling up specific symbol defined by user or default for computer.
     *
     * @param mainGridPane GridPane on which is applied to
     * @param column cell column definition
     * @param row cell row definition
     * @return no value is returned
     *
     *
     */
    void fillCell(GridPane mainGridPane, int column, int row);
    void fillCell(Label actuaLabel,GridPane mainGridPane);

}
