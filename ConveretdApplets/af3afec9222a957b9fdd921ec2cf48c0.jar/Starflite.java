import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.MediaTracker;
import java.util.Random;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Starflite extends Applet implements Runnable
{
    sfModel m_modelStar;
    sfViewpoint m_viewpoint;
    final double STAR_VELOCITY = 13.0;
    final double STAR_Z0 = 40.0;
    final double STAR_XY_RANGE = 20.0;
    final double STAR_OMEGA_RANGE = 0.2;
    int m_nStarObjects;
    sfObject[] m_objectStar;
    double[] m_dRollRate;
    double[] m_dPitchRate;
    double[] m_dYawRate;
    double m_dStarXrange;
    double m_dStarYrange;
    Random m_random;
    MediaTracker m_mediaTracker;
    int m_nWindowWidth;
    int m_nWindowHeight;
    Thread m_threadAnim;
    long m_lStartTime;
    long m_lPrevTime;
    boolean m_bInitialized;
    
    public void start() {
        if (this.m_threadAnim == null) {
            (this.m_threadAnim = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_threadAnim != null) {
            this.m_threadAnim.stop();
            this.m_threadAnim = null;
        }
    }
    
    private sfModel starMaker(final int n, final double n2, final double n3, final double n4, final Color color) {
        final sfModel sfModel = new sfModel(n * 2 + 2, n * 4);
        sfModel.specifyVertex(0, new sfVector(0.0, 0.0, n4 / 2.0));
        sfModel.specifyVertex(1, new sfVector(0.0, 0.0, -n4 / 2.0));
        for (int i = 0; i < n; ++i) {
            final double n5 = i * 2 * 3.141592653589793 / n;
            sfModel.specifyVertex(i * 2 + 2, new sfVector(-n2 * Math.sin(n5), n2 * Math.cos(n5), 0.0));
            final double n6 = n5 + 6.283185307179586 / (n * 2);
            sfModel.specifyVertex(i * 2 + 3, new sfVector(-n3 * Math.sin(n6), n3 * Math.cos(n6), 0.0));
        }
        for (int j = 0; j < n * 2; ++j) {
            final int n7 = j + 2;
            int n8 = n7 + 1;
            if (n8 >= n * 2 + 2) {
                n8 = 2;
            }
            sfModel.specifyFace(j * 2, new sfFace(3, this.af(0, n8, n7), color, null));
            sfModel.specifyFace(j * 2 + 1, new sfFace(3, this.af(1, n7, n8), color, null));
        }
        return sfModel;
    }
    
    private void initialization() {
        this.m_random = new Random();
        final Dimension size = this.size();
        this.m_nWindowWidth = size.width;
        this.m_nWindowHeight = size.height;
        this.m_nStarObjects = 0;
        final String parameter = this.getParameter("stars");
        if (parameter != null) {
            this.m_nStarObjects = Integer.parseInt(parameter);
        }
        if (this.m_nStarObjects < 1) {
            this.m_nStarObjects = 5;
        }
        int int1 = 0;
        final String parameter2 = this.getParameter("bgcolor");
        if (parameter2 != null) {
            int1 = Integer.parseInt(parameter2, 16);
        }
        final Color colorBackgrnd = new Color(int1);
        int int2 = 16776960;
        final String parameter3 = this.getParameter("starcolor");
        if (parameter3 != null) {
            int2 = Integer.parseInt(parameter3, 16);
        }
        final Color color = new Color(int2);
        Image image = null;
        final String parameter4 = this.getParameter("background");
        if (parameter4 != null) {
            final Image image2 = this.getImage(this.getDocumentBase(), parameter4);
            this.m_mediaTracker.addImage(image2, 0);
            while (!this.m_mediaTracker.checkID(0, true)) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            final int width = image2.getWidth(null);
            final int height = image2.getHeight(null);
            image = this.createImage(this.m_nWindowWidth, this.m_nWindowHeight);
            final Graphics graphics = image.getGraphics();
            for (int i = 0; i < this.m_nWindowWidth; i += width) {
                for (int j = 0; j < this.m_nWindowHeight; j += height) {
                    graphics.drawImage(image2, i, j, null);
                }
            }
            graphics.dispose();
        }
        this.m_viewpoint = new sfViewpoint(0, 0, this.m_nWindowWidth, this.m_nWindowHeight, this);
        this.m_viewpoint.m_vector.m_dZ = -5.0;
        this.m_viewpoint.setFratio(0.4);
        this.m_viewpoint.m_colorBackgrnd = colorBackgrnd;
        if (image != null) {
            this.m_viewpoint.m_imageBackgrnd = image;
        }
        this.m_modelStar = this.starMaker(5, 2.0, 1.0, 2.0, color);
        this.m_dStarXrange = 20.0;
        this.m_dStarYrange = 20.0;
        if (this.m_nWindowWidth < this.m_nWindowHeight) {
            this.m_dStarXrange = this.m_nWindowWidth * 20.0 / this.m_nWindowHeight;
        }
        if (this.m_nWindowWidth > this.m_nWindowHeight) {
            this.m_dStarYrange = this.m_nWindowHeight * 20.0 / this.m_nWindowWidth;
        }
        this.m_objectStar = new sfObject[this.m_nStarObjects];
        this.m_dRollRate = new double[this.m_nStarObjects];
        this.m_dPitchRate = new double[this.m_nStarObjects];
        this.m_dYawRate = new double[this.m_nStarObjects];
        for (int k = 0; k < this.m_nStarObjects; ++k) {
            this.m_objectStar[k] = new sfObject(this.m_modelStar);
            this.m_objectStar[k].m_vector.m_dX = this.m_dStarXrange * (this.m_random.nextDouble() - 0.5);
            this.m_objectStar[k].m_vector.m_dY = this.m_dStarYrange * (this.m_random.nextDouble() - 0.5);
            this.m_objectStar[k].m_vector.m_dZ = 40.0 - (40.0 - this.m_viewpoint.m_vector.m_dZ) * k / this.m_nStarObjects;
            this.m_dRollRate[k] = (this.m_random.nextDouble() - 0.5) * 0.2;
            this.m_dPitchRate[k] = (this.m_random.nextDouble() - 0.5) * 0.2;
            this.m_dYawRate[k] = (this.m_random.nextDouble() - 0.5) * 0.2;
        }
        this.m_lStartTime = System.currentTimeMillis();
        this.m_lPrevTime = this.m_lStartTime;
        this.m_bInitialized = true;
    }
    
    private int[] af(final int n, final int n2, final int n3) {
        return new int[] { n, n2, n3 };
    }
    
    public void run() {
        if (!this.m_bInitialized) {
            this.initialization();
        }
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            final double n = (currentTimeMillis - this.m_lPrevTime) / 1000.0;
            this.m_lPrevTime = currentTimeMillis;
            for (int i = 0; i < this.m_nStarObjects; ++i) {
                final sfVector vector = this.m_objectStar[i].m_vector;
                vector.m_dZ -= n * 13.0;
                this.m_objectStar[i].m_matrix.rotateSelf(this.m_dRollRate[i], this.m_dPitchRate[i], this.m_dYawRate[i]);
                if (this.m_objectStar[i].m_vector.m_dZ < this.m_viewpoint.m_vector.m_dZ) {
                    this.m_objectStar[i].m_vector.m_dX = (this.m_random.nextDouble() - 0.5) * this.m_dStarXrange;
                    this.m_objectStar[i].m_vector.m_dY = (this.m_random.nextDouble() - 0.5) * this.m_dStarYrange;
                    this.m_objectStar[i].m_vector.m_dZ = 40.0;
                    this.m_dRollRate[i] = (this.m_random.nextDouble() - 0.5) * 0.2;
                    this.m_dPitchRate[i] = (this.m_random.nextDouble() - 0.5) * 0.2;
                    this.m_dYawRate[i] = (this.m_random.nextDouble() - 0.5) * 0.2;
                }
                this.m_objectStar[i].transform();
            }
            this.m_viewpoint.render(this.m_objectStar, this.m_nStarObjects, this);
            System.gc();
            try {
                Thread.sleep(0L);
            }
            catch (InterruptedException ex) {
                this.stop();
            }
        }
    }
    
    public void init() {
        this.start();
    }
    
    public Starflite() {
        this.m_mediaTracker = new MediaTracker(this);
    }
}
