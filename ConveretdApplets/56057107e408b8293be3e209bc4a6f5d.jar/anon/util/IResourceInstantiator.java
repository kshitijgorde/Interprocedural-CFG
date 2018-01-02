// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.io.InputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.File;

public interface IResourceInstantiator
{
    Object getInstance(final File p0, final File p1) throws Exception;
    
    Object getInstance(final ZipEntry p0, final ZipFile p1) throws Exception;
    
    Object getInstance(final InputStream p0, final String p1) throws Exception;
    
    public static class ResourceInstantiationException extends Exception
    {
        private static final long serialVersionUID = 1L;
    }
}
