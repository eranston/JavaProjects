import javax.swing.Renderer;


public class Game {
    

    Board board;
    int winStreak;
    static final int DEFULTIVE_WIN_STREAK = 3;
    Player playerX;
    Player playerO;

    
    Game(Player playerX , Player playerO , Renderer renderer){
        board = new Board();
        this.winStreak = DEFULTIVE_WIN_STREAK;
        this.playerO = playerO;
        this.playerX = playerX;
    }

    Game(Player playerX , Player playerO , int size , int winStreak ,Renderer renderer ){
        
        board = new Board(size);
        this.winStreak = winStreak;
        this.playerO = playerO;
        this.playerX = playerX;
    }

    public int getWinStreak(){
        return winStreak;
    }

    public Mark run()
    {
        int numberOfEmptyCells = board.size * board.size;
        Mark[] marks = {Mark.X , Mark.O};
        Player[] players = {playerX , playerO};
        int currentPlaying = 0;
        while(!isBoardFull(board))
        {
            players[currentPlaying].playTurn(board, marks[currentPlaying]);
            //TODO: check if a player won
            if(checkWinCondition(board)){
                return marks[currentPlaying];
            }
            currentPlaying = (currentPlaying + 1) % 2;
        }
        return Mark.BLANK;
    }

    private Boolean checkWinCondition(Board boardToCheck){
        //check if the mark that we put in (row,col) got winstreak



    }
    private boolean cellWinCondition(Board boardToCheck , int row , int col)
    {
        
    }


    private boolean isBoardFull(Board board){
        
        Mark[][] currentBoard = board.getBoard();
        for(int row = 0 ; row < board.getSize() ; row++){
            for(int col = 0; col < board.getSize() ; col++){
                if(currentBoard[row][col] == Mark.BLANK)
                {
                    return false;
                }
            }
        }
        return true;
    }
    

}
