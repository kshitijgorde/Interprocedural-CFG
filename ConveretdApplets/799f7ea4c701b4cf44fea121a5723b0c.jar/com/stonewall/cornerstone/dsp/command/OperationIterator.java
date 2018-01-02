// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import org.xmodel.IModelObject;
import com.stonewall.cornerstone.dsp.parser.IParser;
import java.util.List;
import com.stonewall.cornerstone.entity.Device;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.dsp.loader.Loader;
import java.util.ListIterator;

public class OperationIterator
{
    private DeviceOperation operation;
    private ListIterator<DeviceCommand> iter;
    private DeviceCommand current;
    private Loader loader;
    static final Log log;
    static final XMLOutputter xo;
    
    static {
        log = Log.getLog(OperationIterator.class);
        xo = new XMLOutputter(Format.getPrettyFormat());
    }
    
    public OperationIterator(final Device device, final DeviceOperation operation) {
        this.loader = new Loader(device.getSoftware(), device.getHardware());
        this.operation = operation;
        final List<DeviceCommand> commands = operation.getCommands();
        this.iter = commands.listIterator();
    }
    
    public DeviceCommand next() {
        this.current = this.iter.next();
        OperationIterator.log.debug("Next command:" + this.current);
        return this.current;
    }
    
    public boolean hasNext() {
        return this.iter.hasNext();
    }
    
    public void complete() {
        if (this.operation.succeeded()) {
            try {
                final String clazz = this.operation.getParser();
                if (clazz == null) {
                    return;
                }
                final IParser parser = (IParser)this.loader.newInstance(clazz, this.loader);
                final IModelObject doc = parser.parse(this.operation);
                this.operation.setAttachment(doc);
            }
            catch (Exception le) {
                OperationIterator.log.error(this, le);
            }
        }
    }
    
    public int currState() {
        return -1;
    }
    
    public void fail(final Result.FailureType type, final String s) {
        this.current.fail(type, s);
    }
}
