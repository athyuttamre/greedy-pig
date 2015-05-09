----------------------------------------------------------------------------------------------------------------|
----------------------------------------------------------------------------------------------------------------|

PROJECT TITLE: GreedyPig

OBJECTIVE OF THE PROJECT: 
To create a simple playable two player version of the famous dice game Greedy Pig,
with one player as the computer.

VERSION or DATE: 16 October 2010
AUTHOR: Athyuttam Reddy Eleti
----------------------------------------------------------------------------------------------------------------|
----------------------------------------------------------------------------------------------------------------|

HOW TO START THIS PROJECT: 
To start this project, invoke the main method in the Start class. To do this in BlueJ,
right click on the Start class, click on main and in the newly opened dialog box,
choose OK. The terminal window would open executing the program. Follow the instructions
thenceforth.
----------------------------------------------------------------------------------------------------------------|
----------------------------------------------------------------------------------------------------------------|
INTRODUCTION: Greedy Pig was an award-winning dice game devised by the faculty of Gettysburg College
as part of their curriculum on Probability and Computer Science. The rules of the game are as follows :

- 2 players play with  a single six-sided die; aim being the first to reach 100 points.
- On a turn, a player rolls the die repeatedly until either:
        - A 1 is rolled [THE DEATHLY DICE]
        - The player chooses to hold (stop rolling)
If a 1 is rolled, that player's turn ends and no points are earned.
- If the player chooses to hold, all of the points rolled during that turn are added to his or her score and the next player plays.
- When a player reaches a total of 100 or more points, the game ends and that player is the winner. 

Further information about the game can be gathered from http://cs.gettysburg.edu/projects/pig/ .
----------------------------------------------------------------------------------------------------------------|
----------------------------------------------------------------------------------------------------------------|

CLASS STRUCTURE :

................................................................................................................|
DICE

public class Dice extends java.lang.Object
    This class provides a template for the creation of a Dice object, and 
    includes basic functions of Dice objects in the form of various methods.
    
...CONSTRUCTOR SUMMARY...

public Dice(int value)
    A basic constructor that creates a Dice object with a set value of 1(roll of dice).
    
...METHOD DETAIL...

### public void printDice() throws Exception
        This method prints the value of the player's dice as "The Roll of your Dice is : "
        
### public static int randomInt(int low,int high)
        This method returns a random integer within a range of two integers(inclusive).
        It is used to generate a number 1-6 to simulate the roll of a dice.
        PARAMETERS : int low - The lower boundary of the range
                     int high - The higher boundary of the range

### public void randomRoll()
        This method randomly 'rolls' the dice and assigns the player's
        dice's value to a random number withing 1 and 6(inclusive), generated
        using randomInt.

### public void hardRoll()
        If the difficulty level chosen by the user is hard, the hardRoll()
        method is used to roll the dice of the computer. This method deliberately 
        decreases the probabilty of rolling a 1 from 16.66 % to 8%. This makes
        the chances of the computer failing in the turn minimal, and thus makes
        the game harder for the player.
        

................................................................................................................|
START
    
public class Start extends java.lang.Object
    This class is the startup class of the project. It allows the user to learn the rules, end the game immediately
    or play the game. If a player decides to play the game, a coin is tossed. The winner of teoss
    decides which position he/she wants to play in. Following this, the two Playerobjects, human and comp
    are created and the game begins by invoking their respective start methods on tem.
    
...FIELD SUMMARY...

static char difficulty
    This character difficulty indicates the level of difficulty chosen by the user for the game, by default
    set to easy 'e'. Other choices are 'm' for medium and 'h' for hard.
    
...METHOD DETAIL...

### public static void main() throws java.lang.Exception
        The main method for the class Start, it introduces the user to the game
        following which, it invokes the game() method, which begins the actual play.

### public static void game() throws java.lang.Exception
        This method commences the game of Greedy Pig. It allows the user to choose to play the game,
        read the rules and then decide or to immediately exit. If the user decides to read the rules, the 
        printRules() method is called. If the user decides to play the game, the toss() method is called.
        Else, the game terminates.

### public static void printRules() throws java.lang.Exception
        This method prints the rules of the game , following which, prompts the user
        to make a choice between play, rules and exit.
   
### public static void toss() throws java.lang.Exception
        This method carries out the toss of the game. The user chooses either heads or tails. If he
        wins the toss, he decides whether to play first or second. Depending on his choice, the callGame() 
        method is called. If he loses the toss, the computer decides it's position and then calls callGame.
      
### public static void callGame(int plyrPos) throws java.lang.Exception
        This method instantiates two 'Player' objects, human and comp and begins the game on them.
        The parameter plyrPos represents what position the user is playing at, first or second. If first,
        the game begins for the human (human.start()), else the game begins for the computer(comp.cStart).
        PARAMETERS : int plyrPos - The position of the player ; 1 if first, 2 if second.
        
................................................................................................................|
PLAYER


public class Playerextends java.lang.Object
    For extensive detail on the Player class, refer to the class documentation. This class essentially controls
    the exectution of the game and the major gameplay. It has two sets of methods, one for the play of the turn
    of the user, and the other for the play of the turn of the computer.

GENERAL SEQUENCE OF GAMEPLAY FOR A TURN (HUMAN OR COMPUTER) :
    1] The start method of the player is invoked. This would print the player's attributes and then choose to 
       either roll or hold. In case of computer, this is done in the same method using the AI class, in the case 
       of human, this is done using a separate method choose().
    2] The player has chosen to either roll or hold. If he roll's , the roll() or compRoll() method is invoked, as
       the case may be.If he holds, the hold() method is invoked in the human's case, and in the computer's case,
       the compKill method with status 1 is invoked, indicating the end of computer's turn by holding.
    3] If the player holds, the turnScore is added to the totalScore. The details of the player are printed and then
       the next player plays his turn.
    4] If the player rolls, the roll can be either 1 or 2-6. Immediately after rolling, the editTurn or compEditTurn
       method is invoked.    
    5] When the player rolls , the editTurn or compEditTurn method is invoked. If it is a 1, the player's turn ends by calling the 
       respective kill method (kill or compKill) with status 1 (int status is a parameter of the kill method. 1 indicates
       end of turn by rolling a 1, anything else indicates end of turn by holding.) is invoked. If the roll is above
       1, the roll is added to the turnScore and the start method of the player is invoked again, asking them to choose
       whether to roll or hold.
    6] When the player's turn ends, the kill or compKill method is invoked.Both methods take a parameter int status,
       if 1, indicating end of turn due to rolling a 1, or any other number, indicating end of turn by holding.
       If status==1, the turnScore is changed to 0, no changes to the totalScore, and the opponent's turn begins.
       If held, the turnScore is added to the totalScore, and the opponent's turn begins.
    7] At any point of the game, immediately after rolling, if the player reaches a 100, he immediately wins the game.
       The game ends, and the user is asked if he want's to play again.
       
...FIELD SUMMARY...

Instance Variables :
    Dice dice : This is the dice object that belongs to the player, one for each. It will hold a value , can be rolled etc.
    int turnScore : This is the dynamically edited integer holding the turn score so far.
    int totalScore : This is the total score of the player as of last turn. At the end of the current turn, it would be updated as required.

Global Variables :
    static char difficulty : This character difficulty indicates the level of difficulty chosen by the user for the game, by default
                             set to easy 'e'. Other choices are 'm' for medium and 'h' for hard.                        
                                        
...CONSTRUCTOR SUMMARY...

public Player()
    A basic constructor for the Player class, initialising all values of the Player object's instance variables to 0 excepting the value
    of the dice roll, for a dice can never be 0. Therefore, the dice is set to 1.
    
...METHOD DETAIL...

HUMAN METHODS :

### public void start(char diff, int status, Player comp) throws java.lang.Exception
        This method is the start method for the human player. If it is not the first
        roll of the turn, the player is offered a chance to give up. Otherwise, the details
        of the player are printed and the choose() method is called to choose whether to roll
        or to hold.
        PARAMETERS : char diff - Character indicating difficulty level chosen by user (e/m/h)
                     int status - Integer indicating whether to offer give up or not (1-yes, 0-no)
                     comp - The opponent Player, comp, used to pass as parameter to printPlayer and choose.
               
### public void printPlayer(Player comp) throws java.lang.Exception
        A simple method used time and again in the program inorder to print the most
        basic parameters of a player's turn : his score so far, his total score, and the opponent's 
        score.
        PARAMETERS : comp - The opponent Player, comp, used to print the opponent's total score.
      
### public void choose(Player comp) throws java.lang.Exception
        This method asks the player whether he would like to roll or hold. If he wants to roll, the roll
        method is invoked, else the hold method is invoked.
        PARAMETERS : comp - The opponent Player, comp, used to pass as parameter to roll or hold.
        
### public void roll(Player comp) throws java.lang.Exception 
        Rolls the dice of the player using the randomRoll() method in the dice class, 
        thereby assigning a random value 1-6 to dice. It also prints the roll of the dice, following
        which, it invokes turnEdit, a method that will further act upon the rolled dice.
        PARAMETERS : comp - The opponent Player, comp, used to pass as parameter to turnEdit.
        
### public void turnEdit(Player comp) throws java.lang.Exception
        If the player rolls a 1, the kill method is invoked with status 1, indicating that that the player's
        turn ends due to rolling a 1. If roll is >1, the roll is added to the turnScore and the player's start 
        method is again invoked, continuing the game. If the player has crossed a 100, the playerWins method is
        invoked.
        PARAMETERS : comp - The opponent Player, comp, used to pass as parameter to playerWins and start.
        
### public void hold(Player comp) throws java.lang.Exception
        This method is simply an intermediary method when the player decides to hold his turn.
        It essentially invokes the method kill with a parameter int status as 0, indicating the end of
        the player's turn due to holding and not due to rolling a 1.
        PARAMETERS : comp - The opponent Player, comp, used to pass as parameter to kill.
   
### public void kill(int status, Player comp) throws java.lang.Exception
        This method end's the player's turn and begins the opponent's turn. If the parameter status is 1,
        this indicates that the turn end's due to rolling a one, turnScore is changed to 0, no changes to the totalScore, 
        and the opponent's turn begins.If status is 0, the player held, and the turnScore is added to the totalScore, and 
        the opponent's turn begins.
        PARAMETERS : comp - The opponent Player comp ; the method cStart, to begin the computer's turn, is invoked upon comp
                     status - This integer indicates whether the player's turn ends due to rolling a 1 (status is 1) , or due to holding (status is anything else).
        
### public  void playerWins() throws java.lang.Exception
       This method is the congratulatory method to the player when he/she wins the game by reaching a 100
       before the computer. It congratulates the player and then offers the player if he would like to play the game 
       again. If he agrees, the main method of Start class is invoked, restarting the game. Else, the game is terminated
       with a goodbye message.
       
COMPUTER METHODS :

### public void cStart(char diff, Player human) throws java.lang.Exception
        The start method of the computer. The computer chooses whether to roll or to hold using compChoose in class AI.
        If it rolls, the compRoll method is invoked. Else, compKill is invoked with status 0.
        PARAMETERS : char diff - Character indicating difficulty level chosen by user (e/m/h)
                     human - The opponent Player, human, used to pass as parameter to compChoose, killComp and compRoll.
                    
### public void printComp(Player human) throws java.lang.Exception
        A simple method used time and again in the program inorder to print the most
        basic parameters of the computer's turn, it's score so far, it's total score, and the user's 
        score.
        PARAMETERS : human - The opponent Player, human, used to print the opponent's total score.
        
###  public void compRoll(Player human) throws java.lang.Exception 
        Since the computer has decided to roll, int easy and medium levels, the dice is randomly  rolled. However,
        in hard level, to make the chances of the computer losing out in this turn less, the probability of rolling
        a 1 is reduced by rolling the dice using hardRoll(). The dice value is printed and compTurnEdit is invoked.
        PARAMETERS : human - The opponent Player, human, used to pass as parameter to compEditTurn.
       
### public void compEditTurn(Player human) throws java.lang.Exception
        If the computer rolls a 1, the kill method is invoked with status 1, indicating that that the computer's
        turn ends due to rolling a 1. If roll is >1, the roll is added to the turnScore and the computer's cStart 
        method is again invoked, continuing the game. If the computer has crossed a 100, the compWins method is
        invoked.
        PARAMETERS : human - The opponent Player, human, used to pass as parameter to compWins and cStart.
        
### public void killComp(int status, Player human) throws java.lang.Exception
        This method end's the computer's turn and begins the user's turn. If the parameter status is 1,
        this indicates that the turn end's due to rolling a one, turnScore is changed to 0, no changes to the totalScore, 
        and the opponent's turn begins.If status is 0, the player held, and the turnScore is added to the totalScore, and 
        the user's turn begins.
        PARAMETERS : human - The opponent Player human ; the method start, to begin the player's turn, is invoked upon human.
                     status - This integer indicates whether the computer's turn ends due to rolling a 1 (status is 1) , or due to holding (status is anything else).
                  
### public void compWins(int status,Player human) throws java.lang.Exception
        This method informs the player regarding his loss(if he gave up or if the computer reached 100), and then offers the player 
        if he would like to play the game again. If he agrees, the main method of Start class is invoked, restarting the game. 
        Else, the game is terminated with a goodbye message.
        PARAMETERS : status - Integer indicating how the player lost (1 by giving up, anything else by computer reaching a 100)
                     human  - The opponent Player, the user ; used to pass as parameter to printComp, inorder to print the user's score

   
   
................................................................................................................|
ORNAMENTAL

public class Ornamentalextends java.lang.Object
    This class makes the game more user-friendly involving in the beautification of the game.
    The main ornamental factor is printing of text at varying spees.

...METHOD SUMMARY...

### public static void pS(java.lang.String printable,int milli) throws java.lang.Exception
        This method prints the characters of a given string at an interval of milli milliseconds.
        Traversing the whole string, each character is printed at a time, following which the thread of execution
        is rested for milli milliseconds by using the Thread.sleep() method.
        PARAMETERS : String printable - The string to be printed
                     int milli - The interval in milliseconds between the printing of each character.
        
### public static void pS(java.lang.String printable) throws java.lang.Exception
         This method serves the same purpose as the above and therfore is overloaded. The only difference
         is that this method is used as the default printing method for most Strings across the game, 
         for it uses 30 milliseconds as the interval period for the Thread to rest i.e. 30 milliseconds
         between the printing of each character.
         PARAMETERS : String printable - The string to be printed

................................................................................................................|
AI


public class AI extends java.lang.Object
    This class is the mastermind behind the computer's decisions across the game. This class serves the purpose
    of deciding for the computer , in a particular turn, whether to roll or to hold. It deals with the three 
    difficulty levels, easy, medium and hard. In easy, it plays the game in a very minimally logical, scoring
    way. Medium and Hard share the same code, excepting that in Hard, the probability of rolling a 1 is reduced to
    8% from 16.66 % . In this system, the decision lies primarily on three factors, the computer's total score,
    it's turn score so far and the human's total score.

...METHOD DETAIL...

### public static int randomInt(int low, int high)
        This method returns a random integer within a range of two integers(inclusive). It is used to return
        a random integer within 15 and 25, as the limit for the turn score in the easy mode.
        PARAMTERS : int low - The lower boundary of the range(inclusive) to realise a random integer.
                    int high - The higher boundary of the range(inclusive) to realise a random integer.
          RETURNS : A random integer within low and high.           
              
### public static char compChoose(char diff, Player comp,Player human)
        This method recieves as paramters the difficulty level, the computer and the human objects.
        Based on the difficulty level, it decides whether to roll or to hold using different artificial
        intelligence. For further detail on the strategy itself, refer to the class documentation.
        PARAMETERS : char diff - Character indicating difficulty level (e/m/h)
                     Player comp - The computer object
                     Player human - The computer's opponent, the human object
           RETURNS : Character 'r' or 'h' indicating whether to roll or to hold respectively.
           
................................................................................................................|               

LIMITATIONS OF THE GAME :
 • The game offers only a two player mode (Greedy Pig is a reputed multiplayer game).
 • The artificial intelligence adopted by the computer is not top-notch, and can
   give way to many off-by-one errors in deciding whether to roll or hold.
 • The ornamental factor of the game is not the best that could be offered.
 
----------------------------------------------------------------------------------------------------------------|
----------------------------------------------------------------------------------------------------------------|
    
POTENTIAL IMPROVEMENTS IN THE GAME :
 • The game can be made multiplayer.
 • The artificial intelligence can be further developed and made more logical and mathematical.
 • The aritficial intelligence could also be based on probability, making it more accurate.
 • Other variants of the game could be adopted, such as those involving two dice.
 • There is scope for improvement in the Ornamental aspect of the game.
----------------------------------------------------------------------------------------------------------------|
----------------------------------------------------------------------------------------------------------------|
                     
                 

                       
                 
                 

                            
