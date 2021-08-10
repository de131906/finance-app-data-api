package de.gp.finance.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder")
@ToString(callSuper = true, doNotUseGetters = true)
public class Institution extends BaseDocument {

	@NonNull
	@JsonProperty(value = "name")
	private String name;
	
	@NonNull
	@JsonProperty(value = "bic")
	private String bic;
	
}