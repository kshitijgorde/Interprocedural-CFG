// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.songeditor;

import javax.swing.event.DocumentEvent;
import com.grooveshark.sharklet.Song;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFormattedTextField;
import com.grooveshark.ui.component.AutoCompleteField;
import com.grooveshark.sharklet.Genres;
import com.grooveshark.sharklet.Sharklet;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.event.DocumentListener;
import javax.swing.JPanel;

public class SongEditorPanel extends JPanel implements DocumentListener
{
    private static final String FREQUENCY;
    private static final String BITRATE;
    private static final String TITLE;
    private static final String YEAR;
    private static final String ARTIST;
    private static final String ALBUM;
    private static final String GENRE;
    private static final String TRACK;
    private static final long serialVersionUID = -3102638755613662651L;
    private static final Color BACKGROUND_COLOR;
    private static final Color FOREGROUND_COLOR;
    private boolean multipleFileEdit;
    private SongEditorListener oldListener;
    private SongEditorListener listener;
    private JLabel sampleRate;
    private JLabel bitrate;
    private JLabel titleLabel;
    private JLabel artistLabel;
    private JLabel albumLabel;
    private JTextField title;
    private JTextField artist;
    private JTextField album;
    private JTextField genre;
    private JTextField year;
    private JTextField track;
    
    public SongEditorPanel() {
        this.createFormPanel();
        this.setBackground(SongEditorPanel.BACKGROUND_COLOR);
    }
    
    public void setListener(final SongEditorListener listener) {
        this.listener = listener;
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.title.setEnabled(enabled);
        this.artist.setEnabled(enabled);
        this.album.setEnabled(enabled);
        this.genre.setEnabled(enabled);
        this.year.setEnabled(enabled);
        this.track.setEnabled(enabled);
    }
    
    private void setWarning(final JComponent component, final boolean enabled) {
        final Color foreground = (!this.multipleFileEdit && enabled) ? Color.RED : SongEditorPanel.FOREGROUND_COLOR;
        component.setForeground(foreground);
    }
    
    private void createFormPanel() {
        this.setLayout(new GridLayout(6, 1, 0, 10));
        this.sampleRate = new JLabel();
        this.bitrate = new JLabel();
        final JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.add(this.createLabel(SongEditorPanel.FREQUENCY, this.sampleRate));
        headerPanel.add(this.createLabel(SongEditorPanel.BITRATE, this.bitrate));
        headerPanel.setOpaque(false);
        this.add(headerPanel);
        this.title = new JTextField();
        this.titleLabel = new JLabel(SongEditorPanel.TITLE);
        this.add(this.createFieldLabel(this.titleLabel, this.title, new ListenerEvent() {
            public void update() {
                SongEditorPanel.this.listener.titleChanged(SongEditorPanel.this.title.getText());
            }
        }));
        final Frame frame = Sharklet.findParentFrame();
        this.artist = new JTextField();
        this.artistLabel = new JLabel(SongEditorPanel.ARTIST);
        this.add(this.createFieldLabel(this.artistLabel, this.artist, new ListenerEvent() {
            public void update() {
                SongEditorPanel.this.listener.artistChanged(SongEditorPanel.this.artist.getText());
            }
        }));
        this.album = new JTextField();
        this.albumLabel = new JLabel(SongEditorPanel.ALBUM);
        this.add(this.createFieldLabel(this.albumLabel, this.album, new ListenerEvent() {
            public void update() {
                SongEditorPanel.this.listener.albumChanged(SongEditorPanel.this.album.getText());
            }
        }));
        this.genre = new AutoCompleteField(Genres.getGenres(), frame);
        final JLabel genreLabel = new JLabel(SongEditorPanel.GENRE);
        this.add(this.createFieldLabel(genreLabel, this.genre, new ListenerEvent() {
            public void update() {
                SongEditorPanel.this.listener.genreChanged(SongEditorPanel.this.genre.getText());
            }
        }));
        final JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
        panel.setOpaque(false);
        this.year = new JFormattedTextField();
        final JLabel yearLabel = new JLabel(SongEditorPanel.YEAR);
        panel.add(this.createFieldLabel(yearLabel, this.year, new ListenerEvent() {
            public void update() {
                SongEditorPanel.this.listener.yearChanged(SongEditorPanel.this.year.getText());
            }
        }));
        this.track = new JFormattedTextField();
        final JLabel trackLabel = new JLabel(SongEditorPanel.TRACK);
        panel.add(this.createFieldLabel(trackLabel, this.track, new ListenerEvent() {
            public void update() {
                SongEditorPanel.this.listener.trackChanged(SongEditorPanel.this.track.getText());
            }
        }));
        this.add(panel);
    }
    
    public void lock(final boolean isLocked) {
        this.clear();
        final boolean enabled = !isLocked;
        this.title.setEnabled(enabled);
        this.artist.setEnabled(enabled);
        this.album.setEnabled(enabled);
        this.genre.setEnabled(enabled);
        this.year.setEnabled(enabled);
        this.track.setEnabled(enabled);
    }
    
    private void clear() {
        this.disableDocumentListener();
        final String EMPTY = "";
        this.bitrate.setText("");
        this.sampleRate.setText("");
        this.title.setText("");
        this.artist.setText("");
        this.album.setText("");
        this.genre.setText("");
        this.year.setText("");
        this.track.setText("");
        this.disableWarnings();
        this.enableDocumentListener();
    }
    
    private void disableWarnings() {
        this.setWarning(this.titleLabel, false);
        this.setWarning(this.artistLabel, false);
        this.setWarning(this.albumLabel, false);
    }
    
    private JPanel createLabel(final String fieldName, final JComponent field) {
        final JLabel fieldLabel = new JLabel(fieldName);
        fieldLabel.setLabelFor(field);
        fieldLabel.setForeground(Color.GRAY);
        field.setForeground(Color.GRAY);
        field.setFont(field.getFont().deriveFont(12.0f));
        final JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.add(fieldLabel, "West");
        panel.add(field, "East");
        panel.setOpaque(false);
        return panel;
    }
    
    private JPanel createFieldLabel(final JLabel fieldLabel, final JTextField field, final DocumentListener listener) {
        field.getDocument().addDocumentListener(listener);
        field.getDocument().addDocumentListener(this);
        fieldLabel.setForeground(SongEditorPanel.FOREGROUND_COLOR);
        fieldLabel.setFont(fieldLabel.getFont().deriveFont(1).deriveFont(12.0f));
        fieldLabel.setLabelFor(field);
        final JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(fieldLabel);
        panel.add(field);
        panel.setOpaque(false);
        return panel;
    }
    
    private void disableDocumentListener() {
        this.oldListener = this.listener;
        this.listener = new SongEditorListener() {
            public void yearChanged(final String year) {
            }
            
            public void trackChanged(final String track) {
            }
            
            public void titleChanged(final String title) {
            }
            
            public void genreChanged(final String genre) {
            }
            
            public void artistChanged(final String artist) {
            }
            
            public void albumChanged(final String album) {
            }
        };
    }
    
    private void enableDocumentListener() {
        this.setListener(this.oldListener);
    }
    
    public void setMultipleEdit(final boolean isEnabled) {
        this.clear();
        this.multipleFileEdit = isEnabled;
        this.updateWarnings();
        if (this.isEnabled()) {
            this.bitrate.setVisible(!isEnabled);
            this.sampleRate.setVisible(!isEnabled);
            this.title.setEnabled(!isEnabled);
            this.track.setEnabled(!isEnabled);
        }
    }
    
    public void setSong(final Song song) {
        this.disableDocumentListener();
        this.bitrate.setText(song.getBitrate() + " Kbps");
        this.sampleRate.setText(song.getSampleRate() + " Hz");
        this.title.setText(song.getTitle());
        this.artist.setText(song.getArtist());
        this.album.setText(song.getAlbum());
        this.genre.setText(song.getGenre());
        this.year.setText(song.getYear() + "");
        this.track.setText(song.getTrackNumber() + "");
        final boolean lowQualityFile = song.getBitrate() < 128L;
        this.setWarning(this.bitrate, lowQualityFile);
        final boolean badSampleRate = song.getSampleRate() != 44100;
        this.setWarning(this.sampleRate, badSampleRate);
        this.enableDocumentListener();
    }
    
    private void updateWarnings() {
        final boolean noTitle = this.title.getText().trim().equals("");
        this.setWarning(this.titleLabel, noTitle);
        final boolean noArtist = this.artist.getText().trim().equals("");
        this.setWarning(this.artistLabel, noArtist);
        final boolean noAlbum = this.album.getText().trim().equals("");
        this.setWarning(this.albumLabel, noAlbum);
    }
    
    public void changedUpdate(final DocumentEvent e) {
        this.updateWarnings();
    }
    
    public void insertUpdate(final DocumentEvent e) {
        this.updateWarnings();
    }
    
    public void removeUpdate(final DocumentEvent e) {
        this.updateWarnings();
    }
    
    static {
        FREQUENCY = Sharklet.getText("FREQUENCY");
        BITRATE = Sharklet.getText("BITRATE");
        TITLE = Sharklet.getText("TITLE");
        YEAR = Sharklet.getText("YEAR");
        ARTIST = Sharklet.getText("ARTIST");
        ALBUM = Sharklet.getText("ALBUM");
        GENRE = Sharklet.getText("GENRE");
        TRACK = Sharklet.getText("TRACK");
        BACKGROUND_COLOR = Color.decode("#EEEEEE");
        FOREGROUND_COLOR = Color.decode("#818181");
    }
}
