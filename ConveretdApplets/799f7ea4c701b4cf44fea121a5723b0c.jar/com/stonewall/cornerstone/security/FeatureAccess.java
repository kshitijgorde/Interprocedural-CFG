// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.StringTokenizer;
import java.util.Iterator;
import org.xmodel.Element;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import java.util.List;

public class FeatureAccess
{
    private List<String> children;
    private String target;
    private String actionValue;
    private Type type;
    private String variableName;
    private String attributeName;
    private static String branchExpr;
    private static String treeExpr;
    
    static {
        FeatureAccess.branchExpr = "($site/nested-or-self::en:site/@id|$site/ancestor::en:site/@id)";
        FeatureAccess.treeExpr = "($site/ancestor-or-self::en:site/@id)";
    }
    
    public FeatureAccess(final Type type) {
        this.children = new ArrayList<String>();
        this.type = type;
        this.compile();
    }
    
    public FeatureAccess(final IModelObject root) {
        this.children = new ArrayList<String>();
        this.type = Type.valueOf(Xlate.get(root, (String)null));
    }
    
    public Type getType() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return this.type.getValue();
    }
    
    public String getVariableName() {
        return this.variableName;
    }
    
    public boolean hasVariable() {
        return this.getVariableName() != null;
    }
    
    public IModelObject getAccessAsObject(final String targetId) {
        final Iterator<String> iter = this.children.iterator();
        final IModelObject root = new Element(this.target);
        if (this.hasVariable()) {
            root.setAttribute(this.attributeName, targetId);
        }
        IModelObject parent = root;
        while (iter.hasNext()) {
            final IModelObject child = new Element(iter.next());
            parent.addChild(child);
            parent = child;
        }
        final IModelObject actionE = new Element("en:action");
        actionE.setValue(this.actionValue);
        parent.addChild(actionE);
        return root;
    }
    
    public String getAccessAsString(final String targetId) {
        return this.print(this.getAccessAsObject(targetId));
    }
    
    public IModelObject getActionAsObject() {
        final Iterator<String> iter = this.children.iterator();
        IModelObject parent = null;
        while (iter.hasNext()) {
            final IModelObject child = new Element(iter.next());
            if (parent != null) {
                parent.addChild(child);
            }
            parent = child;
        }
        final IModelObject actionE = new Element("en:action");
        actionE.setValue(this.actionValue);
        if (parent != null) {
            parent.addChild(actionE);
        }
        else {
            parent = actionE;
        }
        return parent;
    }
    
    public String getActionAsString() {
        return this.print(this.getActionAsObject());
    }
    
    public String getActionPath() {
        final int index = this.getType().getValue().indexOf(47);
        final String s = this.type.getValue();
        return s.substring(index + 1, s.length());
    }
    
    public String getTargetPath() {
        final StringBuffer buf = new StringBuffer();
        buf.append(this.target);
        if (this.attributeName != null) {
            buf.append("[@");
            buf.append(this.attributeName);
            buf.append("=$");
            buf.append(this.variableName);
            buf.append("]");
        }
        return buf.toString();
    }
    
    public String getMutexPath() {
        String path = this.type.getValue();
        path = path.replace("$" + this.getVariableName(), FeatureAccess.branchExpr);
        return path;
    }
    
    public String getPermissionPath() {
        String path = this.type.getValue();
        path = path.replace("$" + this.getVariableName(), FeatureAccess.treeExpr);
        return path;
    }
    
    public String getPath() {
        return this.type.getValue();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        final FeatureAccess fa = (FeatureAccess)o;
        return this.getType().equals(fa.getType());
    }
    
    private void compile() {
        this.parseIModelObjects();
        this.parseAction();
        this.parseAttribute();
        this.parseVariable();
    }
    
    private void parseIModelObjects() {
        final StringTokenizer st = new StringTokenizer(this.getType().getValue(), "/");
        this.target = this.trimToken(st.nextToken());
        while (st.hasMoreTokens()) {
            final String name = this.trimToken(st.nextToken());
            if (!name.equals("en:action")) {
                this.children.add(name);
            }
        }
    }
    
    private String trimToken(String token) {
        final int pred = token.indexOf(91);
        if (pred > 0) {
            token = token.substring(0, token.indexOf(91));
        }
        return token;
    }
    
    private void parseAction() {
        final StringTokenizer st = new StringTokenizer(this.getType().getValue(), "'");
        st.nextToken();
        this.actionValue = st.nextToken();
    }
    
    private void parseAttribute() {
        final int atIndex = this.getType().getValue().indexOf(64);
        if (atIndex >= 0) {
            final int equals = this.getType().getValue().indexOf(61);
            this.attributeName = this.getType().getValue().substring(atIndex + 1, equals).trim();
        }
    }
    
    private void parseVariable() {
        final int dollarIndex = this.getType().getValue().indexOf(36);
        if (dollarIndex >= 0) {
            final int endPredicate = this.getType().getValue().indexOf(93);
            final int space = this.getType().getValue().indexOf(" ");
            final int index = (space < 0 || endPredicate < space) ? endPredicate : space;
            this.variableName = this.getType().getValue().substring(dollarIndex + 1, index).trim();
        }
    }
    
    private String print(final IModelObject e) {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(e, IXmlIO.Style.printable);
    }
    
    public enum Type
    {
        userLogin("userLogin", 0, "en:user/en:action[text()='login']"), 
        userView("userView", 1, "en:user/en:action[text()='view']"), 
        userInsert("userInsert", 2, "en:user/en:action[text()='insert']"), 
        userUpdate("userUpdate", 3, "en:user/en:action[text()='update']"), 
        userDelete("userDelete", 4, "en:user/en:action[text()='delete']"), 
        roleView("roleView", 5, "en:role/en:action[text()='view']"), 
        roleInsert("roleInsert", 6, "en:role/en:action[text()='insert']"), 
        roleUpdate("roleUpdate", 7, "en:role/en:action[text()='update']"), 
        roleDelete("roleDelete", 8, "en:role/en:action[text()='delete']"), 
        labelView("labelView", 9, "en:label/en:action[text()='view']"), 
        labelInsert("labelInsert", 10, "en:label/en:action[text()='insert']"), 
        labelUpdate("labelUpdate", 11, "en:label/en:action[text()='update']"), 
        labelDelete("labelDelete", 12, "en:label/en:action[text()='delete']"), 
        labelRollback("labelRollback", 13, "en:label/en:action[text()='rollback']"), 
        policyServerView("policyServerView", 14, "en:policyServer/en:action[text()='view']"), 
        policyServerInsert("policyServerInsert", 15, "en:policyServer/en:action[text()='insert']"), 
        policyServerUpdate("policyServerUpdate", 16, "en:policyServer/en:action[text()='update']"), 
        policyServerDelete("policyServerDelete", 17, "en:policyServer/en:action[text()='delete']"), 
        policyServerRemoteServerView("policyServerRemoteServerView", 18, "en:policyServer/en:remoteServer/en:action[text()='view']"), 
        policyServerRemoteServerInsert("policyServerRemoteServerInsert", 19, "en:policyServer/en:remoteServer/en:action[text()='insert']"), 
        policyServerRemoteServerUpdate("policyServerRemoteServerUpdate", 20, "en:policyServer/en:remoteServer/en:action[text()='update']"), 
        policyServerRemoteServerDelete("policyServerRemoteServerDelete", 21, "en:policyServer/en:remoteServer/en:action[text()='delete']"), 
        policyServerDeviceManagerAddDevice("policyServerDeviceManagerAddDevice", 22, "en:policyServer/en:deviceManager/en:action[text()='addDevice']"), 
        siteView("siteView", 23, "en:site[@siteId=$site]/en:action[text()='view']"), 
        siteInsert("siteInsert", 24, "en:site[@siteId=$site]/en:action[text()='insert']"), 
        siteUpdate("siteUpdate", 25, "en:site[@siteId=$site]/en:action[text()='update']"), 
        siteDelete("siteDelete", 26, "en:site[@siteId=$site]/en:action[text()='delete']"), 
        sitePolicyUpdate("sitePolicyUpdate", 27, "en:site[@siteId=$site]/en:policy/en:action[text()='update']"), 
        sitePolicyInsert("sitePolicyInsert", 28, "en:site[@siteId=$site]/en:policy/en:action[text()='insert']"), 
        sitePolicyDeploy("sitePolicyDeploy", 29, "en:site[@siteId=$site]/en:policy/en:action[text()='deploy']"), 
        sitePolicyPreview("sitePolicyPreview", 30, "en:site[@siteId=$site]/en:policy/en:action[text()='preview']"), 
        siteRemoveDevice("siteRemoveDevice", 31, "en:site[@siteId=$site]/en:action[text()='removeDevice']"), 
        siteDeviceView("siteDeviceView", 32, "en:site[@siteId=$site]/en:device/en:action[text()='view']"), 
        siteDeviceInsert("siteDeviceInsert", 33, "en:site[@siteId=$site]/en:device/en:action[text()='insert']"), 
        siteDeviceUpdate("siteDeviceUpdate", 34, "en:site[@siteId=$site]/en:device/en:action[text()='update']"), 
        siteDeviceDelete("siteDeviceDelete", 35, "en:site[@siteId=$site]/en:device/en:action[text()='delete']"), 
        siteDeviceDiscover("siteDeviceDiscover", 36, "en:site[@siteId=$site]/en:device/en:action[text()='discover']"), 
        siteDeviceDeploy("siteDeviceDeploy", 37, "en:site[@siteId=$site]/en:device/en:action[text()='deploy']"), 
        siteDeviceAudit("siteDeviceAudit", 38, "en:site[@siteId=$site]/en:device/en:action[text()='audit']"), 
        siteDeviceBaseline("siteDeviceBaseline", 39, "en:site[@siteId=$site]/en:device/en:action[text()='baseline']"), 
        siteDeviceInfer("siteDeviceInfer", 40, "en:site[@siteId=$site]/en:device/en:action[text()='infer']"), 
        siteDevicePolicyView("siteDevicePolicyView", 41, "en:site[@siteId=$site]/en:devicePolicy/en:action[text()='view']"), 
        siteDevicePolicyUpdate("siteDevicePolicyUpdate", 42, "en:site[@siteId=$site]/en:devicePolicy/en:action[text()='update']"), 
        siteNatPolicyView("siteNatPolicyView", 43, "en:site[@siteId=$site]/en:natPolicy/en:action[text()='view']"), 
        siteNatPolicyUpdate("siteNatPolicyUpdate", 44, "en:site[@siteId=$site]/en:natPolicy/en:action[text()='update']"), 
        siteDeviceConfigView("siteDeviceConfigView", 45, "en:site[@siteId=$site]/en:deviceConfig/en:action[text()='view']"), 
        siteTopologyView("siteTopologyView", 46, "en:site[@siteId=$site]/en:topology/en:action[text()='view']"), 
        siteTopologyUpdate("siteTopologyUpdate", 47, "en:site[@siteId=$site]/en:topology/en:action[text()='update']"), 
        networkView("networkView", 48, "en:network/en:action[text()='view']"), 
        networkInsert("networkInsert", 49, "en:network/en:action[text()='insert']"), 
        networkUpdate("networkUpdate", 50, "en:network/en:action[text()='update']"), 
        networkDelete("networkDelete", 51, "en:network/en:action[text()='delete']"), 
        networkReparent("networkReparent", 52, "en:network/en:action[text()='reparent']"), 
        addressGroupView("addressGroupView", 53, "en:addressGroup/en:action[text()='view']"), 
        addressGroupInsert("addressGroupInsert", 54, "en:addressGroup/en:action[text()='insert']"), 
        addressGroupUpdate("addressGroupUpdate", 55, "en:addressGroup/en:action[text()='update']"), 
        addressGroupDelete("addressGroupDelete", 56, "en:addressGroup/en:action[text()='delete']"), 
        addressGroupImport("addressGroupImport", 57, "en:addressGroup/en:action[text()='import']"), 
        addressRangeView("addressRangeView", 58, "en:addressRange/en:action[text()='view']"), 
        addressRangeInsert("addressRangeInsert", 59, "en:addressRange/en:action[text()='insert']"), 
        addressRangeUpdate("addressRangeUpdate", 60, "en:addressRange/en:action[text()='update']"), 
        addressRangeDelete("addressRangeDelete", 61, "en:addressRange/en:action[text()='delete']"), 
        ipServiceView("ipServiceView", 62, "en:ipService/en:action[text()='view']"), 
        ipServiceInsert("ipServiceInsert", 63, "en:ipService/en:action[text()='insert']"), 
        ipServiceUpdate("ipServiceUpdate", 64, "en:ipService/en:action[text()='update']"), 
        ipServiceDelete("ipServiceDelete", 65, "en:ipService/en:action[text()='delete']"), 
        ipServiceImport("ipServiceImport", 66, "en:ipService/en:action[text()='import']"), 
        ipServiceGroupView("ipServiceGroupView", 67, "en:ipServiceGroup/en:action[text()='view']"), 
        ipServiceGroupInsert("ipServiceGroupInsert", 68, "en:ipServiceGroup/en:action[text()='insert']"), 
        ipServiceGroupUpdate("ipServiceGroupUpdate", 69, "en:ipServiceGroup/en:action[text()='update']"), 
        ipServiceGroupDelete("ipServiceGroupDelete", 70, "en:ipServiceGroup/en:action[text()='delete']"), 
        hostInsert("hostInsert", 71, "en:host/en:action[text()='insert']"), 
        hostUpdate("hostUpdate", 72, "en:host/en:action[text()='update']"), 
        hostDelete("hostDelete", 73, "en:host/en:action[text()='delete']"), 
        hostReparent("hostReparent", 74, "en:host/en:action[text()='reparent']"), 
        p1ProposalInsert("p1ProposalInsert", 75, "en:p1Proposal/en:action[text()='insert']"), 
        p1ProposalUpdate("p1ProposalUpdate", 76, "en:p1Proposal/en:action[text()='update']"), 
        p1ProposalDelete("p1ProposalDelete", 77, "en:p1Proposal/en:action[text()='delete']"), 
        p2ProposalInsert("p2ProposalInsert", 78, "en:p2Proposal/en:action[text()='insert']"), 
        p2ProposalUpdate("p2ProposalUpdate", 79, "en:p2Proposal/en:action[text()='update']"), 
        p2ProposalDelete("p2ProposalDelete", 80, "en:p2Proposal/en:action[text()='delete']"), 
        tagInsert("tagInsert", 81, "en:tag/en:action[text()='insert']"), 
        tagUpdate("tagUpdate", 82, "en:tag/en:action[text()='update']"), 
        tagDelete("tagDelete", 83, "en:tag/en:action[text()='delete']"), 
        complianceRun("complianceRun", 84, "en:compliancePolicy/en:action[text()='run']"), 
        compliancePolicyView("compliancePolicyView", 85, "en:compliancePolicy/en:action[text()='view']"), 
        compliancePolicyInsert("compliancePolicyInsert", 86, "en:compliancePolicy/en:action[text()='insert']"), 
        compliancePolicyUpdate("compliancePolicyUpdate", 87, "en:compliancePolicy/en:action[text()='update']"), 
        compliancePolicyDelete("compliancePolicyDelete", 88, "en:compliancePolicy/en:action[text()='delete']"), 
        notificationView("notificationView", 89, "en:notification/en:action[text()='view']"), 
        notificationInsert("notificationInsert", 90, "en:notification/en:action[text()='insert']"), 
        notificationUpdate("notificationUpdate", 91, "en:notification/en:action[text()='update']"), 
        notificationDelete("notificationDelete", 92, "en:notification/en:action[text()='delete']");
        
        private String value;
        
        private Type(final String s, final int n, final String value) {
            this.value = value;
        }
        
        public String getValue() {
            return this.value;
        }
        
        public FeatureAccess getFeatureAccess() {
            return new FeatureAccess(this);
        }
    }
}
