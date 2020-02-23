package com.omilia.numbersinterpretation.sevices.validation;

import static com.omilia.numbersinterpretation.Constants.*;

public class TenDigitPhoneValidation implements PhoneValidation {

    public boolean isValidNumber(String phoneNumber) {
        return isValidNumber(phoneNumber, TEN_DIGITS, TEN_FIXED, TEN_MOBILE);
    }
}
