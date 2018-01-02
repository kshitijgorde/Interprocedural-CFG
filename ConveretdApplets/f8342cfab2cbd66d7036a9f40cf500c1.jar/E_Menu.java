import java.awt.Component;
import java.awt.Event;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.Font;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.LayoutManager;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class E_Menu extends Applet
{
    boolean b_init;
    int btnWidth;
    int btnHeight;
    int btntotal;
    String TargetWin;
    Image[] btnimg1;
    Image[] btnimg2;
    PopupMenu[] bPop;
    String[] iMain;
    String[][] iName;
    String[][] iLink;
    int btnHit;
    Graphics Ap_G;
    Cursor waitCursor;
    Cursor handCursor;
    Cursor normalCursor;
    
    public E_Menu() {
        this.b_init = false;
        this.btnWidth = 0;
        this.btnHeight = 0;
        this.btntotal = 0;
        this.btnHit = -1;
        this.waitCursor = new Cursor(3);
        this.handCursor = new Cursor(12);
        this.normalCursor = new Cursor(0);
    }
    
    public void setCursor(final int t) {
        switch (t) {
            case 0: {
                this.setCursor(this.normalCursor);
                break;
            }
            case 1: {
                this.setCursor(this.handCursor);
                break;
            }
            case 2: {
                this.setCursor(this.waitCursor);
                break;
            }
        }
    }
    
    public void init() {
        final URL myURL = this.getCodeBase();
        int xx = 0;
        System.out.println(myURL);
        this.Ap_G = this.getGraphics();
        if (this.getParameter("btnWidth") == "" || this.getParameter("btnWidth") == null) {
            System.exit(1);
        }
        this.btnWidth = Integer.parseInt(this.getParameter("btnWidth"));
        if (this.getParameter("btnHeight") == "" || this.getParameter("btnHeight") == null) {
            System.exit(1);
        }
        this.btnHeight = Integer.parseInt(this.getParameter("btnHeight"));
        if (this.getParameter("btntotal") == "" || this.getParameter("btntotal") == null) {
            System.exit(1);
        }
        this.btntotal = Integer.parseInt(this.getParameter("btntotal"));
        String strtmt = this.getParameter("TargetWin");
        if (strtmt == null) {
            this.TargetWin = "_self";
        }
        else {
            this.TargetWin = strtmt;
        }
        this.btnimg1 = new Image[this.btntotal];
        this.btnimg2 = new Image[this.btntotal];
        this.bPop = new PopupMenu[this.btntotal];
        this.iMain = new String[this.btntotal];
        this.iName = new String[this.btntotal][];
        this.iLink = new String[this.btntotal][];
        final String[] pStr = new String[this.btntotal];
        for (int i = 0; i < this.btntotal; ++i) {
            final String sParam = this.getParameter("item".concat(Integer.toString(i)));
            pStr[i] = sParam;
        }
        this.setLayout(null);
        for (int i = 0; i < this.btntotal; ++i) {
            final StringTokenizer st = new StringTokenizer(pStr[i], ",");
            final int cntTok = st.countTokens();
            String tok = st.nextToken();
            this.btnimg1[i] = this.getImage(myURL, tok);
            tok = st.nextToken();
            this.btnimg2[i] = this.getImage(myURL, tok);
            tok = st.nextToken();
            this.iMain[i] = new String(tok);
            System.out.println(this.iMain[i]);
            if (tok.charAt(0) != 'm') {
                this.iName[i] = new String[(cntTok - 2) / 2];
                this.iLink[i] = new String[(cntTok - 2) / 2];
                this.parseMenu(i, st);
            }
            else {
                String dymenu = "";
                for (int ii = 3; ii < cntTok; ++ii) {
                    strtmt = st.nextToken();
                    if (ii > 1) {
                        dymenu = String.valueOf(dymenu).concat(String.valueOf(String.valueOf(strtmt).concat(String.valueOf(","))));
                    }
                }
                final StringTokenizer ss = new StringTokenizer(dymenu, ",");
                final int cccTok = ss.countTokens();
                this.iName[i] = new String[cccTok / 2];
                this.iLink[i] = new String[cccTok / 2];
                this.parseMenu(i, ss);
            }
            this.Ap_G.drawImage(this.btnimg1[i], xx, 0, this);
            xx += this.btnWidth;
        }
        this.show();
    }
    
    void parseMenu(final int col, final StringTokenizer st) {
        final int cntTok = st.countTokens();
        final Font mFont = new Font("\u7d30\u660e\u9ad4", 0, 12);
        final Menu[] tMenu = new Menu[10];
        this.add(this.bPop[col] = new PopupMenu());
        int ii = 0;
        int k = 0;
        while (k < cntTok) {
            String tok = st.nextToken();
            ++k;
            switch (tok.charAt(0)) {
                case 'i': {
                    final int level = tok.charAt(1) - '0';
                    final MenuItem tmpItem = new MenuItem(tok.substring(2));
                    tmpItem.setFont(mFont);
                    if (level == 0) {
                        this.bPop[col].add(tmpItem);
                    }
                    else {
                        tMenu[level - 1].add(tmpItem);
                    }
                    this.iName[col][ii] = tok.substring(2);
                    tok = st.nextToken();
                    ++k;
                    this.iLink[col][ii] = tok;
                    ++ii;
                    continue;
                }
                case 's': {
                    final int level = tok.charAt(1) - '0';
                    (tMenu[level] = new Menu(tok.substring(2))).setFont(mFont);
                    if (level == 0) {
                        this.bPop[col].add(tMenu[level]);
                    }
                    else {
                        tMenu[level - 1].add(tMenu[level]);
                    }
                    continue;
                }
            }
        }
    }
    
    public void paintAll(final Graphics g) {
        this.b_init = false;
        this.paint(g);
    }
    
    public void repaint(final Graphics g) {
        this.b_init = false;
        this.paint(g);
    }
    
    public void update(final Graphics g) {
        this.b_init = false;
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        int xx = 0;
        if (!this.b_init) {
            for (int i = 0; i < this.btntotal; ++i) {
                this.Ap_G.drawImage(this.btnimg1[i], xx, 0, this);
                xx += this.btnWidth;
            }
        }
    }
    
    public boolean action(final Event evt, final Object obj) {
        if (evt.target instanceof MenuItem) {
            for (int i = 0; this.iName[this.btnHit][i] != null; ++i) {
                if (((String)obj).compareTo(this.iName[this.btnHit][i]) == 0) {
                    try {
                        final URL url = new URL(this.getDocumentBase(), this.iLink[this.btnHit][i]);
                        this.getAppletContext().showDocument(url, this.TargetWin);
                        System.out.println(url);
                        this.btnHit = -1;
                        this.paint(this.Ap_G);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        int xx = 0;
        if (x >= 0 && y >= 0 && x <= this.btnWidth * this.btntotal && y <= this.btnHeight) {
            this.setCursor(1);
            for (int i = 0; i < this.btntotal; ++i) {
                if (x >= xx && x < xx + this.btnWidth) {
                    this.update(this.Ap_G);
                    this.Ap_G.drawImage(this.btnimg2[i], xx, 0, this);
                    break;
                }
                xx += this.btnWidth;
            }
        }
        else {
            this.setCursor(0);
            this.update(this.Ap_G);
        }
        return true;
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        int xx = 0;
        if (x >= 0 && y >= 0 && x <= this.btnWidth * this.btntotal && y <= this.btnHeight) {
            this.setCursor(1);
            for (int i = 0; i < this.btntotal; ++i) {
                if (x >= xx && x < xx + this.btnWidth) {
                    this.update(this.Ap_G);
                    this.Ap_G.drawImage(this.btnimg2[i], xx, 0, this);
                    if (this.btnHit != i) {
                        this.bPop[i].show(this, xx, this.btnHeight);
                        this.btnHit = i;
                    }
                    else {
                        this.btnHit = -1;
                    }
                    break;
                }
                xx += this.btnWidth;
            }
        }
        else {
            this.setCursor(0);
            this.update(this.Ap_G);
        }
        return true;
    }
}
