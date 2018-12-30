import game.RockPaperScissors;
import model.Computer;
import model.User;

public class Application {
    public static void main(String[] args) {
        User user = new User();
        Computer computer = new Computer();
        int numberOfRounds = 15;

        RockPaperScissors game = new RockPaperScissors(user, computer, numberOfRounds);
        game.play();
    }
}
