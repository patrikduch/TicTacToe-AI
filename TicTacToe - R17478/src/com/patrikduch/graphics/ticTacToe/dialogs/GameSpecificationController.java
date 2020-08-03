package com.patrikduch.graphics.ticTacToe.dialogs;

import com.patrikduch.graphics.ticTacToe.controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class GameSpecificationController {
    @FXML
    private TextField nickInputTextField;
    @FXML
    private ToggleGroup whoStartsToggleGroup;
    @FXML
    private ToggleGroup whatSymbolToggleGroup;


    @FXML
    public void initialize(){

    }



    public TextField getNickInput(FXMLLoader loader){

        return nickInputTextField;
    }


    public ToggleGroup getWhoStartsToggleGroup(FXMLLoader loader) {

        return whoStartsToggleGroup;
    }

    public ToggleGroup getwhatSymbolToggleGroup(FXMLLoader loader) {

        return whatSymbolToggleGroup;
    }


}
