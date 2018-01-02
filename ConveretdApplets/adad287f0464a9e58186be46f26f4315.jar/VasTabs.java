import java.awt.Event;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class VasTabs extends Panel
{
    Font normalFont;
    Font boldFont;
    FontMetrics fmBoldFont;
    Color defaultColor;
    Color light;
    Color shadow;
    CardLayout tabLayout;
    Panel tabPanel;
    static final int selUpper = 2;
    static final int lineWidth = 2;
    static final int horRound = 2;
    static final int verRound = 2;
    static final int XTitle = 4;
    static final int YTitle = 2;
    final int Z;
    int X;
    int Y;
    final int maxNumber = 25;
    String[] arrName;
    int[] arrEnd;
    int[] arrBeg;
    Color[] arrCol;
    boolean[] arrEna;
    int nbTab;
    int selected;
    
    public String getAppletInfo() {
        return new String("VasTabs" + ' ' + 'v' + '2' + '.' + '1' + ' ' + 'M' + 'a' + 'r' + 'c' + 'h' + ',' + ' ' + '3' + '1' + ' ' + '1' + '9' + '9' + '8' + ',' + ' ' + '(' + 'C' + ')' + ' ' + '1' + '9' + '9' + '7' + ',' + ' ' + '9' + '8' + ' ' + "Vasile Calmatui");
    }
    
    public VasTabs(final int n, final int n2, final int x, final int y) {
        this.normalFont = new Font("Helvetica", 0, 12);
        this.boldFont = new Font("Helvetica", 1, 12);
        this.fmBoldFont = this.getFontMetrics(this.boldFont);
        this.defaultColor = Color.lightGray;
        this.light = new Color(223, 223, 223);
        this.shadow = new Color(127, 127, 127);
        this.tabLayout = new CardLayout();
        this.tabPanel = new Panel();
        this.Z = this.fmBoldFont.getHeight() + 4 + 4 + 1;
        this.X = 400;
        this.Y = 400;
        this.arrName = new String[25];
        this.arrEnd = new int[25];
        this.arrBeg = new int[25];
        this.arrCol = new Color[25];
        this.arrEna = new boolean[25];
        this.reshape(n, n2, this.X = x, this.Y = y);
        this.setBackground(this.defaultColor);
        this.setLayout(null);
        this.tabPanel.reshape(2, this.Z + 1, x - 4, y - this.Z - 4);
        this.tabPanel.setLayout(this.tabLayout);
        this.add(this.tabPanel);
    }
    
    public void addTab(final String s, final Panel panel) {
        if (this.nbTab >= this.arrBeg.length || panel == this) {
            return;
        }
        this.arrName[this.nbTab] = s;
        this.arrBeg[this.nbTab] = ((this.nbTab == 0) ? 0 : ((this.nbTab == 1) ? (this.arrEnd[this.nbTab - 1] - 2) : this.arrEnd[this.nbTab - 1]));
        this.arrEnd[this.nbTab] = ((this.nbTab == 0) ? (8 + this.fmBoldFont.stringWidth(s) + 6) : (this.arrBeg[this.nbTab] + 8 + this.fmBoldFont.stringWidth(s) + 4));
        this.arrCol[this.nbTab] = this.defaultColor;
        this.arrEna[this.nbTab] = true;
        for (int i = 0; i < this.nbTab; ++i) {
            if (this.tabPanel.getComponent(i) == panel || this.arrName[i].equals(s)) {
                return;
            }
        }
        if (this.arrEnd[this.nbTab] > this.X) {
            return;
        }
        this.tabPanel.add(Integer.toString(this.nbTab), panel);
        ++this.nbTab;
    }
    
    public void refreshTabs() {
        this.paint(this.getGraphics());
    }
    
    public void removeTab(final Panel panel) {
        final Component[] components = this.tabPanel.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] == panel) {
                this.tabPanel.remove(panel);
                this.selectTab(this.arrName[0]);
                final int n = this.arrEnd[i] - this.arrBeg[i];
                for (int j = i; j < this.nbTab - 1; ++j) {
                    this.arrEnd[j] = this.arrEnd[j + 1] - n;
                    this.arrBeg[j] = this.arrBeg[j + 1] - n;
                    if (j == 0) {
                        final int[] arrEnd = this.arrEnd;
                        final int n2 = j;
                        arrEnd[n2] += 2;
                        final int[] arrBeg = this.arrBeg;
                        final int n3 = j;
                        arrBeg[n3] += 2;
                    }
                    this.arrName[j] = this.arrName[j + 1];
                }
                --this.nbTab;
                return;
            }
        }
    }
    
    public void setColorTab(final String s, final Color color) {
        for (int i = 0; i < this.nbTab; ++i) {
            if (this.arrName[i].equals(s)) {
                this.arrCol[i] = color;
                return;
            }
        }
    }
    
    public void disableTab(final String s) {
        for (int i = 0; i < this.nbTab; ++i) {
            if (this.arrName[i].equals(s)) {
                this.arrEna[i] = false;
                return;
            }
        }
    }
    
    public void enableTab(final String s) {
        for (int i = 0; i < this.nbTab; ++i) {
            if (this.arrName[i].equals(s)) {
                this.arrEna[i] = true;
                return;
            }
        }
    }
    
    public void renameTab(final String s, final String s2) {
        for (int i = 0; i < this.nbTab; ++i) {
            if (this.arrName[i].equals(s)) {
                final int n = this.fmBoldFont.stringWidth(s2) - this.fmBoldFont.stringWidth(s);
                if (this.arrEnd[this.nbTab - 1] + n > this.X) {
                    return;
                }
                this.arrName[i] = new String(s2);
                final int[] arrEnd = this.arrEnd;
                final int n2 = i;
                arrEnd[n2] += n;
                for (int j = i + 1; j < this.nbTab; ++j) {
                    final int[] arrEnd2 = this.arrEnd;
                    final int n3 = j;
                    arrEnd2[n3] += n;
                    final int[] arrBeg = this.arrBeg;
                    final int n4 = j;
                    arrBeg[n4] += n;
                }
            }
            else {}
        }
    }
    
    public void selectTab(final String s) {
        int i = 0;
        while (i < this.nbTab) {
            if (this.arrName[i].equals(s)) {
                if (i == this.selected || !this.arrEna[i]) {
                    return;
                }
                final int[] arrBeg = this.arrBeg;
                final int selected = this.selected;
                arrBeg[selected] += 2;
                final int[] arrEnd = this.arrEnd;
                final int selected2 = this.selected;
                arrEnd[selected2] -= 2;
                this.selected = i;
                this.tabLayout.show(this.tabPanel, Integer.toString(this.selected));
                final int[] arrBeg2 = this.arrBeg;
                final int n = i;
                arrBeg2[n] -= 2;
                final int[] arrEnd2 = this.arrEnd;
                final int n2 = i;
                arrEnd2[n2] += 2;
            }
            else {
                ++i;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.defaultColor);
        graphics.fillRect(0, 0, this.X, this.Z + 1);
        for (int i = 0; i < this.nbTab; ++i) {
            final int n = (i == this.selected) ? 0 : 2;
            graphics.setFont((i == this.selected) ? this.boldFont : this.normalFont);
            if (this.arrCol[i] != this.defaultColor) {
                graphics.setColor(this.arrCol[i]);
                graphics.fillRect(this.arrBeg[i] + 2, 2 + n, this.arrEnd[i] - this.arrBeg[i] - 4, this.Z + 1);
            }
            graphics.setColor(this.shadow);
            for (int n2 = 2; i != this.selected - 1 && n2 > 0; --n2) {
                if (n2 == 1) {
                    graphics.setColor(Color.black);
                }
                graphics.drawLine(this.arrEnd[i] - n2, n + 2, this.arrEnd[i] - n2, this.Z - 2);
                if (n2 == 1) {
                    graphics.drawLine(this.arrEnd[i] - 2, n + 1, this.arrEnd[i] - 2, n + 1);
                }
            }
            if (this.arrEna[i]) {
                graphics.setColor(Color.black);
            }
            else {
                graphics.setColor(this.shadow);
            }
            graphics.drawString(this.arrName[i], this.arrBeg[i] + 2 + 4, n + 2 + 2 + this.fmBoldFont.getAscent());
            graphics.setColor(Color.white);
            for (int j = 0; j < 2; ++j) {
                if (j == 1) {
                    graphics.setColor(this.light);
                }
                graphics.drawLine(this.arrBeg[i] + 2, n + j, this.arrEnd[i] - 2 - 2 + 1, n + j);
                if (i != this.selected + 1) {
                    graphics.drawLine(this.arrBeg[i] + j, n + 2, this.arrBeg[i] + j, this.Z);
                    if (j == 0) {
                        graphics.drawLine(this.arrBeg[i] + 1, n + 1, this.arrBeg[i] + 1, n + 1);
                    }
                }
            }
        }
        graphics.setColor(Color.white);
        for (int k = 0; k < 2; ++k) {
            if (k == 1) {
                graphics.setColor(this.light);
            }
            graphics.drawLine(k, this.Z, k, this.Y);
            if (this.selected != 0) {
                graphics.drawLine(0, this.Z - 2 + k + 1, this.arrBeg[this.selected], this.Z - 2 + k + 1);
            }
            graphics.drawLine(this.arrEnd[this.selected], this.Z - 2 + k + 1, this.X, this.Z - 2 + k + 1);
        }
        graphics.setColor(this.shadow);
        for (int l = 2; l > 0; --l) {
            if (l == 1) {
                graphics.setColor(Color.black);
            }
            graphics.drawLine(0, this.Y - l, this.X, this.Y - l);
            graphics.drawLine(this.X - l, this.Z, this.X - l, this.Y);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n2 < this.Z) {
            for (int i = 0; i < this.nbTab; ++i) {
                if (n > this.arrBeg[i] && n < this.arrEnd[i] && i != this.selected && this.arrEna[i]) {
                    this.selectTab(this.arrName[i]);
                    this.paint(this.getGraphics());
                    return true;
                }
            }
        }
        return false;
    }
}
