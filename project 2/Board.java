

public class Board {
    static final int DEFULTIVE_SIZE = 4;

    Mark[][] board;
    int size;

    Board(){

        this.board = getEmptyBoard(DEFULTIVE_SIZE);
        this.size = DEFULTIVE_SIZE;
        
    }

    Board(int size){
        
        this.board = getEmptyBoard(size);
        this.size = size;
    }

    public int getSize(){
        return size;
    }

    public Mark[][] getBoard(){
        return board;
    }
    
    public boolean putMark(Mark mark , int row , int col){
        if(isCellEmpty(row, col))
        {
            board[row][col] = mark;
            return true;
        }
        return false;
    }   

    public Mark getMark(int row, int col){
        if((row < 0 ||row > size - 1) || (col < 0 || col > size -1  )){
            return Mark.BLANK;

        }
        return board[row][col];
    }

    private Mark[][] getEmptyBoard(int size)
    {
        Mark[][] emptyBoard = new Mark[size][size];
        for(int row = 0 ; row < size ; row++)
        {
            for(int col = 0 ; col < size ; col++){
                emptyBoard[row][col] = Mark.BLANK;
            }
        }
        return emptyBoard; 
    }


    private boolean isCellEmpty(int row , int col){
        if(board[row][col] != Mark.BLANK)
        {
            return false;
        }
        return true;
    }
}
