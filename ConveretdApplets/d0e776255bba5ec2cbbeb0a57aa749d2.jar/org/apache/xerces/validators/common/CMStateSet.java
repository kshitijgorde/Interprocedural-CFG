// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

class CMStateSet
{
    int fBitCount;
    int fByteCount;
    int fBits1;
    int fBits2;
    byte[] fByteArray;
    
    CMStateSet(final int fBitCount) throws CMException {
        this.fBitCount = fBitCount;
        if (this.fBitCount < 0) {
            throw new CMException(7);
        }
        if (this.fBitCount > 64) {
            this.fByteCount = this.fBitCount / 8;
            if (this.fBitCount % 8 != 0) {
                ++this.fByteCount;
            }
            this.fByteArray = new byte[this.fByteCount];
        }
        this.zeroBits();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append("{");
            for (int i = 0; i < this.fBitCount; ++i) {
                if (this.getBit(i)) {
                    sb.append(" " + i);
                }
            }
            sb.append(" }");
        }
        catch (CMException ex) {}
        return sb.toString();
    }
    
    final void intersection(final CMStateSet set) {
        if (this.fBitCount < 65) {
            this.fBits1 &= set.fBits1;
            this.fBits2 &= set.fBits2;
        }
        else {
            for (int i = this.fByteCount - 1; i >= 0; --i) {
                final byte[] fByteArray = this.fByteArray;
                final int n = i;
                fByteArray[n] &= set.fByteArray[i];
            }
        }
    }
    
    final boolean getBit(final int n) throws CMException {
        if (n >= this.fBitCount) {
            throw new CMException(7);
        }
        if (this.fBitCount >= 65) {
            return (this.fByteArray[n >> 3] & (byte)(1 << n % 8)) != 0x0;
        }
        final int n2 = 1 << n % 32;
        if (n < 32) {
            return (this.fBits1 & n2) != 0x0;
        }
        return (this.fBits2 & n2) != 0x0;
    }
    
    final boolean isEmpty() {
        if (this.fBitCount < 65) {
            return this.fBits1 == 0 && this.fBits2 == 0;
        }
        for (int i = this.fByteCount - 1; i >= 0; --i) {
            if (this.fByteArray[i] != 0) {
                return false;
            }
        }
        return true;
    }
    
    final boolean isSameSet(final CMStateSet set) {
        if (this.fBitCount != set.fBitCount) {
            return false;
        }
        if (this.fBitCount < 65) {
            return this.fBits1 == set.fBits1 && this.fBits2 == set.fBits2;
        }
        for (int i = this.fByteCount - 1; i >= 0; --i) {
            if (this.fByteArray[i] != set.fByteArray[i]) {
                return false;
            }
        }
        return true;
    }
    
    final void union(final CMStateSet set) {
        if (this.fBitCount < 65) {
            this.fBits1 |= set.fBits1;
            this.fBits2 |= set.fBits2;
        }
        else {
            for (int i = this.fByteCount - 1; i >= 0; --i) {
                final byte[] fByteArray = this.fByteArray;
                final int n = i;
                fByteArray[n] |= set.fByteArray[i];
            }
        }
    }
    
    final void setBit(final int n) throws CMException {
        if (n >= this.fBitCount) {
            throw new CMException(7);
        }
        if (this.fBitCount < 65) {
            final int n2 = 1 << n % 32;
            if (n < 32) {
                this.fBits1 &= ~n2;
                this.fBits1 |= n2;
            }
            else {
                this.fBits2 &= ~n2;
                this.fBits2 |= n2;
            }
        }
        else {
            final byte b = (byte)(1 << n % 8);
            final int n3 = n >> 3;
            final byte[] fByteArray = this.fByteArray;
            final int n4 = n3;
            fByteArray[n4] &= (byte)~b;
            final byte[] fByteArray2 = this.fByteArray;
            final int n5 = n3;
            fByteArray2[n5] |= b;
        }
    }
    
    final void setTo(final CMStateSet set) throws CMException {
        if (this.fBitCount != set.fBitCount) {
            throw new CMException(7);
        }
        if (this.fBitCount < 65) {
            this.fBits1 = set.fBits1;
            this.fBits2 = set.fBits2;
        }
        else {
            for (int i = this.fByteCount - 1; i >= 0; --i) {
                this.fByteArray[i] = set.fByteArray[i];
            }
        }
    }
    
    final void zeroBits() {
        if (this.fBitCount < 65) {
            this.fBits1 = 0;
            this.fBits2 = 0;
        }
        else {
            for (int i = this.fByteCount - 1; i >= 0; --i) {
                this.fByteArray[i] = 0;
            }
        }
    }
    
    public boolean equals(final Object o) {
        return o instanceof CMStateSet && this.isSameSet((CMStateSet)o);
    }
    
    public int hashCode() {
        if (this.fBitCount < 65) {
            return this.fBits1 + this.fBits2 * 31;
        }
        int n = 0;
        for (int i = this.fByteCount - 1; i >= 0; --i) {
            n = this.fByteArray[i] + n * 31;
        }
        return n;
    }
}
