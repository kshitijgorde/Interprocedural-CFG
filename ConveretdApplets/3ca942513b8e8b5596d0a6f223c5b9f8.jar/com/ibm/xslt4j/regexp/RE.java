// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

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
    
    public RE(final String pattern) throws RESyntaxException {
        this(pattern, 0);
    }
    
    public RE(final String pattern, final int matchFlags) throws RESyntaxException {
        this(new RECompiler().compile(pattern));
        this.setMatchFlags(matchFlags);
    }
    
    public RE(final REProgram program, final int matchFlags) {
        this.setProgram(program);
        this.setMatchFlags(matchFlags);
    }
    
    public RE(final REProgram program) {
        this(program, 0);
    }
    
    public RE() {
        this((REProgram)null, 0);
    }
    
    public static String simplePatternToFullRegularExpression(final String pattern) {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < pattern.length(); ++i) {
            final char c = pattern.charAt(i);
            switch (c) {
                case '*': {
                    buf.append(".*");
                    continue;
                }
                case '$':
                case '(':
                case ')':
                case '+':
                case '.':
                case '?':
                case '[':
                case '\\':
                case ']':
                case '^':
                case '{':
                case '|':
                case '}': {
                    buf.append('\\');
                    break;
                }
            }
            buf.append(c);
        }
        return buf.toString();
    }
    
    public void setMatchFlags(final int matchFlags) {
        this.matchFlags = matchFlags;
    }
    
    public int getMatchFlags() {
        return this.matchFlags;
    }
    
    public void setProgram(final REProgram program) {
        this.program = program;
    }
    
    public REProgram getProgram() {
        return this.program;
    }
    
    public int getParenCount() {
        return this.parenCount;
    }
    
    public String getParen(final int which) {
        final int start;
        if (which < this.parenCount && (start = this.getParenStart(which)) >= 0) {
            return this.search.substring(start, this.getParenEnd(which));
        }
        return null;
    }
    
    public final int getParenStart(final int which) {
        if (which >= this.parenCount) {
            return -1;
        }
        switch (which) {
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
                return this.startn[which];
            }
        }
    }
    
    public final int getParenEnd(final int which) {
        if (which >= this.parenCount) {
            return -1;
        }
        switch (which) {
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
                return this.endn[which];
            }
        }
    }
    
    public final int getParenLength(final int which) {
        if (which < this.parenCount) {
            return this.getParenEnd(which) - this.getParenStart(which);
        }
        return -1;
    }
    
    protected final void setParenStart(final int which, final int i) {
        if (which < this.parenCount) {
            switch (which) {
                case 0: {
                    this.start0 = i;
                    break;
                }
                case 1: {
                    this.start1 = i;
                    break;
                }
                case 2: {
                    this.start2 = i;
                    break;
                }
                default: {
                    if (this.startn == null) {
                        this.allocParens();
                    }
                    this.startn[which] = i;
                    break;
                }
            }
        }
    }
    
    protected final void setParenEnd(final int which, final int i) {
        if (which < this.parenCount) {
            switch (which) {
                case 0: {
                    this.end0 = i;
                    break;
                }
                case 1: {
                    this.end1 = i;
                    break;
                }
                case 2: {
                    this.end2 = i;
                    break;
                }
                default: {
                    if (this.endn == null) {
                        this.allocParens();
                    }
                    this.endn[which] = i;
                    break;
                }
            }
        }
    }
    
    protected void internalError(final String s) throws Error {
        throw new Error("RE internal error: " + s);
    }
    
    private final void allocParens() {
        this.startn = new int[16];
        this.endn = new int[16];
        for (int i = 0; i < 16; ++i) {
            this.startn[i] = -1;
            this.endn[i] = -1;
        }
    }
    
    protected int matchNodes(final int firstNode, final int lastNode, final int idxStart) {
        int idx = idxStart;
        final char[] instruction = this.program.instruction;
        int node = firstNode;
        while (node < lastNode) {
            final int opcode = instruction[node + 0];
            final int next = node + (short)instruction[node + 2];
            final int opdata = instruction[node + 1];
            Label_2296: {
                switch (opcode) {
                    case 47: {
                        int once = 0;
                        int idxNew;
                        while ((idxNew = this.matchNodes(next, 65536, idx)) == -1) {
                            if (once++ != 0 || (idx = this.matchNodes(node + 3, next, idx)) == -1) {
                                return -1;
                            }
                        }
                        return idxNew;
                    }
                    case 61: {
                        while ((idx = this.matchNodes(node + 3, next, idx)) != -1) {
                            final int idxNew;
                            if ((idxNew = this.matchNodes(next, 65536, idx)) != -1) {
                                return idxNew;
                            }
                        }
                        return -1;
                    }
                    case 56: {
                        int idxNew;
                        while ((idxNew = this.matchNodes(next, 65536, idx)) == -1) {
                            if ((idx = this.matchNodes(node + 3, next, idx)) == -1) {
                                return -1;
                            }
                        }
                        return idxNew;
                    }
                    case 40: {
                        if ((this.program.flags & 0x1) != 0x0) {
                            this.startBackref[opdata] = idx;
                        }
                        final int idxNew;
                        if ((idxNew = this.matchNodes(next, 65536, idx)) != -1) {
                            if (opdata + 1 > this.parenCount) {
                                this.parenCount = opdata + 1;
                            }
                            if (this.getParenStart(opdata) == -1) {
                                this.setParenStart(opdata, idx);
                            }
                        }
                        return idxNew;
                    }
                    case 41: {
                        if ((this.program.flags & 0x1) != 0x0) {
                            this.endBackref[opdata] = idx;
                        }
                        final int idxNew;
                        if ((idxNew = this.matchNodes(next, 65536, idx)) != -1) {
                            if (opdata + 1 > this.parenCount) {
                                this.parenCount = opdata + 1;
                            }
                            if (this.getParenEnd(opdata) == -1) {
                                this.setParenEnd(opdata, idx);
                            }
                        }
                        return idxNew;
                    }
                    case 35: {
                        final int s = this.startBackref[opdata];
                        final int e = this.endBackref[opdata];
                        if (s == -1 || e == -1) {
                            return -1;
                        }
                        if (s == e) {
                            break;
                        }
                        final int l = e - s;
                        if (this.search.isEnd(idx + l - 1)) {
                            return -1;
                        }
                        if ((this.matchFlags & 0x1) != 0x0) {
                            for (int i = 0; i < l; ++i) {
                                if (Character.toLowerCase(this.search.charAt(idx++)) != Character.toLowerCase(this.search.charAt(s + i))) {
                                    return -1;
                                }
                            }
                            break;
                        }
                        for (int i = 0; i < l; ++i) {
                            if (this.search.charAt(idx++) != this.search.charAt(s + i)) {
                                return -1;
                            }
                        }
                        break;
                    }
                    case 94: {
                        if (idx == 0) {
                            break;
                        }
                        if ((this.matchFlags & 0x2) != 0x2) {
                            return -1;
                        }
                        if (idx <= 0 || !this.isNewline(idx - 1)) {
                            return -1;
                        }
                        break;
                    }
                    case 36: {
                        if (this.search.isEnd(0) || this.search.isEnd(idx)) {
                            break;
                        }
                        if ((this.matchFlags & 0x2) != 0x2) {
                            return -1;
                        }
                        if (!this.isNewline(idx)) {
                            return -1;
                        }
                        break;
                    }
                    case 92: {
                        switch (opdata) {
                            case 66:
                            case 98: {
                                final char cLast = (idx == this.getParenStart(0)) ? '\n' : this.search.charAt(idx - 1);
                                final char cNext = this.search.isEnd(idx) ? '\n' : this.search.charAt(idx);
                                if (Character.isLetterOrDigit(cLast) == Character.isLetterOrDigit(cNext) == (opdata == 98)) {
                                    return -1;
                                }
                                break Label_2296;
                            }
                            case 68:
                            case 83:
                            case 87:
                            case 100:
                            case 115:
                            case 119: {
                                if (this.search.isEnd(idx)) {
                                    return -1;
                                }
                                switch (opdata) {
                                    case 87:
                                    case 119: {
                                        if (Character.isLetterOrDigit(this.search.charAt(idx)) != (opdata == 119)) {
                                            return -1;
                                        }
                                        break;
                                    }
                                    case 68:
                                    case 100: {
                                        if (Character.isDigit(this.search.charAt(idx)) != (opdata == 100)) {
                                            return -1;
                                        }
                                        break;
                                    }
                                    case 83:
                                    case 115: {
                                        if (Character.isWhitespace(this.search.charAt(idx)) != (opdata == 115)) {
                                            return -1;
                                        }
                                        break;
                                    }
                                }
                                ++idx;
                                break Label_2296;
                            }
                            default: {
                                this.internalError("Unrecognized escape '" + opdata + "'");
                                break Label_2296;
                            }
                        }
                        break;
                    }
                    case 46: {
                        if ((this.matchFlags & 0x4) == 0x4) {
                            if (this.search.isEnd(idx)) {
                                return -1;
                            }
                            ++idx;
                            break;
                        }
                        else {
                            if (this.search.isEnd(idx) || this.search.charAt(idx++) == '\n') {
                                return -1;
                            }
                            break;
                        }
                        break;
                    }
                    case 65: {
                        if (this.search.isEnd(idx)) {
                            return -1;
                        }
                        final int lenAtom = opdata;
                        final int startAtom = node + 3;
                        if (this.search.isEnd(lenAtom + idx - 1)) {
                            return -1;
                        }
                        if ((this.matchFlags & 0x1) != 0x0) {
                            for (int j = 0; j < lenAtom; ++j) {
                                if (Character.toLowerCase(this.search.charAt(idx++)) != Character.toLowerCase(instruction[startAtom + j])) {
                                    return -1;
                                }
                            }
                            break;
                        }
                        for (int j = 0; j < lenAtom; ++j) {
                            if (this.search.charAt(idx++) != instruction[startAtom + j]) {
                                return -1;
                            }
                        }
                        break;
                    }
                    case 80: {
                        if (this.search.isEnd(idx)) {
                            return -1;
                        }
                        Label_2012: {
                            switch (opdata) {
                                case 119: {
                                    if (!Character.isLetterOrDigit(this.search.charAt(idx))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 97: {
                                    if (!Character.isLetter(this.search.charAt(idx))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 100: {
                                    if (!Character.isDigit(this.search.charAt(idx))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 98: {
                                    if (!Character.isSpaceChar(this.search.charAt(idx))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 115: {
                                    if (!Character.isWhitespace(this.search.charAt(idx))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 99: {
                                    if (Character.getType(this.search.charAt(idx)) != 15) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 103: {
                                    switch (Character.getType(this.search.charAt(idx))) {
                                        case 25:
                                        case 26:
                                        case 27:
                                        case 28: {
                                            break Label_2012;
                                        }
                                        default: {
                                            return -1;
                                        }
                                    }
                                    break;
                                }
                                case 108: {
                                    if (Character.getType(this.search.charAt(idx)) != 2) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 117: {
                                    if (Character.getType(this.search.charAt(idx)) != 1) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 112: {
                                    if (Character.getType(this.search.charAt(idx)) == 15) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 33: {
                                    final int type = Character.getType(this.search.charAt(idx));
                                    switch (type) {
                                        case 20:
                                        case 21:
                                        case 22:
                                        case 23:
                                        case 24: {
                                            break Label_2012;
                                        }
                                        default: {
                                            return -1;
                                        }
                                    }
                                    break;
                                }
                                case 120: {
                                    final boolean isXDigit = (this.search.charAt(idx) >= '0' && this.search.charAt(idx) <= '9') || (this.search.charAt(idx) >= 'a' && this.search.charAt(idx) <= 'f') || (this.search.charAt(idx) >= 'A' && this.search.charAt(idx) <= 'F');
                                    if (!isXDigit) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 106: {
                                    if (!Character.isJavaIdentifierStart(this.search.charAt(idx))) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 107: {
                                    if (!Character.isJavaIdentifierPart(this.search.charAt(idx))) {
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
                        ++idx;
                        break;
                    }
                    case 91: {
                        if (this.search.isEnd(idx)) {
                            return -1;
                        }
                        char c = this.search.charAt(idx);
                        final boolean caseFold = (this.matchFlags & 0x1) != 0x0;
                        if (caseFold) {
                            c = Character.toLowerCase(c);
                        }
                        final int idxRange = node + 3;
                        final int idxEnd = idxRange + opdata * 2;
                        boolean match = false;
                        int k = idxRange;
                        while (k < idxEnd) {
                            char s2 = instruction[k++];
                            char e2 = instruction[k++];
                            if (caseFold) {
                                s2 = Character.toLowerCase(s2);
                                e2 = Character.toLowerCase(e2);
                            }
                            if (c >= s2 && c <= e2) {
                                match = true;
                                break;
                            }
                        }
                        if (!match) {
                            return -1;
                        }
                        ++idx;
                        break;
                    }
                    case 124: {
                        if (instruction[next + 0] != '|') {
                            node += 3;
                            continue;
                        }
                        int idxNew;
                        while ((idxNew = this.matchNodes(node + 3, 65536, idx)) == -1) {
                            final short nextBranch = (short)instruction[node + 2];
                            node += nextBranch;
                            if (nextBranch == 0 || instruction[node + 0] != '|') {
                                return -1;
                            }
                        }
                        return idxNew;
                    }
                    case 71:
                    case 78: {
                        break;
                    }
                    case 69: {
                        this.setParenEnd(0, idx);
                        return idx;
                    }
                    default: {
                        this.internalError("Invalid opcode '" + opcode + "'");
                        break;
                    }
                }
            }
            node = next;
        }
        this.internalError("Corrupt program");
        return -1;
    }
    
    protected boolean matchAt(final int i) {
        this.start0 = -1;
        this.end0 = -1;
        this.start1 = -1;
        this.end1 = -1;
        this.start2 = -1;
        this.end2 = -1;
        this.startn = null;
        this.endn = null;
        this.parenCount = 1;
        this.setParenStart(0, i);
        if ((this.program.flags & 0x1) != 0x0) {
            this.startBackref = new int[16];
            this.endBackref = new int[16];
        }
        final int idx;
        if ((idx = this.matchNodes(0, 65536, i)) != -1) {
            this.setParenEnd(0, idx);
            return true;
        }
        this.parenCount = 0;
        return false;
    }
    
    public boolean match(final String search, final int i) {
        return this.match(new StringCharacterIterator(search), i);
    }
    
    public boolean match(final CharacterIterator search, int i) {
        if (this.program == null) {
            this.internalError("No RE program to run!");
        }
        this.search = search;
        if (this.program.prefix == null) {
            while (!search.isEnd(i - 1)) {
                if (this.matchAt(i)) {
                    return true;
                }
                ++i;
            }
            return false;
        }
        final boolean caseIndependent = (this.matchFlags & 0x1) != 0x0;
        for (char[] prefix = this.program.prefix; !search.isEnd(i + prefix.length - 1); ++i) {
            boolean match = false;
            if (caseIndependent) {
                match = (Character.toLowerCase(search.charAt(i)) == Character.toLowerCase(prefix[0]));
            }
            else {
                match = (search.charAt(i) == prefix[0]);
            }
            if (match) {
                final int firstChar = i++;
                int k = 1;
                while (k < prefix.length) {
                    if (caseIndependent) {
                        match = (Character.toLowerCase(search.charAt(i++)) == Character.toLowerCase(prefix[k++]));
                    }
                    else {
                        match = (search.charAt(i++) == prefix[k++]);
                    }
                    if (!match) {
                        break;
                    }
                }
                if (k == prefix.length && this.matchAt(firstChar)) {
                    return true;
                }
                i = firstChar;
            }
        }
        return false;
    }
    
    public boolean match(final String search) {
        return this.match(search, 0);
    }
    
    public String[] split(final String s) {
        final Vector v = new Vector();
        int pos = 0;
        int newpos;
        for (int len = s.length(); pos < len && this.match(s, pos); pos = newpos) {
            final int start = this.getParenStart(0);
            newpos = this.getParenEnd(0);
            if (newpos == pos) {
                v.addElement(s.substring(pos, start + 1));
                ++newpos;
            }
            else {
                v.addElement(s.substring(pos, start));
            }
        }
        final String remainder = s.substring(pos);
        if (remainder.length() != 0) {
            v.addElement(remainder);
        }
        final String[] ret = new String[v.size()];
        v.copyInto(ret);
        return ret;
    }
    
    public String subst(final String substituteIn, final String substitution) {
        return this.subst(substituteIn, substitution, 0);
    }
    
    public String subst(final String substituteIn, final String substitution, final int flags) {
        final StringBuffer ret = new StringBuffer();
        int pos = 0;
        final int len = substituteIn.length();
        while (pos < len && this.match(substituteIn, pos)) {
            ret.append(substituteIn.substring(pos, this.getParenStart(0)));
            ret.append(substitution);
            int newpos = this.getParenEnd(0);
            if (newpos == pos) {
                ++newpos;
            }
            pos = newpos;
            if ((flags & 0x1) != 0x0) {
                break;
            }
        }
        if (pos < len) {
            ret.append(substituteIn.substring(pos));
        }
        return ret.toString();
    }
    
    public String[] grep(final Object[] search) {
        final Vector v = new Vector();
        for (int i = 0; i < search.length; ++i) {
            final String s = search[i].toString();
            if (this.match(s)) {
                v.addElement(s);
            }
        }
        final String[] ret = new String[v.size()];
        v.copyInto(ret);
        return ret;
    }
    
    private boolean isNewline(int i) {
        if (i < RE.NEWLINE.length() - 1) {
            return false;
        }
        if (this.search.charAt(i) == '\n') {
            return true;
        }
        for (int j = RE.NEWLINE.length() - 1; j >= 0; --j, --i) {
            if (RE.NEWLINE.charAt(j) != this.search.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
