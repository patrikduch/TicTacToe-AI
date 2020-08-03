package com.patrikduch.graphics.ticTacToe.graphics;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class UserInteraction {

    private GridPane mainGridPane;

    public UserInteraction(GridPane maingridPane){

        this.mainGridPane =  maingridPane;
    }


    // Get user choice label information
    public int getLabelPositionId(MouseEvent event){

        String labelId = ((Label) event.getSource()).getId();

        int index = 0;

        for(int i = 0; i< this.mainGridPane.getChildren().size(); i++) {

            if(this.mainGridPane.getChildren().get(i).getId() == labelId){

                index = i;
            }
        }

        return index;
    }




}
