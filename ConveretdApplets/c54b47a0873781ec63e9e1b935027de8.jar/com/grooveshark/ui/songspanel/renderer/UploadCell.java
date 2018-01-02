// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.songspanel.renderer;

import com.grooveshark.sharklet.Sharklet;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import com.grooveshark.sharklet.Song;
import com.grooveshark.ui.table.CellStyle;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import com.grooveshark.net.uploader.UploadState;
import java.awt.Font;
import java.awt.Color;
import com.grooveshark.ui.table.AbstractCell;

public class UploadCell extends AbstractCell
{
    private static final long serialVersionUID = 2162264639614822169L;
    private static final String FAILED;
    private static final String COMPLETE;
    private static final String SKIPPED;
    private static final String PENDING;
    private static final Color DEFAULT_BACKGROUND;
    private static final Color ALTERNATE_BACKGROUND;
    private static final Color PRIMARY_FOREGROUND;
    private static final Color SECONDARY_FOREGROUND;
    private static final Color GOOD_BACKGROUND;
    private static final Color GOOD_PRIMARY_FOREGROUND;
    private static final Color GOOD_SECONDARY_FOREGROUND;
    private static final Color PROBLEM_BACKGROUND;
    private static final Color PROBLEM_PRIMARY_FOREGROUND;
    private static final Color PROBLEM_SECONDARY_FOREGROUND;
    private static Font HEADER_FONT;
    private static Font DEFAULT_FONT;
    private UploadState state;
    private JLabel headerLabel;
    private JLabel artistAlbumLabel;
    private JLabel songTitleLabel;
    private JLabel status;
    
    public UploadCell() {
        this.setLayout(new BorderLayout(0, 0));
        UploadCell.HEADER_FONT = this.getFont().deriveFont(1);
        UploadCell.DEFAULT_FONT = this.getFont().deriveFont(11.0f);
        this.state = UploadState.QUEUED;
        this.setupUi();
        this.setStyle(CellStyle.DEFAULT);
    }
    
    public void setStyle(final CellStyle style) {
        this.headerLabel.setForeground(UploadCell.PRIMARY_FOREGROUND);
        this.artistAlbumLabel.setForeground(UploadCell.SECONDARY_FOREGROUND);
        this.songTitleLabel.setForeground(UploadCell.SECONDARY_FOREGROUND);
        if (this.state.equals(UploadState.COMPLETE)) {
            this.headerLabel.setForeground(UploadCell.GOOD_PRIMARY_FOREGROUND);
            this.artistAlbumLabel.setForeground(UploadCell.GOOD_SECONDARY_FOREGROUND);
            this.songTitleLabel.setForeground(UploadCell.GOOD_SECONDARY_FOREGROUND);
            this.status.setForeground(UploadCell.GOOD_PRIMARY_FOREGROUND);
            this.setBackground(UploadCell.GOOD_BACKGROUND);
        }
        else if (this.state.equals(UploadState.FAILED)) {
            this.headerLabel.setForeground(UploadCell.PROBLEM_PRIMARY_FOREGROUND);
            this.artistAlbumLabel.setForeground(UploadCell.PROBLEM_SECONDARY_FOREGROUND);
            this.songTitleLabel.setForeground(UploadCell.PROBLEM_SECONDARY_FOREGROUND);
            this.status.setForeground(UploadCell.PROBLEM_PRIMARY_FOREGROUND);
            this.setBackground(UploadCell.PROBLEM_BACKGROUND);
        }
        else {
            this.headerLabel.setForeground(UploadCell.PRIMARY_FOREGROUND);
            this.artistAlbumLabel.setForeground(UploadCell.SECONDARY_FOREGROUND);
            this.songTitleLabel.setForeground(UploadCell.SECONDARY_FOREGROUND);
            this.status.setForeground(UploadCell.PRIMARY_FOREGROUND);
            this.setBackground(UploadCell.DEFAULT_BACKGROUND);
            if (style.equals(CellStyle.DEFAULT)) {
                this.setBackground(UploadCell.DEFAULT_BACKGROUND);
            }
            else {
                this.setBackground(UploadCell.ALTERNATE_BACKGROUND);
            }
        }
    }
    
    public void setValue(final Object value) {
        final Song song = (Song)value;
        final String headerText = song.getFile().getName();
        this.headerLabel.setText(headerText);
        final String artist = song.getArtist();
        final String album = song.getAlbum();
        final String artistAlbum = String.format("%s - %s", artist, album);
        this.artistAlbumLabel.setText(artistAlbum);
        final String songTitle = song.getTitle();
        this.songTitleLabel.setText(songTitle);
        this.state = song.getState();
        if (this.state.equals(UploadState.COMPLETE)) {
            this.status.setText(UploadCell.COMPLETE);
        }
        else if (this.state.equals(UploadState.FAILED)) {
            if (song.isInvalid()) {
                this.status.setText(UploadCell.SKIPPED);
            }
            else {
                this.status.setText(UploadCell.FAILED);
            }
        }
        else if (this.state.equals(UploadState.UPLOADING)) {
            this.status.setText(song.getProgress() + "%");
        }
        else if (this.state.equals(UploadState.QUEUED)) {
            this.status.setText(UploadCell.PENDING);
        }
    }
    
    private void setupUi() {
        (this.headerLabel = new JLabel()).setFont(UploadCell.HEADER_FONT);
        (this.artistAlbumLabel = new JLabel()).setFont(UploadCell.DEFAULT_FONT);
        (this.songTitleLabel = new JLabel()).setFont(UploadCell.DEFAULT_FONT);
        final JPanel textPanel = new JPanel(new FlowLayout(0, 0, 0));
        final BoxLayout layout = new BoxLayout(textPanel, 1);
        textPanel.setLayout(layout);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(this.songTitleLabel);
        textPanel.add(this.artistAlbumLabel);
        textPanel.setOpaque(false);
        (this.status = new JLabel()).setHorizontalTextPosition(2);
        this.status.setVerticalTextPosition(0);
        final JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.add(textPanel, "West");
        mainPanel.add(this.status, "East");
        mainPanel.setOpaque(false);
        final JPanel headerPanel = new JPanel(new BorderLayout(0, 0));
        headerPanel.add(this.headerLabel, "West");
        headerPanel.setOpaque(false);
        this.add(headerPanel, "North");
        this.add(mainPanel, "Center");
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    static {
        FAILED = Sharklet.getText("FAILED");
        COMPLETE = Sharklet.getText("COMPLETE");
        SKIPPED = Sharklet.getText("SKIPPED");
        PENDING = Sharklet.getText("PENDING");
        DEFAULT_BACKGROUND = Color.decode("#ffffff");
        ALTERNATE_BACKGROUND = Color.decode("#edf3fe");
        PRIMARY_FOREGROUND = Color.decode("#000000");
        SECONDARY_FOREGROUND = Color.decode("#4f78c3");
        GOOD_BACKGROUND = Color.decode("#EBFED7");
        GOOD_PRIMARY_FOREGROUND = Color.decode("#3F7506");
        GOOD_SECONDARY_FOREGROUND = Color.decode("#94B96C");
        PROBLEM_BACKGROUND = Color.decode("#FFDADA");
        PROBLEM_PRIMARY_FOREGROUND = Color.decode("#E35151");
        PROBLEM_SECONDARY_FOREGROUND = Color.decode("#B50505");
    }
}
