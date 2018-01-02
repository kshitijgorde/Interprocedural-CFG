// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

import java.io.InputStream;

public abstract class JechtIO
{
    public static class File extends JechtIO
    {
        public InputStream ptr;
        public IoFileRead read;
        
        public File(final InputStream is, final IoFileRead read) {
            this.ptr = is;
            this.read = read;
        }
    }
    
    public static class Str extends JechtIO
    {
        public Pointer ptr;
        public int beg;
        public int end;
        public IoStrRead read;
    }
}
