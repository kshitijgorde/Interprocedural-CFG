// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.ssh;

import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import com.stonewall.cornerstone.dm.AuthenticationException;
import com.stonewall.cornerstone.dm.DeviceException;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.dm.DeviceAccess;

public class SshAccess extends DeviceAccess
{
    protected JSch jsch;
    protected Session session;
    protected Channel channel;
    
    public SshAccess(final Device device) {
        throw new Error("Unresolved compilation problems: \n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tJSch cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n");
    }
    
    public void openSession() throws DeviceException, AuthenticationException {
        throw new Error("Unresolved compilation problems: \n\tSession cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n");
    }
    
    @Override
    public void closeSession() {
        throw new Error("Unresolved compilation problem: \n\tSession cannot be resolved to a type\n");
    }
    
    public void sendAndWait(final DeviceCommand deviceCommand) throws DeviceException {
        throw new Error("Unresolved compilation problems: \n\tChannel cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n");
    }
    
    @Override
    public DeviceCommand.Type getCmdType() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public class MyUserInfo
    {
        String passwd;
        
        public MyUserInfo(final SshAccess sshAccess, final String s) {
            throw new Error("Unresolved compilation problems: \n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tJSch cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tJSch cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n");
        }
        
        public String getPassword() {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public boolean promptYesNo(final String s) {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public String getPassphrase() {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public boolean promptPassphrase(final String s) {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public boolean promptPassword(final String s) {
            throw new Error("Unresolved compilation problem: \n");
        }
        
        public void showMessage(final String s) {
            throw new Error("Unresolved compilation problem: \n");
        }
    }
}
