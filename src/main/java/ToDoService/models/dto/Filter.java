package ToDoService.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Filter {

    @JsonProperty(value = "start_date_begin")
    private Timestamp startDateBegin;

    @JsonProperty(value = "start_date_end")
    private Timestamp startDateEnd;

    @JsonProperty(value = "update_date_begin")
    private Timestamp updateDateBegin;

    @JsonProperty(value = "update_date_end")
    private Timestamp updateDateEnd;

    @JsonProperty(value = "category")
    private String category;

}
