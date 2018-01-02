// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.util.Iterator;
import java.util.Vector;

public class UploadManager extends Thread
{
    private Vector threadPool;
    private boolean runningThread;
    
    UploadManager() {
        this.threadPool = new Vector();
        this.runningThread = false;
        this.setPriority(1);
    }
    
    public void addThread(final HTTPRequest httprequest) {
        this.debug("UploadManager() adding thread " + httprequest);
        this.threadPool.add(httprequest);
        this.debug("UploadManager() thread added. there are " + this.threadPool.size() + " threads.");
    }
    
    public void run() {
        this.debug("UploadManager() run()/start()");
        while (true) {
            for (final HTTPRequest element : this.threadPool) {
                if (element.isFinished()) {
                    this.threadPool.remove(element);
                    this.debug("UploadManager() removing finishes thread...");
                    break;
                }
            }
            this.runningThread = false;
            for (final HTTPRequest element : this.threadPool) {
                if (element.isRunning()) {
                    this.runningThread = true;
                }
            }
            if (this.threadPool.size() > 0) {
                if (!this.runningThread) {
                    this.debug("UploadManager() no thread is running... starting one");
                    final HTTPRequest thread = this.threadPool.firstElement();
                    thread.start();
                }
                else {
                    this.debug("UploadManager() there is a thread running. waiting");
                }
            }
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void debug(final String string) {
        if (Configurator.getDebug()) {
            System.out.println(string);
        }
    }
}
