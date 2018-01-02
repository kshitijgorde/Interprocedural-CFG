// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class Result
{
    private IModelObject root;
    
    public Result() {
        this.init();
    }
    
    public Result(final Status status) {
        this.init();
        this.setStatus(status);
    }
    
    public Result(final IModelObject o) {
        this.root = o;
    }
    
    private void init() {
        (this.root = new Element("en:result")).addChild(new Element("en:status"));
        this.setStatus(Status.pending);
    }
    
    public void fail(final String s) {
        this.setStatus(Status.failed);
        this.setFailure(FailureType.execution, s);
    }
    
    public void succeed() {
        this.setStatus(Status.succeeded);
    }
    
    public boolean succeeded() {
        return this.getStatus().equals(Status.succeeded);
    }
    
    public Status getStatus() {
        return Status.valueOf(Xlate.get(this.root.getFirstChild("en:status"), (String)null));
    }
    
    void setStatus(final Status s) {
        this.root.getFirstChild("en:status").setValue(s.name());
    }
    
    public String getFailureReason() {
        return Xlate.get(this.root.getFirstChild("en:failureReason"), (String)null);
    }
    
    public void setFailure(final FailureType type, final String s) {
        final IModelObject reason = new Element("en:failureReason");
        reason.setValue(s);
        reason.setAttribute("type", type.name());
        this.root.addChild(reason);
    }
    
    public void setAttachment(final String s) {
        final IModelObject a = new Element("en:attachment");
        this.root.addChild(a);
        a.setValue(s);
    }
    
    public void setAttachment(final IModelObject o) {
        final IModelObject a = new Element("en:attachment");
        this.root.addChild(a);
        a.addChild(o);
    }
    
    public IModelObject getAttachment() {
        return this.root.getFirstChild("en:attachment");
    }
    
    public void clearAttachment() {
        final IModelObject o = this.getAttachment();
        if (o != null) {
            o.removeFromParent();
        }
    }
    
    public String getAttachmentAsString() {
        final IModelObject o = this.getAttachment();
        if (o == null || o.getNumberOfChildren() == 0) {
            return Xlate.get(o, "");
        }
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.root, IXmlIO.Style.compact);
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public enum FailureType
    {
        communication("communication", 0), 
        authentication("authentication", 1), 
        execution("execution", 2);
        
        private FailureType(final String s, final int n) {
        }
    }
    
    public enum Status
    {
        pending("pending", 0), 
        succeeded("succeeded", 1), 
        failed("failed", 2);
        
        private Status(final String s, final int n) {
        }
    }
}
