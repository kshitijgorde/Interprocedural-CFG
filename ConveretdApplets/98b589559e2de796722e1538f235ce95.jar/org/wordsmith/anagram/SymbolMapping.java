// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

public class SymbolMapping
{
    final SymbolPosition myNewPosition;
    final SymbolPosition myOldPosition;
    
    public SymbolMapping(final SymbolPosition myOldPosition, final SymbolPosition myNewPosition) {
        this.myOldPosition = myOldPosition;
        this.myNewPosition = myNewPosition;
    }
    
    public SymbolPosition getNewPosition() {
        return this.myNewPosition;
    }
    
    public SymbolPosition getOldPosition() {
        return this.myOldPosition;
    }
    
    public boolean isNew() {
        return this.myOldPosition == null;
    }
    
    public boolean isDeleted() {
        return this.myNewPosition == null;
    }
    
    public char getNewChar() {
        if (this.myNewPosition == null) {
            throw new IllegalStateException("I am deleted mapping");
        }
        return this.myNewPosition.getCharacter();
    }
    
    public char getOldChar() {
        if (this.myOldPosition == null) {
            throw new IllegalStateException("I am new mapping");
        }
        return this.myOldPosition.getCharacter();
    }
    
    public char getSubjectChar() {
        return this.isNew() ? this.getNewChar() : this.getOldChar();
    }
}
