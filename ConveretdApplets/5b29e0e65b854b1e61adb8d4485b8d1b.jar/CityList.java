// 
// Decompiled by Procyon v0.5.30
// 

class CityList
{
    public static City[] cityList;
    
    public static City biggestCityInTimeZone(final double n, final Language language) {
        double popMillions = 0.0;
        boolean b = false;
        City city = null;
        for (int i = 0; i < CityList.cityList.length; ++i) {
            final City city2 = CityList.cityList[i];
            final boolean inLanguageList = city2.isInLanguageList(language);
            if (Math.abs(city2.timeZone - n) < 0.1 && (city2.popMillions > popMillions || (inLanguageList && !b))) {
                city = city2;
                popMillions = city2.popMillions;
                b = (b || inLanguageList);
            }
        }
        return city;
    }
    
    static {
        CityList.cityList = new City[] { new City("Alexandria", "Egypt", 3.38, 31.0, 30.0, 2.0), new City("Ankara", "Turkey", 3.03, 40.0, 33.0, 2.0, "tr"), new City("Athens", "Greece", 3.1, 38.0, 23.0, 2.0, "el"), new City("Atlanta", "United States", 3.14, 34.0, -84.0, -5.0, "en"), new City("Baghdad", "Iraq", 3.84, 33.0, 44.0, 3.0), new City("Bangalore", "India", 4.09, 13.0, 78.0, 5.5, "en"), new City("Bangkok", "Thailand", 5.88, 13.0, 100.0, 7.0), new City("Beijing", "China", 12.4, 40.0, 116.0, 8.0, "zh"), new City("Berlin", "Germany", 3.48, 52.0, 13.0, 1.0, "de"), new City("Bogota", "Colombia", 5.03, 4.6, -74.0, -5.0, "es"), new City("Boston", "United States", 3.21, 42.0, -71.0, -5.0, "en"), new City("Bombay", "India", 15.1, 19.0, 73.0, 5.5, "en"), new City("Buenos Aires", "Argentina", 11.0, -34.0, -58.0, -3.0, "es"), new City("Cairo", "Egypt", 9.66, 30.0, 31.0, 2.0), new City("Calcutta", "India", 11.7, 23.0, 88.0, 5.5, "en"), new City("Chicago", "United States", 7.56, 42.0, -87.0, -6.0, "en"), new City("Chongqing", "China", 3.87, 29.0, 106.0, 8.0, "zh"), new City("Copenhagen", "Denmark", 1.4, 56.0, 12.0, 1.0, "da"), new City("Delhi", "India", 9.88, 29.0, 77.0, 5.5, "en"), new City("Detroit", "United States", 4.31, 42.0, -83.0, -5.0, "en"), new City("Dhaka", "Bangladesh", 7.83, 24.0, 90.0, 6.0), new City("Guangzhou", "China", 3.75, 23.0, 113.0, 8.0, "zh"), new City("Harbin", "China", 3.12, 46.0, 127.0, 8.0, "zh"), new City("Hanoi", "Vietnam", 3.06, 21.0, 106.0, 7.0), new City("Ho Chi Minh City", "Vietnam", 3.92, 11.0, 107.0, 7.0), new City("Hong Kong", "China", 6.2, 22.0, 114.0, 8.0, "zh"), new City("Houston", "United States", 3.53, 30.0, -95.0, -6.0, "en"), new City("Hyderabad", "India", 4.28, 17.0, 79.0, 5.5, "en"), new City("Istanbul", "Turkey", 7.49, 41.0, 28.0, 2.0), new City("Jakarta", "Indonesia", 11.5, -6.0, 107.0, 7.0), new City("Karachi", "Pakistan", 9.86, 25.0, 67.0, 5.0), new City("Kinshasa", "Zaire", 3.8, -4.0, 15.0, 1.0), new City("Lagos", "Nigeria", 10.3, 6.0, 7.0, 1.0), new City("Lahore", "Pakistan", 5.08, 32.0, 74.0, 5.0), new City("Lima", "Peru", 6.6, -12.0, -77.0, -5.0, "es"), new City("London", "United Kingdom", 6.97, 51.0, 0.0, 0.0, "en"), new City("Los Angeles", "United States", 12.41, 34.0, -118.0, -8.0, "en"), new City("Madras", "India", 5.36, 13.0, 80.0, 5.5, "en"), new City("Madrid", "Spain", 3.04, 40.0, -3.0, 1.0, "es"), new City("Melbourne", "Australia", 3.19, -37.0, 145.0, 10.0, "en"), new City("Manila", "Philippines", 9.28, 15.0, 121.0, 8.0), new City("Mexico City", "Mexico", 15.6, 19.0, -99.0, -6.0, "es"), new City("Montreal", "Canada", 3.34, 43.0, -73.0, -5.0), new City("Moscow", "Russia", 9.23, 56.0, 38.0, 3.0), new City("New York", "United States", 16.3, 41.0, -74.0, -5.0, "en"), new City("Osaka", "Japan", 10.6, 35.0, 136.0, 9.0, "ja"), new City("Paris", "France", 9.47, 49.0, 2.0, 1.0, "fr"), new City("Philadelphia", "United States", 4.94, 40.0, -75.0, -5.0, "en"), new City("Pusan", "South Korea", 3.8, 35.0, 129.0, 9.0, "ko"), new City("Rio de Janeiro", "Brazil", 9.89, -23.0, -43.0, -3.0, "pt"), new City("Rome", "Italy", 2.7, 42.0, 12.5, 1.0, "it"), new City("San Francisco", "United States", 3.87, 37.0, -122.0, -8.0, "en"), new City("Santiago", "Chile", 4.63, -33.0, -70.0, -4.0, "es"), new City("S\u00e3o Paolo", "Brazil", 16.4, -23.0, -46.0, -3.0, "pt"), new City("Seoul", "South Korea", 11.6, 38.0, 127.0, 9.0, "ko"), new City("Shanghai", "China", 15.1, 31.0, 121.0, 8.0, "zh"), new City("Shenyang", "China", 4.05, 42.0, 123.0, 8.0, "zh"), new City("St. Petersburg", "Russia", 4.88, 60.0, 30.0, 3.0), new City("Sydney", "Australia", 3.71, -33.0, 152.0, 10.0, "en"), new City("Tehran", "Iran", 6.75, 36.0, 51.0, 3.5), new City("Tianjin", "China", 10.7, 39.0, 117.0, 8.0, "zh"), new City("Tokyo", "Japan", 26.8, 36.0, 140.0, 9.0, "ja"), new City("Toronto", "Canada", 4.34, 43.0, -79.0, -5.0, "en", "fr"), new City("Warszawa", "Poland", 1.7, 52.0, 21.0, 1.0, "pl"), new City("Washington, DC", "United States", 4.36, 39.0, -77.0, -5.0, "en"), new City("Wuhan", "China", 3.87, 31.0, 114.0, 8.0, "zh") };
    }
}
