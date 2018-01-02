// 
// Decompiled by Procyon v0.5.30
// 

class SleepThread extends Thread
{
    Food f;
    
    public void run() {
        try {
            Thread.sleep(20000L);
        }
        catch (Exception ex) {
            System.out.println("Caught red handed! Sleeping!");
        }
        this.f.eaten = false;
        if (!Pacman.ended && Pacman.level == this.f.level) {
            this.f.draw(Pacman.gfx);
        }
    }
    
    public void init(final Food f) {
        this.f = f;
    }
}
