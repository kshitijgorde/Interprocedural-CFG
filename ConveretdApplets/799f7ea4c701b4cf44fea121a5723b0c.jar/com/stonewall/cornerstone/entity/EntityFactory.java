// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.utility.Namespaces;
import com.stonewall.cornerstone.entity.policy.security.IPHeader;
import com.stonewall.cornerstone.entity.db.SecurityTunnelDbAccess;
import com.stonewall.cornerstone.entity.policy.security.SecurityTunnel;
import com.stonewall.cornerstone.entity.db.NotificationDbAccess;
import com.stonewall.cornerstone.entity.db.TagDbAccess;
import com.stonewall.cornerstone.entity.policy.compliance.ComplianceRule;
import com.stonewall.cornerstone.entity.db.CompliancePolicyDbAccess;
import com.stonewall.cornerstone.entity.policy.compliance.CompliancePolicy;
import com.stonewall.cornerstone.entity.db.HostDbAccess;
import com.stonewall.cornerstone.entity.db.PolicyIssueDbAccess;
import com.stonewall.cornerstone.entity.policy.PolicyIssue;
import com.stonewall.cornerstone.entity.db.ComplianceReportDbAccess;
import com.stonewall.cornerstone.entity.db.SegmentDbAccess;
import com.stonewall.cornerstone.entity.db.NetworkDbAccess;
import com.stonewall.cornerstone.entity.db.JobDbAccess;
import com.stonewall.cornerstone.entity.db.IPServiceDbAccess;
import com.stonewall.cornerstone.entity.db.AddressRangeDbAccess;
import com.stonewall.cornerstone.entity.db.AddressGroupDbAccess;
import com.stonewall.cornerstone.entity.db.SiteDbAccess;
import com.stonewall.cornerstone.entity.db.P2ProposalDbAccess;
import com.stonewall.cornerstone.entity.policy.security.P2Proposal;
import com.stonewall.cornerstone.entity.db.P1ProposalDbAccess;
import com.stonewall.cornerstone.entity.policy.security.P1Proposal;
import com.stonewall.cornerstone.entity.policy.security.SecurityRule;
import com.stonewall.cornerstone.entity.db.SecurityPolicyDbAccess;
import com.stonewall.cornerstone.entity.policy.security.SecurityPolicy;
import com.stonewall.cornerstone.entity.db.OpsetDbAccess;
import com.stonewall.cornerstone.entity.db.IPInterfaceDbAccess;
import com.stonewall.cornerstone.entity.policy.nat.NatRule;
import com.stonewall.cornerstone.entity.db.NatDbAccess;
import com.stonewall.cornerstone.entity.policy.nat.NatPolicy;
import com.stonewall.cornerstone.entity.db.DeviceConfigDbAccess;
import com.stonewall.cornerstone.entity.db.DeviceDbAccess;
import com.stonewall.cornerstone.entity.db.RoleDbAccess;
import com.stonewall.cornerstone.entity.db.UserDbAccess;
import com.stonewall.cornerstone.entity.db.LabelDbAccess;
import com.stonewall.cornerstone.entity.db.ProtocolServerDbAccess;
import com.stonewall.cornerstone.entity.db.DeviceManagerDbAccess;
import com.stonewall.cornerstone.entity.db.PolicyServerDbAccess;
import com.stonewall.cornerstone.entity.db.AlarmDbAccess;
import java.lang.reflect.Constructor;
import org.xmodel.IModelObject;
import org.xmodel.log.Log;

public class EntityFactory
{
    private static EntityFactory instance;
    public static final Log log;
    
    static {
        EntityFactory.instance = null;
        log = Log.getLog(EntityFactory.class);
    }
    
    public static EntityFactory getInstance() {
        if (EntityFactory.instance == null) {
            EntityFactory.instance = new EntityFactory();
        }
        return EntityFactory.instance;
    }
    
    public Entity createEntity(final IModelObject e) {
        try {
            final EntityType type = EntityType.valueOf(e);
            final Constructor con = type.getEntityClass().getConstructor(IModelObject.class);
            return con.newInstance(e);
        }
        catch (Exception ex) {
            EntityFactory.log.error("Cannot create entity:" + e, ex);
            return null;
        }
    }
    
    public Entity createEntity(final EntityType type, final String id) {
        try {
            final Constructor con = type.getEntityClass().getConstructor(String.class);
            if (con == null) {
                throw new RuntimeException("Cannot locate constructor (String) for:" + type.name());
            }
            return con.newInstance(id);
        }
        catch (Exception ex) {
            EntityFactory.log.error("Cannot create entity:" + type.name(), ex);
            return null;
        }
    }
    
    public Entity createEntity(final EntityType type, final Object[] args) {
        try {
            final Class[] classes = new Class[args.length];
            for (int i = 0; i < args.length; ++i) {
                classes[i] = args[i].getClass();
            }
            final Constructor con = type.getEntityClass().getConstructor((Class[])classes);
            if (con == null) {
                throw new RuntimeException("Cannot locate constructor (" + classes + ") for:" + type.name());
            }
            return con.newInstance(args);
        }
        catch (Exception ex) {
            EntityFactory.log.error("Cannot create entity:" + type.name(), ex);
            return null;
        }
    }
    
    public enum EntityType
    {
        unknown("unknown", 0, new EntityDefinition(null, null, null)), 
        alarm("alarm", 1, new EntityDefinition(Alarm.class, AlarmDbAccess.class, "Alarm")), 
        policyServer("policyServer", 2, new EntityDefinition(PolicyServer.class, PolicyServerDbAccess.class, "Policy Server")), 
        deviceManager("deviceManager", 3, new EntityDefinition(DeviceManager.class, DeviceManagerDbAccess.class, "Device Manager")), 
        protocolServer("protocolServer", 4, new EntityDefinition(ProtocolServer.class, ProtocolServerDbAccess.class, "Protocol Server")), 
        label("label", 5, new EntityDefinition(Label.class, LabelDbAccess.class, "Label")), 
        user("user", 6, new EntityDefinition(User.class, UserDbAccess.class, "User")), 
        role("role", 7, new EntityDefinition(Role.class, RoleDbAccess.class, "Role")), 
        device("device", 8, new EntityDefinition(Device.class, DeviceDbAccess.class, "Device")), 
        deviceConfig("deviceConfig", 9, new EntityDefinition(DeviceConfig.class, DeviceConfigDbAccess.class, "Device Configuration")), 
        natPolicy("natPolicy", 10, new EntityDefinition(NatPolicy.class, NatDbAccess.class, "NAT Policy")), 
        natRule("natRule", 11, new EntityDefinition(NatRule.class, NatDbAccess.class, "NAT Rule")), 
        ipInterface("ipInterface", 12, new EntityDefinition(IPInterface.class, IPInterfaceDbAccess.class, "IP Interface")), 
        opset("opset", 13, new EntityDefinition(Opset.class, OpsetDbAccess.class, "Operation Set")), 
        deviceOperation("deviceOperation", 14, new EntityDefinition(DeviceOperation.class, OpsetDbAccess.class, "Device Operation")), 
        securityPolicy("securityPolicy", 15, new EntityDefinition(SecurityPolicy.class, SecurityPolicyDbAccess.class, "Security Policy")), 
        securityRule("securityRule", 16, new EntityDefinition(SecurityRule.class, SecurityPolicyDbAccess.class, "Security Rule")), 
        p1Proposal("p1Proposal", 17, new EntityDefinition(P1Proposal.class, P1ProposalDbAccess.class, "Phase 1 Proposal")), 
        p2Proposal("p2Proposal", 18, new EntityDefinition(P2Proposal.class, P2ProposalDbAccess.class, "Phase 2 Proposal")), 
        site("site", 19, new EntityDefinition(Site.class, SiteDbAccess.class, "Site")), 
        addressGroup("addressGroup", 20, new EntityDefinition(AddressGroup.class, AddressGroupDbAccess.class, "Address Group")), 
        addressRange("addressRange", 21, new EntityDefinition(AddressRange.class, AddressRangeDbAccess.class, "Address Range")), 
        ipService("ipService", 22, new EntityDefinition(IPService.class, IPServiceDbAccess.class, "IP Service")), 
        ipServiceGroup("ipServiceGroup", 23, new EntityDefinition(IPServiceGroup.class, IPServiceDbAccess.class, "IP Service Group")), 
        job("job", 24, new EntityDefinition(Job.class, JobDbAccess.class, "Job")), 
        task("task", 25, new EntityDefinition(Task.class, JobDbAccess.class, "Task")), 
        network("network", 26, new EntityDefinition(Network.class, NetworkDbAccess.class, "Network")), 
        segment("segment", 27, new EntityDefinition(Segment.class, SegmentDbAccess.class, "Segment")), 
        complianceRecord("complianceRecord", 28, new EntityDefinition(ComplianceRecord.class, ComplianceReportDbAccess.class, "Compliance Record")), 
        complianceReport("complianceReport", 29, new EntityDefinition(ComplianceReport.class, ComplianceReportDbAccess.class, "Compliance Report")), 
        policyIssue("policyIssue", 30, new EntityDefinition(PolicyIssue.class, PolicyIssueDbAccess.class, "Policy Issue")), 
        host("host", 31, new EntityDefinition(Host.class, HostDbAccess.class, "Host")), 
        any("any", 32, new EntityDefinition(Any.class, null, "Any")), 
        compliancePolicy("compliancePolicy", 33, new EntityDefinition(CompliancePolicy.class, CompliancePolicyDbAccess.class, "Compliance Policy")), 
        complianceRule("complianceRule", 34, new EntityDefinition(ComplianceRule.class, CompliancePolicyDbAccess.class, "Compliance Rule")), 
        tag("tag", 35, new EntityDefinition(Tag.class, TagDbAccess.class, "Resource Tag")), 
        notification("notification", 36, new EntityDefinition(Notification.class, NotificationDbAccess.class, "Notification")), 
        securityTunnel("securityTunnel", 37, new EntityDefinition(SecurityTunnel.class, SecurityTunnelDbAccess.class, "Security Tunnel")), 
        ipHeader("ipHeader", 38, new EntityDefinition(IPHeader.class, null, null));
        
        private EntityDefinition def;
        
        private EntityType(final String s, final int n, final EntityDefinition def) {
            this.def = def;
        }
        
        public Class getEntityClass() {
            return this.def.getEntityClass();
        }
        
        public String getDisplayName() {
            return this.def.getDisplayName();
        }
        
        public Class getDbAccess() {
            return this.def.getDbAccess();
        }
        
        public String getQualifiedName() {
            return Namespaces.enns.getQualifiedName(this.name());
        }
        
        public static EntityType valueOf(final IModelObject o) {
            final String tag = Namespaces.enns.getUnqualifiedName(o.getType());
            return valueOf(tag);
        }
    }
}
