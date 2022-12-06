package ToDoService.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "tasks")
@Data
public class Task {

    public Task() {

    }
    public Task(String description, Boolean status, LocalDateTime createTime, User user, TaskCategory taskCategory) {
        this.description = description;
        this.status = status;
        this.createTime = createTime;
        this.user = user;
        this.category = taskCategory;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "creation_time")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updateTime;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = true)
    private TaskCategory category;




//    @Override
//    public String toString() {
//        return "Task ID: " + id + " Description: " + description + " Status: " + status;
//    }

}
