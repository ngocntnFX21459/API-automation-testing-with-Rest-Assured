package data;
public class User {
private String name;
private String gender;
private int age;
private String job;
public User() {}
public User(String name, String gender, int age, String job) {
	super();
	this.name = name;
	this.gender = gender;
	this.age = age;
	this.job = job;
}
public void setName(String name) {
	this.name = name;
}
public String getName() {
	return name;
}
public void setAge(int age) {
	this.age = age;
}
public int getAge() {
	return age;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getJob() {
	return job;
}
public void setJob(String job) {
	this.job = job;
}
}