package com.pn.trainning.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pn.trainning.dto.StudentDTO;
import com.pn.trainning.service.StudentService;
import com.pn.trainning.service.StudentServiceImpl;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentService studentService;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("dbURL");
		String jdbcUsername = getServletContext().getInitParameter("dbUsername");
		String jdbcPassword = getServletContext().getInitParameter("dbPassword");
		
		this.studentService = new StudentServiceImpl(jdbcURL, jdbcUsername, jdbcPassword);

	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action")!= null ? request.getParameter("action") : "none";
		try {
			switch(action){
			case "new":
				RequestDispatcher dispatcher = request.getRequestDispatcher("StudentForm.jsp");
				dispatcher.forward(request, response);
				break;
			case "insert":
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String gender = request.getParameter("gender");
				if ("male".equals(gender)) {
				    // male selected
				} else if ("female".equals(gender)) {
				    // female selected
				}else if ("other".equals(gender)) {
				    // other selected
				}
				String birthday = request.getParameter("birthday");
				String address =request.getParameter("address");
				
				StudentDTO newStudent = new StudentDTO(firstname, lastname, gender, birthday, address);
				this.studentService.addStudent(newStudent);
				response.sendRedirect("student");
				break;
			case "delete":
				deleteStudent(request, response);
				break;
			case "edit":
				this.showEditForm(request, response);
				break;
			case "update":
				this.updateStudent(request, response);
				break;
			default:
				this.getListStudent(request, response);
				break;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void getListStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<StudentDTO> lst = this.studentService.getAllStudent();
		request.setAttribute("listStudent", lst);
		RequestDispatcher dispatcher = request.getRequestDispatcher("StudentList.jsp");
		dispatcher.forward(request, response);		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String address =request.getParameter("address");
		
		StudentDTO student = new StudentDTO(id, firstname, lastname, gender, birthday, address);
		this.studentService.updateStudent(student);
		response.sendRedirect("student");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		StudentDTO extstingS =this.studentService.getStudentByID(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("StudentForm.jsp");
		request.setAttribute("student", extstingS);
		dispatcher.forward(request, response);	
		
	}
	 private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	 
	        StudentDTO  student = new StudentDTO (id);
	        this.studentService.deleteStudent(student);
	        response.sendRedirect("student");
	   }
}
