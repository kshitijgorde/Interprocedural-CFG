import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class Evaluation extends Dialog
{
    public Evaluation() {
        super(new Frame(), false);
        this.setLayout(null);
        this.setTitle("Vertical Menu Pro");
        final Label label = new Label("Evaluation Version");
        label.setAlignment(1);
        this.add(label);
        label.reshape(20, 30, 160, 20);
        final Label label2 = new Label("For Purchase Information");
        label2.setAlignment(1);
        this.add(label2);
        label2.reshape(20, 60, 160, 20);
        final Button button = new Button("Visit 1stjavasoft.com");
        this.add(button);
        button.reshape(20, 90, 160, 20);
        this.show(true);
        this.resize(200, 170);
        this.setResizable(false);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
        }
        if (event.id == 1001) {
            try {
                SIRvmenuPRO.APPLETCONTEXT.showDocument(new URL("http://www.1stjavasoft.com"));
            }
            catch (MalformedURLException ex) {}
            this.dispose();
        }
        return true;
    }
}
