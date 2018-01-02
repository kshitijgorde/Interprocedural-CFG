// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

import java.util.Vector;

public class RE
{
    public static final int MATCH_NORMAL = 0;
    public static final int MATCH_CASEINDEPENDENT = 1;
    public static final int MATCH_MULTILINE = 2;
    public static final int MATCH_SINGLELINE = 4;
    static final char OP_END = 'E';
    static final char OP_BOL = '^';
    static final char OP_EOL = '$';
    static final char OP_ANY = '.';
    static final char OP_ANYOF = '[';
    static final char OP_BRANCH = '|';
    static final char OP_ATOM = 'A';
    static final char OP_STAR = '*';
    static final char OP_PLUS = '+';
    static final char OP_MAYBE = '?';
    static final char OP_ESCAPE = '\\';
    static final char OP_OPEN = '(';
    static final char OP_CLOSE = ')';
    static final char OP_BACKREF = '#';
    static final char OP_GOTO = 'G';
    static final char OP_NOTHING = 'N';
    static final char OP_RELUCTANTSTAR = '8';
    static final char OP_RELUCTANTPLUS = '=';
    static final char OP_RELUCTANTMAYBE = '/';
    static final char OP_POSIXCLASS = 'P';
    static final char E_ALNUM = 'w';
    static final char E_NALNUM = 'W';
    static final char E_BOUND = 'b';
    static final char E_NBOUND = 'B';
    static final char E_SPACE = 's';
    static final char E_NSPACE = 'S';
    static final char E_DIGIT = 'd';
    static final char E_NDIGIT = 'D';
    static final char POSIX_CLASS_ALNUM = 'w';
    static final char POSIX_CLASS_ALPHA = 'a';
    static final char POSIX_CLASS_BLANK = 'b';
    static final char POSIX_CLASS_CNTRL = 'c';
    static final char POSIX_CLASS_DIGIT = 'd';
    static final char POSIX_CLASS_GRAPH = 'g';
    static final char POSIX_CLASS_LOWER = 'l';
    static final char POSIX_CLASS_PRINT = 'p';
    static final char POSIX_CLASS_PUNCT = '!';
    static final char POSIX_CLASS_SPACE = 's';
    static final char POSIX_CLASS_UPPER = 'u';
    static final char POSIX_CLASS_XDIGIT = 'x';
    static final char POSIX_CLASS_JSTART = 'j';
    static final char POSIX_CLASS_JPART = 'k';
    static final int maxNode = 65536;
    static final int maxParen = 16;
    static final int offsetOpcode = 0;
    static final int offsetOpdata = 1;
    static final int offsetNext = 2;
    static final int nodeSize = 3;
    static final String NEWLINE;
    REProgram program;
    CharacterIterator search;
    int idx;
    int matchFlags;
    int parenCount;
    int start0;
    int end0;
    int start1;
    int end1;
    int start2;
    int end2;
    int[] startn;
    int[] endn;
    int[] startBackref;
    int[] endBackref;
    public static final int REPLACE_ALL = 0;
    public static final int REPLACE_FIRSTONLY = 1;
    
    static {
        NEWLINE = System.getProperty("line.separator");
    }
    
    public RE() {
        this((REProgram)null, 0);
    }
    
    public RE(final String s) throws RESyntaxException {
        this(s, 0);
    }
    
    public RE(final String s, final int matchFlags) throws RESyntaxException {
        this(new RECompiler().compile(s));
        this.setMatchFlags(matchFlags);
    }
    
    public RE(final REProgram reProgram) {
        this(reProgram, 0);
    }
    
    public RE(final REProgram program, final int matchFlags) {
        this.setProgram(program);
        this.setMatchFlags(matchFlags);
    }
    
    private final void allocParens() {
        this.startn = new int[16];
        this.endn = new int[16];
        for (int i = 0; i < 16; ++i) {
            this.startn[i] = -1;
            this.endn[i] = -1;
        }
    }
    
    public int getMatchFlags() {
        return this.matchFlags;
    }
    
    public String getParen(final int n) {
        final int parenStart;
        if (n < this.parenCount && (parenStart = this.getParenStart(n)) >= 0) {
            return this.search.substring(parenStart, this.getParenEnd(n));
        }
        return null;
    }
    
    public int getParenCount() {
        return this.parenCount;
    }
    
    public final int getParenEnd(final int n) {
        if (n >= this.parenCount) {
            return -1;
        }
        switch (n) {
            case 0: {
                return this.end0;
            }
            case 1: {
                return this.end1;
            }
            case 2: {
                return this.end2;
            }
            default: {
                if (this.endn == null) {
                    this.allocParens();
                }
                return this.endn[n];
            }
        }
    }
    
    public final int getParenLength(final int n) {
        if (n < this.parenCount) {
            return this.getParenEnd(n) - this.getParenStart(n);
        }
        return -1;
    }
    
    public final int getParenStart(final int n) {
        if (n >= this.parenCount) {
            return -1;
        }
        switch (n) {
            case 0: {
                return this.start0;
            }
            case 1: {
                return this.start1;
            }
            case 2: {
                return this.start2;
            }
            default: {
                if (this.startn == null) {
                    this.allocParens();
                }
                return this.startn[n];
            }
        }
    }
    
    public REProgram getProgram() {
        return this.program;
    }
    
    public String[] grep(final Object[] array) {
        final Vector vector = new Vector<String>();
        for (int i = 0; i < array.length; ++i) {
            final String string = array[i].toString();
            if (this.match(string)) {
                vector.addElement(string);
            }
        }
        final String[] array2 = new String[vector.size()];
        vector.copyInto(array2);
        return array2;
    }
    
    protected void internalError(final String s) throws Error {
        throw new Error("RE internal error: " + s);
    }
    
    private boolean isNewline(int n) {
        if (n < RE.NEWLINE.length() - 1) {
            return false;
        }
        if (this.search.charAt(n) == '\n') {
            return true;
        }
        for (int i = RE.NEWLINE.length() - 1; i >= 0; --i, --n) {
            if (RE.NEWLINE.charAt(i) != this.search.charAt(n)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean match(final String s) {
        return this.match(s, 0);
    }
    
    public boolean match(final String s, final int n) {
        return this.match(new StringCharacterIterator(s), n);
    }
    
    public boolean match(final CharacterIterator search, int n) {
        if (this.program == null) {
            this.internalError("No RE program to run!");
        }
        this.search = search;
        if (this.program.prefix == null) {
            while (!search.isEnd(n - 1)) {
                if (this.matchAt(n)) {
                    return true;
                }
                ++n;
            }
            return false;
        }
        final boolean b = (this.matchFlags & 0x1) != 0x0;
        for (char[] prefix = this.program.prefix; !search.isEnd(n + prefix.length - 1); ++n) {
            boolean b2;
            if (b) {
                b2 = (Character.toLowerCase(search.charAt(n)) == Character.toLowerCase(prefix[0]));
            }
            else {
                b2 = (search.charAt(n) == prefix[0]);
            }
            if (b2) {
                final int n2 = n++;
                int i = 1;
                while (i < prefix.length) {
                    boolean b3;
                    if (b) {
                        b3 = (Character.toLowerCase(search.charAt(n++)) == Character.toLowerCase(prefix[i++]));
                    }
                    else {
                        b3 = (search.charAt(n++) == prefix[i++]);
                    }
                    if (!b3) {
                        break;
                    }
                }
                if (i == prefix.length && this.matchAt(n2)) {
                    return true;
                }
                n = n2;
            }
        }
        return false;
    }
    
    protected boolean matchAt(final int n) {
        this.start0 = -1;
        this.end0 = -1;
        this.start1 = -1;
        this.end1 = -1;
        this.start2 = -1;
        this.end2 = -1;
        this.startn = null;
        this.endn = null;
        this.parenCount = 1;
        this.setParenStart(0, n);
        if ((this.program.flags & 0x1) != 0x0) {
            this.startBackref = new int[16];
            this.endBackref = new int[16];
        }
        final int matchNodes;
        if ((matchNodes = this.matchNodes(0, 65536, n)) != -1) {
            this.setParenEnd(0, matchNodes);
            return true;
        }
        this.parenCount = 0;
        return false;
    }
    
    protected int matchNodes(final int n, final int n2, final int n3) {
        int n4 = n3;
        final char[] instruction = this.program.instruction;
        int i = n;
        while (i < n2) {
            final char c = instruction[i];
            final short n5 = (short)(i + (short)instruction[i + 2]);
            final char c2 = instruction[i + 1];
            Label_2199: {
                switch (c) {
                    case 47: {
                        int n6 = 0;
                        int matchNodes;
                        while ((matchNodes = this.matchNodes(n5, 65536, n4)) == -1) {
                            if (n6++ != 0 || (n4 = this.matchNodes(i + 3, n5, n4)) == -1) {
                                return -1;
                            }
                        }
                        return matchNodes;
                    }
                    case 61: {
                        while ((n4 = this.matchNodes(i + 3, n5, n4)) != -1) {
                            final int matchNodes2;
                            if ((matchNodes2 = this.matchNodes(n5, 65536, n4)) != -1) {
                                return matchNodes2;
                            }
                        }
                        return -1;
                    }
                    case 56: {
                        int matchNodes3;
                        while ((matchNodes3 = this.matchNodes(n5, 65536, n4)) == -1) {
                            if ((n4 = this.matchNodes(i + 3, n5, n4)) == -1) {
                                return -1;
                            }
                        }
                        return matchNodes3;
                    }
                    case 40: {
                        if ((this.program.flags & 0x1) != 0x0) {
                            this.startBackref[c2] = n4;
                        }
                        final int matchNodes4;
                        if ((matchNodes4 = this.matchNodes(n5, 65536, n4)) != -1) {
                            if (c2 + '\u0001' > this.parenCount) {
                                this.parenCount = c2 + '\u0001';
                            }
                            if (this.getParenStart(c2) == -1) {
                                this.setParenStart(c2, n4);
                            }
                        }
                        return matchNodes4;
                    }
                    case 41: {
                        if ((this.program.flags & 0x1) != 0x0) {
                            this.endBackref[c2] = n4;
                        }
                        final int matchNodes5;
                        if ((matchNodes5 = this.matchNodes(n5, 65536, n4)) != -1) {
                            if (c2 + '\u0001' > this.parenCount) {
                                this.parenCount = c2 + '\u0001';
                            }
                            if (this.getParenEnd(c2) == -1) {
                                this.setParenEnd(c2, n4);
                            }
                        }
                        return matchNodes5;
                    }
                    case 35: {
                        final int n7 = this.startBackref[c2];
                        final int n8 = this.endBackref[c2];
                        if (n7 == -1 || n8 == -1) {
                            return -1;
                        }
                        if (n7 == n8) {
                            break Label_2199;
                        }
                        final char c3 = (char)(n8 - n7);
                        if (this.search.isEnd(n4 + c3 - 1)) {
                            return -1;
                        }
                        if ((this.matchFlags & 0x1) != 0x0) {
                            for (char c4 = '\0'; c4 < c3; ++c4) {
                                if (Character.toLowerCase(this.search.charAt(n4++)) != Character.toLowerCase(this.search.charAt(n7 + c4))) {
                                    return -1;
                                }
                            }
                            break Label_2199;
                        }
                        for (char c5 = '\0'; c5 < c3; ++c5) {
                            if (this.search.charAt(n4++) != this.search.charAt(n7 + c5)) {
                                return -1;
                            }
                        }
                        break Label_2199;
                    }
                    case 94: {
                        if (n4 == 0) {
                            break Label_2199;
                        }
                        if ((this.matchFlags & 0x2) != 0x2) {
                            return -1;
                        }
                        if (n4 <= 0 || !this.isNewline(n4 - 1)) {
                            return -1;
                        }
                        break Label_2199;
                    }
                    case 36: {
                        if (this.search.isEnd(0) || this.search.isEnd(n4)) {
                            break Label_2199;
                        }
                        if ((this.matchFlags & 0x2) != 0x2) {
                            return -1;
                        }
                        if (!this.isNewline(n4)) {
                            return -1;
                        }
                        break Label_2199;
                    }
                    case 92: {
                        switch (c2) {
                            case 66:
                            case 98: {
                                if (Character.isLetterOrDigit((n4 == this.getParenStart(0)) ? '\n' : this.search.charAt(n4 - 1)) == Character.isLetterOrDigit(this.search.isEnd(n4) ? '\n' : this.search.charAt(n4)) == (c2 == 'b')) {
                                    return -1;
                                }
                                break Label_2199;
                            }
                            case 68:
                            case 83:
                            case 87:
                            case 100:
                            case 115:
                            case 119: {
                                if (this.search.isEnd(n4)) {
                                    return -1;
                                }
                                switch (c2) {
                                    case 87:
                                    case 119: {
                                        if (Character.isLetterOrDigit(this.search.charAt(n4)) != (c2 == 'w')) {
                                            return -1;
                                        }
                                        break;
                                    }
                                    case 68:
                                    case 100: {
                                        if (Character.isDigit(this.search.charAt(n4)) != (c2 == 'd')) {
                                            return -1;
                                        }
                                        break;
                                    }
                                    case 83:
                                    case 115: {
                                        if (Character.isWhitespace(this.search.charAt(n4)) != (c2 == 's')) {
                                            return -1;
                                        }
                                        break;
                                    }
                                }
                                ++n4;
                                break Label_2199;
                            }
                            default: {
                                this.internalError("Unrecognized escape '" + (int)c2 + "'");
                                break Label_2199;
                            }
                        }
                        break;
                    }
                    case 46: {
                        if ((this.matchFlags & 0x4) == 0x4) {
                            if (this.search.isEnd(n4)) {
                                return -1;
                            }
                            ++n4;
                            break Label_2199;
                        }
                        else {
                            if (this.search.isEnd(n4) || this.search.charAt(n4++) == '\n') {
                                return -1;
                            }
                            break Label_2199;
                        }
                        break;
                    }
                    case 65: {
                        if (this.search.isEnd(n4)) {
                            return -1;
                        }
                        final char c6 = c2;
                        final short n9 = (short)(i + 3);
                        if (this.search.isEnd(c6 + n4 - '\u0001')) {
                            return -1;
                        }
                        if ((this.matchFlags & 0x1) != 0x0) {
                            for (short n10 = 0; n10 < c6; ++n10) {
                                if (Character.toLowerCase(this.search.charAt(n4++)) != Character.toLowerCase(instruction[n9 + n10])) {
                                    return -1;
                                }
                            }
                            break Label_2199;
                        }
                        for (short n11 = 0; n11 < c6; ++n11) {
                            if (this.search.charAt(n4++) != instruction[n9 + n11]) {
                                return -1;
                            }
                        }
                        break Label_2199;
                    }
                    case 80: {
                        if (this.search.isEnd(n4)) {
                            return -1;
                        }
                        Label_1924: {
                            switch (c2) {
                                case 119: {
                                    if (!Character.isLetterOrDigit(this.search.charAt(n4))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 97: {
                                    if (!Character.isLetter(this.search.charAt(n4))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 100: {
                                    if (!Character.isDigit(this.search.charAt(n4))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 98: {
                                    if (!Character.isSpaceChar(this.search.charAt(n4))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 115: {
                                    if (!Character.isWhitespace(this.search.charAt(n4))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 99: {
                                    if (Character.getType(this.search.charAt(n4)) != 15) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 103: {
                                    switch (Character.getType(this.search.charAt(n4))) {
                                        default: {
                                            return -1;
                                        }
                                        case 25:
                                        case 26:
                                        case 27:
                                        case 28: {
                                            break Label_1924;
                                        }
                                    }
                                    break;
                                }
                                case 108: {
                                    if (Character.getType(this.search.charAt(n4)) != 2) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 117: {
                                    if (Character.getType(this.search.charAt(n4)) != 1) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 112: {
                                    if (Character.getType(this.search.charAt(n4)) == 15) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 33: {
                                    switch (Character.getType(this.search.charAt(n4))) {
                                        default: {
                                            return -1;
                                        }
                                        case 20:
                                        case 21:
                                        case 22:
                                        case 23:
                                        case 24: {
                                            break Label_1924;
                                        }
                                    }
                                    break;
                                }
                                case 120: {
                                    if ((this.search.charAt(n4) < '0' || this.search.charAt(n4) > '9') && (this.search.charAt(n4) < 'a' || this.search.charAt(n4) > 'f') && (this.search.charAt(n4) < 'A' || this.search.charAt(n4) > 'F')) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 106: {
                                    if (!Character.isJavaIdentifierStart(this.search.charAt(n4))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 107: {
                                    if (!Character.isJavaIdentifierPart(this.search.charAt(n4))) {
                                        return -1;
                                    }
                                    break;
                                }
                                default: {
                                    this.internalError("Bad posix class");
                                    break;
                                }
                            }
                        }
                        ++n4;
                        break Label_2199;
                    }
                    case 91: {
                        if (this.search.isEnd(n4)) {
                            return -1;
                        }
                        char c7 = this.search.charAt(n4);
                        final boolean b = (this.matchFlags & 0x1) != 0x0;
                        if (b) {
                            c7 = Character.toLowerCase(c7);
                        }
                        final short n12 = (short)(i + 3);
                        final char c8 = (char)(n12 + c2 * '\u0002');
                        boolean b2 = false;
                        char c9 = (char)n12;
                        while (c9 < c8) {
                            char lowerCase = instruction[c9++];
                            char lowerCase2 = instruction[c9++];
                            if (b) {
                                lowerCase = Character.toLowerCase(lowerCase);
                                lowerCase2 = Character.toLowerCase(lowerCase2);
                            }
                            if (c7 >= lowerCase && c7 <= lowerCase2) {
                                b2 = true;
                                break;
                            }
                        }
                        if (!b2) {
                            return -1;
                        }
                        ++n4;
                        break Label_2199;
                    }
                    case 124: {
                        if (instruction[n5] != '|') {
                            i += 3;
                            continue;
                        }
                        int matchNodes6;
                        while ((matchNodes6 = this.matchNodes(i + 3, 65536, n4)) == -1) {
                            final short n13 = (short)instruction[i + 2];
                            i += n13;
                            if (n13 == 0 || instruction[i] != '|') {
                                return -1;
                            }
                        }
                        return matchNodes6;
                    }
                    case 69: {
                        this.setParenEnd(0, n4);
                        return n4;
                    }
                    default: {
                        this.internalError("Invalid opcode '" + (int)c + "'");
                    }
                    case 71:
                    case 78: {
                        i = n5;
                        continue;
                    }
                }
            }
        }
        this.internalError("Corrupt program");
        return -1;
    }
    
    public void setMatchFlags(final int matchFlags) {
        this.matchFlags = matchFlags;
    }
    
    protected final void setParenEnd(final int n, final int end2) {
        if (n < this.parenCount) {
            switch (n) {
                case 0: {
                    this.end0 = end2;
                    break;
                }
                case 1: {
                    this.end1 = end2;
                    break;
                }
                case 2: {
                    this.end2 = end2;
                    break;
                }
                default: {
                    if (this.endn == null) {
                        this.allocParens();
                    }
                    this.endn[n] = end2;
                    break;
                }
            }
        }
    }
    
    protected final void setParenStart(final int n, final int start2) {
        if (n < this.parenCount) {
            switch (n) {
                case 0: {
                    this.start0 = start2;
                    break;
                }
                case 1: {
                    this.start1 = start2;
                    break;
                }
                case 2: {
                    this.start2 = start2;
                    break;
                }
                default: {
                    if (this.startn == null) {
                        this.allocParens();
                    }
                    this.startn[n] = start2;
                    break;
                }
            }
        }
    }
    
    public void setProgram(final REProgram program) {
        this.program = program;
    }
    
    public static String simplePatternToFullRegularExpression(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 42: {
                    sb.append(".*");
                    continue;
                }
                case 36:
                case 40:
                case 41:
                case 43:
                case 46:
                case 63:
                case 91:
                case 92:
                case 93:
                case 94:
                case 123:
                case 124:
                case 125: {
                    sb.append('\\');
                    break;
                }
            }
            sb.append(char1);
        }
        return sb.toString();
    }
    
    public String[] split(final String s) {
        final Vector vector = new Vector<String>();
        int n;
        int parenEnd;
        for (n = 0; n < s.length() && this.match(s, n); n = parenEnd) {
            final int parenStart = this.getParenStart(0);
            parenEnd = this.getParenEnd(0);
            if (parenEnd == n) {
                vector.addElement(s.substring(n, parenStart + 1));
                ++parenEnd;
            }
            else {
                vector.addElement(s.substring(n, parenStart));
            }
        }
        final String substring = s.substring(n);
        if (substring.length() != 0) {
            vector.addElement(substring);
        }
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public String subst(final String s, final String s2) {
        return this.subst(s, s2, 0);
    }
    
    public String subst(final String s, final String s2, final int n) {
        final StringBuffer sb = new StringBuffer();
        int n2 = 0;
        final int length = s.length();
        while (n2 < length && this.match(s, n2)) {
            sb.append(s.substring(n2, this.getParenStart(0)));
            sb.append(s2);
            int parenEnd = this.getParenEnd(0);
            if (parenEnd == n2) {
                ++parenEnd;
            }
            n2 = parenEnd;
            if ((n & 0x1) != 0x0) {
                break;
            }
        }
        if (n2 < length) {
            sb.append(s.substring(n2));
        }
        return sb.toString();
    }
}
