// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.fluendo.utils.Debug;
import java.io.Reader;
import java.io.InputStream;

public class PreBuffer extends InputStream implements Runnable
{
    private InputStream inputStream;
    private int bufferSize;
    private byte[] buffer;
    private int in;
    private int out;
    private Reader reader;
    private Thread thread;
    private boolean stopping;
    private int low;
    private int high;
    private PreBufferNotify notify;
    private boolean eos;
    private boolean readerBlocked;
    private boolean writerBlocked;
    private long received;
    private long receiveStart;
    private long consumed;
    private static int SEGSIZE;
    private int segment;
    private byte[] temp;
    private int state;
    
    public PreBuffer(final InputStream inputStream, final int bufferSize, final int n, final int n2, final PreBufferNotify notify) {
        this.stopping = false;
        this.temp = new byte[1];
        this.state = 0;
        this.inputStream = inputStream;
        this.in = -1;
        this.out = 0;
        this.notify = notify;
        this.low = bufferSize * n / 100;
        if (this.low <= 0) {
            this.low = 1;
        }
        this.high = bufferSize * n2 / 100;
        if (this.high >= bufferSize) {
            this.high = bufferSize - 1;
        }
        this.eos = false;
        this.segment = Math.max(PreBuffer.SEGSIZE, this.low);
        this.bufferSize = bufferSize;
        this.buffer = new byte[this.bufferSize];
        (this.thread = new Thread(this, "preBuffer")).start();
        this.receiveStart = 0L;
    }
    
    public int free() {
        synchronized (this) {
            if (this.in == -1) {
                return this.bufferSize;
            }
            return (this.out + this.bufferSize - this.in) % this.bufferSize;
        }
    }
    
    public int available() {
        synchronized (this) {
            if (this.in == -1) {
                return 0;
            }
            if (this.in == this.out) {
                return this.bufferSize;
            }
            return (this.in + this.bufferSize - this.out) % this.bufferSize;
        }
    }
    
    public void stop() {
        this.stopping = true;
        try {
            this.thread.interrupt();
        }
        catch (Exception ex) {}
        try {
            this.thread.join();
        }
        catch (Exception ex2) {}
    }
    
    public void run() {
        Thread.currentThread().setPriority(10);
        try {
            this.realRun();
        }
        catch (Throwable t) {
            Cortado.shutdown(t);
        }
    }
    
    public boolean isEmpty() {
        return this.available() < this.low;
    }
    
    public boolean isFilled() {
        return this.available() >= this.high;
    }
    
    public synchronized int getFilled() {
        return this.available();
    }
    
    public synchronized long getReceived() {
        return this.received;
    }
    
    public synchronized double getReceiveSpeed() {
        if (this.receiveStart == 0L) {
            return 0.0;
        }
        return this.received / (System.currentTimeMillis() - this.receiveStart);
    }
    
    public synchronized double getConsumeSpeed() {
        if (this.receiveStart == 0L) {
            return 0.0;
        }
        return this.consumed / (System.currentTimeMillis() - this.receiveStart);
    }
    
    private void checkFilled() {
        if (this.state == 1 && this.isFilled()) {
            this.state = 2;
            if (this.notify != null) {
                this.notify.preBufferNotify(this.state);
            }
        }
    }
    
    private void checkEmpty() {
        if (!this.eos && this.state == 2 && this.isEmpty()) {
            this.state = 1;
            if (this.notify != null) {
                this.notify.preBufferNotify(this.state);
            }
        }
    }
    
    public synchronized void startBuffer() {
        Debug.log(3, "start buffer..");
        this.state = 1;
        this.received = 0L;
        this.receiveStart = System.currentTimeMillis();
    }
    
    private void realRun() {
        Debug.log(3, "entering preroll thread");
        while (!this.stopping) {
            try {
                int n;
                int segment;
                int n2;
                synchronized (this) {
                    while (this.free() < this.segment) {
                        if (this.notify != null) {
                            this.notify.preBufferNotify(3);
                        }
                        this.writerBlocked = true;
                        this.wait();
                        this.writerBlocked = false;
                    }
                    if (this.in < 0) {
                        n = this.out;
                    }
                    else {
                        n = this.in;
                    }
                    if (n + this.segment > this.bufferSize) {
                        segment = this.bufferSize - n;
                        n2 = this.segment - segment;
                    }
                    else {
                        segment = this.segment;
                        n2 = 0;
                    }
                }
                final int read = this.inputStream.read(this.buffer, n, segment);
                int read2;
                if (n2 > 0 && read == segment) {
                    read2 = this.inputStream.read(this.buffer, 0, n2);
                }
                else {
                    read2 = 0;
                }
                if (read < 0) {
                    this.eos = true;
                    Debug.log(3, "writer EOS");
                    Debug.log(3, "emptying buffer");
                    break;
                }
                int n3;
                if (read2 <= 0) {
                    n3 = read;
                }
                else {
                    n3 = read + read2;
                }
                synchronized (this) {
                    this.received += n3;
                    this.in = (n + n3) % this.bufferSize;
                    this.checkFilled();
                    if (!this.readerBlocked) {
                        continue;
                    }
                    this.notify();
                }
            }
            catch (Exception ex) {
                if (this.stopping) {
                    continue;
                }
                ex.printStackTrace();
                this.stopping = true;
            }
        }
        Debug.log(3, "exit preroll thread");
    }
    
    public int read() {
        int read = this.read(this.temp, 0, 1);
        if (read > 0) {
            read = this.temp[0] + 256;
        }
        return read;
    }
    
    public int read(final byte[] array, final int n, int min) {
        int n2;
        int n3;
        synchronized (this) {
            int i = this.available();
            if (this.eos) {
                if (i == 0) {
                    Debug.log(3, "reader EOS");
                    return -1;
                }
                min = Math.min(i, min);
            }
            while (i < min) {
                try {
                    Debug.log(4, "read: wait available " + i + " need " + min);
                    this.readerBlocked = true;
                    this.wait();
                    this.readerBlocked = false;
                    Debug.log(4, "read: wait done available " + i + " need " + min);
                }
                catch (InterruptedException ex) {}
                i = this.available();
            }
            if (this.out + min > this.bufferSize) {
                n2 = this.bufferSize - this.out;
                n3 = min - n2;
            }
            else {
                n2 = min;
                n3 = 0;
            }
        }
        System.arraycopy(this.buffer, this.out, array, n, n2);
        if (n3 > 0) {
            System.arraycopy(this.buffer, 0, array, n + n2, n3);
        }
        synchronized (this) {
            this.out = (this.out + min) % this.bufferSize;
            this.checkEmpty();
            if (this.writerBlocked) {
                this.notify();
            }
            this.consumed += min;
            if (this.eos && this.in == this.out) {
                this.in = -1;
                Debug.log(3, "bytes consumed: " + this.consumed);
            }
        }
        return min;
    }
    
    public final void dumpStats() {
        Debug.log(4, "buffer: [in:" + this.getReceived() + ", in-speed:" + this.getReceiveSpeed() + ", avail:" + this.available() + "/" + this.bufferSize + "]");
    }
    
    static {
        PreBuffer.SEGSIZE = 1024;
    }
}
