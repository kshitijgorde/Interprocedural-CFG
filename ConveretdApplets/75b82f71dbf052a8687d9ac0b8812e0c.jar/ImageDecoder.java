import java.awt.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class ImageDecoder extends Thread
{
    static final int ERROR = -1;
    static final int SCAN_DONE = 1;
    static final int FRAME_DONE = 2;
    static final int PROGRESS_UPDATE = 3;
    static final int EXIT = 4;
    protected DecodeListener listener;
    protected InputStream stream;
    protected Source frame;
    protected URL url;
    boolean readToEnd;
    protected boolean mExit;
    protected boolean mShutdown;
    protected Object mLock;
    
    ImageDecoder(final URL url) {
        this.readToEnd = (System.getProperty("os.name").startsWith("Win") && System.getProperty("java.vendor").startsWith("Netscape") && System.getProperty("java.version").compareTo("1.1") >= 0);
        this.mExit = false;
        this.mShutdown = false;
        this.mLock = new Object();
        this.url = url;
    }
    
    void setStream(final InputStream stream) {
        this.stream = stream;
    }
    
    void addDecodeListener(final DecodeListener listener) {
        this.listener = listener;
    }
    
    void getFrame(final Source frame) {
        this.frame = frame;
        this.start();
    }
    
    protected void doDecode() throws IOException, RuntimeException, InterruptedException, Exception {
        final float[] def = { 0.0f, 0.0f, 0.0f, 0.1f };
        this.frame.setProperty("invp", def);
        this.decode(this.frame, 0);
    }
    
    public void run() {
        try {
            if (this.mExit) {
                throw new Exception("exit");
            }
            this.doDecode();
        }
        catch (ThreadDeath e) {
            throw e;
        }
        catch (Throwable e2) {
            if (e2.getMessage() == "exit") {
                this.listener.decodeUpdate(4, 0.0f);
                return;
            }
            e2.printStackTrace();
            this.listener.decodeUpdate(-1, 0.0f);
        }
        finally {
            this.listener = null;
            synchronized (this.mLock) {
                this.mShutdown = true;
                this.mExit = false;
            }
            // monitorexit(this.mLock)
        }
        this.listener = null;
        synchronized (this.mLock) {
            this.mShutdown = true;
            this.mExit = false;
        }
        // monitorexit(this.mLock)
    }
    
    protected byte[] realloc(final byte[] array, final int len) {
        final byte[] tmp = new byte[len];
        System.arraycopy(array, 0, tmp, 0, Math.min(array.length, len));
        return tmp;
    }
    
    protected byte[] parseJPEG(final DataInputStream stream) throws IOException {
        byte[] jpeg = new byte[6];
        stream.readFully(jpeg, 0, 6);
        int tag;
        for (int i = 2; jpeg[i + 1] != -64; i += 2 + tag) {
            if (jpeg[i + 1] == -38) {
                return jpeg;
            }
            tag = (jpeg[i + 2] << 8 & 0xFF00) + (jpeg[i + 3] & 0xFF);
            jpeg = this.realloc(jpeg, i + 4 + (tag + 2));
            stream.readFully(jpeg, i + 4, tag + 2);
        }
        return jpeg;
    }
    
    protected int findEOS(final byte[] jpeg, final int mark) throws RuntimeException {
        for (int i = jpeg.length - 2; i > mark; --i) {
            if (jpeg[i] == -1) {
                if (jpeg[i + 1] == -39) {
                    return i;
                }
                if (jpeg[i + 1] == -38) {
                    return i;
                }
            }
        }
        throw new RuntimeException();
    }
    
    protected void decode(final Source frame, final int length) throws IOException, InterruptedException, Exception {
        byte[] jpeg = this.parseJPEG(new DataInputStream(this.stream));
        int count = jpeg.length;
        jpeg = this.realloc(jpeg, length);
        while (!this.mExit) {
            Thread.yield();
            final int min = Math.min(Math.max(1024, this.stream.available()), jpeg.length - count);
            if (min == 0) {
                return;
            }
            int read;
            int r;
            for (read = 0; read < min; read += r) {
                r = 0;
                if (this.readToEnd) {
                    r = this.stream.read(jpeg, count + read, jpeg.length - count - read);
                }
                else {
                    r = this.stream.read(jpeg, count + read, min - read);
                }
                if (r == -1) {
                    throw new IOException();
                }
            }
            count += read;
            Thread.yield();
            try {
                final int eos = this.findEOS(jpeg, count - read);
                if (jpeg[eos + 1] != -38) {
                    this.setImage(frame, new ByteMappedImage(this.url).createImage(jpeg, 0, eos + 1));
                    this.listener.decodeUpdate(2, 1.0f);
                    return;
                }
                jpeg[eos + 1] = -39;
                this.setImage(frame, new ByteMappedImage(this.url).createImage(jpeg, 0, eos + 1));
                this.listener.decodeUpdate(1, 1.0f);
                jpeg[eos + 1] = -38;
            }
            catch (RuntimeException ex) {}
        }
        throw new Exception("exit");
    }
    
    void shutdown(final int timeout) throws InterruptedException, IllegalThreadStateException {
        if (Thread.currentThread() == this) {
            throw new IllegalThreadStateException();
        }
        synchronized (this.mLock) {
            if (this.mShutdown) {
                // monitorexit(this.mLock)
                return;
            }
            this.mExit = true;
        }
        // monitorexit(this.mLock)
        final long To = System.currentTimeMillis();
        while (this.mExit && System.currentTimeMillis() - To < timeout) {
            Thread.sleep(10L);
        }
    }
    
    protected abstract void setImage(final Source p0, final Image p1) throws InterruptedException;
}
