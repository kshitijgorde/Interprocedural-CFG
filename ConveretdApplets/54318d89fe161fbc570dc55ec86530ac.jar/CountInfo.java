import java.io.IOException;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CountInfo
{
    Applet theApplet;
    int firstLine;
    Date startDate;
    Date currentDate;
    long interval;
    long count;
    long counterID;
    URL bannerImageURL;
    URL bannerTargetURL;
    URL backupImageURL;
    URL backupTargetURL;
    int adNumber;
    boolean subscriber;
    boolean increment;
    boolean expired;
    boolean isItJPEG;
    int numberOfFrames;
    int frameDelay;
    boolean loopFrames;
    boolean isLiveUpdate;
    String digitDirectory;
    String adName;
    String proxyAddress;
    String imageAddress;
    String targetAddress;
    
    public boolean isSubscriber() {
        return this.subscriber;
    }
    
    public boolean getExpired() {
        return this.expired;
    }
    
    public void setExpired(final boolean expired) {
        this.expired = expired;
    }
    
    public Date getStartDate() {
        return this.startDate;
    }
    
    public Date getCurrentDate() {
        return this.currentDate;
    }
    
    public boolean getLoopFrames() {
        return this.loopFrames;
    }
    
    public long getInterval() {
        return this.interval;
    }
    
    public long getCount() {
        return this.count;
    }
    
    public long getCounterID() {
        return this.counterID;
    }
    
    public URL getBannerImageURL() {
        return this.bannerImageURL;
    }
    
    public String getImageAddress() {
        return this.imageAddress;
    }
    
    public URL getBannerTargetURL() {
        return this.bannerTargetURL;
    }
    
    public int getAdNumber() {
        return this.adNumber;
    }
    
    public String getAdName() {
        return this.adName;
    }
    
    public int getNumberOfFrames() {
        return this.numberOfFrames;
    }
    
    public boolean loopFrames() {
        return this.loopFrames;
    }
    
    public boolean isItJPEG() {
        return this.isItJPEG;
    }
    
    public int getFrameDelay() {
        return this.frameDelay;
    }
    
    public String getDigitDirectory() {
        return this.digitDirectory;
    }
    
    public void getCountInfo() {
        final String[] array = new String[50];
        try {
            final int n = (int)(Math.random() * 12345.0);
            final StringBuffer sb = new StringBuffer(this.theApplet.getCodeBase().toString() + "php/counter.php");
            sb.append("?");
            if (this.increment) {
                sb.append("counter_id=" + this.counterID + "&increment=true" + "&random=" + n);
            }
            else {
                sb.append("counter_id=" + this.counterID + "&increment=false" + "&random=" + n);
            }
            if (this.isLiveUpdate) {
                sb.append("&secondary_exposure=true");
            }
            else {
                sb.append("&secondary_exposure=false");
            }
            final DataInputStream dataInputStream = new DataInputStream(new URL(sb.toString()).openConnection().getInputStream());
            int n2 = 0;
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                array[n2] = line;
                System.out.println(line);
                if (line.compareTo("Invalid Input") == 0) {
                    throw new RuntimeException("JCount: Invalid Counter ID");
                }
                if (line.compareTo("Counter Expired") == 0) {
                    this.setExpired(true);
                }
                if (line.compareTo("Start of Output") == 0) {
                    this.firstLine = n2 + 1;
                }
                ++n2;
            }
            dataInputStream.close();
        }
        catch (MalformedURLException ex) {
            System.out.println("MalformedURLException: " + ex);
        }
        catch (IOException ex2) {
            System.out.println("IOException: " + ex2);
        }
        try {
            this.count = Long.parseLong(array[this.firstLine]);
        }
        catch (NumberFormatException ex3) {
            this.count = 0L;
        }
        try {
            this.startDate = new Date(array[this.firstLine + 1]);
        }
        catch (Exception ex4) {
            this.startDate = new Date();
        }
        if (array[this.firstLine + 3].compareTo("http://www.jcount.com/subscriber/") == 0) {
            this.subscriber = true;
        }
        if (array[this.firstLine + 3].compareTo("http://www.jcount.com/subscriber1/") == 0) {
            this.subscriber = true;
            if (new Date(array[this.firstLine + 5]).getTime() < new Date().getTime()) {
                throw new RuntimeException("JCount: Counter Expired");
            }
        }
        try {
            this.adNumber = Integer.parseInt(array[this.firstLine + 4]);
        }
        catch (NumberFormatException ex5) {
            this.adNumber = 0;
        }
        if (!this.subscriber && array[this.firstLine + 7].compareTo("jpg") == 0) {
            this.isItJPEG = true;
        }
        try {
            this.backupImageURL = new URL(this.theApplet.getCodeBase(), "ads/advertisers/default/");
            this.backupTargetURL = new URL("http://www.jcount.com/");
        }
        catch (MalformedURLException ex6) {}
        try {
            final int index = array[this.firstLine + 2].indexOf("\"", 0);
            final int index2 = array[this.firstLine + 2].indexOf("\"", index + 1);
            final int index3 = array[this.firstLine + 2].indexOf("\"", index2 + 1);
            this.imageAddress = array[this.firstLine + 2].substring(index3 + 1, array[this.firstLine + 2].indexOf("\"", index3 + 1));
            this.targetAddress = array[this.firstLine + 2].substring(index + 1, index2);
            System.out.println(this.imageAddress);
            this.bannerImageURL = new URL(this.imageAddress);
            this.bannerTargetURL = new URL(this.targetAddress);
        }
        catch (MalformedURLException ex7) {
            this.bannerImageURL = this.backupImageURL;
            this.bannerTargetURL = this.backupTargetURL;
        }
        if (!this.subscriber) {
            this.adName = array[this.firstLine + 2];
            this.numberOfFrames = Integer.parseInt(array[this.firstLine + 8]);
            if (array[this.firstLine + 9].compareTo("do_not_loop_frames") == 0) {
                this.loopFrames = false;
            }
            else {
                this.loopFrames = true;
            }
            try {
                this.frameDelay = Integer.parseInt(array[this.firstLine + 10]);
            }
            catch (NumberFormatException ex8) {
                this.frameDelay = 3;
            }
        }
        this.digitDirectory = array[this.firstLine + 11];
    }
    
    public CountInfo(final boolean increment, final boolean isLiveUpdate, final long counterID, final Applet theApplet) {
        this.theApplet = theApplet;
        this.subscriber = false;
        this.firstLine = 0;
        this.isItJPEG = false;
        this.frameDelay = 0;
        this.numberOfFrames = 1;
        this.loopFrames = true;
        this.increment = increment;
        this.expired = false;
        this.counterID = counterID;
        this.isLiveUpdate = isLiveUpdate;
        this.getCountInfo();
        this.currentDate = new Date();
        this.interval = (this.currentDate.getTime() - this.startDate.getTime()) / 1000L;
    }
}
