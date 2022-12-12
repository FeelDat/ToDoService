package ToDoService.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Integer id) {

        super(String.format("Task with Id %d not found, can not be updated", id));
    }
}
