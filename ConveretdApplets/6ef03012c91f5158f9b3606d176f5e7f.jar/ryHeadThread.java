import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryHeadThread extends Thread
{
    private ryHeadliner \u014d;
    boolean \u014c;
    private String[] \u014b;
    private boolean \u014a;
    private boolean \u0149;
    private String \u0148;
    
    public ryHeadThread(final ryHeadliner \u014d) {
        this.\u014c = false;
        this.\u014a = false;
        this.\u0149 = false;
        this.\u014d = \u014d;
    }
    
    public void run() {
        while (true) {
            if (this.\u014d.\u0111 && new Long(new Date().getTime()) - this.\u014d.\u0110 > this.\u014d.\u010f * 60000) {
                this.\u014d.readFile();
            }
            for (int i = 0; i < this.\u014d.\u011d; ++i) {
                this.\u014d.\u0114 = "";
                this.\u014b = new String[this.\u014d.\u0123[i].length()];
                for (int j = 0; j < this.\u014b.length; ++j) {
                    this.\u014b[j] = this.\u014d.\u0123[i].substring(j, j + 1);
                }
                for (int k = 0; k < this.\u014b.length; ++k) {
                    this.\u0148 = this.\u014b[k];
                    this.\u014d.\u0114 += this.\u0148;
                    if (k == 0) {
                        this.\u0151(i);
                    }
                    if (!this.\u0148.equals(" ")) {
                        this.\u014d.repaint(this.\u014d.\u0119, 0, this.\u014d.\u010d, this.\u014d.\u0129);
                    }
                    if (!this.\u0148.equals(" ")) {
                        try {
                            Thread.sleep(this.\u014d.\u011a);
                        }
                        catch (InterruptedException ex) {}
                    }
                }
                this.\u014f(true);
            }
        }
    }
    
    protected void \u0151(final int \u0119) {
        this.\u014d.\u0118 = \u0119;
    }
    
    protected void \u0150(final boolean \u014d) {
        this.\u014c = \u014d;
        this.\u014e();
    }
    
    private void \u014f(final boolean \u0149) {
        this.\u0149 = \u0149;
        if (this.\u0149) {
            while (System.currentTimeMillis() + this.\u014d.\u011b > System.currentTimeMillis()) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
                if (this.\u014e()) {
                    break;
                }
            }
            this.\u0149 = false;
        }
    }
    
    private boolean \u014e() {
        if (this.\u0149 && !this.\u014c && this.\u014a) {
            this.\u014a = false;
            this.resume();
            return true;
        }
        if (this.\u0149 && this.\u014c && !this.\u014a) {
            this.\u014a = true;
            this.suspend();
            return true;
        }
        return false;
    }
    
    public void destroy() {
        System.gc();
    }
}
