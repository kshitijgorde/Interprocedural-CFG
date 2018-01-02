import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends Frame
{
    public Button be;
    public TextArea k;
    public boolean bd;
    public String bc;
    public String ba;
    public String a9;
    public String a8;
    
    public final void ax() {
        this.k.appendText("JPilot jIRC applet version " + this.a9 + "\n");
        this.k.appendText("==========================================\n");
        if (this.bd) {
            this.k.appendText("This applet is registered to " + this.ba + "\n");
            this.k.appendText(String.valueOf(this.bc) + "\n");
            this.k.appendText(String.valueOf(this.a8) + "\n");
            this.k.appendText("\n");
            this.k.appendText("Any unauthorized use of the applet in other domains is illegal.");
        }
        else {
            this.k.appendText("**** Warning ****\n");
            this.k.appendText("This is an unregistered copy of JPilot jIRC applet.\n");
            this.k.appendText("If you find it useful, please register your copy at JPilot web site :\n" + this.bc);
            this.k.appendText(String.valueOf(this.a8) + "\n");
        }
        this.k.appendText("\n");
        this.k.appendText("\n");
        this.k.appendText("JPilot Software is not responsible for any contents of the web site it is being hosted.\n\n");
        this.k.appendText("JPilot will not be liable for any direct or indirect damages as \nthe results of using this software.");
    }
    
    public j(final boolean bd, final String bc, final String ba, final String a8, final String a9) {
        this.bd = bd;
        this.bc = bc;
        this.ba = ba;
        this.a8 = a8;
        this.a9 = a9;
        (this.k = new TextArea()).setBackground(Color.white);
        this.k.setForeground(Color.black);
        this.be = new Button("OK");
        final Panel panel = new Panel();
        panel.add(this.be);
        final Canvas canvas = new Canvas();
        canvas.resize(10, 1);
        canvas.setBackground(Color.white);
        final Canvas canvas2 = new Canvas();
        canvas2.resize(10, 1);
        canvas2.setBackground(Color.white);
        final Canvas canvas3 = new Canvas();
        canvas3.resize(1, 10);
        canvas3.setBackground(Color.white);
        final Canvas canvas4 = new Canvas();
        canvas4.resize(1, 10);
        canvas4.setBackground(Color.white);
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout(10, 10));
        panel2.add("Center", this.k);
        panel2.add("East", new Canvas());
        panel2.add("West", new Canvas());
        panel2.add("North", new Canvas());
        panel2.add("South", new Canvas());
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel3.add("Center", panel2);
        panel3.add("North", canvas);
        panel3.add("South", canvas2);
        panel3.add("West", canvas3);
        panel3.add("East", canvas4);
        final Panel panel4 = new Panel();
        panel4.setLayout(new BorderLayout());
        panel4.add("Center", panel3);
        panel4.add("South", panel);
        this.setTitle("About JPilot jIRC applet");
        this.setLayout(new BorderLayout());
        this.add("Center", panel4);
        this.resize(400, 250);
        this.setBackground(Color.lightGray);
        this.ax();
        this.repaint();
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
        }
        else if (event.id == 1001 && event.target == this.be) {
            this.dispose();
        }
        return true;
    }
}
