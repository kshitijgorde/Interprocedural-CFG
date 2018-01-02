// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

class gp implements Runnable
{
    public void run() {
        try {
            boolean b = true;
            if (d.mw != 0 && System.currentTimeMillis() - d.mw < 3000L) {
                b = false;
            }
            if (b) {
                d.mw = System.currentTimeMillis();
                System.gc();
            }
        }
        finally {
            d.mv = null;
        }
    }
}
