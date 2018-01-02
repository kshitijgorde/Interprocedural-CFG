// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import com.stonewall.cornerstone.utility.Checksum;
import com.stonewall.cornerstone.entity.DeviceConfig;
import com.stonewall.cornerstone.entity.DiscoverInfo;
import org.xmodel.Element;
import org.xmodel.Xlate;
import org.xmodel.IPath;
import org.xmodel.xpath.XPath;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmodel.IModelObject;

public class DeviceOperation extends com.stonewall.cornerstone.entity.DeviceOperation
{
    public DeviceOperation() {
    }
    
    public DeviceOperation(final String id) {
        super(id);
    }
    
    public DeviceOperation(final IModelObject root) {
        super(root);
    }
    
    public void addCommand(final DeviceCommand command) {
        final IModelObject commands = this.root.getFirstChild("en:commands");
        commands.addChild(command.getRoot().cloneTree());
    }
    
    public void setCommands(final List<DeviceCommand> commands) {
        final IModelObject parent = this.root.getFirstChild("en:commands");
        parent.removeChildren();
        for (final DeviceCommand command : commands) {
            this.addCommand(command);
        }
    }
    
    public List<DeviceCommand> getCommands() {
        final List<DeviceCommand> l = new ArrayList<DeviceCommand>();
        final List<IModelObject> commands = this.root.getFirstChild("en:commands").getChildren();
        for (final IModelObject o : commands) {
            l.add(DeviceCommand.createCommand(o));
        }
        return l;
    }
    
    public void setAttachment(final IModelObject a) {
        final IModelObject attachment = this.root.getFirstChild("en:attachment");
        attachment.addChild(a);
    }
    
    public void replaceAttachment(final IModelObject a) {
        final IModelObject attachment = this.root.getFirstChild("en:attachment");
        attachment.removeChildren();
        attachment.addChild(a);
    }
    
    public String getRawResponse() {
        final StringBuilder sb = new StringBuilder();
        for (final DeviceCommand c : this.getCommands()) {
            final Result r = c.getResult();
            sb.append(r.getAttachmentAsString());
        }
        return sb.toString();
    }
    
    public void clearResponses() {
        for (final DeviceCommand c : this.getCommands()) {
            final Result r = c.getResult();
            r.clearAttachment();
        }
    }
    
    public boolean shouldCache() {
        final IPath path = XPath.createPath("en:commands/*/en:cacheName");
        final List<IModelObject> names = path.query(this.root, null);
        return !names.isEmpty();
    }
    
    public String getCacheName() {
        final IPath path = XPath.createPath("en:commands/*/en:cacheName");
        final IModelObject name = path.queryFirst(this.root);
        return Xlate.get(name, (String)null);
    }
    
    public String getParser() {
        final IPath path = XPath.createPath("en:commands/*/en:parser");
        final IModelObject parser = path.queryFirst(this.root);
        return Xlate.get(parser, (String)null);
    }
    
    public boolean succeeded() {
        final IPath path = XPath.createPath("en:commands/*[en:result/en:status='failed']");
        final List<IModelObject> failed = path.query(this.root, null);
        return failed.isEmpty();
    }
    
    public String getFailureReason() {
        final IPath path = XPath.createPath("en:commands/*/en:result[en:status='failed']/en:failureReason");
        final IModelObject failure = path.queryFirst(this.root);
        return Xlate.get(failure, (String)null);
    }
    
    public Result.FailureType getFailureType() {
        final IPath path = XPath.createPath("en:commands/*/en:result[en:status='failed']/en:failureReason");
        final IModelObject failure = path.queryFirst(this.root);
        if (failure != null) {
            return Result.FailureType.valueOf(Xlate.get(failure, "type", ""));
        }
        return null;
    }
    
    public void setFailureReason(final Result.FailureType type, final String reason) {
        final IPath path = XPath.createPath("en:commands/*/en:result");
        final List<IModelObject> results = path.query(this.root, null);
        for (final IModelObject result : results) {
            IModelObject failure = result.getFirstChild("en:failureReason");
            if (failure == null) {
                failure = new Element("en:failureReason");
                result.addChild(failure);
            }
            failure.setAttribute("type", type.name());
            failure.setValue(reason);
        }
    }
    
    public void clearFailureReason() {
        final IPath path = XPath.createPath("en:commands/*/en:result");
        final List<IModelObject> results = path.query(this.root, null);
        for (final IModelObject result : results) {
            final IModelObject failure = result.getFirstChild("en:failureReason");
            failure.removeFromParent();
        }
    }
    
    public void setStatus(final Result.Status status) {
        final IPath path = XPath.createPath("en:commands/*/en:result/en:status");
        final List<IModelObject> l = path.query(this.root, null);
        for (final IModelObject s : l) {
            s.setValue(status.name());
        }
    }
    
    public DiscoverInfo getDiscoverInfo() {
        final IModelObject a = this.root.getFirstChild("en:attachment");
        final IModelObject di = a.getFirstChild("en:discoverInfo");
        if (di != null) {
            return new DiscoverInfo(di);
        }
        return new DiscoverInfo();
    }
    
    public DeviceConfig getDeviceConfig() {
        final IModelObject a = this.root.getFirstChild("en:attachment");
        final IModelObject dc = a.getFirstChild("en:deviceConfig");
        if (dc != null) {
            return new DeviceConfig(dc);
        }
        return null;
    }
    
    public Checksum getChecksum() {
        final IModelObject a = this.root.getFirstChild("en:attachment");
        final IModelObject c = a.getFirstChild("en:checksum");
        if (c != null) {
            return new Checksum(c);
        }
        return null;
    }
}
