package ToDoService.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Filter {


//    public Filter() {
//        this.startDateBegin = null;
//        this.startDateEnd = null;
//        this.updateDateBegin = null;
//        this.updateDateEnd = null;
//    }

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
