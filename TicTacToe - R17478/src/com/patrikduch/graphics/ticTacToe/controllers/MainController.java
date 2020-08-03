package com.patrikduch.graphics.ticTacToe.controllers;

import com.patrikduch.graphics.ticTacToe.dialogs.GameSpecificationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class MainController {

    public class User {

        private String name;
        private String firstMove;
        private String gameSymbol;


        public User(String name, String firstMove, String gameSymbol){

            this.name = name;
            this.firstMove = firstMove;
            this.gameSymbol = gameSymbol;
        }

        public String getName() {
            return name;
        }


        public String getFirstMove() {
            return firstMove;
        }


        public String getGameSymbol() {
            return gameSymbol;
        }
    }


    private User actualUser;

    // For storing information about actual user
    private FileWriter locFile = null;

    // Main scene
    private Scene scene;

    // Main stage
    private Stage stage;

    // Main root
    private Parent root;

    // Loader for main window
    private FXMLLoader newLoader;


    public FXMLLoader getNewLoader() {
        return newLoader;
    }

    // Loader for main dialog game specification
    private FXMLLoader dialogLoader;

    // Instance of game specification dialog
    private Dialog<ButtonType> dialog;


    @FXML
    private BorderPane mainLayout;

    public void setup(Parent root,FXMLLoader newLoader, Scene scene, Stage stage) {

        this.scene = scene;
        this.stage =  stage;
        this.newLoader = newLoader;
        this.root = root;
    }

    public void applyDialog(FXMLLoader dialogLoader){

        this.dialogLoader = dialogLoader;
    }
    @FXML
    private MenuBar mainMenu;

    @FXML
    public void initialize(){ }


    public ArrayList<String> getGameSpecification(){

        GameSpecificationController dialogGameController =
                dialogLoader.<GameSpecificationController>getController();

        ArrayList<String> result = new ArrayList<>();

        // Add nickname from dialog to collection
        result.add(dialogGameController.getNickInput(dialogLoader).getText());

        RadioButton whoStartsRadioButton = (RadioButton) dialogGameController.
                getWhoStartsToggleGroup(dialogLoader).getSelectedToggle();

        RadioButton whatSymbolRadioButton = (RadioButton) dialogGameController.
                getwhatSymbolToggleGroup(dialogLoader).getSelectedToggle();

        // Store who first starts in collection
        result.add(whoStartsRadioButton.getText());
        // Store symbol asociated with specific user in collection
        result.add(whatSymbolRadioButton.getText());

        return result;
    }

    public void startGame() {

        if(dialog == null) { // Fix for repeative opening

            dialog = new Dialog<>();
            dialog.initOwner(mainLayout.getScene().getWindow());
        }

        // Asociate dialog (modular dialog) with current window
        try{
            this.root = dialogLoader.load();
            dialog.getDialogPane().setContent(this.root);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        } catch (Exception exception){ }


        // Which button was clicked? OK or Cancel
        Optional<ButtonType> result = dialog.showAndWait();

        // Get data from dialog controller
        actualUser = new User(getGameSpecification().get(0),
                                   getGameSpecification().get(1),
                                   getGameSpecification().get(2)
        );

        if(result.isPresent() && result.get() == ButtonType.OK) {

            while(actualUser.name.length()== 0) {
                actualUser.name = getGameSpecification().get(0);

                if(actualUser.name.length() != 0) {
                    break;
                }

                dialog.showAndWait();

            }

            // Change the main window to the game board
            try{

                this.root = newLoader.load();
                Scene scene = new Scene(root,1000,475);

                stage.setMinWidth(1000);
                stage.setMinHeight(475);
                stage.setScene(scene);
                stage.setMaximized(true);


            }
            catch (Exception ex) { }


            try{

                // Write information about actual user to the file
                locFile = new FileWriter("actualUser.txt");
                locFile.write(actualUser.name + ",");
                locFile.write(actualUser.firstMove + ",");
                locFile.write(actualUser.gameSymbol);

            } catch (Exception ex) {

                ex.printStackTrace();


            } finally {

                try{

                    if(locFile != null) { // Closing file only if reference is avaliable

                        locFile.close(); // Close stream
                    }

                } catch (IOException e) {

                    e.printStackTrace(); // View exception
                }

            }




        }


    }

}
