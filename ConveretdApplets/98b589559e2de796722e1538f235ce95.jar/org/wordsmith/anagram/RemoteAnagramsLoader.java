// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class RemoteAnagramsLoader implements Runnable, AnagramStringsSupplier
{
    private volatile String mySource;
    private volatile String myTarget;
    private volatile boolean myIsIOCompleted;
    private volatile boolean myStarted;
    
    public void run() {
        try {
            this.loadRemoteStrings();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.setSource(null);
            this.setTarget(null);
        }
        finally {
            this.setIOCompleted();
        }
    }
    
    private void loadRemoteStrings() throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL("http://wordsmith.org/anagram/anagram.txt").openStream()));
        try {
            final String line = bufferedReader.readLine();
            if (line == null) {
                return;
            }
            final String line2 = bufferedReader.readLine();
            if (line2 == null) {
                return;
            }
            this.setSource(line.trim());
            this.setTarget(line2.trim());
        }
        finally {
            try {
                bufferedReader.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public synchronized boolean isReady() {
        return this.myIsIOCompleted;
    }
    
    public synchronized String getSourceString() {
        return (this.mySource == null) ? "Internet Anagram Server" : this.mySource;
    }
    
    public synchronized String getTargetString() {
        return (this.myTarget == null) ? "I, Rearrangement Servant" : this.myTarget;
    }
    
    public synchronized void startLoading() {
        if (!this.myStarted) {
            this.myStarted = true;
            new Thread(this).start();
        }
    }
    
    private synchronized void setSource(final String mySource) {
        this.mySource = mySource;
    }
    
    private synchronized void setTarget(final String myTarget) {
        this.myTarget = myTarget;
    }
    
    private synchronized void setIOCompleted() {
        this.myIsIOCompleted = true;
    }
}
