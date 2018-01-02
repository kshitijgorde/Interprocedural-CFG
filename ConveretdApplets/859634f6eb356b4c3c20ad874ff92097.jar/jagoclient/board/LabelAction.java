// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import rene.util.list.ListElement;
import java.io.PrintWriter;

public class LabelAction extends Action
{
    BoardInterface GF;
    
    public LabelAction(final String s, final BoardInterface gf) {
        super("L", s);
        this.GF = gf;
    }
    
    public LabelAction(final BoardInterface gf) {
        super("L");
        this.GF = gf;
    }
    
    public void print(final PrintWriter printWriter) {
        if (this.GF.getParameter("puresgf", false)) {
            printWriter.println();
            printWriter.print("LB");
            final char[] array = { '\0' };
            int n = 0;
            for (ListElement listElement = super.Arguments.first(); listElement != null; listElement = listElement.next()) {
                array[0] = (char)(97 + n);
                printWriter.print("[" + (String)listElement.content() + ":" + new String(array) + "]");
                ++n;
            }
            return;
        }
        super.print(printWriter);
    }
}
