package org.ait.project.template.modules.users.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("message")
    private String message;
}
