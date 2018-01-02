// 
// Decompiled by Procyon v0.5.30
// 

package ChatRoom;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;

public class c
{
    public Socket a;
    public PrintStream b;
    public DataInputStream c;
    
    public void c() throws IOException {
        if (!this.d()) {
            return;
        }
        this.a.close();
        this.a = null;
        this.c = null;
        this.b = null;
    }
    
    public void a(final String s, final int n) throws IOException {
        if (this.d()) {
            this.c();
        }
        this.a = new Socket(s, n);
        this.b = new PrintStream(new BufferedOutputStream(this.a.getOutputStream()), true);
        this.c = new DataInputStream(new BufferedInputStream(this.a.getInputStream()));
    }
    
    public c(final String s, final int n) throws IOException {
        this.a(s, n);
    }
    
    public c() {
    }
    
    public final boolean d() {
        return this.a != null;
    }
    
    public void f(final String s) throws IOException {
        this.b.print(s);
        this.b.flush();
    }
}
