import java.util.Random;
import java.util.Scanner;

public class Game {

    static int highscore = Integer.MAX_VALUE;
    static int num;
    static int guess = -1;
    static int tries = 0;

    public static int returnNum(int bound) {
        Random random = new Random();
        return random.nextInt(bound) + 1;
    }

    public static void main (String[] args) {
        System.out.println("Heya! Let's play a game.");
        System.out.println("Sooooo.");
        System.out.println("You need to guess a number!");

        Scanner scanner = new Scanner(System.in);

        num = returnNum(100);

        System.out.println("Sooo, enter a number, it should be less than 100 though, and positive numbers only!");
        if (highscore != Integer.MAX_VALUE) {
            System.out.println("Current highscore: " + highscore);
        }
        else {
            System.out.println("Current highscore: NOT SET");
        }

        while (guess != num) {
            System.out.println("Try: " + (tries + 1));
            System.out.print("> ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
            } else {
                scanner.next(); // flush bad token
                System.out.println("Numbers only!");
                continue;
            }

            tries++;

            if (guess < num) {
                System.out.println("Higher!");
            }
            else if (guess > num) {
                System.out.println("Lower!");
            }
            else {
                String add_msg;

                if (tries > highscore) {
                    add_msg = (" However, you failed to beat the highscore of " + highscore + "...");
                }

                else {
                    if (highscore != Integer.MAX_VALUE) {
                        add_msg = (" YOU EVEN BEAT THE HIGHSCORE OF " + highscore + " BY A NEW SCORE OF " + tries + "!");
                    }
                    else {
                        add_msg = (" YOU HAVE SET THE FIRST HIGHSCORE: " + tries + "!");
                    }
                    highscore = tries;
                }
                System.out.println("You won!" + add_msg);
            }
        }
    }
}
