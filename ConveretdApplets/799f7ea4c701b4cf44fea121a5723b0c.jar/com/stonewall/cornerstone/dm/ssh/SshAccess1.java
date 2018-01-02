// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.ssh;

import java.net.SocketException;
import com.stonewall.cornerstone.dsp.command.CliCommand;
import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import java.util.regex.Matcher;
import java.io.IOException;
import com.stonewall.cornerstone.dm.DeviceException;
import java.util.regex.Pattern;
import com.stonewall.cornerstone.dm.AuthenticationException;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.dm.DeviceAccess;

public class SshAccess1 extends DeviceAccess
{
    protected SSH1 ssh;
    
    public SshAccess1(final Device device) {
        super(device);
        this.ssh = new SSH1();
    }
    
    public void openSession() throws DeviceException, AuthenticationException {
        SshAccess1.log.debug("Open session for " + this.getAddress());
        try {
            (this.ssh = new SSH1()).connect(this.getAddress(), this.getPort());
            this.ssh.setPrompt(this.cmdPrompt);
            final String result = this.ssh.loginResult(this.loginPrompt, this.passwordPrompt, this.getUsername(), this.getPassword());
            if (result == null) {
                throw new AuthenticationException("Authentication problem on " + this.getAddress());
            }
            final Pattern p = Pattern.compile(this.cmdPrompt);
            final Matcher m = p.matcher(result);
            if (!m.find() && !result.contains(this.cmdPrompt)) {
                throw new AuthenticationException("Authentication problem on " + this.getAddress());
            }
        }
        catch (IOException ioex) {
            throw new DeviceException(ioex.getMessage());
        }
        this.ssh.setPrompt(null);
    }
    
    @Override
    public void closeSession() {
        SshAccess1.log.debug("Close session for " + this.getAddress());
        this.ssh.disconnect();
    }
    
    public void sendAndWait(final DeviceCommand command) throws DeviceException, AuthenticationException {
        try {
            final CliCommand cliCommand = (CliCommand)command;
            SshAccess1.log.debug("Executing device command: " + cliCommand.getCommand());
            final String s = this.ssh.executeCommand(cliCommand.getCommand(), cliCommand.getExpect());
            SshAccess1.log.debug(s);
            if (s == null || s.length() == 0 || s.toLowerCase().contains("time-out activated")) {
                this.close();
                this.open();
                this.sendAndWait(command);
            }
            cliCommand.setResponse(s);
        }
        catch (SocketException soEx) {
            SshAccess1.log.debug(this, soEx);
            this.close();
            this.open();
            this.sendAndWait(command);
        }
        catch (IOException ioEx) {
            SshAccess1.log.debug(this, ioEx);
            this.close();
            this.open();
            this.sendAndWait(command);
        }
        catch (Exception ex) {
            SshAccess1.log.debug(this, ex);
            this.close();
            throw new DeviceException(ex.getMessage());
        }
    }
    
    @Override
    public DeviceCommand.Type getCmdType() {
        return DeviceCommand.Type.cli;
    }
}
