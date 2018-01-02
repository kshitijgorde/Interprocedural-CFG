import java.util.StringTokenizer;
import java.awt.event.ItemEvent;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.TextArea;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SIRfont extends Applet implements ItemListener
{
    boolean started;
    String message;
    Choice choicebox;
    Choice sizechoice;
    TextArea textbox;
    Checkbox checkbold;
    Checkbox checkitalic;
    
    public void init() {
        this.setBackground(this.parmgetcolor("color", "255,255,255"));
        this.add(this.textbox = new TextArea("Type Example Text Here"));
        this.textbox.setBackground(new Color(255, 255, 255));
        this.choicebox = new Choice();
        final String[] fontList = this.getToolkit().getFontList();
        for (int i = 0; i < fontList.length; ++i) {
            this.choicebox.add(fontList[i]);
        }
        this.add(this.choicebox);
        this.choicebox.addItemListener(this);
        (this.sizechoice = new Choice()).add("8");
        this.sizechoice.add("9");
        this.sizechoice.add("10");
        this.sizechoice.add("11");
        for (int j = 12; j < 29; j += 2) {
            this.sizechoice.add(Integer.toString(j));
        }
        this.sizechoice.add("36");
        this.sizechoice.add("48");
        this.sizechoice.add("72");
        this.add(this.sizechoice);
        this.sizechoice.select("12");
        this.sizechoice.addItemListener(this);
        this.textbox.setFont(new Font(fontList[0], 0, 12));
        this.add(this.checkbold = new Checkbox("Bold"));
        this.checkbold.addItemListener(this);
        this.checkbold.setFont(new Font(this.checkbold.getFont().getName(), 1, this.checkbold.getFont().getSize()));
        this.checkbold.setForeground(this.parmgetcolor("text", "0,0,0"));
        this.add(this.checkitalic = new Checkbox("Italic"));
        this.checkitalic.addItemListener(this);
        this.checkitalic.setFont(new Font(this.checkitalic.getFont().getName(), 2, this.checkitalic.getFont().getSize()));
        this.checkitalic.setForeground(this.parmgetcolor("text", "0,0,0"));
    }
    
    public void startUp() {
        this.started = true;
        this.choicebox.setBounds(10, 10, 220, this.choicebox.size().height);
        this.sizechoice.setBounds(240, 10, 50, this.sizechoice.size().height);
        this.checkbold.setBounds(300, 10, this.checkbold.size().width, this.checkbold.size().height);
        this.checkitalic.setBounds(360, 10, this.checkitalic.size().width, this.checkitalic.size().height);
        this.textbox.setBounds(10, 40, 400, 170);
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void paint(final Graphics graphics) {
        if (!this.started) {
            this.startUp();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        int n = 0;
        if (this.checkbold.getState()) {
            n = 1;
        }
        if (this.checkitalic.getState()) {
            n = 2;
        }
        if (this.checkbold.getState() && this.checkitalic.getState()) {
            n = 3;
        }
        this.textbox.setFont(new Font(this.choicebox.getSelectedItem(), n, Integer.parseInt(this.sizechoice.getSelectedItem())));
    }
    
    public Color parmgetcolor(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.parmgetstr(s, s2), ",");
        return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
    }
    
    public String parmgetstr(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        String s3;
        if (parameter == null) {
            s3 = s2;
        }
        else {
            s3 = parameter;
        }
        return s3;
    }
    
    public SIRfont() {
        this.started = false;
    }
}
