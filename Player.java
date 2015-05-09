import java.io.* ;
/**
 * The Player class takes control of the whole process of the game. It executes all the turns, 
 * does the calculations, and displays the result of the game, following which it prompts the 
 * user to decide to play again or to exit.
 * 
 * The Player class provides the template for any player of the game, whether the user or the
 * computer. Although part of the same class, the two objects representing the human the computer
 * will be operating on unique methods during the execution of the game. This class imports 
 * java.io package for it takes input throughout. A Player object has the instance varibles
 * int turnScore, int totalScore, and Dice dice(); turnScore records the points collected by the player
 * in that particular turn upto that point,and is dynamically changed with each roll. totalScore stores 
 * the total score of the player, but is not changed with every roll, for if the player rolls 1, the score
 * must be reverted to the original. Instead, the turnScore is updated only at the end of the player's turn.
 * Dice dice is the personal dice of the player, and holds the dice value. Functions such as randomRoll() are 
 * performed on it.
 * 
 * All across the class, Strings are printed using the method Ornamentla.pS , which serves to print the String
 * at varying speed, slowly or fast.
 *   
 * @author Athyuttam Reddy
 * @version 2.6
 */

public class Player 
{
    /**
     * The score accumulated in the current turn so far by the player
     */
    int turnScore;
    /**
     * The total score of the player as of the previous turn
     */
    int totalScore;
    /**
     * The dice of the player ; it is rolled in the game and holds a value 1-6
     */
    Dice dice = new Dice(); 
    /**
     * A global variable indicating the difficulty level chosen by the user ('e' easy ; 'm' medium; 'h' hard)
     */
    static char difficulty = 'e';
    
    /**
     * A basic constructor for the Player class, initialising all values of the Player object's instance variables
     * to 0 excepting the value of the dice roll, for a dice can never be 0. Therefore, the dice is set to 1.
     */    
    public Player() 
    {
        this.turnScore = 0;          
        this.turnScore = 0;
        this.dice.value = 1 ;
    }

 // THE FOLLOWING METHODS EXECUTE THE HUMAN'S PLAY ////////////////////////////////////////////////////////////////////////////////////////////////   
    
    /**
     * start : This method is begins the play for the human in his turn. It takes as input
     * char diff, indicating the difficulty level chosen by the player, and assigns the value
     * to the global variable difficulty in this class. This is done since the difficulty level
     * is referred to many times and across the class. 
     * <br>
     * It also takes as input int status, which indicates wether to offer the human the chance to give 
     * up in this turn or not(1 to offer,Anything else to not offer). In the very first turn of the player, if 
     * he starts first, the option is not offered and thus the method is called with status as 0 ; 
     * in all other occurences, it is called with status 1.
     * <br>
     * Finally, this method is invoked upon the Player human, and takes as parameter his opponent,
     * Player comp. This is necessary, for through the course of the user's turn, the computer's 
     * score must be printed, and, when the player's turn ends, the computer's start method (cStart)
     * must be invoke upon the computer, for which the method doing this must be having the Player comp
     * as the parameter.
     * <br>
     * In a normal turn of the player, the user is offered the chance to give up. If he agrees, he is
     * questioned again, and if he reiterates the same, the game ends, and he is given the choice to play
     * again. If he disagrees, his statistics (turnScore, totalScore and opponent's score) are printed and 
     * the gameplay continues, asking him to choose wether to roll or hold through the choose() method.
     * 
     * @param diff      Character indicating difficulty level chosen by user ('e' easy ; 'm' medium ; 'h' hard)
     * @param status    Integer indicating wether or not to offer the player a chance to give up (1 for yes, anything else for no.)
     */
    public void start(char diff, int status, Player comp) throws Exception
    {
        difficulty = diff ;
        
        if (status==1) {
            
            Ornamental.pS("Good luck...unless you want to give up? Do you ? (yes/no)");
            InputStreamReader in = new InputStreamReader (System.in);
            BufferedReader choice = new BufferedReader (in);
            String giveUp = choice.readLine();
        
            if(giveUp.equalsIgnoreCase("yes")) {
                Ornamental.pS("\nAre you sure ?");
                String sure = choice.readLine();
                
                if (sure.equalsIgnoreCase("yes")) {
                    Ornamental.pS("\nAlright then...");
                    comp.compWins(1, this);
                }
            }
        
        
            else {
                    Ornamental.pS("Great going! Giving up is for cowards...wish you luck !\n");
            }
        }
                
        this.printPlayer(comp);
        this.choose(comp);       
       
    }
    
    /**
     * printPlayer : A simple method used time and again in the program inorder to print the most
     * basic parameters of a player's turn, his score so far, his total score, and the opponent's 
     * score. It is most commonly used before and after every turn.
     * 
     * @param comp      The Player object comp, the user's opponent; used to print the computer's 
     *                  total score referring to it's instance variable totalScore.
     */
    public void printPlayer(Player comp) throws Exception 
    {
        Ornamental.pS(("Your Score in this turn : " + turnScore ),15);
        Ornamental.pS(("Your Total Score : " + totalScore ),15);
        Ornamental.pS(("Computer's Score : " + comp.totalScore ),15);
    }
    
    /**
     * choose : This method at each turn of the user, prompts him to decide wether to roll of to hold.
     * If the player chooses to roll, the roll method is invoked, else the hold method is invoked.
     * 
     * @param comp      The opponent Player comp, used to pass it as a parameter to roll and hold.
     */
    public void choose(Player comp) throws Exception 
    {
        Ornamental.pS("\nWhat do you want to do? (roll / hold)");
        InputStreamReader in = new InputStreamReader (System.in);
        BufferedReader choice = new BufferedReader (in);
        String chosen = choice.readLine();
                
        if(chosen.equalsIgnoreCase("roll")) 
            roll(comp);
        else 
            hold(comp);
    }
    
    /**
     * roll : Rolls the dice of the player using the randomRoll() method in the dice class, 
     * thereby assigning a random value 1-6 to dice. It also prints the roll of the dice, following
     * which, it invokes turnEdit, a method that will further act upon the rolled dice.
     *
     *@param comp       The opponent Player comp, used to pass as parameter to turnEdit
     */
    public void roll(Player comp) throws Exception 
    {
        dice.randomRoll() ;
        dice.printDice();
        turnEdit(comp);
    }
    
    /**
     * turnEdit : This method inspects the roll of the dice and acts upon it. If the dice rolled
     * is 1, then it invokes the method kill(a method that ends the turn of the player and begins the
     * turn for the computer) with a parameter 1, indicating that the turn of the player ends for he has
     * rolled a 1.
     * <br>
     * If the player has rolled any of the other numbers 2-6, the value of the dice is immediately added
     * on to the totalScore of the player and the player's turn continues prompting him to roll or hold 
     * by invoking start with int status as 0. If, however, by that immediate roll, the player's score reaches
     * 100, the player wins the game and the method playerWins is called.
     * 
     * @param comp      The opponent Player comp, used to pass as parameter to start, kill, and playerWins.
     */
    public void turnEdit(Player comp) throws Exception 
    {
        if(dice.value == 1) 
            kill(1,comp);
        else {
            turnScore += dice.value ;
            if(totalScore + turnScore >= 100) playerWins(); 
            else {
                Ornamental.pS("\nTherefore :");
                start(difficulty,0,comp);
            }
        }
    }
    
    /**
     * hold : This method is simply an intermediary method when the player decides to hold his turn.
     * It essentially invokes the method kill with a parameter int status as 0, indicating the end of
     * the player's turn due to holding and not due to rolling a 1.
     * 
     * @param comp      The opponent Player comp, used to pass as parameter to kill
     */
    public void hold(Player comp) throws Exception 
    {
        Ornamental.pS("\nAllright...so you decide to hold your turn ?");
        kill(0,comp);
    }
    
    /**
     * kill : This method ends the player's turn if he has rolled a 1 or decides to hold. The method recieves
     * an input int status indicating how the player's turn ended. 1 indicates end of turn due to rolling a 1, 
     * and anything else, conventionally 0, indicates end of turn for the player has decided to hold his turn.
     * <br>
     * If the player's turn has come to an end because he has rolled a 1, the computer prints a message informing
     * the player about this, resets the player's turnScore to 0 , without editing the totalScore, and begins the 
     * computer's turn by invoking cStart(the start method for the computer) on Player comp which it recieves as a parameter.
     * <br>
     * If the player's turn has come to an end because he has decided to hold, the computer prints the player's details, his 
     * turn score for this turn, his new total score with his turn score added and the computer's score. Following this, it resets
     * the player's turnScore to 0 and then begins the computer's turn by invoking cStart on Player comp.
     * 
     * @param comp      The opponent Player comp ; the method cStart, to begin the computer's turn, is invoked upon comp
     * @param status    This integer indicates whether the player's turn ends due to rolling a 1 (status is 1) , or due to holding (status is anything else).
     */
    public void kill(int status, Player comp) throws Exception
    {
        if (status == 1) {
            Ornamental.pS("Uhoh...you've rolled the DEATHLY DICE ! Your turn ends here, and you gain NO points whatsoever !");
            
            Ornamental.pS("The computer plays now...\n\n---------------------------------------COMPUTER---------------------------------------\n",10);
            turnScore = 0;
            comp.cStart(difficulty, this);
        }
        else {
            
            Ornamental.pS("Have a look at your score so far...\n");
            totalScore += turnScore ;
            printPlayer(comp);
            turnScore = 0;                     
                        
            Ornamental.pS("\nThe computer plays now...\n\n---------------------------------------COMPUTER---------------------------------------\n",10);
            comp.cStart(difficulty, this);
        }
    }            
      
    /**
     * playerWins : This method is the congratulatory method to the player when he/she wins the game by reaching a 100
     * before the computer. It congratulates the player and then offers the player if he would like to play the game 
     * again. If he agrees, the main method of Start class is invoked, restarting the game. Else, the game is terminated
     * with a goodbye message.
     */
    public  void playerWins() throws Exception 
    {
                
                Ornamental.pS("\n\nCongratulations! You've won the game !\n");
                Ornamental.pS("\nWould you like to play again? (y/n)");
                InputStreamReader in = new InputStreamReader (System.in);
                BufferedReader choice = new BufferedReader (in);
                String playAgain = choice.readLine();
                                
                if(playAgain.equalsIgnoreCase("y")){
                    
                    System.out.println("\n\n\n\n\n");
                    Thread.sleep(80);
                    Start.main();
                }
                else{
                    Ornamental.pS("Goodbye !");
                    System.exit(0);
                }
     }
     
     
     
     
///THE FOLLOWING METHODS EXECUTE THE COMPUTER'S PLAY //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
     
     
     
     /**
      * cStart : This method marks the beginning of the computer's turn. It takes char diff, the difficulty level chosen by the
      * computer as a parameter to set the global variable difficulty to it's value. Essentially, to start the turn, the computer
      * calls the compChoose method in the AI class to decide what to do, roll or hold. It passes as parameters the difficulty level,
      * itself, and the Player human as parameters to compChoose.
      * <br>
      * If the computer decides to roll, it invokes the method compRoll, else it invokes the method killComp with parameter int status
      * as 0, indicating the computer's turn has ended due to holding.
      * 
      * @param diff     Character indicating difficulty level chosen by user ('e' easy; 'm' medium'; 'h' hard)
      * @param human    The computer's opponent, the user ; used to pass as parameter ot compChoose, compRoll and killComp
      */
     public void cStart(char diff, Player human) throws Exception   
     {
        difficulty = diff;
        char compChoice = AI.compChoose(difficulty, this,human);     // Using AI to decide wether to roll or to hold ; the compChoose method is in the AI class ;
        switch(compChoice) 
        {
            case 'r':
                Ornamental.pS("\nThe computer decides to roll...\n");
                compRoll(human);
                break;
            default :
                Ornamental.pS("\nThe computer decides to hold...\n");
                killComp(0,human);
        }     
               
     }
    
    /**
     * printComp : A simple method used time and again in the program inorder to print the most
     * basic parameters of the computer's turn, it's score so far, it's total score, and the user's 
     * score. It is most commonly used before and after every turn.
     * 
     * @param human     The Player object human, the user ; used to print the user's 
     *                  total score referring to his instance variable totalScore.
     */
    public void printComp(Player human) throws Exception
    {
        Ornamental.pS(("Computer's score this turn : " + turnScore ),15);
        Ornamental.pS(("Computer's Total Score : " + totalScore ),15);
        Ornamental.pS(("Your Score : " + human.totalScore ),15);
    }
    
    /**
     * compRoll : compRoll rolls the dice for the computer. If the user's chosen difficulty level
     * is either easy or medium, the dice is rolled randomly, with 1-6 being equiprobable. However,
     * as part of the artificial intelligence, if the difficulty level is hard, the probability of 
     * rolling a 1 for the computer is reduced to 8% from 16.66 %. The hardRoll() method rolls the 
     * dice with the reduced probability of 1.
     * <br>
     * Following the computer's, roll, the rolled dice's value is printed. After this, compEditTurn,
     * a method which would further act upon the rolled dice is invoked.
     * 
     * @param human     The opponent Player, the user ; used to pass as parameter to compEditTurn
     */
    public void compRoll(Player human) throws Exception 
    {
        if(difficulty == 'h') 
            dice.hardRoll();
        else
            dice.randomRoll();
            
        dice.printDice();        
        this.compEditTurn(human);
    }
    
    /**
     * compEditTurn : This method further acts upon the rolled dice of the computer.
     * If the dice rolled is 1, the computer's turn ends, and the method killComp is invoked
     * with the parameter status as 1 ,indicating the end of the computer's turn due to the roll
     * of a 1.
     * <br>
     * If the computer rolls any of other values 2-6, turnScore is added to the totalScore of the
     * computer, following which the computer continues play, deciding wether to roll or hold through
     * the cStart method. If, however, by the roll of this dice, the computer's score reaches 100, the
     * computer wins the game and the compWins method is invoked.
     * 
     * @param human     The opponent Player, the user ; used to pass as parameter to kill, compWins, printComp and cStart.
     */
    public void compEditTurn(Player human) throws Exception 
    {
        if(dice.value == 1) 
            killComp(1,human);
        else {
            turnScore += dice.value ;
            
            if(totalScore + turnScore >= 100) {
                totalScore += turnScore ;
                compWins(0,human);
            }
            else {
                Ornamental.pS("\nTherefore :");
                this.printComp(human);
                this.cStart(difficulty, human);
            }
        }
     }
    
     /**
     * killComp : This method ends the computer's turn if it has rolled a 1 or decides to hold. The method recieves
     * an input int status indicating how the computer's turn ended. 1 indicates end of turn due to rolling a 1, 
     * and anything else, conventionally 0, indicates end of turn for the computer has decided to hold it's turn.
     * <br>
     * If the computer's turn has come to an end because it has rolled a 1, the computer prints a message informing
     * the player about this, resets it's turnScore to 0 , without editing the totalScore, and begins the 
     * user's turn by invoking start(the start method for the human) on Player human which it recieves as a parameter.
     * <br>
     * If the computer's turn has come to an end because it has decided to hold, the computer prints the it's own details, it's 
     * turn score for this turn, it's new total score with it's turn score added and the user's total score. Following this, it resets
     * it's own turnScore to 0 and then begins the human's turn by invoking start on Player human.
     * 
     * @param human      The opponent Player, the user ; the method start, to begin the human's turn, is invoked upon human
     * 
     */
    public void killComp(int status, Player human) throws Exception 
    {
           if (status == 1) {
                Ornamental.pS("\nThe computer has rolled the DEATHLY DICE ! It loses all it's points for this turn !");
            
                Ornamental.pS("It's your turn now...\n\n----------------------------------------PLAYER----------------------------------------\n",10);
                turnScore = 0;
                human.start(difficulty, 1,this);
           }
        
           else {
                totalScore += turnScore ;
                printComp(human);
                                    
            
                turnScore = 0;
            
                Ornamental.pS("\nIt's your turn now...\n\n----------------------------------------PLAYER----------------------------------------\n",10);
                human.start(difficulty,1,this);
           }
    }
    
    /**
     * compWins : This method is the informatory method to the player when the computer wins the game by reaching a 100
     * before the him, or if the player gives up. It informs the player regarding his loss, and then offers the player 
     * if he would like to play the game again. If he agrees, the main method of Start class is invoked, restarting the game. 
     * Else, the game is terminated with a goodbye message.
     * 
     * @param status     Integer indicating how the player lost (1 by giving up, anything else by computer reaching a 100)
     * @param human      The opponent Player, the user ; used to pass as parameter to printComp, inorder to print the user's score
     */
     public  void compWins(int status,Player human) throws Exception 
     {
                
                if(status == 1) 
                    Ornamental.pS("You've given up, so...");
                
                Ornamental.pS("\n\nThe computer wins the game with a score of " + totalScore +" !\n");
                printComp(human);                  
                Ornamental.pS("\nWould you like to play again? (y/n)");
                
                InputStreamReader in = new InputStreamReader (System.in);
                BufferedReader choice = new BufferedReader (in);
                String playAgain = choice.readLine();
                
                if(playAgain.equalsIgnoreCase("y")) {
                    System.out.println("\n\n\n\n\n");
                    Thread.sleep(80);
                    Start.main();
                }
                else {
                    Ornamental.pS("Goodbye !");
                    System.exit(0);
                }
     }
        
}
