package database.creation;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import part1_datamanagement.ManageData;
import part1_model.Course;
import part1_model.Person;
import part1_model.Student;
import part1_model.Teacher;

/*
Auteur : Lyes Hadj Aissa 
Professeur : M Mohamed Zeroug
Date : 29-11-2018
PARTIE II : CREATION ET MANIPULATION DE DONNEES D’UNE BD EN UTILISANTJAVA

*/
public class ApplicationTest {
	
	static Connection connection; 
	static ArrayList<Person> listOfStudents;
	static ArrayList<Course> listOfCourses;
	static ArrayList<Person> listOfTeachers;
	public static void main(String[] args) {
		
		//La fonction qui permet de créer les tables dans la base de données
		creation_table();
		listOfCourses = ManageData.readCourses();
		listOfStudents= ManageData.readStudents(listOfCourses);
		listOfTeachers = ManageData.readTeachers(listOfCourses);
		//L'insértion des données dans les tables
		
		//insert_dataTo_Course();
		//insert_dataTo_Teacher();
		//insert_dataTo_Student();
		//insert_dataTo_ToughtCourse();
		//insert_dataTo_StudiedCourse();
		//insert_dataTo_EvaluateTeacher();
		//insert_dataTo_EvaluateStudent();
		
		//Affichage les évaluations d'un enseignant donné
		displayEvaluationData("1020");
		//Affichage des cours d'un enseignant donné
		displayCoursesData("1000");
	}

	// Les fonctions : 
	
	private static void displayCoursesData(String teacherId) {
		String sqlCommand = "SELECT T.COURSE_ID , C.TITLE\r\n" + 
				"FROM COURSE C, TOUGHTCOURSES T\r\n" + 
				"WHERE C.COURSE_ID = T.COURSE_ID AND\r\n" + 
				" T.TEACHER_ID=?";
		try {
			System.out.println("\nLes cours enseignés par le prof sont :");
			DataBaseCreation.displayCourses(connection, sqlCommand, teacherId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void displayEvaluationData(String teacherId) {
		String sqlCommand = "SELECT S.STUDENT_ID, NAME, GRADE\r\n" + 
				"FROM STUDENT S, TEACHEREVALUATESTUDENT T \r\n" + 
				"WHERE S.STUDENT_ID = T.STUDENT_ID\r\n" + 
				"AND T.TEACHER_ID = ?";
		try {
			System.out.println("Les évaluations faites par cet enseignant :");
			DataBaseCreation.displayEvaluations(connection, sqlCommand, teacherId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insert_dataTo_EvaluateStudent() {
		/*try {
			
			String sqlCommand = "INSERT INTO TEACHEREVALUATESTUDENT VALUES(1000,2000,5)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TEACHEREVALUATESTUDENT VALUES(1000,2001,7)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TEACHEREVALUATESTUDENT VALUES(1000,2002,6)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TEACHEREVALUATESTUDENT VALUES(1020,2000,3)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TEACHEREVALUATESTUDENT VALUES(1020,2001,6)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TEACHEREVALUATESTUDENT VALUES(1020,2002,5)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			System.out.println("Les données ont été ajoutées avec succée");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		File fileId = new File("teacherEvaluateStudent.txt");
		try {
			Scanner data= new Scanner(fileId);
			int teacherId, studentId,grade;
			StringTokenizer st;
			while(data.hasNextLine()) {
				st= new StringTokenizer(data.nextLine(),",");
				teacherId = Integer.parseInt(st.nextToken());
				studentId = Integer.parseInt(st.nextToken());
				grade = Integer.parseInt(st.nextToken());
				String sqlCommand = "INSERT INTO TEACHEREVALUATESTUDENT VALUES (?,?,?)";
				DataBaseCreation.ddl_with_parameters(connection, sqlCommand, teacherId,
				studentId,grade);
			}
		}catch (Exception e) {
			e.getMessage();
			}
		}

	private static void insert_dataTo_EvaluateTeacher() {
	/*	try {
			String sqlCommand="INSERT INTO STUDENTEVALUATETEACHER VALUES(2000,1000,7)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand="INSERT INTO STUDENTEVALUATETEACHER VALUES(2000,1020,5)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand="INSERT INTO STUDENTEVALUATETEACHER VALUES(2000,1030,12)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand="INSERT INTO STUDENTEVALUATETEACHER VALUES(2001,1000,8)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand="INSERT INTO STUDENTEVALUATETEACHER VALUES(2001,1020,7)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand="INSERT INTO STUDENTEVALUATETEACHER VALUES(2002,1000,7)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand="INSERT INTO STUDENTEVALUATETEACHER VALUES(2002,1020,7)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand="INSERT INTO STUDENTEVALUATETEACHER VALUES(2002,1030,8)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			System.out.println("Les données ont été ajoutées avec succée");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		File fileId = new File("studentEvaluateTeacher.txt");
		
		try {
			Scanner data= new Scanner(fileId);
			int teacherId, studentId,grade;
			StringTokenizer st;
			while(data.hasNextLine()) {
				st = new StringTokenizer(data.nextLine(),",");
				studentId=Integer.parseInt(st.nextToken());
				teacherId=Integer.parseInt(st.nextToken());
				grade =Integer.parseInt(st.nextToken());
								try {
									String sqlCommand = "INSERT INTO STUDENTEVALUATETEACHER VALUES(?,?,?)";
									DataBaseCreation.ddl_with_parameters(connection, sqlCommand, studentId,
											teacherId, grade);
								} catch (Exception e) {
									e.getMessage();
								}
							}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void insert_dataTo_StudiedCourse() {
		
		for(Person oneStudent:listOfStudents) {
			Student student= (Student) oneStudent;
			for(Course oneCourse:student.getListOfTakenCourses()) {
				String sqlCommand = "INSERT INTO STUDIEDCOURSE VALUES(?,?)";
				try {
					DataBaseCreation.ddl_with_parameters(connection, sqlCommand, student.getId(),oneCourse.getCourseId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Les données ont été ajoutés avec succées");

		}
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

	private static void insert_dataTo_Course() {
		
		for(Course oneCourse:listOfCourses) {
			String sqlCommand = "INSERT INTO COURSE VALUES(?,?,?,?)";
			try {
				DataBaseCreation.ddl_with_parameters(connection, sqlCommand, oneCourse.getCourseId() , oneCourse.getTitle(),
						String.valueOf(oneCourse.getDomain()), String.valueOf(oneCourse.getTeacherId()));
				System.out.println("Les données ont été insérées");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
		
	private static void insert_dataTo_Teacher() {
	
		for(Person oneTeacher:listOfTeachers) {
			String sqlCommand = "INSERT INTO TEACHER VALUES(?,?,?,?)";
			try {
				DataBaseCreation.ddl_with_parameters(connection, sqlCommand, oneTeacher.getId() , oneTeacher.getName(),
						oneTeacher.getPhone(), oneTeacher.getEmail());
				System.out.println("Les donnes ont été ajoutés avec succes");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private static void insert_dataTo_Student() {
		
		
		for(Person oneStudent:listOfStudents) {
			String sqlCommand = "INSERT INTO STUDENT VALUES(?,?,?,?)";
			try {
				DataBaseCreation.ddl_with_parameters(connection, sqlCommand, oneStudent.getId(), oneStudent.getName()
						, oneStudent.getPhone() , oneStudent.getEmail());
				System.out.println("Les données ont été ajoutées avec succés");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private static void insert_dataTo_ToughtCourse() {
		
		for(Person oneTeacher:listOfTeachers) {
			Teacher teacher= (Teacher) oneTeacher;
			for(Course oneCourse:teacher.getListOfTaughtCourses()) {
				String sqlCommand = "INSERT INTO TOUGHTCOURSES VALUES(?,?)";
				try {
					DataBaseCreation.ddl_with_parameters(connection, sqlCommand, oneTeacher.getId(),oneCourse.getCourseId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Les données ont été ajoutés avec succées");

		}
	}
}

