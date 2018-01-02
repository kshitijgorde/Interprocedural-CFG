// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db.mysql;

import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import com.stonewall.cornerstone.db.DbSession;
import com.stonewall.cornerstone.component.Bootstrap;

public class CleanDatabase
{
    public static void main(final String[] args) {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            for (final String db : getDatabases()) {
                dropDatabase(db);
            }
            dropDatabase(DbSession.DB.latest.name());
            dropDatabase(DbSession.DB.main.name());
            dropDatabase(DbSession.DB.quartz.name());
        }
        catch (Exception e) {
            System.exit(0);
        }
    }
    
    private static List<String> getDatabases() throws Exception {
        final DbSession session = ConnectionFactory.getInstance().getConnection();
        final List<String> dbs = new ArrayList<String>();
        try {
            final DbStatement stmt = session.createStatement("show databases");
            final DbResultSet sql = stmt.execute();
            while (sql.next()) {
                final String s = sql.getString();
                if (s.startsWith("db")) {
                    dbs.add(s);
                }
            }
            stmt.close();
        }
        finally {
            session.close();
        }
        session.close();
        return dbs;
    }
    
    private static void dropDatabase(final String name) throws Exception {
        final DbSession session = ConnectionFactory.getInstance().getConnection();
        try {
            final DbStatement stmt = session.createStatement("drop database if exists $name");
            stmt.set("name", name);
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            stmt.close();
        }
        catch (Exception ex) {
            return;
        }
        finally {
            session.close();
        }
        session.close();
    }
}
