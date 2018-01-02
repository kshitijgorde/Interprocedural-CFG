import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageLoader implements Runnable, WorkMonitor
{
    private ImagePane imagePane;
    private imgViewer applet;
    private Image[] mediatrackerList;
    private boolean[] isIDFree;
    private Metadata[] scenes;
    private final int maxImageFiles = 9;
    private Thread loaderThread;
    private Object loadLock;
    private boolean isLoading;
    private boolean isLoadCancelled;
    private boolean killThread;
    private int numImagesToLoad;
    private int currImageLoading;
    private Metadata[] zOrder;
    private int cellsToDisplay;
    private int pixelSize;
    private Sensor currSensor;
    private int filesToLoad;
    private PendingLoad pendingLoad;
    
    public ImageLoader(final imgViewer applet, final ImagePane imagePane) {
        this.applet = applet;
        this.imagePane = imagePane;
        this.mediatrackerList = new Image[9];
        this.isIDFree = new boolean[9];
        this.scenes = new Metadata[9];
        this.loadLock = new Object();
        this.pendingLoad = new PendingLoad();
        (this.loaderThread = new Thread(this, "Image Loader Thread")).start();
    }
    
    @Override
    public String getWorkLabel() {
        return "Loading Images";
    }
    
    @Override
    public boolean isWorking() {
        return this.isBusy();
    }
    
    @Override
    public int getTotalWork() {
        return this.numImagesToLoad;
    }
    
    @Override
    public int getWorkComplete() {
        return this.currImageLoading;
    }
    
    private boolean waitForAny(final MediaTracker mediaTracker) {
        int i = 0;
        int n = 0;
        if (this.applet.verboseOutput) {
            System.out.println("Waiting for any image");
        }
        while (i == 0) {
            boolean b = false;
            for (int j = 0; j < this.filesToLoad; ++j) {
                if (!this.isIDFree[j]) {
                    b = true;
                    if (mediaTracker.checkID(j)) {
                        if (this.applet.slowdown) {
                            try {
                                final Thread loaderThread = this.loaderThread;
                                Thread.sleep(500L);
                            }
                            catch (InterruptedException ex) {}
                        }
                        i = 1;
                        this.scenes[j].image = this.mediatrackerList[j];
                        mediaTracker.removeImage(this.mediatrackerList[j]);
                        this.mediatrackerList[j] = null;
                        this.isIDFree[j] = true;
                        this.scenes[j] = null;
                        break;
                    }
                }
            }
            if (!b) {
                System.out.println("Warning - nothing to wait for");
                break;
            }
            if (i != 0) {
                continue;
            }
            try {
                final Thread loaderThread2 = this.loaderThread;
                Thread.sleep(20L);
            }
            catch (InterruptedException ex2) {}
            if (++n > 9000) {
                return false;
            }
        }
        if (this.applet.verboseOutput) {
            System.out.println("received image");
        }
        return true;
    }
    
    public void loadImages(final ZOrderList list, final int cellsToDisplay, final int pixelSize, final Sensor currSensor) {
        int filesToLoad = 75000 / currSensor.getImageFileSize(pixelSize);
        if (filesToLoad < 2) {
            filesToLoad = 2;
        }
        else if (filesToLoad > 9) {
            filesToLoad = 9;
        }
        final Metadata[] snapshot = list.getSnapshot();
        if (snapshot == null) {
            return;
        }
        synchronized (this.pendingLoad) {
            this.pendingLoad.zOrder = snapshot;
            this.pendingLoad.cellsToDisplay = cellsToDisplay;
            this.pendingLoad.pixelSize = pixelSize;
            this.pendingLoad.currSensor = currSensor;
            this.pendingLoad.filesToLoad = filesToLoad;
            this.pendingLoad.pending = true;
            if (this.isLoading) {
                this.cancelLoad();
            }
            this.pendingLoad.notify();
        }
    }
    
    public void cancelLoad() {
        if (this.isLoading) {
            if (this.applet.verboseOutput) {
                System.out.println("cancelled");
            }
            this.isLoadCancelled = true;
        }
    }
    
    public void killThread() {
        this.cancelLoad();
        synchronized (this.pendingLoad) {
            this.killThread = true;
            this.pendingLoad.notify();
        }
    }
    
    public void waitUntilDone() {
        if (this.applet.verboseOutput) {
            System.out.println("Waiting for image load to complete");
        }
        synchronized (this.loadLock) {
            if (this.applet.verboseOutput) {
                System.out.println("Image load completed");
            }
        }
        if (this.applet.verboseOutput) {
            System.out.println("Waiting done");
        }
    }
    
    public boolean isBusy() {
        return this.isLoading || this.pendingLoad.pending;
    }
    
    @Override
    public void run() {
        final MediaTracker mediaTracker = new MediaTracker(this.imagePane);
        while (true) {
            if (!this.isLoading) {
                synchronized (this.pendingLoad) {
                    if (!this.pendingLoad.pending && !this.killThread) {
                        try {
                            if (this.applet.verboseOutput) {
                                System.out.println("waiting for load command");
                            }
                            this.pendingLoad.wait();
                        }
                        catch (InterruptedException ex) {}
                    }
                    if (this.pendingLoad.pending) {
                        this.zOrder = this.pendingLoad.zOrder;
                        this.pendingLoad.zOrder = null;
                        this.cellsToDisplay = this.pendingLoad.cellsToDisplay;
                        this.pixelSize = this.pendingLoad.pixelSize;
                        this.currSensor = this.pendingLoad.currSensor;
                        this.filesToLoad = this.pendingLoad.filesToLoad;
                        this.isLoading = true;
                        this.isLoadCancelled = false;
                        this.pendingLoad.pending = false;
                    }
                    if (this.killThread) {
                        this.loaderThread = null;
                        return;
                    }
                    continue;
                }
            }
            else {
                synchronized (this.loadLock) {
                    int i = 0;
                    int j = 0;
                    for (int k = 0; k < this.filesToLoad; ++k) {
                        this.isIDFree[k] = true;
                    }
                    this.currImageLoading = 0;
                    this.numImagesToLoad = 0;
                    for (int l = 0; l < this.zOrder.length; ++l) {
                        final Metadata metadata = this.zOrder[l];
                        if (metadata.image == null || metadata.imageRes != this.pixelSize) {
                            ++this.numImagesToLoad;
                        }
                    }
                    int n = 1;
                    while (j < this.zOrder.length) {
                        final Metadata metadata2 = this.zOrder[j];
                        ++j;
                        if (this.isLoadCancelled) {
                            break;
                        }
                        if (!metadata2.visible) {
                            continue;
                        }
                        if (metadata2.image != null && metadata2.imageRes == this.pixelSize) {
                            continue;
                        }
                        int n2;
                        for (n2 = 0; n2 < this.filesToLoad; ++n2) {
                            if (this.isIDFree[n2]) {
                                this.isIDFree[n2] = false;
                                break;
                            }
                        }
                        final String imageName = this.currSensor.makeImageName(metadata2, this.pixelSize);
                        final Image image = metadata2.image;
                        if (image != null) {
                            image.flush();
                            metadata2.image = null;
                        }
                        if (this.applet.verboseOutput) {
                            System.out.println("Loading image " + imageName);
                        }
                        final Image image2 = this.applet.getImage(this.applet.getCodeBase(), imageName);
                        this.scenes[n2] = metadata2;
                        metadata2.imageRes = this.pixelSize;
                        mediaTracker.addImage(image2, n2);
                        this.mediatrackerList[n2] = image2;
                        mediaTracker.checkID(n2, true);
                        if (++i >= this.filesToLoad) {
                            if (!this.waitForAny(mediaTracker)) {
                                i = 0;
                                System.out.println("Error loading images.  Image load cancelled.");
                                break;
                            }
                            --i;
                            ++this.currImageLoading;
                        }
                        if (n == 0) {
                            continue;
                        }
                        if (this.cellsToDisplay == 0) {
                            break;
                        }
                        n = 0;
                    }
                    this.zOrder = null;
                    while (i > 0) {
                        if (this.waitForAny(mediaTracker)) {
                            --i;
                            ++this.currImageLoading;
                        }
                        else {
                            i = 0;
                            System.out.println("Error loading images.  Image load cancelled.");
                        }
                    }
                    if (this.isLoadCancelled && this.applet.verboseOutput) {
                        System.out.println("Detected cancel");
                    }
                    this.isLoading = false;
                    this.imagePane.repaint();
                }
            }
        }
    }
    
    private class PendingLoad
    {
        Metadata[] zOrder;
        int cellsToDisplay;
        int pixelSize;
        Sensor currSensor;
        int filesToLoad;
        boolean pending;
    }
}
