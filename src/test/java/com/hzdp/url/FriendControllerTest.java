package com.hzdp.url;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.hzdp.web.constants.EncrptConstants;
import com.ming.crypto.EncryptionUtil;
import com.ming.crypto.encryptor.NoPaddingEncryptor;
import com.ming.io.CompressUtil;

public class FriendControllerTest {

	private static final byte[] key = EncrptConstants.key.getBytes(StandardCharsets.UTF_8);
	private static final byte[] iv = EncrptConstants.iv.getBytes(StandardCharsets.UTF_8);

	public static void main(String[] args) throws IOException {
		String postParams = "name=jordan&age=23";
		byte[] data = EncryptionUtil.encrpt(postParams.getBytes(StandardCharsets.UTF_8), key, iv);

		URL url = new URL("http://localhost:8080/wechat-showcase-web/friend.ac?id=2");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestProperty("Content-Length", "" + data.length);
		conn.setRequestProperty("Content-type", "application/octet-stream");

		conn.setRequestMethod("POST");
		conn.connect();

		OutputStream os = conn.getOutputStream();
		os.write(data);
		os.flush();
		// os.close();
		// conn.getOutputStream().close();

		InputStream inputStream = conn.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		byte[] bytes = new byte[512];
		int len = 0;
		while ((len = inputStream.read(bytes)) > 0) {
			bos.write(bytes, 0, len);
		}

		bytes = bos.toByteArray();
		byte[] decrpt = NoPaddingEncryptor.decrpt(bytes, key, iv, true);
		byte[] unGzip = CompressUtil.unGzip(decrpt);
		System.out.println(new String(unGzip));
	}
}
