// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient;

import java.util.Locale;
import java.awt.Frame;
import jagoclient.dialogs.GetParameter;

class GetLanguage extends GetParameter
{
    public GetLanguage(final MainFrame mainFrame) {
        super((Frame)mainFrame, Global.resourceString("Your_Locale"), Global.resourceString("Language"), mainFrame, true, "language");
        this.set(Locale.getDefault().toString());
        this.show();
    }
    
    public boolean tell(final Object o, final String s) {
        rene.gui.Global.setParameter("language", s);
        return true;
    }
}
