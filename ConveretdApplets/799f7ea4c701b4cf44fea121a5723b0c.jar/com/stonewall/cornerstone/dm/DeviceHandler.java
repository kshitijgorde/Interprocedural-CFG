// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import com.stonewall.cornerstone.dsp.command.Result;
import com.stonewall.cornerstone.dsp.command.OperationIterator;
import com.stonewall.cornerstone.component.ComponentServer;
import com.stonewall.cornerstone.remoteServer.CommManager;
import com.stonewall.cornerstone.remoteServer.Correlation;
import com.stonewall.cornerstone.dsp.command.DeviceOperation;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.entity.Device;

public class DeviceHandler
{
    private Device device;
    private DeviceAccess access;
    static final Log log;
    
    static {
        log = Log.getLog(DeviceHandler.class);
    }
    
    public DeviceHandler(final Device device) {
        this.device = device;
        this.access = DeviceAccess.createDeviceAccess(device);
    }
    
    public void shutdown() {
        DeviceHandler.log.info("In Shutdown for DeviceHandler = " + this.getId());
        this.access.close();
    }
    
    public void executeOperation(final DeviceOperation operation, final Correlation c) {
        DeviceHandler.log.info("starting device operation execution: " + operation.getId());
        this.execute(operation);
        try {
            DeviceHandler.log.info("ending device operation execution: " + operation.getId() + ", correlation: " + c + ", succeeded:" + operation.succeeded());
            final CommManager cm = (CommManager)ComponentServer.getInstance().getComponent(CommManager.class);
            cm.respond(c, operation);
        }
        catch (Exception e) {
            DeviceHandler.log.error("cannot send complete message to PS.", e);
        }
    }
    
    protected void execute(final DeviceOperation operation) {
        try {
            final OperationIterator opIter = new OperationIterator(this.device, operation);
            while (opIter.hasNext()) {
                final DeviceCommand command = opIter.next();
                if (!command.isEnabled()) {
                    command.succeed();
                }
                else {
                    if (!this.access.getCmdType().equals(command.getType())) {
                        operation.setStatus(Result.Status.failed);
                        operation.setFailureReason(Result.FailureType.execution, "The selected protocol does not support the command type");
                    }
                    try {
                        this.access.open();
                        DeviceHandler.log.info("Executing device command: " + operation.getId());
                        this.access.executeCommand(command);
                        DeviceHandler.log.info("completed device command: " + operation.getId());
                    }
                    catch (DeviceException de) {
                        DeviceHandler.log.error("Device Exception", de);
                        opIter.fail(Result.FailureType.communication, de.getMessage());
                    }
                    catch (AuthenticationException ae) {
                        DeviceHandler.log.error("Authentication Exception", ae);
                        opIter.fail(Result.FailureType.authentication, ae.getMessage());
                    }
                }
            }
            opIter.complete();
            if (operation.shouldCache()) {
                final String filename = operation.getCacheName();
                final String dirname = String.valueOf(this.device.getId()) + "_" + this.device.getName().replace(" ", "_");
                final Cache cache = new Cache(dirname, filename);
                cache.write(operation.getRawResponse());
            }
        }
        catch (Exception e) {
            DeviceHandler.log.error("execute operation", e);
            operation.setStatus(Result.Status.failed);
            operation.setFailureReason(Result.FailureType.execution, e.getMessage());
        }
    }
    
    public String getId() {
        return this.device.getId();
    }
    
    public Device getDevice() {
        return this.device;
    }
}
