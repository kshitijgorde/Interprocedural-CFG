import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Wastem extends Applet implements ActionListener
{
    Image bird;
    Image logo;
    AudioClip Shot_AC;
    AudioClip Hit_AC;
    AudioClip Empty_AC;
    
    public void init() {
        this.setBackground(Color.black);
        this.setLayout(null);
        this.Shot_AC = this.getAudioClip(this.getCodeBase(), "shot.au");
        this.Hit_AC = this.getAudioClip(this.getCodeBase(), "crash.au");
        this.Empty_AC = this.getAudioClip(this.getCodeBase(), "empty.au");
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.bird = this.getImage(this.getCodeBase(), "ball.gif");
        final Image image = this.getImage(this.getCodeBase(), "bang.gif");
        final Image image2 = this.getImage(this.getCodeBase(), "blast.gif");
        mediaTracker.addImage(image, 0);
        mediaTracker.addImage(this.bird, 1);
        mediaTracker.addImage(image2, 2);
        try {
            mediaTracker.waitForID(0);
            mediaTracker.waitForID(1);
            mediaTracker.waitForID(2);
        }
        catch (Exception ex) {
            System.out.println("Error in waiting for images: " + ex);
        }
        final ShootRange shootRange = new ShootRange(this.bird, image, image2, this.Shot_AC, this.Hit_AC, this.Empty_AC, 350, 100);
        shootRange.setBounds(0, 0, 350, 100);
        this.add(shootRange);
        this.logo = this.getImage(this.getCodeBase(), "ScufoLo.gif");
        final ImageCanvas imageCanvas = new ImageCanvas(this.logo, 94, 52, "http://www.esearch.com.au/scufo");
        imageCanvas.addActionListener(this);
        imageCanvas.setBounds(360, 24, 100, 52);
        this.add(imageCanvas);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.gotourl(new String(actionEvent.getActionCommand()));
    }
    
    void gotourl(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(s), "_top");
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}
