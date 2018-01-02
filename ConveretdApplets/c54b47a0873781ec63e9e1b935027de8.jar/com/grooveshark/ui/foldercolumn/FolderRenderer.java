// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.foldercolumn;

import com.grooveshark.sharklet.Sharklet;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.io.File;
import java.awt.Component;
import javax.swing.JTable;
import com.grooveshark.ui.foldertree.FileSource;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.table.TableCellRenderer;

public class FolderRenderer implements TableCellRenderer
{
    private static final ImageIcon FOLDER_ICON;
    private static final ImageIcon ARROW_ICON;
    private static final ImageIcon SELECTED_ARROW_ICON;
    private static final ImageIcon DISABLED_ARROW_ICON;
    private static final Color FOCUSED_BACKGROUND;
    private static final Color FOCUSED_FOREGROUND;
    private static final Color SELECTED_BACKGROUND;
    private static final Color SELECTED_FOREGROUND;
    private static final Color DEFAULT_BACKGROUND;
    private static final Color DEFAULT_FOREGROUND;
    private static final Color ALTERNATE_BACKGROUND;
    private FileSource source;
    
    public FolderRenderer(final FileSource source) {
        this.source = source;
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final File file = (File)value;
        final JLabel label = new JLabel();
        if (file.getName().equals("")) {
            label.setText(file.getAbsolutePath());
        }
        else {
            label.setText(file.getName());
        }
        label.setIcon(FolderRenderer.FOLDER_ICON);
        final JLabel icon = new JLabel();
        final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));
        panel.add(label, "West");
        panel.add(icon, "East");
        if (isSelected) {
            if (hasFocus) {
                panel.setBackground(FolderRenderer.SELECTED_BACKGROUND);
                label.setForeground(FolderRenderer.SELECTED_FOREGROUND);
                icon.setIcon(FolderRenderer.SELECTED_ARROW_ICON);
            }
            else {
                panel.setBackground(FolderRenderer.FOCUSED_BACKGROUND);
                label.setForeground(FolderRenderer.FOCUSED_FOREGROUND);
                icon.setIcon(FolderRenderer.DISABLED_ARROW_ICON);
            }
        }
        else {
            panel.setBackground((row % 2 == 0) ? FolderRenderer.DEFAULT_BACKGROUND : FolderRenderer.ALTERNATE_BACKGROUND);
            label.setForeground(FolderRenderer.DEFAULT_FOREGROUND);
            icon.setIcon(FolderRenderer.ARROW_ICON);
        }
        if (this.source.getTotalChildren(file.getAbsolutePath()) < 1) {
            icon.setIcon(null);
        }
        System.out.println("Rendered " + file);
        return panel;
    }
    
    static {
        FOLDER_ICON = Sharklet.getImage("icon_folder.png");
        ARROW_ICON = Sharklet.getImage("icon_arrow_small_right_grey.png");
        SELECTED_ARROW_ICON = Sharklet.getImage("icon_arrow_small_right_white.png");
        DISABLED_ARROW_ICON = Sharklet.getImage("icon_arrow_small_right_black.png");
        FOCUSED_BACKGROUND = Color.decode("#D4D4D4");
        FOCUSED_FOREGROUND = Color.decode("#222222");
        SELECTED_BACKGROUND = Color.decode("#1879D9");
        SELECTED_FOREGROUND = Color.decode("#ffffff");
        DEFAULT_BACKGROUND = Color.decode("#ffffff");
        DEFAULT_FOREGROUND = Color.decode("#222222");
        ALTERNATE_BACKGROUND = Color.decode("#edf3fe");
    }
}
