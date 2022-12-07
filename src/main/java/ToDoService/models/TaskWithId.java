package ToDoService.models;

import java.time.LocalDateTime;

public interface TaskWithId {

    Integer getTaskId();

    String getDescription();

    Boolean getStatus();


    LocalDateTime getCreateTime();

    LocalDateTime getUpdateTime();

    Integer getUserId();

    Integer getCategoryId();

}
