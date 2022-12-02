package ToDoService.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "taskscategories")
@Data
public class TaskCategory {

    public TaskCategory() {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;


}


