package ToDoService.controller;


import ToDoService.models.dto.DTO_Task;
import ToDoService.models.dto.Filter;
import ToDoService.models.dto.Response;
import ToDoService.models.dto.UpdateInfo;
import ToDoService.services.TaskService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
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

    @PostMapping("/list")
    public Response getAllTasks(@RequestBody(required = false) Filter filter, @RequestParam(required = false) String sortBy) {


        return new Response(new Date(), taskService.getAllTasks(filter, sortBy), HttpStatus.OK);
    }

    @PostMapping(value = "/pdfreport", produces = "application/pdf")
    public void getPdfReport(@RequestBody(required = false) Filter filter, @RequestParam(required = false) String sortBy, HttpServletResponse response) throws Exception {

        JasperPrint jasperPrint = this.taskService.getPdfReport(filter, sortBy);

        ServletOutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        response.addHeader("Content-disposition", "attachment; filename=" + "TaskReport.pdf");
        response.setContentType("application/pdf");
        outputStream.close();
        outputStream.flush();
        System.out.println("File Generated");
    }

    //Updating the status of the task in the database
    @PutMapping("/update/{ID}/status/{status}")
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


