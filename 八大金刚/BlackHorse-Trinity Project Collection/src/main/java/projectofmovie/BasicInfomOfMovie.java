package projectofmovie;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.namespace.QName;
@Getter
@Setter
public class BasicInfomOfMovie {
    private String movieName;
    private Double price;
    private String actorName;
    private Long idNumber;


    public BasicInfomOfMovie(){

    }
    public BasicInfomOfMovie(String movieName,Double price,String actorName,Long idNumber){
        this.movieName = movieName;
        this.price = price;
        this.actorName = actorName;
        this.idNumber = idNumber;
    }

    @Override
    public String toString(){
        return "{ " + "movie is: " + movieName +
                "/n" + "price is: " + price +
                "/n" + "actor is: " + actorName +
                "/n" + "id is: " + idNumber +
                " }";

    }

}
