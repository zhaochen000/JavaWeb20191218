package com.woniu.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter({"/*"})
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// ����ת�ͣ�ת����Http���͵��������Ӧ
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hresp = (HttpServletResponse) resp;

		// �õ����ض��Ự�����Ķ���
		HttpSession session = hreq.getSession();
		// ����õ�·��(��������Ŀ����)
		String path = hreq.getServletPath();//

		if (session.getAttribute("uname") != null || path.equals("/login.jsp") || path.equals("/login.do")
				|| path.indexOf("/css") != -1 || path.indexOf("/js") != -1 || path.indexOf("/img") != -1
				|| path.indexOf("/fonts") != -1) {

			// ����
			chain.doFilter(req, resp);
			return;
		}
		// ��¼������
		hresp.sendRedirect("login.jsp");

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
