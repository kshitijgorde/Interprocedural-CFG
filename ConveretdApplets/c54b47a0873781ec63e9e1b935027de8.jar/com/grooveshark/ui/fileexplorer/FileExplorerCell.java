// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.fileexplorer;

import javax.swing.BorderFactory;
import com.grooveshark.sharklet.Sharklet;
import javax.swing.Icon;
import com.grooveshark.ui.foldertree.FileSelectionState;
import java.io.File;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.grooveshark.ui.component.TristateCheckbox;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FileExplorerCell extends JPanel
{
    private static final long serialVersionUID = 2449529342708971668L;
    private static final ImageIcon FOLDER_ICON;
    private static final ImageIcon FILE_ICON;
    private static final Color DEFAULT_BACKGROUND;
    private static final Color ALTERNATE_BACKGROUND;
    private static final Color SELECTED_BACKGROUND;
    private static final Color SELECTED_FOREGROUND;
    private static final Color DEFAULT_FOREGROUND;
    private static final Color CHECKED_FOREGROUND;
    private static final Border PANEL_BORDER;
    private JLabel fileLabel;
    private TristateCheckbox checkBox;
    
    public FileExplorerCell() {
        this.checkBox = new TristateCheckbox();
        this.fileLabel = new JLabel();
        this.setLayout(new BorderLayout(10, 0));
        this.add(this.checkBox, "West");
        this.add(this.fileLabel, "Center");
        this.setBorder(FileExplorerCell.PANEL_BORDER);
    }
    
    public void setFile(final File file, final FileSelectionState state, final boolean useAlternateStyle, final boolean isSelected) {
        this.fileLabel.setText(file.getName());
        this.fileLabel.setIcon(file.isFile() ? FileExplorerCell.FILE_ICON : FileExplorerCell.FOLDER_ICON);
        this.checkBox.setState(state);
        Color foregroundColor = FileExplorerCell.DEFAULT_FOREGROUND;
        Color backgroundColor = useAlternateStyle ? FileExplorerCell.DEFAULT_BACKGROUND : FileExplorerCell.ALTERNATE_BACKGROUND;
        if (isSelected) {
            backgroundColor = FileExplorerCell.SELECTED_BACKGROUND;
            foregroundColor = FileExplorerCell.SELECTED_FOREGROUND;
        }
        else if (state.equals(FileSelectionState.SELECTED)) {
            foregroundColor = FileExplorerCell.CHECKED_FOREGROUND;
        }
        this.setBackground(backgroundColor);
        this.fileLabel.setForeground(foregroundColor);
    }
    
    static {
        FOLDER_ICON = Sharklet.getImage("icon_folder.png");
        FILE_ICON = Sharklet.getImage("icon_file.png");
        DEFAULT_BACKGROUND = Color.decode("#ffffff");
        ALTERNATE_BACKGROUND = Color.decode("#edf3fe");
        SELECTED_BACKGROUND = Color.decode("#1879D9");
        SELECTED_FOREGROUND = Color.decode("#ffffff");
        DEFAULT_FOREGROUND = Color.decode("#222222");
        CHECKED_FOREGROUND = Color.decode("#3875D7");
        PANEL_BORDER = BorderFactory.createEmptyBorder(5, 10, 5, 5);
    }
}
