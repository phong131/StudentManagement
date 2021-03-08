package com.pn.trainning.service;

import java.sql.SQLException;
import java.util.List;

import com.pn.trainning.dto.StudentDTO;

public interface StudentService {
	List<StudentDTO> getAllStudent() throws SQLException;
	boolean addStudent(StudentDTO newStudent) throws SQLException;
	boolean updateStudent(StudentDTO student) throws SQLException;
	boolean deleteStudent(StudentDTO student) throws SQLException;
	StudentDTO getStudentByID(int id) throws SQLException;
}
