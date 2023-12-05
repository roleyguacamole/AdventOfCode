import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Scanner;

class Calibration {

    private static HashMap<String, Character> wordToDigitMap = new HashMap<String, Character>()
    {
        {
            put("one", '1');
            put("two", '2');
            put("three", '3');
            put("four", '4');
            put("five", '5');
            put("six", '6');
            put("seven", '7');
            put("eight", '8');
            put("nine", '9');
        }
    };
    
    public static void main(String[] args) throws FileNotFoundException {
        final File input = new File("../data/input.txt");
        final Scanner inputReader = new Scanner(input);
        Integer totalValue = 0;
        while (inputReader.hasNextLine()) {
            final String line = inputReader.nextLine();
            totalValue = totalValue + getCalibrationValueFromLine(line);
        }
        inputReader.close();
        System.out.println(String.format("Answer: %d", totalValue));
    }

    private static Integer getCalibrationValueFromLine(String line) {
        Boolean isFirst = true;
        Character firstDigit = '0';
        Character lastDigit = '0';
        String candidateWord = "";
        //System.out.println(String.format("Line: %s", line));
        for (int i = 0; i < line.length(); i++) {
            final Character c = line.charAt(i);
            if (Character.isDigit(c)) {
                firstDigit = isFirst ? c : firstDigit;
                isFirst = false;
                lastDigit = c;
                candidateWord = "";
            } 
            else {
                candidateWord = candidateWord + c;
                if (wordToDigitMap.containsKey(candidateWord)) {
                    firstDigit = isFirst ? wordToDigitMap.get(candidateWord) : firstDigit;
                    isFirst = false;
                    lastDigit = wordToDigitMap.get(candidateWord);
                    candidateWord = getNextLargestCandidateWord(candidateWord);
                }
                else {
                    if (!isSubstringOfWord(candidateWord)) {
                        candidateWord = getNextLargestCandidateWord(candidateWord);
                    }
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(firstDigit);
        sb.append(lastDigit);
        final String answer = sb.toString();
        //System.out.println(String.format("Calibration Value: %s", answer));
        return Integer.parseInt(answer);
    }

    private static Boolean isSubstringOfWord(String candidate) {
        Boolean result = false;
        for (String word : wordToDigitMap.keySet()) {
            if (word.length() >= candidate.length()) {
                if (word.substring(0, candidate.length()).equals(candidate)) {
                    result = true;
                }
            }
        }
        return result;
    }

    private static String getNextLargestCandidateWord(String candidate) {
        String newCandidate = "";
        for (int i = 1; i < candidate.length(); i++) {
            newCandidate = candidate.substring(i, candidate.length());
            if (isSubstringOfWord(newCandidate)) {
                break;
            }
        }
        return newCandidate;
    }
}