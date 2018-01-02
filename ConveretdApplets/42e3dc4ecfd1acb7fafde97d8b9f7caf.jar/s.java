import java.util.StringTokenizer;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends Thread
{
    private r a;
    private BufferedReader b;
    private BufferedWriter c;
    
    public s(final r a, final BufferedReader b, final BufferedWriter c) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public void run() {
        try {
            String line;
            while ((line = this.b.readLine()) != null) {
                try {
                    this.a.c(line);
                }
                catch (Throwable t) {
                    final StringWriter stringWriter = new StringWriter();
                    final PrintWriter printWriter = new PrintWriter(stringWriter);
                    t.printStackTrace(printWriter);
                    printWriter.flush();
                    final StringTokenizer stringTokenizer = new StringTokenizer(stringWriter.toString(), "\r\n");
                    synchronized (this.a) {
                        this.a.e("### Your implementation of PircBot is faulty and you have");
                        this.a.e("### allowed an uncaught Exception or Error to propagate in your");
                        this.a.e("### code. It may be possible for PircBot to continue operating");
                        this.a.e("### normally. Here is the stack trace that was produced: -");
                        this.a.e("### ");
                        while (stringTokenizer.hasMoreTokens()) {
                            this.a.e("### " + stringTokenizer.nextToken());
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
        this.a.e();
    }
    
    public void a(String substring) {
        if (substring.length() > this.a.f() - 2) {
            substring = substring.substring(0, this.a.f() - 2);
        }
        synchronized (this.c) {
            try {
                this.c.write(substring + "\r\n");
                this.c.flush();
                this.a.e(">>>" + substring);
            }
            catch (Exception ex) {}
        }
    }
}
