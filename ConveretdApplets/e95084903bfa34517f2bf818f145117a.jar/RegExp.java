// 
// Decompiled by Procyon v0.5.30
// 

public class RegExp
{
    private final char METACH_ANY = '.';
    private final char METACH_BEGCHSET = '[';
    private final char METACH_BOL = '^';
    private final char METACH_CH_RANGE = '-';
    private final char METACH_ENDCHSET = ']';
    private final char METACH_EOL = '$';
    private final char METACH_LIT = '\\';
    private final char METACH_OREP = '+';
    private final char METACH_ZREP = '*';
    private final char TYPCOD_ANYCH = '.';
    private final char TYPCOD_BOL = '^';
    private final char TYPCOD_CHSET = '[';
    private final char TYPCOD_EOL = '$';
    private final char TYPCOD_LIT = '\\';
    private final char TYPCOD_PRVREP = '+';
    private final char TYPCOD_NXTREP = '*';
    private String pattern;
    private StringBuffer encodedPattern;
    
    public RegExp(final String pattern) {
        this.pattern = pattern;
    }
    
    private String compileCharSet(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(80);
        sb.append('[');
        sb.append('\0');
        char c = '\0';
        for (int i = 0; i < length; ++i) {
            final char c2 = c;
            c = s.charAt(i);
            if (c == '\\' && i + 1 < length) {
                sb.append(s.charAt(++i));
            }
            else if (c == '-' && i > 0 && i + 1 < length) {
                c = s.charAt(++i);
                for (char c3 = (char)(c2 + '\u0001'); c3 <= c; ++c3) {
                    sb.append(c3);
                }
            }
            else {
                sb.append(c);
            }
        }
        sb.setCharAt(1, (char)(sb.length() - 2));
        return sb.toString();
    }
    
    private boolean matchAt(final int n, final String s, int n2) {
        if (n2 >= s.length()) {
            return false;
        }
        final int length = this.encodedPattern.length();
        int n3 = -1;
        for (int i = n; i < length; i += this.patTypSiz(i)) {
            final char char1 = this.encodedPattern.charAt(i);
            if (char1 == '+' || char1 == '*') {
                final int n4 = n2;
                final int n5 = (char1 == '+') ? n3 : (i + 1);
                int j = this.typeMatches(n5, s, n2);
                if (j < 0) {
                    continue;
                }
                while (j > 0) {
                    n2 += j;
                    j = this.typeMatches(n5, s, n2);
                }
                i += this.patTypSiz(i);
                if (i < length) {
                    for (int k = n2; k >= n4; --k) {
                        if (this.matchAt(i, s, k)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            else {
                final int typeMatches = this.typeMatches(i, s, n2);
                if (typeMatches < 0) {
                    return false;
                }
                n2 += typeMatches;
            }
            n3 = i;
        }
        return true;
    }
    
    private int patTypSiz(final int n) {
        switch (this.encodedPattern.charAt(n)) {
            case '$':
            case '+':
            case '.':
            case '^': {
                return 1;
            }
            case '\\': {
                return 2;
            }
            case '[': {
                return '\u0002' + this.encodedPattern.charAt(n + 1);
            }
            case '*': {
                return 1 + this.patTypSiz(n + 1);
            }
            default: {
                System.err.println("RegExp.patTypSiz: bad type");
                return 1;
            }
        }
    }
    
    private int typeMatches(final int n, final String s, final int n2) {
        final int length = s.length();
        final char char1 = this.encodedPattern.charAt(n);
        if (char1 == '^') {
            if (n2 == 0) {
                return 0;
            }
            return -1;
        }
        else if (char1 == '$') {
            if (n2 >= length) {
                return 0;
            }
            return -1;
        }
        else {
            if (n2 < 0 || n2 >= length) {
                return -1;
            }
            final char char2 = s.charAt(n2);
            switch (char1) {
                case 46: {
                    return 1;
                }
                case 92: {
                    if (char2 == this.encodedPattern.charAt(n + 1)) {
                        return 1;
                    }
                    break;
                }
                case 91: {
                    final char char3 = this.encodedPattern.charAt(n + 1);
                    for (int i = n + 2; i < n + 2 + char3; ++i) {
                        if (char2 == this.encodedPattern.charAt(i)) {
                            return 1;
                        }
                    }
                    break;
                }
                default: {
                    System.err.println("RegExp.typeMatches: bad type");
                    break;
                }
            }
            return -1;
        }
    }
    
    public boolean compilePattern() {
        if (this.encodedPattern != null) {
            return true;
        }
        int length = -1;
        final StringBuffer encodedPattern = new StringBuffer();
        for (int length2 = this.pattern.length(), i = 0; i < length2; ++i) {
            final int n = length;
            length = encodedPattern.length();
            char c = this.pattern.charAt(i);
            if (c == '.') {
                encodedPattern.append('.');
            }
            else if (c == '^' && i == 0) {
                encodedPattern.append('^');
            }
            else if (c == '$' && i == length2 - 1) {
                encodedPattern.append('$');
            }
            else if (c == '\\' && i < length2 - 1) {
                encodedPattern.append('\\');
                encodedPattern.append(this.pattern.charAt(++i));
            }
            else if (c == '+' && i > 0) {
                encodedPattern.append('+');
            }
            else if (c == '*' && i > 0) {
                encodedPattern.insert(n, '*');
            }
            else if (c == '[') {
                final StringBuffer sb = new StringBuffer();
                while (++i < length2) {
                    c = this.pattern.charAt(i);
                    if (c == ']') {
                        break;
                    }
                    sb.append(c);
                }
                if (c != ']') {
                    return false;
                }
                if (sb.length() > 0) {
                    encodedPattern.append(this.compileCharSet(sb.toString()));
                }
            }
            else {
                encodedPattern.append('\\');
                encodedPattern.append(c);
            }
        }
        this.encodedPattern = encodedPattern;
        return true;
    }
    
    public int findPattern(final String s) {
        if (this.encodedPattern == null && !this.compilePattern()) {
            return -1;
        }
        for (int length = s.length(), i = 0; i < length; ++i) {
            if (this.matchAt(0, s, i)) {
                return i;
            }
        }
        return -1;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.pattern);
        if (this.encodedPattern == null) {
            return sb.toString();
        }
        sb.append(":");
        for (int length = this.encodedPattern.length(), i = 0; i < length; ++i) {
            final char char1 = this.encodedPattern.charAt(i);
            if (char1 == '\\') {
                sb.append('\\');
                sb.append(this.encodedPattern.charAt(++i));
            }
            else if (char1 == '[') {
                sb.append(char1);
                int j = this.encodedPattern.charAt(++i);
                sb.append(String.valueOf(j));
                while (j > 0) {
                    if (++i < length) {
                        sb.append(this.encodedPattern.charAt(i));
                    }
                    --j;
                }
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
}
