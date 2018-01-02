// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.CheckboxGroup;
import ji.document.gm;

public class jiAttachmentDataCheckbox extends fs
{
    public gm a;
    static /* synthetic */ Class b;
    
    public jiAttachmentDataCheckbox(final String s, final gm a, final boolean b) {
        super(s, a.a(), b, null);
        this.a = a;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(((jiAttachmentDataCheckbox.b == null) ? (jiAttachmentDataCheckbox.b = class$("ji.awt.jiAttachmentDataCheckbox")) : jiAttachmentDataCheckbox.b).getName());
        sb.append(": ").append(this.a).append(super.toString());
        return sb.toString();
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
