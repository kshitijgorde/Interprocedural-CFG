// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import com.stonewall.cornerstone.entity.Protocol;
import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.entity.Device;

public abstract class DeviceAccess
{
    protected Document authDoc;
    protected String loginPrompt;
    protected String passwordPrompt;
    protected String cmdPrompt;
    private boolean alarmed;
    private Device device;
    private boolean online;
    protected static final Log log;
    
    public static DeviceAccess createDeviceAccess(final Device device) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public DeviceAccess(final Device device) {
        throw new Error("Unresolved compilation problems: \n\tThe import org.jdom cannot be resolved\n\tThe import org.jdom cannot be resolved\n\tDocument cannot be resolved to a type\n\tThe type org.jdom.Document cannot be resolved. It is indirectly referenced from required .class files\n\tThe method getDocument(String, String, String) from the type Loader refers to the missing type Document\n\tDocument cannot be resolved to a type\n\tElement cannot be resolved to a type\n\tDocument cannot be resolved to a type\n\tDocument cannot be resolved to a type\n");
    }
    
    public void finalize() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void open() throws DeviceException, AuthenticationException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void close() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    protected abstract void openSession() throws DeviceException, AuthenticationException;
    
    public abstract void closeSession();
    
    public boolean isOnline() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    protected abstract void sendAndWait(final DeviceCommand p0) throws DeviceException, AuthenticationException;
    
    public void executeCommand(final DeviceCommand deviceCommand) throws DeviceException, AuthenticationException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public String getAddress() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public Protocol getProtocol() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public Protocol.Type getProtocolType() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public int getPort() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public String getUsername() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public String getPassword() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    protected void init() {
        throw new Error("Unresolved compilation problems: \n\tDocument cannot be resolved to a type\n\tThe type org.jdom.Document cannot be resolved. It is indirectly referenced from required .class files\n\tThe method getDocument(String, String, String) from the type Loader refers to the missing type Document\n\tDocument cannot be resolved to a type\n\tElement cannot be resolved to a type\n\tDocument cannot be resolved to a type\n");
    }
    
    public abstract DeviceCommand.Type getCmdType();
    
    private void generateAlarm(final Exception ex) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void clearAlarm() {
        throw new Error("Unresolved compilation problem: \n");
    }
}
