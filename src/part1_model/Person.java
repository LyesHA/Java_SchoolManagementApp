package part1_model;

import java.util.ArrayList;

public abstract class Person {

	protected int id;
	protected String name;
	protected String phone;
	protected String email;
	protected ArrayList<Integer>evaluations;
	
	public Person(int id, String name, String phone, String email) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.evaluations = new ArrayList<Integer>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Integer> getEvaluations() {
		return evaluations;
	}
	
	public abstract ArrayList<String>returnDomain();

	public abstract void evaluate(Person person, int note) throws Exception;
	
	public abstract String avgEvaluation();

	@Override
	public String toString() {
		return "Id : " + id + " Name : " + name + " Phone : " + phone + " Email : " + email +
				" Nb Evaluations : " +  getEvaluations().size();
	}
	
	
}
