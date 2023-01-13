package org.ait.project.template.modules.reqres.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReqresJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "job")
    private String job;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @CreationTimestamp
    private ZonedDateTime updatedAt;

}
