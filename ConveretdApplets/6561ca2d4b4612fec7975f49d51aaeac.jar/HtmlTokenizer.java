import java.util.Hashtable;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class HtmlTokenizer
{
    private InputStream in;
    private byte[] inBuf;
    private char[] buf;
    private int index;
    private int length;
    
    protected HtmlTokenizer(final InputStream in) {
        this.inBuf = new byte[100];
        this.buf = new char[200];
        this.in = in;
    }
    
    protected Hashtable getOpenTag(final String s) {
        try {
            this.index = 0;
            while (Character.isSpace(this.read())) {}
            --this.index;
            if (this.read() != '<') {
                return null;
            }
            while (Character.isSpace(this.read())) {}
            --this.index;
            if (!s.equalsIgnoreCase(this.readIdentifier())) {
                return null;
            }
            while (Character.isSpace(this.read())) {}
            --this.index;
            final Hashtable<String, String> hashtable = new Hashtable<String, String>();
            while (this.read() != '>') {
                --this.index;
                this.clear();
                final String identifier = this.readIdentifier();
                String identifier2 = "";
                while (Character.isSpace(this.read())) {}
                --this.index;
                if (this.read() == '=') {
                    while (Character.isSpace(this.read())) {}
                    --this.index;
                    identifier2 = this.readIdentifier();
                    while (Character.isSpace(this.read())) {}
                    --this.index;
                }
                hashtable.put(identifier.toUpperCase(), identifier2);
            }
            this.clear();
            return hashtable;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    protected boolean getCloseTag(final String s) {
        try {
            this.index = 0;
            while (Character.isSpace(this.read())) {}
            --this.index;
            if (this.read() != '<') {
                return false;
            }
            while (Character.isSpace(this.read())) {}
            --this.index;
            if (this.read() != '/') {
                return false;
            }
            while (Character.isSpace(this.read())) {}
            --this.index;
            if (!s.equalsIgnoreCase(this.readIdentifier())) {
                return false;
            }
            this.readUntil('>');
            this.read();
            this.clear();
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    protected boolean eof() {
        this.index = 0;
        try {
            this.read();
            return false;
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    protected void getTagOrText() {
        try {
            this.index = 0;
            while (Character.isSpace(this.read())) {}
            --this.index;
            if (this.read() == '<') {
                this.readUntil('>');
                this.read();
                this.clear();
                return;
            }
            this.getText();
        }
        catch (Exception ex) {
            this.getText();
        }
    }
    
    protected String getText() {
        try {
            this.index = 0;
            if (this.read() == '<') {
                return null;
            }
            --this.index;
            final String until = this.readUntil('<');
            this.clear();
            return until;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    protected String getPreformattedText(final String s) {
        this.index = 0;
        try {
            while (!this.endOfPreformattedText(s)) {
                this.read();
            }
        }
        catch (Exception ex) {}
        this.index -= 1 + s.length();
        if (this.index == 0) {
            return null;
        }
        final String massagedString = this.makeMassagedString(this.buf, 0, this.index);
        this.clear();
        return massagedString;
    }
    
    private boolean endOfPreformattedText(final String s) {
        if (this.index < 1 + s.length()) {
            return false;
        }
        if (this.buf[this.index - 1 - s.length()] != '<') {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != this.buf[this.index - s.length() + i]) {
                return false;
            }
        }
        return true;
    }
    
    private void reset() {
        this.index = 0;
    }
    
    private void clear() {
        int n = 0;
        for (int i = this.index; i < this.length; ++i) {
            this.buf[n] = this.buf[i];
            ++n;
        }
        this.length -= this.index;
        this.index = 0;
    }
    
    private char read() throws Exception {
        while (this.index == this.length) {
            final int read = this.in.read(this.inBuf);
            if (read <= 0) {
                throw new Exception();
            }
            if (this.length + read > this.buf.length) {
                final char[] buf = new char[this.buf.length + this.inBuf.length];
                System.arraycopy(this.buf, 0, buf, 0, this.length);
                this.buf = buf;
            }
            for (int i = 0; i < read; ++i, ++this.length) {
                this.buf[this.length] = (char)(this.inBuf[i] & 0xFF);
            }
        }
        return this.buf[this.index++];
    }
    
    private void readSpaces() throws Exception {
        while (Character.isSpace(this.read())) {}
        --this.index;
    }
    
    private String readIdentifier() throws Exception {
        final int index = this.index;
        final char read = this.read();
        if (read == '\"') {
            final String until = this.readUntil(read);
            this.read();
            return until;
        }
        if (this.identifierChar(read)) {
            while (this.identifierChar(this.read())) {}
            --this.index;
            return new String(this.buf, index, this.index - index);
        }
        while (!this.identifierChar(this.read())) {}
        --this.index;
        return new String(this.buf, index, 1);
    }
    
    private boolean identifierChar(final char c) {
        return c == '_' || c == '#' || c == '+' || c == '-' || Character.isLetterOrDigit(c);
    }
    
    private String readUntil(final char c) {
        final int index = this.index;
        try {
            while (this.read() != c) {}
            --this.index;
        }
        catch (Exception ex) {}
        for (int i = index; i < this.index; ++i) {
            if (Character.isSpace(this.buf[i])) {
                this.buf[i] = ' ';
            }
        }
        return this.makeMassagedString(this.buf, index, this.index - index);
    }
    
    private String makeMassagedString(final char[] array, final int n, final int n2) {
        final char[] array2 = new char[n2];
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            final char c = array[n + i];
            if (c == '&') {
                int n4 = -1;
                char c2 = ' ';
                for (int n5 = i + 1; n4 < 0 && n5 < n2; ++n5) {
                    if (array[n + n5] == ';') {
                        final String s = new String(array, n + i + 1, n5 - i - 1);
                        if (s.equals("lt")) {
                            c2 = '<';
                            n4 = n5;
                        }
                        else if (s.equals("gt")) {
                            c2 = '>';
                            n4 = n5;
                        }
                        else if (s.equals("amp")) {
                            c2 = '&';
                            n4 = n5;
                        }
                        else if (s.equals("quot")) {
                            c2 = '\"';
                            n4 = n5;
                        }
                        else if (s.equals("nbsp")) {
                            c2 = ' ';
                            n4 = n5;
                        }
                        else if (s.equals("copy")) {
                            c2 = '©';
                            n4 = n5;
                        }
                        else if (s.equals("reg")) {
                            c2 = '®';
                            n4 = n5;
                        }
                        else if (s.equals("Agrave")) {
                            c2 = '\u00c0';
                            n4 = n5;
                        }
                        else if (s.equals("agrave")) {
                            c2 = '\u00e0';
                            n4 = n5;
                        }
                        else if (s.equals("Aacute")) {
                            c2 = '\u00c1';
                            n4 = n5;
                        }
                        else if (s.equals("aacute")) {
                            c2 = '\u00e1';
                            n4 = n5;
                        }
                        else if (s.equals("Acirc")) {
                            c2 = '\u00c2';
                            n4 = n5;
                        }
                        else if (s.equals("acirc")) {
                            c2 = '\u00c2';
                            n4 = n5;
                        }
                        else if (s.equals("Atilde")) {
                            c2 = '\u00c3';
                            n4 = n5;
                        }
                        else if (s.equals("atilde")) {
                            c2 = '\u00e3';
                            n4 = n5;
                        }
                        else if (s.equals("Auml")) {
                            c2 = '\u00c4';
                            n4 = n5;
                        }
                        else if (s.equals("auml")) {
                            c2 = '\u00e4';
                            n4 = n5;
                        }
                        else if (s.equals("Aring")) {
                            c2 = '\u00c5';
                            n4 = n5;
                        }
                        else if (s.equals("aring")) {
                            c2 = '\u00e5';
                            n4 = n5;
                        }
                        else if (s.equals("Aelig")) {
                            c2 = '\u00c6';
                            n4 = n5;
                        }
                        else if (s.equals("aelig")) {
                            c2 = '\u00e6';
                            n4 = n5;
                        }
                        else if (s.equals("Ccedil")) {
                            c2 = '\u00c7';
                            n4 = n5;
                        }
                        else if (s.equals("ccedil")) {
                            c2 = '\u00e7';
                            n4 = n5;
                        }
                        else if (s.equals("Egrave")) {
                            c2 = '\u00c8';
                            n4 = n5;
                        }
                        else if (s.equals("egrave")) {
                            c2 = '\u00e8';
                            n4 = n5;
                        }
                        else if (s.equals("Eacute")) {
                            c2 = '\u00c9';
                            n4 = n5;
                        }
                        else if (s.equals("eacute")) {
                            c2 = '\u00e9';
                            n4 = n5;
                        }
                        else if (s.equals("Ecirc")) {
                            c2 = '\u00ca';
                            n4 = n5;
                        }
                        else if (s.equals("ecirc")) {
                            c2 = '\u00ea';
                            n4 = n5;
                        }
                        else if (s.equals("Euml")) {
                            c2 = '\u00cb';
                            n4 = n5;
                        }
                        else if (s.equals("euml")) {
                            c2 = '\u00eb';
                            n4 = n5;
                        }
                        else if (s.equals("Igrave")) {
                            c2 = '\u00cc';
                            n4 = n5;
                        }
                        else if (s.equals("igrave")) {
                            c2 = '\u00ec';
                            n4 = n5;
                        }
                        else if (s.equals("Iacute")) {
                            c2 = '\u00cd';
                            n4 = n5;
                        }
                        else if (s.equals("iacute")) {
                            c2 = '\u00ed';
                            n4 = n5;
                        }
                        else if (s.equals("Icirc")) {
                            c2 = '\u00ce';
                            n4 = n5;
                        }
                        else if (s.equals("icirc")) {
                            c2 = '\u00ee';
                            n4 = n5;
                        }
                        else if (s.equals("Iuml")) {
                            c2 = '\u00cf';
                            n4 = n5;
                        }
                        else if (s.equals("iuml")) {
                            c2 = '\u00ef';
                            n4 = n5;
                        }
                        else if (s.equals("ETH")) {
                            c2 = '\u00d0';
                            n4 = n5;
                        }
                        else if (s.equals("eth")) {
                            c2 = '\u00f0';
                            n4 = n5;
                        }
                        else if (s.equals("Ntilde")) {
                            c2 = '\u00d1';
                            n4 = n5;
                        }
                        else if (s.equals("ntilde")) {
                            c2 = '\u00f1';
                            n4 = n5;
                        }
                        else if (s.equals("Ograve")) {
                            c2 = '\u00d2';
                            n4 = n5;
                        }
                        else if (s.equals("ograve")) {
                            c2 = '\u00f2';
                            n4 = n5;
                        }
                        else if (s.equals("Oacute")) {
                            c2 = '\u00d3';
                            n4 = n5;
                        }
                        else if (s.equals("oacute")) {
                            c2 = '\u00f3';
                            n4 = n5;
                        }
                        else if (s.equals("Ocirc")) {
                            c2 = '\u00d4';
                            n4 = n5;
                        }
                        else if (s.equals("ocirc")) {
                            c2 = '\u00f4';
                            n4 = n5;
                        }
                        else if (s.equals("Otilde")) {
                            c2 = '\u00d5';
                            n4 = n5;
                        }
                        else if (s.equals("otilde")) {
                            c2 = '\u00f5';
                            n4 = n5;
                        }
                        else if (s.equals("Ouml")) {
                            c2 = '\u00d6';
                            n4 = n5;
                        }
                        else if (s.equals("ouml")) {
                            c2 = '\u00f6';
                            n4 = n5;
                        }
                        else if (s.equals("Oslash")) {
                            c2 = '\u00d8';
                            n4 = n5;
                        }
                        else if (s.equals("oslash")) {
                            c2 = '\u00f8';
                            n4 = n5;
                        }
                        else if (s.equals("Ugrave")) {
                            c2 = '\u00d9';
                            n4 = n5;
                        }
                        else if (s.equals("ugrave")) {
                            c2 = '\u00f9';
                            n4 = n5;
                        }
                        else if (s.equals("Uacute")) {
                            c2 = '\u00da';
                            n4 = n5;
                        }
                        else if (s.equals("uacute")) {
                            c2 = '\u00fa';
                            n4 = n5;
                        }
                        else if (s.equals("Ucirc")) {
                            c2 = '\u00db';
                            n4 = n5;
                        }
                        else if (s.equals("ucirc")) {
                            c2 = '\u00fb';
                            n4 = n5;
                        }
                        else if (s.equals("Uuml")) {
                            c2 = '\u00dc';
                            n4 = n5;
                        }
                        else if (s.equals("uuml")) {
                            c2 = '\u00fc';
                            n4 = n5;
                        }
                        else if (s.equals("Yacute")) {
                            c2 = '\u00dd';
                            n4 = n5;
                        }
                        else if (s.equals("uacute")) {
                            c2 = '\u00fd';
                            n4 = n5;
                        }
                        else if (s.equals("THORN")) {
                            c2 = '\u00de';
                            n4 = n5;
                        }
                        else if (s.equals("thorn")) {
                            c2 = '\u00fe';
                            n4 = n5;
                        }
                        else if (s.equals("szlig")) {
                            c2 = '\u00df';
                            n4 = n5;
                        }
                        else if (s.equals("uuml")) {
                            c2 = '\u00ff';
                            n4 = n5;
                        }
                        else if (s.charAt(0) == '#') {
                            int n6 = 0;
                            for (int j = i + 2; j < n5; ++j) {
                                n6 = 10 * n6 + array[n + j] - 48;
                                n4 = n5;
                            }
                            c2 = (char)n6;
                        }
                    }
                }
                if (n4 > 0) {
                    array2[n3++] = c2;
                    i = n4;
                }
                else {
                    array2[n3++] = c;
                }
            }
            else {
                array2[n3++] = c;
            }
        }
        return new String(array2, 0, n3);
    }
}
