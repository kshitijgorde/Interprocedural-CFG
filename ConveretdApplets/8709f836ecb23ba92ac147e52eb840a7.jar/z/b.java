// 
// Decompiled by Procyon v0.5.30
// 

package z;

import com.sun.image.codec.jpeg.ImageFormatException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import com.photochannel.easyUploader.AppletParams;
import java.util.Observable;

public class b extends Observable implements Runnable
{
    private final aD a;
    private final aG b;
    private boolean c;
    private static /* synthetic */ boolean d;
    
    public b(final aD a, final aG b, final AppletParams appletParams) {
        this.c = false;
        if (!z.b.d && a == null) {
            throw new AssertionError();
        }
        if (!z.b.d && b == null) {
            throw new AssertionError();
        }
        if (a.a == null || a.a.length() == 0) {
            throw new RuntimeException("Invalid name: " + a.a);
        }
        if (a.b < 0L) {
            throw new RuntimeException("Invalid size: " + a.b);
        }
        if (a.c < 0L) {
            throw new RuntimeException("Invalid projected size: " + a.c);
        }
        if (a.f == null) {
            throw new RuntimeException("Invalid data source (null)");
        }
        if (a.d == null || a.d.length() == 0) {
            throw new RuntimeException("Invalid serverUploadFileID: " + a.d);
        }
        if (a.e == null) {
            throw new RuntimeException("Invalid receiverPage: " + a.e);
        }
        this.a = a;
        this.b = b;
    }
    
    private int b() {
        return (int)this.a.b;
    }
    
    public final void a() {
        this.c = false;
    }
    
    public void run() {
        this.c = true;
        System.out.println(String.format("starting upload: %s %d bytes...", au.a(this.a.a), this.b()));
        Exception ex = null;
        boolean a = false;
        for (int i = 0; i < 3; ++i) {
            final ag c;
            if ((c = this.c()).c != null && !(c.c instanceof ah) && !(c.c instanceof ae)) {
                this.a(c.c);
                this.a(false);
                this.c = false;
                return;
            }
            this.a.b = c.b;
            final boolean b = c.c != null && (c.c instanceof ah || c.c instanceof ae);
            final String a2 = this.a.a;
            final int b2 = this.b();
            final String s = a2;
            try {
                this.setChanged();
                this.notifyObservers(new bk(this, s, b2));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            final String a3 = this.a.a;
            try {
                this.setChanged();
                this.notifyObservers(new P(this, a3));
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
            System.out.println("uploading image to " + this.a.e);
            ex = null;
            HttpURLConnection httpURLConnection = null;
            a = false;
            try {
                final int h = this.a.f.h(this.a.a);
                final int j = this.a.f.i(this.a.a);
                final int fixedLengthStreamingMode = this.b.a(this.a.d, this.a.a, b, h, j, c.d).length() + ((int)c.b + aG.b().length());
                System.out.println("Connection is now created.");
                final HttpURLConnection httpURLConnection2;
                (httpURLConnection2 = (HttpURLConnection)this.a.e.openConnection()).setReadTimeout(0);
                httpURLConnection2.setFixedLengthStreamingMode(fixedLengthStreamingMode);
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setRequestProperty("Content-Type", aG.a());
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setDoOutput(true);
                final OutputStream outputStream = (httpURLConnection = httpURLConnection2).getOutputStream();
                final PrintWriter printWriter;
                (printWriter = new PrintWriter(outputStream)).print(this.b.a(this.a.d, this.a.a, b, h, j, c.d));
                printWriter.flush();
                a = this.a(c.a, outputStream);
                c.a.close();
                final PrintWriter printWriter2;
                (printWriter2 = new PrintWriter(outputStream)).print(aG.b());
                printWriter2.flush();
                if (!a) {
                    this.a(new Exception("Client aborted transfer"));
                    System.out.println("upload aborted: " + au.a(this.a.a));
                    try {
                        this.setChanged();
                        this.notifyObservers(new C(this, this.a.a, "user aborted"));
                    }
                    catch (Exception ex4) {
                        ex4.printStackTrace();
                    }
                }
            }
            catch (IOException ex6) {
                final IOException ex5 = ex6;
                ex6.printStackTrace();
                ex = ex5;
            }
            finally {
                try {
                    if (httpURLConnection != null) {
                        if (httpURLConnection.getResponseCode() == 200) {
                            final InputStream inputStream = httpURLConnection.getInputStream();
                            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            if (inputStream.available() > 0) {
                                String string = "";
                                while (bufferedReader.ready()) {
                                    string += bufferedReader.readLine();
                                }
                                if (string.length() > 0) {
                                    System.out.println("SERVER SAYS (UploadTask): " + string);
                                    final int a4;
                                    if ((a4 = aG.a(aa.a(ap.a(string)))) != 0) {
                                        System.out.println("SERVER RESPONSE code = " + a4);
                                        ex = new Exception("Upload failed, PNI status code = " + a4);
                                    }
                                }
                            }
                            bufferedReader.close();
                        }
                        else {
                            System.out.println("SERVER RESPONSE = " + httpURLConnection.getResponseCode());
                            ex = new Exception("Server responded HTTP " + httpURLConnection.getResponseCode());
                        }
                    }
                }
                catch (Exception ex7) {
                    if (ex == null) {
                        ex = ex7;
                    }
                    ex7.printStackTrace();
                }
                try {
                    c.a.close();
                }
                catch (Exception ex8) {
                    ex8.printStackTrace();
                }
            }
            if (ex == null && a) {
                break;
            }
            System.out.println(">>>retrying upload of " + this.a.a);
            System.out.println(">> conn > " + httpURLConnection);
            httpURLConnection.disconnect();
            System.out.println("disconnected.");
        }
        if (ex == null && a) {
            this.b(true);
        }
        else {
            this.a(ex);
            this.a(true);
            this.b(false);
        }
        this.c = false;
    }
    
    private void a(final Exception ex) {
        this.b.a(this.a.d, ex);
    }
    
    private boolean a(final InputStream inputStream, final OutputStream outputStream) {
        final byte[] array = new byte[4096];
        int n = 0;
        while (this.c && n < this.b()) {
            final int read;
            if ((read = inputStream.read(array)) == -1) {
                throw new RuntimeException("Unexpected EOF");
            }
            outputStream.write(array, 0, read);
            outputStream.flush();
            n += read;
            if (!z.b.d && n > this.b()) {
                throw new AssertionError();
            }
            this.setChanged();
            this.notifyObservers(new n(this, this.a.a, read));
        }
        return n == this.b();
    }
    
    private ag c() {
        final ag ag = new ag(null, 0L);
        try {
            return this.a.f.g((this = this).a.a);
        }
        catch (ImageFormatException ex) {
            final Exception c = (Exception)ex;
            ex.printStackTrace();
            ag.c = c;
        }
        catch (ah ah) {
            final ah c2 = ah;
            ah.printStackTrace();
            ag.c = c2;
        }
        catch (IOException ex2) {
            final IOException c3 = ex2;
            ex2.printStackTrace();
            ag.c = c3;
        }
        catch (am am) {
            final am c4 = am;
            am.printStackTrace();
            ag.c = c4;
        }
        catch (M m) {
            final M c5 = m;
            m.printStackTrace();
            ag.c = c5;
        }
        catch (ae c6) {
            ag.c = c6;
        }
        return ag;
    }
    
    private void a(final boolean b) {
        try {
            this.setChanged();
            this.notifyObservers(new p(this, (this = this).a.a, b));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void b(final boolean b) {
        if (b) {
            System.out.println("upload completed OK: " + au.a(this.a.a));
        }
        else {
            System.out.println("upload FAILED 3 times: " + au.a(this.a.a));
        }
        try {
            this.setChanged();
            this.notifyObservers(new bs(this, this.a.a, b));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    static {
        b.d = !b.class.desiredAssertionStatus();
    }
}
