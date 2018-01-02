// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ant;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.apache.tools.ant.BuildException;
import org.jruby.embed.ScriptingContainer;
import org.apache.tools.ant.Task;

public class RakeTaskBase extends Task
{
    private Object rakeWrapper;
    private ScriptingContainer container;
    protected String filename;
    
    public RakeTaskBase() {
        this.acquireRakeReference();
    }
    
    public void setFile(final String filename) {
        this.filename = filename;
    }
    
    public void execute() throws BuildException {
        this.container.put("$project", this.getProject());
    }
    
    protected void acquireRakeReference() {
        final ClassLoader prevClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
            System.setProperty("jruby.native.enabled", "false");
            (this.container = new ScriptingContainer()).setLoadPaths(Arrays.asList("lib"));
            this.container.runScriptlet("require 'ant/tasks/raketasks'");
            this.rakeWrapper = this.container.runScriptlet("RakeWrapper.new");
        }
        finally {
            Thread.currentThread().setContextClassLoader(prevClassLoader);
        }
    }
    
    protected List handleFilenameArgument() {
        final List args = new ArrayList();
        if (this.filename != null) {
            args.add("-f");
            args.add(this.filename);
        }
        return args;
    }
    
    public void rakeMethod(final String method, final Object... args) throws BuildException {
        try {
            this.container.callMethod(this.rakeWrapper, method, args);
        }
        catch (Exception e) {
            throw new BuildException("Build failed: " + e.getMessage(), (Throwable)e);
        }
    }
}
