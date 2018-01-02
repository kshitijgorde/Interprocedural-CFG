// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.util.Enumeration;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Hashtable;
import java.io.OutputStream;

public final class TimedOutputStream extends OutputStream
{
    private OutputStream m_Out;
    private long m_TimeoutInTicks;
    private volatile long m_TimeOutTick;
    private volatile boolean m_bTimedOut;
    private static Thread ms_threadInterrupt;
    private static Hashtable ms_hashtableOutputStreams;
    private static volatile long ms_currentTick;
    static final long MS_PER_TICK = 5000L;
    
    private TimedOutputStream() {
    }
    
    public static void init() {
        TimedOutputStream.ms_hashtableOutputStreams = new Hashtable(1000);
        (TimedOutputStream.ms_threadInterrupt = new Thread(new TimedOutputStreamInterrupt(), "TimedOutputStream")).setDaemon(true);
        TimedOutputStream.ms_threadInterrupt.start();
    }
    
    public TimedOutputStream(final OutputStream out, final long n) {
        this.m_Out = out;
        this.m_TimeoutInTicks = n / 5000L;
    }
    
    public void write(final int n) throws IOException {
        this.m_TimeOutTick = TimedOutputStream.ms_currentTick + this.m_TimeoutInTicks;
        TimedOutputStream.ms_hashtableOutputStreams.put(this, this);
        this.m_bTimedOut = false;
        try {
            this.m_Out.write(n);
        }
        catch (IOException ex) {
            TimedOutputStream.ms_hashtableOutputStreams.remove(this);
            if (this.m_bTimedOut) {
                throw new InterruptedIOException("TimedOutputStream: write() timed out!");
            }
            throw ex;
        }
        TimedOutputStream.ms_hashtableOutputStreams.remove(this);
    }
    
    public void write(final byte[] array) throws IOException {
        this.write(array, 0, array.length);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this.m_TimeOutTick = TimedOutputStream.ms_currentTick + this.m_TimeoutInTicks;
        TimedOutputStream.ms_hashtableOutputStreams.put(this, this);
        this.m_bTimedOut = false;
        try {
            this.m_Out.write(array, n, n2);
        }
        catch (IOException ex) {
            TimedOutputStream.ms_hashtableOutputStreams.remove(this);
            if (this.m_bTimedOut) {
                throw new InterruptedIOException("TimedOutputStream: write() timed out!");
            }
            throw ex;
        }
        TimedOutputStream.ms_hashtableOutputStreams.remove(this);
    }
    
    public void close() throws IOException {
        this.m_Out.close();
    }
    
    public void flush() throws IOException {
        this.m_TimeOutTick = TimedOutputStream.ms_currentTick + this.m_TimeoutInTicks;
        TimedOutputStream.ms_hashtableOutputStreams.put(this, this);
        this.m_bTimedOut = false;
        try {
            this.m_Out.flush();
        }
        catch (IOException ex) {
            TimedOutputStream.ms_hashtableOutputStreams.remove(this);
            if (this.m_bTimedOut) {
                throw new InterruptedIOException("TimedOutputStream: flush() timed out!");
            }
            throw ex;
        }
        TimedOutputStream.ms_hashtableOutputStreams.remove(this);
    }
    
    private static final class TimedOutputStreamInterrupt implements Runnable
    {
        public void run() {
            TimedOutputStream.ms_currentTick = 0L;
            while (true) {
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException ex) {
                    continue;
                }
                TimedOutputStream.ms_currentTick++;
                try {
                    final Enumeration<TimedOutputStream> elements = TimedOutputStream.ms_hashtableOutputStreams.elements();
                    while (elements.hasMoreElements()) {
                        final TimedOutputStream timedOutputStream = elements.nextElement();
                        if (TimedOutputStream.ms_currentTick > timedOutputStream.m_TimeOutTick) {
                            try {
                                timedOutputStream.m_bTimedOut = true;
                                timedOutputStream.close();
                            }
                            catch (Throwable t) {}
                        }
                    }
                }
                catch (Exception ex2) {}
            }
        }
    }
}
