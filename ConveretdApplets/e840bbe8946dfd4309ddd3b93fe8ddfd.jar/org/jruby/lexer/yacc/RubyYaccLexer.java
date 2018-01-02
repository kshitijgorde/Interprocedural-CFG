// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.jcodings.specific.ASCIIEncoding;
import org.jcodings.specific.USASCIIEncoding;
import org.jcodings.specific.UTF8Encoding;
import org.jruby.ast.NthRefNode;
import org.jruby.ast.BackRefNode;
import org.joni.Matcher;
import org.jruby.util.StringSupport;
import org.jruby.ast.StrNode;
import java.io.IOException;
import org.jruby.ast.FixnumNode;
import org.jruby.ast.BignumNode;
import java.math.BigInteger;
import org.jruby.ast.FloatNode;
import org.joni.Regex;
import org.jruby.common.IRubyWarnings;
import org.jruby.parser.ParserSupport;
import java.util.HashMap;
import org.jruby.util.ByteList;
import org.jcodings.Encoding;

public class RubyYaccLexer
{
    public static final Encoding UTF8_ENCODING;
    public static final Encoding USASCII_ENCODING;
    public static final Encoding ASCII8BIT_ENCODING;
    private static ByteList END_MARKER;
    private static ByteList BEGIN_DOC_MARKER;
    private static ByteList END_DOC_MARKER;
    private static final HashMap<String, Keyword> map;
    private Encoding encoding;
    private int token;
    Object yaccValue;
    private LexerSource src;
    private ParserSupport parserSupport;
    private IRubyWarnings warnings;
    private LexState lex_state;
    private StringBuilder tokenBuffer;
    private StackState conditionState;
    private StackState cmdArgumentState;
    private StrTerm lex_strterm;
    public boolean commandStart;
    static final int EOF = -1;
    static final int STR_FUNC_ESCAPE = 1;
    static final int STR_FUNC_EXPAND = 2;
    static final int STR_FUNC_REGEXP = 4;
    static final int STR_FUNC_QWORDS = 8;
    static final int STR_FUNC_SYMBOL = 16;
    static final int STR_FUNC_INDENT = 32;
    private static final int str_squote = 0;
    private static final int str_dquote = 2;
    private static final int str_xquote = 2;
    private static final int str_regexp = 7;
    private static final int str_ssym = 16;
    private static final int str_dsym = 18;
    private boolean isOneEight;
    private int parenNest;
    private int leftParenBegin;
    private static final String magicString = "([^\\s'\":;]+)\\s*:\\s*(\"(?:\\\\.|[^\"])*\"|[^\"\\s;]+)[\\s;]*";
    private static final Regex magicRegexp;
    private static final String encodingString = "[cC][oO][dD][iI][nN][gG]\\s*[=:]\\s*([a-zA-Z0-9\\-_]+)";
    private static final Regex encodingRegexp;
    private byte[] mbcBuf;
    
    public Encoding getEncoding() {
        return this.encoding;
    }
    
    private int getFloatToken(final String number) {
        double d;
        try {
            d = Double.parseDouble(number);
        }
        catch (NumberFormatException e) {
            this.warnings.warn(IRubyWarnings.ID.FLOAT_OUT_OF_RANGE, this.getPosition(), "Float " + number + " out of range.", number);
            d = (number.startsWith("-") ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
        }
        this.yaccValue = new FloatNode(this.getPosition(), d);
        return 379;
    }
    
    private Object newBignumNode(final String value, final int radix) {
        return new BignumNode(this.getPosition(), new BigInteger(value, radix));
    }
    
    private Object newFixnumNode(final String value, final int radix) throws NumberFormatException {
        return new FixnumNode(this.getPosition(), Long.parseLong(value, radix));
    }
    
    public static Keyword getKeyword(final String str) {
        return RubyYaccLexer.map.get(str);
    }
    
    public int incrementParenNest() {
        return ++this.parenNest;
    }
    
    public int getLeftParenBegin() {
        return this.leftParenBegin;
    }
    
    public void setLeftParenBegin(final int value) {
        this.leftParenBegin = value;
    }
    
    public RubyYaccLexer() {
        this(true);
    }
    
    public RubyYaccLexer(final boolean isOneEight) {
        this.parserSupport = null;
        this.tokenBuffer = new StringBuilder(60);
        this.conditionState = new StackState();
        this.cmdArgumentState = new StackState();
        this.parenNest = 0;
        this.leftParenBegin = 0;
        this.mbcBuf = new byte[6];
        this.reset();
        this.isOneEight = isOneEight;
    }
    
    public final void reset() {
        this.token = 0;
        this.yaccValue = null;
        this.src = null;
        this.setState(null);
        this.resetStacks();
        this.lex_strterm = null;
        this.commandStart = true;
    }
    
    public boolean advance() throws IOException {
        final int yylex = this.yylex();
        this.token = yylex;
        return yylex != -1;
    }
    
    public int nextToken() throws IOException {
        this.token = this.yylex();
        return (this.token == -1) ? 0 : this.token;
    }
    
    public int token() {
        return this.token;
    }
    
    public StringBuilder getTokenBuffer() {
        return this.tokenBuffer;
    }
    
    public Object value() {
        return this.yaccValue;
    }
    
    public ISourcePosition getPosition(final ISourcePosition startPosition) {
        return this.src.getPosition(startPosition);
    }
    
    public ISourcePosition getPosition() {
        return this.src.getPosition();
    }
    
    public String getCurrentLine() {
        return this.src.getCurrentLine();
    }
    
    public void setParserSupport(final ParserSupport parserSupport) {
        this.parserSupport = parserSupport;
    }
    
    private void setEncoding(final ByteList name) {
        final Encoding newEncoding = this.parserSupport.getConfiguration().getEncodingService().loadEncoding(name);
        if (newEncoding == null) {
            throw new SyntaxException(SyntaxException.PID.UNKNOWN_ENCODING, this.getPosition(), null, "unknown encoding name: " + name.toString(), new Object[0]);
        }
        if (!newEncoding.isAsciiCompatible()) {
            throw new SyntaxException(SyntaxException.PID.NOT_ASCII_COMPATIBLE, this.getPosition(), null, name.toString() + " is not ASCII compatible", new Object[0]);
        }
        this.setEncoding(newEncoding);
    }
    
    public void setEncoding(final Encoding encoding) {
        this.encoding = encoding;
    }
    
    public void setSource(final LexerSource source) {
        this.src = source;
    }
    
    public StrTerm getStrTerm() {
        return this.lex_strterm;
    }
    
    public void setStrTerm(final StrTerm strterm) {
        this.lex_strterm = strterm;
    }
    
    public void resetStacks() {
        this.conditionState.reset();
        this.cmdArgumentState.reset();
    }
    
    public void setWarnings(final IRubyWarnings warnings) {
        this.warnings = warnings;
    }
    
    private void printState() {
        if (this.lex_state == null) {
            System.out.println("NULL");
        }
        else {
            System.out.println(this.lex_state);
        }
    }
    
    public void setState(final LexState state) {
        this.lex_state = state;
    }
    
    public StackState getCmdArgumentState() {
        return this.cmdArgumentState;
    }
    
    public boolean isOneEight() {
        return this.isOneEight;
    }
    
    public StackState getConditionState() {
        return this.conditionState;
    }
    
    public void setValue(final Object yaccValue) {
        this.yaccValue = yaccValue;
    }
    
    private boolean isNext_identchar() throws IOException {
        final int c = this.src.read();
        this.src.unread(c);
        return c != -1 && (Character.isLetterOrDigit(c) || c == 95);
    }
    
    private boolean isBEG() {
        return this.lex_state == LexState.EXPR_BEG || this.lex_state == LexState.EXPR_MID || this.lex_state == LexState.EXPR_CLASS || (!this.isOneEight && this.lex_state == LexState.EXPR_VALUE);
    }
    
    private boolean isEND() {
        return this.lex_state == LexState.EXPR_END || this.lex_state == LexState.EXPR_ENDARG || (!this.isOneEight && this.lex_state == LexState.EXPR_ENDFN);
    }
    
    private boolean isARG() {
        return this.lex_state == LexState.EXPR_ARG || this.lex_state == LexState.EXPR_CMDARG;
    }
    
    private void determineExpressionState() {
        switch (this.lex_state) {
            case EXPR_FNAME:
            case EXPR_DOT: {
                this.setState(LexState.EXPR_ARG);
                break;
            }
            default: {
                this.setState(LexState.EXPR_BEG);
                break;
            }
        }
    }
    
    private Object getInteger(final String value, final int radix) {
        try {
            return this.newFixnumNode(value, radix);
        }
        catch (NumberFormatException e) {
            return this.newBignumNode(value, radix);
        }
    }
    
    static boolean isHexChar(final int c) {
        return Character.isDigit(c) || (97 <= c && c <= 102) || (65 <= c && c <= 70);
    }
    
    static boolean isOctChar(final int c) {
        return 48 <= c && c <= 55;
    }
    
    public boolean isIdentifierChar(final int c) {
        return Character.isLetterOrDigit(c) || c == 95 || this.isMultiByteChar(c);
    }
    
    protected boolean isMultiByteChar(final int c) {
        return this.encoding.codeToMbcLength(c) != 1;
    }
    
    public StrNode createStrNode(final ISourcePosition position, final ByteList buffer, final int flags) {
        final Encoding bufferEncoding = buffer.getEncoding();
        final int codeRange = StringSupport.codeRangeScan(bufferEncoding, buffer);
        if ((flags & 0x4) == 0x0 && bufferEncoding.isAsciiCompatible()) {
            if (codeRange != 32) {
                if (this.getEncoding() == RubyYaccLexer.USASCII_ENCODING && bufferEncoding != RubyYaccLexer.UTF8_ENCODING) {
                    buffer.setEncoding(RubyYaccLexer.ASCII8BIT_ENCODING);
                }
            }
        }
        return new StrNode(position, buffer, codeRange);
    }
    
    private int parseQuote(int c) throws IOException {
        int begin;
        boolean shortHand;
        if (!Character.isLetterOrDigit(c)) {
            begin = c;
            c = 81;
            shortHand = true;
        }
        else {
            shortHand = false;
            begin = this.src.read();
            if (Character.isLetterOrDigit(begin)) {
                throw new SyntaxException(SyntaxException.PID.STRING_UNKNOWN_TYPE, this.getPosition(), this.getCurrentLine(), "unknown type of %string", new Object[0]);
            }
        }
        if (c == -1 || begin == -1) {
            throw new SyntaxException(SyntaxException.PID.STRING_HITS_EOF, this.getPosition(), this.getCurrentLine(), "unterminated quoted string meets end of file", new Object[0]);
        }
        int end = 0;
        switch (begin) {
            case 40: {
                end = 41;
                break;
            }
            case 91: {
                end = 93;
                break;
            }
            case 123: {
                end = 125;
                break;
            }
            case 60: {
                end = 62;
                break;
            }
            default: {
                end = begin;
                begin = 0;
                break;
            }
        }
        switch (c) {
            case 81: {
                this.lex_strterm = new StringTerm(2, begin, end);
                this.yaccValue = new Token("%" + (shortHand ? ("" + end) : ("" + c + begin)), this.getPosition());
                return 365;
            }
            case 113: {
                this.lex_strterm = new StringTerm(0, begin, end);
                this.yaccValue = new Token("%" + c + begin, this.getPosition());
                return 365;
            }
            case 87: {
                this.lex_strterm = new StringTerm(10, begin, end);
                do {
                    c = this.src.read();
                } while (Character.isWhitespace(c));
                this.src.unread(c);
                this.yaccValue = new Token("%" + c + begin, this.getPosition());
                return 368;
            }
            case 119: {
                this.lex_strterm = new StringTerm(8, begin, end);
                do {
                    c = this.src.read();
                } while (Character.isWhitespace(c));
                this.src.unread(c);
                this.yaccValue = new Token("%" + c + begin, this.getPosition());
                return 369;
            }
            case 120: {
                this.lex_strterm = new StringTerm(2, begin, end);
                this.yaccValue = new Token("%" + c + begin, this.getPosition());
                return 366;
            }
            case 114: {
                this.lex_strterm = new StringTerm(7, begin, end);
                this.yaccValue = new Token("%" + c + begin, this.getPosition());
                return 367;
            }
            case 115: {
                this.lex_strterm = new StringTerm(16, begin, end);
                this.setState(LexState.EXPR_FNAME);
                this.yaccValue = new Token("%" + c + begin, this.getPosition());
                return 364;
            }
            default: {
                throw new SyntaxException(SyntaxException.PID.STRING_UNKNOWN_TYPE, this.getPosition(), this.getCurrentLine(), "Unknown type of %string. Expected 'Q', 'q', 'w', 'x', 'r' or any non letter character, but found '" + c + "'.", new Object[0]);
            }
        }
    }
    
    private int hereDocumentIdentifier() throws IOException {
        int c = this.src.read();
        int func = 0;
        if (c == 45) {
            c = this.src.read();
            func = 32;
        }
        ByteList markerValue;
        int term;
        if (c == 39 || c == 34 || c == 96) {
            if (c == 39) {
                func |= 0x0;
            }
            else if (c == 34) {
                func |= 0x2;
            }
            else {
                func |= 0x2;
            }
            markerValue = new ByteList();
            term = c;
            while ((c = this.src.read()) != -1 && c != term) {
                markerValue.append(c);
            }
            if (c == -1) {
                throw new SyntaxException(SyntaxException.PID.STRING_MARKER_MISSING, this.getPosition(), this.getCurrentLine(), "unterminated here document identifier", new Object[0]);
            }
        }
        else {
            if (!this.isIdentifierChar(c)) {
                this.src.unread(c);
                if ((func & 0x20) != 0x0) {
                    this.src.unread(45);
                }
                return 0;
            }
            markerValue = new ByteList();
            term = 34;
            func |= 0x2;
            do {
                markerValue.append(c);
            } while ((c = this.src.read()) != -1 && this.isIdentifierChar(c));
            this.src.unread(c);
        }
        final ByteList lastLine = this.src.readLineBytes();
        lastLine.append(10);
        this.lex_strterm = new HeredocTerm(markerValue, func, lastLine);
        if (term == 96) {
            this.yaccValue = new Token("`", this.getPosition());
            return 366;
        }
        this.yaccValue = new Token("\"", this.getPosition());
        this.getPosition();
        return 365;
    }
    
    private void arg_ambiguous() {
        if (this.warnings.isVerbose()) {
            this.warnings.warning(IRubyWarnings.ID.AMBIGUOUS_ARGUMENT, this.getPosition(), "Ambiguous first argument; make sure.", new Object[0]);
        }
    }
    
    private int magicCommentMarker(final ByteList str, final int begin) {
        int i = begin;
        final int len = str.length();
        while (i < len) {
            switch (str.charAt(i)) {
                case '-': {
                    if (i >= 2 && str.charAt(i - 1) == '*' && str.charAt(i - 2) == '-') {
                        return i + 1;
                    }
                    i += 2;
                    continue;
                }
                case '*': {
                    if (i + 1 >= len) {
                        return -1;
                    }
                    if (str.charAt(i + 1) != '-') {
                        i += 4;
                        continue;
                    }
                    if (str.charAt(i - 1) != '-') {
                        i += 2;
                        continue;
                    }
                    return i + 2;
                }
                default: {
                    i += 3;
                    continue;
                }
            }
        }
        return -1;
    }
    
    private boolean magicCommentSpecialChar(final char c) {
        switch (c) {
            case '\"':
            case '\'':
            case ':':
            case ';': {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected boolean parseMagicComment(final ByteList magicLine) throws IOException {
        final int length = magicLine.length();
        if (length <= 7) {
            return false;
        }
        final int beg = this.magicCommentMarker(magicLine, 0);
        if (beg < 0) {
            return false;
        }
        final int end = this.magicCommentMarker(magicLine, beg);
        if (end < 0) {
            return false;
        }
        final int realSize = magicLine.getRealSize();
        final int begin = magicLine.getBegin();
        final Matcher matcher = RubyYaccLexer.magicRegexp.matcher(magicLine.getUnsafeBytes(), begin, begin + realSize);
        final int result = matcher.search(begin, begin + realSize, 0);
        if (result < 0) {
            return false;
        }
        final int[] begs = matcher.getRegion().beg;
        final int[] ends = matcher.getRegion().end;
        final String name = magicLine.subSequence(begs[1], ends[1]).toString();
        if (!name.equalsIgnoreCase("encoding")) {
            return false;
        }
        this.setEncoding(new ByteList(magicLine.getUnsafeBytes(), begs[2], ends[2] - begs[2]));
        return true;
    }
    
    protected void handleFileEncodingComment(final ByteList encodingLine) throws IOException {
        final int realSize = encodingLine.getRealSize();
        final int begin = encodingLine.getBegin();
        final Matcher matcher = RubyYaccLexer.encodingRegexp.matcher(encodingLine.getUnsafeBytes(), begin, begin + realSize);
        final int result = matcher.search(begin, begin + realSize, 1);
        if (result < 0) {
            return;
        }
        final int[] begs = matcher.getRegion().beg;
        final int[] ends = matcher.getRegion().end;
        this.setEncoding(new ByteList(encodingLine.getUnsafeBytes(), begs[1], ends[1] - begs[1]));
    }
    
    protected int readComment() throws IOException {
        boolean handledMagicComment = false;
        if (!this.isOneEight() && this.getPosition().getLine() == 0) {
            if (this.src.peek(33)) {
                final int c = this.src.skipUntil(10);
                if (!this.src.peek(35)) {
                    return c;
                }
            }
            final ByteList commentLine = this.src.readUntil('\n');
            handledMagicComment = this.parseMagicComment(commentLine);
            if (!handledMagicComment) {
                this.handleFileEncodingComment(commentLine);
            }
            return 0;
        }
        return this.src.skipUntil(10);
    }
    
    private void printToken(final int token) {
        switch (token) {
            case 256: {
                System.err.print("yyErrorCode,");
                break;
            }
            case 257: {
                System.err.print("kClass,");
                break;
            }
            case 258: {
                System.err.print("kModule,");
                break;
            }
            case 259: {
                System.err.print("kDEF,");
                break;
            }
            case 260: {
                System.err.print("kUNDEF,");
                break;
            }
            case 261: {
                System.err.print("kBEGIN,");
                break;
            }
            case 262: {
                System.err.print("kRESCUE,");
                break;
            }
            case 263: {
                System.err.print("kENSURE,");
                break;
            }
            case 264: {
                System.err.print("kEND,");
                break;
            }
            case 265: {
                System.err.print("kIF,");
                break;
            }
            case 266: {
                System.err.print("kUNLESS,");
                break;
            }
            case 267: {
                System.err.print("kTHEN,");
                break;
            }
            case 268: {
                System.err.print("kELSIF,");
                break;
            }
            case 269: {
                System.err.print("kELSE,");
                break;
            }
            case 270: {
                System.err.print("kCASE,");
                break;
            }
            case 271: {
                System.err.print("kWHEN,");
                break;
            }
            case 272: {
                System.err.print("kWHILE,");
                break;
            }
            case 273: {
                System.err.print("kUNTIL,");
                break;
            }
            case 274: {
                System.err.print("kFOR,");
                break;
            }
            case 275: {
                System.err.print("kBREAK,");
                break;
            }
            case 276: {
                System.err.print("kNEXT,");
                break;
            }
            case 277: {
                System.err.print("kREDO,");
                break;
            }
            case 278: {
                System.err.print("kRETRY,");
                break;
            }
            case 279: {
                System.err.print("kIN,");
                break;
            }
            case 280: {
                System.err.print("kDO,");
                break;
            }
            case 281: {
                System.err.print("kDO_COND,");
                break;
            }
            case 282: {
                System.err.print("kDO_BLOCK,");
                break;
            }
            case 283: {
                System.err.print("kRETURN,");
                break;
            }
            case 284: {
                System.err.print("kYIELD,");
                break;
            }
            case 285: {
                System.err.print("kSUPER,");
                break;
            }
            case 286: {
                System.err.print("kSELF,");
                break;
            }
            case 287: {
                System.err.print("kNIL,");
                break;
            }
            case 288: {
                System.err.print("kTRUE,");
                break;
            }
            case 289: {
                System.err.print("kFALSE,");
                break;
            }
            case 290: {
                System.err.print("kAND,");
                break;
            }
            case 291: {
                System.err.print("kOR,");
                break;
            }
            case 292: {
                System.err.print("kNOT,");
                break;
            }
            case 293: {
                System.err.print("kIF_MOD,");
                break;
            }
            case 294: {
                System.err.print("kUNLESS_MOD,");
                break;
            }
            case 295: {
                System.err.print("kWHILE_MOD,");
                break;
            }
            case 296: {
                System.err.print("kUNTIL_MOD,");
                break;
            }
            case 297: {
                System.err.print("kRESCUE_MOD,");
                break;
            }
            case 298: {
                System.err.print("kALIAS,");
                break;
            }
            case 299: {
                System.err.print("kDEFINED,");
                break;
            }
            case 300: {
                System.err.print("klBEGIN,");
                break;
            }
            case 301: {
                System.err.print("klEND,");
                break;
            }
            case 302: {
                System.err.print("k__LINE__,");
                break;
            }
            case 303: {
                System.err.print("k__FILE__,");
                break;
            }
            case 304: {
                System.err.print("k__ENCODING__,");
                break;
            }
            case 305: {
                System.err.print("kDO_LAMBDA,");
                break;
            }
            case 306: {
                System.err.print("tIDENTIFIER[" + this.value() + "],");
                break;
            }
            case 307: {
                System.err.print("tFID[" + this.value() + "],");
                break;
            }
            case 308: {
                System.err.print("tGVAR[" + this.value() + "],");
                break;
            }
            case 309: {
                System.err.print("tIVAR[" + this.value() + "],");
                break;
            }
            case 310: {
                System.err.print("tCONSTANT[" + this.value() + "],");
                break;
            }
            case 311: {
                System.err.print("tCVAR,");
                break;
            }
            case 378: {
                System.err.print("tINTEGER,");
                break;
            }
            case 379: {
                System.err.print("tFLOAT,");
                break;
            }
            case 377: {
                System.err.print("tSTRING_CONTENT[" + ((StrNode)this.value()).getValue().toString() + "],");
                break;
            }
            case 365: {
                System.err.print("tSTRING_BEG,");
                break;
            }
            case 372: {
                System.err.print("tSTRING_END,");
                break;
            }
            case 370: {
                System.err.print("STRING_DBEG,");
                break;
            }
            case 371: {
                System.err.print("tSTRING_DVAR,");
                break;
            }
            case 366: {
                System.err.print("tXSTRING_BEG,");
                break;
            }
            case 367: {
                System.err.print("tREGEXP_BEG,");
                break;
            }
            case 380: {
                System.err.print("tREGEXP_END,");
                break;
            }
            case 368: {
                System.err.print("tWORDS_BEG,");
                break;
            }
            case 369: {
                System.err.print("tQWORDS_BEG,");
                break;
            }
            case 376: {
                System.err.print("tBACK_REF,");
                break;
            }
            case 363: {
                System.err.print("tBACK_REF2,");
                break;
            }
            case 375: {
                System.err.print("tNTH_REF,");
                break;
            }
            case 314: {
                System.err.print("tUPLUS");
                break;
            }
            case 315: {
                System.err.print("tUMINUS,");
                break;
            }
            case 317: {
                System.err.print("tPOW,");
                break;
            }
            case 318: {
                System.err.print("tCMP,");
                break;
            }
            case 319: {
                System.err.print("tEQ,");
                break;
            }
            case 320: {
                System.err.print("tEQQ,");
                break;
            }
            case 321: {
                System.err.print("tNEQ,");
                break;
            }
            case 322: {
                System.err.print("tGEQ,");
                break;
            }
            case 323: {
                System.err.print("tLEQ,");
                break;
            }
            case 324: {
                System.err.print("tANDOP,");
                break;
            }
            case 325: {
                System.err.print("tOROP,");
                break;
            }
            case 326: {
                System.err.print("tMATCH,");
                break;
            }
            case 327: {
                System.err.print("tNMATCH,");
                break;
            }
            case 328: {
                System.err.print("tDOT,");
                break;
            }
            case 329: {
                System.err.print("tDOT2,");
                break;
            }
            case 330: {
                System.err.print("tDOT3,");
                break;
            }
            case 331: {
                System.err.print("tAREF,");
                break;
            }
            case 332: {
                System.err.print("tASET,");
                break;
            }
            case 333: {
                System.err.print("tLSHFT,");
                break;
            }
            case 334: {
                System.err.print("tRSHFT,");
                break;
            }
            case 335: {
                System.err.print("tCOLON2,");
                break;
            }
            case 336: {
                System.err.print("tCOLON3,");
                break;
            }
            case 337: {
                System.err.print("tOP_ASGN,");
                break;
            }
            case 338: {
                System.err.print("tASSOC,");
                break;
            }
            case 339: {
                System.err.print("tLPAREN,");
                break;
            }
            case 340: {
                System.err.print("tLPAREN2,");
                break;
            }
            case 342: {
                System.err.print("tLPAREN_ARG,");
                break;
            }
            case 343: {
                System.err.print("tLBRACK,");
                break;
            }
            case 344: {
                System.err.print("tRBRACK,");
                break;
            }
            case 345: {
                System.err.print("tLBRACE,");
                break;
            }
            case 346: {
                System.err.print("tLBRACE_ARG,");
                break;
            }
            case 347: {
                System.err.print("tSTAR,");
                break;
            }
            case 348: {
                System.err.print("tSTAR2,");
                break;
            }
            case 349: {
                System.err.print("tAMPER,");
                break;
            }
            case 350: {
                System.err.print("tAMPER2,");
                break;
            }
            case 364: {
                System.err.print("tSYMBEG,");
                break;
            }
            case 351: {
                System.err.print("tTILDE,");
                break;
            }
            case 352: {
                System.err.print("tPERCENT,");
                break;
            }
            case 353: {
                System.err.print("tDIVIDE,");
                break;
            }
            case 354: {
                System.err.print("tPLUS,");
                break;
            }
            case 355: {
                System.err.print("tMINUS,");
                break;
            }
            case 356: {
                System.err.print("tLT,");
                break;
            }
            case 357: {
                System.err.print("tGT,");
                break;
            }
            case 360: {
                System.err.print("tCARET,");
                break;
            }
            case 359: {
                System.err.print("tBANG,");
                break;
            }
            case 361: {
                System.err.print("tTLCURLY,");
                break;
            }
            case 362: {
                System.err.print("tRCURLY,");
                break;
            }
            case 358: {
                System.err.print("tTPIPE,");
                break;
            }
            case 373: {
                System.err.print("tLAMBDA,");
                break;
            }
            case 374: {
                System.err.print("tLAMBEG,");
                break;
            }
            case 341: {
                System.err.print("tRPAREN,");
                break;
            }
            case 10: {
                System.err.println("NL");
                break;
            }
            case -1: {
                System.out.println("EOF");
                break;
            }
            default: {
                System.err.print("'" + (char)token + "',");
                break;
            }
        }
    }
    
    private int yylex2() throws IOException {
        final int currentToken = this.yylex2();
        this.printToken(currentToken);
        return currentToken;
    }
    
    private int yylex() throws IOException {
        boolean spaceSeen = false;
        if (this.lex_strterm != null) {
            final int tok = this.lex_strterm.parseString(this, this.src);
            if (tok == 372 || tok == 380) {
                this.lex_strterm = null;
                this.setState(LexState.EXPR_END);
            }
            return tok;
        }
        final boolean commandState = this.commandStart;
        this.commandStart = false;
    Label_0060:
        while (true) {
            while (true) {
                int c = this.src.read();
                switch (c) {
                    case -1:
                    case 0:
                    case 4:
                    case 26: {
                        return -1;
                    }
                    case 9:
                    case 11:
                    case 12:
                    case 13:
                    case 32: {
                        this.getPosition();
                        spaceSeen = true;
                        continue;
                    }
                    case 35: {
                        if (this.readComment() == -1) {
                            return -1;
                        }
                    }
                    case 10: {
                        if (this.isOneEight) {
                            while ((c = this.src.read()) == 10) {}
                        }
                        else {
                            switch (this.lex_state) {
                                case EXPR_FNAME:
                                case EXPR_DOT:
                                case EXPR_BEG:
                                case EXPR_CLASS:
                                case EXPR_VALUE: {
                                    continue;
                                }
                                default: {
                                    boolean done = false;
                                    while (!done) {
                                        c = this.src.read();
                                        switch (c) {
                                            case 9:
                                            case 11:
                                            case 12:
                                            case 13:
                                            case 32: {
                                                spaceSeen = true;
                                                continue;
                                            }
                                            case 46: {
                                                if ((c = this.src.read()) != 46) {
                                                    this.src.unread(c);
                                                    this.src.unread(46);
                                                    continue Label_0060;
                                                }
                                                break;
                                            }
                                        }
                                        done = true;
                                    }
                                    break;
                                }
                            }
                        }
                        if (c == -1) {
                            return -1;
                        }
                        this.src.unread(c);
                        this.getPosition();
                        switch (this.lex_state) {
                            case EXPR_FNAME:
                            case EXPR_DOT:
                            case EXPR_BEG:
                            case EXPR_CLASS: {
                                continue;
                            }
                            default: {
                                this.commandStart = true;
                                this.setState(LexState.EXPR_BEG);
                                return 10;
                            }
                        }
                        break;
                    }
                    case 42: {
                        return this.star(spaceSeen);
                    }
                    case 33: {
                        return this.bang();
                    }
                    case 61: {
                        if (!this.src.wasBeginOfLine() || !this.src.matchMarker(RubyYaccLexer.BEGIN_DOC_MARKER, false, false)) {
                            break Label_0060;
                        }
                        c = this.src.read();
                        if (!Character.isWhitespace(c)) {
                            this.src.unread(c);
                            break Label_0060;
                        }
                        this.src.unread(c);
                        while (true) {
                            for (c = this.src.read(); c == 10; c = this.src.read()) {}
                            if (c == -1) {
                                throw new SyntaxException(SyntaxException.PID.STRING_HITS_EOF, this.getPosition(), this.getCurrentLine(), "embedded document meets end of file", new Object[0]);
                            }
                            if (c != 61) {
                                continue;
                            }
                            if (this.src.wasBeginOfLine() && this.src.matchMarker(RubyYaccLexer.END_DOC_MARKER, false, false)) {
                                final ByteList list = this.src.readLineBytes();
                                this.src.unread(10);
                                continue Label_0060;
                            }
                        }
                        break;
                    }
                    case 60: {
                        return this.lessThan(spaceSeen);
                    }
                    case 62: {
                        return this.greaterThan();
                    }
                    case 34: {
                        return this.doubleQuote();
                    }
                    case 96: {
                        return this.backtick(commandState);
                    }
                    case 39: {
                        return this.singleQuote();
                    }
                    case 63: {
                        return this.questionMark();
                    }
                    case 38: {
                        return this.ampersand(spaceSeen);
                    }
                    case 124: {
                        return this.pipe();
                    }
                    case 43: {
                        return this.plus(spaceSeen);
                    }
                    case 45: {
                        return this.minus(spaceSeen);
                    }
                    case 46: {
                        return this.dot();
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
                        return this.parseNumber(c);
                    }
                    case 41: {
                        return this.rightParen();
                    }
                    case 93: {
                        return this.rightBracket();
                    }
                    case 125: {
                        return this.rightCurly();
                    }
                    case 58: {
                        return this.colon(spaceSeen);
                    }
                    case 47: {
                        return this.slash(spaceSeen);
                    }
                    case 94: {
                        return this.caret();
                    }
                    case 59: {
                        this.commandStart = true;
                        if (!this.isOneEight) {
                            this.setState(LexState.EXPR_BEG);
                            this.yaccValue = new Token(";", this.getPosition());
                            return 59;
                        }
                        return this.comma(c);
                    }
                    case 44: {
                        return this.comma(c);
                    }
                    case 126: {
                        return this.tilde();
                    }
                    case 40: {
                        return this.leftParen(spaceSeen);
                    }
                    case 91: {
                        return this.leftBracket(spaceSeen);
                    }
                    case 123: {
                        return this.leftCurly();
                    }
                    case 92: {
                        c = this.src.read();
                        if (c == 10) {
                            spaceSeen = true;
                            continue;
                        }
                        this.src.unread(c);
                        this.yaccValue = new Token("\\", this.getPosition());
                        return 92;
                    }
                    case 37: {
                        return this.percent(spaceSeen);
                    }
                    case 36: {
                        return this.dollar();
                    }
                    case 64: {
                        return this.at();
                    }
                    case 95: {
                        if (this.src.wasBeginOfLine() && this.src.matchMarker(RubyYaccLexer.END_MARKER, false, true)) {
                            this.parserSupport.getResult().setEndOffset(this.src.getOffset());
                            return -1;
                        }
                        return this.identifier(c, commandState);
                    }
                    default: {
                        return this.identifier(c, commandState);
                    }
                }
            }
            break;
        }
        this.determineExpressionState();
        int c = this.src.read();
        if (c == 61) {
            c = this.src.read();
            if (c == 61) {
                this.yaccValue = new Token("===", this.getPosition());
                return 320;
            }
            this.src.unread(c);
            this.yaccValue = new Token("==", this.getPosition());
            return 319;
        }
        else {
            if (c == 126) {
                this.yaccValue = new Token("=~", this.getPosition());
                return 326;
            }
            if (c == 62) {
                this.yaccValue = new Token("=>", this.getPosition());
                return 338;
            }
            this.src.unread(c);
            this.yaccValue = new Token("=", this.getPosition());
            return 61;
        }
        return this.comma(c);
    }
    
    private int identifierToken(final LexState last_state, final int result, final String value) {
        if (result == 306 && last_state != LexState.EXPR_DOT && this.parserSupport.getCurrentScope().isDefined(value) >= 0) {
            this.setState(LexState.EXPR_END);
        }
        this.yaccValue = new Token(value, result, this.getPosition());
        return result;
    }
    
    private int getIdentifier(int first) throws IOException {
        if (this.isMultiByteChar(first)) {
            first = this.src.readCodepoint(first, this.encoding);
        }
        if (!this.isIdentifierChar(first)) {
            return first;
        }
        this.tokenBuffer.append((char)first);
        int c;
        for (c = this.src.read(); c != -1; c = this.src.read()) {
            if (this.isMultiByteChar(c)) {
                c = this.src.readCodepoint(c, this.encoding);
            }
            if (!this.isIdentifierChar(c)) {
                break;
            }
            this.tokenBuffer.append((char)c);
        }
        this.src.unread(c);
        return first;
    }
    
    private int ampersand(final boolean spaceSeen) throws IOException {
        int c = this.src.read();
        switch (c) {
            case 38: {
                this.setState(LexState.EXPR_BEG);
                if ((c = this.src.read()) == 61) {
                    this.yaccValue = new Token("&&", this.getPosition());
                    this.setState(LexState.EXPR_BEG);
                    return 337;
                }
                this.src.unread(c);
                this.yaccValue = new Token("&&", this.getPosition());
                return 324;
            }
            case 61: {
                this.yaccValue = new Token("&", this.getPosition());
                this.setState(LexState.EXPR_BEG);
                return 337;
            }
            default: {
                this.src.unread(c);
                final ISourcePosition tmpPosition = this.getPosition();
                if (this.isARG() && spaceSeen && !Character.isWhitespace(c)) {
                    if (this.warnings.isVerbose()) {
                        this.warnings.warning(IRubyWarnings.ID.ARGUMENT_AS_PREFIX, tmpPosition, "`&' interpreted as argument prefix", "&");
                    }
                    c = 349;
                }
                else if (this.isBEG()) {
                    c = 349;
                }
                else {
                    c = 350;
                }
                this.determineExpressionState();
                this.yaccValue = new Token("&", tmpPosition);
                return c;
            }
        }
    }
    
    private int at() throws IOException {
        int c = this.src.read();
        this.tokenBuffer.setLength(0);
        this.tokenBuffer.append('@');
        int result;
        if (c == 64) {
            this.tokenBuffer.append('@');
            c = this.src.read();
            result = 311;
        }
        else {
            result = 309;
        }
        if (Character.isDigit(c)) {
            if (this.tokenBuffer.length() == 1) {
                throw new SyntaxException(SyntaxException.PID.IVAR_BAD_NAME, this.getPosition(), this.getCurrentLine(), "`@" + c + "' is not allowed as an instance variable name", new Object[0]);
            }
            throw new SyntaxException(SyntaxException.PID.CVAR_BAD_NAME, this.getPosition(), this.getCurrentLine(), "`@@" + c + "' is not allowed as a class variable name", new Object[0]);
        }
        else {
            if (!this.isIdentifierChar(c)) {
                this.src.unread(c);
                this.yaccValue = new Token("@", this.getPosition());
                return 64;
            }
            this.getIdentifier(c);
            final LexState last_state = this.lex_state;
            this.setState(LexState.EXPR_END);
            return this.identifierToken(last_state, result, this.tokenBuffer.toString().intern());
        }
    }
    
    private int backtick(final boolean commandState) throws IOException {
        this.yaccValue = new Token("`", this.getPosition());
        switch (this.lex_state) {
            case EXPR_FNAME: {
                this.setState(this.isOneEight ? LexState.EXPR_END : LexState.EXPR_ENDFN);
                return 363;
            }
            case EXPR_DOT: {
                this.setState(commandState ? LexState.EXPR_CMDARG : LexState.EXPR_ARG);
                return 363;
            }
            default: {
                this.lex_strterm = new StringTerm(2, 0, 96);
                return 366;
            }
        }
    }
    
    private int bang() throws IOException {
        final int c = this.src.read();
        if (!this.isOneEight && (this.lex_state == LexState.EXPR_FNAME || this.lex_state == LexState.EXPR_DOT)) {
            this.setState(LexState.EXPR_ARG);
            if (c == 64) {
                this.yaccValue = new Token("!", this.getPosition());
                return 359;
            }
        }
        else {
            this.setState(LexState.EXPR_BEG);
        }
        switch (c) {
            case 61: {
                this.yaccValue = new Token("!=", this.getPosition());
                return 321;
            }
            case 126: {
                this.yaccValue = new Token("!~", this.getPosition());
                return 327;
            }
            default: {
                this.src.unread(c);
                this.yaccValue = new Token("!", this.getPosition());
                return 359;
            }
        }
    }
    
    private int caret() throws IOException {
        final int c = this.src.read();
        if (c == 61) {
            this.setState(LexState.EXPR_BEG);
            this.yaccValue = new Token("^", this.getPosition());
            return 337;
        }
        this.determineExpressionState();
        this.src.unread(c);
        this.yaccValue = new Token("^", this.getPosition());
        return 360;
    }
    
    private int colon(final boolean spaceSeen) throws IOException {
        final int c = this.src.read();
        if (c == 58) {
            if (this.isBEG() || this.lex_state == LexState.EXPR_CLASS || (this.isARG() && spaceSeen)) {
                this.setState(LexState.EXPR_BEG);
                this.yaccValue = new Token("::", this.getPosition());
                return 336;
            }
            this.setState(LexState.EXPR_DOT);
            this.yaccValue = new Token(":", this.getPosition());
            return 335;
        }
        else {
            if (this.isEND() || Character.isWhitespace(c)) {
                this.src.unread(c);
                this.setState(LexState.EXPR_BEG);
                this.yaccValue = new Token(":", this.getPosition());
                return 58;
            }
            switch (c) {
                case 39: {
                    this.lex_strterm = new StringTerm(16, 0, c);
                    break;
                }
                case 34: {
                    this.lex_strterm = new StringTerm(18, 0, c);
                    break;
                }
                default: {
                    this.src.unread(c);
                    break;
                }
            }
            this.setState(LexState.EXPR_FNAME);
            this.yaccValue = new Token(":", this.getPosition());
            return 364;
        }
    }
    
    private int comma(final int c) throws IOException {
        this.setState(LexState.EXPR_BEG);
        this.yaccValue = new Token(",", this.getPosition());
        return c;
    }
    
    private int doKeyword(final LexState state) {
        this.commandStart = true;
        if (!this.isOneEight && this.leftParenBegin > 0 && this.leftParenBegin == this.parenNest) {
            this.leftParenBegin = 0;
            --this.parenNest;
            return 305;
        }
        if (this.conditionState.isInState()) {
            return 281;
        }
        if (state != LexState.EXPR_CMDARG && this.cmdArgumentState.isInState()) {
            return 282;
        }
        if (state == LexState.EXPR_ENDARG || (!this.isOneEight && state == LexState.EXPR_BEG)) {
            return 282;
        }
        return 280;
    }
    
    private int dollar() throws IOException {
        LexState last_state = this.lex_state;
        this.setState(LexState.EXPR_END);
        int c = this.src.read();
        switch (c) {
            case 95: {
                c = this.src.read();
                if (this.isIdentifierChar(c)) {
                    this.tokenBuffer.setLength(0);
                    this.tokenBuffer.append("$_");
                    this.getIdentifier(c);
                    last_state = this.lex_state;
                    this.setState(LexState.EXPR_END);
                    return this.identifierToken(last_state, 308, this.tokenBuffer.toString().intern());
                }
                this.src.unread(c);
                c = 95;
            }
            case 33:
            case 34:
            case 36:
            case 42:
            case 44:
            case 46:
            case 47:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 92:
            case 126: {
                this.yaccValue = new Token("$" + (char)c, 308, this.getPosition());
                return 308;
            }
            case 45: {
                this.tokenBuffer.setLength(0);
                this.tokenBuffer.append('$');
                this.tokenBuffer.append((char)c);
                c = this.src.read();
                if (this.isIdentifierChar(c)) {
                    this.tokenBuffer.append((char)c);
                }
                else {
                    this.src.unread(c);
                }
                this.yaccValue = new Token(this.tokenBuffer.toString(), 308, this.getPosition());
                return 308;
            }
            case 38:
            case 39:
            case 43:
            case 96: {
                if (last_state == LexState.EXPR_FNAME) {
                    this.yaccValue = new Token("$" + (char)c, 308, this.getPosition());
                    return 308;
                }
                this.yaccValue = new BackRefNode(this.getPosition(), c);
                return 376;
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
                this.tokenBuffer.setLength(0);
                this.tokenBuffer.append('$');
                do {
                    this.tokenBuffer.append((char)c);
                    c = this.src.read();
                } while (Character.isDigit(c));
                this.src.unread(c);
                if (last_state == LexState.EXPR_FNAME) {
                    this.yaccValue = new Token(this.tokenBuffer.toString(), 308, this.getPosition());
                    return 308;
                }
                this.yaccValue = new NthRefNode(this.getPosition(), Integer.parseInt(this.tokenBuffer.substring(1)));
                return 375;
            }
            case 48: {
                this.setState(LexState.EXPR_END);
                return this.identifierToken(last_state, 308, ("$" + (char)c).intern());
            }
            default: {
                if (!this.isIdentifierChar(c)) {
                    this.src.unread(c);
                    this.yaccValue = new Token("$", this.getPosition());
                    return 36;
                }
                this.tokenBuffer.setLength(0);
                this.tokenBuffer.append('$');
                this.getIdentifier(c);
                last_state = this.lex_state;
                this.setState(LexState.EXPR_END);
                return this.identifierToken(last_state, 308, this.tokenBuffer.toString().intern());
            }
        }
    }
    
    private int dot() throws IOException {
        this.setState(LexState.EXPR_BEG);
        int c;
        if ((c = this.src.read()) == 46) {
            if ((c = this.src.read()) == 46) {
                this.yaccValue = new Token("...", this.getPosition());
                return 330;
            }
            this.src.unread(c);
            this.yaccValue = new Token("..", this.getPosition());
            return 329;
        }
        else {
            this.src.unread(c);
            if (Character.isDigit(c)) {
                throw new SyntaxException(SyntaxException.PID.FLOAT_MISSING_ZERO, this.getPosition(), this.getCurrentLine(), "no .<digit> floating literal anymore; put 0 before dot", new Object[0]);
            }
            this.setState(LexState.EXPR_DOT);
            this.yaccValue = new Token(".", this.getPosition());
            return 328;
        }
    }
    
    private int doubleQuote() throws IOException {
        this.lex_strterm = new StringTerm(2, 0, 34);
        this.yaccValue = new Token("\"", this.getPosition());
        return 365;
    }
    
    private int greaterThan() throws IOException {
        this.determineExpressionState();
        int c = this.src.read();
        switch (c) {
            case 61: {
                this.yaccValue = new Token(">=", this.getPosition());
                return 322;
            }
            case 62: {
                if ((c = this.src.read()) == 61) {
                    this.setState(LexState.EXPR_BEG);
                    this.yaccValue = new Token(">>", this.getPosition());
                    return 337;
                }
                this.src.unread(c);
                this.yaccValue = new Token(">>", this.getPosition());
                return 334;
            }
            default: {
                this.src.unread(c);
                this.yaccValue = new Token(">", this.getPosition());
                return 357;
            }
        }
    }
    
    private int identifier(int c, final boolean commandState) throws IOException {
        if (!this.isIdentifierChar(c)) {
            final String badChar = "\\" + Integer.toOctalString(c & 0xFF);
            throw new SyntaxException(SyntaxException.PID.CHARACTER_BAD, this.getPosition(), "Invalid char `" + badChar + "' ('" + (char)c + "') in expression", badChar, new Object[0]);
        }
        this.tokenBuffer.setLength(0);
        final int first = this.getIdentifier(c);
        c = this.src.read();
        boolean lastBangOrPredicate = false;
        if (c == 33 || c == 63) {
            if (!this.src.peek(61)) {
                lastBangOrPredicate = true;
                this.tokenBuffer.append((char)c);
            }
            else {
                this.src.unread(c);
            }
        }
        else {
            this.src.unread(c);
        }
        int result = 0;
        final LexState last_state = this.lex_state;
        if (lastBangOrPredicate) {
            result = 307;
        }
        else {
            if (this.lex_state == LexState.EXPR_FNAME) {
                if ((c = this.src.read()) == 61) {
                    final int c2 = this.src.read();
                    if (c2 != 126 && c2 != 62 && (c2 != 61 || (c2 == 10 && this.src.peek(62)))) {
                        result = 306;
                        this.tokenBuffer.append((char)c);
                        this.src.unread(c2);
                    }
                    else {
                        this.src.unread(c2);
                        this.src.unread(c);
                    }
                }
                else {
                    this.src.unread(c);
                }
            }
            if (result == 0 && Character.isUpperCase(first)) {
                result = 310;
            }
            else {
                result = 306;
            }
        }
        final String tempVal = this.tokenBuffer.toString().intern();
        if (!this.isOneEight && ((this.lex_state == LexState.EXPR_BEG && !commandState) || this.lex_state == LexState.EXPR_ARG || this.lex_state == LexState.EXPR_CMDARG)) {
            final int c3 = this.src.read();
            if (c3 == 58 && !this.src.peek(58)) {
                this.src.unread(c3);
                this.setState(LexState.EXPR_BEG);
                this.src.read();
                this.yaccValue = new Token(tempVal, this.getPosition());
                return 312;
            }
            this.src.unread(c3);
        }
        if (this.lex_state != LexState.EXPR_DOT) {
            final Keyword keyword = getKeyword(tempVal);
            if (keyword != null && (keyword != Keyword.__ENCODING__ || !this.isOneEight)) {
                final LexState state = this.lex_state;
                if (!this.isOneEight && keyword == Keyword.NOT) {
                    this.setState(LexState.EXPR_ARG);
                }
                else {
                    this.setState(keyword.state);
                }
                if (state == LexState.EXPR_FNAME) {
                    this.yaccValue = new Token(keyword.name, this.getPosition());
                }
                else {
                    this.yaccValue = new Token(tempVal, this.getPosition());
                    if (keyword.id0 == 280) {
                        return this.doKeyword(state);
                    }
                }
                if (state == LexState.EXPR_BEG || (!this.isOneEight && state == LexState.EXPR_VALUE)) {
                    return keyword.id0;
                }
                if (keyword.id0 != keyword.id1) {
                    this.setState(LexState.EXPR_BEG);
                }
                return keyword.id1;
            }
        }
        if (this.isBEG() || this.lex_state == LexState.EXPR_DOT || this.isARG()) {
            this.setState(commandState ? LexState.EXPR_CMDARG : LexState.EXPR_ARG);
        }
        else if (!this.isOneEight && this.lex_state == LexState.EXPR_ENDFN) {
            this.setState(LexState.EXPR_ENDFN);
        }
        else {
            this.setState(LexState.EXPR_END);
        }
        return this.identifierToken(last_state, result, tempVal);
    }
    
    private int leftBracket(final boolean spaceSeen) throws IOException {
        ++this.parenNest;
        int c = 91;
        if (this.lex_state != LexState.EXPR_FNAME && this.lex_state != LexState.EXPR_DOT) {
            if (this.isBEG() || (this.isARG() && spaceSeen)) {
                c = 343;
            }
            this.setState(LexState.EXPR_BEG);
            this.conditionState.stop();
            this.cmdArgumentState.stop();
            this.yaccValue = new Token("[", this.getPosition());
            return c;
        }
        this.setState(LexState.EXPR_ARG);
        if ((c = this.src.read()) != 93) {
            this.src.unread(c);
            this.yaccValue = new Token("[", this.getPosition());
            return 91;
        }
        if (this.src.peek(61)) {
            c = this.src.read();
            this.yaccValue = new Token("[]=", this.getPosition());
            return 332;
        }
        this.yaccValue = new Token("[]", this.getPosition());
        return 331;
    }
    
    private int leftCurly() {
        if (!this.isOneEight && this.leftParenBegin > 0 && this.leftParenBegin == this.parenNest) {
            this.setState(LexState.EXPR_BEG);
            this.leftParenBegin = 0;
            --this.parenNest;
            this.conditionState.stop();
            this.cmdArgumentState.stop();
            this.yaccValue = new Token("{", this.getPosition());
            return 374;
        }
        char c;
        if (this.isARG() || this.lex_state == LexState.EXPR_END || (!this.isOneEight && this.lex_state == LexState.EXPR_ENDFN)) {
            c = '\u0169';
        }
        else if (this.lex_state == LexState.EXPR_ENDARG) {
            c = '\u015a';
        }
        else {
            c = '\u0159';
        }
        this.conditionState.stop();
        this.cmdArgumentState.stop();
        this.setState(LexState.EXPR_BEG);
        this.yaccValue = new Token("{", this.getPosition());
        if (!this.isOneEight && c != '\u0159') {
            this.commandStart = true;
        }
        return c;
    }
    
    private int leftParen(final boolean spaceSeen) throws IOException {
        if (this.isOneEight) {
            this.commandStart = true;
        }
        int result = 340;
        if (this.isBEG()) {
            result = 339;
        }
        else if (spaceSeen) {
            if (this.lex_state == LexState.EXPR_CMDARG) {
                result = 342;
            }
            else if (this.lex_state == LexState.EXPR_ARG) {
                if (this.isOneEight) {
                    this.warnings.warn(IRubyWarnings.ID.ARGUMENT_EXTRA_SPACE, this.getPosition(), "don't put space before argument parentheses", new Object[0]);
                    result = 340;
                }
                else {
                    result = 342;
                }
            }
        }
        ++this.parenNest;
        this.conditionState.stop();
        this.cmdArgumentState.stop();
        this.setState(LexState.EXPR_BEG);
        this.yaccValue = new Token("(", this.getPosition());
        return result;
    }
    
    private int lessThan(final boolean spaceSeen) throws IOException {
        int c = this.src.read();
        if (c == 60 && this.lex_state != LexState.EXPR_DOT && this.lex_state != LexState.EXPR_CLASS && !this.isEND() && (!this.isARG() || spaceSeen)) {
            final int tok = this.hereDocumentIdentifier();
            if (tok != 0) {
                return tok;
            }
        }
        this.determineExpressionState();
        switch (c) {
            case 61: {
                if ((c = this.src.read()) == 62) {
                    this.yaccValue = new Token("<=>", this.getPosition());
                    return 318;
                }
                this.src.unread(c);
                this.yaccValue = new Token("<=", this.getPosition());
                return 323;
            }
            case 60: {
                if ((c = this.src.read()) == 61) {
                    this.setState(LexState.EXPR_BEG);
                    this.yaccValue = new Token("<<", this.getPosition());
                    return 337;
                }
                this.src.unread(c);
                this.yaccValue = new Token("<<", this.getPosition());
                return 333;
            }
            default: {
                this.yaccValue = new Token("<", this.getPosition());
                this.src.unread(c);
                return 356;
            }
        }
    }
    
    private int minus(final boolean spaceSeen) throws IOException {
        final int c = this.src.read();
        if (this.lex_state == LexState.EXPR_FNAME || this.lex_state == LexState.EXPR_DOT) {
            this.setState(LexState.EXPR_ARG);
            if (c == 64) {
                this.yaccValue = new Token("-@", this.getPosition());
                return 315;
            }
            this.src.unread(c);
            this.yaccValue = new Token("-", this.getPosition());
            return 355;
        }
        else {
            if (c == 61) {
                this.setState(LexState.EXPR_BEG);
                this.yaccValue = new Token("-", this.getPosition());
                return 337;
            }
            if (!this.isOneEight && c == 62) {
                this.setState(LexState.EXPR_ARG);
                this.yaccValue = new Token("->", this.getPosition());
                return 373;
            }
            if (!this.isBEG() && (!this.isARG() || !spaceSeen || Character.isWhitespace(c))) {
                this.setState(LexState.EXPR_BEG);
                this.src.unread(c);
                this.yaccValue = new Token("-", this.getPosition());
                return 355;
            }
            if (this.isARG()) {
                this.arg_ambiguous();
            }
            this.setState(LexState.EXPR_BEG);
            this.src.unread(c);
            this.yaccValue = new Token("-", this.getPosition());
            if (Character.isDigit(c)) {
                return 316;
            }
            return 315;
        }
    }
    
    private int percent(final boolean spaceSeen) throws IOException {
        if (this.isBEG()) {
            return this.parseQuote(this.src.read());
        }
        final int c = this.src.read();
        if (c == 61) {
            this.setState(LexState.EXPR_BEG);
            this.yaccValue = new Token("%", this.getPosition());
            return 337;
        }
        if (this.isARG() && spaceSeen && !Character.isWhitespace(c)) {
            return this.parseQuote(c);
        }
        this.determineExpressionState();
        this.src.unread(c);
        this.yaccValue = new Token("%", this.getPosition());
        return 352;
    }
    
    private int pipe() throws IOException {
        int c = this.src.read();
        switch (c) {
            case 124: {
                this.setState(LexState.EXPR_BEG);
                if ((c = this.src.read()) == 61) {
                    this.setState(LexState.EXPR_BEG);
                    this.yaccValue = new Token("||", this.getPosition());
                    return 337;
                }
                this.src.unread(c);
                this.yaccValue = new Token("||", this.getPosition());
                return 325;
            }
            case 61: {
                this.setState(LexState.EXPR_BEG);
                this.yaccValue = new Token("|", this.getPosition());
                return 337;
            }
            default: {
                this.determineExpressionState();
                this.src.unread(c);
                this.yaccValue = new Token("|", this.getPosition());
                return 358;
            }
        }
    }
    
    private int plus(final boolean spaceSeen) throws IOException {
        int c = this.src.read();
        if (this.lex_state == LexState.EXPR_FNAME || this.lex_state == LexState.EXPR_DOT) {
            this.setState(LexState.EXPR_ARG);
            if (c == 64) {
                this.yaccValue = new Token("+@", this.getPosition());
                return 314;
            }
            this.src.unread(c);
            this.yaccValue = new Token("+", this.getPosition());
            return 354;
        }
        else {
            if (c == 61) {
                this.setState(LexState.EXPR_BEG);
                this.yaccValue = new Token("+", this.getPosition());
                return 337;
            }
            if (!this.isBEG() && (!this.isARG() || !spaceSeen || Character.isWhitespace(c))) {
                this.setState(LexState.EXPR_BEG);
                this.src.unread(c);
                this.yaccValue = new Token("+", this.getPosition());
                return 354;
            }
            if (this.isARG()) {
                this.arg_ambiguous();
            }
            this.setState(LexState.EXPR_BEG);
            this.src.unread(c);
            if (Character.isDigit(c)) {
                c = 43;
                return this.parseNumber(c);
            }
            this.yaccValue = new Token("+", this.getPosition());
            return 314;
        }
    }
    
    private int questionMark() throws IOException {
        if (this.isEND()) {
            this.setState(this.isOneEight ? LexState.EXPR_BEG : LexState.EXPR_VALUE);
            this.yaccValue = new Token("?", this.getPosition());
            return 63;
        }
        int c = this.src.read();
        if (c == -1) {
            throw new SyntaxException(SyntaxException.PID.INCOMPLETE_CHAR_SYNTAX, this.getPosition(), this.getCurrentLine(), "incomplete character syntax", new Object[0]);
        }
        if (Character.isWhitespace(c)) {
            if (!this.isARG()) {
                int c2 = 0;
                switch (c) {
                    case 32: {
                        c2 = 115;
                        break;
                    }
                    case 10: {
                        c2 = 110;
                        break;
                    }
                    case 9: {
                        c2 = 116;
                        break;
                    }
                    case 13: {
                        c2 = 114;
                        break;
                    }
                    case 12: {
                        c2 = 102;
                        break;
                    }
                }
                if (c2 != 0) {
                    this.warnings.warn(IRubyWarnings.ID.INVALID_CHAR_SEQUENCE, this.getPosition(), "invalid character syntax; use ?\\" + c2, new Object[0]);
                }
            }
            this.src.unread(c);
            this.setState(this.isOneEight ? LexState.EXPR_BEG : LexState.EXPR_VALUE);
            this.yaccValue = new Token("?", this.getPosition());
            return 63;
        }
        if (this.isIdentifierChar(c) && !this.src.peek(10) && this.isNext_identchar()) {
            this.src.unread(c);
            this.setState(this.isOneEight ? LexState.EXPR_BEG : LexState.EXPR_VALUE);
            this.yaccValue = new Token("?", this.getPosition());
            return 63;
        }
        if (c == 92) {
            if (!this.isOneEight && this.src.peek(117)) {
                this.src.read();
                c = this.readUTFEscape(null, false, false);
            }
            else {
                c = this.readEscape();
            }
        }
        this.setState(LexState.EXPR_END);
        if (this.isOneEight) {
            c &= 0xFF;
            this.yaccValue = new FixnumNode(this.getPosition(), c);
        }
        else {
            final ByteList oneCharBL = new ByteList(1);
            oneCharBL.append(c);
            this.yaccValue = new StrNode(this.getPosition(), oneCharBL);
        }
        return 378;
    }
    
    private int rightBracket() {
        --this.parenNest;
        this.conditionState.restart();
        this.cmdArgumentState.restart();
        this.setState(this.isOneEight ? LexState.EXPR_END : LexState.EXPR_ENDARG);
        this.yaccValue = new Token(")", this.getPosition());
        return 344;
    }
    
    private int rightCurly() {
        this.conditionState.restart();
        this.cmdArgumentState.restart();
        this.setState(this.isOneEight ? LexState.EXPR_END : LexState.EXPR_ENDARG);
        this.yaccValue = new Token("}", this.getPosition());
        return 362;
    }
    
    private int rightParen() {
        --this.parenNest;
        this.conditionState.restart();
        this.cmdArgumentState.restart();
        this.setState(this.isOneEight ? LexState.EXPR_END : LexState.EXPR_ENDFN);
        this.yaccValue = new Token(")", this.getPosition());
        return 341;
    }
    
    private int singleQuote() throws IOException {
        this.lex_strterm = new StringTerm(0, 0, 39);
        this.yaccValue = new Token("'", this.getPosition());
        return 365;
    }
    
    private int slash(final boolean spaceSeen) throws IOException {
        if (this.isBEG()) {
            this.lex_strterm = new StringTerm(7, 0, 47);
            this.yaccValue = new Token("/", this.getPosition());
            return 367;
        }
        final int c = this.src.read();
        if (c == 61) {
            this.yaccValue = new Token("/", this.getPosition());
            this.setState(LexState.EXPR_BEG);
            return 337;
        }
        this.src.unread(c);
        if (this.isARG() && spaceSeen && !Character.isWhitespace(c)) {
            this.arg_ambiguous();
            this.lex_strterm = new StringTerm(7, 0, 47);
            this.yaccValue = new Token("/", this.getPosition());
            return 367;
        }
        this.determineExpressionState();
        this.yaccValue = new Token("/", this.getPosition());
        return 353;
    }
    
    private int star(final boolean spaceSeen) throws IOException {
        int c = this.src.read();
        switch (c) {
            case 42: {
                if ((c = this.src.read()) == 61) {
                    this.setState(LexState.EXPR_BEG);
                    this.yaccValue = new Token("**", this.getPosition());
                    return 337;
                }
                this.src.unread(c);
                this.yaccValue = new Token("**", this.getPosition());
                c = 317;
                break;
            }
            case 61: {
                this.setState(LexState.EXPR_BEG);
                this.yaccValue = new Token("*", this.getPosition());
                return 337;
            }
            default: {
                this.src.unread(c);
                if (this.isARG() && spaceSeen && !Character.isWhitespace(c)) {
                    if (this.warnings.isVerbose()) {
                        this.warnings.warning(IRubyWarnings.ID.ARGUMENT_AS_PREFIX, this.getPosition(), "`*' interpreted as argument prefix", "*");
                    }
                    c = 347;
                }
                else if (this.isBEG()) {
                    c = 347;
                }
                else {
                    c = 348;
                }
                this.yaccValue = new Token("*", this.getPosition());
                break;
            }
        }
        this.determineExpressionState();
        return c;
    }
    
    private int tilde() throws IOException {
        if (this.lex_state == LexState.EXPR_FNAME || this.lex_state == LexState.EXPR_DOT) {
            final int c;
            if ((c = this.src.read()) != 64) {
                this.src.unread(c);
            }
            this.setState(LexState.EXPR_ARG);
        }
        else {
            this.setState(LexState.EXPR_BEG);
        }
        this.yaccValue = new Token("~", this.getPosition());
        return 351;
    }
    
    private int parseNumber(int c) throws IOException {
        this.setState(LexState.EXPR_END);
        this.tokenBuffer.setLength(0);
        if (c == 45) {
            this.tokenBuffer.append((char)c);
            c = this.src.read();
        }
        else if (c == 43) {
            c = this.src.read();
        }
        int nondigit = 0;
        if (c == 48) {
            final int startLen = this.tokenBuffer.length();
            switch (c = this.src.read()) {
                case 88:
                case 120: {
                    c = this.src.read();
                    if (isHexChar(c)) {
                        while (true) {
                            if (c == 95) {
                                if (nondigit != 0) {
                                    break;
                                }
                                nondigit = c;
                            }
                            else {
                                if (!isHexChar(c)) {
                                    break;
                                }
                                nondigit = 0;
                                this.tokenBuffer.append((char)c);
                            }
                            c = this.src.read();
                        }
                    }
                    this.src.unread(c);
                    if (this.tokenBuffer.length() == startLen) {
                        throw new SyntaxException(SyntaxException.PID.BAD_HEX_NUMBER, this.getPosition(), this.getCurrentLine(), "Hexadecimal number without hex-digits.", new Object[0]);
                    }
                    if (nondigit != 0) {
                        throw new SyntaxException(SyntaxException.PID.TRAILING_UNDERSCORE_IN_NUMBER, this.getPosition(), this.getCurrentLine(), "Trailing '_' in number.", new Object[0]);
                    }
                    this.yaccValue = this.getInteger(this.tokenBuffer.toString(), 16);
                    return 378;
                }
                case 66:
                case 98: {
                    c = this.src.read();
                    if (c == 48 || c == 49) {
                        while (true) {
                            if (c == 95) {
                                if (nondigit != 0) {
                                    break;
                                }
                                nondigit = c;
                            }
                            else {
                                if (c != 48 && c != 49) {
                                    break;
                                }
                                nondigit = 0;
                                this.tokenBuffer.append((char)c);
                            }
                            c = this.src.read();
                        }
                    }
                    this.src.unread(c);
                    if (this.tokenBuffer.length() == startLen) {
                        throw new SyntaxException(SyntaxException.PID.EMPTY_BINARY_NUMBER, this.getPosition(), this.getCurrentLine(), "Binary number without digits.", new Object[0]);
                    }
                    if (nondigit != 0) {
                        throw new SyntaxException(SyntaxException.PID.TRAILING_UNDERSCORE_IN_NUMBER, this.getPosition(), this.getCurrentLine(), "Trailing '_' in number.", new Object[0]);
                    }
                    this.yaccValue = this.getInteger(this.tokenBuffer.toString(), 2);
                    return 378;
                }
                case 68:
                case 100: {
                    c = this.src.read();
                    if (Character.isDigit(c)) {
                        while (true) {
                            if (c == 95) {
                                if (nondigit != 0) {
                                    break;
                                }
                                nondigit = c;
                            }
                            else {
                                if (!Character.isDigit(c)) {
                                    break;
                                }
                                nondigit = 0;
                                this.tokenBuffer.append((char)c);
                            }
                            c = this.src.read();
                        }
                    }
                    this.src.unread(c);
                    if (this.tokenBuffer.length() == startLen) {
                        throw new SyntaxException(SyntaxException.PID.EMPTY_BINARY_NUMBER, this.getPosition(), this.getCurrentLine(), "Binary number without digits.", new Object[0]);
                    }
                    if (nondigit != 0) {
                        throw new SyntaxException(SyntaxException.PID.TRAILING_UNDERSCORE_IN_NUMBER, this.getPosition(), this.getCurrentLine(), "Trailing '_' in number.", new Object[0]);
                    }
                    this.yaccValue = this.getInteger(this.tokenBuffer.toString(), 10);
                    return 378;
                }
                case 79:
                case 111: {
                    c = this.src.read();
                }
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 95: {
                    while (true) {
                        if (c == 95) {
                            if (nondigit != 0) {
                                break;
                            }
                            nondigit = c;
                        }
                        else {
                            if (c < 48 || c > 55) {
                                break;
                            }
                            nondigit = 0;
                            this.tokenBuffer.append((char)c);
                        }
                        c = this.src.read();
                    }
                    if (this.tokenBuffer.length() <= startLen) {
                        throw new SyntaxException(SyntaxException.PID.BAD_OCTAL_DIGIT, this.getPosition(), this.getCurrentLine(), "Illegal octal digit.", new Object[0]);
                    }
                    this.src.unread(c);
                    if (nondigit != 0) {
                        throw new SyntaxException(SyntaxException.PID.TRAILING_UNDERSCORE_IN_NUMBER, this.getPosition(), this.getCurrentLine(), "Trailing '_' in number.", new Object[0]);
                    }
                    this.yaccValue = this.getInteger(this.tokenBuffer.toString(), 8);
                    return 378;
                }
                case 56:
                case 57: {
                    throw new SyntaxException(SyntaxException.PID.BAD_OCTAL_DIGIT, this.getPosition(), this.getCurrentLine(), "Illegal octal digit.", new Object[0]);
                }
                case 46:
                case 69:
                case 101: {
                    this.tokenBuffer.append('0');
                    break;
                }
                default: {
                    this.src.unread(c);
                    this.yaccValue = new FixnumNode(this.getPosition(), 0L);
                    return 378;
                }
            }
        }
        boolean seen_point = false;
        boolean seen_e = false;
        while (true) {
            switch (c) {
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
                    nondigit = 0;
                    this.tokenBuffer.append((char)c);
                    break;
                }
                case 46: {
                    if (nondigit != 0) {
                        this.src.unread(c);
                        throw new SyntaxException(SyntaxException.PID.TRAILING_UNDERSCORE_IN_NUMBER, this.getPosition(), this.getCurrentLine(), "Trailing '_' in number.", new Object[0]);
                    }
                    if (seen_point || seen_e) {
                        this.src.unread(c);
                        return this.getNumberToken(this.tokenBuffer.toString(), true, nondigit);
                    }
                    final int c2;
                    if (!Character.isDigit(c2 = this.src.read())) {
                        this.src.unread(c2);
                        this.src.unread(46);
                        if (c != 95) {
                            this.yaccValue = this.getInteger(this.tokenBuffer.toString(), 10);
                            return 378;
                        }
                    }
                    else {
                        this.tokenBuffer.append('.');
                        this.tokenBuffer.append((char)c2);
                        seen_point = true;
                        nondigit = 0;
                    }
                    break;
                }
                case 69:
                case 101: {
                    if (nondigit != 0) {
                        throw new SyntaxException(SyntaxException.PID.TRAILING_UNDERSCORE_IN_NUMBER, this.getPosition(), this.getCurrentLine(), "Trailing '_' in number.", new Object[0]);
                    }
                    if (seen_e) {
                        this.src.unread(c);
                        return this.getNumberToken(this.tokenBuffer.toString(), true, nondigit);
                    }
                    this.tokenBuffer.append((char)c);
                    seen_e = true;
                    nondigit = c;
                    c = this.src.read();
                    if (c == 45 || c == 43) {
                        this.tokenBuffer.append((char)c);
                        nondigit = c;
                        break;
                    }
                    this.src.unread(c);
                    break;
                }
                case 95: {
                    if (nondigit != 0) {
                        throw new SyntaxException(SyntaxException.PID.TRAILING_UNDERSCORE_IN_NUMBER, this.getPosition(), this.getCurrentLine(), "Trailing '_' in number.", new Object[0]);
                    }
                    nondigit = c;
                    break;
                }
                default: {
                    this.src.unread(c);
                    return this.getNumberToken(this.tokenBuffer.toString(), seen_e || seen_point, nondigit);
                }
            }
            c = this.src.read();
        }
    }
    
    private int getNumberToken(final String number, final boolean isFloat, final int nondigit) {
        if (nondigit != 0) {
            throw new SyntaxException(SyntaxException.PID.TRAILING_UNDERSCORE_IN_NUMBER, this.getPosition(), this.getCurrentLine(), "Trailing '_' in number.", new Object[0]);
        }
        if (isFloat) {
            return this.getFloatToken(number);
        }
        this.yaccValue = this.getInteger(number, 10);
        return 378;
    }
    
    public void readUTFEscapeRegexpLiteral(final ByteList buffer) throws IOException {
        buffer.append(92);
        buffer.append(117);
        if (this.src.peek(123)) {
            do {
                buffer.append(this.src.read());
                if (this.scanHexLiteral(buffer, 6, false, "invalid Unicode escape") > 1114111) {
                    throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "invalid Unicode codepoint (too large)", new Object[0]);
                }
            } while (this.src.peek(32) || this.src.peek(9));
            final int c = this.src.read();
            if (c != 125) {
                throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "unterminated Unicode escape", new Object[0]);
            }
            buffer.append((char)c);
        }
        else {
            this.scanHexLiteral(buffer, 4, true, "Invalid Unicode escape");
        }
    }
    
    public int tokenAddMBC(final int codepoint, final ByteList buffer) {
        final int length = buffer.getEncoding().codeToMbc(codepoint, this.mbcBuf, 0);
        if (length <= 0) {
            return -1;
        }
        buffer.append(this.mbcBuf, 0, length);
        return length;
    }
    
    public void tokenAddMBCFromSrc(final int c, final ByteList buffer) throws IOException {
        final int length = buffer.getEncoding().length((byte)c);
        buffer.append((byte)c);
        for (int off = 0; off < length - 1; ++off) {
            buffer.append((byte)this.src.read());
        }
    }
    
    public int readUTFEscape(final ByteList buffer, final boolean stringLiteral, final boolean symbolLiteral) throws IOException {
        int codepoint;
        if (this.src.peek(123)) {
            do {
                this.src.read();
                codepoint = this.scanHex(6, false, "invalid Unicode escape");
                if (codepoint > 1114111) {
                    throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "invalid Unicode codepoint (too large)", new Object[0]);
                }
                if (codepoint >= 128) {
                    buffer.setEncoding(RubyYaccLexer.UTF8_ENCODING);
                    if (!stringLiteral) {
                        continue;
                    }
                    this.tokenAddMBC(codepoint, buffer);
                }
                else {
                    if (!stringLiteral) {
                        continue;
                    }
                    if (codepoint == 0 && symbolLiteral) {
                        throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "symbol cannot contain '\\u0000'", new Object[0]);
                    }
                    buffer.append((char)codepoint);
                }
            } while (this.src.peek(32) || this.src.peek(9));
            final int c = this.src.read();
            if (c != 125) {
                throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "unterminated Unicode escape", new Object[0]);
            }
        }
        else {
            codepoint = this.scanHex(4, true, "Invalid Unicode escape");
            if (codepoint >= 128) {
                buffer.setEncoding(RubyYaccLexer.UTF8_ENCODING);
                if (stringLiteral) {
                    this.tokenAddMBC(codepoint, buffer);
                }
            }
            else if (stringLiteral) {
                if (codepoint == 0 && symbolLiteral) {
                    throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "symbol cannot contain '\\u0000'", new Object[0]);
                }
                buffer.append((char)codepoint);
            }
        }
        return codepoint;
    }
    
    public int readEscape() throws IOException {
        int c = this.src.read();
        switch (c) {
            case 92: {
                return c;
            }
            case 110: {
                return 10;
            }
            case 116: {
                return 9;
            }
            case 114: {
                return 13;
            }
            case 102: {
                return 12;
            }
            case 118: {
                return 11;
            }
            case 97: {
                return 7;
            }
            case 101: {
                return 27;
            }
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55: {
                this.src.unread(c);
                return this.scanOct(3);
            }
            case 120: {
                return this.scanHex(2, false, "Invalid escape character syntax");
            }
            case 98: {
                return 8;
            }
            case 115: {
                return 32;
            }
            case 77: {
                if ((c = this.src.read()) != 45) {
                    throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
                }
                if ((c = this.src.read()) == 92) {
                    return (char)(this.readEscape() | 0x80);
                }
                if (c == -1) {
                    throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
                }
                return (char)((c & 0xFF) | 0x80);
            }
            case 67: {
                if ((c = this.src.read()) != 45) {
                    throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
                }
            }
            case 99: {
                if ((c = this.src.read()) == 92) {
                    c = this.readEscape();
                }
                else {
                    if (c == 63) {
                        return 375;
                    }
                    if (c == -1) {
                        throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
                    }
                }
                return (char)(c & 0x9F);
            }
            case -1: {
                throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
            }
            default: {
                return c;
            }
        }
    }
    
    private char scanHexLiteral(final ByteList buffer, final int count, final boolean strict, final String errorMessage) throws IOException {
        int i = 0;
        char hexValue = '\0';
        while (i < count) {
            final int h1 = this.src.read();
            if (!isHexChar(h1)) {
                this.src.unread(h1);
                break;
            }
            buffer.append(h1);
            hexValue <<= 4;
            hexValue |= (char)(Integer.parseInt("" + (char)h1, 16) & 0xF);
            ++i;
        }
        if (i == 0 || (strict && count != i)) {
            throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), errorMessage, new Object[0]);
        }
        return hexValue;
    }
    
    private int scanHex(final int count, final boolean strict, final String errorMessage) throws IOException {
        int i = 0;
        int hexValue = 0;
        while (i < count) {
            final int h1 = this.src.read();
            if (!isHexChar(h1)) {
                this.src.unread(h1);
                break;
            }
            hexValue <<= 4;
            hexValue |= (Integer.parseInt("" + (char)h1, 16) & 0xF);
            ++i;
        }
        if (i == 0 || (strict && count != i)) {
            throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, this.getPosition(), this.getCurrentLine(), errorMessage, new Object[0]);
        }
        return hexValue;
    }
    
    private char scanOct(final int count) throws IOException {
        char value = '\0';
        for (int i = 0; i < count; ++i) {
            final int c = this.src.read();
            if (!isOctChar(c)) {
                this.src.unread(c);
                break;
            }
            value <<= 3;
            value |= (char)Integer.parseInt("" + (char)c, 8);
        }
        return value;
    }
    
    static {
        UTF8_ENCODING = UTF8Encoding.INSTANCE;
        USASCII_ENCODING = USASCIIEncoding.INSTANCE;
        ASCII8BIT_ENCODING = ASCIIEncoding.INSTANCE;
        RubyYaccLexer.END_MARKER = new ByteList(new byte[] { 95, 69, 78, 68, 95, 95 });
        RubyYaccLexer.BEGIN_DOC_MARKER = new ByteList(new byte[] { 98, 101, 103, 105, 110 });
        RubyYaccLexer.END_DOC_MARKER = new ByteList(new byte[] { 101, 110, 100 });
        (map = new HashMap<String, Keyword>()).put("end", Keyword.END);
        RubyYaccLexer.map.put("else", Keyword.ELSE);
        RubyYaccLexer.map.put("case", Keyword.CASE);
        RubyYaccLexer.map.put("ensure", Keyword.ENSURE);
        RubyYaccLexer.map.put("module", Keyword.MODULE);
        RubyYaccLexer.map.put("elsif", Keyword.ELSIF);
        RubyYaccLexer.map.put("def", Keyword.DEF);
        RubyYaccLexer.map.put("rescue", Keyword.RESCUE);
        RubyYaccLexer.map.put("not", Keyword.NOT);
        RubyYaccLexer.map.put("then", Keyword.THEN);
        RubyYaccLexer.map.put("yield", Keyword.YIELD);
        RubyYaccLexer.map.put("for", Keyword.FOR);
        RubyYaccLexer.map.put("self", Keyword.SELF);
        RubyYaccLexer.map.put("false", Keyword.FALSE);
        RubyYaccLexer.map.put("retry", Keyword.RETRY);
        RubyYaccLexer.map.put("return", Keyword.RETURN);
        RubyYaccLexer.map.put("true", Keyword.TRUE);
        RubyYaccLexer.map.put("if", Keyword.IF);
        RubyYaccLexer.map.put("defined?", Keyword.DEFINED_P);
        RubyYaccLexer.map.put("super", Keyword.SUPER);
        RubyYaccLexer.map.put("undef", Keyword.UNDEF);
        RubyYaccLexer.map.put("break", Keyword.BREAK);
        RubyYaccLexer.map.put("in", Keyword.IN);
        RubyYaccLexer.map.put("do", Keyword.DO);
        RubyYaccLexer.map.put("nil", Keyword.NIL);
        RubyYaccLexer.map.put("until", Keyword.UNTIL);
        RubyYaccLexer.map.put("unless", Keyword.UNLESS);
        RubyYaccLexer.map.put("or", Keyword.OR);
        RubyYaccLexer.map.put("next", Keyword.NEXT);
        RubyYaccLexer.map.put("when", Keyword.WHEN);
        RubyYaccLexer.map.put("redo", Keyword.REDO);
        RubyYaccLexer.map.put("and", Keyword.AND);
        RubyYaccLexer.map.put("begin", Keyword.BEGIN);
        RubyYaccLexer.map.put("__LINE__", Keyword.__LINE__);
        RubyYaccLexer.map.put("class", Keyword.CLASS);
        RubyYaccLexer.map.put("__FILE__", Keyword.__FILE__);
        RubyYaccLexer.map.put("END", Keyword.LEND);
        RubyYaccLexer.map.put("BEGIN", Keyword.LBEGIN);
        RubyYaccLexer.map.put("while", Keyword.WHILE);
        RubyYaccLexer.map.put("alias", Keyword.ALIAS);
        RubyYaccLexer.map.put("__ENCODING__", Keyword.__ENCODING__);
        magicRegexp = new Regex("([^\\s'\":;]+)\\s*:\\s*(\"(?:\\\\.|[^\"])*\"|[^\"\\s;]+)[\\s;]*".getBytes(), 0, "([^\\s'\":;]+)\\s*:\\s*(\"(?:\\\\.|[^\"])*\"|[^\"\\s;]+)[\\s;]*".length(), 0, Encoding.load("ASCII"));
        encodingRegexp = new Regex("[cC][oO][dD][iI][nN][gG]\\s*[=:]\\s*([a-zA-Z0-9\\-_]+)".getBytes(), 0, "[cC][oO][dD][iI][nN][gG]\\s*[=:]\\s*([a-zA-Z0-9\\-_]+)".length(), 0, Encoding.load("ASCII"));
    }
    
    public enum Keyword
    {
        END("end", 264, 264, LexState.EXPR_END), 
        ELSE("else", 269, 269, LexState.EXPR_BEG), 
        CASE("case", 270, 270, LexState.EXPR_BEG), 
        ENSURE("ensure", 263, 263, LexState.EXPR_BEG), 
        MODULE("module", 258, 258, LexState.EXPR_BEG), 
        ELSIF("elsif", 268, 268, LexState.EXPR_BEG), 
        DEF("def", 259, 259, LexState.EXPR_FNAME), 
        RESCUE("rescue", 262, 297, LexState.EXPR_MID), 
        NOT("not", 292, 292, LexState.EXPR_BEG), 
        THEN("then", 267, 267, LexState.EXPR_BEG), 
        YIELD("yield", 284, 284, LexState.EXPR_ARG), 
        FOR("for", 274, 274, LexState.EXPR_BEG), 
        SELF("self", 286, 286, LexState.EXPR_END), 
        FALSE("false", 289, 289, LexState.EXPR_END), 
        RETRY("retry", 278, 278, LexState.EXPR_END), 
        RETURN("return", 283, 283, LexState.EXPR_MID), 
        TRUE("true", 288, 288, LexState.EXPR_END), 
        IF("if", 265, 293, LexState.EXPR_BEG), 
        DEFINED_P("defined?", 299, 299, LexState.EXPR_ARG), 
        SUPER("super", 285, 285, LexState.EXPR_ARG), 
        UNDEF("undef", 260, 260, LexState.EXPR_FNAME), 
        BREAK("break", 275, 275, LexState.EXPR_MID), 
        IN("in", 279, 279, LexState.EXPR_BEG), 
        DO("do", 280, 280, LexState.EXPR_BEG), 
        NIL("nil", 287, 287, LexState.EXPR_END), 
        UNTIL("until", 273, 296, LexState.EXPR_BEG), 
        UNLESS("unless", 266, 294, LexState.EXPR_BEG), 
        OR("or", 291, 291, LexState.EXPR_BEG), 
        NEXT("next", 276, 276, LexState.EXPR_MID), 
        WHEN("when", 271, 271, LexState.EXPR_BEG), 
        REDO("redo", 277, 277, LexState.EXPR_END), 
        AND("and", 290, 290, LexState.EXPR_BEG), 
        BEGIN("begin", 261, 261, LexState.EXPR_BEG), 
        __LINE__("__LINE__", 302, 302, LexState.EXPR_END), 
        CLASS("class", 257, 257, LexState.EXPR_CLASS), 
        __FILE__("__FILE__", 303, 303, LexState.EXPR_END), 
        LEND("END", 301, 301, LexState.EXPR_END), 
        LBEGIN("BEGIN", 300, 300, LexState.EXPR_END), 
        WHILE("while", 272, 295, LexState.EXPR_BEG), 
        ALIAS("alias", 298, 298, LexState.EXPR_FNAME), 
        __ENCODING__("__ENCODING__", 304, 304, LexState.EXPR_END);
        
        public final String name;
        public final int id0;
        public final int id1;
        public final LexState state;
        
        private Keyword(final String name, final int id0, final int id1, final LexState state) {
            this.name = name;
            this.id0 = id0;
            this.id1 = id1;
            this.state = state;
        }
    }
    
    public enum LexState
    {
        EXPR_BEG, 
        EXPR_END, 
        EXPR_ARG, 
        EXPR_CMDARG, 
        EXPR_ENDARG, 
        EXPR_MID, 
        EXPR_FNAME, 
        EXPR_DOT, 
        EXPR_CLASS, 
        EXPR_VALUE, 
        EXPR_ENDFN;
    }
}
