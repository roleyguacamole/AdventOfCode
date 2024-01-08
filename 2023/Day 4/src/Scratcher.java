import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scratcher {
    private static final HashMap<Integer, Integer> tickets = new HashMap<>();
    private static final Pattern numberPattern = Pattern.compile("\\d+");
    public static void main(String[] args) throws FileNotFoundException {
        final File input = new File("../data/input.txt");
        final Scanner inputReader = new Scanner(input);
        final Scanner inputReader2 = new Scanner(input);

        // Part 1 and initialize the map for Part 2
        Integer totalPoints = 0;
        Integer ticketNumber = 1;
        while (inputReader.hasNextLine()) {
            tickets.put(ticketNumber, 1);
            ticketNumber++;
            totalPoints += getScore(inputReader.nextLine());
        }
        inputReader.close();

        // Part 2
        Integer totalTickets = 0;
        ticketNumber = 1;
        while (inputReader2.hasNextLine()) {
            totalTickets += getTickets(inputReader2.nextLine(), tickets.get(ticketNumber), ticketNumber);
            ticketNumber++;
        }
        inputReader2.close();

        System.out.println(String.format("Answer 1: %d", totalPoints));
        System.out.println(String.format("Answer 2: %d", totalTickets));
    }

    private static Integer getNumberOfWinners(String scratchCard) {
        // Get the winning numbers and our numbers from the card
        final HashSet<Integer> winningNumbers = new HashSet<Integer>();
        final HashSet<Integer> ourNumbers = new HashSet<Integer>();
        final Matcher numberMatcher = numberPattern.matcher(scratchCard);
        Integer currentNumber = 0;
        while (numberMatcher.find()) {
            if (currentNumber > 0 && currentNumber <= 10) {
                winningNumbers.add(Integer.parseInt(numberMatcher.group()));
            }
            else if (currentNumber > 10) {
                ourNumbers.add(Integer.parseInt(numberMatcher.group()));
            }
            currentNumber++;
        }

        // Get the intersection of winning numbers and our numbers
        winningNumbers.retainAll(ourNumbers);

        return winningNumbers.size();
    }

    private static Integer getScore(String scratchCard) {
        final Integer numWinners = getNumberOfWinners(scratchCard);
        final Integer score;
        if (numWinners == 0) {
            score = 0;
        }
        else {
            score = Double.valueOf(Math.pow(2, numWinners - 1)).intValue();
        }
        return score;
    }

    private static Integer getTickets(String scratchCard, Integer numberOfTickets, Integer currentTicket) {
        final Integer numWinners = getNumberOfWinners(scratchCard);
        for (int i = currentTicket + 1; i <= currentTicket + numWinners; i++) {
            if (tickets.keySet().contains(i)) {
                tickets.put(i, tickets.get(i) + numberOfTickets);
            }
        }
        return numberOfTickets;
    }
}
