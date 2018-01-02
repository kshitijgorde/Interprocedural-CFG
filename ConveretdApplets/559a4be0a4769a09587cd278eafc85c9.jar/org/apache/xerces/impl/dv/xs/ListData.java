// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

final class ListData
{
    final Object[] data;
    private String canonical;
    
    public ListData(final Object[] data) {
        this.data = data;
    }
    
    public synchronized String toString() {
        if (this.canonical == null) {
            final int len = this.data.length;
            final StringBuffer buf = new StringBuffer();
            if (len > 0) {
                buf.append(this.data[0].toString());
            }
            for (int i = 1; i < len; ++i) {
                buf.append(' ');
                buf.append(this.data[i].toString());
            }
            this.canonical = buf.toString();
        }
        return this.canonical;
    }
    
    public int length() {
        return this.data.length;
    }
    
    public Object item(final int index) {
        return this.data[index];
    }
    
    public boolean equals(final Object obj) {
        if (!(obj instanceof ListData)) {
            return false;
        }
        final Object[] odata = ((ListData)obj).data;
        final int count = this.data.length;
        if (count != odata.length) {
            return false;
        }
        for (int i = 0; i < count; ++i) {
            if (!this.data[i].equals(odata[i])) {
                return false;
            }
        }
        return true;
    }
}
