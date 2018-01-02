import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class Datagram extends JComponent
{
    int mode;
    int[] offsets;
    private int comp;
    private int finalDest;
    private double tolerance;
    private double fadeInc;
    private String[] names;
    private String[] layerNames;
    private String data;
    Color bgcolor;
    Color borderColor;
    Color c1;
    Color c2;
    Color c3;
    Color c4;
    Color c5;
    Color c6;
    Color c7;
    Color dataColor;
    
    public Datagram(final Graphics g, final String[] _names, final Color _bgcolor) {
        this.mode = 0;
        this.comp = 0;
        this.finalDest = 7;
        this.tolerance = 0.2;
        this.fadeInc = 0.15;
        this.layerNames = new String[] { "Link Layer", "Network Layer", "Transport Layer", "Application Layer" };
        this.bgcolor = Color.white;
        this.borderColor = new Color(102, 0, 0);
        this.setPreferredSize(new Dimension(480, 40));
        this.names = _names;
        this.bgcolor = _bgcolor;
        this.offsets = new int[this.names.length + this.layerNames.length];
        this.computeOffsets(g);
        this.setStartState();
    }
    
    public void setStartState() {
        this.c1 = this.bgcolor;
        this.c2 = this.bgcolor;
        this.c3 = this.bgcolor;
        this.c4 = this.bgcolor;
        this.c5 = this.bgcolor;
        this.c6 = this.bgcolor;
        this.c7 = this.bgcolor;
        this.data = "";
        this.dataColor = this.bgcolor;
        this.mode = 6;
    }
    
    public void setMode(final int _mode) {
        this.mode = _mode;
    }
    
    public void setLinkAddress(final int _n) {
        this.comp = _n;
    }
    
    public void setFinalAddress(final int _n) {
        this.finalDest = _n;
    }
    
    public void setData(final String s) {
        if (s != null) {
            this.data = s;
        }
    }
    
    public void setDataColor(final Color c) {
        if (c != null) {
            this.dataColor = c;
        }
    }
    
    public void setOutline(final boolean b) {
        if (b) {
            this.c6 = Color.red;
        }
        else {
            this.c6 = Color.blue;
        }
    }
    
    public int getMode() {
        return this.mode;
    }
    
    private void computeOffsets(final Graphics g) {
        int[] temp = Toolbox.getStringWidth(g, this.names);
        for (int i = 0; i < this.names.length; ++i) {
            this.offsets[i] = 60 - temp[i] / 2;
        }
        temp = Toolbox.getStringWidth(g, this.layerNames);
        for (int i = 0; i < this.layerNames.length; ++i) {
            this.offsets[i + this.names.length] = 60 - temp[i] / 2;
        }
    }
    
    private boolean fadeFirstToColor(final Color fadeTo, final Color fadeTo2) {
        this.c1 = Toolbox.getNextColor(this.c1, fadeTo, this.tolerance, this.fadeInc);
        return this.c1.equals(fadeTo) && this.fadeLinkAddrToColor(fadeTo2);
    }
    
    private boolean fadeSecondToColor(final Color fadeTo, final Color fadeTo2) {
        this.c2 = Toolbox.getNextColor(this.c2, fadeTo, this.tolerance, this.fadeInc);
        return this.c2.equals(fadeTo) && this.fadeNetworkAddrToColor(fadeTo2);
    }
    
    private boolean fadeThirdToColor(final Color fadeTo) {
        this.c3 = Toolbox.getNextColor(this.c3, fadeTo, this.tolerance, this.fadeInc);
        return this.c3.equals(fadeTo);
    }
    
    private boolean fadeForthToColor(final Color fadeTo) {
        this.c4 = Toolbox.getNextColor(this.c4, fadeTo, this.tolerance, this.fadeInc);
        return this.c4.equals(fadeTo);
    }
    
    private boolean fadeNetworkAddrToColor(final Color fadeTo) {
        this.c6 = Toolbox.getNextColor(this.c6, fadeTo, this.tolerance, this.fadeInc);
        return this.c6.equals(fadeTo);
    }
    
    private boolean fadeLinkAddrToColor(final Color fadeTo) {
        this.c7 = Toolbox.getNextColor(this.c7, fadeTo, this.tolerance, this.fadeInc);
        return this.c7.equals(fadeTo);
    }
    
    private boolean fadeOutAllToColor(final Color fadeTo1, final Color fadeTo2, final Color fadeTo3, final Color fadeTo4) {
        return this.fadeFirstToColor(fadeTo1, fadeTo4) && this.fadeSecondToColor(fadeTo2, fadeTo4) && this.fadeThirdToColor(fadeTo3);
    }
    
    private boolean fadeInAllToColor(final Color fadeTo1, final Color fadeTo2, final Color fadeTo3, final Color fadeTo4) {
        return this.fadeThirdToColor(fadeTo3) && this.fadeSecondToColor(fadeTo2, fadeTo4) && this.fadeFirstToColor(fadeTo1, fadeTo4);
    }
    
    private boolean fadeInMiddleTwo(final Color fadeTo, final Color fadeTo2) {
        return this.fadeThirdToColor(fadeTo) && this.fadeSecondToColor(fadeTo, fadeTo2);
    }
    
    private boolean fadeData(final Color fadeTo) {
        this.c5 = Toolbox.getNextColor(this.c5, fadeTo, this.tolerance, this.fadeInc);
        return this.c5.equals(fadeTo);
    }
    
    public void paint(final Graphics g) {
        g.setColor(this.bgcolor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (this.mode == 1) {
            if (this.fadeOutAllToColor(this.bgcolor, this.bgcolor, this.bgcolor, this.bgcolor)) {
                this.mode = 0;
            }
        }
        else if (this.mode == 2) {
            if (this.fadeInAllToColor(this.borderColor, this.borderColor, this.borderColor, Color.blue)) {
                this.mode = 0;
            }
        }
        else if (this.mode == 3) {
            if (this.fadeFirstToColor(this.bgcolor, this.bgcolor)) {
                this.mode = 0;
            }
        }
        else if (this.mode == 4) {
            if (this.fadeFirstToColor(this.borderColor, Color.blue)) {
                this.mode = 0;
            }
        }
        else if (this.mode == 5) {
            if (this.fadeForthToColor(this.bgcolor)) {
                this.mode = 0;
            }
        }
        else if (this.mode == 6) {
            if (this.fadeForthToColor(this.borderColor)) {
                this.mode = 0;
            }
        }
        else if (this.mode == 7) {
            if (this.fadeData(this.dataColor)) {
                this.mode = 0;
            }
        }
        else if (this.mode == 8) {
            if (this.fadeData(this.bgcolor)) {
                this.mode = 0;
            }
        }
        else if (this.mode == 9 && this.fadeInMiddleTwo(this.borderColor, Color.blue)) {
            this.mode = 0;
        }
        g.setColor(this.c1);
        g.drawLine(0, 0, 119, 0);
        g.drawLine(119, 0, 119, 39);
        g.drawLine(118, 38, 1, 38);
        g.drawLine(119, 39, 0, 39);
        g.drawLine(1, 38, 1, 1);
        g.drawLine(0, 39, 0, 0);
        g.drawString(this.layerNames[0], this.offsets[8], 21);
        g.setColor(this.c7);
        g.drawString(this.names[this.comp], this.offsets[this.comp], 34);
        g.setColor(this.c2);
        g.drawLine(120, 0, 239, 0);
        g.drawLine(239, 0, 239, 39);
        g.drawLine(238, 38, 121, 38);
        g.drawLine(239, 39, 120, 39);
        g.drawLine(121, 38, 121, 1);
        g.drawLine(120, 39, 120, 0);
        g.drawString(this.layerNames[1], this.offsets[9] + 120, 21);
        g.setColor(this.c6);
        g.drawString(this.names[this.finalDest], this.offsets[this.finalDest] + 120, 34);
        g.setColor(this.c3);
        g.drawLine(240, 0, 359, 0);
        g.drawLine(359, 0, 359, 39);
        g.drawLine(358, 38, 241, 38);
        g.drawLine(359, 39, 240, 39);
        g.drawLine(241, 38, 241, 1);
        g.drawLine(240, 39, 240, 0);
        g.drawString(this.layerNames[2], this.offsets[10] + 240, 21);
        g.setColor(this.c4);
        g.drawLine(360, 0, 479, 0);
        g.drawLine(479, 0, 479, 39);
        g.drawLine(478, 38, 361, 38);
        g.drawLine(479, 39, 360, 39);
        g.drawLine(361, 38, 361, 1);
        g.drawLine(360, 39, 360, 0);
        g.drawString(this.layerNames[3], this.offsets[11] + 360, 21);
        g.setColor(this.c5);
        g.drawString(this.data, 415, 34);
    }
}
