// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.test;

import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import com.stonewall.cornerstone.dsp.command.CliCommand;
import com.stonewall.cornerstone.dm.DeviceAccess;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.entity.Device;
import com.stonewall.cornerstone.entity.Protocol;
import com.stonewall.cornerstone.dsp.DSP;
import com.stonewall.cornerstone.component.Bootstrap;

public class TestTelnet
{
    static final String IP = "localhost";
    
    public static void main(final String[] args) {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            DSP.init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final Protocol p = new Protocol(Protocol.Type.telnet);
        p.setUsername("cisco");
        p.setPassword("cisco");
        final String ip = "localhost";
        final String hw = "sg1001";
        final String sw = "os_v2_2_1";
        final String method = "ls";
        final String params = "";
        final String result = "0";
        try {
            final Device d = new Device("1");
            d.setSoftware(sw);
            d.setHardware(hw);
            d.setIpAddress(new IpAddr(ip));
            d.setProtocol(p);
            final DeviceAccess access = DeviceAccess.createDeviceAccess(d);
            access.open();
            System.out.println("");
            System.out.println("Result:");
            final CliCommand command = new CliCommand();
            command.setCommand(method);
            command.addExpect(result);
            access.executeCommand(command);
            System.out.println(command.getResult().getAttachmentAsString());
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
