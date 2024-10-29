import java.util.Scanner;
public class GameUI {
    private final Scanner input;

    public GameUI() {
        this.input = new Scanner(System.in);
    }

    public String readUserGuess(){
        System.out.println("Enter Your Guess:");
        return this.input.next();
    }

    public static void displayResult(String feedback, String guess, int attemptsLeft){
        System.out.println("You have " + attemptsLeft + " attempts left");
        System.out.println("Your guess is " +  guess);
        System.out.println("Feedback: " + feedback);
    }
    public static void displayWin() {
        System.out.println("Congratulations, YOU WIN!");
    }
    public static void displayLoss(String targetWord) {
        System.out.println("Game over! The word was: " + targetWord);
    }

}
