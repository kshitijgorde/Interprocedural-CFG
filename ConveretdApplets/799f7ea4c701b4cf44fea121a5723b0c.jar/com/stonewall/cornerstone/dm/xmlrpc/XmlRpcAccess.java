// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.xmlrpc;

import java.util.Vector;
import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import com.stonewall.cornerstone.dm.AuthenticationException;
import com.stonewall.cornerstone.dm.DeviceException;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.dm.DeviceAccess;

public class XmlRpcAccess extends DeviceAccess
{
    private XmlRpcClient xmlrpc;
    
    public XmlRpcAccess(final Device device) {
        throw new Error("Unresolved compilation problems: \n\tThe import org.apache.xmlrpc cannot be resolved\n\tThe import org.apache.xmlrpc cannot be resolved\n\tXmlRpcClient cannot be resolved to a type\n\tXmlRpcClient cannot be resolved to a type\n\tXmlRpcClient cannot be resolved to a type\n\tXmlRpcClient cannot be resolved to a type\n\tXmlRpcClient cannot be resolved to a type\n\tXmlRpcClient cannot be resolved to a type\n\tXmlRpcException cannot be resolved to a type\n\tXmlRpcClient cannot be resolved to a type\n");
    }
    
    public void openSession() throws DeviceException, AuthenticationException {
        throw new Error("Unresolved compilation problems: \n\tXmlRpcClient cannot be resolved to a type\n\tXmlRpcClient cannot be resolved to a type\n\tXmlRpcClient cannot be resolved to a type\n");
    }
    
    @Override
    public void closeSession() {
        throw new Error("Unresolved compilation problem: \n\tXmlRpcClient cannot be resolved to a type\n");
    }
    
    public void sendAndWait(final DeviceCommand deviceCommand) throws DeviceException, AuthenticationException {
        throw new Error("Unresolved compilation problems: \n\tXmlRpcClient cannot be resolved to a type\n\tXmlRpcClient cannot be resolved to a type\n\tXmlRpcException cannot be resolved to a type\n");
    }
    
    private Vector getVector(final String[] array) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private Vector getVector(final String s) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    @Override
    public DeviceCommand.Type getCmdType() {
        throw new Error("Unresolved compilation problem: \n");
    }
}
