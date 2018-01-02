// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import ch.randelshofer.gui.event.ChangeEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Polygon;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Color;
import ch.randelshofer.media.Player;
import ch.randelshofer.gui.event.ChangeListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class MovieControlAWT extends Panel implements ActionListener, ItemListener, ChangeListener
{
    private Player player_;
    private MovieSliderAWT slider_;
    private AbstractButton forwardButton_;
    private AbstractButton rewindButton_;
    private ToggleButton startButton_;
    private BoundedRangeModel boundedRangeModel_;
    
    public MovieControlAWT() {
        this.setForeground(Color.black);
        final Dimension dimension = new Dimension(15, 15);
        final Dimension dimension2 = new Dimension(13, 13);
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        (this.startButton_ = new ToggleButton()).setUnselectedIcon(new PolygonIcon(new Polygon(new int[] { 4, 7, 7, 4 }, new int[] { 2, 5, 6, 9 }, 4), dimension2));
        this.startButton_.setSelectedIcon(new PolygonIcon(new Polygon[] { new Polygon(new int[] { 3, 4, 4, 3 }, new int[] { 2, 2, 9, 9 }, 4), new Polygon(new int[] { 7, 8, 8, 7 }, new int[] { 2, 2, 9, 9 }, 4) }, dimension2));
        this.startButton_.addItemListener(this);
        this.startButton_.setPreferredSize(dimension);
        this.startButton_.setMinimumSize(dimension);
        layout.setConstraints(this.startButton_, new GridBagConstraints());
        this.add(this.startButton_);
        this.slider_ = new MovieSliderAWT();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(this.slider_, gridBagConstraints);
        this.add(this.slider_);
        (this.rewindButton_ = new AbstractButton()).setIcon(new PolygonIcon(new Polygon[] { new Polygon(new int[] { 4, 4, 1, 1 }, new int[] { 2, 9, 6, 5 }, 4), new Polygon(new int[] { 7, 8, 8, 7 }, new int[] { 2, 2, 9, 9 }, 4) }, dimension2));
        this.rewindButton_.setPreferredSize(dimension);
        this.rewindButton_.setMinimumSize(dimension);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        layout.setConstraints(this.rewindButton_, gridBagConstraints2);
        this.add(this.rewindButton_);
        this.rewindButton_.addActionListener(this);
        (this.forwardButton_ = new AbstractButton()).setIcon(new PolygonIcon(new Polygon[] { new Polygon(new int[] { 2, 3, 3, 2 }, new int[] { 2, 2, 9, 9 }, 4), new Polygon(new int[] { 6, 9, 9, 6 }, new int[] { 2, 5, 6, 9 }, 4) }, dimension2));
        this.forwardButton_.setPreferredSize(dimension);
        this.forwardButton_.setMinimumSize(dimension);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 3;
        layout.setConstraints(this.forwardButton_, gridBagConstraints3);
        this.add(this.forwardButton_);
        this.forwardButton_.addActionListener(this);
    }
    
    public synchronized void setPlayer(final Player player_) {
        if (this.player_ != null) {
            this.player_.removeChangeListener(this);
        }
        this.player_ = player_;
        this.boundedRangeModel_ = ((this.player_ == null) ? null : this.player_.getBoundedRangeModel());
        this.slider_.setModel(this.boundedRangeModel_);
        if (this.player_ != null) {
            this.startButton_.setSelected(this.player_.isActive());
            this.player_.addChangeListener(this);
        }
    }
    
    public void doLayout() {
        super.doLayout();
        final Point location = this.startButton_.getLocation();
        this.startButton_.setLocation(location.x - 1, location.y);
        final Point location2 = this.rewindButton_.getLocation();
        this.rewindButton_.setLocation(location2.x + 1, location2.y);
        final Rectangle bounds = this.slider_.getBounds();
        this.slider_.setBounds(bounds.x - 2, bounds.y, bounds.width + 4, bounds.height);
    }
    
    public void setProgressModel(final BoundedRangeModel progressModel) {
        this.slider_.setProgressModel(progressModel);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (this.boundedRangeModel_ != null) {
            final int value = this.boundedRangeModel_.getValue();
            if (source == this.forwardButton_) {
                this.boundedRangeModel_.setValue((value == this.boundedRangeModel_.getMaximum()) ? this.boundedRangeModel_.getMinimum() : (value + 1));
            }
            else if (source == this.rewindButton_) {
                this.boundedRangeModel_.setValue((value == this.boundedRangeModel_.getMinimum()) ? this.boundedRangeModel_.getMaximum() : (value - 1));
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (this.player_ == null) {
            return;
        }
        if (this.startButton_.isSelected() != this.player_.isActive()) {
            if (this.startButton_.isSelected()) {
                this.player_.start();
            }
            else {
                this.player_.stop();
            }
        }
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.startButton_.setSelected(this.player_.isActive());
    }
}
