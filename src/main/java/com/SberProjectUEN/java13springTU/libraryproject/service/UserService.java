package com.SberProjectUEN.java13springTU.libraryproject.service;


import com.SberProjectUEN.java13springTU.libraryproject.constants.MailConstants;
import com.SberProjectUEN.java13springTU.libraryproject.dto.RoleDTO;
import com.SberProjectUEN.java13springTU.libraryproject.dto.UserDTO;
import com.SberProjectUEN.java13springTU.libraryproject.exception.MyDeleteException;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.UserMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.User;
import com.SberProjectUEN.java13springTU.libraryproject.repository.UserRepository;
import com.SberProjectUEN.java13springTU.libraryproject.utils.MailUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.SberProjectUEN.java13springTU.libraryproject.constants.UserRolesConstants.ADMIN;

@Service
public class UserService
        extends GenericService<User, UserDTO> {
    private final JavaMailSender javaMailSender;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    protected UserService(UserRepository userRepository,
                          UserMapper userMapper,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          JavaMailSender javaMailSender) {
        super(userRepository, userMapper);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.javaMailSender = javaMailSender;
    }

    public UserDTO getUserByLogin(final String login) {
        return mapper.toDTO(((UserRepository) repository).findUserByLogin(login));
    }

    public UserDTO getUserByEmail(final String email) {
        return mapper.toDTO(((UserRepository) repository).findUserByEmail(email));
    }

    public Boolean checkPassword(String password, UserDetails userDetails) {
        return bCryptPasswordEncoder.matches(password, userDetails.getPassword());
    }

    @Override
    public UserDTO create(UserDTO object) {
        RoleDTO roleDTO = new RoleDTO();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (ADMIN.equalsIgnoreCase(userName)) {
            roleDTO.setId(2L);//библиотекарь
        }
        else {
            roleDTO.setId(1L);//пользователь
        }
        object.setRole(roleDTO);
        object.setCreatedBy("REGISTRATION FORM");
        object.setCreatedWhen(LocalDateTime.now());
        object.setPassword(bCryptPasswordEncoder.encode(object.getPassword()));
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }

    public void sendChangePasswordEmail(final UserDTO userDTO) {
        UUID uuid = UUID.randomUUID();
        userDTO.setChangePasswordToken(uuid.toString());
        update(userDTO);
        SimpleMailMessage mailMessage = MailUtils.createEmailMessage(userDTO.getEmail(),
                MailConstants.MAIL_SUBJECT_FOR_REMEMBER_PASSWORD,
                MailConstants.MAIL_MESSAGE_FOR_REMEMBER_PASSWORD + uuid);
        javaMailSender.send(mailMessage);
    }

    public void changePassword(final String uuid,
                               final String password) {
        UserDTO user = mapper.toDTO(((UserRepository) repository).findUserByChangePasswordToken(uuid));
        user.setChangePasswordToken(null);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        update(user);
    }

    public Page<UserDTO> findUsers(UserDTO userDTO,
                                   Pageable pageable) {
        Page<User> users = ((UserRepository) repository).searchUsers(userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getLogin(),
                pageable);
        List<UserDTO> result = mapper.toDTOs(users.getContent());
        return new PageImpl<>(result, pageable, users.getTotalElements());
    }

    public List<String> getUserEmailsWithDelayedRentDate() {
        return ((UserRepository) repository).getDelayedEmails();
    }
    @Override
    public void delete(Long id) throws MyDeleteException {
        User user = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Пользователя с заданным ID=" + id + " не существует"));
        markAsDeleted(user);
        repository.save(user);
    }

    public void restore(Long objectId) {
        User user = repository.findById(objectId).orElseThrow(
                () -> new NotFoundException("Пользователя с заданным ID=" + objectId + " не существует"));
        unMarkAsDeleted(user);
        repository.save(user);
    }
}



