import java.awt.Container;
import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import util102.gui.BorderPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class FenetreAide extends Frame
{
    TextArea texteAide;
    Button bouttonOK;
    boolean ouverte;
    
    FenetreAide(final String s) {
        super(s);
        this.ouverte = false;
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(0, 0));
        final BorderPanel borderPanel = new BorderPanel(10, 10, 10, 10, 3);
        ((Container)borderPanel).setLayout(new BorderLayout(5, 5));
        final BorderPanel borderPanel2 = new BorderPanel(5, 5, 5, 5, 7);
        ((Container)borderPanel2).setLayout(new GridLayout(1, 1, 0, 0));
        (this.texteAide = new TextArea("JavaDraw, by Vincent Zimmermann at NCTech (Lyon, France)\nVersion 1.0.5, march 1998\nNCTech home : http://www.nctech.fr\nMy HomePage : http://www.nctech.fr/Zimmermann\n--------------------------------------------------------------------------\n\nTo use JavaDraw, select a tool in the Tool window. Then you can draw shapes\nin the edit area (in your browser).\n\nOutline and fill colors can be choose in a list or in RGB.\nYou can define a transparent outline or fill.\nTo obtain circles and squares, press SHIFT.\n\nThe \"Select\" tool is important, it allow you to select a shape, and then\nmove it, change its color, outline thickness, delete it... etc.\n\nWhen you finish your work, you can generate the coresponding Java code.\nselect menu \"Object\", then \"Generate code\"\n(This code have to be compiled).\n\nJavaDraw is a Java Application too, if you execute JavaDraw as an application\nyou will be able to save your drawing (.jdr file) and the Java code.\n\n\nKeys : \nArrow keys  : move the object, 1 pixel.\nPage UP     : generate the Java code.\nPage DOWN   : delete selected object.\n\nComment :\nTo validate text fields, press ENTER.\n\n--------------------------------------------------------------------------\n\nJavaDraw is a FreeWare ;-)\nIf you like JavaDraw, please send me an email (zimmerma@nctech.fr)\nDevelopped on Apple Macintosh and PC with sun JDK 1.0.2.\nYour comments at : zimmerma@nctech.fr\n\nVersion history :Version 1.0, april 1997\nVersion 1.0.5, march 1998\n", 20, 70)).setFont(new Font("Helvetica", 0, 11));
        this.texteAide.setBackground(Color.gray);
        this.texteAide.setForeground(Color.white);
        this.texteAide.setEditable(false);
        ((Container)borderPanel2).add(this.texteAide);
        ((Container)borderPanel).add("Center", (Component)borderPanel2);
        final BorderPanel borderPanel3 = new BorderPanel(5, 5, 5, 5, 7);
        ((Container)borderPanel3).setLayout(new GridLayout(1, 1, 0, 0));
        ((Container)borderPanel3).add(this.bouttonOK = new Button("Ok"));
        ((Container)borderPanel).add("South", (Component)borderPanel3);
        this.add("Center", (Component)borderPanel);
        this.validate();
        this.pack();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.ouverte = false;
            this.hide();
            return true;
        }
        return false;
    }
}
