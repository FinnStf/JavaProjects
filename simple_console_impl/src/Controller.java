import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player winner;
    private Scanner scanner;
    private int moves_played = 0;
    private int[] latestMove = new int[2];

    public Controller(){
        this.scanner = new Scanner(System.in);
        this.board = initBoard();
        this.player1 = new Player(1);
        this.player2 = new Player(2);
        this.currentPlayer = player1;
        initGame();
    }

    private void initGame() {
        System.out.println(board.toString());
        while(winner==null){
            askMove();
            if(moves_played>6){
                decideWinner();
            }
            System.out.println(board.toString());
            currentPlayer=currentPlayer==player1?player2:player1;
        }
        System.out.println("Spieler "+winner.id+" hat gewonnen!");
    }

    private Board initBoard() {
        System.out.println("Wie viele Spalten und Zeilen soll das Spiel haben?");
        int col = scanner.nextInt();
        try{
            if (col>=4) {
                return new Board(col);
            }else {
                throw new IllegalArgumentException("Das Spielbrett muss mindestens 4 Spalten haben!");
            }
        }catch (IllegalArgumentException e){
           System.out.println(e.getMessage());
        }
       return initBoard();
    }

    private void askMove(){
        System.out.println("Spieler "+currentPlayer +": In welche Spalte willst du einwerfen?");
        try {
            doMove(scanner.nextInt());
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("In diese Spalte kann nicht eingeworfen werden");
            askMove();
        }
    }

    private void doMove (int col){
        int column = --col;
        moves_played++;
        for (int i=0; i<board.grid[i].length ;i++) {
            if (board.grid[i][column]==0){
                board.grid[i][column]=currentPlayer.id;
                latestMove[0]=i;
                latestMove[1]=column;
                return;
            }
        }
    }

    private void decideWinner(){
        int row = latestMove[0];
        int column = latestMove[1];
        int board_length = board.grid.length;
        //check row
        count(board.grid[row], "mit Reihe");
        if (winner!=null){
            return;
        }
        //check column
        int[] col = new int[board_length];
        for (int i = 0; i < board_length; i++) {
            col[i] = board.grid[i][column];
        }
        count(col, "über Spalte");
        if (winner!=null){
            return;
        }
        //check diagonals
        ArrayList<Integer> diagLR = new ArrayList<>();
        for (int i=row, j=column; i>=0 && j>=0; i--, j--){
            diagLR.add(board.grid[i][j]);
        }
        for (int i=(row+1), j=(column+1); i<board_length && j<board_length; i++, j++){
            diagLR.add(board.grid[i][j]);
        }
        ArrayList<Integer> diagRL = new ArrayList<>();
        for (int i=row, j=column; i>=0 && j<board_length; i--, j++){
            diagRL.add(board.grid[i][j]);
        }
        for (int i=(row+1), j=(column-1); i<board_length && j>=0; i++, j--){
            diagRL.add(board.grid[i][j]);
        }
        int[] diag1 = diagLR.stream().mapToInt(i -> i).toArray();
        count(diag1, "mit Diagonale");
        int[] diag2 = diagRL.stream().mapToInt(i -> i).toArray();
        count(diag2, "mit Diagonale");
    }

    private void count(int [] row, String msg){
        int row_count = 0;
        for (int i = 0; i< row.length ;i++){
            if (row[i] == currentPlayer.id) {
                row_count += 1;
                if (row_count>=4) {
                    winner = currentPlayer;
                    System.out.println(msg);
                    return;
                }
            } else {
                row_count = 0;
            }
        }
    }
}
