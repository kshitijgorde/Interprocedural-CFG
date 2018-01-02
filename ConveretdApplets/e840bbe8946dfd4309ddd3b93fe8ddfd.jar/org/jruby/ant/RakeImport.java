// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ant;

import org.apache.tools.ant.BuildException;

public class RakeImport extends RakeTaskBase
{
    public void execute() throws BuildException {
        super.execute();
        this.rakeMethod("import", this.handleFilenameArgument());
    }
}
