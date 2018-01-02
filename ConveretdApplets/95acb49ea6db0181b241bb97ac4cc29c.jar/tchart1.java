import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;
import java.awt.Event;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Button;
import java.awt.Choice;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tchart1 extends Applet
{
    private Vector dFile;
    private int iMax;
    private int iActif;
    private int iPress;
    private int iFr;
    private int iCol;
    private boolean bReg;
    static String sR;
    cchart cv1;
    cchart cv2;
    Choice c1;
    Button b1;
    
    public void setBgImg(final String s, final int n) {
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
        this.cv1.setBkImage(image);
    }
    
    public void setCol(final int iCol) {
        this.clearData();
        this.cv1.setCol(iCol);
        if (this.iFr != 0) {
            this.cv2.setCol(iCol);
        }
        this.iCol = iCol;
    }
    
    public tchart1() {
        this.dFile = new Vector(5, 5);
        this.iMax = 1;
        this.iCol = 1;
    }
    
    public void setPress(final int iPress, final int n) {
        this.iPress = iPress;
        this.cv1.setPress(iPress, n);
        if (this.iFr != 0) {
            this.cv2.setPress(4, n);
        }
    }
    
    public void clearData() {
        this.cv1.clearData();
        if (this.iFr != 0) {
            this.cv2.clearData();
        }
        this.dFile.removeAllElements();
        this.iMax = 0;
    }
    
    public void paintOn() {
        this.cv1.show();
        if (this.iFr != 0) {
            this.cv2.show();
        }
        this.cv1.repaint();
        if (this.iFr != 0) {
            this.cv2.repaint();
        }
    }
    
    public void addRow(final String s) {
        final rd rd = new rd();
        if (rd.get(rd, s, '§', this.iCol)) {
            this.cv1.addRow(rd.iD, rd.sIt);
            if (this.iFr != 0) {
                this.cv2.addRow(rd.iD, rd.sIt);
            }
        }
    }
    
    public void setDec(final int n) {
        this.cv1.setDec(n);
        if (this.iFr != 0) {
            this.cv2.setDec(n);
        }
    }
    
    public void addRow(final double[] array, final String s) {
        this.cv1.addRow(array, s);
        if (this.iFr != 0) {
            this.cv2.addRow(array, s);
        }
    }
    
    public void setFont(final String s, final int n) {
        this.cv1.setFont(s, 0, n);
        if (this.iFr != 0) {
            this.cv2.setFont(s, 0, n);
        }
    }
    
    public void setBkColor(final int n) {
        this.cv1.setBkColor(n);
        if (this.iFr != 0) {
            this.cv2.setBkColor(n);
        }
    }
    
    static {
        tchart1.sR = "http://www.javaside.com/";
    }
    
    public void setMinMax(final double n, final double n2) {
        if (n != n2) {
            this.cv1.setMinMax(n, n2);
        }
    }
    
    public void setTitle(final String title) {
        this.cv1.setTitle(title);
    }
    
    public void paintOff() {
        this.cv1.hide();
        if (this.iFr != 0) {
            this.cv2.hide();
        }
    }
    
    public void addCol(final int n, final int n2, final String s) {
        this.cv1.addCol(n, n2, s);
        if (this.iFr != 0) {
            this.cv2.addCol(n, n2, s);
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (!this.bReg && event.target instanceof Button) {
            try {
                this.getAppletContext().showDocument(new URL(tchart1.sR), "r");
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public String getAppletInfo() {
        return "Name: tchart1 V 4.21\r\n" + "Author: R. BERTHOU\r\n" + "E-Mail : rbl@berthou.com\r\n" + "URL : " + tchart1.sR;
    }
    
    public void init() {
        int int1 = 13684944;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setFont(new Font("Helvetica", 0, 14));
        this.setLayout(layout);
        gridBagConstraints.anchor = 12;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        if (!this.bReg) {
            layout.setConstraints(this.b1 = new Button("About"), gridBagConstraints);
            this.add(this.b1);
        }
        final Label label = new Label("");
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        final String parameter = this.getParameter("frame");
        this.iFr = 0;
        if (parameter != null) {
            this.iFr = Integer.parseInt(parameter);
        }
        this.cv1 = new cchart();
        final String parameter2 = this.getParameter("bgcolor");
        if (parameter2 != null) {
            int1 = Integer.parseInt(parameter2);
        }
        this.cv1.setBkColor(int1);
        if (this.iFr != 0) {
            (this.cv2 = new cchart()).setBkColor(int1);
            this.cv2.setBkImage(null);
        }
        final String parameter3 = this.getParameter("fontsize");
        int int2;
        if (parameter3 != null) {
            int2 = Integer.parseInt(parameter3);
        }
        else {
            int2 = 12;
        }
        String parameter4 = this.getParameter("font");
        if (parameter4 == null) {
            parameter4 = new String("Helvetica");
        }
        this.cv1.setFont(parameter4, 0, int2);
        if (this.iFr != 0) {
            this.cv2.setFont(parameter4, 0, int2);
        }
        Image image = null;
        final String parameter5 = this.getParameter("bgImg");
        if (parameter5 != null) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            image = this.getImage(this.getCodeBase(), parameter5);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {}
        }
        this.cv1.setBkImage(image);
        gridBagConstraints.weighty = 1.0;
        if (this.iFr == 0) {
            gridBagConstraints.gridheight = 0;
        }
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 10.0 - this.iFr / 10;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.cv1, gridBagConstraints);
        this.add(this.cv1);
        if (this.iFr != 0) {
            gridBagConstraints.weighty = this.iFr / 10;
            gridBagConstraints.gridheight = 0;
            layout.setConstraints(this.cv2, gridBagConstraints);
            this.add(this.cv2);
        }
    }
    
    public void setVal(final int val) {
        if (val > 0) {
            this.cv1.setVal(val);
            return;
        }
        this.cv1.setVal(-1);
    }
}
