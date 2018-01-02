// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import com.stonewall.cornerstone.dsp.command.DeviceOperation;
import java.util.Iterator;
import java.util.List;
import com.stonewall.cornerstone.entity.DiscoverInfo;
import com.stonewall.cornerstone.dsp.command.Result;
import com.stonewall.cornerstone.dsp.command.OperationInterpreter;
import com.stonewall.cornerstone.dsp.packaging.DeviceDef;
import com.stonewall.cornerstone.dsp.packaging.Packaging;
import com.stonewall.cornerstone.component.ComponentServer;
import com.stonewall.cornerstone.remoteServer.CommManager;
import com.stonewall.cornerstone.remoteServer.Correlation;
import com.stonewall.cornerstone.entity.Device;
import org.xmodel.log.Log;

public class DiscoverAction extends AdminAction
{
    static final Log log;
    
    static {
        log = Log.getLog(DiscoverAction.class);
    }
    
    public void discoverDevice(final Device d, final Correlation c) {
        try {
            final CommManager cm = (CommManager)ComponentServer.getInstance().getComponent(CommManager.class);
            String failureReason = "";
            final List<DeviceDef> defs = Packaging.getDeviceDefs(d.getVendor(), d.getHardware());
            final List<DeviceDef> uniqueDefs = Packaging.uniqueDefsByResource(defs);
            if (uniqueDefs.isEmpty()) {
                failureReason = "Cannot find device definition for Device(" + d.getName() + ") " + d.getVendor() + "(" + d.getHardware() + ").";
            }
            for (final DeviceDef def : uniqueDefs) {
                DiscoverAction.log.debug("Trying to discover: " + def.getSoftware() + " " + def.getHardware());
                d.setSoftware(def.getSoftware());
                d.setHardware(def.getHardware());
                final DeviceHandler handler = new DeviceHandler(d);
                try {
                    final OperationInterpreter interpreter = new OperationInterpreter(d);
                    final DeviceOperation op = interpreter.generateOperation("discover.xml");
                    handler.execute(op);
                    if (!op.succeeded() && op.getFailureType() == Result.FailureType.communication) {
                        failureReason = op.getFailureReason();
                        break;
                    }
                    final DiscoverInfo info = op.getDiscoverInfo();
                    if (info.validate()) {
                        handler.shutdown();
                        cm.respond(c, info, failureReason);
                        return;
                    }
                    failureReason = "Discovered content is not valid";
                }
                catch (Exception e) {
                    DiscoverAction.log.error(this, e);
                    failureReason = "Cannot find device definition for device:" + d.getVendor() + "," + d.getHardware();
                }
                handler.shutdown();
            }
            DiscoverAction.log.error(failureReason);
            cm.respond(c, new DiscoverInfo(), failureReason);
        }
        catch (Exception e2) {
            DiscoverAction.log.error("Discover Action", e2);
        }
    }
}
