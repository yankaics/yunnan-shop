package indi.zhangzqit.yunnanshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (req.getSession().getAttribute("user") == null) {
			// ���浱ǰ�ͻ�Ҫȥ�ĵ�ַ
			String goURL = req.getServletPath();
			System.out.println("�ͻ�Ҫȥ�ĵ�ַ:" + goURL);
			String param = req.getQueryString();
			if (param != null) {
				goURL += "?" + param;
			}
			req.getSession().setAttribute("goURL", goURL);
			// �Ƿ�����,��ת����½ҳ��
			req.getSession().setAttribute("error", "�Ƿ��������½!");
			res.sendRedirect(req.getContextPath() + "/ulogin.jsp");
		} else {
			// �Ϸ�����
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
