import java.awt.*;
/**
 * class Ornamental is a simple class that deals with the beautification involved in the project.
 * Essentially, the main ornamental factor in the game was the printing of text at varying speeds.
 * This is achieved by the methods pS(Stringp printable, int milli) and pS(String printable). These
 * two overloaded methods serve a similar purpose except that one has a constant speed of printing
 * while the other takes an integer input and changes the speed of printing the text.
 * <br>
 * Furthermore, another experiment was printing graphically the die that was rolled. The code for that
 * is included as the drawDice method. This, however, is commented out for there were major drawbacks.
 * <br>
 * In essence, the objective of the Ornamental class is to make the game a wholesome, user-friendly experience.
 * 
 * @author Athyuttam Reddy Eleti    
 * @version 2.8
 */
public class Ornamental
{ 
    /**
     * pS(String printable, int milli) : This method prints the characters of the String printable at an
     * interval of milli milliseconds. A for loop traverses the whole String , printing one character
     * at a time, yet 'sleeping' after each charcter was printed. This is attained by he Thread.sleep() 
     * method, which keeps the present cycle of events rested for a given time. Therefore, depending on the interval
     * of milliseconds ,the String prints faster or slower. Lesser the milliseconds interval, faster the string prints
     * and vice versa.
     */
    public static void pS(String printable, int milli) throws Exception
    {
        for(int i = 0; i < printable.length() ; i++) 
        {
            System.out.print(printable.charAt(i));
            Thread.sleep(milli);  // A thread is a thread of execution in a program i.e. the sequence of the steps executed
                                  // through the program. Thread.sleep() rests the current thread for a particular no. of 
                                  // milliseconds, thereby slowing down the execution.
        }
        System.out.println();
    }
    
    /**
     * pS(String printable) : An overloaded method with pS(String printable, int milli), this method 
     * performs a similar function as the other, that is, printing a given String at varying speeds,
     * printing the characters at intervals a specific no. of milliseconds. This method acts as the
     * default printing method for the entire project, as it uses 30 milliseconds as the amount of time
     * for which the Thread sleeps.
     */
    public static void pS(String printable) throws Exception
    {
        for(int i = 0; i < printable.length() ; i++) 
        {
            System.out.print(printable.charAt(i));
            Thread.sleep(30);
        }
        System.out.println();
    
    }
    
    /**
     * drawDice : This method was intended to print a graphical representation of the roll of the 
     * dice. It originally prints a rounded rectangle with filled circles at the positon as in the
     * die, albeit on a new frame. The drawback of this code was that each time a new dice was rolled,
     * a new frame opens with the drawing in it, so by the end of the game, the no. of rolls, both player
     * and computer, was the no. of windows open. This being a problem, the idea was dropped. To generate
     * this frame as a backdrop to the graphics, the class Slate is used courtesy Allen B. Downey and his 
     * book 'How to Think Like a Computer Scientist'.
     */
    /**public static void drawDice(int val) {
        int width = 80;
        int height = 100;
        Slate slate = new Slate (width, height);
        Graphics g = slate.getSlateGraphics ();
        g.setColor (Color.black);
        g.drawRoundRect(10, 30, 60, 60, 15, 15);
        
        switch(val) {
            
            case 1: // Printing 1
                g.fillOval(36,56,8,8);  
                break;
            case 2: // Printing 2
                g.fillOval(21,41,8,8);
                g.fillOval(51,71,8,8);
                break;
            case 3: // Printing 3
                g.fillOval(21,71,8,8);
                g.fillOval(36,56,8,8);
                g.fillOval(51,41,8,8);
                break;
            case 4: // Printing 4
                g.fillOval(21,41,8,8);
                g.fillOval(51,41,8,8);
                g.fillOval(21,71,8,8);
                g.fillOval(51,71,8,8);
                break;
            case 5: // Printing 5
                g.fillOval(21,41,8,8);
                g.fillOval(51,41,8,8);
                g.fillOval(21,71,8,8);
                g.fillOval(51,71,8,8);
                g.fillOval(36,56,8,8);
                break;
            case 6: // Prining 6
                g.fillOval(21,41,8,8);
                g.fillOval(51,41,8,8);
                g.fillOval(21,71,8,8);
                g.fillOval(51,71,8,8);
                g.fillOval(21,41,8,8);
                g.fillOval(51,71,8,8);
                break;
            default:
                break;
        }
        
    }        
    **/
     
      
}
