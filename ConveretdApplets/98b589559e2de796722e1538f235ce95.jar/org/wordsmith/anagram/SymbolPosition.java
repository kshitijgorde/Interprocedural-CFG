// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

public class SymbolPosition implements Comparable
{
    private final int myLineNumber;
    private final int myPosition;
    private final char myCharacter;
    
    public SymbolPosition(final int myLineNumber, final int myPosition, final char myCharacter) {
        this.myLineNumber = myLineNumber;
        this.myPosition = myPosition;
        this.myCharacter = myCharacter;
    }
    
    public int getLineNumber() {
        return this.myLineNumber;
    }
    
    public int getPosition() {
        return this.myPosition;
    }
    
    public char getCharacter() {
        return this.myCharacter;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof SymbolPosition) {
            final SymbolPosition symbolPosition = (SymbolPosition)o;
            return this.getLineNumber() == symbolPosition.getLineNumber() && this.getPosition() == symbolPosition.getPosition();
        }
        return false;
    }
    
    public int compareTo(final Object o) {
        final SymbolPosition symbolPosition = (SymbolPosition)o;
        final int n = this.getLineNumber() - symbolPosition.getLineNumber();
        return (n != 0) ? n : (this.getPosition() - symbolPosition.getPosition());
    }
    
    public int hashCode() {
        return 17 * this.getLineNumber() + 3 * this.getPosition();
    }
}
