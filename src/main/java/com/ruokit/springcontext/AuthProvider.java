package com.ruokit.springcontext;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Created by ds on 2017-10-31.
 */

@Component
public class AuthProvider implements AuthenticationProvider {

  // @Autowired
  // AuthorizationService authorizationService;

  // �α��� ��ư�� ���� ���
  // ���� 1
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String id = authentication.getName();
    String password = authentication.getCredentials().toString();
    return authenticate(id, password);
  }

  // ���� 2
  public Authentication authenticate(String id, String password) throws AuthenticationException {
    // User user = authorizationService.login(id, password);
    // if (user == null) return null;
    List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
    String role = "";
    /**
     * 0 : �Ϲ� ����� 1 : �л� 2 : ���� 3 : ���� 4 : ������
     */
    switch (0) {
      case 0:
        role = "ROLE_NO";
        break;
      case 1:
        role = "ROLE_USER";
        break;
      case 2:
      case 3:
      case 4:
        role = "ROLE_ADMIN";
        break;
    }
    grantedAuthorityList.add(new SimpleGrantedAuthority(role));
    return null;// new MyAuthenticaion(id, password, grantedAuthorityList, user);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
