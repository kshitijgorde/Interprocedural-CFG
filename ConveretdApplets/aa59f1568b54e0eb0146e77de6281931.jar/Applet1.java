import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Applet1 extends Applet implements ActionListener
{
    Button button1;
    Label label1;
    
    public void init() {
        this.setLayout(null);
        this.setSize(470, 266);
        this.setBackground(Color.lightGray);
        (this.button1 = new Button()).setLabel("Select font");
        this.button1.setBounds(180, 228, 85, 36);
        this.button1.setBackground(Color.lightGray);
        this.add(this.button1);
        (this.label1 = new Label("Press button to select font", 1)).setBounds(12, 24, 444, 192);
        this.label1.setFont(new Font("Dialog", 0, 30));
        this.add(this.label1);
        this.button1.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.button1) {
            final fontSelect fontSelect = new fontSelect(new Frame(), true);
            fontSelect.show();
            if (fontSelect.isSelected) {
                this.label1.setFont(fontSelect.selectedFont);
                this.label1.setBackground(fontSelect.selectedBackground);
                this.label1.setForeground(fontSelect.selectedForeground);
                this.label1.setText("Font has been selected");
            }
        }
    }
}
