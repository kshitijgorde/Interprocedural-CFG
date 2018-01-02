// 
// Decompiled by Procyon v0.5.30
// 

package biz.ddcr.overfitted;

import org.apache.commons.logging.LogFactory;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.io.File;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.Icon;
import javax.imageio.ImageIO;
import java.awt.Cursor;
import javazoom.jlgui.basicplayer.AppletMpegSPIWorkaround;
import javazoom.jlgui.basicplayer.BasicPlayerApplet;
import javazoom.jlgui.basicplayer.BasicController;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import org.apache.commons.logging.Log;
import java.awt.event.MouseListener;
import javax.swing.JApplet;

public class ShoutcastPlayer extends JApplet implements MouseListener
{
    private static Log log;
    private ImageIcon playImage;
    private ImageIcon stopImage;
    private ImageIcon playOverImage;
    private ImageIcon stopOverImage;
    private JLabel fakeButton;
    private Color backgroundColour;
    private BasicController theSoundPlayer;
    private M3UPlaylist thePlaylist;
    boolean isPlaying;
    
    public ShoutcastPlayer() {
        this.theSoundPlayer = null;
        this.thePlaylist = null;
    }
    
    public void init() {
        this.isPlaying = false;
        try {
            this.theSoundPlayer = (BasicController)new BasicPlayerApplet();
            AppletMpegSPIWorkaround.useragent = "JavaApplet JL/1.0.1 MP3SPI/1.9.4 BasicPlayer/3.0";
            String currentPlaylist = this.getParameter("playlist");
            if (currentPlaylist == null || currentPlaylist.isEmpty()) {
                currentPlaylist = "http://overfitted.ddcr.biz/radio.php?.pls";
            }
            final String currentPlaylistCharset = this.getParameter("charset");
            if (currentPlaylistCharset == null || currentPlaylistCharset.isEmpty()) {
                this.thePlaylist = new M3UPlaylist(currentPlaylist);
            }
            else {
                this.thePlaylist = new M3UPlaylist(currentPlaylist, currentPlaylistCharset);
            }
            this.setCursor(Cursor.getPredefinedCursor(12));
            final ClassLoader cl = ImageScaling.class.getClassLoader();
            final ImageScaling oPlayImage = new ImageScaling(ImageIO.read(cl.getResourceAsStream("resources/play.gif")));
            final ImageScaling oStopImage = new ImageScaling(ImageIO.read(cl.getResourceAsStream("resources/stop.gif")));
            final ImageScaling oPlayOverImage = new ImageScaling(ImageIO.read(cl.getResourceAsStream("resources/play_over.gif")));
            final ImageScaling oStopOverImage = new ImageScaling(ImageIO.read(cl.getResourceAsStream("resources/stop_over.gif")));
            this.playImage = oPlayImage.getScaledImageIcon(this.getWidth(), this.getHeight());
            this.stopImage = oStopImage.getScaledImageIcon(this.getWidth(), this.getHeight());
            this.playOverImage = oPlayOverImage.getScaledImageIcon(this.getWidth(), this.getHeight());
            this.stopOverImage = oStopOverImage.getScaledImageIcon(this.getWidth(), this.getHeight());
            (this.fakeButton = new JLabel()).setOpaque(false);
            final String currentBackgroundColour = this.getParameter("color");
            this.backgroundColour = null;
            if (currentBackgroundColour != null && !currentBackgroundColour.isEmpty() && (this.backgroundColour = HTMLColour.decode(currentBackgroundColour)) != null) {
                this.fakeButton.setBackground(this.backgroundColour);
                this.setBackground(this.backgroundColour);
            }
            this.fakeButton.setIcon(this.isPlaying ? this.stopImage : this.playImage);
            this.add(this.fakeButton);
            this.addMouseListener(this);
        }
        catch (Exception e) {
            ShoutcastPlayer.log.error((Object)"Initialization failed", (Throwable)e);
        }
    }
    
    public void mouseEntered(final MouseEvent e) {
        this.fakeButton.setIcon(this.isPlaying ? this.stopOverImage : this.playOverImage);
        this.repaint();
        e.consume();
    }
    
    public void mouseExited(final MouseEvent e) {
        this.fakeButton.setIcon(this.isPlaying ? this.stopImage : this.playImage);
        this.repaint();
        e.consume();
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (this.isPlaying) {
            if (!this.stopStream()) {
                return;
            }
        }
        else if (!this.playStream()) {
            return;
        }
        this.fakeButton.setIcon(this.isPlaying ? this.stopOverImage : this.playOverImage);
        this.repaint();
        e.consume();
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public boolean playStream() {
        boolean result = false;
        String currentFile = null;
        while ((currentFile = this.thePlaylist.getNextItem()) != null) {
            try {
                try {
                    this.theSoundPlayer.open(new URL(currentFile));
                }
                catch (MalformedURLException e2) {
                    this.theSoundPlayer.open(new File(currentFile));
                }
                this.theSoundPlayer.play();
                result = true;
            }
            catch (Exception e) {
                ShoutcastPlayer.log.error((Object)("Cannot read file : " + currentFile), (Throwable)e);
            }
        }
        return this.isPlaying = result;
    }
    
    public boolean stopStream() {
        boolean result = false;
        try {
            this.theSoundPlayer.stop();
            this.thePlaylist.rewind();
            result = true;
        }
        catch (Exception e) {
            ShoutcastPlayer.log.error((Object)"Stop failed", (Throwable)e);
        }
        this.isPlaying = !result;
        return result;
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        if (this.backgroundColour != null) {
            g.setColor(this.backgroundColour);
        }
    }
    
    static {
        ShoutcastPlayer.log = LogFactory.getLog((Class)ShoutcastPlayer.class);
    }
}
