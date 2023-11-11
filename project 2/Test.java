public class Test {

    Test()
    {

    }
    public int runTest() {
        
        int counter = 0;

        if(handTest())
        {
            counter++;
        }

        return counter;

        
        
    }
    
    private boolean handTest()
    {
        //TODO: getting input form main
        String[] playerNames = {"genius","whatever"};
        PlayerFactory playerFactory = new PlayerFactory();
        Player[] players = new Player[playerNames.length];
        for(int i = 0 ; i< playerNames.length ; i++)
        {
            players[i] = playerFactory.buildPlayer(playerNames[i]);

        }
        int winStreak = 3;
        int size = 4;
        Renderer rend = new VoidRenderer(); //TODO: change this to renderer factory
        Tournament tournament = new Tournament(10000, rend, players);
        tournament.playTournament(size,winStreak , playerNames);
        //playTournament(size , winStreak , playerNames);
        return true;
    }

}
