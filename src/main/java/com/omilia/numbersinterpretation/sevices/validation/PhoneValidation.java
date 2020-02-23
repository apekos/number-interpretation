package com.omilia.numbersinterpretation.sevices.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface PhoneValidation {

    default boolean isValidNumber(String phoneNumber, String digits, String fixed, String mobile) {
        String noSpacesPhone = phoneNumber.replaceAll("\\s+", "");
        boolean isValid = false;

        Pattern digitsPattern = Pattern.compile(digits);
        Matcher matcher = digitsPattern.matcher(noSpacesPhone);

        if (noSpacesPhone.matches("[0-9]+")) {
            if (matcher.find() && matcher.group().equals(noSpacesPhone) && (noSpacesPhone.startsWith(fixed) || noSpacesPhone.startsWith(mobile))) {
                isValid = true;
            }

            if (noSpacesPhone.length() < 10 && !matcher.find()) {
                isValid = false;
            }
        } else {
            isValid = false;
        }

        return isValid;
    }
}
