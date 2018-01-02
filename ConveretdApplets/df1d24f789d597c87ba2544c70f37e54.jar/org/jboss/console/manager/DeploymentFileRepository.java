// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager;

import org.jboss.system.server.ServerConfig;
import org.jboss.system.server.ServerConfigLocator;
import javax.management.ObjectName;
import javax.management.MBeanServer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import org.jboss.system.ServiceMBeanSupport;

public class DeploymentFileRepository extends ServiceMBeanSupport implements DeploymentFileRepositoryMBean
{
    private String baseDir;
    private File base;
    protected File serverHome;
    
    public void store(final String folder, final String name, final String fileExtension, final String data, final boolean noHotDeploy) throws IOException {
        this.log.debug("store called");
        final File dir = this.getFile(this.base, folder);
        this.log.debug("repository folder: " + dir.toString());
        this.log.debug("absolute: " + dir.getAbsolutePath());
        if (!dir.exists() && !dir.mkdirs()) {
            throw new RuntimeException("Failed to create directory: " + dir.toString());
        }
        final String filename = name.replace(' ', '_') + fileExtension;
        final File file = this.getFile(dir, filename);
        final File tmpfile = new File(dir, filename + ".tmp");
        final PrintWriter writer = new PrintWriter(new FileOutputStream(tmpfile));
        writer.write(data);
        writer.close();
        if (file.exists() && noHotDeploy) {
            final long modified = file.lastModified();
            tmpfile.setLastModified(modified);
            file.delete();
        }
        if (!tmpfile.renameTo(file)) {
            throw new RuntimeException("Failed to rename tmpfile to " + file.toString());
        }
    }
    
    public void remove(final String folder, final String name, final String fileExtension) throws IOException {
        final File dir = this.getFile(this.base, folder);
        final String filename = name.replace(' ', '_') + fileExtension;
        final File file = this.getFile(dir, filename);
        file.delete();
    }
    
    public boolean isStored(final String folder, final String name, final String fileExtension) throws IOException {
        final File dir = this.getFile(this.base, folder);
        final String filename = name.replace(' ', '_') + fileExtension;
        final File file = this.getFile(dir, filename);
        return file.exists();
    }
    
    public String getBaseDir() {
        return this.baseDir;
    }
    
    public void setBaseDir(final String baseDir) throws IOException {
        this.base = this.getFile(this.serverHome, baseDir);
        this.baseDir = baseDir;
        this.log.debug("BaseDir set to: " + this.base);
    }
    
    public ObjectName preRegister(final MBeanServer server, final ObjectName name) throws Exception {
        final ServerConfig serverConfig = ServerConfigLocator.locate();
        this.serverHome = serverConfig.getServerHomeDir();
        return super.preRegister(server, name);
    }
    
    private File getFile(final File parent, final String child) throws IOException {
        final File childFile = new File(parent, child);
        if (childFile.getCanonicalPath().indexOf(parent.getCanonicalPath()) != 0) {
            throw new IllegalArgumentException("child '" + child + "' should be a child of parent '" + parent + "'");
        }
        return childFile;
    }
}
