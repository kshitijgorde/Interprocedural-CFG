// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import java.util.Enumeration;

public class MenuItemTokenizer implements Enumeration
{
    public static final int MIT_NOTHING = -1;
    public static final int MIT_IDERROR = -2;
    public static final int MIT_STRDATA = 1;
    public static final int MIT_IDENT = 2;
    public static final int MIT_LPAREN = 3;
    public static final int MIT_RPAREN = 4;
    public static final int MIT_LCURLY = 5;
    public static final int MIT_RCURLY = 6;
    public static final int MIT_COMMA = 7;
    public static final int MIT_SEMI = 8;
    public static final int MIT_EQU = 9;
    public static final int MIT_INT = 10;
    public static final int MIT_BOOL = 11;
    public static final int MIT_COMMENT = 12;
    private static final int QUOTE_SINGLE = 0;
    private static final int QUOTE_DOUBLE = 1;
    private static final int SINGLELINE_COMMENT = 0;
    private static final int MULTILINE_COMMENT = 1;
    private static boolean consumeComments;
    protected String stream;
    protected int tokenType;
    private int streamPrevPos;
    private int streamPos;
    private int maxStreamPos;
    private int lineNo;
    private int linePrevNo;
    
    public void finalize() {
        this.stream = null;
    }
    
    public static void setConsumeComments(final boolean consumeComments) {
        MenuItemTokenizer.consumeComments = consumeComments;
    }
    
    protected int read() {
        if (this.streamPos > this.maxStreamPos) {
            return -1;
        }
        return this.stream.charAt(this.streamPos++);
    }
    
    protected int peek() {
        if (this.streamPos > this.maxStreamPos) {
            return -1;
        }
        return this.stream.charAt(this.streamPos);
    }
    
    protected void pushBack() {
        if (this.streamPos > 0) {
            --this.streamPos;
        }
    }
    
    public int ttype() {
        return this.tokenType;
    }
    
    public int getLineNo() {
        return this.lineNo;
    }
    
    public void pushTokenBack() {
        if (this.streamPos != this.streamPrevPos) {
            this.streamPos = this.streamPrevPos;
            this.lineNo = this.linePrevNo;
        }
    }
    
    public String nextToken() {
        String s = this.composeToken();
        if (MenuItemTokenizer.consumeComments) {
            while (this.hasMoreTokens() && this.ttype() == 12) {
                s = this.nextToken();
            }
            if (!this.hasMoreTokens() && this.ttype() == 12) {
                return null;
            }
        }
        return s;
    }
    
    protected String composeToken() {
        this.streamPrevPos = this.streamPos;
        this.linePrevNo = this.lineNo;
        String s = "";
        int n;
        for (n = this.read(); Character.isWhitespace((char)n); n = this.read()) {
            if (n == 13) {
                ++this.lineNo;
            }
        }
        switch ((char)n) {
            case '\"':
            case '\'': {
                final boolean b = n == 34;
                int n2 = this.read();
                if (n2 != 39 && n2 != 34) {
                    while (n2 != -1 && n2 != 13) {
                        final char c = (char)n2;
                        n2 = this.read();
                        if (c != '\\') {
                            s += c;
                            if ((n2 == 39 && !b) || (n2 == 34 && b)) {
                                break;
                            }
                            continue;
                        }
                        else {
                            if ("nrt".indexOf(n2) == -1) {
                                continue;
                            }
                            s += c;
                        }
                    }
                }
                if ((b && n2 == 39) || (!b && n2 == 34)) {
                    throw new IllegallyTerminatedStringException(this.lineNo);
                }
                if (n2 == -1 || n2 == 13) {
                    throw new UnterminatedStringException(this.lineNo, s);
                }
                this.tokenType = 1;
                return s;
            }
            case '(': {
                this.tokenType = 3;
                return s + '(';
            }
            case ')': {
                this.tokenType = 4;
                return s + ')';
            }
            case '{': {
                this.tokenType = 5;
                return s + '{';
            }
            case '}': {
                this.tokenType = 6;
                return s + '}';
            }
            case ',': {
                this.tokenType = 7;
                return s + ',';
            }
            case '=': {
                this.tokenType = 9;
                return s + '=';
            }
            case ';': {
                this.tokenType = 8;
                return s + ';';
            }
            case '/': {
                n = this.peek();
                if (n != -1 && (n == 47 || n == 42)) {
                    int n3 = this.read();
                    if (n3 == 47) {
                        int n4;
                        for (n4 = this.read(); n4 != -1 && n4 != 13; n4 = this.read()) {
                            s += (char)n4;
                        }
                        if (n4 == 13) {
                            ++this.lineNo;
                        }
                    }
                    else {
                        while (n3 != -1 && (n3 != 42 || this.peek() != 47)) {
                            if (n3 == 13) {
                                ++this.lineNo;
                            }
                            s += (char)n3;
                            n3 = this.read();
                        }
                        if (n3 == -1) {
                            throw new UnterminatedCommentException(this.lineNo, s);
                        }
                        this.read();
                    }
                    this.tokenType = 12;
                    return s;
                }
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
                while (n != -1 && n != 13 && Character.isDigit((char)n)) {
                    s += (char)n;
                    n = this.read();
                }
                if (n != -1) {
                    this.pushBack();
                }
                this.tokenType = 10;
                return s;
            }
            default: {
                while (Character.isLetterOrDigit((char)n) || n == 95) {
                    s += (char)n;
                    n = this.read();
                }
                this.tokenType = 2;
                this.pushBack();
                if ("truefalse".indexOf(s.toLowerCase()) != -1) {
                    this.tokenType = 11;
                }
                if (s.length() == 0) {
                    int i;
                    for (i = this.read(); i != -1 && Character.isWhitespace((char)i); i = this.read()) {
                        if (i == 13) {
                            ++this.lineNo;
                        }
                    }
                    while (i != -1) {
                        if (i == 95 || Character.isLetterOrDigit((char)i) || "\"',(){};=/".indexOf((char)i) != -1) {
                            this.pushBack();
                            break;
                        }
                        s += (char)i;
                        i = this.read();
                    }
                    this.tokenType = -2;
                }
                return s;
            }
        }
    }
    
    public boolean hasMoreTokens() {
        return this.streamPos <= this.maxStreamPos;
    }
    
    public boolean hasMoreElements() {
        return this.hasMoreTokens();
    }
    
    public Object nextElement() {
        return this.nextToken();
    }
    
    public String typeToString(final int n) {
        switch (n) {
            case 1: {
                return "String data";
            }
            case 2: {
                return "An identifier";
            }
            case 3: {
                return "Left parenthesis";
            }
            case 4: {
                return "Right parenthesis";
            }
            case 5: {
                return "Left brace {";
            }
            case 6: {
                return "Right brace }";
            }
            case 7: {
                return "Comma (parameter separator ',')";
            }
            case 9: {
                return "Equevalency sign '='";
            }
            case 8: {
                return "Semicolon";
            }
            case -2: {
                return "Errored item identifier";
            }
            case 11: {
                return "Value of type boolean";
            }
            case 10: {
                return "An integer";
            }
            case 12: {
                return "A comment";
            }
            default: {
                return "Nothing";
            }
        }
    }
    
    public MenuItemTokenizer(final String stream) {
        this.tokenType = -1;
        this.streamPrevPos = 0;
        this.streamPos = 0;
        this.stream = stream;
        this.maxStreamPos = stream.length() - 1;
        final boolean b = true;
        this.linePrevNo = (b ? 1 : 0);
        this.lineNo = (b ? 1 : 0);
    }
    
    static {
        MenuItemTokenizer.consumeComments = true;
    }
}
