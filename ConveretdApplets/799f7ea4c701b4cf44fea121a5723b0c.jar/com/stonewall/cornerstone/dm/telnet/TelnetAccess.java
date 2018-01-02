// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.telnet;

import java.util.List;
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

public class TelnetAccess extends DeviceAccess
{
    protected Telnet telnet;
    
    public TelnetAccess(final Device device) {
        super(device);
    }
    
    public void openSession() throws DeviceException, AuthenticationException {
        TelnetAccess.log.debug("Open session for " + this.getAddress());
        try {
            (this.telnet = new Telnet()).connect(this.getAddress(), this.getPort());
            this.telnet.setPrompt(this.cmdPrompt);
            final String result = this.telnet.loginResult(this.loginPrompt, this.passwordPrompt, this.getUsername(), this.getPassword());
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
            throw new DeviceException(ioex);
        }
    }
    
    @Override
    public void closeSession() {
        TelnetAccess.log.debug("Close session for " + this.getAddress());
        this.telnet.disconnect();
        this.telnet = null;
    }
    
    public void sendAndWait(final DeviceCommand command) throws DeviceException, AuthenticationException {
        try {
            final CliCommand cliCommand = (CliCommand)command;
            TelnetAccess.log.debug("Executing device command: " + cliCommand.getCommand());
            final List<String> cmdWaits = cliCommand.getExpect();
            String s = this.telnet.executeCommand(cliCommand.getCommand(), cmdWaits);
            TelnetAccess.log.debug(s);
            if ((!cmdWaits.isEmpty() && (s == null || s.length() == 0)) || s.toLowerCase().contains("time-out activated")) {
                this.close();
                this.open();
                s = this.telnet.executeCommand(cliCommand.getCommand(), cliCommand.getExpect());
                TelnetAccess.log.debug(s);
            }
            cliCommand.setResponse(s);
        }
        catch (SocketException soEx) {
            TelnetAccess.log.debug(this, soEx);
            this.close();
            this.open();
            this.sendAndWait(command);
        }
        catch (IOException ioEx) {
            TelnetAccess.log.debug(this, ioEx);
            this.close();
            this.open();
            this.sendAndWait(command);
        }
        catch (Exception ex) {
            TelnetAccess.log.debug(this, ex);
            this.close();
            throw new DeviceException(ex.getMessage());
        }
    }
    
    @Override
    public DeviceCommand.Type getCmdType() {
        return DeviceCommand.Type.cli;
    }
}
