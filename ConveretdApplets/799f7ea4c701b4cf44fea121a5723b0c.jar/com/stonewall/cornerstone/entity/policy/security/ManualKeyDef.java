// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class ManualKeyDef extends TunnelDef
{
    protected static String tag;
    
    static {
        ManualKeyDef.tag = "en:manualkeyAction";
    }
    
    public ManualKeyDef() {
        super(ManualKeyDef.tag);
    }
    
    public ManualKeyDef(final IModelObject root) {
        super(root);
    }
    
    public ManualKeyDef(final int localSpi, final int remoteSpi, final String cipherSecret, final String cipherSecretType, final String hashSecret, final String hashSecretType, final Transform trans, final String type, final String group) {
        this();
        this.setLocalSpi(localSpi);
        this.setRemoteSpi(remoteSpi);
        this.setCipherSecret(cipherSecret, cipherSecretType);
        this.setHashSecret(hashSecret, hashSecretType);
        this.setTransform(trans);
        this.setSAType(type);
        this.setGroup(group);
    }
    
    @Override
    protected void init() {
        final IModelObject spi = new Element("en:spi");
        this.root.addChild(spi);
        spi.addChild(new Element("en:local"));
        spi.addChild(new Element("en:remote"));
        this.root.addChild(new Element("en:cipherSecret"));
        this.root.addChild(new Element("en:hashSecret"));
        this.root.addChild(new Element("en:transform"));
        this.root.addChild(new Element("en:type"));
        this.root.addChild(new Element("en:group"));
    }
    
    public void setLocalSpi(final int value) {
        this.root.getFirstChild("en:spi").getFirstChild("en:local").setValue(String.valueOf(value));
    }
    
    public int getLocalSpi() {
        return Xlate.get(this.root.getFirstChild("en:spi").getFirstChild("en:local"), 0);
    }
    
    public void setRemoteSpi(final int value) {
        this.root.getFirstChild("en:spi").getFirstChild("en:remote").setValue(String.valueOf(value));
    }
    
    public int getRemoteSpi() {
        return Xlate.get(this.root.getFirstChild("en:spi").getFirstChild("en:remote"), 0);
    }
    
    public void reverseSPI() {
        final IModelObject spi = this.root.getFirstChild("en:spi");
        final IModelObject localSpi = spi.getFirstChild("en:local");
        final IModelObject remoteSpi = spi.getFirstChild("en:remote");
        final String local = Xlate.get(localSpi, (String)null);
        final String remote = Xlate.get(remoteSpi, (String)null);
        localSpi.setValue(remote);
        remoteSpi.setValue(local);
    }
    
    public void setCipherSecret(final String value, final String type) {
        final IModelObject e = this.root.getFirstChild("en:cipherSecret");
        e.setValue(value);
        e.setAttribute("type", type);
    }
    
    public String getCipherSecret() {
        return Xlate.get(this.root.getFirstChild("en:cipherSecret"), (String)null);
    }
    
    public String getCipherSecretType() {
        return Xlate.get(this.root.getFirstChild("en:cipherSecret"), "type", (String)null);
    }
    
    public void setHashSecret(final String value, final String type) {
        final IModelObject e = this.root.getFirstChild("en:hashSecret");
        e.setValue(value);
        e.setAttribute("type", type);
    }
    
    public String getHashSecret() {
        return Xlate.get(this.root.getFirstChild("en:hashSecret"), (String)null);
    }
    
    public String getHashSecretType() {
        return Xlate.get(this.root.getFirstChild("en:hashSecret"), "type", (String)null);
    }
    
    public void setTransform(final Transform value) {
        final IModelObject transform = this.root.getFirstChild("en:transform");
        transform.addChild(value.getRoot().cloneTree());
    }
    
    public Transform getTransform() {
        final List<IModelObject> content = this.root.getFirstChild("en:transform").getChildren();
        return Transform.createTransform(content.get(0));
    }
    
    public void setSAType(final String value) {
        this.root.getFirstChild("en:type").setValue(value);
    }
    
    public String getSAType() {
        return Xlate.get(this.root.getFirstChild("en:type"), (String)null);
    }
    
    public void setGroup(final String value) {
        this.root.getFirstChild("en:group").setValue(value);
    }
    
    public String getGroup() {
        return Xlate.get(this.root.getFirstChild("en:group"), (String)null);
    }
    
    @Override
    public Type getType() {
        return Type.manualKey;
    }
}
