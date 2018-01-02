// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.remoteServer;

import org.xmodel.Element;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public class Correlation
{
    private String fromid;
    private String toid;
    private String tag;
    
    public Correlation(final IModelObject e) {
        this.tag = Xlate.get(e.getFirstChild("tag"), (String)null);
        this.fromid = Xlate.get(e.getFirstChild("fromid"), (String)null);
        this.toid = Xlate.get(e.getFirstChild("toid"), (String)null);
    }
    
    public Correlation(final String fromid, final String toid, final String tag) {
        this.fromid = fromid;
        this.toid = toid;
        this.tag = tag;
    }
    
    public String getFromId() {
        return this.fromid;
    }
    
    public void setFromId(final String id) {
        this.fromid = id;
    }
    
    public String getToId() {
        return this.toid;
    }
    
    public void setToId(final String id) {
        this.toid = id;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public IModelObject getRoot() {
        final IModelObject root = new Element("response");
        root.getCreateChild("tag").setValue(this.tag);
        if (this.fromid != null) {
            root.getCreateChild("fromid").setValue(this.fromid);
        }
        if (this.toid != null) {
            root.getCreateChild("toid").setValue(this.toid);
        }
        return root;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Correlation)) {
            return false;
        }
        final Correlation c = (Correlation)obj;
        return this.tag.equals(c.tag);
    }
    
    @Override
    public int hashCode() {
        return this.tag.hashCode();
    }
    
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("Correlation\n{\n");
        buf.append("correlation:");
        buf.append(this.tag);
        buf.append(";from:");
        buf.append(this.fromid);
        buf.append(";to:");
        buf.append(this.toid);
        buf.append("\n}\n");
        return buf.toString();
    }
}
