package database.creation;

import java.sql.Connection;

public class ApplicationTest {
	
	static Connection connection; 

	public static void main(String[] args) {
		
		creation_table();
		//insert_dataTo_Course();
		//insert_dataTo_Teacher();
		//insert_dataTo_Student();
		//insert_dataTo_ToughtCourse();
		//insert_dataTo_StudiedCourse();
		//insert_dataTo_EvaluateTeacher();
		insert_dataTo_EvaluateStudent();
	}

	private static void insert_dataTo_EvaluateStudent() {
		try {
			
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
		}
	}

	private static void insert_dataTo_EvaluateTeacher() {
		try {
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
		}
	}

	private static void insert_dataTo_StudiedCourse() {
		try {
			String sqlCommand ="INSERT INTO STUDIEDCOURSE VALUES(2000,100)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand ="INSERT INTO STUDIEDCOURSE VALUES(2000,600)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand ="INSERT INTO STUDIEDCOURSE VALUES(2001,200)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand ="INSERT INTO STUDIEDCOURSE VALUES(2001,300)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand ="INSERT INTO STUDIEDCOURSE VALUES(2002,400)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			System.out.println("Les données ont été ajoutées avec succée");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void creation_table() {
		try {
			connection = DatabaseSetup.getConnection("dbConfig.properties");
			String sqlCommand = "CREATE TABLE COURSE(\r\n" + 
					"COURSE_ID NUMBER(3) CONSTRAINT COURSE_PK1 PRIMARY KEY,\r\n" + 
					"TITLE VARCHAR2(50),\r\n" + 
					"DOMAINE NUMBER(1),\r\n" + 
					"TEACHER_ID NUMBER(4))";
			
			//DataBaseCreation.createTables(connection, sqlCommand);
			//System.out.println("La table a été crée avec succée");
			
			sqlCommand = "CREATE TABLE TEACHER (\r\n" + 
					"TEACHER_ID NUMBER(4) CONSTRAINT TEACHER_PK PRIMARY KEY,\r\n" + 
					"NAME VARCHAR2(20),\r\n" + 
					"PHONE VARCHAR2(10),\r\n" + 
					"EMAIL VARCHAR2(30),\r\n" + 
					"COURSE_ID NUMBER(3),\r\n" + 
					"CONSTRAINT FK_COURSE_ID1 FOREIGN KEY (COURSE_ID)\r\n" + 
					"REFERENCES COURSE(COURSE_ID))";
			//DataBaseCreation.createTables(connection, sqlCommand);
			//System.out.println("La table a été crée avec succée");
			
			sqlCommand = "CREATE TABLE STUDENT (\r\n" + 
					"STUDENT_ID NUMBER(4) CONSTRAINT STUDENT_PK PRIMARY KEY,\r\n" + 
					"NAME VARCHAR2(20),\r\n" + 
					"PHONE VARCHAR2(10),\r\n" + 
					"EMAIL VARCHAR2(30),\r\n" + 
					"COURSE_ID NUMBER(3),\r\n" + 
					"CONSTRAINT FK_COURSE_STUDENT_ID1 FOREIGN KEY (COURSE_ID)\r\n" + 
					"REFERENCES COURSE(COURSE_ID))";
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
		//table Course	
		try {
			String sqlCommand = "INSERT INTO COURSE VALUES(100,'SQL Database',1,1000)" ;
			//DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO COURSE VALUES(200,'Java',1,1000)";
			//DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO COURSE VALUES(400,'WINDOWS OS',1,1030)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO COURSE VALUES(500,'Project management',4,1000)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO COURSE VALUES(300,'UML',1,1020)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand ="INSERT INTO COURSE VALUES(600,'Building doily',0,1040)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			System.out.println("Les données ont été insérées");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void insert_dataTo_Teacher() {
		try {
			String sqlCommand = "INSERT INTO TEACHER VALUES(1000,'Zeroug','5147889014','zeroug@hotmail.com',100)";
			//DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TEACHER VALUES(1020,'Fode','5146721234','fode@hotmail.ca',300)";
			//DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TEACHER VALUES(1030,'Guenaoui','5146612188','guenaoui@hotmail.ca',400)";
			//DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TEACHER (Teacher_id, name, phone, email) VALUES(1040,'Catherine','5147122187','cath@hotmail.ca')";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			System.out.println("Les donnes ont été ajoutés avec succes");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void insert_dataTo_Student() {
		
		try {
			String sqlCommand="INSERT INTO STUDENT VALUES(2000,'Marc','5147881213','marc@yahoo.ca',100)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand="INSERT INTO STUDENT VALUES(2001,'Maria','5146781415','maria@hotmail.ca',200)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand="INSERT INTO STUDENT VALUES(2002,'catherine','4389124567','catherine@yahoo.ca',400)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			System.out.println("Les données ont été ajoutées avec succés");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void insert_dataTo_ToughtCourse() {
		try {
			String sqlCommand = "INSERT INTO TOUGHTCOURSES VALUES (1000,100)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TOUGHTCOURSES VALUES (1000,200)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TOUGHTCOURSES VALUES (1000,500)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TOUGHTCOURSES VALUES (1020,300)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			sqlCommand = "INSERT INTO TOUGHTCOURSES VALUES (1030,400)";
			DataBaseCreation.insertData_ddl(connection, sqlCommand);
			System.out.println("Les données ont été ajoutés avec succées");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

