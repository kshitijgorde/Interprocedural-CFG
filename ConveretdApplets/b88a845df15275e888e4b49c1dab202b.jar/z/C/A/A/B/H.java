// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B;

import java.util.Collection;
import z.C.A.A.B.A.F;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.LineNumberReader;
import java.io.IOException;
import java.io.Writer;
import java.io.Reader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;
import z.C.A.A.B.A.I;
import z.C.A.A.B.A.L;
import java.util.Vector;
import z.C.A.A.B.A.N;
import z.C.A.A.B.A.R;
import z.C.A.A.B.A.G;

public final class H
{
    private G F;
    private R E;
    private N C;
    private Vector B;
    private Vector D;
    private B A;
    
    public H(final R e, final N c) {
        this.F = null;
        this.B = new Vector();
        this.D = new Vector();
        this.A = new z.C.A.A.B.G();
        this.E = e;
        this.C = c;
    }
    
    public H() {
        this(new L(), new I());
    }
    
    public void A(final String s, final int n, final B b) throws z.C.A.A.B.A.H {
        if (s != null) {
            this.B.addElement(this.E.A(s, n));
        }
        else {
            this.B.addElement(null);
        }
        this.D.addElement(b);
    }
    
    public void B(final String s, final int n) throws z.C.A.A.B.A.H {
        this.A(s, n, this.A);
    }
    
    public void B(final String s) throws z.C.A.A.B.A.H {
        this.B(s, 0);
    }
    
    public void A(final String s, final B b) throws z.C.A.A.B.A.H {
        this.A(s, 0, b);
    }
    
    public void A(final String s, final int n) throws z.C.A.A.B.A.H {
        if (s == null) {
            this.F = null;
            return;
        }
        this.F = this.E.A(s, n);
    }
    
    public void A(final String s) throws z.C.A.A.B.A.H {
        this.A(s, 0);
    }
    
    public void A(final InputStream inputStream, final OutputStream outputStream, final String s) throws IOException {
        this.A(new InputStreamReader(inputStream, s), new OutputStreamWriter(outputStream));
    }
    
    public void A(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        this.A(new InputStreamReader(inputStream), new OutputStreamWriter(outputStream));
    }
    
    public void A(final Reader reader, final Writer writer) throws IOException {
        final LineNumberReader g = new LineNumberReader(reader);
        final PrintWriter a = new PrintWriter(writer);
        final A a2 = new A();
        final ArrayList list = new ArrayList();
        a2.D = this.C;
        a2.I = this.F;
        a2.G = g;
        a2.A = a;
        a2.E = null;
        final int size = this.B.size();
        a2.B = 0;
        while ((a2.J = g.readLine()) != null) {
            a2.H = a2.J.toCharArray();
            for (int i = 0; i < size; ++i) {
                if (this.B.elementAt(i) != null) {
                    final G f = this.B.elementAt(i);
                    if (this.C.C(a2.H, f)) {
                        a2.C = this.C.A();
                        a2.B = g.getLineNumber();
                        a2.F = f;
                        if (this.F != null) {
                            list.clear();
                            z.C.A.A.B.A.F.A(list, this.C, this.F, a2.J);
                            a2.E = list;
                        }
                        else {
                            a2.E = null;
                        }
                        ((B)this.D.elementAt(i)).A(a2);
                    }
                }
                else {
                    a2.C = null;
                    a2.B = g.getLineNumber();
                    if (this.F != null) {
                        list.clear();
                        z.C.A.A.B.A.F.A(list, this.C, this.F, a2.J);
                        a2.E = list;
                    }
                    else {
                        a2.E = null;
                    }
                    ((B)this.D.elementAt(i)).A(a2);
                }
            }
        }
        a.flush();
        g.close();
    }
}
