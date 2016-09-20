vertx.createHttpServer().requestHandler(function (request) {

  var response = request.response();
  response.putHeader("content-type", "text/plain");

  response.end("Hello Vert.x JavaScript " + Date.now());
}).listen(8080);

