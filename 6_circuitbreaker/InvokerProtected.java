import io.vertx.core.AbstractVerticle;
import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.Future;


public class InvokerProtected extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    /*
    vertx.setPeriodic(1000, id -> {
      // This handler will get called every second
      String msg = "Java Now " + new java.util.Date();
      System.out.println(msg);      
    }); // setPeriodic
    */
    CircuitBreakerOptions options = new CircuitBreakerOptions()
        .setMaxFailures(1)
        .setTimeout(2000)
        .setFallbackOnFailure(true);        
 
    CircuitBreaker breaker = CircuitBreaker.create(
      "my-breaker", vertx, options
    ); 
    vertx.setPeriodic(1000, v -> {    
        Future<String> result = breaker.executeWithFallback(
        future -> {
            System.out.println("Calling...");
            vertx.eventBus().send("callmemaybe", "INPUT", response -> {
                  if (response.failed()) {
                    future.fail(response.cause());
                  } else {
                    System.out.println("Else: " + response.result().body());
                    if (! future.isComplete()) {
                      future.complete();
                    } // if
                  } // else
            });
        }, v -> {          
              return "Fallback";
        });
        result.setHandler(ar -> {
          // Do something with the result
          System.out.println("Result: " + ar.result());
        });
    }); // setPeriodic
  } // start
}