// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Frame;
import jagoclient.Global;
import jagoclient.dialogs.GetParameter;

class SizeQuestion extends GetParameter
{
    public SizeQuestion(final GoFrame goFrame) {
        super(goFrame, Global.resourceString("Size_between_5_and_29"), Global.resourceString("Board_size"), goFrame, true);
        this.show();
    }
    
    public boolean tell(final Object o, final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
            if (int1 < 5 || int1 > 29) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            return false;
        }
        ((GoFrame)o).doboardsize(int1);
        return true;
    }
}
