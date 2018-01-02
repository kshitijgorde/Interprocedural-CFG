// 
// Decompiled by Procyon v0.5.30
// 

package net.Slingshot.ClientAPI;

class FidValue
{
    public Short fid;
    public short offset;
    public String value;
    public boolean partial;
    public boolean missUpdate;
    
    FidValue(final Short fid, final short offset, final String value) {
        this.partial = false;
        this.missUpdate = false;
        this.fid = fid;
        this.offset = offset;
        this.value = value;
    }
    
    FidValue(final Short fid, final short offset, final String value, final boolean partial, final boolean missUpdate) {
        this.partial = false;
        this.missUpdate = false;
        this.fid = fid;
        this.offset = offset;
        this.value = value;
        this.partial = partial;
        this.missUpdate = missUpdate;
    }
}
