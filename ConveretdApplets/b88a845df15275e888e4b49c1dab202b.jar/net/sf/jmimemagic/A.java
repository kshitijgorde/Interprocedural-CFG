// 
// Decompiled by Procyon v0.5.30
// 

package net.sf.jmimemagic;

import java.io.File;
import java.util.Map;

public interface A
{
    String getName();
    
    String getDisplayName();
    
    String getVersion();
    
    String[] getHandledTypes();
    
    String[] getHandledExtensions();
    
    String[] process(final byte[] p0, final int p1, final int p2, final long p3, final char p4, final String p5, final Map p6);
    
    String[] process(final File p0, final int p1, final int p2, final long p3, final char p4, final String p5, final Map p6);
}
