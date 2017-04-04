package com.hzdp.url;

import java.nio.charset.StandardCharsets;

import com.hzdp.web.constants.EncrptConstants;
import com.ming.crypto.EncryptionUtil;

public class StringTest {


	private static final byte[] key = EncrptConstants.key.getBytes(StandardCharsets.UTF_8);
	private static final byte[] iv = EncrptConstants.iv.getBytes(StandardCharsets.UTF_8);

	public static void main(String[] args) {
		String str = "微信hello world ! 微信";
		byte[] bytes = str.getBytes();
		print(bytes);
		System.out.println();
		
		String postParams = "name=jordan&age=23";
		byte[] data = EncryptionUtil.encrpt(postParams.getBytes(StandardCharsets.UTF_8), key, iv);
		print(data);

	}

	public static void print(byte[] bytes) {
		for (byte b : bytes) {
			System.out.print(b & 0xff);
			System.out.print(", ");
		}
	}
}
