// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.bv;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ReaderThread extends Thread
{
    URL url;
    BookViewPane vp;
    int me;
    volatile boolean zombie;
    static volatile int count;
    private InputStream is;
    boolean includeTimeAndMPID;
    
    static {
        ReaderThread.count = 0;
    }
    
    public ReaderThread(final URL url1, final BookViewPane bookviewpane) {
        super("ReaderThread " + url1.getFile());
        this.includeTimeAndMPID = false;
        this.zombie = false;
        this.me = getNextCount();
        Debug.x("Created thread #" + this.me + " for " + url1.getFile());
        this.url = url1;
        this.vp = bookviewpane;
    }
    
    public ReaderThread(final URL url1, final BookViewPane bookviewpane, final boolean includeTimeAndMPID) {
        this(url1, bookviewpane);
        this.includeTimeAndMPID = includeTimeAndMPID;
    }
    
    static synchronized int getNextCount() {
        return ++ReaderThread.count;
    }
    
    public void kill() {
        Debug.x("Killing #" + this.me);
        this.zombie = true;
    }
    
    void readHeartbeat(final InputStream inputstream) throws IOException {
        final int i = (int)Utils.readNum(inputstream, 3);
        this.vp.showMessage("As of " + Utils.timeString(i));
    }
    
    void readStockRecord(final InputStream inputstream, final boolean halted) throws IOException {
        final int i = (int)Utils.readNum(inputstream, 3);
        ReadableStockRecord readablestockrecord = null;
        if (this.includeTimeAndMPID) {
            readablestockrecord = new ReadableStockRecord(inputstream, halted, this.includeTimeAndMPID);
        }
        else {
            readablestockrecord = new ReadableStockRecord(inputstream, halted);
        }
        this.vp.showStockRecord(readablestockrecord);
        this.vp.showMessage("As of " + Utils.timeString(i));
    }
    
    public void run() {
        Debug.x("Running #" + this.me);
        String s = null;
        Label_0357: {
            try {
                Debug.x("Connecting #" + this.me);
                this.vp.showMessage("Connecting...");
                s = "Could not connect to host";
                this.is = this.url.openStream();
                this.vp.showMessage("Connected...");
                Debug.x("Connected #" + this.me);
                s = "Zombies are alive!";
                while (!this.zombie) {
                    final int i = this.is.read();
                    if (i == 0) {
                        s = "Connection closed";
                        Debug.x("Connection closed #" + this.me);
                        break Label_0357;
                    }
                    if (i < 0) {
                        s = "Connection Failed";
                        Debug.x("Read Failed #" + this.me);
                        break Label_0357;
                    }
                    switch ((char)i) {
                        default: {
                            continue;
                        }
                        case 'H': {
                            this.readHeartbeat(this.is);
                            continue;
                        }
                        case 'S': {
                            this.readStockRecord(this.is, false);
                            continue;
                        }
                        case 'X': {
                            this.readStockRecord(this.is, true);
                            continue;
                        }
                        case 'N': {
                            Debug.x(" got N");
                            s = "Stock not found";
                            break Label_0357;
                        }
                    }
                }
                Debug.x("Closing #" + this.me);
                this.is.close();
                Debug.x("Closed #" + this.me);
            }
            catch (IOException ioexception) {
                Debug.x("IOException #" + ioexception);
                s = "Connection lost";
            }
        }
        Debug.x("Finished #" + this.me);
        if (!this.zombie) {
            Debug.x("Blanking #" + this.me);
            this.vp.blankStockRecord(s);
        }
        this.is = null;
        this.url = null;
        Debug.x("Goodbye #" + this.me);
    }
}
