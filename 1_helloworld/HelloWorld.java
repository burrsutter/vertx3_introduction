import io.vertx.core.AbstractVerticle;

public class HelloWorld extends AbstractVerticle {
  @Override
  public void start() {
    vertx.createHttpServer()
        .requestHandler(req -> req.response().end("Hello Vert.x Java " + new java.util.Date()))
        .listen(8080);
  }

}