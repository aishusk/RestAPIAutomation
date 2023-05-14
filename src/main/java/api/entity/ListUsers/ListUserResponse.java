package api.entity.ListUsers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.templates.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties
public class ListUserResponse extends BaseResponse {
    int page;
    @JsonProperty("per_page")
    int perPage;
    int total;
    @JsonProperty("total_pages")
    int totalPages;
    List<Data> data;
    @JsonProperty("support")
    Support support;


    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties
   public static class Data{
       int id;
       String email;
       @JsonProperty("first_name")
       String firstName;
       @JsonProperty("last_name")
       String lastName;
       String avatar;
   }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties
   public static class Support{
        String url;
        String text;
   }
}
