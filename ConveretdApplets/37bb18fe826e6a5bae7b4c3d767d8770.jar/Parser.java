import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class Parser
{
    private StreamTokenizer st;
    
    public Parser(final Reader reader) {
        (this.st = new StreamTokenizer(reader)).resetSyntax();
        this.st.eolIsSignificant(false);
        this.st.lowerCaseMode(false);
        this.st.commentChar(35);
        this.st.slashSlashComments(false);
        this.st.slashStarComments(false);
        this.st.wordChars(97, 122);
        this.st.wordChars(65, 90);
        this.st.wordChars(48, 57);
        this.st.wordChars(94, 94);
        this.st.wordChars(45, 45);
        this.st.wordChars(95, 95);
        this.st.wordChars(39, 39);
        this.st.wordChars(46, 46);
        this.st.wordChars(92, 92);
        this.st.wordChars(47, 47);
        this.st.whitespaceChars(0, 32);
        this.st.whitespaceChars(44, 44);
    }
    
    public String toString() {
        return this.st.toString();
    }
    
    public int getLine() {
        return this.st.lineno();
    }
    
    public void skipOpenBracket() throws IOException {
        this.skipChar('(');
    }
    
    public void skipChar(final char c) throws IOException {
        final int nextToken = this.st.nextToken();
        if (nextToken == -3) {
            throw new IOException("Expected " + c + " but " + this.st.sval + " was found.");
        }
        if (nextToken != c) {
            throw new IOException("Missing " + c + ".");
        }
    }
    
    public int readNumber() throws IOException {
        return this.readNumber(0);
    }
    
    public int readNumber(final int n) throws IOException {
        return this.readNumber(n, n - 1);
    }
    
    public int readNumber(final int n, final int n2) throws IOException {
        final int nextToken = this.st.nextToken();
        switch (nextToken) {
            case -3: {
                int int1;
                try {
                    int1 = Integer.parseInt(this.st.sval);
                }
                catch (NumberFormatException ex) {
                    throw new IOException("Number expected but '" + this.st.sval + "' found.");
                }
                if (int1 < n || (int1 > n2 && n2 >= n)) {
                    throw new IOException("Number " + int1 + " is not in range (" + n + "," + ((n2 >= n) ? ("" + n2) : "inf") + ").");
                }
                return int1;
            }
            case -1: {
                throw new IOException("Number expected.");
            }
            default: {
                throw new IOException("Number expected but '" + (char)nextToken + "' found.");
            }
        }
    }
    
    public String readString() throws IOException {
        return this.readString(false);
    }
    
    public String readString(final boolean b) throws IOException {
        final int nextToken = this.st.nextToken();
        switch (nextToken) {
            case -3: {
                return this.st.sval;
            }
            case -1:
            case 10: {
                if (b) {
                    return null;
                }
                throw new IOException("Unexpected end of file.");
            }
            default: {
                return "" + (char)nextToken;
            }
        }
    }
    
    public void pushback() {
        this.st.pushBack();
    }
}
