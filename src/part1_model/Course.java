package part1_model;

public class Course {
	private int courseId;
	private String title;
	private int domain;
	private int teacherId;
	private static final String[] tabDomans = {"Fashion","Computer Science","Language","Mathematics","Management"};
	private static int sequence=100;
	
	public Course(String title, int domain, int teacherId) {
		this.courseId = sequence;
		this.title = title;
		this.domain = domain;
		this.teacherId = teacherId;
		sequence += 100;
	}
	public Course(String title, int teacherId) {
		this.courseId = sequence;
		this.title = title;
		this.teacherId = teacherId;
		this.domain = 1;
		sequence += 100;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getCourseId() {
		return courseId;
	}
	public String getTitle() {
		return title.substring(0,1).toUpperCase()+title.substring(1).toLowerCase();
	}
	public int getDomain() {
		return domain;
	}
	public static String[] getTabdomans() {
		return tabDomans;
	}
	
	public String getDomainName() {
		String domainName="";
		if(getDomain()==0) {
			domainName="Fashion";
		}
		if(getDomain()==1) {
			domainName="Computer Science";
		}
		if(getDomain()==2) {
			domainName="Language";
		}
		if(getDomain()==3) {
			domainName="Mathematics";
		}
		if(getDomain()==4) {
			domainName="Management";
		}
		return domainName;
	}
	
	@Override
	public String toString() {
		return "Course Id : " + courseId + "\t Title : " + getTitle() + "\t Domain name : " + getDomainName() +
				" Teacher : " + teacherId;
	}
	@Override
	public boolean equals(Object obj) {
		Course other = (Course)obj;
		if(other.getCourseId()==this.getCourseId()) {
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	
	
	
	
}
