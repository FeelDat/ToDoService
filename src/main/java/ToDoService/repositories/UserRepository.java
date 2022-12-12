package ToDoService.repositories;

import ToDoService.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM USERS u WHERE u.user_id = ?1", nativeQuery = true)
    User findUserByUserId(Integer userId);

}
