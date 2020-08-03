package com.patrikduch.graphics.ticTacToe.graphics;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GuiManipulation {


    public User getUser(){
        return user;
    }

    private Computer computer;
    private User user;



    public GuiManipulation(){




    }


    // Method to get node from specific row and column

    public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }



    public void draw(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Remíza", ButtonType.OK);
        alert.setHeaderText("Konec hry");
        alert.setTitle("Byla nastolena remíza");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        // Show draw dialog
        alert.showAndWait();
    }





    public class Computer
    {
        private int[] computerFirstMoveSequence;

        // Collection to store all labels for cell manipulation
        private List<String> labelNamesList;


        public Computer(){
            computerFirstMoveSequence = new int[2];
            labelNamesList = new ArrayList<>();
        }








        // Method to get label from the Fxml file
        public Label getLabel(String labelCode, FXMLLoader loader) {

            Label firtElementFirstRow = (Label) loader.getNamespace().get(labelCode);
            return firtElementFirstRow;
        }









    }



    public class User {

        private List<String> labelList;

        public User(){

            this.labelList = new ArrayList<>();
        }






    }


}
