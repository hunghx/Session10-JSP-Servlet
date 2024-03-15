package ra.example.sesssion10servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "todoJspServlet", value = "/todo-jsp")
public class TodoJspServlet extends HttpServlet {
    private static List<Todo> todoList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        // theem gia tri mac dinh
        todoList.add(new Todo(1, "lam viec nha", "6h - 7h phai lam", false));
        todoList.add(new Todo(2, "lam BT ve nha", "8h - 9h phai lam", false));
        todoList.add(new Todo(3, "rua bat", "11h - 12h phai lam", false));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "GETALL":
                    req.setAttribute("todoList",todoList);
                    req.getRequestDispatcher("/list-todo.jsp").forward(req,resp);
                    break;
                case "EDIT":
                    int idEDIT = Integer.parseInt(req.getParameter("id"));
                    Todo todoEdit = todoList.stream().filter(t -> t.getId() == idEDIT).findFirst().orElseThrow(() -> new RuntimeException("ko tim thay id"));
                    req.setAttribute("todoEdit",todoEdit);
                    req.getRequestDispatcher("/edit-todo.jsp").forward(req,resp);
                    break;
                case "DELETE":
                    int idDel = Integer.parseInt(req.getParameter("id"));
                    Todo todo = todoList.stream().filter(t -> t.getId() == idDel).findFirst().orElseThrow(() -> new RuntimeException("ko tim thay id"));
                    todoList.remove(todo);
//                    displayTodoList(resp);
                    resp.sendRedirect("/todo-jsp?action=GETALL");
                    break;
                case "ADD":
                    req.getRequestDispatcher("/add-todo.jsp").forward(req,resp);
                    break;
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
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
                    resp.sendRedirect("/todo-jsp?action=GETALL");
                    break;
                case "UPDATE":
                    int idUp = Integer.parseInt(req.getParameter("id"));
                    String titleUp = req.getParameter("title");
                    String contentUp = req.getParameter("content");
                    boolean statusUp = Boolean.parseBoolean(req.getParameter("status"));
                    Todo todoUp = todoList.stream().filter(t -> t.getId() == idUp).findFirst().orElseThrow(() -> new RuntimeException("ko tim thay id"));
                    todoList.set(todoList.indexOf(todoUp), new Todo(idUp, titleUp, contentUp, statusUp));
                    resp.sendRedirect("/todo-jsp?action=GETALL");
                    break;
            }
        }
    }
}
