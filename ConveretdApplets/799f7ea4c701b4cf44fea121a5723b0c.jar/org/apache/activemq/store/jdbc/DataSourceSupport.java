// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc;

import org.apache.derby.jdbc.EmbeddedDataSource;
import java.io.IOException;
import org.apache.activemq.util.IOHelper;
import javax.sql.DataSource;
import java.io.File;

public class DataSourceSupport
{
    private String dataDirectory;
    private File dataDirectoryFile;
    private DataSource dataSource;
    
    public DataSourceSupport() {
        this.dataDirectory = IOHelper.getDefaultDataDirectory();
    }
    
    public DataSourceSupport(final DataSource dataSource) {
        this.dataDirectory = IOHelper.getDefaultDataDirectory();
        this.dataSource = dataSource;
    }
    
    public File getDataDirectoryFile() {
        if (this.dataDirectoryFile == null) {
            this.dataDirectoryFile = new File(this.getDataDirectory());
        }
        return this.dataDirectoryFile;
    }
    
    public void setDataDirectoryFile(final File dataDirectory) {
        this.dataDirectoryFile = dataDirectory;
    }
    
    public String getDataDirectory() {
        return this.dataDirectory;
    }
    
    public void setDataDirectory(final String dataDirectory) {
        this.dataDirectory = dataDirectory;
    }
    
    public DataSource getDataSource() throws IOException {
        if (this.dataSource == null) {
            this.dataSource = this.createDataSource();
            if (this.dataSource == null) {
                throw new IllegalArgumentException("No dataSource property has been configured");
            }
        }
        return this.dataSource;
    }
    
    public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    protected DataSource createDataSource() throws IOException {
        System.setProperty("derby.system.home", this.getDataDirectoryFile().getCanonicalPath());
        System.setProperty("derby.storage.fileSyncTransactionLog", "true");
        System.setProperty("derby.storage.pageCacheSize", "100");
        final EmbeddedDataSource ds = new EmbeddedDataSource();
        ds.setDatabaseName("derbydb");
        ds.setCreateDatabase("create");
        return (DataSource)ds;
    }
    
    @Override
    public String toString() {
        return "" + this.dataSource;
    }
}
