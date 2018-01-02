// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.b;

public abstract class c extends b
{
    public void goto() {
        super.try = "stop";
    }
    
    public void new() {
        System.out.println("reset");
        this.for();
        this.a();
        this.int();
        this.repaint();
        this.try();
    }
    
    public void char() {
        (super.for = new Thread(this)).start();
        if (super.a.new.equalsIgnoreCase("false")) {
            super.try = "stop";
        }
    }
    
    public void for() {
        if (super.for != null && super.for.isAlive()) {
            super.for.stop();
            super.for = null;
        }
    }
    
    public void try() {
        System.out.println("play");
        super.for.resume();
        super.try = "play";
    }
    
    protected void int() {
        if (super.for == null) {
            (super.for = new Thread(this)).start();
        }
    }
    
    public void byte() {
        super.for.resume();
        super.try = "next";
    }
    
    public void case() {
        super.for.suspend();
        super.try = "pause";
    }
    
    public void else() {
        super.for.resume();
        super.try = "prev";
    }
}
