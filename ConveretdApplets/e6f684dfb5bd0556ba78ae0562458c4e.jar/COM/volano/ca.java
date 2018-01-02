// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.IOException;

public class ca extends cf implements Runnable
{
    private by uc;
    
    ca(final by uc) {
        this.uc = uc;
    }
    
    private void jd(final Object[] array) throws IOException {
        for (int i = 0; i < array.length; ++i) {
            this.uc.xj((cb)array[i]);
        }
    }
    
    public void run() {
        int n = 200;
        try {
            while (this.zb()) {
                this.jd(this.hd());
                Thread.yield();
            }
        }
        catch (InterruptedException ex) {}
        catch (IOException ex2) {}
        catch (ThreadDeath threadDeath) {
            n = 503;
            throw threadDeath;
        }
        catch (Throwable t) {
            by.hh("Error sending to " + this.uc.oj() + ".", t);
            n = 500;
        }
        finally {
            this.uc.c(n);
        }
    }
}
