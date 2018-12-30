package model;

import java.util.Scanner;

public class User {
    private Scanner scanner;

    public User() {
        this.scanner = new Scanner(System.in);
    }

    public HandShape getShape() {
        String userInput = scanner.nextLine();
        if (userInput.equals("R") || userInput.equals("P") || userInput.equals("S")) {
            switch (userInput) {
                case "R":
                    return HandShape.Rock;
                case "P":
                    return HandShape.Paper;
                case "S":
                    return HandShape.Scissors;
            }
        }
        System.out.println("Please enter a valid choice: [R | P | S]");
        return getShape();
    }
}