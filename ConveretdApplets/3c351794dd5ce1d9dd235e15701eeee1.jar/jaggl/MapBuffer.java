// 
// Decompiled by Procyon v0.5.30
// 

package jaggl;

import jaclib.memory.NativeBuffer;

public class MapBuffer extends NativeBuffer
{
    private int c;
    
    @Override
    public final void a(final byte[] array, final int n, final int n2, final int n3) {
        try {
            if (~this.c == -1) {
                throw new RuntimeException();
            }
            super.a(array, n, n2, n3);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final boolean a() {
        try {
            return ~this.c != -1;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final boolean b() {
        try {
            boolean glUnmapBufferARB = true;
            if (this.c != 0) {
                glUnmapBufferARB = OpenGL.glUnmapBufferARB(this.c);
                this.a(0L, 0);
                this.c = 0;
            }
            return glUnmapBufferARB;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final boolean a(final int c, final int n, final int n2) {
        try {
            if (this.c != 0) {
                return false;
            }
            final long glMapBufferARB = OpenGL.glMapBufferARB(c, n2);
            if (glMapBufferARB == 0L) {
                return false;
            }
            this.a(glMapBufferARB, n);
            this.c = c;
            return true;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
