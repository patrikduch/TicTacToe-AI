package com.patrikduch.graphics.ticTacToe.graphics;

import com.patrikduch.graphics.ticTacToe.graphics.interfaces.IPaintable;
import com.patrikduch.graphics.ticTacToe.graphics.interfaces.IWinnable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PaintByUser implements IPaintable, IWinnable {

    private String symbol;
    private String symbolColor;

    public PaintByUser(String symbol, String symbolColor) {
        this.symbol = symbol;
        this.symbolColor = symbolColor;
    }

    @Override
    public void fillCell(GridPane mainGridPane, int column, int row) {

        throw new NotImplementedException();
    }

    @Override
    public void fillCell(Label actuaLabel, GridPane mainGridPane) {

        actuaLabel.setText(this.symbol);
        actuaLabel.setTextFill(Paint.valueOf(this.symbolColor));
        actuaLabel.setFont(Font.font("Verdana", 120));
        actuaLabel.setAlignment(Pos.CENTER);
    }


    public void draw() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Byla nastolena remíza", ButtonType.OK);
        alert.setHeaderText("Konec hry");
        alert.setTitle("Podařilo se vám jen remizovat.");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        // Show win dialog
        alert.showAndWait();

    }

    @Override
    public void win() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Gratulujeme! Zvítěžil jste.", ButtonType.OK);
        alert.setHeaderText("Konec hry");
        alert.setTitle("Porazil jste UI.");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        // Show win dialog
        alert.showAndWait();
    }
}
