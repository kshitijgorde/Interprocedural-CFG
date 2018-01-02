// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class HttpCommand extends DeviceCommand
{
    public HttpCommand() {
        super("en:httpCommand", Type.http);
    }
    
    public HttpCommand(final IModelObject o) {
        super(o);
    }
    
    @Override
    protected void init() {
        super.init();
        this.root.addChild(new Element("en:mode"));
        this.root.addChild(new Element("en:command"));
        this.root.addChild(new Element("en:parameter"));
        this.root.addChild(new Element("en:expected"));
    }
    
    public Mode getMode() {
        return Mode.valueOf(Xlate.get(this.root.getFirstChild("en:mode"), ""));
    }
    
    public String getCommand() {
        return Xlate.get(this.root.getFirstChild("en:command"), "");
    }
    
    public String getParameter() {
        return Xlate.get(this.root.getFirstChild("en:parameter"), "");
    }
    
    public String getExpected() {
        return Xlate.get(this.root.getFirstChild("en:expected"), "");
    }
    
    @Override
    protected void determineStatus() {
        this.getResult().succeed();
    }
    
    public enum Mode
    {
        multiget("multiget", 0), 
        post("post", 1), 
        get("get", 2);
        
        private Mode(final String s, final int n) {
        }
    }
}
