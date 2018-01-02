import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Label;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class AlertContinue extends Frame
{
    private Label label1;
    private Label label2;
    private Button buttonYes;
    private Button buttonNo;
    private ecm appletEcm;
    
    public AlertContinue(final ecm appletEcm) {
        super("Warning!");
        this.resize(400, 210);
        this.show();
        this.setLayout(null);
        this.label1 = new Label("A factorization is still in progress!", 1);
        this.label2 = new Label("Do you want to stop it and start a new one?", 1);
        this.label1.setFont(new Font("Helvetica", 0, 12));
        this.label2.setFont(new Font("Helvetica", 0, 12));
        this.buttonYes = new Button("Yes");
        this.buttonNo = new Button("No");
        this.label1.reshape(25, 40, 350, 20);
        this.label2.reshape(25, 70, 350, 20);
        this.buttonYes.reshape(100, 130, 40, 30);
        this.buttonNo.reshape(260, 130, 40, 30);
        this.add(this.label1);
        this.add(this.label2);
        this.add(this.buttonYes);
        this.add(this.buttonNo);
        this.buttonNo.requestFocus();
        this.validate();
        this.appletEcm = appletEcm;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == this.buttonYes) {
            this.appletEcm.startNewFactorization(false);
            super.dispose();
        }
        if ((event.id == 1001 && event.target == this.buttonNo) || event.id == 201) {
            super.dispose();
        }
        return super.handleEvent(event);
    }
}
