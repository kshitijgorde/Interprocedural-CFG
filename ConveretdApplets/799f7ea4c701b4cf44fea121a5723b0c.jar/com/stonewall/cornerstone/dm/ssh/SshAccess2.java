// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.ssh;

import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import com.stonewall.cornerstone.dm.AuthenticationException;
import com.stonewall.cornerstone.dm.DeviceException;
import com.stonewall.cornerstone.entity.Device;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import com.stonewall.cornerstone.dm.DeviceAccess;

public class SshAccess2 extends DeviceAccess
{
    protected SSH2 ssh;
    protected Session session;
    protected Channel channel;
    protected InputStream in;
    protected OutputStream out;
    protected Socket socket;
    protected String prompt;
    
    public SshAccess2(final Device device) {
        throw new Error("Unresolved compilation problems: \n\tThe import org.jdom.Element cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tElement cannot be resolved to a type\n\tDocument cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n");
    }
    
    @Override
    protected void init() {
        throw new Error("Unresolved compilation problems: \n\tElement cannot be resolved to a type\n\tDocument cannot be resolved to a type\n");
    }
    
    public void openSession() throws DeviceException, AuthenticationException {
        throw new Error("Unresolved compilation problem: \n");
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
    
    public class MyUserInfo
    {
        String passwd;
        
        public MyUserInfo(final SshAccess2 sshAccess2, final String s) {
            throw new Error("Unresolved compilation problems: \n\tThe import org.jdom.Element cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tThe import com.jcraft cannot be resolved\n\tElement cannot be resolved to a type\n\tDocument cannot be resolved to a type\n\tSession cannot be resolved to a type\n\tChannel cannot be resolved to a type\n\tUserInfo cannot be resolved to a type\n");
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
