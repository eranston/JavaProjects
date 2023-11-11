import java.util.Random;


public class CleverPlayer implements Player {

    Random rand;

    CleverPlayer()
    {
        rand = new Random();

    }

    public void playTurn(Board board , Mark mark)
    {


        if(!startegy(board, mark))
        {
            //put a randomg mark on the screen
        
            randomChoice(board, mark);
        }
        
        

    }   


    private boolean startegy(Board board, Mark mark)
    //check if you can win with 1 move
    {
        
        for(int col = 0; col <board.getSize() ; col++)
        {
            Mark currentMark = board.getMark(0, col);
            if((currentMark != mark) && (currentMark != Mark.BLANK))
            {
                return false;
            }
            if(currentMark == Mark.BLANK)
            {
                board.putMark(mark, 0, col);
                return true;
            }
        }
        return false;   

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
