package de.gp.finance.data.srv.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EQUITIES")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class EquityEntity extends BaseEntity {
    
    @Column(name = "symbol", nullable = false, unique = true)
	@JsonProperty(value = "symbol")
	private String symbol;

    @Column(name = "name", nullable = false, unique = true)
	@JsonProperty(value = "name")
	private String name;

    @Column(name = "isin", nullable = false, unique = true)
	@JsonProperty(value = "isin")
	private String isin;

    @Column(name = "type", nullable = false, unique = false)
	@JsonProperty(value = "type")
	private String type;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="CURRENCY_ID")
    @JsonProperty(value = "currency")
    private CurrencyEntity currency;
}
