package com.example.demo.service;

import com.example.demo.domain.UserInfo;
import com.example.demo.domain.UserInfoDTO;
import com.example.demo.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Long save(UserInfoDTO infoDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDTO.setPassword(encoder.encode(infoDTO.getPassword()));

        System.out.println(infoDTO.getId());
        System.out.println(infoDTO.getPassword());
        System.out.println(infoDTO.getAuth());

        return userRepository.save(UserInfo.builder()
                .id(infoDTO.getId())
                .auth(infoDTO.getAuth())
                .password(infoDTO.getPassword()).build()).getCode();
    }

    @Override
    public UserInfo loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException((id)));
    }
}
