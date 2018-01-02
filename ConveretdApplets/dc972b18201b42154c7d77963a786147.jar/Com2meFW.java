import java.util.Observable;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.CardLayout;
import java.util.Observer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Com2meFW extends Applet implements Observer
{
    private Com2meFWO frameStatus;
    private Com2meFWO gameStatus;
    private Com2meFWO loadStatus;
    private Com2meFWO originalStatus;
    private static final int GAME = 1;
    private static final int LOAD = 2;
    private static final int ORIGINAL = 3;
    private CardLayout cardLayout;
    private Com2meFWg1 gamePanel;
    private Com2meFWl1 loadPanel;
    private String baseUrl;
    private Image image;
    private Com2meFWPP[] pieces;
    private AudioClip sound1;
    private AudioClip sound2;
    private AudioClip sound3;
    private AudioClip sound4;
    private AudioClip sound5;
    private Image access_image;
    
    private void closeFrame() {
        this.loadPanel.stop();
        this.hide();
        this.frameStatus.setObject(null);
    }
    
    public String getAppletInfo() {
        return " * Version 1.0 *  (c) copyright by ICA (International Consultants Agency), Ltd. ... ";
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id != 201) {
            return false;
        }
        this.closeFrame();
        return true;
    }
    
    public Image http_access(final String s, final String s2) {
        try {
            return this.getImage(new URL(s), s2);
        }
        catch (MalformedURLException ex) {
            return null;
        }
        catch (Exception ex2) {
            return null;
        }
    }
    
    public void init() {
        final Image image = null;
        (this.frameStatus = new Com2meFWO()).addObserver(this);
        (this.gameStatus = new Com2meFWO()).addObserver(this);
        (this.loadStatus = new Com2meFWO()).addObserver(this);
        (this.originalStatus = new Com2meFWO()).addObserver(this);
        this.gamePanel = new Com2meFWg1(this.gameStatus, image);
        this.loadPanel = new Com2meFWl1(this.loadStatus);
        final int n = 37008;
        this.setBackground(new Color(0xFF & n >> 16, 0xFF & n >> 8, 0xFF & n));
        final int n2 = 15761552;
        this.gamePanel.setPictureground(new Color(0xFF & n2 >> 16, 0xFF & n2 >> 8, 0xFF & n2));
        this.setForeground(Color.black);
        this.setLayout(this.cardLayout = new CardLayout());
        this.add("game", this.gamePanel);
        this.add("load", this.loadPanel);
        this.baseUrl = this.getDocumentBase().toString();
        final String s = "M";
        final String s2 = "Y";
        final String s3 = "P";
        final String s4 = "I";
        final String s5 = "C";
        final String s6 = "j";
        final String s7 = "p";
        final String s8 = "g";
        final int n3 = 15;
        this.gamePanel.setImageName(String.valueOf(s) + s2 + s3 + s4 + s5 + "." + s6 + s7 + s8);
        this.gamePanel.setPieces(Integer.toString(n3));
        this.showCard(1);
        this.show();
        this.gamePanel.start();
    }
    
    private void showCard(final int n) {
        if (n == 1) {
            this.cardLayout.show(this, "game");
            return;
        }
        if (n == 2) {
            this.cardLayout.show(this, "load");
        }
    }
    
    public void update(final Observable observable, final Object o) {
        final String s = (String)o;
        if (observable != this.originalStatus) {
            if (observable == this.loadStatus) {
                if (s.equals("ok")) {
                    this.image = this.loadPanel.getImage();
                    this.pieces = this.loadPanel.getPieces();
                    this.showCard(1);
                    this.gamePanel.startNewGame(this.image, this.pieces);
                    return;
                }
                if (!s.equals("cancel") && !s.equals("error")) {}
            }
            else if (observable == this.gameStatus) {
                if (s.equals("quit")) {
                    this.closeFrame();
                    return;
                }
                if (s.equals("scramble")) {
                    this.showCard(2);
                    this.loadPanel.createPuzzle(this.http_access(this.baseUrl, this.gamePanel.getImageName()), this.gamePanel.getPieceNumber(), 10, 400, 300);
                }
            }
        }
        else if (s.equals("ok")) {
            this.showCard(1);
        }
    }
}
