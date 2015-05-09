
/**
 * class AI is the mastermind class behind the artificial intelligence used by the computer; Through the course of the game, 
 * the computer uses it to decide wether to roll or to hold in a particular turn. It deals with varying difficulty levels :
 * easy, medium and hard (which is chosen by the user at the beginning of the game). In the 'easy' difficulty level, the computer
 * plays a fairly random game, whereas in the 'medium' level and 'hard' level, the computer plays a very calculated game and decides
 * based on three factors : 
 * <ol>
 * <li> The Computer's total score (comp.totalScore),
 * <li> The Computer's score accumulated in this turn so far (comp.turnScore), and
 * <li> The User's total score as of his/her last turn. (human.totalScore).</ol>
 * 
 * Further interestingly, the basic mechanism of AI in the medium and hard levels are similar, except that in the 'hard' level,
 * rather than making a more calculated decision as to roll or hold, the PROBABILITY of the computer rolling a 1 is reduced by 
 * half, carried out by the hardRoll() method in the Dice class.
 * 
 * @author Athyuttam Reddy Eleti
 * @version 2.80
 */
public class AI
{
    /**
     * compChoose : This method , depending on the difficulty level chosen by the user, the user's total score,
     * the computer's total score and the computer's turn score, decides wether to roll or to hold in a particular turn.
     * The Artificial Intelligence itself , therefore , is :
     * <ul>
     * <li> Difficulty Level Easy : In the easy difficulty level, the AI used by the computer is fairly random. The optimal limit for a
     *      turn's score lies between 15 and 25. Anything below this is too less an accumulation for that particular turn and anything 
     *      above can be considered too big a risk. Therefore, a random integer within the range 15-25 is obtained and the computer
     *      rolls until it equals or crosses the set limit; unless of course,if it reaches 100. Thus, essentially, the computer works 
     *      on an AI where the turn score is the only concern and defines optimal play.
     * 
     * <li> Difficulty Level Medium : The medium level shares it's code with the hard level and thus is set as the default case
     * when not easy in the switch statement in the method. In the medium difficulty level, the AI works upon two main clauses : If the computer's
     *      totalScore + turnScore (int cPS) is greater than the player's score ; or the player's score is greater than the cPS.
     *      In the first clause (cPS > human.totalScore), the following conditionals determine the decision :
     *          <ol>
     *          <li> if (100 - human.totalScore <= 18) return 'r'; This indicates that the human's total score is within 18 of 100,
     *               that is to say, he is VERY CLOSE TO WINNING THE GAME, and thus, since the computer should not resort to any strategy
     *               but to roll and try to beat the player, it rolls.
     *               
     *          <li> if(comp.turnScore >= 25) return 'h'; Since execution has come to this statement, it means that the human is fairly away
     *               from reaching a 100 (>18) and thus the computer must take it's turn score into accomodation. If in this case, where
     *               there is no urgency to roll and beat the player, the computer checks if it is risking too much for too little. If the
     *               turn score is already >=25 , then at anytime a 1 could be rolled and the whole score wasted. Thus, when the human is 
     *               fairly far away from a 100, and the computer is risking too much, the computer holds.
     *               
     *          <li> if(human.totalScore - cPs < 18 && comp.turnScore >=15) return 'h' ; Here, the computer looks at it's difference from
     *               the human in score and at the same time, it's own turn score. (human.totalScore - cPS < 18) implies that the computer
     *               is not too far away from the human and (comp.turnScore>=15) checks if the computer is risking too much. Thus, if the 
     *               computer is in a healthy stage where it is not too far away from the human, it must not risk too much of it's turn
     *               score, and thereby hold.
     *               
     *          <li> In this clause, if all the above sub-clauses are bypassed, the computer still remains at a score below that of the user.
     *               Therefore, when the computer is losing, the obvious reaction is to roll and try to catch up, which is exactly what is done
     *               through (else return 'r';). However, keep in mind that this is executed only if the above statements are not executed.
     *          </ol>
     *          
     *      In the second clause (cPS > human.totalScore && human.totalScore >=5), the computer is ahead of the user and the user himself
     *      is not at a score less than 5 (which could indicate either his very first turn or a very early turn). If he had been less than
     *      the computer would have applied AI which is unnecessary, for at this early stage of the game, a good accumulation of score is 
     *      the main aim. Therefore, if this clause is bypassed (either the two players' scores are equal, or human's totalScore < 5), the
     *      computer resorts to AI used in the easy difficulty level, aiming at accumulation score. This clause has two 
     *      sub-clauses, which decide the roll or hold :
     *          <ol>
     *          <li> if ((100 - cPS) <= 8 && cPS-human.totalScore <= 10 ) return 'r'; : (100 - cPS <=8) indicates that the computer is very
     *               close to reaching 100 and (cPS-human.totalScore <=10) indicates that at the same time, the human is fast on the computer's
     *               heels, within a range of 10. In this crucial stage of the game, where both players have a very high chance of winning
     *               the game, it makes sense to roll and try to get to a 100 before the other. This is safer for the computer has an advantage
     *               that the current turn is it's , thus it can try to win the game. Thus, it rolls in this condition.
     *               
     *          <li> if (cPS-human.totalScore >=10 && comp.turnScore >=15) return 'h'; Since execution has come to this stage,this indicates 
     *               both players are not necessarily very close to winning. Thus, the computer must take into account it's own turn score
     *               and check if it is risking too much. Hence, (cPS-human.totalScore >= 10) indicates that the human is at a fair distance
     *               away from the computer(>=10) and (comp.turnScore >=15) indicates that the computer's turnScore is a considerable amount
     *               (15 or more). Thus, if the conditional is true, the computer must not risk too much of it's turn score while in a fairly
     *               safe positon, and thereby hold.
     *          </ol>
     *      As mentioned previously, if both clauses fail, (either both scores are equal, or the second clause is false(due to human.totalScore
     *      < 5)) the computer retorts to the strategy of accumulating points, which is also used in the easy difficulty level. If both
     *      scores are equal, it is as though the computer is back at square one, with noone winning the game. Thus, it must try and gather
     *      score and outrun the human. If the computer's score is greater than the human's, and the human's score is minimal(<5), the computer
     *      tries and builds up a lead and resorts to this strategy.
     *          This strategy essentially takes a random integer within a range of 15-25, and rolls until it equals or exceeds this random number,
     *      ensuring a decent collection of score every turn.
     *      
     *<li>  Difficult Level Hard : The hard difficulty level essentially works on the same strategy as that of the medium level, but what
     *      makes the play harder for the user is the cheating involved. In this level, the dice of the computer, generally rolled by the
     *      randomRoll() method in the Dice class, is rolled by the hardRoll() method instead. The hardRoll() method, as described in the
     *      Dice class itself, DECREASES the probability of a 1 on the dice from 16.66 % to 8 % . Thus, the computer has a lot lesser 
     *      chances to lose the turn, and accumulates considerably more amount of points. This makes the game harder for the player.
     *          As far as the returning of 'r' or 'h' itself, the hard levels shares it's code with the medium level and is hence set 
     *      as the default case in the switch statemnt in the method.
     *
     *</ul>   
     *           
     * 
     * @param diff      A character indicating the difficulty level chosen by the user, 'e' for easy, 'm' for medium and 'h' for hard.
     * @param comp      The Player object indicating the computer, referred to for the computer's total score and it's turn score.
     * @param human     The Player object indicating the human, referred to for the human's total score.
     * 
     * @return A character 'r' or 'h' indicating roll or hold respectively.
     */
    public static char compChoose(char diff, Player comp, Player human)
    {
        int withinRange = randomInt(15,25);
        int cPS = comp.turnScore + comp.totalScore ;  
        if(cPS >= 100) return 'h';
        
        switch(diff) 
        {
            
            case 'e':                
                if (comp.turnScore < withinRange) return 'r';
                else return 'h';
            
            default:             
                if (human.totalScore > cPS)  {
                    if (100 - human.totalScore <= 18) 
                        return 'r';
                    if (comp.turnScore >= 25) 
                        return 'h';
                    if (human.totalScore - cPS < 18  &&  comp.turnScore >=15) 
                        return 'h';
                    
                    return 'r';
                }
            
                if (cPS > human.totalScore && human.totalScore >=5) {
                    if ((100 - cPS) <= 8 && cPS-human.totalScore <= 10 ) 
                        return 'r';
                    if (cPS-human.totalScore >=10 && comp.turnScore >=15) 
                        return 'h';
                }
                
                if (comp.turnScore < withinRange) 
                    return 'r';
                else 
                    return 'h';
        }
            
        
    }
    
    /** 
     * randomInt : This method returns a random integer within a range of two integers(inclusive). Thus, it is used to 
     * return a random integer between 15 and 25 , a range within which the limit for the turn score optimally should stand. Thus, in the easy
     * level, the computer rolls upto this random integer, and then holds . Using the Math.random() function, a random double 
     * is created, which is then used to create a random integer within the range.
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
}
