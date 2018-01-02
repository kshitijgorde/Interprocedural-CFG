// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

public class IllegalTargetException extends IllegalArgumentException
{
    private static final String CVS_ID = "@(#) $RCSfile: IllegalTargetException.java,v $ $Revision: 1.15 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    
    IllegalTargetException(final String target, final String reason) {
        super("The target \"" + target + "\" is not legal for JDOM/XML Processing Instructions: " + reason + ".");
    }
    
    public IllegalTargetException(final String reason) {
        super(reason);
    }
}
