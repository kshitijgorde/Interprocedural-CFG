// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Vector;

public interface NFUserInputForm
{
    void userInputFormCreate(final Vector p0, final Vector p1);
    
    void userInputFormAddObserver(final NFUserInputObserver p0);
    
    void userInputFormSetHeader(final String p0);
    
    void userInputFormSetValues(final Vector p0);
    
    void userInputFormGetValues(final Vector p0);
    
    void setFieldsActivateButton(final String p0);
}
