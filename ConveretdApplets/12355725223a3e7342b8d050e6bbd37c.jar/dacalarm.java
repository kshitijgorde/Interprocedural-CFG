import java.awt.Event;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.CheckboxGroup;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class dacalarm extends Frame
{
    Button ok;
    Checkbox alarmcb;
    TextField alarmHtf;
    TextField alarmMtf;
    TextField alarmStf;
    public boolean alarmset;
    public int alarmH;
    public int alarmM;
    public int alarmS;
    
    dacalarm() {
        super("Alarm Settings");
        this.alarmset = false;
        this.alarmH = 0;
        this.alarmM = 0;
        this.alarmS = 0;
        this.ok = new Button("OK");
        this.setFont(new Font("Helvetica", 1, 12));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        this.setLayout(layout);
        final Panel panel = new Panel();
        panel.add(this.alarmcb = new Checkbox(" Enable / Disable the Alarm ", null, this.alarmset));
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(3, 2));
        this.alarmHtf = new TextField(new Integer(this.alarmH).toString(), 5);
        this.alarmMtf = new TextField(new Integer(this.alarmM).toString(), 5);
        this.alarmStf = new TextField(new Integer(this.alarmS).toString(), 5);
        panel2.add(new Label("Hour:"));
        panel2.add(this.alarmHtf);
        panel2.add(new Label("Minute:"));
        panel2.add(this.alarmMtf);
        panel2.add(new Label("Second:"));
        panel2.add(this.alarmStf);
        layout.setConstraints(panel2, gridBagConstraints);
        this.add(panel2);
        layout.setConstraints(this.ok, gridBagConstraints);
        this.add(this.ok);
        this.pack();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.ok) {
            this.alarmset = false;
            final boolean alarmH = false;
            this.alarmS = (alarmH ? 1 : 0);
            this.alarmM = (alarmH ? 1 : 0);
            this.alarmH = (alarmH ? 1 : 0);
            if (this.alarmcb.getState()) {
                this.alarmset = true;
                final String text = this.alarmHtf.getText();
                final String text2 = this.alarmMtf.getText();
                final String text3 = this.alarmStf.getText();
                try {
                    this.alarmH = new Integer(text.trim());
                    this.alarmM = new Integer(text2.trim());
                    this.alarmS = new Integer(text3.trim());
                }
                catch (NumberFormatException ex) {}
            }
            else {
                this.alarmHtf.setText("0");
                this.alarmMtf.setText("0");
                this.alarmStf.setText("0");
            }
            this.dispose();
        }
        return true;
    }
}
