package ToDoService.services;


import ToDoService.models.Task;
import ToDoService.models.TaskCategory;
import ToDoService.models.TaskWithId;
import ToDoService.models.User;
import ToDoService.models.dto.DTO_Task;
import ToDoService.models.dto.Filter;
import ToDoService.models.dto.UpdateInfo;
import ToDoService.repositories.TaskCategoryRepository;
import ToDoService.repositories.TaskRepository;
import ToDoService.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public Object getAllTasks(Filter filter, String sortBy) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime startDateBegin = null;
        LocalDateTime startDateEnd = null;
        LocalDateTime updateDateBegin = null;
        LocalDateTime updateDateEnd = null;
        Integer category = null;

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

        List<Task> ListTask = new ArrayList<>();


        for (TaskWithId dt : dtoTaskList) {

            User usr = userRepository.findUserByUserId(dt.getUserId());
            TaskCategory ctg = taskCategoryRepository.findTaskCategoryById(dt.getCategoryId());

            Task task = new Task(dt.getTaskId(),
                    dt.getDescription(),
                    dt.getStatus(),
                    dt.getCreateTime(),
                    dt.getUpdateTime(),
                    usr,
                    ctg
            );
            ListTask.add(task);
        }


        return ListTask;
    }


    public UpdateInfo updateTaskStatus(Integer ID, Boolean status) {

        Optional<Task> taskToUpdate = this.taskRepository.findById(ID);
        Task t = taskToUpdate.orElseThrow(NullPointerException::new);
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

        System.out.println(task);

        return this.taskRepository.save(task);

    }

}

