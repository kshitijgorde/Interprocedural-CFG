import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class TokenStream
{
    String str;
    int offset;
    
    TokenStream(final String str) {
        this.offset = 0;
        this.str = str;
        this.skipSpace();
    }
    
    Object[] readList(final LContext lContext) {
        final Vector vector = new Vector<Object>();
        Object token;
        while (!this.eof() && (token = this.readToken(lContext)) != null) {
            vector.addElement(token);
        }
        final Object[] array = new Object[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    Object readToken(final LContext lContext) {
        final String next = this.next();
        try {
            if (next.length() > 2 && next.charAt(0) == '0' && next.charAt(1) == 'x') {
                return new Double(Long.parseLong(next.substring(2), 16));
            }
        }
        catch (NumberFormatException ex) {}
        try {
            if (next.length() > 1 && next.charAt(0) == '$') {
                return new Double(Long.parseLong(next.substring(1), 16));
            }
        }
        catch (NumberFormatException ex2) {}
        try {
            if (next.length() > 1 && next.charAt(0) == '0') {
                return new Double(Long.parseLong(next.substring(1), 8));
            }
        }
        catch (NumberFormatException ex3) {}
        if (next.equals("]")) {
            return null;
        }
        if (Logo.aValidNumber(next)) {
            try {
                return Double.valueOf(next);
            }
            catch (NumberFormatException ex4) {}
        }
        if (next.charAt(0) == '\"') {
            return new QuotedSymbol(Logo.intern(next.substring(1), lContext));
        }
        if (next.charAt(0) == ':') {
            return new DottedSymbol(Logo.intern(next.substring(1), lContext));
        }
        if (next.equals("[")) {
            return this.readList(lContext);
        }
        if (next.charAt(0) == '|') {
            return next.substring(1);
        }
        return Logo.intern(next, lContext);
    }
    
    boolean startsWith(final String s) {
        return this.str.startsWith(s, this.offset);
    }
    
    void skipToNextLine() {
        while (!this.eof() && "\n\r".indexOf(this.str.charAt(this.offset)) == -1) {
            ++this.offset;
        }
        this.skipSpace();
    }
    
    void skipSpace() {
        while (!this.eof() && " ;,\t\r\n".indexOf(this.str.charAt(this.offset)) != -1) {
            if (this.peekChar().equals(";")) {
                while (!this.eof() && "\n\r".indexOf(this.str.charAt(this.offset)) == -1) {
                    ++this.offset;
                }
            }
            else {
                ++this.offset;
            }
        }
    }
    
    String nextLine() {
        String string = "";
        while (!this.eof() && ";\n\r".indexOf(this.peekChar()) == -1) {
            string += this.nextChar();
        }
        this.skipSpace();
        return string;
    }
    
    String next() {
        String s = "";
        if (!this.delim(this.peekChar())) {
            while (!this.eof()) {
                if (this.delim(this.peekChar())) {
                    break;
                }
                if (this.peekChar().equals("|")) {
                    final String string = s + "|" + this.getVbarString();
                    this.skipSpace();
                    return string;
                }
                s += this.nextChar();
            }
        }
        else {
            s = this.nextChar();
        }
        this.skipSpace();
        return s;
    }
    
    String getVbarString() {
        final StringBuffer sb = new StringBuffer();
        this.nextChar();
        while (!this.eof()) {
            if (this.peekChar().equals("|")) {
                this.nextChar();
                return sb.toString();
            }
            sb.append(this.nextChar());
        }
        return sb.toString();
    }
    
    boolean delim(final String s) {
        return "()[] ,\t\r\n".indexOf(s.charAt(0)) != -1;
    }
    
    String peekChar() {
        return String.valueOf(this.str.charAt(this.offset));
    }
    
    String nextChar() {
        return String.valueOf(this.str.charAt(this.offset++));
    }
    
    boolean eof() {
        return this.str.length() == this.offset;
    }
}
