// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class CliCommand extends DeviceCommand
{
    public CliCommand() {
        super("en:cliCommand", Type.cli);
    }
    
    public CliCommand(final IModelObject o) {
        super(o);
    }
    
    @Override
    protected void init() {
        super.init();
        this.root.addChild(new Element("en:cmd"));
    }
    
    public String getCommand() {
        return Xlate.get(this.root.getFirstChild("en:cmd"), "");
    }
    
    public void setCommand(final String s) {
        final IModelObject o = this.root.getFirstChild("en:cmd");
        o.setValue(s);
    }
    
    @Override
    protected void determineStatus() {
        final List<FailurePattern> patterns = new ArrayList<FailurePattern>();
        final List<IModelObject> children = this.root.getFirstChild("en:failurePatterns").getChildren();
        for (final IModelObject o : children) {
            patterns.add(new FailurePattern(Xlate.get(o, "")));
        }
        this.succeed();
        final Result r = this.getResult();
        final IModelObject attachment = r.getAttachment();
        for (final FailurePattern p : patterns) {
            final String s = Xlate.get(attachment, "");
            if (p.match(s)) {
                this.fail(s);
            }
        }
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.getCommand());
        sb.append("\n");
        return sb.toString();
    }
}
