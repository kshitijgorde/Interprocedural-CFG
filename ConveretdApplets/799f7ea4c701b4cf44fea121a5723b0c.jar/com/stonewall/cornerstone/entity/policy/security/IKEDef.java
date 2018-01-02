// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import org.xmodel.Xlate;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.IModelObject;

public class IKEDef extends TunnelDef
{
    protected static String tag;
    
    static {
        IKEDef.tag = "en:ike";
    }
    
    public IKEDef() {
        super(IKEDef.tag);
    }
    
    public IKEDef(final IModelObject root) {
        super(root);
    }
    
    public IKEDef(final String exchangeMode, final String key, final EntityReference ike, final String type, final int idletime, final EntityReference ipSec, final String saOptions) {
        this();
        this.setExchangeMode(exchangeMode);
        this.setPresharedKey(key);
        this.setPhase2Type(type);
        this.setPhase2Idletime(idletime);
        this.setSAOptions(saOptions);
        this.setP1Proposal(ike);
        this.setP2Proposal(ipSec);
    }
    
    @Override
    protected void init() {
        this.root.addChild(new Element("en:exchangeMode"));
        final IModelObject phase1 = new Element("en:phase1");
        this.root.addChild(phase1);
        phase1.addChild(new Element("en:p1Proposals"));
        final IModelObject phase2 = new Element("en:phase2");
        this.root.addChild(phase2);
        phase2.addChild(new Element("en:p2Proposals"));
    }
    
    public void setExchangeMode(final String value) {
        this.root.getFirstChild("en:exchangeMode").setValue(value);
    }
    
    public String getExchangeMode() {
        return Xlate.get(this.root.getFirstChild("en:exchangeMode"), (String)null);
    }
    
    public void setP1Proposal(final EntityReference value) {
        final IModelObject parent = this.root.getFirstChild("en:phase1").getFirstChild("en:p1Proposals");
        final IExpression xpath = XPath.createExpression("en:p1Proposal[@id=$id]");
        xpath.setVariable("id", value.getId());
        final IModelObject p1 = xpath.queryFirst(parent);
        if (p1 != null) {
            p1.removeFromParent();
        }
        parent.addChild(value.getRoot().cloneTree());
    }
    
    public List<P1Proposal> getP1Proposals() {
        final IModelObject parent = this.root.getFirstChild("en:phase1").getFirstChild("en:p1Proposals");
        final List<P1Proposal> proposals = new ArrayList<P1Proposal>();
        for (final IModelObject e : parent.getChildren("en:p1Proposal")) {
            proposals.add(new P1Proposal(e.cloneTree()));
        }
        return proposals;
    }
    
    public void setPresharedKey(final String value) {
        final IModelObject p1 = this.root.getFirstChild("en:phase1");
        IModelObject keys = p1.getFirstChild("en:presharedKey");
        if (keys == null) {
            keys = new Element("en:presharedKey");
            p1.addChild(keys);
        }
        keys.setValue(value);
    }
    
    public String getPresharedKey() {
        return Xlate.get(this.root.getFirstChild("en:phase1").getFirstChild("en:presharedKey"), (String)null);
    }
    
    public void setP2Proposal(final EntityReference value) {
        final IModelObject parent = this.root.getFirstChild("en:phase2").getFirstChild("en:p2Proposals");
        final IExpression xpath = XPath.createExpression("en:p2Proposal[@id=$id]");
        xpath.setVariable("id", value.getId());
        final IModelObject p2 = xpath.queryFirst(parent);
        if (p2 != null) {
            p2.removeFromParent();
        }
        parent.addChild(value.getRoot().cloneTree());
    }
    
    public List<P2Proposal> getP2Proposals() {
        final IModelObject parent = this.root.getFirstChild("en:phase2").getFirstChild("en:p2Proposals");
        final List<P2Proposal> proposals = new ArrayList<P2Proposal>();
        for (final IModelObject e : parent.getChildren("en:p2Proposal")) {
            proposals.add(new P2Proposal(e.cloneTree()));
        }
        return proposals;
    }
    
    public void setPhase2Type(final String value) {
        final IModelObject phase2 = this.root.getFirstChild("en:phase2");
        IModelObject e = phase2.getFirstChild("en:type");
        if (e == null) {
            e = new Element("en:type");
            phase2.addChild(e);
        }
        e.setValue(value);
    }
    
    public String getPhase2Type() {
        return Xlate.get(this.root.getFirstChild("en:phase2").getFirstChild("en:type"), (String)null);
    }
    
    public void setPhase2Idletime(final int value) {
        final IModelObject phase2 = this.root.getFirstChild("en:phase2");
        IModelObject e = phase2.getFirstChild("en:idletime");
        if (e == null) {
            e = new Element("en:idletime");
            phase2.addChild(e);
        }
        e.setValue(String.valueOf(value));
    }
    
    public int getPhase2Idletime() {
        final IModelObject e = this.root.getFirstChild("en:phase2").getFirstChild("en:idletime");
        return Xlate.get(e, 0);
    }
    
    public void setSAOptions(final String value) {
        if (value == null) {
            return;
        }
        final IModelObject e = this.root.getCreateChild("en:saOptions");
        e.setValue(value);
    }
    
    public String getSAOptions() {
        return Xlate.get(this.root.getFirstChild("en:saOptions"), "");
    }
    
    @Override
    public Type getType() {
        return Type.ike;
    }
}
