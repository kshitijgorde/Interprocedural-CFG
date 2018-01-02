// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.filter.msg;

import ji.res.s;
import ji.util.t;
import java.util.Hashtable;
import java.io.StringReader;
import ji.util.d;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import daeja4.cbf.nq;
import java.io.PipedInputStream;
import java.io.InputStream;
import ji.io.a8;
import ji.io.q;
import java.io.File;
import ji.io.ac;
import ji.document.gm;
import daeja4.cbf.ne;
import java.io.IOException;
import ji.io.h;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import daeja4.cbf.ni;

class zf implements Runnable
{
    private final /* synthetic */ nd a;
    private final /* synthetic */ ni b;
    private final /* synthetic */ PipedOutputStream c;
    private final /* synthetic */ boolean[] d;
    
    zf(final nd a, final ni b, final PipedOutputStream c, final boolean[] d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public void run() {
        try {
            this.a.a(this.b, this.c);
            this.c.flush();
            this.c.close();
            this.d[0] = true;
        }
        catch (Exception ex2) {}
        finally {
            if (this.c != null) {
                try {
                    this.c.close();
                }
                catch (IOException ex) {
                    h.a(this.a.i, ex);
                }
            }
        }
    }
}
