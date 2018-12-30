package game;

import model.Computer;
import model.HandShape;
import model.User;

public class RockPaperScissors {
    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfRounds;

    public RockPaperScissors(User user, Computer computer, int numberOfRounds) {
        this.user = user;
        this.computer = computer;
        this.userScore = 0;
        this.computerScore = 0;
        this.numberOfRounds = numberOfRounds;
    }

    public int getUserScore() {
        return userScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void play() {
        start();

        while (numberOfRounds > 0) {
            System.out.println("Rock, paper, or scissors? [R | P | S]");

            HandShape userShape = user.getShape();
            System.out.println("Your choice: " + userShape.toString());

            HandShape computerShape = computer.getShape();
            System.out.println("Computer's choice: " + computerShape.toString());

            int compareMoves = userShape.compareMoves(computerShape);
            switch (compareMoves) {
                case 0:
                    System.out.println("Tie!");
                    break;
                case 1:
                    System.out.println(userShape + " beats " + computerShape);
                    userScore++;
                    break;
                case -1:
                    System.out.println(computerShape + " beats " + userShape);
                    computerScore++;
                    break;
            }

            displayCurrentScore();
            --numberOfRounds;
        }

        end();
    }

    private void start(){
        System.out.println("------------ROCK, PAPER, SCISSORS!------------");
        displayCurrentScore();
    }

    private void end() {
        displayResults();
    }

    private void displayResults(){
        displayCurrentScore();

        if(userScore > computerScore) {
            System.out.println("User won with a score of " + userScore + " points");
        } else if (userScore < computerScore) {
            System.out.println("Computer won with a score of " + computerScore + " points");
        } else {
            System.out.println("Is a draw for both computer and user.");
        }
    }

    private void displayCurrentScore(){
        System.out.println("User score: " + userScore);
        System.out.println("Computer score: " + computerScore);
        System.out.println("----------------------------------------------");
    }
}
