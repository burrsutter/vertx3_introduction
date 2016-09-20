
import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;


public class Client extends AbstractVerticle {

  @Override
  public void start() {
    CircuitBreakerOptions options = new CircuitBreakerOptions()
        .setMaxFailures(5)
        .setTimeout(5000)
        .setFallbackOnFailure(true);

    CircuitBreaker breaker =
        CircuitBreaker.create("my-circuit-breaker", vertx, options)
        .openHandler(v -> {
          System.out.println("Circuit opened");
        }).closeHandler(v -> {
          System.out.println("Circuit closed");
        });

    Future<String> result = breaker.executeWithFallback(future -> {
      vertx.createHttpClient().getNow(8080, "localhost", "/", response -> {
        if (response.statusCode() != 200) {
          future.fail("HTTP error");
        } else {
          response.exceptionHandler(future::fail).bodyHandler(buffer -> {
            future.complete(buffer.toString());
          });
        }
      });
    }, v -> {
      // Executed when the circuit is opened
      return "Hello (fallback)";
    });

    result.setHandler(ar -> {
      // Do something with the result
      System.out.println("Result: " + ar.result());
    });
  }
}
