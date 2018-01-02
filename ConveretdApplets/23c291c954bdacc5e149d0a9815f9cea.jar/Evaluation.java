import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.applet.Applet;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class Evaluation extends Dialog
{
    private Applet parentapplet;
    private String link;
    
    public Evaluation(final Applet parentapplet, final String title, final String link) {
        super(new Frame(), false);
        this.link = link;
        this.setLayout(null);
        this.setTitle(title);
        this.setBackground(new Color(255, 204, 0));
        final Label label = new Label("Free Trial Version");
        label.setFont(new Font("Helvetica", 1, 12));
        label.setAlignment(1);
        this.add(label);
        label.setBounds(140, 40, 160, 20);
        final Label label2 = new Label("For Licensing, Purchasing Information");
        label2.setFont(new Font("Helvetica", 0, 12));
        label2.setAlignment(1);
        this.add(label2);
        label2.setBounds(100, 60, 240, 15);
        final Label label3 = new Label("and more great products.");
        label3.setFont(new Font("Helvetica", 0, 12));
        label3.setAlignment(1);
        this.add(label3);
        label3.setBounds(100, 75, 240, 15);
        final Button button = new Button("Visit jpowered.com - click here !");
        button.setBackground(new Color(255, 219, 15));
        button.setForeground(new Color(136, 96, 0));
        button.setFont(new Font("Arial", 1, 14));
        this.add(button);
        button.setBounds(100, 100, 240, 30);
        final Label label4 = new Label("(Please Note: This pop-up has been removed from the licensed version.)");
        label4.setFont(new Font("Helvetica", 0, 10));
        label4.setAlignment(1);
        this.add(label4);
        label4.setBounds(50, 140, 340, 15);
        this.setBounds(this.getToolkit().getScreenSize().width / 2 - 220, this.getToolkit().getScreenSize().height / 2 - 110, 440, 220);
        this.setResizable(false);
        this.show(true);
        this.parentapplet = parentapplet;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
        }
        if (event.id == 1001) {
            try {
                this.parentapplet.getAppletContext().showDocument(new URL(this.link), "Jpowered");
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        }
        return true;
    }
}
