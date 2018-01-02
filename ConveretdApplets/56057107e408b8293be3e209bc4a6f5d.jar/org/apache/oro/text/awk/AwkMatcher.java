// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.oro.text.awk;

import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.PatternMatcherInput;
import java.io.IOException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;

public final class AwkMatcher implements PatternMatcher
{
    private int __lastMatchedBufferOffset;
    private AwkMatchResult __lastMatchResult;
    private AwkStreamInput __scratchBuffer;
    private AwkStreamInput __streamSearchBuffer;
    private AwkPattern __awkPattern;
    private int[] __offsets;
    private int __beginOffset;
    
    public AwkMatcher() {
        this.__lastMatchResult = null;
        this.__offsets = new int[2];
        this.__scratchBuffer = new AwkStreamInput();
        this.__scratchBuffer._endOfStreamReached = true;
    }
    
    public boolean matchesPrefix(final char[] buffer, final Pattern pattern, final int n) {
        this.__awkPattern = (AwkPattern)pattern;
        this.__scratchBuffer._buffer = buffer;
        this.__scratchBuffer._bufferSize = buffer.length;
        final AwkStreamInput _scratchBuffer = this.__scratchBuffer;
        final boolean b = false;
        this.__beginOffset = (b ? 1 : 0);
        _scratchBuffer._bufferOffset = (b ? 1 : 0);
        this.__scratchBuffer._endOfStreamReached = true;
        this.__streamSearchBuffer = this.__scratchBuffer;
        this.__offsets[0] = n;
        int _streamMatchPrefix;
        try {
            _streamMatchPrefix = this.__streamMatchPrefix();
        }
        catch (IOException ex) {
            _streamMatchPrefix = -1;
        }
        if (_streamMatchPrefix < 0) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__lastMatchResult = new AwkMatchResult(new String(buffer, 0, _streamMatchPrefix), n);
        return true;
    }
    
    public boolean matchesPrefix(final char[] array, final Pattern pattern) {
        return this.matchesPrefix(array, pattern, 0);
    }
    
    public boolean matchesPrefix(final String s, final Pattern pattern) {
        return this.matchesPrefix(s.toCharArray(), pattern, 0);
    }
    
    public boolean matchesPrefix(final PatternMatcherInput patternMatcherInput, final Pattern pattern) {
        this.__awkPattern = (AwkPattern)pattern;
        this.__scratchBuffer._buffer = patternMatcherInput.getBuffer();
        final AwkStreamInput _scratchBuffer = this.__scratchBuffer;
        final int beginOffset = patternMatcherInput.getBeginOffset();
        this.__beginOffset = beginOffset;
        _scratchBuffer._bufferOffset = beginOffset;
        this.__offsets[0] = patternMatcherInput.getCurrentOffset();
        this.__scratchBuffer._bufferSize = patternMatcherInput.length();
        this.__scratchBuffer._endOfStreamReached = true;
        this.__streamSearchBuffer = this.__scratchBuffer;
        int _streamMatchPrefix;
        try {
            _streamMatchPrefix = this.__streamMatchPrefix();
        }
        catch (IOException ex) {
            _streamMatchPrefix = -1;
        }
        if (_streamMatchPrefix < 0) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__lastMatchResult = new AwkMatchResult(new String(this.__scratchBuffer._buffer, this.__offsets[0], _streamMatchPrefix), this.__offsets[0]);
        return true;
    }
    
    public boolean matches(final char[] buffer, final Pattern pattern) {
        this.__awkPattern = (AwkPattern)pattern;
        this.__scratchBuffer._buffer = buffer;
        this.__scratchBuffer._bufferSize = buffer.length;
        final AwkStreamInput _scratchBuffer = this.__scratchBuffer;
        final boolean b = false;
        this.__beginOffset = (b ? 1 : 0);
        _scratchBuffer._bufferOffset = (b ? 1 : 0);
        this.__scratchBuffer._endOfStreamReached = true;
        this.__streamSearchBuffer = this.__scratchBuffer;
        this.__offsets[0] = 0;
        int _streamMatchPrefix;
        try {
            _streamMatchPrefix = this.__streamMatchPrefix();
        }
        catch (IOException ex) {
            _streamMatchPrefix = -1;
        }
        if (_streamMatchPrefix != buffer.length) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__lastMatchResult = new AwkMatchResult(new String(buffer, 0, _streamMatchPrefix), 0);
        return true;
    }
    
    public boolean matches(final String s, final Pattern pattern) {
        return this.matches(s.toCharArray(), pattern);
    }
    
    public boolean matches(final PatternMatcherInput patternMatcherInput, final Pattern pattern) {
        this.__awkPattern = (AwkPattern)pattern;
        this.__scratchBuffer._buffer = patternMatcherInput.getBuffer();
        this.__scratchBuffer._bufferSize = patternMatcherInput.length();
        final AwkStreamInput _scratchBuffer = this.__scratchBuffer;
        final int beginOffset = patternMatcherInput.getBeginOffset();
        this.__beginOffset = beginOffset;
        _scratchBuffer._bufferOffset = beginOffset;
        this.__offsets[0] = patternMatcherInput.getBeginOffset();
        this.__scratchBuffer._endOfStreamReached = true;
        this.__streamSearchBuffer = this.__scratchBuffer;
        int _streamMatchPrefix;
        try {
            _streamMatchPrefix = this.__streamMatchPrefix();
        }
        catch (IOException ex) {
            _streamMatchPrefix = -1;
        }
        if (_streamMatchPrefix != this.__scratchBuffer._bufferSize) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__lastMatchResult = new AwkMatchResult(new String(this.__scratchBuffer._buffer, this.__offsets[0], this.__scratchBuffer._bufferSize), this.__offsets[0]);
        return true;
    }
    
    public boolean contains(final char[] buffer, final Pattern pattern) {
        this.__awkPattern = (AwkPattern)pattern;
        if (this.__awkPattern._hasBeginAnchor && !this.__awkPattern._fastMap[buffer[0]]) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__scratchBuffer._buffer = buffer;
        this.__scratchBuffer._bufferSize = buffer.length;
        final AwkStreamInput _scratchBuffer = this.__scratchBuffer;
        final boolean b = false;
        this.__beginOffset = (b ? 1 : 0);
        _scratchBuffer._bufferOffset = (b ? 1 : 0);
        this.__scratchBuffer._endOfStreamReached = true;
        this.__streamSearchBuffer = this.__scratchBuffer;
        this.__lastMatchedBufferOffset = 0;
        try {
            this._search();
        }
        catch (IOException ex) {}
        return this.__lastMatchResult != null;
    }
    
    public boolean contains(final String s, final Pattern pattern) {
        return this.contains(s.toCharArray(), pattern);
    }
    
    public boolean contains(final PatternMatcherInput patternMatcherInput, final Pattern pattern) {
        this.__awkPattern = (AwkPattern)pattern;
        this.__scratchBuffer._buffer = patternMatcherInput.getBuffer();
        final AwkStreamInput _scratchBuffer = this.__scratchBuffer;
        final int beginOffset = patternMatcherInput.getBeginOffset();
        this.__beginOffset = beginOffset;
        _scratchBuffer._bufferOffset = beginOffset;
        this.__lastMatchedBufferOffset = patternMatcherInput.getCurrentOffset();
        if (this.__awkPattern._hasBeginAnchor && (this.__beginOffset != this.__lastMatchedBufferOffset || !this.__awkPattern._fastMap[this.__scratchBuffer._buffer[this.__beginOffset]])) {
            this.__lastMatchResult = null;
            return false;
        }
        this.__scratchBuffer._bufferSize = patternMatcherInput.length();
        this.__scratchBuffer._endOfStreamReached = true;
        this.__streamSearchBuffer = this.__scratchBuffer;
        try {
            this._search();
        }
        catch (IOException ex) {}
        patternMatcherInput.setCurrentOffset(this.__lastMatchedBufferOffset);
        if (this.__lastMatchResult == null) {
            return false;
        }
        patternMatcherInput.setMatchOffsets(this.__lastMatchResult.beginOffset(0), this.__lastMatchResult.endOffset(0));
        return true;
    }
    
    public boolean contains(final AwkStreamInput _streamSearchBuffer, final Pattern pattern) throws IOException {
        this.__awkPattern = (AwkPattern)pattern;
        if (this.__awkPattern._hasBeginAnchor) {
            if (_streamSearchBuffer._bufferOffset != 0) {
                this.__lastMatchResult = null;
                return false;
            }
            if (_streamSearchBuffer.read() && !this.__awkPattern._fastMap[_streamSearchBuffer._buffer[0]]) {
                this.__lastMatchResult = null;
                return false;
            }
        }
        this.__lastMatchedBufferOffset = _streamSearchBuffer._currentOffset;
        this.__streamSearchBuffer = _streamSearchBuffer;
        this.__beginOffset = 0;
        this._search();
        _streamSearchBuffer._currentOffset = this.__lastMatchedBufferOffset;
        if (this.__lastMatchResult != null) {
            this.__lastMatchResult._incrementMatchBeginOffset(_streamSearchBuffer._bufferOffset);
            return true;
        }
        return false;
    }
    
    private int __streamMatchPrefix() throws IOException {
        int n = 1;
        int n2 = -1;
        int i;
        int n3 = i = this.__offsets[0];
        int n4 = this.__streamSearchBuffer._bufferSize + this.__beginOffset;
        while (i < n4) {
            final char c = this.__streamSearchBuffer._buffer[i++];
            if (n >= this.__awkPattern._numStates) {
                break;
            }
            final int n5 = n;
            final int[] getStateArray = this.__awkPattern._getStateArray(n);
            n = getStateArray[c];
            if (n == 0) {
                this.__awkPattern._createNewState(n5, c, getStateArray);
                n = getStateArray[c];
            }
            if (n == -1) {
                break;
            }
            if (this.__awkPattern._endStates.get(n)) {
                n2 = i;
            }
            if (i != n4) {
                continue;
            }
            i = this.__streamSearchBuffer._reallocate(n3) + this.__beginOffset;
            n4 = this.__streamSearchBuffer._bufferSize + this.__beginOffset;
            if (i == n4) {
                continue;
            }
            if (n2 != -1) {
                n2 -= n3;
            }
            n3 = 0;
        }
        this.__offsets[0] = n3;
        this.__offsets[1] = n2 - 1;
        if (n2 == -1 && this.__awkPattern._matchesNullString) {
            return 0;
        }
        if (this.__awkPattern._hasEndAnchor && (!this.__streamSearchBuffer._endOfStreamReached || n2 < this.__streamSearchBuffer._bufferSize + this.__beginOffset)) {
            return -1;
        }
        return n2 - n3;
    }
    
    void _search() throws IOException {
        this.__lastMatchResult = null;
        while (true) {
            if (this.__lastMatchedBufferOffset >= this.__streamSearchBuffer._bufferSize + this.__beginOffset) {
                if (this.__streamSearchBuffer._endOfStreamReached) {
                    this.__streamSearchBuffer = null;
                    return;
                }
                if (!this.__streamSearchBuffer.read()) {
                    return;
                }
                this.__lastMatchedBufferOffset = 0;
            }
            int i;
            for (i = this.__lastMatchedBufferOffset; i < this.__streamSearchBuffer._bufferSize + this.__beginOffset; i = this.__offsets[0] + 1) {
                this.__offsets[0] = i;
                final int _streamMatchPrefix;
                if (this.__awkPattern._fastMap[this.__streamSearchBuffer._buffer[i]] && (_streamMatchPrefix = this.__streamMatchPrefix()) > -1) {
                    this.__lastMatchResult = new AwkMatchResult(new String(this.__streamSearchBuffer._buffer, this.__offsets[0], _streamMatchPrefix), this.__offsets[0]);
                    this.__lastMatchedBufferOffset = ((_streamMatchPrefix > 0) ? (this.__offsets[1] + 1) : (this.__offsets[0] + 1));
                    return;
                }
                if (this.__awkPattern._matchesNullString) {
                    this.__lastMatchResult = new AwkMatchResult(new String(), i);
                    this.__lastMatchedBufferOffset = i + 1;
                    return;
                }
            }
            this.__lastMatchedBufferOffset = i;
        }
    }
    
    public MatchResult getMatch() {
        return this.__lastMatchResult;
    }
}
