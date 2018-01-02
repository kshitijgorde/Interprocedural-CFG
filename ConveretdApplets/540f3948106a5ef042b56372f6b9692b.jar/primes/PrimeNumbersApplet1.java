// 
// Decompiled by Procyon v0.5.30
// 

package primes;

import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Button;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Label;
import java.applet.Applet;

public class PrimeNumbersApplet1 extends Applet
{
    boolean isStandalone;
    Label label1;
    TextArea textArea1;
    TextField textField1;
    Label label2;
    Button button1;
    Label label3;
    TextField textField2;
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public PrimeNumbersApplet1() {
        this.isStandalone = false;
        this.label1 = new Label();
        this.textArea1 = new TextArea();
        this.textField1 = new TextField();
        this.label2 = new Label();
        this.button1 = new Button();
        this.label3 = new Label();
        this.textField2 = new TextField();
    }
    
    public void init() {
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.textArea1.setText("");
    }
    
    private void jbInit() throws Exception {
        this.label1.setFont(new Font("Dialog", 0, 36));
        this.label1.setText("Prime Numbers");
        this.label1.setBounds(new Rectangle(71, 11, 255, 47));
        this.textArea1.setText("textArea1");
        this.textArea1.setBounds(new Rectangle(188, 66, 143, 318));
        this.setLayout(null);
        this.textField1.setText("100");
        this.textField1.setBounds(new Rectangle(45, 121, 99, 28));
        this.label2.setText("Number Of Primes");
        this.label2.setBounds(new Rectangle(41, 88, 106, 27));
        this.button1.setLabel("Go");
        this.button1.setBounds(new Rectangle(63, 287, 63, 29));
        this.button1.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent e) {
                PrimeNumbersApplet1.this.button1_mouseReleased(e);
            }
        });
        this.label3.setText("Lower Bound");
        this.label3.setBounds(new Rectangle(56, 183, 76, 27));
        this.textField2.setText("0");
        this.textField2.setBounds(new Rectangle(45, 217, 99, 28));
        this.add(this.label1, null);
        this.add(this.textArea1, null);
        this.add(this.button1, null);
        this.add(this.label2, null);
        this.add(this.textField1, null);
        this.add(this.textField2, null);
        this.add(this.label3, null);
    }
    
    public String getAppletInfo() {
        return "Applet Information";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    void button1_mouseReleased(final MouseEvent e) {
        final long lowerBound = Long.parseLong(this.textField2.getText());
        final PrimeNumbers p = new PrimeNumbers(lowerBound);
        final long numberToGenerate = Long.parseLong(this.textField1.getText());
        this.textArea1.setText("");
        for (int i = 0; i < numberToGenerate; ++i) {
            final Long lg = (Long)p.next();
            this.textArea1.append(String.valueOf(String.valueOf(lg.toString())).concat("\n"));
        }
    }
}
