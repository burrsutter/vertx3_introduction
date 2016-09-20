import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

public class MyWebServer extends AbstractVerticle {
  @Override
  public void start() {

    // Create a router object.
    Router router = Router.router(vertx);

    router.get("/").handler(this::getRoot);

    router.get("/goodbye").handler(this::getGoodbye);

    router.get("/endpoint").handler(this::getEndpoint);
    router.put("/endpoint").handler(this::putEndpoint);
    router.delete("/endpoint").handler(this::deleteEndpoint);

    // static assets in local folder "webroot"
    // at http://localhost:8080/webroot/
    router.route("/webroot/*").handler(StaticHandler.create("webroot"));

    vertx.createHttpServer()        
        .requestHandler(router::accept)
        .listen(8080);
  } // start

  private void getRoot(RoutingContext routingContext) {
       HttpServerResponse response = routingContext.response();
       response
         .putHeader("content-type", "text/html")
         .end("<h1>Hello</h1>" + java.time.LocalDateTime.now());
  }
  private void getGoodbye(RoutingContext routingContext) {
       HttpServerResponse response = routingContext.response();
       response
         .putHeader("content-type", "text/html")
         .end("<h1>Goodbye</h1>" + java.time.LocalDateTime.now());
  }

  private void getEndpoint(RoutingContext routingContext) {
    HttpServerResponse response = routingContext.response();
    String responseBody =
    " {" +
	  "  \"employees\": [{ " + 
		"  \"firstName\": \"John\", " +
		"  \"lastName\": \"Doe\" " + 
	  "  }, { " +
		"  \"firstName\": \"Anna\", " +
		"  \"lastName\": \"Smith\" " + 
	  "  }, { " +
		"  \"firstName\": \"Peter\", " +
		"  \"lastName\": \"Jones\" " +
	  " }]" + 
    " } ";    
    
    response
         .setStatusCode(200)
         .putHeader("content-type", "application/json")
         .end(responseBody);
  }
  private void putEndpoint(RoutingContext routingContext) {
     HttpServerResponse response = routingContext.response();
     response
         .setStatusCode(200)
         .putHeader("content-type", "application/json")
         .end();

  }
  private void deleteEndpoint(RoutingContext routingContext) {
     HttpServerResponse response = routingContext.response();
     response
         .setStatusCode(204)
         .putHeader("content-type", "application/json")
         .end();
  
  }
  
}