// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.io.Serializable;

public interface InputObject extends Serializable
{
    void checkInput();
    
    void notifyControllerOnChange(final Controller p0);
}
