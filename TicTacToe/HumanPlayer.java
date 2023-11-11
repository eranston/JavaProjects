import java.util.Scanner;

public class HumanPlayer implements Player{


    HumanPlayer(){

    }

    public void playTurn(Board board , Mark mark){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + mark + ", type coordinates: ");
        while(true)
        {
            String input = scanner.nextLine();
            int row = Character.getNumericValue(input.charAt(0));
            int col = Character.getNumericValue(input.charAt(1));
            if(checkValidInput(board ,row, col))
            {
                if(board.putMark(mark , row ,col))
                {
                    break;
                }
            } 
            System.out.println("invalid coordinates, type again: ");  
        }


        //TODO: check if input is valid


    }
    
    private boolean checkValidInput(Board board ,int row , int col)
    {
        return ((row<board.getSize())&&(col<board.getSize())&&(row>=0)&&(col>=0)); 
    }
}
