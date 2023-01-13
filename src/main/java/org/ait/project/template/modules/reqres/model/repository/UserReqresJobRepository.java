package org.ait.project.template.modules.reqres.model.repository;

import org.ait.project.template.modules.reqres.model.entity.UserReqres;
import org.ait.project.template.modules.reqres.model.entity.UserReqresJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReqresJobRepository extends JpaRepository<UserReqresJob, Integer> {
}
