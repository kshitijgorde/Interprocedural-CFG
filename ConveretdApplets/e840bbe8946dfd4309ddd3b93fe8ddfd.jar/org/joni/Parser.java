// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.ast.CallNode;
import org.joni.ast.BackRefNode;
import org.joni.ast.QuantifierNode;
import org.joni.ast.AnyCharNode;
import org.joni.ast.ConsAltNode;
import org.jcodings.ApplyAllCaseFoldFunction;
import org.joni.ast.CTypeNode;
import org.joni.ast.StringNode;
import org.joni.ast.EncloseNode;
import org.joni.ast.AnchorNode;
import org.joni.constants.CCVALTYPE;
import org.joni.constants.CCSTATE;
import org.joni.constants.TokenType;
import org.jcodings.IntHolder;
import org.jcodings.constants.PosixBracket;
import org.joni.ast.CClassNode;
import org.joni.ast.Node;

class Parser extends Lexer
{
    protected final Regex regex;
    protected Node root;
    protected int returnCode;
    private static final int POSIX_BRACKET_NAME_MIN_LEN = 4;
    private static final int POSIX_BRACKET_CHECK_LIMIT_LENGTH = 20;
    private static final byte[] BRACKET_END;
    private int nextChar;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    protected Parser(final ScanEnvironment env, final byte[] bytes, final int p, final int end) {
        super(env, bytes, p, end);
        this.regex = env.reg;
    }
    
    protected final Node parse() {
        this.root = this.parseRegexp();
        this.regex.numMem = this.env.numMem;
        return this.root;
    }
    
    private boolean parsePosixBracket(final CClassNode cc) {
        this.mark();
        boolean not;
        if (this.peekIs(94)) {
            this.inc();
            not = true;
        }
        else {
            not = false;
        }
        if (this.enc.strLength(this.bytes, this.p, this.stop) >= 7) {
            final byte[][] pbs = PosixBracket.PBSNamesLower;
            for (int i = 0; i < pbs.length; ++i) {
                final byte[] name = pbs[i];
                if (this.enc.strNCmp(this.bytes, this.p, this.stop, name, 0, name.length) == 0) {
                    this.p = this.enc.step(this.bytes, this.p, this.stop, name.length);
                    if (this.enc.strNCmp(this.bytes, this.p, this.stop, Parser.BRACKET_END, 0, Parser.BRACKET_END.length) != 0) {
                        this.newSyntaxException("invalid POSIX bracket type");
                    }
                    cc.addCType(PosixBracket.PBSValues[i], not, this.env, this);
                    this.inc();
                    this.inc();
                    return false;
                }
            }
        }
        this.c = 0;
        int j = 0;
        while (this.left() && (this.c = this.peek()) != 58 && this.c != 93) {
            this.inc();
            if (++j > 20) {
                break;
            }
        }
        if (this.c == 58 && this.left()) {
            this.inc();
            if (this.left()) {
                this.fetch();
                if (this.c == 93) {
                    this.newSyntaxException("invalid POSIX bracket type");
                }
            }
        }
        this.restore();
        return true;
    }
    
    private CClassNode parseCharProperty() {
        final int ctype = this.fetchCharPropertyToCType();
        final CClassNode n = new CClassNode();
        n.addCType(ctype, false, this.env, this);
        if (this.token.getPropNot()) {
            n.setNot();
        }
        return n;
    }
    
    private boolean codeExistCheck(final int code, final boolean ignoreEscaped) {
        this.mark();
        boolean inEsc = false;
        while (this.left()) {
            if (ignoreEscaped && inEsc) {
                inEsc = false;
            }
            else {
                this.fetch();
                if (this.c == code) {
                    this.restore();
                    return true;
                }
                if (this.c != this.syntax.metaCharTable.esc) {
                    continue;
                }
                inEsc = true;
            }
        }
        this.restore();
        return false;
    }
    
    private CClassNode parseCharClass() {
        this.fetchTokenInCC();
        boolean neg;
        if (this.token.type == TokenType.CHAR && this.token.getC() == 94 && !this.token.escaped) {
            neg = true;
            this.fetchTokenInCC();
        }
        else {
            neg = false;
        }
        if (this.token.type == TokenType.CC_CLOSE) {
            if (!this.codeExistCheck(93, true)) {
                this.newSyntaxException("empty char-class");
            }
            this.env.ccEscWarn("]");
            this.token.type = TokenType.CHAR;
        }
        CClassNode cc = new CClassNode();
        CClassNode prevCC = null;
        CClassNode workCC = null;
        final CClassNode.CCStateArg arg = new CClassNode.CCStateArg();
        boolean andStart = false;
        arg.state = CCSTATE.START;
        while (this.token.type != TokenType.CC_CLOSE) {
            boolean fetched = false;
            Label_1172: {
                switch (this.token.type) {
                    case CHAR: {
                        final int len = this.enc.codeToMbcLength(this.token.getC());
                        if (len > 1) {
                            arg.inType = CCVALTYPE.CODE_POINT;
                        }
                        else {
                            arg.inType = CCVALTYPE.SB;
                        }
                        arg.v = this.token.getC();
                        arg.vIsRaw = false;
                        this.valEntry2(cc, arg);
                        break Label_1172;
                    }
                    case RAW_BYTE: {
                        if (!this.enc.isSingleByte() && this.token.base != 0) {
                            final byte[] buf = new byte[18];
                            final int psave = this.p;
                            final int base = this.token.base;
                            buf[0] = (byte)this.token.getC();
                            int i;
                            for (i = 1; i < this.enc.maxLength(); ++i) {
                                this.fetchTokenInCC();
                                if (this.token.type != TokenType.RAW_BYTE || this.token.base != base) {
                                    fetched = true;
                                    break;
                                }
                                buf[i] = (byte)this.token.getC();
                            }
                            if (i < this.enc.minLength()) {
                                this.newValueException("too short multibyte code string");
                            }
                            final int len = this.enc.length(buf, 0, i);
                            if (i < len) {
                                this.newValueException("too short multibyte code string");
                            }
                            else if (i > len) {
                                this.p = psave;
                                for (i = 1; i < len; ++i) {
                                    this.fetchTokenInCC();
                                }
                                fetched = false;
                            }
                            if (i == 1) {
                                arg.v = (buf[0] & 0xFF);
                                arg.inType = CCVALTYPE.SB;
                            }
                            else {
                                arg.v = this.enc.mbcToCode(buf, 0, buf.length);
                                arg.inType = CCVALTYPE.CODE_POINT;
                            }
                        }
                        else {
                            arg.v = this.token.getC();
                            arg.inType = CCVALTYPE.SB;
                        }
                        arg.vIsRaw = true;
                        this.valEntry2(cc, arg);
                        break Label_1172;
                    }
                    case CODE_POINT: {
                        arg.v = this.token.getCode();
                        arg.vIsRaw = true;
                        this.valEntry(cc, arg);
                        break Label_1172;
                    }
                    case POSIX_BRACKET_OPEN: {
                        if (this.parsePosixBracket(cc)) {
                            this.env.ccEscWarn("[");
                            this.p = this.token.backP;
                            arg.v = this.token.getC();
                            arg.vIsRaw = false;
                            this.valEntry(cc, arg);
                            break Label_1172;
                        }
                        cc.nextStateClass(arg, this.env);
                        break Label_1172;
                    }
                    case CHAR_TYPE: {
                        cc.addCType(this.token.getPropCType(), this.token.getPropNot(), this.env, this);
                        cc.nextStateClass(arg, this.env);
                        break Label_1172;
                    }
                    case CHAR_PROPERTY: {
                        final int ctype = this.fetchCharPropertyToCType();
                        cc.addCType(ctype, this.token.getPropNot(), this.env, this);
                        cc.nextStateClass(arg, this.env);
                        break Label_1172;
                    }
                    case CC_RANGE: {
                        if (arg.state == CCSTATE.VALUE) {
                            this.fetchTokenInCC();
                            fetched = true;
                            if (this.token.type == TokenType.CC_CLOSE) {
                                this.rangeEndVal(cc, arg);
                                break Label_1172;
                            }
                            if (this.token.type == TokenType.CC_AND) {
                                this.env.ccEscWarn("-");
                                this.rangeEndVal(cc, arg);
                                break Label_1172;
                            }
                            arg.state = CCSTATE.RANGE;
                            break Label_1172;
                        }
                        else {
                            if (arg.state == CCSTATE.START) {
                                arg.v = this.token.getC();
                                arg.vIsRaw = false;
                                this.fetchTokenInCC();
                                fetched = true;
                                if (this.token.type == TokenType.CC_RANGE || andStart) {
                                    this.env.ccEscWarn("-");
                                }
                                this.valEntry(cc, arg);
                                break Label_1172;
                            }
                            if (arg.state == CCSTATE.RANGE) {
                                this.env.ccEscWarn("-");
                                this.sbChar(cc, arg);
                                break Label_1172;
                            }
                            this.fetchTokenInCC();
                            fetched = true;
                            if (this.token.type == TokenType.CC_CLOSE) {
                                this.rangeEndVal(cc, arg);
                                break Label_1172;
                            }
                            if (this.token.type == TokenType.CC_AND) {
                                this.env.ccEscWarn("-");
                                this.rangeEndVal(cc, arg);
                                break Label_1172;
                            }
                            if (this.syntax.allowDoubleRangeOpInCC()) {
                                this.env.ccEscWarn("-");
                                this.sbChar(cc, arg);
                                break Label_1172;
                            }
                            this.newSyntaxException("unmatched range specifier in char-class");
                            break Label_1172;
                        }
                        break;
                    }
                    case CC_CC_OPEN: {
                        final CClassNode acc = this.parseCharClass();
                        cc.or(acc, this.enc);
                        break Label_1172;
                    }
                    case CC_AND: {
                        if (arg.state == CCSTATE.VALUE) {
                            arg.v = 0;
                            arg.vIsRaw = false;
                            cc.nextStateValue(arg, this.env);
                        }
                        andStart = true;
                        arg.state = CCSTATE.START;
                        if (prevCC != null) {
                            prevCC.and(cc, this.enc);
                            break Label_1172;
                        }
                        prevCC = cc;
                        if (workCC == null) {
                            workCC = new CClassNode();
                        }
                        cc = workCC;
                        break Label_1172;
                    }
                    case EOT: {
                        this.newSyntaxException("premature end of char-class");
                        break;
                    }
                }
                this.newInternalException("internal parser error (bug)");
            }
            if (!fetched) {
                this.fetchTokenInCC();
            }
        }
        if (arg.state == CCSTATE.VALUE) {
            arg.v = 0;
            arg.vIsRaw = false;
            cc.nextStateValue(arg, this.env);
        }
        if (prevCC != null) {
            prevCC.and(cc, this.enc);
            cc = prevCC;
        }
        if (neg) {
            cc.setNot();
        }
        else {
            cc.clearNot();
        }
        if (cc.isNot() && this.syntax.notNewlineInNegativeCC() && !cc.isEmpty()) {
            final int NEW_LINE = 10;
            if (this.enc.isNewLine(10)) {
                if (this.enc.codeToMbcLength(10) == 1) {
                    cc.bs.set(10);
                }
                else {
                    cc.addCodeRange(this.env, 10, 10);
                }
            }
        }
        return cc;
    }
    
    private void valEntry2(final CClassNode cc, final CClassNode.CCStateArg arg) {
        cc.nextStateValue(arg, this.env);
    }
    
    private void valEntry(final CClassNode cc, final CClassNode.CCStateArg arg) {
        final int len = this.enc.codeToMbcLength(arg.v);
        arg.inType = ((len == 1) ? CCVALTYPE.SB : CCVALTYPE.CODE_POINT);
        this.valEntry2(cc, arg);
    }
    
    private void sbChar(final CClassNode cc, final CClassNode.CCStateArg arg) {
        arg.inType = CCVALTYPE.SB;
        arg.v = this.token.getC();
        arg.vIsRaw = false;
        this.valEntry2(cc, arg);
    }
    
    private void rangeEndVal(final CClassNode cc, final CClassNode.CCStateArg arg) {
        arg.v = 45;
        arg.vIsRaw = false;
        this.valEntry(cc, arg);
    }
    
    private Node parseEnclose(final TokenType term) {
        Node node = null;
        if (!this.left()) {
            this.newSyntaxException("end pattern with unmatched parenthesis");
        }
        int option = this.env.option;
        if (this.peekIs(63) && this.syntax.op2QMarkGroupEffect()) {
            this.inc();
            if (!this.left()) {
                this.newSyntaxException("end pattern in group");
            }
            boolean listCapture = false;
            this.fetch();
            switch (this.c) {
                case 58: {
                    this.fetchToken();
                    node = this.parseSubExp(term);
                    this.returnCode = 1;
                    return node;
                }
                case 61: {
                    node = new AnchorNode(1024);
                    break;
                }
                case 33: {
                    node = new AnchorNode(2048);
                    break;
                }
                case 62: {
                    node = new EncloseNode(4);
                    break;
                }
                case 39: {
                    if (this.syntax.op2QMarkLtNamedGroup()) {
                        listCapture = false;
                        node = this.namedGroup2(listCapture);
                        break;
                    }
                    this.newSyntaxException("undefined group option");
                    break;
                }
                case 60: {
                    this.fetch();
                    if (this.c == 61) {
                        node = new AnchorNode(4096);
                        break;
                    }
                    if (this.c == 33) {
                        node = new AnchorNode(8192);
                        break;
                    }
                    if (this.syntax.op2QMarkLtNamedGroup()) {
                        this.unfetch();
                        this.c = 60;
                        listCapture = false;
                        node = this.namedGroup2(listCapture);
                        break;
                    }
                    this.newSyntaxException("undefined group option");
                    break;
                }
                case 64: {
                    if (this.syntax.op2AtMarkCaptureHistory()) {
                        if (this.syntax.op2QMarkLtNamedGroup()) {
                            this.fetch();
                            if (this.c == 60 || this.c == 39) {
                                listCapture = true;
                                node = this.namedGroup2(listCapture);
                            }
                            this.unfetch();
                        }
                        final EncloseNode en = new EncloseNode(this.env.option, false);
                        final int num = this.env.addMemEntry();
                        if (num >= 32) {
                            this.newValueException("group number is too big for capture history");
                        }
                        en.regNum = num;
                        node = en;
                        break;
                    }
                    this.newSyntaxException("undefined group option");
                    break;
                }
                case 45:
                case 105:
                case 109:
                case 115:
                case 120: {
                    boolean neg = false;
                    while (true) {
                        switch (this.c) {
                            case 41:
                            case 58: {
                                break;
                            }
                            case 45: {
                                neg = true;
                                break;
                            }
                            case 120: {
                                option = BitStatus.bsOnOff(option, 2, neg);
                                break;
                            }
                            case 105: {
                                option = BitStatus.bsOnOff(option, 1, neg);
                                break;
                            }
                            case 115: {
                                if (this.syntax.op2OptionPerl()) {
                                    option = BitStatus.bsOnOff(option, 4, neg);
                                    break;
                                }
                                this.newSyntaxException("undefined group option");
                                break;
                            }
                            case 109: {
                                if (this.syntax.op2OptionPerl()) {
                                    option = BitStatus.bsOnOff(option, 8, !neg);
                                    break;
                                }
                                if (this.syntax.op2OptionRuby()) {
                                    option = BitStatus.bsOnOff(option, 4, neg);
                                    break;
                                }
                                this.newSyntaxException("undefined group option");
                                break;
                            }
                            default: {
                                this.newSyntaxException("undefined group option");
                                break;
                            }
                        }
                        if (this.c == 41) {
                            final EncloseNode en2 = (EncloseNode)(node = new EncloseNode(option, 0));
                            this.returnCode = 2;
                            return node;
                        }
                        if (this.c == 58) {
                            final int prev = this.env.option;
                            this.env.option = option;
                            this.fetchToken();
                            final Node target = this.parseSubExp(term);
                            this.env.option = prev;
                            final EncloseNode en3 = new EncloseNode(option, 0);
                            en3.setTarget(target);
                            node = en3;
                            this.returnCode = 0;
                            return node;
                        }
                        if (!this.left()) {
                            this.newSyntaxException("end pattern in group");
                        }
                        this.fetch();
                    }
                    break;
                }
                default: {
                    this.newSyntaxException("undefined group option");
                    break;
                }
            }
        }
        else {
            if (Option.isDontCaptureGroup(this.env.option)) {
                this.fetchToken();
                node = this.parseSubExp(term);
                this.returnCode = 1;
                return node;
            }
            final EncloseNode en4 = new EncloseNode(this.env.option, false);
            final int num2 = this.env.addMemEntry();
            en4.regNum = num2;
            node = en4;
        }
        this.fetchToken();
        final Node target2 = this.parseSubExp(term);
        if (node.getType() == 7) {
            final AnchorNode an = (AnchorNode)node;
            an.setTarget(target2);
        }
        else {
            final EncloseNode en = (EncloseNode)node;
            en.setTarget(target2);
            if (en.type == 1) {
                this.env.setMemNode(en.regNum, node);
            }
        }
        this.returnCode = 0;
        return node;
    }
    
    private Node namedGroup2(final boolean listCapture) {
        final int nm = this.p;
        int num = this.fetchName(this.c, false);
        final int nameEnd = this.value;
        num = this.env.addMemEntry();
        if (listCapture && num >= 32) {
            this.newValueException("group number is too big for capture history");
        }
        this.regex.nameAdd(this.bytes, nm, nameEnd, num, this.syntax);
        final EncloseNode en = new EncloseNode(this.env.option, true);
        en.regNum = num;
        final Node node = en;
        if (listCapture) {
            this.env.captureHistory = BitStatus.bsOnAtSimple(this.env.captureHistory, num);
        }
        final ScanEnvironment env = this.env;
        ++env.numNamed;
        return node;
    }
    
    private int findStrPosition(final int[] s, final int n, final int from, final int to) {
        int p = from;
        int i = 0;
        while (p < to) {
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
                    if (this.bytes[this.nextChar] != 0) {
                        this.nextChar = q;
                    }
                    return p;
                }
            }
            p = q;
        }
        return -1;
    }
    
    private Node parseExp(final TokenType term) {
        if (this.token.type == term) {
            return new StringNode();
        }
        Node node = null;
        boolean group = false;
        switch (this.token.type) {
            case EOT:
            case ALT: {
                return new StringNode();
            }
            case SUBEXP_OPEN: {
                node = this.parseEnclose(TokenType.SUBEXP_CLOSE);
                if (this.returnCode == 1) {
                    group = true;
                    break;
                }
                if (this.returnCode == 2) {
                    final int prev = this.env.option;
                    final EncloseNode en = (EncloseNode)node;
                    this.env.option = en.option;
                    this.fetchToken();
                    final Node target = this.parseSubExp(term);
                    this.env.option = prev;
                    en.setTarget(target);
                    return node;
                }
                break;
            }
            case SUBEXP_CLOSE: {
                if (!this.syntax.allowUnmatchedCloseSubexp()) {
                    this.newSyntaxException("unmatched close parenthesis");
                }
                if (this.token.escaped) {
                    return this.parseExpTkRawByte(group);
                }
                return this.parseExpTkByte(group);
            }
            case STRING: {
                return this.parseExpTkByte(group);
            }
            case RAW_BYTE: {
                return this.parseExpTkRawByte(group);
            }
            case CODE_POINT: {
                final byte[] buf = new byte[7];
                final int num = this.enc.codeToMbc(this.token.getCode(), buf, 0);
                node = new StringNode(buf, 0, num);
                break;
            }
            case QUOTE_OPEN: {
                final int[] endOp = { this.syntax.metaCharTable.esc, 69 };
                final int qstart = this.p;
                int qend = this.findStrPosition(endOp, endOp.length, qstart, this.stop);
                if (qend == -1) {
                    qend = (this.nextChar = this.stop);
                }
                node = new StringNode(this.bytes, qstart, qend);
                this.p = this.nextChar;
                break;
            }
            case CHAR_TYPE: {
                switch (this.token.getPropCType()) {
                    case 12: {
                        node = new CTypeNode(this.token.getPropCType(), this.token.getPropNot());
                        break;
                    }
                    case 4:
                    case 9:
                    case 11: {
                        final CClassNode ccn = new CClassNode();
                        ccn.addCType(this.token.getPropCType(), false, this.env, this);
                        if (this.token.getPropNot()) {
                            ccn.setNot();
                        }
                        node = ccn;
                        break;
                    }
                    default: {
                        this.newInternalException("internal parser error (bug)");
                        break;
                    }
                }
                break;
            }
            case CHAR_PROPERTY: {
                node = this.parseCharProperty();
                break;
            }
            case CC_CC_OPEN: {
                final CClassNode cc = (CClassNode)(node = this.parseCharClass());
                if (Option.isIgnoreCase(this.env.option)) {
                    final ApplyCaseFoldArg arg = new ApplyCaseFoldArg(this.env, cc);
                    this.enc.applyAllCaseFold(this.env.caseFoldFlag, ApplyCaseFold.INSTANCE, arg);
                    if (arg.altRoot != null) {
                        node = ConsAltNode.newAltNode(node, arg.altRoot);
                    }
                    break;
                }
                break;
            }
            case ANYCHAR: {
                node = new AnyCharNode();
                break;
            }
            case ANYCHAR_ANYTIME: {
                node = new AnyCharNode();
                final QuantifierNode qn = new QuantifierNode(0, -1, false);
                qn.setTarget(node);
                node = qn;
                break;
            }
            case BACKREF: {
                final int[] array;
                if (this.token.getBackrefNum() > 1) {
                    this.token.getBackrefRefs();
                }
                else {
                    array = new int[] { this.token.getBackrefRef1() };
                }
                final int[] backRefs = array;
                node = new BackRefNode(this.token.getBackrefNum(), backRefs, this.token.getBackrefByName(), this.token.getBackrefExistLevel(), this.token.getBackrefLevel(), this.env);
                break;
            }
            case CALL: {
                int gNum = this.token.getCallGNum();
                if (gNum < 0) {
                    gNum = this.backrefRelToAbs(gNum);
                    if (gNum <= 0) {
                        this.newValueException("invalid backref number/name");
                    }
                }
                node = new CallNode(this.bytes, this.token.getCallNameP(), this.token.getCallNameEnd(), gNum);
                final ScanEnvironment env = this.env;
                ++env.numCall;
                break;
            }
            case ANCHOR: {
                node = new AnchorNode(this.token.getAnchor());
                break;
            }
            case OP_REPEAT:
            case INTERVAL: {
                if (!this.syntax.contextIndepRepeatOps()) {
                    return this.parseExpTkByte(group);
                }
                if (this.syntax.contextInvalidRepeatOps()) {
                    this.newSyntaxException("target of repeat operator is not specified");
                    break;
                }
                node = new StringNode();
                break;
            }
            default: {
                this.newInternalException("internal parser error (bug)");
                break;
            }
        }
        this.fetchToken();
        return this.parseExpRepeat(node, group);
    }
    
    private Node parseExpTkByte(final boolean group) {
        final StringNode node = new StringNode(this.bytes, this.token.backP, this.p);
        while (true) {
            this.fetchToken();
            if (this.token.type != TokenType.STRING) {
                break;
            }
            if (this.token.backP == node.end) {
                node.end = this.p;
            }
            else {
                node.cat(this.bytes, this.token.backP, this.p);
            }
        }
        return this.parseExpRepeat(node, group);
    }
    
    private Node parseExpTkRawByte(final boolean group) {
        final StringNode node = new StringNode((byte)this.token.getC());
        node.setRaw();
        for (int len = 1; len < this.enc.minLength() || len != this.enc.length(node.bytes, node.p, node.end); ++len) {
            this.fetchToken();
            if (this.token.type != TokenType.RAW_BYTE) {
                this.newValueException("too short multibyte code string");
            }
            node.cat((byte)this.token.getC());
        }
        this.fetchToken();
        node.clearRaw();
        return this.parseExpRepeat(node, group);
    }
    
    private Node parseExpRepeat(Node target, final boolean group) {
        while (this.token.type == TokenType.OP_REPEAT || this.token.type == TokenType.INTERVAL) {
            if (target.isInvalidQuantifier()) {
                this.newSyntaxException("target of repeat operator is invalid");
            }
            final QuantifierNode qtfr = new QuantifierNode(this.token.getRepeatLower(), this.token.getRepeatUpper(), this.token.type == TokenType.INTERVAL);
            qtfr.greedy = this.token.getRepeatGreedy();
            final int ret = qtfr.setQuantifier(target, group, this.env, this.bytes, this.getBegin(), this.getEnd());
            Node qn = qtfr;
            if (this.token.getRepeatPossessive()) {
                final EncloseNode en = new EncloseNode(4);
                en.setTarget(qn);
                qn = en;
            }
            if (ret == 0) {
                target = qn;
            }
            else if (ret == 2) {
                target = ConsAltNode.newListNode(target, null);
                final ConsAltNode tmp = ((ConsAltNode)target).setCdr(ConsAltNode.newListNode(qn, null));
                this.fetchToken();
                return this.parseExpRepeatForCar(target, tmp, group);
            }
            this.fetchToken();
        }
        return target;
    }
    
    private Node parseExpRepeatForCar(final Node top, final ConsAltNode target, final boolean group) {
        while (this.token.type == TokenType.OP_REPEAT || this.token.type == TokenType.INTERVAL) {
            if (target.car.isInvalidQuantifier()) {
                this.newSyntaxException("target of repeat operator is invalid");
            }
            final QuantifierNode qtfr = new QuantifierNode(this.token.getRepeatLower(), this.token.getRepeatUpper(), this.token.type == TokenType.INTERVAL);
            qtfr.greedy = this.token.getRepeatGreedy();
            final int ret = qtfr.setQuantifier(target.car, group, this.env, this.bytes, this.getBegin(), this.getEnd());
            Node qn = qtfr;
            if (this.token.getRepeatPossessive()) {
                final EncloseNode en = new EncloseNode(4);
                en.setTarget(qn);
                qn = en;
            }
            if (ret == 0) {
                target.setCar(qn);
            }
            else if (ret == 2 && !Parser.$assertionsDisabled) {
                throw new AssertionError();
            }
            this.fetchToken();
        }
        return top;
    }
    
    private Node parseBranch(final TokenType term) {
        Node node = this.parseExp(term);
        if (this.token.type == TokenType.EOT || this.token.type == term || this.token.type == TokenType.ALT) {
            return node;
        }
        ConsAltNode t;
        final ConsAltNode top = t = ConsAltNode.newListNode(node, null);
        while (this.token.type != TokenType.EOT && this.token.type != term && this.token.type != TokenType.ALT) {
            node = this.parseExp(term);
            if (node.getType() == 8) {
                t.setCdr((ConsAltNode)node);
                while (((ConsAltNode)node).cdr != null) {
                    node = ((ConsAltNode)node).cdr;
                }
                t = (ConsAltNode)node;
            }
            else {
                t.setCdr(ConsAltNode.newListNode(node, null));
                t = t.cdr;
            }
        }
        return top;
    }
    
    private Node parseSubExp(final TokenType term) {
        Node node = this.parseBranch(term);
        if (this.token.type == term) {
            return node;
        }
        if (this.token.type == TokenType.ALT) {
            ConsAltNode t;
            final ConsAltNode top = t = ConsAltNode.newAltNode(node, null);
            while (this.token.type == TokenType.ALT) {
                this.fetchToken();
                node = this.parseBranch(term);
                t.setCdr(ConsAltNode.newAltNode(node, null));
                t = t.cdr;
            }
            if (this.token.type != term) {
                this.parseSubExpError(term);
            }
            return top;
        }
        this.parseSubExpError(term);
        return null;
    }
    
    private void parseSubExpError(final TokenType term) {
        if (term == TokenType.SUBEXP_CLOSE) {
            this.newSyntaxException("end pattern with unmatched parenthesis");
        }
        else {
            this.newInternalException("internal parser error (bug)");
        }
    }
    
    private Node parseRegexp() {
        this.fetchToken();
        return this.parseSubExp(TokenType.EOT);
    }
    
    static {
        BRACKET_END = ":]".getBytes();
    }
}
