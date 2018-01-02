// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.control;

import java.awt.Button;

public class ButtonDescriptor
{
    String command;
    String label;
    int id;
    boolean closeDialog;
    Button button;
    
    public ButtonDescriptor(final String command, final String label, final int id, final boolean closeDialog) {
        this.command = command;
        this.label = label;
        this.id = id;
        this.closeDialog = closeDialog;
    }
    
    public int getID() {
        return this.id;
    }
}
