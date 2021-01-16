package pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter @Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class ArticlePOJO {

    private String author;
    private  String title;
    private String description;

}
