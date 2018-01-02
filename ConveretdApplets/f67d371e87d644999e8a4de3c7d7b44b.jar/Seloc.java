import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

class Seloc
{
    double latitude;
    double longitude;
    int offset;
    
    public void locMenu(final Choice locChoice) {
        locChoice.addItem("User Input");
        locChoice.addItem("_________");
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
        locChoice.addItem("Lisbon");
        locChoice.addItem("Ljubljana");
        locChoice.addItem("London");
        locChoice.addItem("Luxembourg");
        locChoice.addItem("Madrid");
        locChoice.addItem("Nicosia");
        locChoice.addItem("Oslo");
        locChoice.addItem("P. de Mallorca");
        locChoice.addItem("Paris");
        locChoice.addItem("Prague");
        locChoice.addItem("Reykjavik");
        locChoice.addItem("Riga");
        locChoice.addItem("Rome");
        locChoice.addItem("Sarajevo");
        locChoice.addItem("Skopje");
        locChoice.addItem("Sofia");
        locChoice.addItem("Stockholm");
        locChoice.addItem("Tallinn");
        locChoice.addItem("Tirana");
        locChoice.addItem("Vienna");
        locChoice.addItem("Vilnius");
        locChoice.addItem("Warsaw");
        locChoice.addItem("Zagreb");
        locChoice.addItem("Zurich");
        locChoice.addItem("_________");
        locChoice.addItem("Berlin");
        locChoice.addItem("Dortmund");
        locChoice.addItem("Dresden");
        locChoice.addItem("Frankfurt/M");
        locChoice.addItem("Frankfurt/O");
        locChoice.addItem("Hamburg");
        locChoice.addItem("Hannover");
        locChoice.addItem("Kiel");
        locChoice.addItem("Koeln");
        locChoice.addItem("Leipzig");
        locChoice.addItem("Muenchen");
        locChoice.addItem("Rostock");
        locChoice.addItem("Stuttgart");
        locChoice.addItem("50N10E");
        locChoice.addItem("_________");
        locChoice.addItem("Kiev");
        locChoice.addItem("Moscow");
        locChoice.addItem("St. Petersburg");
        locChoice.addItem("Vladivostok");
        locChoice.addItem("_________");
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
        locChoice.addItem("Fort Lauderdale FL");
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
        locChoice.addItem("Saint Louis MS");
        locChoice.addItem("Salt Lake City UT");
        locChoice.addItem("Santa Fe NM");
        locChoice.addItem("San Francisco CA");
        locChoice.addItem("Washington DC");
        locChoice.addItem("_________");
        locChoice.addItem("Montreal");
        locChoice.addItem("Vancouver");
        locChoice.addItem("Winnipeg");
        locChoice.addItem("_________");
        locChoice.addItem("Bangkog");
        locChoice.addItem("Beijing");
        locChoice.addItem("Buenos Aires");
        locChoice.addItem("Cairo");
        locChoice.addItem("Cape Town");
        locChoice.addItem("Caracas");
        locChoice.addItem("Hong Kong");
        locChoice.addItem("Jakarta");
        locChoice.addItem("Lima");
        locChoice.addItem("Rio de Janeiro");
        locChoice.addItem("Santiago");
        locChoice.addItem("Singapore");
        locChoice.addItem("Sydney");
        locChoice.addItem("Tel Aviv");
        locChoice.addItem("Tokyo");
    }
    
    public void timeMenu(final Choice timeChoice) {
        timeChoice.addItem("UT  +/- 0 h");
        timeChoice.addItem("UT  +1 h");
        timeChoice.addItem("UT  -1 h");
        timeChoice.addItem("UT  -2 h");
        timeChoice.addItem("UT  +2 h");
        timeChoice.addItem("UT  -3 h");
        timeChoice.addItem("UT  +3 h");
        timeChoice.addItem("UT  -4 h");
        timeChoice.addItem("UT  +4 h");
        timeChoice.addItem("UT  -5 h");
        timeChoice.addItem("UT  +5 h");
        timeChoice.addItem("UT  -6 h");
        timeChoice.addItem("UT  +6 h");
        timeChoice.addItem("UT  -7 h");
        timeChoice.addItem("UT  +7 h");
        timeChoice.addItem("UT  -8 h");
        timeChoice.addItem("UT  +8 h");
        timeChoice.addItem("UT  -9 h");
        timeChoice.addItem("UT  +9 h");
        timeChoice.addItem("UT  -10 h");
        timeChoice.addItem("UT  +10 h");
        timeChoice.addItem("UT  -11 h");
        timeChoice.addItem("UT  +11 h");
        timeChoice.addItem("UT  -12 h");
        timeChoice.addItem("UT  +12 h");
        timeChoice.addItem("UT  +13 h");
    }
    
    public int getTimeZone(final String timeString) {
        int timeOffset = 0;
        if (timeString.equals("UT +/- 0 h")) {
            timeOffset = 0;
        }
        else if (timeString.equals("UT  -1 h")) {
            timeOffset = -1;
        }
        else if (timeString.equals("UT  +1 h")) {
            timeOffset = 1;
        }
        else if (timeString.equals("UT  -2 h")) {
            timeOffset = -2;
        }
        else if (timeString.equals("UT  +2 h")) {
            timeOffset = 2;
        }
        else if (timeString.equals("UT  -3 h")) {
            timeOffset = -3;
        }
        else if (timeString.equals("UT  +3 h")) {
            timeOffset = 3;
        }
        else if (timeString.equals("UT  -4 h")) {
            timeOffset = -4;
        }
        else if (timeString.equals("UT  +4 h")) {
            timeOffset = 4;
        }
        else if (timeString.equals("UT  -5 h")) {
            timeOffset = -5;
        }
        else if (timeString.equals("UT  +5 h")) {
            timeOffset = 5;
        }
        else if (timeString.equals("UT  -6 h")) {
            timeOffset = -6;
        }
        else if (timeString.equals("UT  +6 h")) {
            timeOffset = 6;
        }
        else if (timeString.equals("UT  -7 h")) {
            timeOffset = -7;
        }
        else if (timeString.equals("UT  +7 h")) {
            timeOffset = 7;
        }
        else if (timeString.equals("UT  -8 h")) {
            timeOffset = -8;
        }
        else if (timeString.equals("UT  +8 h")) {
            timeOffset = 8;
        }
        else if (timeString.equals("UT  -9 h")) {
            timeOffset = -9;
        }
        else if (timeString.equals("UT  +9 h")) {
            timeOffset = 9;
        }
        else if (timeString.equals("UT  -10 h")) {
            timeOffset = -10;
        }
        else if (timeString.equals("UT  +10 h")) {
            timeOffset = 10;
        }
        else if (timeString.equals("UT  -11 h")) {
            timeOffset = -11;
        }
        else if (timeString.equals("UT  +11 h")) {
            timeOffset = 11;
        }
        else if (timeString.equals("UT  -12 h")) {
            timeOffset = -12;
        }
        else if (timeString.equals("UT  +12 h")) {
            timeOffset = 12;
        }
        else if (timeString.equals("UT  +13 h")) {
            timeOffset = 13;
        }
        return timeOffset;
    }
    
    public double getLatLong(final String locString, final int n) {
        double value = 0.0;
        int dst = 1;
        if (locString.equals("User Input")) {
            this.latitude = 0.0;
            this.longitude = 0.0;
            this.offset = 0;
        }
        else if (locString.equals("Dortmund")) {
            this.latitude = 51.52;
            this.longitude = 7.47;
            this.offset = 1;
        }
        else if (locString.equals("Dresden")) {
            this.latitude = 51.05;
            this.longitude = 13.73;
            this.offset = 1;
        }
        else if (locString.equals("Hamburg")) {
            this.latitude = 53.55;
            this.longitude = 9.97;
            this.offset = 1;
        }
        else if (locString.equals("Hannover")) {
            this.latitude = 52.37;
            this.longitude = 9.74;
            this.offset = 1;
        }
        else if (locString.equals("Muenchen")) {
            this.latitude = 48.15;
            this.longitude = 11.61;
            this.offset = 1;
        }
        else if (locString.equals("Berlin")) {
            this.latitude = 52.51;
            this.longitude = 13.4;
            this.offset = 1;
        }
        else if (locString.equals("Frankfurt/M")) {
            this.latitude = 50.11;
            this.longitude = 8.69;
            this.offset = 1;
        }
        else if (locString.equals("Frankfurt/O")) {
            this.latitude = 52.37;
            this.longitude = 14.55;
            this.offset = 1;
        }
        else if (locString.equals("Koeln")) {
            this.latitude = 50.94;
            this.longitude = 6.96;
            this.offset = 1;
        }
        else if (locString.equals("Kiel")) {
            this.latitude = 54.32;
            this.longitude = 10.12;
            this.offset = 1;
        }
        else if (locString.equals("Leipzig")) {
            this.latitude = 51.34;
            this.longitude = 12.39;
            this.offset = 1;
        }
        else if (locString.equals("Rostock")) {
            this.latitude = 54.09;
            this.longitude = 12.14;
            this.offset = 1;
        }
        else if (locString.equals("Stuttgart")) {
            this.latitude = 48.78;
            this.longitude = 9.18;
            this.offset = 1;
        }
        else if (locString.equals("50N10E")) {
            this.latitude = 50.0;
            this.longitude = 10.0;
            this.offset = 1;
        }
        else if (locString.equals("Ankara")) {
            this.latitude = 40.13;
            this.longitude = 33.0;
            this.offset = 2;
        }
        else if (locString.equals("Amsterdam")) {
            this.latitude = 52.38;
            this.longitude = 4.89;
            this.offset = 1;
        }
        else if (locString.equals("Athens")) {
            this.latitude = 37.97;
            this.longitude = 23.72;
            this.offset = 2;
        }
        else if (locString.equals("Belgrade")) {
            this.latitude = 44.83;
            this.longitude = 20.46;
            this.offset = 2;
        }
        else if (locString.equals("Bratislava")) {
            this.latitude = 48.15;
            this.longitude = 17.12;
            this.offset = 1;
        }
        else if (locString.equals("Brussels")) {
            this.latitude = 50.85;
            this.longitude = 4.37;
            this.offset = 1;
        }
        else if (locString.equals("Bucharest")) {
            this.latitude = 44.5;
            this.longitude = 26.1;
            this.offset = 2;
        }
        else if (locString.equals("Budapest")) {
            this.latitude = 47.49;
            this.longitude = 19.06;
            this.offset = 1;
        }
        else if (locString.equals("Copenhagen")) {
            this.latitude = 55.69;
            this.longitude = 12.58;
            this.offset = 1;
        }
        else if (locString.equals("Dublin")) {
            this.latitude = 53.39;
            this.longitude = -6.34;
            this.offset = 0;
        }
        else if (locString.equals("Edinburgh")) {
            this.latitude = 55.95;
            this.longitude = -3.1;
            this.offset = 0;
        }
        else if (locString.equals("Geneva")) {
            this.latitude = 46.2;
            this.longitude = 6.15;
            this.offset = 1;
        }
        else if (locString.equals("Helsinki")) {
            this.latitude = 60.16;
            this.longitude = 24.96;
            this.offset = 2;
        }
        else if (locString.equals("Kiev")) {
            this.latitude = 50.45;
            this.longitude = 30.3;
            this.offset = 2;
        }
        else if (locString.equals("Lisbon")) {
            this.latitude = 38.71;
            this.longitude = -9.19;
            this.offset = 0;
        }
        else if (locString.equals("Ljubljana")) {
            this.latitude = 46.055;
            this.longitude = 14.514;
            this.offset = 1;
        }
        else if (locString.equals("London")) {
            this.latitude = 51.51;
            this.longitude = -0.1;
            this.offset = 0;
        }
        else if (locString.equals("Luxembourg")) {
            this.latitude = 49.6;
            this.longitude = 6.2;
            this.offset = 1;
        }
        else if (locString.equals("Madrid")) {
            this.latitude = 40.41;
            this.longitude = -3.69;
            this.offset = 1;
        }
        else if (locString.equals("Nicosia")) {
            this.latitude = 35.17;
            this.longitude = 33.37;
            this.offset = 2;
        }
        else if (locString.equals("Oslo")) {
            this.latitude = 59.92;
            this.longitude = 10.73;
            this.offset = 1;
        }
        else if (locString.equals("P. de Mallorca")) {
            this.latitude = 39.57;
            this.longitude = 2.65;
            this.offset = 1;
        }
        else if (locString.equals("Paris")) {
            this.latitude = 48.84;
            this.longitude = 2.34;
            this.offset = 1;
        }
        else if (locString.equals("Prague")) {
            this.latitude = 50.09;
            this.longitude = 14.42;
            this.offset = 1;
        }
        else if (locString.equals("Reykjavik")) {
            this.latitude = 64.15;
            this.longitude = -21.92;
            this.offset = -1;
        }
        else if (locString.equals("Riga")) {
            this.latitude = 56.95;
            this.longitude = 24.1;
            this.offset = 2;
        }
        else if (locString.equals("Rome")) {
            this.latitude = 41.9;
            this.longitude = 12.48;
            this.offset = 1;
        }
        else if (locString.equals("Sarajevo")) {
            this.latitude = 43.85;
            this.longitude = 18.38;
            this.offset = 1;
        }
        else if (locString.equals("Skopje")) {
            this.latitude = 42.0;
            this.longitude = 21.43;
            this.offset = 1;
        }
        else if (locString.equals("Sofia")) {
            this.latitude = 42.7;
            this.longitude = 23.45;
            this.offset = 2;
        }
        else if (locString.equals("Stockholm")) {
            this.latitude = 59.34;
            this.longitude = 18.06;
            this.offset = 1;
        }
        else if (locString.equals("Tallinn")) {
            this.latitude = 59.43;
            this.longitude = 24.73;
            this.offset = 2;
        }
        else if (locString.equals("Tirana")) {
            this.latitude = 41.2;
            this.longitude = 19.9;
            this.offset = 1;
        }
        else if (locString.equals("Warsaw")) {
            this.latitude = 52.22;
            this.longitude = 21.03;
            this.offset = 1;
        }
        else if (locString.equals("Vienna")) {
            this.latitude = 48.21;
            this.longitude = 16.38;
            this.offset = 1;
        }
        else if (locString.equals("Vilnius")) {
            this.latitude = 54.68;
            this.longitude = 25.32;
            this.offset = 2;
        }
        else if (locString.equals("Zagreb")) {
            this.latitude = 45.8;
            this.longitude = 15.98;
            this.offset = 1;
        }
        else if (locString.equals("Zurich")) {
            this.latitude = 47.38;
            this.longitude = 8.55;
            this.offset = 1;
        }
        else if (locString.equals("Kiev")) {
            this.latitude = 50.45;
            this.longitude = 30.5;
            this.offset = 2;
        }
        else if (locString.equals("Moscow")) {
            this.latitude = 55.76;
            this.longitude = 37.57;
            this.offset = 3;
        }
        else if (locString.equals("St. Petersburg")) {
            this.latitude = 59.92;
            this.longitude = 30.25;
            this.offset = 3;
        }
        else if (locString.equals("Vladivostok")) {
            this.latitude = 43.17;
            this.longitude = 131.93;
            this.offset = 10;
        }
        else if (locString.equals("Anchorage AK")) {
            this.latitude = 61.22;
            this.longitude = -149.0;
            this.offset = -9;
        }
        else if (locString.equals("Atlanta GA")) {
            this.latitude = 33.75;
            this.longitude = -84.39;
            this.offset = -5;
        }
        else if (locString.equals("Baltimore MD")) {
            this.latitude = 39.29;
            this.longitude = -76.61;
            this.offset = -5;
        }
        else if (locString.equals("Birmingham AL")) {
            this.latitude = 33.52;
            this.longitude = -86.81;
            this.offset = -6;
        }
        else if (locString.equals("Bismarck ND")) {
            this.latitude = 46.81;
            this.longitude = -100.78;
            this.offset = -6;
        }
        else if (locString.equals("Boston MA")) {
            this.latitude = 42.36;
            this.longitude = -71.06;
            this.offset = -5;
        }
        else if (locString.equals("Bridgeport CT")) {
            this.latitude = 41.17;
            this.longitude = -73.2;
            this.offset = -5;
        }
        else if (locString.equals("Chicago IL")) {
            this.latitude = 41.85;
            this.longitude = -87.65;
            this.offset = -6;
        }
        else if (locString.equals("Cleveland OH")) {
            this.latitude = 41.5;
            this.longitude = -81.7;
            this.offset = -5;
        }
        else if (locString.equals("Concord NH")) {
            this.latitude = 43.21;
            this.longitude = -71.54;
            this.offset = -5;
        }
        else if (locString.equals("Dallas TX")) {
            this.latitude = 32.78;
            this.longitude = -96.8;
            this.offset = -6;
        }
        else if (locString.equals("Denver CO")) {
            this.latitude = 39.74;
            this.longitude = -104.98;
            this.offset = -7;
        }
        else if (locString.equals("Des Moines IA")) {
            this.latitude = 41.6;
            this.longitude = -93.61;
            this.offset = -6;
        }
        else if (locString.equals("Detroit MI")) {
            this.latitude = 42.33;
            this.longitude = -83.05;
            this.offset = -5;
        }
        else if (locString.equals("Dover DE")) {
            this.latitude = 39.16;
            this.longitude = -75.53;
            this.offset = -5;
        }
        else if (locString.equals("Eugene OR")) {
            this.latitude = 44.05;
            this.longitude = -123.09;
            this.offset = -8;
        }
        else if (locString.equals("Fort Lauderdale FL")) {
            this.latitude = 26.12;
            this.longitude = -80.14;
            this.offset = -5;
        }
        else if (locString.equals("Fort Knox KY")) {
            this.latitude = 37.91;
            this.longitude = -85.97;
            this.offset = -5;
        }
        else if (locString.equals("Green Bay WI")) {
            this.latitude = 44.52;
            this.longitude = -88.02;
            this.offset = -6;
        }
        else if (locString.equals("Helena MT")) {
            this.latitude = 46.59;
            this.longitude = -112.04;
            this.offset = -7;
        }
        else if (locString.equals("Honolulu HI")) {
            this.latitude = 21.31;
            this.longitude = -157.86;
            this.offset = -10;
            dst = 0;
        }
        else if (locString.equals("Indianapolis IN")) {
            this.latitude = 39.77;
            this.longitude = -86.16;
            this.offset = -5;
        }
        else if (locString.equals("Jackson WY")) {
            this.latitude = 43.61;
            this.longitude = -110.73;
            this.offset = -6;
        }
        else if (locString.equals("Memphis TN")) {
            this.latitude = 35.15;
            this.longitude = -90.05;
            this.offset = -6;
        }
        else if (locString.equals("Minneapolis MN")) {
            this.latitude = 44.98;
            this.longitude = -93.26;
            this.offset = -6;
        }
        else if (locString.equals("New Orleans LA")) {
            this.latitude = 29.95;
            this.longitude = -90.07;
            this.offset = -6;
        }
        else if (locString.equals("New York NY")) {
            this.latitude = 40.76;
            this.longitude = -73.97;
            this.offset = -5;
        }
        else if (locString.equals("Philadelphia PA")) {
            this.latitude = 39.95;
            this.longitude = -75.16;
            this.offset = -5;
        }
        else if (locString.equals("Phoenix AZ")) {
            this.latitude = 33.3;
            this.longitude = -112.03;
            this.offset = -8;
            dst = 0;
        }
        else if (locString.equals("Raleigh NC")) {
            this.latitude = 35.78;
            this.longitude = -78.64;
            this.offset = -5;
        }
        else if (locString.equals("Reno NV")) {
            this.latitude = 39.52;
            this.longitude = -119.81;
            this.offset = -8;
        }
        else if (locString.equals("Saint Louis MS")) {
            this.latitude = 38.63;
            this.longitude = -90.18;
            this.offset = -6;
        }
        else if (locString.equals("Salt Lake City UT")) {
            this.latitude = 40.77;
            this.longitude = -111.88;
            this.offset = -7;
        }
        else if (locString.equals("Santa Fe NM")) {
            this.latitude = 35.69;
            this.longitude = -105.94;
            this.offset = -7;
        }
        else if (locString.equals("San Francisco CA")) {
            this.latitude = 37.79;
            this.longitude = -122.43;
            this.offset = -8;
        }
        else if (locString.equals("Topeka KS")) {
            this.latitude = 39.05;
            this.longitude = -95.67;
            this.offset = -6;
        }
        else if (locString.equals("Washington DC")) {
            this.latitude = 38.92;
            this.longitude = -77.0;
            this.offset = -5;
        }
        else if (locString.equals("Montreal")) {
            this.latitude = 45.5;
            this.longitude = -73.6;
            this.offset = -5;
        }
        else if (locString.equals("Vancouver")) {
            this.latitude = 49.32;
            this.longitude = -123.08;
            this.offset = -8;
        }
        else if (locString.equals("Winnipeg")) {
            this.latitude = 49.88;
            this.longitude = -97.17;
            this.offset = -6;
        }
        else if (locString.equals("Bangkog")) {
            this.latitude = 13.2;
            this.longitude = 100.5;
            this.offset = 7;
            dst = 0;
        }
        else if (locString.equals("Buenos Aires")) {
            this.latitude = -34.6;
            this.longitude = -58.45;
            this.offset = -3;
        }
        else if (locString.equals("Caracas")) {
            this.latitude = 10.5;
            this.longitude = -66.93;
            this.offset = -4;
            dst = 0;
        }
        else if (locString.equals("Hong Kong")) {
            this.latitude = 22.3;
            this.longitude = 114.18;
            this.offset = 8;
            dst = 0;
        }
        else if (locString.equals("Cairo")) {
            this.latitude = 30.1;
            this.longitude = 31.25;
            this.offset = 2;
        }
        else if (locString.equals("Cape Town")) {
            this.latitude = -33.94;
            this.longitude = 18.49;
            this.offset = 2;
            dst = 0;
        }
        else if (locString.equals("Beijing")) {
            this.latitude = 39.91;
            this.longitude = 116.47;
            this.offset = 8;
            dst = 0;
        }
        else if (locString.equals("Rio de Janeiro")) {
            this.latitude = -22.88;
            this.longitude = -43.28;
            this.offset = -3;
        }
        else if (locString.equals("Singapore")) {
            this.latitude = 1.27;
            this.longitude = 103.51;
            this.offset = 8;
            dst = 0;
        }
        else if (locString.equals("Sydney")) {
            this.latitude = -33.87;
            this.longitude = 151.22;
            this.offset = 10;
        }
        else if (locString.equals("Tokyo")) {
            this.latitude = 35.7;
            this.longitude = 139.77;
            this.offset = 9;
            dst = 0;
        }
        else if (locString.equals("Lima")) {
            this.latitude = -12.05;
            this.longitude = -77.05;
            this.offset = -5;
        }
        else if (locString.equals("Santiago")) {
            this.latitude = -33.45;
            this.longitude = -70.67;
            this.offset = -6;
        }
        else if (locString.equals("Jakarta")) {
            this.latitude = -6.17;
            this.longitude = 106.8;
            this.offset = 7;
            dst = 0;
        }
        else if (locString.equals("Tel Aviv")) {
            this.latitude = 32.07;
            this.longitude = 34.77;
            this.offset = 2;
        }
        if (n == 1) {
            value = this.latitude;
        }
        if (n == 2) {
            value = this.longitude;
        }
        if (n == 3) {
            value = this.offset;
        }
        if (n == 4) {
            value = dst;
        }
        return value;
    }
    
    Seloc() {
        this.offset = 0;
    }
}
