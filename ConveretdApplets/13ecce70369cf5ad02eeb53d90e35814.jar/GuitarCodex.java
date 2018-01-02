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
    Color bgColor;
    Color borderColor;
    Color detailColor;
    Color displayColor;
    Color chordScaleColor;
    Color scopeColor;
    Color showResetColor;
    Color midiColor;
    Image[] fingerboardImg;
    ImageCanvas imgCanvas;
    ChordAnalyser analyser;
    
    public GuitarCodex() {
        this.fingerboardImg = new Image[2];
    }
    
    public void init() {
        final String parameter = this.getParameter("applet-bgcolor");
        if (parameter != null) {
            this.bgColor = this.parseColorStr(parameter);
        }
        else {
            this.bgColor = Color.white;
        }
        final String parameter2 = this.getParameter("border-color");
        if (parameter2 != null) {
            this.borderColor = this.parseColorStr(parameter2);
        }
        else {
            this.borderColor = Color.black;
        }
        final String parameter3 = this.getParameter("display-bgcolor");
        if (parameter3 != null) {
            this.displayColor = this.parseColorStr(parameter3);
        }
        else {
            this.displayColor = Color.white;
        }
        final String parameter4 = this.getParameter("chord-scale-bgcolor");
        if (parameter4 != null) {
            this.chordScaleColor = this.parseColorStr(parameter4);
        }
        else {
            this.chordScaleColor = Color.white;
        }
        final String parameter5 = this.getParameter("detail-bgcolor");
        if (parameter5 != null) {
            this.detailColor = this.parseColorStr(parameter5);
        }
        else {
            this.detailColor = Color.white;
        }
        final String parameter6 = this.getParameter("scope-bgcolor");
        if (parameter6 != null) {
            this.scopeColor = this.parseColorStr(parameter6);
        }
        else {
            this.scopeColor = Color.white;
        }
        final String parameter7 = this.getParameter("shownote-reset-bgcolor");
        if (parameter7 != null) {
            this.showResetColor = this.parseColorStr(parameter7);
        }
        else {
            this.showResetColor = Color.white;
        }
        final String parameter8 = this.getParameter("midi-bgcolor");
        if (parameter8 != null) {
            this.midiColor = this.parseColorStr(parameter8);
        }
        else {
            this.midiColor = Color.white;
        }
        this.setBackground(this.bgColor);
        this.setLayout(new BorderLayout(4, 4));
        this.loadImages();
        this.analyser = new ChordAnalyser();
        this.imgCanvas = new ImageCanvas(this.fingerboardImg, this.analyser);
        final MidiGuitar midiGuitar = new MidiGuitar(this, this.midiColor);
        final AppletComponents appletComponents = new AppletComponents(this.getAppletContext(), this.bgColor, this, this.analyser);
        final AppletCtrl appletCtrl = new AppletCtrl(appletComponents, this.imgCanvas, this.analyser, this, midiGuitar);
        final FramedArea framedArea = new FramedArea(appletComponents.getDisplaySettings(), this.displayColor, this.borderColor);
        final FramedArea framedArea2 = new FramedArea(appletComponents.getListLayout(), this.chordScaleColor, this.borderColor);
        final FramedArea framedArea3 = new FramedArea(appletComponents.getDetails(), this.detailColor, this.borderColor);
        final FramedArea framedArea4 = new FramedArea(appletComponents.getScopeBtnLayout(), this.scopeColor, this.borderColor);
        final FramedArea framedArea5 = new FramedArea(appletComponents.getResetAllBtnLayout(), this.showResetColor, this.borderColor);
        final FramedArea framedArea6 = new FramedArea(appletComponents.getScaleImgLayout(), Color.white, this.borderColor);
        final FramedArea framedArea7 = new FramedArea(midiGuitar.getMidiConsole(), this.midiColor, this.borderColor);
        final Panel panel = new Panel();
        panel.setLayout(null);
        framedArea2.setBounds(0, 0, 343, 190);
        panel.add(framedArea2);
        framedArea3.setBounds(0, 195, 343, 178);
        panel.add(framedArea3);
        framedArea4.setBounds(0, 378, 343, 40);
        panel.add(framedArea4);
        framedArea5.setBounds(0, 423, 343, 29);
        panel.add(framedArea5);
        this.add("North", framedArea);
        this.add("Center", panel);
        this.add("East", framedArea6);
        this.add("South", framedArea7);
    }
    
    private Color parseColorStr(final String s) {
        if (s.length() == 7 && s.charAt(0) == '#') {
            try {
                return new Color(Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
            }
            catch (Exception ex) {}
        }
        return Color.white;
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
