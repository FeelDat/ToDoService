package ToDoService.services;


import ToDoService.models.dto.DTO_Task;
import ToDoService.models.Task;
import ToDoService.models.TaskCategory;
import ToDoService.models.User;
import ToDoService.models.dto.Filter;
import ToDoService.repositories.TaskCategoryRepository;
import ToDoService.repositories.TaskRepository;
import ToDoService.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public Iterable<Task> getAllTasks(Filter filter) {
//        public Iterable<Task> getAllTasks() {

        java.util.Date date = new Date();

        System.out.println("Getting all the tasks");

//        return this.taskRepository.findAll();
        List<Task> result = new ArrayList<Task>();

//        Iterable<Task> result = this.taskRepository.findTasksWithFilter(filter.getStartDateBegin(), filter.getUpdateDateEnd());


        return this.taskRepository.findAllByCreation_timeBetween(filter.getStartDateBegin(), filter.getUpdateDateEnd());
//        for (t i: DTO_result) {
//
//            TaskCategory category = taskCategoryRepository.findTaskCategoryById(i.getCategory_id());
//            User usr = userRepository.findUserByUserId(i.getUser_id());
//
//            Task task = new Task(i.getDescription(),
//                    i.getStatus(),
//                    new Timestamp(date.getTime()),
//                    usr,
//                    category
//            );
//            result.add(task);
//
//        }



       // return result;
    }


    public void updateTaskStatus(Integer ID, Boolean status) {

        Optional<Task> taskToUpdate = this.taskRepository.findById(ID);
        Task t = taskToUpdate.orElseThrow(() -> new NullPointerException());
        t.setStatus(status);

        java.util.Date date = new Date();
        t.setUpdateTime(new Timestamp(date.getTime()));

        this.taskRepository.save(t);
        System.out.println("Task Updated");

    }

    public void addTask(DTO_Task taskDto) {

        java.util.Date date = new Date();

        TaskCategory category = taskCategoryRepository.findTaskCategoryById(taskDto.getCategory_id());
        User usr = userRepository.findUserByUserId(taskDto.getUser_id());
//
        System.out.println(category == null);
        System.out.println(usr == null);
//
        Task task = new Task(taskDto.getDescription(),
                taskDto.getStatus(),
                new Timestamp(date.getTime()),
                usr,
                category
        );





         System.out.println(task);

        task = this.taskRepository.save(task);


    }

}

