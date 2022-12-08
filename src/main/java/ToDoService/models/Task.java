package ToDoService.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor

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

    public Task(String description, Boolean status, LocalDateTime now, LocalDateTime now1, User usr, TaskCategory category) {
    }


//    @Override
//    public String toString() {
//        return "Task ID: " + id + " Description: " + description + " Status: " + status;
//    }

}
