package com.sseob.chaptor01.reservation.domain;


import com.sseob.chaptor01.generic.Money;

public class Movie {
  private Long id;
  private String title;
  private Integer runningTime;
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
  
  public Money getFee() {
    return fee;
  }
  
}
