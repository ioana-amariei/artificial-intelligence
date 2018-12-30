package model;

import java.util.Scanner;

public class User {
    private Scanner scanner = new Scanner(System.in);

    public Move getShape() {
        String userInput = scanner.nextLine();

        if ("R".equals(userInput)) {
            return Move.Rock;
        } else if ("P".equals(userInput)) {
            return Move.Paper;
        } else if ("S".equals(userInput)) {
            return Move.Scissors;
        } else {
            System.out.println("Please enter a valid choice: [R | P | S]");
        }

        return getShape();
    }
}