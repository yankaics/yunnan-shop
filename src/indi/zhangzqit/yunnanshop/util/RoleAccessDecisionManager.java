package indi.zhangzqit.yunnanshop.util;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component("accessDecisionManager")
public class RoleAccessDecisionManager implements AccessDecisionManager {

	@Override
	// �������ִ�����û���׳��쳣,��˵�����Է���, �����׳��쳣 AccessDeniedException
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		System.out.println("---------------decide------------------");
		// �����½�ɹ�,����Ϣ��洢��Authorities��
		Collection<? extends GrantedAuthority> myRoles = authentication
				.getAuthorities();
		// ���ǰ��� getAttributes() ���طǿ�,�򷵻ص�������Ϊ�βδ���, �������Ϊnull �򲻻����decide() ֱ�ӷ���
		System.out.println("myRole:" + myRoles);
		System.out.println("sysRole:" + configAttributes);
		for (GrantedAuthority myRole : myRoles) {
			for (ConfigAttribute urlRole : configAttributes) {
				if (myRole.getAuthority().equals(urlRole.getAttribute())) {
					// ˵����URL��ַ����Ȩ��,���Է���
					return;
				}
			}
		}
		System.out.println("***********Ȩ����֤ʧ��************");
		throw new AccessDeniedException("Ȩ��Խ�磡");
	}

	@Override
	// �жϲ����Ƿ�Ϊ�����Ĳ���
	public boolean supports(ConfigAttribute attribute) {
		System.out
				.println("public boolean supports(ConfigAttribute attribute)");
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("public boolean supports(Class<?> clazz)");
		return true;
	}

}
