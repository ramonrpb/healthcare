package br.com.healthcare.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Exam implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O campo patientName é obrigatório")
	@Length(max = 300, message = "O tamanho deve ser no máximo 300 caracteres")
	@Column(nullable = false, length = 300)
	private String patientName;
	
	@NotNull(message = "O campo patientAge é obrigatório")
	@Column(nullable = false)
	private int patientAge;
	
	@NotNull(message = "O campo patientGender é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Gender patientGender;
	
	@NotNull(message = "O campo physicianName é obrigatório")
	@Length(max = 300, message = "O tamanho deve ser no máximo 300 caracteres")
	@Column(nullable = false, length = 300)
	private String physicianName;
	
	@NotNull(message = "O campo physicianCrm é obrigatório")
	@Column(nullable = false)
	private Long physicianCrm;
	
	@NotNull(message = "O campo procedureName é obrigatório")
	@Length(max = 300, message = "O tamanho deve ser no máximo 300 caracteres")
	@Column(nullable = false, length = 300)
	private String procedureName;
	
	@NotNull(message = "O campo healthcareInstitution é obrigatório")
	@OneToOne
	@JoinColumn(name = "id_healthcare_Institution", referencedColumnName = "id", nullable = false)
	private HealthcareInstitution healthcareInstitution;
	
	@Column(nullable = false)
	private	boolean read;
	
	public Exam() {
	}

	public Exam(Long id, String patientName, int patientAge, Gender patientGender, String physicianName,
			Long physicianCrm, String procedureName, boolean read) {
		super();
		this.id = id;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientGender = patientGender;
		this.physicianName = physicianName;
		this.physicianCrm = physicianCrm;
		this.procedureName = procedureName;
		this.read = read;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public Gender getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(Gender patientGender) {
		this.patientGender = patientGender;
	}

	public String getPhysicianName() {
		return physicianName;
	}
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public Long getPhysicianCrm() {
		return physicianCrm;
	}
	public void setPhysicianCrm(Long physicianCrm) {
		this.physicianCrm = physicianCrm;
	}

	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public HealthcareInstitution getHealthcareInstitution() {
		return healthcareInstitution;
	}
	public void setHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
		this.healthcareInstitution = healthcareInstitution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((patientName == null) ? 0 : patientName.hashCode());
		result = prime * result + ((physicianCrm == null) ? 0 : physicianCrm.hashCode());
		result = prime * result + ((procedureName == null) ? 0 : procedureName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (patientName == null) {
			if (other.patientName != null)
				return false;
		} else if (!patientName.equals(other.patientName))
			return false;
		if (physicianCrm == null) {
			if (other.physicianCrm != null)
				return false;
		} else if (!physicianCrm.equals(other.physicianCrm))
			return false;
		if (procedureName == null) {
			if (other.procedureName != null)
				return false;
		} else if (!procedureName.equals(other.procedureName))
			return false;
		return true;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}
	
}
