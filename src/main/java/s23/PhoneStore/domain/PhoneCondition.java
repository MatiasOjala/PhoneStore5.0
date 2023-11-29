package s23.PhoneStore.domain;

import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// Minun täytyi muuttaa Condition -> PhoneCondition, sillä oli ilmeisesti kielletty sana. Ainakaan SQL ei tykännyt.

@Entity
public class PhoneCondition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long phoneconditionid;
	private String name;
	// Tiedot puhelimen kunnosta ja kunnon "nimi"
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="phonecondition")
	private List<Phone> phones;
	
	
	public PhoneCondition() {
		
		super();
		// TODO Auto-generated constructor stub
	}


	public PhoneCondition(String name) {
		super();
		this.name = name;
	}


	public Long getPhoneconditionid() {
		return phoneconditionid;
	}


	public void setPhoneconditionid(Long phoneconditionid) {
		this.phoneconditionid = phoneconditionid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Phone> getPhones() {
		return phones;
	}


	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}


	@Override
	public String toString() {
		return "PhoneCondition [phoneconditionid=" + phoneconditionid + ", name=" + name + "]";
	}
	

}
