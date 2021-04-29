package com.github.krunalvora.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;

public class Metrics {

  static Histogram requestsDuration;
  static Counter requestsTotal;

  static void registerMetrics() {
    requestsTotal = Counter.build()
          .help("Requests count")
          .name("request_total")
          .register();

    requestsDuration = Histogram.build()
          .help("Requests duration")
          .name("request_seconds")
          .buckets(.005, .01, .025, .05, .1, .25, .5, 1, 5, 10, 20, 30)
          // .exponentialBuckets(0.005, 5, 10)
          .register();
  }
}
