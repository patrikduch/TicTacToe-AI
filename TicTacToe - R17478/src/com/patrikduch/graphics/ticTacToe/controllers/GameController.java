package com.patrikduch.graphics.ticTacToe.controllers;

import com.patrikduch.graphics.ticTacToe.funcInterfaces.IGameInit;
import com.patrikduch.graphics.ticTacToe.graphics.*;
import com.patrikduch.ticTacToe.Board;
import com.patrikduch.ticTacToe.Cell;
import com.patrikduch.ticTacToe.Constants;
import com.patrikduch.ticTacToe.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.*;

public class GameController {


    private Board board;

    // Dialog properties
    private Parent root;
    private FXMLLoader dialogLoader;

    @FXML
    private GridPane mainGridPane;


    // Implementing function interface (IGameInit)

    IGameInit iGameInit  = ()-> {

        board = new Board();
        board.setupBoard();
    };


    @FXML
    public void initialize() { // Game initialization

        iGameInit.init();
    }

    @FXML
    public void chooseCell(MouseEvent event) {


        // Get choosen cell by user
        Label actualLabel = (Label) event.getSource();

        // Get information about selected cell
        UserInteraction userInteraction = new UserInteraction(mainGridPane);
        int index = userInteraction.getLabelPositionId(event); // Single coordinate

        int[] coordinateArray = Conversions.convertToCellGrid(index); // Get grid-coordinate
        Cell cell = new Cell(coordinateArray[0], coordinateArray[1]);
        board.move(cell, Player.USER);

        // Display users choosen cell
        PaintByUser paintByUser = new PaintByUser("O","BLUE");
        paintByUser.fillCell(actualLabel, mainGridPane);

        if(board.isWinning(Player.USER)) {

            paintByUser.win();
        }

        if(board.getEmptyCells().size() == 0) {

            paintByUser.draw();
        }


        try
        {
            // Computer reaction
            board.callMinimax(0, Player.COMPUTER);
            Cell cellx = board.getBestMove();

            board.move(board.getBestMove(),Player.COMPUTER);

            PaintByComputer paintByComputer = new PaintByComputer("X","PURPLE");
            paintByComputer.fillCell(mainGridPane, cellx.getX(),cellx.getY());


            if(board.isWinning(Player.COMPUTER)) {

                paintByComputer.win(); // Display winning message
            }

        } catch (Exception ex) {}

    }


    public void deleteCellContent(){

        for(int i = 0; i<3; i++) {

            Label actuaLabel = (Label)GuiManipulation.getNodeFromGridPane(mainGridPane,i, 0);
            actuaLabel.setText("");

            actuaLabel = (Label)GuiManipulation.getNodeFromGridPane(mainGridPane,i, 1);
            actuaLabel.setText("");

            actuaLabel = (Label)GuiManipulation.getNodeFromGridPane(mainGridPane,i, 2);
            actuaLabel.setText("");

        }
    }




    public void resetGame(){

        deleteCellContent();
        board.setupBoard();
    }


    public void saveGame(){

        try{

            PrintWriter writer = new PrintWriter("gameSetup.txt", "UTF-8");

            for (Player[] o : board.getBoard()) {

                writer.write(o[0].toString());
                writer.write(o[1].toString());
                writer.write(o[2].toString());

            }

            writer.close();

        } catch (Exception ex) {

        }
    }


    public void loadGame() throws IOException {

        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader("gameSetup.txt"));
        try {

            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

        } finally {
            br.close();
        }


        for(int i = 0; i<= 8; i++) {

            char foundedCharacter = sb.toString().charAt(i);


        }


















    }




}
