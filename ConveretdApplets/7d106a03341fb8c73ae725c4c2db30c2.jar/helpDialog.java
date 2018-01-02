import java.awt.Event;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class helpDialog extends Dialog
{
    TextArea textArea;
    
    helpDialog(final String title, final String s) {
        super(new Frame(), false);
        this.addNotify();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setFont(new Font("Helvetica", 1, 12));
        this.setLayout(layout);
        this.setBackground(Color.lightGray);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        layout.setConstraints(this.textArea = new TextArea(s), gridBagConstraints);
        this.add(this.textArea);
        this.textArea.setEditable(false);
        this.textArea.setBackground(Color.white);
        this.setTitle(title);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.resize(this.textArea.preferredSize().width + 15, screenSize.height / 2);
        this.move(Math.max(0, screenSize.width - this.size().width), 0);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 || (event.id == 401 && event.key == 27)) {
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void show() {
        super.show();
        this.textArea.requestFocus();
    }
}
