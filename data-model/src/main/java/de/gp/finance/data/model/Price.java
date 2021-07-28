package de.gp.finance.data.model;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString(callSuper = true, doNotUseGetters = true)
public class Price extends BaseDocument {

	@NonNull
	private String date;
	
	private double rate;
	
	@NonNull
	private String equityId;
	
	private Equity equity;
	
	public LocalDate getDate() {
		return LocalDate.parse(date);
	}
}
