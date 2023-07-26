package api.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resulting<T> {
    private String jsonrpc;
    private T result;
    private String error;
    private int id;
}



