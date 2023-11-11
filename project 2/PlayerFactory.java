public class PlayerFactory {
    


    PlayerFactory(){

    }


    public Player buildPlayer(String type ){
        //TODO: check for type corectness
        String lowerCaseType = type.toLowerCase();
        Player player;
        switch (lowerCaseType) {
            case "human":
                player = new HumanPlayer();
                return player;
                
            case "clever":
                player = new CleverPlayer();
                return player;
                
            
            case "whatever":
                player = new WhateverPlayer();
                return player;
                
                
            case "genius":
                player = new GeniusPlayer();
                return player;
            default:
                System.out.println("no player like that");
                break;
        }
        //TODO: add more player type
        return null;
    }

}
