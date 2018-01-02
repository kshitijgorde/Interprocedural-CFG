// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.File;
import java.net.URL;
import java.awt.Image;

public class NFImageCacheEntry
{
    public String filename;
    public Image im;
    public URL url;
    public File file;
    public Object ts;
    
    public NFImageCacheEntry() {
        this.filename = null;
        this.im = null;
        this.url = null;
        this.file = null;
        this.ts = null;
    }
}
