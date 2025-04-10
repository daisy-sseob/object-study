package com.sseob.movie.reservation.domain;

import com.sseob.movie.generic.Money;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Screening {
  
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Movie movie;
  private Integer sequence;
  private LocalDateTime screeningTime;
  
  public Screening() {
  }

  public Screening(Movie movie, Integer sequence, LocalDateTime screeningTime) {
    this(null, movie, sequence, screeningTime);
  }

  public Screening(Long id, Movie movie, Integer sequence, LocalDateTime screeningTime) {
    this.id = id;
    this.movie = movie;
    this.sequence = sequence;
    this.screeningTime = screeningTime;
  }

  public boolean isPlayedIn(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
    return this.screeningTime.getDayOfWeek().equals(dayOfWeek) &&
            (this.screeningTime.toLocalTime().equals(startTime) || this.screeningTime.toLocalTime().isAfter(startTime)) &&
            (this.screeningTime.toLocalTime().equals(endTime) || this.screeningTime.toLocalTime().isBefore(endTime));
  }

  public Reservation reserve(Long customerId, Integer audienceCount) {
    return new Reservation(customerId, this.id, audienceCount, movie.calculateFee(this).times(audienceCount));
  }
  
  public Money getFixedFee() {
    return this.movie.getFee();
  }
  
  public boolean isSequence(Integer sequence) {
    return this.sequence.equals(sequence);
  }
  
}
