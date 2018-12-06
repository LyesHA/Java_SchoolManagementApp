package part1_datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import database.creation.DataBaseCreation;
import database.creation.DatabaseSetup;
import part1_model.Course;
import part1_model.Person;
import part1_model.Student;
import part1_model.Teacher;

public class ManageData {
	
	public static ArrayList<Course> readCourses(){
		ArrayList<Course> listOfCourses=new ArrayList<Course>();
		File fileId = new File("course.txt");
		try {
			Scanner data = new Scanner(fileId);
			int courseId,teacherId=0;
			String title;
			Course c0;
			while(data.hasNextLine()) {			

				StringTokenizer st = new StringTokenizer(data.nextLine(),",");
				courseId = Integer.parseInt(st.nextToken());
				title= st.nextToken();
				int i = Integer.parseInt(st.nextToken());
				if(st.hasMoreTokens()) {
					teacherId = Integer.parseInt(st.nextToken());
					c0 = new Course(title,i,teacherId);
				}									
				else {				
					c0 = new Course(title,i);			
				}	
				listOfCourses.add(c0);
			}			
			data.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listOfCourses;
	}

	
	public static void displayCourses(ArrayList<Course> listOfCourses){
		for(Course oneCourse:listOfCourses) {
			System.out.println(oneCourse);
		}
	}

	public static ArrayList<Person> readTeachers(ArrayList<Course> listOfCourses){
		ArrayList<Person> listTeachers = new ArrayList<Person>();
		ArrayList<Course> listCourses;
		File fileId = new File("teacher.txt");
		try {
			Scanner data = new Scanner(fileId);
			int id;
			String name, phone, email;
			StringTokenizer st;
			while(data.hasNext()) {
				listCourses= new ArrayList<Course>();
				st = new StringTokenizer(data.nextLine(),",");
				id = Integer.parseInt(st.nextToken());
				name = st.nextToken();
				phone = st.nextToken();
				email = st.nextToken();
				
				while(st.hasMoreTokens()) {
					int i = Integer.parseInt(st.nextToken());
					for(Course oneCourse:listOfCourses) {
						if(oneCourse.getCourseId()==i) {
							listCourses.add(oneCourse);
							//t1.teachCourse(oneCourse);
						}
					}					
				}
				
				Teacher t1 = new Teacher(id,name,phone,email,listCourses);
				listTeachers.add(t1);
			}
			data.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		return listTeachers;		
	}

	public static void displayTeachers(ArrayList<Person> listOfTeachers){
		for(Person onePerson:listOfTeachers) {
			System.out.println(onePerson+"\n");
		}
	}

	public static ArrayList<Person> readStudents(ArrayList<Course> listOfCourses){
		ArrayList<Person> listStudents = new ArrayList<Person>();
		ArrayList<Course> listCourses;
		File fileId = new File("student.txt");
		try {
			Scanner data = new Scanner(fileId);
			int id;
			String name, phone, email;
			StringTokenizer st;
			while(data.hasNextLine()) {
				listCourses= new ArrayList<Course>();
				st = new StringTokenizer(data.nextLine(),",");
				id = Integer.parseInt(st.nextToken());
				name = st.nextToken();
				phone = st.nextToken();
				email = st.nextToken();
				
				while(st.hasMoreTokens()) {
					int i = Integer.parseInt(st.nextToken());
					for(Course oneCourse:listOfCourses) {
						if(oneCourse.getCourseId() == i) {
							//s1.takeCourse(oneCourse);
							listCourses.add(oneCourse);
						}
					}
				}
				Student s1 = new Student(id,name,phone,email,listCourses);
				listStudents.add(s1);
			}
			data.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		return listStudents;
	}

	public static void displayStudents(ArrayList<Person> listOfStudents) {
		for(Person oneStudent:listOfStudents) {
			System.out.println(oneStudent+"\n");
		}
	}
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn= DatabaseSetup.getConnection("dbConfig.properties");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void teacherEvaluateStudent(ArrayList<Person> listOfTeachers,ArrayList<Person> listOfStudents) {
		
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
				for(Person oneTeacher:listOfTeachers) {
					if(oneTeacher.getId() == teacherId) {
						for(Person oneStudent:listOfStudents) {
							if(oneStudent.getId()==studentId) {
								try {
									oneTeacher.evaluate(oneStudent, grade);
									String sqlCommand = "INSERT INTO TEACHEREVALUATESTUDENT VALUES (?,?,?)";
									/*DataBaseCreation.ddl_with_parameters(getConnection(), sqlCommand, oneTeacher.getId(),
											oneStudent.getId(),grade);*/
								} catch (Exception e) {
									e.getMessage();
								}
							}
						}
					}						
				}
				
			}
			data.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

	public static void studentEvaluateTeacher(ArrayList<Person> listOfStudents,ArrayList<Person> listOfTeachers) {
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
					for(Person oneStudent:listOfStudents) {
						if(oneStudent.getId()==studentId) {
							for(Person oneTeacher:listOfTeachers) {
								if(oneTeacher.getId()==teacherId) {
									try {
										oneStudent.evaluate(oneTeacher, grade);
										String sqlCommand = "INSERT INTO STUDENTEVALUATETEACHER VALUES(?,?,?)";
										/*DataBaseCreation.ddl_with_parameters(getConnection(), sqlCommand, oneStudent.getId(),
												oneTeacher.getId(), grade);*/
									} catch (Exception e) {
										e.getMessage();
									}
								}
							}
						}					
					}
				}
				data.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
	}
			
	public static void teacherAddCourse(ArrayList<Person> listOfTeachers,ArrayList<Course> listOfCourses) {
		
		File fileId = new File("teacherAddCourse.txt");
		try {
			Scanner data = new Scanner(fileId);
			int teacherId, courseId;
			StringTokenizer st = new StringTokenizer(data.nextLine(),",");
			teacherId= Integer.parseInt(st.nextToken());
			courseId = Integer.parseInt(st.nextToken());
			
			for(Course course:listOfCourses) {
				if(course.getCourseId()==courseId) {
					for(Person person:listOfTeachers) {
						if(person.getId()==teacherId) {
							((Teacher)person).teachCourse(course);
						}
					}
				}
			}	
			data.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}

	public static void studentAddCourse(ArrayList<Person> listOfStudents, ArrayList<Course> listOfCourses) {
		File fileId = new File("studentTakeCourse.txt");
		try {
			Scanner data = new Scanner(fileId);
			int studentId, courseId;
			StringTokenizer st = new StringTokenizer(data.nextLine(),",");
			studentId= Integer.parseInt(st.nextToken());
			courseId= Integer.parseInt(st.nextToken());
			for(Person person:listOfStudents) {
				if(person.getId()==studentId) {
					for(Course oneCourse:listOfCourses) {
						if(oneCourse.getCourseId()==courseId) {
							((Student)person).takeCourse(oneCourse);
						}
					}
				}
			}
			
			
			
			
			data.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public static void displayOneStudent(ArrayList<Person> listOfStudents,Person student) {
		for(Person person:listOfStudents) {
			if(person.equals(student)) {
				System.out.println(person);
			}
		}
	}
	
	public static void displayOneTeacher(ArrayList<Person>listOfTeachers,Person teacher) {
		for(Person person:listOfTeachers) {
			if(person.equals(teacher)) {
				System.out.println(person);
			}
		}
	}
}
