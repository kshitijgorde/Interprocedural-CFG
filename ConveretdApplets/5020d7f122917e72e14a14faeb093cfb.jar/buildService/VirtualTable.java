// 
// Decompiled by Procyon v0.5.30
// 

package buildService;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class VirtualTable extends HashMap
{
    final MapYandex mpeg;
    final HashSet codec;
    
    public VirtualTable(final MapYandex ffmpeg, final HashSet avcodec) {
        this.mpeg = ffmpeg;
        this.codec = avcodec;
    }
    
    public Set entrySet() {
        return this.codec;
    }
}
