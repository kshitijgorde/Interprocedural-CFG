// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import java.util.ArrayList;
import java.util.Iterator;
import com.stonewall.cornerstone.jms.msg.event.DbEvent;
import java.util.List;
import com.stonewall.cornerstone.entity.db.TagDbAccess;
import com.stonewall.cornerstone.entity.util.IpAddr;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.DeviceDbAccess;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class Device extends Entity implements AlarmableEntity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.device.getQualifiedName();
    }
    
    public Device() {
        super(Device.tag);
        this.init();
    }
    
    public Device(final String id) {
        super(Device.tag, id);
    }
    
    public Device(final IModelObject d) {
        super(d);
    }
    
    private void init() {
        this.setSiteId("SITE0");
        final Mode mode = new Mode(Mode.Policy.site, Mode.Communication.online, Mode.Management.managed);
        this.root.addChild(mode.root);
        final State state = new State();
        this.root.addChild(state.root);
        this.setHighestAlarmSeverity(Alarm.Severity.none);
        this.root.addChild(new Element("en:interfaces"));
        final IModelObject online = new Element("en:online");
        this.root.addChild(online);
        online.addChild(new Element("en:protocol"));
        online.addChild(new Element("en:address"));
    }
    
    @Override
    public Entity fetch(final Label label) throws DbException {
        return new DeviceDbAccess().fetchById(this.getId());
    }
    
    @Override
    public void insert(final Label label) throws DbException {
        new DeviceDbAccess().insert(this);
    }
    
    @Override
    public void delete(final Label label) throws DbException {
        new DeviceDbAccess().delete(this);
    }
    
    @Override
    public String getName() {
        return Xlate.get(this.root.getFirstChild("en:name"), (String)null);
    }
    
    public void setName(final String name) {
        this.setChild(this.root, "en:name", name);
    }
    
    public void setSiteId(final String siteId) {
        this.setChild(this.root, "en:site", siteId);
    }
    
    public String getSiteId() {
        return Xlate.get(this.root.getFirstChild("en:site"), (String)null);
    }
    
    public Alarm.Severity getHighestAlarmSeverity() {
        return Alarm.Severity.valueOf(Xlate.get(this.root.getFirstChild("en:highestAlarmSeverity"), (String)null));
    }
    
    public void setHighestAlarmSeverity(final Alarm.Severity severity) {
        this.setChild(this.root, "en:highestAlarmSeverity", severity.name());
    }
    
    public String getSoftware() {
        return Xlate.get(this.root.getFirstChild("en:software"), (String)null);
    }
    
    public void setSoftware(final String value) {
        this.setChild(this.root, "en:software", value);
    }
    
    public String getHardware() {
        return Xlate.get(this.root.getFirstChild("en:hardware"), (String)null);
    }
    
    public void setHardware(final String value) {
        this.setChild(this.root, "en:hardware", value);
    }
    
    public String getVendor() {
        return Xlate.get(this.root.getFirstChild("en:vendor"), (String)null);
    }
    
    public void setVendor(final String value) {
        this.setChild(this.root, "en:vendor", value);
    }
    
    public void setIpAddress(final IpAddr address) {
        if (address == null) {
            return;
        }
        this.root.getCreateChild("en:online").getCreateChild("en:address").setValue(address.toString());
    }
    
    public IpAddr getIpAddress() {
        try {
            final IModelObject online = this.root.getFirstChild("en:online");
            if (online == null) {
                return null;
            }
            final String ip = Xlate.get(online.getFirstChild("en:address"), (String)null);
            if (ip != null) {
                return new IpAddr(ip);
            }
        }
        catch (Exception e) {
            Device.log.error(e);
        }
        return null;
    }
    
    public Protocol getProtocol() {
        final IModelObject online = this.root.getFirstChild("en:online");
        if (online == null) {
            return null;
        }
        final IModelObject protocol = online.getFirstChild("en:protocol");
        if (protocol != null && !protocol.getChildren().isEmpty()) {
            final IModelObject e = protocol.getChild(0);
            return new Protocol(e);
        }
        return null;
    }
    
    public void setProtocol(final Protocol value) {
        final IModelObject pe = this.root.getCreateChild("en:online").getCreateChild("en:protocol");
        pe.removeChildren();
        final IModelObject pRoot = value.getRoot();
        if (pRoot != null) {
            pe.addChild(pRoot);
        }
    }
    
    public String getDeviceManagerId() {
        final IModelObject online = this.root.getFirstChild("en:online");
        if (online == null) {
            return "";
        }
        return Xlate.get(online.getFirstChild("en:deviceManagerId"), "");
    }
    
    public void setDeviceManagerId(final String id) {
        if (id == null || id.equals("")) {
            return;
        }
        final IModelObject online = this.root.getCreateChild("en:online");
        online.getCreateChild("en:deviceManagerId").setValue(id);
    }
    
    public boolean isManaged() {
        final String id = this.getDeviceManagerId();
        return !id.equals("");
    }
    
    public DiscoverInfo getDiscoverInfo() {
        DiscoverInfo info = null;
        final IModelObject di = this.root.getFirstChild("en:discoverInfo");
        if (di != null) {
            info = new DiscoverInfo(di);
        }
        return info;
    }
    
    public void setDiscoverInfo(final DiscoverInfo info) throws Exception {
        this.setHardware(info.getHardware());
        this.setSoftware(info.getSoftware());
        this.setVendor(info.getVendor());
        final List<String> types = info.getTypes();
        final List<Tag> tags = new TagDbAccess().fetchByNames(types);
        this.setTags(tags);
        info.trim();
        this.root.addChild(info.getRoot());
    }
    
    public void updateProvisionedInfo(final Device d) {
        final Mode mode = this.getMode();
        mode.setPolicy(d.getMode().getPolicy());
        this.setName(d.getName());
        this.setIpAddress(d.getIpAddress());
        this.setProtocol(d.getProtocol());
    }
    
    @Override
    public void updateAlarmSeverity(final Alarm.Severity severity) throws DbException {
        this.setChild(this.root, "en:highestAlarmSeverity", severity.name());
        new DeviceDbAccess().updateAlarmSeverity(this.getId(), severity);
        DbEvent.sendUpdate(this.getReference());
    }
    
    public void setDeviceContent(final List<IModelObject> content) {
        for (final IModelObject o : content) {
            this.root.addChild(o);
        }
    }
    
    public IPInterface getManagementInterface() {
        final IpAddr addr = this.getIpAddress();
        if (addr == null) {
            return null;
        }
        for (final IPInterface iface : this.getInterfaces()) {
            if (iface.getIpAddress() != null && iface.getIpAddress().equalsIgnoreMask(addr)) {
                return iface;
            }
        }
        return null;
    }
    
    public List<IPInterface> getInterfaces() {
        final List<IPInterface> l = new ArrayList<IPInterface>();
        final List<IModelObject> interfaces = this.root.getFirstChild("en:interfaces").getChildren();
        if (interfaces == null) {
            return l;
        }
        for (final IModelObject e : interfaces) {
            l.add(new IPInterface(e));
        }
        return l;
    }
    
    public List<IPInterface> getTrafficInterfaces() {
        final List<IPInterface> l = new ArrayList<IPInterface>();
        final List<IModelObject> interfaces = this.root.getFirstChild("en:interfaces").getChildren();
        if (interfaces == null) {
            return l;
        }
        for (final IModelObject e : interfaces) {
            final IPInterface iface = new IPInterface(e);
            if (iface.isTraffic()) {
                l.add(iface);
            }
        }
        return l;
    }
    
    public IPInterface getInterface(final String id) {
        final String path = "./en:ipInterface[@id = $id]";
        final IExpression xpath = XPath.createExpression(path);
        xpath.setVariable("id", id);
        final IModelObject iface = xpath.queryFirst(this.getInterfacesElement());
        if (iface != null) {
            return new IPInterface(iface);
        }
        return null;
    }
    
    public IPInterface getInterfaceWithIdentifier(final IModelObject interfaces, final String identifier) {
        final String path = "./en:ipInterface[en:identifier = $identifier]";
        final IExpression xpath = XPath.createExpression(path);
        xpath.setVariable("identifier", identifier);
        final IModelObject iface = xpath.queryFirst(interfaces);
        if (iface != null) {
            return new IPInterface(iface);
        }
        return null;
    }
    
    public IModelObject getInterfacesElement() {
        return this.root.getCreateChild("en:interfaces");
    }
    
    public void addInterface(final IPInterface iface) {
        iface.setParentId(this.getId());
        this.getInterfacesElement().addChild(iface.getRoot());
    }
    
    public void deleteInterface(final IPInterface iface) {
        this.getInterfacesElement().removeChild(iface.root);
    }
    
    public void removeInterfaces() {
        this.getInterfacesElement().removeChildren("en:ipInterface");
    }
    
    public Mode getMode() {
        return new Mode(this.root.getFirstChild("en:mode"));
    }
    
    public State getState() {
        return new State(this.root.getFirstChild("en:state"));
    }
    
    public Device clone() {
        final IModelObject e = this.getRoot().cloneTree();
        return new Device(e);
    }
    
    public static class State
    {
        private IModelObject root;
        
        State() {
            this.root = new Element("en:state");
            this.root.getCreateChild("en:operational").setValue(Operational.created.name());
            this.root.getCreateChild("en:admin").setValue(Admin.enabled.name());
        }
        
        State(final IModelObject root) {
            this.root = root;
        }
        
        public Admin getAdmin() {
            return Admin.valueOf(Xlate.get(this.root.getFirstChild("en:admin"), (String)null));
        }
        
        public void setAdmin(final Admin state) {
            this.root.getFirstChild("en:admin").setValue(state.name());
        }
        
        public void setOperational(final Operational state) {
            this.root.getFirstChild("en:operational").setValue(state.name());
        }
        
        public Operational getOperational() {
            return Operational.valueOf(Xlate.get(this.root.getFirstChild("en:operational"), (String)null));
        }
        
        public boolean isDeployed() {
            return this.getOperational().equals(Operational.deployed);
        }
        
        public boolean isDiscovered() {
            return this.getOperational().equals(Operational.discovered);
        }
        
        public boolean isCreated() {
            return this.getOperational().equals(Operational.created);
        }
        
        public boolean isEnabled() {
            return this.getAdmin().equals(Admin.enabled);
        }
        
        public enum Admin
        {
            enabled("enabled", 0), 
            disabled("disabled", 1);
            
            private Admin(final String s, final int n) {
            }
        }
        
        public enum Operational
        {
            discovered("discovered", 0), 
            deployed("deployed", 1), 
            created("created", 2);
            
            private Operational(final String s, final int n) {
            }
        }
    }
    
    public static class Mode
    {
        private IModelObject root;
        
        Mode() {
            (this.root = new Element("en:mode")).addChild(new Element("en:policy"));
            this.root.addChild(new Element("en:mgmt"));
            this.root.addChild(new Element("en:comm"));
        }
        
        public Mode(final IModelObject e) {
            this.root = e;
        }
        
        public Mode(final Policy policy, final Communication communication, final Management management) {
            this();
            this.setPolicy(policy);
            this.setManagement(management);
            this.setCommunication(communication);
        }
        
        public Policy getPolicy() {
            return Policy.valueOf(Xlate.get(this.root.getFirstChild("en:policy"), (String)null));
        }
        
        public void setPolicy(final Policy mode) {
            this.root.getFirstChild("en:policy").setValue(mode.name());
        }
        
        public Management getManagement() {
            return Management.valueOf(Xlate.get(this.root.getFirstChild("en:mgmt"), (String)null));
        }
        
        public void setManagement(final Management mode) {
            this.root.getFirstChild("en:mgmt").setValue(mode.name());
        }
        
        public Communication getCommunication() {
            return Communication.valueOf(Xlate.get(this.root.getFirstChild("en:comm"), (String)null));
        }
        
        public void setCommunication(final Communication mode) {
            this.root.getFirstChild("en:comm").setValue(mode.name());
        }
        
        public boolean isAudit() {
            return this.getPolicy().equals(Policy.audit);
        }
        
        public boolean isSite() {
            return this.getPolicy().equals(Policy.site);
        }
        
        public boolean isDevice() {
            return this.getPolicy().equals(Policy.device);
        }
        
        public boolean isManaged() {
            return this.getManagement().equals(Management.managed);
        }
        
        public boolean isOnline() {
            return this.getCommunication().equals(Communication.online);
        }
        
        @Override
        public boolean equals(final Object obj) {
            if (!(obj instanceof Mode)) {
                return false;
            }
            final Mode mode = (Mode)obj;
            return (this.ignore(this.getPolicy(), mode.getPolicy()) || this.getPolicy().equals(mode.getPolicy())) && (this.ignore(this.getManagement(), mode.getManagement()) || this.getManagement().equals(mode.getManagement())) && (this.ignore(this.getCommunication(), mode.getCommunication()) || this.getCommunication().equals(mode.getCommunication()));
        }
        
        private boolean ignore(final Enum e1, final Enum e2) {
            return e1.name().equals("ignore") || e2.name().equals("ignore");
        }
        
        @Override
        public int hashCode() {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.getPolicy().name());
            sb.append(this.getManagement().name());
            sb.append(this.getCommunication().name());
            return sb.toString().hashCode();
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("\nMode:");
            sb.append(this.getPolicy().name());
            sb.append(":");
            sb.append(this.getManagement().name());
            sb.append(":");
            sb.append(this.getCommunication().name());
            sb.append("\n");
            return sb.toString();
        }
        
        public enum Communication
        {
            online("online", 0), 
            offline("offline", 1), 
            ignore("ignore", 2);
            
            private Communication(final String s, final int n) {
            }
        }
        
        public enum Management
        {
            managed("managed", 0), 
            unmanaged("unmanaged", 1), 
            ignore("ignore", 2);
            
            private Management(final String s, final int n) {
            }
        }
        
        public enum Policy
        {
            device("device", 0), 
            site("site", 1), 
            audit("audit", 2), 
            unmanaged("unmanaged", 3), 
            ignore("ignore", 4);
            
            private Policy(final String s, final int n) {
            }
        }
    }
}
