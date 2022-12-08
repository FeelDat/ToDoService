package ToDoService.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Response {

    private Date requestDate;
    private Object body;
    private HttpStatus statusCode;

}
