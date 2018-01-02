import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

class DNloc
{
    public void timeMenu(final Choice timeChoice) {
        timeChoice.addItem(" 0 h");
        timeChoice.addItem(" -1 h");
        timeChoice.addItem(" +1 h");
        timeChoice.addItem(" -2 h");
        timeChoice.addItem(" +2 h");
        timeChoice.addItem(" -3 h");
        timeChoice.addItem(" +3 h");
        timeChoice.addItem(" -4 h");
        timeChoice.addItem(" +4 h");
        timeChoice.addItem(" -5 h");
        timeChoice.addItem(" +5 h");
        timeChoice.addItem(" -6 h");
        timeChoice.addItem(" +6 h");
        timeChoice.addItem(" -7 h");
        timeChoice.addItem(" +7 h");
        timeChoice.addItem(" -8 h");
        timeChoice.addItem(" +8 h");
        timeChoice.addItem(" -9 h");
        timeChoice.addItem(" +9 h");
        timeChoice.addItem(" -10 h");
        timeChoice.addItem(" +10 h");
        timeChoice.addItem(" -11 h");
        timeChoice.addItem(" +11 h");
        timeChoice.addItem(" -12 h");
        timeChoice.addItem(" +12 h");
        timeChoice.addItem(" +13 h");
    }
    
    public int getTimeZone(final String timeString) {
        int timeOffset = 0;
        if (timeString.equals("0 h")) {
            timeOffset = 0;
        }
        else if (timeString.equals(" -1 h")) {
            timeOffset = -1;
        }
        else if (timeString.equals(" +1 h")) {
            timeOffset = 1;
        }
        else if (timeString.equals(" -2 h")) {
            timeOffset = -2;
        }
        else if (timeString.equals(" +2 h")) {
            timeOffset = 2;
        }
        else if (timeString.equals(" -3 h")) {
            timeOffset = -3;
        }
        else if (timeString.equals(" +3 h")) {
            timeOffset = 3;
        }
        else if (timeString.equals(" -4 h")) {
            timeOffset = -4;
        }
        else if (timeString.equals(" +4 h")) {
            timeOffset = 4;
        }
        else if (timeString.equals(" -5 h")) {
            timeOffset = -5;
        }
        else if (timeString.equals(" +5 h")) {
            timeOffset = 5;
        }
        else if (timeString.equals(" -6 h")) {
            timeOffset = -6;
        }
        else if (timeString.equals(" +6 h")) {
            timeOffset = 6;
        }
        else if (timeString.equals(" -7 h")) {
            timeOffset = -7;
        }
        else if (timeString.equals(" +7 h")) {
            timeOffset = 7;
        }
        else if (timeString.equals(" -8 h")) {
            timeOffset = -8;
        }
        else if (timeString.equals(" +8 h")) {
            timeOffset = 8;
        }
        else if (timeString.equals(" -9 h")) {
            timeOffset = -9;
        }
        else if (timeString.equals(" +9 h")) {
            timeOffset = 9;
        }
        else if (timeString.equals(" -10 h")) {
            timeOffset = -10;
        }
        else if (timeString.equals(" +10 h")) {
            timeOffset = 10;
        }
        else if (timeString.equals(" -11 h")) {
            timeOffset = -11;
        }
        else if (timeString.equals(" +11 h")) {
            timeOffset = 11;
        }
        else if (timeString.equals(" -12 h")) {
            timeOffset = -12;
        }
        else if (timeString.equals(" +12 h")) {
            timeOffset = 12;
        }
        else if (timeString.equals(" +13 h")) {
            timeOffset = 13;
        }
        return timeOffset;
    }
}
