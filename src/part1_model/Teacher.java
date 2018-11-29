package part1_model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Teacher extends Person {
	private ArrayList<Course> listOfTaughtCourses;
	
	public Teacher(int id, String name, String phone, String email,ArrayList<Course> listOfTaughtCourses) {
		super(id, name, phone, email);
		this.listOfTaughtCourses = listOfTaughtCourses;
	}
 	

	public ArrayList<Course> getListOfTaughtCourses() {
		return listOfTaughtCourses;
	}


	@Override
	public ArrayList<String> returnDomain() {	
		ArrayList<String> listOfDomain = new ArrayList<String>();
		for(Course oneCourse:this.listOfTaughtCourses) {
			if(!listOfDomain.contains(oneCourse.getDomainName())) {
				listOfDomain.add(oneCourse.getDomainName());
			}			
		}
		return listOfDomain;
	}

	@Override
	public void evaluate(Person person, int note) throws Exception {
		if(note<=10 && note >=0) {
		if(person instanceof Student) {
			Student s1 = ((Student)person);
			s1.evaluations.add(note);
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
		DecimalFormat df= new DecimalFormat("0.00");
		float result = avg/number;
		return df.format(result);
	}
	
	public void teachCourse(Course course) {
		this.listOfTaughtCourses.add(course);
	}
	
	public Course findCourse(Course course) {
		Course c1=null;
		for(Course oneCourse:this.listOfTaughtCourses) {
			if(oneCourse.equals(course)) {
				c1 = oneCourse;
			}
		}
		return c1;
	}


	@Override
	public String toString() {
		StringBuilder st = new StringBuilder("");
		st.append(super.toString()+"\n");
		String text = "------- List of taught courses -------\n";
		for(Course oneCourse:this.listOfTaughtCourses) {
			text+="Course : id : "+ oneCourse.getCourseId() + " Title : " + oneCourse.getTitle() + "\n";
		}
		text += "Total number of taught courses : " + this.listOfTaughtCourses.size()+"\n"+
		"-----------------------\n";
		text +="Avg evaluation is : "+ avgEvaluation() + "\n" + 
		"------------------List of domains------------------\n";
		for(String domain:returnDomain())	{
			text+= "Domain : " + domain+"\n";
		}
		st.append(text);
		return st.toString();
	}
}
