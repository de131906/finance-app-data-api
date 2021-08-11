package de.gp.finance.data.srv.jpa.model;

import java.time.LocalDate;

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
@Table(name = "PRICES")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class PriceEntity extends BaseEntity {
    
    @Column(name = "date", nullable = false, unique = true)
	@JsonProperty(value = "date")
	private LocalDate date;

    @Column(name = "rate", nullable = false, unique = false)
	@JsonProperty(value = "rate")
	private double rate;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="EQUITY_ID")
    @JsonProperty(value = "equity")
    private EquityEntity equity;
}
