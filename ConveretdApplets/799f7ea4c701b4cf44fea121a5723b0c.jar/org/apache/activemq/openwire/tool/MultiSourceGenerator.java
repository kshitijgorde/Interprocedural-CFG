// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.openwire.tool;

import org.apache.tools.ant.taskdefs.FixCRLF;
import org.apache.tools.ant.Project;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import org.codehaus.jam.JProperty;
import java.util.List;
import org.codehaus.jam.JamClassIterator;
import java.util.HashSet;
import org.codehaus.jam.JClass;
import java.io.File;
import java.util.Set;

public abstract class MultiSourceGenerator extends OpenWireGenerator
{
    protected Set<String> manuallyMaintainedClasses;
    protected File destDir;
    protected File destFile;
    protected JClass jclass;
    protected JClass superclass;
    protected String simpleName;
    protected String className;
    protected String baseClass;
    protected StringBuffer buffer;
    
    public MultiSourceGenerator() {
        this.manuallyMaintainedClasses = new HashSet<String>();
        this.initialiseManuallyMaintainedClasses();
    }
    
    public Object run() {
        if (this.destDir == null) {
            throw new IllegalArgumentException("No destDir defined!");
        }
        System.out.println(this.getClass().getName() + " generating files in: " + this.destDir);
        this.destDir.mkdirs();
        this.buffer = new StringBuffer();
        final JamClassIterator iter = this.getClasses();
        while (iter.hasNext()) {
            try {
                this.jclass = iter.nextClass();
                if (!this.isValidClass(this.jclass)) {
                    continue;
                }
                this.processClass(this.jclass);
            }
            catch (Exception e) {
                System.err.println("Unable to process: " + this.jclass);
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public List<JProperty> getProperties() {
        final List<JProperty> answer = new ArrayList<JProperty>();
        final JProperty[] properties = this.jclass.getDeclaredProperties();
        for (int i = 0; i < properties.length; ++i) {
            final JProperty property = properties[i];
            if (this.isValidProperty(property)) {
                answer.add(property);
            }
        }
        return answer;
    }
    
    protected boolean isValidClass(final JClass jclass) {
        return jclass.getAnnotation("openwire:marshaller") != null && !this.manuallyMaintainedClasses.contains(jclass.getSimpleName());
    }
    
    protected void processClass(final JClass jclass) {
        this.simpleName = jclass.getSimpleName();
        this.superclass = jclass.getSuperclass();
        System.out.println(this.getClass().getName() + " processing class: " + this.simpleName);
        this.className = this.getClassName(jclass);
        this.destFile = new File(this.destDir, this.className + this.filePostFix);
        this.baseClass = this.getBaseClassName(jclass);
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(this.destFile));
            this.generateFile(out);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
        final Project project = new Project();
        project.init();
        final FixCRLF fixCRLF = new FixCRLF();
        fixCRLF.setProject(project);
        fixCRLF.setSrcdir(this.destFile.getParentFile());
        fixCRLF.setIncludes(this.destFile.getName());
        fixCRLF.execute();
    }
    
    protected abstract void generateFile(final PrintWriter p0) throws Exception;
    
    protected String getBaseClassName(final JClass jclass) {
        String answer = "BaseDataStructure";
        if (this.superclass != null) {
            final String name = this.superclass.getSimpleName();
            if (name != null && !name.equals("Object")) {
                answer = name;
            }
        }
        return answer;
    }
    
    protected String getClassName(final JClass jclass) {
        return jclass.getSimpleName();
    }
    
    public boolean isAbstractClass() {
        return this.jclass != null && this.jclass.isAbstract();
    }
    
    public String getAbstractClassText() {
        return this.isAbstractClass() ? "abstract " : "";
    }
    
    public boolean isMarshallerAware() {
        return this.isMarshallAware(this.jclass);
    }
    
    protected void initialiseManuallyMaintainedClasses() {
        final String[] names = { "ActiveMQDestination", "ActiveMQTempDestination", "ActiveMQQueue", "ActiveMQTopic", "ActiveMQTempQueue", "ActiveMQTempTopic", "BaseCommand", "ActiveMQMessage", "ActiveMQTextMessage", "ActiveMQMapMessage", "ActiveMQBytesMessage", "ActiveMQStreamMessage", "ActiveMQBlobMessage", "DataStructureSupport", "WireFormatInfo", "ActiveMQObjectMessage" };
        for (int i = 0; i < names.length; ++i) {
            this.manuallyMaintainedClasses.add(names[i]);
        }
    }
    
    public String getBaseClass() {
        return this.baseClass;
    }
    
    public void setBaseClass(final String baseClass) {
        this.baseClass = baseClass;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public void setClassName(final String className) {
        this.className = className;
    }
    
    public File getDestDir() {
        return this.destDir;
    }
    
    public void setDestDir(final File destDir) {
        this.destDir = destDir;
    }
    
    public File getDestFile() {
        return this.destFile;
    }
    
    public void setDestFile(final File destFile) {
        this.destFile = destFile;
    }
    
    public JClass getJclass() {
        return this.jclass;
    }
    
    public void setJclass(final JClass jclass) {
        this.jclass = jclass;
    }
    
    public Set<String> getManuallyMaintainedClasses() {
        return this.manuallyMaintainedClasses;
    }
    
    public void setManuallyMaintainedClasses(final Set<String> manuallyMaintainedClasses) {
        this.manuallyMaintainedClasses = manuallyMaintainedClasses;
    }
    
    public String getSimpleName() {
        return this.simpleName;
    }
    
    public void setSimpleName(final String simpleName) {
        this.simpleName = simpleName;
    }
    
    public JClass getSuperclass() {
        return this.superclass;
    }
    
    public void setSuperclass(final JClass superclass) {
        this.superclass = superclass;
    }
}
