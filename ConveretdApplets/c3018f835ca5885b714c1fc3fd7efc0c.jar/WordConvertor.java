// 
// Decompiled by Procyon v0.5.30
// 

public class WordConvertor
{
    public static String tensName(final int n) {
        String tensName = null;
        switch (n) {
            case 2: {
                tensName = "twenty";
                break;
            }
            case 3: {
                tensName = "thirty";
                break;
            }
            case 4: {
                tensName = "forty";
                break;
            }
            case 5: {
                tensName = "fifty";
                break;
            }
            case 6: {
                tensName = "sixty";
                break;
            }
            case 7: {
                tensName = "seventy";
                break;
            }
            case 8: {
                tensName = "eighty";
                break;
            }
            case 9: {
                tensName = "ninety";
                break;
            }
            default: {
                tensName = "";
                break;
            }
        }
        return tensName;
    }
    
    public static String teenName(final int n) {
        String teenName = null;
        switch (n) {
            case 10: {
                teenName = "ten";
                break;
            }
            case 11: {
                teenName = "eleven";
                break;
            }
            case 12: {
                teenName = "twelve";
                break;
            }
            case 13: {
                teenName = "thirteen";
                break;
            }
            case 14: {
                teenName = "fourteen";
                break;
            }
            case 15: {
                teenName = "fifteen";
                break;
            }
            case 16: {
                teenName = "sixteen";
                break;
            }
            case 17: {
                teenName = "seventeen";
                break;
            }
            case 18: {
                teenName = "eighteen";
                break;
            }
            case 19: {
                teenName = "nineteen";
                break;
            }
            default: {
                teenName = "";
                break;
            }
        }
        return teenName;
    }
    
    public static String digitName(final int n) {
        String digitName = null;
        switch (n) {
            case 1: {
                digitName = "one";
                break;
            }
            case 2: {
                digitName = "two";
                break;
            }
            case 3: {
                digitName = "three";
                break;
            }
            case 4: {
                digitName = "four";
                break;
            }
            case 5: {
                digitName = "five";
                break;
            }
            case 6: {
                digitName = "six";
                break;
            }
            case 7: {
                digitName = "seven";
                break;
            }
            case 8: {
                digitName = "eight";
                break;
            }
            case 9: {
                digitName = "nine";
                break;
            }
            default: {
                digitName = "";
                break;
            }
        }
        return digitName;
    }
    
    public static String intName(final int n) {
        String result = "";
        int c = n;
        if (c >= 1000000000) {
            result = intName(c / 1000000000) + " billion";
            c %= 1000000000;
        }
        if (c >= 1000000) {
            result = result + " " + intName(c / 1000000) + " million";
            c %= 1000000;
        }
        if (c >= 1000) {
            result = result + " " + intName(c / 1000) + " thousand";
            c %= 1000;
        }
        if (c >= 100) {
            result = result + " " + digitName(c / 100) + " hundred";
            c %= 100;
        }
        if (c >= 20) {
            result = result + " " + tensName(c / 10);
            c %= 10;
        }
        else if (c >= 10) {
            result = result + " " + teenName(c);
            c = 0;
        }
        if (c >= 0) {
            result = result + " " + digitName(c);
        }
        return result;
    }
}
