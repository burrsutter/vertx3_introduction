import io.vertx.core.AbstractVerticle;

public class InvokerUnprotected extends AbstractVerticle {
  @Override
  public void start() throws Exception {
     String msg = "INPUT";
     
     vertx.setPeriodic(1000, v -> {
       System.out.println("Calling...");
       vertx.eventBus().send("callmemaybe", msg, reply -> {
         if (reply.succeeded()) {
           System.out.println("Received reply " + reply.result().body());
         } else {
           System.out.println("No reply");
         }
       }); // send
     }); // setPeriodic  
  } // start
}  