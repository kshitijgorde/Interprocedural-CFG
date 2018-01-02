// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import org.jdom.output.XMLOutputter;

public class Comment extends Content
{
    private static final String CVS_ID = "@(#) $RCSfile: Comment.java,v $ $Revision: 1.33 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
    protected String text;
    
    protected Comment() {
    }
    
    public Comment(final String text) {
        this.setText(text);
    }
    
    public String getValue() {
        return this.text;
    }
    
    public String getText() {
        return this.text;
    }
    
    public Comment setText(final String text) {
        final String reason;
        if ((reason = Verifier.checkCommentData(text)) != null) {
            throw new IllegalDataException(text, "comment", reason);
        }
        this.text = text;
        return this;
    }
    
    public String toString() {
        return "[Comment: " + new XMLOutputter().outputString(this) + "]";
    }
}
