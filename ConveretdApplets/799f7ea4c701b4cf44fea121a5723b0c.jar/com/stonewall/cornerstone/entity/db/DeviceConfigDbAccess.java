// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import com.stonewall.cornerstone.entity.Device;
import java.util.Collection;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.DeviceConfig;
import java.util.List;
import com.stonewall.cornerstone.entity.Label;

public class DeviceConfigDbAccess extends DbAccess
{
    static final String insert = "insert into device_configuration\n(id, device, checksum, timestamp, content)\nvalues\n($id, $device, $checksum, $timestamp, $content)";
    static final String update = "update device_configuration set\nchecksum = $checksum, \ntimestamp = $timestamp, \nraw = $raw, \ncontent = $content \nwhere id = $id ";
    static final String delete = "delete from device_configuration where id = $id";
    static final String deleteByParent = "delete from device_configuration where device = $device";
    static final String fetchAllStubs = "select id, device, timestamp from device_configuration";
    static final String fetchAll = "select * from device_configuration";
    static final String fetchStubByParent = "select id, device, timestamp from device_configuration where device = $device";
    static final String fetchByParent = "select * from device_configuration where device = $device";
    static final String fetchByParents = "select * from device_configuration where device in $devices";
    static final String fetchById = "select * from device_configuration where id = $id";
    static final String fetchStubById = "select id, device, timestamp from device_configuration where id = $id";
    static final String fetchChecksum = "select checksum from device_configuration where id = $id";
    static final String updateChecksum = "update device_configuration set checksum = $checksum where device = $device";
    static final String fetchRawById = "select raw from device_configuration where id = $id";
    
    public DeviceConfigDbAccess() {
    }
    
    public DeviceConfigDbAccess(final Label label) {
        super(label);
    }
    
    public DeviceConfigDbAccess(final String label) {
        super(label);
    }
    
    public List<DeviceConfig> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<DeviceConfig> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<DeviceConfig> list = new ArrayList<DeviceConfig>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, device, timestamp from device_configuration");
            }
            else {
                stmt = session.createStatement("select * from device_configuration");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(this.build(result, stub));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public DeviceConfig fetchByParent(final String device) throws DbException {
        return this.fetchByParent(device, false);
    }
    
    public DeviceConfig fetchByParent(final String device, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, device, timestamp from device_configuration where device = $device");
            }
            else {
                stmt = session.createStatement("select * from device_configuration where device = $device");
            }
            stmt.setString("device", device);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.build(result, stub);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public List<DeviceConfig> fetchByParent(final Collection<String> devices) throws DbException {
        final DbSession session = this.getDbSession();
        final List<DeviceConfig> list = new ArrayList<DeviceConfig>();
        try {
            final DbStatement stmt = session.createStatement("select * from device_configuration where device in $devices");
            stmt.setString("devices", devices);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(this.build(result, false));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public DeviceConfig fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public DeviceConfig fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, device, timestamp from device_configuration where id = $id");
            }
            else {
                stmt = session.createStatement("select * from device_configuration where id = $id");
            }
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.build(result, stub);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public String fetchChecksum(final String id) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select checksum from device_configuration where id = $id");
            stmt.setString("id", id);
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
    
    public void insert(final DeviceConfig config) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into device_configuration\n(id, device, checksum, timestamp, content)\nvalues\n($id, $device, $checksum, $timestamp, $content)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", config.getId());
            stmt.setString("device", config.getParentId());
            stmt.setString("checksum", config.getChecksum());
            stmt.set("timestamp", config.getTimestamp());
            stmt.setString("content", config.getRoot().cloneTree());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final DeviceConfig config) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update device_configuration set\nchecksum = $checksum, \ntimestamp = $timestamp, \nraw = $raw, \ncontent = $content \nwhere id = $id ");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", config.getId());
            stmt.setString("checksum", config.getChecksum());
            stmt.set("timestamp", config.getTimestamp());
            stmt.setString("raw", config.getRaw());
            config.clearRaw();
            stmt.setString("content", config.getRoot().cloneTree());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void deleteForParent(final String device) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from device_configuration where device = $device");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("device", device);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final DeviceConfig config) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("delete from device_configuration where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", config.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void updateChecksum(final Device device, final String checksum) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update device_configuration set checksum = $checksum where device = $device");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("device", device.getId());
            stmt.setString("checksum", checksum);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public String fetchRawById(final String id) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select raw from device_configuration where id = $id");
            stmt.setString("id", id);
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
    
    private DeviceConfig build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final DeviceConfig c = new DeviceConfig(result.getString("id"));
            c.setParentId(result.getString("device"));
            c.setTimestamp(result.getLong("timestamp"));
            c.trim();
            return c;
        }
        final DeviceConfig c = new DeviceConfig(result.getElement("content"));
        c.setChecksum(result.getString("checksum"));
        c.setTimestamp(result.getLong("timestamp"));
        return c;
    }
}
