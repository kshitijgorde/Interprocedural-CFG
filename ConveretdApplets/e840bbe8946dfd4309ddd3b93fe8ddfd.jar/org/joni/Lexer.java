// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.jcodings.exception.CharacterPropertyException;
import org.joni.constants.TokenType;
import org.joni.ast.QuantifierNode;

class Lexer extends ScannerSupport
{
    protected final ScanEnvironment env;
    protected final Syntax syntax;
    protected final Token token;
    private static final int[] send;
    
    protected Lexer(final ScanEnvironment env, final byte[] bytes, final int p, final int end) {
        super(env.enc, bytes, p, end);
        this.token = new Token();
        this.env = env;
        this.syntax = env.syntax;
    }
    
    private int fetchRangeQuantifier() {
        this.mark();
        final boolean synAllow = this.syntax.allowInvalidInterval();
        if (!this.left()) {
            if (synAllow) {
                return 1;
            }
            this.newSyntaxException("end pattern at left brace");
        }
        if (!synAllow) {
            this.c = this.peek();
            if (this.c == 41 || this.c == 40 || this.c == 124) {
                this.newSyntaxException("end pattern at left brace");
            }
        }
        int low = this.scanUnsignedNumber();
        if (low < 0) {
            this.newSyntaxException("too big number for repeat range");
        }
        if (low > 100000) {
            this.newSyntaxException("too big number for repeat range");
        }
        boolean nonLow = false;
        if (this.p == this._p) {
            if (!this.syntax.allowIntervalLowAbbrev()) {
                return this.invalidRangeQuantifier(synAllow);
            }
            low = 0;
            nonLow = true;
        }
        if (!this.left()) {
            return this.invalidRangeQuantifier(synAllow);
        }
        this.fetch();
        int ret = 0;
        int up;
        if (this.c == 44) {
            final int prev = this.p;
            up = this.scanUnsignedNumber();
            if (up < 0) {
                this.newValueException("too big number for repeat range");
            }
            if (up > 100000) {
                this.newValueException("too big number for repeat range");
            }
            if (this.p == prev) {
                if (nonLow) {
                    return this.invalidRangeQuantifier(synAllow);
                }
                up = -1;
            }
        }
        else {
            if (nonLow) {
                return this.invalidRangeQuantifier(synAllow);
            }
            this.unfetch();
            up = low;
            ret = 2;
        }
        if (!this.left()) {
            return this.invalidRangeQuantifier(synAllow);
        }
        this.fetch();
        if (this.syntax.opEscBraceInterval()) {
            if (this.c != this.syntax.metaCharTable.esc) {
                return this.invalidRangeQuantifier(synAllow);
            }
            this.fetch();
        }
        if (this.c != 125) {
            return this.invalidRangeQuantifier(synAllow);
        }
        if (!QuantifierNode.isRepeatInfinite(up) && low > up) {
            this.newValueException("upper is smaller than lower in repeat range");
        }
        this.token.type = TokenType.INTERVAL;
        this.token.setRepeatLower(low);
        this.token.setRepeatUpper(up);
        return ret;
    }
    
    private int invalidRangeQuantifier(final boolean synAllow) {
        if (synAllow) {
            this.restore();
            return 1;
        }
        this.newSyntaxException("invalid repeat range {lower,upper}");
        return 0;
    }
    
    private int fetchEscapedValue() {
        if (!this.left()) {
            this.newSyntaxException("end pattern at escape");
        }
        this.fetch();
        switch (this.c) {
            case 77: {
                if (this.syntax.op2EscCapitalMBarMeta()) {
                    if (!this.left()) {
                        this.newSyntaxException("end pattern at meta");
                    }
                    this.fetch();
                    if (this.c != 45) {
                        this.newSyntaxException("invalid meta-code syntax");
                    }
                    if (!this.left()) {
                        this.newSyntaxException("end pattern at meta");
                    }
                    this.fetch();
                    if (this.c == this.syntax.metaCharTable.esc) {
                        this.c = this.fetchEscapedValue();
                    }
                    this.c = ((this.c & 0xFF) | 0x80);
                    return this.c;
                }
                this.fetchEscapedValueBackSlash();
                return this.c;
            }
            case 67: {
                if (this.syntax.op2EscCapitalCBarControl()) {
                    if (!this.left()) {
                        this.newSyntaxException("end pattern at control");
                    }
                    this.fetch();
                    if (this.c != 45) {
                        this.newSyntaxException("invalid control-code syntax");
                    }
                    this.fetchEscapedValueControl();
                    return this.c;
                }
                this.fetchEscapedValueBackSlash();
                return this.c;
            }
            case 99: {
                if (this.syntax.opEscCControl()) {
                    this.fetchEscapedValueControl();
                    break;
                }
                break;
            }
        }
        this.fetchEscapedValueBackSlash();
        return this.c;
    }
    
    private void fetchEscapedValueBackSlash() {
        this.c = this.env.convertBackslashValue(this.c);
    }
    
    private void fetchEscapedValueControl() {
        if (!this.left()) {
            this.newSyntaxException("end pattern at control");
        }
        this.fetch();
        if (this.c == 63) {
            this.c = 127;
        }
        else {
            if (this.c == this.syntax.metaCharTable.esc) {
                this.c = this.fetchEscapedValue();
            }
            this.c &= 0x9F;
        }
    }
    
    private int nameEndCodePoint(final int start) {
        switch (start) {
            case 60: {
                return 62;
            }
            case 39: {
                return 39;
            }
            default: {
                return 0;
            }
        }
    }
    
    private boolean fetchNameWithLevel(final int startCode, final int[] rbackNum, final int[] rlevel) {
        final int src = this.p;
        boolean existLevel = false;
        int isNum = 0;
        int sign = 1;
        final int endCode = this.nameEndCodePoint(startCode);
        int pnumHead = this.p;
        int nameEnd = this.stop;
        String err = null;
        if (!this.left()) {
            this.newValueException("group name is empty");
        }
        else {
            this.fetch();
            if (this.c == endCode) {
                this.newValueException("group name is empty");
            }
            if (this.enc.isDigit(this.c)) {
                isNum = 1;
            }
            else if (this.c == 45) {
                isNum = 2;
                sign = -1;
                pnumHead = this.p;
            }
            else if (!this.enc.isWord(this.c)) {
                err = "invalid group name <%n>";
            }
        }
        while (this.left()) {
            nameEnd = this.p;
            this.fetch();
            if (this.c == endCode || this.c == 41 || this.c == 43 || this.c == 45) {
                if (isNum == 2) {
                    err = "invalid group name <%n>";
                    break;
                }
                break;
            }
            else if (isNum != 0) {
                if (this.enc.isDigit(this.c)) {
                    isNum = 1;
                }
                else {
                    err = "invalid group name <%n>";
                }
            }
            else {
                if (this.enc.isWord(this.c)) {
                    continue;
                }
                err = "invalid char in group name <%n>";
            }
        }
        boolean isEndCode = false;
        if (err == null && this.c != endCode) {
            if (this.c == 43 || this.c == 45) {
                final int flag = (this.c == 45) ? -1 : 1;
                this.fetch();
                if (!this.enc.isDigit(this.c)) {
                    this.newValueException("invalid group name <%n>", src, this.stop);
                }
                this.unfetch();
                final int level = this.scanUnsignedNumber();
                if (level < 0) {
                    this.newValueException("too big number");
                }
                rlevel[0] = level * flag;
                existLevel = true;
                this.fetch();
                isEndCode = (this.c == endCode);
            }
            if (!isEndCode) {
                err = "invalid group name <%n>";
                nameEnd = this.stop;
            }
        }
        if (err == null) {
            if (isNum != 0) {
                this.mark();
                this.p = pnumHead;
                final int backNum = this.scanUnsignedNumber();
                this.restore();
                if (backNum < 0) {
                    this.newValueException("too big number");
                }
                else if (backNum == 0) {
                    this.newValueException("invalid group name <%n>", src, this.stop);
                }
                rbackNum[0] = backNum * sign;
            }
            this.value = nameEnd;
            return existLevel;
        }
        this.newValueException("invalid group name <%n>", src, nameEnd);
        return false;
    }
    
    private int fetchNameForNamedGroup(final int startCode, final boolean ref) {
        final int src = this.p;
        this.value = 0;
        int isNum = 0;
        int sign = 1;
        final int endCode = this.nameEndCodePoint(startCode);
        int pnumHead = this.p;
        int nameEnd = this.stop;
        String err = null;
        if (!this.left()) {
            this.newValueException("group name is empty");
        }
        else {
            this.fetch();
            if (this.c == endCode) {
                this.newValueException("group name is empty");
            }
            if (this.enc.isDigit(this.c)) {
                if (ref) {
                    isNum = 1;
                }
                else {
                    err = "invalid group name <%n>";
                }
            }
            else if (this.c == 45) {
                if (ref) {
                    isNum = 2;
                    sign = -1;
                    pnumHead = this.p;
                }
                else {
                    err = "invalid group name <%n>";
                }
            }
            else if (!this.enc.isWord(this.c)) {
                err = "invalid char in group name <%n>";
            }
        }
        if (err == null) {
            while (this.left()) {
                nameEnd = this.p;
                this.fetch();
                if (this.c == endCode || this.c == 41) {
                    if (isNum == 2) {
                        err = "invalid group name <%n>";
                        break;
                    }
                    break;
                }
                else if (isNum != 0) {
                    if (this.enc.isDigit(this.c)) {
                        isNum = 1;
                    }
                    else if (!this.enc.isWord(this.c)) {
                        err = "invalid char in group name <%n>";
                    }
                    else {
                        err = "invalid group name <%n>";
                    }
                }
                else {
                    if (this.enc.isWord(this.c)) {
                        continue;
                    }
                    err = "invalid char in group name <%n>";
                }
            }
            if (this.c != endCode) {
                err = "invalid group name <%n>";
                nameEnd = this.stop;
            }
            int backNum = 0;
            if (isNum != 0) {
                this.mark();
                this.p = pnumHead;
                backNum = this.scanUnsignedNumber();
                this.restore();
                if (backNum < 0) {
                    this.newValueException("too big number");
                }
                else if (backNum == 0) {
                    this.newValueException("invalid group name <%n>", src, nameEnd);
                }
                backNum *= sign;
            }
            this.value = nameEnd;
            return backNum;
        }
        while (this.left()) {
            nameEnd = this.p;
            this.fetch();
            if (this.c == endCode || this.c == 41) {
                break;
            }
        }
        if (!this.left()) {
            nameEnd = this.stop;
        }
        this.newValueException(err, src, nameEnd);
        return 0;
    }
    
    private final int fetchNameForNoNamedGroup(final int startCode, final boolean ref) {
        final int src = this.p;
        this.value = 0;
        int isNum = 0;
        int sign = 1;
        final int endCode = this.nameEndCodePoint(startCode);
        int pnumHead = this.p;
        int nameEnd = this.stop;
        String err = null;
        if (!this.left()) {
            this.newValueException("group name is empty");
        }
        else {
            this.fetch();
            if (this.c == endCode) {
                this.newValueException("group name is empty");
            }
            if (this.enc.isDigit(this.c)) {
                isNum = 1;
            }
            else if (this.c == 45) {
                isNum = 2;
                sign = -1;
                pnumHead = this.p;
            }
            else {
                err = "invalid char in group name <%n>";
            }
        }
        while (this.left()) {
            nameEnd = this.p;
            this.fetch();
            if (this.c == endCode) {
                break;
            }
            if (this.c == 41) {
                break;
            }
            if (this.enc.isDigit(this.c)) {
                continue;
            }
            err = "invalid char in group name <%n>";
        }
        if (err == null && this.c != endCode) {
            err = "invalid group name <%n>";
            nameEnd = this.stop;
        }
        if (err == null) {
            this.mark();
            this.p = pnumHead;
            int backNum = this.scanUnsignedNumber();
            this.restore();
            if (backNum < 0) {
                this.newValueException("too big number");
            }
            else if (backNum == 0) {
                this.newValueException("invalid group name <%n>", src, nameEnd);
            }
            backNum *= sign;
            this.value = nameEnd;
            return backNum;
        }
        this.newValueException(err, src, nameEnd);
        return 0;
    }
    
    protected final int fetchName(final int startCode, final boolean ref) {
        return this.fetchNameForNamedGroup(startCode, ref);
    }
    
    private boolean strExistCheckWithEsc(final int[] s, final int n, final int bad) {
        int p = this.p;
        final int to = this.stop;
        boolean inEsc = false;
        int i = 0;
        while (p < to) {
            if (inEsc) {
                inEsc = false;
                p += this.enc.length(this.bytes, p, to);
            }
            else {
                int x = this.enc.mbcToCode(this.bytes, p, to);
                int q = p + this.enc.length(this.bytes, p, to);
                if (x == s[0]) {
                    for (i = 1; i < n && q < to; q += this.enc.length(this.bytes, q, to), ++i) {
                        x = this.enc.mbcToCode(this.bytes, q, to);
                        if (x != s[i]) {
                            break;
                        }
                    }
                    if (i >= n) {
                        return true;
                    }
                    p += this.enc.length(this.bytes, p, to);
                }
                else {
                    x = this.enc.mbcToCode(this.bytes, p, to);
                    if (x == bad) {
                        return false;
                    }
                    if (x == this.syntax.metaCharTable.esc) {
                        inEsc = true;
                    }
                    p = q;
                }
            }
        }
        return false;
    }
    
    protected final TokenType fetchTokenInCC() {
        if (!this.left()) {
            return this.token.type = TokenType.EOT;
        }
        this.fetch();
        this.token.type = TokenType.CHAR;
        this.token.base = 0;
        this.token.setC(this.c);
        this.token.escaped = false;
        if (this.c == 93) {
            this.token.type = TokenType.CC_CLOSE;
        }
        else if (this.c == 45) {
            this.token.type = TokenType.CC_RANGE;
        }
        else if (this.c == this.syntax.metaCharTable.esc) {
            if (!this.syntax.backSlashEscapeInCC()) {
                return this.token.type;
            }
            if (!this.left()) {
                this.newSyntaxException("end pattern at escape");
            }
            this.fetch();
            this.token.escaped = true;
            this.token.setC(this.c);
            switch (this.c) {
                case 119: {
                    this.token.type = TokenType.CHAR_TYPE;
                    this.token.setPropCType(12);
                    this.token.setPropNot(false);
                    break;
                }
                case 87: {
                    this.token.type = TokenType.CHAR_TYPE;
                    this.token.setPropCType(12);
                    this.token.setPropNot(true);
                    break;
                }
                case 100: {
                    this.token.type = TokenType.CHAR_TYPE;
                    this.token.setPropCType(4);
                    this.token.setPropNot(false);
                    break;
                }
                case 68: {
                    this.token.type = TokenType.CHAR_TYPE;
                    this.token.setPropCType(4);
                    this.token.setPropNot(true);
                    break;
                }
                case 115: {
                    this.token.type = TokenType.CHAR_TYPE;
                    this.token.setPropCType(9);
                    this.token.setPropNot(false);
                    break;
                }
                case 83: {
                    this.token.type = TokenType.CHAR_TYPE;
                    this.token.setPropCType(9);
                    this.token.setPropNot(true);
                    break;
                }
                case 104: {
                    if (!this.syntax.op2EscHXDigit()) {
                        break;
                    }
                    this.token.type = TokenType.CHAR_TYPE;
                    this.token.setPropCType(11);
                    this.token.setPropNot(false);
                    break;
                }
                case 72: {
                    if (!this.syntax.op2EscHXDigit()) {
                        break;
                    }
                    this.token.type = TokenType.CHAR_TYPE;
                    this.token.setPropCType(11);
                    this.token.setPropNot(true);
                    break;
                }
                case 80:
                case 112: {
                    int c2 = this.peek();
                    if (c2 != 123 || !this.syntax.op2EscPBraceCharProperty()) {
                        break;
                    }
                    this.inc();
                    this.token.type = TokenType.CHAR_PROPERTY;
                    this.token.setPropNot(this.c == 80);
                    if (!this.syntax.op2EscPBraceCircumflexNot()) {
                        break;
                    }
                    c2 = this.fetchTo();
                    if (c2 == 94) {
                        this.token.setPropNot(!this.token.getPropNot());
                        break;
                    }
                    this.unfetch();
                    break;
                }
                case 120: {
                    if (!this.left()) {
                        break;
                    }
                    final int last = this.p;
                    if (this.peekIs(123) && this.syntax.opEscXBraceHex8()) {
                        this.inc();
                        final int num = this.scanUnsignedHexadecimalNumber(8);
                        if (num < 0) {
                            this.newValueException("too big wide-char value");
                        }
                        if (this.left()) {
                            final int c2 = this.peek();
                            if (this.enc.isXDigit(c2)) {
                                this.newValueException("too long wide-char value");
                            }
                        }
                        if (this.p > last + this.enc.length(this.bytes, last, this.stop) && this.left() && this.peekIs(125)) {
                            this.inc();
                            this.token.type = TokenType.CODE_POINT;
                            this.token.base = 16;
                            this.token.setCode(num);
                        }
                        else {
                            this.p = last;
                        }
                        break;
                    }
                    if (this.syntax.opEscXHex2()) {
                        int num = this.scanUnsignedHexadecimalNumber(2);
                        if (num < 0) {
                            this.newValueException("too big number");
                        }
                        if (this.p == last) {
                            num = 0;
                        }
                        this.token.type = TokenType.RAW_BYTE;
                        this.token.base = 16;
                        this.token.setC(num);
                        break;
                    }
                    break;
                }
                case 117: {
                    if (!this.left()) {
                        break;
                    }
                    final int last = this.p;
                    if (this.syntax.op2EscUHex4()) {
                        int num = this.scanUnsignedHexadecimalNumber(4);
                        if (num < 0) {
                            this.newValueException("too big number");
                        }
                        if (this.p == last) {
                            num = 0;
                        }
                        this.token.type = TokenType.CODE_POINT;
                        this.token.base = 16;
                        this.token.setCode(num);
                        break;
                    }
                    break;
                }
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55: {
                    if (this.syntax.opEscOctal3()) {
                        this.unfetch();
                        final int last = this.p;
                        int num = this.scanUnsignedOctalNumber(3);
                        if (num < 0) {
                            this.newValueException("too big number");
                        }
                        if (this.p == last) {
                            num = 0;
                        }
                        this.token.type = TokenType.RAW_BYTE;
                        this.token.base = 8;
                        this.token.setC(num);
                        break;
                    }
                    break;
                }
                default: {
                    this.unfetch();
                    final int num = this.fetchEscapedValue();
                    if (this.token.getC() != num) {
                        this.token.setCode(num);
                        this.token.type = TokenType.CODE_POINT;
                        break;
                    }
                    break;
                }
            }
        }
        else if (this.c == 91) {
            if (this.syntax.opPosixBracket() && this.peekIs(58)) {
                this.token.backP = this.p;
                this.inc();
                if (this.strExistCheckWithEsc(Lexer.send, Lexer.send.length, 93)) {
                    this.token.type = TokenType.POSIX_BRACKET_OPEN;
                }
                else {
                    this.unfetch();
                    if (this.syntax.op2CClassSetOp()) {
                        this.token.type = TokenType.CC_CC_OPEN;
                    }
                    else {
                        this.env.ccEscWarn("[");
                    }
                }
            }
            else if (this.syntax.op2CClassSetOp()) {
                this.token.type = TokenType.CC_CC_OPEN;
            }
            else {
                this.env.ccEscWarn("[");
            }
        }
        else if (this.c == 38 && this.syntax.op2CClassSetOp() && this.left() && this.peekIs(38)) {
            this.inc();
            this.token.type = TokenType.CC_AND;
        }
        return this.token.type;
    }
    
    protected final int backrefRelToAbs(final int relNo) {
        return this.env.numMem + 1 + relNo;
    }
    
    protected final TokenType fetchToken() {
        while (this.left()) {
            this.token.type = TokenType.STRING;
            this.token.base = 0;
            this.token.backP = this.p;
            this.fetch();
            Label_3846: {
                if (this.c == this.syntax.metaCharTable.esc && !this.syntax.op2IneffectiveEscape()) {
                    if (!this.left()) {
                        this.newSyntaxException("end pattern at escape");
                    }
                    this.token.backP = this.p;
                    this.fetch();
                    this.token.setC(this.c);
                    this.token.escaped = true;
                    Label_2826: {
                        switch (this.c) {
                            case 42: {
                                if (!this.syntax.opEscAsteriskZeroInf()) {
                                    break;
                                }
                                this.token.type = TokenType.OP_REPEAT;
                                this.token.setRepeatLower(0);
                                this.token.setRepeatUpper(-1);
                                this.greedyCheck();
                                break;
                            }
                            case 43: {
                                if (!this.syntax.opEscPlusOneInf()) {
                                    break;
                                }
                                this.token.type = TokenType.OP_REPEAT;
                                this.token.setRepeatLower(1);
                                this.token.setRepeatUpper(-1);
                                this.greedyCheck();
                                break;
                            }
                            case 63: {
                                if (!this.syntax.opEscQMarkZeroOne()) {
                                    break;
                                }
                                this.token.type = TokenType.OP_REPEAT;
                                this.token.setRepeatLower(0);
                                this.token.setRepeatUpper(1);
                                this.greedyCheck();
                                break;
                            }
                            case 123: {
                                if (!this.syntax.opEscBraceInterval()) {
                                    break;
                                }
                                switch (this.fetchRangeQuantifier()) {
                                    case 0: {
                                        this.greedyCheck();
                                        break Label_2826;
                                    }
                                    case 2: {
                                        if (this.syntax.fixedIntervalIsGreedyOnly()) {
                                            this.possessiveCheck();
                                            break Label_2826;
                                        }
                                        this.greedyCheck();
                                        break Label_2826;
                                    }
                                    default: {
                                        break Label_2826;
                                    }
                                }
                                break;
                            }
                            case 124: {
                                if (!this.syntax.opEscVBarAlt()) {
                                    break;
                                }
                                this.token.type = TokenType.ALT;
                                break;
                            }
                            case 40: {
                                if (!this.syntax.opEscLParenSubexp()) {
                                    break;
                                }
                                this.token.type = TokenType.SUBEXP_OPEN;
                                break;
                            }
                            case 41: {
                                if (!this.syntax.opEscLParenSubexp()) {
                                    break;
                                }
                                this.token.type = TokenType.SUBEXP_CLOSE;
                                break;
                            }
                            case 119: {
                                if (!this.syntax.opEscWWord()) {
                                    break;
                                }
                                this.token.type = TokenType.CHAR_TYPE;
                                this.token.setPropCType(12);
                                this.token.setPropNot(false);
                                break;
                            }
                            case 87: {
                                if (!this.syntax.opEscWWord()) {
                                    break;
                                }
                                this.token.type = TokenType.CHAR_TYPE;
                                this.token.setPropCType(12);
                                this.token.setPropNot(true);
                                break;
                            }
                            case 98: {
                                if (!this.syntax.opEscBWordBound()) {
                                    break;
                                }
                                this.token.type = TokenType.ANCHOR;
                                this.token.setAnchor(64);
                                break;
                            }
                            case 66: {
                                if (!this.syntax.opEscBWordBound()) {
                                    break;
                                }
                                this.token.type = TokenType.ANCHOR;
                                this.token.setAnchor(128);
                                break;
                            }
                            case 60: {
                                if (!this.syntax.opEscLtGtWordBeginEnd()) {
                                    break;
                                }
                                this.token.type = TokenType.ANCHOR;
                                this.token.setAnchor(256);
                                break;
                            }
                            case 62: {
                                if (!this.syntax.opEscLtGtWordBeginEnd()) {
                                    break;
                                }
                                this.token.type = TokenType.ANCHOR;
                                this.token.setAnchor(512);
                                break;
                            }
                            case 115: {
                                if (!this.syntax.opEscSWhiteSpace()) {
                                    break;
                                }
                                this.token.type = TokenType.CHAR_TYPE;
                                this.token.setPropCType(9);
                                this.token.setPropNot(false);
                                break;
                            }
                            case 83: {
                                if (!this.syntax.opEscSWhiteSpace()) {
                                    break;
                                }
                                this.token.type = TokenType.CHAR_TYPE;
                                this.token.setPropCType(9);
                                this.token.setPropNot(true);
                                break;
                            }
                            case 100: {
                                if (!this.syntax.opEscDDigit()) {
                                    break;
                                }
                                this.token.type = TokenType.CHAR_TYPE;
                                this.token.setPropCType(4);
                                this.token.setPropNot(false);
                                break;
                            }
                            case 68: {
                                if (!this.syntax.opEscDDigit()) {
                                    break;
                                }
                                this.token.type = TokenType.CHAR_TYPE;
                                this.token.setPropCType(4);
                                this.token.setPropNot(true);
                                break;
                            }
                            case 104: {
                                if (!this.syntax.op2EscHXDigit()) {
                                    break;
                                }
                                this.token.type = TokenType.CHAR_TYPE;
                                this.token.setPropCType(11);
                                this.token.setPropNot(false);
                                break;
                            }
                            case 72: {
                                if (!this.syntax.op2EscHXDigit()) {
                                    break;
                                }
                                this.token.type = TokenType.CHAR_TYPE;
                                this.token.setPropCType(11);
                                this.token.setPropNot(true);
                                break;
                            }
                            case 65: {
                                if (!this.syntax.opEscAZBufAnchor()) {
                                    break;
                                }
                                this.token.type = TokenType.ANCHOR;
                                this.token.setSubtype(1);
                                break;
                            }
                            case 90: {
                                if (!this.syntax.opEscAZBufAnchor()) {
                                    break;
                                }
                                this.token.type = TokenType.ANCHOR;
                                this.token.setSubtype(16);
                                break;
                            }
                            case 122: {
                                if (!this.syntax.opEscAZBufAnchor()) {
                                    break;
                                }
                                this.token.type = TokenType.ANCHOR;
                                this.token.setSubtype(8);
                                break;
                            }
                            case 71: {
                                if (!this.syntax.opEscCapitalGBeginAnchor()) {
                                    break;
                                }
                                this.token.type = TokenType.ANCHOR;
                                this.token.setSubtype(4);
                                break;
                            }
                            case 96: {
                                if (!this.syntax.op2EscGnuBufAnchor()) {
                                    break;
                                }
                                this.token.type = TokenType.ANCHOR;
                                this.token.setSubtype(1);
                                break;
                            }
                            case 39: {
                                if (!this.syntax.op2EscGnuBufAnchor()) {
                                    break;
                                }
                                this.token.type = TokenType.ANCHOR;
                                this.token.setSubtype(8);
                                break;
                            }
                            case 120: {
                                if (!this.left()) {
                                    break;
                                }
                                final int last = this.p;
                                if (this.peekIs(123) && this.syntax.opEscXBraceHex8()) {
                                    this.inc();
                                    final int num = this.scanUnsignedHexadecimalNumber(8);
                                    if (num < 0) {
                                        this.newValueException("too big wide-char value");
                                    }
                                    if (this.left() && this.enc.isXDigit(this.peek())) {
                                        this.newValueException("too long wide-char value");
                                    }
                                    if (this.p > last + this.enc.length(this.bytes, last, this.stop) && this.left() && this.peekIs(125)) {
                                        this.inc();
                                        this.token.type = TokenType.CODE_POINT;
                                        this.token.setCode(num);
                                    }
                                    else {
                                        this.p = last;
                                    }
                                    break;
                                }
                                if (this.syntax.opEscXHex2()) {
                                    int num = this.scanUnsignedHexadecimalNumber(2);
                                    if (num < 0) {
                                        this.newValueException("too big number");
                                    }
                                    if (this.p == last) {
                                        num = 0;
                                    }
                                    this.token.type = TokenType.RAW_BYTE;
                                    this.token.base = 16;
                                    this.token.setC(num);
                                    break;
                                }
                                break;
                            }
                            case 117: {
                                if (!this.left()) {
                                    break;
                                }
                                final int last = this.p;
                                if (this.syntax.op2EscUHex4()) {
                                    int num = this.scanUnsignedHexadecimalNumber(4);
                                    if (num < 0) {
                                        this.newValueException("too big number");
                                    }
                                    if (this.p == last) {
                                        num = 0;
                                    }
                                    this.token.type = TokenType.CODE_POINT;
                                    this.token.base = 16;
                                    this.token.setCode(num);
                                    break;
                                }
                                break;
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
                                this.unfetch();
                                final int last = this.p;
                                final int num = this.scanUnsignedNumber();
                                if (num >= 0) {
                                    if (num <= 1000) {
                                        if (this.syntax.opDecimalBackref() && (num <= this.env.numMem || num <= 9)) {
                                            if (this.syntax.strictCheckBackref() && (num > this.env.numMem || this.env.memNodes == null || this.env.memNodes[num] == null)) {
                                                this.newValueException("invalid backref number/name");
                                            }
                                            this.token.type = TokenType.BACKREF;
                                            this.token.setBackrefNum(1);
                                            this.token.setBackrefRef1(num);
                                            this.token.setBackrefByName(false);
                                            this.token.setBackrefExistLevel(false);
                                            break;
                                        }
                                    }
                                }
                                if (this.c == 56 || this.c == 57) {
                                    this.p = last;
                                    this.inc();
                                    break;
                                }
                                this.p = last;
                            }
                            case 48: {
                                if (this.syntax.opEscOctal3()) {
                                    final int last = this.p;
                                    int num = this.scanUnsignedOctalNumber((this.c == 48) ? 2 : 3);
                                    if (num < 0) {
                                        this.newValueException("too big number");
                                    }
                                    if (this.p == last) {
                                        num = 0;
                                    }
                                    this.token.type = TokenType.RAW_BYTE;
                                    this.token.base = 8;
                                    this.token.setC(num);
                                    break;
                                }
                                if (this.c != 48) {
                                    this.inc();
                                    break;
                                }
                                break;
                            }
                            case 107: {
                                if (!this.syntax.op2EscKNamedBackref()) {
                                    break;
                                }
                                this.fetch();
                                if (this.c == 60 || this.c == 39) {
                                    final int last = this.p;
                                    final int[] rbackNum = { 0 };
                                    final int[] rlevel = { 0 };
                                    this.token.setBackrefExistLevel(this.fetchNameWithLevel(this.c, rbackNum, rlevel));
                                    this.token.setBackrefLevel(rlevel[0]);
                                    int backNum = rbackNum[0];
                                    final int nameEnd = this.value;
                                    if (backNum != 0) {
                                        if (backNum < 0) {
                                            backNum = this.backrefRelToAbs(backNum);
                                            if (backNum <= 0) {
                                                this.newValueException("invalid backref number/name");
                                            }
                                        }
                                        if (this.syntax.strictCheckBackref() && (backNum > this.env.numMem || this.env.memNodes == null)) {
                                            this.newValueException("invalid backref number/name");
                                        }
                                        this.token.type = TokenType.BACKREF;
                                        this.token.setBackrefByName(false);
                                        this.token.setBackrefNum(1);
                                        this.token.setBackrefRef1(backNum);
                                    }
                                    else {
                                        final NameEntry e = this.env.reg.nameToGroupNumbers(this.bytes, last, nameEnd);
                                        if (e == null) {
                                            this.newValueException("undefined name <%n> reference", last, nameEnd);
                                        }
                                        if (this.syntax.strictCheckBackref()) {
                                            if (e.backNum == 1) {
                                                if (e.backRef1 > this.env.numMem || this.env.memNodes == null || this.env.memNodes[e.backRef1] == null) {
                                                    this.newValueException("invalid backref number/name");
                                                }
                                            }
                                            else {
                                                for (int i = 0; i < e.backNum; ++i) {
                                                    if (e.backRefs[i] > this.env.numMem || this.env.memNodes == null || this.env.memNodes[e.backRefs[i]] == null) {
                                                        this.newValueException("invalid backref number/name");
                                                    }
                                                }
                                            }
                                        }
                                        this.token.type = TokenType.BACKREF;
                                        this.token.setBackrefByName(true);
                                        if (e.backNum == 1) {
                                            this.token.setBackrefNum(1);
                                            this.token.setBackrefRef1(e.backRef1);
                                        }
                                        else {
                                            this.token.setBackrefNum(e.backNum);
                                            this.token.setBackrefRefs(e.backRefs);
                                        }
                                    }
                                    break;
                                }
                                this.unfetch();
                                break;
                            }
                            case 103: {
                                if (!this.syntax.op2EscGSubexpCall()) {
                                    break;
                                }
                                this.fetch();
                                if (this.c == 60 || this.c == 39) {
                                    final int last = this.p;
                                    final int gNum = this.fetchName(this.c, true);
                                    final int nameEnd = this.value;
                                    this.token.type = TokenType.CALL;
                                    this.token.setCallNameP(last);
                                    this.token.setCallNameEnd(nameEnd);
                                    this.token.setCallGNum(gNum);
                                    break;
                                }
                                this.unfetch();
                                break;
                            }
                            case 81: {
                                if (this.syntax.op2EscCapitalQQuote()) {
                                    this.token.type = TokenType.QUOTE_OPEN;
                                    break;
                                }
                                break;
                            }
                            case 80:
                            case 112: {
                                if (!this.peekIs(123) || !this.syntax.op2EscPBraceCharProperty()) {
                                    break;
                                }
                                this.inc();
                                this.token.type = TokenType.CHAR_PROPERTY;
                                this.token.setPropNot(this.c == 80);
                                if (!this.syntax.op2EscPBraceCircumflexNot()) {
                                    break;
                                }
                                this.fetch();
                                if (this.c == 94) {
                                    this.token.setPropNot(!this.token.getPropNot());
                                    break;
                                }
                                this.unfetch();
                                break;
                            }
                            default: {
                                this.unfetch();
                                final int num = this.fetchEscapedValue();
                                if (this.token.getC() != num) {
                                    this.token.type = TokenType.CODE_POINT;
                                    this.token.setCode(num);
                                    break;
                                }
                                this.p = this.token.backP + this.enc.length(this.bytes, this.token.backP, this.stop);
                                break;
                            }
                        }
                    }
                }
                else {
                    this.token.setC(this.c);
                    this.token.escaped = false;
                    if (this.c != 0 && this.syntax.opVariableMetaCharacters()) {
                        if (this.c == this.syntax.metaCharTable.anyChar) {
                            this.token.type = TokenType.ANYCHAR;
                            return this.token.type;
                        }
                        if (this.c == this.syntax.metaCharTable.anyTime) {
                            this.token.type = TokenType.OP_REPEAT;
                            this.token.setRepeatLower(0);
                            this.token.setRepeatUpper(-1);
                            this.greedyCheck();
                            return this.token.type;
                        }
                        if (this.c == this.syntax.metaCharTable.zeroOrOneTime) {
                            this.token.type = TokenType.OP_REPEAT;
                            this.token.setRepeatLower(0);
                            this.token.setRepeatUpper(1);
                            this.greedyCheck();
                            return this.token.type;
                        }
                        if (this.c == this.syntax.metaCharTable.oneOrMoreTime) {
                            this.token.type = TokenType.OP_REPEAT;
                            this.token.setRepeatLower(1);
                            this.token.setRepeatUpper(-1);
                            this.greedyCheck();
                            return this.token.type;
                        }
                        if (this.c == this.syntax.metaCharTable.anyCharAnyTime) {
                            this.token.type = TokenType.ANYCHAR_ANYTIME;
                            return this.token.type;
                        }
                    }
                    switch (this.c) {
                        case 46: {
                            if (!this.syntax.opDotAnyChar()) {
                                break;
                            }
                            this.token.type = TokenType.ANYCHAR;
                            break;
                        }
                        case 42: {
                            if (!this.syntax.opAsteriskZeroInf()) {
                                break;
                            }
                            this.token.type = TokenType.OP_REPEAT;
                            this.token.setRepeatLower(0);
                            this.token.setRepeatUpper(-1);
                            this.greedyCheck();
                            break;
                        }
                        case 43: {
                            if (!this.syntax.opPlusOneInf()) {
                                break;
                            }
                            this.token.type = TokenType.OP_REPEAT;
                            this.token.setRepeatLower(1);
                            this.token.setRepeatUpper(-1);
                            this.greedyCheck();
                            break;
                        }
                        case 63: {
                            if (!this.syntax.opQMarkZeroOne()) {
                                break;
                            }
                            this.token.type = TokenType.OP_REPEAT;
                            this.token.setRepeatLower(0);
                            this.token.setRepeatUpper(1);
                            this.greedyCheck();
                            break;
                        }
                        case 123: {
                            if (!this.syntax.opBraceInterval()) {
                                break;
                            }
                            switch (this.fetchRangeQuantifier()) {
                                case 0: {
                                    this.greedyCheck();
                                    break Label_3846;
                                }
                                case 2: {
                                    if (this.syntax.fixedIntervalIsGreedyOnly()) {
                                        this.possessiveCheck();
                                        break Label_3846;
                                    }
                                    this.greedyCheck();
                                    break Label_3846;
                                }
                                default: {
                                    break Label_3846;
                                }
                            }
                            break;
                        }
                        case 124: {
                            if (!this.syntax.opVBarAlt()) {
                                break;
                            }
                            this.token.type = TokenType.ALT;
                            break;
                        }
                        case 40: {
                            if (this.peekIs(63) && this.syntax.op2QMarkGroupEffect()) {
                                this.inc();
                                if (this.peekIs(35)) {
                                    this.fetch();
                                    while (true) {
                                        if (!this.left()) {
                                            this.newSyntaxException("end pattern in group");
                                        }
                                        this.fetch();
                                        if (this.c == this.syntax.metaCharTable.esc) {
                                            if (!this.left()) {
                                                continue;
                                            }
                                            this.fetch();
                                        }
                                        else {
                                            if (this.c == 41) {
                                                break;
                                            }
                                            continue;
                                        }
                                    }
                                    continue;
                                }
                                this.unfetch();
                            }
                            if (!this.syntax.opLParenSubexp()) {
                                break;
                            }
                            this.token.type = TokenType.SUBEXP_OPEN;
                            break;
                        }
                        case 41: {
                            if (!this.syntax.opLParenSubexp()) {
                                break;
                            }
                            this.token.type = TokenType.SUBEXP_CLOSE;
                            break;
                        }
                        case 94: {
                            if (!this.syntax.opLineAnchor()) {
                                break;
                            }
                            this.token.type = TokenType.ANCHOR;
                            this.token.setSubtype(Option.isSingleline(this.env.option) ? 1 : 2);
                            break;
                        }
                        case 36: {
                            if (!this.syntax.opLineAnchor()) {
                                break;
                            }
                            this.token.type = TokenType.ANCHOR;
                            this.token.setSubtype(Option.isSingleline(this.env.option) ? 16 : 32);
                            break;
                        }
                        case 91: {
                            if (!this.syntax.opBracketCC()) {
                                break;
                            }
                            this.token.type = TokenType.CC_CC_OPEN;
                            break;
                        }
                        case 35: {
                            if (Option.isExtend(this.env.option)) {
                                while (this.left()) {
                                    this.fetch();
                                    if (this.enc.isNewLine(this.c)) {
                                        break;
                                    }
                                }
                                continue;
                            }
                            break;
                        }
                        case 9:
                        case 10:
                        case 12:
                        case 13:
                        case 32: {
                            if (Option.isExtend(this.env.option)) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
            return this.token.type;
        }
        return this.token.type = TokenType.EOT;
    }
    
    private void greedyCheck() {
        if (this.left() && this.peekIs(63) && this.syntax.opQMarkNonGreedy()) {
            this.fetch();
            this.token.setRepeatGreedy(false);
            this.token.setRepeatPossessive(false);
        }
        else {
            this.possessiveCheck();
        }
    }
    
    private void possessiveCheck() {
        if (this.left() && this.peekIs(43) && ((this.syntax.op2PlusPossessiveRepeat() && this.token.type != TokenType.INTERVAL) || (this.syntax.op2PlusPossessiveInterval() && this.token.type == TokenType.INTERVAL))) {
            this.fetch();
            this.token.setRepeatGreedy(true);
            this.token.setRepeatPossessive(true);
        }
        else {
            this.token.setRepeatGreedy(true);
            this.token.setRepeatPossessive(false);
        }
    }
    
    protected final int fetchCharPropertyToCType() {
        this.mark();
        while (this.left()) {
            final int last = this.p;
            this.fetch();
            if (this.c == 125) {
                return this.enc.propertyNameToCType(this.bytes, this._p, last);
            }
            if (this.c == 40 || this.c == 41 || this.c == 123 || this.c == 124) {
                throw new CharacterPropertyException("invalid character property name {%n}", this.bytes, this._p, last);
            }
        }
        this.newInternalException("internal parser error (bug)");
        return 0;
    }
    
    static {
        send = new int[] { 58, 93 };
    }
}
