// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.Element;

public class ESPTransform extends Transform
{
    protected static String tag;
    
    static {
        ESPTransform.tag = "en:esp";
    }
    
    public ESPTransform() {
        super(ESPTransform.tag);
        this.root.addChild(new Element("en:cipher"));
        this.root.addChild(new Element("en:hash"));
        this.root.addChild(new Element("en:pfs"));
        this.root.addChild(new Element("en:replayPrevention"));
    }
    
    public ESPTransform(final IModelObject root) {
        super(root);
    }
    
    public ESPTransform(final String cipher, final String hash, final boolean pfs, final boolean replay) {
        this();
        this.setCipher(cipher);
        this.setHash(hash);
        this.setPFS(pfs);
        this.setReplayPrevention(replay);
    }
    
    public ESPTransform(final String cipher, final String hash, final boolean pfs) {
        this();
        this.setCipher(cipher);
        this.setHash(hash);
        this.setPFS(pfs);
        this.setReplayPrevention(false);
    }
    
    public void setCipher(final String value) {
        this.root.getFirstChild("en:cipher").setValue(value);
    }
    
    public String getCipher() {
        return Xlate.get(this.root.getFirstChild("en:cipher"), (String)null);
    }
    
    public void setHash(final String value) {
        this.root.getFirstChild("en:hash").setValue(value);
    }
    
    public String getHash() {
        return Xlate.get(this.root.getFirstChild("en:hash"), (String)null);
    }
    
    public void setPFS(final boolean value) {
        this.root.getFirstChild("en:pfs").setValue(String.valueOf(value));
    }
    
    public boolean usePFS() {
        return new Boolean(Xlate.get(this.root.getFirstChild("en:pfs"), (String)null));
    }
    
    public void setReplayPrevention(final boolean value) {
        this.root.getFirstChild("en:replayPrevention").setValue(String.valueOf(value));
    }
    
    public boolean useReplayPrevention() {
        return Xlate.get(this.root.getFirstChild("en:replayPrevention"), (String)null) != null && new Boolean(Xlate.get(this.root.getFirstChild("en:replayPrevention"), (String)null));
    }
    
    @Override
    public Type getType() {
        return Type.esp;
    }
}
