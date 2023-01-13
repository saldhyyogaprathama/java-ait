package org.ait.project.template.modules.reqres.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserReqresJob {

    @JsonProperty("name")
    private String name;

    @JsonProperty("job")
    private String job;
}
