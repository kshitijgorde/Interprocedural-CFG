// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.scene;

import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JComponent;

public final class Scene extends Node
{
    private static Scene currentScene;
    
    public static void setCurrentScene(final Scene currentScene) {
        Scene.currentScene = currentScene;
    }
    
    public static Scene getCurrentScene() {
        return Scene.currentScene;
    }
    
    public Scene(final int n, final int n2) {
        super(new RootJPanel());
        this.setId("scene");
        this.setSize(800.0f, 600.0f);
        this.setPosition(400.0f, 300.0f);
    }
    
    @Override
    protected final void refreshDisplayBounds() {
        super.refreshDisplayBounds();
        final JComponent component;
        if ((component = this.getComponent()) != null) {
            component.setPreferredSize(new Dimension(component.getBounds().width, component.getBounds().height));
        }
    }
    
    static final class RootJPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;
        private boolean hasBackground;
        
        public RootJPanel() {
            this.setLayout(null);
            this.setBackground(Color.BLACK);
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public final void componentResized(final ComponentEvent componentEvent) {
                    final Component component = componentEvent.getComponent();
                    RootJPanel.this.setSize(component.getWidth(), component.getHeight());
                }
            });
        }
        
        @Override
        public final void setBackground(final Color background) {
            super.setBackground(background);
            this.hasBackground = (background != null);
        }
        
        @Override
        public final void paint(final Graphics graphics) {
            super.paint(graphics);
        }
        
        @Override
        protected final void paintComponent(final Graphics graphics) {
            if (this.hasBackground) {
                super.paintComponent(graphics);
            }
        }
    }
}
