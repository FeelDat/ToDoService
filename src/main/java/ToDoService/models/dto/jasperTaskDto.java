package ToDoService.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class jasperTaskDto {

    @JsonProperty(value = "task_id")
    private Integer taskId;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "status")
    private Boolean status;
    
    @JsonProperty(value = "creation_time")
    private String createTime;

    @JsonProperty(value = "update_time")
    private String updateTime;

    @JsonProperty(value = "user_id")
    private Integer userId;

    @JsonProperty(value = "category_id")
    private Integer categoryId;

}
