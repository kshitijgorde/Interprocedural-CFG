import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

class sunloc
{
    double latitude;
    double longitude;
    final int unten = 270;
    
    public void locMenu(final Choice choice) {
        choice.addItem("User Input");
        choice.addItem("Home");
        choice.addItem("_________");
        choice.addItem("Berlin");
        choice.addItem("Dortmund");
        choice.addItem("Frankfurt / M");
        choice.addItem("Frankfurt / O");
        choice.addItem("Hamburg");
        choice.addItem("Kassel");
        choice.addItem("Kiel");
        choice.addItem("Koeln");
        choice.addItem("Muenchen");
        choice.addItem("Stuttgart");
        choice.addItem("Werl");
        choice.addItem("50N10E");
        choice.addItem("_________");
        choice.addItem("Amsterdam");
        choice.addItem("Athens");
        choice.addItem("Belgrade");
        choice.addItem("Brussels");
        choice.addItem("Budapest");
        choice.addItem("Copenhagen");
        choice.addItem("Dublin");
        choice.addItem("Geneva");
        choice.addItem("Helsinki");
        choice.addItem("Lisbon");
        choice.addItem("London");
        choice.addItem("Madrid");
        choice.addItem("Oslo");
        choice.addItem("Paris");
        choice.addItem("Prague");
        choice.addItem("Rome");
        choice.addItem("Stockholm");
        choice.addItem("Warsaw");
        choice.addItem("Vienna");
        choice.addItem("_________");
        choice.addItem("Atlanta");
        choice.addItem("Boston");
        choice.addItem("Chicago");
        choice.addItem("Denver");
        choice.addItem("Detroit");
        choice.addItem("New York");
        choice.addItem("Phoenix");
        choice.addItem("Saint Louis");
        choice.addItem("Salt Lake City");
        choice.addItem("San Francisco");
        choice.addItem("Washington");
        choice.addItem("_________");
        choice.addItem("Caracas");
        choice.addItem("Rio de Janeiro");
        choice.addItem("Sydney");
        choice.addItem("Tokyo");
    }
    
    public void timeMenu(final Choice choice) {
        choice.addItem(" 0 h");
        choice.addItem(" -1 h");
        choice.addItem(" +1 h");
        choice.addItem(" -2 h");
        choice.addItem(" +2 h");
        choice.addItem(" -3 h");
        choice.addItem(" +3 h");
        choice.addItem(" -4 h");
        choice.addItem(" +4 h");
        choice.addItem(" -5 h");
        choice.addItem(" +5 h");
        choice.addItem(" -6 h");
        choice.addItem(" +6 h");
        choice.addItem(" -7 h");
        choice.addItem(" +7 h");
        choice.addItem(" -8 h");
        choice.addItem(" +8 h");
        choice.addItem(" -9 h");
        choice.addItem(" +9 h");
        choice.addItem(" -10 h");
        choice.addItem(" +10 h");
        choice.addItem(" -11 h");
        choice.addItem(" +11 h");
        choice.addItem(" -12 h");
        choice.addItem(" +12 h");
    }
    
    public int getTimeZone(final String s) {
        int n = 0;
        if (s.equals(" 0 h")) {
            n = 0;
        }
        else if (s.equals(" -1 h")) {
            n = -1;
        }
        else if (s.equals(" +1 h")) {
            n = 1;
        }
        else if (s.equals(" -2 h")) {
            n = -2;
        }
        else if (s.equals(" +2 h")) {
            n = 2;
        }
        else if (s.equals(" -3 h")) {
            n = -3;
        }
        else if (s.equals(" +3 h")) {
            n = 3;
        }
        else if (s.equals(" -4 h")) {
            n = -4;
        }
        else if (s.equals(" +4 h")) {
            n = 4;
        }
        else if (s.equals(" -5 h")) {
            n = -5;
        }
        else if (s.equals(" +5 h")) {
            n = 5;
        }
        else if (s.equals(" -6 h")) {
            n = -6;
        }
        else if (s.equals(" +6 h")) {
            n = 6;
        }
        else if (s.equals(" -7 h")) {
            n = -7;
        }
        else if (s.equals(" +7 h")) {
            n = 7;
        }
        else if (s.equals(" -8 h")) {
            n = -8;
        }
        else if (s.equals(" +8 h")) {
            n = 8;
        }
        else if (s.equals(" -9 h")) {
            n = -9;
        }
        else if (s.equals(" +9 h")) {
            n = 9;
        }
        else if (s.equals(" -10 h")) {
            n = -10;
        }
        else if (s.equals(" +10 h")) {
            n = 10;
        }
        else if (s.equals(" -11 h")) {
            n = -11;
        }
        else if (s.equals(" +11 h")) {
            n = 11;
        }
        else if (s.equals(" -12 h")) {
            n = -12;
        }
        else if (s.equals(" +12 h")) {
            n = 12;
        }
        return n;
    }
    
    public double getLatLong(final String s, final int n) {
        if (s.equals("User Input")) {
            this.latitude = 0.0;
            this.longitude = 0.0;
        }
        else if (s.equals("Dortmund")) {
            this.latitude = 51.52;
            this.longitude = 7.47;
        }
        else if (s.equals("Kassel")) {
            this.latitude = 51.32;
            this.longitude = 9.5;
        }
        else if (s.equals("Hamburg")) {
            this.latitude = 53.55;
            this.longitude = 9.97;
        }
        else if (s.equals("Muenchen")) {
            this.latitude = 48.15;
            this.longitude = 11.61;
        }
        else if (s.equals("Berlin")) {
            this.latitude = 52.51;
            this.longitude = 13.4;
        }
        else if (s.equals("Frankfurt / M")) {
            this.latitude = 50.11;
            this.longitude = 8.69;
        }
        else if (s.equals("Frankfurt / O")) {
            this.latitude = 52.37;
            this.longitude = 14.55;
        }
        else if (s.equals("Rostock")) {
            this.latitude = 54.09;
            this.longitude = 12.14;
        }
        else if (s.equals("Koeln")) {
            this.latitude = 50.94;
            this.longitude = 6.96;
        }
        else if (s.equals("Kiel")) {
            this.latitude = 54.32;
            this.longitude = 10.12;
        }
        else if (s.equals("Stuttgart")) {
            this.latitude = 48.78;
            this.longitude = 9.18;
        }
        else if (s.equals("Werl")) {
            this.latitude = 51.56;
            this.longitude = 7.91;
        }
        else if (s.equals("50N10E")) {
            this.latitude = 50.0;
            this.longitude = 10.0;
        }
        else if (s.equals("Amsterdam")) {
            this.latitude = 52.38;
            this.longitude = 4.89;
        }
        else if (s.equals("Athens")) {
            this.latitude = 37.97;
            this.longitude = 23.72;
        }
        else if (s.equals("Belgrade")) {
            this.latitude = 44.83;
            this.longitude = 20.46;
        }
        else if (s.equals("Belgrade")) {
            this.latitude = 44.83;
            this.longitude = 20.46;
        }
        else if (s.equals("Brussels")) {
            this.latitude = 50.85;
            this.longitude = 4.37;
        }
        else if (s.equals("Budapest")) {
            this.latitude = 47.49;
            this.longitude = 19.06;
        }
        else if (s.equals("Copenhagen")) {
            this.latitude = 55.69;
            this.longitude = 12.58;
        }
        else if (s.equals("Dublin")) {
            this.latitude = 53.39;
            this.longitude = -6.34;
        }
        else if (s.equals("Geneva")) {
            this.latitude = 46.2;
            this.longitude = 6.15;
        }
        else if (s.equals("Helsinki")) {
            this.latitude = 60.16;
            this.longitude = 24.96;
        }
        else if (s.equals("Lisbon")) {
            this.latitude = 38.71;
            this.longitude = -9.19;
        }
        else if (s.equals("London")) {
            this.latitude = 51.51;
            this.longitude = -0.1;
        }
        else if (s.equals("Madrid")) {
            this.latitude = 40.41;
            this.longitude = -3.69;
        }
        else if (s.equals("Oslo")) {
            this.latitude = 59.92;
            this.longitude = 10.73;
        }
        else if (s.equals("Paris")) {
            this.latitude = 48.84;
            this.longitude = 2.34;
        }
        else if (s.equals("Prague")) {
            this.latitude = 50.09;
            this.longitude = 14.42;
        }
        else if (s.equals("Rome")) {
            this.latitude = 41.9;
            this.longitude = 12.48;
        }
        else if (s.equals("Stockholm")) {
            this.latitude = 59.34;
            this.longitude = 18.06;
        }
        else if (s.equals("Warsaw")) {
            this.latitude = 52.22;
            this.longitude = 21.03;
        }
        else if (s.equals("Vienna")) {
            this.latitude = 48.21;
            this.longitude = 16.38;
        }
        else if (s.equals("Atlanta")) {
            this.latitude = 33.75;
            this.longitude = -84.38;
        }
        else if (s.equals("Boston")) {
            this.latitude = 42.33;
            this.longitude = -71.08;
        }
        else if (s.equals("Denver")) {
            this.latitude = 39.73;
            this.longitude = -104.98;
        }
        else if (s.equals("Chicago")) {
            this.latitude = 41.88;
            this.longitude = -87.63;
        }
        else if (s.equals("Detroit")) {
            this.latitude = 42.38;
            this.longitude = -83.08;
        }
        else if (s.equals("New York")) {
            this.latitude = 40.76;
            this.longitude = -73.97;
        }
        else if (s.equals("Phoenix")) {
            this.latitude = 33.3;
            this.longitude = -112.03;
        }
        else if (s.equals("Saint Louis")) {
            this.latitude = 38.63;
            this.longitude = -90.18;
        }
        else if (s.equals("Salt Lake City")) {
            this.latitude = 40.77;
            this.longitude = -111.88;
        }
        else if (s.equals("San Francisco")) {
            this.latitude = 37.79;
            this.longitude = -122.43;
        }
        else if (s.equals("Washington")) {
            this.latitude = 38.92;
            this.longitude = -77.0;
        }
        else if (s.equals("Caracas")) {
            this.latitude = 10.5;
            this.longitude = -66.93;
        }
        else if (s.equals("Rio de Janeiro")) {
            this.latitude = -22.88;
            this.longitude = -43.28;
        }
        else if (s.equals("Sydney")) {
            this.latitude = -33.87;
            this.longitude = 151.22;
        }
        else if (s.equals("Tokyo")) {
            this.latitude = 35.7;
            this.longitude = 139.77;
        }
        double n2;
        if (n == 1) {
            n2 = this.latitude;
        }
        else {
            n2 = this.longitude;
        }
        return n2;
    }
}
