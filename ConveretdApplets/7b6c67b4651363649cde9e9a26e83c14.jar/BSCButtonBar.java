import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.net.URL;
import java.awt.Event;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BSCButtonBar extends Applet
{
    private String P_Target;
    private Color P_BGColour;
    private int NumButs;
    
    public void start() {
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "Target", "String", "Target Frame" }, { "Iconx", "String", "Icon Filename relative to BSBButton.Class file" }, { "Jumpx", "String", "URL to jump to relative to the document URL" }, { "Alignment", "Integer", "0=Left 1=Center 2=Right" }, { "Spacing", "Integer", "Distance between buttons" } };
        return info;
    }
    
    public void stop() {
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        return true;
    }
    
    public String getAppletInfo() {
        return "Name: BSCButtonBar\r\n" + "Author: Simon Daykin\r\n" + "Created with Microsoft Visual J++ Version 1.0";
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        return true;
    }
    
    public boolean action(final Event e, final Object o) {
        try {
            this.getAppletContext().showDocument(new URL(this.getDocumentBase(), o.toString()), this.P_Target);
        }
        catch (Exception ex) {}
        return true;
    }
    
    public void destroy() {
    }
    
    public void init() {
        Color P_ButColour = Color.lightGray;
        int P_Align = 0;
        int P_Spacing = 5;
        String param = this.getParameter("Target");
        if (param != null) {
            this.P_Target = param;
        }
        param = this.getParameter("Alignment");
        if (param != null) {
            P_Align = Integer.parseInt(param);
        }
        param = this.getParameter("Spacing");
        if (param != null) {
            P_Spacing = Integer.parseInt(param);
        }
        this.setLayout(new FlowLayout(P_Align, P_Spacing, P_Spacing));
        param = this.getParameter("BGColour");
        if (param != null) {
            this.P_BGColour = new Color(Integer.parseInt(param, 16));
        }
        param = this.getParameter("ButColour");
        if (param != null) {
            P_ButColour = new Color(Integer.parseInt(param, 16));
        }
        this.NumButs = 0;
        boolean MoreButs = true;
        while (MoreButs) {
            if (this.getParameter("Icon" + Integer.toString(this.NumButs + 1)) != null) {
                ++this.NumButs;
                param = this.getParameter("Icon" + Integer.toString(this.NumButs));
                final Image P_Icon = this.getImage(this.getDocumentBase(), param);
                final String P_Jump;
                param = (P_Jump = this.getParameter("Jump" + Integer.toString(this.NumButs)));
                final BSCButton NewBut = new BSCButton(P_Icon, P_Jump);
                this.add(NewBut);
                NewBut.setColor(P_ButColour);
            }
            else {
                MoreButs = false;
            }
        }
    }
    
    public void paint(final Graphics g) {
        g.setColor(this.P_BGColour);
        g.fillRect(0, 0, this.size().width, this.size().height);
    }
    
    public BSCButtonBar() {
        this.P_Target = "_top";
        this.P_BGColour = Color.lightGray;
    }
}
