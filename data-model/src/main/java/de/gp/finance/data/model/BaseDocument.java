package de.gp.finance.data.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(doNotUseGetters = true)
public abstract class BaseDocument {

	@JsonAlias({"_id", "uid"})
	private String id;
	
	@NonNull
	private String docType;
	
	public String getId() {
		return id;
	}
	
	@JsonAlias({"_id", "uid"})
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDocType() {
		return docType;
	}
	
	public void setDocType(String docType) {
		this.docType = docType;
	}

	@JsonIgnore
	public boolean isPersisted() {
		return id != null;
	}

}
