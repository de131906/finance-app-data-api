package de.gp.finance.data.srv.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INSTITUTIONS")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class InstitutionEntity extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "bic", nullable = false, unique = true)
	private String bic;
	
}
