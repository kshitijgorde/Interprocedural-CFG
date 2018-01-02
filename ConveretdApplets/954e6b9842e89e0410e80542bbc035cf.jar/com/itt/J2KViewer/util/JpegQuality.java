// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

public class JpegQuality
{
    public float value;
    public String description;
    
    public JpegQuality(final float value, final String description) {
        this.value = value;
        this.description = description;
    }
    
    public String toString() {
        return this.description + " (" + this.value + ")";
    }
}
