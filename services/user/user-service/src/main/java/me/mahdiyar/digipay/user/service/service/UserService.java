package me.mahdiyar.digipay.user.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.user.service.contract.domain.BaseUser;
import me.mahdiyar.digipay.user.service.contract.domain.User;
import me.mahdiyar.digipay.user.service.contract.domain.request.CreateUserRequestDto;
import me.mahdiyar.digipay.user.service.domain.UserEntity;
import me.mahdiyar.digipay.user.service.exceptions.UsernameExistsException;
import me.mahdiyar.digipay.user.service.repository.UserRepository;
import me.mahdiyar.digipay.user.service.service.exceptions.UserNotFoundException;
import me.mahdiyar.digipay.user.service.service.mapper.BaseUserMapper;
import me.mahdiyar.digipay.user.service.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User getById(String id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UserMapper.map(userEntity);
    }

    public BaseUser searchUsers(String username) throws UserNotFoundException {
        return BaseUserMapper.map(
                userRepository.findFirstByUsernameEquals(username).orElseThrow(UserNotFoundException::new));
    }

    public List<BaseUser> getAll() {
        return userRepository.findAll().stream().map(BaseUserMapper::map).collect(Collectors.toList());
    }

    public BaseUser create(CreateUserRequestDto requestDto) throws UsernameExistsException {
        logger.info("trying to create user with request : {}", requestDto);
        if (userRepository.existsByUsername(requestDto.getUsername()))
            throw new UsernameExistsException();
        UserEntity userEntity =
                new UserEntity(requestDto.getUsername(), requestDto.getHashedPassword(), requestDto.getMobileNo());
        userEntity = userRepository.save(userEntity);
        return BaseUserMapper.map(userEntity);
    }

    public String getUserMobileNo(String id) throws UserNotFoundException {
        return getById(id).getMobileNo();
    }
}
