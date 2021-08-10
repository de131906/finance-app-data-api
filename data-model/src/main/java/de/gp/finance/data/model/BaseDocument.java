package de.gp.finance.data.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(doNotUseGetters = true)
public abstract class BaseDocument {

	@JsonAlias({"id", "uid"})
	@JsonProperty(value = "id")
	private String _id;
	
	@JsonAlias({"rev"})
	@JsonProperty(value = "rev")
	private String _rev;
	
	@NonNull
	private String docType;
	
	public String getId() {
		return _id;
	}
	
	@JsonAlias({"id", "uid"})
	public void setId(String id) {
		this._id = id;
	}
	
	public String getRev() {
		return _rev;
	}
	
	@JsonAlias({"rev"})
	public void setRev(String rev) {
		this._rev = rev;
	}
	
	public String getDocType() {
		return docType;
	}
	
	public void setDocType(String docType) {
		this.docType = docType;
	}

	@JsonIgnore
	public boolean isPersisted() {
		return _id != null && _id.length() > 0;
	}

}
