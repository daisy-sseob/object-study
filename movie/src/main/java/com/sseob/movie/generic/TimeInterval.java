package com.sseob.movie.generic;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class TimeInterval {
  private LocalTime startTime;
  private LocalTime endTime;

  private TimeInterval(LocalTime startTime, LocalTime endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public static TimeInterval of(LocalTime startTime, LocalTime endTime) {
    return new TimeInterval(startTime, endTime);
  }
}
