import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemrr extends Thread
{
    public int a;
    public int b;
    public InetAddress c;
    protected abljema d;
    protected boolean e;
    protected ServerSocket f;
    protected Socket g;
    protected DataInputStream h;
    protected DataOutputStream i;
    protected boolean j;
    
    abljemrr(final abljema d, final boolean e) {
        super(String.valueOf(d.l) + "abljemrr");
        this.a = 43855;
        this.j = false;
        this.setDaemon(true);
        this.d = d;
        this.e = e;
    }
    
    public void run() {
        this.j = true;
        try {
            if (this.e) {
                abljem.d("Relay thread started");
            }
            this.f = new ServerSocket(this.a, this.b, this.c);
            while (this.j) {
                try {
                    final Socket accept = this.f.accept();
                    this.b();
                    this.g = accept;
                    if (this.e) {
                        abljem.d("Relay connected");
                    }
                    this.h = new DataInputStream(new BufferedInputStream(accept.getInputStream()));
                    this.i = new DataOutputStream(new BufferedOutputStream(accept.getOutputStream()));
                    new Receiver(this.h, this.d).start();
                }
                catch (Throwable t) {
                    if (!this.j) {
                        continue;
                    }
                    t.printStackTrace();
                }
            }
        }
        catch (Throwable t2) {
            if (this.j) {
                t2.printStackTrace();
            }
        }
        finally {
            this.a();
            if (this.e) {
                abljem.d("Relay thread ended");
            }
        }
    }
    
    public void a(final byte[] array, final int n) {
        if (array == null || this.i == null) {
            return;
        }
        try {
            if (this.e) {
                String string = new String(array, 0, n);
                if (string.length() > 50) {
                    string = String.valueOf(string.substring(0, 50)) + "...";
                }
                abljem.d("Relay sending " + n + " bytes=" + string);
            }
            this.i.write(this.d.dm, 0, 4);
            this.i.writeInt(n);
            this.i.write(array, 0, n);
            this.i.flush();
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.b();
        }
    }
    
    public void a() {
        if (this.e && this.j) {
            abljem.d("Relay ending");
        }
        this.j = false;
        try {
            this.f.close();
        }
        catch (Throwable t) {}
        this.b();
    }
    
    protected void b() {
        if (this.e && this.g != null) {
            abljem.d("Relay disconnected");
        }
        try {
            this.g.close();
        }
        catch (Throwable t) {}
        this.g = null;
        this.h = null;
        this.i = null;
    }
    
    class Receiver extends Thread
    {
        private DataInputStream b;
        
        Receiver(final DataInputStream b, final abljema abljema) {
            super(String.valueOf(abljema.l) + "abljemrrReceiver");
            this.setDaemon(true);
            this.b = b;
        }
        
        public void run() {
            final byte[] array = new byte[4];
            try {
                while (true) {
                    this.a(array);
                    if (!abljema.a(array, 0, abljemrr.this.d.dm, 0, 4)) {
                        abljem.d("Relay BLXTYP bad " + array[0] + "," + array[1] + "," + array[2] + "," + array[3]);
                        abljemrr.this.b();
                        return;
                    }
                    final int int1 = this.b.readInt();
                    if (int1 < 0 || int1 > 1999999) {
                        abljem.d("Relay input length " + int1 + " invalid");
                        abljemrr.this.b();
                        return;
                    }
                    final byte[] array2 = new byte[int1];
                    this.a(array2);
                    if (abljemrr.this.e) {
                        String string = new String(array2);
                        if (string.length() > 50) {
                            string = String.valueOf(string.substring(0, 50)) + "...";
                        }
                        abljem.d("Relay received " + int1 + " bytes=" + string);
                    }
                    if (int1 <= 0) {
                        continue;
                    }
                    abljemrr.this.d.bm.a(array2);
                }
            }
            catch (Throwable t) {
                if (this.b == abljemrr.this.h) {
                    t.printStackTrace();
                    abljemrr.this.b();
                }
            }
        }
        
        private void a(final byte[] array) throws Throwable {
            int read;
            for (int i = array.length, n = 0; i > 0; i -= read, n += read) {
                read = this.b.read(array, n, i);
                if (read < 0) {
                    abljemrr.this.b();
                    throw new RuntimeException("Read hit end-of-stream");
                }
            }
        }
    }
}
