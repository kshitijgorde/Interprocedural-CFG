// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.IModelObject;
import org.xmodel.log.Log;

public class DiscoverInfo
{
    public static final Log log;
    private IModelObject root;
    
    static {
        log = Log.getLog(DiscoverInfo.class);
    }
    
    public DiscoverInfo() {
        (this.root = new Element("en:discoverInfo")).addChild(new Element("en:types"));
        this.root.addChild(new Element("en:model"));
        this.root.addChild(new Element("en:serialNo"));
    }
    
    public DiscoverInfo(final IModelObject root) {
        this.root = root;
    }
    
    public boolean validate() {
        final IModelObject e = this.root;
        if (e == null) {
            DiscoverInfo.log.error("Element is null");
            return false;
        }
        String s = Xlate.get(e.getFirstChild("en:software"), (String)null);
        if (s == null) {
            DiscoverInfo.log.error("Software element is null");
            return false;
        }
        if (e.getFirstChild("en:types").getChildren("en:type").isEmpty()) {
            DiscoverInfo.log.error("Device type is null");
            return false;
        }
        s = Xlate.get(e.getFirstChild("en:model"), (String)null);
        if (s == null) {
            DiscoverInfo.log.error("Model element is null");
            return false;
        }
        s = Xlate.get(e.getFirstChild("en:serialNo"), (String)null);
        if (s == null) {
            DiscoverInfo.log.error("Serial Number element is null");
            return false;
        }
        return true;
    }
    
    public List<String> getTypes() {
        final List<String> types = new ArrayList<String>();
        for (final IModelObject type : this.root.getFirstChild("en:types").getChildren("en:type")) {
            types.add(Xlate.get(type, ""));
        }
        return types;
    }
    
    public void setCategory(final List<String> value) {
        final IModelObject types = this.root.getFirstChild("en:types");
        for (final String type : value) {
            final IModelObject o = new Element("en:type");
            o.setValue(type);
            types.addChild(o);
        }
    }
    
    public String getModel() {
        return Xlate.get(this.root.getFirstChild("en:model"), (String)null);
    }
    
    public void setModel(final String value) {
        this.root.getFirstChild("en:model").setValue(value);
    }
    
    public String getHostname() {
        return Xlate.get(this.root.getFirstChild("en:hostname"), (String)null);
    }
    
    public void setHostname(final String value) {
        this.root.getCreateChild("en:hostname").setValue(value);
    }
    
    public String getSerialNumber() {
        return Xlate.get(this.root.getFirstChild("en:serialNo"), (String)null);
    }
    
    public void setSerialNumber(final String value) {
        this.root.getFirstChild("en:serialNo").setValue(value);
    }
    
    public String getSoftware() {
        return Xlate.get(this.root.getFirstChild("en:software"), (String)null);
    }
    
    public String getHardware() {
        return Xlate.get(this.root.getFirstChild("en:hardware"), (String)null);
    }
    
    public String getVendor() {
        return Xlate.get(this.root.getFirstChild("en:vendor"), (String)null);
    }
    
    protected void trim() {
        this.root.removeChildren("en:software");
        this.root.removeChildren("en:hardware");
        this.root.removeChildren("en:vendor");
        this.root.removeChildren("en:types");
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.getRoot(), IXmlIO.Style.printable);
    }
}
