import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class ImportDialog extends Dialog implements ActionListener
{
    CirSim cframe;
    Button importButton;
    Button closeButton;
    TextArea text;
    boolean isURL;
    
    ImportDialog(final CirSim cframe, final String s, final boolean isURL) {
        super(cframe, (s.length() > 0) ? "Export" : "Import", false);
        this.isURL = isURL;
        this.cframe = cframe;
        this.setLayout(new ImportDialogLayout());
        this.add(this.text = new TextArea(s, 10, 60, 0));
        this.importButton = new Button("Import");
        if (!this.isURL) {
            this.add(this.importButton);
        }
        this.importButton.addActionListener(this);
        this.add(this.closeButton = new Button("Close"));
        this.closeButton.addActionListener(this);
        final CirSim cframe2 = this.cframe;
        final Point locationOnScreen = CirSim.main.getLocationOnScreen();
        this.resize(400, 300);
        final Dimension size = this.getSize();
        this.setLocation(locationOnScreen.x + (this.cframe.winSize.width - size.width) / 2, locationOnScreen.y + (this.cframe.winSize.height - size.height) / 2);
        this.show();
        if (s.length() > 0) {
            this.text.selectAll();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.importButton) {
            this.cframe.readSetup(this.text.getText());
            this.setVisible(false);
        }
        if (source == this.closeButton) {
            this.setVisible(false);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            CirSim.main.requestFocus();
            this.setVisible(false);
            final CirSim cframe = this.cframe;
            CirSim.impDialog = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
