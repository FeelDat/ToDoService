package ToDoService.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "tasks")
@Data
public class Task {

    public Task() {

    }

    public Task(String description, Boolean status, LocalDateTime createTime, LocalDateTime updateTime, User user, TaskCategory taskCategory) {
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = user;
        this.category = taskCategory;
    }

    public Task(Integer Id, String description, Boolean status, LocalDateTime createTime, LocalDateTime updateTime, User user, TaskCategory taskCategory) {
        this.taskId = Id;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = user;
        this.category = taskCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "creation_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id")
    private TaskCategory category;


//    @Override
//    public String toString() {
//        return "Task ID: " + id + " Description: " + description + " Status: " + status;
//    }

}
