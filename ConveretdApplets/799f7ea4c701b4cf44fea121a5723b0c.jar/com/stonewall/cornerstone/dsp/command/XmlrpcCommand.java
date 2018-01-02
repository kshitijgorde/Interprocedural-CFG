// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import org.xmodel.IModelObject;

public class XmlrpcCommand extends RMICommand
{
    public XmlrpcCommand() {
        super("en:xmlrpcCommand", Type.xmlrpc);
    }
    
    public XmlrpcCommand(final IModelObject o) {
        super(o);
    }
    
    @Override
    protected void determineStatus() {
        this.getResult().succeed();
    }
}
