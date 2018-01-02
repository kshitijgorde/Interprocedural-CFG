// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.caching.IFileAssociation;
import java.util.List;
import org.xidget.config.TagProcessor;

public interface IToolkit extends IFeatured
{
    void configure(final TagProcessor p0);
    
    List<String> getFonts();
    
    IFileAssociation getImageFileAssociation();
    
    Confirmation openConfirmDialog(final StatefulContext p0, final String p1, final Object p2, final String p3, final boolean p4);
    
    void openMessageDialog(final StatefulContext p0, final String p1, final Object p2, final String p3, final MessageType p4);
    
    String[] openFileDialog(final StatefulContext p0, final IExpression p1, final IExpression p2, final String p3, final FileDialogType p4);
    
    public enum Confirmation
    {
        yes("yes", 0), 
        no("no", 1), 
        cancel("cancel", 2);
        
        private Confirmation(final String s, final int n) {
        }
    }
    
    public enum FileDialogType
    {
        openOne("openOne", 0), 
        openMany("openMany", 1), 
        save("save", 2);
        
        private FileDialogType(final String s, final int n) {
        }
    }
    
    public enum MessageType
    {
        error("error", 0), 
        warning("warning", 1), 
        information("information", 2), 
        status("status", 3);
        
        private MessageType(final String s, final int n) {
        }
    }
}
