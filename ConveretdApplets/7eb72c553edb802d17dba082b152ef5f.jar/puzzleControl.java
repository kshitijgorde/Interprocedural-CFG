import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Event;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class puzzleControl extends Applet
{
    Applet imagePuzzle;
    appletController ac;
    Panel pnlButtons;
    Button btnStart;
    Button btnReset;
    Panel pnlLabels;
    Label lblTries;
    Label lblCount;
    Label lblStatus;
    
    void puzzleControl_MouseEnter(final Event event) {
        this.repaint();
    }
    
    void btnReset_Clicked(final Event event) {
        this.setCount(0);
        this.setStatus("");
        if (this.imagePuzzle != null) {
            if (this.imagePuzzle instanceof puzzle) {
                ((puzzle)this.imagePuzzle).btnResetClicked();
            }
        }
        else {
            System.out.println("Image puzzle found to be null");
        }
    }
    
    void btnStart_Clicked(final Event event) {
        this.setCount(0);
        this.setStatus("");
        if (this.imagePuzzle != null) {
            if (this.imagePuzzle instanceof puzzle) {
                ((puzzle)this.imagePuzzle).btnStartClicked();
            }
        }
        else {
            System.out.println("Image puzzle found to be null");
        }
    }
    
    public void setCount(final int count) {
        this.lblCount.setText(Integer.toString(count));
    }
    
    public void setStatus(final String text) {
        this.lblStatus.setText(text);
    }
    
    public void init() {
        super.init();
        this.setLayout(new GridLayout(2, 1, 0, 4));
        this.addNotify();
        this.resize(214, 56);
        (this.pnlButtons = new Panel()).setLayout(new GridLayout(1, 2, 30, 40));
        this.pnlButtons.reshape(0, 0, 214, 26);
        this.add(this.pnlButtons);
        (this.btnStart = new Button("Start")).reshape(0, 0, 92, 26);
        this.pnlButtons.add(this.btnStart);
        (this.btnReset = new Button("Reset")).reshape(122, 0, 92, 26);
        this.pnlButtons.add(this.btnReset);
        (this.pnlLabels = new Panel()).setLayout(new GridLayout(1, 2, 5, 5));
        this.pnlLabels.reshape(0, 30, 214, 26);
        this.add(this.pnlLabels);
        (this.lblTries = new Label("Tries:")).reshape(0, 0, 68, 26);
        this.pnlLabels.add(this.lblTries);
        (this.lblCount = new Label("")).reshape(73, 0, 68, 26);
        this.pnlLabels.add(this.lblCount);
        (this.lblStatus = new Label("")).reshape(146, 0, 68, 26);
        this.pnlLabels.add(this.lblStatus);
        appletController.setController(this);
        this.imagePuzzle = appletController.getJigSaw();
        final String param = this.getParameter("bgColor");
        Color c;
        if (param != null) {
            c = this.parseColorString(param);
        }
        else {
            c = Color.white;
        }
        this.lblStatus.setBackground(c);
        this.lblTries.setBackground(c);
        this.lblCount.setBackground(c);
        this.pnlButtons.setBackground(c);
        this.pnlLabels.setBackground(c);
        this.setBackground(c);
        this.lblStatus.setFont(new Font("TimesRoman", 0, 12));
    }
    
    private Color parseColorString(final String colorString) {
        Color returnColor;
        if (colorString.equalsIgnoreCase("red")) {
            returnColor = Color.red;
        }
        else if (colorString.equalsIgnoreCase("blue")) {
            returnColor = Color.blue;
        }
        else if (colorString.equalsIgnoreCase("green")) {
            returnColor = Color.green;
        }
        else if (colorString.equalsIgnoreCase("yellow")) {
            returnColor = Color.yellow;
        }
        else if (colorString.equalsIgnoreCase("white")) {
            returnColor = Color.white;
        }
        else if (colorString.equalsIgnoreCase("orange")) {
            returnColor = Color.orange;
        }
        else if (colorString.equalsIgnoreCase("cyan")) {
            returnColor = Color.cyan;
        }
        else if (colorString.equalsIgnoreCase("magenta")) {
            returnColor = Color.magenta;
        }
        else if (colorString.equalsIgnoreCase("gray")) {
            returnColor = Color.gray;
        }
        else if (colorString.equalsIgnoreCase("lightGray")) {
            returnColor = Color.lightGray;
        }
        else if (colorString.equalsIgnoreCase("darkGray")) {
            returnColor = Color.darkGray;
        }
        else if (colorString.equalsIgnoreCase("pink")) {
            returnColor = Color.pink;
        }
        else if (colorString.length() == 6) {
            try {
                final int R = Integer.valueOf(colorString.substring(0, 2), 16);
                final int G = Integer.valueOf(colorString.substring(2, 4), 16);
                final int B = Integer.valueOf(colorString.substring(4, 6), 16);
                returnColor = new Color(R, G, B);
            }
            catch (NumberFormatException ex) {
                returnColor = null;
            }
        }
        else {
            returnColor = null;
        }
        return returnColor;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.btnStart && event.id == 1001) {
            this.btnStart_Clicked(event);
            return true;
        }
        if (event.target == this.btnReset && event.id == 1001) {
            this.btnReset_Clicked(event);
            return true;
        }
        if (event.target == this && event.id == 504) {
            this.puzzleControl_MouseEnter(event);
            return true;
        }
        return super.handleEvent(event);
    }
}
