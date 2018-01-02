// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xpath.regex;

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
            this.resources = ResourceBundle.getBundle("org.apache.xerces.impl.xpath.regex.message", locale);
        }
        catch (MissingResourceException mre) {
            throw new RuntimeException("Installation Problem???  Couldn't load messages: " + mre.getMessage());
        }
    }
    
    final ParseException ex(final String key, final int loc) {
        return new ParseException(this.resources.getString(key), loc);
    }
    
    private final boolean isSet(final int flag) {
        return (this.options & flag) == flag;
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
        final Token ret = this.parseRegex();
        if (this.offset != this.regexlen) {
            throw this.ex("parser.parse.1", this.offset);
        }
        if (this.references != null) {
            for (int i = 0; i < this.references.size(); ++i) {
                final ReferencePosition position = this.references.elementAt(i);
                if (this.parennumber <= position.refNumber) {
                    throw this.ex("parser.parse.2", position.position);
                }
            }
            this.references.removeAllElements();
        }
        return ret;
    }
    
    protected final void setContext(final int con) {
        this.context = con;
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
        int ch = this.regex.charAt(this.offset++);
        this.chardata = ch;
        int ret = 0;
        if (this.context == 1) {
            Label_0309: {
                switch (ch) {
                    case 92: {
                        ret = 10;
                        if (this.offset >= this.regexlen) {
                            throw this.ex("parser.next.1", this.offset - 1);
                        }
                        this.chardata = this.regex.charAt(this.offset++);
                        break Label_0309;
                    }
                    case 45: {
                        if (this.isSet(512) && this.offset < this.regexlen && this.regex.charAt(this.offset) == '[') {
                            ++this.offset;
                            ret = 24;
                            break Label_0309;
                        }
                        ret = 0;
                        break Label_0309;
                    }
                    case 91: {
                        if (!this.isSet(512) && this.offset < this.regexlen && this.regex.charAt(this.offset) == ':') {
                            ++this.offset;
                            ret = 20;
                            break Label_0309;
                        }
                        break;
                    }
                }
                if (REUtil.isHighSurrogate(ch) && this.offset < this.regexlen) {
                    final int low = this.regex.charAt(this.offset);
                    if (REUtil.isLowSurrogate(low)) {
                        this.chardata = REUtil.composeFromSurrogates(ch, low);
                        ++this.offset;
                    }
                }
                ret = 0;
            }
            this.nexttoken = ret;
            return;
        }
        Label_0922: {
            switch (ch) {
                case 124: {
                    ret = 2;
                    break;
                }
                case 42: {
                    ret = 3;
                    break;
                }
                case 43: {
                    ret = 4;
                    break;
                }
                case 63: {
                    ret = 5;
                    break;
                }
                case 41: {
                    ret = 7;
                    break;
                }
                case 46: {
                    ret = 8;
                    break;
                }
                case 91: {
                    ret = 9;
                    break;
                }
                case 94: {
                    ret = 11;
                    break;
                }
                case 36: {
                    ret = 12;
                    break;
                }
                case 40: {
                    ret = 6;
                    if (this.offset >= this.regexlen) {
                        break;
                    }
                    if (this.regex.charAt(this.offset) != '?') {
                        break;
                    }
                    if (++this.offset >= this.regexlen) {
                        throw this.ex("parser.next.2", this.offset - 1);
                    }
                    ch = this.regex.charAt(this.offset++);
                    switch (ch) {
                        case 58: {
                            ret = 13;
                            break Label_0922;
                        }
                        case 61: {
                            ret = 14;
                            break Label_0922;
                        }
                        case 33: {
                            ret = 15;
                            break Label_0922;
                        }
                        case 91: {
                            ret = 19;
                            break Label_0922;
                        }
                        case 62: {
                            ret = 18;
                            break Label_0922;
                        }
                        case 60: {
                            if (this.offset >= this.regexlen) {
                                throw this.ex("parser.next.2", this.offset - 3);
                            }
                            ch = this.regex.charAt(this.offset++);
                            if (ch == 61) {
                                ret = 16;
                                break Label_0922;
                            }
                            if (ch == 33) {
                                ret = 17;
                                break Label_0922;
                            }
                            throw this.ex("parser.next.3", this.offset - 3);
                        }
                        case 35: {
                            while (this.offset < this.regexlen) {
                                ch = this.regex.charAt(this.offset++);
                                if (ch == 41) {
                                    break;
                                }
                            }
                            if (ch != 41) {
                                throw this.ex("parser.next.4", this.offset - 1);
                            }
                            ret = 21;
                            break Label_0922;
                        }
                        default: {
                            if (ch == 45 || (97 <= ch && ch <= 122) || (65 <= ch && ch <= 90)) {
                                --this.offset;
                                ret = 22;
                                break Label_0922;
                            }
                            if (ch == 40) {
                                ret = 23;
                                break Label_0922;
                            }
                            throw this.ex("parser.next.2", this.offset - 2);
                        }
                    }
                    break;
                }
                case 92: {
                    ret = 10;
                    if (this.offset >= this.regexlen) {
                        throw this.ex("parser.next.1", this.offset - 1);
                    }
                    this.chardata = this.regex.charAt(this.offset++);
                    break;
                }
                default: {
                    ret = 0;
                    break;
                }
            }
        }
        this.nexttoken = ret;
    }
    
    Token parseRegex() throws ParseException {
        Token tok = this.parseTerm();
        Token parent = null;
        while (this.read() == 2) {
            this.next();
            if (parent == null) {
                parent = Token.createUnion();
                parent.addChild(tok);
                tok = parent;
            }
            tok.addChild(this.parseTerm());
        }
        return tok;
    }
    
    Token parseTerm() throws ParseException {
        int ch = this.read();
        if (ch == 2 || ch == 7 || ch == 1) {
            return Token.createEmpty();
        }
        Token tok = this.parseFactor();
        Token concat = null;
        while ((ch = this.read()) != 2 && ch != 7 && ch != 1) {
            if (concat == null) {
                concat = Token.createConcat();
                concat.addChild(tok);
                tok = concat;
            }
            concat.addChild(this.parseFactor());
        }
        return tok;
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
        final Token tok = Token.createLook(20, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }
    
    Token processNegativelookahead() throws ParseException {
        this.next();
        final Token tok = Token.createLook(21, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }
    
    Token processLookbehind() throws ParseException {
        this.next();
        final Token tok = Token.createLook(22, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }
    
    Token processNegativelookbehind() throws ParseException {
        this.next();
        final Token tok = Token.createLook(23, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
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
    
    Token processStar(final Token tok) throws ParseException {
        this.next();
        if (this.read() == 5) {
            this.next();
            return Token.createNGClosure(tok);
        }
        return Token.createClosure(tok);
    }
    
    Token processPlus(final Token tok) throws ParseException {
        this.next();
        if (this.read() == 5) {
            this.next();
            return Token.createConcat(tok, Token.createNGClosure(tok));
        }
        return Token.createConcat(tok, Token.createClosure(tok));
    }
    
    Token processQuestion(final Token tok) throws ParseException {
        this.next();
        final Token par = Token.createUnion();
        if (this.read() == 5) {
            this.next();
            par.addChild(Token.createEmpty());
            par.addChild(tok);
        }
        else {
            par.addChild(tok);
            par.addChild(Token.createEmpty());
        }
        return par;
    }
    
    boolean checkQuestion(final int off) {
        return off < this.regexlen && this.regex.charAt(off) == '?';
    }
    
    Token processParen() throws ParseException {
        this.next();
        final int p = this.parennumber++;
        final Token tok = Token.createParen(this.parseRegex(), p);
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }
    
    Token processParen2() throws ParseException {
        this.next();
        final Token tok = Token.createParen(this.parseRegex(), 0);
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }
    
    Token processCondition() throws ParseException {
        if (this.offset + 1 >= this.regexlen) {
            throw this.ex("parser.factor.4", this.offset);
        }
        int refno = -1;
        Token condition = null;
        final int ch = this.regex.charAt(this.offset);
        if (49 <= ch && ch <= 57) {
            refno = ch - 48;
            this.hasBackReferences = true;
            if (this.references == null) {
                this.references = new Vector();
            }
            this.references.addElement(new ReferencePosition(refno, this.offset));
            ++this.offset;
            if (this.regex.charAt(this.offset) != ')') {
                throw this.ex("parser.factor.1", this.offset);
            }
            ++this.offset;
        }
        else {
            if (ch == 63) {
                --this.offset;
            }
            this.next();
            condition = this.parseFactor();
            switch (condition.type) {
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
        Token yesPattern = this.parseRegex();
        Token noPattern = null;
        if (yesPattern.type == 2) {
            if (yesPattern.size() != 2) {
                throw this.ex("parser.factor.6", this.offset);
            }
            noPattern = yesPattern.getChild(1);
            yesPattern = yesPattern.getChild(0);
        }
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return Token.createCondition(refno, condition, yesPattern, noPattern);
    }
    
    Token processModifiers() throws ParseException {
        int add = 0;
        int mask = 0;
        int ch = -1;
        while (this.offset < this.regexlen) {
            ch = this.regex.charAt(this.offset);
            final int v = REUtil.getOptionValue(ch);
            if (v == 0) {
                break;
            }
            add |= v;
            ++this.offset;
        }
        if (this.offset >= this.regexlen) {
            throw this.ex("parser.factor.2", this.offset - 1);
        }
        if (ch == 45) {
            ++this.offset;
            while (this.offset < this.regexlen) {
                ch = this.regex.charAt(this.offset);
                final int v = REUtil.getOptionValue(ch);
                if (v == 0) {
                    break;
                }
                mask |= v;
                ++this.offset;
            }
            if (this.offset >= this.regexlen) {
                throw this.ex("parser.factor.2", this.offset - 1);
            }
        }
        Token tok;
        if (ch == 58) {
            ++this.offset;
            this.next();
            tok = Token.createModifierGroup(this.parseRegex(), add, mask);
            if (this.read() != 7) {
                throw this.ex("parser.factor.1", this.offset - 1);
            }
            this.next();
        }
        else {
            if (ch != 41) {
                throw this.ex("parser.factor.3", this.offset);
            }
            ++this.offset;
            this.next();
            tok = Token.createModifierGroup(this.parseRegex(), add, mask);
        }
        return tok;
    }
    
    Token processIndependent() throws ParseException {
        this.next();
        final Token tok = Token.createLook(24, this.parseRegex());
        if (this.read() != 7) {
            throw this.ex("parser.factor.1", this.offset - 1);
        }
        this.next();
        return tok;
    }
    
    Token processBacksolidus_c() throws ParseException {
        final int ch2;
        if (this.offset >= this.regexlen || ((ch2 = this.regex.charAt(this.offset++)) & 0xFFE0) != 0x40) {
            throw this.ex("parser.atom.1", this.offset - 1);
        }
        this.next();
        return Token.createChar(ch2 - 64);
    }
    
    Token processBacksolidus_C() throws ParseException {
        throw this.ex("parser.process.1", this.offset);
    }
    
    Token processBacksolidus_i() throws ParseException {
        final Token tok = Token.createChar(105);
        this.next();
        return tok;
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
        final int refnum = this.chardata - 48;
        final Token tok = Token.createBackReference(refnum);
        this.hasBackReferences = true;
        if (this.references == null) {
            this.references = new Vector();
        }
        this.references.addElement(new ReferencePosition(refnum, this.offset - 2));
        this.next();
        return tok;
    }
    
    Token parseFactor() throws ParseException {
        int ch = this.read();
        Label_0211: {
            switch (ch) {
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
        Token tok = this.parseAtom();
        ch = this.read();
        switch (ch) {
            case 3: {
                return this.processStar(tok);
            }
            case 4: {
                return this.processPlus(tok);
            }
            case 5: {
                return this.processQuestion(tok);
            }
            case 0: {
                if (this.chardata != 123) {
                    break;
                }
                int off = this.offset;
                int min = 0;
                int max = -1;
                if (off >= this.regexlen) {
                    break;
                }
                ch = this.regex.charAt(off++);
                if (ch != 44) {
                    if (ch < 48) {
                        break;
                    }
                    if (ch > 57) {
                        break;
                    }
                }
                if (ch != 44) {
                    min = ch - 48;
                    while (off < this.regexlen && (ch = this.regex.charAt(off++)) >= 48 && ch <= 57) {
                        min = min * 10 + ch - 48;
                        ch = -1;
                    }
                    if (ch < 0) {
                        break;
                    }
                }
                max = min;
                if (ch == 44) {
                    if (off >= this.regexlen) {
                        break;
                    }
                    if (((ch = this.regex.charAt(off++)) < 48 || ch > 57) && ch != 125) {
                        break;
                    }
                    if (ch == 125) {
                        max = -1;
                    }
                    else {
                        max = ch - 48;
                        while (off < this.regexlen && (ch = this.regex.charAt(off++)) >= 48 && ch <= 57) {
                            max = max * 10 + ch - 48;
                            ch = -1;
                        }
                        if (ch < 0) {
                            break;
                        }
                    }
                }
                if (ch != 125) {
                    break;
                }
                if (this.checkQuestion(off)) {
                    tok = Token.createNGClosure(tok);
                    this.offset = off + 1;
                }
                else {
                    tok = Token.createClosure(tok);
                    this.offset = off;
                }
                tok.setMin(min);
                tok.setMax(max);
                this.next();
                break;
            }
        }
        return tok;
    }
    
    Token parseAtom() throws ParseException {
        final int ch = this.read();
        Token tok = null;
        switch (ch) {
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
                tok = Token.token_dot;
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
                        tok = this.getTokenForShorthand(this.chardata);
                        this.next();
                        return tok;
                    }
                    case 101:
                    case 102:
                    case 110:
                    case 114:
                    case 116:
                    case 117:
                    case 118:
                    case 120: {
                        final int ch2 = this.decodeEscaped();
                        if (ch2 < 65536) {
                            tok = Token.createChar(ch2);
                        }
                        else {
                            tok = Token.createString(REUtil.decomposeToSurrogates(ch2));
                        }
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
                        final int pstart = this.offset;
                        tok = this.processBacksolidus_pP(this.chardata);
                        if (tok == null) {
                            throw this.ex("parser.atom.5", pstart);
                        }
                        break;
                    }
                    default: {
                        tok = Token.createChar(this.chardata);
                        break;
                    }
                }
                this.next();
                break;
            }
            case 0: {
                tok = Token.createChar(this.chardata);
                final int high = this.chardata;
                this.next();
                if (REUtil.isHighSurrogate(high) && this.read() == 0 && REUtil.isLowSurrogate(this.chardata)) {
                    final char[] sur = { (char)high, (char)this.chardata };
                    tok = Token.createParen(Token.createString(new String(sur)), 0);
                    this.next();
                    break;
                }
                break;
            }
            default: {
                throw this.ex("parser.atom.4", this.offset - 1);
            }
        }
        return tok;
    }
    
    protected RangeToken processBacksolidus_pP(final int c) throws ParseException {
        final boolean positive = c == 112;
        this.next();
        if (this.read() != 0) {
            throw this.ex("parser.atom.2", this.offset - 1);
        }
        RangeToken tok = null;
        switch (this.chardata) {
            case 76: {
                tok = Token.getRange("L", positive);
                break;
            }
            case 77: {
                tok = Token.getRange("M", positive);
                break;
            }
            case 78: {
                tok = Token.getRange("N", positive);
                break;
            }
            case 90: {
                tok = Token.getRange("Z", positive);
                break;
            }
            case 67: {
                tok = Token.getRange("C", positive);
                break;
            }
            case 80: {
                tok = Token.getRange("P", positive);
                break;
            }
            case 83: {
                tok = Token.getRange("S", positive);
                break;
            }
            case 123: {
                final int namestart = this.offset;
                final int nameend = this.regex.indexOf(125, namestart);
                if (nameend < 0) {
                    throw this.ex("parser.atom.3", this.offset);
                }
                final String pname = this.regex.substring(namestart, nameend);
                this.offset = nameend + 1;
                tok = Token.getRange(pname, positive, this.isSet(512));
                break;
            }
            default: {
                throw this.ex("parser.atom.2", this.offset - 1);
            }
        }
        return tok;
    }
    
    int processCIinCharacterClass(final RangeToken tok, final int c) {
        return this.decodeEscaped();
    }
    
    protected RangeToken parseCharacterClass(final boolean useNrange) throws ParseException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: iconst_1       
        //     2: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.setContext:(I)V
        //     5: aload_0         /* this */
        //     6: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //     9: iconst_0       
        //    10: istore_2        /* nrange */
        //    11: aconst_null    
        //    12: astore_3        /* base */
        //    13: aload_0         /* this */
        //    14: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //    17: ifne            66
        //    20: aload_0         /* this */
        //    21: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //    24: bipush          94
        //    26: if_icmpne       66
        //    29: iconst_1       
        //    30: istore_2        /* nrange */
        //    31: aload_0         /* this */
        //    32: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //    35: iload_1         /* useNrange */
        //    36: ifeq            47
        //    39: invokestatic    org/apache/xerces/impl/xpath/regex/Token.createNRange:()Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //    42: astore          tok
        //    44: goto            71
        //    47: invokestatic    org/apache/xerces/impl/xpath/regex/Token.createRange:()Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //    50: astore_3        /* base */
        //    51: aload_3         /* base */
        //    52: iconst_0       
        //    53: ldc             1114111
        //    55: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //    58: invokestatic    org/apache/xerces/impl/xpath/regex/Token.createRange:()Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //    61: astore          tok
        //    63: goto            71
        //    66: invokestatic    org/apache/xerces/impl/xpath/regex/Token.createRange:()Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //    69: astore          tok
        //    71: iconst_1       
        //    72: istore          firstloop
        //    74: goto            647
        //    77: iload           5
        //    79: ifne            99
        //    82: aload_0         /* this */
        //    83: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //    86: bipush          93
        //    88: if_icmpne       99
        //    91: iload           firstloop
        //    93: ifne            99
        //    96: goto            658
        //    99: iconst_0       
        //   100: istore          firstloop
        //   102: aload_0         /* this */
        //   103: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //   106: istore          c
        //   108: iconst_0       
        //   109: istore          end
        //   111: iload           5
        //   113: bipush          10
        //   115: if_icmpne       316
        //   118: iload           c
        //   120: lookupswitch {
        //               67: 245
        //               68: 228
        //               73: 245
        //               80: 266
        //               83: 228
        //               87: 228
        //               99: 245
        //              100: 228
        //              105: 245
        //              112: 266
        //              115: 228
        //              119: 228
        //          default: 307
        //        }
        //   228: aload           tok
        //   230: aload_0         /* this */
        //   231: iload           c
        //   233: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.getTokenForShorthand:(I)Lorg/apache/xerces/impl/xpath/regex/Token;
        //   236: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.mergeRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //   239: iconst_1       
        //   240: istore          end
        //   242: goto            313
        //   245: aload_0         /* this */
        //   246: aload           tok
        //   248: iload           c
        //   250: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.processCIinCharacterClass:(Lorg/apache/xerces/impl/xpath/regex/RangeToken;I)I
        //   253: istore          c
        //   255: iload           c
        //   257: ifge            313
        //   260: iconst_1       
        //   261: istore          end
        //   263: goto            313
        //   266: aload_0         /* this */
        //   267: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   270: istore          pstart
        //   272: aload_0         /* this */
        //   273: iload           c
        //   275: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.processBacksolidus_pP:(I)Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //   278: astore          tok2
        //   280: aload           tok2
        //   282: ifnonnull       294
        //   285: aload_0         /* this */
        //   286: ldc             "parser.atom.5"
        //   288: iload           pstart
        //   290: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   293: athrow         
        //   294: aload           tok
        //   296: aload           tok2
        //   298: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.mergeRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //   301: iconst_1       
        //   302: istore          end
        //   304: goto            313
        //   307: aload_0         /* this */
        //   308: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.decodeEscaped:()I
        //   311: istore          c
        //   313: goto            487
        //   316: iload           5
        //   318: bipush          20
        //   320: if_icmpne       487
        //   323: aload_0         /* this */
        //   324: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.regex:Ljava/lang/String;
        //   327: bipush          58
        //   329: aload_0         /* this */
        //   330: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   333: invokevirtual   java/lang/String.indexOf:(II)I
        //   336: istore          nameend
        //   338: iload           nameend
        //   340: ifge            354
        //   343: aload_0         /* this */
        //   344: ldc             "parser.cc.1"
        //   346: aload_0         /* this */
        //   347: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   350: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   353: athrow         
        //   354: iconst_1       
        //   355: istore          positive
        //   357: aload_0         /* this */
        //   358: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.regex:Ljava/lang/String;
        //   361: aload_0         /* this */
        //   362: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   365: invokevirtual   java/lang/String.charAt:(I)C
        //   368: bipush          94
        //   370: if_icmpne       386
        //   373: aload_0         /* this */
        //   374: dup            
        //   375: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   378: iconst_1       
        //   379: iadd           
        //   380: putfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   383: iconst_0       
        //   384: istore          positive
        //   386: aload_0         /* this */
        //   387: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.regex:Ljava/lang/String;
        //   390: aload_0         /* this */
        //   391: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   394: iload           nameend
        //   396: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   399: astore          name
        //   401: aload           name
        //   403: iload           positive
        //   405: aload_0         /* this */
        //   406: sipush          512
        //   409: invokespecial   org/apache/xerces/impl/xpath/regex/RegexParser.isSet:(I)Z
        //   412: invokestatic    org/apache/xerces/impl/xpath/regex/Token.getRange:(Ljava/lang/String;ZZ)Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //   415: astore          range
        //   417: aload           range
        //   419: ifnonnull       433
        //   422: aload_0         /* this */
        //   423: ldc             "parser.cc.3"
        //   425: aload_0         /* this */
        //   426: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   429: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   432: athrow         
        //   433: aload           tok
        //   435: aload           range
        //   437: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.mergeRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //   440: iconst_1       
        //   441: istore          end
        //   443: iload           nameend
        //   445: iconst_1       
        //   446: iadd           
        //   447: aload_0         /* this */
        //   448: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.regexlen:I
        //   451: if_icmpge       470
        //   454: aload_0         /* this */
        //   455: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.regex:Ljava/lang/String;
        //   458: iload           nameend
        //   460: iconst_1       
        //   461: iadd           
        //   462: invokevirtual   java/lang/String.charAt:(I)C
        //   465: bipush          93
        //   467: if_icmpeq       479
        //   470: aload_0         /* this */
        //   471: ldc             "parser.cc.1"
        //   473: iload           nameend
        //   475: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   478: athrow         
        //   479: aload_0         /* this */
        //   480: iload           nameend
        //   482: iconst_2       
        //   483: iadd           
        //   484: putfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   487: aload_0         /* this */
        //   488: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //   491: iload           end
        //   493: ifne            617
        //   496: aload_0         /* this */
        //   497: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   500: ifne            512
        //   503: aload_0         /* this */
        //   504: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //   507: bipush          45
        //   509: if_icmpeq       524
        //   512: aload           tok
        //   514: iload           c
        //   516: iload           c
        //   518: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //   521: goto            617
        //   524: aload_0         /* this */
        //   525: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //   528: aload_0         /* this */
        //   529: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   532: dup            
        //   533: istore          type
        //   535: iconst_1       
        //   536: if_icmpne       550
        //   539: aload_0         /* this */
        //   540: ldc             "parser.cc.2"
        //   542: aload_0         /* this */
        //   543: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   546: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   549: athrow         
        //   550: iload           type
        //   552: ifne            585
        //   555: aload_0         /* this */
        //   556: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //   559: bipush          93
        //   561: if_icmpne       585
        //   564: aload           tok
        //   566: iload           c
        //   568: iload           c
        //   570: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //   573: aload           tok
        //   575: bipush          45
        //   577: bipush          45
        //   579: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //   582: goto            617
        //   585: aload_0         /* this */
        //   586: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //   589: istore          rangeend
        //   591: iload           type
        //   593: bipush          10
        //   595: if_icmpne       604
        //   598: aload_0         /* this */
        //   599: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.decodeEscaped:()I
        //   602: istore          rangeend
        //   604: aload_0         /* this */
        //   605: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //   608: aload           tok
        //   610: iload           c
        //   612: iload           rangeend
        //   614: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.addRange:(II)V
        //   617: aload_0         /* this */
        //   618: sipush          1024
        //   621: invokespecial   org/apache/xerces/impl/xpath/regex/RegexParser.isSet:(I)Z
        //   624: ifeq            647
        //   627: aload_0         /* this */
        //   628: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   631: ifne            647
        //   634: aload_0         /* this */
        //   635: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //   638: bipush          44
        //   640: if_icmpne       647
        //   643: aload_0         /* this */
        //   644: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //   647: aload_0         /* this */
        //   648: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   651: dup            
        //   652: istore          type
        //   654: iconst_1       
        //   655: if_icmpne       77
        //   658: aload_0         /* this */
        //   659: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   662: iconst_1       
        //   663: if_icmpne       677
        //   666: aload_0         /* this */
        //   667: ldc             "parser.cc.2"
        //   669: aload_0         /* this */
        //   670: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   673: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   676: athrow         
        //   677: iload_1         /* useNrange */
        //   678: ifne            694
        //   681: iload_2         /* nrange */
        //   682: ifeq            694
        //   685: aload_3         /* base */
        //   686: aload           tok
        //   688: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.subtractRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //   691: aload_3         /* base */
        //   692: astore          tok
        //   694: aload           tok
        //   696: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.sortRanges:()V
        //   699: aload           tok
        //   701: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.compactRanges:()V
        //   704: aload_0         /* this */
        //   705: iconst_0       
        //   706: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.setContext:(I)V
        //   709: aload_0         /* this */
        //   710: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //   713: aload           tok
        //   715: areturn        
        //    Exceptions:
        //  throws org.apache.xerces.impl.xpath.regex.ParseException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name       Signature
        //  -----  ------  ----  ---------  ------------------------------------------------
        //  0      716     0     this       Lorg/apache/xerces/impl/xpath/regex/RegexParser;
        //  0      716     1     useNrange  Z
        //  11     705     2     nrange     Z
        //  13     703     3     base       Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //  44     672     4     tok        Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //  535    181     5     type       I
        //  74     642     6     firstloop  Z
        //  108    539     7     c          I
        //  111    536     8     end        Z
        //  272    41      9     pstart     I
        //  280    33      10    tok2       Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //  338    149     9     nameend    I
        //  357    130     10    positive   Z
        //  401    86      11    name       Ljava/lang/String;
        //  417    70      12    range      Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //  591    26      9     rangeend   I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected RangeToken parseSetOperations() throws ParseException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: iconst_0       
        //     2: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.parseCharacterClass:(Z)Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //     5: astore_1        /* tok */
        //     6: goto            138
        //     9: aload_0         /* this */
        //    10: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.chardata:I
        //    13: istore_3        /* ch */
        //    14: iload_2        
        //    15: ifne            30
        //    18: iload_3         /* ch */
        //    19: bipush          45
        //    21: if_icmpeq       35
        //    24: iload_3         /* ch */
        //    25: bipush          38
        //    27: if_icmpeq       35
        //    30: iload_2        
        //    31: iconst_4       
        //    32: if_icmpne       125
        //    35: aload_0         /* this */
        //    36: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //    39: aload_0         /* this */
        //    40: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //    43: bipush          9
        //    45: if_icmpeq       61
        //    48: aload_0         /* this */
        //    49: ldc             "parser.ope.1"
        //    51: aload_0         /* this */
        //    52: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //    55: iconst_1       
        //    56: isub           
        //    57: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //    60: athrow         
        //    61: aload_0         /* this */
        //    62: iconst_0       
        //    63: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.parseCharacterClass:(Z)Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //    66: astore          t2
        //    68: iload_2        
        //    69: iconst_4       
        //    70: if_icmpne       82
        //    73: aload_1         /* tok */
        //    74: aload           t2
        //    76: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.mergeRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //    79: goto            122
        //    82: iload_3         /* ch */
        //    83: bipush          45
        //    85: if_icmpne       97
        //    88: aload_1         /* tok */
        //    89: aload           t2
        //    91: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.subtractRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //    94: goto            122
        //    97: iload_3         /* ch */
        //    98: bipush          38
        //   100: if_icmpne       112
        //   103: aload_1         /* tok */
        //   104: aload           t2
        //   106: invokevirtual   org/apache/xerces/impl/xpath/regex/RangeToken.intersectRanges:(Lorg/apache/xerces/impl/xpath/regex/Token;)V
        //   109: goto            122
        //   112: new             Ljava/lang/RuntimeException;
        //   115: dup            
        //   116: ldc             "ASSERT"
        //   118: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   121: athrow         
        //   122: goto            138
        //   125: aload_0         /* this */
        //   126: ldc             "parser.ope.2"
        //   128: aload_0         /* this */
        //   129: getfield        org/apache/xerces/impl/xpath/regex/RegexParser.offset:I
        //   132: iconst_1       
        //   133: isub           
        //   134: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.ex:(Ljava/lang/String;I)Lorg/apache/xerces/impl/xpath/regex/ParseException;
        //   137: athrow         
        //   138: aload_0         /* this */
        //   139: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.read:()I
        //   142: dup            
        //   143: istore_2        /* type */
        //   144: bipush          7
        //   146: if_icmpne       9
        //   149: aload_0         /* this */
        //   150: invokevirtual   org/apache/xerces/impl/xpath/regex/RegexParser.next:()V
        //   153: aload_1         /* tok */
        //   154: areturn        
        //    Exceptions:
        //  throws org.apache.xerces.impl.xpath.regex.ParseException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ------------------------------------------------
        //  0      155     0     this  Lorg/apache/xerces/impl/xpath/regex/RegexParser;
        //  6      149     1     tok   Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        //  144    11      2     type  I
        //  14     124     3     ch    I
        //  68     54      4     t2    Lorg/apache/xerces/impl/xpath/regex/RangeToken;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    Token getTokenForShorthand(final int ch) {
        Token tok = null;
        switch (ch) {
            case 100: {
                tok = (this.isSet(32) ? Token.getRange("Nd", true) : Token.token_0to9);
                break;
            }
            case 68: {
                tok = (this.isSet(32) ? Token.getRange("Nd", false) : Token.token_not_0to9);
                break;
            }
            case 119: {
                tok = (this.isSet(32) ? Token.getRange("IsWord", true) : Token.token_wordchars);
                break;
            }
            case 87: {
                tok = (this.isSet(32) ? Token.getRange("IsWord", false) : Token.token_not_wordchars);
                break;
            }
            case 115: {
                tok = (this.isSet(32) ? Token.getRange("IsSpace", true) : Token.token_spaces);
                break;
            }
            case 83: {
                tok = (this.isSet(32) ? Token.getRange("IsSpace", false) : Token.token_not_spaces);
                break;
            }
            default: {
                throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(ch, 16));
            }
        }
        return tok;
    }
    
    int decodeEscaped() throws ParseException {
        if (this.read() != 10) {
            throw this.ex("parser.next.1", this.offset - 1);
        }
        int c = this.chardata;
        Label_0859: {
            switch (c) {
                case 101: {
                    c = 27;
                    break;
                }
                case 102: {
                    c = 12;
                    break;
                }
                case 110: {
                    c = 10;
                    break;
                }
                case 114: {
                    c = 13;
                    break;
                }
                case 116: {
                    c = 9;
                    break;
                }
                case 120: {
                    this.next();
                    if (this.read() != 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    if (this.chardata == 123) {
                        int v1 = 0;
                        int uv = 0;
                        while (true) {
                            this.next();
                            if (this.read() != 0) {
                                throw this.ex("parser.descape.1", this.offset - 1);
                            }
                            if ((v1 = hexChar(this.chardata)) < 0) {
                                if (this.chardata != 125) {
                                    throw this.ex("parser.descape.3", this.offset - 1);
                                }
                                if (uv > 1114111) {
                                    throw this.ex("parser.descape.4", this.offset - 1);
                                }
                                c = uv;
                                break Label_0859;
                            }
                            else {
                                if (uv > uv * 16) {
                                    throw this.ex("parser.descape.2", this.offset - 1);
                                }
                                uv = uv * 16 + v1;
                            }
                        }
                    }
                    else {
                        int v1 = 0;
                        if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                            throw this.ex("parser.descape.1", this.offset - 1);
                        }
                        int uv = v1;
                        this.next();
                        if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                            throw this.ex("parser.descape.1", this.offset - 1);
                        }
                        uv = (c = uv * 16 + v1);
                        break;
                    }
                    break;
                }
                case 117: {
                    int v1 = 0;
                    this.next();
                    if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    int uv = v1;
                    this.next();
                    if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    uv = uv * 16 + v1;
                    this.next();
                    if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    uv = uv * 16 + v1;
                    this.next();
                    if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    uv = (c = uv * 16 + v1);
                    break;
                }
                case 118: {
                    this.next();
                    int v1;
                    if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    int uv = v1;
                    this.next();
                    if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    uv = uv * 16 + v1;
                    this.next();
                    if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    uv = uv * 16 + v1;
                    this.next();
                    if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    uv = uv * 16 + v1;
                    this.next();
                    if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    uv = uv * 16 + v1;
                    this.next();
                    if (this.read() != 0 || (v1 = hexChar(this.chardata)) < 0) {
                        throw this.ex("parser.descape.1", this.offset - 1);
                    }
                    uv = uv * 16 + v1;
                    if (uv > 1114111) {
                        throw this.ex("parser.descappe.4", this.offset - 1);
                    }
                    c = uv;
                    break;
                }
                case 65:
                case 90:
                case 122: {
                    throw this.ex("parser.descape.5", this.offset - 2);
                }
            }
        }
        return c;
    }
    
    private static final int hexChar(final int ch) {
        if (ch < 48) {
            return -1;
        }
        if (ch > 102) {
            return -1;
        }
        if (ch <= 57) {
            return ch - 48;
        }
        if (ch < 65) {
            return -1;
        }
        if (ch <= 70) {
            return ch - 65 + 10;
        }
        if (ch < 97) {
            return -1;
        }
        return ch - 97 + 10;
    }
    
    static class ReferencePosition
    {
        int refNumber;
        int position;
        
        ReferencePosition(final int n, final int pos) {
            this.refNumber = n;
            this.position = pos;
        }
    }
}
