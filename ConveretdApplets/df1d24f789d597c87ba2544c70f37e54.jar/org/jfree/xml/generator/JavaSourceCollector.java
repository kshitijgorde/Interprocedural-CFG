// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator;

import org.jfree.ui.ExtensionFileFilter;
import java.lang.reflect.Modifier;
import java.io.FileFilter;
import org.jfree.util.Log;
import java.io.File;
import java.util.ArrayList;

public class JavaSourceCollector implements SourceCollector
{
    private CollectorFileFilter eff;
    private ArrayList fileList;
    private ArrayList ignoredPackages;
    private ArrayList ignoredBaseClasses;
    private File startDirectory;
    private String initialPackageName;
    
    public JavaSourceCollector(final File file) {
        this(file, "");
    }
    
    public JavaSourceCollector(final File startDirectory, final String initialPackageName) {
        this.eff = new CollectorFileFilter("<ignore>", ".java");
        this.fileList = new ArrayList();
        this.startDirectory = startDirectory;
        this.initialPackageName = initialPackageName;
        this.ignoredPackages = new ArrayList();
        this.ignoredBaseClasses = new ArrayList();
    }
    
    public void addIgnoredPackage(final String s) {
        Log.debug(new Log.SimpleMessage("Added IgnPackage: ", s));
        this.ignoredPackages.add(s);
    }
    
    public void addIgnoredBaseClass(final String s) {
        final Class loadClass = this.loadClass(s);
        if (loadClass != null) {
            Log.debug(new Log.SimpleMessage("Added IgnClass: ", s));
            this.ignoredBaseClasses.add(loadClass);
        }
    }
    
    public void addIgnoredBaseClass(final Class clazz) {
        this.ignoredBaseClasses.add(clazz);
    }
    
    protected boolean isIgnoredPackage(final String s) {
        for (int i = 0; i < this.ignoredPackages.size(); ++i) {
            if (s.startsWith((String)this.ignoredPackages.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean isIgnoredBaseClass(final Class clazz) {
        for (int i = 0; i < this.ignoredBaseClasses.size(); ++i) {
            if (((Class)this.ignoredBaseClasses.get(i)).isAssignableFrom(clazz)) {
                return true;
            }
        }
        return false;
    }
    
    public void collectFiles() {
        this.collectFiles(this.startDirectory, this.initialPackageName);
    }
    
    protected void collectFiles(final File file, final String s) {
        final File[] listFiles = file.listFiles(this.eff);
        for (int i = 0; i < listFiles.length; ++i) {
            if (listFiles[i].isDirectory()) {
                this.collectFiles(listFiles[i], this.buildJavaName(s, listFiles[i].getName()));
            }
            else {
                final String name = listFiles[i].getName();
                final String substring = name.substring(0, name.length() - 5);
                final String buildJavaName = this.buildJavaName(s, substring);
                if (this.isIgnoredPackage(buildJavaName)) {
                    Log.debug(new Log.SimpleMessage("Do not process: Ignored: ", substring));
                }
                else {
                    final Class loadClass = this.loadClass(buildJavaName);
                    if (loadClass != null) {
                        if (!this.isIgnoredBaseClass(loadClass)) {
                            if (loadClass.isInterface() || Modifier.isAbstract(loadClass.getModifiers())) {
                                Log.debug(new Log.SimpleMessage("Do not process: Abstract: ", substring));
                            }
                            else if (!Modifier.isPublic(loadClass.getModifiers())) {
                                Log.debug(new Log.SimpleMessage("Do not process: Not public: ", substring));
                            }
                            else {
                                this.fileList.add(loadClass);
                            }
                        }
                    }
                }
            }
        }
    }
    
    protected Class loadClass(final String s) {
        try {
            return Class.forName(s);
        }
        catch (Exception ex) {
            Log.warn(new Log.SimpleMessage("Do not process: Failed to load class:", s));
            return null;
        }
    }
    
    protected String buildJavaName(final String s, final String s2) {
        if (s.length() == 0) {
            return s2;
        }
        return s + "." + s2;
    }
    
    public Class[] getClasses() {
        return this.fileList.toArray(new Class[0]);
    }
    
    private static class CollectorFileFilter extends ExtensionFileFilter implements FileFilter
    {
        public CollectorFileFilter(final String s, final String s2) {
            super(s, s2);
        }
    }
}
