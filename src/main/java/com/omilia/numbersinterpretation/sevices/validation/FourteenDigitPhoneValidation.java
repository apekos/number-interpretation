package com.omilia.numbersinterpretation.sevices.validation;

import static com.omilia.numbersinterpretation.Constants.*;

public class FourteenDigitPhoneValidation implements PhoneValidation {

    public boolean isValidNumber(String phoneNumber) {
        return isValidNumber(phoneNumber, FOURTEEN_DIGITS, FOURTEEN_FIXED, FOURTEEN_MOBILE);
    }
}
