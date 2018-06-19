package br.com.xyinc.utils;

import static spark.Spark.before;
import static spark.Spark.options;

public final class FiltroCors {

	public static void habilitarCors() {

		options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}
			return "OK";
		});

		before((request, response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Access-Control-Request-Method", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
			response.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
			response.type("application/json");
		});
	}
}