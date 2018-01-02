import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MiniDlg extends Panel implements ActionListener
{
    Selection callback;
    Label captionLabel;
    Button yesButton;
    Button noButton;
    
    public MiniDlg() {
        this.captionLabel = new Label();
        this.yesButton = new Button();
        this.noButton = new Button();
        this.setLayout(null);
        this.setBackground(new Color(0, 128, 0));
        this.setSize(125, 51);
        this.captionLabel.setAlignment(1);
        this.add(this.captionLabel);
        this.captionLabel.setFont(new Font("Dialog", 1, 12));
        this.captionLabel.setBounds(0, 0, 125, 15);
        this.add(this.yesButton);
        this.yesButton.setBackground(Color.lightGray);
        this.yesButton.setBounds(10, 26, 52, 24);
        this.add(this.noButton);
        this.noButton.setBackground(Color.lightGray);
        this.noButton.setBounds(64, 26, 52, 24);
        this.yesButton.addActionListener(this);
        this.noButton.addActionListener(this);
    }
    
    public MiniDlg(final String text, final String label, final String label2, final Selection callback) {
        this();
        this.captionLabel.setText(text);
        this.yesButton.setLabel(label);
        this.noButton.setLabel(label2);
        this.callback = callback;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.yesButton) {
            this.callback.selectYes();
        }
        if (source == this.noButton) {
            this.callback.selectNo();
        }
    }
}
