package indi.zhangzqit.yunnanshop.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SecondInterceptor implements Interceptor {

	@Override
	public void destroy() {
		System.out.println("************Second destroy()************");
	}

	@Override
	public void init() {
		System.out.println("***********Second init()**********");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("************Second in()***********");
		String result = invocation.invoke();
		System.out.println("************Second out()*********");
		return result;
	}
}
