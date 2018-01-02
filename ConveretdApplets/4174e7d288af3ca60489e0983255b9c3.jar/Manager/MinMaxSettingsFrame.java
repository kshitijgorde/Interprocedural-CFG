// 
// Decompiled by Procyon v0.5.30
// 

package Manager;

import java.awt.Component;
import Go.strategy.GoStrategy;
import Go.strategy.MinMaxGoStrategy;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Label;

public class MinMaxSettingsFrame extends GeneralOKCancel
{
    Label label;
    TextField textField;
    Panel panel;
    protected MinMaxGoStrategy strategy;
    
    public MinMaxSettingsFrame(final GoStrategy theStrategy) {
        this.label = new Label();
        this.textField = new TextField();
        this.panel = new Panel();
        this.strategy = null;
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.strategy = (MinMaxGoStrategy)theStrategy;
        this.loadData();
    }
    
    protected void jbInit() throws Exception {
        super.jbInit();
        this.add(this.panel, "Center");
        this.label.setText("Maximum Search Depth:");
        this.textField.setText("0");
        this.panel.add(this.label);
        this.panel.add(this.textField);
    }
    
    protected void loadData() {
        if (this.strategy != null) {
            this.textField.setText(new Integer(this.strategy.getMaxDepth()).toString());
        }
    }
    
    protected void saveChanges() {
        if (this.strategy != null) {
            try {
                final int maxDepth = Integer.parseInt(this.textField.getText());
                this.strategy.setMaxDepth(maxDepth);
            }
            catch (NumberFormatException ex) {}
        }
    }
    
    public static void main(final String[] args) {
        final MinMaxSettingsFrame minMaxSettingsFrame = new MinMaxSettingsFrame((GoStrategy)null);
        minMaxSettingsFrame.setVisible(true);
    }
}
