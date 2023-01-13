package org.ait.project.template.modules.reqres.service.delegate.impl;

import lombok.RequiredArgsConstructor;
import org.ait.project.template.modules.reqres.dto.request.UpdateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.response.ReqresResponseDetail;
import org.ait.project.template.modules.reqres.exception.UserNotFoundException;
import org.ait.project.template.modules.reqres.model.entity.UserReqres;
import org.ait.project.template.modules.reqres.model.entity.UserReqresJob;
import org.ait.project.template.modules.reqres.model.repository.UserReqresJobRepository;
import org.ait.project.template.modules.reqres.model.repository.UserReqresRepository;
import org.ait.project.template.modules.reqres.service.delegate.UserReqresDelegate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserReqresDelegateImpl implements UserReqresDelegate {
    private final UserReqresRepository repository;

    private final UserReqresJobRepository userReqresJobRepository;

    @Override
    public List<UserReqres> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public List<UserReqres> saveAll(List<UserReqres> userReqresList) {
        return repository.saveAll(userReqresList);
    }

    @Override
    public UserReqresJob save(UserReqresJob userReqresJob) {
        return userReqresJobRepository.save(userReqresJob);
    }

    @Override
    public UserReqres getUserById(Integer id) {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<UserReqresJob> getUserReqresJobById(Integer id) {
        return userReqresJobRepository.findById(id);
    }

    @Override
    public UserReqresJob updateUserReqresJob(UserReqresJob userReqresJob, Integer id) {
        UserReqresJob user = userReqresJobRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return userReqresJobRepository.save(userReqresJob);
    }
}
