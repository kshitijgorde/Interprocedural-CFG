import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

class sunloc
{
    double latitude;
    double longitude;
    final int unten = 270;
    
    public void locMenu(final Choice locChoice) {
        locChoice.addItem("User Input");
        locChoice.addItem("_________");
        locChoice.addItem("Berlin");
        locChoice.addItem("Dortmund");
        locChoice.addItem("Frankfurt / M");
        locChoice.addItem("Frankfurt / O");
        locChoice.addItem("Hamburg");
        locChoice.addItem("Kassel");
        locChoice.addItem("Kiel");
        locChoice.addItem("Koeln");
        locChoice.addItem("Muenchen");
        locChoice.addItem("Stuttgart");
        locChoice.addItem("Welver");
        locChoice.addItem("50N10E");
        locChoice.addItem("Extern");
        locChoice.addItem("Guenne");
        locChoice.addItem("_________");
        locChoice.addItem("Ankara");
        locChoice.addItem("Amsterdam");
        locChoice.addItem("Athens");
        locChoice.addItem("Belgrade");
        locChoice.addItem("Brussels");
        locChoice.addItem("Bucharest");
        locChoice.addItem("Budapest");
        locChoice.addItem("Copenhagen");
        locChoice.addItem("Dublin");
        locChoice.addItem("Geneva");
        locChoice.addItem("Helsinki");
        locChoice.addItem("Lisbon");
        locChoice.addItem("London");
        locChoice.addItem("Madrid");
        locChoice.addItem("Moscow");
        locChoice.addItem("Oslo");
        locChoice.addItem("P. de Mallorca");
        locChoice.addItem("Paris");
        locChoice.addItem("Prague");
        locChoice.addItem("Rome");
        locChoice.addItem("Sofia");
        locChoice.addItem("Stockholm");
        locChoice.addItem("Warsaw");
        locChoice.addItem("Vienna");
        locChoice.addItem("Zurich");
        locChoice.addItem("_________");
        locChoice.addItem("Atlanta GA");
        locChoice.addItem("Boston");
        locChoice.addItem("Chicago");
        locChoice.addItem("Denver");
        locChoice.addItem("Detroit");
        locChoice.addItem("New York");
        locChoice.addItem("Phoenix");
        locChoice.addItem("Saint Louis");
        locChoice.addItem("Salt Lake City");
        locChoice.addItem("San Francisco");
        locChoice.addItem("Washington");
        locChoice.addItem("_________");
        locChoice.addItem("Caracas");
        locChoice.addItem("Rio de Janeiro");
        locChoice.addItem("Sydney");
        locChoice.addItem("Tokyo");
    }
    
    public void timeMenu(final Choice timeChoice) {
        timeChoice.addItem("UT +0 h");
        timeChoice.addItem("UT -1 h");
        timeChoice.addItem("UT +1 h");
        timeChoice.addItem("UT -2 h");
        timeChoice.addItem("UT +2 h");
        timeChoice.addItem("UT -3 h");
        timeChoice.addItem("UT +3 h");
        timeChoice.addItem("UT -4 h");
        timeChoice.addItem("UT +4 h");
        timeChoice.addItem("UT -5 h");
        timeChoice.addItem("UT +5 h");
        timeChoice.addItem("UT -6 h");
        timeChoice.addItem("UT +6 h");
        timeChoice.addItem("UT -7 h");
        timeChoice.addItem("UT +7 h");
        timeChoice.addItem("v -8 h");
        timeChoice.addItem("UT +8 h");
        timeChoice.addItem("UT -9 h");
        timeChoice.addItem("UT +9 h");
        timeChoice.addItem("UT -10 h");
        timeChoice.addItem("v +10 h");
        timeChoice.addItem("UT -11 h");
        timeChoice.addItem("UT +11 h");
        timeChoice.addItem("UT -12 h");
        timeChoice.addItem("UT +12 h");
        timeChoice.addItem("UT +13 h");
    }
    
    public int getTimeZone(final String timeString) {
        int timeOffset = 0;
        if (timeString.equals("UT +0 h")) {
            timeOffset = 0;
        }
        else if (timeString.equals("UT -1 h")) {
            timeOffset = -1;
        }
        else if (timeString.equals("UT +1 h")) {
            timeOffset = 1;
        }
        else if (timeString.equals("UT -2 h")) {
            timeOffset = -2;
        }
        else if (timeString.equals("UT +2 h")) {
            timeOffset = 2;
        }
        else if (timeString.equals("UT -3 h")) {
            timeOffset = -3;
        }
        else if (timeString.equals("UT +3 h")) {
            timeOffset = 3;
        }
        else if (timeString.equals("UT -4 h")) {
            timeOffset = -4;
        }
        else if (timeString.equals("UT +4 h")) {
            timeOffset = 4;
        }
        else if (timeString.equals("UT -5 h")) {
            timeOffset = -5;
        }
        else if (timeString.equals("UT +5 h")) {
            timeOffset = 5;
        }
        else if (timeString.equals("UT -6 h")) {
            timeOffset = -6;
        }
        else if (timeString.equals("UT +6 h")) {
            timeOffset = 6;
        }
        else if (timeString.equals("UT -7 h")) {
            timeOffset = -7;
        }
        else if (timeString.equals("UT +7 h")) {
            timeOffset = 7;
        }
        else if (timeString.equals("UT -8 h")) {
            timeOffset = -8;
        }
        else if (timeString.equals("UT +8 h")) {
            timeOffset = 8;
        }
        else if (timeString.equals("UT -9 h")) {
            timeOffset = -9;
        }
        else if (timeString.equals("UT +9 h")) {
            timeOffset = 9;
        }
        else if (timeString.equals("UT -10 h")) {
            timeOffset = -10;
        }
        else if (timeString.equals("UT +10 h")) {
            timeOffset = 10;
        }
        else if (timeString.equals("v -11 h")) {
            timeOffset = -11;
        }
        else if (timeString.equals("UT +11 h")) {
            timeOffset = 11;
        }
        else if (timeString.equals("UT -12 h")) {
            timeOffset = -12;
        }
        else if (timeString.equals("UT +12 h")) {
            timeOffset = 12;
        }
        else if (timeString.equals("UT +13 h")) {
            timeOffset = 13;
        }
        return timeOffset;
    }
    
    public double getLatLong(final String locString, final int n) {
        double value = 0.0;
        if (locString.equals("User Input")) {
            this.latitude = 0.0;
            this.longitude = 0.0;
        }
        else if (locString.equals("Dortmund")) {
            this.latitude = 51.52;
            this.longitude = 7.47;
        }
        else if (locString.equals("Hamburg")) {
            this.latitude = 53.55;
            this.longitude = 9.97;
        }
        else if (locString.equals("Muenchen")) {
            this.latitude = 48.15;
            this.longitude = 11.61;
        }
        else if (locString.equals("Berlin")) {
            this.latitude = 52.51;
            this.longitude = 13.41;
        }
        else if (locString.equals("Frankfurt / M")) {
            this.latitude = 50.11;
            this.longitude = 8.69;
        }
        else if (locString.equals("Frankfurt / O")) {
            this.latitude = 52.37;
            this.longitude = 14.55;
        }
        else if (locString.equals("Rostock")) {
            this.latitude = 54.09;
            this.longitude = 12.14;
        }
        else if (locString.equals("Koeln")) {
            this.latitude = 50.94;
            this.longitude = 6.96;
        }
        else if (locString.equals("Kiel")) {
            this.latitude = 54.32;
            this.longitude = 10.12;
        }
        else if (locString.equals("Stuttgart")) {
            this.latitude = 48.78;
            this.longitude = 9.18;
        }
        else if (locString.equals("Kassel")) {
            this.latitude = 51.32;
            this.longitude = 9.5;
        }
        else if (locString.equals("Welver")) {
            this.latitude = 51.62;
            this.longitude = 7.97;
        }
        else if (locString.equals("50N10E")) {
            this.latitude = 50.0;
            this.longitude = 10.0;
        }
        else if (locString.equals("Extern")) {
            this.latitude = 51.869;
            this.longitude = 8.917;
        }
        else if (locString.equals("Guenne")) {
            this.latitude = 51.4942;
            this.longitude = 8.0491;
        }
        else if (locString.equals("Ankara")) {
            this.latitude = 40.13;
            this.longitude = 33.0;
        }
        else if (locString.equals("Amsterdam")) {
            this.latitude = 52.38;
            this.longitude = 4.89;
        }
        else if (locString.equals("Athens")) {
            this.latitude = 37.97;
            this.longitude = 23.72;
        }
        else if (locString.equals("Belgrade")) {
            this.latitude = 44.83;
            this.longitude = 20.46;
        }
        else if (locString.equals("Belgrade")) {
            this.latitude = 44.83;
            this.longitude = 20.46;
        }
        else if (locString.equals("Brussels")) {
            this.latitude = 50.85;
            this.longitude = 4.37;
        }
        else if (locString.equals("Bucharest")) {
            this.latitude = 44.5;
            this.longitude = 26.1;
        }
        else if (locString.equals("Budapest")) {
            this.latitude = 47.49;
            this.longitude = 19.06;
        }
        else if (locString.equals("Copenhagen")) {
            this.latitude = 55.69;
            this.longitude = 12.58;
        }
        else if (locString.equals("Dublin")) {
            this.latitude = 53.39;
            this.longitude = -6.34;
        }
        else if (locString.equals("Geneva")) {
            this.latitude = 46.2;
            this.longitude = 6.15;
        }
        else if (locString.equals("Helsinki")) {
            this.latitude = 60.16;
            this.longitude = 24.96;
        }
        else if (locString.equals("Lisbon")) {
            this.latitude = 38.71;
            this.longitude = -9.19;
        }
        else if (locString.equals("London")) {
            this.latitude = 51.51;
            this.longitude = -0.1;
        }
        else if (locString.equals("Madrid")) {
            this.latitude = 40.41;
            this.longitude = -3.69;
        }
        else if (locString.equals("Moscow")) {
            this.latitude = 55.76;
            this.longitude = 37.57;
        }
        else if (locString.equals("Oslo")) {
            this.latitude = 59.92;
            this.longitude = 10.73;
        }
        else if (locString.equals("P. de Mallorca")) {
            this.latitude = 39.57;
            this.longitude = 2.65;
        }
        else if (locString.equals("Paris")) {
            this.latitude = 48.84;
            this.longitude = 2.34;
        }
        else if (locString.equals("Prague")) {
            this.latitude = 50.09;
            this.longitude = 14.42;
        }
        else if (locString.equals("Rome")) {
            this.latitude = 41.9;
            this.longitude = 12.48;
        }
        else if (locString.equals("Sofia")) {
            this.latitude = 42.7;
            this.longitude = 23.45;
        }
        else if (locString.equals("Stockholm")) {
            this.latitude = 59.34;
            this.longitude = 18.06;
        }
        else if (locString.equals("Warsaw")) {
            this.latitude = 52.22;
            this.longitude = 21.03;
        }
        else if (locString.equals("Vienna")) {
            this.latitude = 48.21;
            this.longitude = 16.38;
        }
        else if (locString.equals("Zurich")) {
            this.latitude = 47.38;
            this.longitude = 8.55;
        }
        else if (locString.equals("Atlanta")) {
            this.latitude = 33.75;
            this.longitude = -84.38;
        }
        else if (locString.equals("Boston")) {
            this.latitude = 42.33;
            this.longitude = -71.08;
        }
        else if (locString.equals("Denver")) {
            this.latitude = 39.73;
            this.longitude = -104.98;
        }
        else if (locString.equals("Chicago")) {
            this.latitude = 41.88;
            this.longitude = -87.63;
        }
        else if (locString.equals("Detroit")) {
            this.latitude = 42.38;
            this.longitude = -83.08;
        }
        else if (locString.equals("New York")) {
            this.latitude = 40.76;
            this.longitude = -73.97;
        }
        else if (locString.equals("Phoenix")) {
            this.latitude = 33.3;
            this.longitude = -112.03;
        }
        else if (locString.equals("Saint Louis")) {
            this.latitude = 38.63;
            this.longitude = -90.18;
        }
        else if (locString.equals("Salt Lake City")) {
            this.latitude = 40.77;
            this.longitude = -111.88;
        }
        else if (locString.equals("San Francisco")) {
            this.latitude = 37.79;
            this.longitude = -122.43;
        }
        else if (locString.equals("Washington")) {
            this.latitude = 38.92;
            this.longitude = -77.0;
        }
        else if (locString.equals("Caracas")) {
            this.latitude = 10.5;
            this.longitude = -66.93;
        }
        else if (locString.equals("Rio de Janeiro")) {
            this.latitude = -22.88;
            this.longitude = -43.28;
        }
        else if (locString.equals("Sydney")) {
            this.latitude = -33.87;
            this.longitude = 151.22;
        }
        else if (locString.equals("Tokyo")) {
            this.latitude = 35.7;
            this.longitude = 139.77;
        }
        if (n == 1) {
            value = this.latitude;
        }
        else {
            value = -this.longitude;
        }
        return value;
    }
}
