// 
// Decompiled by Procyon v0.5.30
// 

public class TokenList
{
    private static final String CLASS_NAME = "TokenList";
    private static final String BKSLASH_ERROR = "No character following a backslash ('\\')";
    private static final String MISSN_PAREN = "Missing a closing parenthesis (')')";
    private static final String MISSN_SQIGBRKT = "Missing a closing squiggly bracket ('}')";
    private static final String MISSN_SQRBRKT = "Missing a closing square bracket (']')";
    private static final String MISSN_VERTBAR = "Missing a closing vertical bar ('|')";
    private static final String UNXP_PAREN = "Unexpected closing parenthesis (')')";
    private static final String UNXP_SQIGBRKT = "Unexpected closing squiggly bracket ('}')";
    private static final String UNXP_SQRBRKT = "Unexpected closing square bracket (']')";
    private Token token;
    private TokenList next;
    
    public TokenList(final Token token) {
        this.token = token;
        this.next = null;
    }
    
    public TokenList(final StringStream stringStream, final char c) throws LexError {
        this(stringStream, c, true);
    }
    
    public TokenList(final StringStream stringStream, final char c, final boolean b) throws LexError {
        this.token = null;
        this.next = null;
        final StringBuffer sb = new StringBuffer(80);
        char read;
        while ((read = stringStream.read()) != '\0' && read != c) {
            if (read == ' ') {
                if (sb.length() > 0) {
                    this.append(new Token(5, sb.toString()));
                }
                sb.setLength(0);
            }
            else if (read == '\\') {
                final char read2;
                if ((read2 = stringStream.read()) == '\0') {
                    throw new LexError("No character following a backslash ('\\')");
                }
                sb.append(read2);
            }
            else if (read == '|') {
                char read3;
                while ((read3 = stringStream.read()) != '\0' && read3 != '|') {
                    sb.append(read3);
                }
                if (read3 != '\0') {
                    continue;
                }
                this.throwMissingError('|');
            }
            else if (read == ';') {
                if (sb.length() > 0) {
                    this.append(new Token(5, sb.toString()));
                }
                sb.setLength(0);
                char read4;
                while ((read4 = stringStream.read()) != '\0') {
                    sb.append(read4);
                }
                this.append(new Token(1, sb.toString()));
                sb.setLength(0);
            }
            else if (b && read == '(') {
                if (sb.length() > 0) {
                    this.append(new Token(5, sb.toString()));
                }
                sb.setLength(0);
                final TokenList list = new TokenList(stringStream, ')', true);
                if (stringStream.reread() != ')') {
                    this.throwMissingError(')');
                }
                this.append(new Token(3, list));
            }
            else if (read == '[' || read == '{') {
                if (sb.length() > 0) {
                    this.append(new Token(5, sb.toString()));
                }
                sb.setLength(0);
                char c2 = '\0';
                int n = -1;
                switch (read) {
                    case '[': {
                        c2 = ']';
                        n = 2;
                        break;
                    }
                    case '{': {
                        c2 = '}';
                        n = 4;
                        break;
                    }
                }
                final TokenList list2 = new TokenList(stringStream, c2, false);
                if (stringStream.reread() != c2) {
                    this.throwMissingError(c2);
                }
                this.append(new Token(n, list2));
            }
            else {
                if (b && read == ')') {
                    throw new LexError("Unexpected closing parenthesis (')')");
                }
                if (read == ']') {
                    throw new LexError("Unexpected closing square bracket (']')");
                }
                if (read == '}') {
                    throw new LexError("Unexpected closing squiggly bracket ('}')");
                }
                if (read < ' ') {
                    continue;
                }
                sb.append(read);
            }
        }
        if (sb.length() > 0) {
            this.append(new Token(5, sb.toString()));
        }
    }
    
    private void append(final Token token) {
        if (this.token == null) {
            this.token = token;
        }
        else {
            TokenList next;
            for (next = this; next.next != null; next = next.next) {}
            next.next = new TokenList(token);
        }
    }
    
    private void throwMissingError(final char c) throws LexError {
        if (c == '|') {
            throw new LexError("Missing a closing vertical bar ('|')");
        }
        if (c == ')') {
            throw new LexError("Missing a closing parenthesis (')')");
        }
        if (c == ']') {
            throw new LexError("Missing a closing square bracket (']')");
        }
        if (c == '}') {
            throw new LexError("Missing a closing squiggly bracket ('}')");
        }
    }
    
    public Token getToken() {
        return this.token;
    }
    
    public TokenList getNext() {
        return this.next;
    }
    
    public int length() {
        int n = 0;
        if (this.token != null) {
            n = 1;
            for (TokenList list = this.next; list != null; list = list.next) {
                ++n;
            }
        }
        return n;
    }
    
    public String toString() {
        if (this.token == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(this.token.toString());
        for (TokenList list = this.next; list != null; list = list.next) {
            sb.append(' ');
            sb.append(list.token.toString());
        }
        return sb.toString();
    }
}
