package part1_model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Student extends Person{
	private ArrayList<Course>listOfTakenCourses;

	public Student(int id, String name, String phone, String email,ArrayList<Course>listOfTakenCourses) {
		super(id, name, phone, email);
		this.listOfTakenCourses = listOfTakenCourses;
	}

	public ArrayList<Course> getListOfTakenCourses() {
		return listOfTakenCourses;
	}

	@Override
	public ArrayList<String> returnDomain() {
		ArrayList<String> listOfDomain = new ArrayList<String>();
		for(Course oneCourse:this.listOfTakenCourses) {
			if(!listOfDomain.contains(oneCourse.getDomainName())) {
				listOfDomain.add(oneCourse.getDomainName());
			}
		}
		return listOfDomain;
	}

	public void takeCourse(Course course) {
		this.listOfTakenCourses.add(course);
	}
	
	@Override
	public void evaluate(Person person, int note) throws Exception {
		if(note<=10 && note >=0) {
			if(person instanceof Teacher) {
				Teacher t1 = ((Teacher)person);
				t1.evaluations.add(note);
			}
		}else {
			throw new Exception("La note doit être entre 0 et 10!");
		}
		
	}

	@Override
	public String avgEvaluation() {
		float avg=0;
		int number=0;
		for(int oneNote:this.evaluations) {
			avg+=oneNote;
			number++;
		}
		//number =1;
		DecimalFormat df= new DecimalFormat(".##");
		float result = avg/number;
		return df.format(result);
		//return result;
	}
	

	
	public ArrayList<Person> listOfTeacher(ArrayList<Person> listTeachers){
		ArrayList<Person>listOfTeacher = new ArrayList<Person>();
		for(Course oneCourse:this.listOfTakenCourses) {
			for(Person oneTeacher:listTeachers) {
				if(oneTeacher.getId()==oneCourse.getTeacherId()) {
					listOfTeacher.add(oneTeacher);
				}
			}
		}	
		return listOfTeacher;	
	}
	
	@Override
	public String toString() {
		StringBuilder st = new StringBuilder("");
		st.append(super.toString()+"\n");
		String text = "------- List of taken courses -------\n";
		for(Course oneCourse:this.listOfTakenCourses) {
			text+="Course : id : "+ oneCourse.getCourseId() + " Title : " + oneCourse.getTitle() + "\n";
		}
		text += "Total number of taken courses : " + this.listOfTakenCourses.size()+"\n"+
		"-----------------------\n";
		text +="Avg evaluation is : "+ avgEvaluation() + "\n" + 
		"------------------List of domains------------------\n";
		for(String domainName:this.returnDomain())	{
			text+= "Domain : " + domainName+"\n";
		}
		st.append(text);
		return st.toString();
	}
	
}
