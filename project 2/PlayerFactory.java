public class PlayerFactory {
    


    PlayerFactory(){

    }

    public Player buildPlayer(String type ){
        //TODO: check for type corectness
        String lowerCaseType = type.toLowerCase();
        if(lowerCaseType == "human"){
            Player player = new HumanPlayer();
            return player;
        }
        //TODO: add more player type
        return null;
    }

}
