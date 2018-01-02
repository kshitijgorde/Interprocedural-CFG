import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.FontMetrics;
import java.util.Enumeration;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NewsPro extends Applet implements MouseListener, MouseMotionListener, HotSpotListener, Runnable
{
    private HotSpot hs1;
    private HotSpot hs2;
    private HotSpot hs3;
    private HotSpot hs4;
    private Word tmpWord;
    private RAMLReader raml;
    protected Skin skin;
    private Color bgcolor;
    private Thread newsThread;
    private boolean paused;
    public Dimension dim;
    private Vector groups;
    private Group t;
    private String target;
    private int curG;
    private int mCount;
    boolean running;
    private int rank;
    private int page;
    private int id;
    boolean loaded;
    private Graphics gbuf;
    private Image buf;
    private Image[][] buttons;
    boolean delay;
    boolean notChecking;
    private int delayTime;
    private Image[] icons;
    
    public NewsPro() {
        this.paused = false;
        this.running = true;
        this.loaded = false;
        this.buttons = new Image[4][3];
        this.delay = false;
        this.notChecking = true;
        this.delayTime = 2000;
        this.icons = new Image[40];
    }
    
    private Word checkForAction(final int n, final int n2) {
        this.notChecking = false;
        final Enumeration<TextBlock> elements = this.t.blockVector.elements();
        while (elements.hasMoreElements()) {
            final TextBlock textBlock = elements.nextElement();
            if (this.page + 1 == textBlock.getPage() && textBlock.containsLink() && textBlock.isOnLink(n, n2)) {
                final Word actionItem = textBlock.getActionItem();
                actionItem.setActiveLink(true);
                this.setCursor(new Cursor(12));
                this.repaint();
                this.notChecking = true;
                return actionItem;
            }
        }
        this.notChecking = true;
        if (this.tmpWord != null) {
            this.tmpWord.setActiveLink(false);
            this.setCursor(new Cursor(0));
            this.repaint();
        }
        return null;
    }
    
    public void destroy() {
        this.running = false;
    }
    
    public int getAppletWidth() {
        return this.dim.width;
    }
    
    public int getFontDescent(final String s) {
        return this.getFontMetrics(this.skin.getFont("textfont")).getMaxDescent();
    }
    
    public int getFontHeight(final String s) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.skin.getFont(s));
        return fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
    }
    
    public int getFontWidth(final String s) {
        return this.getFontMetrics(this.skin.getFont("textfont")).stringWidth(s);
    }
    
    public int getGroupWidth(final String s) {
        return this.getFontMetrics(this.skin.getFont("groupfont")).stringWidth(s);
    }
    
    public Image getIcon(final String s) {
        return this.icons[Integer.parseInt(s.substring(5, s.length()).trim()) - 1];
    }
    
    public int getMaxHeight() {
        return this.dim.height - this.dim.width / 5;
    }
    
    public int getMaxWidth() {
        return this.dim.width - this.dim.width / 5;
    }
    
    public void hotSpotEvent(final HotSpot hotSpot) {
        this.delay = true;
        if (hotSpot == this.hs1) {
            this.nextItem(true);
        }
        else if (hotSpot == this.hs2) {
            this.nextItem(false);
        }
        else if (hotSpot == this.hs3) {
            this.remove(this.hs3);
            this.add(this.hs4);
            this.paused = true;
        }
        else if (hotSpot == this.hs4) {
            this.remove(this.hs4);
            this.add(this.hs3);
            this.paused = false;
        }
    }
    
    public void init() {
        this.setLayout(null);
        this.dim = this.getSize();
        this.skin = new Skin(this);
        this.raml = new RAMLReader(this, this.getParameter("Newsfile"));
        this.delayTime = Integer.parseInt(this.getParameter("delay"));
        this.bgcolor = new Color(Integer.parseInt(this.getParameter("bgcolor").substring(0, 3)), Integer.parseInt(this.getParameter("bgcolor").substring(4, 7)), Integer.parseInt(this.getParameter("bgcolor").substring(8, 11)));
        (this.newsThread = new Thread(this)).start();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    private void loadAll() {
        this.skin.load();
        this.loadButtons();
        this.loadIcons();
        this.groups = this.raml.getAllData();
        (this.hs1 = new HotSpot(this)).setBounds(this.dim.width / 2 + 10, this.skin.getOffset("btOffset"), 25, 15);
        this.hs1.setImage(this.buttons[0][0], 1);
        this.hs1.setImage(this.buttons[0][1], 2);
        this.hs1.setImage(this.buttons[0][2], 3);
        this.add(this.hs1);
        (this.hs2 = new HotSpot(this)).setBounds(this.dim.width / 2 - 37, this.skin.getOffset("btOffset"), 25, 15);
        this.hs2.setImage(this.buttons[1][0], 1);
        this.hs2.setImage(this.buttons[1][1], 2);
        this.hs2.setImage(this.buttons[1][2], 3);
        this.add(this.hs2);
        (this.hs3 = new HotSpot(this)).setBounds(this.dim.width / 2 - 14, this.skin.getOffset("btOffset"), 25, 15);
        this.hs3.setImage(this.buttons[2][0], 1);
        this.hs3.setImage(this.buttons[2][1], 2);
        this.hs3.setImage(this.buttons[2][2], 3);
        this.add(this.hs3);
        (this.hs4 = new HotSpot(this)).setBounds(this.dim.width / 2 - 14, this.skin.getOffset("btOffset"), 25, 15);
        this.hs4.setImage(this.buttons[3][0], 1);
        this.hs4.setImage(this.buttons[3][1], 2);
        this.hs4.setImage(this.buttons[3][2], 3);
        this.buf = this.createImage(this.dim.width, this.dim.height);
        this.gbuf = this.buf.getGraphics();
    }
    
    private void loadButtons() {
        final Graphics[][] array = new Graphics[4][3];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.buttons[i][j] = this.createImage(25, 15);
                (array[i][j] = this.buttons[i][j].getGraphics()).drawImage(this.skin.getBgimage(), this.skin.btxco[i][j][0], this.skin.btyco[i][j][0], this.skin.btxco[i][j][1], this.skin.btyco[i][j][1], this.skin.btxco[i][j][2], this.skin.btyco[i][j][2], this.skin.btxco[i][j][3], this.skin.btyco[i][j][3], this);
            }
        }
    }
    
    private void loadIcons() {
        int n = -10;
        int n2 = 0;
        final Graphics[] array = new Graphics[40];
        for (int i = 0; i < 40; ++i) {
            if (i % 5 == 0) {
                n += 10;
                n2 = 0;
            }
            this.icons[i] = this.createImage(10, 10);
            (array[i] = this.icons[i].getGraphics()).setColor(this.skin.getColor("bgcolor"));
            array[i].fillRect(0, 0, 20, 20);
            array[i].drawImage(this.skin.getIconImage(), 0, 0, 10, 10, n2, n, n2 + 10, n + 10, this);
            n2 += 10;
        }
    }
    
    public Image loadMedia(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), s);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
        return image;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        ++this.mCount;
        if (this.mCount % 2 == 0 && this.notChecking) {
            this.tmpWord = this.checkForAction(x, y);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.tmpWord = this.checkForAction(mouseEvent.getX(), mouseEvent.getY());
        if (this.tmpWord != null) {
            this.openLink(this.tmpWord.getLink(), this.tmpWord.getTarget());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void nextItem(final boolean b) {
        if (this.tmpWord != null) {
            this.tmpWord.setActiveLink(false);
        }
        this.setCursor(new Cursor(0));
        this.t = this.groups.elementAt(this.curG);
        if (b) {
            ++this.rank;
            ++this.id;
            if (!this.t.hasMoreOnPage(this.id, this.rank)) {
                ++this.page;
                this.rank = 1;
                if (!this.t.hasMorePages(this.page)) {
                    ++this.curG;
                    this.page = 0;
                    this.id = 1;
                    if (this.groups.size() <= this.curG) {
                        this.curG = 0;
                    }
                    this.t = this.groups.elementAt(this.curG);
                }
            }
        }
        else {
            this.rank = 1;
            this.page = 0;
            if (this.id == 1) {
                if (this.curG > 0) {
                    --this.curG;
                }
                else {
                    this.curG = this.groups.size() - 1;
                }
            }
            else {
                this.id = 1;
            }
            this.t = this.groups.elementAt(this.curG);
        }
        this.repaint();
    }
    
    public void openLink(String string, final String s) {
        if (string.indexOf("http://") == -1) {
            string = String.valueOf(String.valueOf(this.getCodeBase())) + string;
        }
        try {
            final URL url = new URL(string);
            System.out.println("Linking" + url);
            this.getAppletContext().showDocument(url, s);
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.gbuf != null && this.t != null) {
            this.gbuf.setColor(this.bgcolor);
            this.gbuf.fillRect(0, 0, this.dim.width, this.dim.height);
            this.gbuf.drawImage(this.skin.getBgimage(), 0, 0, this.dim.width, this.dim.height, this.skin.bgxco[2], this.skin.bgyco[2], this.skin.bgxco[3], this.skin.bgyco[3], this);
            this.t.drawGroup(this.rank, this.page, this.gbuf, this, this.tmpWord);
            graphics.drawImage(this.buf, 0, 0, this);
        }
        else {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.dim.width, this.dim.height);
            graphics.setColor(Color.blue);
            graphics.drawString("Loading News...", 20, 40);
        }
    }
    
    public void run() {
        if (!this.loaded) {
            this.repaint();
            this.loadAll();
            this.loaded = true;
        }
        this.repaint();
        this.hs1.repaint();
        this.hs2.repaint();
        this.hs3.repaint();
        while (this.running) {
            if (this.delay) {
                try {
                    Thread.sleep(2000L);
                    this.delay = false;
                }
                catch (Exception ex) {}
            }
            if (!this.paused) {
                this.nextItem(true);
            }
            try {
                Thread.sleep(this.delayTime);
            }
            catch (Exception ex2) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
