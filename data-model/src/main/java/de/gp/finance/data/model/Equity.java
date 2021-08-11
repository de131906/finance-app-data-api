package de.gp.finance.data.model;

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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true, doNotUseGetters = true)
public class Equity extends BaseDocument {

	@NonNull
	private String symbol;
	
	@NonNull
	private String name;
	
	@NonNull
	private String isin;
	
	@NonNull
	private EquityType type;
	
	private String currencyId;
	
	private Currency currency;
	
}
