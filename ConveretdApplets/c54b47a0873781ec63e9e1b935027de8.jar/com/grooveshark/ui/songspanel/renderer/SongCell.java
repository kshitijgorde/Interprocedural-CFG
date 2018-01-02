// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.songspanel.renderer;

import com.grooveshark.sharklet.Sharklet;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.FontMetrics;
import java.io.File;
import com.grooveshark.sharklet.Song;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import com.grooveshark.ui.table.CellStyle;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.grooveshark.player.MusicPlayer;
import com.grooveshark.ui.component.Label;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import com.grooveshark.ui.table.AbstractCell;

public class SongCell extends AbstractCell
{
    private static final String STOP_TEXT;
    private static final String LISTEN_TEXT;
    public static final String REMOVE_TEXT;
    private static final String BAD_SAMPLE_RATE;
    private static final String MISSING_METADATA;
    private static final String LOW_QUALITY;
    private static final ImageIcon LISTEN_ICON;
    private static final ImageIcon REMOVE_ICON;
    private static final ImageIcon SELECTED_LISTEN_ICON;
    private static final ImageIcon SELECTED_REMOVE_ICON;
    private static final ImageIcon SELECTED_STOP_ICON;
    private static final ImageIcon STOP_ICON;
    private static final ImageIcon PROBLEM_STOP_ICON;
    private static final ImageIcon PROBLEM_LISTEN_ICON;
    private static final ImageIcon PROBLEM_REMOVE_ICON;
    private static final Color DEFAULT_BACKGROUND;
    private static final Color ALTERNATE_BACKGROUND;
    private static final Color SELECTED_BACKGROUND;
    private static final Color SELECTED_FOREGROUND;
    private static final Color WARNING_FOREGROUND;
    private static final Color DEFAULT_FOREGROUND;
    private static final Color SECONDARY_FOREGROUND;
    private static final Color PROBLEM_SECONDARY_FOREGROUND;
    private static final Color PROBLEM_BACKGROUND;
    private static final Color PROBLEM_PRIMARY_FOREGROUND;
    private static final long serialVersionUID = 2162264639614822169L;
    private static final int BUTTON_WIDTH = 250;
    private static Font DEFAULT_FONT;
    private static Font HEADER_FONT;
    boolean hasProblems;
    private JLabel warningLabel;
    private JLabel headerLabel;
    private JLabel artistAlbumLabel;
    private JLabel songTitleLabel;
    private Label remove;
    private Label play;
    private MusicPlayer player;
    private boolean isBeingPlayed;
    
    public SongCell(final MusicPlayer player) {
        this.player = player;
        this.setLayout(new BorderLayout(0, 0));
        SongCell.HEADER_FONT = this.getFont().deriveFont(1);
        SongCell.DEFAULT_FONT = this.getFont().deriveFont(11.0f);
        this.setupUi();
        this.setStyle(CellStyle.DEFAULT);
    }
    
    public void addRemoveListener(final ActionListener listener) {
        this.remove.addActionListener(listener);
    }
    
    public void addListenListener(final ActionListener listener) {
        this.play.addActionListener(listener);
    }
    
    public void setStyle(final CellStyle style) {
        if (style.equals(CellStyle.SELECTED)) {
            this.headerLabel.setForeground(SongCell.SELECTED_FOREGROUND);
            this.artistAlbumLabel.setForeground(SongCell.SELECTED_FOREGROUND);
            this.songTitleLabel.setForeground(SongCell.SELECTED_FOREGROUND);
            this.warningLabel.setForeground(SongCell.SELECTED_FOREGROUND);
            this.remove.setForeground(SongCell.SELECTED_FOREGROUND);
            this.remove.setIcon(SongCell.SELECTED_REMOVE_ICON);
            this.play.setForeground(SongCell.SELECTED_FOREGROUND);
            this.play.setIcon(this.isBeingPlayed ? SongCell.SELECTED_STOP_ICON : SongCell.SELECTED_LISTEN_ICON);
            this.play.setText(this.isBeingPlayed ? SongCell.STOP_TEXT : SongCell.LISTEN_TEXT);
            this.setBackground(SongCell.SELECTED_BACKGROUND);
        }
        else if (this.hasProblems) {
            this.headerLabel.setForeground(SongCell.PROBLEM_PRIMARY_FOREGROUND);
            this.artistAlbumLabel.setForeground(SongCell.PROBLEM_SECONDARY_FOREGROUND);
            this.songTitleLabel.setForeground(SongCell.PROBLEM_SECONDARY_FOREGROUND);
            this.warningLabel.setForeground(SongCell.PROBLEM_SECONDARY_FOREGROUND);
            this.remove.setForeground(SongCell.PROBLEM_SECONDARY_FOREGROUND);
            this.remove.setIcon(SongCell.PROBLEM_REMOVE_ICON);
            this.play.setForeground(SongCell.PROBLEM_SECONDARY_FOREGROUND);
            this.play.setIcon(this.isBeingPlayed ? SongCell.PROBLEM_STOP_ICON : SongCell.PROBLEM_LISTEN_ICON);
            this.play.setText(this.isBeingPlayed ? SongCell.STOP_TEXT : SongCell.LISTEN_TEXT);
            this.setBackground(SongCell.PROBLEM_BACKGROUND);
        }
        else {
            this.headerLabel.setForeground(SongCell.DEFAULT_FOREGROUND);
            this.artistAlbumLabel.setForeground(SongCell.SECONDARY_FOREGROUND);
            this.songTitleLabel.setForeground(SongCell.SECONDARY_FOREGROUND);
            this.warningLabel.setForeground(SongCell.WARNING_FOREGROUND);
            this.remove.setForeground(SongCell.SECONDARY_FOREGROUND);
            this.remove.setIcon(SongCell.REMOVE_ICON);
            this.play.setForeground(SongCell.SECONDARY_FOREGROUND);
            this.play.setIcon(this.isBeingPlayed ? SongCell.STOP_ICON : SongCell.LISTEN_ICON);
            this.play.setText(this.isBeingPlayed ? SongCell.STOP_TEXT : SongCell.LISTEN_TEXT);
            if (style.equals(CellStyle.DEFAULT)) {
                this.setBackground(SongCell.DEFAULT_BACKGROUND);
            }
            else {
                this.setBackground(SongCell.ALTERNATE_BACKGROUND);
            }
        }
    }
    
    public void setValue(final Object value) {
        final Song song = (Song)value;
        this.updateWarning(song);
        this.updateSongPanel(song);
        final File file = song.getFile();
        this.isBeingPlayed = this.player.isPlaying(file);
        this.hasProblems = song.isInvalid();
    }
    
    private void updateSongPanel(final Song song) {
        String headerText = song.getFile().getName();
        final int maximumHeaderWidth = this.getPreferredSize().width - 250;
        headerText = this.truncateText(headerText, this.headerLabel.getFont(), maximumHeaderWidth);
        this.headerLabel.setText(headerText);
        final String artist = song.getArtist();
        final String album = song.getAlbum();
        String artistAlbum = String.format("%s - %s", artist, album);
        if (artist.equals("") && album.equals("")) {
            artistAlbum = "";
        }
        final int maximumSize = this.getPreferredSize().width - (this.warningLabel.getPreferredSize().width + 35);
        artistAlbum = this.truncateText(artistAlbum, this.artistAlbumLabel.getFont(), maximumSize);
        this.artistAlbumLabel.setText(artistAlbum);
        String songTitle = song.getTitle();
        if (songTitle.equals("")) {
            songTitle = " ";
        }
        songTitle = this.truncateText(songTitle, this.getFont(), maximumSize - 10);
        this.songTitleLabel.setText(songTitle);
    }
    
    private void updateWarning(final Song song) {
        if (song.getBitrate() < 128L) {
            this.warningLabel.setText(SongCell.LOW_QUALITY);
        }
        else if (song.getSampleRate() != 44100) {
            this.warningLabel.setText(SongCell.BAD_SAMPLE_RATE);
        }
        else if (song.isInvalid()) {
            this.warningLabel.setText(SongCell.MISSING_METADATA);
        }
        else {
            this.warningLabel.setText("");
        }
    }
    
    private String truncateText(final String text, final Font font, final int maximumWidth) {
        String newString = text;
        final FontMetrics metrics = this.getFontMetrics(font);
        final float width = metrics.stringWidth(text);
        final int extraWidth = metrics.stringWidth("...");
        if (width > maximumWidth) {
            final float availableWidth = maximumWidth - extraWidth;
            final float averageCharacterWidth = width / text.length();
            final int index = Math.round(availableWidth / averageCharacterWidth);
            newString = text.substring(0, index) + "...";
        }
        return newString;
    }
    
    private void setupUi() {
        final JPanel textPanel = this.createSongInfoPanel();
        final JPanel warningPanel = this.createWarningPanel();
        final JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.add(textPanel, "West");
        mainPanel.add(warningPanel, "East");
        mainPanel.setOpaque(false);
        final JPanel buttons = this.createButtonsPanel();
        final JPanel headerPanel = new JPanel(new BorderLayout(0, 0));
        headerPanel.add(this.headerLabel, "West");
        headerPanel.add(buttons, "East");
        headerPanel.setOpaque(false);
        this.add(headerPanel, "North");
        this.add(mainPanel, "Center");
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    private JPanel createSongInfoPanel() {
        (this.headerLabel = new JLabel()).setFont(SongCell.HEADER_FONT);
        (this.artistAlbumLabel = new JLabel()).setFont(SongCell.DEFAULT_FONT);
        (this.songTitleLabel = new JLabel()).setFont(SongCell.DEFAULT_FONT);
        final JPanel textPanel = new JPanel(new FlowLayout(0, 0, 0));
        final BoxLayout layout = new BoxLayout(textPanel, 1);
        textPanel.setLayout(layout);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(this.songTitleLabel);
        textPanel.add(this.artistAlbumLabel);
        textPanel.setOpaque(false);
        return textPanel;
    }
    
    private JPanel createWarningPanel() {
        (this.warningLabel = new JLabel()).setFont(SongCell.DEFAULT_FONT);
        final JPanel warningPanel = new JPanel(new BorderLayout());
        warningPanel.add(this.warningLabel, "South");
        warningPanel.setOpaque(false);
        return warningPanel;
    }
    
    private JPanel createButtonsPanel() {
        this.play = this.createPlayLabel();
        final ImageIcon removeIcon = SongCell.REMOVE_ICON;
        this.remove = new Label(SongCell.REMOVE_TEXT, removeIcon);
        final JPanel buttons = new JPanel(new BorderLayout(10, 0));
        buttons.add(this.play, "West");
        buttons.add(this.remove, "East");
        buttons.setOpaque(false);
        return buttons;
    }
    
    private Label createPlayLabel() {
        final ImageIcon playIcon = SongCell.LISTEN_ICON;
        final Label play = new Label(SongCell.LISTEN_TEXT, playIcon);
        play.setFont(play.getFont().deriveFont(12.0f));
        return play;
    }
    
    static {
        STOP_TEXT = Sharklet.getText("STOP_BUTTON");
        LISTEN_TEXT = Sharklet.getText("LISTEN_BUTTON");
        REMOVE_TEXT = Sharklet.getText("REMOVE_BUTTON");
        BAD_SAMPLE_RATE = Sharklet.getText("UNSUPPORTED_SAMPLERATE");
        MISSING_METADATA = Sharklet.getText("MISSING_METADATA");
        LOW_QUALITY = Sharklet.getText("UNSUPPORTED_BITRATE");
        LISTEN_ICON = Sharklet.getImage("icon_play_small_blue.png");
        REMOVE_ICON = Sharklet.getImage("icon_close_small_blue.png");
        SELECTED_LISTEN_ICON = Sharklet.getImage("icon_play_small_white.png");
        SELECTED_REMOVE_ICON = Sharklet.getImage("icon_close_small_white.png");
        SELECTED_STOP_ICON = Sharklet.getImage("icon_stop_white.png");
        STOP_ICON = Sharklet.getImage("icon_stop_blue.png");
        PROBLEM_STOP_ICON = Sharklet.getImage("icon_stop_red.png");
        PROBLEM_LISTEN_ICON = Sharklet.getImage("icon_play_small_red.png");
        PROBLEM_REMOVE_ICON = Sharklet.getImage("icon_close_small_red.png");
        DEFAULT_BACKGROUND = Color.decode("#ffffff");
        ALTERNATE_BACKGROUND = Color.decode("#edf3fe");
        SELECTED_BACKGROUND = Color.decode("#1879D9");
        SELECTED_FOREGROUND = Color.decode("#ffffff");
        WARNING_FOREGROUND = Color.decode("#ff0000");
        DEFAULT_FOREGROUND = Color.decode("#000000");
        SECONDARY_FOREGROUND = Color.decode("#4f78c3");
        PROBLEM_SECONDARY_FOREGROUND = Color.decode("#B50505");
        PROBLEM_BACKGROUND = Color.decode("#FFDADA");
        PROBLEM_PRIMARY_FOREGROUND = Color.decode("#E35151");
    }
}
