package ToDoService.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {


    @JsonProperty(value = "start_date_begin")
    private String startDateBegin;

    @JsonProperty(value = "start_date_end")
    private String startDateEnd;

    @JsonProperty(value = "update_date_begin")
    private String updateDateBegin;

    @JsonProperty(value = "update_date_end")
    private String updateDateEnd;

    @JsonProperty(value = "category")
    private Integer category;

}
