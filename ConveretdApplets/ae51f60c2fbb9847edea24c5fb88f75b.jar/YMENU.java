import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import netscape.javascript.JSObject;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.PopupMenu;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class YMENU extends Applet
{
    Image imgLst;
    Image[] imgBtn;
    Image[] imgMoveBtn;
    PopupMenu[] bPop;
    String[] bLink;
    String[] sBtn;
    String[] sMoveBtn;
    String[][] iName;
    String[][] iLink;
    Rectangle[] rcBtn;
    int[] iBtnX;
    int iBtnY;
    int hitBtn;
    int moveBtn;
    Graphics myG;
    String[] sId;
    public int Uab;
    Cursor m_curHand;
    Cursor m_curNormal;
    int cpop;
    int opop;
    
    public boolean setId(final int n, final String s) {
        if (n > 0 && n <= this.sId.length) {
            this.sId[n - 1] = s.trim().toUpperCase();
            return true;
        }
        return false;
    }
    
    String[] parse(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(this.m_curNormal);
        this.moveBtn = -1;
        this.paint(this.myG);
        return true;
    }
    
    private void calcBtn() {
        final Dimension size = this.getSize();
        this.rcBtn = new Rectangle[this.iBtnX.length];
        int n = 0;
        for (int i = 0; i < this.rcBtn.length; ++i) {
            n += this.iBtnX[i];
        }
        int x = this.getAlignCenter(n, size.width);
        int alignCenter = this.getAlignCenter(this.iBtnY, size.height);
        for (int j = 0; j < this.rcBtn.length; ++j) {
            this.rcBtn[j] = new Rectangle();
            this.rcBtn[j].width = this.iBtnX[j];
            this.rcBtn[j].height = this.iBtnY;
            if (x + this.rcBtn[j].width > size.width) {
                x = this.getAlignCenter(n, size.width);
                alignCenter += this.iBtnY;
            }
            this.rcBtn[j].x = x;
            this.rcBtn[j].y = alignCenter;
            x += this.rcBtn[j].width;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.imgLst, this.rcBtn[0].x, this.rcBtn[0].y, this);
        if (this.moveBtn >= 0) {
            graphics.drawImage(this.imgMoveBtn[this.moveBtn], this.rcBtn[this.moveBtn].x, this.rcBtn[this.moveBtn].y, this);
        }
        if (this.hitBtn >= 0) {
            graphics.drawImage(this.imgBtn[this.hitBtn], this.rcBtn[this.hitBtn].x, this.rcBtn[this.hitBtn].y, this);
        }
    }
    
    public YMENU() {
        this.hitBtn = -1;
        this.moveBtn = -1;
        this.Uab = 0;
        this.m_curHand = new Cursor(12);
        this.m_curNormal = new Cursor(0);
        this.cpop = 0;
        this.opop = 0;
    }
    
    void parseMenu(final int n, final StringTokenizer stringTokenizer) {
        final int countTokens = stringTokenizer.countTokens();
        final Font font = new Font("\u7d30\u660e\u9ad4", 0, 12);
        final Menu[] array = new Menu[10];
        this.add(this.bPop[n] = new PopupMenu());
        int n2 = 0;
        int i = 0;
        while (i < countTokens) {
            final String nextToken = stringTokenizer.nextToken();
            ++i;
            switch (nextToken.charAt(0)) {
                case 'm': {
                    final char c = (char)(nextToken.charAt(1) - '0');
                    (array[c] = new Menu(nextToken.substring(2))).setFont(font);
                    if (c == '\0') {
                        this.bPop[n].add(array[c]);
                        continue;
                    }
                    array[c - '\u0001'].add(array[c]);
                    continue;
                }
                default: {
                    continue;
                }
                case 'i': {
                    final char c2 = (char)(nextToken.charAt(1) - '0');
                    final MenuItem menuItem = new MenuItem(nextToken.substring(2));
                    menuItem.setFont(font);
                    if (c2 == '\0') {
                        this.bPop[n].add(menuItem);
                    }
                    else {
                        array[c2 - '\u0001'].add(menuItem);
                    }
                    this.iName[n][n2] = menuItem.getName();
                    final String nextToken2 = stringTokenizer.nextToken();
                    ++i;
                    this.iLink[n][n2] = nextToken2;
                    ++n2;
                    continue;
                }
            }
        }
    }
    
    void actionScript(String s) {
        try {
            if (s.startsWith("javascript:")) {
                s = s.substring(11);
                for (int i = 0; i < this.sId.length; ++i) {
                    s = this.replace(s, "%" + (i + 1), this.sId[i]);
                    s = this.replace(s, "_", ",");
                }
                JSObject.getWindow((Applet)this).eval(s);
                return;
            }
            for (int j = 0; j < this.sId.length; ++j) {
                s = this.replace(s, "%" + (j + 1), this.sId[j]);
            }
            String substring = "main";
            final int index = s.indexOf(40);
            final int lastIndex = s.lastIndexOf(41);
            if (index >= 0 && lastIndex >= 0) {
                substring = s.substring(index + 1, lastIndex);
                s = s.substring(0, index);
            }
            this.getAppletContext().showDocument(new URL(this.getDocumentBase(), s), substring);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private int getAlignCenter(final int n, final int n2) {
        return (n2 - n) / 2;
    }
    
    int[] parseI(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public void closePop() {
        this.cpop = 1;
        this.opop = 0;
        if (this.hitBtn >= 0) {
            this.bPop[this.hitBtn].removeNotify();
        }
    }
    
    String replace(String string, final String s, final String s2) {
        final int length = s.length();
        if (length < 1) {
            return string;
        }
        int i;
        do {
            i = string.indexOf(s);
            if (i <= 0) {
                return string;
            }
            string = string.substring(0, i) + s2 + string.substring(i + length);
        } while (i > 0);
        return string;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void notifyLoaded() {
        final String parameter = this.getParameter("LOAD");
        if (parameter != null) {
            try {
                JSObject.getWindow((Applet)this).eval(parameter);
            }
            catch (Exception ex) {}
        }
    }
    
    public void Disable(final String s) {
        this.Uab = Integer.parseInt(s);
    }
    
    public boolean setPop(int n, final String s) {
        if (--n >= 0 && n < this.sBtn.length) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            final int countTokens = stringTokenizer.countTokens();
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.length() > 1) {
                this.bLink[n] = nextToken.substring(1);
            }
            else {
                this.bLink[n] = "";
            }
            this.iName[n] = new String[(countTokens - 1) / 2];
            this.iLink[n] = new String[(countTokens - 1) / 2];
            this.parseMenu(n, stringTokenizer);
            return true;
        }
        return false;
    }
    
    public void openPop() {
        this.cpop = 0;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.Uab == 1) {
            return true;
        }
        if (this.cpop == 1) {
            ++this.opop;
            if (this.opop > 10) {
                this.cpop = 0;
            }
        }
        if (this.cpop == 0) {
            for (int i = 0; i < this.rcBtn.length; ++i) {
                if (this.rcBtn[i].contains(n, n2)) {
                    this.hitBtn = i;
                    this.paint(this.myG);
                    if (i >= 0) {
                        if (this.bLink[i] != null && this.bLink[i].length() > 0) {
                            this.actionScript(this.bLink[i]);
                        }
                        else {
                            this.bPop[i].show(this, this.rcBtn[i].x, this.rcBtn[i].y + this.rcBtn[i].height + 1);
                        }
                    }
                }
            }
        }
        else if (this.hitBtn != -1) {
            this.hitBtn = -1;
            this.paint(this.myG);
        }
        return true;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof MenuItem) {
            for (int n = 0; this.iName[this.hitBtn][n] != null; ++n) {
                if (((MenuItem)event.target).getName().compareTo(this.iName[this.hitBtn][n]) == 0) {
                    this.actionScript(this.iLink[this.hitBtn][n]);
                    break;
                }
            }
        }
        return true;
    }
    
    public void init() {
        final URL codeBase = this.getCodeBase();
        if (this.getParameter("DISABLE") != null) {
            this.Uab = 1;
        }
        this.setBackground(new Color(Integer.parseInt(this.getParameter("BGCOLOR"), 16)));
        this.imgLst = this.getImage(codeBase, this.getParameter("IMG0"));
        this.iBtnY = Integer.parseInt(this.getParameter("IMGY"));
        this.sBtn = this.parse(this.getParameter("IMGS"), ",");
        if (this.getParameter("IMGM") != null) {
            this.sMoveBtn = this.parse(this.getParameter("IMGM"), ",");
        }
        this.iBtnX = this.parseI(this.getParameter("IMGX"), ",");
        this.calcBtn();
        this.imgBtn = new Image[this.sBtn.length];
        this.imgMoveBtn = new Image[this.sBtn.length];
        this.bPop = new PopupMenu[this.sBtn.length];
        this.bLink = new String[this.sBtn.length];
        this.iName = new String[this.sBtn.length][];
        this.iLink = new String[this.sBtn.length][];
        this.myG = this.getGraphics();
        this.sId = this.parse(this.getParameter("REPL"), ",");
        for (int i = 0; i < this.sBtn.length; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("POP" + (i + 1)), ",");
            final int countTokens = stringTokenizer.countTokens();
            this.imgBtn[i] = this.getImage(codeBase, this.sBtn[i]);
            if (this.sMoveBtn != null) {
                this.imgMoveBtn[i] = this.getImage(codeBase, this.sMoveBtn[i]);
            }
            else {
                this.imgMoveBtn[i] = this.getImage(codeBase, this.sBtn[i]);
            }
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.length() > 1) {
                this.bLink[i] = nextToken.substring(1);
            }
            this.iName[i] = new String[(countTokens - 1) / 2];
            this.iLink[i] = new String[(countTokens - 1) / 2];
            this.parseMenu(i, stringTokenizer);
        }
        this.setCursor(new Cursor(12));
        this.notifyLoaded();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.Uab == 1) {
            return true;
        }
        if (this.cpop == 1) {
            ++this.opop;
            if (this.opop > 10) {
                this.cpop = 0;
            }
        }
        if (this.cpop == 0) {
            this.moveBtn = -1;
            this.setCursor(this.m_curNormal);
            for (int i = 0; i < this.rcBtn.length; ++i) {
                if (this.rcBtn[i].contains(n, n2)) {
                    this.setCursor(this.m_curHand);
                    this.moveBtn = i;
                }
            }
            this.paint(this.myG);
        }
        return true;
    }
}
