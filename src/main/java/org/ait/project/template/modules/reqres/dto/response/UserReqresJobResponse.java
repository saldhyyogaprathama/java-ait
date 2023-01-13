package org.ait.project.template.modules.reqres.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.ait.project.template.modules.reqres.model.entity.UserReqres;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class UserReqresJobResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("job")
    private String job;

    @JsonProperty("createdAt")
    private ZonedDateTime createdAt;
}
