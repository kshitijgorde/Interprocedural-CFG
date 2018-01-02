// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

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
    
    int atom() throws RESyntaxException {
        final int node = this.node('A', 0);
        int n = 0;
    Label_0365:
        while (this.idx < this.len) {
            if (this.idx + 1 < this.len) {
                char c = this.pattern.charAt(this.idx + 1);
                if (this.pattern.charAt(this.idx) == '\\') {
                    final int idx = this.idx;
                    this.escape();
                    if (this.idx < this.len) {
                        c = this.pattern.charAt(this.idx);
                    }
                    this.idx = idx;
                }
                switch (c) {
                    case 42:
                    case 43:
                    case 63:
                    case 123: {
                        if (n != 0) {
                            break Label_0365;
                        }
                        break;
                    }
                }
            }
            switch (this.pattern.charAt(this.idx)) {
                case '*':
                case '+':
                case '?':
                case '{': {
                    if (n == 0) {
                        this.syntaxError("Missing operand to closure");
                        break Label_0365;
                    }
                    break Label_0365;
                }
                case '\\': {
                    final int idx2 = this.idx;
                    final char escape = this.escape();
                    if ((escape & '\ufff0') == '\ufff0') {
                        this.idx = idx2;
                        break Label_0365;
                    }
                    this.emit(escape);
                    ++n;
                    continue;
                }
                default: {
                    this.emit(this.pattern.charAt(this.idx++));
                    ++n;
                    continue;
                }
                case '$':
                case '(':
                case ')':
                case '.':
                case '[':
                case ']':
                case '^':
                case '|': {
                    break Label_0365;
                }
            }
        }
        if (n == 0) {
            this.internalError();
        }
        this.instruction[node + 1] = (char)n;
        return node;
    }
    
    void bracket() throws RESyntaxException {
        if (this.idx >= this.len || this.pattern.charAt(this.idx++) != '{') {
            this.internalError();
        }
        if (this.idx >= this.len || !Character.isDigit(this.pattern.charAt(this.idx))) {
            this.syntaxError("Expected digit");
        }
        final StringBuffer sb = new StringBuffer();
        while (this.idx < this.len) {
            if (!Character.isDigit(this.pattern.charAt(this.idx))) {
                break;
            }
            sb.append(this.pattern.charAt(this.idx++));
        }
        try {
            RECompiler.bracketMin[RECompiler.brackets] = Integer.parseInt(sb.toString());
        }
        catch (NumberFormatException ex) {
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
        sb.setLength(0);
        while (this.idx < this.len) {
            if (!Character.isDigit(this.pattern.charAt(this.idx))) {
                break;
            }
            sb.append(this.pattern.charAt(this.idx++));
        }
        try {
            RECompiler.bracketOpt[RECompiler.brackets] = Integer.parseInt(sb.toString()) - RECompiler.bracketMin[RECompiler.brackets];
        }
        catch (NumberFormatException ex2) {
            this.syntaxError("Expected valid number");
        }
        if (RECompiler.bracketOpt[RECompiler.brackets] <= 0) {
            this.syntaxError("Bad range");
        }
        if (this.idx >= this.len || this.pattern.charAt(this.idx++) != '}') {
            this.syntaxError("Missing close brace");
        }
    }
    
    int branch(final int[] array) throws RESyntaxException {
        final int node = this.node('|', 0);
        int n = -1;
        final int[] array2 = { 0 };
        boolean b = true;
        while (this.idx < this.len && this.pattern.charAt(this.idx) != '|' && this.pattern.charAt(this.idx) != ')') {
            array2[0] = 0;
            final int closure = this.closure(array2);
            if (array2[0] == 0) {
                b = false;
            }
            if (n != -1) {
                this.setNextOfEnd(n, closure);
            }
            n = closure;
        }
        if (n == -1) {
            this.node('N', 0);
        }
        if (b) {
            final int n2 = 0;
            array[n2] |= 0x1;
        }
        return node;
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
            final int idx = this.idx;
            while (this.idx < this.len && this.pattern.charAt(this.idx) >= 'a' && this.pattern.charAt(this.idx) <= 'z') {
                ++this.idx;
            }
            if (this.idx + 1 < this.len && this.pattern.charAt(this.idx) == ':' && this.pattern.charAt(this.idx + 1) == ']') {
                final String substring = this.pattern.substring(idx, this.idx);
                final Character c = RECompiler.hashPOSIX.get(substring);
                if (c != null) {
                    this.idx += 2;
                    return this.node('P', c);
                }
                this.syntaxError("Invalid POSIX character class '" + substring + "'");
            }
            this.syntaxError("Invalid POSIX character class syntax");
        }
        final int node = this.node('[', 0);
        char c3;
        final char c2 = c3 = '\uffff';
        boolean b = true;
        int n = 0;
        final int idx2 = this.idx;
        char c4 = '\0';
        final RERange reRange = new RERange();
        while (this.idx < this.len && this.pattern.charAt(this.idx) != ']') {
            char char1 = '\0';
            Label_0745: {
                switch (this.pattern.charAt(this.idx)) {
                    case '^': {
                        b ^= true;
                        if (this.idx == idx2) {
                            reRange.include(0, 65535, true);
                        }
                        ++this.idx;
                        continue;
                    }
                    case '\\': {
                        final char escape;
                        switch (escape = this.escape()) {
                            case '\ufffe':
                            case '\uffff': {
                                this.syntaxError("Bad character class");
                            }
                            case '\ufffd': {
                                if (n != 0) {
                                    this.syntaxError("Bad character class");
                                }
                                switch (this.pattern.charAt(this.idx - 1)) {
                                    case 'D':
                                    case 'S':
                                    case 'W': {
                                        this.syntaxError("Bad character class");
                                    }
                                    case 's': {
                                        reRange.include('\t', b);
                                        reRange.include('\r', b);
                                        reRange.include('\f', b);
                                        reRange.include('\n', b);
                                        reRange.include('\b', b);
                                        reRange.include(' ', b);
                                        break;
                                    }
                                    case 'w': {
                                        reRange.include(97, 122, b);
                                        reRange.include(65, 90, b);
                                        reRange.include('_', b);
                                    }
                                    case 'd': {
                                        reRange.include(48, 57, b);
                                        break;
                                    }
                                }
                                c3 = c2;
                                continue;
                            }
                            default: {
                                char1 = escape;
                                break Label_0745;
                            }
                        }
                        break;
                    }
                    case '-': {
                        if (n != 0) {
                            this.syntaxError("Bad class range");
                        }
                        n = 1;
                        c4 = ((c3 == c2) ? '\0' : c3);
                        if (this.idx + 1 < this.len && this.pattern.charAt(++this.idx) == ']') {
                            char1 = '\uffff';
                            break;
                        }
                        continue;
                    }
                    default: {
                        char1 = this.pattern.charAt(this.idx++);
                        break;
                    }
                }
            }
            if (n != 0) {
                final char c5 = char1;
                if (c4 >= c5) {
                    this.syntaxError("Bad character class");
                }
                reRange.include(c4, c5, b);
                c3 = c2;
                n = 0;
            }
            else {
                if (this.idx + 1 >= this.len || this.pattern.charAt(this.idx + 1) != '-') {
                    reRange.include(char1, b);
                }
                c3 = char1;
            }
        }
        if (this.idx == this.len) {
            this.syntaxError("Unterminated character class");
        }
        ++this.idx;
        this.instruction[node + 1] = (char)reRange.num;
        for (int i = 0; i < reRange.num; ++i) {
            this.emit((char)reRange.minRange[i]);
            this.emit((char)reRange.maxRange[i]);
        }
        return node;
    }
    
    int closure(final int[] array) throws RESyntaxException {
        final int idx = this.idx;
        final int[] array2 = { 0 };
        final int terminal = this.terminal(array2);
        final int n = 0;
        array[n] |= array2[0];
        if (this.idx >= this.len) {
            return terminal;
        }
        boolean b = true;
        int char1 = this.pattern.charAt(this.idx);
        switch (char1) {
            case 42:
            case 63: {
                final int n2 = 0;
                array[n2] |= 0x1;
            }
            case 43: {
                ++this.idx;
            }
            case 123: {
                final char c = this.instruction[terminal];
                if (c == '^' || c == '$') {
                    this.syntaxError("Bad closure operand");
                }
                if ((array2[0] & 0x1) != 0x0) {
                    this.syntaxError("Closure operand can't be nullable");
                    break;
                }
                break;
            }
        }
        if (this.idx < this.len && this.pattern.charAt(this.idx) == '?') {
            ++this.idx;
            b = false;
        }
        if (b) {
            Label_0473: {
                switch (char1) {
                    case 123: {
                        boolean b2 = false;
                        this.allocBrackets();
                        int i;
                        for (i = 0; i < RECompiler.brackets; ++i) {
                            if (RECompiler.bracketStart[i] == this.idx) {
                                b2 = true;
                                break;
                            }
                        }
                        if (!b2) {
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
                            this.idx = idx;
                            break;
                        }
                        if (RECompiler.bracketOpt[i] == -2) {
                            char1 = 42;
                            RECompiler.bracketOpt[i] = 0;
                            this.idx = RECompiler.bracketEnd[i];
                            break Label_0473;
                        }
                        if (RECompiler.bracketOpt[i] == -1) {
                            this.idx = idx;
                            RECompiler.bracketOpt[i] = -2;
                            break;
                        }
                        if (RECompiler.bracketOpt[i]-- > 0) {
                            this.idx = idx;
                            char1 = 63;
                            break Label_0473;
                        }
                        this.idx = RECompiler.bracketEnd[i];
                        break;
                    }
                    case 42:
                    case 63: {
                        if (!b) {
                            break;
                        }
                        if (char1 == 63) {
                            this.nodeInsert('|', 0, terminal);
                            this.setNextOfEnd(terminal, this.node('|', 0));
                            final int node = this.node('N', 0);
                            this.setNextOfEnd(terminal, node);
                            this.setNextOfEnd(terminal + 3, node);
                        }
                        if (char1 == 42) {
                            this.nodeInsert('|', 0, terminal);
                            this.setNextOfEnd(terminal + 3, this.node('|', 0));
                            this.setNextOfEnd(terminal + 3, this.node('G', 0));
                            this.setNextOfEnd(terminal + 3, terminal);
                            this.setNextOfEnd(terminal, this.node('|', 0));
                            this.setNextOfEnd(terminal, this.node('N', 0));
                            break;
                        }
                        break;
                    }
                    case 43: {
                        final int node2 = this.node('|', 0);
                        this.setNextOfEnd(terminal, node2);
                        this.setNextOfEnd(this.node('G', 0), terminal);
                        this.setNextOfEnd(node2, this.node('|', 0));
                        this.setNextOfEnd(terminal, this.node('N', 0));
                        break;
                    }
                }
            }
        }
        else {
            this.setNextOfEnd(terminal, this.node('E', 0));
            switch (char1) {
                case 63: {
                    this.nodeInsert('/', 0, terminal);
                    break;
                }
                case 42: {
                    this.nodeInsert('8', 0, terminal);
                    break;
                }
                case 43: {
                    this.nodeInsert('=', 0, terminal);
                    break;
                }
            }
            this.setNextOfEnd(terminal, this.lenInstruction);
        }
        return terminal;
    }
    
    public REProgram compile(final String pattern) throws RESyntaxException {
        this.pattern = pattern;
        this.len = pattern.length();
        this.idx = 0;
        this.lenInstruction = 0;
        this.parens = 1;
        RECompiler.brackets = 0;
        this.expr(new int[] { 2 });
        if (this.idx != this.len) {
            if (pattern.charAt(this.idx) == ')') {
                this.syntaxError("Unmatched close paren");
            }
            this.syntaxError("Unexpected input remains");
        }
        final char[] array = new char[this.lenInstruction];
        System.arraycopy(this.instruction, 0, array, 0, this.lenInstruction);
        return new REProgram(array);
    }
    
    void emit(final char c) {
        this.ensure(1);
        this.instruction[this.lenInstruction++] = c;
    }
    
    void ensure(final int n) {
        int length = this.instruction.length;
        if (this.lenInstruction + n >= length) {
            while (this.lenInstruction + n >= length) {
                length *= 2;
            }
            final char[] instruction = new char[length];
            System.arraycopy(this.instruction, 0, instruction, 0, this.lenInstruction);
            this.instruction = instruction;
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
        final char char1 = this.pattern.charAt(this.idx - 1);
        switch (char1) {
            case 66:
            case 98: {
                return '\ufffe';
            }
            case 68:
            case 83:
            case 87:
            case 100:
            case 115:
            case 119: {
                return '\ufffd';
            }
            case 117:
            case 120: {
                int n = (char1 == 'u') ? 4 : 2;
                int n2 = 0;
                while (this.idx < this.len && n-- > 0) {
                    final char char2 = this.pattern.charAt(this.idx);
                    if (char2 >= '0' && char2 <= '9') {
                        n2 = (n2 << 4) + char2 - 48;
                    }
                    else {
                        final char lowerCase = Character.toLowerCase(char2);
                        if (lowerCase >= 'a' && lowerCase <= 'f') {
                            n2 = (n2 << 4) + (lowerCase - 'a') + 10;
                        }
                        else {
                            this.syntaxError("Expected " + n + " hexadecimal digits after \\" + char1);
                        }
                    }
                    ++this.idx;
                }
                return (char)n2;
            }
            case 116: {
                return '\t';
            }
            case 110: {
                return '\n';
            }
            case 114: {
                return '\r';
            }
            case 102: {
                return '\f';
            }
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57: {
                if ((this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx))) || char1 == '0') {
                    int n3 = char1 - '0';
                    if (this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx))) {
                        n3 = (n3 << 3) + (this.pattern.charAt(this.idx++) - '0');
                        if (this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx))) {
                            n3 = (n3 << 3) + (this.pattern.charAt(this.idx++) - '0');
                        }
                    }
                    return (char)n3;
                }
                return '\uffff';
            }
            default: {
                return char1;
            }
        }
    }
    
    int expr(final int[] array) throws RESyntaxException {
        boolean b = false;
        int node = -1;
        final int parens = this.parens;
        if ((array[0] & 0x2) == 0x0 && this.pattern.charAt(this.idx) == '(') {
            ++this.idx;
            b = true;
            node = this.node('(', this.parens++);
        }
        final int n = 0;
        array[n] &= 0xFFFFFFFD;
        final int branch = this.branch(array);
        if (node == -1) {
            node = branch;
        }
        else {
            this.setNextOfEnd(node, branch);
        }
        while (this.idx < this.len && this.pattern.charAt(this.idx) == '|') {
            ++this.idx;
            this.setNextOfEnd(node, this.branch(array));
        }
        int n2;
        if (b) {
            if (this.idx < this.len && this.pattern.charAt(this.idx) == ')') {
                ++this.idx;
            }
            else {
                this.syntaxError("Missing close paren");
            }
            n2 = this.node(')', parens);
        }
        else {
            n2 = this.node('E', 0);
        }
        this.setNextOfEnd(node, n2);
        for (int i = -1, n3 = node; i != 0; i = this.instruction[n3 + 2], n3 += i) {
            if (this.instruction[n3] == '|') {
                this.setNextOfEnd(n3 + 3, n2);
            }
        }
        return node;
    }
    
    void internalError() throws Error {
        throw new Error("Internal error!");
    }
    
    int node(final char c, final int n) {
        this.ensure(3);
        this.instruction[this.lenInstruction] = c;
        this.instruction[this.lenInstruction + 1] = (char)n;
        this.instruction[this.lenInstruction + 2] = '\0';
        this.lenInstruction += 3;
        return this.lenInstruction - 3;
    }
    
    void nodeInsert(final char c, final int n, final int n2) {
        this.ensure(3);
        System.arraycopy(this.instruction, n2, this.instruction, n2 + 3, this.lenInstruction - n2);
        this.instruction[n2] = c;
        this.instruction[n2 + 1] = (char)n;
        this.instruction[n2 + 2] = '\0';
        this.lenInstruction += 3;
    }
    
    void setNextOfEnd(int n, final int n2) {
        char c;
        while ((c = this.instruction[n + 2]) != '\0') {
            n += c;
        }
        this.instruction[n + 2] = (char)(short)(n2 - n);
    }
    
    void syntaxError(final String s) throws RESyntaxException {
        throw new RESyntaxException(s);
    }
    
    int terminal(final int[] array) throws RESyntaxException {
        Label_0323: {
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
                    return this.expr(array);
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
                    final int idx = this.idx;
                    switch (this.escape()) {
                        case '\ufffd':
                        case '\ufffe': {
                            final int n = 0;
                            array[n] &= 0xFFFFFFFE;
                            return this.node('\\', this.pattern.charAt(this.idx - 1));
                        }
                        case '\uffff': {
                            final char c = (char)(this.pattern.charAt(this.idx - 1) - '0');
                            if (this.parens <= c) {
                                this.syntaxError("Bad backreference");
                            }
                            final int n2 = 0;
                            array[n2] |= 0x1;
                            return this.node('#', c);
                        }
                        default: {
                            this.idx = idx;
                            final int n3 = 0;
                            array[n3] &= 0xFFFFFFFE;
                            break Label_0323;
                        }
                    }
                    break;
                }
            }
        }
        final int n4 = 0;
        array[n4] &= 0xFFFFFFFE;
        return this.atom();
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
        
        void delete(int n) {
            if (this.num == 0 || n >= this.num) {
                return;
            }
            while (n++ < this.num) {
                if (n - 1 >= 0) {
                    this.minRange[n - 1] = this.minRange[n];
                    this.maxRange[n - 1] = this.maxRange[n];
                }
            }
            --this.num;
        }
        
        void include(final char c, final boolean b) {
            this.include(c, c, b);
        }
        
        void include(final int n, final int n2, final boolean b) {
            if (b) {
                this.merge(n, n2);
            }
            else {
                this.remove(n, n2);
            }
        }
        
        void merge(int n, int n2) {
            for (int i = 0; i < this.num; ++i) {
                if (n >= this.minRange[i] && n2 <= this.maxRange[i]) {
                    return;
                }
                if (n <= this.minRange[i] && n2 >= this.maxRange[i]) {
                    this.delete(i);
                    this.merge(n, n2);
                    return;
                }
                if (n >= this.minRange[i] && n <= this.maxRange[i]) {
                    this.delete(i);
                    n = this.minRange[i];
                    this.merge(n, n2);
                    return;
                }
                if (n2 >= this.minRange[i] && n2 <= this.maxRange[i]) {
                    this.delete(i);
                    n2 = this.maxRange[i];
                    this.merge(n, n2);
                    return;
                }
            }
            if (this.num >= this.size) {
                this.size *= 2;
                final int[] minRange = new int[this.size];
                final int[] maxRange = new int[this.size];
                System.arraycopy(this.minRange, 0, minRange, 0, this.num);
                System.arraycopy(this.maxRange, 0, maxRange, 0, this.num);
                this.minRange = minRange;
                this.maxRange = maxRange;
            }
            this.minRange[this.num] = n;
            this.maxRange[this.num] = n2;
            ++this.num;
        }
        
        void remove(final int n, final int n2) {
            for (int i = 0; i < this.num; ++i) {
                if (this.minRange[i] >= n && this.maxRange[i] <= n2) {
                    this.delete(i);
                    --i;
                    return;
                }
                if (n >= this.minRange[i] && n2 <= this.maxRange[i]) {
                    final int n3 = this.minRange[i];
                    final int n4 = this.maxRange[i];
                    this.delete(i);
                    if (n3 < n - 1) {
                        this.merge(n3, n - 1);
                    }
                    if (n2 + 1 < n4) {
                        this.merge(n2 + 1, n4);
                    }
                    return;
                }
                if (this.minRange[i] >= n && this.minRange[i] <= n2) {
                    this.minRange[i] = n2 + 1;
                    return;
                }
                if (this.maxRange[i] >= n && this.maxRange[i] <= n2) {
                    this.maxRange[i] = n - 1;
                    return;
                }
            }
        }
    }
}
