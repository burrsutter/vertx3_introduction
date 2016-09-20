import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;

public class BadBadVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
     vertx.eventBus().<String>consumer("callmemaybe").handler(this::myhandler);
  } // start

  private void myhandler(Message<String> message) {
    String msg = message.body();
    System.out.println("Received: " + msg);
    System.out.println("Going to Sleep " + new java.util.Date());
    try {
        Thread.sleep(5000); // NEVER DO THIS :-)
        System.out.println("Waking from Sleep " + new java.util.Date());
        // another way to break things is to never reply
        // just comment out the line below
        message.reply(message.body() + " OUTPUT");
    } catch (InterruptedException e) {
        System.err.println(e);
    }
  } // myHandler

}