package com.omilia.numbersinterpretation;

public class Constants {

    public Constants() {
    }

    public static final String FOURTEEN_DIGITS = "^[0-9]{14}$";
    public static final String FOURTEEN_FIXED = "00302";
    public static final String FOURTEEN_MOBILE = "003069";

    public static final String TEN_DIGITS = "^[0-9]{10}$";
    public static final String TEN_FIXED = "2";
    public static final String TEN_MOBILE = "69";

    public static final String ZERO = "0";
    public static final String TWO_ZEROS = "00";
    public static final String THREE_ZEROS = "000";

    public static final int GROUP_LENGTH_TWO = 2;
    public static final int GROUP_LENGTH_THREE = 3;

    public static final String ELEVEN = "11";
    public static final String TWELVE = "12";

    static final String VALID = "[phone number: VALID]";
    static final String INVALID = "[phone number: INVALID]";
    static final String INTERPRETATION = "Interpretation ";
    static final String INPUT_PHONE = "Input phone number: ";

    static final String PATTERN = "\\b\\d{2}\\b|\\b\\d{3}\\b";

}
