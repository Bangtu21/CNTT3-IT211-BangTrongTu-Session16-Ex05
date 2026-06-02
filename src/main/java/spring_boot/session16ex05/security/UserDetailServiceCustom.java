package spring_boot.session16ex05.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring_boot.session16ex05.model.entity.User;
import spring_boot.session16ex05.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailServiceCustom implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetailServiceCustom(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản: "+username));

        List<SimpleGrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));

        return new UserPrincipal(user, grantedAuthorities);
    }
}
