// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.StringTokenizer;
import logging.LogHolder;
import logging.LogType;
import java.util.zip.ZipFile;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.lang.reflect.Method;
import java.net.URL;
import java.io.IOException;
import java.io.File;
import java.util.Vector;
import java.util.Hashtable;

public final class ClassUtil
{
    private static final String JAR_FILE = "jar:file:";
    private static final String FILE = "file:";
    private static Hashtable ms_loadedClasses;
    private static Vector ms_loadedDirectories;
    private static boolean ms_bEnableFindSubclasses;
    static /* synthetic */ Class class$java$io$File;
    static /* synthetic */ Class class$java$lang$ClassLoader;
    static /* synthetic */ Class class$java$net$URL;
    static /* synthetic */ Class class$java$lang$reflect$Method;
    static /* synthetic */ Class class$anon$util$ClassUtil;
    
    public static void addFileToClasspath(final String s) throws IOException, IllegalAccessException {
        addFileToClasspath(new File(s));
    }
    
    public static void addFileToClasspath(final File file) throws IllegalAccessException {
        URL url;
        try {
            url = (URL)((ClassUtil.class$java$io$File == null) ? (ClassUtil.class$java$io$File = class$("java.io.File")) : ClassUtil.class$java$io$File).getMethod("toURL", (Class[])new Class[0]).invoke(file, new Object[0]);
        }
        catch (Exception ex) {
            throw new IllegalAccessException(ex.getMessage());
        }
        addURLToClasspath(url);
    }
    
    public static void addURLToClasspath(final URL url) throws IllegalAccessException {
        try {
            final Object invoke = ((ClassUtil.class$java$lang$ClassLoader == null) ? (ClassUtil.class$java$lang$ClassLoader = class$("java.lang.ClassLoader")) : ClassUtil.class$java$lang$ClassLoader).getMethod("getSystemClassLoader", (Class[])new Class[0]).invoke(null, new Object[0]);
            final Method declaredMethod = Class.forName("java.net.URLClassLoader").getDeclaredMethod("addURL", (ClassUtil.class$java$net$URL == null) ? (ClassUtil.class$java$net$URL = class$("java.net.URL")) : ClassUtil.class$java$net$URL);
            ((ClassUtil.class$java$lang$reflect$Method == null) ? (ClassUtil.class$java$lang$reflect$Method = class$("java.lang.reflect.Method")) : ClassUtil.class$java$lang$reflect$Method).getMethod("setAccessible", Boolean.TYPE).invoke(declaredMethod, new Boolean(true));
            declaredMethod.invoke(invoke, url);
        }
        catch (Throwable t) {
            throw new IllegalAccessException("Error, could not add URL to system classloader");
        }
    }
    
    public static String getShortClassName(final Class clazz) {
        String s = clazz.getName();
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex >= 0) {
            s = s.substring(lastIndex + 1, s.length());
        }
        return s;
    }
    
    public static Class getClassStatic() {
        return new ClassGetter().getCurrentClassStatic();
    }
    
    public static String getClassNameStatic() {
        return getCallingClassStatic().getName();
    }
    
    public static Class getCallingClassStatic() {
        return new ClassGetter().getCallingClassStatic();
    }
    
    public static String getUserDir() {
        try {
            return System.getProperty("user.dir");
        }
        catch (SecurityException ex) {
            return new File(".").toString();
        }
    }
    
    public static String getClassPath() {
        return getClassPath(false);
    }
    
    public static void enableFindSubclasses(final boolean ms_bEnableFindSubclasses) {
        ClassUtil.ms_bEnableFindSubclasses = ms_bEnableFindSubclasses;
    }
    
    public static boolean isFindSubclassesEnabled() {
        return ClassUtil.ms_bEnableFindSubclasses;
    }
    
    public static Vector findSubclasses(final Class clazz) {
        if (!ClassUtil.ms_bEnableFindSubclasses) {
            return new Vector();
        }
        loadClasses(clazz);
        final Enumeration loadClasses = loadClasses(getCallingClassStatic());
        final Vector<Class> vector = new Vector<Class>();
        while (loadClasses.hasMoreElements()) {
            final Class clazz2 = loadClasses.nextElement();
            if (clazz.isAssignableFrom(clazz2)) {
                vector.addElement(clazz2);
            }
        }
        return vector;
    }
    
    public static Enumeration loadClasses() {
        loadClasses(getCallingClassStatic());
        return ClassUtil.ms_loadedClasses.elements();
    }
    
    public static Enumeration loadClasses(final Class clazz) {
        return loadClasses(clazz, null);
    }
    
    public static Enumeration loadClasses(final File file) {
        return loadClasses(null, file);
    }
    
    private static Enumeration loadClasses(final Class clazz, final File file) {
        final PrintStream printStream = new PrintStream(new ByteArrayOutputStream());
        final Class classStatic = getClassStatic();
        final Class callingClassStatic = getCallingClassStatic();
        final PrintStream err = System.err;
        try {
            if (file != null) {
                System.setErr(printStream);
                loadClassesInternal(clazz, file);
                System.setErr(err);
            }
            else if (clazz != null) {
                System.setErr(printStream);
                loadClassesInternal(clazz, file);
                System.setErr(err);
                loadClassesInternal(classStatic, null);
                if (callingClassStatic != clazz && callingClassStatic != classStatic) {
                    loadClassesInternal(callingClassStatic, null);
                }
            }
        }
        catch (Throwable t) {
            System.setErr(err);
            if (t instanceof Exception && !(t instanceof RuntimeException)) {
                t.printStackTrace();
            }
        }
        return ClassUtil.ms_loadedClasses.elements();
    }
    
    public static File getClassDirectory(final String s) {
        if (s == null) {
            return null;
        }
        try {
            return getClassDirectory(Class.forName(s));
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public static ZipFile getJarFile() {
        final File classDirectory = getClassDirectory((ClassUtil.class$anon$util$ClassUtil == null) ? (ClassUtil.class$anon$util$ClassUtil = class$("anon.util.ClassUtil")) : ClassUtil.class$anon$util$ClassUtil);
        if (classDirectory != null && classDirectory.getPath().endsWith(".jar")) {
            try {
                return new ZipFile(classDirectory);
            }
            catch (IOException ex) {
                LogHolder.log(3, LogType.MISC, "An I/O error occured while opening the JAR file: ", ex);
            }
        }
        return null;
    }
    
    public static File getClassDirectory(final Class clazz) {
        final String string = "/" + toRelativeResourcePath(clazz);
        final URL resource = clazz.getResource(string);
        File systemResource;
        if (resource != null) {
            systemResource = ResourceLoader.getSystemResource(resource.toString());
        }
        else {
            LogHolder.log(3, LogType.THREAD, "Get class resource failed!");
            systemResource = null;
        }
        if (systemResource == null) {
            final String decode = URLDecoder.decode(resource.toString());
            if (decode.startsWith("jar:file:")) {
                String s = decode.substring("jar:file:".length(), decode.lastIndexOf(string) - 1);
                if (s.charAt(2) == ':') {
                    s = s.substring(1, s.length());
                }
                systemResource = new File(ResourceLoader.replaceFileSeparatorsSystemSpecific(s));
            }
            else if (decode.startsWith("file:")) {
                systemResource = new File(decode.substring("file:".length(), decode.lastIndexOf(string)));
            }
            else {
                systemResource = null;
            }
            if (systemResource == null || !systemResource.exists()) {
                return null;
            }
        }
        return systemResource;
    }
    
    public static String toRelativeResourcePath(final Class clazz) {
        return clazz.getName().replace('.', '/') + ".class";
    }
    
    public static Class getFirstClassFound(final File file) {
        final Hashtable<Object, Class> hashtable = new Hashtable<Object, Class>();
        ResourceLoader.loadResources("/", file, new ClassInstantiator(3), true, true, hashtable);
        if (hashtable.size() == 1) {
            return hashtable.elements().nextElement();
        }
        return null;
    }
    
    protected static String getClassPath(final boolean b) {
        String string = "";
        if (!b) {
            try {
                string = getClassDirectory((ClassUtil.class$anon$util$ClassUtil == null) ? (ClassUtil.class$anon$util$ClassUtil = class$("anon.util.ClassUtil")) : ClassUtil.class$anon$util$ClassUtil).toString() + File.pathSeparator;
            }
            catch (Exception ex) {}
        }
        try {
            return string + System.getProperty("java.class.path");
        }
        catch (SecurityException ex2) {
            return string;
        }
    }
    
    private static void loadClassesInternal(final Class clazz, final File file) throws IOException {
        File classDirectory;
        if (file != null) {
            classDirectory = file;
        }
        else if (clazz == null || clazz.getName().startsWith("java.") || clazz.getName().startsWith("javax.") || (classDirectory = getClassDirectory(clazz)) == null) {
            return;
        }
        if (ClassUtil.ms_loadedDirectories.contains(classDirectory.getAbsolutePath())) {
            return;
        }
        ClassUtil.ms_loadedDirectories.addElement(classDirectory.getAbsolutePath());
        ResourceLoader.loadResources("/", classDirectory, new ClassInstantiator(), true, false, ClassUtil.ms_loadedClasses);
    }
    
    private static Class toClass(final File file, final File file2) {
        if (file == null || !file.getName().endsWith(".class")) {
            return null;
        }
        int length;
        if (file2 == null || !file2.isDirectory()) {
            length = 0;
        }
        else {
            final String string = file2.toString();
            if (string.endsWith(System.getProperty("file.separator"))) {
                length = string.length();
            }
            else {
                length = string.length() + 1;
            }
        }
        Class<?> forName;
        try {
            final String string2 = file.toString();
            forName = Class.forName(string2.substring(length, string2.lastIndexOf(".class")).replace(File.separatorChar, '.'));
        }
        catch (Throwable t) {
            forName = null;
        }
        return forName;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ClassUtil.ms_loadedClasses = new Hashtable();
        ClassUtil.ms_loadedDirectories = new Vector();
        ClassUtil.ms_bEnableFindSubclasses = true;
    }
    
    public static final class Package
    {
        private String m_strPackage;
        
        public Package(final Class clazz) {
            if (clazz == null || clazz.getName().indexOf(".") < 0) {
                this.m_strPackage = "";
            }
            else {
                this.m_strPackage = clazz.getName().substring(0, clazz.getName().lastIndexOf("."));
            }
        }
        
        public Package(final String strPackage) {
            if (this.m_strPackage == null || this.m_strPackage.trim().length() == 0) {
                this.m_strPackage = "";
            }
            else {
                if (new StringTokenizer(this.m_strPackage).countTokens() > 1) {
                    throw new IllegalArgumentException("Package names may not contain whitespaces!");
                }
                for (int i = 0; i < this.m_strPackage.length(); ++i) {
                    if (!Character.isLetterOrDigit(this.m_strPackage.charAt(i))) {
                        if (this.m_strPackage.charAt(i) != '.') {
                            if (this.m_strPackage.charAt(i) == '\\' && this.m_strPackage.length() > i + 5 && this.m_strPackage.charAt(i + 1) == 'u') {
                                boolean b = true;
                                for (int j = i + 2; j < i + 5; ++j) {
                                    if (!Character.isDigit(this.m_strPackage.charAt(j))) {
                                        b = false;
                                        break;
                                    }
                                }
                                if (b) {
                                    i += 5;
                                    continue;
                                }
                            }
                            throw new IllegalArgumentException("Illegal character in package name: " + this.m_strPackage.charAt(i));
                        }
                    }
                }
                this.m_strPackage = strPackage;
            }
        }
        
        public String getPackage() {
            return this.m_strPackage;
        }
    }
    
    private static class ClassGetter extends SecurityManager
    {
        public Class getCurrentClassStatic() {
            return this.getClassContext()[2];
        }
        
        public Class getCallingClassStatic() {
            return this.getClassContext()[3];
        }
    }
    
    private static class ClassInstantiator implements IResourceInstantiator
    {
        private int m_invalidAfterFailure;
        private int m_currentFailure;
        
        public ClassInstantiator() {
            this.m_invalidAfterFailure = 0;
            this.m_currentFailure = 0;
        }
        
        public ClassInstantiator(final int invalidAfterFailure) {
            this.m_invalidAfterFailure = invalidAfterFailure;
            this.m_currentFailure = 0;
        }
        
        public Object getInstance(final File file, final File file2) throws ResourceInstantiationException {
            final Class access$100 = toClass(file, file2);
            if (this.m_invalidAfterFailure > 0) {
                this.checkValidity(access$100, file.getName());
            }
            return access$100;
        }
        
        public Object getInstance(final ZipEntry zipEntry, final ZipFile zipFile) throws ResourceInstantiationException {
            final Class access$100 = toClass(new File(zipEntry.toString()), null);
            if (this.m_invalidAfterFailure > 0) {
                this.checkValidity(access$100, zipEntry.getName());
            }
            return access$100;
        }
        
        private void checkValidity(final Class clazz, final String s) throws ResourceInstantiationException {
            if (clazz == null && s.endsWith(".class")) {
                ++this.m_currentFailure;
            }
            if (this.m_currentFailure >= this.m_invalidAfterFailure) {
                throw new ResourceInstantiationException();
            }
        }
        
        public Object getInstance(final InputStream inputStream, final String s) {
            return null;
        }
    }
}
