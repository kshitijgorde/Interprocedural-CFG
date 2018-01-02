// 
// Decompiled by Procyon v0.5.30
// 

package microtex.TeletextOnWeb;

import java.awt.Component;
import java.awt.Event;
import java.util.BitSet;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Panel;

public class TextPanel extends Panel
{
    private String m_strPresentationStatusMsg;
    private String m_strWelcomeSt;
    public int m_nFontSel;
    public int m_nFontMultiply;
    private teletext m_appletParent;
    private int[] m_nFontH;
    private int[] m_nFontW;
    private PageObj m_pageCur;
    private boolean m_bTextReady;
    private Graphics m_offg;
    private Image m_offimg;
    MediaTracker t;
    private int[][] m_pixFont;
    private int[][] m_pixComprFont;
    private int[][] m_pixComprGridFont;
    private boolean m_bTextChanged;
    private int m_nLocalLoaded;
    private int m_nAppearence;
    private int m_nStep;
    private Dimension m_prefSize;
    private Image m_fontImgSrc;
    private Image m_nMicroTexLogo;
    private final int[] ColTab;
    private final Color[] ColorTab;
    private Font m_bigFont;
    private Font m_smallFont;
    
    public void init() {
        this.lateInit();
    }
    
    public void destroy() {
        if (this.m_offg != null) {
            this.m_offg.dispose();
        }
    }
    
    public void lateInit() {
        final int nx = this.getWidth();
        final int ny = this.getHeight();
        this.m_pixFont = new int[256][this.getWidth() * this.getHeight()];
        this.m_pixComprFont = new int[256][this.getWidth() * this.getHeight()];
        this.m_pixComprGridFont = new int[256][this.getWidth() * this.getHeight()];
        final int[] pix = new int[this.getWidth() * this.getHeight() * 8 * 32];
        this.m_offimg = this.createImage(this.getWidth() * this.m_nFontMultiply * 40, this.getHeight() * this.m_nFontMultiply * 24);
        if (this.m_offg != null) {
            this.m_offg.dispose();
        }
        (this.m_offg = this.m_offimg.getGraphics()).setColor(Color.black);
        this.m_offg.fillRect(0, 0, this.getWidth() * this.m_nFontMultiply * 40, this.getHeight() * this.m_nFontMultiply * 24);
        this.m_fontImgSrc = this.m_appletParent.getImage(this.m_appletParent.getCodeBase(), "fonts/" + this.getWidth() + "x" + this.getHeight() + ".gif");
        this.m_nMicroTexLogo = this.m_appletParent.getImage(this.m_appletParent.getCodeBase(), "images/logo.gif");
        this.t.addImage(this.m_fontImgSrc, 0);
        try {
            this.t.waitForID(0);
        }
        catch (InterruptedException e) {
            System.err.println("image " + this.m_appletParent.getCodeBase() + "fonts/" + this.getWidth() + "x" + this.getHeight() + ".gif fetch aborted or errored");
            return;
        }
        if (this.t.statusID(0, false) != 8) {
            System.out.println("couldn't retrieve font file.");
            return;
        }
        final PixelGrabber pg = new PixelGrabber(this.m_fontImgSrc, 0, 0, 32 * nx, 8 * ny, pix, 0, 32 * nx);
        try {
            if (!pg.grabPixels()) {
                System.err.println("pixel grabbing aborted or errored");
                return;
            }
        }
        catch (InterruptedException e2) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        if ((pg.status() & 0x80) != 0x0) {
            System.err.println("image fetch aborted or errored");
            return;
        }
        for (int cod = 0; cod < 256; ++cod) {
            final int row = cod / 32;
            final int col = cod % 32;
            final int offset = row * (32 * nx * ny) + col * nx;
            for (int j = 0; j < ny; ++j) {
                for (int i = 0; i < nx; ++i) {
                    this.m_pixFont[cod][j * nx + i] = (((pix[offset + i + j * 32 * nx] & 0x1) != 0x1) ? 1 : 0);
                }
            }
        }
        int smartI = 0;
        int cont = 0;
        for (int cod = 0; cod < 256; ++cod) {
            smartI = 0;
            for (int k = 0; k < ny; ++k) {
                boolean bLookFor1 = true;
                cont = 0;
                for (int h = 0; h < nx; ++h) {
                    final int i = k * nx + h;
                    if (bLookFor1) {
                        if (this.m_pixFont[cod][i] == 1) {
                            ++cont;
                        }
                        else {
                            this.m_pixComprFont[cod][smartI++] = cont;
                            bLookFor1 = !bLookFor1;
                            cont = 0;
                            --h;
                        }
                    }
                    else if (this.m_pixFont[cod][i] == 0) {
                        ++cont;
                    }
                    else {
                        this.m_pixComprFont[cod][smartI++] = cont;
                        bLookFor1 = !bLookFor1;
                        cont = 0;
                        --h;
                    }
                }
                this.m_pixComprFont[cod][smartI++] = cont;
            }
            while (smartI < nx * ny) {
                this.m_pixComprFont[cod][smartI++] = nx;
            }
        }
        for (int i = 0; i < nx * ny; ++i) {
            this.m_pixFont[255][i] = ((this.m_pixFont[255][i] != 1) ? 1 : 0);
        }
        for (int cod = 0; cod < 256; ++cod) {
            smartI = 0;
            for (int k = 0; k < ny; ++k) {
                boolean bLookFor1 = true;
                cont = 0;
                for (int h = 0; h < nx; ++h) {
                    final int i = k * nx + h;
                    if (bLookFor1) {
                        if ((this.m_pixFont[cod][i] & this.m_pixFont[255][i]) == 0x1) {
                            ++cont;
                        }
                        else {
                            this.m_pixComprGridFont[cod][smartI++] = cont;
                            bLookFor1 = !bLookFor1;
                            cont = 0;
                            --h;
                        }
                    }
                    else if ((this.m_pixFont[cod][i] & this.m_pixFont[255][i]) == 0x0) {
                        ++cont;
                    }
                    else {
                        this.m_pixComprGridFont[cod][smartI++] = cont;
                        bLookFor1 = !bLookFor1;
                        cont = 0;
                        --h;
                    }
                }
                this.m_pixComprGridFont[cod][smartI++] = cont;
            }
            while (smartI < nx * ny) {
                this.m_pixComprGridFont[cod][smartI++] = nx;
            }
        }
    }
    
    public void cleanUp() {
        this.m_offg.dispose();
    }
    
    public void setChanged() {
        this.m_bTextChanged = true;
    }
    
    public void update(final Graphics g) {
        if (!this.m_appletParent.m_AllLoadedCtrl || !this.m_appletParent.m_EndPresentazione) {
            this.m_nLocalLoaded = 0;
            for (int i = 0; i < 10; ++i) {
                if (this.m_appletParent.m_tracker.checkID(i, true)) {
                    ++this.m_nLocalLoaded;
                }
            }
            if (this.t.checkID(1, true)) {
                ++this.m_nLocalLoaded;
            }
            if (this.m_appletParent.m_tracker.checkAll() && this.t.checkID(1, true)) {
                this.m_appletParent.m_AllLoadedCtrl = true;
            }
            this.m_nAppearence += this.m_nStep;
            if (this.m_nAppearence % 6 == 0) {
                final String first = this.m_strPresentationStatusMsg.substring(0, 1);
                this.m_strPresentationStatusMsg = this.m_strPresentationStatusMsg.substring(1).concat(first);
                this.m_appletParent.showStatus(this.m_strPresentationStatusMsg);
            }
            if (this.m_nAppearence >= 450) {
                this.m_appletParent.m_EndPresentazione = true;
                if (this.m_appletParent.m_AllLoadedCtrl) {
                    this.m_appletParent.m_wndControl.lateInit();
                    this.m_appletParent.m_wndControl.paintAll(this.m_appletParent.m_wndControl.getGraphics());
                    this.m_appletParent.pageRequest(this.m_appletParent.m_strStartPageStr, "0");
                    this.m_appletParent.showStatus("");
                }
            }
            if (!this.m_appletParent.m_EndPresentazione) {
                if (this.m_nMicroTexLogo != null) {
                    this.m_offg.drawImage(this.m_nMicroTexLogo, this.getWidth() * 40 - this.m_nMicroTexLogo.getWidth(this), this.getHeight() * 25 - this.m_nMicroTexLogo.getHeight(this), this);
                }
                if (!this.m_appletParent.m_AllLoadedCtrl) {
                    this.m_offg.setFont(this.m_smallFont);
                    this.m_offg.drawString("Loading", 0, 12);
                    this.m_offg.drawString("images", 0, 27);
                    this.m_offg.setColor(Color.green);
                    this.m_offg.fillRect(0, 30, 110, 10);
                    for (int k = 0; k < this.m_nLocalLoaded; ++k) {
                        this.m_offg.setColor(Color.blue);
                        this.m_offg.fillRect(1 + k * 10, 32, 7, 6);
                    }
                }
                else {
                    this.m_offg.setFont(this.m_smallFont);
                    this.m_offg.setColor(Color.black);
                    this.m_offg.fillRect(0, 0, 150, 45);
                    this.m_offg.setColor(new Color((float)Math.abs(Math.sin(this.m_nAppearence % 80 * 3.141592653589793 / 20.0)), (float)Math.abs(Math.sin(this.m_nAppearence % 80 * 3.141592653589793 / 20.0)), (float)Math.abs(Math.sin(this.m_nAppearence % 80 * 3.141592653589793 / 20.0))));
                    this.m_offg.drawString("Loaded", 0, 12);
                    this.m_offg.drawString("All images", 0, 27);
                }
                if (this.m_nAppearence < 230 && this.m_nAppearence > 1) {
                    this.m_offg.setColor(Color.black);
                    this.m_offg.fillRect(0, 300 - this.m_nAppearence + 145, 155, 20);
                    this.m_offg.setColor(Color.red);
                    this.m_offg.fill3DRect(0, 300 - this.m_nAppearence, 150, 150, true);
                }
                if (this.m_nAppearence <= 121 && this.m_nAppearence > 1) {
                    this.m_offg.setFont(this.m_bigFont);
                    final int k = this.m_nAppearence - 16;
                    this.m_offg.setColor(Color.black);
                    this.m_offg.fillRect(80, k - 30, 300, k + 5);
                    this.m_offg.setColor(Color.white);
                    this.m_offg.drawString(this.m_strWelcomeSt, 95, k);
                }
                if (this.m_nAppearence > 121 && this.m_nAppearence < 420) {
                    this.m_offg.setFont(this.m_bigFont);
                    this.m_offg.setColor(new Color(1.0f - (float)(0.2 * Math.abs(Math.sin(this.m_nAppearence % 80 * 3.141592653589793 / 20.0))), 1.0f - (float)(0.3 * Math.abs(Math.sin(this.m_nAppearence % 80 * 3.141592653589793 / 20.0))), (float)(0.3 * Math.abs(Math.sin(this.m_nAppearence % 80 * 3.141592653589793 / 20.0)))));
                    this.m_offg.drawString(this.m_strWelcomeSt, 95, 104);
                }
                if (this.m_nAppearence > 421 && this.m_nAppearence < 430) {
                    this.m_offg.setFont(this.m_bigFont);
                    this.m_offg.setColor(new Color(255, 235, 200));
                    this.m_offg.drawString(this.m_strWelcomeSt, 95, 104);
                }
                if (this.m_nAppearence < 300 && this.m_nAppearence > 100) {
                    this.m_offg.setFont(this.m_bigFont);
                    final int k = this.m_nAppearence - 100;
                    this.m_offg.setColor(Color.yellow);
                    this.m_offg.drawLine(0, 110, (int)(k * 1.2), 110);
                }
                if (this.m_nAppearence >= 300) {
                    this.m_offg.setFont(this.m_smallFont);
                    this.m_offg.setColor(Color.white);
                    this.m_offg.drawString("Teletext on Web", 5, 140);
                }
                if (this.m_nAppearence >= 324) {
                    this.m_offg.setFont(this.m_smallFont);
                    this.m_offg.setColor(Color.white);
                    this.m_offg.drawString("(c)1998,99 MicroTex", 5, 180);
                }
                g.drawImage(this.m_offimg, 0, 0, this);
                this.repaint(30L);
            }
        }
        else {
            this.paint(g);
        }
    }
    
    public void paint(final Graphics g) {
        if (!this.m_appletParent.m_AllLoadedCtrl || !this.m_appletParent.m_EndPresentazione) {
            this.repaint();
        }
        else {
            if (this.m_bTextReady && this.m_bTextChanged) {
                final int cx = this.getWidth();
                final int cy = this.getHeight();
                final int dim = cx * cy;
                final int[] pix = new int[dim];
                final int[] costCol = new int[2];
                boolean bDouble = false;
                boolean bPrevDouble = false;
                int smartI = 0;
                int cont = 0;
                for (int row = 0; row < this.m_pageCur.m_textData.length() / 40; ++row) {
                    for (int col = 0; col < 40; ++col) {
                        final int i = row * 40 + col;
                        final int X = col * cx;
                        final int Y = row * cy;
                        final int cod = this.m_pageCur.m_textData.charAt(i);
                        if (((bPrevDouble && !this.m_pageCur.m_textDouble.get(i - 40)) || (!bPrevDouble && !this.m_pageCur.m_textDouble.get(i))) && !this.m_pageCur.m_textGrid.get(i)) {
                            smartI = 0;
                            for (int k = 0; k < cy; ++k) {
                                boolean bLookFor1 = true;
                                cont = 0;
                                for (int h = 0; h < cx; h += cont) {
                                    if ((cont = this.m_pixComprFont[cod][smartI++]) > 0) {
                                        this.m_offg.setColor(bLookFor1 ? this.ColorTab[this.m_pageCur.m_textFore[i]] : this.ColorTab[this.m_pageCur.m_textBack[i]]);
                                        this.m_offg.fillRect((X + h) * this.m_nFontMultiply, (Y + k) * this.m_nFontMultiply, cont * this.m_nFontMultiply, this.m_nFontMultiply);
                                        bLookFor1 = !bLookFor1;
                                    }
                                    else {
                                        bLookFor1 = !bLookFor1;
                                    }
                                }
                            }
                        }
                        else {
                            if (this.m_pageCur.m_textDouble.get(i) && !this.m_pageCur.m_textGrid.get(i)) {
                                bDouble = true;
                                smartI = 0;
                                for (int k = 0; k < cy; ++k) {
                                    boolean bLookFor1 = true;
                                    cont = 0;
                                    for (int h = 0; h < cx; h += cont) {
                                        if ((cont = this.m_pixComprFont[cod][smartI++]) > 0) {
                                            this.m_offg.setColor(bLookFor1 ? this.ColorTab[this.m_pageCur.m_textFore[i]] : this.ColorTab[this.m_pageCur.m_textBack[i]]);
                                            this.m_offg.fillRect((X + h) * this.m_nFontMultiply, (Y + k * 2) * this.m_nFontMultiply, cont * this.m_nFontMultiply, this.m_nFontMultiply * 2);
                                            bLookFor1 = !bLookFor1;
                                        }
                                        else {
                                            bLookFor1 = !bLookFor1;
                                        }
                                    }
                                }
                            }
                            if (!this.m_pageCur.m_textDouble.get(i) && this.m_pageCur.m_textGrid.get(i)) {
                                smartI = 0;
                                for (int k = 0; k < cy; ++k) {
                                    boolean bLookFor1 = true;
                                    cont = 0;
                                    for (int h = 0; h < cx; h += cont) {
                                        if ((cont = this.m_pixComprGridFont[cod][smartI++]) > 0) {
                                            this.m_offg.setColor(bLookFor1 ? this.ColorTab[this.m_pageCur.m_textFore[i]] : this.ColorTab[this.m_pageCur.m_textBack[i]]);
                                            this.m_offg.fillRect((X + h) * this.m_nFontMultiply, (Y + k) * this.m_nFontMultiply, cont * this.m_nFontMultiply, this.m_nFontMultiply);
                                            bLookFor1 = !bLookFor1;
                                        }
                                        else {
                                            bLookFor1 = !bLookFor1;
                                        }
                                    }
                                }
                            }
                            if (this.m_pageCur.m_textDouble.get(i) && this.m_pageCur.m_textGrid.get(i)) {
                                bDouble = true;
                                smartI = 0;
                                for (int k = 0; k < cy; ++k) {
                                    boolean bLookFor1 = true;
                                    cont = 0;
                                    for (int h = 0; h < cx; h += cont) {
                                        if ((cont = this.m_pixComprGridFont[cod][smartI++]) > 0) {
                                            this.m_offg.setColor(bLookFor1 ? this.ColorTab[this.m_pageCur.m_textFore[i]] : this.ColorTab[this.m_pageCur.m_textBack[i]]);
                                            this.m_offg.fillRect((X + h) * this.m_nFontMultiply, (Y + k * 2) * this.m_nFontMultiply, cont * this.m_nFontMultiply, this.m_nFontMultiply * 2);
                                            bLookFor1 = !bLookFor1;
                                        }
                                        else {
                                            bLookFor1 = !bLookFor1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (bPrevDouble) {
                        bPrevDouble = false;
                    }
                    if (bDouble) {
                        bPrevDouble = true;
                    }
                    bDouble = false;
                }
                this.m_bTextChanged = false;
            }
            g.drawImage(this.m_offimg, 0, 0, this);
        }
    }
    
    public int getHeight() {
        return this.m_nFontH[this.m_nFontSel];
    }
    
    public int getWidth() {
        return this.m_nFontW[this.m_nFontSel];
    }
    
    public void setFontSize(final int nSize) {
        this.m_nFontSel = nSize;
        if (this.m_nFontSel == 2) {
            this.m_nFontMultiply = 2;
            this.m_nFontSel = 0;
        }
        else {
            this.m_nFontMultiply = 1;
        }
        this.lateInit();
        this.setChanged();
    }
    
    public void setTextData(final int page, final int roll, final int ofRoll, final String data) {
        final StringBuffer textData = new StringBuffer(960);
        final byte[] textFore = new byte[960];
        final byte[] textBack = new byte[960];
        final BitSet textDouble = new BitSet(960);
        final BitSet textGrid = new BitSet(960);
        int j = 0;
        textData.setLength(960);
        for (int i = 0; i < data.length() - 1; i += 2) {
            textData.setCharAt(j, data.charAt(i + 1));
            textFore[j] = (byte)(data.charAt(i) & '\u0007');
            textBack[j] = (byte)(data.charAt(i) >> 3 & '\u0007');
            if ((data.charAt(i) & '@') == '@') {
                textDouble.set(j);
            }
            else {
                textDouble.clear(j);
            }
            if ((data.charAt(i) & '\u0080') == '\u0080') {
                textGrid.set(j);
            }
            else {
                textGrid.clear(j);
            }
            ++j;
        }
        this.m_pageCur = new PageObj(page, roll, ofRoll, textData.toString(), textFore, textBack, textDouble, textGrid);
        this.setChanged();
        this.m_bTextReady = true;
    }
    
    private boolean isDigit(final char ch) {
        return ch >= '0' && ch <= '9';
    }
    
    public int getLink(int x, int y) {
        if (this.m_nFontMultiply > 1) {
            x /= this.m_nFontMultiply;
            y /= this.m_nFontMultiply;
        }
        final int col = x / this.getWidth();
        final int row = y / this.getHeight();
        final int spot = row * 40 + col;
        String strSpot = this.m_pageCur.m_textData.toString();
        boolean found = false;
        for (strSpot = strSpot.substring((col - 3 > 0) ? (spot - 3) : (row * 40), (col + 4 < 40) ? (spot + 4) : ((row + 1) * 40)); strSpot.length() < 7; strSpot += " ") {}
        int i;
        for (i = 0; i < 3; ++i) {
            if (!this.isDigit(strSpot.charAt(i)) && this.isDigit(strSpot.charAt(i + 1)) && this.isDigit(strSpot.charAt(i + 2)) && this.isDigit(strSpot.charAt(i + 3)) && !this.isDigit(strSpot.charAt(i + 4))) {
                found = true;
                break;
            }
        }
        int num;
        if (found) {
            num = Integer.parseInt(strSpot.substring(i + 1, i + 4));
        }
        else {
            num = -1;
        }
        return num;
    }
    
    public int getPage() {
        if (this.m_pageCur != null) {
            return this.m_pageCur.m_curPage;
        }
        return 0;
    }
    
    public int getRolling() {
        if (this.m_pageCur != null) {
            return this.m_pageCur.m_curRoll;
        }
        return 0;
    }
    
    public int getRollingOf() {
        if (this.m_pageCur != null) {
            return this.m_pageCur.m_curOfRoll;
        }
        return 0;
    }
    
    public void setWelcomeString(final String WelcomeString) {
        this.m_strWelcomeSt = WelcomeString;
    }
    
    public int getTVWidth() {
        return 40 * this.getWidth() * this.m_nFontMultiply;
    }
    
    public int getTVHeight() {
        return 24 * this.getHeight() * this.m_nFontMultiply;
    }
    
    public void setPreferredSize(final Dimension prefSize) {
        this.m_prefSize = prefSize;
    }
    
    public Dimension minimumSize() {
        return this.m_prefSize;
    }
    
    public Dimension preferredSize() {
        return this.m_prefSize;
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        if (!this.m_appletParent.m_EndPresentazione) {
            return false;
        }
        final int pageRequested = this.getLink(x, y);
        if (pageRequested > -1) {
            this.m_appletParent.pageRequest(String.valueOf(pageRequested), "0");
        }
        return true;
    }
    
    public TextPanel(final teletext parent) {
        this.m_strPresentationStatusMsg = "Finalmente Teletext arriva su Internet........                    ";
        this.m_nFontSel = 0;
        this.m_nFontMultiply = 1;
        this.m_nFontH = new int[3];
        this.m_nFontW = new int[3];
        this.m_pageCur = null;
        this.m_bTextReady = false;
        this.t = new MediaTracker(this);
        this.m_bTextChanged = false;
        this.m_nLocalLoaded = 0;
        this.m_nAppearence = 0;
        this.m_nStep = 2;
        this.ColTab = new int[] { Color.black.getRGB(), Color.red.getRGB(), Color.green.getRGB(), Color.yellow.getRGB(), Color.blue.getRGB(), Color.magenta.getRGB(), Color.cyan.getRGB(), Color.white.getRGB() };
        this.ColorTab = new Color[] { Color.black, Color.red, Color.green, Color.yellow, Color.blue, Color.magenta, Color.cyan, Color.white };
        this.m_bigFont = new Font("Arial", 1, 15);
        this.m_smallFont = new Font("Arial", 2, 13);
        this.m_appletParent = parent;
        this.m_bTextReady = false;
        this.m_nFontW[0] = 10;
        this.m_nFontW[1] = 15;
        this.m_nFontW[2] = 20;
        this.m_nFontH[0] = 12;
        this.m_nFontH[1] = 18;
        this.m_nFontH[2] = 24;
    }
}
