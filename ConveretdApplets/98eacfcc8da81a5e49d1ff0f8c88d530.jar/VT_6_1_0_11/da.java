// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Collection;
import java.util.Vector;
import com.hw.client.util.f;
import java.io.EOFException;
import java.io.OutputStream;
import java.io.InputStream;
import com.hw.client.util.a;
import com.hw.client.util.e;

public abstract class da extends e
{
    protected cg a;
    private dA b;
    private aK c;
    private bS d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    
    public da() {
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = false;
    }
    
    public final void a(final dA b) {
        this.b = b;
        try {
            this.h = (Integer.parseInt(b.b("-hmsclient:skip_connect")) == 1);
        }
        catch (NumberFormatException ex) {
            com.hw.client.util.a.c("skip_connect not specified");
        }
    }
    
    public final bS f() {
        if (this.d == null) {
            this.d = new bS(this.g());
        }
        return this.d;
    }
    
    public final aK g() {
        if (this.c == null) {
            this.c = new aK(this.b);
        }
        return this.c;
    }
    
    public void b() {
        this.c();
        (this.a = new cg(this)).a();
    }
    
    public void c() {
        if (this.a != null) {
            this.a.b();
            this.a = null;
        }
    }
    
    public final boolean h() {
        return !this.h || !this.g;
    }
    
    public final void a(final boolean b) {
        this.g = false;
    }
    
    public final void a(final Object o) {
        if (this.a != null) {
            this.a.a(o);
        }
    }
    
    protected abstract void a(final InputStream p0);
    
    protected abstract void b(final InputStream p0);
    
    protected abstract Object d();
    
    protected abstract Object e();
    
    protected InputStream c(final InputStream inputStream) {
        return inputStream;
    }
    
    protected InputStream d(final InputStream inputStream) {
        return inputStream;
    }
    
    protected OutputStream a(final OutputStream outputStream) {
        return outputStream;
    }
    
    protected abstract void a(final OutputStream p0, final Object p1, final boolean p2);
    
    protected final String e(final InputStream inputStream) {
        try {
            final StringBuffer sb = new StringBuffer();
            int read;
            while ((read = inputStream.read()) != 13 && read != 10 && read > 0) {
                sb.append((char)read);
            }
            if (read < 0 && this.a != null && this.a.c()) {
                com.hw.client.util.a.d("unexpectedly reached EOF while reading input stream");
                throw new EOFException("unexpectedly reached EOF while reading input stream");
            }
            return sb.toString();
        }
        catch (NullPointerException ex) {
            com.hw.client.util.a.a("caught null pointer exception while reading input stream", ex);
            throw new EOFException("caught null pointer exception while reading input stream");
        }
    }
    
    public final void a(final aB ab) {
        this.a(new cG(this, ab), true);
    }
    
    protected final String[] i() {
        try {
            final Vector vector;
            (vector = new Vector<String>()).add(this.g().f());
            vector.addAll(new Vector<String>(0));
            if (this.b.a() && bj.f() && this.g().g()) {
                com.hw.client.util.a.b("add log directory path");
                vector.add("-l");
                vector.add("../logs");
                if (this.b.a("MEDIUM_INTEGRITY") != null && this.b.a("MEDIUM_INTEGRITY").equals("1")) {
                    com.hw.client.util.a.b("forcing horizonmedia to run at a medium integrity level");
                    vector.add("-m");
                }
                if (this.b.a("FORCE_KILL") != null && this.b.a("FORCE_KILL").equals("1")) {
                    com.hw.client.util.a.b("set high proxy to explicitly kill agent process");
                    vector.add("-k");
                }
                com.hw.client.util.a.b("add door executable");
                vector.add(this.b.a("door_name") + ".exe");
            }
            final String[] array = new String[vector.size()];
            vector.toArray(array);
            return array;
        }
        catch (Exception ex) {
            com.hw.client.util.a.a("unable to get agent command array", ex);
            throw new at("unable to get agent command array");
        }
    }
    
    protected final String j() {
        try {
            final String[] i = this.i();
            String string = new String();
            for (int j = 0; j < i.length; ++j) {
                string = string + i[j] + " ";
            }
            com.hw.client.util.a.b("command_string => " + string);
            return string;
        }
        catch (at at) {
            com.hw.client.util.a.a("unable to get agent command as a string", at);
            throw new at("unable to get agent command as a string");
        }
    }
    
    public final void b(final boolean b) {
        this.e = false;
    }
    
    public final boolean k() {
        return this.e;
    }
    
    public final void c(final boolean b) {
        this.f = false;
    }
    
    public final boolean l() {
        return this.f;
    }
}
