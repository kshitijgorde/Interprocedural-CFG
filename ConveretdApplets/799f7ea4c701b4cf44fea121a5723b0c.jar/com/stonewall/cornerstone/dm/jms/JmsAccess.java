// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.jms;

import java.util.List;
import com.stonewall.cornerstone.dsp.command.Result;
import com.stonewall.cornerstone.remoteServer.Request;
import java.util.Collection;
import java.util.ArrayList;
import com.stonewall.cornerstone.dsp.command.JmsCommand;
import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import com.stonewall.cornerstone.dm.AuthenticationException;
import com.stonewall.cornerstone.dm.DeviceException;
import com.stonewall.cornerstone.entity.Server;
import com.stonewall.cornerstone.entity.Alarm;
import com.stonewall.cornerstone.component.ComponentServer;
import com.stonewall.cornerstone.remoteServer.CommManager;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.entity.ProtocolServer;
import com.stonewall.cornerstone.dm.DeviceAccess;

public class JmsAccess extends DeviceAccess
{
    protected ProtocolServer server;
    
    public JmsAccess(final Device device) {
        super(device);
        this.server = this.getProtocol().getServer();
    }
    
    public void openSession() throws DeviceException, AuthenticationException {
        final ProtocolServer s = this.getProtocol().getServer();
        JmsAccess.log.debug("Open session for " + this.getAddress());
        final CommManager cm = (CommManager)ComponentServer.getInstance().getComponent(CommManager.class);
        if (!cm.hasServer(s.getId())) {
            try {
                this.server.setHighestAlarmSeverity(Alarm.Severity.none);
                cm.addServer(this.server);
            }
            catch (Exception e) {
                throw new DeviceException(e);
            }
        }
    }
    
    @Override
    public void closeSession() {
        JmsAccess.log.debug("Close session for " + this.getAddress());
    }
    
    public void sendAndWait(final DeviceCommand command) throws DeviceException, AuthenticationException {
        try {
            final JmsCommand jms = (JmsCommand)command;
            final List<Object> params = new ArrayList<Object>();
            params.add(this.getAddress());
            params.addAll(jms.getParameters());
            final Request request = new Request(this.server.getId(), true, jms.getTarget(), jms.getMethod(), params.toArray());
            JmsAccess.log.debug("Executing device command: " + request);
            final CommManager cm = (CommManager)ComponentServer.getInstance().getComponent(CommManager.class);
            jms.setResult((Result)cm.send(request));
        }
        catch (Exception ex) {
            JmsAccess.log.error(ex);
            throw new DeviceException("Communication to remote server " + this.server.getDisplayName() + " has failed");
        }
    }
    
    @Override
    public DeviceCommand.Type getCmdType() {
        return DeviceCommand.Type.jms;
    }
}
