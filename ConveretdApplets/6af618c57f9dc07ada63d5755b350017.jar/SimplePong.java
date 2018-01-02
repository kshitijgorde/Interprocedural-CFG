import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SimplePong extends Applet
{
    public void init() {
        int intValue = 0;
        int intValue2 = 0;
        int intValue3 = 0;
        int n = 300;
        int n2 = 80;
        int intValue4 = 5;
        String parameter = "backing.gif";
        if (this.getParameter("Red") != null) {
            intValue = new Integer(this.getParameter("Red").trim());
        }
        if (this.getParameter("Blue") != null) {
            intValue2 = new Integer(this.getParameter("Blue").trim());
        }
        if (this.getParameter("Green") != null) {
            intValue3 = new Integer(this.getParameter("Green").trim());
        }
        if (this.getParameter("Width") != null) {
            n = new Integer(this.getParameter("Width").trim()) - 20;
        }
        if (this.getParameter("Height") != null) {
            n2 = new Integer(this.getParameter("Height").trim()) - 20;
        }
        if (this.getParameter("Difficulty") != null) {
            intValue4 = new Integer(this.getParameter("Difficulty").trim());
        }
        if (this.getParameter("BackImage") != null) {
            parameter = this.getParameter("BackImage");
        }
        this.setBackground(new Color(intValue, intValue3, intValue2));
        final PongTable pongTable = new PongTable(n, n2, this.getImage(this.getCodeBase(), "ball.gif"), this.getImage(this.getCodeBase(), "panel1.gif"), this.getImage(this.getCodeBase(), "panel2.gif"), this.getImage(this.getCodeBase(), parameter), this.getImage(this.getCodeBase(), "Toplay.gif"), intValue4);
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.add(pongTable);
        this.add(panel, "Center");
    }
}
