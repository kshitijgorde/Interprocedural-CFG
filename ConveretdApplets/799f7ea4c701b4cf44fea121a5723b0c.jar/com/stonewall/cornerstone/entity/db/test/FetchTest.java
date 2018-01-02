// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db.test;

import com.stonewall.cornerstone.entity.db.DeviceManagerDbAccess;
import com.stonewall.cornerstone.entity.DeviceManager;
import com.stonewall.cornerstone.entity.db.CompliancePolicyDbAccess;
import com.stonewall.cornerstone.entity.policy.compliance.CompliancePolicy;
import com.stonewall.cornerstone.entity.db.OpsetDbAccess;
import com.stonewall.cornerstone.entity.DeviceOperation;
import com.stonewall.cornerstone.entity.Opset;
import com.stonewall.cornerstone.entity.db.NatDbAccess;
import com.stonewall.cornerstone.entity.policy.nat.NatPolicy;
import com.stonewall.cornerstone.entity.policy.security.SecurityPolicy;
import com.stonewall.cornerstone.entity.db.SecurityPolicyDbAccess;
import com.stonewall.cornerstone.entity.policy.security.DevicePolicy;
import com.stonewall.cornerstone.entity.policy.DeployablePolicy;
import com.stonewall.cornerstone.entity.db.DeviceConfigDbAccess;
import com.stonewall.cornerstone.entity.DeviceConfig;
import java.util.Collection;
import com.stonewall.cornerstone.entity.db.DeviceDbAccess;
import com.stonewall.cornerstone.entity.Protocol;
import com.stonewall.cornerstone.entity.DiscoverInfo;
import java.util.Collections;
import com.stonewall.cornerstone.entity.db.TagDbAccess;
import com.stonewall.cornerstone.entity.db.AlarmDbAccess;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.entity.EntityFactory;
import com.stonewall.cornerstone.entity.Alarm;
import com.stonewall.cornerstone.entity.db.SegmentDbAccess;
import com.stonewall.cornerstone.entity.Entity;
import com.stonewall.cornerstone.entity.db.AddressGroupDbAccess;
import com.stonewall.cornerstone.entity.Label;
import com.stonewall.cornerstone.entity.AddressGroup;
import com.stonewall.cornerstone.entity.db.HostDbAccess;
import java.util.List;
import com.stonewall.cornerstone.entity.db.NetworkDbAccess;
import com.stonewall.cornerstone.entity.util.IpAddr;
import com.stonewall.cornerstone.component.Bootstrap;
import com.stonewall.cornerstone.entity.Tag;
import com.stonewall.cornerstone.entity.Segment;
import com.stonewall.cornerstone.entity.Host;
import com.stonewall.cornerstone.entity.Network;
import com.stonewall.cornerstone.entity.Device;
import junit.framework.TestCase;

public class FetchTest extends TestCase
{
    private Device device;
    private Network network;
    private Host host;
    private Segment segment;
    private Tag tag;
    
    public void testAll() {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            this.network();
            this.host();
            this.segment();
            this.addressGroup();
            this.alarm();
            this.tag();
            this.device();
            this.devicePolicy();
            this.deviceConfig();
            this.natPolicy();
            this.opset();
            this.compliancePolicy();
            this.deviceManager();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void network() throws Exception {
        (this.network = new Network()).setIpAddress(new IpAddr("172.1.100.0/24"));
        this.network.insert();
        final NetworkDbAccess access = new NetworkDbAccess();
        Network n = access.fetchById(this.network.getId());
        System.out.println(n);
        n = access.fetchById(this.network.getId(), true);
        System.out.println(n);
        List<Network> ns = access.fetchAll();
        System.out.println(ns);
        ns = access.fetchAll(true);
        System.out.println(ns);
    }
    
    private void host() throws Exception {
        (this.host = new Host()).setIpAddress(new IpAddr("172.1.100.0/24"));
        this.host.setParentId(this.network.getId());
        this.host.insert();
        final HostDbAccess access = new HostDbAccess();
        Host h = access.fetchById(this.host.getId());
        System.out.println(h);
        h = access.fetchById(this.host.getId(), true);
        System.out.println(h);
        List<Host> hs = access.fetchAll();
        System.out.println(hs);
        hs = access.fetchAll(true);
        System.out.println(hs);
    }
    
    private void addressGroup() throws Exception {
        AddressGroup group = new AddressGroup();
        group.setName("group");
        group.addPart(this.network.getReference());
        final AddressGroupDbAccess access = new AddressGroupDbAccess(Label.latest);
        access.insert(group);
        group = access.fetchById(group.getId());
        System.out.println(group);
        group = access.fetchById(group.getId(), true);
        System.out.println(group);
        List<AddressGroup> groups = access.fetchAll();
        System.out.println(groups);
        groups = access.fetchAll(true);
        System.out.println(groups);
    }
    
    private void segment() throws Exception {
        (this.segment = new Segment()).setEndpoints(this.network, this.host);
        this.segment.insert();
        Segment s = new SegmentDbAccess().fetchById(this.segment.getId());
        System.out.println(s);
        s = new SegmentDbAccess().fetchById(this.segment.getId(), true);
        System.out.println(s);
        List<Segment> ss = new SegmentDbAccess().fetchAll();
        System.out.println(ss);
        ss = new SegmentDbAccess().fetchAll(true);
        System.out.println(ss);
    }
    
    private void alarm() throws Exception {
        Alarm a = new Alarm();
        a.open(new EntityReference(EntityFactory.EntityType.device, "12345"), Alarm.Severity.major, Alarm.Type.deployFailed, "Alarmed");
        final AlarmDbAccess access = new AlarmDbAccess();
        access.insert(a);
        a = access.fetchById(a.getId());
        System.out.println(a);
        a = access.fetchById(a.getId(), true);
        System.out.println(a);
        List<Alarm> as = access.fetchAll();
        System.out.println(as);
        as = access.fetchAll(true);
        System.out.println(as);
    }
    
    private void tag() throws Exception {
        (this.tag = new Tag()).setName("Device");
        this.tag.setCategory(Tag.Category.deviceType);
        final TagDbAccess access = new TagDbAccess();
        try {
            this.tag.insert();
        }
        catch (Exception e) {
            this.tag = access.fetchByNames(Collections.singletonList("Device")).get(0);
        }
        Tag t = access.fetchById(this.tag.getId());
        System.out.println(t);
        t = access.fetchById(this.tag.getId(), true);
        System.out.println(t);
        List<Tag> ns = access.fetchAll();
        System.out.println(ns);
        ns = access.fetchAll(true);
        System.out.println(ns);
    }
    
    private void device() throws Exception {
        (this.device = new Device()).setDeviceManagerId("1234");
        this.device.setHardware("ns5gt");
        this.device.setVendor("Juniper");
        final DiscoverInfo info = new DiscoverInfo();
        info.setHostname("host");
        info.setSerialNumber("BR549");
        info.setModel("modelT");
        this.device.setDiscoverInfo(info);
        this.device.setHighestAlarmSeverity(Alarm.Severity.major);
        this.device.setIpAddress(new IpAddr("172.1.100.1"));
        this.device.setName("my device");
        final Protocol protocol = new Protocol(Protocol.Type.telnet);
        protocol.setAdminPwd("admin");
        protocol.setPassword("pwd");
        protocol.setPort(5);
        this.device.setProtocol(protocol);
        this.device.setSiteId("SITE0");
        this.device.setSoftware("v5.0.1");
        this.device.setTags(Collections.singletonList(this.tag));
        this.device.insert();
        final DeviceDbAccess access = new DeviceDbAccess();
        Device d = access.fetchById(this.device.getId());
        System.out.println(d);
        d = access.fetchById(this.device.getId(), true);
        System.out.println(d);
        Collection<Device> ds = access.fetchAll();
        System.out.println(ds);
        ds = access.fetchAll(true);
        System.out.println(ds);
    }
    
    private void deviceConfig() throws Exception {
        final DeviceConfig config = new DeviceConfig();
        config.setParentId(this.device.getId());
        config.insert();
        DeviceConfig c = new DeviceConfigDbAccess().fetchByParent(this.device.getId());
        System.out.println(c);
        c = new DeviceConfigDbAccess().fetchByParent(this.device.getId(), true);
        System.out.println(c);
        List<DeviceConfig> configs = new DeviceConfigDbAccess().fetchAll();
        System.out.println(configs);
        configs = new DeviceConfigDbAccess().fetchAll(true);
        System.out.println(configs);
    }
    
    private void devicePolicy() throws Exception {
        final DevicePolicy policy = new DevicePolicy(DeployablePolicy.Phase.baseline);
        policy.setParentId(this.device.getId());
        policy.insert();
        DevicePolicy p = new SecurityPolicyDbAccess().fetchBaselinePolicy(this.device.getId());
        System.out.println(p);
        p = new SecurityPolicyDbAccess().fetchBaselineReference(this.device.getId());
        System.out.println(p);
        List<SecurityPolicy> policies = new SecurityPolicyDbAccess().fetchAll();
        System.out.println(policies);
        policies = new SecurityPolicyDbAccess().fetchAll(true);
        System.out.println(policies);
    }
    
    private void natPolicy() throws Exception {
        final NatPolicy policy = new NatPolicy(DeployablePolicy.Phase.working);
        policy.setParentId(this.device.getId());
        policy.insert();
        NatPolicy p = new NatDbAccess().fetchWorkingPolicy(this.device.getId());
        System.out.println(p);
        p = new NatDbAccess().fetchWorkingReference(this.device.getId());
        System.out.println(p);
        List<NatPolicy> policies = new NatDbAccess().fetchAll();
        System.out.println(policies);
        policies = new NatDbAccess().fetchAll(true);
        System.out.println(policies);
    }
    
    private void opset() throws Exception {
        final Opset opset = new Opset(Opset.Type.audit);
        opset.setParentId(this.device.getId());
        opset.addOperation(new DeviceOperation());
        opset.addOperation(new DeviceOperation());
        opset.insert();
        Opset o = new OpsetDbAccess().fetchAudit(this.device.getId(), true);
        System.out.println(o);
        o = new OpsetDbAccess().fetchAudit(this.device.getId(), false);
        System.out.println(o);
        o = new OpsetDbAccess().fetchById(this.device.getId());
        System.out.println(o);
        o = new OpsetDbAccess().fetchById(this.device.getId(), true);
        System.out.println(o);
        List<Opset> opsets = new OpsetDbAccess().fetchAll();
        System.out.println(opsets);
        opsets = new OpsetDbAccess().fetchAll(true);
        System.out.println(opsets);
    }
    
    private void compliancePolicy() throws Exception {
        final CompliancePolicy policy = new CompliancePolicy();
        policy.insert();
        CompliancePolicy p = new CompliancePolicyDbAccess().fetchById(policy.getId());
        System.out.println(p);
        p = new CompliancePolicyDbAccess().fetchById(policy.getId(), true);
        System.out.println(p);
        List<CompliancePolicy> policies = new CompliancePolicyDbAccess().fetchAll();
        System.out.println(policies);
        policies = new CompliancePolicyDbAccess().fetchAll(true);
        System.out.println(policies);
    }
    
    private void deviceManager() throws Exception {
        final DeviceManager manager = new DeviceManager();
        manager.setSerialNumber("5678");
        manager.setParentId("ER231GM70");
        manager.setName("Manager");
        manager.setQueue("DM5");
        manager.insert();
        DeviceManager dm = new DeviceManagerDbAccess().fetchById(manager.getId());
        System.out.println(dm);
        dm = new DeviceManagerDbAccess().fetchById(manager.getId(), true);
        System.out.println(dm);
        List<DeviceManager> configs = new DeviceManagerDbAccess().fetchAll();
        System.out.println(configs);
        configs = new DeviceManagerDbAccess().fetchAll(true);
        System.out.println(configs);
    }
}
