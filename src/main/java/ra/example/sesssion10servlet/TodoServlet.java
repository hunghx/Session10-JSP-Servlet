package ra.example.sesssion10servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.List;

@WebServlet(name = "todoServlet", value = "/todo")

public class TodoServlet extends HttpServlet {
    private static List<Todo> todoList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        // theem gia tri mac dinh
        todoList.add(new Todo(1, "lam viec nha", "6h - 7h phai lam", false));
        todoList.add(new Todo(2, "lam BT ve nha", "8h - 9h phai lam", false));
        todoList.add(new Todo(3, "rua bat", "11h - 12h phai lam", false));
    }
    // chuc CRUD

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // thêm mới và update thông tin
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "ADD":
                    // xuwr li thong tin
                    int idNew = Integer.parseInt(req.getParameter("id"));
                    String titleNew = req.getParameter("title");
                    String contentNew = req.getParameter("content");
                    // tao todo
                    Todo todo = new Todo(idNew, titleNew, contentNew, false);
                    todoList.add(todo);
                    resp.sendRedirect("/todo?action=GETALL");
                    break;
                case "UPDATE":
                    int idUp = Integer.parseInt(req.getParameter("id"));
                    String titleUp = req.getParameter("title");
                    String contentUp = req.getParameter("content");
                    boolean statusUp = Boolean.parseBoolean(req.getParameter("status"));
                    Todo todoUp = todoList.stream().filter(t -> t.getId() == idUp).findFirst().orElseThrow(() -> new RuntimeException("ko tim thay id"));
                    todoList.set(todoList.indexOf(todoUp),new Todo(idUp,titleUp,contentUp,statusUp));
                    resp.sendRedirect("/todo?action=GETALL");
                    break;

            }
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // chức năng hiên thị , tim kiếm theo id , chức năng xoa
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "GETALL":
                    // hiển thị
                    displayTodoList(resp);
                    break;
                case "DELETE":
                    int idDel = Integer.parseInt(req.getParameter("id"));
                    Todo todo = todoList.stream().filter(t -> t.getId() == idDel).findFirst().orElseThrow(() -> new RuntimeException("ko tim thay id"));
                    todoList.remove(todo);
//                    displayTodoList(resp);
                    resp.sendRedirect("/todo?action=GETALL"); // điều huong theo duowng dan
                    break;
                case "ADD":
                    // hieenr thi ra form
                    displayAddForm(resp);
                    break;
                case "EDIT":
                    int idEDIT = Integer.parseInt(req.getParameter("id"));
                    Todo todoEdit = todoList.stream().filter(t -> t.getId() == idEDIT).findFirst().orElseThrow(() -> new RuntimeException("ko tim thay id"));
                    displayEditForm(resp, todoEdit);
            }
        }

    }

    private static void displayEditForm(HttpServletResponse resp, Todo todo) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        // Hello

        PrintWriter out = resp.getWriter();
        out.println("<form action=\"/todo\" method=\"post\">\n" +
                "    <input type=\"text\" readonly placeholder=\"id\" value=\""+todo.getId()+"\" name=\"id\"><br>\n" +
                "    <input type=\"text\" placeholder=\"title\" value=\""+todo.getTitle()+"\" name=\"title\"><br>\n" +
                "    <textarea name=\"content\" placeholder=\"content ...\">"+todo.getContent()+"</textarea><br>\n" +
                "    <select name=\"status\">\n" +
                "    <option value=\"true\""+(todo.isStatus()?"selected":"")+">Đã hoàn thành</option>\n" +
                "    <option value=\"false\""+(todo.isStatus()?"":"selected")+">Chưa hoàn thành</option>\n" +
                "</select>" +
                "    <br>\n" +
                "    <input type=\"submit\" name=\"action\" value=\"UPDATE\"/>\n" +
                "</form>");
    }

    private static void displayAddForm(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        // Hello

        PrintWriter out = resp.getWriter();
        out.println("<form action=\"/todo\" method=\"post\">\n" +
                "    <input type=\"text\" placeholder=\"id\" name=\"id\"><br>\n" +
                "    <input type=\"text\" placeholder=\"title\" name=\"title\"><br>\n" +
                "    <textarea name=\"content\" placeholder=\"content ...\"></textarea>\n" +
                "    <br>\n" +
                "    <input type=\"submit\" name=\"action\" value=\"ADD\"/>\n" +
                "</form>");
    }

    private static void displayTodoList(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        // Hello
        StringBuilder tbody = new StringBuilder();
        for (Todo t : todoList) {
            tbody.append("<tr>\n" +
                    "        <td>" + t.getId() + "</td>\n" +
                    "        <td>" + t.getTitle() + "</td>\n" +
                    "        <td>" + t.getContent() + "</td>\n" +
                    "        <td>" + (t.isStatus() ? "Hoàn thành" : "Chưa hoàn thành") + "</td>\n" +
                    "        <td><a href=\"?action=EDIT&id=" + t.getId() + "\">Edit</a></td>\n" +
                    "        <td><a href=\"?action=DELETE&id=" + t.getId() + "\" onclick=\"" +
                    "return confirm('ban có chắc chắn muôn xóa không')\">Delete</a></td>\n" +
                    "    </tr>");
        }


        PrintWriter out = resp.getWriter();
        out.println("<h1>Danh sach công việc</h1>\n" + "<a  href=\"/todo?action=ADD\">ADD NEW TODO</a>" +
                "<table border=\"10\" cellpadding=\"10\" cellspacing=\"10\">\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th>ID</th>\n" +
                "        <th>Title</th>\n" +
                "        <th>Content</th>\n" +
                "        <th>Status</th>\n" +
                "        <th colspan=\"2\">Action</th></tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n" + tbody +
                "    </tbody>\n" +
                "</table>");
    }
}
