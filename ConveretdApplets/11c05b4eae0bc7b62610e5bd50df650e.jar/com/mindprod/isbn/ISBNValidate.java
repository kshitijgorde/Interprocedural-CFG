// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.isbn;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;

public final class ISBNValidate
{
    private static final boolean DEBUGGING = false;
    private static final int[] BANDS;
    private static final int[] VALID_GROUPS;
    private static BitSet groupValidLookup;
    
    public static String appendCheckDigitToISBN12(final String dashlessISBN12) {
        if (dashlessISBN12.length() != 12) {
            throw new IllegalArgumentException("ISBN-12 must be 12 chars long without dashes, without check digit");
        }
        return dashlessISBN12 + calcISBN13CheckDigit(dashlessISBN12);
    }
    
    public static String appendCheckDigitToISBN9(final String dashlessISBN9) {
        if (dashlessISBN9.length() != 9) {
            throw new IllegalArgumentException("ISBN-9 must be 9 chars long without dashes, without check digit");
        }
        return dashlessISBN9 + calcISBN10CheckDigit(dashlessISBN9);
    }
    
    public static void ensureISBN10Valid(final String dashlessISBN10) {
        if (dashlessISBN10.length() != 10) {
            throw new IllegalArgumentException("ISBN-10 must be 10 digits long");
        }
        if (dashlessISBN10.charAt(9) == 'x') {
            throw new IllegalArgumentException("ISBN-10 should end in a capital X");
        }
        ensureISBN10GroupValid(dashlessISBN10);
        ensureISBN10PublisherValid(dashlessISBN10);
        if (!isISBN10CheckDigitValid(dashlessISBN10)) {
            throw new IllegalArgumentException("Bad Check Digit");
        }
    }
    
    public static void ensureISBN13Valid(final String dashlessISBN13) {
        if (dashlessISBN13.length() != 13) {
            throw new IllegalArgumentException("ISBN-13 must be 13 digits.");
        }
        final String lead = dashlessISBN13.substring(0, 3);
        if (!lead.equals("978") && !lead.equals("979")) {
            throw new IllegalArgumentException("ISBN-13 must begin with 978 or 979.");
        }
        final String isbn10Part = dashlessISBN13.substring(3, 13);
        ensureISBN10GroupValid(isbn10Part);
        ensureISBN10PublisherValid(isbn10Part);
        if (!isISBN13CheckDigitValid(dashlessISBN13)) {
            throw new IllegalArgumentException("Bad check digit.");
        }
    }
    
    public static boolean isISBN10CheckDigitValid(final String dashlessISBN10) {
        if (dashlessISBN10.length() != 10) {
            throw new IllegalArgumentException("ISBN-10 must be 10 digits long.");
        }
        return calcISBN10CheckDigit(dashlessISBN10.substring(0, 9)) == dashlessISBN10.charAt(9);
    }
    
    public static boolean isISBN13CheckDigitValid(final String dashlessISBN13) {
        if (dashlessISBN13.length() != 13) {
            throw new IllegalArgumentException("ISBN-13 must be 13 digits long.");
        }
        return calcISBN13CheckDigit(dashlessISBN13.substring(0, 12)) == dashlessISBN13.charAt(12);
    }
    
    public static String isbn10To13(final String dashlessISBN10) {
        if (dashlessISBN10.length() != 10) {
            throw new IllegalArgumentException("ISBN-10 must be 10 chars long without dashes, with check digit");
        }
        return appendCheckDigitToISBN12("978" + dashlessISBN10.substring(0, 9));
    }
    
    public static String isbn13To10(final String dashlessISBN13) {
        if (dashlessISBN13.length() != 13) {
            throw new IllegalArgumentException("ISBN-13 must be 13 chars long without dashes, with check digit.");
        }
        if (dashlessISBN13.substring(0, 3).equals("978")) {
            return appendCheckDigitToISBN9(dashlessISBN13.substring(3, 12));
        }
        return "n/a";
    }
    
    public static String tidyISBN10or13InsertingDashes(final String rawISBN10or13) {
        if (rawISBN10or13 == null || rawISBN10or13.length() == 0) {
            throw new IllegalArgumentException("no ISBN");
        }
        final String dashlessISBN = strip(rawISBN10or13);
        final int length = dashlessISBN.length();
        switch (length) {
            case 10: {
                ensureISBN10Valid(dashlessISBN);
                return insertDashesInISBN10or13(dashlessISBN);
            }
            case 13: {
                ensureISBN13Valid(dashlessISBN);
                return insertDashesInISBN10or13(dashlessISBN);
            }
            default: {
                throw new IllegalArgumentException("ISBN must be 10 or 13 digits long.");
            }
        }
    }
    
    public static String tidyISBN10or13RemovingDashes(final String rawISBN10or13) {
        if (rawISBN10or13 == null || rawISBN10or13.length() == 0) {
            throw new IllegalArgumentException("no ISBN");
        }
        final String dashlessISBN = strip(rawISBN10or13);
        final int length = dashlessISBN.length();
        switch (length) {
            case 10: {
                ensureISBN10Valid(dashlessISBN);
                return dashlessISBN;
            }
            case 13: {
                ensureISBN13Valid(dashlessISBN);
                return dashlessISBN;
            }
            default: {
                throw new IllegalArgumentException("must be 10 or 13 digits long.");
            }
        }
    }
    
    private static void buildGroupValidLookup() {
        ISBNValidate.groupValidLookup = new BitSet(100000);
        for (final int group : ISBNValidate.VALID_GROUPS) {
            final int width = Integer.toString(group).length();
            switch (width) {
                case 5: {
                    ISBNValidate.groupValidLookup.set(group);
                    break;
                }
                case 4: {
                    for (int j = group * 10; j <= group * 10 + 9; ++j) {
                        ISBNValidate.groupValidLookup.set(j);
                    }
                    break;
                }
                case 3: {
                    for (int j = group * 100; j <= group * 100 + 99; ++j) {
                        ISBNValidate.groupValidLookup.set(j);
                    }
                    break;
                }
                case 2: {
                    for (int j = group * 1000; j <= group * 1000 + 999; ++j) {
                        ISBNValidate.groupValidLookup.set(j);
                    }
                    break;
                }
                case 1: {
                    for (int j = group * 10000; j <= group * 10000 + 9999; ++j) {
                        ISBNValidate.groupValidLookup.set(j);
                    }
                    break;
                }
                default: {
                    throw new IllegalArgumentException("bug: bad VALID_GROUPS table");
                }
            }
        }
    }
    
    private static char calcISBN10CheckDigit(final String dashlessISBN9) {
        if (dashlessISBN9.length() != 9) {
            throw new IllegalArgumentException("ISBN-9 must be 9 digits.");
        }
        int sum = 0;
        for (int weight = 2; weight <= 10; ++weight) {
            final int digit = dashlessISBN9.charAt(10 - weight) - '0';
            sum += digit * weight;
        }
        final int remainder = sum % 11;
        switch (remainder) {
            case 0: {
                return '0';
            }
            case 1: {
                return 'X';
            }
            default: {
                return (char)(11 - remainder + 48);
            }
        }
    }
    
    private static char calcISBN13CheckDigit(final String dashlessISBN12) {
        if (dashlessISBN12.length() != 12) {
            throw new IllegalArgumentException("ISBN-12 must be 12 digits.");
        }
        int sum = 0;
        for (int i = 0; i < 12; ++i) {
            final int digit = dashlessISBN12.charAt(i) - '0';
            sum += (((i & 0x1) != 0x0) ? (digit * 3) : digit);
        }
        final int remainder = sum % 10;
        return (remainder == 0) ? '0' : ((char)(58 - remainder));
    }
    
    private static void ensureISBN10GroupValid(final String isbn10) {
        int group;
        try {
            group = Integer.parseInt(isbn10.substring(0, 5));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bad group number");
        }
        if (!ISBNValidate.groupValidLookup.get(group)) {
            throw new IllegalArgumentException("Bad group number");
        }
    }
    
    private static void ensureISBN10PublisherValid(final String dashlessISBN10) {
        try {
            Integer.parseInt(dashlessISBN10.substring(0, 9));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bad publisher");
        }
    }
    
    private static String insertDashesInISBN10or13(final String dashlessISBN10or13) {
        final int length = dashlessISBN10or13.length();
        int range = 0;
        boolean isISBN13 = false;
        try {
            switch (length) {
                case 10: {
                    range = Integer.parseInt(dashlessISBN10or13.substring(0, 9));
                    isISBN13 = false;
                    break;
                }
                case 13: {
                    range = Integer.parseInt(dashlessISBN10or13.substring(3, 12));
                    isISBN13 = true;
                    break;
                }
                default: {
                    return dashlessISBN10or13;
                }
            }
        }
        catch (NumberFormatException e) {
            return dashlessISBN10or13;
        }
        int whereFirstDash = -1;
        int whereMidDash = -1;
        final int whereLastDash = 8;
        final boolean tryAgain = false;
        do {
            int band = 0;
            for (int i = 0; i < ISBNValidate.BANDS.length; ++i) {
                if (range < ISBNValidate.BANDS[i]) {
                    band = i;
                    break;
                }
            }
            switch (band) {
                default: {
                    continue;
                }
                case 0:
                case 6: {
                    whereFirstDash = 0;
                    whereMidDash = whereFirstDash + 2;
                    continue;
                }
                case 1:
                case 7: {
                    whereFirstDash = 0;
                    whereMidDash = whereFirstDash + 3;
                    continue;
                }
                case 2:
                case 8: {
                    whereFirstDash = 0;
                    whereMidDash = whereFirstDash + 4;
                    continue;
                }
                case 3:
                case 9: {
                    whereFirstDash = 0;
                    whereMidDash = whereFirstDash + 5;
                    continue;
                }
                case 4:
                case 10: {
                    whereFirstDash = 0;
                    whereMidDash = whereFirstDash + 6;
                    continue;
                }
                case 5:
                case 11: {
                    whereFirstDash = 0;
                    whereMidDash = whereFirstDash + 7;
                    continue;
                }
                case 12: {
                    whereFirstDash = 0;
                    continue;
                }
                case 13: {
                    whereFirstDash = 1;
                    continue;
                }
                case 14: {
                    whereFirstDash = 2;
                    continue;
                }
                case 15: {
                    whereFirstDash = 3;
                    continue;
                }
                case 16: {
                    whereFirstDash = 4;
                    continue;
                }
            }
        } while (tryAgain);
        final StringBuilder cooked = new StringBuilder(17);
        if (isISBN13) {
            cooked.append(dashlessISBN10or13.substring(0, 3));
            cooked.append('-');
        }
        for (int i = 0; i < 10; ++i) {
            final char c = dashlessISBN10or13.charAt(isISBN13 ? (i + 3) : i);
            cooked.append(c);
            if (i == whereFirstDash || i == whereMidDash || i == 8) {
                cooked.append('-');
            }
        }
        return cooked.toString();
    }
    
    private static String strip(String rawISBN10or13) {
        boolean foundNumbers = false;
        for (int i = 0; i < rawISBN10or13.length(); ++i) {
            final char c = rawISBN10or13.charAt(i);
            if ("0123456789".indexOf(c) >= 0) {
                rawISBN10or13 = rawISBN10or13.substring(i);
                foundNumbers = true;
                break;
            }
        }
        if (!foundNumbers) {
            return "";
        }
        for (int i = rawISBN10or13.length() - 1; i >= 0; --i) {
            final char c = rawISBN10or13.charAt(i);
            if ("0123456789Xx".indexOf(c) >= 0) {
                rawISBN10or13 = rawISBN10or13.substring(0, i + 1);
                break;
            }
        }
        final StringBuilder cooked = new StringBuilder(rawISBN10or13.length());
        for (int j = 0; j < rawISBN10or13.length(); ++j) {
            final char c2 = rawISBN10or13.charAt(j);
            if ("0123456789".indexOf(c2) >= 0) {
                cooked.append(c2);
            }
            else if ("Xx".indexOf(c2) >= 0) {
                cooked.append('X');
            }
        }
        if (cooked.length() == 14 && cooked.charAt(13) == 'X') {
            cooked.delete(13, 13);
        }
        return cooked.toString();
    }
    
    public static void main(final String[] args) {
    }
    
    static {
        BANDS = new int[] { 20000000, 70000000, 85000000, 90000000, 95000000, 100000000, 110000000, 140000000, 155000000, 186980000, 199900000, 200000000, 800000000, 950000000, 994000000, 999000000, 1000000000 };
        VALID_GROUPS = new int[] { 0, 1, 2, 3, 4, 5, 7, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 98, 600, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, 989, 9940, 9941, 9942, 9943, 9944, 9945, 9946, 9947, 9948, 9949, 9950, 9951, 9952, 9953, 9954, 9955, 9956, 9957, 9958, 9959, 9960, 9961, 9962, 9963, 9964, 9965, 9966, 9967, 9968, 9970, 9971, 9972, 9973, 9974, 9975, 9976, 9977, 9978, 9979, 9980, 9981, 9982, 9983, 9984, 9985, 9986, 9987, 9988, 9989, 99901, 99902, 99903, 99905, 99906, 99908, 99909, 99910, 99911, 99912, 99913, 99914, 99915, 99916, 99917, 99918, 99919, 99920, 99921, 99922, 99923, 99924, 99925, 99926, 99927, 99928, 99929, 99930, 99931, 99932, 99933, 99934, 99935, 99936, 99937, 99938, 99939, 99940, 99941, 99942, 99943, 99944, 99945, 99946, 99947, 99948, 99949, 99950, 99951, 99952, 99953, 99954, 99955 };
        buildGroupValidLookup();
    }
}
