package ToDoService.controller;

import ToDoService.models.Task;
import ToDoService.models.TaskCategory;
import ToDoService.models.User;
import ToDoService.models.dto.DTO_Task;
import ToDoService.models.dto.Filter;
import ToDoService.models.dto.Response;
import ToDoService.models.dto.UpdateInfo;
import ToDoService.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @InjectMocks
    TaskController tskController;

    @Mock
    TaskService tskService;

    @Test
    void getAllTasks() {

        Task task1 = new Task(1952, "Swim", true,
                LocalDateTime.parse("2022-10-08T15:35:54.2499249"),
                LocalDateTime.parse("2022-11-15T15:35:54.2499249"),
                new User(1, "Alim"), new TaskCategory(2, "Sport"));

        Task task2 = new Task(2465, "Run", true,
                LocalDateTime.parse("2022-10-04T15:35:54.2499249"),
                LocalDateTime.parse("2022-11-03T15:35:54.2499249"),
                new User(2, "Danila"), new TaskCategory(2, "Sport"));

        Filter filter = new Filter("2022-09-07T15:35:54.2499249",
                "2022-12-12T15:35:54.2499249",
                "2022-09-08T15:35:54.2499249",
                "2022-12-08T15:35:54.2499249",
                1);


        List<Task> testList = new ArrayList<>();
        testList.add(task1);
        testList.add(task2);

        when(tskService.getAllTasks(filter, "taskId")).thenReturn(testList);


        Response testResponse = tskController.getAllTasks(filter, "taskId");
        List<Task> resultList = (List<Task>) testResponse.getBody();
        HttpStatus resultStatusCode = testResponse.getStatusCode();

        assertThat(resultStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList.get(0)).isEqualTo(task1);
        assertThat(resultList.get(1)).isEqualTo(task2);

    }

    @Test
    void updateTaskStatus() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        UpdateInfo updateInfo = new UpdateInfo(1803, false, true);

        when(tskService.updateTaskStatus(1803, true)).thenReturn(updateInfo);


        Response testResponce = tskController.updateTaskStatus(1803, true);
        UpdateInfo resultUpdateInfo = (UpdateInfo) testResponce.getBody();
        HttpStatus resultStatusCode = testResponce.getStatusCode();

        assertThat(resultStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(resultUpdateInfo).isEqualTo(updateInfo);

    }

    @Test
    void addTask() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Task taskReceive = new Task(2465, "Run", true,
                LocalDateTime.parse("2022-10-04T15:35:54.2499249"),
                LocalDateTime.parse("2022-11-03T15:35:54.2499249"),
                new User(2, "Danila"), new TaskCategory(2, "Sport"));

        DTO_Task taskUpload = new DTO_Task(1, 1, "Swim", true);


        when(tskService.addTask(any(DTO_Task.class))).thenReturn(taskReceive);

        Response testResponce = tskController.addTask(taskUpload);
        Task resultTask = (Task) testResponce.getBody();
        HttpStatus resultStatusCode = testResponce.getStatusCode();

        assertThat(resultStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(resultTask).isEqualTo(taskReceive);
    }
}