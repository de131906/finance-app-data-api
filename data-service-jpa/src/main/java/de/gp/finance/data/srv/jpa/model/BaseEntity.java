package de.gp.finance.data.srv.jpa.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@EntityListeners({BaseEntity.EntityListener.class})
public abstract class BaseEntity {

	@Id
	@Column(length=36)
	private String uid;
	

	private String uid() {
		if (uid == null) {
			uid = UUID.randomUUID().toString();
		}
		return uid;
	}
	
	public String getUid() {
		return uid;
	}
	
	public static class EntityListener {

		@PrePersist
		public void onPrePersist(BaseEntity baseEntity) {
			baseEntity.uid();
		}
	}
	
	@Override
	public boolean equals(Object o) {
		return (o == this || (o instanceof BaseEntity && uid().equals(((BaseEntity) o).uid())));
	}

	@Override
	public int hashCode() {
		return uid().hashCode();
	}
	
	@JsonIgnore
	public boolean isPersisted() {
		return uid != null && uid.length() > 0;
	}
}
