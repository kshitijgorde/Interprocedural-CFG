// 
// Decompiled by Procyon v0.5.30
// 

package org.chaingang.game.imageshuffle;

import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.applet.Applet;
import org.chaingang.common.AboutBox;
import org.chaingang.common.AppProperties;
import java.awt.MediaTracker;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

public class Main extends JApplet implements ActionListener
{
    private MediaTracker mt;
    Puzzle canvas;
    PopUp popup;
    AppProperties ap;
    public boolean isAppletContext;
    public String puzzleStatus;
    AboutBox aboutbox;
    
    public Main() {
        this.isAppletContext = true;
        this.puzzleStatus = "c_win";
    }
    
    public void init() {
        this.aboutbox = new AboutBox("Image Puzzle", "Copyleft (c) 2002 brett@chaingang.org", "http://www.chaingang.org/code/game/", this.isAppletContext ? this : null);
        this.ap = new AppProperties();
        this.mt = new MediaTracker(this);
        this.loadParms();
        this.canvas = new Puzzle(this, this.getImageUrl(), (int)this.ap.get("cols"), (int)this.ap.get("rows"), (Color)this.ap.get("color.empty"), (Color)this.ap.get("color.puzzle"));
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        panel.setBackground((Color)this.ap.get("color.background"));
        panel.setLayout(new BoxLayout(panel, 0));
        panel.add(Box.createHorizontalGlue());
        panel.add(this.canvas);
        panel.add(Box.createHorizontalGlue());
        contentPane.add(panel, "Center");
        this.popup = new PopUp(this);
        this.canvas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    Main.this.popup.fireFirst();
                }
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    Main.this.popup.setMoves(Main.this.canvas.getMoveCount());
                    Main.this.popup.show(mouseEvent);
                }
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    Main.this.popup.setMoves(Main.this.canvas.getMoveCount());
                    Main.this.popup.show(mouseEvent);
                }
            }
        });
        this.validate();
    }
    
    private Image getImageUrl() {
        final URL url = (URL)this.ap.get("image.url");
        Image image;
        if (this.ap.isDefUrl(url)) {
            image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource((String)this.ap.get("image.resource")));
        }
        else {
            image = this.getImage(url);
        }
        this.mt.addImage(image, 0);
        try {
            this.mt.waitForID(0);
        }
        catch (InterruptedException ex) {
            System.err.println(ex);
            System.exit(1);
        }
        return image;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        boolean b = true;
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("c_play")) {
            this.popup.setWin(false);
            this.canvas.cmdShuffle();
        }
        else if (actionCommand.equals("c_peek")) {
            this.canvas.setPlayState(false);
        }
        else if (actionCommand.equals("c_unpeek")) {
            this.canvas.setPlayState(true);
        }
        else if (actionCommand.equals("c_win")) {
            this.canvas.setPlayState(false);
            this.popup.setWin(true);
        }
        else if (actionCommand.equals("c_about")) {
            this.aboutbox.show();
        }
        else {
            b = false;
        }
        if (b) {
            this.puzzleStatus = actionCommand;
            this.popup.setStatus(this.puzzleStatus);
        }
    }
    
    public String getAppletInfo() {
        return "Image Shuffle Game";
    }
    
    public String[][] getParameterInfo() {
        return this.ap.getParameterInfo();
    }
    
    private void loadParms() {
        this.ap.add("cols", new Integer(4), "columns in puzzle");
        this.ap.add("rows", new Integer(4), "rows in puzzle");
        this.ap.add("image.url", this.ap.getDefUrl(), "url to image for puzzle");
        this.ap.add("image.resource", new String("default.jpg"), "class resource name for image for puzzle");
        this.ap.add("color.empty", Color.black, "color for empty piece");
        this.ap.add("color.puzzle", Color.white, "color for puzzle background");
        this.ap.add("color.background", Color.white, "color for app background");
        try {
            this.ap.loadProperties(this.getClass().getResourceAsStream("ImageShuffleDefault.properties"));
        }
        catch (IOException ex) {}
        if (this.isAppletContext) {
            this.ap.loadParameters(this);
        }
        else {
            try {
                this.ap.loadProperties(this.getClass().getResourceAsStream("ImageShuffle.properties"));
            }
            catch (IOException ex2) {}
        }
    }
    
    public static void main(final String[] array) throws Exception {
        final JFrame frame = new JFrame("ImageShuffle");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        final Main main = new Main();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        main.isAppletContext = false;
        frame.getContentPane().add("Center", main);
        main.init();
        frame.pack();
        final Dimension dimension = new Dimension(main.getSize().width + 20, main.getSize().height + 50);
        frame.setSize(dimension.width, dimension.height);
        frame.setLocation(screenSize.width / 2 - dimension.width / 2, screenSize.height / 2 - dimension.height / 2);
        frame.setIconImage(new ImageIcon(main.getClass().getResource("icon.gif")).getImage());
        frame.show();
    }
}
