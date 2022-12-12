package ToDoService.controller;


import ToDoService.models.dto.DTO_Task;
import ToDoService.models.dto.Filter;
import ToDoService.models.dto.Response;
import ToDoService.models.dto.UpdateInfo;
import ToDoService.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("todo")
@RestController

public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {

        this.taskService = taskService;

    }

    @GetMapping(path = "list")
    public Response getAllTasks(@RequestBody(required = false) Filter filter, @RequestParam(required = false) String sortBy) {


        return new Response(new Date(), taskService.getAllTasks(filter, sortBy), HttpStatus.OK);
    }

    @GetMapping(path = "pdfreport")
    public Response getPdfReport(@RequestBody(required = false) Filter filter, @RequestParam(required = false) String sortBy) {

        taskService.getPdfReport(filter, sortBy);
        return new Response(new Date(), null, HttpStatus.OK);
    }

    //Updating the status of the task in the database
    @PutMapping(path = "/{ID}/status/{status}")
    public Response updateTaskStatus(@PathVariable Integer ID, @PathVariable Boolean status) {

        UpdateInfo updateInfo = this.taskService.updateTaskStatus(ID, status);
        return new Response(new Date(), updateInfo, HttpStatus.OK);

    }

    //Adding new task to database
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public Response addTask(@RequestBody DTO_Task task) {

        System.out.println("Adding new task");


        return new Response(new Date(), this.taskService.addTask(task), HttpStatus.OK);
    }
}


