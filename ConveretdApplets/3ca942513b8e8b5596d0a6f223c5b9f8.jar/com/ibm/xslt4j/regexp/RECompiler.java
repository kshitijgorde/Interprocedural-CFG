// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

import java.util.Hashtable;

public class RECompiler
{
    char[] instruction;
    int lenInstruction;
    String pattern;
    int len;
    int idx;
    int parens;
    static final int NODE_NORMAL = 0;
    static final int NODE_NULLABLE = 1;
    static final int NODE_TOPLEVEL = 2;
    static final char ESC_MASK = '\ufff0';
    static final char ESC_BACKREF = '\uffff';
    static final char ESC_COMPLEX = '\ufffe';
    static final char ESC_CLASS = '\ufffd';
    static final int maxBrackets = 10;
    static int brackets;
    static int[] bracketStart;
    static int[] bracketEnd;
    static int[] bracketMin;
    static int[] bracketOpt;
    static final int bracketUnbounded = -1;
    static final int bracketFinished = -2;
    static Hashtable hashPOSIX;
    
    static {
        RECompiler.brackets = 0;
        RECompiler.bracketStart = null;
        RECompiler.bracketEnd = null;
        RECompiler.bracketMin = null;
        RECompiler.bracketOpt = null;
        (RECompiler.hashPOSIX = new Hashtable()).put("alnum", new Character('w'));
        RECompiler.hashPOSIX.put("alpha", new Character('a'));
        RECompiler.hashPOSIX.put("blank", new Character('b'));
        RECompiler.hashPOSIX.put("cntrl", new Character('c'));
        RECompiler.hashPOSIX.put("digit", new Character('d'));
        RECompiler.hashPOSIX.put("graph", new Character('g'));
        RECompiler.hashPOSIX.put("lower", new Character('l'));
        RECompiler.hashPOSIX.put("print", new Character('p'));
        RECompiler.hashPOSIX.put("punct", new Character('!'));
        RECompiler.hashPOSIX.put("space", new Character('s'));
        RECompiler.hashPOSIX.put("upper", new Character('u'));
        RECompiler.hashPOSIX.put("xdigit", new Character('x'));
        RECompiler.hashPOSIX.put("javastart", new Character('j'));
        RECompiler.hashPOSIX.put("javapart", new Character('k'));
    }
    
    public RECompiler() {
        this.instruction = new char[128];
        this.lenInstruction = 0;
    }
    
    void ensure(final int n) {
        int curlen = this.instruction.length;
        if (this.lenInstruction + n >= curlen) {
            while (this.lenInstruction + n >= curlen) {
                curlen *= 2;
            }
            final char[] newInstruction = new char[curlen];
            System.arraycopy(this.instruction, 0, newInstruction, 0, this.lenInstruction);
            this.instruction = newInstruction;
        }
    }
    
    void emit(final char c) {
        this.ensure(1);
        this.instruction[this.lenInstruction++] = c;
    }
    
    void nodeInsert(final char opcode, final int opdata, final int insertAt) {
        this.ensure(3);
        System.arraycopy(this.instruction, insertAt, this.instruction, insertAt + 3, this.lenInstruction - insertAt);
        this.instruction[insertAt + 0] = opcode;
        this.instruction[insertAt + 1] = (char)opdata;
        this.instruction[insertAt + 2] = '\0';
        this.lenInstruction += 3;
    }
    
    void setNextOfEnd(int node, final int pointTo) {
        int next;
        while ((next = this.instruction[node + 2]) != 0) {
            node += next;
        }
        this.instruction[node + 2] = (char)(short)(pointTo - node);
    }
    
    int node(final char opcode, final int opdata) {
        this.ensure(3);
        this.instruction[this.lenInstruction + 0] = opcode;
        this.instruction[this.lenInstruction + 1] = (char)opdata;
        this.instruction[this.lenInstruction + 2] = '\0';
        this.lenInstruction += 3;
        return this.lenInstruction - 3;
    }
    
    void internalError() throws Error {
        throw new Error("Internal error!");
    }
    
    void syntaxError(final String s) throws RESyntaxException {
        throw new RESyntaxException(s);
    }
    
    void allocBrackets() {
        if (RECompiler.bracketStart == null) {
            RECompiler.bracketStart = new int[10];
            RECompiler.bracketEnd = new int[10];
            RECompiler.bracketMin = new int[10];
            RECompiler.bracketOpt = new int[10];
            for (int i = 0; i < 10; ++i) {
                final int[] bracketStart = RECompiler.bracketStart;
                final int n = i;
                final int[] bracketEnd = RECompiler.bracketEnd;
                final int n2 = i;
                final int[] bracketMin = RECompiler.bracketMin;
                final int n3 = i;
                final int[] bracketOpt = RECompiler.bracketOpt;
                final int n4 = i;
                final int n5 = -1;
                bracketMin[n3] = (bracketOpt[n4] = n5);
                bracketStart[n] = (bracketEnd[n2] = n5);
            }
        }
    }
    
    void bracket() throws RESyntaxException {
        if (this.idx >= this.len || this.pattern.charAt(this.idx++) != '{') {
            this.internalError();
        }
        if (this.idx >= this.len || !Character.isDigit(this.pattern.charAt(this.idx))) {
            this.syntaxError("Expected digit");
        }
        final StringBuffer number = new StringBuffer();
        while (this.idx < this.len) {
            if (!Character.isDigit(this.pattern.charAt(this.idx))) {
                break;
            }
            number.append(this.pattern.charAt(this.idx++));
        }
        try {
            RECompiler.bracketMin[RECompiler.brackets] = Integer.parseInt(number.toString());
        }
        catch (NumberFormatException e) {
            this.syntaxError("Expected valid number");
        }
        if (this.idx >= this.len) {
            this.syntaxError("Expected comma or right bracket");
        }
        if (this.pattern.charAt(this.idx) == '}') {
            ++this.idx;
            RECompiler.bracketOpt[RECompiler.brackets] = 0;
            return;
        }
        if (this.idx >= this.len || this.pattern.charAt(this.idx++) != ',') {
            this.syntaxError("Expected comma");
        }
        if (this.idx >= this.len) {
            this.syntaxError("Expected comma or right bracket");
        }
        if (this.pattern.charAt(this.idx) == '}') {
            ++this.idx;
            RECompiler.bracketOpt[RECompiler.brackets] = -1;
            return;
        }
        if (this.idx >= this.len || !Character.isDigit(this.pattern.charAt(this.idx))) {
            this.syntaxError("Expected digit");
        }
        number.setLength(0);
        while (this.idx < this.len) {
            if (!Character.isDigit(this.pattern.charAt(this.idx))) {
                break;
            }
            number.append(this.pattern.charAt(this.idx++));
        }
        try {
            RECompiler.bracketOpt[RECompiler.brackets] = Integer.parseInt(number.toString()) - RECompiler.bracketMin[RECompiler.brackets];
        }
        catch (NumberFormatException e) {
            this.syntaxError("Expected valid number");
        }
        if (RECompiler.bracketOpt[RECompiler.brackets] <= 0) {
            this.syntaxError("Bad range");
        }
        if (this.idx >= this.len || this.pattern.charAt(this.idx++) != '}') {
            this.syntaxError("Missing close brace");
        }
    }
    
    char escape() throws RESyntaxException {
        if (this.pattern.charAt(this.idx) != '\\') {
            this.internalError();
        }
        if (this.idx + 1 == this.len) {
            this.syntaxError("Escape terminates string");
        }
        this.idx += 2;
        final char escapeChar = this.pattern.charAt(this.idx - 1);
        switch (escapeChar) {
            case 'B':
            case 'b': {
                return '\ufffe';
            }
            case 'D':
            case 'S':
            case 'W':
            case 'd':
            case 's':
            case 'w': {
                return '\ufffd';
            }
            case 'u':
            case 'x': {
                int hexDigits = (escapeChar == 'u') ? 4 : 2;
                int val = 0;
                while (this.idx < this.len && hexDigits-- > 0) {
                    char c = this.pattern.charAt(this.idx);
                    if (c >= '0' && c <= '9') {
                        val = (val << 4) + c - 48;
                    }
                    else {
                        c = Character.toLowerCase(c);
                        if (c >= 'a' && c <= 'f') {
                            val = (val << 4) + (c - 'a') + 10;
                        }
                        else {
                            this.syntaxError("Expected " + hexDigits + " hexadecimal digits after \\" + escapeChar);
                        }
                    }
                    ++this.idx;
                }
                return (char)val;
            }
            case 't': {
                return '\t';
            }
            case 'n': {
                return '\n';
            }
            case 'r': {
                return '\r';
            }
            case 'f': {
                return '\f';
            }
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': {
                if ((this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx))) || escapeChar == '0') {
                    int val2 = escapeChar - '0';
                    if (this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx))) {
                        val2 = (val2 << 3) + (this.pattern.charAt(this.idx++) - '0');
                        if (this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx))) {
                            val2 = (val2 << 3) + (this.pattern.charAt(this.idx++) - '0');
                        }
                    }
                    return (char)val2;
                }
                return '\uffff';
            }
            default: {
                return escapeChar;
            }
        }
    }
    
    int characterClass() throws RESyntaxException {
        if (this.pattern.charAt(this.idx) != '[') {
            this.internalError();
        }
        if (this.idx + 1 >= this.len || this.pattern.charAt(++this.idx) == ']') {
            this.syntaxError("Empty or unterminated class");
        }
        if (this.idx < this.len && this.pattern.charAt(this.idx) == ':') {
            ++this.idx;
            final int idxStart = this.idx;
            while (this.idx < this.len && this.pattern.charAt(this.idx) >= 'a' && this.pattern.charAt(this.idx) <= 'z') {
                ++this.idx;
            }
            if (this.idx + 1 < this.len && this.pattern.charAt(this.idx) == ':' && this.pattern.charAt(this.idx + 1) == ']') {
                final String charClass = this.pattern.substring(idxStart, this.idx);
                final Character i = RECompiler.hashPOSIX.get(charClass);
                if (i != null) {
                    this.idx += 2;
                    return this.node('P', i);
                }
                this.syntaxError("Invalid POSIX character class '" + charClass + "'");
            }
            this.syntaxError("Invalid POSIX character class syntax");
        }
        final int ret = this.node('[', 0);
        char last;
        final char CHAR_INVALID = last = '\uffff';
        char simpleChar = '\0';
        boolean include = true;
        boolean definingRange = false;
        final int idxFirst = this.idx;
        char rangeStart = '\0';
        final RERange range = new RERange();
        while (this.idx < this.len && this.pattern.charAt(this.idx) != ']') {
            Label_0758: {
                switch (this.pattern.charAt(this.idx)) {
                    case '^': {
                        include = !include;
                        if (this.idx == idxFirst) {
                            range.include(0, 65535, true);
                        }
                        ++this.idx;
                        continue;
                    }
                    case '\\': {
                        final char c;
                        switch (c = this.escape()) {
                            case '\ufffe':
                            case '\uffff': {
                                this.syntaxError("Bad character class");
                            }
                            case '\ufffd': {
                                if (definingRange) {
                                    this.syntaxError("Bad character class");
                                }
                                switch (this.pattern.charAt(this.idx - 1)) {
                                    case 'D':
                                    case 'S':
                                    case 'W': {
                                        this.syntaxError("Bad character class");
                                    }
                                    case 's': {
                                        range.include('\t', include);
                                        range.include('\r', include);
                                        range.include('\f', include);
                                        range.include('\n', include);
                                        range.include('\b', include);
                                        range.include(' ', include);
                                        break;
                                    }
                                    case 'w': {
                                        range.include(97, 122, include);
                                        range.include(65, 90, include);
                                        range.include('_', include);
                                    }
                                    case 'd': {
                                        range.include(48, 57, include);
                                        break;
                                    }
                                }
                                last = CHAR_INVALID;
                                continue;
                            }
                            default: {
                                simpleChar = c;
                                break Label_0758;
                            }
                        }
                        break;
                    }
                    case '-': {
                        if (definingRange) {
                            this.syntaxError("Bad class range");
                        }
                        definingRange = true;
                        rangeStart = ((last == CHAR_INVALID) ? '\0' : last);
                        if (this.idx + 1 < this.len && this.pattern.charAt(++this.idx) == ']') {
                            simpleChar = '\uffff';
                            break;
                        }
                        continue;
                    }
                    default: {
                        simpleChar = this.pattern.charAt(this.idx++);
                        break;
                    }
                }
            }
            if (definingRange) {
                final char rangeEnd = simpleChar;
                if (rangeStart >= rangeEnd) {
                    this.syntaxError("Bad character class");
                }
                range.include(rangeStart, rangeEnd, include);
                last = CHAR_INVALID;
                definingRange = false;
            }
            else {
                if (this.idx + 1 >= this.len || this.pattern.charAt(this.idx + 1) != '-') {
                    range.include(simpleChar, include);
                }
                last = simpleChar;
            }
        }
        if (this.idx == this.len) {
            this.syntaxError("Unterminated character class");
        }
        ++this.idx;
        this.instruction[ret + 1] = (char)range.num;
        for (int j = 0; j < range.num; ++j) {
            this.emit((char)range.minRange[j]);
            this.emit((char)range.maxRange[j]);
        }
        return ret;
    }
    
    int atom() throws RESyntaxException {
        final int ret = this.node('A', 0);
        int lenAtom = 0;
    Label_0366:
        while (this.idx < this.len) {
            if (this.idx + 1 < this.len) {
                char c = this.pattern.charAt(this.idx + 1);
                if (this.pattern.charAt(this.idx) == '\\') {
                    final int idxEscape = this.idx;
                    this.escape();
                    if (this.idx < this.len) {
                        c = this.pattern.charAt(this.idx);
                    }
                    this.idx = idxEscape;
                }
                switch (c) {
                    case '*':
                    case '+':
                    case '?':
                    case '{': {
                        if (lenAtom != 0) {
                            break Label_0366;
                        }
                        break;
                    }
                }
            }
            switch (this.pattern.charAt(this.idx)) {
                case '$':
                case '(':
                case ')':
                case '.':
                case '[':
                case ']':
                case '^':
                case '|': {
                    break Label_0366;
                }
                case '*':
                case '+':
                case '?':
                case '{': {
                    if (lenAtom == 0) {
                        this.syntaxError("Missing operand to closure");
                        break Label_0366;
                    }
                    break Label_0366;
                }
                case '\\': {
                    final int idxBeforeEscape = this.idx;
                    final char c2 = this.escape();
                    if ((c2 & '\ufff0') == '\ufff0') {
                        this.idx = idxBeforeEscape;
                        break Label_0366;
                    }
                    this.emit(c2);
                    ++lenAtom;
                    continue;
                }
                default: {
                    this.emit(this.pattern.charAt(this.idx++));
                    ++lenAtom;
                    continue;
                }
            }
        }
        if (lenAtom == 0) {
            this.internalError();
        }
        this.instruction[ret + 1] = (char)lenAtom;
        return ret;
    }
    
    int terminal(final int[] flags) throws RESyntaxException {
        Label_0325: {
            switch (this.pattern.charAt(this.idx)) {
                case '$':
                case '.':
                case '^': {
                    return this.node(this.pattern.charAt(this.idx++), 0);
                }
                case '[': {
                    return this.characterClass();
                }
                case '(': {
                    return this.expr(flags);
                }
                case ')': {
                    this.syntaxError("Unexpected close paren");
                }
                case '|': {
                    this.internalError();
                }
                case ']': {
                    this.syntaxError("Mismatched class");
                }
                case '\0': {
                    this.syntaxError("Unexpected end of input");
                }
                case '*':
                case '+':
                case '?':
                case '{': {
                    this.syntaxError("Missing operand to closure");
                }
                case '\\': {
                    final int idxBeforeEscape = this.idx;
                    switch (this.escape()) {
                        case '\ufffd':
                        case '\ufffe': {
                            final int n = 0;
                            flags[n] &= 0xFFFFFFFE;
                            return this.node('\\', this.pattern.charAt(this.idx - 1));
                        }
                        case '\uffff': {
                            final char backreference = (char)(this.pattern.charAt(this.idx - 1) - '0');
                            if (this.parens <= backreference) {
                                this.syntaxError("Bad backreference");
                            }
                            final int n2 = 0;
                            flags[n2] |= 0x1;
                            return this.node('#', backreference);
                        }
                        default: {
                            this.idx = idxBeforeEscape;
                            final int n3 = 0;
                            flags[n3] &= 0xFFFFFFFE;
                            break Label_0325;
                        }
                    }
                    break;
                }
            }
        }
        final int n4 = 0;
        flags[n4] &= 0xFFFFFFFE;
        return this.atom();
    }
    
    int closure(final int[] flags) throws RESyntaxException {
        final int idxBeforeTerminal = this.idx;
        final int[] terminalFlags = { 0 };
        final int ret = this.terminal(terminalFlags);
        final int n = 0;
        flags[n] |= terminalFlags[0];
        if (this.idx >= this.len) {
            return ret;
        }
        boolean greedy = true;
        char closureType = this.pattern.charAt(this.idx);
        switch (closureType) {
            case '*':
            case '?': {
                final int n2 = 0;
                flags[n2] |= 0x1;
            }
            case '+': {
                ++this.idx;
            }
            case '{': {
                final int opcode = this.instruction[ret + 0];
                if (opcode == 94 || opcode == 36) {
                    this.syntaxError("Bad closure operand");
                }
                if ((terminalFlags[0] & 0x1) != 0x0) {
                    this.syntaxError("Closure operand can't be nullable");
                    break;
                }
                break;
            }
        }
        if (this.idx < this.len && this.pattern.charAt(this.idx) == '?') {
            ++this.idx;
            greedy = false;
        }
        if (greedy) {
            Label_0474: {
                switch (closureType) {
                    case '{': {
                        boolean found = false;
                        this.allocBrackets();
                        int i;
                        for (i = 0; i < RECompiler.brackets; ++i) {
                            if (RECompiler.bracketStart[i] == this.idx) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            if (RECompiler.brackets >= 10) {
                                this.syntaxError("Too many bracketed closures (limit is 10)");
                            }
                            RECompiler.bracketStart[RECompiler.brackets] = this.idx;
                            this.bracket();
                            RECompiler.bracketEnd[RECompiler.brackets] = this.idx;
                            i = RECompiler.brackets++;
                        }
                        final int[] bracketMin = RECompiler.bracketMin;
                        final int n3 = i;
                        if (--bracketMin[n3] > 0) {
                            this.idx = idxBeforeTerminal;
                            break;
                        }
                        if (RECompiler.bracketOpt[i] == -2) {
                            closureType = '*';
                            RECompiler.bracketOpt[i] = 0;
                            this.idx = RECompiler.bracketEnd[i];
                            break Label_0474;
                        }
                        if (RECompiler.bracketOpt[i] == -1) {
                            this.idx = idxBeforeTerminal;
                            RECompiler.bracketOpt[i] = -2;
                            break;
                        }
                        if (RECompiler.bracketOpt[i]-- > 0) {
                            this.idx = idxBeforeTerminal;
                            closureType = '?';
                            break Label_0474;
                        }
                        this.idx = RECompiler.bracketEnd[i];
                        break;
                    }
                    case '*':
                    case '?': {
                        if (!greedy) {
                            break;
                        }
                        if (closureType == '?') {
                            this.nodeInsert('|', 0, ret);
                            this.setNextOfEnd(ret, this.node('|', 0));
                            final int nothing = this.node('N', 0);
                            this.setNextOfEnd(ret, nothing);
                            this.setNextOfEnd(ret + 3, nothing);
                        }
                        if (closureType == '*') {
                            this.nodeInsert('|', 0, ret);
                            this.setNextOfEnd(ret + 3, this.node('|', 0));
                            this.setNextOfEnd(ret + 3, this.node('G', 0));
                            this.setNextOfEnd(ret + 3, ret);
                            this.setNextOfEnd(ret, this.node('|', 0));
                            this.setNextOfEnd(ret, this.node('N', 0));
                            break;
                        }
                        break;
                    }
                    case '+': {
                        final int branch = this.node('|', 0);
                        this.setNextOfEnd(ret, branch);
                        this.setNextOfEnd(this.node('G', 0), ret);
                        this.setNextOfEnd(branch, this.node('|', 0));
                        this.setNextOfEnd(ret, this.node('N', 0));
                        break;
                    }
                }
            }
        }
        else {
            this.setNextOfEnd(ret, this.node('E', 0));
            switch (closureType) {
                case '?': {
                    this.nodeInsert('/', 0, ret);
                    break;
                }
                case '*': {
                    this.nodeInsert('8', 0, ret);
                    break;
                }
                case '+': {
                    this.nodeInsert('=', 0, ret);
                    break;
                }
            }
            this.setNextOfEnd(ret, this.lenInstruction);
        }
        return ret;
    }
    
    int branch(final int[] flags) throws RESyntaxException {
        final int ret = this.node('|', 0);
        int chain = -1;
        final int[] closureFlags = { 0 };
        boolean nullable = true;
        while (this.idx < this.len && this.pattern.charAt(this.idx) != '|' && this.pattern.charAt(this.idx) != ')') {
            closureFlags[0] = 0;
            final int node = this.closure(closureFlags);
            if (closureFlags[0] == 0) {
                nullable = false;
            }
            if (chain != -1) {
                this.setNextOfEnd(chain, node);
            }
            chain = node;
        }
        if (chain == -1) {
            this.node('N', 0);
        }
        if (nullable) {
            final int n = 0;
            flags[n] |= 0x1;
        }
        return ret;
    }
    
    int expr(final int[] flags) throws RESyntaxException {
        boolean paren = false;
        int ret = -1;
        final int closeParens = this.parens;
        if ((flags[0] & 0x2) == 0x0 && this.pattern.charAt(this.idx) == '(') {
            ++this.idx;
            paren = true;
            ret = this.node('(', this.parens++);
        }
        final int n = 0;
        flags[n] &= 0xFFFFFFFD;
        int branch = this.branch(flags);
        if (ret == -1) {
            ret = branch;
        }
        else {
            this.setNextOfEnd(ret, branch);
        }
        while (this.idx < this.len && this.pattern.charAt(this.idx) == '|') {
            ++this.idx;
            branch = this.branch(flags);
            this.setNextOfEnd(ret, branch);
        }
        int end;
        if (paren) {
            if (this.idx < this.len && this.pattern.charAt(this.idx) == ')') {
                ++this.idx;
            }
            else {
                this.syntaxError("Missing close paren");
            }
            end = this.node(')', closeParens);
        }
        else {
            end = this.node('E', 0);
        }
        this.setNextOfEnd(ret, end);
        for (int next = -1, i = ret; next != 0; next = this.instruction[i + 2], i += next) {
            if (this.instruction[i + 0] == '|') {
                this.setNextOfEnd(i + 3, end);
            }
        }
        return ret;
    }
    
    public REProgram compile(final String pattern) throws RESyntaxException {
        this.pattern = pattern;
        this.len = pattern.length();
        this.idx = 0;
        this.lenInstruction = 0;
        this.parens = 1;
        RECompiler.brackets = 0;
        final int[] flags = { 2 };
        this.expr(flags);
        if (this.idx != this.len) {
            if (pattern.charAt(this.idx) == ')') {
                this.syntaxError("Unmatched close paren");
            }
            this.syntaxError("Unexpected input remains");
        }
        final char[] ins = new char[this.lenInstruction];
        System.arraycopy(this.instruction, 0, ins, 0, this.lenInstruction);
        return new REProgram(ins);
    }
    
    class RERange
    {
        int size;
        int[] minRange;
        int[] maxRange;
        int num;
        
        RERange() {
            this.size = 16;
            this.minRange = new int[this.size];
            this.maxRange = new int[this.size];
            this.num = 0;
        }
        
        void delete(int index) {
            if (this.num == 0 || index >= this.num) {
                return;
            }
            while (index++ < this.num) {
                if (index - 1 >= 0) {
                    this.minRange[index - 1] = this.minRange[index];
                    this.maxRange[index - 1] = this.maxRange[index];
                }
            }
            --this.num;
        }
        
        void merge(int min, int max) {
            for (int i = 0; i < this.num; ++i) {
                if (min >= this.minRange[i] && max <= this.maxRange[i]) {
                    return;
                }
                if (min <= this.minRange[i] && max >= this.maxRange[i]) {
                    this.delete(i);
                    this.merge(min, max);
                    return;
                }
                if (min >= this.minRange[i] && min <= this.maxRange[i]) {
                    this.delete(i);
                    min = this.minRange[i];
                    this.merge(min, max);
                    return;
                }
                if (max >= this.minRange[i] && max <= this.maxRange[i]) {
                    this.delete(i);
                    max = this.maxRange[i];
                    this.merge(min, max);
                    return;
                }
            }
            if (this.num >= this.size) {
                this.size *= 2;
                final int[] newMin = new int[this.size];
                final int[] newMax = new int[this.size];
                System.arraycopy(this.minRange, 0, newMin, 0, this.num);
                System.arraycopy(this.maxRange, 0, newMax, 0, this.num);
                this.minRange = newMin;
                this.maxRange = newMax;
            }
            this.minRange[this.num] = min;
            this.maxRange[this.num] = max;
            ++this.num;
        }
        
        void remove(final int min, final int max) {
            for (int i = 0; i < this.num; ++i) {
                if (this.minRange[i] >= min && this.maxRange[i] <= max) {
                    this.delete(i);
                    --i;
                    return;
                }
                if (min >= this.minRange[i] && max <= this.maxRange[i]) {
                    final int minr = this.minRange[i];
                    final int maxr = this.maxRange[i];
                    this.delete(i);
                    if (minr < min - 1) {
                        this.merge(minr, min - 1);
                    }
                    if (max + 1 < maxr) {
                        this.merge(max + 1, maxr);
                    }
                    return;
                }
                if (this.minRange[i] >= min && this.minRange[i] <= max) {
                    this.minRange[i] = max + 1;
                    return;
                }
                if (this.maxRange[i] >= min && this.maxRange[i] <= max) {
                    this.maxRange[i] = min - 1;
                    return;
                }
            }
        }
        
        void include(final int min, final int max, final boolean include) {
            if (include) {
                this.merge(min, max);
            }
            else {
                this.remove(min, max);
            }
        }
        
        void include(final char minmax, final boolean include) {
            this.include(minmax, minmax, include);
        }
    }
}
