// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Collection;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.DeviceOperation;
import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.Opset;
import com.stonewall.cornerstone.entity.Label;

public class OpsetDbAccess extends DbAccess
{
    static final String fetchAll = "select * from opset";
    static final String fetchAllStubs = "select id, type, device, opcount from opset";
    static final String fetchById = "select * from opset where id = $id";
    static final String fetchStubById = "select id, type, device, opcount from opset where id = $id";
    static final String fetchReferencesByParent = "select * from opset where device = $device";
    static final String fetchByDeviceAndType = "select * from opset where device = $device and type = $type";
    static final String fetchAllOps = "select * from device_operation where opset = $opset";
    static final String fetchAllOpStubs = "select id, opset from device_operation where opset = $opset";
    static final String fetchByIndex = "select * from device_operation where opset = $opset and idx = $index";
    static final String fetchOpById = "select * from device_operation where id = $id";
    static final String fetchOpStubById = "select id, opset from device_operation where id = $id";
    static final String deleteOperationsByOpset = "delete from device_operation where opset = $opset";
    static final String deleteOperationsByDevice = "delete from device_operation\nwhere opset in (select distinct id from opset where device = $device)";
    static final String fetchOpsetId = "select id from opset where device = $device and type = $type";
    static final String clearOperations = "delete from device_operation where id in $ids";
    static final String fetchClearedOperations = "select id, opset from device_operation where opset in\n(select distinct id from opset where device in $device and type = $type)";
    static final String delete = "delete from opset where id = $id";
    static final String deleteByDevice = "delete from opset where device = $device";
    static final String insertOpset = "insert into opset\n(id, type, correlation, timestamp, device, opcount)\nvalues\n($id, $type, $correlation, $timestamp, $device, $opcount)";
    static final String updateOpset = "update opset set\ncorrelation = $correlation,\ntimestamp = $timestamp,\nopcount = $opcount \nwhere id = $id";
    static final String insertOperation = "insert into device_operation\n(id, opset, idx, content)\nvalues \n($id, $opset, $idx, $content)";
    static final String emptyCommands = "<en:commands xmlns:en=\"http://www.stonewallnetworks.com/ns/entity\"/>";
    static final String updateProcessedOperation = "update device_operation set\ncontent = $content\nwhere id = $id";
    static final String[] enclosures;
    static final String operationValues = "($id, $opset, $idx, $content)";
    
    static {
        enclosures = new String[] { "", ",\n", "" };
    }
    
    public OpsetDbAccess() {
    }
    
    public OpsetDbAccess(final Label label) {
        super(label);
    }
    
    public OpsetDbAccess(final String label) {
        super(label);
    }
    
    public Opset fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public Opset fetchById(final String id, final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id, type, device, opcount from opset where id = $id");
            }
            else {
                stmt = db.createStatement("select * from opset where id = $id");
            }
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.buildOpset(result, stub);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return null;
    }
    
    public List<Opset> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<Opset> fetchAll(final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        final List<Opset> list = new ArrayList<Opset>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id, type, device, opcount from opset");
            }
            else {
                stmt = db.createStatement("select * from opset");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final Opset opset = this.buildOpset(result, stub);
                list.add(opset);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public Opset fetchByDeviceAndType(final String deviceId, final Opset.Type type, final boolean includeOps) throws DbException {
        final DbSession db = this.getDbSession();
        Opset opset = null;
        try {
            final DbStatement stmt = db.createStatement("select * from opset where device = $device and type = $type");
            stmt.setString("device", deviceId);
            stmt.setString("type", type.name());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                opset = this.buildOpset(result, false);
            }
            if (includeOps) {
                final List<DeviceOperation> ops = this.fetchAllOps(opset.getId());
                for (final DeviceOperation op : ops) {
                    opset.addOperation(op);
                }
            }
        }
        finally {
            db.close();
        }
        db.close();
        return opset;
    }
    
    public Opset fetchPreview(final String deviceId, final boolean includeOps) throws DbException {
        return this.fetchByDeviceAndType(deviceId, Opset.Type.preview, includeOps);
    }
    
    public Opset fetchDeploy(final String deviceId, final boolean includeOps) throws DbException {
        return this.fetchByDeviceAndType(deviceId, Opset.Type.deploy, includeOps);
    }
    
    public Opset fetchAudit(final String deviceId, final boolean includeOps) throws DbException {
        return this.fetchByDeviceAndType(deviceId, Opset.Type.audit, includeOps);
    }
    
    public List<DeviceOperation> fetchAllOps(final String opset) throws DbException {
        return this.fetchAllOps(opset, false);
    }
    
    public List<DeviceOperation> fetchAllOps(final String opset, final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        final List<DeviceOperation> list = new ArrayList<DeviceOperation>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id, opset from device_operation where opset = $opset");
            }
            else {
                stmt = db.createStatement("select * from device_operation where opset = $opset");
            }
            stmt.setString("opset", opset);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(this.buildOperation(result, stub));
            }
        }
        finally {
            db.close();
        }
        db.close();
        return list;
    }
    
    public DeviceOperation fetchOpById(final String id) throws DbException {
        return this.fetchOpById(id, false);
    }
    
    public DeviceOperation fetchOpById(final String id, final boolean stub) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = db.createStatement("select id, opset from device_operation where id = $id");
            }
            else {
                stmt = db.createStatement("select * from device_operation where id = $id");
            }
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.buildOperation(result, stub);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return null;
    }
    
    public DeviceOperation fetchByIndex(final String opset, final int index) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("select * from device_operation where opset = $opset and idx = $index");
            stmt.setString("opset", opset);
            stmt.set("index", index);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.buildOperation(result, false);
            }
        }
        finally {
            db.close();
        }
        db.close();
        return null;
    }
    
    public void update(final Opset opset) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            DbStatement stmt = db.createStatement("select id from opset where device = $device and type = $type");
            stmt.setString("device", opset.getParentId());
            stmt.setString("type", opset.getType().name());
            final DbResultSet result = stmt.execute();
            if (!result.next()) {
                throw new DbException("not-found");
            }
            opset.setId(result.getString());
            stmt.close();
            stmt = db.createStatement("update opset set\ncorrelation = $correlation,\ntimestamp = $timestamp,\nopcount = $opcount \nwhere id = $id");
            stmt.setString("id", opset.getId());
            stmt.setString("correlation", opset.getCorrelation());
            stmt.set("timestamp", opset.getTimestamp());
            stmt.set("opcount", opset.getOpCount());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            stmt.close();
            stmt = db.createStatement("delete from device_operation where opset = $opset");
            stmt.setString("opset", opset.getId());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            stmt.close();
            this.insertOperations(opset);
        }
        catch (Exception e) {
            db.rollback();
            throw new DbException(e);
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final DeviceOperation operation) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update device_operation set\ncontent = $content\nwhere id = $id");
            stmt.setString("id", operation.getId());
            stmt.setString("content", operation.getRoot());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void insert(final Opset opset) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into opset\n(id, type, correlation, timestamp, device, opcount)\nvalues\n($id, $type, $correlation, $timestamp, $device, $opcount)");
            stmt.setString("id", opset.getId());
            stmt.setString("type", opset.getType().name());
            stmt.setString("correlation", opset.getCorrelation());
            stmt.setString("device", opset.getParentId());
            stmt.set("timestamp", opset.getTimestamp());
            stmt.set("opcount", opset.getOpCount());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            this.insertOperations(opset);
        }
        catch (Exception e) {
            db.rollback();
            throw new DbException(e);
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void insertOperations(final Opset opset) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            int index = 0;
            for (final DeviceOperation d : opset.getOperations()) {
                final DbStatement stmt = db.createStatement("insert into device_operation\n(id, opset, idx, content)\nvalues \n($id, $opset, $idx, $content)");
                stmt.set(DbStatement.Mode.Update);
                stmt.setString("id", d.getId());
                stmt.setString("opset", opset.getId());
                stmt.set("idx", index);
                stmt.setString("content", d.getRoot());
                stmt.execute();
                ++index;
            }
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void deleteByDevice(final String deviceId) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            DbStatement stmt = db.createStatement("delete from device_operation\nwhere opset in (select distinct id from opset where device = $device)");
            stmt.setString("device", deviceId);
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            stmt.close();
            stmt = db.createStatement("delete from opset where device = $device");
            stmt.setString("device", deviceId);
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            stmt.close();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final Opset opset) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            DbStatement stmt = db.createStatement("delete from device_operation where opset = $opset");
            stmt.setString("opset", opset.getId());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            stmt.close();
            stmt = db.createStatement("delete from opset where id = $id");
            stmt.setString("id", opset.getId());
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            stmt.close();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public List<String> clearOperations(final List<String> deviceIds, final Opset.Type type) throws DbException {
        final DbSession db = this.getDbSession();
        final List<String> operations = new ArrayList<String>();
        final List<String> opsets = new ArrayList<String>();
        try {
            DbStatement stmt = db.createStatement("select id, opset from device_operation where opset in\n(select distinct id from opset where device in $device and type = $type)");
            stmt.setString("device", deviceIds);
            stmt.setString("type", type.name());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                operations.add(result.getString("id"));
                opsets.add(result.getString("opset"));
            }
            stmt.close();
            if (operations.size() > 0) {
                stmt = db.createStatement("delete from device_operation where id in $ids");
                stmt.setString("ids", operations);
                stmt.set(DbStatement.Mode.Update);
                stmt.execute();
                stmt.close();
            }
        }
        finally {
            db.close();
        }
        db.close();
        return opsets;
    }
    
    private Opset buildOpset(final DbResultSet result, final boolean stub) throws DbException {
        final Opset opset = new Opset(Opset.Type.valueOf(result.getString("type")));
        opset.setId(result.getString("id"));
        opset.setParentId(result.getString("device"));
        opset.setTimestamp(result.getLong("timestamp"));
        opset.setOpCount(result.getInteger("opcount"));
        if (stub) {
            opset.trim();
            return opset;
        }
        final String correlation = result.getString("correlation");
        opset.setCorrelation(correlation);
        return opset;
    }
    
    private DeviceOperation buildOperation(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final DeviceOperation op = new DeviceOperation(result.getString("id"));
            op.setParentId(result.getString("opset"));
            op.trim();
            return op;
        }
        try {
            return new DeviceOperation(result.getElement("content"));
        }
        catch (Exception ex) {
            throw new DbException(ex);
        }
    }
}
