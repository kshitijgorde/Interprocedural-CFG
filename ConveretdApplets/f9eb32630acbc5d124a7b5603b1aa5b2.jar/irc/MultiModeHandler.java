// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public class MultiModeHandler
{
    private String[] _parameters;
    private char[][] _modes;
    private char[] _prefix;
    private int _paramIndex;
    private int _codeIndex;
    private boolean _positive;
    private String _currentMode;
    private char _currentModeChar;
    private boolean _currentHasParameter;
    private String _currentParameter;
    private boolean _currentPrefix;
    
    public MultiModeHandler(final String s, final char[][] modes, final char[] prefix) {
        this._modes = modes;
        this._prefix = prefix;
        this._parameters = new StringParser().parseString(s);
        this._paramIndex = 1;
        this._codeIndex = 0;
        this._positive = true;
    }
    
    public boolean terminated() {
        return this._codeIndex == this._parameters[0].length();
    }
    
    public void next() {
        if (this.terminated()) {
            return;
        }
        final char char1 = this._parameters[0].charAt(this._codeIndex++);
        if (char1 == '-') {
            this._positive = false;
            this.next();
            return;
        }
        if (char1 == '+') {
            this._positive = true;
            this.next();
            return;
        }
        this._currentMode = "-";
        if (this._positive) {
            this._currentMode = "+";
        }
        this._currentMode += char1;
        this._currentModeChar = char1;
        if (this.hasParameter(this._positive, char1) && this._paramIndex < this._parameters.length) {
            this._currentHasParameter = true;
            this._currentParameter = this._parameters[this._paramIndex++];
        }
        else {
            this._currentHasParameter = false;
            this._currentParameter = "";
        }
        this._currentPrefix = this.inside(this._prefix, char1);
    }
    
    private boolean inside(final char[] array, final char c) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == c) {
                return true;
            }
        }
        return false;
    }
    
    public String getMode() {
        return this._currentMode;
    }
    
    public boolean hasParameter() {
        return this._currentHasParameter;
    }
    
    public String getParameter() {
        return this._currentParameter;
    }
    
    public boolean isPrefix() {
        return this._currentPrefix;
    }
    
    public boolean isModeA() {
        return this.inside(this._modes[0], this._currentModeChar);
    }
    
    private boolean hasParameter(final boolean b, final char c) {
        return this.inside(this._prefix, c) || this.inside(this._modes[0], c) || this.inside(this._modes[1], c) || (this.inside(this._modes[2], c) && b);
    }
}
