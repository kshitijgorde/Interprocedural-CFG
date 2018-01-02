import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Calc1 extends Applet
{
    private NumPanel nums;
    private OperationPanel myoperation;
    static TextField display;
    static String s;
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(105, 105, 105));
        (this.myoperation = new OperationPanel()).setFont(new Font("SansSerif", 1, 14));
        this.add("East", this.myoperation);
        (this.nums = new NumPanel()).setFont(new Font("SansSerif", 1, 14));
        this.add("West", this.nums);
        (Calc1.display = new TextField()).setEditable(false);
        Calc1.display.setBackground(Color.yellow);
        Calc1.display.setFont(new Font("SansSerif", 1, 14));
        this.add("North", Calc1.display);
    }
    
    static {
        Calc1.s = new String();
    }
}
