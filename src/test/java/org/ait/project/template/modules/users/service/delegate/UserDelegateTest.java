package org.ait.project.template.modules.users.service.delegate;

import org.ait.project.template.modules.users.dto.request.CreateUserDto;
import org.ait.project.template.modules.users.dto.request.UpdateUserDto;
import org.ait.project.template.modules.users.model.entity.Users;
import org.ait.project.template.modules.users.model.repository.UsersRepository;
import org.ait.project.template.modules.users.service.delegate.impl.UserDelegateImpl;
import org.ait.project.template.modules.users.transform.UserTransform;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserDelegateTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserDelegateImpl userDelegate;

    private EasyRandom easyRandom = new EasyRandom();

    private UserTransform userTransform;

    @Test
    public void getAllUsers() {
        Integer numberOfObjects = 10;
        List<Users> expectedList = easyRandom.objects(Users.class, numberOfObjects).collect(Collectors.toList());

        when(usersRepository.findAll()).thenReturn(expectedList);

        List<Users> actualList = userDelegate.getAllUsers();

        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            Users expected = expectedList.get(i);
            Users actual = actualList.get(i);

            assertEquals(expected.getPassword(), actual.getPassword());
            assertEquals(expected.getId(), actual.getId());
            assertEquals(expected.getName(), actual.getName());
            assertEquals(expected.getEmail(), actual.getEmail());
            assertEquals(expected.getPhonenumber(), actual.getPhonenumber());
        }
    }

    @Test
    public void getUserById() {
        Users expectedObject = easyRandom.nextObject(Users.class);

        when(usersRepository.findById(expectedObject.getId())).thenReturn(Optional.of(expectedObject));

        Users actualObject = userDelegate.getUserById(expectedObject.getId());

        assertEquals(expectedObject.getPassword(), actualObject.getPassword());
        assertEquals(expectedObject.getId(), actualObject.getId());
        assertEquals(expectedObject.getName(), actualObject.getName());
        assertEquals(expectedObject.getEmail(), actualObject.getEmail());
        assertEquals(expectedObject.getPhonenumber(), actualObject.getPhonenumber());
    }

    @Test
    public void createUser() {


        Users toSave = easyRandom.nextObject(Users.class);
        Users expectedAfterSave = toSave;

        Integer savedId = easyRandom.nextInt(10);
        expectedAfterSave.setId(savedId);


        when(usersRepository.save(toSave)).thenReturn(expectedAfterSave);

        Users actualAfterSave = userDelegate.createUser(toSave);

        assertEquals(expectedAfterSave.getId(), actualAfterSave.getId());
        assertEquals(expectedAfterSave.getEmail(), actualAfterSave.getEmail());
        assertEquals(expectedAfterSave.getPhonenumber(), actualAfterSave.getPhonenumber());
        assertEquals(expectedAfterSave.getName(), actualAfterSave.getName());
        assertEquals(expectedAfterSave.getPassword(), actualAfterSave.getPassword());
    }

    @Test
    public void getUserByEmail() {
        Users expectedObject = easyRandom.nextObject(Users.class);

        when(usersRepository.findByEmail(expectedObject.getEmail())).thenReturn(Optional.of(expectedObject));

        Users actualObject = userDelegate.getUserByEmail(expectedObject.getEmail());

        assertEquals(expectedObject.getPassword(), actualObject.getPassword());
        assertEquals(expectedObject.getId(), actualObject.getId());
        assertEquals(expectedObject.getName(), actualObject.getName());
        assertEquals(expectedObject.getEmail(), actualObject.getEmail());
    }

    @Test
    public void getUserByName() {
        Users expectedObject = easyRandom.nextObject(Users.class);

        when(usersRepository.findByName(expectedObject.getName())).thenReturn(Optional.of(expectedObject));

        Optional<Users> actualObject = userDelegate.getUserByName(expectedObject.getName());

        assertEquals(expectedObject.getPassword(), actualObject.get().getPassword());
        assertEquals(expectedObject.getId(), actualObject.get().getId());
        assertEquals(expectedObject.getName(), actualObject.get().getName());
        assertEquals(expectedObject.getEmail(), actualObject.get().getEmail());
    }

    @Test
    public void updateUser() {


        UpdateUserDto toUpdate = easyRandom.nextObject(UpdateUserDto.class);
        Users expectedAfterSave = easyRandom.nextObject(Users.class);

        Integer updateId = easyRandom.nextInt(10);
        expectedAfterSave.setId(updateId);


        when(usersRepository.save(expectedAfterSave)).thenReturn(expectedAfterSave);
        when(usersRepository.findById(updateId)).thenReturn(Optional.of(expectedAfterSave));

        Users actualAfterSave = userDelegate.updateUser(toUpdate, updateId);

        assertEquals(expectedAfterSave.getId(), actualAfterSave.getId());
        assertEquals(expectedAfterSave.getEmail(), actualAfterSave.getEmail());
        assertEquals(expectedAfterSave.getPhonenumber(), actualAfterSave.getPhonenumber());
        assertEquals(expectedAfterSave.getName(), actualAfterSave.getName());
        assertEquals(expectedAfterSave.getPassword(), actualAfterSave.getPassword());
    }

//    @Test
//    public void deleteUser() {
//        Users expectedObject = easyRandom.nextObject(Users.class);
//        when(usersRepository.findById(expectedObject.getId())).thenReturn(Optional.of(expectedObject));
//
//        usersRepository.delete(expectedObject);
//
//        Users users1 = null;
//
//        Optional<Users> optionalUsers = usersRepository.findById(expectedObject.getId());
//
//        if(optionalUsers.isPresent()){
//            users1 = optionalUsers.get();
//        }
//
//        Assertions.assertThat(users1).isNull();
//    }

}
