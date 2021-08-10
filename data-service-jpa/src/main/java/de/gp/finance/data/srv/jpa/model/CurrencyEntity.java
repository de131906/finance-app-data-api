package de.gp.finance.data.srv.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CURRENCIES")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class CurrencyEntity extends BaseEntity {

	@Column(name = "symbol", nullable = false, unique = true)
	@JsonProperty(value = "symbol")
	private String symbol;
	
	@Column(name = "name", nullable = false, unique = true)
	@JsonProperty(value = "name")
	private String name;
	
}
