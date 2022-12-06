package ToDoService.repositories;

import ToDoService.models.Task;
import ToDoService.models.TaskCategory;
import ToDoService.models.User;
import ToDoService.models.dto.DTO_Task;
import ToDoService.models.dto.Filter;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {


//    @Query(value = "select t.task_id, t.description, t.status, t.creation_time, t.update_time, t.user_id, t.category_id from tasks t where t.creation_time is null or t.creation_time between :startDateBegin and :startDateEnd", nativeQuery = true)
//    List<Task> findTasksWithFilter(@Param("startDateBegin") LocalDateTime startDateBegin
//            , @Param("startDateEnd") LocalDateTime startDateEnd);

//    @Query("SELECT tasks FROM tasks t WHERE (t.category = :category OR t.category is null) AND (t.creation_time BETWEEN :startDateBegin AND :startDateEnd) AND (t.update_time BETWEEN :updateDateBegin AND :updateDateEnd)")
//    List<Task> findAllByCategoryAndCreateTimeBetweenAndUpdateTimeBetween(@Param("category") TaskCategory category,
//                                                                         @Param("startDateBegin") LocalDateTime startDateBegin,
//                                                                         @Param("startDateEnd") LocalDateTime startDateEnd,
//                                                                         @Param("updateDateBegin") LocalDateTime updateDateBegin,
//                                                                         @Param("updateDateEnd") LocalDateTime updateDateEnd);


    List<Task> findAllByCreateTimeBetween(@Param("startDateBegin") LocalDateTime startDateBegin
            , @Param("startDateEnd") LocalDateTime startDateEnd);

    List<Task> findAllByUpdateTimeBetween(@Param("updateDateBegin") LocalDateTime updateDateBegin
            , @Param("updateDateEnd") LocalDateTime updateDateEnd);

    List<Task> findAllByCategory(@Param("category") TaskCategory category);

    List<Task> findAllByCategoryAndCreateTimeBetweenAndUpdateTimeBetween(@Param("category") TaskCategory category
            , @Param("startDateBegin") LocalDateTime startDateBegin, @Param("startDateEnd") LocalDateTime startDateEnd
            , @Param("updateDateBegin") LocalDateTime updateDateBegin, @Param("updateDateEnd") LocalDateTime updateDateEnd);


}
