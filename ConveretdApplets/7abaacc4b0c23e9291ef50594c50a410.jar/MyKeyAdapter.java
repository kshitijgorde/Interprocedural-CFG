import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class MyKeyAdapter extends KeyAdapter
{
    Geo geo;
    
    public MyKeyAdapter(final Geo geo) {
        this.geo = geo;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        this.geo.KeyPressed(keyEvent);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.geo.KeyReleased();
    }
}
