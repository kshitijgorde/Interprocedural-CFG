// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Frame;
import jagoclient.Global;
import jagoclient.dialogs.GetParameter;

class NodeNameEdit extends GetParameter
{
    public NodeNameEdit(final GoFrame goFrame, final String s) {
        super(goFrame, Global.resourceString("Name"), Global.resourceString("Node_Name"), goFrame, true);
        this.set(s);
        this.show();
    }
    
    public boolean tell(final Object o, final String s) {
        ((GoFrame)o).setname(s);
        return true;
    }
}
