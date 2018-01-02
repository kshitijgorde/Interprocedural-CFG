// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.exception.InternalException;
import org.joni.exception.ValueException;
import org.joni.exception.SyntaxException;
import org.jcodings.Encoding;
import org.joni.exception.ErrorMessages;
import org.jcodings.IntHolder;

abstract class ScannerSupport extends IntHolder implements ErrorMessages
{
    protected final Encoding enc;
    protected final byte[] bytes;
    protected int p;
    protected int stop;
    private int lastFetched;
    protected int c;
    private final int begin;
    private final int end;
    protected int _p;
    private final int INT_SIGN_BIT = Integer.MIN_VALUE;
    
    protected ScannerSupport(final Encoding enc, final byte[] bytes, final int p, final int end) {
        this.enc = enc;
        this.bytes = bytes;
        this.begin = p;
        this.end = end;
        this.reset();
    }
    
    protected int getBegin() {
        return this.begin;
    }
    
    protected int getEnd() {
        return this.end;
    }
    
    protected final int scanUnsignedNumber() {
        int num = 0;
        while (this.left()) {
            this.fetch();
            if (!this.enc.isDigit(this.c)) {
                this.unfetch();
                break;
            }
            final int onum = num;
            num = num * 10 + Encoding.digitVal(this.c);
            if (((onum ^ num) & Integer.MIN_VALUE) != 0x0) {
                return -1;
            }
        }
        return num;
    }
    
    protected final int scanUnsignedHexadecimalNumber(int maxLength) {
        int num = 0;
        while (this.left() && maxLength-- != 0) {
            this.fetch();
            if (!this.enc.isXDigit(this.c)) {
                this.unfetch();
                break;
            }
            final int onum = num;
            final int val = this.enc.xdigitVal(this.c);
            num = (num << 4) + val;
            if (((onum ^ num) & Integer.MIN_VALUE) != 0x0) {
                return -1;
            }
        }
        return num;
    }
    
    protected final int scanUnsignedOctalNumber(int maxLength) {
        int num = 0;
        while (this.left() && maxLength-- != 0) {
            this.fetch();
            if (!this.enc.isDigit(this.c) || this.c >= 56) {
                this.unfetch();
                break;
            }
            final int onum = num;
            final int val = Encoding.odigitVal(this.c);
            num = (num << 3) + val;
            if (((onum ^ num) & Integer.MIN_VALUE) != 0x0) {
                return -1;
            }
        }
        return num;
    }
    
    protected final void reset() {
        this.p = this.begin;
        this.stop = this.end;
    }
    
    protected final void mark() {
        this._p = this.p;
    }
    
    protected final void restore() {
        this.p = this._p;
    }
    
    protected final void inc() {
        this.lastFetched = this.p;
        this.p += this.enc.length(this.bytes, this.p, this.stop);
    }
    
    protected final void fetch() {
        this.c = this.enc.mbcToCode(this.bytes, this.p, this.stop);
        this.lastFetched = this.p;
        this.p += this.enc.length(this.bytes, this.p, this.stop);
    }
    
    protected int fetchTo() {
        final int to = this.enc.mbcToCode(this.bytes, this.p, this.stop);
        this.lastFetched = this.p;
        this.p += this.enc.length(this.bytes, this.p, this.stop);
        return to;
    }
    
    protected final void unfetch() {
        this.p = this.lastFetched;
    }
    
    protected final int peek() {
        return (this.p < this.stop) ? this.enc.mbcToCode(this.bytes, this.p, this.stop) : 0;
    }
    
    protected final boolean peekIs(final int c) {
        return this.peek() == c;
    }
    
    protected final boolean left() {
        return this.p < this.stop;
    }
    
    protected void newSyntaxException(final String message) {
        throw new SyntaxException(message);
    }
    
    protected void newValueException(final String message) {
        throw new ValueException(message);
    }
    
    protected void newValueException(final String message, final String str) {
        throw new ValueException(message, str);
    }
    
    protected void newValueException(final String message, final int p, final int end) {
        throw new ValueException(message, new String(this.bytes, p, end - p));
    }
    
    protected void newInternalException(final String message) {
        throw new InternalException(message);
    }
}
