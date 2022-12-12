package ToDoService.repositories;

import ToDoService.models.Task;
import ToDoService.models.TaskWithId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    //Fix ordering by status (boolean data type
    @Query(value = "select t.task_id as taskId, t.description as description, t.status as status, "
            + "t.creation_time as createTime, t.update_time as updateTime, t.user_id as userId,"
            + " t.category_id as categoryId from tasks t "
            + "where t.creation_time is null or t.creation_time between :startDateBegin and :startDateEnd "
            + "and t.update_time is null or t.update_time between :updateDateBegin and :updateDateEnd "
            + "and t.category_id IS NULL or t.category_id = :category order by :sortBy ", nativeQuery = true)
    List<TaskWithId> findAllByCategoryAndCreateTimeBetweenAndUpdateTimeBetween(@Param("startDateBegin") LocalDateTime startDateBegin,
                                                                               @Param("startDateEnd") LocalDateTime startDateEnd,
                                                                               @Param("updateDateBegin") LocalDateTime updateDateBegin,
                                                                               @Param("updateDateEnd") LocalDateTime updateDateEnd,
                                                                               @Param("category") Integer category,
                                                                               @Param("sortBy") String sortBy);

}
