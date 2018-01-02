// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.Enumeration;
import java.util.Hashtable;
import logging.LogHolder;
import logging.LogType;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.Vector;

public final class ResourceLoader
{
    public static final String SYSTEM_RESOURCE_TYPE_ZIP = "zip";
    public static final String SYSTEM_RESOURCE_TYPE_JAR = "jar";
    public static final String SYSTEM_RESOURCE_TYPE_FILE = "file";
    public static final String SYSTEM_RESOURCE_TYPE_GENERIC = "systemresource";
    private static final String SYSTEM_RESOURCE = "systemresource:/";
    private static final String SYSTEM_RESOURCE_ENDSIGN = "/+/";
    private static final String DIR_UP = "../";
    private static final String DIR_CURRENT = "./";
    private static final int READ_BUFFER = 2000;
    private static final String RESOURCE_NO_CLASSES_FOUND = "";
    private static Vector ms_classpathFiles;
    private static Object ms_classpathResourceLock;
    private static Vector ms_classpathResourceTypes;
    private static File ms_parentResourceFile;
    private static boolean ms_bTriedToLoadParentResourceFile;
    private static final Object SYNC_RESOURCE;
    private static String ms_parentResourceFileResourceURL;
    private static String ms_parentResourceFileResourceType;
    private static String ms_classpath;
    static /* synthetic */ Class class$anon$util$ResourceLoader;
    
    private ResourceLoader() {
        try {
            ResourceLoader.ms_parentResourceFile = new File(ClassUtil.getClassDirectory((ResourceLoader.class$anon$util$ResourceLoader == null) ? (ResourceLoader.class$anon$util$ResourceLoader = class$("anon.util.ResourceLoader")) : ResourceLoader.class$anon$util$ResourceLoader).getAbsolutePath());
        }
        catch (Exception ex) {
            ResourceLoader.ms_parentResourceFile = null;
        }
    }
    
    public static Vector getFilesInClassPath() {
        try {
            return (Vector)ResourceLoader.ms_classpathFiles.clone();
        }
        catch (NullPointerException ex) {
            return null;
        }
    }
    
    public static byte[] getStreamAsBytes(final InputStream inputStream) throws IOException {
        int i = 1;
        if (inputStream == null) {
            throw new IOException("Stream is null!");
        }
        byte[] trimByteArray;
        byte[] array;
        for (trimByteArray = new byte[0]; i >= 0; i = inputStream.read(array), trimByteArray = trimByteArray(array, i, trimByteArray)) {
            if (inputStream.available() > 0) {
                array = new byte[inputStream.available()];
            }
            else {
                array = new byte[2000];
            }
        }
        inputStream.close();
        return trimByteArray;
    }
    
    public static URL getResourceURL(String formatResourcePath) {
        if ((formatResourcePath = formatResourcePath(formatResourcePath)) == null || formatResourcePath.endsWith("/")) {
            return null;
        }
        URL url = ((ResourceLoader.class$anon$util$ResourceLoader == null) ? (ResourceLoader.class$anon$util$ResourceLoader = class$("anon.util.ResourceLoader")) : ResourceLoader.class$anon$util$ResourceLoader).getResource("/" + formatResourcePath);
        if (url == null) {
            final File file = new File(formatResourcePath);
            if (file.exists() && file.canRead()) {
                try {
                    url = new URL("file:" + file.getAbsolutePath());
                }
                catch (MalformedURLException ex) {}
            }
        }
        if (url == null && getParentResourceFile() != null && !readFilesFromClasspath(false).contains(getParentResourceFile())) {
            final Vector<File> vector = new Vector<File>();
            final Vector<String> vector2 = new Vector<String>();
            final Vector<String> vector3 = new Vector<String>();
            vector.addElement(getParentResourceFile());
            vector2.addElement(ResourceLoader.ms_parentResourceFileResourceURL);
            vector3.addElement(ResourceLoader.ms_parentResourceFileResourceType);
            url = getResourceURL(formatResourcePath, vector, vector2, vector3);
            ResourceLoader.ms_parentResourceFileResourceURL = vector2.firstElement();
            ResourceLoader.ms_parentResourceFileResourceType = vector3.firstElement();
        }
        return url;
    }
    
    public static InputStream loadResourceAsStream(final String s) {
        return loadResourceAsStream(s, false);
    }
    
    public static InputStream loadResourceAsStream(String formatResourcePath, final boolean b) {
        InputStream resourceAsStream = null;
        if ((formatResourcePath = formatResourcePath(formatResourcePath)) == null || formatResourcePath.endsWith("/")) {
            return null;
        }
        if (b && getParentResourceFile() != null) {
            try {
                File parentResourceFile = getParentResourceFile();
                if (parentResourceFile.isFile()) {
                    final String parent = parentResourceFile.getParent();
                    if (parent != null) {
                        parentResourceFile = new File(parent);
                    }
                }
                resourceAsStream = new FileInputStream(new File(parentResourceFile, replaceFileSeparatorsSystemSpecific(formatResourcePath)));
            }
            catch (IOException ex) {}
        }
        try {
            if (resourceAsStream == null) {
                resourceAsStream = ((ResourceLoader.class$anon$util$ResourceLoader == null) ? (ResourceLoader.class$anon$util$ResourceLoader = class$("anon.util.ResourceLoader")) : ResourceLoader.class$anon$util$ResourceLoader).getResourceAsStream("/" + formatResourcePath);
            }
        }
        catch (Throwable t) {
            LogHolder.log(3, LogType.MISC, t);
        }
        try {
            if (resourceAsStream == null && !b && getParentResourceFile() != null && !readFilesFromClasspath(false).contains(getParentResourceFile())) {
                File parentResourceFile2 = getParentResourceFile();
                if (parentResourceFile2.isFile()) {
                    final String parent2 = parentResourceFile2.getParent();
                    if (parent2 != null) {
                        parentResourceFile2 = new File(parent2);
                    }
                }
                resourceAsStream = new FileInputStream(new File(parentResourceFile2, replaceFileSeparatorsSystemSpecific(formatResourcePath)));
            }
        }
        catch (IOException ex2) {}
        try {
            if (resourceAsStream == null) {
                resourceAsStream = new FileInputStream(formatResourcePath);
            }
            return resourceAsStream;
        }
        catch (IOException ex3) {
            return null;
        }
    }
    
    public static byte[] loadResource(final String s) {
        final InputStream loadResourceAsStream = loadResourceAsStream(s);
        byte[] streamAsBytes = null;
        if (loadResourceAsStream == null) {
            return null;
        }
        try {
            streamAsBytes = getStreamAsBytes(loadResourceAsStream);
        }
        catch (IOException ex) {}
        Util.closeStream(loadResourceAsStream);
        return streamAsBytes;
    }
    
    public static Hashtable loadResources(final String s, final boolean b) {
        return loadResources(s, new ResourceLoader().createByteArrayInstantiator(), b);
    }
    
    public static Hashtable loadResources(final String s, final IResourceInstantiator resourceInstantiator, final boolean b) {
        final Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
        Object instance = null;
        InputStream loadResourceAsStream = null;
        try {
            loadResourceAsStream = loadResourceAsStream(s);
            instance = resourceInstantiator.getInstance(loadResourceAsStream, s);
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, ex);
        }
        Util.closeStream(loadResourceAsStream);
        if (instance != null) {
            hashtable.put(s, instance);
            return hashtable;
        }
        final Enumeration<File> elements = (Enumeration<File>)readFilesFromClasspath(false).elements();
        while (elements.hasMoreElements()) {
            loadResources(s, elements.nextElement(), resourceInstantiator, b, false, hashtable);
        }
        loadResources(s, new File(ClassUtil.getUserDir()), resourceInstantiator, b, false, hashtable);
        return hashtable;
    }
    
    public static Hashtable loadResources(final String s, final File file, final boolean b) {
        final Hashtable hashtable = new Hashtable();
        loadResources(s, file, new ResourceLoader().createByteArrayInstantiator(), b, false, hashtable);
        return hashtable;
    }
    
    public static Hashtable loadResources(final String s, final File file, final IResourceInstantiator resourceInstantiator, final boolean b) {
        final Hashtable hashtable = new Hashtable();
        loadResources(s, file, resourceInstantiator, b, false, hashtable);
        return hashtable;
    }
    
    public static String replaceFileSeparatorsSystemSpecific(String s) {
        if (s == null) {
            return null;
        }
        s = s.replace('/', File.separatorChar);
        s = s.replace('\\', File.separatorChar);
        return s;
    }
    
    protected static File getSystemResource(String s) {
        if (s.indexOf("systemresource:/") != 0) {
            return null;
        }
        s = s.substring("systemresource:/".length(), s.length());
        if (s.toLowerCase().startsWith("zip")) {
            s = s.substring("zip".length(), s.length());
        }
        else if (s.toLowerCase().startsWith("jar")) {
            s = s.substring("jar".length(), s.length());
        }
        else if (s.toLowerCase().startsWith("file")) {
            s = s.substring("file".length(), s.length());
        }
        final int index = s.indexOf("/+/");
        if (index >= 0) {
            s = s.substring(0, index);
        }
        try {
            return readFilesFromClasspath(true).elementAt(Integer.parseInt(s));
        }
        catch (Exception ex) {
            return new File(s);
        }
    }
    
    protected static void loadResources(String formatResourcePath, final File file, final IResourceInstantiator resourceInstantiator, final boolean b, final boolean b2, final Hashtable hashtable) {
        if ((formatResourcePath = formatResourcePath(formatResourcePath)) == null || hashtable == null || file == null || resourceInstantiator == null || !file.exists() || !file.canRead()) {
            return;
        }
        try {
            if (file.isDirectory()) {
                throw new IOException("This is a directory.");
            }
            final ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> enumeration;
            if (!formatResourcePath.endsWith("/")) {
                final ZipEntry entry = zipFile.getEntry(formatResourcePath);
                if (entry == null) {
                    throw new IOException("Requested entry not found.");
                }
                final Vector<ZipEntry> vector = new Vector<ZipEntry>();
                vector.addElement(entry);
                enumeration = vector.elements();
            }
            else {
                enumeration = zipFile.entries();
            }
            while (enumeration.hasMoreElements()) {
                final ZipEntry zipEntry = (ZipEntry)enumeration.nextElement();
                if (!zipEntry.isDirectory()) {
                    if (!isResourceInSearchPath(zipEntry.toString(), formatResourcePath, b)) {
                        continue;
                    }
                    Object instance = null;
                    try {
                        instance = resourceInstantiator.getInstance(zipEntry, zipFile);
                    }
                    catch (IResourceInstantiator.ResourceInstantiationException ex) {
                        return;
                    }
                    catch (Exception ex2) {}
                    if (instance == null) {
                        continue;
                    }
                    final String currentResourcePath = getCurrentResourcePath(zipEntry);
                    if (hashtable.containsKey(currentResourcePath)) {
                        continue;
                    }
                    hashtable.put(currentResourcePath, instance);
                    if (!formatResourcePath.endsWith("/") || b2) {
                        return;
                    }
                    continue;
                }
            }
        }
        catch (Exception ex3) {
            try {
                loadResourcesFromFile(formatResourcePath, file, file, resourceInstantiator, hashtable, b, b2);
            }
            catch (IResourceInstantiator.ResourceInstantiationException ex4) {}
        }
    }
    
    private static void loadResourcesFromFile(final String s, File file, final File file2, final IResourceInstantiator resourceInstantiator, final Hashtable hashtable, final boolean b, final boolean b2) throws IResourceInstantiator.ResourceInstantiationException {
        if ((!s.endsWith("/") || b2) && hashtable.size() > 0) {
            return;
        }
        if (file != null && file.exists()) {
            final String currentResourcePath = getCurrentResourcePath(file, file2);
            if (currentResourcePath.indexOf(s) != 0 && !s.equals("/")) {
                file = new File(file2, replaceFileSeparatorsSystemSpecific(s));
                loadResourcesFromFile(s, file, file2, resourceInstantiator, hashtable, b, b2);
                return;
            }
            if (file.isFile() && isResourceInSearchPath(currentResourcePath, s, b)) {
                Object instance = null;
                if (hashtable.containsKey(currentResourcePath)) {
                    return;
                }
                try {
                    instance = resourceInstantiator.getInstance(file, file2);
                }
                catch (IResourceInstantiator.ResourceInstantiationException ex) {
                    throw ex;
                }
                catch (Exception ex2) {}
                if (instance != null) {
                    hashtable.put(currentResourcePath, instance);
                    if (!s.endsWith("/") || b2) {
                        return;
                    }
                }
            }
            else if (file.isDirectory() && isResourceInSearchPath(currentResourcePath, s, b)) {
                final String[] list = file.list();
                for (int i = 0; i < list.length; ++i) {
                    String string = "" + File.separatorChar;
                    if (file.getAbsolutePath().endsWith(string)) {
                        string = "";
                    }
                    loadResourcesFromFile(s, new File(file.getAbsolutePath() + string + list[i]), file2, resourceInstantiator, hashtable, b, b2);
                }
            }
        }
    }
    
    private static URL getResourceURL(String string, final Vector vector, final Vector vector2, final Vector vector3) {
        final Enumeration<File> elements = vector.elements();
        final FileTypeInstantiator fileTypeInstantiator = new ResourceLoader().createFileTypeInstantiator();
        int n = 0;
        while (elements.hasMoreElements()) {
            final File file = elements.nextElement();
            String substring = vector2.elementAt(n);
            Label_0331: {
                if (substring == null) {
                    final Class firstClassFound = ClassUtil.getFirstClassFound(file);
                    if (firstClassFound == null) {
                        vector2.setElementAt("", n);
                        break Label_0331;
                    }
                    final String relativeResourcePath = ClassUtil.toRelativeResourcePath(firstClassFound);
                    final Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
                    loadResources(relativeResourcePath, file, fileTypeInstantiator, false, true, hashtable);
                    vector3.setElementAt(hashtable.elements().nextElement(), n);
                    final String string2 = "/" + relativeResourcePath;
                    final String string3 = firstClassFound.getResource(string2).toString();
                    if (!string3.endsWith(string2)) {
                        break Label_0331;
                    }
                    substring = string3.substring(0, string3.lastIndexOf(string2));
                    vector2.setElementAt(substring, n);
                }
                else if (substring.trim().equals("")) {
                    break Label_0331;
                }
                if (vector3.elementAt(n).equals("file")) {
                    if (!new File(file, replaceFileSeparatorsSystemSpecific(string)).exists()) {
                        break Label_0331;
                    }
                }
                else {
                    try {
                        if (new ZipFile(file).getEntry(string) == null) {
                            break Label_0331;
                        }
                    }
                    catch (Exception ex) {
                        break Label_0331;
                    }
                }
                if (!string.startsWith("/")) {
                    string = "/" + string;
                }
                try {
                    return new URL(substring + string);
                }
                catch (MalformedURLException ex2) {}
            }
            ++n;
        }
        return null;
    }
    
    private static String getCurrentResourcePath(final File file, final File file2) {
        if (file.toString().equals(file2.toString())) {
            return "/";
        }
        int n = 1;
        if (file2.toString().endsWith(File.separator)) {
            n = 0;
        }
        String s = file.toString().substring(file2.toString().length() + n, file.toString().length()).replace('\\', '/');
        if (file.isDirectory() && !s.endsWith("/")) {
            s += "/";
        }
        return s;
    }
    
    private static File getParentResourceFile() {
        if (ResourceLoader.ms_parentResourceFile == null && !ResourceLoader.ms_bTriedToLoadParentResourceFile) {
            synchronized (ResourceLoader.SYNC_RESOURCE) {
                if (!ResourceLoader.ms_bTriedToLoadParentResourceFile) {
                    ResourceLoader.ms_bTriedToLoadParentResourceFile = true;
                    try {
                        ResourceLoader.ms_parentResourceFile = new File(ClassUtil.getClassDirectory((ResourceLoader.class$anon$util$ResourceLoader == null) ? (ResourceLoader.class$anon$util$ResourceLoader = class$("anon.util.ResourceLoader")) : ResourceLoader.class$anon$util$ResourceLoader).getAbsolutePath());
                    }
                    catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        }
        return ResourceLoader.ms_parentResourceFile;
    }
    
    private static String getCurrentResourcePath(final ZipEntry zipEntry) {
        if (zipEntry.isDirectory() && !zipEntry.toString().endsWith("/")) {
            return zipEntry.toString() + "/";
        }
        return zipEntry.toString();
    }
    
    private static boolean isResourceInSearchPath(final String s, final String s2, final boolean b) {
        if (s.equals(s2) || s.equals("/")) {
            return true;
        }
        if (s2.equals("/")) {
            if (b) {
                return true;
            }
            if (s.indexOf("/") >= 0) {
                return false;
            }
        }
        if (s.length() <= s2.length()) {
            return false;
        }
        if (s.startsWith(s2) && s2.endsWith("/")) {
            if (b) {
                return true;
            }
            if (s.substring(s2.length()).indexOf("/") < 0) {
                return true;
            }
        }
        return false;
    }
    
    private static String formatResourcePath(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim().replace('\\', '/');
        if (s.equals("/")) {
            return s;
        }
        if (s.length() == 0 || s.startsWith("/")) {
            return null;
        }
        int index;
        while ((index = s.indexOf("/../")) >= 0) {
            if (s.startsWith("../")) {
                return null;
            }
            final String substring = s.substring(0, index);
            final int lastIndex;
            String substring2;
            if ((lastIndex = substring.lastIndexOf("/")) >= 0) {
                substring2 = substring.substring(0, lastIndex + 1);
            }
            else {
                substring2 = "/";
            }
            for (s = substring2 + s.substring(index + "/../".length(), s.length()); s.startsWith("/"); s = s.substring(1, s.length())) {
                if (s.equals("/")) {
                    break;
                }
            }
        }
        if (s.startsWith("../")) {
            return null;
        }
        return s;
    }
    
    private static byte[] trimByteArray(final byte[] array, final int n, final byte[] array2) {
        byte[] array3;
        if (n <= 0) {
            array3 = array2;
        }
        else {
            int length;
            if (array.length > n) {
                length = n;
            }
            else {
                length = array.length;
            }
            array3 = new byte[array2.length + length];
            System.arraycopy(array2, 0, array3, 0, array2.length);
            System.arraycopy(array, 0, array3, array2.length, length);
        }
        return array3;
    }
    
    private static Vector readFilesFromClasspath(final boolean b) {
        final String classPath = ClassUtil.getClassPath(b);
        if (ResourceLoader.ms_classpath == null || !ResourceLoader.ms_classpath.equals(classPath)) {
            synchronized (ResourceLoader.ms_classpathResourceLock) {
                ResourceLoader.ms_classpath = classPath;
                ResourceLoader.ms_classpathFiles = new Vector();
                ResourceLoader.ms_classpathResourceTypes = new Vector();
                final StringTokenizer stringTokenizer = new StringTokenizer(ResourceLoader.ms_classpath, File.pathSeparator);
                while (stringTokenizer.hasMoreTokens()) {
                    final File file = new File(new File(stringTokenizer.nextToken()).getAbsolutePath());
                    if (ResourceLoader.ms_classpathFiles.contains(file)) {
                        continue;
                    }
                    ResourceLoader.ms_classpathFiles.addElement(file);
                    ResourceLoader.ms_classpathResourceTypes.addElement(null);
                }
            }
        }
        return ResourceLoader.ms_classpathFiles;
    }
    
    private ByteArrayInstantiator createByteArrayInstantiator() {
        return new ByteArrayInstantiator();
    }
    
    private FileTypeInstantiator createFileTypeInstantiator() {
        return new FileTypeInstantiator();
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
        ResourceLoader.ms_classpathResourceLock = new Object();
        ResourceLoader.ms_bTriedToLoadParentResourceFile = false;
        SYNC_RESOURCE = new Object();
    }
    
    private final class ByteArrayInstantiator implements IResourceInstantiator
    {
        public Object getInstance(final File file, final File file2) throws IOException {
            return ResourceLoader.getStreamAsBytes(new FileInputStream(file));
        }
        
        public Object getInstance(final ZipEntry zipEntry, final ZipFile zipFile) throws IOException {
            return ResourceLoader.getStreamAsBytes(zipFile.getInputStream(zipEntry));
        }
        
        public Object getInstance(final InputStream inputStream, final String s) throws IOException {
            return ResourceLoader.getStreamAsBytes(inputStream);
        }
    }
    
    private final class FileTypeInstantiator implements IResourceInstantiator
    {
        public Object getInstance(final File file, final File file2) {
            return "file";
        }
        
        public Object getInstance(final ZipEntry zipEntry, final ZipFile zipFile) {
            return "zip";
        }
        
        public Object getInstance(final InputStream inputStream, final String s) {
            return null;
        }
    }
}
