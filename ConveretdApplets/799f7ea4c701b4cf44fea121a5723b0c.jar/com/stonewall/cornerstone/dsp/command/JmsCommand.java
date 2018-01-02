// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import org.xmodel.IModelObject;

public class JmsCommand extends RMICommand
{
    public JmsCommand() {
        super("en:jmsCommand", Type.jms);
    }
    
    public JmsCommand(final IModelObject o) {
        super(o);
    }
    
    @Override
    protected void determineStatus() {
    }
}
