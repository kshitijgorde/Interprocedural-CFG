// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.songscanner;

import java.util.Collection;
import java.util.Stack;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.FileFilter;
import java.util.List;
import java.io.File;

public class FileScanner
{
    private boolean isCancelled;
    
    public List<File> scanFolder(final File rootFolder) {
        return this.scanFolder(rootFolder, new NoFilter());
    }
    
    public List<File> scanFolder(final File rootFolder, final FileFilter filter) {
        final List<File> rootFolders = new ArrayList<File>();
        rootFolders.add(rootFolder);
        return this.scanFolders(rootFolders, filter);
    }
    
    public List<File> scanFolders(final List<File> rootFolders, final FileFilter filter) {
        final List<File> filesFound = new LinkedList<File>();
        final Stack<File> foldersToScan = new Stack<File>();
        foldersToScan.addAll((Collection<?>)rootFolders);
        this.isCancelled = false;
        while (!foldersToScan.isEmpty()) {
            final File currentFolder = foldersToScan.pop();
            final boolean isAccessible = currentFolder.canRead();
            if (!isAccessible) {
                continue;
            }
            final File[] files = currentFolder.listFiles(filter);
            final boolean noFilesFound = files == null;
            if (noFilesFound) {
                continue;
            }
            for (final File file : files) {
                if (this.isCancelled) {
                    break;
                }
                if (file.isFile()) {
                    filesFound.add(file);
                }
                else {
                    foldersToScan.push(file);
                }
            }
        }
        return filesFound;
    }
    
    public void cancel() {
        this.isCancelled = true;
        System.out.println("filescanner cancelled");
    }
}
