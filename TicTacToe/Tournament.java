import java.util.Arrays;

class Tournament{

    int numberOfRounds;
    Renderer rendererType;
    Player[] allPlayers;
    int[] wins;
    int ties;



    Tournament(int rounds , Renderer renderer, Player[] players){
        numberOfRounds= rounds;
        rendererType = renderer;
        allPlayers = players;
        wins = new int[2];
        Arrays.fill(wins, 0);
        ties = 0;
    }


    public void playTournament(int size , int winStreak, String[] playerNames)
    {
        
        int i = 0;
        
        while(numberOfRounds>0)
        {
            Game game = new Game(allPlayers[i], allPlayers[(i+1)%2], size, winStreak, rendererType);
            Mark winner = game.run();
            //check who won the gamme
            if(winner == Mark.X)
            {
                wins[i]++;
            }
            if(winner == Mark.O)
            {
                wins[(i+1)%2]++;
            }
            if (winner == Mark.BLANK) {
                ties++;
            }
            //update who is X player and who is O , and update the number of games left to play in the tournment
            i = (i+1)%2;
            numberOfRounds--;
        }

        printResults(playerNames);


    }

    private void printResults(String[] playerNames)
    {
        System.out.println("######### Results #########");
        System.out.println("Player 1, " + playerNames[0] +" won: " + wins[0] );
        System.out.println("Player 2, " + playerNames[1] +" won: " + wins[1]);
        System.out.println("Ties: " + ties);
    }


    public static void main(String[] args){
        
        Test test = new Test();
        test.runTest();
        
        

    }
}