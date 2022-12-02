package ToDoService.repositories;

import ToDoService.models.Task;
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
import java.util.List;


@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {


    @Query(value = "select t.task_id, t.description, t.status, t.creation_time, t.update_time, t.user_id, t.category_id from tasks t where t.creation_time is null or t.creation_time between :startDateBegin and :startDateEnd", nativeQuery = true)
    List<DTO_Task> findTasksWithFilter(@Param("startDateBegin") Timestamp startDateBegin
            , @Param("startDateEnd") Timestamp startDateEnd);

    List<Task> findAllByCreation_timeBetween(Timestamp start, Timestamp end);

}
