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

public class JavaGeneratorTask extends Task
{
    int version;
    File basedir;
    
    public JavaGeneratorTask() {
        this.version = 2;
        this.basedir = new File(".");
    }
    
    public static void main(final String[] args) {
        final Project project = new Project();
        project.init();
        final JavaGeneratorTask generator = new JavaGeneratorTask();
        generator.setProject(project);
        if (args.length > 0) {
            generator.version = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            generator.basedir = new File(args[1]);
        }
        generator.execute();
    }
    
    public void execute() throws BuildException {
        try {
            final String sourceDir = this.basedir + "/src/main/java";
            System.out.println("Parsing source files in: " + sourceDir);
            final JamServiceFactory jamServiceFactory = JamServiceFactory.getInstance();
            final JamServiceParams params = jamServiceFactory.createServiceParams();
            final File[] dirs = { new File(sourceDir) };
            params.includeSourcePattern(dirs, "**/*.java");
            final JamService jam = jamServiceFactory.createService(params);
            final JavaMarshallingGenerator script = new JavaMarshallingGenerator();
            script.setJam(jam);
            script.setTargetDir(this.basedir + "/src/main/java");
            script.setOpenwireVersion(this.version);
            script.run();
            final JavaTestsGenerator script2 = new JavaTestsGenerator();
            script2.setJam(jam);
            script2.setTargetDir(this.basedir + "/src/test/java");
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
    
    public File getBasedir() {
        return this.basedir;
    }
    
    public void setBasedir(final File basedir) {
        this.basedir = basedir;
    }
}
