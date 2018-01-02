// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.oro.text.regex;

final class Perl5MatchResult implements MatchResult
{
    int _matchBeginOffset;
    int[] _beginGroupOffset;
    int[] _endGroupOffset;
    String _match;
    
    Perl5MatchResult(final int n) {
        this._beginGroupOffset = new int[n];
        this._endGroupOffset = new int[n];
    }
    
    public int length() {
        final int n = this._endGroupOffset[0] - this._beginGroupOffset[0];
        return (n > 0) ? n : false;
    }
    
    public int groups() {
        return this._beginGroupOffset.length;
    }
    
    public String group(final int n) {
        if (n < this._beginGroupOffset.length) {
            final int n2 = this._beginGroupOffset[n];
            final int n3 = this._endGroupOffset[n];
            final int length = this._match.length();
            if (n2 >= 0 && n3 >= 0) {
                if (n2 < length && n3 <= length && n3 > n2) {
                    return this._match.substring(n2, n3);
                }
                if (n2 <= n3) {
                    return "";
                }
            }
        }
        return null;
    }
    
    public int begin(final int n) {
        if (n < this._beginGroupOffset.length) {
            final int n2 = this._beginGroupOffset[n];
            final int n3 = this._endGroupOffset[n];
            if (n2 >= 0 && n3 >= 0) {
                return n2;
            }
        }
        return -1;
    }
    
    public int end(final int n) {
        if (n < this._beginGroupOffset.length) {
            final int n2 = this._beginGroupOffset[n];
            final int n3 = this._endGroupOffset[n];
            if (n2 >= 0 && n3 >= 0) {
                return n3;
            }
        }
        return -1;
    }
    
    public int beginOffset(final int n) {
        if (n < this._beginGroupOffset.length) {
            final int n2 = this._beginGroupOffset[n];
            final int n3 = this._endGroupOffset[n];
            if (n2 >= 0 && n3 >= 0) {
                return this._matchBeginOffset + n2;
            }
        }
        return -1;
    }
    
    public int endOffset(final int n) {
        if (n < this._endGroupOffset.length) {
            final int n2 = this._beginGroupOffset[n];
            final int n3 = this._endGroupOffset[n];
            if (n2 >= 0 && n3 >= 0) {
                return this._matchBeginOffset + n3;
            }
        }
        return -1;
    }
    
    public String toString() {
        return this.group(0);
    }
}
