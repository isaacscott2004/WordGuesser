import gamePlay.Repl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean playAgain = true;
        Scanner scanner = new Scanner(System.in);
        while(playAgain) {
            Repl repl = new Repl();
            repl.run(scanner);
            String input = "";
            while (!input.equals("yes") && !input.equals("no")) {
                System.out.println("Do you want to play again? Type 'yes' to play again or 'no' to quit.");
                System.out.print(">>> ");
                input = scanner.nextLine().strip().toLowerCase();
            }

            playAgain = input.equals("yes");
        }

        System.out.println("Goodbye!");
        scanner.close();
        }
}