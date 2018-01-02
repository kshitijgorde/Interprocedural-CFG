// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ant;

import org.apache.tools.ant.BuildException;
import java.util.List;

public class Rake extends RakeTaskBase
{
    private String taskname;
    
    public void execute() throws BuildException {
        super.execute();
        final List args = this.handleFilenameArgument();
        if (this.taskname != null) {
            args.add(this.taskname);
        }
        this.rakeMethod("execute", args.toArray(new Object[args.size()]));
    }
    
    public void setTask(final String taskname) {
        this.taskname = taskname;
    }
}
