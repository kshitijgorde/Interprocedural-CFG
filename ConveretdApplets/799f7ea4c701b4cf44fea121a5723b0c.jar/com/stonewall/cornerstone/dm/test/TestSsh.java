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

public class TestSsh
{
    public static void main(final String[] args) {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            DSP.init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final Protocol p = new Protocol(Protocol.Type.ssh);
        p.setUsername("root");
        p.setPassword("cyberwerx");
        final String ip = "192.168.1.13";
        final String hw = "pix501";
        final String sw = "pix_ios_v6_1_1";
        final String method = "?";
        final String params = "";
        final String result = "";
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
            System.out.println(result);
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
