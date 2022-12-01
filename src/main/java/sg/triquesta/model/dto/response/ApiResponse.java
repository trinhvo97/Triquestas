package sg.triquesta.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ApiResponse <T>{
    private Integer status;
    private T data;
}
