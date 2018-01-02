// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix.util;

import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.jruby.ext.posix.POSIXHandler;

public class ExecIt
{
    POSIXHandler handler;
    
    public ExecIt(final POSIXHandler handler) {
        this.handler = handler;
    }
    
    public int runAndWait(final String... args) throws IOException, InterruptedException {
        return this.runAndWait(this.handler.getOutputStream(), args);
    }
    
    public int runAndWait(final OutputStream output, final String... args) throws IOException, InterruptedException {
        final Process process = this.run(args);
        this.handleStreams(process, this.handler.getInputStream(), output, this.handler.getErrorStream());
        return process.waitFor();
    }
    
    public Process run(final String... args) throws IOException {
        final File cwd = this.handler.getCurrentWorkingDirectory();
        return Runtime.getRuntime().exec(args, this.handler.getEnv(), cwd);
    }
    
    private void handleStreams(final Process p, final InputStream in, final OutputStream out, final OutputStream err) throws IOException {
        final InputStream pOut = p.getInputStream();
        final InputStream pErr = p.getErrorStream();
        final OutputStream pIn = p.getOutputStream();
        final StreamPumper t1 = new StreamPumper(pOut, out, false);
        final StreamPumper t2 = new StreamPumper(pErr, err, false);
        final StreamPumper t3 = new StreamPumper(in, pIn, true);
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
        }
        catch (InterruptedException ex) {}
        try {
            t2.join();
        }
        catch (InterruptedException ex2) {}
        t3.quit();
        try {
            err.flush();
        }
        catch (IOException ex3) {}
        try {
            out.flush();
        }
        catch (IOException ex4) {}
        try {
            pIn.close();
        }
        catch (IOException ex5) {}
        try {
            pOut.close();
        }
        catch (IOException ex6) {}
        try {
            pErr.close();
        }
        catch (IOException ex7) {}
    }
    
    private static class StreamPumper extends Thread
    {
        private InputStream in;
        private OutputStream out;
        private boolean onlyIfAvailable;
        private volatile boolean quit;
        private final Object waitLock;
        
        StreamPumper(final InputStream in, final OutputStream out, final boolean avail) {
            this.waitLock = new Object();
            this.in = in;
            this.out = out;
            this.onlyIfAvailable = avail;
        }
        
        public void run() {
            final byte[] buf = new byte[1024];
            boolean hasReadSomething = false;
            try {
                while (!this.quit) {
                    if (this.onlyIfAvailable && !hasReadSomething) {
                        if (this.in.available() == 0) {
                            synchronized (this.waitLock) {
                                this.waitLock.wait(10L);
                            }
                            continue;
                        }
                        hasReadSomething = true;
                    }
                    final int numRead;
                    if ((numRead = this.in.read(buf)) == -1) {
                        break;
                    }
                    this.out.write(buf, 0, numRead);
                }
            }
            catch (Exception e) {}
            finally {
                if (this.onlyIfAvailable) {
                    try {
                        this.out.close();
                    }
                    catch (IOException ex) {}
                }
            }
        }
        
        public void quit() {
            this.quit = true;
            synchronized (this.waitLock) {
                this.waitLock.notify();
            }
        }
    }
}
