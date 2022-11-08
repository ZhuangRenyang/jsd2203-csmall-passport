package cn.tedu.jsd2203.csmall.passport.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    // 最终，过滤器可以选择“阻止”或“放行”
    // 如果选择“阻止”，则后续的所有组件都不会被执行
    // 如果选择“放行”，会执行“过滤器链”中剩余的部分，甚至继续向后执行到控制器等组件
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 此方法是任何请求都会执行的方法
        log.debug("执行到JwtAuthorizationFilter过滤器");

        // 清除Security的上下文
        // 如果不清除，只要此前存入过信息，即使后续不携带JWT，上下文中的登录信息依然存在
        SecurityContextHolder.clearContext();

        // 从请求头中获取JWT
        String jwt =  request.getHeader("Authorization");
        log.debug("从请求头中获取的JWT数据:{}",jwt);

        //先判断是否获取到了有效的jwt数据，无jwt数据-放行
        if(!StringUtils.hasText(jwt)){//不为null && 不为Empty() && 包含文本containsText();
            log.debug("请求头中的jwt数据是无效的,直接放行");
            filterChain.doFilter(request,response);
            return;
        }
        //解析
        Claims claims = Jwts.parser().setSigningKey("udiasjia").parseClaimsJws(jwt).getBody();
        Object username = claims.get("username");
        log.debug("从JWT中解析得到用户名:{}",username);

        //准备临时权限
        GrantedAuthority authority = new SimpleGrantedAuthority("1");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);


        //解析成功后，将数据存入到Spring Security上下文中
        //当登录成功后，密码无需传入，设置为null
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(username,null,authorities);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);



        //"放行"
        filterChain.doFilter(request,response);
    }
}
