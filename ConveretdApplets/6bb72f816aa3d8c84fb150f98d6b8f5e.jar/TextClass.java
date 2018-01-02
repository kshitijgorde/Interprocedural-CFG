// 
// Decompiled by Procyon v0.5.30
// 

final class TextClass
{
    private static final char[] validChars;
    
    public static long longForName(final String s) {
        long n = 0L;
        for (int n2 = 0; n2 < s.length() && n2 < 12; ++n2) {
            final char char1 = s.charAt(n2);
            n *= 37L;
            if (char1 >= 'A' && char1 <= 'Z') {
                n += '\u0001' + char1 - 'A';
            }
            else if (char1 >= 'a' && char1 <= 'z') {
                n += '\u0001' + char1 - 'a';
            }
            else if (char1 >= '0' && char1 <= '9') {
                n += '\u001b' + char1 - '0';
            }
        }
        while (n % 37L == 0L && n != 0L) {
            n /= 37L;
        }
        return n;
    }
    
    public static String nameForLong(long n) {
        try {
            if (n <= 0L || n >= 6582952005840035281L) {
                return "invalid_name";
            }
            if (n % 37L == 0L) {
                return "invalid_name";
            }
            int n2;
            char[] array;
            long n3;
            for (n2 = 0, array = new char[12]; n != 0L; n /= 37L, array[11 - n2++] = TextClass.validChars[(int)(n3 - n * 37L)]) {
                n3 = n;
            }
            return new String(array, 12 - n2, n2);
        }
        catch (RuntimeException ex) {
            SignLink.reporterror("81570, " + n + ", " + -99 + ", " + ex.toString());
            throw new RuntimeException();
        }
    }
    
    public static long method585(String upperCase) {
        upperCase = upperCase.toUpperCase();
        long n = 0L;
        for (int i = 0; i < upperCase.length(); ++i) {
            final long n2 = n * 61L + upperCase.charAt(i) - 32L;
            n = (n2 + (n2 >> 56) & 0xFFFFFFFFFFFFFFL);
        }
        return n;
    }
    
    public static String method586(final int n) {
        return (n >> 24 & 0xFF) + "." + (n >> 16 & 0xFF) + "." + (n >> 8 & 0xFF) + "." + (n & 0xFF);
    }
    
    public static String fixName(final String s) {
        if (s.length() > 0) {
            final char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                if (charArray[i] == '_') {
                    charArray[i] = ' ';
                    if (i + 1 < charArray.length && charArray[i + 1] >= 'a' && charArray[i + 1] <= 'z') {
                        charArray[i + 1] = (char)(charArray[i + 1] + 'A' - 'a');
                    }
                }
            }
            if (charArray[0] >= 'a' && charArray[0] <= 'z') {
                charArray[0] = (char)(charArray[0] + 'A' - 'a');
            }
            return new String(charArray);
        }
        return s;
    }
    
    public static String passwordAsterisks(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            sb.append("*");
        }
        return sb.toString();
    }
    
    static {
        validChars = new char[] { '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    }
}
