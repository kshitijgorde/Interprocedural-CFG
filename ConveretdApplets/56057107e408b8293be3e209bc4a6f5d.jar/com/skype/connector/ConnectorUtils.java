// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector;

import java.io.IOException;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.io.OutputStream;
import java.net.URL;
import java.lang.reflect.Constructor;
import java.util.zip.ZipEntry;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.StringTokenizer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class ConnectorUtils
{
    private static List loadedLibraries;
    static /* synthetic */ Class class$0;
    
    static {
        ConnectorUtils.loadedLibraries = new ArrayList();
    }
    
    public static void checkNotNull(final String name, final Object value) {
        if (value == null) {
            throw new NullPointerException("The " + name + " must not be null.");
        }
    }
    
    public static boolean extractFromJarToTemp(final String filename) {
        return extractFromJar(filename, filename, System.getProperty("java.io.tmpdir"));
    }
    
    public static boolean extractFromJar(final String filename, final String destinationDirectory) {
        return extractFromJar(filename, filename, destinationDirectory);
    }
    
    public static boolean extractFromJar(final String searchString, final String filename, final String destinationDirectory) {
        final boolean extracted = extractFromJarZipMethod(filename, filename, destinationDirectory);
        if (!extracted) {
            extractFromJarUsingClassLoader(filename, filename, destinationDirectory);
        }
        return extracted;
    }
    
    private static boolean extractFromJarZipMethod(final String searchString, final String filename, String destinationDirectory) {
        boolean extracted = false;
        final String classpath = getExtendedClasspath();
        File jarfile = null;
        final byte[] buf = new byte[1024];
        final StringTokenizer st = new StringTokenizer(classpath, File.pathSeparator);
        while (st.hasMoreTokens() && !extracted) {
            final String jarFileName = st.nextToken();
            jarfile = new File(jarFileName);
            if (jarfile.exists() && jarfile.isFile()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(jarFileName);
                    final BufferedInputStream bis = new BufferedInputStream(fis);
                    final ZipInputStream zis = new ZipInputStream(bis);
                    ZipEntry ze = null;
                    while ((ze = zis.getNextEntry()) != null) {
                        if (ze.getName().endsWith(searchString)) {
                            if (!destinationDirectory.endsWith(File.separator)) {
                                destinationDirectory = String.valueOf(destinationDirectory) + File.separator;
                            }
                            final File destFile = new File(String.valueOf(destinationDirectory) + filename);
                            if (destFile.exists()) {
                                destFile.delete();
                            }
                            final FileOutputStream fileoutputstream = new FileOutputStream(String.valueOf(destinationDirectory) + filename);
                            int n;
                            while ((n = zis.read(buf, 0, 1024)) > -1) {
                                fileoutputstream.write(buf, 0, n);
                            }
                            fileoutputstream.close();
                            extracted = true;
                            destFile.deleteOnExit();
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    extracted = false;
                }
            }
        }
        return extracted;
    }
    
    private static boolean extractFromJarUsingClassLoader(final String searchString, final String filename, String destinationDirectory) {
        ClassLoader cl = null;
        try {
            final Class clClass = Class.forName("com.simontuffs.onejar.JarClassLoader");
            final Constructor[] constructor = clClass.getConstructors();
            final Object[] o = { ClassLoader.getSystemClassLoader() };
            cl = constructor[1].newInstance(o);
            System.out.println("Loaded JarClassLoader. cl=" + cl.toString());
        }
        catch (Throwable e2) {
            cl = ClassLoader.getSystemClassLoader();
        }
        final URL liburl = cl.getResource(filename);
        if (liburl == null) {
            return false;
        }
        if (!destinationDirectory.endsWith(File.separator)) {
            destinationDirectory = String.valueOf(destinationDirectory) + File.separator;
        }
        try {
            final File destFile = new File(String.valueOf(destinationDirectory) + filename);
            if (destFile.exists()) {
                destFile.delete();
            }
            final InputStream is = liburl.openStream();
            final OutputStream os = new FileOutputStream(String.valueOf(destinationDirectory) + filename);
            final byte[] buf = new byte[4096];
            for (int cnt = is.read(buf); cnt > 0; cnt = is.read(buf)) {
                os.write(buf, 0, cnt);
            }
            os.close();
            is.close();
            destFile.deleteOnExit();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean isInJar(final String searchString) {
        boolean found = false;
        final String classpath = getExtendedClasspath();
        File jarfile = null;
        final StringTokenizer st = new StringTokenizer(classpath, File.pathSeparator);
        while (st.hasMoreTokens() && !found) {
            final String jarFileName = st.nextToken();
            jarfile = new File(jarFileName);
            if (jarfile.exists() && jarfile.isFile()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(jarFileName);
                    final BufferedInputStream bis = new BufferedInputStream(fis);
                    final ZipInputStream zis = new ZipInputStream(bis);
                    ZipEntry ze = null;
                    while ((ze = zis.getNextEntry()) != null) {
                        if (ze.getName().endsWith(searchString)) {
                            found = true;
                        }
                    }
                }
                catch (Exception e) {
                    found = false;
                }
            }
        }
        return found;
    }
    
    private static String getLibrarySearchPath() {
        return String.valueOf(System.getProperty("java.library.path")) + File.pathSeparatorChar + System.getProperty("user.dir") + File.pathSeparatorChar;
    }
    
    private static String getExtendedClasspath() {
        String classpath = String.valueOf(System.getProperty("java.class.path")) + File.pathSeparatorChar;
        String userDirStr = System.getProperty("user.dir");
        final File userDir = new File(userDirStr);
        userDirStr = userDir.getAbsolutePath();
        if (!userDirStr.endsWith(File.separator)) {
            userDirStr = String.valueOf(userDirStr) + File.separator;
        }
        final String[] files = userDir.list();
        for (int i = 0; i < files.length; ++i) {
            if (files[i].endsWith("jar")) {
                classpath = String.valueOf(classpath) + File.pathSeparatorChar + userDirStr + files[i];
            }
        }
        return classpath;
    }
    
    public static boolean checkLibraryInPath(final String libFilename) {
        boolean libfilefound = false;
        final String libpath = getLibrarySearchPath();
        File libfile = new File("");
        for (StringTokenizer st = new StringTokenizer(libpath, File.pathSeparator); st.hasMoreTokens() && !libfilefound; libfilefound = libfile.exists()) {
            libfile = new File(String.valueOf(st.nextToken()) + File.separatorChar + libFilename);
        }
        return libfilefound;
    }
    
    public static boolean deleteDir(final File dir) {
        if (dir.isDirectory()) {
            final String[] children = dir.list();
            for (int i = 0; i < children.length; ++i) {
                final boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    
    public static void loadLibrary(final String libraryName) throws LoadLibraryException {
        synchronized (ConnectorUtils.loadedLibraries) {
            if (ConnectorUtils.loadedLibraries.contains(libraryName)) {
                // monitorexit(ConnectorUtils.loadedLibraries)
                return;
            }
            try {
                System.loadLibrary(libraryName);
            }
            catch (UnsatisfiedLinkError err) {
                final String libraryFileName = System.mapLibraryName(libraryName);
                Class class$0;
                if ((class$0 = ConnectorUtils.class$0) == null) {
                    try {
                        class$0 = (ConnectorUtils.class$0 = Class.forName("com.skype.connector.ConnectorUtils"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                final URL url = class$0.getResource("/" + libraryFileName);
                Label_0152: {
                    if (url.getProtocol().toLowerCase().equals("file")) {
                        try {
                            final File libraryFile = new File(URLDecoder.decode(url.getPath(), "UTF-8"));
                            break Label_0152;
                        }
                        catch (UnsupportedEncodingException e) {
                            throw new LoadLibraryException("UTF-8 is not supported encoding.");
                        }
                    }
                    cleanUpOldLibraryFiles(libraryFileName);
                    final File libraryFile = createTempLibraryFile(libraryFileName);
                    try {
                        System.load(libraryFile.getAbsolutePath());
                    }
                    catch (UnsatisfiedLinkError e2) {
                        throw new LoadLibraryException("Loading " + libraryFileName + " failed.");
                    }
                }
            }
            ConnectorUtils.loadedLibraries.add(libraryName);
        }
        // monitorexit(ConnectorUtils.loadedLibraries)
    }
    
    private static void cleanUpOldLibraryFiles(final String libraryFileName) {
        final String fileNamePrefix = libraryFileName.substring(0, libraryFileName.indexOf(46));
        final String extension = libraryFileName.substring(libraryFileName.lastIndexOf(46));
        final File[] theFiles = new File(System.getProperty("java.io.tmpdir")).listFiles(new FilenameFilter() {
            private final /* synthetic */ String val$fileNamePrefix = val$fileNamePrefix;
            
            public boolean accept(final File dir, final String name) {
                return name.startsWith(this.val$fileNamePrefix) && name.endsWith(extension);
            }
        });
        for (int i = 0; i < theFiles.length; ++i) {
            theFiles[i].delete();
        }
    }
    
    private static File createTempLibraryFile(final String libraryFileName) throws LoadLibraryException {
        Class class$0;
        if ((class$0 = ConnectorUtils.class$0) == null) {
            try {
                class$0 = (ConnectorUtils.class$0 = Class.forName("com.skype.connector.ConnectorUtils"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final InputStream in = class$0.getResourceAsStream("/" + libraryFileName);
        if (in == null) {
            throw new LoadLibraryException(String.valueOf(libraryFileName) + " is not contained in the jar.");
        }
        FileOutputStream out = null;
        try {
            final String fileNamePrefix = libraryFileName.substring(0, libraryFileName.indexOf(46));
            final String extension = libraryFileName.substring(libraryFileName.lastIndexOf(46));
            final File libraryFile = File.createTempFile(fileNamePrefix, extension);
            libraryFile.deleteOnExit();
            out = new FileOutputStream(libraryFile);
            final byte[] buffer = new byte[1024];
            int count;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
            return libraryFile;
        }
        catch (IOException e) {
            throw new LoadLibraryException("Writing " + libraryFileName + " failed.");
        }
        finally {
            try {
                in.close();
            }
            catch (IOException ex2) {}
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException ex3) {}
            }
        }
    }
}
