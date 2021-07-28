package de.gp.finance.data.model;

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
public class Equity extends BaseDocument {

	@NonNull
	private String symbol;
	
	@NonNull
	private String name;
	
	@NonNull
	private String isin;
	
	@NonNull
	private EquityType type;
	
	@NonNull
	private String currencyId;
	
	@NonNull
	private Currency currency;
	
}
