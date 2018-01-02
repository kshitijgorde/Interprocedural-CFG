import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class MiscListener implements ActionListener
{
    DrawPanel drawPanel;
    
    public MiscListener(final DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand == "Clear") {
            this.drawPanel.clear();
        }
        if (actionCommand == "Undo") {
            this.drawPanel.undo();
        }
        if (actionCommand == "Quit" && JOptionPane.showConfirmDialog(this.drawPanel, "Do you really want to finish?", "Quit?", 0) == 0) {
            System.exit(0);
        }
        if (actionCommand == "Background") {
            this.drawPanel.backGroundImage();
        }
    }
}
