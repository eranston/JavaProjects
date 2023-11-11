import java.util.Random;


public class WhateverPlayer implements Player {

    Random rand;

    WhateverPlayer()
    {
        rand = new Random();

    }

    public void playTurn(Board board , Mark mark)
    {
        //put random choice on the board
        randomChoice(board, mark);
        

    }   

    private void randomChoice(Board board , Mark mark)
    {
        while(true)
        {
            int row = rand.nextInt(board.getSize());
            int col = rand.nextInt(board.getSize());
            if(board.getMark(row, col) == Mark.BLANK)
            {
                board.putMark(mark, row, col);
                break;
            }
        }
    }


    
    
    
}
