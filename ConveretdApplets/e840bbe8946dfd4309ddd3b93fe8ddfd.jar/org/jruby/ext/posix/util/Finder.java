// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix.util;

import java.util.HashMap;
import java.util.Iterator;
import org.jruby.ext.posix.FileStat;
import java.io.File;
import org.jruby.ext.posix.POSIX;
import java.util.Map;

public class Finder
{
    private static String PS;
    private static Map<String, String> EXECUTABLE_EXTENSIONS;
    
    public static String findFileInPath(final POSIX posix, final String name, String path) {
        if (path == null || path.length() == 0) {
            path = System.getenv("PATH");
        }
        if (path == null || path.length() == 0) {
            return name;
        }
        return findFileCommon(posix, name, path, true);
    }
    
    public static String findFileCommon(final POSIX posix, final String name, final String path, final boolean executableOnly) {
        if (name == null || name.length() == 0) {
            return name;
        }
        final int length = name.length();
        boolean isAbsolute = false;
        boolean isPath = false;
        int i = 0;
        if (Platform.IS_WINDOWS) {
            if (length > 1 && Character.isLetter(name.charAt(0)) && name.charAt(1) == ':') {
                i = 2;
                isAbsolute = true;
            }
            int extensionIndex = -1;
            char c = name.charAt(i);
            if (i == 47 || i == 92) {
                ++i;
                c = name.charAt(i);
                isAbsolute = true;
            }
            while (i < length) {
                switch (c) {
                    case '/':
                    case '\\': {
                        isPath = true;
                        extensionIndex = -1;
                        break;
                    }
                    case '.': {
                        extensionIndex = i - 1;
                        break;
                    }
                }
                c = name.charAt(i);
                ++i;
            }
            if (extensionIndex >= 0 && Finder.EXECUTABLE_EXTENSIONS.get(name.substring(extensionIndex).toLowerCase()) == null) {
                extensionIndex = -1;
            }
            if (!executableOnly) {
                if (isAbsolute) {
                    return name;
                }
            }
            else if (isPath) {
                if (extensionIndex >= 0) {
                    return name;
                }
                if (executableOnly) {
                    return addExtension(name);
                }
                if (new File(name).exists()) {
                    return name;
                }
                return null;
            }
            final String[] paths = path.split(Finder.PS);
            for (int p = 0; p < paths.length; ++p) {
                String currentPath = paths[p];
                final int currentPathLength = currentPath.length();
                if (currentPath != null) {
                    if (currentPathLength != 0) {
                        if (currentPath.charAt(0) == '~' && (currentPathLength == 1 || (currentPathLength > 1 && (currentPath.charAt(1) == '/' || currentPath.charAt(1) == '\\')))) {
                            final String home = System.getenv("HOME");
                            if (home != null) {
                                currentPath = home + ((currentPathLength == 1) ? "" : currentPath.substring(1));
                            }
                        }
                        if (!currentPath.endsWith("/") && !currentPath.endsWith("\\")) {
                            currentPath += "\\";
                        }
                        final String filename = currentPath + name;
                        if (Platform.IS_WINDOWS) {
                            filename.replace('/', '\\');
                        }
                        if (Platform.IS_WINDOWS && executableOnly && extensionIndex == -1) {
                            final String extendedFilename = addExtension(filename);
                            if (extendedFilename != null) {
                                return extendedFilename;
                            }
                        }
                        else {
                            final FileStat stat = posix.allocateStat();
                            final int value = posix.libc().stat(filename, stat);
                            if (value >= 0) {
                                if (!executableOnly) {
                                    return filename;
                                }
                                if (!stat.isDirectory() && stat.isExecutable()) {
                                    return filename;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public static String addExtension(final String path) {
        for (final String extension : Finder.EXECUTABLE_EXTENSIONS.keySet()) {
            final String newPath = path + extension;
            if (new File(newPath).exists()) {
                return newPath;
            }
        }
        return null;
    }
    
    static {
        Finder.PS = (Platform.IS_WINDOWS ? ";" : ":");
        Finder.EXECUTABLE_EXTENSIONS = (Map<String, String>)new HashMap() {
            {
                this.put(".exe", ".exe");
                this.put(".com", ".com");
                this.put(".cmd", ".cmd");
                this.put(".bat", ".bat");
            }
        };
    }
}
