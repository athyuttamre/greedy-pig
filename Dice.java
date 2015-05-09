
/**
 * Class Dice provides a simple template for the Dice object, an instance
 * variable of both players, the human and the computer. It also includes
 * basic functions of the Dice objects, such as printDice(prints the value),
 * randomRoll(assings a random integer 1-6 to the Dice) and randomInt(A 
 * helper method returning a random integer within a range).
 * 
 * @author Athyuttam Reddy
 * @version 1.0
 */
public class Dice 
{
    /**
     * value : Value of the roll of the dice (1-6).
     */
    int value;

    /**
     * Dice : A constructor that creates a Dice object with a set value( of 1(roll of dice).
     * 
     * @param value     The value of the roll of the dice(1-6).
     */
    public Dice()
    {

        this.value = 1 ;
    }

    /**
     * printDice : This method prints the value of the roll of the player's dice. Therefore, if the player rolls 3, the 
     * method prints "The Roll of your Dice is : 3". 
     * 
     * printDice is an object method working on a Dice object.
     */
    public void printDice() throws Exception
    {
        Ornamental.pS("The Roll of your Dice is : " + value) ;
    }
    
    /**
     * randomRoll : This method randomly 'rolls' the dice and assigns the players' dice's value to the random number
     * within 1 and 6 (inclusive). Thus , in essence, this method rolls the player's dice. 
     */
    public void randomRoll() 
    {
        int random = randomInt(1,6);
        value = random ;
    }
    
    /**
     * randomInt : This method returns a random integer within a range of two integers(inclusive). Thus, it is used to 
     * return a random integer between 1 and 6(inclusive) to be used as the value of the roll of the dice. Using the 
     * Math.random() function, a random double is created, which is then used to create a random integer within the range.
     * 
     * @param low       The lower boundary of the range (inclusive) to realise a random integer.
     * @param high      The higher boundary of the range (inclusive) to realise a random integer.
     * 
     * @return          A random integer within the range of 'low' and 'high' (inclusive).
     */
    public static int randomInt (int low, int high) 
    {
        double random = Math.random() ;
        double randomWithin = (random * (high-low+1)) + low ;
        int value = (int) randomWithin ;
        return value ;
    }
    
    /**
     * hardRoll : If the difficulty level chosen by the user is hard, the hardRoll() method is used to roll the dice of the 
     * computer. Through this method, the chances of the computer losing out in this turn is reduced by a great margin by 
     * reducing the PROBABILITY of rolling a 1. Initially, the probability of rolling a 1 as compared to the other numbers
     * is equal i.e. 16.66 % . However, through this method , the probability or rolling a 1 is decreased to 8 % ; thus, the 
     * computer has lesser chances to lose this turn and more chances to win the game, making it 'hard' for the user.
     */
    public void hardRoll() 
    {
        int diceValue = randomInt(1,6);
        double random = Math.random();
        if(diceValue == 1 && random <= 0.08) {
            value = 1 ;
        }
        else {
            value = diceValue ;
        }
    }
    
    
    
}
