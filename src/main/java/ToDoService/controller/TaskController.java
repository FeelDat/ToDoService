package ToDoService.controller;


import ToDoService.models.dto.DTO_Task;
import ToDoService.models.Task;
import ToDoService.models.dto.Filter;
import ToDoService.services.TaskService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("todo")
@RestController
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {

        this.taskService = taskService;

    }

    @GetMapping(path = "list")
    public Iterable<Task> getAllTasks(@RequestBody Filter filter) {
//        public Iterable<Task> getAllTasks() {

//        return this.taskService.getAllTasks();
        return this.taskService.getAllTasks(filter);

    }

    @PutMapping(path = "/{ID}/status/{status}")
    public void updateTaskStatus(@PathVariable Integer ID, @PathVariable Boolean status) {

        this.taskService.updateTaskStatus(ID, status);
        System.out.println("Status updated");

    }

    @PostMapping(path = "/add")
    public void addTask(@RequestBody DTO_Task task) {

        System.out.println("Adding new task");
        this.taskService.addTask(task);

    }


}
