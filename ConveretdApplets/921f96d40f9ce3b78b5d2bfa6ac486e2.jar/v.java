import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class v extends Thread
{
    boolean p;
    f p;
    dx p;
    OutputStream p;
    
    public v(final f p3, final dx p4, final OutputStream p5) {
        this.p = p3;
        this.p = p4;
        this.p = p5;
        this.start();
    }
    
    final void p() {
        this.p = true;
    }
    
    public final void run() {
        int n = 0;
        long currentTimeMillis = System.currentTimeMillis();
        while (!this.p && this.p.p()) {
            try {
                du.p(100);
                ++n;
                if (!this.p.d) {
                    continue;
                }
                if (this.p.p <= this.p.d && this.p.p + 10000L < System.currentTimeMillis()) {
                    this.p.p = System.currentTimeMillis();
                    currentTimeMillis = this.p.p + 3000L;
                    this.p.d("PING");
                }
                else if (this.p.p > this.p.d) {
                    final long n2 = (System.currentTimeMillis() - this.p.p) / 1000L;
                    if (n2 > 120L) {
                        this.p.n("ERROR your network lag too much");
                        this.p.d = false;
                        try {
                            this.p.p.close();
                        }
                        catch (Exception ex2) {}
                    }
                    else {
                        if (currentTimeMillis < System.currentTimeMillis()) {
                            currentTimeMillis += 3000L;
                            this.p.d("PING");
                        }
                        this.p.p.p(n2);
                    }
                }
                while (this.p.p() > 0) {
                    this.p.write((String.valueOf(du.p((String)this.p.p())) + "\n").getBytes());
                    this.p.flush();
                }
            }
            catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
