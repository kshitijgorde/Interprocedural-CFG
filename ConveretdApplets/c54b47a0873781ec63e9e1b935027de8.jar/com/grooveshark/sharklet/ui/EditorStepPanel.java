// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.ui;

import com.grooveshark.sharklet.Sharklet;
import java.io.File;
import javax.swing.event.TableModelEvent;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Cursor;
import com.grooveshark.ui.component.GradientPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.util.Comparator;
import com.grooveshark.ui.songspanel.SongComparator;
import javax.swing.table.TableStringConverter;
import com.grooveshark.ui.songspanel.SongConverter;
import javax.swing.table.TableRowSorter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.grooveshark.ui.songspanel.SongsPanel;
import javax.swing.table.TableModel;
import java.util.LinkedList;
import javax.swing.event.TableModelListener;
import com.grooveshark.ui.component.Button;
import com.grooveshark.ui.songspanel.controller.FixController;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.grooveshark.sharklet.Song;
import com.grooveshark.ui.table.SingleColumnTableModel;
import javax.swing.ImageIcon;

public class EditorStepPanel extends EditorPanel
{
    private static final long serialVersionUID = -4581318913625826551L;
    private static final String PANEL_TITLE;
    private static final String PANEL_DESCRIPTION;
    private static final ImageIcon FIX_BUTTON_CORNER;
    private static final ImageIcon FIX_BUTTON_MIDDLE;
    private static final ImageIcon TOGGLED_FIX_BUTTON_CORNER;
    private static final ImageIcon TOGGLED_FIX_BUTTON_MIDDLE;
    private static final ImageIcon EYE_ICON;
    private static final String FIX_BUTTON;
    private static final String VIEW_TEXT;
    private boolean scanFinished;
    private SingleColumnTableModel<Song> tableModel;
    private JPanel warningPanel;
    private JLabel problemFilesLabel;
    private List<Integer> invalidFiles;
    private FixController fixController;
    private Button warningButton;
    
    public EditorStepPanel(final SingleColumnTableModel<Song> tableModel) {
        super(EditorStepPanel.PANEL_TITLE, EditorStepPanel.PANEL_DESCRIPTION);
        (this.tableModel = tableModel).addTableModelListener(this);
        this.invalidFiles = new LinkedList<Integer>();
        this.setupUI();
    }
    
    private void setupUI() {
        final TableRowSorter<TableModel> rowSorter = this.createTableSorter(this.tableModel);
        final SongsPanel songsPanel = new SongsPanel(this.tableModel, rowSorter);
        songsPanel.setOpaque(false);
        this.fixController = new FixController(rowSorter);
        this.warningPanel = this.createWarningPanel();
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(this.warningPanel, "North");
        this.add(songsPanel, "Center");
    }
    
    private TableRowSorter<TableModel> createTableSorter(final TableModel model) {
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>();
        sorter.setModel(model);
        sorter.setStringConverter(new SongConverter());
        sorter.setComparator(0, new SongComparator());
        return sorter;
    }
    
    private JLabel createProblemFilesLabel() {
        final JLabel problemFilesLabel = new JLabel();
        problemFilesLabel.setForeground(Color.WHITE);
        problemFilesLabel.setFont(problemFilesLabel.getFont().deriveFont(11.0f).deriveFont(1));
        return problemFilesLabel;
    }
    
    private JPanel createWarningPanel() {
        this.problemFilesLabel = this.createProblemFilesLabel();
        (this.warningButton = new Button(EditorStepPanel.FIX_BUTTON_CORNER, EditorStepPanel.FIX_BUTTON_MIDDLE)).setIcon(EditorStepPanel.EYE_ICON);
        this.warningButton.setIconOrientation(1);
        this.warningButton.setText(EditorStepPanel.VIEW_TEXT);
        this.warningButton.setForeground(Color.WHITE);
        this.warningButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                EditorStepPanel.this.toggleButton();
            }
        });
        final GradientPanel warningPanel = new GradientPanel();
        warningPanel.setVisible(false);
        warningPanel.setCursor(Cursor.getPredefinedCursor(12));
        warningPanel.setPreferredSize(new Dimension(700, 25));
        warningPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        warningPanel.setLayout(new BorderLayout(0, 0));
        warningPanel.setGradientColor(Color.decode("#fe482a"), Color.decode("#d93937"), Color.decode("#b61e1d"));
        warningPanel.add(this.warningButton, "East");
        warningPanel.add(this.problemFilesLabel, "West");
        return warningPanel;
    }
    
    private void toggleButton() {
        this.fixController.toggleFilter();
        if (this.fixController.isFiltering()) {
            this.warningButton.setAllImages(EditorStepPanel.TOGGLED_FIX_BUTTON_CORNER, EditorStepPanel.TOGGLED_FIX_BUTTON_MIDDLE);
        }
        else {
            this.warningButton.setAllImages(EditorStepPanel.FIX_BUTTON_CORNER, EditorStepPanel.FIX_BUTTON_MIDDLE);
        }
    }
    
    public void tableChanged(final TableModelEvent e) {
        final int type = e.getType();
        if (type == 1) {
            final int index = e.getFirstRow();
            final Song s = this.tableModel.getValueAt(index, 0);
            if (s.isInvalid()) {
                this.invalidFiles.add(index);
            }
        }
        else if (type == -1) {
            final int index = e.getFirstRow();
            this.invalidFiles.remove(new Integer(index));
            for (int i = 0; i < this.invalidFiles.size(); ++i) {
                final int invalidIndex = this.invalidFiles.get(i);
                if (invalidIndex > index) {
                    this.invalidFiles.set(i, invalidIndex - 1);
                }
            }
            if (this.tableModel.getRowCount() == 0) {
                this.invalidFiles.clear();
            }
        }
        else if (type == 0) {
            final int index = e.getFirstRow();
            final Song s = this.tableModel.getValueAt(index, 0);
            if (s.isInvalid()) {
                if (!this.invalidFiles.contains(index)) {
                    this.invalidFiles.add(index);
                }
            }
            else {
                this.invalidFiles.remove(new Integer(index));
            }
        }
        if (this.invalidFiles.isEmpty() && this.fixController.isFiltering()) {
            this.toggleButton();
        }
        this.warningPanel.setVisible(this.invalidFiles.size() > 0);
        this.problemFilesLabel.setText(String.format(EditorStepPanel.FIX_BUTTON, this.invalidFiles.size()));
        this.updateReadyStatus();
    }
    
    public boolean isReady() {
        final boolean hasValidFiles = this.invalidFiles.size() < this.tableModel.getRowCount();
        return hasValidFiles && this.scanFinished;
    }
    
    public void badFile(final File file, final Exception exception) {
    }
    
    public void foundFiles(final int totalFiles) {
    }
    
    public void newSong(final Song song) {
    }
    
    public void scanFinished() {
        this.scanFinished = true;
        this.updateReadyStatus();
    }
    
    public void scanStarted() {
        this.scanFinished = false;
        this.updateReadyStatus();
    }
    
    static {
        PANEL_TITLE = Sharklet.getText("EDITOR_TITLE");
        PANEL_DESCRIPTION = Sharklet.getText("EDITOR_DESCRIPTION");
        FIX_BUTTON_CORNER = Sharklet.getImage("btn_red_corner_up.png");
        FIX_BUTTON_MIDDLE = Sharklet.getImage("btn_red_middle_up.png");
        TOGGLED_FIX_BUTTON_CORNER = Sharklet.getImage("btn_red_corner_down.png");
        TOGGLED_FIX_BUTTON_MIDDLE = Sharklet.getImage("btn_red_middle_down.png");
        EYE_ICON = Sharklet.getImage("icon_eye_white.png");
        FIX_BUTTON = Sharklet.getText("FIX_BUTTON");
        VIEW_TEXT = Sharklet.getText("VIEW_SONGS");
    }
}
