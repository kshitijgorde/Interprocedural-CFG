// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.io.IOException;
import java.io.DataOutputStream;

public class UTFDOSWriter
{
    private DataOutputStream out;
    
    UTFDOSWriter(final DataOutputStream out) {
        this.out = out;
    }
    
    public void writeUTFData(final String s) throws IOException {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= '\u0001' && char1 <= '\u007f') {
                this.out.writeByte(char1);
            }
            else if (char1 > '\u07ff') {
                this.out.writeByte('\u00e0' | (char1 >> 12 & '\u000f'));
                this.out.writeByte('\u0080' | (char1 >> 6 & '?'));
                this.out.writeByte('\u0080' | (char1 >> 0 & '?'));
            }
            else {
                this.out.writeByte('\u00c0' | (char1 >> 6 & '\u001f'));
                this.out.writeByte('\u0080' | (char1 >> 0 & '?'));
            }
        }
    }
}
