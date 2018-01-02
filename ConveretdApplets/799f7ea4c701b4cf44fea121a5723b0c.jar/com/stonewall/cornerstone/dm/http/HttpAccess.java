// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.http;

import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import com.stonewall.cornerstone.dm.AuthenticationException;
import com.stonewall.cornerstone.dm.DeviceException;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.dm.DeviceAccess;

public class HttpAccess extends DeviceAccess
{
    private HttpHandler http;
    
    public HttpAccess(final Device device) {
        throw new Error("Unresolved compilation problems: \n\tThe import org.jdom.Element cannot be resolved\n\tElement cannot be resolved to a type\n\tDocument cannot be resolved to a type\n");
    }
    
    public void openSession() throws DeviceException, AuthenticationException {
        throw new Error("Unresolved compilation problems: \n\tElement cannot be resolved to a type\n\tDocument cannot be resolved to a type\n");
    }
    
    @Override
    public void closeSession() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void sendAndWait(final DeviceCommand deviceCommand) throws DeviceException, AuthenticationException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    @Override
    public DeviceCommand.Type getCmdType() {
        throw new Error("Unresolved compilation problem: \n");
    }
}
