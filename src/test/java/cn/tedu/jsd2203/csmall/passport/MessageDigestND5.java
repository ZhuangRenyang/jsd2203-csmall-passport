package cn.tedu.jsd2203.csmall.passport;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import javax.swing.plaf.PanelUI;
import java.util.UUID;

@SpringBootTest
public class MessageDigestND5 {
    @Test
    public void test1MD5() {
        String rawPassword = "123123";
        String encodePassword  = DigestUtils.md5DigestAsHex(rawPassword.getBytes());
        System.out.println("明文:"+rawPassword);
        System.out.println("密文:"+encodePassword);//4297f44b13955235245b2497399d7a93
    }

    @Test
    public void test2MD5() {
        String rawPassword = "123123";
        String salt = "fdhufjulkjanlalfafh";
        String encodePassword  = DigestUtils.md5DigestAsHex((rawPassword+salt).getBytes());
        System.out.println("明文:"+rawPassword);
        System.out.println("密文:"+encodePassword);//be8e4f38da7ebe72bd4e184b9bd5fb6f
    }

    @Test
    public void test3MD5() {
        String rawPassword = "123123";
        String salt = UUID.randomUUID().toString();
        String encodePassword  = DigestUtils.md5DigestAsHex((rawPassword+salt).getBytes());
        System.out.println("UUID:"+salt);//e190476f-83c1-44be-973a-5eafb9bc7ce3
        System.out.println("明文:"+rawPassword);
        System.out.println("密文:"+encodePassword);//3d31d29bfaacb00850f69d216c000784
    }

    @Test
    public void testBCrypt(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123123";
        String encodePassword = passwordEncoder.encode(rawPassword);
        System.out.println("密文:"+encodePassword);
        //$2a$10$PMEKcgUDq9.OptSusoNoR.maYWfXXG9y625SB9nhO8dRyZEnrOVWW
        //$2a$10$N0apUrLhMk7TdL3RTsqz7O5nwgundq0zWa3/uh8eD7RL2RXc15xOC
    }

    @Test
    public void testMatches(){
        String rawPassword = "123123";
        String encodePassword = "$2a$10$N0apUrLhMk7TdL3RTsqz7O5nwgundq0zWa3/uh8eD7RL2RXc15xOC";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(rawPassword, encodePassword);
        System.out.println("匹配结果:"+matches);
    }
}
