package com.wz.levelverticalviewpager.model;

/**
 * 
 * @Author LiJingHuan
 * @Email:ljh0576123@163.com
 * @CreateTime:2015-6-4 下午4:38:39
 * @Description:实体基类
 * @Modifier:
 * @ModifyContent:
 * 
 */
public class BaseEntity<T> {
	private String message = "";
	private T data;
	private int code = 0;
	private String error_msg = "";

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

}
