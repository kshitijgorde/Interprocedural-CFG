// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.fileexplorer;

import com.grooveshark.ui.foldertree.FileSelectionState;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.table.TableModel;
import javax.swing.event.TableModelEvent;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import com.grooveshark.ui.table.SingleColumnTableModel;

public class FileExplorerModel extends SingleColumnTableModel<File>
{
    private static final long serialVersionUID = -3185220181628063014L;
    private List<File> includedFiles;
    private List<File> excludedFiles;
    
    public FileExplorerModel() {
        this.includedFiles = new ArrayList<File>();
        this.excludedFiles = new ArrayList<File>();
    }
    
    public void includeFiles(final List<File> files) {
        for (final File file : files) {
            this.doFileInclusion(file);
        }
        this.fireTableChanged(new TableModelEvent(this));
    }
    
    public void includeFile(final File file) {
        this.doFileInclusion(file);
        this.updateModel(file);
    }
    
    private void doFileInclusion(final File file) {
        if (this.excludedFiles.contains(file)) {
            System.out.println("Removing Exclusion for " + file);
            this.excludedFiles.remove(file);
        }
        if (file.isDirectory()) {
            this.removeIncludedChildren(file);
            this.removeExcludedChildren(file);
        }
        if (this.includedFiles.contains(file)) {
            return;
        }
        this.includedFiles.add(file);
    }
    
    public List<File> getIncludedFiles() {
        return this.includedFiles;
    }
    
    public void setIncludedFiles(final List<File> includedFiles) {
        this.includedFiles = includedFiles;
    }
    
    public List<File> getExcludedFiles() {
        return this.excludedFiles;
    }
    
    public void refresh() {
        this.fireTableChanged(new TableModelEvent(this));
    }
    
    public void setExcludedFiles(final List<File> excludedFiles) {
        this.excludedFiles = excludedFiles;
    }
    
    private void updateModel(final File file) {
        final int index = this.indexOf(file);
        this.setValueAt(file, index, 0);
    }
    
    public void excludeFiles(final List<File> files) {
        for (final File file : files) {
            this.doFileExclusion(file);
        }
        this.fireTableChanged(new TableModelEvent(this));
    }
    
    public void excludeFile(final File file) {
        this.doFileExclusion(file);
        this.updateModel(file);
    }
    
    private void doFileExclusion(final File file) {
        if (this.includedFiles.contains(file)) {
            this.includedFiles.remove(file);
        }
        if (file.isDirectory()) {
            this.removeIncludedChildren(file);
            this.removeExcludedChildren(file);
        }
        if (this.excludedFiles.contains(file)) {
            return;
        }
        if (this.includedFiles.isEmpty()) {
            this.excludedFiles.clear();
        }
        else {
            this.excludedFiles.add(file);
        }
    }
    
    private boolean hasIncludedChildren(final File parent) {
        return this.hasChildren(this.includedFiles, parent);
    }
    
    private boolean hasExcludedChildren(final File parent) {
        return this.hasChildren(this.excludedFiles, parent);
    }
    
    private boolean hasChildren(final List<File> list, final File parent) {
        for (final File child : list) {
            if (this.isChildOf(child, parent)) {
                return true;
            }
        }
        return false;
    }
    
    private void removeExcludedChildren(final File parent) {
        System.out.println("Removing Excluded children of " + parent);
        this.removeChildren(this.excludedFiles, parent);
    }
    
    private void removeIncludedChildren(final File parent) {
        System.out.println("Removing Included for " + parent);
        this.removeChildren(this.includedFiles, parent);
    }
    
    private void removeChildren(final List<File> list, final File parent) {
        final ArrayList<File> removeList = new ArrayList<File>();
        for (final File file : list) {
            if (this.isChildOf(file, parent)) {
                System.out.println("Removing child " + file);
                removeList.add(file);
            }
        }
        list.removeAll(removeList);
    }
    
    private boolean isChildOf(final File file, final File parent) {
        return !parent.isFile() && file.getAbsolutePath().startsWith(parent.getAbsolutePath() + File.separator);
    }
    
    private boolean isIncluded(final File file) {
        return this.includedFiles.contains(file) || (!this.excludedFiles.contains(file) && this.isParentIncluded(file));
    }
    
    private boolean isParentExcluded(final File child) {
        for (final File parent : this.excludedFiles) {
            if (this.isChildOf(child, parent)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isParentIncluded(final File child) {
        if (this.isParentExcluded(child)) {
            return false;
        }
        for (final File parent : this.includedFiles) {
            if (this.isChildOf(child, parent)) {
                return true;
            }
        }
        return false;
    }
    
    public List<File> getExclusions() {
        return this.excludedFiles;
    }
    
    public List<File> getSelectedFiles() {
        return this.includedFiles;
    }
    
    public FileSelectionState getFileState(final File file) {
        if (file.isDirectory()) {
            if (this.isIncluded(file)) {
                if (this.hasExcludedChildren(file)) {
                    return FileSelectionState.PARTIALLY_SELECTED;
                }
                return FileSelectionState.SELECTED;
            }
            else if (this.hasIncludedChildren(file)) {
                return FileSelectionState.PARTIALLY_SELECTED;
            }
        }
        else if (this.isIncluded(file)) {
            return FileSelectionState.SELECTED;
        }
        return FileSelectionState.NOT_SELECTED;
    }
}
