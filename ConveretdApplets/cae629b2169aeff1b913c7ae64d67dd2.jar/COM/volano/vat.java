// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.IOException;

public class vat extends var implements Runnable
{
    private vc a;
    
    public vat(final vc a) {
        this.a = a;
    }
    
    private void a(final vq vq) throws InterruptedException {
        final long b = vq.b();
        if (b > 0L) {
            Thread.sleep(b);
        }
    }
    
    private void a(final Object[] array) throws IOException, InterruptedException {
        for (int i = 0; i < array.length; ++i) {
            final vq vq = (vq)array[i];
            this.a.b(vq);
            this.a(vq);
        }
    }
    
    public void run() {
        int n = 200;
        try {
            while (super.a) {
                this.a(this.b());
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
            vc.a("Error sending to " + this.a.b() + ".", t);
            n = 500;
        }
        finally {
            this.a.c(n);
        }
    }
}
