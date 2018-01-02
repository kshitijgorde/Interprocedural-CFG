import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Button;
import java.awt.Choice;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tchart2 extends Applet
{
    private Vector dFile;
    private int iMax;
    private int iActif;
    private int iPress;
    private int iFr;
    private boolean bReg;
    static String sR;
    private boolean bLoc;
    private char cSep;
    cchart cv1;
    cchart cv2;
    Choice c1;
    Button b1;
    
    public void setPress(final int iPress, final int n) {
        this.iPress = iPress;
        this.cv1.setPress(iPress, n);
        if (this.iFr != 0) {
            this.cv2.setPress(4, n);
        }
    }
    
    public void readFile(final URL url, final String s) {
        this.dFile.removeAllElements();
        this.iMax = 0;
        int n = 0;
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(new URL(this.getCodeBase(), s).openStream());
            this.bLoc = false;
        }
        catch (Exception ex) {
            dataInputStream = null;
            this.bLoc = true;
        }
        while (true) {
            String s2;
            if (this.bLoc) {
                s2 = this.getParameter(s + n);
                ++n;
            }
            else {
                try {
                    s2 = dataInputStream.readLine();
                }
                catch (Exception ex2) {
                    return;
                }
            }
            int char1;
            if (s2 != null) {
                char1 = s2.charAt(0);
            }
            else {
                char1 = 32;
            }
            if (char1 != 59 && char1 != 47) {
                final rd rd = new rd();
                if (!rd.get(rd, s2, this.cSep, 7)) {
                    break;
                }
                this.dFile.addElement(rd);
                ++this.iMax;
            }
        }
    }
    
    public void setData(final int n) {
        this.cv1.clearData();
        if (this.iFr != 0) {
            this.cv2.clearData();
        }
        this.cv1.setCol((int)this.dFile.elementAt(n).iD[0]);
        if (this.iFr != 0) {
            this.cv2.setCol((int)this.dFile.elementAt(n).iD[0]);
        }
        final int n2 = (int)this.dFile.elementAt(n).iD[0];
        int n3 = 0;
        int n4 = 0;
        final String sUrl = this.dFile.elementAt(this.iActif).sUrl;
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(new URL(this.getCodeBase(), sUrl).openStream());
            this.bLoc = false;
        }
        catch (Exception ex) {
            dataInputStream = null;
            this.bLoc = true;
        }
        while (true) {
            String s;
            if (this.bLoc) {
                s = this.getParameter(sUrl + n4);
                ++n4;
            }
            else {
                try {
                    s = dataInputStream.readLine();
                }
                catch (Exception ex2) {
                    break;
                }
            }
            int char1;
            if (s != null) {
                char1 = s.charAt(0);
            }
            else {
                char1 = 32;
            }
            if (char1 != 59 && char1 != 47) {
                final rd rd = new rd();
                if (!rd.get(rd, s, this.cSep, n2)) {
                    break;
                }
                if (rd.sIt.equals("col")) {
                    this.cv1.addCol(n3++, (int)rd.iD[0], rd.sUrl);
                    if (this.iFr == 0) {
                        continue;
                    }
                    this.cv2.addCol(n3 - 1, (int)rd.iD[0], rd.sUrl);
                }
                else if (rd.sIt.equals("title")) {
                    this.cv1.setTitle(rd.sUrl);
                }
                else {
                    this.cv1.addRow(rd.iD, rd.sIt);
                    if (this.iFr == 0) {
                        continue;
                    }
                    this.cv2.addRow(rd.iD, rd.sIt);
                }
            }
        }
        this.setPress((int)this.dFile.elementAt(n).iD[1], (int)this.dFile.elementAt(n).iD[2]);
        if (this.dFile.elementAt(n).iD[3] > 0.0 && this.dFile.elementAt(n).iD[3] != rd.iNan) {
            this.cv1.setVal((int)this.dFile.elementAt(n).iD[3]);
        }
        if (this.dFile.elementAt(n).iD[4] != this.dFile.elementAt(n).iD[5]) {
            this.cv1.setMinMax(this.dFile.elementAt(n).iD[4], this.dFile.elementAt(n).iD[5]);
        }
        if (this.dFile.elementAt(n).iD[6] != rd.iNan) {
            this.cv1.setDec((int)this.dFile.elementAt(n).iD[6]);
            if (this.iFr != 0) {
                this.cv2.setDec((int)this.dFile.elementAt(n).iD[6]);
            }
        }
        this.cv1.repaint();
        if (this.iFr != 0) {
            this.cv2.repaint();
        }
    }
    
    static {
        tchart2.sR = "http://www.javaside.com/";
    }
    
    public String getAppletInfo() {
        return "Name: tchart2 V 4.30\r\n" + "Author: R. BERTHOU\r\n" + "E-Mail : rbl@berthou.com\r\n" + "URL : " + tchart2.sR;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            final int selectedIndex = this.c1.getSelectedIndex();
            if (selectedIndex != this.iActif) {
                this.setData(this.iActif = selectedIndex);
            }
        }
        else if (!this.bReg && event.target instanceof Button) {
            try {
                this.getAppletContext().showDocument(new URL(tchart2.sR), "r");
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public void init() {
        int int1 = 13684944;
        final String parameter = this.getParameter("csep");
        if (parameter != null) {
            this.cSep = parameter.charAt(0);
        }
        String parameter2 = this.getParameter("file");
        if (parameter2 == null) {
            parameter2 = "file.txt";
        }
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setFont(new Font("Helvetica", 0, 14));
        this.setLayout(layout);
        gridBagConstraints.anchor = 12;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        this.readFile(this.getCodeBase(), parameter2);
        final String parameter3 = this.getParameter("actif");
        if (parameter3 != null) {
            this.iActif = Integer.parseInt(parameter3);
        }
        if (!this.bReg) {
            layout.setConstraints(this.b1 = new Button("About"), gridBagConstraints);
            this.add(this.b1);
        }
        final Label label = new Label("");
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        this.c1 = new Choice();
        for (int i = 0; i < this.iMax; ++i) {
            this.c1.addItem(((rd)this.dFile.elementAt(i)).sIt);
        }
        if (this.iActif > this.iMax) {
            this.c1.select(this.iActif);
        }
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.c1, gridBagConstraints);
        this.add(this.c1);
        final String parameter4 = this.getParameter("frame");
        this.iFr = 0;
        if (parameter4 != null) {
            this.iFr = Integer.parseInt(parameter4);
        }
        this.cv1 = new cchart();
        final String parameter5 = this.getParameter("bgcolor");
        if (parameter5 != null) {
            int1 = Integer.parseInt(parameter5);
        }
        this.cv1.setBkColor(int1);
        if (this.iFr != 0) {
            (this.cv2 = new cchart()).setBkColor(int1);
        }
        final String parameter6 = this.getParameter("fontsize");
        int int2;
        if (parameter6 != null) {
            int2 = Integer.parseInt(parameter6);
        }
        else {
            int2 = 12;
        }
        String parameter7 = this.getParameter("font");
        if (parameter7 == null) {
            parameter7 = new String("Helvetica");
        }
        this.cv1.setFont(parameter7, 0, int2);
        if (this.iFr != 0) {
            this.cv2.setFont(parameter7, 0, int2);
            this.cv2.setBkImage(null);
        }
        Image image = null;
        final String parameter8 = this.getParameter("bgImg");
        if (parameter8 != null) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            image = this.getImage(this.getCodeBase(), parameter8);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {}
        }
        this.cv1.setBkImage(image);
        gridBagConstraints.weighty = 1.0;
        if (this.iActif < this.iMax) {
            this.setData(this.iActif);
        }
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
    
    public tchart2() {
        this.dFile = new Vector(5, 5);
        this.iMax = 1;
        this.cSep = '§';
    }
}
