package ToDoService.services;


import ToDoService.models.Task;
import ToDoService.models.TaskCategory;
import ToDoService.models.User;
import ToDoService.models.dto.DTO_Task;
import ToDoService.models.dto.Filter;
import ToDoService.repositories.TaskCategoryRepository;
import ToDoService.repositories.TaskRepository;
import ToDoService.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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


    public List<Task> getAllTasks(Filter filter, String sortBy) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime startDateBegin = null;
        LocalDateTime startDateEnd = null;
        LocalDateTime updateDateBegin = null;
        LocalDateTime updateDateEnd = null;
        TaskCategory category = null;

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
//        if (filter.getCategory() != null) {
//            category = this.taskCategoryRepository.findTaskCategoryById(filter.getCategory());
//        }

        if (sortBy == null) {
            sortBy = "task_id";
        }

//        LocalDateTime startDateBegin = LocalDate.parse(filter.getStartDateBegin(), formatter).atStartOfDay();
//        LocalDateTime startDateEnd = LocalDate.parse(filter.getStartDateEnd(), formatter).atStartOfDay();
//        LocalDateTime updateDateBegin = LocalDate.parse(filter.getUpdateDateBegin(), formatter).atStartOfDay();
//        LocalDateTime updateDateEnd = LocalDate.parse(filter.getUpdateDateEnd(), formatter).atStartOfDay();
//        TaskCategory category = this.taskCategoryRepository.findTaskCategoryById(filter.getCategory());

        //Very questionable optional parameters filter logic, probably have to be in CRUD level or idk


//

        List<Task> result = taskRepository.findAllByCategoryAndCreateTimeBetweenAndUpdateTimeBetween(startDateBegin, startDateEnd, updateDateBegin, updateDateEnd, filter.getCategory(), sortBy);
        System.out.println(result);
        return result;
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

        System.out.println(category == null);
        System.out.println(usr == null);

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

