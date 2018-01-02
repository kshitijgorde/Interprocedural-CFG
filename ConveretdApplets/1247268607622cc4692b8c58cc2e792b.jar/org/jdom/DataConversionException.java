// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

public class DataConversionException extends JDOMException
{
    private static final String CVS_ID = "@(#) $RCSfile: DataConversionException.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
    
    public DataConversionException(final String name, final String dataType) {
        super("The XML construct " + name + " could not be converted to a " + dataType);
    }
}
