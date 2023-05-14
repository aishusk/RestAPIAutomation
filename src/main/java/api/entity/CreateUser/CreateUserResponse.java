package api.entity.CreateUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import core.templates.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
@ToString
public class CreateUserResponse extends BaseResponse {
    String name;
    String job;
    String id;
    String createdAt;
}
