// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import jagoclient.Go;
import java.awt.Frame;
import jagoclient.Global;
import jagoclient.dialogs.GetParameter;

class GetEncoding extends GetParameter
{
    GoFrame GCF;
    
    public GetEncoding(final GoFrame gcf) {
        super(gcf, Global.resourceString("Encoding__empty__default_"), Global.resourceString("Encoding"), gcf, true, "encoding");
        if (!Go.isApplet) {
            this.set(rene.gui.Global.getParameter("encoding", System.getProperty("file.encoding")));
        }
        this.GCF = gcf;
        this.show();
    }
    
    public boolean tell(final Object o, final String s) {
        if (s.equals("")) {
            rene.gui.Global.removeParameter("encoding");
        }
        else {
            rene.gui.Global.setParameter("encoding", s);
        }
        return true;
    }
}
