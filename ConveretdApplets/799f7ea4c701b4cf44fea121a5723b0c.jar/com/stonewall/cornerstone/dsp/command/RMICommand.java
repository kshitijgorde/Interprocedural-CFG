// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public abstract class RMICommand extends DeviceCommand
{
    public RMICommand(final String tag, final Type type) {
        super(tag, type);
    }
    
    public RMICommand(final IModelObject o) {
        super(o);
    }
    
    @Override
    protected void init() {
        super.init();
        this.root.addChild(new Element("en:target"));
        this.root.addChild(new Element("en:method"));
        this.root.addChild(new Element("en:parameters"));
    }
    
    public void setTarget(final String s) {
        final IModelObject o = this.root.getFirstChild("en:target");
        o.setValue(s);
    }
    
    public String getTarget() {
        return Xlate.get(this.root.getFirstChild("en:target"), "");
    }
    
    public void setMethod(final String s) {
        final IModelObject o = this.root.getFirstChild("en:method");
        o.setValue(s);
    }
    
    public String getMethod() {
        return Xlate.get(this.root.getFirstChild("en:method"), "");
    }
    
    public void addParameter(final String s) {
        final IModelObject p = new Element("en:parameter");
        p.setValue(s);
        p.setAttribute("class", s.getClass().getName());
        this.root.getFirstChild("en:parameters").addChild(p);
    }
    
    public void addParameter(final Number i) {
        final IModelObject p = new Element("en:parameter");
        p.setValue(String.valueOf(i));
        p.setAttribute("class", i.getClass().getName());
        this.root.getFirstChild("en:parameters").addChild(p);
    }
    
    public List<Object> getParameters() {
        final List<Object> parameters = new ArrayList<Object>();
        final List<IModelObject> paramObjs = this.root.getFirstChild("en:parameters").getChildren();
        for (final IModelObject o : paramObjs) {
            final String cName = Xlate.get(o, "class", "");
            if (cName.equals(String.class.getName())) {
                parameters.add(Xlate.get(o, ""));
            }
            else {
                if (!cName.equals(Integer.class.getName())) {
                    continue;
                }
                final Integer param = new Integer(Xlate.get(o, ""));
                parameters.add(param);
            }
        }
        return parameters;
    }
}
