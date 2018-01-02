// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.entity.IPInterface;
import java.util.ArrayList;
import com.stonewall.cornerstone.utility.Server;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.remoteServer.Request;
import java.util.List;
import com.stonewall.cornerstone.remoteServer.CommManager;
import com.stonewall.cornerstone.component.ComponentServer;

public class ServerManagement extends com.stonewall.cornerstone.remoteServer.ServerManagement
{
    @Override
    public void init(final ComponentServer server) throws Exception {
        super.init(server);
        final CommManager cm = (CommManager)server.getComponent(CommManager.class);
        this.updateInterfaces(cm, server.getId());
        final DeployedDevicesRequest r = new DeployedDevicesRequest(server.getId());
        final List<Device> devices = (List<Device>)cm.send(r);
        final DeployAction action = (DeployAction)server.getComponent(DeployAction.class);
        action.deployDevice(devices);
    }
    
    private void updateInterfaces(final CommManager cm, final String id) throws Exception {
        final Server host = new Server();
        final List<IPInterface> l = new ArrayList<IPInterface>();
        Server.IntRole[] values;
        for (int length = (values = Server.IntRole.values()).length, i = 0; i < length; ++i) {
            final Server.IntRole r = values[i];
            final String address = host.getAddress(r);
            if (address != null) {
                final IPInterface intf = new IPInterface();
                intf.setParentId(id);
                intf.setIpAddress(new IpAddr(address));
                intf.setIdentifier(host.identifier(r));
                intf.setName(r.name());
                l.add(intf);
            }
        }
        final UpdateInterfacesRequest r2 = new UpdateInterfacesRequest(id, l);
        cm.send(r2);
    }
}
