// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import org.xidget.swing.key.KeyManager;
import org.xmodel.xaction.IXAction;
import org.xidget.IXidget;
import org.xidget.ifeature.IKeyFeature;
import java.awt.event.KeyAdapter;

public class KeyFeature extends KeyAdapter implements IKeyFeature
{
    private IXidget xidget;
    
    public KeyFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void bind(final String s, final boolean b, final IXAction ixAction) {
        KeyManager.getInstance().bind(this.xidget, s, b, ixAction);
    }
    
    @Override
    public void unbind(final String s, final IXAction ixAction) {
        KeyManager.getInstance().unbind(this.xidget, s, ixAction);
    }
}
