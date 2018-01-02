// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

public final class NameEntry
{
    static final int INIT_NAME_BACKREFS_ALLOC_NUM = 8;
    public final byte[] name;
    public final int nameP;
    public final int nameEnd;
    int backNum;
    int backRef1;
    int[] backRefs;
    
    public NameEntry(final byte[] bytes, final int p, final int end) {
        this.name = bytes;
        this.nameP = p;
        this.nameEnd = end;
    }
    
    public int[] getBackRefs() {
        switch (this.backNum) {
            case 0: {
                return new int[0];
            }
            case 1: {
                return new int[] { this.backRef1 };
            }
            default: {
                final int[] result = new int[this.backNum];
                System.arraycopy(this.backRefs, 0, result, 0, this.backNum);
                return result;
            }
        }
    }
    
    private void alloc() {
        this.backRefs = new int[8];
    }
    
    private void ensureSize() {
        if (this.backNum > this.backRefs.length) {
            final int[] tmp = new int[this.backRefs.length << 1];
            System.arraycopy(this.backRefs, 0, tmp, 0, this.backRefs.length);
            this.backRefs = tmp;
        }
    }
    
    public void addBackref(final int backRef) {
        switch (++this.backNum) {
            case 1: {
                this.backRef1 = backRef;
                break;
            }
            case 2: {
                this.alloc();
                this.backRefs[0] = this.backRef1;
                this.backRefs[1] = backRef;
                break;
            }
            default: {
                this.ensureSize();
                this.backRefs[this.backNum - 1] = backRef;
                break;
            }
        }
    }
    
    public String toString() {
        final StringBuilder buff = new StringBuilder(new String(this.name, this.nameP, this.nameEnd - this.nameP) + " ");
        if (this.backNum == 0) {
            buff.append("-");
        }
        else if (this.backNum == 1) {
            buff.append(this.backRef1);
        }
        else {
            for (int i = 0; i < this.backNum; ++i) {
                if (i > 0) {
                    buff.append(", ");
                }
                buff.append(this.backRefs[i]);
            }
        }
        return buff.toString();
    }
}
