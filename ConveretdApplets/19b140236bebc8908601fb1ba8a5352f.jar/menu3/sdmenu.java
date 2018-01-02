// 
// Decompiled by Procyon v0.5.30
// 

package menu3;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

public class sdmenu extends Applet
{
    boolean isStandalone;
    MenuItem[] arrayMenu;
    int startX;
    int startY;
    int howMany;
    Color color1;
    Color color2;
    int activeItem;
    int depth;
    int itemDepth;
    int[] maxX;
    int clickedItem;
    int[] lastHI;
    int maxWidth;
    String bigString;
    Image image1;
    Image offScreenImage;
    Graphics offScreenGraphic;
    int nHeight;
    int nWidth;
    int width;
    Font newF;
    boolean firstTime;
    boolean debug;
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    int[] parseInt(final String s, final String sep) {
        final StringTokenizer st = new StringTokenizer(s, sep);
        final int[] result = new int[st.countTokens()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = Integer.parseInt(st.nextToken());
        }
        return result;
    }
    
    public sdmenu() {
        this.isStandalone = false;
        this.arrayMenu = new MenuItem[256];
        this.startX = 30;
        this.startY = 40;
        this.howMany = 0;
        this.activeItem = -1;
        this.depth = 1;
        this.itemDepth = 1;
        this.maxX = new int[10];
        this.clickedItem = -1;
        this.lastHI = new int[10];
        this.maxWidth = 0;
        this.nHeight = 0;
        this.nWidth = 0;
        this.firstTime = true;
        this.debug = false;
    }
    
    public void init() {
        String at = this.getParameter("height");
        this.nHeight = ((at != null) ? Integer.valueOf(at) : 100);
        at = this.getParameter("width");
        this.nWidth = ((at != null) ? Integer.valueOf(at) : 170);
        this.offScreenImage = this.createImage(this.nWidth, this.nHeight);
        this.offScreenGraphic = this.offScreenImage.getGraphics();
        for (int x = 0; x < 10; ++x) {
            this.lastHI[x] = (this.maxX[x] = 0);
        }
        final String temp = this.getParameter("debug");
        if (temp != null && temp.equals("true")) {
            this.debug = true;
            System.out.println("Debug set On");
        }
        int[] ints = this.parseInt(this.getParameter("color1"), " ");
        this.color1 = new Color(ints[0], ints[1], ints[2]);
        ints = this.parseInt(this.getParameter("color2"), " ");
        this.color2 = new Color(ints[0], ints[1], ints[2]);
        this.image1 = this.getImage(this.getDocumentBase(), this.getParameter("image1"));
        this.newF = new Font("Helvetica", 1, 14);
        this.readInFile();
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void paintMenu(final int depth) {
        if (this.debug) {
            System.out.println(String.valueOf("Starting ===============").concat(String.valueOf(depth)));
        }
        int items = 0;
        int x = 0;
        if (this.clickedItem >= 0 && depth > 1) {
            x = this.lastHI[depth - 1];
        }
        while (x < this.howMany) {
            if (this.arrayMenu[x].getDepth() == depth) {
                if (this.arrayMenu[x].getType() == MenuItem.END) {
                    break;
                }
                ++items;
                int thisY;
                if (depth == 1 || this.clickedItem == -1) {
                    thisY = this.startY;
                }
                else {
                    thisY = this.arrayMenu[this.lastHI[depth - 1]].gety();
                }
                if (depth == 1) {
                    final int startX = this.startX;
                }
                final int thisX = 30 + (depth - 1) * this.width;
                this.arrayMenu[x].drawMenuItem(this.offScreenGraphic, thisX, items, thisY, this.activeItem == x, this.width);
            }
            ++x;
        }
    }
    
    public void readInFile() {
        int index = 0;
        String menuLine;
        while (true) {
            menuLine = new String(String.valueOf("menu").concat(String.valueOf(index + 1)));
            final String buf = this.getParameter(menuLine);
            if (buf == null) {
                break;
            }
            try {
                this.arrayMenu[index] = this.processLine(buf, index);
            }
            catch (Exception e) {
                System.out.println(String.valueOf("Error processing menu on Line ").concat(String.valueOf(index)));
            }
            ++index;
        }
        if (this.debug) {
            System.out.println(String.valueOf("Breaking at ").concat(String.valueOf(menuLine)));
        }
        this.howMany = index;
    }
    
    public MenuItem processLine(final String buf, final int index) throws Exception {
        final StringTokenizer st = new StringTokenizer(buf);
        final String tag = new String();
        final String name = new String();
        final String url = new String();
        final String sub = new String();
        String[] token;
        int count;
        for (token = new String[3], count = 0; st.hasMoreTokens() && count < 4; token[count++] = new String(st.nextToken())) {}
        if (count > 1 && this.maxWidth < token[1].length()) {
            this.maxWidth = token[1].length();
            this.bigString = new String(token[1]);
        }
        if (token[0].equals("<MS>")) {
            return new MenuItem(MenuItem.MENU, token[1], new String(), index, this.color1, this.color2, this.itemDepth++);
        }
        if (token[0].equals("<ME>")) {
            return new MenuItem(MenuItem.END, new String(), new String(), index, this.color1, this.color2, this.itemDepth--);
        }
        if (token[0].equals("<MI>")) {
            return new MenuItem(MenuItem.ITEM, token[1], token[2], index, this.color1, this.color2, this.itemDepth);
        }
        throw new Exception("Unknown Menu Line");
    }
    
    public void jbInit() throws Exception {
        final String Author = this.getParameter("Author");
        if (Author == null || !Author.equals("Simon Dubey")) {
            for (int x = 0; x < this.howMany; ++x) {
                this.arrayMenu[x].setMenuName("Simon Dubey");
            }
            throw new Exception("Author Parameter in HTML must be set to \"Simon Dubey\"");
        }
        this.resize(this.nWidth, this.nHeight);
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void paint(final Graphics g) {
        g.setFont(this.newF);
        this.update(g);
    }
    
    public void update(final Graphics g) {
        this.offScreenGraphic.setColor(Color.lightGray);
        this.offScreenGraphic.fillRect(0, 0, this.nWidth, this.nHeight);
        this.offScreenGraphic.setColor(Color.black);
        this.offScreenGraphic.drawImage(this.image1, 0, 0, this.nWidth, this.nHeight, this);
        this.width = this.offScreenGraphic.getFontMetrics().stringWidth(this.bigString) + 5;
        for (int x = 1; x <= this.depth; ++x) {
            this.paintMenu(x);
        }
        g.drawImage(this.offScreenImage, 0, 0, this);
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        return this.mouseMove(evt, x, y);
    }
    
    public boolean mouseMove(final Event evt, final int inx, final int iny) {
        final int x = this.getIndex(inx, iny);
        if (x < 0) {
            this.activeItem = -1;
            return false;
        }
        if (this.activeItem == x) {
            return true;
        }
        if (this.debug) {
            System.out.println(String.valueOf("Over ").concat(String.valueOf(x)));
        }
        this.depth = this.arrayMenu[x].getDepth();
        this.activeItem = x;
        this.repaint();
        return true;
    }
    
    public int getIndex(final int x, final int y) {
        for (int count = this.lastHI[this.depth - 1]; count < this.howMany; ++count) {
            if (this.arrayMenu[count].getDepth() <= this.depth) {
                if (count <= this.clickedItem || this.depth >= this.arrayMenu[count].getDepth()) {
                    if (this.arrayMenu[count].overlaps(x, y)) {
                        return count;
                    }
                }
            }
        }
        return -1;
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        this.activeItem = -1;
        this.depth = 1;
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        if (this.activeItem < 0) {
            this.clickedItem = -1;
            this.depth = 1;
            return false;
        }
        if (this.arrayMenu[this.activeItem].getType() == MenuItem.MENU) {
            if (this.depth >= 1) {
                this.lastHI[this.depth] = this.activeItem;
            }
            this.clickedItem = this.activeItem;
            if (this.debug) {
                System.out.println("About to load next Menu");
            }
            this.depth = this.arrayMenu[this.activeItem].getDepth() + 1;
            this.repaint();
            return true;
        }
        if (this.arrayMenu[this.activeItem].getType() == MenuItem.ITEM) {
            if (this.debug) {
                System.out.println(String.valueOf("Loading ").concat(String.valueOf(this.arrayMenu[this.activeItem].getUrl())));
            }
            try {
                final URL url = new URL(this.getDocumentBase(), this.arrayMenu[this.activeItem].getUrl());
                this.getAppletContext().showDocument(url);
            }
            catch (MalformedURLException ee) {
                System.out.println(String.valueOf("Error Loading Url ").concat(String.valueOf(ee.getMessage())));
            }
            return true;
        }
        return false;
    }
}
