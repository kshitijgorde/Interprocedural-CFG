import java.awt.Label;
import java.awt.Component;
import java.awt.Button;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Event;
import java.awt.TextField;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ControlPanel
{
    final int m_nBoxWidth = 500;
    final int m_nBoxHeight = 60;
    Panel m_DialogPanel;
    AboutBox m_AboutBox;
    Main m_Main;
    TextField m_sFPS;
    
    public void setFPS(final int n) {
        this.m_sFPS.setText(Integer.toString(n));
    }
    
    public void show() {
        this.m_DialogPanel.show();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            this.m_Main.setTextColor((String)o);
        }
        else if ("+".equals(o)) {
            this.m_Main.incrementFPS();
        }
        else if ("-".equals(o)) {
            this.m_Main.decrementFPS();
        }
        else if ("Start".equals(o)) {
            this.m_Main.startDrawing();
        }
        else if ("Stop".equals(o)) {
            this.m_Main.stopDrawing();
        }
        else if ("Hide".equals(o)) {
            this.m_DialogPanel.hide();
        }
        else if ("About".equals(o)) {
            this.m_AboutBox = new AboutBox("About 3D Text Applet");
        }
        return true;
    }
    
    public ControlPanel(final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s, final Main main) {
        this.m_Main = main;
        (this.m_DialogPanel = new Panel()).setBackground(Color.lightGray);
        if (b) {
            this.m_DialogPanel.add(new Button("Start"));
            this.m_DialogPanel.add(new Button("Stop"));
        }
        if (b2) {
            this.m_DialogPanel.add(new Label("FPS:", 2));
            this.m_DialogPanel.add(this.m_sFPS = new TextField(Integer.toString(this.m_Main.getFPS()), 4));
            this.m_sFPS.disable();
            this.m_DialogPanel.add(new Button("-"));
            this.m_DialogPanel.add(new Button("+"));
        }
        if (b3) {
            this.m_DialogPanel.add(new Label("Color:", 2));
            final Choice choice;
            this.m_DialogPanel.add(choice = new Choice());
            choice.addItem("Blue");
            choice.addItem("Cyan");
            choice.addItem("Dark Gray");
            choice.addItem("Gray");
            choice.addItem("Green");
            choice.addItem("Light Gray");
            choice.addItem("Magenta");
            choice.addItem("Orange");
            choice.addItem("Pink");
            choice.addItem("Red");
            choice.addItem("White");
            choice.addItem("Yellow");
            choice.select(this.m_Main.getInitialColor());
        }
        if (b4) {
            this.m_DialogPanel.add(new Button("Hide"));
        }
        this.m_DialogPanel.add(new Button("About"));
        main.add("Center", this.m_DialogPanel);
    }
}
