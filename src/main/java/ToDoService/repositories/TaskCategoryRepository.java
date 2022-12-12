package ToDoService.repositories;

import ToDoService.models.TaskCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository extends CrudRepository<TaskCategory, Integer> {


    @Query(value = "SELECT * FROM TASKSCATEGORIES TC WHERE TC.category_id = ?1", nativeQuery = true)
    TaskCategory findTaskCategoryById(Integer categoryID);


}
