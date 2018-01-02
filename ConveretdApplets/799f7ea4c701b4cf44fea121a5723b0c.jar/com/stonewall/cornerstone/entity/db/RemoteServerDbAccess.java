// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Iterator;
import com.stonewall.cornerstone.entity.IPInterface;
import com.stonewall.cornerstone.entity.ProtocolServer;
import com.stonewall.cornerstone.entity.DeviceManager;
import com.stonewall.cornerstone.entity.Alarm;
import com.stonewall.cornerstone.component.ComponentServer;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import com.stonewall.cornerstone.entity.RemoteServer;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.remoteServer.ServerConfiguration;
import com.stonewall.cornerstone.db.DbSession;

public class RemoteServerDbAccess extends DbAccess
{
    static final String fetchAllByType = "select * from remote_server where type = $type";
    static final String fetchAllStubsByType = "select id, type from remote_server where type = $type";
    static final String fetchAll = "select * from remote_server";
    static final String fetchAllStubs = "select id, type from remote_server";
    static final String fetchIds = "select id from remote_server where type = $type";
    static final String fetchById = "select * from remote_server where id = $id";
    static final String fetchStubById = "select id, type from remote_server where id = $id";
    static final String fetchByParent = "select * from remote_server where policy_server = $policy_server";
    static final String fetchByIds = "select * from remote_server where id in $ids and type = $type";
    static final String fetchBySerialNumber = "select * from remote_server where sn = $sn";
    static final String insert = "insert into remote_server\n(id, name, type, queue, operational_state, admin_state, sn, policy_server, highest_alarm_severity)\nvalues\n($id, $name, $type, $queue, $operational_state, $admin_state, $sn, $policy_server, $highest_alarm_severity)";
    static final String update = "update remote_server set\nname = $name,\nsn = $sn,\noperational_state = $operational_state,\nadmin_state = $admin_state\nwhere id = $id";
    static final String delete = "delete from remote_server where id = $id";
    static final String fetchConfiguration = "select id, name, queue from remote_server where sn = $sn";
    private static String updateAlarmSeverity;
    
    static {
        RemoteServerDbAccess.updateAlarmSeverity = "update remote_server set highest_alarm_severity = $highest_alarm_severity\nwhere id = $id";
    }
    
    public RemoteServerDbAccess() {
        super(DbSession.DB.main);
    }
    
    public ServerConfiguration fetchConfiguration(final String sn) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select id, name, queue from remote_server where sn = $sn");
            stmt.setString("sn", sn);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final ServerConfiguration c = new ServerConfiguration(result.getString("id"), result.getString("name"), result.getString("queue"));
                return c;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public <T extends RemoteServer> T fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public <T extends RemoteServer> T fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, type from remote_server where id = $id");
            }
            else {
                stmt = session.createStatement("select * from remote_server where id = $id");
            }
            stmt.setString("id", id);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final T c = this.build(result, stub);
                return c;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public <T extends RemoteServer> List<T> fetchById(final Collection<String> ids) throws DbException {
        final DbSession session = this.getDbSession();
        final List<T> list = new ArrayList<T>();
        try {
            final DbStatement stmt = session.createStatement("select * from remote_server where id in $ids and type = $type");
            stmt.setString("ids", ids);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final T c = this.build(result, false);
                list.add(c);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public <T extends RemoteServer> T fetchBySerialNumber(final String sn) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from remote_server where sn = $sn");
            stmt.setString("sn", sn);
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                final T c = this.build(result, false);
                return c;
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public <T extends RemoteServer> List<T> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public <T extends RemoteServer> List<T> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<T> list = new ArrayList<T>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, type from remote_server");
            }
            else {
                stmt = session.createStatement("select * from remote_server");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final T c = this.build(result, stub);
                list.add(c);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public <T extends RemoteServer> List<T> fetchAll(final ComponentServer.Type type) throws DbException {
        return this.fetchAll(type, false);
    }
    
    public <T extends RemoteServer> List<T> fetchAll(final ComponentServer.Type type, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<T> list = new ArrayList<T>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id, type from remote_server where type = $type");
            }
            else {
                stmt = session.createStatement("select * from remote_server where type = $type");
            }
            stmt.setString("type", type.name());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final T c = this.build(result, stub);
                list.add(c);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public <T extends RemoteServer> List<T> fetchByParent(final String parent) throws DbException {
        final DbSession session = this.getDbSession();
        final List<T> list = new ArrayList<T>();
        try {
            final DbStatement stmt = session.createStatement("select * from remote_server where policy_server = $policy_server");
            stmt.setString("policy_server", parent);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final T c = this.build(result, false);
                list.add(c);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public List<String> fetchIds(final ComponentServer.Type type) throws DbException {
        final DbSession session = this.getDbSession();
        final List<String> list = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("select id from remote_server where type = $type");
            stmt.setString("type", type.name());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                list.add(result.getString());
            }
        }
        finally {
            session.close();
        }
        session.close();
        return list;
    }
    
    public void insert(final RemoteServer server) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("insert into remote_server\n(id, name, type, queue, operational_state, admin_state, sn, policy_server, highest_alarm_severity)\nvalues\n($id, $name, $type, $queue, $operational_state, $admin_state, $sn, $policy_server, $highest_alarm_severity)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", server.getId());
            stmt.setString("name", server.getName());
            stmt.setString("type", server.getType().name());
            stmt.setString("queue", server.getQueue());
            stmt.setString("operational_state", server.getOperationalState().name());
            stmt.setString("admin_state", server.getAdminState().name());
            stmt.setString("sn", server.getSerialNumber());
            stmt.setString("policy_server", server.getParentId());
            stmt.setString("highest_alarm_severity", server.getHighestAlarmSeverity().name());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void update(final RemoteServer server) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            final DbStatement stmt = db.createStatement("update remote_server set\nname = $name,\nsn = $sn,\noperational_state = $operational_state,\nadmin_state = $admin_state\nwhere id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", server.getId());
            stmt.setString("name", server.getName());
            stmt.setString("operational_state", server.getOperationalState().name());
            stmt.setString("admin_state", server.getAdminState().name());
            stmt.setString("sn", server.getSerialNumber());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void delete(final RemoteServer server) throws DbException {
        final DbSession db = this.getDbSession();
        try {
            new IPInterfaceDbAccess(this.getDb()).deleteByParent(server.getId());
            final DbStatement stmt = db.createStatement("delete from remote_server where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", server.getId());
            stmt.execute();
        }
        finally {
            db.close();
        }
        db.close();
    }
    
    public void updateAlarmSeverity(final String id, final Alarm.Severity severity) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement(RemoteServerDbAccess.updateAlarmSeverity);
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
    
    protected <T extends RemoteServer> T build(final DbResultSet result, final boolean stub) throws DbException {
        if (stub) {
            final ComponentServer.Type type = ComponentServer.Type.valueOf(result.getString("type"));
            T server = null;
            switch (type) {
                case dm: {
                    server = (T)new DeviceManager(result.getString("id"));
                    break;
                }
                case cp: {
                    server = (T)new ProtocolServer(result.getString("id"));
                    break;
                }
                default: {
                    throw new DbException("Unknown remote server type:" + type.name());
                }
            }
            server.trim();
            return server;
        }
        final ComponentServer.Type type = ComponentServer.Type.valueOf(result.getString("type"));
        T server = null;
        switch (type) {
            case dm: {
                server = (T)new DeviceManager(result.getString("id"));
                this.fetchInterfaces((DeviceManager)server);
                break;
            }
            case cp: {
                server = (T)new ProtocolServer(result.getString("id"));
                break;
            }
            default: {
                throw new DbException("Unknown remote server type:" + type.name());
            }
        }
        server.setType(type);
        server.setName(result.getString("name"));
        server.setQueue(result.getString("queue"));
        server.setOperationalState(RemoteServer.OperationalState.valueOf(result.getString("operational_state")));
        server.setAdminState(RemoteServer.AdminState.valueOf(result.getString("admin_state")));
        server.setSerialNumber(result.getString("sn"));
        server.setParentId(result.getString("policy_server"));
        server.setHighestAlarmSeverity(Alarm.Severity.valueOf(result.getString("highest_alarm_severity")));
        return server;
    }
    
    private void fetchInterfaces(final DeviceManager m) throws DbException {
        for (final IPInterface intf : new IPInterfaceDbAccess(this.getDb()).fetchByParent(m.getId())) {
            m.addInterface(intf);
        }
    }
}
