import java.util.Vector;
import java.awt.Polygon;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class TOC
{
    public Metadata[] scenes;
    public String cellDir;
    public boolean hasMetrics;
    public boolean hasLines;
    public boolean valid;
    public int gridCol;
    public int gridRow;
    public int projCode;
    public int numImg;
    public int minX;
    public int maxX;
    public int minY;
    public int maxY;
    public int currentDateIndex;
    private Point centerProjCoords;
    private NavigationModel navModel;
    private ProjectionTransformation savedProj;
    private URL appletURL;
    private boolean sceneCornersSet;
    private int[] lineOffset;
    private int[] sampOffset;
    Dimension maxSceneSize;
    private Sensor currSensor;
    
    TOC(final URL appletURL, final int gridCol, final int gridRow) {
        this.appletURL = appletURL;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
        this.centerProjCoords = null;
        this.navModel = null;
        this.savedProj = null;
        this.currentDateIndex = -1;
        this.valid = false;
        this.sceneCornersSet = false;
        this.lineOffset = new int[4];
        this.sampOffset = new int[4];
    }
    
    TOC(final TOC toc) {
        this.scenes = new Metadata[toc.numImg];
        for (int i = 0; i < this.scenes.length; ++i) {
            this.scenes[i] = new Metadata(toc.scenes[i]);
        }
        this.cellDir = new String(toc.cellDir);
        this.hasMetrics = toc.hasMetrics;
        this.hasLines = toc.hasLines;
        this.valid = toc.valid;
        this.gridCol = toc.gridCol;
        this.gridRow = toc.gridRow;
        this.projCode = toc.projCode;
        this.numImg = toc.numImg;
        this.minX = toc.minX;
        this.maxX = toc.maxX;
        this.minY = toc.minY;
        this.maxY = toc.maxY;
        this.currentDateIndex = toc.currentDateIndex;
        this.centerProjCoords = toc.centerProjCoords;
        this.navModel = toc.navModel;
        this.savedProj = toc.savedProj;
        this.appletURL = toc.appletURL;
        this.sceneCornersSet = toc.sceneCornersSet;
        this.lineOffset = toc.lineOffset;
        this.sampOffset = toc.sampOffset;
        this.maxSceneSize = toc.maxSceneSize;
        this.currSensor = toc.currSensor;
    }
    
    public void read(final Sensor currSensor) {
        final double offsetResolution = currSensor.getOffsetResolution();
        this.currSensor = currSensor;
        this.navModel = currSensor.navModel;
        this.valid = false;
        this.sceneCornersSet = false;
        this.numImg = 0;
        this.maxSceneSize = currSensor.getNominalSceneSize();
        this.cellDir = currSensor.getCellDirectory(this.gridCol, this.gridRow);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.appletURL, this.cellDir + "/TOC").openStream()));
            final String line = bufferedReader.readLine();
            if (line == null) {
                System.out.println("Error reading Table of Contents file for gridCol/gridRow " + this.gridCol + "/" + this.gridRow);
                bufferedReader.close();
                return;
            }
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                final int columnNumberFromString = this.navModel.getColumnNumberFromString(stringTokenizer.nextToken());
                if (columnNumberFromString != this.gridCol) {
                    System.out.println("Error in TOC file -- incorrect Path specified.");
                    System.out.println(" " + columnNumberFromString + " " + this.gridCol + "\n");
                    bufferedReader.close();
                    return;
                }
                if (this.navModel.getRowNumberFromString(stringTokenizer.nextToken()) != this.gridRow) {
                    System.out.println("Error in TOC file -- incorrect Row specified.");
                    bufferedReader.close();
                    return;
                }
                this.projCode = Integer.parseInt(stringTokenizer.nextToken());
                if (Integer.parseInt(stringTokenizer.nextToken()) == 1) {
                    this.hasLines = true;
                }
                else {
                    this.hasLines = false;
                }
                if (Integer.parseInt(stringTokenizer.nextToken()) == 1) {
                    this.hasMetrics = true;
                }
                else {
                    this.hasMetrics = false;
                }
                this.numImg = Integer.parseInt(stringTokenizer.nextToken());
            }
            catch (NoSuchElementException ex) {
                System.out.println("Exception:  " + ex.getMessage());
                bufferedReader.close();
                return;
            }
            catch (NumberFormatException ex2) {
                System.out.println("Exception:  " + ex2.getMessage());
                bufferedReader.close();
                return;
            }
            this.scenes = new Metadata[this.numImg];
            for (int i = 0; i < this.numImg; ++i) {
                final String line2 = bufferedReader.readLine();
                if (line2 == null) {
                    System.out.println("Error reading Table of Contents file for gridCol/gridRow " + this.gridCol + "/" + this.gridRow);
                    bufferedReader.close();
                    return;
                }
                try {
                    (this.scenes[i] = new Metadata(line2, currSensor, this.gridCol, this.gridRow)).calculateSceneCenter(offsetResolution);
                }
                catch (NoSuchElementException ex3) {
                    System.out.println("Exception:  " + ex3.getMessage());
                    bufferedReader.close();
                    return;
                }
                catch (NumberFormatException ex4) {
                    System.out.println("Exception:  " + ex4.getMessage());
                    bufferedReader.close();
                    return;
                }
            }
            bufferedReader.close();
        }
        catch (IOException ex5) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (Exception ex6) {}
                System.out.println("Exception:  " + ex5.getMessage());
            }
            return;
        }
        this.findCoordinateExtents(offsetResolution);
        if (this.numImg > 0) {
            this.valid = true;
        }
    }
    
    private void findCoordinateExtents(final double n) {
        if (this.numImg > 0) {
            int n2 = 0;
            int n3 = 0;
            final Metadata metadata = this.scenes[0];
            this.minY = metadata.ulY;
            this.maxY = metadata.ulY;
            this.minX = metadata.ulX;
            this.maxX = metadata.ulX;
            for (int i = 0; i < this.numImg; ++i) {
                final Metadata metadata2 = this.scenes[i];
                int ulX = metadata2.ulX;
                int ulX2 = metadata2.ulX;
                int ulY = metadata2.ulY;
                int ulY2 = metadata2.ulY;
                for (int j = 0; j < 4; ++j) {
                    final int n4 = metadata2.ulY - (int)Math.round(metadata2.lineOffset[j] * n);
                    if (n4 < ulY) {
                        ulY = n4;
                    }
                    if (n4 > ulY2) {
                        ulY2 = n4;
                    }
                    final int n5 = metadata2.ulX + (int)Math.round(metadata2.sampOffset[j] * n);
                    if (n5 < ulX) {
                        ulX = n5;
                    }
                    if (n5 > ulX2) {
                        ulX2 = n5;
                    }
                }
                if (ulY < this.minY) {
                    this.minY = ulY;
                }
                if (ulY2 > this.maxY) {
                    this.maxY = ulY2;
                }
                if (ulX < this.minX) {
                    this.minX = ulX;
                }
                if (ulX2 > this.maxX) {
                    this.maxX = ulX2;
                }
                final int n6 = ulX2 - ulX;
                if (n6 > n3) {
                    n3 = n6;
                }
                final int n7 = ulY2 - ulY;
                if (n7 > n2) {
                    n2 = n7;
                }
            }
            this.maxSceneSize = new Dimension(n3, n2);
        }
    }
    
    public void clearSceneFilters(final int n) {
        if (!this.valid) {
            return;
        }
        for (int i = 0; i < this.numImg; ++i) {
            this.scenes[i].clearFilter(n);
        }
    }
    
    public void filterScenesToViewport(final Polygon polygon) {
        if (!this.valid) {
            return;
        }
        for (int i = 0; i < this.numImg; ++i) {
            this.scenes[i].filterToViewport(polygon);
        }
    }
    
    public void filterScenesToCloudCover(final int n) {
        if (!this.valid) {
            return;
        }
        for (int i = 0; i < this.numImg; ++i) {
            this.scenes[i].filterToCloudCover(n);
        }
    }
    
    public void filterScenesToDateRange(final int n, final int n2, final int n3, final int n4) {
        if (!this.valid) {
            return;
        }
        for (int i = 0; i < this.numImg; ++i) {
            this.scenes[i].filterToDateRange(n, n2, n3, n4);
        }
    }
    
    public void filterScenesToGridColRowRange(final int n, final int n2, final int n3, final int n4) {
        if (!this.valid) {
            return;
        }
        for (int i = 0; i < this.numImg; ++i) {
            this.scenes[i].filterToGridColRowRange(n, n2, n3, n4);
        }
    }
    
    public void filterScenesToSceneList(final boolean b) {
        if (!this.valid) {
            return;
        }
        for (int i = 0; i < this.numImg; ++i) {
            this.scenes[i].filterToSceneList(b);
        }
    }
    
    public void filterToHiddenScene() {
        if (!this.valid) {
            return;
        }
        for (int i = 0; i < this.numImg; ++i) {
            this.scenes[i].filterToHiddenScene();
        }
    }
    
    public void filterScenesToUserArea(final boolean b, final UserDefinedAreaDialog userDefinedAreaDialog) {
        if (!this.valid) {
            return;
        }
        for (int i = 0; i < this.numImg; ++i) {
            this.scenes[i].filterToUserArea(b, userDefinedAreaDialog);
        }
    }
    
    public void filterScenesToQuality(final int n) {
        if (!this.valid) {
            return;
        }
        if (this.currSensor.numQualityValues > 0) {
            for (int i = 0; i < this.numImg; ++i) {
                this.scenes[i].filterToQuality(n);
            }
        }
    }
    
    public void filterScenesToDataVersion(final String s) {
        if (!this.valid) {
            return;
        }
        for (int i = 0; i < this.numImg; ++i) {
            this.scenes[i].filterToDataVersion(s);
        }
    }
    
    public int findScene(final Metadata metadata) {
        int n = -1;
        if (this.valid) {
            for (int i = 0; i < this.numImg; ++i) {
                if (metadata.date == this.scenes[i].date && metadata.entityID.equals(this.scenes[i].entityID)) {
                    n = i;
                    break;
                }
            }
        }
        return n;
    }
    
    public int findDate(final int n, final int n2) {
        int n3 = -1;
        int n4 = 10000000;
        for (int i = 0; i < this.numImg; ++i) {
            final Metadata metadata = this.scenes[i];
            if (metadata.visible) {
                final int daysBetween = metadata.daysBetween(n, n2);
                if (daysBetween < n4) {
                    n4 = daysBetween;
                    n3 = i;
                }
            }
        }
        return n3;
    }
    
    public void findClosestVisibleDate() {
        if (this.currentDateIndex < 0 || this.currentDateIndex > this.numImg) {
            return;
        }
        if (this.scenes[this.currentDateIndex].visible) {
            return;
        }
        int n = -1;
        int n2 = -1;
        for (int i = this.currentDateIndex; i >= 0; --i) {
            if (this.scenes[i].visible) {
                n = i;
                break;
            }
        }
        for (int j = this.currentDateIndex + 1; j < this.numImg; ++j) {
            if (this.scenes[j].visible) {
                n2 = j;
                break;
            }
        }
        if (n != -1 && n2 == -1) {
            this.currentDateIndex = n;
        }
        else if (n == -1 && n2 != -1) {
            this.currentDateIndex = n2;
        }
        else if (n != -1 && n2 != -1) {
            if (this.scenes[this.currentDateIndex].daysBetween(this.scenes[n].year, this.scenes[n].jDate) < this.scenes[this.currentDateIndex].daysBetween(this.scenes[n2].year, this.scenes[n2].jDate)) {
                this.currentDateIndex = n;
            }
            else {
                this.currentDateIndex = n2;
            }
        }
    }
    
    public void findSceneClosestToDate(final Metadata metadata) {
        if (this.currentDateIndex < 0 || this.currentDateIndex > this.numImg) {
            return;
        }
        if (this.scenes[this.currentDateIndex].visible && this.scenes[this.currentDateIndex].date == metadata.date) {
            return;
        }
        final int date = this.findDate(metadata.year, metadata.jDate);
        if (date != -1) {
            this.currentDateIndex = date;
        }
    }
    
    public void getScenesOnDate(final int n, final Sensor sensor, final Vector vector) {
        if (!this.valid) {
            return;
        }
        final int indexOfDate = this.getIndexOfDate(n, sensor);
        if (indexOfDate == -1) {
            return;
        }
        int n2 = indexOfDate;
        for (int n3 = indexOfDate; n3 < this.numImg && this.scenes[n3].date == n; ++n3) {
            n2 = n3;
        }
        for (int i = indexOfDate; i <= n2; ++i) {
            final Metadata metadata = this.scenes[i];
            if (sensor == metadata.getSensor()) {
                int ulY;
                int n4;
                for (ulY = metadata.ulY, n4 = 0; n4 < vector.size() && ulY <= vector.elementAt(n4).ulY; ++n4) {}
                vector.insertElementAt(metadata, n4);
            }
        }
    }
    
    public int getIndexOfDate(final int n, final Sensor sensor) {
        if (!this.valid) {
            return -1;
        }
        for (int i = 0; i < this.numImg; ++i) {
            final Metadata metadata = this.scenes[i];
            if (metadata.date >= n) {
                if (n != metadata.date) {
                    break;
                }
                if (sensor == metadata.getSensor()) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public Point getCenterProjCoords(final ProjectionTransformation savedProj) {
        if (this.savedProj == savedProj && this.centerProjCoords != null) {
            return this.centerProjCoords;
        }
        this.savedProj = savedProj;
        return this.centerProjCoords = this.navModel.gridToProjCoords(this.gridCol, this.gridRow, savedProj);
    }
    
    public void setSceneCorners(final ProjectionTransformation sceneCorners) {
        if (this.valid && !this.sceneCornersSet) {
            this.sceneCornersSet = true;
            for (int i = 0; i < this.numImg; ++i) {
                this.scenes[i].setSceneCorners(sceneCorners);
            }
        }
    }
    
    public void add(final TOC toc) {
        if (!toc.valid) {
            return;
        }
        final Metadata[] scenes = this.scenes;
        final Metadata[] scenes2 = toc.scenes;
        int n = 0;
        int n2 = 0;
        final int numImg = this.numImg;
        final int numImg2 = toc.numImg;
        this.numImg = numImg + numImg2;
        this.scenes = new Metadata[this.numImg];
        for (int i = 0; i < this.numImg; ++i) {
            if (n == numImg2) {
                this.scenes[i] = scenes[n2];
                ++n2;
            }
            else if (n2 == numImg) {
                this.scenes[i] = scenes2[n];
                ++n;
            }
            else if (scenes[n2].date < scenes2[n].date) {
                this.scenes[i] = scenes[n2];
                ++n2;
            }
            else {
                this.scenes[i] = scenes2[n];
                ++n;
            }
        }
        if (this.numImg > 0) {
            this.valid = true;
        }
    }
    
    public void eliminateUndownloadableScenes() {
        if (this.valid) {
            int numImg = 0;
            for (int i = 0; i < this.numImg; ++i) {
                if (this.scenes[i].isDownloadable) {
                    ++numImg;
                }
            }
            if (numImg == 0) {
                this.valid = false;
                for (int j = 0; j < this.numImg; ++j) {
                    this.scenes[j].cleanup();
                }
                this.scenes = null;
                this.numImg = 0;
            }
            else {
                final Metadata[] scenes = new Metadata[numImg];
                int n = 0;
                for (int k = 0; k < this.numImg; ++k) {
                    if (this.scenes[k].isDownloadable) {
                        scenes[n] = this.scenes[k];
                        ++n;
                    }
                    else {
                        this.scenes[k].cleanup();
                        this.scenes[k] = null;
                    }
                }
                this.scenes = scenes;
                this.numImg = numImg;
            }
        }
    }
    
    public void cleanup() {
        this.cellDir = null;
        this.centerProjCoords = null;
        this.navModel = null;
        this.savedProj = null;
        this.currentDateIndex = -1;
        this.valid = false;
        for (int i = 0; i < this.numImg; ++i) {
            if (this.scenes[i] != null) {
                this.scenes[i].cleanup();
                this.scenes[i] = null;
            }
        }
        this.numImg = 0;
    }
}
