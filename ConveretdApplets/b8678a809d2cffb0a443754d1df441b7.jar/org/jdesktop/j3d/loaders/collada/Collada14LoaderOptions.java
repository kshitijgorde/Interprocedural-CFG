// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada;

public class Collada14LoaderOptions
{
    private static Collada14LoaderOptions options;
    private boolean colored;
    
    static {
        Collada14LoaderOptions.options = null;
    }
    
    private Collada14LoaderOptions() {
        this.colored = false;
    }
    
    public static Collada14LoaderOptions getInstance() {
        if (Collada14LoaderOptions.options == null) {
            Collada14LoaderOptions.options = new Collada14LoaderOptions();
        }
        return Collada14LoaderOptions.options;
    }
    
    public boolean isColored() {
        return this.colored;
    }
    
    public void setColored(final boolean coloring) {
        this.colored = coloring;
    }
}
