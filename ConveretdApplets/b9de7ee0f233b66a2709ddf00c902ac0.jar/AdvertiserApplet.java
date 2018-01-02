import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AdvertiserApplet extends Applet implements Runnable, MouseListener
{
    private int antallBilder;
    private int pause;
    private int bildeNr;
    private String paramNavn;
    private String bildeNavn;
    private Image[] bildeTabell;
    private String[] menyTekst;
    private String[] tabellURL;
    private String author;
    private Thread advertiser;
    private PopupMenu popUp;
    private MenuItem link;
    
    public void init() {
        this.author = this.getParameter("AUTHOR");
        this.pause = Integer.parseInt(this.getParameter("DELAY")) * 1000;
        this.antallBilder = Integer.parseInt(this.getParameter("NUMBEROFADS"));
        this.bildeTabell = new Image[this.antallBilder];
        this.menyTekst = new String[this.antallBilder];
        this.tabellURL = new String[this.antallBilder];
        this.popUp = new PopupMenu();
        for (int i = 1; i <= this.antallBilder; ++i) {
            this.paramNavn = "IMG" + i;
            this.bildeNavn = this.getParameter(this.paramNavn);
            this.bildeTabell[i - 1] = this.getImage(this.getDocumentBase(), this.bildeNavn);
            this.paramNavn = "TXT" + i;
            this.menyTekst[i - 1] = this.getParameter(this.paramNavn);
            this.paramNavn = "URL" + i;
            this.tabellURL[i - 1] = this.getParameter(this.paramNavn);
        }
        for (int j = 0; j < this.antallBilder; ++j) {
            (this.link = new MenuItem(this.menyTekst[j])).setActionCommand(this.tabellURL[j]);
            this.popUp.add(this.link);
        }
        this.add(this.popUp);
        this.popUp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                AdvertiserApplet.this.tilSide(actionEvent.getActionCommand());
            }
        });
        this.addMouseListener(this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.author.equals("Sondre Skaug Bjoernebekk, sondre@mobilpost.com")) {
            if (this.bildeNr >= this.antallBilder) {
                this.bildeNr = 0;
            }
            graphics.drawImage(this.bildeTabell[this.bildeNr], 0, 0, this);
            return;
        }
        graphics.drawString("Wrong author specified in the HTML-code, contact sondre@mobilpost.com for more info.", 10, 10);
    }
    
    public void start() {
        if (this.advertiser == null) {
            (this.advertiser = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.advertiser != null) {
            this.advertiser.stop();
            this.advertiser = null;
        }
    }
    
    public void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            this.stop();
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            this.pause(this.pause);
            ++this.bildeNr;
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.popUp.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void tilSide(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(s));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public AdvertiserApplet() {
        this.pause = 5000;
    }
}
