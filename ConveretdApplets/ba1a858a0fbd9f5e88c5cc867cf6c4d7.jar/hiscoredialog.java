import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class hiscoredialog extends Dialog
{
    private TextField name;
    private Label text;
    private int score;
    
    public hiscoredialog(final Frame frame) {
        super(frame, "Congratulations!", true);
        this.setLayout(new FlowLayout());
        this.setBackground(Color.white);
        this.setFont(new Font("Helvetica", 0, 12));
        this.add(this.text = new Label("Enter your name for the hiscore list", 1));
        this.add(this.name = new TextField(10));
        this.add(new Button("OK"));
        this.resize(230, 120);
        this.setResizable(false);
        this.move(300, 200);
    }
    
    public void enterHiscore(final int score) {
        this.score = score;
        this.show();
        this.name.requestFocus();
    }
    
    public String getName() {
        return this.name.getText();
    }
    
    public boolean action(final Event event, final Object o) {
        if (o == "OK") {
            this.hide();
            return true;
        }
        return false;
    }
}
