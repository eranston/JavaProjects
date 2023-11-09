import java.util.Scanner;

class Chat {

    public static void main(String[] args) {
        ChatterBot[] bots = new ChatterBot[2];
        String[] bot1IllegalReplyString = {"what <request> , ok <request>" ,"say <request> ,should I say"};
        String[] bot2IllegalReplyString = {"whaaat <request>", "say <request> say" };
        String[] bot1LeagalReplyString = {"say <phrase>? okay: <phrase>" , "allright i will say it , <phrase>"};
        bots[0] = new ChatterBot("Bob" , bot1LeagalReplyString, bot1IllegalReplyString  );
        bots[1] = new ChatterBot("Allice" , bot1LeagalReplyString, bot2IllegalReplyString  );

        String statement = "say door";


        Scanner scanner = new Scanner(System.in);
        while(true) {
            for(ChatterBot bot : bots) {
                
                statement = bot.replyTo(statement);
                System.out.print(bot.name + ": ");
                System.out.print(statement);
                scanner.nextLine();
                }
        }

        

    }

}