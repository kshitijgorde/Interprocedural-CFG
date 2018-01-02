// 
// Decompiled by Procyon v0.5.30
// 

class g extends Thread
{
    l p;
    boolean p;
    
    g(final l p) {
        this.p = false;
        this.p = p;
        this.start();
    }
    
    public final void run() {
        try {
            while (!this.p) {
                final String line = this.p.p.readLine();
                if (line == null) {
                    this.p.d = false;
                    return;
                }
                this.p.n(du.d(line));
            }
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
