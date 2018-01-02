import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class c_pa extends Frame
{
    Label lN;
    TextField tN;
    Label lP;
    TextField tP;
    Label lM;
    Button bOK;
    Button bCANCEL;
    GridBagLayout gb;
    GridBagConstraints g;
    tpassword frP;
    
    public boolean action(final Event event, final Object o) {
        if ("Cancel".equals(o)) {
            this.hide();
        }
        if (" OK ".equals(o)) {
            this.frP.verb(this.tN.getText(), this.tP.getText());
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public c_pa(final tpassword frP, final String title) {
        this.gb = new GridBagLayout();
        this.g = new GridBagConstraints();
        this.frP = frP;
        this.setTitle(title);
        this.setLayout(this.gb);
        (this.bOK = new Button(" OK ")).setFont(new Font("Arail", 1, 14));
        this.bOK.setBackground(new Color(this.frP.bgcolor2));
        this.bOK.setForeground(new Color(this.frP.fgcolor2));
        (this.bCANCEL = new Button("Cancel")).setFont(new Font("Arail", 1, 14));
        this.bCANCEL.setBackground(new Color(this.frP.bgcolor2));
        this.bCANCEL.setForeground(new Color(this.frP.fgcolor2));
        this.lN = new Label("User");
        (this.tN = new TextField("", 10)).setForeground(new Color(this.frP.fgcolor));
        this.lN.setForeground(new Color(this.frP.fgcolor));
        this.lP = new Label("Password");
        (this.tP = new TextField("", 10)).setEchoCharacter('*');
        this.tP.setForeground(new Color(this.frP.fgcolor));
        this.lP.setForeground(new Color(this.frP.fgcolor));
        this.setBackground(new Color(this.frP.bgcolor));
        this.g.anchor = 12;
        this.g.fill = 2;
        this.g.weightx = 1.0;
        this.g.insets = new Insets(2, 2, 5, 5);
        final Label label = new Label(this.frP.m_s, 1);
        label.setFont(new Font("Arial", 1, 16));
        label.setForeground(new Color(this.frP.fgcolor));
        this.g.gridwidth = 0;
        this.gb.setConstraints(label, this.g);
        this.add(label);
        this.g.gridwidth = -1;
        this.gb.setConstraints(this.lN, this.g);
        this.add(this.lN);
        this.g.gridwidth = 0;
        this.gb.setConstraints(this.tN, this.g);
        this.add(this.tN);
        this.g.gridwidth = -1;
        this.gb.setConstraints(this.lP, this.g);
        this.add(this.lP);
        this.g.gridwidth = 0;
        this.gb.setConstraints(this.tP, this.g);
        this.add(this.tP);
        this.add(this.bOK);
        this.add(this.bCANCEL);
        this.reshape(300, 200, 150, 100);
        this.pack();
    }
}
