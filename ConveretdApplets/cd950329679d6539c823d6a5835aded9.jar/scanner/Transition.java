// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

public class Transition
{
    private char[] chars;
    private State targetState;
    private State sourceState;
    
    public Transition(final State state, final char[] characters) {
        this.chars = null;
        this.targetState = null;
        this.sourceState = null;
        this.targetState = state;
        this.chars = characters;
    }
    
    public Transition(final State state, final char character) {
        this.chars = null;
        this.targetState = null;
        this.sourceState = null;
        this.targetState = state;
        (this.chars = new char[1])[0] = character;
    }
    
    public boolean isPossible(final char character) {
        boolean isPossible = false;
        int index = 0;
        final int length = this.chars.length;
        while (!isPossible && index < length) {
            if (this.chars[index] == character) {
                isPossible = true;
            }
            else {
                ++index;
            }
        }
        return isPossible;
    }
    
    public char[] getChars() {
        return this.chars;
    }
    
    public State getTargetState() {
        return this.targetState;
    }
    
    public State getSourceState() {
        return this.sourceState;
    }
    
    public void setTargetState(final State state) {
        this.targetState = state;
    }
    
    public boolean isSelfTransition() {
        return this.targetState.equals(this.sourceState);
    }
    
    void setSourceState(final State state) {
        if (state == null) {
            this.sourceState = null;
        }
        else {
            if (this.sourceState != null) {
                throw new RuntimeException("Already assigned transition for " + this.toString());
            }
            this.sourceState = state;
        }
    }
    
    public void add(final char[] characters) {
        final char[] allContainedCharactersTemp = new char[characters.length + this.chars.length];
        System.arraycopy(this.chars, 0, allContainedCharactersTemp, 0, this.chars.length);
        int index = this.chars.length;
        for (int i = 0; i < characters.length; ++i) {
            if (!this.contains(characters[i])) {
                allContainedCharactersTemp[index] = characters[i];
                ++index;
            }
        }
        final char[] allContainedCharacters = new char[index];
        System.arraycopy(allContainedCharactersTemp, 0, allContainedCharacters, 0, index);
        this.chars = allContainedCharacters;
    }
    
    public char[] intersect(final char[] parameter) {
        final int charsLength = this.chars.length;
        final int parameterLength = parameter.length;
        char[] commonTemp = null;
        int commonLength = 0;
        if (charsLength < parameterLength) {
            commonTemp = new char[charsLength];
        }
        else {
            commonTemp = new char[parameterLength];
        }
        for (int i = 0; i < charsLength; ++i) {
            if (contains(this.chars[i], parameter)) {
                commonTemp[commonLength] = this.chars[i];
                ++commonLength;
            }
        }
        final char[] common = new char[commonLength];
        System.arraycopy(commonTemp, 0, common, 0, commonLength);
        return common;
    }
    
    public boolean contains(final char[] characters) {
        boolean contains = true;
        int index = 0;
        while (contains && index < characters.length) {
            if (!contains(this.chars[index], characters)) {
                contains = false;
            }
            else {
                ++index;
            }
        }
        return contains;
    }
    
    public boolean contains(final char character) {
        return contains(character, this.chars);
    }
    
    public void substract(final char[] characters) {
        final char[] thisOnlyTemp = new char[this.chars.length];
        int thisOnlyLength = 0;
        for (int i = 0; i < this.chars.length; ++i) {
            if (!contains(this.chars[i], characters)) {
                thisOnlyTemp[thisOnlyLength] = this.chars[i];
                ++thisOnlyLength;
            }
        }
        final char[] thisOnly = new char[thisOnlyLength];
        System.arraycopy(thisOnlyTemp, 0, thisOnly, 0, thisOnlyLength);
        this.chars = thisOnly;
    }
    
    private static boolean contains(final char character, final char[] characters) {
        boolean contains = false;
        int index = 0;
        while (!contains && index < characters.length) {
            if (characters[index] == character) {
                contains = true;
            }
            else {
                ++index;
            }
        }
        return contains;
    }
    
    public String toString() {
        String stringReturned = "[";
        for (int i = 0; i < this.chars.length; ++i) {
            stringReturned = stringReturned.concat(new Character(this.chars[i]).toString() + " ");
        }
        stringReturned = stringReturned.concat("]-> " + this.getTargetState());
        return stringReturned;
    }
}
