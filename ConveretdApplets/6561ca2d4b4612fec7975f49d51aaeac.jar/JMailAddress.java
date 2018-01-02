// 
// Decompiled by Procyon v0.5.30
// 

public class JMailAddress
{
    String source;
    String name;
    String first;
    String last;
    String address;
    boolean quoted;
    boolean parens;
    boolean justAddress;
    boolean bracketed;
    boolean nullAddress;
    boolean nonquoted;
    char space;
    char openBracket;
    char closeBracket;
    char quote;
    char openParen;
    char closeParen;
    int openInt;
    int closeInt;
    int quoteInt;
    int spaceInt;
    int oParenInt;
    int cParenInt;
    
    public JMailAddress(final String source) {
        this.quoted = false;
        this.parens = false;
        this.justAddress = false;
        this.bracketed = false;
        this.nullAddress = false;
        this.nonquoted = false;
        this.space = ' ';
        this.openBracket = '<';
        this.closeBracket = '>';
        this.quote = '\"';
        this.openParen = '(';
        this.closeParen = ')';
        this.openInt = this.openBracket;
        this.closeInt = this.closeBracket;
        this.quoteInt = this.quote;
        this.spaceInt = this.space;
        this.oParenInt = this.openParen;
        this.cParenInt = this.closeParen;
        this.source = source;
        final int index = this.source.indexOf(this.spaceInt);
        if (this.source.equals("<>") || this.source.trim().equals("")) {
            this.nullAddress = true;
        }
        if (this.source.startsWith("<")) {
            this.bracketed = true;
        }
        if (index == -1) {
            this.justAddress = true;
        }
        if (this.source.startsWith("\"")) {
            this.quoted = true;
        }
        if (this.source.endsWith(")")) {
            this.parens = true;
        }
        if (!this.source.startsWith("<") && this.source.endsWith(">")) {
            this.nonquoted = true;
        }
        if (this.nullAddress) {
            this.address = "<>";
            this.name = "";
            this.first = "";
            this.last = "";
            return;
        }
        if (this.bracketed) {
            this.address = this.source.substring(1, this.source.indexOf(this.closeInt));
            this.name = "";
            this.first = "";
            this.last = "";
            return;
        }
        if (this.quoted) {
            final int index2 = this.source.indexOf(this.openInt);
            final int index3 = this.source.indexOf(this.closeInt);
            final int lastIndex = this.source.lastIndexOf(this.quoteInt);
            this.address = this.source.substring(index2 + 1, index3);
            this.name = this.source.substring(1, lastIndex);
            final int lastIndex2 = this.name.lastIndexOf(this.spaceInt);
            if (lastIndex2 == -1) {
                this.first = this.name;
                this.last = "";
                return;
            }
            this.first = this.name.substring(0, lastIndex2);
            this.last = this.name.substring(lastIndex2 + 1);
        }
        else {
            if (this.justAddress) {
                this.address = this.source;
                this.name = "";
                this.first = "";
                this.last = "";
                return;
            }
            if (this.parens) {
                final int index4 = this.source.indexOf(this.spaceInt);
                this.name = this.source.substring(this.source.indexOf(this.oParenInt) + 1, this.source.indexOf(this.cParenInt));
                this.address = this.source.substring(0, index4);
                final int lastIndex3 = this.name.lastIndexOf(this.spaceInt);
                if (lastIndex3 == -1) {
                    this.first = this.name;
                    this.last = "";
                    return;
                }
                this.first = this.name.substring(0, lastIndex3);
                this.last = this.name.substring(lastIndex3 + 1);
            }
            else {
                if (!this.nonquoted) {
                    this.address = this.source;
                    this.name = "";
                    this.first = "";
                    this.last = "";
                    return;
                }
                final int index5 = this.source.indexOf(this.openInt);
                this.address = this.source.substring(index5 + 1, this.source.indexOf(this.closeInt));
                this.name = this.source.substring(0, index5);
                if (this.name.endsWith(" ")) {
                    this.name = this.name.substring(0, this.name.length() - 1);
                }
                final int lastIndex4 = this.name.lastIndexOf(this.spaceInt);
                if (lastIndex4 == -1) {
                    this.first = this.name;
                    this.last = "";
                    return;
                }
                this.first = this.name.substring(0, lastIndex4);
                this.last = this.name.substring(lastIndex4 + 1);
            }
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getFirst() {
        return this.first;
    }
    
    public String getLast() {
        return this.last;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String toString() {
        return this.source;
    }
    
    public String getShort() {
        String s;
        if (this.name.equals("")) {
            s = this.address.trim();
        }
        else {
            s = this.name.trim();
        }
        for (int i = s.indexOf(9); i != -1; i = s.indexOf(9)) {
            s = String.valueOf(s.substring(0, i)) + "   " + s.substring(i + 1);
        }
        return s;
    }
}
