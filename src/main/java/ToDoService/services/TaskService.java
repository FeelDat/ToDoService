package ToDoService.services;


import ToDoService.exception.NoDataFoundException;
import ToDoService.exception.TaskNotFoundException;
import ToDoService.models.Task;
import ToDoService.models.TaskCategory;
import ToDoService.models.TaskWithId;
import ToDoService.models.User;
import ToDoService.models.dto.DTO_Task;
import ToDoService.models.dto.Filter;
import ToDoService.models.dto.UpdateInfo;
import ToDoService.models.dto.jasperTaskDto;
import ToDoService.repositories.TaskCategoryRepository;
import ToDoService.repositories.TaskRepository;
import ToDoService.repositories.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskCategoryRepository taskCategoryRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskCategoryRepository taskCategoryRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskCategoryRepository = taskCategoryRepository;
    }

    public void getPdfReport(Filter filter, String sortBy) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime startDateBegin = null;
        LocalDateTime startDateEnd = null;
        LocalDateTime updateDateBegin = null;
        LocalDateTime updateDateEnd = null;
        Integer category = null;


        if (filter == null) {
            throw new NoDataFoundException();
        }

        if (filter.getStartDateBegin() != null) {
            startDateBegin = LocalDate.parse(filter.getStartDateBegin(), formatter).atStartOfDay();
        }
        if (filter.getStartDateEnd() != null) {
            startDateEnd = LocalDate.parse(filter.getStartDateEnd(), formatter).atStartOfDay();
        }
        if (filter.getUpdateDateBegin() != null) {
            updateDateBegin = LocalDate.parse(filter.getUpdateDateBegin(), formatter).atStartOfDay();
        }
        if (filter.getUpdateDateEnd() != null) {
            updateDateEnd = LocalDate.parse(filter.getUpdateDateEnd(), formatter).atStartOfDay();
        }
        if (filter.getCategory() != null) {
            category = filter.getCategory();
        }

        if (sortBy == null) {
            sortBy = "task_id";
        }

        List<TaskWithId> dtoTaskList = taskRepository.findAllByCategoryAndCreateTimeBetweenAndUpdateTimeBetween(startDateBegin, startDateEnd, updateDateBegin, updateDateEnd, category, sortBy);

        if (dtoTaskList.isEmpty()) {
            throw new NoDataFoundException();
        }

        List<jasperTaskDto> dtoJasperTaskList = new ArrayList<>();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (TaskWithId dt : dtoTaskList) {

            jasperTaskDto dtoJasperTask = new jasperTaskDto(dt.getTaskId()
                    , dt.getDescription()
                    , dt.getStatus()
                    , dt.getCreateTime() == null ?
                    null : dt.getCreateTime().format(dateTimeFormatter)
                    , dt.getUpdateTime() == null ?
                    null : dt.getUpdateTime().format(dateTimeFormatter)
                    , dt.getUserId(), dt.getCategoryId());
            dtoJasperTaskList.add(dtoJasperTask);
        }

        /* User home directory location */
        String userHomeDirectory = System.getProperty("user.home");

        /* Output file location */
        String outputFile = userHomeDirectory + File.separatorChar + "JasperTableExample.pdf";


        try {

            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(dtoJasperTaskList);

            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("tasks", itemsJRBean);

            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Alim.Aldybergen\\JavaProjects\\ToDoService\\src\\main\\resources\\jasper\\Blank_A4.jasper", parameters, new JREmptyDataSource());

            /* outputStream to create PDF */
            OutputStream outputStream = new FileOutputStream(new File(outputFile));
            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        } catch (
                JRException | FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    public Object getAllTasks(Filter filter, String sortBy) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime startDateBegin = null;
        LocalDateTime startDateEnd = null;
        LocalDateTime updateDateBegin = null;
        LocalDateTime updateDateEnd = null;
        Integer category = null;


        if (filter == null) {
            throw new NoDataFoundException();
        }

        if (filter.getStartDateBegin() != null) {
            startDateBegin = LocalDate.parse(filter.getStartDateBegin(), formatter).atStartOfDay();
        }
        if (filter.getStartDateEnd() != null) {
            startDateEnd = LocalDate.parse(filter.getStartDateEnd(), formatter).atStartOfDay();
        }
        if (filter.getUpdateDateBegin() != null) {
            updateDateBegin = LocalDate.parse(filter.getUpdateDateBegin(), formatter).atStartOfDay();
        }
        if (filter.getUpdateDateEnd() != null) {
            updateDateEnd = LocalDate.parse(filter.getUpdateDateEnd(), formatter).atStartOfDay();
        }
        if (filter.getCategory() != null) {
            category = filter.getCategory();
        }

        if (sortBy == null) {
            sortBy = "task_id";
        }

        List<TaskWithId> dtoTaskList = taskRepository.findAllByCategoryAndCreateTimeBetweenAndUpdateTimeBetween(startDateBegin, startDateEnd, updateDateBegin, updateDateEnd, category, sortBy);

        if (dtoTaskList.isEmpty()) {
            throw new NoDataFoundException();
        }
        List<Task> ListTask = new ArrayList<>();


        for (TaskWithId dt : dtoTaskList) {

            Integer a = dt.getTaskId();
            System.out.println(a);
            User usr = userRepository.findUserByUserId(dt.getUserId());
            TaskCategory ctg = taskCategoryRepository.findTaskCategoryById(dt.getCategoryId());

            Task task = new Task(dt.getTaskId()
                    , dt.getDescription()
                    , dt.getStatus()
                    , dt.getCreateTime()
                    , dt.getUpdateTime()
                    , usr
                    , ctg);

            ListTask.add(task);
        }


        return ListTask;
    }


    public UpdateInfo updateTaskStatus(Integer ID, Boolean status) {

        Task t = this.taskRepository.findById(ID).orElseThrow(() -> new TaskNotFoundException(ID));

        Boolean oldStatus = t.getStatus();
        t.setStatus(status);
        t.setUpdateTime(LocalDateTime.now());

        this.taskRepository.save(t);

        return new UpdateInfo(t.getTaskId(), oldStatus, t.getStatus());
    }

    public Task addTask(DTO_Task taskDto) {

        TaskCategory category = taskCategoryRepository.findTaskCategoryById(taskDto.getCategoryId());
        User usr = userRepository.findUserByUserId(taskDto.getUserId());

        System.out.println(category == null);
        System.out.println(usr == null);

        Task task = new Task(taskDto.getDescription(),
                taskDto.getStatus(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                usr,
                category
        );

        return this.taskRepository.save(task);

    }

}

