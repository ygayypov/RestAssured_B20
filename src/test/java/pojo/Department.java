package pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

@Data //@ToString @Getter @Setter @EqualsAndHashCode @RequiredArgsConstructor

public class Department {

    private int department_id ;
    private String department_name ;
    private int manager_id ;
    private int location_id ;


}
