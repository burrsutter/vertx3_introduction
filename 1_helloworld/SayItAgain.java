import io.vertx.core.AbstractVerticle;

public class SayItAgain extends AbstractVerticle {
  @Override
  public void start() {
   vertx.setPeriodic(1000, id -> {
    // This handler will get called every second
    System.out.println("Hello World " + new java.util.Date());
   });
  }

}