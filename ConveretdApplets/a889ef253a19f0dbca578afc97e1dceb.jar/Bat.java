import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

final class Bat implements Runnable
{
    Thread me;
    Applet parent;
    int x;
    int y;
    int pic;
    private boolean OK;
    
    public Bat(final Applet parent) {
        this.parent = parent;
        this.x = 190;
        this.y = 190;
        this.OK = false;
        this.pic = 0;
    }
    
    public final void setCoords(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.parent.repaint();
    }
    
    public final int getX() {
        return this.x;
    }
    
    public final int getY() {
        return this.y;
    }
    
    public final int getPic() {
        return this.pic;
    }
    
    public final void start() {
        if (this.me == null) {
            (this.me = new Thread(this)).start();
        }
    }
    
    public final void run() {
        try {
            while (this.me != null) {
                while (!this.OK) {
                    Thread.sleep(100L);
                }
                this.pic = 1;
                while (this.pic < 4) {
                    this.parent.repaint();
                    Thread.sleep(50L);
                    ++this.pic;
                }
                this.pic = 0;
                this.parent.repaint();
                this.OK = false;
            }
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final void go() {
        this.OK = true;
    }
    
    public final void stop() {
        this.me = null;
    }
}
