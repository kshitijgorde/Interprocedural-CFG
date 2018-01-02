// 
// Decompiled by Procyon v0.5.30
// 

package y.cnt;

import java.io.IOException;

class 8 extends Thread
{
    15 a;
    
    8(final 15 a) {
        this.a = a;
        this.start();
    }
    
    public void run() {
        try {
            while (true) {
                this.a.c();
            }
        }
        catch (IOException ex) {}
    }
}
