package co.edu.unbosque.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usergrades")
public class UserGrade {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)Long id;
	@Column(unique = true)
	private String name;
	private String grade1;
	private String grade2;
	private String grade3;
	
	public UserGrade() {
		// TODO Auto-generated constructor stub
	}

	public UserGrade(String name, String grade1, String grade2, String grade3) {
		super();
		this.name = name;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.grade3 = grade3;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade1() {
		return grade1;
	}

	public void setGrade1(String grade1) {
		this.grade1 = grade1;
	}

	public String getGrade2() {
		return grade2;
	}

	public void setGrade2(String grade2) {
		this.grade2 = grade2;
	}

	public String getGrade3() {
		return grade3;
	}

	public void setGrade3(String grade3) {
		this.grade3 = grade3;
	}

	@Override
	public int hashCode() {
		return Objects.hash(grade1, grade2, grade3, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserGrade other = (UserGrade) obj;
		return Objects.equals(grade1, other.grade1) && Objects.equals(grade2, other.grade2)
				&& Objects.equals(grade3, other.grade3) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "UserGrade [id=" + id + ", name=" + name + ", grade1=" + grade1 + ", grade2=" + grade2 + ", grade3="
				+ grade3 + "]";
	}

}
