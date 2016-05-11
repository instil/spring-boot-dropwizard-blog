### Example of minimum required for dropwizard metrics.

Add following to your gradle dependencies.
```
compile ('com.ryantenney.metrics:metrics-spring:3.1.3') {
    exclude group: 'com.codahale.metrics'
    exclude group: 'org.springframework'
}
compile 'io.dropwizard.metrics:metrics-core:3.1.2'
compile 'io.dropwizard.metrics:metrics-annotation:3.1.2'
```

Enable metrics on your SpringBootApplication entry point.
```
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMetrics(proxyTargetClass = true)
public class Application extends MetricsConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

```

Add the [@Timed](https://dropwizard.github.io/metrics/3.1.0/apidocs/com/codahale/metrics/annotation/Timed.html) annotation to any of your endpoints you wish to have timed metrics for.
```
import com.codahale.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    @Timed
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
```

### Run this example project
```
./gradlew build && java -jar build/libs/gs-spring-boot-0.1.0.jar
```

### View metrics
```
localhost:8080/metrics
```

### Example metrics
```
{
  "mem": 657794,
  "mem.free": 455872,
  "processors": 4,
  "instance.uptime": 25609,
  "uptime": 32713,
  "systemload.average": 2.64697265625,
  "heap.committed": 609792,
  "heap.init": 131072,
  "heap.used": 153919,
  "heap": 1864192,
  "nonheap.committed": 49344,
  "nonheap.init": 2496,
  "nonheap.used": 48002,
  "nonheap": 0,
  "threads.peak": 14,
  "threads.daemon": 4,
  "threads.totalStarted": 18,
  "threads": 14,
  "classes": 6239,
  "classes.loaded": 6239,
  "classes.unloaded": 0,
  "gc.ps_scavenge.count": 9,
  "gc.ps_scavenge.time": 126,
  "gc.ps_marksweep.count": 1,
  "gc.ps_marksweep.time": 54,
  "co.instil.HelloController.index.snapshot.75thPercentile": 0,
  "co.instil.HelloController.index.snapshot.median": 0,
  "co.instil.HelloController.index.snapshot.max": 8,
  "co.instil.HelloController.index.fifteenMinuteRate": 0.004432121596082617,
  "co.instil.HelloController.index.snapshot.mean": 1,
  "co.instil.HelloController.index.meanRate": 0.26342740827887096,
  "co.instil.HelloController.index.snapshot.95thPercentile": 8,
  "co.instil.HelloController.index.oneMinuteRate": 0.06396446829654137,
  "co.instil.HelloController.index.count": 7,
  "co.instil.HelloController.index.snapshot.98thPercentile": 8,
  "gauge.response.metrics": 184,
  "co.instil.HelloController.index.snapshot.999thPercentile": 8,
  "gauge.response.root": 4,
  "counter.status.200.metrics": 1,
  "co.instil.HelloController.index.snapshot.stdDev": 2,
  "counter.status.200.root": 7,
  "co.instil.HelloController.index.fiveMinuteRate": 0.013222836942706007,
  "co.instil.HelloController.index.snapshot.min": 0,
  "co.instil.HelloController.index.snapshot.99thPercentile": 8
}
```