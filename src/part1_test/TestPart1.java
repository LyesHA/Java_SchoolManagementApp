/*
 * Author :  Lyes Hadj Aissa
 * Teacher : Mohamed Zeroug
 * Title :   First part of Java Project
 * Date :    05/11/2018 
 * 
*/
package part1_test;

import java.sql.Connection;
import java.util.ArrayList;

import database.creation.DataBaseCreation;
import database.creation.DatabaseSetup;
import part1_datamanagement.ManageData;
import part1_model.Course;
import part1_model.Person;
import part1_model.Student;
import part1_model.Teacher;

public class TestPart1 {
	static Connection connection; 

	public static void main(String[] args) {

		ArrayList<Course> listCourses = ManageData.readCourses();
		//ManageData.displayCourses(listCourses);
		
		ArrayList<Person> listTeachers = ManageData.readTeachers(listCourses);
		//displayTeachers(listTeachers);
		
		ArrayList<Person> listStudents = ManageData.readStudents(listCourses);
		
		ManageData.teacherEvaluateStudent(listTeachers, listStudents);		
		
		ManageData.studentEvaluateTeacher(listStudents,listTeachers);
		
		ManageData.displayStudents(listStudents);

		ManageData.displayTeachers(listTeachers);
		
		ManageData.teacherAddCourse(listTeachers, listCourses);
		
		ManageData.displayTeachers(listTeachers);
		
		ManageData.studentAddCourse(listStudents, listCourses);
	
		ManageData.displayStudents(listStudents);
		
		ManageData.displayOneStudent(listStudents, listStudents.get(1));
		
		creation_table();
	}
	
	private static void creation_table() {
		try {
			connection = DatabaseSetup.getConnection("dbConfig.properties");

			
			String sqlCommand = "CREATE TABLE TEACHER (\r\n" + 
					"TEACHER_ID NUMBER(4) CONSTRAINT TEACHER_PK PRIMARY KEY,\r\n" + 
					"NAME VARCHAR2(20),\r\n" + 
					"PHONE VARCHAR2(10),\r\n" + 
					"EMAIL VARCHAR2(30)";
			//DataBaseCreation.createTables(connection, sqlCommand);
			//System.out.println("La table a été crée avec succée");
			
			 sqlCommand = "CREATE TABLE COURSE(\r\n" + 
					"COURSE_ID NUMBER(3) CONSTRAINT COURSE_PK1 PRIMARY KEY,\r\n" + 
					"TITLE VARCHAR2(50),\r\n" + 
					"DOMAINE NUMBER(1),\r\n" + 
					"TEACHER_ID NUMBER(4))";
			
			//DataBaseCreation.createTables(connection, sqlCommand);
			//System.out.println("La table a été crée avec succée");
			
			sqlCommand = "CREATE TABLE STUDENT (\r\n" + 
					"STUDENT_ID NUMBER(4) CONSTRAINT STUDENT_PK PRIMARY KEY,\r\n" + 
					"NAME VARCHAR2(20),\r\n" + 
					"PHONE VARCHAR2(10),\r\n" + 
					"EMAIL VARCHAR2(30)";
			//DataBaseCreation.createTables(connection, sqlCommand);
			//System.out.println("La table a été crée avec succée");

			sqlCommand = "CREATE TABLE TEACHEREVALUATESTUDENT(\r\n" + 
					"TEACHER_ID NUMBER(4),\r\n" + 
					"STUDENT_ID NUMBER(4),\r\n" + 
					"GRADE NUMBER(2),\r\n" + 
					"CONSTRAINT FK_TEACHER_ID FOREIGN KEY (TEACHER_ID)\r\n" + 
					"REFERENCES TEACHER(TEACHER_ID),\r\n" + 
					"CONSTRAINT FK_student_id FOREIGN KEY (STUDENT_ID)\r\n" + 
					"REFERENCES STUDENT(STUDENT_ID),\r\n" + 
					"CONSTRAINT PK_TEACHER_student PRIMARY KEY (TEACHER_ID,STUDENT_ID))";
			
			//DataBaseCreation.createTables(connection, sqlCommand);
			//System.out.println("La table a été crée avec succée");
			
			sqlCommand = "CREATE TABLE STUDENTEVALUATETEACHER(\r\n" + 
					"STUDENT_ID NUMBER(4),\r\n" + 
					"TEACHER_ID NUMBER(4),\r\n" + 
					"GRADE NUMBER(2),\r\n" + 
					"CONSTRAINT FK_student_id2 FOREIGN KEY (STUDENT_ID)\r\n" + 
					"REFERENCES STUDENT(STUDENT_ID),\r\n" + 
					"CONSTRAINT FK_TEACHER_ID2 FOREIGN KEY (TEACHER_ID)\r\n" + 
					"REFERENCES TEACHER(TEACHER_ID),\r\n" + 
					"CONSTRAINT PK_STUDENT_TEACHER PRIMARY KEY (STUDENT_ID,TEACHER_ID))";
			//DataBaseCreation.createTables(connection, sqlCommand);
			//System.out.println("La table a été crée avec succée");
			
			sqlCommand = "CREATE TABLE TOUGHTCOURSES(\r\n" + 
					"TEACHER_ID NUMBER(4),\r\n" + 
					"COURSE_ID NUMBER(3),\r\n" + 
					"CONSTRAINT FK_TEACHER_TOUGHTCOURSES_ID FOREIGN KEY (TEACHER_ID)\r\n" + 
					"REFERENCES TEACHER(TEACHER_ID),\r\n" + 
					"CONSTRAINT FK_COURSE_TOUGHTCOURSES_ID FOREIGN KEY (COURSE_ID)\r\n" + 
					"REFERENCES COURSE(COURSE_ID),\r\n" + 
					"CONSTRAINT PK_TEACHERTOUGHT_COURSES PRIMARY KEY (TEACHER_ID,COURSE_ID))";
			//DataBaseCreation.createTables(connection, sqlCommand);
			//System.out.println("La table a été crée avec succée");
			
			sqlCommand = "CREATE TABLE STUDIEDCOURSE (\r\n" + 
					"STUDENT_ID NUMBER(4),\r\n" + 
					"COURSE_ID NUMBER(3),\r\n" + 
					"CONSTRAINT FK_student_STUDIEDCOURSE_id FOREIGN KEY (STUDENT_ID)\r\n" + 
					"REFERENCES STUDENT(STUDENT_ID),\r\n" + 
					"CONSTRAINT FK_COURSE_STUDIEDCOURSE_id FOREIGN KEY (COURSE_ID)\r\n" + 
					"REFERENCES COURSE(COURSE_ID),\r\n" + 
					"CONSTRAINT PK_STUDIED_COURSE PRIMARY KEY (STUDENT_ID,COURSE_ID))";
			//DataBaseCreation.createTables(connection, sqlCommand);
			//System.out.println("La table a été crée avec succée");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
