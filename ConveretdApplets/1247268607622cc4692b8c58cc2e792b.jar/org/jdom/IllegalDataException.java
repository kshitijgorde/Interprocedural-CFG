// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

public class IllegalDataException extends IllegalArgumentException
{
    private static final String CVS_ID = "@(#) $RCSfile: IllegalDataException.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    
    IllegalDataException(final String data, final String construct, final String reason) {
        super("The data \"" + data + "\" is not legal for a JDOM " + construct + ": " + reason + ".");
    }
    
    IllegalDataException(final String data, final String construct) {
        super("The data \"" + data + "\" is not legal for a JDOM " + construct + ".");
    }
    
    public IllegalDataException(final String reason) {
        super(reason);
    }
}
