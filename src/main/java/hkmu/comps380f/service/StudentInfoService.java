package hkmu.comps380f.service;

//import hkmu.comps380f.dao.TicketUserRepository;
import hkmu.comps380f.dao.StudentInfoRepository;
import hkmu.comps380f.model.StudentInfo;
//import hkmu.comps380f.model.TicketUser;
//import hkmu.comps380f.model.UserRole;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentInfoService implements UserDetailsService {

    @Resource
    StudentInfoRepository studentInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        StudentInfo studentInfo = studentInfoRepo.findById(username).orElse(null);
        if (studentInfo == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        //for (StudentInfo role : studentInfo.getUserrole()) {
            authorities.add(new SimpleGrantedAuthority(studentInfo.getUserrole()));
        //}
        return new User(studentInfo.getUsername(), studentInfo.getPassword(), authorities);
    }
}