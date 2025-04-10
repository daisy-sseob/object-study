package com.sseob.movie.reservation.domain;


import com.sseob.movie.generic.Money;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Movie {
  
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private Integer runningTime;
  @Getter
  private Money fee;
  private DiscountPolicy discountPolicy;

  public Movie() {
  }

  public Movie(String title, Integer runningTime, Money fee) {
    this(null, title, runningTime, fee, null);
  }

  public Movie(Long id, String title, Integer runningTime, Money fee, DiscountPolicy discountPolicy) {
    this.id = id;
    this.title = title;
    this.runningTime = runningTime;
    this.fee = fee;
    this.discountPolicy = discountPolicy;
  }

  public Money calculateFee(Screening screening) {
    return fee.minus(discountPolicy.calculateDiscount(screening));
  }
  
}
