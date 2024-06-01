package com.alana.javaweb.controller;

import com.alana.javaweb.model.Group;
import com.alana.javaweb.model.Member;
import com.alana.javaweb.service.MemberService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
    private MemberService memberService = new MemberService();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        switch (action) {
            case "/add":
                addMember(request, response);
                break;
            case "/edit":
                editMember(request,response);
                break;
            case "/update":
                updateMember(request,response);
                break;
            case "/delete":
                deleteMember(request, response);
                break;
            case "/list":
                listMembers(request, response);
                break;
            case "/search":
                searchGroup(request,response);
                break;
        }
    }



    private void addMember(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        int group_id = Integer.parseInt(request.getParameter("group_id"));
        Member member = new Member();
        member.setName(name);
        member.setGroupId(group_id);
        if (memberService.addMember(member)) {
            response.sendRedirect(request.getContextPath()+"/member/list");
        } else {
            request.setAttribute("error", "添加成员失败");
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }

    }


    private void editMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询信息
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        Member member = memberService.getMemberById(memberId);
        request.setAttribute("member",member);
        request.getRequestDispatcher("/memberEdit.jsp").forward(request, response);

    }
    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String name = request.getParameter("name");
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        Member member = new Member();

        member.setMemberId(memberId);
        member.setName(name);
        member.setGroupId(groupId);


        if (memberService.updateMember(member)) {
            response.sendRedirect(request.getContextPath()+"/member/list");
        } else {
            request.setAttribute("error", "更新失败");
//            response.sendRedirect(request.getContextPath() + "/error.jsp");

            request.getRequestDispatcher("memberEdit.jsp").forward(request, response);
//            response.sendRedirect(request.getContextPath()+"/memberEdit.jsp");
        }
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int memberId =Integer.parseInt( request.getParameter("memberId"));
        if (memberService.deleteMember(memberId)) {
            response.sendRedirect(request.getContextPath()+"/member/list");
        } else {
            request.setAttribute("error", "删除失败");
//            request.getRequestDispatcher("groupManagement.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
    // 实现遍历成员列表逻辑
    private void listMembers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> memberList = memberService.listMembers();
        // 将一个集合放到请求域当中
        request.setAttribute("memberList",memberList);

        // 转发（不要重定向）
        request.getRequestDispatcher("/memberManagement.jsp").forward(request, response);
    }




    private void searchGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Member member = memberService.getMemberByName(name);
        request.setAttribute("member", member);
        request.getRequestDispatcher("/memberDetails.jsp").forward(request, response); // 转发到显示结果的页面
    }
}
