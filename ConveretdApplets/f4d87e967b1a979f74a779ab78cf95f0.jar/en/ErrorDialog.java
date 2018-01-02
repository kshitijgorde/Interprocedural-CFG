// 
// Decompiled by Procyon v0.5.30
// 

package en;

import java.io.PrintStream;

public class ErrorDialog extends TextOutputDialog
{
    protected StringPrintStream ps;
    
    public ErrorDialog(final String s, final String s2, final Exception ex) {
        super(s, null);
        this.ps = new StringPrintStream();
        if (ex == null) {
            super.textArea.setText(String.valueOf(String.valueOf(s2)).concat("\nFor more information, visit www.waterlogic.com.sg"));
        }
        else {
            ex.printStackTrace(this.ps);
            super.textArea.setText(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("\n").append(this.ps.stringBuffer.toString()).append("\nFor more information, visit www.waterlogic.com.sg"))));
        }
    }
}
