package com.hzdp.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.hzdp.web.api.IResponse;
import com.hzdp.web.constants.EncrptConstants;
import com.ming.crypto.encryptor.NoPaddingEncryptor;
import com.ming.io.CompressUtil;
import com.ming.serialize.JsonSerializer;
import com.ming.serialize.Serializer;

public class ResponseWrapper {

	public static byte[] wrap(IResponse response) throws IOException {
		Serializer serializer = new JsonSerializer();
		byte[] bytes = serializer.serialize(response);
		bytes = CompressUtil.gzip(bytes);
		bytes = NoPaddingEncryptor.encrpt(bytes, EncrptConstants.key.getBytes(StandardCharsets.UTF_8), EncrptConstants.iv.getBytes(StandardCharsets.UTF_8),
				true);
		return bytes;
	}
}
