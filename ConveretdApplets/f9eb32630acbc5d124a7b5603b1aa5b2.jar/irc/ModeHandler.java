// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Vector;

public class ModeHandler
{
    private String _mode;
    private Vector _parameters;
    private char[][] _modes;
    private char[] _prefix;
    
    public ModeHandler(final char[][] array, final char[] array2) {
        this("", array, array2);
    }
    
    public ModeHandler(String substring, final char[][] modes, final char[] prefix) {
        this._modes = modes;
        this._prefix = prefix;
        this._mode = "";
        this._parameters = new Vector();
        if (substring.startsWith("+")) {
            substring = substring.substring(1);
        }
        this.apply("+" + substring);
    }
    
    public void reset() {
        this._mode = "";
    }
    
    public String getPassword() {
        if (this.hasMode('k')) {
            return this.findParameter('k');
        }
        return "";
    }
    
    public int getLimit() {
        if (this.hasMode('l')) {
            return new Integer(this.findParameter('l'));
        }
        return 0;
    }
    
    private boolean inside(final char[] array, final char c) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == c) {
                return true;
            }
        }
        return false;
    }
    
    private String findParameter(final char c) {
        final int parameterIndex = this.getParameterIndex(c);
        if (parameterIndex >= 0) {
            return (String)this._parameters.elementAt(parameterIndex);
        }
        return "";
    }
    
    private boolean hasParameter(final boolean b, final char c) {
        return !this.inside(this._prefix, c) && (this.inside(this._modes[0], c) || this.inside(this._modes[1], c) || (this.inside(this._modes[2], c) && b));
    }
    
    private int getParameterIndex(final char c) {
        if (this._parameters.size() == 0) {
            return -1;
        }
        int n = 0;
        for (int i = 0; i < this._mode.length(); ++i) {
            final char char1 = this._mode.charAt(i);
            if (this.hasParameter(true, char1)) {
                if (char1 == c) {
                    return n;
                }
                if (++n >= this._parameters.size()) {
                    return -1;
                }
            }
            else if (char1 == c) {
                return -1;
            }
        }
        return -1;
    }
    
    private void addMode(final char c, final String s) {
        if (this.hasMode(c)) {
            this.removeMode(c, s);
        }
        this._mode += c;
        if (this.hasParameter(true, c)) {
            this._parameters.insertElementAt(s, this._parameters.size());
        }
    }
    
    private void removeMode(final char c, final String s) {
        if (!this.hasMode(c)) {
            return;
        }
        if (this.hasParameter(true, c)) {
            this._parameters.removeElementAt(this.getParameterIndex(c));
        }
        final int index = this._mode.indexOf(c);
        this._mode = this._mode.substring(0, index) + this._mode.substring(index + 1);
    }
    
    private void applyOne(final boolean b, final char c, final String s) {
        if (b) {
            this.addMode(c, s);
        }
        else {
            this.removeMode(c, s);
        }
    }
    
    public void apply(final String s) {
        final String[] string = new StringParser().parseString(s);
        boolean b = true;
        int n = 0;
        for (int i = 0; i < string[0].length(); ++i) {
            final char char1 = string[0].charAt(i);
            if (char1 == '+') {
                b = true;
            }
            else if (char1 == '-') {
                b = false;
            }
            else {
                String s2 = "";
                if (this.hasParameter(b, char1)) {
                    if (++n >= string.length) {
                        return;
                    }
                    s2 = string[n];
                }
                this.applyOne(b, char1, s2);
            }
        }
    }
    
    public boolean hasMode(final char c) {
        return this._mode.indexOf(c) != -1;
    }
    
    public String getMode() {
        String s = this._mode;
        for (int i = 0; i < this._parameters.size(); ++i) {
            s = s + " " + this._parameters.elementAt(i);
        }
        return s;
    }
}
