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
  "mem": 657978,
  "mem.free": 467910,
  "processors": 4,
  "instance.uptime": 48465,
  "uptime": 54666,
  "systemload.average": 3.3994140625,
  "heap.committed": 608768,
  "heap.init": 131072,
  "heap.used": 140857,
  "heap": 1864192,
  "nonheap.committed": 51008,
  "nonheap.init": 2496,
  "nonheap.used": 49210,
  "nonheap": 0,
  "threads.peak": 14,
  "threads.daemon": 4,
  "threads.totalStarted": 18,
  "threads": 14,
  "classes": 6239,
  "classes.loaded": 6239,
  "classes.unloaded": 0,
  "gc.ps_scavenge.count": 9,
  "gc.ps_scavenge.time": 117,
  "gc.ps_marksweep.count": 1,
  "gc.ps_marksweep.time": 53,
  "co.instil.HelloController.Timed.index.snapshot.99thPercentile": 60,
  "co.instil.HelloController.Timed.index.snapshot.min": 0,
  "co.instil.HelloController.Timed.index.snapshot.98thPercentile": 60,
  "co.instil.HelloController.Timed.index.count": 8,
  "co.instil.HelloController.Timed.index.snapshot.75thPercentile": 0,
  "co.instil.HelloController.Timed.index.meanRate": 0.16182403613729707,
  "co.instil.HelloController.Timed.index.snapshot.95thPercentile": 60,
  "co.instil.HelloController.Timed.index.snapshot.999thPercentile": 60,
  "co.instil.HelloController.Timed.index.snapshot.stdDev": 19,
  "co.instil.HelloController.Timed.index.snapshot.max": 60,
  "co.instil.HelloController.Timed.index.snapshot.mean": 7,
  "gauge.response.metrics": 147,
  "counter.status.200.metrics": 1,
  "co.instil.HelloController.Timed.index.fifteenMinuteRate": 0,
  "counter.status.200.root": 8,
  "co.instil.HelloController.Timed.index.snapshot.median": 0,
  "co.instil.HelloController.Timed.index.fiveMinuteRate": 0,
  "co.instil.HelloController.Timed.index.oneMinuteRate": 0,
  "gauge.response.root": 3
}
```