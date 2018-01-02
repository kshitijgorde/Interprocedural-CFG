// 
// Decompiled by Procyon v0.5.30
// 

package abcynth;

import abc.notation.NoteAbstract;
import abc.midi.PlayerStateChangeEvent;
import abc.midi.TempoChangeEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import abc.notation.Tune;
import javax.swing.JButton;
import abc.midi.TunePlayer;
import javax.swing.JSlider;
import javax.swing.JLabel;
import abc.midi.TunePlayerListenerInterface;
import javax.swing.JToolBar;

public class PlayerToolBar extends JToolBar implements TunePlayerListenerInterface
{
    private JLabel m_tempoField;
    private JSlider m_tempoSlider;
    private TunePlayer m_player;
    private JButton m_playButton;
    private Tune m_tune;
    
    public PlayerToolBar(final TunePlayer player) {
        super("Tune Player");
        this.m_tempoField = new JLabel("180");
        this.m_tempoSlider = new JSlider(0, 300, 180);
        this.m_player = null;
        this.m_playButton = null;
        this.m_tune = null;
        this.m_player = player;
        this.setLayout(new BorderLayout());
        this.add(this.m_playButton = new JButton("PLAY | STOP"), "East");
        this.add(this.m_tempoSlider, "Center");
        this.add(this.m_tempoField, "West");
        final ChangeListener tempoListener = new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                PlayerToolBar.this.m_player.setTempo(PlayerToolBar.this.m_tempoSlider.getValue());
                PlayerToolBar.this.m_tempoField.setText(new Integer(PlayerToolBar.this.m_tempoSlider.getValue()).toString());
            }
        };
        this.m_tempoSlider.addChangeListener(tempoListener);
        player.addListener(this);
    }
    
    public void tempoChanged(final TempoChangeEvent e) {
        this.m_tempoSlider.setValue(e.getNewTempoValue());
    }
    
    public void playBegin(final PlayerStateChangeEvent e) {
    }
    
    public void notePlayed(final NoteAbstract e) {
    }
    
    public void playEnd(final PlayerStateChangeEvent e) {
    }
    
    public void partPlayed(final int begin, final int end) {
    }
    
    public void setTune(final Tune tune) {
        this.m_tune = tune;
    }
    
    public JButton getPlayButton() {
        return this.m_playButton;
    }
}
