import java.io.IOException;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class RSSocket implements Runnable
{
    private InputStream inputStream;
    private OutputStream outputStream;
    private final Socket socket;
    private boolean closed;
    private final RSApplet rsApplet;
    private byte[] buffer;
    private int writeIndex;
    private int buffIndex;
    private boolean isWriter;
    private boolean hasIOError;
    
    public RSSocket(final RSApplet rsApplet, final Socket socket) throws IOException {
        this.closed = false;
        this.isWriter = false;
        this.hasIOError = false;
        this.rsApplet = rsApplet;
        (this.socket = socket).setSoTimeout(30000);
        this.socket.setTcpNoDelay(true);
        this.inputStream = this.socket.getInputStream();
        this.outputStream = this.socket.getOutputStream();
    }
    
    public void close() {
        this.closed = true;
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
            if (this.outputStream != null) {
                this.outputStream.close();
            }
            if (this.socket != null) {
                this.socket.close();
            }
        }
        catch (IOException ex) {}
        this.isWriter = false;
        synchronized (this) {
            this.notify();
        }
        this.buffer = null;
    }
    
    public int read() throws IOException {
        if (this.closed) {
            return 0;
        }
        return this.inputStream.read();
    }
    
    public int available() throws IOException {
        if (this.closed) {
            return 0;
        }
        return this.inputStream.available();
    }
    
    public void flushInputStream(final byte[] array, int i) throws IOException {
        int n = 0;
        if (this.closed) {
            return;
        }
        while (i > 0) {
            final int read = this.inputStream.read(array, n, i);
            if (read <= 0) {
                throw new IOException("EOF");
            }
            n += read;
            i -= read;
        }
    }
    
    public void queueBytes(final int n, final byte[] array) throws IOException {
        if (this.closed) {
            return;
        }
        if (this.hasIOError) {
            this.hasIOError = false;
            throw new IOException("Error in writer thread");
        }
        if (this.buffer == null) {
            this.buffer = new byte[5000];
        }
        synchronized (this) {
            for (int i = 0; i < n; ++i) {
                this.buffer[this.buffIndex] = array[i];
                this.buffIndex = (this.buffIndex + 1) % 5000;
                if (this.buffIndex == (this.writeIndex + 4900) % 5000) {
                    throw new IOException("buffer overflow");
                }
            }
            if (!this.isWriter) {
                this.isWriter = true;
                this.rsApplet.startRunnable(this, 3);
            }
            this.notify();
        }
    }
    
    @Override
    public void run() {
        while (this.isWriter) {
            final int writeIndex;
            int n;
            synchronized (this) {
                if (this.buffIndex == this.writeIndex) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                if (!this.isWriter) {
                    return;
                }
                writeIndex = this.writeIndex;
                if (this.buffIndex >= this.writeIndex) {
                    n = this.buffIndex - this.writeIndex;
                }
                else {
                    n = 5000 - this.writeIndex;
                }
            }
            if (n > 0) {
                try {
                    this.outputStream.write(this.buffer, writeIndex, n);
                }
                catch (IOException ex2) {
                    this.hasIOError = true;
                }
                this.writeIndex = (this.writeIndex + n) % 5000;
                try {
                    if (this.buffIndex != this.writeIndex) {
                        continue;
                    }
                    this.outputStream.flush();
                }
                catch (IOException ex3) {
                    this.hasIOError = true;
                }
            }
        }
    }
    
    public void printDebug() {
        System.out.println("dummy:" + this.closed);
        System.out.println("tcycl:" + this.writeIndex);
        System.out.println("tnum:" + this.buffIndex);
        System.out.println("writer:" + this.isWriter);
        System.out.println("ioerror:" + this.hasIOError);
        try {
            System.out.println("available:" + this.available());
        }
        catch (IOException ex) {}
    }
}
