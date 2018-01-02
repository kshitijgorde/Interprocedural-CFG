import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Font;
import java.net.URL;
import java.awt.Event;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tchart0 extends Applet
{
    private boolean bReg;
    static String sR;
    cchart[] cv;
    Button b1;
    private int iNb;
    
    public void setBgImg(final int n, final String s, final int n2) {
        if (n >= this.iNb) {
            return;
        }
        Image image = null;
        if (s != null) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            image = this.getImage(this.getCodeBase(), s);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {}
        }
        this.cv[n].setBkImage(image);
    }
    
    public void setCol(final int n, final int col) {
        if (n >= this.iNb) {
            return;
        }
        this.clearData(n);
        this.cv[n].setCol(col);
    }
    
    public void clearChart() {
        for (int i = 0; i < this.iNb; ++i) {
            this.remove(this.cv[i]);
            this.cv[i] = null;
        }
    }
    
    public void clearChart(final int n) {
        if (n >= this.iNb) {
            return;
        }
        if (this.cv[n] != null) {
            this.remove(this.cv[n]);
        }
        this.cv[n] = null;
    }
    
    public void creaChart(final int iNb) {
        this.clearChart();
        this.cv = new cchart[iNb];
        this.iNb = iNb;
    }
    
    public void setPress(final int n, final int n2, final int n3) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].setPress(n2, n3);
    }
    
    public void clearData(final int n) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].clearData();
    }
    
    public void paintOn(final int n) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].show();
        this.cv[n].repaint();
    }
    
    public void addRow(final int n, final String s) {
        if (n >= this.iNb) {
            return;
        }
        final rd rd = new rd();
        if (rd.get(rd, s, '§', this.cv[n].getCol())) {
            this.cv[n].addRow(rd.iD, rd.sIt);
        }
    }
    
    public void setDec(final int n, final int dec) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].setDec(dec);
    }
    
    public void addRow(final int n, final double[] array, final String s) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].addRow(array, s);
    }
    
    public void setFont(final int n, final String s, final int n2) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].setFont(s, 0, n2);
    }
    
    public void setBkColor(final int n, final int bkColor) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].setBkColor(bkColor);
    }
    
    static {
        tchart0.sR = "http://www.javaside.com/";
    }
    
    public void setMinMax(final int n, final double n2, final double n3) {
        if (n >= this.iNb) {
            return;
        }
        if (n2 != n3) {
            this.cv[n].setMinMax(n2, n3);
        }
    }
    
    public void setTitle(final int n, final String title) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].setTitle(title);
    }
    
    public void paintOff(final int n) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].hide();
    }
    
    public void addCol(final int n, final int n2, final int n3, final String s) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].addCol(n2, n3, s);
    }
    
    public boolean action(final Event event, final Object o) {
        if (!this.bReg && event.target instanceof Button) {
            try {
                this.getAppletContext().showDocument(new URL(tchart0.sR), "r");
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public String getAppletInfo() {
        return "Name: tchart1 V 4.21\r\n" + "Author: R. BERTHOU\r\n" + "E-Mail : rbl@berthou.com\r\n" + "URL : " + tchart0.sR;
    }
    
    public void init() {
        int int1 = 13684944;
        this.setFont(new Font("Helvetica", 0, 14));
        this.setLayout(null);
        if (!this.bReg) {
            this.add(this.b1 = new Button("About"));
            this.b1.reshape(1, 1, 100, 22);
        }
        final String parameter = this.getParameter("bgcolor");
        if (parameter != null) {
            int1 = Integer.parseInt(parameter);
        }
        this.setBackground(new Color(int1));
    }
    
    public void setVal(final int n, final int val) {
        if (n >= this.iNb) {
            return;
        }
        if (val > 0) {
            this.cv[n].setVal(val);
            return;
        }
        this.cv[n].setVal(-1);
    }
    
    public void addChart(final int n) {
        if (n >= this.iNb) {
            return;
        }
        this.clearChart(n);
        this.add(this.cv[n] = new cchart());
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n >= this.iNb) {
            return;
        }
        this.cv[n].reshape(n2, n3, n4, n5);
    }
}
