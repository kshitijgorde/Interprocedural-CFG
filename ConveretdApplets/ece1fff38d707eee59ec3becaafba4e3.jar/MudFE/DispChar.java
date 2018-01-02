// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

class DispChar
{
    char character;
    Attribute attribute;
    
    char getChar() {
        return this.character;
    }
    
    void setChar(final char ch) {
        this.character = ch;
    }
    
    Attribute getAttribute() {
        return this.attribute;
    }
    
    void setAttribute(final Attribute a) {
        this.attribute = a;
    }
    
    void set(final char ch, final Attribute a) {
        this.setChar(ch);
        this.setAttribute(a);
    }
    
    DispChar() {
        this.character = ' ';
        this.attribute = Attribute._defaultat;
    }
    
    DispChar(final char ch) {
        this.character = ch;
        this.attribute = Attribute._defaultat;
    }
    
    DispChar(final Attribute att) {
        this.character = ' ';
        this.attribute = att;
    }
    
    DispChar(final DispChar dc) {
        this.character = dc.getChar();
        this.attribute = dc.getAttribute();
    }
    
    DispChar(final char ch, final Attribute a) {
        this.character = ch;
        this.attribute = a;
    }
}
