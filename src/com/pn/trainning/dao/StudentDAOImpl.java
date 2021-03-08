package com.pn.trainning.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pn.trainning.dto.StudentDTO;

public class StudentDAOImpl implements StudentDAO{

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	

	public StudentDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	
	@Override
	public List<StudentDTO> getAllStudent() throws SQLException {
		List<StudentDTO> lst = new ArrayList<StudentDTO>();
		
			String sql = "SELECT * FROM student";
			
			connect();
			Statement statement = jdbcConnection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("student_id");
				String firstname=rs.getString("firstname");
				String lastname = rs.getString("lastname"); 
				String gender = rs.getString("gender");
				String birthday = rs.getString("birthday");
				String address = rs.getString("address");
				StudentDTO student = new StudentDTO(id, firstname, lastname, gender, birthday, address);
				lst.add(student);
			}
			rs.close();
			statement.close();
			disconnect();	
		return lst;
	}

	@Override
	public boolean addStudent(StudentDTO newStudent) throws SQLException {
	
			String sql = " INSERT INTO student(firstname, lastname, gender, birthday, address) VALUES (?, ?, ?, ?,?)";
			connect(); 
			PreparedStatement cmd = jdbcConnection.prepareStatement(sql);
			cmd.setString(1, newStudent.getFirstname());
			cmd.setString(2, newStudent.getLastname());
			cmd.setString(3, newStudent.getGender());
			cmd.setString(4, newStudent.getBirthday());
			cmd.setString(5, newStudent.getAddress());
		
			boolean rowInserted = cmd.executeUpdate() > 0;
			cmd.close();
			disconnect();
			return rowInserted;
	}

	@Override
	public boolean updateStudent(StudentDTO student) throws SQLException {

			String sql = " UPDATE student SET firstname= ?, lastname= ?, gender=?, birthday= ?, address= ? WHERE student_id= ? ";
			connect(); 
			PreparedStatement cmd = jdbcConnection.prepareStatement(sql);
			
			cmd.setString(1, student.getFirstname());
			cmd.setString(2, student.getLastname());
			cmd.setString(3, student.getGender());
			cmd.setString(4,  student.getBirthday());
			cmd.setString(5, student.getAddress());
			cmd.setInt(6, student.getId());
			boolean rowUpdated = cmd.executeUpdate() > 0;
			cmd.close();
			disconnect();
			return rowUpdated;
	}

	@Override
	public boolean deleteStudent(StudentDTO student) throws SQLException {
	
			String sql = "DELETE FROM student WHERE student_id = ?";
			connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, student.getId());
			
			boolean rowDeleted = statement.executeUpdate() > 0;
			statement.close();
			disconnect();
			return rowDeleted;	
	}

	@Override
	public StudentDTO getStudentByID(int id) throws SQLException {
		    StudentDTO sv = null;
			String sql = "SELECT * FROM student WHERE student_id= ?";
			connect();
			
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				String firstname=rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				String birthday = rs.getString("birthday");
				String address = rs.getString("address");
				
				sv = new StudentDTO(id, firstname, lastname, gender, birthday, address);
			}
			
			rs.close();
			statement.close();
			
			return sv;
		
	}

}
