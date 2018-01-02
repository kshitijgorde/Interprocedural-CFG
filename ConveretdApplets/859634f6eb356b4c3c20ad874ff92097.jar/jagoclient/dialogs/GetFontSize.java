// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.dialogs;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import jagoclient.gui.ButtonAction;
import java.awt.Toolkit;
import jagoclient.gui.ChoiceAction;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.TextFieldAction;
import java.awt.Component;
import jagoclient.gui.MyLabel;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jagoclient.Global;
import java.awt.Canvas;
import java.awt.Choice;
import jagoclient.gui.IntField;
import java.awt.TextField;
import jagoclient.gui.CloseDialog;

public class GetFontSize extends CloseDialog
{
    String FontTag;
    String SizeTag;
    TextField FontName;
    IntField FontSize;
    Choice Fonts;
    Choice Mode;
    Canvas Example;
    String E;
    
    public GetFontSize(final String fontTag, final String s, final String sizeTag, final int n, final boolean b) {
        super(Global.frame(), Global.resourceString("Font_Size"), b);
        this.E = Global.resourceString("10_good_letters__A_J_");
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 2));
        panel.add(new MyLabel(Global.resourceString("Font_name")));
        panel.add(this.FontName = new TextFieldAction(this, "FontName"));
        this.FontName.setText(String.valueOf(rene.gui.Global.getParameter(fontTag, s)));
        panel.add(new MyLabel(Global.resourceString("Available_Fonts")));
        panel.add(this.Fonts = new ChoiceAction(this, Global.resourceString("Fonts")));
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        if (fontList != null) {
            for (int i = 0; i < fontList.length; ++i) {
                this.Fonts.add(fontList[i]);
            }
        }
        else {
            this.Fonts.add("Dialog");
            this.Fonts.add("SansSerif");
            this.Fonts.add("Serif");
            this.Fonts.add("Monospaced");
            this.Fonts.add("DialogInput");
        }
        this.Fonts.select(this.FontName.getText());
        panel.add(new MyLabel(Global.resourceString("Mode")));
        panel.add(this.Mode = new ChoiceAction(this, Global.resourceString("Mode")));
        this.Mode.add(Global.resourceString("Plain"));
        this.Mode.add(Global.resourceString("Bold"));
        this.Mode.add(Global.resourceString("Italic"));
        final String text = this.FontName.getText();
        if (text.startsWith("Bold")) {
            this.FontName.setText(text.substring(4));
            this.Mode.select(1);
        }
        else if (text.startsWith("Italic")) {
            this.FontName.setText(text.substring(5));
            this.Mode.select(2);
        }
        else {
            this.Mode.select(0);
        }
        panel.add(new MyLabel(Global.resourceString("Font_size")));
        panel.add(this.FontSize = new IntField(this, "FontSize", rene.gui.Global.getParameter(sizeTag, n)));
        this.add("North", panel);
        this.add("Center", this.Example = new ExampleCanvas(this));
        final Panel panel2 = new Panel();
        panel2.add(new ButtonAction(this, Global.resourceString("OK")));
        this.add("South", panel2);
        this.FontTag = fontTag;
        this.SizeTag = sizeTag;
        Global.setpacked(this, "getfontsize", 200, 150);
        this.validate();
    }
    
    public void doAction(final String s) {
        if (Global.resourceString("OK").equals(s)) {
            Global.notewindow(this, "getfontsize");
            String s2 = this.FontName.getText();
            if (this.mode() == 1) {
                s2 = "Bold" + s2;
            }
            else if (this.mode() == 2) {
                s2 = "Italic" + s2;
            }
            rene.gui.Global.setParameter(this.FontTag, s2);
            rene.gui.Global.setParameter(this.SizeTag, this.FontSize.value(3, 50));
            Global.createfonts();
            this.setVisible(false);
            this.dispose();
            this.tell();
        }
        else {
            super.doAction(s);
        }
        this.Example.repaint();
    }
    
    public void itemAction(final String s, final boolean b) {
        this.FontName.setText(this.Fonts.getSelectedItem());
        this.Example.repaint();
    }
    
    int mode() {
        if (this.Mode.getSelectedItem().equals(Global.resourceString("Bold"))) {
            return 1;
        }
        if (this.Mode.getSelectedItem().equals(Global.resourceString("Italic"))) {
            return 2;
        }
        return 0;
    }
    
    public void example(final Graphics graphics, final int n, final int n2) {
        graphics.setFont(new Font(this.FontName.getText(), this.mode(), this.FontSize.value(3, 50)));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(this.E, (n - fontMetrics.stringWidth(this.E)) / 2, (n2 - fontMetrics.getAscent()) / 2 - 1);
    }
    
    public void tell() {
    }
}
