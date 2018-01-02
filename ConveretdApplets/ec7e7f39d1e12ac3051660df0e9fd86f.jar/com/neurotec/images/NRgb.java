// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import com.sun.jna.Structure;

public final class NRgb
{
    private NRgbData data;
    
    public NRgb() {
        this.data = new NRgbData();
    }
    
    public NRgb(final int red, final int green, final int blue) {
        if (red < 0 || red > 255) {
            throw new IllegalArgumentException("red must be in range [0..255].");
        }
        if (green < 0 || green > 255) {
            throw new IllegalArgumentException("green must be in range [0..255].");
        }
        if (blue < 0 || blue > 255) {
            throw new IllegalArgumentException("blue must be in range [0..255].");
        }
        this.data = new NRgbData((byte)red, (byte)green, (byte)blue);
    }
    
    public int getRed() {
        return this.data.red & 0xFF;
    }
    
    public void setRed(final int red) {
        if (red < 0 || red > 255) {
            throw new IllegalArgumentException("red must be in range [0..255].");
        }
        this.data.red = (byte)red;
    }
    
    public int getGreen() {
        return this.data.green & 0xFF;
    }
    
    public void setGreen(final int green) {
        if (green < 0 || green > 255) {
            throw new IllegalArgumentException("green must be in range [0..255].");
        }
        this.data.green = (byte)green;
    }
    
    public int getBlue() {
        return this.data.blue & 0xFF;
    }
    
    public void setBlue(final int blue) {
        if (blue < 0 || blue > 255) {
            throw new IllegalArgumentException("blue must be in range [0..255].");
        }
        this.data.blue = (byte)blue;
    }
    
    public String toString() {
        return String.format("{{Red={%s}, Green={%s}, Blue={%s}}}", this.getRed(), this.getGreen(), this.getBlue());
    }
    
    public NRgbData getData() {
        return this.data;
    }
    
    protected class NRgbData extends Structure
    {
        public byte red;
        public byte green;
        public byte blue;
        final /* synthetic */ NRgb this$0;
        
        public NRgbData() {
        }
        
        public NRgbData(final byte red, final byte green, final byte blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
        
        protected final ByReference newByReference() {
            return new ByReference();
        }
        
        protected final ByValue newByValue() {
            return new ByValue();
        }
        
        public class ByReference extends NRgbData implements Structure.ByReference
        {
            public ByReference() {
                NRgbData.this.this$0.super();
            }
        }
        
        public class ByValue extends NRgbData implements Structure.ByValue
        {
            public ByValue() {
                NRgbData.this.this$0.super();
            }
        }
    }
}
