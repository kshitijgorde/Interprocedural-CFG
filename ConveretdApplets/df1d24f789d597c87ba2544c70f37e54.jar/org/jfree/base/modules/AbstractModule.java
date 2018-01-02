// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base.modules;

import java.io.IOException;
import java.util.ArrayList;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public abstract class AbstractModule extends DefaultModuleInfo implements Module
{
    private ModuleInfo[] requiredModules;
    private ModuleInfo[] optionalModules;
    private String name;
    private String description;
    private String producer;
    private String subsystem;
    
    public AbstractModule() {
        this.setModuleClass(this.getClass().getName());
    }
    
    protected void loadModuleInfo() throws ModuleInitializeException {
        final InputStream resourceAsStream = this.getClass().getResourceAsStream("module.properties");
        if (resourceAsStream == null) {
            throw new ModuleInitializeException("File 'module.properties' not found in module package.");
        }
        this.loadModuleInfo(resourceAsStream);
    }
    
    protected void loadModuleInfo(final InputStream inputStream) throws ModuleInitializeException {
        try {
            if (inputStream == null) {
                throw new NullPointerException("Given InputStream is null.");
            }
            final ReaderHelper readerHelper = new ReaderHelper(new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1")));
            final ArrayList list = new ArrayList<DefaultModuleInfo>();
            final ArrayList list2 = new ArrayList<DefaultModuleInfo>();
            while (readerHelper.hasNext()) {
                final String next = readerHelper.next();
                if (next.startsWith("module-info:")) {
                    this.readModuleInfo(readerHelper);
                }
                else if (next.startsWith("depends:")) {
                    list2.add(this.readExternalModule(readerHelper));
                }
                else {
                    if (!next.startsWith("optional:")) {
                        continue;
                    }
                    list.add(this.readExternalModule(readerHelper));
                }
            }
            readerHelper.close();
            this.optionalModules = list.toArray(new ModuleInfo[list.size()]);
            this.requiredModules = list2.toArray(new ModuleInfo[list2.size()]);
        }
        catch (IOException ex) {
            throw new ModuleInitializeException("Failed to load properties", ex);
        }
    }
    
    private String readValue(final ReaderHelper readerHelper, String next) throws IOException {
        final StringBuffer sb = new StringBuffer(next.trim());
        int n = 1;
        while (this.isNextLineValueLine(readerHelper)) {
            next = readerHelper.next();
            final String trim = next.trim();
            if (trim.length() == 0 && n == 0) {
                sb.append("\n");
                n = 1;
            }
            else {
                if (n == 0) {
                    sb.append(" ");
                }
                sb.append(this.parseValue(trim));
                n = 0;
            }
        }
        return sb.toString();
    }
    
    private boolean isNextLineValueLine(final ReaderHelper readerHelper) throws IOException {
        if (!readerHelper.hasNext()) {
            return false;
        }
        final String next = readerHelper.next();
        if (next == null) {
            return false;
        }
        if (this.parseKey(next) != null) {
            readerHelper.pushBack(next);
            return false;
        }
        readerHelper.pushBack(next);
        return true;
    }
    
    private void readModuleInfo(final ReaderHelper readerHelper) throws IOException {
        while (readerHelper.hasNext()) {
            final String next = readerHelper.next();
            if (!Character.isWhitespace(next.charAt(0))) {
                readerHelper.pushBack(next);
                return;
            }
            final String trim = next.trim();
            final String key = this.parseKey(trim);
            if (key == null) {
                continue;
            }
            final String value = this.readValue(readerHelper, this.parseValue(trim.trim()));
            if (key.equals("name")) {
                this.setName(value);
            }
            else if (key.equals("producer")) {
                this.setProducer(value);
            }
            else if (key.equals("description")) {
                this.setDescription(value);
            }
            else if (key.equals("subsystem")) {
                this.setSubSystem(value);
            }
            else if (key.equals("version.major")) {
                this.setMajorVersion(value);
            }
            else if (key.equals("version.minor")) {
                this.setMinorVersion(value);
            }
            else {
                if (!key.equals("version.patchlevel")) {
                    continue;
                }
                this.setPatchLevel(value);
            }
        }
    }
    
    private String parseKey(final String s) {
        final int index = s.indexOf(58);
        if (index == -1) {
            return null;
        }
        return s.substring(0, index);
    }
    
    private String parseValue(final String s) {
        final int index = s.indexOf(58);
        if (index == -1) {
            return s;
        }
        if (index + 1 == s.length()) {
            return "";
        }
        return s.substring(index + 1);
    }
    
    private DefaultModuleInfo readExternalModule(final ReaderHelper readerHelper) throws IOException {
        final DefaultModuleInfo defaultModuleInfo = new DefaultModuleInfo();
        while (readerHelper.hasNext()) {
            final String next = readerHelper.next();
            if (!Character.isWhitespace(next.charAt(0))) {
                readerHelper.pushBack(next);
                return defaultModuleInfo;
            }
            final String trim = next.trim();
            final String key = this.parseKey(trim);
            if (key == null) {
                continue;
            }
            final String value = this.readValue(readerHelper, this.parseValue(trim));
            if (key.equals("module")) {
                defaultModuleInfo.setModuleClass(value);
            }
            else if (key.equals("version.major")) {
                defaultModuleInfo.setMajorVersion(value);
            }
            else if (key.equals("version.minor")) {
                defaultModuleInfo.setMinorVersion(value);
            }
            else {
                if (!key.equals("version.patchlevel")) {
                    continue;
                }
                defaultModuleInfo.setPatchLevel(value);
            }
        }
        return defaultModuleInfo;
    }
    
    public String getName() {
        return this.name;
    }
    
    protected void setName(final String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    protected void setDescription(final String description) {
        this.description = description;
    }
    
    public String getProducer() {
        return this.producer;
    }
    
    protected void setProducer(final String producer) {
        this.producer = producer;
    }
    
    public ModuleInfo[] getRequiredModules() {
        final ModuleInfo[] array = new ModuleInfo[this.requiredModules.length];
        System.arraycopy(this.requiredModules, 0, array, 0, this.requiredModules.length);
        return array;
    }
    
    public ModuleInfo[] getOptionalModules() {
        final ModuleInfo[] array = new ModuleInfo[this.optionalModules.length];
        System.arraycopy(this.optionalModules, 0, array, 0, this.optionalModules.length);
        return array;
    }
    
    protected void setRequiredModules(final ModuleInfo[] array) {
        System.arraycopy(array, 0, this.requiredModules = new ModuleInfo[array.length], 0, array.length);
    }
    
    public void setOptionalModules(final ModuleInfo[] array) {
        System.arraycopy(array, 0, this.optionalModules = new ModuleInfo[array.length], 0, array.length);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Module : ");
        sb.append(this.getName());
        sb.append("\n");
        sb.append("ModuleClass : ");
        sb.append(this.getModuleClass());
        sb.append("\n");
        sb.append("Version: ");
        sb.append(this.getMajorVersion());
        sb.append(".");
        sb.append(this.getMinorVersion());
        sb.append(".");
        sb.append(this.getPatchLevel());
        sb.append("\n");
        sb.append("Producer: ");
        sb.append(this.getProducer());
        sb.append("\n");
        sb.append("Description: ");
        sb.append(this.getDescription());
        sb.append("\n");
        return sb.toString();
    }
    
    protected static boolean isClassLoadable(final String s) {
        try {
            Thread.currentThread().getContextClassLoader().loadClass(s);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public void configure(final SubSystem subSystem) {
        final InputStream resourceAsStream = this.getClass().getResourceAsStream("configuration.properties");
        if (resourceAsStream == null) {
            return;
        }
        subSystem.getPackageManager().getPackageConfiguration().load(resourceAsStream);
    }
    
    protected void performExternalInitialize(final String s) throws ModuleInitializeException {
        try {
            ((ModuleInitializer)Thread.currentThread().getContextClassLoader().loadClass(s).newInstance()).performInit();
        }
        catch (ModuleInitializeException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new ModuleInitializeException("Failed to load specified initializer class.", ex2);
        }
    }
    
    public String getSubSystem() {
        if (this.subsystem == null) {
            return this.getName();
        }
        return this.subsystem;
    }
    
    protected void setSubSystem(final String subsystem) {
        this.subsystem = subsystem;
    }
    
    private static class ReaderHelper
    {
        private String buffer;
        private final BufferedReader reader;
        
        public ReaderHelper(final BufferedReader reader) {
            this.reader = reader;
        }
        
        public boolean hasNext() throws IOException {
            if (this.buffer == null) {
                this.buffer = this.readLine();
            }
            return this.buffer != null;
        }
        
        public String next() {
            final String buffer = this.buffer;
            this.buffer = null;
            return buffer;
        }
        
        public void pushBack(final String buffer) {
            this.buffer = buffer;
        }
        
        protected String readLine() throws IOException {
            String s;
            for (s = this.reader.readLine(); s != null && (s.length() == 0 || s.startsWith("#")); s = this.reader.readLine()) {}
            return s;
        }
        
        public void close() throws IOException {
            this.reader.close();
        }
    }
}
