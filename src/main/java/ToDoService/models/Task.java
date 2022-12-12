package ToDoService.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Task {


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


    public Task(String description, Boolean status, LocalDateTime createTime, LocalDateTime updateTime, User usr, TaskCategory category) {
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = usr;
        this.category = category;
    }

    public Task(Integer taskId, String description, Boolean status, LocalDateTime createTime, LocalDateTime updateTime, User usr, TaskCategory category) {
        this.taskId = taskId;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.user = usr;
        this.category = category;
    }
}

