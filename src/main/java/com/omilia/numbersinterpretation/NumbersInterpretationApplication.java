package com.omilia.numbersinterpretation;

import com.omilia.numbersinterpretation.sevices.Utils.PrefixCodeCombinations;
import com.omilia.numbersinterpretation.sevices.interpretation.InterpretationCases;
import com.omilia.numbersinterpretation.sevices.validation.FourteenDigitPhoneValidation;
import com.omilia.numbersinterpretation.sevices.validation.TenDigitPhoneValidation;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.omilia.numbersinterpretation.Constants.*;

public class NumbersInterpretationApplication {

    public static void main(String[] args) {

        TenDigitPhoneValidation tenDigitPhoneValidation = new TenDigitPhoneValidation();
        FourteenDigitPhoneValidation fourteenDigitPhoneValidation = new FourteenDigitPhoneValidation();
        InterpretationCases interpretationCases = new InterpretationCases();
        PrefixCodeCombinations prefixCodeCombinations = new PrefixCodeCombinations();
        String prefix = "";

        System.out.println("Please enter a number:");
        Scanner scanner = new Scanner(System.in);
        String inputPhone = scanner.nextLine();
        System.out.println();

        // Instantiation of stringbuilder with string input
        StringBuilder phoneNumber = new StringBuilder(inputPhone.trim());


        // Get all combinations of fourteen digits prefix
        List<List<String>> combos = prefixCodeCombinations.getCombos(FOURTEEN_FIXED);
        List<List<String>> combos2 = prefixCodeCombinations.getCombos(FOURTEEN_MOBILE);

        List<String> toBeJoinedList1 = new ArrayList<>();
        List<String> toBeJoinedList2 = new ArrayList<>();

        for (List<String> strCombos : combos) {
            String joinStr = String.join(" ", strCombos);
            toBeJoinedList1.add(joinStr);
        }

        for (List<String> strCombos : combos2) {
            String joinStr = String.join(" ", strCombos);
            toBeJoinedList2.add(joinStr);
        }

        // Join two lists with fixed and mobile prefix combinations
        List<String> joinedList = Stream.of(toBeJoinedList1, toBeJoinedList2)
                .distinct()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

            // Validation of input phone number
            for (String combo : joinedList) {

                if (phoneNumber.toString().startsWith(combo)) {
                    if (fourteenDigitPhoneValidation.isValidNumber(phoneNumber.toString())) {
                        System.out.println(INPUT_PHONE + phoneNumber.toString().replaceAll("\\s+", "") + "\t" + VALID);
                    } else {
                        System.out.println(INPUT_PHONE + phoneNumber.toString().replaceAll("\\s+", "") + "\t" + INVALID);
                    }

                    prefix = phoneNumber.toString().replaceAll("\\s+", "").substring(0, 5);
                    phoneNumber.delete(0, combo.length() - 1);
                    break;

                } else if (phoneNumber.toString().startsWith(TEN_FIXED) || phoneNumber.toString().startsWith(TEN_MOBILE)) {

                    if (tenDigitPhoneValidation.isValidNumber(phoneNumber.toString())) {
                        System.out.println(INPUT_PHONE + phoneNumber.toString().replaceAll("\\s+", "") + "\t" + VALID);
                    } else {
                        System.out.println(INPUT_PHONE + phoneNumber.toString().replaceAll("\\s+", "") + "\t" + INVALID);
                    }

                    prefix = phoneNumber.substring(0, 1);
                    phoneNumber.deleteCharAt(0);
                }
            }

        System.out.println();

        // Pattern to locate the sequences
        Pattern digitsPattern = Pattern.compile(PATTERN);
        Matcher digitsMatcher = digitsPattern.matcher(phoneNumber);

        List<StringBuilder> listOfCombinations = new ArrayList<>();
        List<String> collectMatches = new ArrayList<>();

        // Collect all matches to list
        while (digitsMatcher.find()) {
            collectMatches.add(digitsMatcher.group());
        }

        // Number interpretations
        for (int k = 1; k <= collectMatches.size(); k++) {

            StringBuilder newNumber = new StringBuilder(phoneNumber.toString().replaceAll("\\s+", ""));

            for (int m = k - 1; m < collectMatches.size(); m++) {
                String group = collectMatches.get(m);

                if (group.equals(TWO_ZEROS) || group.equals(THREE_ZEROS) || group.equals(ELEVEN) || group.equals(TWELVE)) {
                    continue;
                }

                newNumber = interpretationCases.getNumberInterpretations(listOfCombinations, newNumber, group);
            }
        }

        String finalPrefix = prefix;
        // Add prefix and remove whitespaces
        List<String> noDuplicates = listOfCombinations.stream()
                .distinct()
                .map(x -> x.insert(0, finalPrefix))
                .map(x -> x.toString().replaceAll("\\s+", ""))
                .collect(Collectors.toList());

        // Clear duplicates
        Set<String> set = new LinkedHashSet<>(noDuplicates);
        noDuplicates.clear();
        noDuplicates.addAll(set);

        // Print all interpretations and check validity
        AtomicInteger counter = new AtomicInteger(1);
        noDuplicates.forEach(item -> {
            if (fourteenDigitPhoneValidation.isValidNumber(item) || tenDigitPhoneValidation.isValidNumber(item)) {
                System.out.println(INTERPRETATION + counter.getAndIncrement() + ": " + item + "\t" + VALID);
            } else {
                System.out.println(INTERPRETATION + counter.getAndIncrement() + ": " + item + "\t" + INVALID);
            }
        });
    }
}
