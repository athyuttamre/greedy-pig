import java.io.* ;
/**
 * class Start initialises a new game of Greedy Pig. It allows the user
 * to learn the rules, end the game immediately or to play the game.
 * Additionally, if the player decides to play the game, it tosses a coin.
 * The winner of the toss decides which position he/she wants to play in,
 * first or second. Following this, two 'Player' objects, human and comp are
 * created, and, depending on who starts first, their respective start method
 * is invoked upon them. Thus, the Start class sets the table to play a game
 * of Greedy Pig.
 * 
 * The Ornamental.ps(String x) method is a method in the Ornamental class which
 * prints the given text in a user friendly and time-tied manner.
 * 
 * 
 * @author Athyuttam Reddy 
 * @version 1.0
 */
public class Start
{
    /** 
     * char difficulty : A global variable, the character difficulty indicates the difficulty level chosen by the
     * user for the game, by default set to easy (e). Since it is used and edited in multiple
     * methods, it is defined as a static global variable.
     */
   static char difficulty = 'e';

   /** 
    * main : The main method for the class Start, it also serves as the main method for the program. Typically speaking,
    * the main method introduces the game and greets the user, following which it invokes the game() method, which
    * takes over control and begins the actual execution of the game.
    */
   public static void main () throws Exception 
   {
       
       System.out.println("    *           *         ");
       System.out.println("   *  * * * * *  *        ");
       System.out.println("  *                *   GREEDY PIG");
       System.out.println("  *   *       *    *      ");
       System.out.println("  *       *        *      ");
       System.out.println("    *    ****    *        ");
       System.out.println("       *  *   *           ");
         
       Ornamental.pS("\nWelcome to Greedy Pig") ;
       game(); // Invoking the game() method, the method where the actual game commences.
      
    }    
    
    /**
     * game : The game() method commences the game of Greedy Pig. It provides the user with a choice of either continue playing the game,
     * read the rules and then decide or to immediately exit. Therefore, if the user decides to play the game, the program moves on
     * to the toss. If the user decides to read the rules of the game, he/she would be provided with the rules, following which prompted
     * to make the choice again. If the user decides to exit, the program immediately terminates.
     */
    public static void game() throws Exception 
    {
       
       Ornamental.pS("To play the game, type "+ "play ." + "\nTo know the rules of the game, type " + "rules ." + "\nTo exit, type " + "exit");
       
       InputStreamReader in = new InputStreamReader (System.in);
       BufferedReader keyboard = new BufferedReader (in);
       String choice = keyboard.readLine();
             
       if (choice.equalsIgnoreCase("play")) {
           toss() ; // Beginning the game by calling the toss
       }
       else
       if (choice.equalsIgnoreCase("rules")) {
             printRules();  // Printing the rules
             game();    // Invoking the method itself, it reprompts the user to make a choice
       }
       else {
           Ornamental.pS("Thank You");
       }
       
       System.exit (0); // Terminates the program
    }
    
    /**
     *printRules : The method printRules() prints the rules of the game Greedy Pig in a user friendly manner, following which, again prompts the 
     *player to make a choice between play, exit and rules.
     */
    public static void printRules() throws Exception
    {
        Ornamental.pS("\nRules of Greedy Pig : \n", 20);
        Ornamental.pS("- 2 players play with  single six-sided die; aim being the first to reach 100 points.", 20);
        Ornamental.pS("- On a turn, a player rolls the die repeatedly until either:", 20);
        Ornamental.pS("     - A 1 is rolled [THE DEATHLY DICE]", 20);
        Ornamental.pS("     - The player chooses to hold (stop rolling)", 20);
        Ornamental.pS("- If a 1 is rolled, that player's turn ends and no points are earned.", 20);        
        Ornamental.pS("- If the player chooses to hold, all of the points rolled during that turn are added to his or her score and the next player plays.", 20);
        Ornamental.pS("- When a player reaches a total of 100 or more points, the game ends and that player is the winner.\n ", 20);
    }
    
    /** 
     * toss : The toss() method asks the user to provide his/her name, carries out the toss and calls the game for the player who would start the game
     * 
     * The toss is carried out in the following manner :
     *  <ol>
     *  <li>The user is provided with the choice of choosing heads or tails.
     *  <li>If he wins the toss, he is provide with the choice of starting the game or playing second.
     *  <li>Depending on what he chooses, the callGame() method is invoked beginning the game for the first player.
     *  <li>Supposing he loses the toss, the Computer randomly decides to either play first or second and callGame() is invoked.
     */
    public static void toss() throws Exception
    {
        Ornamental.pS("\nAllright then, so you've decided to play !");
        Ornamental.pS("So, to get things going, how about you telling us your name ?");
        
        InputStreamReader in = new InputStreamReader (System.in);
        BufferedReader keyboard = new BufferedReader (in);
        String name = keyboard.readLine();
                
        Ornamental.pS("Alright " + name + "! At what difficulty level would you like to play ? (easy/medium/hard)");
        String diff = keyboard.readLine();
        
        if(diff.equalsIgnoreCase("medium")) 
            difficulty = 'm';
        if (diff.equalsIgnoreCase("hard")) 
            difficulty = 'h';
        else 
            difficulty = 'e';
        
        Ornamental.pS("You want to play " + diff + "? Ok then, make sure you know the rules and....here we go !");
        
        Ornamental.pS("\nTo decide who begins the game, we TOSS A COIN ! To choose heads, type 'heads' or to choose tails, type 'tails'.");
        String tossChoice = keyboard.readLine();
        
        double random = Math.random();
        
        if (random<=0.50)
        { 
            Ornamental.pS("Congratulations! It's " + tossChoice + "! You win the toss ! If you would like to start first, type '1', else type '2'.");
            int startChoice = Integer.parseInt(keyboard.readLine());
            if (startChoice == 1){
                Ornamental.pS("So...you want to start ? Alright, It's PIG TIME !\n");
                callGame(1);
            }
            else {
                Ornamental.pS("You want to play second ? Ok...\n");
                callGame(2);
            }
        }
        
        else
        {
            Ornamental.pS("Uhoh...you've lost the toss !");
            double compChoice = Math.random();
            if(compChoice <= 0.50){
                Ornamental.pS("The computer decides to start ! Here we go...\n");
                callGame(2);
            }
            else {
                Ornamental.pS("The computer wants you to start ! It's PIG TIME !\n");
                callGame(1);
            }
         }
     }
     
     /**
      * callGame : Once the toss is carried out and the player who starts first is decided, the game
      * begins with the calling of callGame. callGame takes an integer plyrPos i.e. the position at which
      * the user is playing, as an input. It creates two 'Player' objects, human and comp. If the user plays
      * first, the start method is invoked on human, else the cStart method is invoked on comp. Thus, the game
      * continues in the Player class.
      * 
      * @param  plyrPos     An integer indicating the position of the user's play, whether first or second.
      */
     public static void callGame(int plyrPos) throws Exception
     {
         Player human = new Player();
         Player comp = new Player();
         if (plyrPos == 1)
            human.start(difficulty, 0,comp); // Calling start method on human. 0 as paramter to indicate not to give the user the choice to give up.
                                             // the comp object as a parameter, for at the end of the turn, the human methods must invoke cStart on comp.
         else
            comp.cStart(difficulty, human);  // Calling cStart methodon comp, beginning the game with the computer as the first player.
      }        

}
