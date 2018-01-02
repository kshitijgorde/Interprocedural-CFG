// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.db;

import java.util.Iterator;
import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import java.util.ArrayList;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.Alarm;
import java.util.List;
import com.stonewall.cornerstone.db.DbSession;

public class AlarmDbAccess extends DbAccess
{
    static final String insert = "insert into alarm\n(id, type, state, severity, created, entity, description, cleared)\nvalues\n($id, $type, $state, $severity, $created, $entity, $description, $cleared)";
    static final String update = "update alarm set state = $state, cleared = $cleared where id = $id";
    static final String findAlarm = "select * from alarm\nwhere entity = $entity and\ntype = $type and\nstate = $state";
    static final String determineHighestSeverity = "select severity from alarm\nwhere entity = $entity and state = 'opened'";
    static final String fetchAll = "select * from alarm";
    static final String fetchAllStubs = "select id from alarm";
    static final String fetchById = "select * from alarm where id = $id";
    static final String fetchOpen = "select * from alarm where state = 'opened'";
    static final String fetchOpenStubs = "select id from alarm where state = 'opened'";
    static final String fetchByEntity = "select * from alarm where entity = $entity";
    static final String fetchOpenByEntity = "select * from alarm\nwhere entity = $entity and state = 'opened'";
    static final String fetchOpenByType = "select * from alarm\nwhere type = $type and\nstate = 'opened'";
    static final String delete = "delete from alarm where id = $id";
    
    public AlarmDbAccess() {
        super(DbSession.DB.main);
    }
    
    public List<Alarm> fetchAll() throws DbException {
        return this.fetchAll(false);
    }
    
    public List<Alarm> fetchAll(final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        final List<Alarm> alarms = new ArrayList<Alarm>();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from alarm");
            }
            else {
                stmt = session.createStatement("select * from alarm");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                alarms.add(this.build(result, stub));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return alarms;
    }
    
    public Alarm fetchById(final String id) throws DbException {
        return this.fetchById(id, false);
    }
    
    public Alarm fetchById(final String id, final boolean stub) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            if (stub) {
                final Alarm a = new Alarm(id);
                a.trim();
                return a;
            }
            final DbStatement stmt = session.createStatement("select * from alarm where id = $id");
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
    
    public List<Alarm> fetchAllOpen(final EntityReference entity) throws DbException {
        final List<Alarm> alarms = new ArrayList<Alarm>();
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from alarm\nwhere entity = $entity and state = 'opened'");
            stmt.setString("entity", entity.getEncoded());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                alarms.add(this.build(result, false));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return alarms;
    }
    
    public List<Alarm> fetchAllOpen() throws DbException {
        return this.fetchAllOpen(false);
    }
    
    public List<Alarm> fetchAllOpen(final boolean stub) throws DbException {
        final List<Alarm> alarms = new ArrayList<Alarm>();
        final DbSession session = this.getDbSession();
        try {
            DbStatement stmt = null;
            if (stub) {
                stmt = session.createStatement("select id from alarm where state = 'opened'");
            }
            else {
                stmt = session.createStatement("select * from alarm where state = 'opened'");
            }
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                alarms.add(this.build(result, stub));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return alarms;
    }
    
    public List<Alarm> fetchOpenByType(final Alarm.Type type) throws DbException {
        final List<Alarm> alarms = new ArrayList<Alarm>();
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from alarm\nwhere type = $type and\nstate = 'opened'");
            stmt.setString("type", type.name());
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                alarms.add(this.build(result, false));
            }
        }
        finally {
            session.close();
        }
        session.close();
        return alarms;
    }
    
    public Alarm findAlarm(final EntityReference reference, final Alarm.Type alarmType, final Alarm.State state) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select * from alarm\nwhere entity = $entity and\ntype = $type and\nstate = $state");
            stmt.setString("entity", reference.getEncoded());
            stmt.setString("type", alarmType.name());
            stmt.setString("state", state.name());
            final DbResultSet result = stmt.execute();
            if (result.next()) {
                return this.build(result, false);
            }
        }
        finally {
            session.close();
        }
        session.close();
        return null;
    }
    
    public Alarm.Severity determineHighestSeverity(final EntityReference reference) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("select severity from alarm\nwhere entity = $entity and state = 'opened'");
            stmt.setString("entity", reference.getEncoded());
            final DbResultSet result = stmt.execute();
            final List<String> l = new ArrayList<String>();
            while (result.next()) {
                l.add(result.getString());
            }
            Alarm.Severity highest = Alarm.Severity.none;
            for (final String name : l) {
                final Alarm.Severity s = Alarm.Severity.valueOf(name);
                if (s.compareTo(highest) > 0) {
                    highest = s;
                }
            }
            return highest;
        }
        finally {
            session.close();
        }
    }
    
    public void insert(final Alarm alarm) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("insert into alarm\n(id, type, state, severity, created, entity, description, cleared)\nvalues\n($id, $type, $state, $severity, $created, $entity, $description, $cleared)");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", alarm.getId());
            stmt.setString("type", alarm.getType().name());
            stmt.setString("state", alarm.getState().name());
            stmt.setString("severity", alarm.getSeverity().name());
            stmt.set("created", alarm.getCreatedTs());
            stmt.setString("entity", alarm.getAlarmedReference().getEncoded());
            stmt.setString("description", alarm.getAlarmText());
            stmt.set("cleared", "null");
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void update(final Alarm alarm) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("update alarm set state = $state, cleared = $cleared where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", alarm.getId());
            stmt.setString("state", alarm.getState().name());
            stmt.set("cleared", alarm.getClearedTs());
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public void delete(final Alarm alarm) throws DbException {
        final DbSession session = this.getDbSession();
        try {
            final DbStatement stmt = session.createStatement("delete from alarm where id = $id");
            stmt.set(DbStatement.Mode.Update);
            stmt.setString("id", alarm.getId());
            stmt.execute();
        }
        finally {
            session.close();
        }
        session.close();
    }
    
    public Alarm build(final DbResultSet result, final boolean stub) throws DbException {
        final Alarm a = new Alarm(result.getString("id"));
        if (stub) {
            a.trim();
            return a;
        }
        a.setType(Alarm.Type.valueOf(result.getString("type")));
        a.setState(Alarm.State.valueOf(result.getString("state")));
        a.setSeverity(Alarm.Severity.valueOf(result.getString("severity")));
        a.setCreatedTs(result.getLong("created"));
        a.setAlarmedReference(new EntityReference(result.getString("entity")));
        a.setAlarmText(result.getString("description"));
        a.setClearedTs(result.getLong("cleared"));
        return a;
    }
}
