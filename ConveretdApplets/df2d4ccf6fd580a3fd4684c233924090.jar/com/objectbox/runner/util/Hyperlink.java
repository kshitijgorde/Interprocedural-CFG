// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.util;

import java.net.URL;

public class Hyperlink
{
    public URL url;
    public int level;
    public boolean local;
    
    public Hyperlink() {
        this.url = null;
        this.level = 0;
        this.local = true;
    }
    
    public String toString() {
        return this.url.toString();
    }
}
