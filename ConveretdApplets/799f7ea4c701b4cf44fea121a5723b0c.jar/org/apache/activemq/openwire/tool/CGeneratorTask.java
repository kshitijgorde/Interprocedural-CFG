// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

import org.codehaus.jam.JamService;
import org.codehaus.jam.JamServiceParams;
import org.apache.tools.ant.BuildException;
import org.codehaus.jam.JamServiceFactory;
import org.apache.tools.ant.Project;
import java.io.File;
import org.apache.tools.ant.Task;

public class CGeneratorTask extends Task
{
    int version;
    File source;
    File target;
    
    public CGeneratorTask() {
        this.version = 2;
        this.source = new File(".");
        this.target = new File(".");
    }
    
    public static void main(final String[] args) {
        final Project project = new Project();
        project.init();
        final CGeneratorTask generator = new CGeneratorTask();
        generator.setProject(project);
        if (args.length > 0) {
            generator.version = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            generator.source = new File(args[1]);
        }
        if (args.length > 2) {
            generator.target = new File(args[2]);
        }
        generator.execute();
    }
    
    public void execute() throws BuildException {
        try {
            final String sourceDir = this.source + "/src/main/java";
            System.out.println("Parsing source files in: " + sourceDir);
            final JamServiceFactory jamServiceFactory = JamServiceFactory.getInstance();
            final JamServiceParams params = jamServiceFactory.createServiceParams();
            final File[] dirs = { new File(sourceDir) };
            params.includeSourcePattern(dirs, "**/*.java");
            final JamService jam = jamServiceFactory.createService(params);
            final CHeadersGenerator script = new CHeadersGenerator();
            script.setJam(jam);
            script.setTargetDir(this.target + "/src/libopenwire");
            script.setOpenwireVersion(this.version);
            script.run();
            final CSourcesGenerator script2 = new CSourcesGenerator();
            script2.setJam(jam);
            script2.setTargetDir(this.target + "/src/libopenwire");
            script2.setOpenwireVersion(this.version);
            script2.run();
        }
        catch (Exception e) {
            throw new BuildException((Throwable)e);
        }
    }
    
    public int getVersion() {
        return this.version;
    }
    
    public void setVersion(final int version) {
        this.version = version;
    }
    
    public File getSource() {
        return this.source;
    }
    
    public void setSource(final File basedir) {
        this.source = basedir;
    }
    
    public File getTarget() {
        return this.target;
    }
    
    public void setTarget(final File target) {
        this.target = target;
    }
}
