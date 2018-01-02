// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public class HSBReference extends ColorReference
{
    protected int hue;
    protected boolean preserveGrey;
    
    public HSBReference(final int hue, final int sat, final int bri, final int ref) {
        this.hue = hue;
        this.sat = sat;
        this.bri = bri;
        this.ref = ref;
        this.preserveGrey = true;
    }
    
    public int getHue() {
        return this.hue;
    }
    
    public void setHue(final int hue) {
        this.hue = hue;
    }
    
    public void load(final DataInputStream dataInputStream) throws IOException {
        try {
            this.hue = dataInputStream.readInt();
            this.sat = dataInputStream.readInt();
            this.bri = dataInputStream.readInt();
            this.ref = dataInputStream.readInt();
            this.preserveGrey = dataInputStream.readBoolean();
        }
        catch (Exception ex) {
            throw new IOException("HSBReference.load() : " + ex.getMessage());
        }
    }
    
    public void save(final DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.hue);
        dataOutputStream.writeInt(this.sat);
        dataOutputStream.writeInt(this.bri);
        dataOutputStream.writeInt(this.ref);
        dataOutputStream.writeBoolean(this.preserveGrey);
    }
    
    public boolean isPreserveGrey() {
        return this.preserveGrey;
    }
    
    public void setPreserveGrey(final boolean preserveGrey) {
        this.preserveGrey = preserveGrey;
    }
}
