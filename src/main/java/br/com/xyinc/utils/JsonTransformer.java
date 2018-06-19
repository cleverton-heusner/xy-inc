package br.com.xyinc.utils;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

	@Override
	public String render(Object model) {
		return new JsonMapper().toJSON(model);
	}
}
