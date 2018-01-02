// 
// Decompiled by Procyon v0.5.30
// 

package viewer.tools;

import java.awt.Component;
import java.awt.Color;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Event;
import java.awt.TextField;
import java.awt.Choice;
import viewer.RoundButton;
import viewer.RaisedPanel;

public class Tools_ZoomPanel extends RaisedPanel
{
    public RoundButton zoomIn;
    public RoundButton zoomOut;
    public Choice zoomChoice;
    private int zoom;
    private TextField inicator;
    
    public int getZoom() {
        return this.zoom;
    }
    
    public void reset() {
        this.zoom = 100;
        this.zoomChoice.select(3);
        this.inicator.setText("Zoom = " + this.zoom + "%");
    }
    
    public boolean action(final Event event, final Object o) {
        final String string = o.toString();
        if (event.target == this.zoomChoice) {
            if (string == "300%") {
                this.zoom = 300;
            }
            else if (string == "200%") {
                this.zoom = 200;
            }
            else if (string == "150%") {
                this.zoom = 150;
            }
            else if (string == "100%") {
                this.zoom = 100;
            }
            else if (string == "75%") {
                this.zoom = 75;
            }
            else if (string == "50%") {
                this.zoom = 50;
            }
            else if (string == "25%") {
                this.zoom = 25;
            }
            else if (string == "10%") {
                this.zoom = 10;
            }
            this.inicator.setText("Zoom = " + this.zoom + "%");
        }
        else if (event.target == this.zoomIn) {
            this.zoom += 10;
            if (this.zoom < 500) {
                this.inicator.setText("Zoom = " + this.zoom + "%");
            }
            else {
                this.zoom = 500;
                this.inicator.setText("Max zoom = 500%");
            }
        }
        else if (event.target == this.zoomOut) {
            this.zoom -= 10;
            if (this.zoom > 10) {
                this.inicator.setText("Zoom = " + this.zoom + "%");
            }
            else {
                this.zoom = 10;
                this.inicator.setText("Min zoom = 10%");
            }
        }
        return false;
    }
    
    Tools_ZoomPanel() {
        super(20, 10, 10, 10, 0);
        super.setLabel("Zoom");
        super.setBorder(true);
        this.zoom = 100;
        this.setLayout(new GridLayout(3, 1, 8, 8));
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2, 4, 4));
        (this.zoomOut = new RoundButton("Out")).setColor(Color.orange);
        this.zoomOut.setForeground(Color.black);
        panel.add(this.zoomOut);
        (this.zoomIn = new RoundButton("In")).setColor(Color.orange);
        this.zoomIn.setForeground(Color.black);
        panel.add(this.zoomIn);
        this.add(panel);
        (this.inicator = new TextField("Zoom value")).setEditable(false);
        this.inicator.setBackground(Color.white);
        this.inicator.setForeground(Color.black);
        this.add(this.inicator);
        (this.zoomChoice = new Choice()).setForeground(Color.black);
        this.zoomChoice.addItem("300%");
        this.zoomChoice.addItem("200%");
        this.zoomChoice.addItem("150%");
        this.zoomChoice.addItem("100%");
        this.zoomChoice.addItem("75%");
        this.zoomChoice.addItem("50%");
        this.zoomChoice.addItem("25%");
        this.zoomChoice.addItem("10%");
        this.zoomChoice.select(3);
        this.add(this.zoomChoice);
    }
}
