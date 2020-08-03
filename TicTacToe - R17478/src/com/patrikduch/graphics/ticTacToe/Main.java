package com.patrikduch.graphics.ticTacToe;
import com.patrikduch.graphics.ticTacToe.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Main fxml
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "fxmls/main.fxml"
                )
        );

        // Game fxml
        FXMLLoader gameLoader = new FXMLLoader(
                getClass().getResource(
                        "fxmls/game.fxml"
                )
        );

        // Game specification dialog fxml
        FXMLLoader endGameDialog = new FXMLLoader(
                getClass().getResource(
                        "/com/patrikduch/graphics/ticTacToe/dialogs/gameSpecificationDialog.fxml"
                )
        );


        // To get reference to the  controller we need  to load to the parent
        Parent root =  loader.load();

        // Title specification
        primaryStage.setTitle("UMINT Projekt (Piškvorky) - R17478");

        MainController controller =
                loader.<MainController>getController();

        Scene scene = new Scene(root,1000,475);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(475);

        // Passing data to controller
        controller.setup(root,gameLoader,scene, primaryStage);
        controller.applyDialog(endGameDialog);

        primaryStage.show();
    }


    public static void main(String[] args) {



            launch(args);






    }
}
