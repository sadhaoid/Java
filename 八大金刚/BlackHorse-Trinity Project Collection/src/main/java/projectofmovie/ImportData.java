package projectofmovie;

import java.util.Arrays;



public class ImportData {
    public String getShowOnScreen(){
        BasicInfomOfMovie movie1 = new BasicInfomOfMovie("11",13.8,"a",01L);
        BasicInfomOfMovie movie2 = new BasicInfomOfMovie("22",26.8,"b",02L);
        BasicInfomOfMovie movie3 = new BasicInfomOfMovie("11",39.8,"c",03L);

        BasicInfomOfMovie[] showOnScreen = {movie1,movie2,movie3};

        return Arrays.toString(showOnScreen);
    }


}
