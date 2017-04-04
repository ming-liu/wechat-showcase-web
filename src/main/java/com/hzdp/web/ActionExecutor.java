package com.hzdp.web;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.hzdp.web.api.IAction;
import com.hzdp.web.api.IRequest;
import com.hzdp.web.api.IResponse;

public class ActionExecutor {

	public static IResponse execute(@SuppressWarnings("rawtypes") IAction action, RequestContext context) {
		IResponse response = null;
		try {
			Type[] genericInterfaces = action.getClass().getGenericInterfaces();
			Type reqType = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments()[0];
			if (reqType instanceof Class) {
				Class actualType = (Class) reqType;
				IRequest request = (IRequest) actualType.newInstance();
				Method[] methods = actualType.getMethods();
				for (Method method : methods) {
					String name = method.getName();
					Class<?>[] parameterTypes = method.getParameterTypes();
					if (name.startsWith("set") && parameterTypes.length == 1) {
						char first = name.charAt(3);
						if (first >= 'A' && first <= 'Z') {
							first = (char) (first - ('A' - 'a'));
						}

						String propertyName = "" + first + name.substring(4);
						String propertyValue = context.get(propertyName);
						if (parameterTypes[0].isPrimitive()) {
							Class<?> clazz = parameterTypes[0];
							if (clazz == int.class) {
								method.invoke(request, Integer.parseInt(propertyValue));
							} else if (clazz == long.class) {
								method.invoke(request, Long.parseLong(propertyValue));
							} else if (clazz == float.class) {
								method.invoke(request, Float.parseFloat(propertyValue));
							} else if (clazz == double.class) {
								method.invoke(request, Double.parseDouble(propertyValue));
							} else {
								method.invoke(request, propertyValue);
							}
						} else {
							method.invoke(request, propertyValue);
						}
					}
				}
				response = action.execute(request);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return response;
	}
}
