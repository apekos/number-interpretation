package com.omilia.numbersinterpretation.sevices.interpretation;

import java.util.List;

import static com.omilia.numbersinterpretation.Constants.*;

public class InterpretationCases {

    public StringBuilder getNumberInterpretations(List<StringBuilder> listOfCombinations, StringBuilder newNumber, String group) {

        if (group.length() == GROUP_LENGTH_THREE && !group.substring(1, 3).equals(TWO_ZEROS)) {
            listOfCombinations.add(newNumber);
            newNumber = new StringBuilder(newNumber).insert(newNumber.indexOf(group) + 1, TWO_ZEROS);
            listOfCombinations.add(newNumber);
        }

        if (group.length() == GROUP_LENGTH_THREE && group.substring(1, 3).equals(TWO_ZEROS) && newNumber.length() != newNumber.indexOf(group) && !group.equals(newNumber.substring(newNumber.length() - 3))) {
            listOfCombinations.add(newNumber);
            newNumber = new StringBuilder(newNumber).replace(newNumber.indexOf(group) + 1, newNumber.indexOf(group) + 4, "");
            listOfCombinations.add(newNumber);
        }

        if (group.length() == GROUP_LENGTH_TWO && !group.substring(1, 2).equals(ZERO)){
            listOfCombinations.add(newNumber);
            newNumber = new StringBuilder(newNumber).insert(newNumber.indexOf(group) + 1, "0");
            listOfCombinations.add(newNumber);
        }

        if (group.length() == GROUP_LENGTH_TWO && group.substring(1, 2).equals(ZERO) && newNumber.length() != newNumber.indexOf(group) && !group.equals(newNumber.substring(newNumber.length() - 2))){
            listOfCombinations.add(newNumber);
            newNumber = new StringBuilder(newNumber).replace(newNumber.indexOf(group) + 1, newNumber.indexOf(group) + 3, "");
            listOfCombinations.add(newNumber);
        }
        return newNumber;
    }
}
