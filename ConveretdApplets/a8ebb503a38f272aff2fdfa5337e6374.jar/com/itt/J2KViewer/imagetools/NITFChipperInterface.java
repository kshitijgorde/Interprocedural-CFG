// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.imagetools;

import com.idl.javaidl.JIDLNumber;
import com.idl.javaidl.JIDLString;
import com.idl.javaidl.JIDLObject;

public class NITFChipperInterface extends JIDLObject
{
    private static String IDL_CLASS;
    private static String OPS_NAME;
    
    public NITFChipperInterface() {
        super(NITFChipperInterface.IDL_CLASS, NITFChipperInterface.OPS_NAME);
    }
    
    public void WRITECHIP(final JIDLString jidlString, final JIDLString jidlString2, final JIDLNumber jidlNumber, final JIDLNumber jidlNumber2, final JIDLNumber jidlNumber3, final JIDLNumber jidlNumber4, final JIDLString jidlString3, final JIDLString jidlString4, final JIDLString jidlString5) {
        final Object[] array = new Object[9];
        final int[] array2 = new int[9];
        array[0] = jidlString;
        array[array2[0] = 1] = jidlString2;
        array2[1] = 1;
        array[2] = jidlNumber;
        array2[2] = 1;
        array[3] = jidlNumber2;
        array2[3] = 1;
        array[4] = jidlNumber3;
        array2[4] = 1;
        array[5] = jidlNumber4;
        array2[5] = 1;
        array[6] = jidlString3;
        array2[6] = 1;
        array[7] = jidlString4;
        array2[7] = 1;
        array[8] = jidlString5;
        array2[8] = 2;
        super.callProcedure("WRITECHIP", 9, array, array2);
        jidlString5.setValue((JIDLString)array[8]);
    }
    
    static {
        NITFChipperInterface.IDL_CLASS = "NITFChipperInterface";
        NITFChipperInterface.OPS_NAME = "Default_Process_Name";
    }
}
