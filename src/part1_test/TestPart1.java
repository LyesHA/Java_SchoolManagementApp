/*
 * Author :  Lyes Hadj Aissa
 * Teacher : Mohamed Zeroug
 * Title :   First part of Java Project
 * Date :    05/11/2018 
 * 
*/
package part1_test;

import java.util.ArrayList;

import part1_datamanagement.ManageData;
import part1_model.Course;
import part1_model.Person;
import part1_model.Student;
import part1_model.Teacher;

public class TestPart1 {

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
		
	}

}
