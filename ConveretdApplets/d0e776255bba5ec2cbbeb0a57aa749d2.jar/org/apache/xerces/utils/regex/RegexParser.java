// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils.regex;

import java.util.MissingResourceException;
import java.util.Locale;
import java.util.Vector;
import java.util.ResourceBundle;

class RegexParser
{
    static final int T_CHAR = 0;
    static final int T_EOF = 1;
    static final int T_OR = 2;
    static final int T_STAR = 3;
    static final int T_PLUS = 4;
    static final int T_QUESTION = 5;
    static final int T_LPAREN = 6;
    static final int T_RPAREN = 7;
    static final int T_DOT = 8;
    static final int T_LBRACKET = 9;
    static final int T_BACKSOLIDUS = 10;
    static final int T_CARET = 11;
    static final int T_DOLLAR = 12;
    static final int T_LPAREN2 = 13;
    static final int T_LOOKAHEAD = 14;
    static final int T_NEGATIVELOOKAHEAD = 15;
    static final int T_LOOKBEHIND = 16;
    static final int T_NEGATIVELOOKBEHIND = 17;
    static final int T_INDEPENDENT = 18;
    static final int T_SET_OPERATIONS = 19;
    static final int T_POSIX_CHARCLASS_START = 20;
    static final int T_COMMENT = 21;
    static final int T_MODIFIERS = 22;
    static final int T_CONDITION = 23;
    static final int T_XMLSCHEMA_CC_SUBTRACTION = 24;
    int offset;
    String regex;
    int regexlen;
    int options;
    ResourceBundle resources;
    int chardata;
    int nexttoken;
    protected static final int S_NORMAL = 0;
    protected static final int S_INBRACKETS = 1;
    protected static final int S_INXBRACKETS = 2;
    int context;
    int parennumber;
    boolean hasBackReferences;
    Vector references;
    
    public RegexParser() {
        this.context = 0;
        this.parennumber = 1;
        this.references = null;
        this.setLocale(Locale.getDefault());
    }
    
    public RegexParser(final Locale locale) {
        this.context = 0;
        this.parennumber = 1;
        this.references = null;
        this.setLocale(locale);
    }
    
    public void setLocale(final Locale locale) {
        try {
            this.resources = ResourceBundle.getBundle("org.apache.xerces.utils.regex.message", locale);
        }
        catch (MissingResourceException ex) {
            throw new RuntimeException("Installation Problem???  Couldn't load messages: " + ex.getMessage());
        }
    }
    
    final ParseException ex(final String s, final int n) {
        return new ParseException(this.resources.getString(s), n);
    }
    
    private final boolean isSet(final int n) {
        return (this.options & n) == n;
    }
    
    synchronized Token parse(final String regex, final int options) throws ParseException {
        this.options = options;
        this.setContext(this.offset = 0);
        this.parennumber = 1;
        this.hasBackReferences = false;
        this.regex = regex;
        if (this.isSet(16)) {
            this.regex = REUtil.stripExtendedComment(this.regex);
        }
        this.regexlen = this.regex.length();
        this.next();
        final Token regex2 = this.parseRegex();
        if (this.offset != this.regexlen) {
            throw this.ex("parser.parse.1", this.offset);
        }
        if (this.references != null) {
            for (int i = 0; i < this.references.size(); ++i) {
                final ReferencePosition referencePosition = this.references.elementAt(i);
                if (this.parennumber <= referencePosition.refNumber) {
                    throw this.ex("parser.parse.2", referencePosition.position);
                }
            }
            this.references.removeAllElements();
        }
        return regex2;
    }
    
    protected final void setContext(final int context) {
        this.context = context;
    }
    
    final int read() {
        return this.nexttoken;
    }
    
    final void next() {
        if (this.offset >= this.regexlen) {
            this.chardata = -1;
            this.nexttoken = 1;
            return;
        }
        final char char1 = this.regex.charAt(this.offset++);
        this.chardata = char1;
        if (this.context == 1) {
            int nexttoken = 0;
            Label_0309: {
                switch (char1) {
                    case 92: {
                        nexttoken = 10;
                        if (this.offset >= this.regexlen) {
                            throw this.ex("parser.next.1", this.offset - 1);
                        }
                        this.chardata = this.regex.charAt(this.offset++);
                        break Label_0309;
                    }
                    case 45: {
                        if (this.isSet(512) && this.offset < this.regexlen && this.regex.charAt(this.offset) == '[') {
                            ++this.offset;
                            nexttoken = 24;
                            break Label_0309;
                        }
                        nexttoken = 0;
                        break Label_0309;
                    }
                    case 91: {
                        if (!this.isSet(512) && this.offset < this.regexlen && this.regex.charAt(this.offset) == ':') {
                            ++this.offset;
                            nexttoken = 20;
                            break Label_0309;
                        }
                        break;
                    }
                }
                if (REUtil.isHighSurrogate(char1) && this.offset < this.regexlen) {
                    final char char2 = this.regex.charAt(this.offset);
                    if (REUtil.isLowSurrogate(char2)) {
                        this.chardata = REUtil.composeFromSurrogates(char1, char2);
                        ++this.offset;
                    }
                }
                nexttoken = 0;
            }
            this.nexttoken = nexttoken;
            return;
        }
        int nexttoken2 = 0;
        Label_0972: {
            switch (char1) {
                case 124: {
                    nexttoken2 = 2;
                    break;
                }
                case 42: {
                    nexttoken2 = 3;
                    break;
                }
                case 43: {
                    nexttoken2 = 4;
                    break;
                }
                case 63: {
                    nexttoken2 = 5;
                    break;
                }
                case 41: {
                    nexttoken2 = 7;
                    break;
                }
                case 46: {
                    nexttoken2 = 8;
                    break;
                }
                case 91: {
                    nexttoken2 = 9;
                    break;
                }
                case 94: {
                    nexttoken2 = 11;
                    break;
                }
                case 36: {
                    nexttoken2 = 12;
                    break;
                }
                case 40: {
                    nexttoken2 = 6;
                    if (this.offset >= this.regexlen) {
                        break;
                    }
                    if (this.regex.charAt(this.offset) != '?') {
                        break;
                    }
                    if (++this.offset >= this.regexlen) {
                        throw this.ex("parser.next.2", this.offset - 1);
                    }
                    char c = this.regex.charAt(this.offset++);
                    switch (c) {
                        case 58: {
                            nexttoken2 = 13;
                            break Label_0972;
                        }
                        case 61: {
                            nexttoken2 = 14;
                            break Label_0972;
                        }
                        case 33: {
                            nexttoken2 = 15;
                            break Label_0972;
                        }
                        case 91: {
                            nexttoken2 = 19;
                            break Label_0972;
                        }
                        case 62: {
                            nexttoken2 = 18;
                            break Label_0972;
                        }
                        case 60: {
                            if (this.offset >= this.regexlen) {
                                throw this.ex("parser.next.2", this.offset - 3);
                            }
                            final char char3 = this.regex.charAt(this.offset++);
                            if (char3 == '=') {
                                nexttoken2 = 16;
                                break Label_0972;
                            }
                            if (char3 == '!') {
                                nexttoken2 = 17;
                                break Label_0972;
                            }
                            throw this.ex("parser.next.3", this.offset - 3);
                        }
                        case 35: {
                            while (this.offset < this.regexlen) {
                                c = this.regex.charAt(this.offset++);
                                if (c == ')') {
                                    break;
                                }
                            }
                            if (c != ')') {
                                throw this.ex("parser.next.4", this.offset - 1);
                            }
                            nexttoken2 = 21;
                            break Label_0972;
                        }
                        default: {
                            if (c == '-' || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) {
                                --this.offset;
                                nexttoken2 = 22;
                                break Label_0972;
                            }
                            if (c == '(') {
                                nexttoken2 = 23;
                                break Label_0972;
                            }
                            throw this.ex("parser.next.2", this.offset - 2);
                        }
                    }
                    break;
                }
                case 92: {
                    nexttoken2 = 10;
                    if (this.offset >= this.regexlen) {
                        throw this.ex("parser.next.1", this.offset - 1);
                    }
                    this.chardata = this.regex.charAt(this.offset++);
                    break;
                }
                default: {
                    nexttoken2 = 0;
                    if (REUtil.isHighSurrogate(this.chardata) && this.offset < this.regexlen) {
                        this.chardata = REUtil.composeFromSurrogates(this.chardata, this.regex.charAt(this.offset++));
                        break;
                    }
                    break;
                }
            }
        }
        this.nexttoken = nexttoken2;
    }
    
    Token parseRegex() throws ParseException {
        Token term = this.parseTerm();
        Token union = null;
        while (this.read() == 2) {
            this.next();
            if (union == null) {
                union = Token.createUnion();
                union.addChild(term);
                term = union;
            }
            term.addChild(this.parseTerm());
        }
        return term;
    }
    
    Token parseTerm() throws ParseException {
        final int read = this.read();
        if (read == 2 || read == 7 || read == 1) {
            return Token.createEmpty();
        }
        Token factor = this.parseFactor();
        Token concat = null;
        int read2;
        while ((read2 = this.read()) != 2 && read2 != 7 && read2 != 1) {
            if (concat == null) {
                concat = Token.createConcat();
                concat.addChild(factor);
                factor = concat;
            }
            concat.addChild(this.parseFactor());
        }
        return factor;
    }
    
    Token processCaret() throws ParseException {
        this.next();
        return Token.token_linebeginning;
    }
    
    Token processDollar() throws ParseException {
        this.next();
        return Token.token_lineend;
    }
    
    Token processLookahead() throws ParseException {
        this.next();
        final Token.ParenToken look = Token.createLook(20, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return look;
    }
    
    Token processNegativelookahead() throws ParseException {
        this.next();
        final Token.ParenToken look = Token.createLook(21, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return look;
    }
    
    Token processLookbehind() throws ParseException {
        this.next();
        final Token.ParenToken look = Token.createLook(22, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return look;
    }
    
    Token processNegativelookbehind() throws ParseException {
        this.next();
        final Token.ParenToken look = Token.createLook(23, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return look;
    }
    
    Token processBacksolidus_A() throws ParseException {
        this.next();
        return Token.token_stringbeginning;
    }
    
    Token processBacksolidus_Z() throws ParseException {
        this.next();
        return Token.token_stringend2;
    }
    
    Token processBacksolidus_z() throws ParseException {
        this.next();
        return Token.token_stringend;
    }
    
    Token processBacksolidus_b() throws ParseException {
        this.next();
        return Token.token_wordedge;
    }
    
    Token processBacksolidus_B() throws ParseException {
        this.next();
        return Token.token_not_wordedge;
    }
    
    Token processBacksolidus_lt() throws ParseException {
        this.next();
        return Token.token_wordbeginning;
    }
    
    Token processBacksolidus_gt() throws ParseException {
        this.next();
        return Token.token_wordend;
    }
    
    Token processStar(final Token token) throws ParseException {
        this.next();
        if (this.read() == 5) {
            this.next();
            return Token.createNGClosure(token);
        }
        return Token.createClosure(token);
    }
    
    Token processPlus(final Token token) throws ParseException {
        this.next();
        if (this.read() == 5) {
            this.next();
            return Token.createConcat(token, Token.createNGClosure(token));
        }
        return Token.createConcat(token, Token.createClosure(token));
    }
    
    Token processQuestion(final Token token) throws ParseException {
        this.next();
        final Token.UnionToken union = Token.createUnion();
        if (this.read() == 5) {
            this.next();
            union.addChild(Token.createEmpty());
            union.addChild(token);
        }
        else {
            union.addChild(token);
            union.addChild(Token.createEmpty());
        }
        return union;
    }
    
    boolean checkQuestion(final int n) {
        return n < this.regexlen && this.regex.charAt(n) == '?';
    }
    
    Token processParen() throws ParseException {
        this.next();
        final Token.ParenToken paren = Token.createParen(this.parseRegex(), this.parennumber++);
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return paren;
    }
    
    Token processParen2() throws ParseException {
        this.next();
        final Token.ParenToken paren = Token.createParen(this.parseRegex(), 0);
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return paren;
    }
    
    Token processCondition() throws ParseException {
        if (this.offset + 1 >= this.regexlen) {
            throw this.ex("parser.factor.4", this.offset);
        }
        int n = -1;
        Token factor = null;
        final char char1 = this.regex.charAt(this.offset);
        if ('1' <= char1 && char1 <= '9') {
            n = char1 - '0';
            this.hasBackReferences = true;
            if (this.references == null) {
                this.references = new Vector();
            }
            this.references.addElement(new ReferencePosition(n, this.offset));
            ++this.offset;
            if (this.regex.charAt(this.offset) != ')') {
                throw this.ex("parser.factor.1", this.offset);
            }
            ++this.offset;
        }
        else {
            if (char1 == '?') {
                --this.offset;
            }
            this.next();
            factor = this.parseFactor();
            switch (factor.type) {
                case 20:
                case 21:
                case 22:
                case 23: {
                    break;
                }
                case 8: {
                    if (this.read() != 7) {
                        throw this.ex("parser.factor.1", this.offset - 1);
                    }
                    break;
                }
                default: {
                    throw this.ex("parser.factor.5", this.offset);
                }
            }
        }
        this.next();
        Token token = this.parseRegex();
        Token child = null;
        if (token.type == 2) {
            if (token.size() != 2) {
                throw this.ex("parser.factor.6", this.offset);
            }
            child = token.getChild(1);
            token = token.getChild(0);
        }
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return Token.createCondition(n, factor, token, child);
    }
    
    Token processModifiers() throws ParseException {
        int n = 0;
        int n2 = 0;
        int n3 = -1;
        while (this.offset < this.regexlen) {
            n3 = this.regex.charAt(this.offset);
            final int optionValue = REUtil.getOptionValue(n3);
            if (optionValue == 0) {
                break;
            }
            n |= optionValue;
            ++this.offset;
        }
        if (this.offset >= this.regexlen) {
            throw this.ex("parser.factor.2", this.offset - 1);
        }
        if (n3 == 45) {
            ++this.offset;
            while (this.offset < this.regexlen) {
                n3 = this.regex.charAt(this.offset);
                final int optionValue2 = REUtil.getOptionValue(n3);
                if (optionValue2 == 0) {
                    break;
                }
                n2 |= optionValue2;
                ++this.offset;
            }
            if (this.offset >= this.regexlen) {
                throw this.ex("parser.factor.2", this.offset - 1);
            }
        }
        Token.ModifierToken modifierToken;
        if (n3 == 58) {
            ++this.offset;
            this.next();
            modifierToken = Token.createModifierGroup(this.parseRegex(), n, n2);
            if (this.read() != 7) {
                throw this.ex("parser.factor.1", this.offset - 1);
            }
            this.next();
        }
        else {
            if (n3 != 41) {
                throw this.ex("parser.factor.3", this.offset);
            }
            ++this.offset;
            this.next();
            modifierToken = Token.createModifierGroup(this.parseRegex(), n, n2);
        }
        return modifierToken;
    }
    
    Token processIndependent() throws ParseException {
        this.next();
        final Token.ParenToken look = Token.createLook(24, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return look;
    }
    
    Token processBacksolidus_c() throws ParseException {
        final char char1;
        if (this.offset >= this.regexlen || ((char1 = this.regex.charAt(this.offset++)) & '\uffe0') != '@') {
            throw this.ex("parser.atom.1", this.offset - 1);
        }
        this.next();
        return Token.createChar(char1 - '@');
    }
    
    Token processBacksolidus_C() throws ParseException {
        throw this.ex("parser.process.1", this.offset);
    }
    
    Token processBacksolidus_i() throws ParseException {
        final Token.CharToken char1 = Token.createChar(105);
        this.next();
        return char1;
    }
    
    Token processBacksolidus_I() throws ParseException {
        throw this.ex("parser.process.1", this.offset);
    }
    
    Token processBacksolidus_g() throws ParseException {
        this.next();
        return Token.getGraphemePattern();
    }
    
    Token processBacksolidus_X() throws ParseException {
        this.next();
        return Token.getCombiningCharacterSequence();
    }
    
    Token processBackreference() throws ParseException {
        final int n = this.chardata - 48;
        final Token.StringToken backReference = Token.createBackReference(n);
        this.hasBackReferences = true;
        if (this.references == null) {
            this.references = new Vector();
        }
        this.references.addElement(new ReferencePosition(n, this.offset - 2));
        this.next();
        return backReference;
    }
    
    Token parseFactor() throws ParseException {
        Label_0211: {
            switch (this.read()) {
                case 11: {
                    return this.processCaret();
                }
                case 12: {
                    return this.processDollar();
                }
                case 14: {
                    return this.processLookahead();
                }
                case 15: {
                    return this.processNegativelookahead();
                }
                case 16: {
                    return this.processLookbehind();
                }
                case 17: {
                    return this.processNegativelookbehind();
                }
                case 21: {
                    this.next();
                    return Token.createEmpty();
                }
                case 10: {
                    switch (this.chardata) {
                        case 65: {
                            return this.processBacksolidus_A();
                        }
                        case 90: {
                            return this.processBacksolidus_Z();
                        }
                        case 122: {
                            return this.processBacksolidus_z();
                        }
                        case 98: {
                            return this.processBacksolidus_b();
                        }
                        case 66: {
                            return this.processBacksolidus_B();
                        }
                        case 60: {
                            return this.processBacksolidus_lt();
                        }
                        case 62: {
                            return this.processBacksolidus_gt();
                        }
                        default: {
                            break Label_0211;
                        }
                    }
                    break;
                }
            }
        }
        Token token = this.parseAtom();
        switch (this.read()) {
            case 3: {
                return this.processStar(token);
            }
            case 4: {
                return this.processPlus(token);
            }
            case 5: {
                return this.processQuestion(token);
            }
            case 0: {
                if (this.chardata != 123) {
                    break;
                }
                int offset = this.offset;
                int min = 0;
                if (offset >= this.regexlen) {
                    break;
                }
                int n = this.regex.charAt(offset++);
                if (n != 44) {
                    if (n < 48) {
                        break;
                    }
                    if (n > 57) {
                        break;
                    }
                }
                if (n != 44) {
                    min = n - 48;
                    while (offset < this.regexlen && (n = this.regex.charAt(offset++)) >= 48 && n <= 57) {
                        min = min * 10 + n - 48;
                        n = -1;
                    }
                    if (n < 0) {
                        break;
                    }
                }
                int max = min;
                if (n == 44) {
                    if (offset >= this.regexlen) {
                        break;
                    }
                    if (((n = this.regex.charAt(offset++)) < 48 || n > 57) && n != 125) {
                        break;
                    }
                    if (n == 125) {
                        max = -1;
                    }
                    else {
                        max = n - 48;
                        while (offset < this.regexlen && (n = this.regex.charAt(offset++)) >= 48 && n <= 57) {
                            max = max * 10 + n - 48;
                            n = -1;
                        }
                        if (n < 0) {
                            break;
                        }
                    }
                }
                if (n != 125) {
                    break;
                }
                if (this.checkQuestion(offset)) {
                    token = Token.createNGClosure(token);
                    this.offset = offset + 1;
                }
                else {
                    token = Token.createClosure(token);
                    this.offset = offset;
                }
                token.setMin(min);
                token.setMax(max);
                this.next();
                break;
            }
        }
        return token;
    }
    
    Token parseAtom() throws ParseException {
        Token token = null;
        switch (this.read()) {
            case 6: {
                return this.processParen();
            }
            case 13: {
                return this.processParen2();
            }
            case 23: {
                return this.processCondition();
            }
            case 22: {
                return this.processModifiers();
            }
            case 18: {
                return this.processIndependent();
            }
            case 8: {
                this.next();
                token = Token.token_dot;
                break;
            }
            case 9: {
                return this.parseCharacterClass(true);
            }
            case 19: {
                return this.parseSetOperations();
            }
            case 10: {
                switch (this.chardata) {
                    case 68:
                    case 83:
                    case 87:
                    case 100:
                    case 115:
                    case 119: {
                        final Token tokenForShorthand = this.getTokenForShorthand(this.chardata);
                        this.next();
                        return tokenForShorthand;
                    }
                    case 101:
                    case 102:
                    case 110:
                    case 114:
                    case 116:
                    case 117:
                    case 118:
                    case 120: {
                        final int decodeEscaped = this.decodeEscaped();
                        if (decodeEscaped < 65536) {
                            token = Token.createChar(decodeEscaped);
                            break;
                        }
                        token = Token.createString(REUtil.decomposeToSurrogates(decodeEscaped));
                        break;
                    }
                    case 99: {
                        return this.processBacksolidus_c();
                    }
                    case 67: {
                        return this.processBacksolidus_C();
                    }
                    case 105: {
                        return this.processBacksolidus_i();
                    }
                    case 73: {
                        return this.processBacksolidus_I();
                    }
                    case 103: {
                        return this.processBacksolidus_g();
                    }
                    case 88: {
                        return this.processBacksolidus_X();
                    }
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57: {
                        return this.processBackreference();
                    }
                    case 80:
                    case 112: {
                        final int offset = this.offset;
                        token = this.processBacksolidus_pP(this.chardata);
                        if (token == null) {
                            throw this.ex("parser.atom.5", offset);
                        }
                        break;
                    }
                    default: {
                        token = Token.createChar(this.chardata);
                        break;
                    }
                }
                this.next();
                break;
            }
            case 0: {
                token = Token.createChar(this.chardata);
                this.next();
                break;
            }
            default: {
                throw this.ex("parser.atom.4", this.offset - 1);
            }
        }
        return token;
    }
    
    protected RangeToken processBacksolidus_pP(final int n) throws ParseException {
        final boolean b = n == 112;
        this.next();
        if (this.read() != 0) {
            throw this.ex("parser.atom.2", this.offset - 1);
        }
        RangeToken rangeToken = null;
        switch (this.chardata) {
            case 76: {
                rangeToken = Token.getRange("L", b);
                break;
            }
            case 77: {
                rangeToken = Token.getRange("M", b);
                break;
            }
            case 78: {
                rangeToken = Token.getRange("N", b);
                break;
            }
            case 90: {
                rangeToken = Token.getRange("Z", b);
                break;
            }
            case 67: {
                rangeToken = Token.getRange("C", b);
                break;
            }
            case 80: {
                rangeToken = Token.getRange("P", b);
                break;
            }
            case 83: {
                rangeToken = Token.getRange("S", b);
                break;
            }
            case 123: {
                final int offset = this.offset;
                final int index = this.regex.indexOf(125, offset);
                if (index < 0) {
                    throw this.ex("parser.atom.3", this.offset);
                }
                this.offset = index + 1;
                rangeToken = Token.getRange(this.regex.substring(offset, index), b);
                break;
            }
            default: {
                throw this.ex("parser.atom.2", this.offset - 1);
            }
        }
        return rangeToken;
    }
    
    int processCIinCharacterClass(final RangeToken rangeToken, final int n) {
        return this.decodeEscaped();
    }
    
    protected RangeToken parseCharacterClass(final boolean b) throws ParseException {
        this.setContext(1);
        this.next();
        boolean b2 = false;
        RangeToken range = null;
        RangeToken rangeToken;
        if (this.read() == 0 && this.chardata == 94) {
            b2 = true;
            this.next();
            if (b) {
                rangeToken = Token.createNRange();
            }
            else {
                range = Token.createRange();
                range.addRange(0, 1114111);
                rangeToken = Token.createRange();
            }
        }
        else {
            rangeToken = Token.createRange();
        }
        int n = 1;
        int read;
        while ((read = this.read()) != 1 && (read != 0 || this.chardata != 93 || n != 0)) {
            n = 0;
            int n2 = this.chardata;
            boolean b3 = false;
            if (read == 10) {
                switch (n2) {
                    case 68:
                    case 83:
                    case 87:
                    case 100:
                    case 115:
                    case 119: {
                        rangeToken.mergeRanges(this.getTokenForShorthand(n2));
                        b3 = true;
                        break;
                    }
                    case 67:
                    case 73:
                    case 99:
                    case 105: {
                        n2 = this.processCIinCharacterClass(rangeToken, n2);
                        if (n2 < 0) {
                            b3 = true;
                            break;
                        }
                        break;
                    }
                    case 80:
                    case 112: {
                        final int offset = this.offset;
                        final RangeToken processBacksolidus_pP = this.processBacksolidus_pP(n2);
                        if (processBacksolidus_pP == null) {
                            throw this.ex("parser.atom.5", offset);
                        }
                        rangeToken.mergeRanges(processBacksolidus_pP);
                        b3 = true;
                        break;
                    }
                    default: {
                        n2 = this.decodeEscaped();
                        break;
                    }
                }
            }
            else if (read == 20) {
                final int index = this.regex.indexOf(58, this.offset);
                if (index < 0) {
                    throw this.ex("parser.cc.1", this.offset);
                }
                boolean b4 = true;
                if (this.regex.charAt(this.offset) == '^') {
                    ++this.offset;
                    b4 = false;
                }
                final RangeToken range2 = Token.getRange(this.regex.substring(this.offset, index), b4);
                if (range2 == null) {
                    throw this.ex("parser.cc.3", this.offset);
                }
                rangeToken.mergeRanges(range2);
                b3 = true;
                if (index + 1 >= this.regexlen || this.regex.charAt(index + 1) != ']') {
                    throw this.ex("parser.cc.1", index);
                }
                this.offset = index + 2;
            }
            this.next();
            if (!b3) {
                if (this.read() != 0 || this.chardata != 45) {
                    rangeToken.addRange(n2, n2);
                }
                else {
                    this.next();
                    final int read2;
                    if ((read2 = this.read()) == 1) {
                        throw this.ex("parser.cc.2", this.offset);
                    }
                    if (read2 == 0 && this.chardata == 93) {
                        rangeToken.addRange(n2, n2);
                        rangeToken.addRange(45, 45);
                    }
                    else {
                        int n3 = this.chardata;
                        if (read2 == 10) {
                            n3 = this.decodeEscaped();
                        }
                        this.next();
                        rangeToken.addRange(n2, n3);
                    }
                }
            }
            if (this.isSet(1024) && this.read() == 0 && this.chardata == 44) {
                this.next();
            }
        }
        if (this.read() == 1) {
            throw this.ex("parser.cc.2", this.offset);
        }
        if (!b && b2) {
            range.subtractRanges(rangeToken);
            rangeToken = range;
        }
        rangeToken.sortRanges();
        rangeToken.compactRanges();
        this.setContext(0);
        this.next();
        return rangeToken;
    }
    
    private RangeToken parseCharacterClass_old(final boolean b) throws ParseException {
        this.setContext(1);
        this.next();
        boolean b2 = false;
        RangeToken range = null;
        RangeToken rangeToken;
        if (this.read() == 0 && this.chardata == 94) {
            b2 = true;
            this.next();
            if (b) {
                rangeToken = Token.createNRange();
            }
            else {
                range = Token.createRange();
                range.addRange(0, 1114111);
                rangeToken = Token.createRange();
            }
        }
        else {
            rangeToken = Token.createRange();
        }
        int read;
        while ((read = this.read()) != 1 && (read != 0 || this.chardata != 93)) {
            int n = this.chardata;
            boolean b3 = false;
            if (read == 10) {
                switch (n) {
                    case 68:
                    case 83:
                    case 87:
                    case 100:
                    case 115:
                    case 119: {
                        rangeToken.mergeRanges(this.getTokenForShorthand(n));
                        b3 = true;
                        break;
                    }
                    case 67:
                    case 73:
                    case 99:
                    case 105: {
                        n = this.processCIinCharacterClass(rangeToken, n);
                        if (n < 0) {
                            b3 = true;
                            break;
                        }
                        break;
                    }
                    case 80:
                    case 112: {
                        final boolean b4 = n == 112;
                        int n2 = this.offset;
                        this.next();
                        if (this.read() != 0) {
                            throw this.ex("parser.atom.2", this.offset - 1);
                        }
                        RangeToken rangeToken2 = null;
                        switch (this.chardata) {
                            case 76: {
                                rangeToken2 = Token.getRange("L", b4);
                                break;
                            }
                            case 77: {
                                rangeToken2 = Token.getRange("M", b4);
                                break;
                            }
                            case 78: {
                                rangeToken2 = Token.getRange("N", b4);
                                break;
                            }
                            case 90: {
                                rangeToken2 = Token.getRange("Z", b4);
                                break;
                            }
                            case 67: {
                                rangeToken2 = Token.getRange("C", b4);
                                break;
                            }
                            case 80: {
                                rangeToken2 = Token.getRange("P", b4);
                                break;
                            }
                            case 83: {
                                rangeToken2 = Token.getRange("S", b4);
                                break;
                            }
                            case 123: {
                                n2 = this.offset;
                                final int offset = this.offset;
                                final int index = this.regex.indexOf(125, offset);
                                if (index < 0) {
                                    throw this.ex("parser.atom.3", this.offset);
                                }
                                this.offset = index + 1;
                                rangeToken2 = Token.getRange(this.regex.substring(offset, index), b4);
                                break;
                            }
                            default: {
                                throw this.ex("parser.atom.2", this.offset - 1);
                            }
                        }
                        if (rangeToken2 == null) {
                            throw this.ex("parser.atom.5", n2);
                        }
                        rangeToken.mergeRanges(rangeToken2);
                        b3 = true;
                        break;
                    }
                    default: {
                        n = this.decodeEscaped();
                        break;
                    }
                }
            }
            else if (read == 20) {
                final int index2 = this.regex.indexOf(58, this.offset);
                if (index2 < 0) {
                    throw this.ex("parser.cc.1", this.offset);
                }
                final RangeToken range2 = Token.getRange(this.regex.substring(this.offset, index2), true);
                if (range2 == null) {
                    throw this.ex("parser.cc.3", this.offset);
                }
                rangeToken.mergeRanges(range2);
                b3 = true;
                if (index2 + 1 >= this.regexlen || this.regex.charAt(index2 + 1) != ']') {
                    throw this.ex("parser.cc.1", index2);
                }
                this.offset = index2 + 2;
            }
            this.next();
            if (!b3) {
                if (this.read() != 0 || this.chardata != 45) {
                    rangeToken.addRange(n, n);
                }
                else {
                    this.next();
                    final int read2;
                    if ((read2 = this.read()) == 1) {
                        throw this.ex("parser.cc.2", this.offset);
                    }
                    int n3 = this.chardata;
                    if (read2 == 10) {
                        n3 = this.decodeEscaped();
                    }
                    this.next();
                    rangeToken.addRange(n, n3);
                }
            }
            if (this.read() == 0 && this.chardata == 44) {
                this.next();
            }
        }
        if (this.read() == 1) {
            throw this.ex("parser.cc.2", this.offset);
        }
        if (!b && b2) {
            range.subtractRanges(rangeToken);
            rangeToken = range;
        }
        rangeToken.sortRanges();
        rangeToken.compactRanges();
        this.setContext(0);
        this.next();
        return rangeToken;
    }
    
    protected RangeToken parseSetOperations() throws ParseException {
        final RangeToken characterClass = this.parseCharacterClass(false);
        int read;
        while ((read = this.read()) != 7) {
            final int chardata = this.chardata;
            if ((read != 0 || (chardata != 45 && chardata != 38)) && read != 4) {
                throw this.ex("parser.ope.2", this.offset - 1);
            }
            this.next();
            if (this.read() != 9) {
                throw this.ex("parser.ope.1", this.offset - 1);
            }
            final RangeToken characterClass2 = this.parseCharacterClass(false);
            if (read == 4) {
                characterClass.mergeRanges(characterClass2);
            }
            else if (chardata == 45) {
                characterClass.subtractRanges(characterClass2);
            }
            else {
                if (chardata != 38) {
                    throw new RuntimeException("ASSERT");
                }
                characterClass.intersectRanges(characterClass2);
            }
        }
        this.next();
        return characterClass;
    }
    
    Token getTokenForShorthand(final int n) {
        Token token = null;
        switch (n) {
            case 100: {
                token = (this.isSet(32) ? Token.getRange("Nd", true) : Token.token_0to9);
                break;
            }
            case 68: {
                token = (this.isSet(32) ? Token.getRange("Nd", false) : Token.token_not_0to9);
                break;
            }
            case 119: {
                token = (this.isSet(32) ? Token.getRange("IsWord", true) : Token.token_wordchars);
                break;
            }
            case 87: {
                token = (this.isSet(32) ? Token.getRange("IsWord", false) : Token.token_not_wordchars);
                break;
            }
            case 115: {
                token = (this.isSet(32) ? Token.getRange("IsSpace", true) : Token.token_spaces);
                break;
            }
            case 83: {
                token = (this.isSet(32) ? Token.getRange("IsSpace", false) : Token.token_not_spaces);
                break;
            }
            default: {
                throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(n, 16));
            }
        }
        return token;
    }
    
    int decodeEscaped() throws ParseException {
        if (this.read() != 10) {
            throw this.ex("parser.next.1", this.offset - 1);
        }
        int chardata = this.chardata;
        Label_0859: {
            switch (chardata) {
                case 101: {
                    chardata = 27;
                    break;
                }
                case 102: {
                    chardata = 12;
                    break;
                }
                case 110: {
                    chardata = 10;
                    break;
                }
                case 114: {
                    chardata = 13;
                    break;
                }
                case 116: {
                    chardata = 9;
                    break;
                }
                case 120: {
                    this.next();
                    if (this.read() != 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    if (this.chardata == 123) {
                        int n = 0;
                        while (true) {
                            this.next();
                            if (this.read() != 0) {
                                throw this.ex("parser.descape.1", this.offset - 1);
                            }
                            final int hexChar;
                            if ((hexChar = hexChar(this.chardata)) < 0) {
                                if (this.chardata != 125) {
                                    throw this.ex("parser.descape.3", this.offset - 1);
                                }
                                if (n > 1114111) {
                                    throw this.ex("parser.descape.4", this.offset - 1);
                                }
                                chardata = n;
                                break Label_0859;
                            }
                            else {
                                if (n > n * 16) {
                                    throw this.ex("parser.descape.2", this.offset - 1);
                                }
                                n = n * 16 + hexChar;
                            }
                        }
                    }
                    else {
                        final int hexChar2;
                        if (this.read() != 0 || (hexChar2 = hexChar(this.chardata)) < 0) {
                            throw this.ex("parser.descape.1", this.offset - 1);
                        }
                        final int n2 = hexChar2;
                        this.next();
                        final int hexChar3;
                        if (this.read() != 0 || (hexChar3 = hexChar(this.chardata)) < 0) {
                            throw this.ex("parser.descape.1", this.offset - 1);
                        }
                        chardata = n2 * 16 + hexChar3;
                        break;
                    }
                    break;
                }
                case 117: {
                    this.next();
                    final int hexChar4;
                    if (this.read() != 0 || (hexChar4 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    final int n3 = hexChar4;
                    this.next();
                    final int hexChar5;
                    if (this.read() != 0 || (hexChar5 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    final int n4 = n3 * 16 + hexChar5;
                    this.next();
                    final int hexChar6;
                    if (this.read() != 0 || (hexChar6 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    final int n5 = n4 * 16 + hexChar6;
                    this.next();
                    final int hexChar7;
                    if (this.read() != 0 || (hexChar7 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    chardata = n5 * 16 + hexChar7;
                    break;
                }
                case 118: {
                    this.next();
                    final int hexChar8;
                    if (this.read() != 0 || (hexChar8 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    final int n6 = hexChar8;
                    this.next();
                    final int hexChar9;
                    if (this.read() != 0 || (hexChar9 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    final int n7 = n6 * 16 + hexChar9;
                    this.next();
                    final int hexChar10;
                    if (this.read() != 0 || (hexChar10 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    final int n8 = n7 * 16 + hexChar10;
                    this.next();
                    final int hexChar11;
                    if (this.read() != 0 || (hexChar11 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    final int n9 = n8 * 16 + hexChar11;
                    this.next();
                    final int hexChar12;
                    if (this.read() != 0 || (hexChar12 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    final int n10 = n9 * 16 + hexChar12;
                    this.next();
                    final int hexChar13;
                    if (this.read() != 0 || (hexChar13 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    final int n11 = n10 * 16 + hexChar13;
                    if (n11 > 1114111) {
                        throw this.ex("parser.descappe.4", this.offset - 1);
                    }
                    chardata = n11;
                    break;
                }
                case 65:
                case 90:
                case 122: {
                    throw this.ex("parser.descape.5", this.offset - 2);
                }
            }
        }
        return chardata;
    }
    
    private static final int hexChar(final int n) {
        if (n < 48) {
            return -1;
        }
        if (n > 102) {
            return -1;
        }
        if (n <= 57) {
            return n - 48;
        }
        if (n < 65) {
            return -1;
        }
        if (n <= 70) {
            return n - 65 + 10;
        }
        if (n < 97) {
            return -1;
        }
        return n - 97 + 10;
    }
    
    static class ReferencePosition
    {
        int refNumber;
        int position;
        
        ReferencePosition(final int refNumber, final int position) {
            this.refNumber = refNumber;
            this.position = position;
        }
    }
}
