import netscape.javascript.JSObject;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.LayoutManager;
import java.awt.Font;
import java.net.URL;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GConsole extends Applet implements HotSpotListener, Loadable
{
    private HotSpot hsUp;
    private HotSpot hsDown;
    private HotSpot hsLogo;
    private HotSpot hsStart;
    private Cdescription cdes;
    private Cselection csel;
    private Vector games;
    private String js;
    private boolean registered;
    private RealMedia rm;
    private Graphics gbuf;
    private Image buf;
    private Dimension dim;
    private int gIndex;
    private String companyname;
    private boolean useWhite;
    private boolean ready;
    
    public GConsole() {
        this.gIndex = 0;
        this.companyname = "RealApplets";
        this.useWhite = false;
    }
    
    private void addComponents() {
        this.add(this.hsUp);
        this.add(this.hsDown);
        this.add(this.hsStart);
        this.add(this.cdes);
        this.add(this.csel);
        this.add(this.hsLogo);
        this.hsUp.repaint();
        this.hsDown.repaint();
        this.hsStart.repaint();
        this.hsLogo.repaint();
        this.cdes.repaint();
        this.csel.repaint();
        this.repaint();
    }
    
    public void destroy() {
        this.csel.quit();
        this.cdes.quit();
        try {
            this.csel = null;
            this.games = null;
            this.hsUp = null;
            this.hsDown = null;
            this.hsStart = null;
            this.cdes.quit();
            this.cdes = null;
        }
        catch (Exception ex) {}
    }
    
    private boolean doCheckRoutine() {
        try {
            new URL(String.valueOf(String.valueOf(this.getCodeBase())) + "license.ra").openStream();
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public Game gameAt(int n) {
        if (n > this.numberOfGames() - 1) {
            n = this.numberOfGames() - 1;
        }
        else if (n < 0) {
            n = 0;
        }
        return this.games.elementAt(n);
    }
    
    public int getFontWidth(final String s) {
        return this.getFontMetrics(new Font("Arial", 1, 16)).stringWidth(s);
    }
    
    public Game getSelectedGame() {
        return this.games.elementAt(this.gIndex);
    }
    
    public void giveData(final Vector games) {
        this.ready = true;
        this.games = games;
    }
    
    public void hotSpotEvent(final HotSpot hotSpot) {
        this.csel.wakeUp();
        if (hotSpot == this.hsUp) {
            this.csel.selectUp();
        }
        else if (hotSpot == this.hsDown) {
            this.csel.selectDown();
        }
        else if (hotSpot == this.hsStart) {
            this.popGame(this.getSelectedGame());
        }
        else if (hotSpot == this.hsLogo) {
            this.popRA();
        }
    }
    
    public void hotSpotOver(final HotSpot hotSpot) {
        this.csel.wakeUp();
        if (!this.registered && hotSpot == this.hsLogo) {
            this.showAd();
        }
    }
    
    public void init() {
        this.registered = false;
        if (this.registered && !this.doCheckRoutine()) {
            System.out.println("License is missing");
            return;
        }
        System.out.println("******************************");
        System.out.println("* RealApplets Game Console   *");
        if (this.registered) {
            System.out.println("* REGISTERED VERSION         *");
        }
        else {
            System.out.println("* UNREGISTERED VERSION       *");
        }
        System.out.println("* Release 1.2                *");
        System.out.println("******************************");
        this.setLayout(null);
        this.dim = this.getSize();
        this.buf = this.createImage(this.dim.width, this.dim.height);
        this.gbuf = this.buf.getGraphics();
        if (this.getParameter("useWhite").toUpperCase().equals("YES")) {
            this.useWhite = true;
        }
        final ReadUtil readUtil = new ReadUtil(this, this.getParameter("games_file"));
        (this.rm = new RealMedia(this)).add("logo1.jpg");
        this.rm.add("logo2.jpg");
        this.rm.add("gc_pijlup1.jpg");
        this.rm.add("gc_pijlup2.jpg");
        this.rm.add("gc_pijldown1.jpg");
        this.rm.add("gc_pijldown2.jpg");
        this.rm.add("button1.jpg");
        this.rm.add("button2.jpg");
        this.rm.add("gc_desc.jpg");
        this.rm.add("gc_sel.jpg");
        if (!this.useWhite) {
            this.rm.add("gc_ruw2.jpg");
        }
        else {
            this.rm.add("gc_ruw_white.jpg");
        }
        this.rm.load();
        readUtil.loadAll();
    }
    
    public AudioClip loadAudio(final String s) {
        return this.getAudioClip(this.getCodeBase(), s);
    }
    
    public Image loadImage(final String s) {
        return this.getImage(this.getCodeBase(), s);
    }
    
    public int numberOfGames() {
        if (this.registered) {
            return this.games.size();
        }
        if (this.games.size() > 10) {
            return 10;
        }
        return this.games.size();
    }
    
    public void paint(final Graphics graphics) {
        if (this.useWhite) {
            this.gbuf.setColor(Color.white);
        }
        else {
            this.gbuf.setColor(Color.black);
        }
        this.gbuf.fillRect(0, 0, this.dim.width, this.dim.height);
        this.gbuf.setFont(new Font("Arial", 1, 12));
        if (!this.rm.isLoaded() || !this.ready) {
            if (!this.useWhite) {
                this.gbuf.setColor(Color.white);
            }
            else {
                this.gbuf.setColor(Color.black);
            }
            this.gbuf.drawString("Loading file " + this.rm.getCurrent() + " of " + this.rm.getTotalFiles(), 20, 20);
            this.gbuf.drawRect(20, 60, 200, 20);
            this.gbuf.setColor(Color.gray);
            this.gbuf.fillRect(21, 61, 2 * this.rm.getPercent() - 2, 19);
            if (!this.useWhite) {
                this.gbuf.setColor(Color.white);
            }
            else {
                this.gbuf.setColor(Color.black);
            }
            this.gbuf.drawString(String.valueOf(this.rm.getPercent()) + " %", 70, 73);
            if (!this.ready) {
                this.gbuf.drawString("Reading games...", 20, 120);
            }
            this.gbuf.drawString("RealApplets Game Console", 20, 150);
            if (!this.registered) {
                this.gbuf.drawString("Limited Unregistered Version (V1.1)", 20, 170);
                this.gbuf.drawString("Register now at Http://www.realapplets.com", 20, 190);
                this.gbuf.drawString("Limited to 10 games, full version has 30 games!", 20, 210);
                this.gbuf.drawString("Graphics and design by Carl Nollet", 300, 50);
                this.gbuf.drawString("Programming by Bavo Bruylandt", 300, 70);
            }
            else {
                this.gbuf.drawString("Registered to: " + this.companyname, 20, 170);
                this.gbuf.drawString("DEMO of Registered Version (V1.1)", 20, 190);
                this.gbuf.setFont(new Font("Arial", 2, 12));
                this.gbuf.drawString("It is not allowed to link to this page", 20, 230);
            }
        }
        else if (this.rm.isLoaded() && this.ready) {
            if (this.useWhite) {
                this.gbuf.drawImage(this.rm.get("gc_ruw_white.jpg"), 0, 0, this);
            }
            else {
                this.gbuf.drawImage(this.rm.get("gc_ruw2.jpg"), 0, 0, this);
            }
        }
        graphics.drawImage(this.buf, 0, 0, this);
    }
    
    private void popGame(final Game game) {
        this.csel.rest();
        URL url = null;
        final String string = String.valueOf(String.valueOf(this.getCodeBase())) + "" + game.getDir() + "/" + game.getHTML();
        try {
            url = new URL(string);
            JSObject.getWindow((Applet)this).eval("window.open('" + string + "','Game','status=yes,width=" + game.getWidth() + ",height=" + game.getHeight() + ", toolbar=no, location=no,menubar=no,scrollbars=no,resize=yes,copyhistory=no')");
        }
        catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Does your applet tag include MAYSCRIPT?");
            System.out.println("eg. <APPLET CODE=Console.class  archive=Console.jar WIDTH=550 HEIGHT=335 MAYSCRIPT>");
            this.getAppletContext().showDocument(url, "Game");
        }
    }
    
    private void popRA() {
        if (!this.registered) {
            try {
                this.getAppletContext().showDocument(new URL("http://www.realapplets.com"), "_blank");
            }
            catch (Exception ex) {}
        }
    }
    
    public void setSelectedGame(final int gIndex) {
        this.gIndex = gIndex;
    }
    
    public void showAd() {
        this.cdes.showAd();
    }
    
    public void startUp() {
        while (!this.ready) {
            try {
                Thread.sleep(500L);
            }
            catch (Exception ex) {}
        }
        this.hsUp = new HotSpot(this);
        this.hsDown = new HotSpot(this);
        this.hsLogo = new HotSpot(this);
        this.hsStart = new HotSpot(this);
        this.cdes = new Cdescription(this, 164, 228);
        this.csel = new Cselection(this, 147, 71);
        this.hsUp.setBounds(316, 126, 46, 27);
        this.hsDown.setBounds(316, 235, 46, 27);
        this.hsStart.setBounds(408, 277, 68, 40);
        this.hsLogo.setBounds(203, 273, 87, 47);
        this.csel.setBounds(267, 159, 147, 71);
        this.cdes.setBounds(10, 92, 164, 228);
        this.hsUp.setImage(this.rm.get("gc_pijlup1.jpg"), 1);
        this.hsUp.setImage(this.rm.get("gc_pijlup2.jpg"), 2);
        this.hsUp.setImage(this.rm.get("gc_pijlup2.jpg"), 3);
        this.hsDown.setImage(this.rm.get("gc_pijldown1.jpg"), 1);
        this.hsDown.setImage(this.rm.get("gc_pijldown2.jpg"), 2);
        this.hsDown.setImage(this.rm.get("gc_pijldown2.jpg"), 3);
        this.hsStart.setImage(this.rm.get("button1.jpg"), 1);
        this.hsStart.setImage(this.rm.get("button2.jpg"), 2);
        this.hsStart.setImage(this.rm.get("button2.jpg"), 3);
        this.hsLogo.setImage(this.rm.get("logo1.jpg"), 1);
        this.hsLogo.setImage(this.rm.get("logo2.jpg"), 2);
        this.hsLogo.setImage(this.rm.get("logo2.jpg"), 3);
        this.hsLogo.setMouseOverAction(true);
        this.cdes.setImage(this.rm.get("gc_desc.jpg"));
        this.csel.setImage(this.rm.get("gc_sel.jpg"));
        this.addComponents();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void updateDescription() {
        this.cdes.showDescription();
    }
}
