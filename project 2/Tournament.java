class Tournament{

    int numberOfRounds;
    Renderer rendererType;
    Player[] allPlayers;



    Tournament(int rounds , Renderer renderer, Player[] players){
        numberOfRounds= rounds;
        rendererType = renderer;
        allPlayers = players;
    }


    public void playTournament(int size , int winStreak, String[] playerNames)
    {
        Player[] players = createplayers(playerNames);
        this.allPlayers = players;
        Game game = new Game(allPlayers[0], allPlayers[1], size, winStreak, null);
        game.




    }


    private Player[] createplayers(String[] playerNames) {
        //TODO: check if need the function be static or not
        PlayerFactory playerFactory = new PlayerFactory();
        Player[] players = new Player[playerNames.length];
        for(int i = 0 ; i< playerNames.length ; i++)
        {
            players[i] = playerFactory.buildPlayer(playerNames[i]);

        }
        return players;
    }
    public static void main(String[] args){
        
        
        //TODO: getting input form main
        String[] playerNames = {"human","human"};
        int winStreak = 3;
        int size = 4;
        Renderer rend = new ConsoleRenderer(size); //TODO: change this to renderer factory
        Tournament tournament = new Tournament(1, rend, new Player[0]);
        tournament.playTournament(size,winStreak , playerNames);
        //playTournament(size , winStreak , playerNames);
        
        

    }
}