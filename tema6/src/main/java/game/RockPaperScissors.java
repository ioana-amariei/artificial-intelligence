package game;

import model.Computer;
import model.Move;
import model.User;

import java.util.Arrays;
import java.util.List;

import static model.Move.*;

public class RockPaperScissors {
    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfRounds;
    private List<Move> userHistory;

    public RockPaperScissors(User user, Computer computer, int numberOfRounds) {
        this.user = user;
        this.computer = computer;
        this.userScore = 0;
        this.computerScore = 0;
        this.numberOfRounds = numberOfRounds;
//        this.userHistory = getTrainingSetOne();
        this.userHistory = getTrainingSetTwo();
    }

    private List<Move> getTrainingSetOne() {
        return Arrays.asList(
                Paper, Paper, Rock, Scissors, Scissors, Rock, Rock, Scissors, Paper, Paper,
                Scissors, Rock, Rock, Scissors, Paper, Paper, Scissors, Rock, Rock, Paper,
                Rock, Rock, Scissors, Paper, Paper, Scissors, Scissors, Scissors, Scissors, Rock
        );
    }

    private List<Move> getTrainingSetTwo() {
        return Arrays.asList(
                Rock, Paper, Rock, Scissors, Scissors, Rock, Rock, Scissors, Paper, Rock,
                Scissors, Rock, Rock, Scissors, Rock, Paper, Rock, Rock, Rock, Paper,
                Rock, Rock, Scissors, Paper, Paper, Rock, Scissors, Rock, Rock, Rock
        );
    }

    public void play() {
        start();

        while (numberOfRounds > 0) {
            System.out.println("Rock, paper, or scissors? [R | P | S]");

            Move userShape = user.getShape();
            System.out.println("Your choice: " + userShape.toString());

            Move computerShape = computer.getShape(userHistory);
            System.out.println("Computer's choice: " + computerShape.toString());

            int compareMoves = userShape.compareMoves(computerShape);

            if (compareMoves == 0) {
                System.out.println("Tie!");

            } else if (compareMoves == 1) {
                System.out.println(userShape + " beats " + computerShape);
                userScore++;

            } else if (compareMoves == -1) {
                System.out.println(computerShape + " beats " + userShape);
                computerScore++;

            }

            --numberOfRounds;
            displayCurrentScore();
        }

        end();
    }

    private void start() {
        System.out.println("------------ROCK, PAPER, SCISSORS------------");
        displayCurrentScore();
    }

    private void end() {
        displayResults();
    }

    private void displayResults() {
        displayCurrentScore();

        if (userScore > computerScore) {
            System.out.println("User won with a score of " + userScore + " points");
        } else if (userScore < computerScore) {
            System.out.println("Computer won with a score of " + computerScore + " points");
        } else {
            System.out.println("Is a draw for both computer and user.");
        }
    }

    private void displayCurrentScore() {
        System.out.println("User score: " + userScore);
        System.out.println("Computer score: " + computerScore);
        System.out.println("---------------------------------------------");
    }
}
