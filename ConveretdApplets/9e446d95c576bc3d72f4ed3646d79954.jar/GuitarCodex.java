import java.awt.Graphics;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GuitarCodex extends Applet
{
    int dxR;
    int dxG;
    int dxB;
    int appR;
    int appG;
    int appB;
    Color appColor;
    Image[] fingerboardImg;
    int dx;
    int dy;
    ImageCanvas imgCanvas;
    ChordAnalyser analyser;
    
    public GuitarCodex() {
        this.fingerboardImg = new Image[2];
    }
    
    public void init() {
        try {
            this.dxR = Integer.parseInt(this.getParameter("backR"));
            this.dxG = Integer.parseInt(this.getParameter("backG"));
            this.dxB = Integer.parseInt(this.getParameter("backB"));
            this.appR = Integer.parseInt(this.getParameter("appColorR"));
            this.appG = Integer.parseInt(this.getParameter("appColorG"));
            this.appB = Integer.parseInt(this.getParameter("appColorB"));
        }
        catch (NumberFormatException ex) {
            this.dxR = 255;
            this.dxG = 255;
            this.dxB = 255;
            this.appR = 216;
            this.appG = 212;
            this.appB = 206;
        }
        this.setBackground(new Color(this.dxR, this.dxG, this.dxB));
        this.setLayout(new BorderLayout(4, 4));
        this.appColor = new Color(255, 255, 255);
        final Color color = new Color(220, 220, 150);
        this.loadImages();
        this.analyser = new ChordAnalyser();
        this.imgCanvas = new ImageCanvas(this.fingerboardImg, this.analyser);
        final AppletComponents appletComponents = new AppletComponents(this.getAppletContext(), this.appColor, this, this.analyser);
        final AppletCtrl appletCtrl = new AppletCtrl(appletComponents, this.imgCanvas, this.analyser);
        final FramedArea framedArea = new FramedArea(appletComponents.getScopeBtnLayout(), color);
        final FramedArea framedArea2 = new FramedArea(appletComponents.getResetAllBtnLayout(), new Color(140, 170, 210));
        final Panel panel = new Panel();
        panel.setLayout(null);
        appletComponents.getListLayout().setBounds(0, 0, 355, 185);
        panel.add(appletComponents.getListLayout());
        appletComponents.getDetails().setBounds(5, 185, 340, 165);
        panel.add(appletComponents.getDetails());
        framedArea.setBounds(0, 360, 350, 40);
        panel.add(framedArea);
        framedArea2.setBounds(0, 405, 350, 29);
        panel.add(framedArea2);
        this.add("North", appletComponents.getDisplaySettings());
        this.add("Center", panel);
        this.add("East", appletComponents.getScaleImgLayout());
        this.add("South", appletComponents.getInfoLayout());
    }
    
    ImageCanvas getImageCanvas() {
        return this.imgCanvas;
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void loadImages() {
        this.fingerboardImg[0] = this.getImage(this.getCodeBase(), "images/fingerboard7.jpg");
        this.fingerboardImg[1] = this.getImage(this.getCodeBase(), "images/l_fingerboard.jpg");
    }
    
    public String getAppletInfo() {
        return "Chord-Builder v1.0 (C) 2001 Robert Leh";
    }
}
