import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.URL;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NQPhotoAlbum extends Applet
{
    private Panel buttonPanel;
    private Panel picturePanel;
    private PhotoCanvas canvas;
    private Button bck;
    private Button fwd;
    private Button fit;
    private Label showing;
    private TextField txt;
    private Image[] photos;
    private String[] imageNames;
    private URL url;
    private int currIdx;
    private String numPhotosString;
    private int num_photos;
    private String image_loc;
    
    public NQPhotoAlbum() {
        this.buttonPanel = new Panel();
        this.picturePanel = new Panel();
        this.canvas = new PhotoCanvas();
        this.bck = new Button("Back");
        this.fwd = new Button("Next");
        this.fit = new Button("Fit Image");
        this.showing = new Label("     Showing:");
        this.txt = new TextField("None.", 10);
        this.currIdx = -1;
    }
    
    public String getAppletInfo() {
        return new String("by Jim Effinger, version 1.0, Copyright (c) 2002.");
    }
    
    public void init() {
        this.loadParameters();
        this.photos = new Image[this.num_photos];
        this.setLayout(new BorderLayout(2, 2));
        this.add(this.picturePanel, "Center");
        this.add(this.buttonPanel, "South");
        this.canvas.setSize(this.getSize());
        this.canvas.setBackground(Color.white);
        this.picturePanel.add(this.canvas);
        this.buttonPanel.add(this.bck);
        this.buttonPanel.add(this.fwd);
        this.buttonPanel.add(this.showing);
        this.showing.setBackground(Color.lightGray);
        this.buttonPanel.add(this.txt);
        this.buttonPanel.add(this.fit);
        this.txt.setBackground(Color.lightGray);
        this.txt.setEditable(false);
        this.setBackground(Color.lightGray);
        this.bck.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                NQPhotoAlbum.this.showPreviousImage();
            }
        });
        this.fwd.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                NQPhotoAlbum.this.showNextImage();
            }
        });
        this.fit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                NQPhotoAlbum.this.fitImage();
            }
        });
        this.canvas.setText("Click the [Next] button to begin.");
    }
    
    public void start() {
        this.canvas.setSize(this.picturePanel.getSize());
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    public void paint(final Graphics graphics) {
    }
    
    private void loadParameters() {
        this.num_photos = 1;
        this.numPhotosString = this.getParameter("NUM_PHOTOS");
        if (this.numPhotosString != null) {
            try {
                this.num_photos = Integer.parseInt(this.numPhotosString);
            }
            catch (Exception ex) {}
        }
        this.image_loc = this.getParameter("IMAGE_LOC");
        if (this.image_loc == null) {
            this.image_loc = ".";
        }
        this.imageNames = new String[this.num_photos];
        for (int i = 0; i < this.num_photos; ++i) {
            this.imageNames[i] = new String(this.getParameter(new String("IMAGE" + String.valueOf(i + 1))));
        }
    }
    
    public void showPreviousImage() {
        --this.currIdx;
        if (this.currIdx < 0) {
            this.currIdx = this.num_photos - 1;
        }
        this.displayPhoto(this.currIdx);
    }
    
    public void showNextImage() {
        ++this.currIdx;
        if (this.currIdx == this.num_photos) {
            this.currIdx = 0;
        }
        this.displayPhoto(this.currIdx);
    }
    
    private void displayPhoto(final int n) {
        try {
            if (this.photos[n] == null) {
                this.canvas.setText("Loading image, please wait...");
                this.photos[n] = this.getImage(this.getCodeBase(), this.image_loc + "/" + this.imageNames[n]);
            }
            this.canvas.setImage(this.photos[n]);
            this.txt.setText(new String(n + 1 + " of " + this.num_photos));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void fitImage() {
        if (this.currIdx >= 0 && this.photos[this.currIdx] != null) {
            final float n = this.canvas.getSize().height;
            final float n2 = this.canvas.getSize().width;
            final float n3 = this.photos[this.currIdx].getHeight(this.canvas);
            final float n4 = this.photos[this.currIdx].getWidth(this.canvas);
            float n5;
            if (n4 / n3 <= n2 / n) {
                n5 = n / n3;
            }
            else {
                n5 = n2 / n4;
            }
            final int n6 = (int)(n4 * n5);
            final Image scaledInstance = this.photos[this.currIdx].getScaledInstance(n6, (int)(n3 * n5), 4);
            this.canvas.setImage(scaledInstance);
            if (n6 != n4) {
                this.photos[this.currIdx] = scaledInstance;
            }
        }
    }
}
