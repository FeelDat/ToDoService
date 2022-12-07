package ToDoService.repositories;

import ToDoService.models.Task;
import ToDoService.models.TaskCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    /*Make dto to retrive dto_Task with user and category fields replaced with their id,
    and retrieve both objects by id using corresponding repositories*/

    @Query(value = "select task_id, description, status, creation_time, update_time, t.user_id, t.category_id, category_name, user_login from tasks t "
            + "join taskscategories tc on tc.category_id = t.category_id "
            + "join users u on t.user_id = u.user_id "
            + "where t.creation_time is null or t.creation_time between :startDateBegin and :startDateEnd "
            + "and t.update_time is null or t.update_time between :updateDateBegin and :updateDateEnd "
            + "and t.category_id IS NULL or t.category_id = :category order by :sortBy ", nativeQuery = true)
    List<Task> findAllByCategoryAndCreateTimeBetweenAndUpdateTimeBetween(@Param("startDateBegin") LocalDateTime startDateBegin,
                                                                         @Param("startDateEnd") LocalDateTime startDateEnd,
                                                                         @Param("updateDateBegin") LocalDateTime updateDateBegin,
                                                                         @Param("updateDateEnd") LocalDateTime updateDateEnd,
                                                                         @Param("category") Integer category,
                                                                         @Param("sortBy") String sortBy);


    List<Task> findAllByCreateTimeBetween(@Param("startDateBegin") LocalDateTime startDateBegin, @Param("startDateEnd") LocalDateTime startDateEnd);

    List<Task> findAllByUpdateTimeBetween(@Param("updateDateBegin") LocalDateTime updateDateBegin, @Param("updateDateEnd") LocalDateTime updateDateEnd);

    List<Task> findAllByCategory(@Param("category") TaskCategory category);

//    List<Task> findAllByCategoryAndCreateTimeBetweenAndUpdateTimeBetween(@Param("category") TaskCategory category
//            , @Param("startDateBegin") LocalDateTime startDateBegin, @Param("startDateEnd") LocalDateTime startDateEnd
//            , @Param("updateDateBegin") LocalDateTime updateDateBegin, @Param("updateDateEnd") LocalDateTime updateDateEnd, Sort sort);


}
