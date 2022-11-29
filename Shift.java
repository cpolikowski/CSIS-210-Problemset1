import java.util.Random;
import java.util.Scanner;
/**
 * Create a game with a single dimensional array as the game board, use numbers as chips, and move the
 * chips to the end of the board without them jumping each other, they don't go past the end, they
 * don't occupy the same spot, and they are in increasing order
 *
 * @author Kade Garrison and Cam Polikowski
 * @version 1.0 (Due Date: 2/23/22)
 */
public class Shift
{
    // instance variables - replace the example below with your own
    private String[] board;
    private int boardSize;
    private int numChips;
    private Integer numPlaced;
    private Random rand;

    /**
     * Constructor for objects of class Shift
     */
    private Shift()
    {
        // initialise instance variables
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the size of your game board [9, 15]: ");
        boardSize = scan.nextInt();
        while (boardSize < 9 || boardSize > 15) {
            System.out.println("Invalid Size!");
            System.out.println("Enter the size of your game board [9, 15]: ");
            boardSize = scan.nextInt();
        }
        board = new String[boardSize];

        // board being created with all dashes (no chips yet)
        for (int i = 0; i < board.length; i++){
            board[i] = "-";
        }

        //randomly generate number of chips
        rand = new Random();
        numChips = rand.nextInt(3) + 3;
        numPlaced = 0;
    }

    // gives position index of desired chip
    private int checkLocation(String chipWanted){
        int location = board.length - 1;
        for (int i = 0; i < board.length; i++){
            if (board[i].equals(chipWanted)){ 
                location = i;
                i = board.length + 1;
            }
        }
        return location;
    }

    private void placeChips(){
        int counter = numChips;

        // just placing first chip
        board[rand.nextInt(board.length - numChips)] = numPlaced.toString();
        numPlaced++;
        counter--;

        // this section places the rest of the chips besides the first one
        // GET RID OF FOR LOOP AND REPLACE WITH IF STATEMENT THAT MAKES SURE NEXT CHIP IS THREE SPACES OR LESS AWAY AND LEAVES ENOUGH ROOM FOR THE REST OF THE CHIPS TO BE PLACED
        Integer numPlaced2 = new Integer(0);
        int place = rand.nextInt((board.length - counter) - checkLocation(numPlaced.toString()) + checkLocation(numPlaced.toString()));
        //System.out.println(place);
        while (numPlaced < numChips){
            if (place > checkLocation(numPlaced2.toString()) && place < (checkLocation(numPlaced2.toString()) + 3)) {
                board[place] = numPlaced.toString();
                numPlaced++;
                numPlaced2++;
                counter--;
            } else {
                place = rand.nextInt((board.length - counter) - checkLocation(numPlaced.toString()) + checkLocation(numPlaced.toString()));
            }
        }
    }

    public String toString(){
        String printOut = "";
        for (int i = 0; i < board.length; i++){
            printOut += board[i] + " ";
        }
        return printOut;
    }

    private boolean gameOver() {
        boolean ret = false;
        for (int i = board.length - numChips; i < board.length; i++) {
            if (!(board[i].equals("-"))) {
                ret = true;
            } else {
                ret = false;
                i = board.length + 1;
            }
        }
        return ret;
    }

    private void move(Integer chip, int spaces) {
        boolean testMove = false;
        if (gameOver() == false) {

            for (int i = checkLocation(chip.toString()) + 1; i <= checkLocation(chip.toString()) + spaces; i++) {
                if (board[i].equals("-")) {
                    testMove = true;
                } else {
                    testMove = false;
                    i = board.length + 1;
                }
            }

            if (testMove == true) {
                board[checkLocation(chip.toString()) + spaces] = chip.toString();
                board[checkLocation(chip.toString())] = "-";
                
            } else{
                System.out.println("Invalid Move!");
                
            }
            
        } else {
            System.out.println("Game Over!");
            return;
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Shift test = new Shift();
        test.placeChips();
        System.out.println("Board: " + test.toString());
        while (test.gameOver() == false) {
            System.out.println("Specify the coin number and move distance");
            System.out.println("Enter the chip you wish to move"); 
            int chip = scan.nextInt();
            System.out.println("Enter the spaces you wish to move");
            int spaces = scan.nextInt();
            test.move(chip,spaces);
            System.out.println("Board: " + test.toString());
        }
        System.out.println("Game Over!");
    }
}
