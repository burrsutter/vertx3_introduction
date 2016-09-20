import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;

public class Server extends AbstractVerticle {

  @Override
  public void start() {
    vertx.createHttpServer()
    .requestHandler(req -> req.response().end("Bonjour"))
    .listen(8080);
  }

}
