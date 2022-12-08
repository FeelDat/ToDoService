package ToDoService.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateInfo {

    private Integer updatedTaskId;
    private Boolean updatedStatusFrom;
    private Boolean updatedStatusTo;

}
