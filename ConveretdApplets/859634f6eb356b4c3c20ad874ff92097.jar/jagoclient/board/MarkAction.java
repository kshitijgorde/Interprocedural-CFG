// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import rene.util.list.ListElement;
import java.io.PrintWriter;

public class MarkAction extends Action
{
    BoardInterface GF;
    
    public MarkAction(final String s, final BoardInterface gf) {
        super("M", s);
        this.GF = gf;
    }
    
    public MarkAction(final BoardInterface gf) {
        super("M");
        this.GF = gf;
    }
    
    public void print(final PrintWriter printWriter) {
        if (this.GF.getParameter("puresgf", false)) {
            printWriter.println();
            printWriter.print("MA");
            for (ListElement listElement = super.Arguments.first(); listElement != null; listElement = listElement.next()) {
                printWriter.print("[" + (String)listElement.content() + "]");
            }
            return;
        }
        super.print(printWriter);
    }
}
