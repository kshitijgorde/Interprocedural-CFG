// 
// Decompiled by Procyon v0.5.30
// 

package matt.web;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;

class CheckListItem
{
    public String label;
    private boolean isSelected;
    
    public CheckListItem(final String label) {
        this.isSelected = false;
        this.label = label;
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (UnsupportedLookAndFeelException e) {}
        catch (ClassNotFoundException e2) {}
        catch (InstantiationException e3) {}
        catch (IllegalAccessException ex) {}
    }
    
    public boolean isSelected() {
        return this.isSelected;
    }
    
    public void setSelected(final boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    public String toString() {
        return this.label;
    }
}
