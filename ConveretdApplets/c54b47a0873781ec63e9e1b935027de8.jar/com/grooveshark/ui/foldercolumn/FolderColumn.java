// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.foldercolumn;

import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import com.grooveshark.ui.foldertree.CacheFileSource;
import com.grooveshark.ui.foldertree.FolderFileSource;
import com.grooveshark.ui.foldertree.FolderSelectionListener;
import java.util.ArrayList;
import java.util.Stack;
import com.grooveshark.ui.foldertree.FileSource;
import com.grooveshark.ui.component.BackButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class FolderColumn extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 2064396014219613463L;
    private FolderView firstPanel;
    private FolderView secondPanel;
    private FolderView thirdPanel;
    private BackButton backButton;
    private FileSource fileSource;
    private Stack<String> pathStack;
    private ArrayList<FolderSelectionListener> listeners;
    
    public FolderColumn() {
        this.pathStack = new Stack<String>();
        this.fileSource = new CacheFileSource(new FolderFileSource());
        this.listeners = new ArrayList<FolderSelectionListener>();
        this.createFirstPanel();
        this.createSecondPanel();
        this.createThirdPanel();
        this.firstPanel.displayRoots();
        this.setupUI();
    }
    
    private void setupUI() {
        final JPanel columnsPanel = new JPanel(new GridLayout(1, 3, 1, 0));
        columnsPanel.add(this.firstPanel);
        columnsPanel.add(this.secondPanel);
        columnsPanel.add(this.thirdPanel);
        columnsPanel.setOpaque(false);
        (this.backButton = new BackButton()).addActionListener(this);
        this.setOpaque(false);
        this.setLayout(new BorderLayout(0, 0));
        this.add(this.backButton, "West");
        this.add(columnsPanel, "Center");
    }
    
    private void createFirstPanel() {
        (this.firstPanel = new FolderView(this.fileSource)).addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                final File folder = FolderColumn.this.firstPanel.getSelectedFolder();
                FolderColumn.this.secondPanel.displayFolder(folder);
                FolderColumn.this.thirdPanel.displayFolder(null);
                FolderColumn.this.folderSelected(folder);
            }
        });
    }
    
    private void createSecondPanel() {
        (this.secondPanel = new FolderView(this.fileSource)).addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                final File folder = FolderColumn.this.secondPanel.getSelectedFolder();
                FolderColumn.this.thirdPanel.displayFolder(folder);
                FolderColumn.this.folderSelected(folder);
            }
        });
    }
    
    private void createThirdPanel() {
        (this.thirdPanel = new FolderView(this.fileSource)).addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                final File folder = FolderColumn.this.thirdPanel.getSelectedFolder();
                FolderColumn.this.nextPage(folder);
                FolderColumn.this.folderSelected(folder);
            }
        });
    }
    
    private void nextPage(final File folder) {
        final boolean isEmptyFolder = this.fileSource.getTotalChildren(folder.getAbsolutePath()) == 0;
        if (isEmptyFolder) {
            return;
        }
        this.pathStack.push(this.firstPanel.getCurrentPath());
        final File parent = folder.getParentFile();
        final File grandparent = parent.getParentFile();
        this.firstPanel.displayFolder(grandparent);
        this.firstPanel.selectFolder(parent);
        this.secondPanel.displayFolder(parent);
        this.secondPanel.selectFolder(folder);
        this.secondPanel.requestFocus();
        this.thirdPanel.displayFolder(folder);
    }
    
    public void addSelectionListener(final FolderSelectionListener listener) {
        this.listeners.add(listener);
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.goUpOneLevel();
    }
    
    private void goUpOneLevel() {
        final String path = this.firstPanel.getCurrentPath();
        if (path.equals("ROOTS")) {
            return;
        }
        final File folder = new File(path);
        final File firstSelectedFile = this.firstPanel.getSelectedFolder();
        final File secondSelectedFile = this.secondPanel.getSelectedFolder();
        final String lastPath = this.pathStack.pop();
        if (lastPath.equals("ROOTS")) {
            this.firstPanel.displayRoots();
        }
        else {
            final File parent = new File(lastPath);
            this.firstPanel.displayFolder(parent);
        }
        this.firstPanel.selectFolder(folder);
        if (firstSelectedFile == null) {
            return;
        }
        this.secondPanel.displayFolder(folder);
        this.secondPanel.selectFolder(firstSelectedFile);
        if (secondSelectedFile == null) {
            return;
        }
        this.thirdPanel.displayFolder(firstSelectedFile);
        this.thirdPanel.selectFolder(secondSelectedFile);
        this.thirdPanel.requestFocus();
        this.folderSelected(secondSelectedFile);
    }
    
    public void folderSelected(final File folder) {
        for (final FolderSelectionListener l : this.listeners) {
            l.folderSelected(folder);
        }
    }
}
