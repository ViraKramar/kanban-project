package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class Result {
    private String username;
    private String name;
    private String email;
    private String id;
    private String title;
    private String reference;
    private String comment;

}
