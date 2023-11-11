


public class Game {
    

    Board board;
    int winStreak;
    static final int DEFULTIVE_WIN_STREAK = 3;
    Player playerX;
    Player playerO;
    Renderer renderer;

    Game(Player playerX , Player playerO , int size , int winStreak ,Renderer rendererType ){
        
        this.board = new Board(size);
        this.winStreak = winStreak;
        this.playerO = playerO;
        this.playerX = playerX;
        this.renderer = rendererType;
    }

    Game(Player playerX , Player playerO , Renderer renderer){
        this.board = new Board();
        this.winStreak = DEFULTIVE_WIN_STREAK;
        this.playerO = playerO;
        this.playerX = playerX;
        this.renderer = renderer;
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
        renderer.renderBoard(board);

        while(!isBoardFull(board))
        {
            
            players[currentPlaying].playTurn(board, marks[currentPlaying]);
            //TODO: check if a player won
            if(checkWinCondition()){
                renderer.renderBoard(board);
                return marks[currentPlaying];
            }
            currentPlaying = (currentPlaying + 1) % 2;
            renderer.renderBoard(board);
        }
        
        return Mark.BLANK;
    }

    private Boolean checkWinCondition(){
        //check if the mark that we put in (row,col) got winstreak
        for(int row = 0 ; row < board.getSize() ; row++)
        {
            for(int col = 0 ; col < board.getSize() ; col++)
            {
                if(cellWinCondition(row, col))
                {
                    return true;
                }
            }
        }
        return false;



    }
    private boolean cellWinCondition(int row , int col)
    {
        if(cellRowWinCondition(row, col) || cellcolWinCondition(row, col) || cellDiagnolWinCondition(row, col))
        {
            return true;
        }
        return false;

        
        
        
        
    }


    private boolean cellcolWinCondition(int row , int col)
    //check if there is a col of the same mark (X or O) from the starting cell (row,col)
    {
        int counter = 0;
        Mark winConditionmark = board.getMark(row, col);
        if( winConditionmark == Mark.BLANK)
        {
            return false;
        }

        for(int i = 0 ; i < winStreak ; i++ )
        {
            if(checkValidCell(row, col + i))
            {
                if(board.getMark(row, col + i) == winConditionmark)
                {
                    counter++;
                }   
            }
        }
        if(counter == winStreak)
        {
            return true;
        }
        return false;
    }


    private boolean cellDiagnolWinCondition(int row , int col)
    //check if there is a diagnol of the same mark (X or O) from the starting cell (row,col)
    {
        int counter1 = 0;
        int counter2 = 0;
        Mark winConditionmark = board.getMark(row, col);
        if( winConditionmark == Mark.BLANK)
        {
            return false;
        }

        for(int i = 0 ; i < winStreak ; i++ )
        {
            if(checkValidCell(row+i, col + i))
            {
                if(board.getMark(row + i, col + i) == winConditionmark)
                {
                    counter1++;
                }   
            }
            if(checkValidCell(row+i, col - i))
            {
                if(board.getMark(row + i, col - i) == winConditionmark)
                {
                    counter2++;
                }   
            }
        }
        if(counter1 == winStreak || counter2 == winStreak)
        {
            return true;
        }
        return false;
    }
    
    

    
    private boolean cellRowWinCondition(int row , int col)
    //check if there is a row of the same mark (X or O) from the starting cell (row,col)
    {
        int counter = 0;
        Mark winConditionmark = board.getMark(row, col);
        if( winConditionmark == Mark.BLANK)
        {
            return false;
        }

        for(int i = 0 ; i < winStreak ; i++ )
        {
            if(checkValidCell(row+i, col))
            {
                if(board.getMark(row + i, col) == winConditionmark)
                {
                    counter++;
                }   
            }
        }
        if(counter == winStreak)
        {
            return true;
        }
        return false;
    }
    

    private boolean checkValidCell(int row, int col)
    {
        if((row <board.getSize()) && (col < board.getSize()) )
        {
            return true;
        }
        return false;
        
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
