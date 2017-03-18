package com.hzdp.web;

import java.io.IOException;

import com.hzdp.web.api.IResponse;
import com.ming.crypto.EncryptionUtil;
import com.ming.io.CompressUtil;
import com.ming.serialize.JsonSerializer;
import com.ming.serialize.Serializer;

public class ResponseWrapper {

	private static final String key = "07C1276A22153AE5";
	private static final String iv = "55F9567817BDABFD";

	public static byte[] wrap(IResponse response) throws IOException {
		Serializer serializer = new JsonSerializer();
		byte[] bytes = serializer.serialize(response);
		bytes = CompressUtil.gzip(bytes);
		bytes = EncryptionUtil.encrpt(bytes, key, iv);
		return bytes;
	}
}
