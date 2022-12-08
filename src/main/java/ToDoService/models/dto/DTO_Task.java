package ToDoService.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTO_Task {


    @JsonProperty(value = "task_id")
    private Integer taskId;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "status")
    private Boolean status;


    @JsonProperty(value = "creation_time")
    private LocalDateTime createTime;


    @JsonProperty(value = "update_time")
    private LocalDateTime updateTime;

    @JsonProperty(value = "user_id")
    private Integer userId;

    @JsonProperty(value = "category_id")
    private Integer categoryId;


    public DTO_Task(int i, int i1, String swim, boolean b) {
    }
}
