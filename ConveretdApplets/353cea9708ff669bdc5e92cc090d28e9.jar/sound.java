import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.Color;
import java.awt.Button;
import java.awt.Image;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class sound extends Applet
{
    AudioClip eagle;
    Image bild;
    Button button;
    
    public void init() {
        this.setBackground(Color.white);
        this.bild = this.getImage(this.getDocumentBase(), "eagleApplet/eagle.gif");
        this.eagle = this.getAudioClip(this.getDocumentBase(), "eagleApplet/eagle.au");
        this.add(this.button = new Button("play sound"));
        this.repaint();
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target == this.button) {
            this.eagle.play();
            this.repaint();
        }
        return true;
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.bild, 7, 35, this);
        g.drawString("Houston, . . .", 20, 230);
        g.drawString("Tranquility Base here.", 20, 245);
        g.drawString("The Eagle has landed.", 20, 260);
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
    }
}
