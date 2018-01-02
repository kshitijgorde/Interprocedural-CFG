import java.net.Socket;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

// 
// Decompiled by Procyon v0.5.30
// 

public final class d extends Thread
{
    protected ServerSocket a;
    protected c b;
    
    public d(final c b) throws IOException {
        super("TCPThread");
        this.a = null;
        this.b = b;
        try {
            (this.a = new ServerSocket(c.a)).setSoTimeout(4000);
        }
        catch (BindException ex) {
            e.c("TCP-S", "Couldnt bind to port " + c.a);
        }
    }
    
    public final void run() {
        if (!this.a.isBound()) {
            e.c("TCP-S", "TCP test failed");
            return;
        }
        e.a("TCP-S", "Listening on TCP port " + c.a);
        Socket accept = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            this.a.setSoTimeout(5000);
            accept = this.a.accept();
            printWriter = new PrintWriter(accept.getOutputStream(), true);
            final String line;
            if ((line = (bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()))).readLine()).startsWith("TCP")) {
                e.d("TCP-S", line);
                this.b.c(line);
            }
            printWriter.println("OK");
        }
        catch (IOException ex) {
            e.c("TCP-S", "TCP Test failed: " + ex.getMessage());
        }
        finally {
            e.a("TCP-S", "Closing sockets");
            if (printWriter != null) {
                printWriter.close();
            }
            if (accept != null) {
                try {
                    accept.close();
                }
                catch (IOException ex2) {
                    e.c("TCP-S", "couldn't close clientSocket - " + ex2.getMessage());
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex3) {
                    e.c("TCP-S", "couldn't close in - " + ex3.getMessage());
                }
            }
            if (this.a != null) {
                try {
                    this.a.close();
                }
                catch (IOException ex4) {
                    e.c("TCP-S", "couldn't close socket - " + ex4.getMessage());
                }
            }
        }
        e.a("TCP-S", "TCP Thread destroyed");
    }
}
