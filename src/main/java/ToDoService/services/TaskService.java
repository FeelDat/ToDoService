package ToDoService.services;


import ToDoService.models.dto.DTO_Task;
import ToDoService.models.Task;
import ToDoService.models.TaskCategory;
import ToDoService.models.User;
import ToDoService.models.dto.Filter;
import ToDoService.repositories.TaskCategoryRepository;
import ToDoService.repositories.TaskRepository;
import ToDoService.repositories.UserRepository;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

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

    public List<Task> getAllTasks(Filter filter) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//        LocalDateTime startDateBegin = new

        if () {

        }
        LocalDateTime startDateBegin = LocalDate.parse(filter.getStartDateBegin(), formatter).atStartOfDay();
        LocalDateTime startDateEnd = LocalDate.parse(filter.getStartDateEnd(), formatter).atStartOfDay();
        LocalDateTime updateDateBegin = LocalDate.parse(filter.getUpdateDateBegin(), formatter).atStartOfDay();
        LocalDateTime updateDateEnd = LocalDate.parse(filter.getUpdateDateEnd(), formatter).atStartOfDay();
        TaskCategory category = this.taskCategoryRepository.findTaskCategoryById(filter.getCategory());

        //Very questionable optional parameters filter logic, probably have to be in CRUD level or idk



        if(startDateBegin != null && startDateEnd != null && updateDateBegin != null && updateDateEnd != null && category != null) {
            return this.taskRepository.findAllByCategoryAndCreateTimeBetweenAndUpdateTimeBetween(category, startDateBegin, startDateEnd, updateDateBegin, updateDateEnd);
        } else {
            return new ArrayList<Task>();
        }

    }

    public void updateTaskStatus(Integer ID, Boolean status) {

        Optional<Task> taskToUpdate = this.taskRepository.findById(ID);
        Task t = taskToUpdate.orElseThrow(NullPointerException::new);
        t.setStatus(status);

        t.setUpdateTime(LocalDateTime.now());

        this.taskRepository.save(t);
        System.out.println("Task Updated");

    }

    public void addTask(DTO_Task taskDto) {



        TaskCategory category = taskCategoryRepository.findTaskCategoryById(taskDto.getCategory_id());
        User usr = userRepository.findUserByUserId(taskDto.getUser_id());
//
        System.out.println(category == null);
        System.out.println(usr == null);
//
        Task task = new Task(taskDto.getDescription(),
                taskDto.getStatus(),
                LocalDateTime.now(),
                usr,
                category
        );

         System.out.println(task);

        this.taskRepository.save(task);

    }

}

