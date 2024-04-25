package hu.yokudlela.yokudlela.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiException {
    private String path;
    private String message;
    private Set<String> errors = new LinkedHashSet<>();



    public ApiException(String path, String message) {
        this.path = path;
        this.message = message;
    }
}
