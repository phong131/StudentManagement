package com.pn.trainning.dao;

import java.sql.SQLException;
import java.util.List;

import com.pn.trainning.dto.StudentDTO;

public interface StudentDAO {
	List<StudentDTO> getAllStudent() throws SQLException;
	boolean addStudent(StudentDTO newStudent) throws SQLException;
	boolean updateStudent(StudentDTO student) throws SQLException;
	boolean deleteStudent(StudentDTO student) throws SQLException;
	StudentDTO getStudentByID(int id) throws SQLException;
}
