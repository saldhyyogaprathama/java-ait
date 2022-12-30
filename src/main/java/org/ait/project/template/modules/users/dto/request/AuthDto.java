package org.ait.project.template.modules.users.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

}
