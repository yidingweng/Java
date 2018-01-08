package yiding.softwareguildweek1;

/**
 *
 * @author yidingweng
 */
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    static int numberOfGames;
    static int numberOfWin = 0;
    static int numberOfLoss = 0;
    static int numberOfDraw = 0;
    
    public static void main(String[] args) {
        askRoundNumber();
    }
    
    public static void askRoundNumber() {
        System.out.println("How many games do you want to play (minimum 1, maximum 10)? ");
        Scanner sc = new Scanner(System.in);
        numberOfGames = Integer.parseInt(sc.nextLine());
        checkRange(numberOfGames);
    }
    public static void checkRange(int number) {
        if (number >= 1 && number <= 10){
            askUserChoice();
        } else {
            System.out.println("You entered a number outside of the range(1-10).");;
        }
    }
    public static void askUserChoice(){
        System.out.println("Rock,paper,or scissor? (1 for rock,2 for paper, 3 for scissor) ");
        Scanner sc = new Scanner(System.in);
        int userChoice = Integer.parseInt(sc.nextLine());
        vsComputer(userChoice);
    }
    
    public static void vsComputer(int userChoice){
        Random rGen = new Random();
        int computerChoice = rGen.nextInt(3)+1;
        int difference = userChoice - computerChoice;
        if(difference == 0){
            numberOfDraw++;
            System.out.println("You draw this time.");
        }
        else if ((difference == 1) || (difference == -2)){
            numberOfWin++;
            System.out.println("You win this time.");
        } else {
            numberOfLoss++;
            System.out.println("You lose this time.");
        }
        numberOfGames--;
        repeatChoice();       
    }
    
    public static void repeatChoice(){
        if (numberOfGames > 0){
            askUserChoice();
        } else {
            printResult();
        }
    }
    public static void printResult(){
        System.out.println("You win " + numberOfWin + " games.");
        System.out.println("You lose " + numberOfLoss + " games.");
        System.out.println("You draw " + numberOfDraw + " games.");
        repeatGame();
    }
    public static void repeatGame(){
        System.out.println("Do you want to play again? (1 for Yes,2 for No) ");
        Scanner sc = new Scanner(System.in);
        int repeat = Integer.parseInt(sc.nextLine());
        if (repeat == 1){
            numberOfWin = 0;
            numberOfLoss = 0;
            numberOfDraw = 0;
            askRoundNumber();
        } else {
            System.out.println("Thanks for playing!");
        }
    }
}

