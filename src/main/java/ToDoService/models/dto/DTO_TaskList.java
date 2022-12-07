package ToDoService.models.dto;

import ToDoService.models.Task;
import lombok.Data;

import java.util.List;

@Data
public class DTO_TaskList {

    private List<Task> TaskList;
}
