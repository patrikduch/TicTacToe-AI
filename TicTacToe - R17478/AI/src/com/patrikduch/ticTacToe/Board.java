package com.patrikduch.ticTacToe;

import java.util.*;

public class Board {

    private List<Cell> emptyCells;
    private Player[][] board;
    private List<Cell> rootValues;

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        this.rootValues = new ArrayList<>();
        this.board = new Player[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        setupBoard(); // Setup default value for every cell
    }

    public boolean isRunning() {

        if( isWinning(Player.COMPUTER) ) return false;
        if( isWinning(Player.USER)) return false;
        if( getEmptyCells().isEmpty() )return false;

        return true;
    }

    public boolean isWinning(Player player) {

        // checking for first diagonal
        if ( board[0][0] == player && board[1][1] == player && board[2][2] == player ) {
            return true;
        }

        // checking for second diagonal
        if( board[0][2] == player && board[1][1] == player && board[2][0] == player ){
            return true;
        }

        for (int i = 0; i < Constants.BOARD_SIZE; i++) {

            // checking the rows
            if ( board[i][0] == player && board[i][1] == player && board[i][2] == player ) {
                return true;
            }

            // checking the columns
            if( board[0][i] == player && board[1][i] == player && board[2][i] == player ){
                return true;
            }
        }

        return false;
    }

    public List<Cell> getEmptyCells() {

        emptyCells = new ArrayList<>();

        for (int i = 0; i < Constants.BOARD_SIZE; ++i) {
            for (int j = 0; j < Constants.BOARD_SIZE; ++j) {
                if (board[i][j] == Player.EMPTY) {
                    emptyCells.add(new Cell(i, j));
                }
            }
        }
        return emptyCells;
    }

    public void move(Cell point, Player player) {
        board[point.getX()][point.getY()] = player;
    }

    public Cell getBestMove() { // Call after the tree is reconstructed

        int max = Integer.MIN_VALUE;
        int best = Integer.MIN_VALUE;

        for (int i = 0; i < rootValues.size(); ++i) {
            if (max < rootValues.get(i).getMinimaxValue()) {
                max = rootValues.get(i).getMinimaxValue();
                best = i;
            }
        }

        return rootValues.get(best);
    }


    public int returnMin(List<Integer> list) {

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        return list.get(0);
    }

    public int returnMax(List<Integer> list) {

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        return list.get(0);
    }

    public void callMinimax(int depth, Player player){
        rootValues.clear();
        minimax(depth, player);
    }

    public int minimax(int depth, Player player) {

        if (isWinning(Player.COMPUTER)) return +1;
        if (isWinning(Player.USER)) return -1;

        List<Cell> availableCells = getEmptyCells();

        if (availableCells.isEmpty()) return 0;

        List<Integer> scores = new ArrayList<>();

        for (int i = 0; i < availableCells.size(); i++) {

            Cell point = availableCells.get(i);

            if (player == Player.COMPUTER) { //X's turn select the highest from below minimax() call
                move(point, Player.COMPUTER);
                int currentScore = minimax(depth + 1, Player.USER);
                scores.add(currentScore);

                if (depth == 0) {
                    point.setMinimaxValue(currentScore);
                    rootValues.add(point);
                }

            } else if (player == Player.USER) {//O's turn select the lowest from below minimax() call
                move(point, Player.USER);
                scores.add(minimax(depth + 1, Player.COMPUTER));
            }

            board[point.getX()][point.getY()] = Player.EMPTY; //Reset this point
        }

        if( player == Player.COMPUTER ){
            return returnMax(scores);
        }

        return returnMin(scores);
    }

    public List<Cell> getAvailablePoints() {
        return emptyCells;
    }

    public void setAvailablePoints(List<Cell> availablePoints) {
        this.emptyCells = availablePoints;
    }

    public void setupBoard() {
        for(int i=0;i<Constants.BOARD_SIZE;i++){
            for(int j=0;j<Constants.BOARD_SIZE;j++){
                board[i][j] = Player.EMPTY;
            }
        }
    }

    //getters

    public Player[][] getBoard() {
        return board;
    }

    public void setBoard(Player[][] board) {
        this.board = board;
    }

    public List<Cell> getRootValues(){
        return this.rootValues;
    }
}