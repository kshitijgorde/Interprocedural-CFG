import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Calendar;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class noteb extends Applet implements MouseMotionListener, MouseListener, Runnable
{
    String number;
    String release;
    String[] parameter;
    String[] date;
    String[] occation;
    String[][] calendar;
    String happening;
    int cellnumber;
    int[] occationNumber;
    int[] day;
    int[] month;
    int[] year;
    int numberOfParameters;
    int xCoord;
    int yCoord;
    int moveTextX;
    int positionX;
    int actualMonth;
    int actualDay;
    Calendar cal;
    Calendar tempCal;
    Image bg;
    Image mark;
    Image wareHouse;
    Image left;
    Image leftOn;
    Image leftOff;
    Image right;
    Image rightOn;
    Image rightOff;
    Graphics offScreen;
    Font text;
    Font bold;
    FontMetrics fm;
    Thread th;
    boolean isInBox;
    
    public noteb() {
        this.release = "000710";
        this.happening = "";
        this.cellnumber = 0;
        this.numberOfParameters = 0;
        this.xCoord = 0;
        this.yCoord = 0;
        this.moveTextX = 1;
        this.positionX = 170;
        this.actualMonth = 0;
        this.actualDay = 0;
        this.cal = Calendar.getInstance();
        this.tempCal = Calendar.getInstance();
        this.isInBox = false;
    }
    
    public void init() {
        this.wareHouse = this.createImage(160, 190);
        this.offScreen = this.wareHouse.getGraphics();
        this.actualMonth = this.cal.get(2);
        this.actualDay = this.cal.get(5);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.occationNumber = new int[42];
        int temp = 0;
        this.bg = this.getImage(this.getCodeBase(), "bg.gif");
        this.mark = this.getImage(this.getCodeBase(), "mark.gif");
        this.leftOn = this.getImage(this.getCodeBase(), "leftOn.gif");
        this.leftOff = this.getImage(this.getCodeBase(), "leftOff.gif");
        this.rightOn = this.getImage(this.getCodeBase(), "rightOn.gif");
        this.rightOff = this.getImage(this.getCodeBase(), "rightOff.gif");
        this.left = this.leftOff;
        this.right = this.rightOff;
        this.text = new Font("SansSerif", 0, 12);
        this.bold = new Font("SansSerif", 1, 14);
        this.fm = this.getFontMetrics(this.text);
        this.number = "1";
        String test;
        while ((test = this.getParameter(this.number)) != null) {
            temp = Integer.valueOf(this.number);
            ++temp;
            this.number = Integer.toString(temp);
        }
        this.parameter = new String[temp - 1];
        this.date = new String[temp - 1];
        this.occation = new String[temp - 1];
        this.day = new int[temp - 1];
        this.month = new int[temp - 1];
        this.year = new int[temp - 1];
        this.numberOfParameters = temp - 1;
        for (int i = 0; i < temp - 1; ++i) {
            this.parameter[i] = this.getParameter(Integer.toString(i + 1));
            final StringTokenizer st = new StringTokenizer(this.parameter[i], "|");
            this.date[i] = st.nextToken();
            this.occation[i] = st.nextToken();
            final StringTokenizer st2 = new StringTokenizer(this.date[i], ".");
            this.day[i] = Integer.valueOf(st2.nextToken());
            this.month[i] = Integer.valueOf(st2.nextToken());
            this.year[i] = Integer.valueOf(st2.nextToken());
        }
        this.createCalendar();
        this.repaint();
    }
    
    public void start() {
        if (this.th == null) {
            (this.th = new Thread(this)).setPriority(5);
            this.th.start();
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            if (this.positionX + this.fm.stringWidth(this.happening) < 0) {
                this.positionX = 161;
            }
            this.positionX += -this.moveTextX;
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics g) {
        this.offScreen.fillRect(0, 0, 160, 190);
        this.offScreen.setFont(this.text);
        this.offScreen.drawImage(this.bg, 0, 0, this);
        String dayNumber = "";
        int x = 18;
        int y = 70;
        int start = 0;
        int end = 7;
        int dayValue = 0;
        final String date = String.valueOf(new StringBuffer(String.valueOf(this.getMonth(this.cal.get(2)))).append(" ").append(this.cal.get(1)));
        this.offScreen.drawString(date, 80 - this.fm.stringWidth(date) / 2, 18);
        for (int j = 0; j < 6; ++j) {
            for (int i = start; i < end; ++i) {
                if (this.calendar[i][0] != null) {
                    dayNumber = this.calendar[i][0];
                    dayValue = Integer.valueOf(dayNumber);
                }
                else {
                    dayNumber = "";
                }
                this.offScreen.setColor(Color.red);
                if (this.calendar[i][1] == "true") {
                    this.offScreen.drawImage(this.mark, x - 10, y - 18, this);
                }
                if (dayValue == this.cal.get(5) && this.actualMonth == this.cal.get(2)) {
                    this.offScreen.setFont(this.bold);
                }
                else {
                    this.offScreen.setFont(this.text);
                }
                this.offScreen.setColor(Color.black);
                this.offScreen.drawString(dayNumber, x - this.fm.stringWidth(dayNumber) / 2, y);
                x += 20;
            }
            start += 7;
            end += 7;
            y += 20;
            x = 18;
        }
        this.offScreen.drawImage(this.left, 9, 3, this);
        this.offScreen.drawImage(this.right, 130, 3, this);
        this.offScreen.setFont(this.text);
        this.offScreen.drawString(this.happening, this.positionX, 185);
        this.offScreen.setColor(Color.black);
        g.drawImage(this.wareHouse, 0, 0, this);
    }
    
    public boolean imageUpdate(final Image i, final int info, final int x, final int y, final int width, final int height) {
        return true;
    }
    
    public void createCalendar() {
        this.calendar = new String[42][2];
        int maxDaysInMonth = 0;
        this.cal.setFirstDayOfWeek(2);
        this.tempCal.setFirstDayOfWeek(2);
        this.tempCal.set(5, 1);
        int firstDayOfMonth = this.tempCal.get(7) - 1;
        if (firstDayOfMonth == 0) {
            firstDayOfMonth = 7;
        }
        while (maxDaysInMonth < this.tempCal.get(5)) {
            ++maxDaysInMonth;
            this.tempCal.roll(5, true);
        }
        int counter = 1;
        for (int i = 0; i < 42; ++i) {
            if (i < firstDayOfMonth - 1 || counter > maxDaysInMonth) {
                this.calendar[i][0] = null;
                this.calendar[i][1] = "false";
            }
            else {
                this.calendar[i][0] = Integer.toString(counter);
                for (int j = 0; j < this.numberOfParameters; ++j) {
                    if (counter == this.day[j] && this.month[j] == this.cal.get(2) + 1 && this.year[j] == this.cal.get(1)) {
                        this.calendar[i][1] = "true";
                        this.occationNumber[i] = j;
                        break;
                    }
                    this.calendar[i][1] = "false";
                }
                ++counter;
            }
        }
    }
    
    public String getMonth(final int monthNumber) {
        final String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        return months[monthNumber];
    }
    
    public void mouseMoved(final MouseEvent m) {
        this.cellnumber = this.cell(m.getX(), m.getY());
        if (this.calendar[this.cellnumber][1] == "true") {
            if (!this.isInBox) {
                this.positionX = 161;
                this.happening = this.occation[this.occationNumber[this.cellnumber]];
                this.isInBox = true;
            }
        }
        else {
            this.happening = "";
            this.isInBox = false;
        }
        if (m.getX() > 9 && m.getX() < 29 && m.getY() > 3 && m.getY() < 28) {
            this.left = this.leftOn;
            this.repaint();
        }
        else {
            this.left = this.leftOff;
        }
        if (m.getX() > 130 && m.getX() < 150 && m.getY() > 3 && m.getY() < 28) {
            this.right = this.rightOn;
            this.repaint();
        }
        else {
            this.right = this.rightOff;
        }
        if (m.getY() > 170) {
            this.happening = String.valueOf(new StringBuffer("Notebook (release ").append(this.release).append("), by Niko Kortelainen. eMail: nxn@gnwmail.com"));
        }
    }
    
    public void mouseDragged(final MouseEvent m) {
    }
    
    public void mouseClicked(final MouseEvent m) {
        if (m.getX() > 9 && m.getX() < 29 && m.getY() > 3 && m.getY() < 28) {
            if (this.cal.get(2) - 1 != this.actualMonth) {
                this.cal.set(5, 1);
            }
            if (this.cal.get(2) == 0) {
                this.cal.roll(1, false);
                this.tempCal.roll(1, false);
            }
            this.cal.roll(2, false);
            this.tempCal.roll(2, false);
            if (this.cal.get(2) == this.actualMonth) {
                this.cal.set(5, this.actualDay);
                this.tempCal.set(5, this.actualDay);
            }
            this.createCalendar();
            this.repaint();
        }
        if (m.getX() > 130 && m.getX() < 150 && m.getY() > 3 && m.getY() < 28) {
            if (this.cal.get(2) + 1 != this.actualMonth) {
                this.cal.set(5, 1);
            }
            if (this.cal.get(2) == 11) {
                this.cal.roll(1, true);
                this.tempCal.roll(1, true);
            }
            this.cal.roll(2, true);
            this.tempCal.roll(2, true);
            if (this.cal.get(2) == this.actualMonth) {
                this.cal.set(5, this.actualDay);
                this.tempCal.set(5, this.actualDay);
            }
            this.createCalendar();
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent m) {
    }
    
    public void mouseReleased(final MouseEvent m) {
    }
    
    public void mouseExited(final MouseEvent m) {
        this.right = this.rightOff;
        this.left = this.leftOff;
        this.happening = "";
    }
    
    public void mouseEntered(final MouseEvent m) {
    }
    
    public int cell(final int x, final int y) {
        int cellNumber = 0;
        int xDimenMin = 10;
        int xDimenMax = 30;
        int yDimenMax = 75;
        int yDimenMin = 55;
        int row = 0;
        for (int i = 0; i < 5; ++i) {
            row = i * 7;
            for (int j = 0; j < 7; ++j) {
                if (x > xDimenMin && x < xDimenMax && y > yDimenMin && y < yDimenMax) {
                    cellNumber = row + j;
                }
                xDimenMin += 20;
                xDimenMax += 20;
            }
            xDimenMin = 10;
            xDimenMax = 26;
            yDimenMin += 20;
            yDimenMax += 20;
        }
        return cellNumber;
    }
}
