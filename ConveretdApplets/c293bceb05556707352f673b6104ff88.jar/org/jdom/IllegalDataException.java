// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

public class IllegalDataException extends IllegalArgumentException
{
    private static final String CVS_ID = "@(#) $RCSfile: IllegalDataException.java,v $ $Revision: 1.7 $ $Date: 2002/01/08 09:17:10 $ $Name: jdom_1_0_b8 $";
    
    public IllegalDataException(final String data, final String construct, final String reason) {
        super("The data \"" + data + "\" is not legal for a JDOM " + construct + ": " + reason + ".");
    }
    
    public IllegalDataException(final String data, final String construct) {
        super("The data \"" + data + "\" is not legal for a JDOM " + construct + ".");
    }
}
