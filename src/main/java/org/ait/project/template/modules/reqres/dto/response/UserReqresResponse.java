package org.ait.project.template.modules.reqres.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.ait.project.template.modules.reqres.model.entity.UserReqres;

import java.util.List;

@Data
public class UserReqresResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("per_page")
    private Integer per_page;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("total_pages")
    private Integer total_pages;

    @JsonProperty("data")
    private List<ReqresResponseDetail> data;
}
