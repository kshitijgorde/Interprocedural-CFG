// 
// Decompiled by Procyon v0.5.30
// 

package jaggl;

import java.awt.Canvas;
import java.util.Hashtable;

public class OpenGL
{
    private Hashtable a;
    private static Hashtable b;
    long peer;
    private Thread c;
    
    public static final native void glGetIntegerv(final int p0, final int[] p1, final int p2);
    
    public static final native int glGenLists(final int p0);
    
    public static final native void glTexSubImage2Df(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final float[] p8, final int p9);
    
    public static final native void glGenTextures(final int p0, final int[] p1, final int p2);
    
    public static final native void glBindTexture(final int p0, final int p1);
    
    private final native boolean attachPeer();
    
    public static final native void glColorPointer(final int p0, final int p1, final int p2, final long p3);
    
    public static final native void glGetTexImageub(final int p0, final int p1, final int p2, final int p3, final byte[] p4, final int p5);
    
    public final native void releasePbuffer(final long p0);
    
    public static final native void glPolygonMode(final int p0, final int p1);
    
    public static final native void glProgramRawARB(final int p0, final int p1, final byte[] p2);
    
    public static final native void glDeleteLists(final int p0, final int p1);
    
    public final native void setPbuffer(final long p0);
    
    public static final native void glMultiTexCoord2f(final int p0, final float p1, final float p2);
    
    public static final native void glRasterPos2i(final int p0, final int p1);
    
    public static final native void glDrawBuffersARB(final int p0, final int[] p1, final int p2);
    
    public static final native void glCompileShaderARB(final long p0);
    
    public static final native void glTexEnvfv(final int p0, final int p1, final float[] p2, final int p3);
    
    public static final native void glDeleteRenderbuffersEXT(final int p0, final int[] p1, final int p2);
    
    public static final native void glGetProgramivARB(final int p0, final int p1, final int[] p2, final int p3);
    
    public static final native void glShadeModel(final int p0);
    
    public static final native void glHint(final int p0, final int p1);
    
    public static final native void glBlendFunc(final int p0, final int p1);
    
    public static final native void glUniform1iARB(final int p0, final int p1);
    
    public static final native void glLoadMatrixf(final float[] p0, final int p1);
    
    public static final native int glGetError();
    
    public static final native void glColor4f(final float p0, final float p1, final float p2, final float p3);
    
    public static final native void glBufferSubDataARBa(final int p0, final int p1, final int p2, final long p3);
    
    public static final native void glNormal3f(final float p0, final float p1, final float p2);
    
    public static final native void glNormalPointer(final int p0, final int p1, final long p2);
    
    public static final native int glGetUniformLocationARB(final long p0, final String p1);
    
    public static final native void glEndList();
    
    public static final native void glLightf(final int p0, final int p1, final float p2);
    
    public final native boolean setSurface(final long p0);
    
    public static final native void glActiveTexture(final int p0);
    
    public static final native void glCopyTexSubImage2D(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    public static final native String glGetString(final int p0);
    
    public static final native long glCreateProgramObjectARB();
    
    public static final native int glGenProgramARB();
    
    public static final native void glStencilOp(final int p0, final int p1, final int p2);
    
    public static final native void glProgramLocalParameter4fARB(final int p0, final int p1, final float p2, final float p3, final float p4, final float p5);
    
    public static final native void glTexSubImage2Di(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int[] p8, final int p9);
    
    public static final native void glUseProgramObjectARB(final long p0);
    
    public static final native void glGenFramebuffersEXT(final int p0, final int[] p1, final int p2);
    
    public static final native void glAttachObjectARB(final long p0, final long p1);
    
    public static final native void glTexParameteri(final int p0, final int p1, final int p2);
    
    public static final native void glTexImage2Df(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final float[] p8, final int p9);
    
    static final native boolean glUnmapBufferARB(final int p0);
    
    public static final native void glFogfv(final int p0, final float[] p1, final int p2);
    
    public final native void setSwapInterval(final int p0);
    
    public static final native void glDeleteBuffersARB(final int p0, final int[] p1, final int p2);
    
    public static final native void glShaderSourceRawARB(final long p0, final byte[] p1);
    
    public static final native void glDepthFunc(final int p0);
    
    public static final native void glDeleteObjectARB(final long p0);
    
    public static final native void glGetObjectParameterivARB(final long p0, final int p1, final int[] p2, final int p3);
    
    public final synchronized boolean a() {
        try {
            if (this.c != Thread.currentThread()) {
                return false;
            }
            this.detachPeer();
            OpenGL.b.remove(this.c);
            this.c = null;
            return true;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static final native void glDeleteProgramARB(final int p0);
    
    public static final native void glTexCoord3i(final int p0, final int p1, final int p2);
    
    public static final native void glClearDepth(final float p0);
    
    public static final native void glLinkProgramARB(final long p0);
    
    public static final native void glGetInfoLogARB(final long p0, final int p1, final int[] p2, final int p3, final byte[] p4, final int p5);
    
    public static final native void glScalef(final float p0, final float p1, final float p2);
    
    public static final native void glLightModelfv(final int p0, final float[] p1, final int p2);
    
    public static final native void glDisable(final int p0);
    
    public static final native void glFlush();
    
    public static final native void glMatrixMode(final int p0);
    
    public static final native void glDrawPixelsi(final int p0, final int p1, final int p2, final int p3, final int[] p4, final int p5);
    
    public static final native void glTexGeni(final int p0, final int p1, final int p2);
    
    public static final native void glDrawBuffer(final int p0);
    
    public static final native void glUniformMatrix3fvARB(final int p0, final int p1, final boolean p2, final float[] p3, final int p4);
    
    public static final native void glMultMatrixf(final float[] p0, final int p1);
    
    public static final native void glBindFramebufferEXT(final int p0, final int p1);
    
    public static final native void glDrawArrays(final int p0, final int p1, final int p2);
    
    public static final native void glTexGenfv(final int p0, final int p1, final float[] p2, final int p3);
    
    public static final native long glCreateShaderObjectARB(final int p0);
    
    public static final native void glPushMatrix();
    
    public final native void releaseSurface(final Canvas p0, final long p1);
    
    public static final native void glCullFace(final int p0);
    
    public static final native void glGenerateMipmapEXT(final int p0);
    
    public static final native void glColor4ub(final byte p0, final byte p1, final byte p2, final byte p3);
    
    public static final native void glUniformMatrix4fvARB(final int p0, final int p1, final boolean p2, final float[] p3, final int p4);
    
    public static final native void glPopAttrib();
    
    public static final native void glPushAttrib(final int p0);
    
    public static final native void glPixelTransferf(final int p0, final float p1);
    
    public static final native void glBindRenderbufferEXT(final int p0, final int p1);
    
    public static final native void glFramebufferRenderbufferEXT(final int p0, final int p1, final int p2, final int p3);
    
    public static final native void glBufferDataARBub(final int p0, final int p1, final byte[] p2, final int p3, final int p4);
    
    public static final native void glTexImage1Dub(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final byte[] p7, final int p8);
    
    public final native long prepareSurface(final Canvas p0);
    
    public static final native void glVertex3f(final float p0, final float p1, final float p2);
    
    public final native void surfaceResized(final long p0);
    
    public static final native void glTexEnvf(final int p0, final int p1, final float p2);
    
    public static final native void glOrtho(final double p0, final double p1, final double p2, final double p3, final double p4, final double p5);
    
    public static final native void glFogf(final int p0, final float p1);
    
    public static final native void glReadPixelsi(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int[] p6, final int p7);
    
    public final native long init(final Canvas p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    public static final native void glScissor(final int p0, final int p1, final int p2, final int p3);
    
    public static final native void glFinish();
    
    public static final native void glTexImage2Di(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int[] p8, final int p9);
    
    public static final native void glGenRenderbuffersEXT(final int p0, final int[] p1, final int p2);
    
    public static final native void glTexCoord2f(final float p0, final float p1);
    
    public static final native void glCallList(final int p0);
    
    public static final native void glColor3ub(final byte p0, final byte p1, final byte p2);
    
    public static final native void glDrawElements(final int p0, final int p1, final int p2, final long p3);
    
    public static final native void glPixelZoom(final float p0, final float p1);
    
    public static final native void glMultiTexCoord3i(final int p0, final int p1, final int p2, final int p3);
    
    public static final native void glGenBuffersARB(final int p0, final int[] p1, final int p2);
    
    public static final native void glBufferDataARBa(final int p0, final int p1, final long p2, final int p3);
    
    public static final native void glDeleteFramebuffersEXT(final int p0, final int[] p1, final int p2);
    
    public static final native void glFogi(final int p0, final int p1);
    
    public static final native void glClientActiveTexture(final int p0);
    
    public final native boolean arePbuffersAvailable();
    
    public static final native void glTexCoordPointer(final int p0, final int p1, final int p2, final long p3);
    
    public static final native void glVertexPointer(final int p0, final int p1, final int p2, final long p3);
    
    public static final native void glBindBufferARB(final int p0, final int p1);
    
    public static final native void glProgramLocalParameter4fvARB(final int p0, final int p1, final float[] p2, final int p3);
    
    public static final native void glGetFloatv(final int p0, final float[] p1, final int p2);
    
    public static final native void glCopyPixels(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native void glDetachObjectARB(final long p0, final long p1);
    
    public static final native void glTexParameterf(final int p0, final int p1, final float p2);
    
    public final boolean a(final String s) {
        try {
            if (this.a == null) {
                this.a = new Hashtable();
                final String glGetString = glGetString(7939);
                int n = 0;
                while (true) {
                    final int index = glGetString.indexOf(32, n);
                    if (~index == 0x0) {
                        break;
                    }
                    final String trim = glGetString.substring(n, index).trim();
                    n = 1 + index;
                    if (trim.length() == 0) {
                        continue;
                    }
                    this.a.put(trim, trim);
                }
                final String trim2 = glGetString.substring(n).trim();
                if (~trim2.length() != -1) {
                    this.a.put(trim2, trim2);
                }
            }
            return this.a.containsKey(s);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static final native void glRenderbufferStorageEXT(final int p0, final int p1, final int p2, final int p3);
    
    public static final native void glAlphaFunc(final int p0, final float p1);
    
    public static final native void glProgramStringARB(final int p0, final int p1, final String p2);
    
    public static final native void glRenderbufferStorageMultisampleEXT(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native void glUniform4fARB(final int p0, final float p1, final float p2, final float p3, final float p4);
    
    public static final native void glNewList(final int p0, final int p1);
    
    public static final native void glEnable(final int p0);
    
    public static final native void glStencilFunc(final int p0, final int p1, final int p2);
    
    public static final native void glUniform1fARB(final int p0, final float p1);
    
    private final native void detachPeer();
    
    public final native long createPbuffer(final int p0, final int p1);
    
    public static final native void glUniformMatrix2fvARB(final int p0, final int p1, final boolean p2, final float[] p3, final int p4);
    
    public static final native void glTexImage3Dub(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final byte[] p9, final int p10);
    
    public static final native void glColorMask(final boolean p0, final boolean p1, final boolean p2, final boolean p3);
    
    public final native void release();
    
    public static final native void glTexCoord3f(final float p0, final float p1, final float p2);
    
    public static final native void glDrawPixelsub(final int p0, final int p1, final int p2, final int p3, final byte[] p4, final int p5);
    
    public static final native void glBlitFramebufferEXT(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9);
    
    public static final native void glReadPixelsub(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final byte[] p6, final int p7);
    
    public static final native void glRotatef(final float p0, final float p1, final float p2, final float p3);
    
    public static final native void glClearColor(final float p0, final float p1, final float p2, final float p3);
    
    public static final native void glClear(final int p0);
    
    public static final native void glTexEnvi(final int p0, final int p1, final int p2);
    
    public static final native void glDepthMask(final boolean p0);
    
    public static final native void glDeleteTextures(final int p0, final int[] p1, final int p2);
    
    public static final native void glEnd();
    
    public static final native void glMaterialfv(final int p0, final int p1, final float[] p2, final int p3);
    
    public static final native void glMultiTexCoord2i(final int p0, final int p1, final int p2);
    
    public static final native void glPointSize(final float p0);
    
    public static final native void glPopMatrix();
    
    public final synchronized boolean b() {
        try {
            final Thread currentThread = Thread.currentThread();
            if (this.attachPeer()) {
                final OpenGL openGL = OpenGL.b.put(currentThread, this);
                if (openGL != null) {
                    openGL.c = null;
                }
                this.c = currentThread;
                return true;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static final native void glBindProgramARB(final int p0, final int p1);
    
    public static final native void glBegin(final int p0);
    
    public static final native void glUniform2fARB(final int p0, final float p1, final float p2);
    
    public static final native void glEnableClientState(final int p0);
    
    public static final native void glCopyTexSubImage3D(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    public static final native void glFrustum(final double p0, final double p1, final double p2, final double p3, final double p4, final double p5);
    
    static final native long glMapBufferARB(final int p0, final int p1);
    
    public static final native void glVertex2i(final int p0, final int p1);
    
    public static final native void glTexImage2Dub(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final byte[] p8, final int p9);
    
    public static final native void glLoadIdentity();
    
    public static final native void glPixelStorei(final int p0, final int p1);
    
    public static final native int glCheckFramebufferStatusEXT(final int p0);
    
    public static final native void glCopyTexImage2D(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    public static final native void glVertex2f(final float p0, final float p1);
    
    public static final native void glBufferSubDataARBub(final int p0, final int p1, final int p2, final byte[] p3, final int p4);
    
    public static final native void glViewport(final int p0, final int p1, final int p2, final int p3);
    
    public static final native void glFramebufferTexture3DEXT(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native void glColor3f(final float p0, final float p1, final float p2);
    
    public static final native void glUniform3fARB(final int p0, final float p1, final float p2, final float p3);
    
    public static final native void glTexSubImage2Dub(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final byte[] p8, final int p9);
    
    public static final native void glGetTexImagei(final int p0, final int p1, final int p2, final int p3, final int[] p4, final int p5);
    
    public final native void swapBuffers();
    
    public static final native void glFramebufferTexture2DEXT(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native void glLineWidth(final float p0);
    
    public static final native void glShaderSourceARB(final long p0, final String p1);
    
    public static final native void glTranslatef(final float p0, final float p1, final float p2);
    
    public static final native void glDisableClientState(final int p0);
    
    public static final native void glColorMaterial(final int p0, final int p1);
    
    public static final native void glTexCoord2i(final int p0, final int p1);
    
    public static final native void glReadBuffer(final int p0);
    
    public static final native void glLightfv(final int p0, final int p1, final float[] p2, final int p3);
    
    static {
        OpenGL.b = new Hashtable();
    }
}
