package me.mahdiyar.digipay.user.service;

import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.user.contract.domain.BaseUser;
import me.mahdiyar.digipay.user.contract.domain.User;
import me.mahdiyar.digipay.user.contract.domain.request.CreateUserRequestDto;
import me.mahdiyar.digipay.user.domain.UserEntity;
import me.mahdiyar.digipay.user.exceptions.UsernameExistsException;
import me.mahdiyar.digipay.user.repository.UserRepository;
import me.mahdiyar.digipay.user.service.exceptions.UserNotFoundException;
import me.mahdiyar.digipay.user.service.mapper.BaseUserMapper;
import me.mahdiyar.digipay.user.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getById(String id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UserMapper.map(userEntity);
    }

    public List<BaseUser> searchUsers(String username) {
        return userRepository.findAllByUsernameContaining(username).stream()
                .map(BaseUserMapper::map).collect(Collectors.toList());
    }

    public List<BaseUser> getAll() {
        return userRepository.findAll().stream().map(BaseUserMapper::map).collect(Collectors.toList());
    }

    public BaseUser create(CreateUserRequestDto requestDto) throws UsernameExistsException {
        if (userRepository.existsByUsername(requestDto.getUsername()))
            throw new UsernameExistsException();
        UserEntity userEntity = new UserEntity(requestDto.getUsername(), requestDto.getHashedPassword());
        userEntity = userRepository.save(userEntity);
        return BaseUserMapper.map(userEntity);
    }
}
