// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

public class IllegalNameException extends IllegalArgumentException
{
    private static final String CVS_ID = "@(#) $RCSfile: IllegalNameException.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    
    IllegalNameException(final String name, final String construct, final String reason) {
        super("The name \"" + name + "\" is not legal for JDOM/XML " + construct + "s: " + reason + ".");
    }
    
    IllegalNameException(final String name, final String construct) {
        super("The name \"" + name + "\" is not legal for JDOM/XML " + construct + "s.");
    }
    
    public IllegalNameException(final String reason) {
        super(reason);
    }
}
