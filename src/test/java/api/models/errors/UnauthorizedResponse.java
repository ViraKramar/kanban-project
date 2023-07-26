package api.models.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class UnauthorizedResponse {
    private String jsonrpc;
    private ErrorUnauthorizedResponse error;
    private String message;
}

