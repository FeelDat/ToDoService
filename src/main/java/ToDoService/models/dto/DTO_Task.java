package ToDoService.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Data
public class DTO_Task {


    @JsonProperty(value = "task_id")
    private Integer task_id;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "status")
    private Boolean status;

    @JsonProperty(value = "creation_time")
    private Timestamp creation_time;

    @JsonProperty(value = "update_time")
    private Timestamp update_time;

    @JsonProperty(value = "user_id")
    private Integer user_id;

    @JsonProperty(value = "category_id")
    private Integer category_id;






}
