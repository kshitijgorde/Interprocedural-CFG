// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

import java.io.Serializable;

public class ParserContext implements Serializable
{
    public static final int END_OF_STRING = 1;
    public static final int NUMBER = 2;
    public static final int IDENTIFIER = 3;
    public static final int OPCHARS = 4;
    private static final int NONE = 0;
    public String data;
    public int pos;
    public ExpressionProgram prog;
    public int token;
    public String tokenString;
    public MathObject tokenObject;
    public double tokenValue;
    public int options;
    protected SymbolTable symbols;
    private StringBuffer tokBuf;
    
    public ParserContext(final String data, final int options, final SymbolTable symbols) {
        this.tokBuf = new StringBuffer();
        this.data = data;
        this.options = options;
        this.symbols = symbols;
        this.prog = new ExpressionProgram();
    }
    
    public void mark() {
        this.symbols = new SymbolTable(this.symbols);
    }
    
    public void revert() {
        this.symbols = this.symbols.getParent();
    }
    
    public MathObject get(final String s) {
        if ((this.options & 0x1) != 0x0) {
            return this.symbols.get(s);
        }
        return this.symbols.get(s.toLowerCase());
    }
    
    public void add(final MathObject mathObject) {
        if ((this.options & 0x1) != 0x0) {
            this.symbols.add(mathObject);
        }
        else {
            this.symbols.add(mathObject.getName().toLowerCase(), mathObject);
        }
    }
    
    public int next() {
        final int look = this.look();
        if (this.token != 1) {
            this.token = 0;
        }
        return look;
    }
    
    public int look() {
        if (this.token == 0) {
            while (this.pos < this.data.length() && (this.data.charAt(this.pos) == ' ' || this.data.charAt(this.pos) == '\t')) {
                ++this.pos;
            }
            if (this.pos >= this.data.length()) {
                this.token = 1;
                this.tokenString = null;
            }
            else {
                this.readToken();
            }
        }
        return this.token;
    }
    
    private void readToken() {
        char c = this.data.charAt(this.pos);
        final int pos = this.pos;
        this.tokBuf.setLength(0);
        if (Character.isLetter(c) || (c == '_' && (this.options & 0x80) == 0x0)) {
            this.token = 3;
            while (Character.isLetter(c) || (c == '_' && (this.options & 0x80) == 0x0) || (Character.isDigit(c) && (this.options & 0x100) == 0x0)) {
                this.tokBuf.append(c);
                ++this.pos;
                if (this.pos >= this.data.length()) {
                    break;
                }
                c = this.data.charAt(this.pos);
            }
            this.tokenString = this.tokBuf.toString();
            this.tokenObject = null;
            for (int i = this.tokenString.length(); i > 0; --i) {
                final String substring = this.tokenString.substring(0, i);
                if ((this.options & 0x20) != 0x0) {
                    if (substring.equalsIgnoreCase("and")) {
                        this.token = 4;
                        this.tokenString = "&";
                        this.pos = pos + 3;
                        return;
                    }
                    if (substring.equalsIgnoreCase("or")) {
                        this.token = 4;
                        this.tokenString = "|";
                        this.pos = pos + 2;
                        return;
                    }
                    if (substring.equalsIgnoreCase("not")) {
                        this.token = 4;
                        this.tokenString = "~";
                        this.pos = pos + 3;
                        return;
                    }
                }
                if (this.get(substring) != null) {
                    this.tokenString = substring;
                    this.tokenObject = this.get(this.tokenString);
                    this.pos = pos + i;
                    break;
                }
                if ((this.options & 0x4) == 0x0) {
                    break;
                }
            }
        }
        else if (Character.isDigit(c) || c == '.') {
            this.token = 2;
            while (this.pos < this.data.length() && Character.isDigit(this.data.charAt(this.pos))) {
                this.tokBuf.append(this.data.charAt(this.pos++));
            }
            if (this.pos < this.data.length() && this.data.charAt(this.pos) == '.') {
                this.tokBuf.append(this.data.charAt(this.pos++));
                while (this.pos < this.data.length() && Character.isDigit(this.data.charAt(this.pos))) {
                    this.tokBuf.append(this.data.charAt(this.pos++));
                }
            }
            if (this.pos < this.data.length() && (this.data.charAt(this.pos) == 'e' || this.data.charAt(this.pos) == 'E')) {
                final int pos2 = this.pos;
                this.tokBuf.append(this.data.charAt(this.pos++));
                if (this.pos < this.data.length() && (this.data.charAt(this.pos) == '+' || this.data.charAt(this.pos) == '-')) {
                    this.tokBuf.append(this.data.charAt(this.pos++));
                }
                if (this.pos >= this.data.length() || !Character.isDigit(this.data.charAt(this.pos))) {
                    if ((this.options & 0x2) == 0x0) {
                        throw new ParseError("Illegal number, '" + this.tokBuf.toString() + "'.  No digits in exponential part.", this);
                    }
                    this.pos = pos2;
                }
                else {
                    while (this.pos < this.data.length() && Character.isDigit(this.data.charAt(this.pos))) {
                        this.tokBuf.append(this.data.charAt(this.pos++));
                    }
                }
            }
            this.tokenString = this.tokBuf.toString();
            final double stringToReal = NumUtils.stringToReal(this.tokenString);
            if (Double.isInfinite(stringToReal)) {
                throw new ParseError("The number '" + this.tokBuf.toString() + "' is outside the range of legal numbers.", this);
            }
            if (Double.isNaN(stringToReal)) {
                throw new ParseError("The string '" + this.tokBuf.toString() + "' is not a legal number.", this);
            }
            this.tokenValue = stringToReal;
        }
        else {
            this.token = 4;
            this.tokenString = String.valueOf(c);
            ++this.pos;
            if (this.pos < this.data.length()) {
                final char char1 = this.data.charAt(this.pos);
                switch (c) {
                    case '*': {
                        if (char1 == '*') {
                            this.tokenString = "^";
                            ++this.pos;
                            break;
                        }
                        break;
                    }
                    case '=': {
                        if (char1 == '<' || char1 == '>') {
                            this.tokenString = String.valueOf(this.data.charAt(this.pos++)) + this.tokenString;
                            break;
                        }
                        break;
                    }
                    case '<': {
                        if (char1 == '=' || char1 == '>') {
                            this.tokenString = String.valueOf(this.tokenString) + this.data.charAt(this.pos++);
                            break;
                        }
                        break;
                    }
                    case '>': {
                        if (char1 == '=') {
                            this.tokenString = String.valueOf(this.tokenString) + this.data.charAt(this.pos++);
                            break;
                        }
                        if (char1 == '<') {
                            this.tokenString = String.valueOf(this.data.charAt(this.pos++)) + this.tokenString;
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
}
