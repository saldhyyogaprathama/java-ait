package org.ait.project.template.modules.reqres.model.repository;

import org.ait.project.template.modules.reqres.model.entity.UserReqres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReqresRepository extends JpaRepository<UserReqres, Integer> {
}
