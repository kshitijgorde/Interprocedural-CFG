// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.ProtocolServer;
import com.stonewall.cornerstone.entity.Alarm;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;
import com.stonewall.cornerstone.entity.IPInterface;
import java.util.HashMap;
import com.stonewall.cornerstone.entity.Device;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;

public class DeviceDbAccess extends DbAccess
{
    static final String insert = "insert into device\n(id,\nname,\noperational_state,\nadmin_state,\npolicy_mode, \nmgmt_mode, \ncomm_mode, \nsite,\nhighest_alarm_severity,\ncontent, \ndevice_manager) \nvalues\n($id,\n$name,\n$operational_state,\n$admin_state,\n$policy_mode, \n$mgmt_mode, \n$comm_mode, \n$site,\n$highest_alarm_severity,\n$content, \n$device_manager)";
    private static String update;
    static final String delete = "delete from device where id = $id";
    static final String countDevicesForDM = "select count(*) from device where device_manager = $device_manager";
    static final String countDevices = "select count(*) from device\n";
    static final String fetchDeployedByDeviceManager = "select * from device\nwhere device_manager = $device_manager and\noperational_state = 'deployed' and admin_state = 'enabled'";
    static final String fetchAllBySite = "select * from device where site = $site";
    static final String fetchAll = "select * from device order by name";
    static final String fetchAllStubs = "select id from device order by name";
    static final String fetchAllByDeviceManager = "select * from device where device_manager = $device_manager";
    static final String fetchAllIdsBySites = "select id from device where site in $siteIds";
    static final String fetchAllIds = "select id from device";
    static final String fetchForPolicyDeployment = "select * from device\nwhere id in $ids and operational_state = 'deployed' and comm_mode = 'online' and mgmt_mode = 'managed' and policy_mode = 'site'";
    static final String fetchIdsForPolicyDeployment = "select id from device\nwhere id in $ids and operational_state = 'deployed' and comm_mode = 'online' and mgmt_mode = 'managed' and policy_mode = 'site'";
    static final String fetchForPolicyDeploymentBySite = "select id from device \nwhere operational_state = 'deployed' and comm_mode = 'online' and mgmt_mode = 'managed' and site = $site and policy_mode = 'site'";
    static final String fetchById = "select * from device where id = $id";
    static final String fetchByIds = "select * from device where id in $ids";
    static final String fetchSiteIds = "select distinct site from device where id in $ids";
    static final String fetchName = "select name from device where id = $id";
    private static String updateAlarmSeverity;
    private static String updateOperationalState;
    private static String updateAdminState;
    private static String updateSite;
    static final String fetchIdAndName = "select id, name from device order by name";
    static final String fetchIdAndNameByIds = "select id, name from device where id in $ids";
    static final String fetchIdAndNameBySite = "select id, name from device where site = $site order by name";
    static final String fetchDeployed = "select id from device d where operational_state='deployed'";
    static final String protocolServerInUse = "select * from device where protocol_server = $id";
    
    static {
        DeviceDbAccess.update = "update device set\nname = $name,\noperational_state = $operational_state, \nadmin_state = $admin_state, \npolicy_mode = $policy_mode, \nmgmt_mode = $mgmt_mode, \ncomm_mode = $comm_mode, \nsite = $site, \nhighest_alarm_severity = $highest_alarm_severity, \ndevice_manager = $device_manager, \ncontent = $content \nwhere id = $id";
        DeviceDbAccess.updateAlarmSeverity = "update device set highest_alarm_severity = $highest_alarm_severity where id = $id";
        DeviceDbAccess.updateOperationalState = "update device set operational_state = $operational_state where id = $id";
        DeviceDbAccess.updateAdminState = "update device set admin_state = $admin_state where id = $id";
        DeviceDbAccess.updateSite = "update device set site = $site where id in $ids";
    }
    
    public DeviceDbAccess() {
        super(DbSession.DB.main);
    }
    
    public int countDevices(final String dmId) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select count(*) from device where device_manager = $device_manager");
            stmt.setString("device_manager", dmId);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return result.getInteger();
            }
        }
        finally {
            session.close();
        }
        session.close();
        return 0;
    }
    
    public int countDevices() throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select count(*) from device\n");
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return result.getInteger();
            }
        }
        finally {
            session.close();
        }
        session.close();
        return 0;
    }
    
    public Collection<Device> fetchDeployedByDeviceManager(final String dmId) throws DbException {
        final DbSession session = this.getDbSession();
        final Map<String, Device> devices = new HashMap<String, Device>();
        try {
            final DbStatement stmt = session.createStatement("select * from device\nwhere device_manager = $device_manager and\noperational_state = 'deployed' and admin_state = 'enabled'");
            stmt.setString("device_manager", dmId);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Device d = this.build(result, false);
                devices.put(d.getId(), d);
                d.removeInterfaces();
            }
            for (final IPInterface intf : new IPInterfaceDbAccess(this.getDb()).fetchByParent(devices.keySet())) {
                final Device d2 = devices.get(intf.getParentId());
                if (d2 != null) {
                    d2.addInterface(intf);
                }
            }
        }
        finally {
            session.close();
        }
        session.close();
        return devices.values();
    }
    
    public Collection<Device> fetchAllByDeviceManager(final String dmId) throws DbException {
        final DbSession session = this.getDbSession();
        final Map<String, Device> devices = new HashMap<String, Device>();
        try {
            final DbStatement stmt = session.createStatement("select * from device where device_manager = $device_manager");
            stmt.setString("device_manager", dmId);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Device d = this.build(result, false);
                devices.put(d.getId(), d);
                d.removeInterfaces();
            }
            for (final IPInterface intf : new IPInterfaceDbAccess(this.getDb()).fetchByParent(devices.keySet())) {
                final Device d2 = devices.get(intf.getParentId());
                if (d2 != null) {
                    d2.addInterface(intf);
                }
            }
        }
        finally {
            session.close();
        }
        session.close();
        return devices.values();
    }
    
    public Collection<Device> fetchAllBySite(final String siteId) throws DbException {
        final DbSession session = this.getDbSession();
        final Map<String, Device> devices = new LinkedHashMap<String, Device>();
        try {
            final DbStatement stmt = session.createStatement("select * from device where site = $site");
            stmt.setString("site", siteId);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Device d = this.build(result, false);
                devices.put(d.getId(), d);
                d.removeInterfaces();
            }
            for (final IPInterface intf : new IPInterfaceDbAccess(this.getDb()).fetchByParent(devices.keySet())) {
                final Device d2 = devices.get(intf.getParentId());
                if (d2 != null) {
                    d2.addInterface(intf);
                }
            }
        }
        finally {
            session.close();
        }
        session.close();
        return devices.values();
    }
    
    public List<String> fetchAllIds() throws DbException {
        final DbSession session = this.getDbSession();
        final List<String> devices = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("select id from device");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                devices.add(result.getString());
            }
        }
        finally {
            session.close();
        }
        session.close();
        return devices;
    }
    
    public List<String> fetchAllIdsBySites(final List<String> siteIds) throws DbException {
        final DbSession session = this.getDbSession();
        final List<String> devices = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("select id from device where site in $siteIds");
            stmt.setString("siteIds", siteIds);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                devices.add(result.getString());
            }
        }
        finally {
            session.close();
        }
        session.close();
        return devices;
    }
    
    public Collection<Device> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public Collection<Device> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final Map<String, Device> devices = new LinkedHashMap<String, Device>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from device order by name");
            }
            else {
                stmt = session.createStatement("select * from device order by name");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Device d = this.build(result, stub);
                devices.put(d.getId(), d);
                d.removeInterfaces();
            }
            if (!stub) {
                for (final IPInterface intf : new IPInterfaceDbAccess(this.getDb()).fetchAll()) {
                    final Device d2 = devices.get(intf.getParentId());
                    if (d2 != null) {
                        d2.addInterface(intf);
                    }
                }
            }
        }
        finally {
            session.close();
        }
        session.close();
        return devices.values();
    }
    
    public Collection<Device> fetchForPolicyDeployment(final Collection<String> deviceIds) throws DbException {
        final DbSession session = this.getDbSession();
        final Map<String, Device> devices = new HashMap<String, Device>();
        try {
            final DbStatement stmt = session.createStatement("select * from device\nwhere id in $ids and operational_state = 'deployed' and comm_mode = 'online' and mgmt_mode = 'managed' and policy_mode = 'site'");
            stmt.setString("ids", deviceIds);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Device d = this.build(result, false);
                devices.put(d.getId(), d);
                d.removeInterfaces();
            }
            for (final IPInterface intf : new IPInterfaceDbAccess(this.getDb()).fetchByParent(devices.keySet())) {
                final Device d2 = devices.get(intf.getParentId());
                if (d2 != null) {
                    d2.addInterface(intf);
                }
            }
        }
        finally {
            session.close();
        }
        session.close();
        return devices.values();
    }
    
    public Collection<String> fetchIdsForPolicyDeployment(final Collection<String> deviceIds) throws DbException {
        final DbSession session = this.getDbSession();
        final List<String> ids = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("select id from device\nwhere id in $ids and operational_state = 'deployed' and comm_mode = 'online' and mgmt_mode = 'managed' and policy_mode = 'site'");
            stmt.setString("ids", deviceIds);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                ids.add(result.getString("id"));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return ids;
    }
    
    public Set<String> fetchForPolicyDeployment(final String siteId) throws DbException {
        final DbSession session = this.getDbSession();
        final Set<String> devices = new HashSet<String>();
        try {
            final DbStatement stmt = session.createStatement("select id from device \nwhere operational_state = 'deployed' and comm_mode = 'online' and mgmt_mode = 'managed' and site = $site and policy_mode = 'site'");
            stmt.setString("site", siteId);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                devices.add(result.getString());
            }
        }
        finally {
            session.close();
        }
        session.close();
        return devices;
    }
    
    public Device fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public Device fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final Device d = new Device(id);
                d.trim();
                return d;
            }
            final DbStatement stmt = session.createStatement("select * from device where id = $id");
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final Device d2 = this.build(result, stub);
                d2.removeInterfaces();
                for (final IPInterface intf : new IPInterfaceDbAccess(this.getDb()).fetchByParent(d2.getId())) {
                    d2.addInterface(intf);
                }
                return d2;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public Collection<Device> fetchById(final Collection<String> list) throws DbException {
        final DbSession session = this.getDbSession();
        final Map<String, Device> devices = new HashMap<String, Device>();
        try {
            final DbStatement stmt = session.createStatement("select * from device where id in $ids");
            stmt.setString("ids", list);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Device d = this.build(result, false);
                devices.put(d.getId(), d);
                d.removeInterfaces();
            }
            for (final IPInterface intf : new IPInterfaceDbAccess(this.getDb()).fetchByParent(devices.keySet())) {
                final Device d2 = devices.get(intf.getParentId());
                if (d2 != null) {
                    d2.addInterface(intf);
                }
            }
        }
        finally {
            session.close();
        }
        session.close();
        return devices.values();
    }
    
    public String fetchName(final String deviceId) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select name from device where id = $id");
            stmt.setString("id", deviceId);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return result.getString();
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public Set<String> fetchSiteIds(final Collection<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        final Set<String> siteIds = new HashSet<String>();
        try {
            final DbStatement stmt = session.createStatement("select distinct site from device where id in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                siteIds.add(result.getString());
            }
        }
        finally {
            session.close();
        }
        session.close();
        return siteIds;
    }
    
    public void insert(final Device device) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into device\n(id,\nname,\noperational_state,\nadmin_state,\npolicy_mode, \nmgmt_mode, \ncomm_mode, \nsite,\nhighest_alarm_severity,\ncontent, \ndevice_manager) \nvalues\n($id,\n$name,\n$operational_state,\n$admin_state,\n$policy_mode, \n$mgmt_mode, \n$comm_mode, \n$site,\n$highest_alarm_severity,\n$content, \n$device_manager)");
            stmt.setString("id", device.getId());
            stmt.setString("name", device.getName());
            final Device.Mode mode = device.getMode();
            stmt.setString("policy_mode", mode.getPolicy().name());
            stmt.setString("mgmt_mode", mode.getManagement().name());
            stmt.setString("comm_mode", mode.getCommunication().name());
            final Device.State state = device.getState();
            stmt.setString("operational_state", state.getOperational().name());
            stmt.setString("admin_state", state.getAdmin().name());
            stmt.setString("site", device.getSiteId());
            stmt.setString("device_manager", device.getDeviceManagerId());
            stmt.setString("highest_alarm_severity", device.getHighestAlarmSeverity().name());
            stmt.setString("content", device.getRoot().cloneTree());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            final TagDbAccess tagAccess = new TagDbAccess(DbSession.DB.latest);
            tagAccess.insert(device.getTags(), device.getId());
            if (!device.getMode().isManaged()) {
                new IPInterfaceDbAccess(this.getDb()).replaceForParent(device.getId(), device.getInterfaces());
            }
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final Device device) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from device where id = $id");
            stmt.setString("id", device.getId());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            final TagDbAccess tagAccess = new TagDbAccess(DbSession.DB.latest);
            tagAccess.deleteByEntity(device.getId());
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void updateAlarmSeverity(final String id, final Alarm.Severity severity) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement(DeviceDbAccess.updateAlarmSeverity);
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", id);
            stmt.setString("highest_alarm_severity", severity.name());
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void updateOperationalState(final String id, final Device.State.Operational state) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement(DeviceDbAccess.updateOperationalState);
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", id);
            stmt.setString("operational_state", state.name());
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void updateAdminState(final String id, final Device.State.Admin state) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement(DeviceDbAccess.updateAdminState);
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", id);
            stmt.setString("admin_state", state.name());
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void updateSite(final List<String> ids, final String site) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement(DeviceDbAccess.updateSite);
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("ids", ids);
            stmt.setString("site", site);
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void update(final Device device) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement(DeviceDbAccess.update);
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", device.getId());
            stmt.setString("name", device.getName());
            final Device.Mode mode = device.getMode();
            stmt.setString("policy_mode", mode.getPolicy().name());
            stmt.setString("mgmt_mode", mode.getManagement().name());
            stmt.setString("comm_mode", mode.getCommunication().name());
            final Device.State state = device.getState();
            stmt.setString("operational_state", state.getOperational().name());
            stmt.setString("admin_state", state.getAdmin().name());
            stmt.setString("site", device.getSiteId());
            stmt.setString("device_manager", device.getDeviceManagerId());
            stmt.setString("highest_alarm_severity", device.getHighestAlarmSeverity().name());
            stmt.setString("content", device.getRoot().cloneTree());
            stmt.execute();
            final TagDbAccess tagAccess = new TagDbAccess(DbSession.DB.latest);
            tagAccess.deleteByEntity(device.getId());
            tagAccess.insert(device.getTags(), device.getId());
            if (!device.getMode().isManaged()) {
                new IPInterfaceDbAccess(this.getDb()).replaceForParent(device.getId(), device.getInterfaces());
            }
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public List<String> fetchDeployed() throws DbException {
        final DbSession db = this.getDbSession();
        final List<String> list = new ArrayList<String>();
        try {
            final DbStatement stmt = db.createStatement("select id from device d where operational_state='deployed'");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(result.getString());
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public List<Device> fetchIdAndName() throws DbException {
        final DbSession db = this.getDbSession();
        final List<Device> list = new ArrayList<Device>();
        try {
            final DbStatement stmt = db.createStatement("select id, name from device order by name");
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Device d = new Device(result.getString("id"));
                d.setName(result.getString("name"));
                list.add(d);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public List<Device> fetchIdAndName(final Collection<String> ids) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Device> list = new ArrayList<Device>();
        try {
            final DbStatement stmt = db.createStatement("select id, name from device where id in $ids");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Device d = new Device(result.getString("id"));
                d.setName(result.getString("name"));
                list.add(d);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public List<Device> fetchIdAndName(final String siteId) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Device> list = new ArrayList<Device>();
        try {
            final DbStatement stmt = db.createStatement("select id, name from device where site = $site order by name");
            stmt.setString("site", siteId);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Device d = new Device(result.getString("id"));
                d.setName(result.getString("name"));
                list.add(d);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public boolean inUse(final ProtocolServer server) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from device where protocol_server = $id");
            stmt.setString("id", server.getId());
            final DbResultSet result = stmt.execute();
            return result.next();
        }
        finally {
            session.close();
        }
    }
    
    private Device build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final Device d = new Device(result.getString("id"));
            d.trim();
            return d;
        }
        final Device d = new Device(result.getElement("content"));
        d.setName(result.getString("name"));
        d.setHighestAlarmSeverity(Alarm.Severity.valueOf(result.getString("highest_alarm_severity")));
        d.setSiteId(result.getString("site"));
        d.setDeviceManagerId(result.getString("device_manager"));
        final Device.Mode mode = d.getMode();
        mode.setPolicy(Device.Mode.Policy.valueOf(result.getString("policy_mode")));
        mode.setManagement(Device.Mode.Management.valueOf(result.getString("mgmt_mode")));
        mode.setCommunication(Device.Mode.Communication.valueOf(result.getString("comm_mode")));
        final Device.State state = d.getState();
        state.setOperational(Device.State.Operational.valueOf(result.getString("operational_state")));
        state.setAdmin(Device.State.Admin.valueOf(result.getString("admin_state")));
        return d;
    }
}
