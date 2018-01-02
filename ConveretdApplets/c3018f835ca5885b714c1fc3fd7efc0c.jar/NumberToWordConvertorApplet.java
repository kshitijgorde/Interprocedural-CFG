import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NumberToWordConvertorApplet extends Applet
{
    private TextField textField;
    private TextArea display;
    
    public void init() {
        this.setBackground(Color.white);
        final RaisedPanel raisedPanel = new RaisedPanel(30, 14, 14, 14, 6);
        raisedPanel.setBackground(new Color(50, 100, 225));
        raisedPanel.setLayout(new BorderLayout(5, 5));
        raisedPanel.setBorder(true);
        raisedPanel.setLabel("Number to word Convertor");
        (this.textField = new TextField(10)).setBackground(Color.white);
        this.textField.setForeground(Color.black);
        final RaisedPanel raisedPanel2 = new RaisedPanel(8, 8, 8, 8, 5);
        raisedPanel2.add(new Label("Eneter a number up to 1 billion"));
        raisedPanel2.add(this.textField);
        raisedPanel2.add(new Button("Convert"));
        raisedPanel.add(raisedPanel2);
        raisedPanel.add("Center", raisedPanel2);
        (this.display = new TextArea(4, 30)).setBackground(Color.white);
        this.display.setForeground(Color.black);
        raisedPanel.add("South", this.display);
        this.add(raisedPanel);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                boolean b = false;
                final String text = this.textField.getText();
                int int1 = 0;
                try {
                    int1 = Integer.parseInt(text);
                }
                catch (Exception ex) {
                    b = true;
                    this.display.setText(" Invalid number");
                }
                if (int1 < 0) {
                    this.display.setText(" You are bankruptcy!");
                    break;
                }
                if (!b) {
                    this.display.setText(WordConvertor.intName(int1));
                    break;
                }
                break;
            }
            case 1004: {
                if (event.target == this.textField) {
                    this.textField.selectAll();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
