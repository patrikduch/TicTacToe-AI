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

public class PaintByComputer implements IWinnable, IPaintable {

    private String symbol;
    private String symbolColor;


    public PaintByComputer(String symbol, String symbolColor){

        this.symbol = symbol;
        this.symbolColor = symbolColor;
    }


    @Override
    public void win() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Počítač zvítěžil", ButtonType.OK);
        alert.setHeaderText("Konec hry");
        alert.setTitle("UI vás porazilo");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        // Show win dialog
        alert.showAndWait();

    }


    @Override
    public void fillCell(GridPane mainGridPane, int column, int row) {

        Label actuaLabel = (Label) GuiManipulation.getNodeFromGridPane(mainGridPane,column, row);
        actuaLabel.setText(this.symbol);
        actuaLabel.setTextFill(Paint.valueOf(this.symbolColor));
        actuaLabel.setFont(Font.font("Verdana", 120));
        actuaLabel.setAlignment(Pos.CENTER);
    }

    @Override
    public void fillCell(Label actuaLabel, GridPane mainGridPane) {
        throw new NotImplementedException();
    }

}
