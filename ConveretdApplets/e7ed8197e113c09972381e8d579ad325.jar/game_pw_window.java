import java.awt.Event;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class game_pw_window extends Frame implements groupboard_consts
{
    groupboard parent;
    String password_string;
    int game_id;
    TextField password;
    Button ok_but;
    Button cancel_but;
    
    game_pw_window(final groupboard parent, final int game_id) {
        this.parent = parent;
        this.game_id = game_id;
        this.setTitle("Enter Password");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final Label label;
        this.add(label = new Label("Enter game password:"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(label, gridBagConstraints);
        this.add(this.password = new TextField(20));
        this.password.setEchoCharacter('*');
        gridBagConstraints.gridx = 1;
        layout.setConstraints(this.password, gridBagConstraints);
        final Panel panel = new Panel();
        panel.add(this.ok_but = new Button("OK"));
        panel.add(this.cancel_but = new Button("Cancel"));
        this.add(panel);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        layout.setConstraints(panel, gridBagConstraints);
        this.pack();
        this.show();
        this.password.requestFocus();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.cancel_but) {
            this.dispose();
            return true;
        }
        if (event.target == this.ok_but) {
            this.dispose();
            this.parent.join_game(this.game_id, this.password.getText());
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            return true;
        }
        if (event.id == 401 && 10 == event.key) {
            this.dispose();
            this.parent.join_game(this.game_id, this.password.getText());
            return true;
        }
        return super.handleEvent(event);
    }
}
