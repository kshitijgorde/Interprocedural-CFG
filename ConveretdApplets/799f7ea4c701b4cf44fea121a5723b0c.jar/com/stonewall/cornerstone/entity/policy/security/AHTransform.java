// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.Element;

public class AHTransform extends Transform
{
    protected static String tag;
    
    static {
        AHTransform.tag = "en:ah";
    }
    
    public AHTransform() {
        super(AHTransform.tag);
        this.root.addChild(new Element("en:hash"));
        this.root.addChild(new Element("en:replayPrevention"));
    }
    
    public AHTransform(final IModelObject root) {
        super(root);
    }
    
    public AHTransform(final String hash, final boolean replay) {
        this();
        this.setHash(hash);
        this.setReplayPrevention(replay);
    }
    
    public AHTransform(final String hash) {
        this();
        this.setHash(hash);
        this.setReplayPrevention(false);
    }
    
    public void setHash(final String value) {
        this.root.getFirstChild("en:hash").setValue(value);
    }
    
    public String getHash() {
        return Xlate.get(this.root.getFirstChild("en:hash"), (String)null);
    }
    
    public void setReplayPrevention(final boolean value) {
        this.root.getFirstChild("en:replayPrevention").setValue(String.valueOf(value));
    }
    
    public boolean useReplayPrevention() {
        return Xlate.get(this.root.getFirstChild("en:replayPrevention"), (String)null) != null && new Boolean(Xlate.get(this.root.getFirstChild("en:replayPrevention"), (String)null));
    }
    
    @Override
    public Type getType() {
        return Type.ah;
    }
}
