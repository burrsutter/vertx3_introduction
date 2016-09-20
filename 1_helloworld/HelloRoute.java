import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.core.http.HttpServerResponse;

public class HelloRoute extends AbstractVerticle {
  @Override
  public void start() {

    // Create a router object.
    Router router = Router.router(vertx);

    router.route("/").handler(routingContext -> {
       HttpServerResponse response = routingContext.response();
       response
         .putHeader("content-type", "text/html")
         .end("<h1>Hello</h1>" + java.time.LocalDateTime.now());
    });

    router.route("/goodbye").handler(routingContext -> {
       HttpServerResponse response = routingContext.response();
       response
         .putHeader("content-type", "text/html")
         .end("<h1>Goodbye</h1>" + java.time.LocalDateTime.now());
    });

    vertx.createHttpServer()        
        .requestHandler(router::accept)
        .listen(8080);
  } // start

}