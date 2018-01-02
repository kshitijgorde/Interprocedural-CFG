// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.IOException;

public class mar extends mac implements Runnable
{
    private mc a;
    
    public mar(final mc a) {
        this.a = a;
    }
    
    private void a(final mn mn) throws InterruptedException {
        final long b = mn.b();
        if (b > 0L) {
            Thread.sleep(b);
        }
    }
    
    private void a(final Object[] array) throws IOException, InterruptedException {
        for (int i = 0; i < array.length; ++i) {
            final mn mn = (mn)array[i];
            this.a.b(mn);
            this.a(mn);
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
            mc.a("Error sending to " + this.a.b() + ".", t);
            n = 500;
        }
        finally {
            this.a.c(n);
        }
    }
}
