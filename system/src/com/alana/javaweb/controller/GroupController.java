import com.alana.javaweb.model.Group;
import com.alana.javaweb.service.GroupService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/group/*")
public class GroupController extends HttpServlet {
    private GroupService groupService = new GroupService();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        switch (action) {
            case "/add":
                addGroup(request, response);
                break;
            case "/edit":
                editGroup(request, response);
                break;
            case "/update":
                updateGroup(request,response);
                break;
            case "/delete":
                deleteGroup(request, response);
                break;
            case "/list":
                listGroups(request, response);
                break;
            case "/search":
                searchGroup(request,response);
                break;
        }
    }

    /**
     * 实现添加小组逻辑
     * @param request
     * @param response
     */
    private void addGroup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String groupName = request.getParameter("groupName");
        String description = request.getParameter("description");

        Group group = new Group();
        group.setGroupName(groupName);
        group.setDescription(description);

        if (groupService.addGroup(group)) {
            response.sendRedirect(request.getContextPath()+"/group/list");
        } else {
            request.setAttribute("error", "添加小组失败");
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }

    }
    // 实现修改小组逻辑
    private void editGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询小组信息
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        Group group = groupService.getGroupById(groupId);
        request.setAttribute("group",group);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);

    }
    private void updateGroup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String groupName = request.getParameter("groupName");
        String description = request.getParameter("description");

        Group group = new Group();
        group.setGroupId(groupId);
        group.setGroupName(groupName);
        group.setDescription(description);

        if (groupService.updateGroup(group)) {
            response.sendRedirect(request.getContextPath()+"/group/list");
        } else {
            request.setAttribute("error", "更新小组失败");
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        }
    }

    // 实现删除小组逻辑
    private void deleteGroup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        if (groupService.deleteGroup(groupId)) {
            response.sendRedirect(request.getContextPath()+"/group/list");
        } else {
            request.setAttribute("error", "删除小组失败");
            request.getRequestDispatcher("groupManagement.jsp").forward(request, response);
        }
    }

    // 实现遍历小组列表逻辑
    private void listGroups(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> groupList = groupService.listGroups();

        // 将一个集合放到请求域当中
        request.setAttribute("groupList", groupList);

        // 转发（不要重定向）
        request.getRequestDispatcher("/groupManagement.jsp").forward(request, response);
    }


    private void searchGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupName = request.getParameter("groupName");
        Group group = groupService.getGroupByName(groupName);
        request.setAttribute("group", group);
        request.getRequestDispatcher("/groupDetails.jsp").forward(request, response); // 转发到显示结果的页面
    }
}
