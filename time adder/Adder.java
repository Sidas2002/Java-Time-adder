import java.time.LocalTime;
import java.util.Scanner;

public class Adder {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Input validation
        int currentHour = getValidInput("Enter current hour:", 0, 23, scan);
        int currentMinute = getValidInput("Enter current minute:", 0, 59, scan);
        int currentSecond = getValidInput("Enter current second:", 0, 59, scan);
        int forwardHour = getValidInput("Enter hours to go forward:", 0, 23, scan);
        int forwardMinute = getValidInput("Enter minutes to go forward:", 0, 59, scan);
        int forwardSecond = getValidInput("Enter seconds to go forward:", 0, 59, scan);

        // Calculate new time
        LocalTime currentTime = LocalTime.of(currentHour, currentMinute, currentSecond);
        LocalTime forwardTime = LocalTime.of(forwardHour, forwardMinute, forwardSecond);
        LocalTime resultTime = calculateNewTime(currentTime, forwardTime);

        // Output
        printResult(resultTime);
    }

    private static int getValidInput(String prompt, int min, int max, Scanner scanner) {
        int input;
        do {
            System.out.println(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // consume invalid input
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        return input;
    }

    private static LocalTime calculateNewTime(LocalTime currentTime, LocalTime forwardTime) {
        final int HOURS_IN_DAY = 24;

        LocalTime resultTime = currentTime.plusHours(forwardTime.getHour())
                                          .plusMinutes(forwardTime.getMinute())
                                          .plusSeconds(forwardTime.getSecond());

        if (resultTime.isAfter(LocalTime.MAX)) {
            resultTime = resultTime.minusHours(HOURS_IN_DAY);
        }

        return resultTime;
    }

    private static void printResult(LocalTime resultTime) {
        boolean isNextDay = resultTime.isAfter(LocalTime.MAX);

        if (isNextDay) {
            System.out.printf("The answer is %02d:%02d:%02d on the next day%n",
                              resultTime.getHour(), resultTime.getMinute(), resultTime.getSecond());
        } else {
            System.out.printf("The answer is %02d:%02d:%02d%n",
                              resultTime.getHour(), resultTime.getMinute(), resultTime.getSecond());
        }
    }
}
