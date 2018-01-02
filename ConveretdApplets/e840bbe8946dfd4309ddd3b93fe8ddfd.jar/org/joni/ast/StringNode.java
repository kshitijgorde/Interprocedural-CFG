// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.ast;

import org.jcodings.Encoding;
import org.joni.constants.StringType;

public final class StringNode extends Node implements StringType
{
    private static final int NODE_STR_MARGIN = 16;
    private static final int NODE_STR_BUF_SIZE = 24;
    public byte[] bytes;
    public int p;
    public int end;
    int flag;
    
    public StringNode() {
        this.bytes = new byte[24];
    }
    
    public StringNode(final byte[] bytes, final int p, final int end) {
        this.bytes = bytes;
        this.p = p;
        this.end = end;
        this.setShared();
    }
    
    public StringNode(final byte c) {
        this();
        this.bytes[this.end++] = c;
    }
    
    public void ensure(final int ahead) {
        final int len = this.end - this.p + ahead;
        if (len >= this.bytes.length) {
            final byte[] tmp = new byte[len + 16];
            System.arraycopy(this.bytes, this.p, tmp, 0, this.end - this.p);
            this.bytes = tmp;
        }
    }
    
    private void modifyEnsure(final int ahead) {
        final int len = this.end - this.p + ahead;
        if (this.isShared()) {
            final byte[] tmp = new byte[len + 16];
            System.arraycopy(this.bytes, this.p, tmp, 0, this.end - this.p);
            this.bytes = tmp;
            this.end -= this.p;
            this.p = 0;
            this.clearShared();
        }
        else if (len >= this.bytes.length) {
            final byte[] tmp = new byte[len + 16];
            System.arraycopy(this.bytes, this.p, tmp, 0, this.end - this.p);
            this.bytes = tmp;
        }
    }
    
    public int getType() {
        return 0;
    }
    
    public String getName() {
        return "String";
    }
    
    public String toString(final int level) {
        final StringBuilder value = new StringBuilder();
        value.append("\n  bytes: ");
        for (int i = this.p; i < this.end; ++i) {
            if ((this.bytes[i] & 0xFF) >= 32 && (this.bytes[i] & 0xFF) < 127) {
                value.append((char)this.bytes[i]);
            }
            else {
                value.append(String.format(" 0x%02x", this.bytes[i]));
            }
        }
        return value.toString();
    }
    
    public int length() {
        return this.end - this.p;
    }
    
    public int length(final Encoding enc) {
        return enc.strLength(this.bytes, this.p, this.end);
    }
    
    public StringNode splitLastChar(final Encoding enc) {
        StringNode n = null;
        if (this.end > this.p) {
            final int prev = enc.prevCharHead(this.bytes, this.p, this.end, this.end);
            if (prev != -1 && prev > this.p) {
                n = new StringNode(this.bytes, prev, this.end);
                if (this.isRaw()) {
                    n.setRaw();
                }
                this.end = prev;
            }
        }
        return n;
    }
    
    public boolean canBeSplit(final Encoding enc) {
        return this.end > this.p && enc.length(this.bytes, this.p, this.end) < this.end - this.p;
    }
    
    public void set(final byte[] bytes, final int p, final int end) {
        this.bytes = bytes;
        this.p = p;
        this.end = end;
        this.setShared();
    }
    
    public void cat(final byte[] cat, final int catP, final int catEnd) {
        final int len = catEnd - catP;
        this.modifyEnsure(len);
        System.arraycopy(cat, catP, this.bytes, this.end, len);
        this.end += len;
    }
    
    public void cat(final byte c) {
        this.modifyEnsure(1);
        this.bytes[this.end++] = c;
    }
    
    public void clear() {
        if (this.bytes.length > 24) {
            this.bytes = new byte[24];
        }
        this.flag = 0;
        final boolean b = false;
        this.end = (b ? 1 : 0);
        this.p = (b ? 1 : 0);
    }
    
    public void setRaw() {
        this.flag |= 0x1;
    }
    
    public void clearRaw() {
        this.flag &= 0xFFFFFFFE;
    }
    
    public boolean isRaw() {
        return (this.flag & 0x1) != 0x0;
    }
    
    public void setAmbig() {
        this.flag |= 0x2;
    }
    
    public void clearAmbig() {
        this.flag &= 0xFFFFFFFD;
    }
    
    public boolean isAmbig() {
        return (this.flag & 0x2) != 0x0;
    }
    
    public void setDontGetOptInfo() {
        this.flag |= 0x4;
    }
    
    public void clearDontGetOptInfo() {
        this.flag &= 0xFFFFFFFB;
    }
    
    public boolean isDontGetOptInfo() {
        return (this.flag & 0x4) != 0x0;
    }
    
    public void setShared() {
        this.flag |= 0x8;
    }
    
    public void clearShared() {
        this.flag &= 0xFFFFFFF7;
    }
    
    public boolean isShared() {
        return (this.flag & 0x8) != 0x0;
    }
}
