package com.pn.trainning.service;

import java.sql.SQLException;
import java.util.List;

import com.pn.trainning.dao.StudentDAO;
import com.pn.trainning.dao.StudentDAOImpl;
import com.pn.trainning.dto.StudentDTO;

public class StudentServiceImpl implements StudentService{
	StudentDAO studentDAO;
	
	@Override
	public List<StudentDTO> getAllStudent() throws SQLException {
		return this.studentDAO.getAllStudent();
	}

	@Override
	public boolean addStudent(StudentDTO newStudent) throws SQLException {
		return this.studentDAO.addStudent(newStudent);
	}

	@Override
	public boolean updateStudent(StudentDTO student) throws SQLException {
		return this.studentDAO.updateStudent(student);
	}

	@Override
	public boolean deleteStudent(StudentDTO student) throws SQLException {
		return this.studentDAO.deleteStudent(student);
	}

	@Override
	public StudentDTO getStudentByID(int id) throws SQLException {
		return this.studentDAO.getStudentByID(id);
	}
	public StudentServiceImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.studentDAO = new StudentDAOImpl(jdbcURL, jdbcUsername, jdbcPassword);
	}

}
