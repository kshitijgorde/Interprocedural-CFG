// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.ui;

import com.grooveshark.sharklet.Sharklet;
import javax.swing.event.TableModelEvent;
import java.io.File;
import java.util.Collection;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.Box;
import com.grooveshark.ui.foldertree.FileSelectionState;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import com.grooveshark.ui.fileexplorer.FilesList;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.grooveshark.ui.component.TristateCheckbox;
import com.grooveshark.ui.component.GradientPanel;
import com.grooveshark.ui.component.Button;
import com.grooveshark.ui.foldercolumn.FolderColumn;
import java.awt.CardLayout;
import javax.swing.JPanel;
import com.grooveshark.ui.fileexplorer.FileExplorer;
import com.grooveshark.ui.fileexplorer.FileExplorerModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import com.grooveshark.ui.foldertree.FolderSelectionListener;
import javax.swing.event.TableModelListener;
import com.grooveshark.ui.wizard.WizardContentPanel;

public class FolderStepPanel extends WizardContentPanel implements TableModelListener, FolderSelectionListener, ActionListener
{
    private static final long serialVersionUID = 4195375939831061967L;
    private static final ImageIcon SELECTED_CORNER;
    private static final ImageIcon SELECTED_MIDDLE;
    private static final ImageIcon TOGGLED_SELECTED_CORNER;
    private static final ImageIcon TOGGLED_SELECTED_MIDDLE;
    private static final ImageIcon VIEW_ICON;
    private static final String DESELECT_ALL;
    private static final String SELECT_ALL;
    private static final String SONGS;
    private static final String PANEL_DESCRIPTION;
    private static final String PANEL_TITLE;
    private FileExplorerModel selectedModel;
    private FileExplorerModel filesModel;
    private FileExplorer explorer;
    private JPanel contentPanel;
    private CardLayout layout;
    private FolderColumn view;
    private boolean toggled;
    private Button selectedPanel;
    private GradientPanel buttonPanel;
    private TristateCheckbox checkbox;
    
    public FolderStepPanel(final FileExplorerModel filesModel) {
        super(FolderStepPanel.PANEL_TITLE, FolderStepPanel.PANEL_DESCRIPTION);
        this.setupModels(filesModel);
        this.setupUI();
    }
    
    private void setupModels(final FileExplorerModel filesModel) {
        (this.filesModel = filesModel).addTableModelListener(this);
        (this.selectedModel = new FileExplorerModel()).addTableModelListener(this);
    }
    
    private void setupUI() {
        final JPanel folderNavigation = this.createFolderNavigation();
        final JPanel filesPanel = this.createFilesView();
        this.setLayout(new BorderLayout(0, 0));
        this.add(folderNavigation, "North");
        this.add(filesPanel, "Center");
        this.setOpaque(false);
    }
    
    private JPanel createFolderNavigation() {
        (this.view = new FolderColumn()).addSelectionListener(this);
        this.view.setOpaque(false);
        this.view.setPreferredSize(new Dimension(660, 175));
        return this.view;
    }
    
    private JPanel createFilesView() {
        this.contentPanel = this.createContentPanel();
        final JPanel buttonPanel = this.createButtonPanel();
        final JPanel filesPanel = new JPanel(new BorderLayout());
        filesPanel.add(buttonPanel, "North");
        filesPanel.add(this.contentPanel, "Center");
        filesPanel.setOpaque(false);
        return filesPanel;
    }
    
    private JPanel createContentPanel() {
        final FilesList selectedFiles = new FilesList(this.selectedModel);
        this.explorer = this.createFileExplorer(this.filesModel);
        this.layout = new CardLayout();
        final JPanel contentPanel = new JPanel(this.layout);
        contentPanel.add(this.explorer, "Explorer");
        contentPanel.add(selectedFiles, "List");
        contentPanel.setOpaque(false);
        return contentPanel;
    }
    
    private JPanel createButtonPanel() {
        (this.selectedPanel = new Button(FolderStepPanel.SELECTED_CORNER, FolderStepPanel.SELECTED_MIDDLE)).setIcon(FolderStepPanel.VIEW_ICON);
        this.selectedPanel.setIconOrientation(1);
        this.selectedPanel.addActionListener(this);
        this.selectedPanel.setForeground(Color.decode("#3875d7"));
        this.selectedPanel.setVisible(false);
        this.selectedPanel.addMouseListener(new MouseAdapter() {
            private boolean isToggled;
            
            public void mouseClicked(final MouseEvent e) {
                this.isToggled = !this.isToggled;
                if (this.isToggled) {
                    FolderStepPanel.this.selectedPanel.setAllImages(FolderStepPanel.TOGGLED_SELECTED_CORNER, FolderStepPanel.TOGGLED_SELECTED_MIDDLE);
                }
                else {
                    FolderStepPanel.this.selectedPanel.setAllImages(FolderStepPanel.SELECTED_CORNER, FolderStepPanel.SELECTED_MIDDLE);
                }
            }
        });
        (this.checkbox = new TristateCheckbox()).setVisible(false);
        this.checkbox.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (FolderStepPanel.this.checkbox.getState().equals(FileSelectionState.NOT_SELECTED)) {
                    FolderStepPanel.this.checkbox.setText(FolderStepPanel.DESELECT_ALL);
                    FolderStepPanel.this.checkbox.setState(FileSelectionState.SELECTED);
                    FolderStepPanel.this.explorer.selectAll();
                }
                else {
                    FolderStepPanel.this.checkbox.setText(FolderStepPanel.SELECT_ALL);
                    FolderStepPanel.this.checkbox.setState(FileSelectionState.NOT_SELECTED);
                    FolderStepPanel.this.explorer.deselectAll();
                }
            }
        });
        (this.buttonPanel = new GradientPanel()).setGradientColor(Color.WHITE, Color.decode("#dadada"), Color.decode("#a0a1a2"));
        this.buttonPanel.setDrawHorizontalBorders(false);
        this.buttonPanel.setLayout(new BorderLayout(0, 0));
        this.buttonPanel.add(Box.createVerticalStrut(36));
        this.buttonPanel.add(this.selectedPanel, "East");
        this.buttonPanel.add(this.checkbox, "West");
        this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 13, 0, 0));
        return this.buttonPanel;
    }
    
    private void toggleSelectedList() {
        this.toggled = !this.toggled;
        this.layout.next(this.contentPanel);
        this.buttonPanel.setDrawHorizontalBorders(this.toggled);
        this.view.setVisible(!this.toggled);
        this.checkbox.setVisible(!this.toggled);
        if (this.toggled) {
            this.selectedModel.setIncludedFiles(this.filesModel.getIncludedFiles());
            this.selectedModel.setExcludedFiles(this.filesModel.getExcludedFiles());
            this.selectedModel.clear();
            this.selectedModel.addAll(this.filesModel.getIncludedFiles());
        }
        else {
            this.filesModel.setIncludedFiles(this.selectedModel.getIncludedFiles());
            this.filesModel.setExcludedFiles(this.selectedModel.getExcludedFiles());
            this.selectedModel.refresh();
        }
    }
    
    private FileExplorer createFileExplorer(final FileExplorerModel model) {
        final FileExplorer explorer = new FileExplorer(this.filesModel);
        return explorer;
    }
    
    public boolean isReady() {
        final boolean hasSelectedFiles = !this.filesModel.getSelectedFiles().isEmpty();
        return hasSelectedFiles;
    }
    
    public void tableChanged(final TableModelEvent e) {
        this.updateReadyStatus();
        this.updateSelectedFiles();
        this.updateCheckBox();
    }
    
    private void updateSelectedFiles() {
        final int totalSelectedFiles = this.toggled ? this.selectedModel.getIncludedFiles().size() : this.filesModel.getIncludedFiles().size();
        this.selectedPanel.setText(String.format(FolderStepPanel.SONGS, totalSelectedFiles));
        this.selectedPanel.setVisible(totalSelectedFiles > 0);
        if (this.toggled && totalSelectedFiles < 1) {
            this.toggleSelectedList();
        }
    }
    
    public void folderSelected(final File folder) {
        this.explorer.displayFolder(folder);
        this.checkbox.setVisible(true);
        this.updateCheckBox();
        System.out.println("Explorer:" + folder);
    }
    
    private void updateCheckBox() {
        if (this.filesModel.getRowCount() > 0 && this.explorer.getTotalSelected() == this.filesModel.getRowCount()) {
            this.checkbox.setText(FolderStepPanel.DESELECT_ALL);
            this.checkbox.setState(FileSelectionState.SELECTED);
        }
        else {
            this.checkbox.setText(FolderStepPanel.SELECT_ALL);
            this.checkbox.setState(FileSelectionState.NOT_SELECTED);
        }
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.toggleSelectedList();
    }
    
    static {
        SELECTED_CORNER = Sharklet.getImage("btn_selected_corner_down.png");
        SELECTED_MIDDLE = Sharklet.getImage("btn_selected_middle_down.png");
        TOGGLED_SELECTED_CORNER = Sharklet.getImage("btn_selected_corner.png");
        TOGGLED_SELECTED_MIDDLE = Sharklet.getImage("btn_selected_middle.png");
        VIEW_ICON = Sharklet.getImage("icon_eye_blue.png");
        DESELECT_ALL = Sharklet.getText("DESELECT_ALL");
        SELECT_ALL = Sharklet.getText("SELECT_ALL");
        SONGS = Sharklet.getText("SONGS");
        PANEL_DESCRIPTION = Sharklet.getText("LIBRARY_DESCRIPTION");
        PANEL_TITLE = Sharklet.getText("LIBRARY_TITLE");
    }
}
