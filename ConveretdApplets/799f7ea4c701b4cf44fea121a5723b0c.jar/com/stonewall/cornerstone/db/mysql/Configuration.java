// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db.mysql;

import java.util.regex.Pattern;
import java.io.Reader;
import java.net.URL;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.stonewall.cornerstone.utility.Stopwatch;
import com.stonewall.cornerstone.db.DbResultSet;
import com.stonewall.cornerstone.db.DbStatement;
import com.stonewall.cornerstone.db.DbSession;
import org.xmodel.log.Log;

public class Configuration
{
    static final String[][] schema;
    static final String insertStmt = "insert into $table select * from $src.$table";
    static final String showTablesStmt = "show tables in $src";
    static final String showCreateTableStmt = "show create table $src.$table";
    static final String disableFkStmt = "set foreign_key_checks = 0";
    static final String enableFkStmt = "set foreign_key_checks = 1";
    static final String disableKeysStmt = "alter table $0 disable keys";
    static final String enableKeysStmt = "alter table $0 enable keys";
    static final Log log;
    
    static {
        schema = new String[][] { { "main", "main.sql" }, { "latest", "latest.sql" }, { "quartz", "quartz.sql" }, { "(^\\s)*infer", "latest.sql" } };
        log = Log.getLog(Configuration.class);
    }
    
    public void validate() throws Exception {
        DbSession.DB[] values;
        for (int length = (values = DbSession.DB.values()).length, i = 0; i < length; ++i) {
            final DbSession.DB d = values[i];
            this.validate(d.name());
        }
    }
    
    public void validate(final String name) throws Exception {
        Configuration.log.info("validating ...");
        final Connection con = ConnectionFactory.getInstance().getConnection();
        try {
            boolean needSetup = true;
            final DbStatement stmt = con.createStatement("show databases like $name");
            stmt.setString("name", name);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final String s = result.getString();
                if (s.equals(name)) {
                    needSetup = false;
                    break;
                }
            }
            if (needSetup) {
                this.createDatabase(con, name);
                this.createTables(con, name);
            }
        }
        finally {
            con.close();
        }
        con.close();
        Configuration.log.info("validation - complete");
    }
    
    public void makeSnapshot(final String src, final String dst) throws Exception {
        this.cloneDatabase(src, dst, "InnoDB", "MyISAM");
        this.copyData(src, dst);
    }
    
    public void copyData(final String src, final String dst) throws Exception {
        final Connection con = ConnectionFactory.getInstance().getConnection();
        try {
            final Stopwatch stopwatch = new Stopwatch();
            con.setDatabase(dst);
            con.execute("set foreign_key_checks = 0", new String[0]);
            DbStatement stmt = con.createStatement("show tables in $src");
            stmt.set("src", src);
            final DbResultSet result = stmt.execute();
            while (result.next()) {
                final String table = result.getString();
                stopwatch.start();
                con.execute("alter table $0 disable keys", table);
                stmt = con.createStatement("insert into $table select * from $src.$table");
                stmt.set("table", table);
                stmt.set("src", src);
                stmt.set(DbStatement.Mode.Update);
                stmt.execute();
                stmt.close();
                con.execute("alter table $0 enable keys", table);
                Configuration.log.info("table: " + table + " copied " + stopwatch.stop());
            }
            con.execute("set foreign_key_checks = 1", new String[0]);
        }
        finally {
            con.close();
        }
        con.close();
    }
    
    public void cloneDatabase(final String src, final String dst, final String... eng) throws Exception {
        final Connection con = ConnectionFactory.getInstance().getConnection();
        final List<String> schema = new ArrayList<String>();
        try {
            this.createDatabase(con, dst);
            DbStatement stmt = con.createStatement("show tables in $src");
            stmt.set("src", src);
            final DbResultSet tables = stmt.execute();
            while (tables.next()) {
                final String table = tables.getString();
                stmt = con.createStatement("show create table $src.$table");
                stmt.set("src", src);
                stmt.set("table", table);
                final DbResultSet sql = stmt.execute();
                if (sql.next()) {
                    final String s = this.replace(sql.getString(2), eng);
                    schema.add(s);
                }
            }
            con.execute("set foreign_key_checks = 0", new String[0]);
            for (final String sql2 : schema) {
                stmt = con.createStatement(sql2);
                stmt.set(DbStatement.Mode.Update);
                stmt.execute();
            }
            con.execute("set foreign_key_checks = 1", new String[0]);
        }
        finally {
            con.close();
        }
        con.close();
    }
    
    public void createDatabase(final DbSession con, final String name) throws Exception {
        con.execute("create database $0", name);
        con.setDatabase(name);
    }
    
    public void createTables(final DbSession con, final String name) throws Exception {
        final String configuration = this.readConfiguration(this.schema(name));
        final StringTokenizer tk = new StringTokenizer(configuration, ";");
        while (tk.hasMoreTokens()) {
            final String s = tk.nextToken();
            final DbStatement stmt = con.createStatement(s);
            Configuration.log.info(s);
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            stmt.close();
        }
    }
    
    public void createProcedures(final DbSession con) throws Exception {
        final String configuration = this.readConfiguration("functions.sql");
        final StringTokenizer tk = new StringTokenizer(configuration, "/");
        while (tk.hasMoreTokens()) {
            final String s = tk.nextToken();
            if (s.contains("DELIMITER")) {
                continue;
            }
            final DbStatement stmt = con.createStatement(s);
            Configuration.log.info(s);
            stmt.set(DbStatement.Mode.Update);
            stmt.execute();
            stmt.close();
        }
    }
    
    public String readConfiguration(final String name) throws Exception {
        final char[] bfr = new char[1024];
        final StringBuilder sb = new StringBuilder();
        final URL resource = this.getClass().getResource(name);
        final Reader reader = new InputStreamReader(resource.openStream());
        while (true) {
            final int n = reader.read(bfr);
            if (n == -1) {
                break;
            }
            sb.append(bfr, 0, n);
        }
        return sb.toString();
    }
    
    private String replace(String s, final String... t) {
        for (int i = 0; i < t.length; s = s.replaceAll(t[i], t[++i]), ++i) {}
        return s;
    }
    
    public String schema(final String db) {
        String[][] schema;
        for (int length = (schema = Configuration.schema).length, i = 0; i < length; ++i) {
            final String[] a = schema[i];
            final Pattern p = Pattern.compile(a[0]);
            if (p.matcher(db).find()) {
                return a[1];
            }
        }
        return null;
    }
}
