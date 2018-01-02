import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

class Seloc
{
    double latitude;
    double longitude;
    String separator;
    
    public void locMenu(final Choice locChoice) {
        locChoice.addItem("User Input");
        locChoice.addItem(this.separator);
        locChoice.addItem("Berlin");
        locChoice.addItem("Dortmund");
        locChoice.addItem("Dresden");
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
        locChoice.addItem(this.separator);
        locChoice.addItem("Ankara");
        locChoice.addItem("Amsterdam");
        locChoice.addItem("Athens");
        locChoice.addItem("Belgrade");
        locChoice.addItem("Bratislava");
        locChoice.addItem("Brussels");
        locChoice.addItem("Bucharest");
        locChoice.addItem("Budapest");
        locChoice.addItem("Copenhagen");
        locChoice.addItem("Dublin");
        locChoice.addItem("Edinburgh");
        locChoice.addItem("Geneva");
        locChoice.addItem("Helsinki");
        locChoice.addItem("Las Palmas");
        locChoice.addItem("Lisbon");
        locChoice.addItem("London");
        locChoice.addItem("Luxembourg");
        locChoice.addItem("Madrid");
        locChoice.addItem("Moscow");
        locChoice.addItem("Oslo");
        locChoice.addItem("P. de Mallorca");
        locChoice.addItem("Paris");
        locChoice.addItem("Prague");
        locChoice.addItem("Rome");
        locChoice.addItem("Sofia");
        locChoice.addItem("Stockholm");
        locChoice.addItem("Tirana");
        locChoice.addItem("Vienna");
        locChoice.addItem("Warsaw");
        locChoice.addItem("Zurich");
        locChoice.addItem(this.separator);
        locChoice.addItem("Anchorage AK");
        locChoice.addItem("Atlanta GA");
        locChoice.addItem("Baltimore MD");
        locChoice.addItem("Birmingham AL");
        locChoice.addItem("Bismarck ND");
        locChoice.addItem("Boston MA");
        locChoice.addItem("Bridgeport CT");
        locChoice.addItem("Chicago IL");
        locChoice.addItem("Cleveland OH");
        locChoice.addItem("Concord NH");
        locChoice.addItem("Dallas TX");
        locChoice.addItem("Denver CO");
        locChoice.addItem("Des Moines IA");
        locChoice.addItem("Detroit MI");
        locChoice.addItem("Dover DE");
        locChoice.addItem("Eugene OR");
        locChoice.addItem("Ft Lauderdale FL");
        locChoice.addItem("Fort Knox KY");
        locChoice.addItem("Green Bay WI");
        locChoice.addItem("Helena MT");
        locChoice.addItem("Honolulu HI");
        locChoice.addItem("Indianapolis IN");
        locChoice.addItem("Jackson WY");
        locChoice.addItem("Memphis TN");
        locChoice.addItem("Minneapolis MN");
        locChoice.addItem("New Orleans LA");
        locChoice.addItem("New York NY");
        locChoice.addItem("Philadelphia PA");
        locChoice.addItem("Phoenix AZ");
        locChoice.addItem("Raleigh NC");
        locChoice.addItem("Reno NV");
        locChoice.addItem("Topeka KS");
        locChoice.addItem("Saint Louis");
        locChoice.addItem("Salt Lake City UT");
        locChoice.addItem("San Francisco CA");
        locChoice.addItem("Walla Walla WA");
        locChoice.addItem("Washington");
        locChoice.addItem(this.separator);
        locChoice.addItem("Montreal");
        locChoice.addItem("Vancouver");
        locChoice.addItem("Winnipeg");
        locChoice.addItem(this.separator);
        locChoice.addItem("Bogota");
        locChoice.addItem("Buenos Aires");
        locChoice.addItem("Caracas");
        locChoice.addItem("La Paz");
        locChoice.addItem("Lima");
        locChoice.addItem("Montevideo");
        locChoice.addItem("Quito");
        locChoice.addItem("Rio de Janeiro");
        locChoice.addItem("Santiago");
        locChoice.addItem(this.separator);
        locChoice.addItem("Auckland");
        locChoice.addItem("Sydney");
        locChoice.addItem("Tokyo");
        locChoice.addItem(this.separator);
        locChoice.addItem("New Delhi");
        locChoice.addItem("Hong Kong");
        locChoice.addItem("Jakarta");
        locChoice.addItem("Peking");
        locChoice.addItem("Singapore");
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
        timeChoice.addItem("UT -8 h");
        timeChoice.addItem("UT +8 h");
        timeChoice.addItem("UT -9 h");
        timeChoice.addItem("UT +9 h");
        timeChoice.addItem("UT -10 h");
        timeChoice.addItem("UT +10 h");
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
        else if (timeString.equals("UT -11 h")) {
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
        else if (locString.equals("Dresden")) {
            this.latitude = 51.05;
            this.longitude = 13.73;
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
        else if (locString.equals("Bratislava")) {
            this.latitude = 48.15;
            this.longitude = 17.12;
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
        else if (locString.equals("Edinburgh")) {
            this.latitude = 55.95;
            this.longitude = -3.1;
        }
        else if (locString.equals("Geneva")) {
            this.latitude = 46.2;
            this.longitude = 6.15;
        }
        else if (locString.equals("Helsinki")) {
            this.latitude = 60.16;
            this.longitude = 24.96;
        }
        else if (locString.equals("Las Palmas")) {
            this.latitude = 27.9;
            this.longitude = -15.7;
        }
        else if (locString.equals("Lisbon")) {
            this.latitude = 38.71;
            this.longitude = -9.19;
        }
        else if (locString.equals("London")) {
            this.latitude = 51.51;
            this.longitude = -0.1;
        }
        else if (locString.equals("Luxembourg")) {
            this.latitude = 49.6;
            this.longitude = 6.2;
        }
        else if (locString.equals("Moscow")) {
            this.latitude = 55.76;
            this.longitude = 37.57;
        }
        else if (locString.equals("Madrid")) {
            this.latitude = 40.41;
            this.longitude = -3.69;
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
        else if (locString.equals("Tirana")) {
            this.latitude = 41.2;
            this.longitude = 19.9;
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
        else if (locString.equals("Anchorage AK")) {
            this.latitude = 61.17;
            this.longitude = -149.98;
        }
        else if (locString.equals("Atlanta GA")) {
            this.latitude = 33.75;
            this.longitude = -84.38;
        }
        else if (locString.equals("Baltimore MD")) {
            this.latitude = 39.29;
            this.longitude = -76.61;
        }
        else if (locString.equals("Birmingham AL")) {
            this.latitude = 33.52;
            this.longitude = -86.81;
        }
        else if (locString.equals("Bismarck ND")) {
            this.latitude = 46.81;
            this.longitude = -100.79;
        }
        else if (locString.equals("Boston MA")) {
            this.latitude = 42.33;
            this.longitude = -71.08;
        }
        else if (locString.equals("Bridgeport CT")) {
            this.latitude = 41.18;
            this.longitude = -73.19;
        }
        else if (locString.equals("Chicago IL")) {
            this.latitude = 41.88;
            this.longitude = -87.63;
        }
        else if (locString.equals("Cleveland OH")) {
            this.latitude = 39.29;
            this.longitude = -76.61;
        }
        else if (locString.equals("Concord NH")) {
            this.latitude = 43.21;
            this.longitude = -71.54;
        }
        else if (locString.equals("Dallas TX")) {
            this.latitude = 32.79;
            this.longitude = -96.79;
        }
        else if (locString.equals("Denver CO")) {
            this.latitude = 39.73;
            this.longitude = -104.98;
        }
        else if (locString.equals("Des Moines IA")) {
            this.latitude = 41.59;
            this.longitude = -93.62;
        }
        else if (locString.equals("Detroit MI")) {
            this.latitude = 42.38;
            this.longitude = -83.08;
        }
        else if (locString.equals("Dover DE")) {
            this.latitude = 39.13;
            this.longitude = -75.55;
        }
        else if (locString.equals("Eugene OR")) {
            this.latitude = 44.05;
            this.longitude = -123.09;
        }
        else if (locString.equals("Ft Lauderdale FL")) {
            this.latitude = 26.12;
            this.longitude = -80.14;
        }
        else if (locString.equals("Fort Knox KY")) {
            this.latitude = 37.91;
            this.longitude = -85.97;
        }
        else if (locString.equals("Green Bay WI")) {
            this.latitude = 44.51;
            this.longitude = -88.01;
        }
        else if (locString.equals("Helena MT")) {
            this.latitude = 46.59;
            this.longitude = -112.04;
        }
        else if (locString.equals("Honolulu HI")) {
            this.latitude = 21.31;
            this.longitude = -157.86;
        }
        else if (locString.equals("Indianapolis IN")) {
            this.latitude = 39.77;
            this.longitude = -86.16;
        }
        else if (locString.equals("Jackson WY")) {
            this.latitude = 43.61;
            this.longitude = -110.73;
        }
        else if (locString.equals("Memphis TN")) {
            this.latitude = 35.15;
            this.longitude = -90.05;
        }
        else if (locString.equals("Minneapolis MN")) {
            this.latitude = 41.18;
            this.longitude = -73.19;
        }
        else if (locString.equals("New Orleans LA")) {
            this.latitude = 29.95;
            this.longitude = -90.07;
        }
        else if (locString.equals("New York NY")) {
            this.latitude = 40.76;
            this.longitude = -73.97;
        }
        else if (locString.equals("Philadelphia PA")) {
            this.latitude = 39.95;
            this.longitude = -75.16;
        }
        else if (locString.equals("Phoenix AZ")) {
            this.latitude = 33.3;
            this.longitude = -112.03;
        }
        else if (locString.equals("Raleigh NC")) {
            this.latitude = 35.78;
            this.longitude = -78.64;
        }
        else if (locString.equals("Reno NV")) {
            this.latitude = 39.52;
            this.longitude = -119.81;
        }
        else if (locString.equals("Saint Louis")) {
            this.latitude = 38.63;
            this.longitude = -90.18;
        }
        else if (locString.equals("Salt Lake City UT")) {
            this.latitude = 40.77;
            this.longitude = -111.88;
        }
        else if (locString.equals("San Francisco CA")) {
            this.latitude = 37.79;
            this.longitude = -122.43;
        }
        else if (locString.equals("Topeka KS")) {
            this.latitude = 39.05;
            this.longitude = -95.67;
        }
        else if (locString.equals("Walla Walla WA")) {
            this.latitude = 46.07;
            this.longitude = -118.34;
        }
        else if (locString.equals("Washington")) {
            this.latitude = 38.92;
            this.longitude = -77.0;
        }
        else if (locString.equals("Montreal")) {
            this.latitude = 45.5;
            this.longitude = -73.6;
        }
        else if (locString.equals("Vancouver")) {
            this.latitude = 49.32;
            this.longitude = -123.08;
        }
        else if (locString.equals("Winnipeg")) {
            this.latitude = 49.88;
            this.longitude = -97.17;
        }
        else if (locString.equals("Bogota")) {
            this.latitude = 3.0;
            this.longitude = -102.0;
        }
        else if (locString.equals("Buenos Aires")) {
            this.latitude = -34.8;
            this.longitude = -58.0;
        }
        else if (locString.equals("Caracas")) {
            this.latitude = 10.5;
            this.longitude = -66.93;
        }
        else if (locString.equals("Caracas")) {
            this.latitude = 10.5;
            this.longitude = -66.93;
        }
        else if (locString.equals("La Paz")) {
            this.latitude = -16.5;
            this.longitude = -68.15;
        }
        else if (locString.equals("Lima")) {
            this.latitude = -12.05;
            this.longitude = -77.05;
        }
        else if (locString.equals("Montevideo")) {
            this.latitude = -34.9;
            this.longitude = -56.17;
        }
        else if (locString.equals("Quito")) {
            this.latitude = -0.5;
            this.longitude = -78.5;
        }
        else if (locString.equals("Rio de Janeiro")) {
            this.latitude = -22.88;
            this.longitude = -43.28;
        }
        else if (locString.equals("Santiago")) {
            this.latitude = -33.45;
            this.longitude = -70.67;
        }
        else if (locString.equals("Auckland")) {
            this.latitude = -36.85;
            this.longitude = 174.77;
        }
        else if (locString.equals("Sydney")) {
            this.latitude = -33.87;
            this.longitude = 151.22;
        }
        else if (locString.equals("Tokyo")) {
            this.latitude = 35.7;
            this.longitude = 139.77;
        }
        else if (locString.equals("New Delhi")) {
            this.latitude = 28.63;
            this.longitude = 77.2;
        }
        else if (locString.equals("Hong Kong")) {
            this.latitude = 22.5;
            this.longitude = 114.0;
        }
        else if (locString.equals("Jakarta")) {
            this.latitude = -6.0;
            this.longitude = 107.0;
        }
        else if (locString.equals("Peking")) {
            this.latitude = 39.91;
            this.longitude = 116.5;
        }
        else if (locString.equals("Singapore")) {
            this.latitude = 1.27;
            this.longitude = 103.85;
        }
        if (n == 1) {
            value = this.latitude;
        }
        else {
            value = this.longitude;
        }
        return value;
    }
    
    Seloc() {
        this.separator = "---------------";
    }
}
