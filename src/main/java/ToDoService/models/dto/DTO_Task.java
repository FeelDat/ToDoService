package ToDoService.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class DTO_Task {


    @JsonProperty(value = "task_id")
    private Integer task_id;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "status")
    private Boolean status;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    @JsonProperty(value = "creation_time")
    private LocalDateTime creation_time;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    @JsonProperty(value = "update_time")
    private LocalDateTime update_time;

    @JsonProperty(value = "user_id")
    private Integer user_id;

    @JsonProperty(value = "category_id")
    private Integer category_id;






}
